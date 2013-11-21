package com.litt.saap.system.biz.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import javax.annotation.Resource;
import javax.mail.MessagingException;

import org.apache.commons.lang.LocaleUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AopContext;
import org.springframework.ui.context.Theme;

import com.litt.core.common.BeanManager;
import com.litt.core.common.Utility;
import com.litt.core.exception.BusiCodeException;
import com.litt.core.exception.BusiException;
import com.litt.core.security.EncryptFailedException;
import com.litt.core.security.MessageDigestTool;
import com.litt.core.util.ArrayUtils;
import com.litt.core.util.Assert;
import com.litt.core.util.BeanCopier;
import com.litt.core.util.JsonUtils;
import com.litt.saap.common.service.IEmailService;
import com.litt.saap.common.service.ITemplateService;
import com.litt.saap.common.vo.LoginUserVo;
import com.litt.saap.common.vo.TenantUserVo;
import com.litt.saap.core.common.SaapConstants;
import com.litt.saap.system.biz.IUserBizService;
import com.litt.saap.system.dao.TenantDao;
import com.litt.saap.system.dao.TenantMemberDao;
import com.litt.saap.system.dao.UserGroupDao;
import com.litt.saap.system.dao.UserGroupRoleDao;
import com.litt.saap.system.dao.UserInfoDao;
import com.litt.saap.system.dao.UserRoleDao;
import com.litt.saap.system.dao.UserStateDao;
import com.litt.saap.system.po.ActivationCode;
import com.litt.saap.system.po.RoleFuncPermission;
import com.litt.saap.system.po.Tenant;
import com.litt.saap.system.po.TenantMember;
import com.litt.saap.system.po.UserInfo;
import com.litt.saap.system.po.UserRole;
import com.litt.saap.system.po.UserState;
import com.litt.saap.system.service.ISystemInfoService;
import com.litt.saap.system.service.ITenantService;
import com.litt.saap.system.service.IUserInfoService;
import com.litt.saap.system.service.impl.IActivationCodeService;
import com.litt.saap.system.vo.SystemInfoVo;
import com.litt.saap.system.vo.TenantVo;
import com.litt.saap.system.vo.UserInfoVo;

/**
 * .
 * 
 * <pre><b>描述：</b>
 *    
 * </pre>
 * 
 * <pre><b>修改记录：</b>
 *    
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">蔡源</a>
 * @since 2013-10-17
 * @version 1.0
 */
public class UserBizServiceImpl implements IUserBizService {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory.getLogger(UserBizServiceImpl.class);	
		
	@Resource
	private UserInfoDao userInfoDao;
	
	@Resource
	private UserStateDao userStateDao;
	
	@Resource
	private UserRoleDao userRoleDao;
	
	@Resource
	private UserGroupDao userGroupDao;
	
	@Resource
	private UserGroupRoleDao userGroupRoleDao;
	
	@Resource
	private TenantDao tenantDao;
	
	@Resource
	private TenantMemberDao tenantMemberDao;
	@Resource
	private ITenantService tenantService;
	
	@Resource
	private IUserInfoService userInfoService;
	
	@Resource
	private IActivationCodeService activationCodeService;
	
	@Resource
	private IEmailService emailService;
	
	@Resource
	private ITemplateService templateService;
	
	@Resource
	private ISystemInfoService systemInfoService;
	
	/**
	 * 用户注册.
	 *
	 * @param loginId the login id
	 * @param password the password
	 * @param email the email
	 * @param loginIp the login ip
	 * @param locale the locale
	 * @param timeZone the time zone
	 * @param theme the theme
	 */
	public UserInfoVo doRegister(String loginId, String password, String email, String loginIp, Locale locale, TimeZone timeZone, Theme theme)
	{
		UserInfoVo vo = userInfoService.doRegister(loginId, password, email, loginIp, locale, timeZone, theme);
		//发送激活邮件
		//获得系统信息
		SystemInfoVo systemInfo = systemInfoService.getSystemInfo();
		
		//生成激活码
		ActivationCode activationCode = activationCodeService.gen(vo.getId(), 24 * 60 * 60 * 1000);	//超时时间24小时
		
		Map<String, Object> propMap = new HashMap<String, Object>();
		propMap.put("email", email);
		propMap.put("url", systemInfo.getBaseUrl());
		propMap.put("code", activationCode.getId());
		propMap.put("systemName", systemInfo.getSystemName());
		
		//String content = templateService.genString("register", propMap);
				
		try {
			String content = BeanManager.getMessage("register.email.content", new Object[]{email, activationCode.getId(), systemInfo.getBaseUrl(), systemInfo.getSystemName()}, locale);
			
			emailService.sendMime(email, BeanManager.getMessage("register.email.subject", new Object[]{systemInfo.getSystemName()}, locale), content, content);
		} catch (MessagingException e) {
			logger.error("User register email sent failed.", e);
		}
		
		return vo;
	}
	
	/**
	 * 邀请用户加入.
	 *
	 * @param userId 发起用户
	 * @param email the email 目标抵制
	 * @param locale the locale
	 * @param timeZone the time zone
	 * @param theme the theme
	 */
	public void doInvite(Integer inviterUserId, Integer targetRoleId, String email, Locale locale, TimeZone timeZone, Theme theme)
	{		
		UserInfo userInfo = userInfoService.load(inviterUserId);	//发起邀请用户
		TenantMember tenantMember = tenantMemberDao.load(inviterUserId, SaapConstants.PLATFORM_APP_ID);	//发起用户所在的租户
		
		//检查激活码中的参数是否有效
		Assert.notNull(inviterUserId, BeanManager.getMessage("invite.error.inviterIsNull", locale));
		Assert.notNull(tenantMember, BeanManager.getMessage("invite.error.inviterIsNotOwner", locale));
		Assert.notNull(targetRoleId, BeanManager.getMessage("invite.error.targetRoleIsNull", locale));
		
		Tenant tenant = tenantDao.load(tenantMember.getTenantId());
		//获得系统信息
		SystemInfoVo systemInfo = systemInfoService.getSystemInfo();		
		
		//生成激活码
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("inviterUserId", inviterUserId);
		paramMap.put("tenantId", tenant.getId());
		paramMap.put("roleId", targetRoleId);
		ActivationCode activationCode = activationCodeService.gen(inviterUserId, 30 * 24 * 60 * 60 * 1000, paramMap);	//超时时间30天
		
		UserInfo targetUserInfo = userInfoService.loadByEmail(email);
		if(targetUserInfo!=null)	//注册用户，额外发送一条消息，方便站内激活
		{
			
		}
		//发送激活邮件
		try {
			Map<String, Object> propMap = new HashMap<String, Object>();
			propMap.put("activationCode", activationCode);
			propMap.put("email", email);
			propMap.put("tenantName", tenant.getAppAlias());
			propMap.put("sourceUser", userInfo);
			propMap.put("url", systemInfo.getBaseUrl());
			propMap.put("systemName", systemInfo.getSystemName());
			
			String content = templateService.genString("invite", propMap);			
			emailService.sendMime(email, BeanManager.getMessage("invite.email.subject", new Object[]{systemInfo.getSystemName(), userInfo.getUserName()}, locale), content, content);
		} catch (MessagingException e) {
			logger.error("User invite email sent failed.", e);
		}
	}
	
	/**
	 * 加入.
	 *
	 * @param code the code 激活码
	 * @param source the source 发起用户邮件地址
	 * @param account the account 目标用户邮件地址
	 * @return the login user vo
	 */
	public LoginUserVo doJoin(String loginId, String password, String email, String loginIp, String code, Locale locale, TimeZone timeZone, Theme theme)
	{
		ActivationCode activationCode = activationCodeService.loadAndDelete(code);
		if(activationCode==null)
			throw new BusiCodeException("activate.error.codeNotExist");
		if(activationCode.isExpired())	//激活码已过期，删除用户账号，由用户重新注册
		{			
			throw new BusiCodeException("activate.error.expired");
		}
		//从激活码中获得动态参数
		Map<String, Object> paramMap;
		try {
			paramMap = JsonUtils.toObject(activationCode.getParams(), Map.class);
		} catch (IOException e) {
			throw new BusiException("convert activation code's params error", e);
		}		
		
		Integer inviterUserId = Utility.parseInt(paramMap.get("inviterUserId").toString());
		Integer targetRoleId = Utility.parseInt(paramMap.get("targetRoleId").toString());
		Integer tenantId = Utility.parseInt(paramMap.get("tenantId").toString());
		
	
		//检查邮件用户是否存在
		UserInfo targetUser = userInfoService.loadByEmail(email);
		if(targetUser==null)
		{
			//创建用户
			UserInfoVo vo = userInfoService.doRegister(loginId, password, email, loginIp, locale, timeZone, theme);
			Integer userId = vo.getId();
			
			//直接激活用户
			UserInfo userInfo = userInfoService.load(userId);
			userInfo.setStatus(SaapConstants.UserStatus.NORMAL);
			userInfoService.update(userInfo);
			//添加默认的个人事务角色（自动赋予权限）
			UserRole userRole = new UserRole(0, userId, SaapConstants.DEFAULT_ROLE_ID);
			userRoleDao.save(userRole);
			
			targetUser = userInfo;
		}
		else 
		{
			//检查目标用户是否已属于某个租户，是的话则不能加入
			boolean isTenantMember = tenantService.isTenantMember(targetUser.getId());
			if(isTenantMember)
			{
				throw new BusiCodeException("join.error.isTenantMember");
			}
		}
		
		
		//读取邀请人信息
		//UserInfo inviterUser = userInfoService.load(inviterUserId);
		//根据邀请人信息获得租户信息
		//Tenant tenant = tenantDao.load(tenantId);		
		//把用户加入到邀请用户的租户空间		
		tenantService.doJoin(tenantId, targetUser.getId(), targetRoleId);
		
		//进行登录
		LoginUserVo loginUser = this.doLogin(targetUser, loginIp, false, LocaleUtils.toLocale(targetUser.getLocale()));	
		return loginUser;
	}
	
	/**
	 * 用户账号激活.
	 * @param email
	 * @param code
	 */
	public LoginUserVo doActivate(String code, String loginIp)
	{
		Assert.notEmpty(code);
		logger.info("User activate, code:{}", new Object[]{code});
		
		ActivationCode activationCode = activationCodeService.loadAndDelete(code);
		if(activationCode==null)
			throw new BusiCodeException("activate.error.codeNotExist");
		if(activationCode.isExpired())	//激活码已过期，删除用户账号，由用户重新注册
		{
			//*** 这里需要获取代理类，以实现嵌套事务
			IUserBizService proxyService = (IUserBizService)AopContext.currentProxy();
			//删除用户账号，已保证用户可以重新注册
			proxyService.newTranDeleteUser(activationCode);			
			
			throw new BusiCodeException("activate.error.expired");
		}
		else {
			UserInfo userInfo = userInfoService.load(activationCode.getUserId());
			if(userInfo.getStatus()==SaapConstants.UserStatus.UNAUTHERIZED)
			{
				userInfo.setStatus(SaapConstants.UserStatus.NORMAL);
				userInfoService.update(userInfo);
				
				//添加默认的个人事务角色（自动赋予权限）
				UserRole userRole = new UserRole(0, userInfo.getId(), SaapConstants.DEFAULT_ROLE_ID);
				userRoleDao.save(userRole);
				
				//进行登录			
				LoginUserVo loginUser = this.doLogin(userInfo, loginIp, false, LocaleUtils.toLocale(userInfo.getLocale()));						
				
				return loginUser;
				
			}
			else 
			{
				throw new BusiCodeException("activate.error.activated");
			}
		}
		
	}
	
	/**
	 * 启用新事务删除用户.
	 *
	 * @param userId the user id
	 */
	public void newTranDeleteUser(ActivationCode activationCode)
	{		
		//删除用户注册信息
		UserInfo userInfo = userInfoService.load(activationCode.getUserId());		
		if(userInfo!=null)
		{
			userInfoService.delete(userInfo);
		}
	}

	/**
	 * 找回密码.
	 *
	 * @param email the email
	 * @param loginIp the login ip
	 * @param locale the locale
	 */
	public void doForgetPassword(String email, String loginIp, Locale locale)
	{
		UserInfo userInfo = userInfoService.loadByEmail(email);
		if(userInfo == null)
		{
			throw new BusiCodeException("forgetPassword.error.emailNotExist", locale);
		}
		//发送找回密码邮件给该用户
		
	}
	
	/**
	 * 重置密码.
	 *
	 * @param id the id
	 * @param password 新密码
	 * @param loginIp 客户端IP
	 * @param locale the locale
	 */
	public void doResetPassword(String id, String password, String loginIp, Locale locale)
	{		
		ActivationCode forgetPassword = userInfoService.loadForgetPassword(id, locale);
				
		UserInfo userInfo = userInfoService.load(forgetPassword.getUserId());
		if(userInfo==null)
		{
			throw new BusiCodeException("error.UserInfo.deleted");	
		}
		//设置新密码
		//MD5加密密码
		String encryptPassowrd = userInfo.getPassword();
		try
		{
			encryptPassowrd = MessageDigestTool.encryptMD5(encryptPassowrd);
			userInfo.setPassword(encryptPassowrd);
			
			logger.info("User:{} reset password from {}.", new Object[]{userInfo.getEmail(), loginIp});
		}
		catch (EncryptFailedException e)
		{
			throw new BusiCodeException("forgetPassword.error.resetFailed", LocaleUtils.toLocale(userInfo.getLocale()), e);
		}	
	}
	
	/**
	 * Do login.
	 *
	 * @param loginId the login id
	 * @param password the password
	 * @param loginIp the login ip
	 * @param isAutoLogin the is auto login
	 * @param locale the locale
	 * @return the login vo
	 * @throws BusiException the busi exception
	 */
	public LoginUserVo doLogin(String loginId, String password, String loginIp, boolean isAutoLogin, Locale locale) throws BusiException
	{
		LoginUserVo loginUser = userInfoService.doLogin(loginId, password, loginIp, isAutoLogin, locale);
		//初始化全局权限
		initDefaultPermission(loginUser);
		//初始化租户相关
		initLoginUserTenant(loginUser);		
		
		return loginUser;
	}
	
	/**
	 * 注册激活或其他通过链接，非密码方式登录.
	 *
	 * @param userInfo the user info
	 * @param loginIp the login ip
	 * @param isAutoLogin the is auto login
	 * @param locale the locale
	 * @return the login user vo
	 * @throws BusiException the busi exception
	 */
	private LoginUserVo doLogin(UserInfo userInfo, String loginIp, boolean isAutoLogin, Locale locale) throws BusiException
	{
		UserState userState = userStateDao.load(userInfo.getId());
		LoginUserVo loginUser = userInfoService.doLogin(userInfo, userState, loginIp, isAutoLogin, locale);
		//初始化全局权限
		initDefaultPermission(loginUser);
		//初始化租户相关
		initLoginUserTenant(loginUser);		
		
		return loginUser;
	}

	/**
	 * @param loginUser
	 */
	private void initDefaultPermission(LoginUserVo loginUser) {
		String[] permissionCodes = this.getUserDefaultPermission(loginUser.getOpId().intValue());
		loginUser.initPermission(permissionCodes);
	}

	/**
	 * 初始化登录用户租户相关内容.
	 *
	 * @param loginUser the login user
	 */
	private void initLoginUserTenant(LoginUserVo loginUser) {
		//获取该用户的租户信息		
		TenantMember tenantMember = tenantMemberDao.load(loginUser.getOpId().intValue(), SaapConstants.PLATFORM_APP_ID);
		if(tenantMember!=null)
		{
			Tenant tenant = tenantDao.load(tenantMember.getTenantId());
		
			TenantVo tenantVo = new TenantVo(tenant.getId(), tenant.getCode(), tenant.getAppCode(), tenantMember.getIsAdmin(), tenant.getExpiredDate());
			tenantVo.setExpiredDate(tenant.getExpiredDate());
			
			loginUser.setTenant(tenantVo);		
			
			//获取租户权限
			//List<UserRole> userRoleList = userRoleDao.listByUserTenant(loginUser.getOpId().intValue(), tenantMember.getTenantId());
			
			String[] permissionCodes = this.getUserTenantPermission(loginUser.getOpId().intValue(), tenantMember.getTenantId());
			loginUser.addPermissions(permissionCodes);
		}
	}
	
	/**
	 * 自动登录.
	 *
	 * @param token the token
	 * @param loginIp 请求IP
	 * @param locale 语言
	 * @return LoginVo
	 */
	public LoginUserVo doAutoLogin(String token, String loginIp, Locale locale)
	{
		LoginUserVo loginUser = userInfoService.doAutoLogin(token, loginIp, locale);
		initLoginUserTenant(loginUser);		
		
		return loginUser;
	}
	
	/**
	 * 获得用户默认权限.
	 *
	 * @param userId the user id
	 * @return the user tenant permission
	 */
	private String[] getUserDefaultPermission(int userId)
	{
		//获取用户直接角色的权限
		String listHql = "select rfp from UserRole ur, RoleFuncPermission rfp where ur.tenantId=? and ur.userId=? and ur.roleId=rfp.roleId and rfp.tenantId=ur.tenantId";
		List<RoleFuncPermission> roleFuncPermissionList = userRoleDao.listAll(listHql, new Object[]{0, userId});		
		String[] permissionCodes = new String[roleFuncPermissionList.size()];
		for (int i=0; i<roleFuncPermissionList.size(); i++ ) {
			RoleFuncPermission roleFuncPermission = roleFuncPermissionList.get(i);
			permissionCodes[i] = roleFuncPermission.getPermissionCode();
		}		
		
		return permissionCodes;
	}
	
	/**
	 * 获得用户所在租赁区的权限.
	 *
	 * @param userId the user id
	 * @param tenantId the tenant id
	 * @return the user tenant permission
	 */
	private String[] getUserTenantPermission(int userId, int tenantId)
	{
		//获取用户直接角色的权限
		String listHql = "select rfp from UserRole ur, RoleFuncPermission rfp where ur.tenantId=? and ur.userId=? and ur.roleId=rfp.roleId and rfp.tenantId=ur.tenantId";
		List<RoleFuncPermission> roleFuncPermissionList = userRoleDao.listAll(listHql, new Object[]{tenantId, userId});		
		String[] permissionCodesA = new String[roleFuncPermissionList.size()];
		for (int i=0; i<roleFuncPermissionList.size(); i++ ) {
			RoleFuncPermission roleFuncPermission = roleFuncPermissionList.get(i);
			permissionCodesA[i] = roleFuncPermission.getPermissionCode();
		}
		
		//获取用户所在组角色的权限
		listHql = "select rfp from UserGroupMember ugm, UserGroup ug, UserGroupRole ugr, RoleFuncPermission rfp where ugm.tenantId=? and ugm.userId=? and ugm.tenantId=ug.tenantId and ugm.groupId=ug.id and ug.id=ugr.groupId and ug.tenantId=ugr.tenantId and ugr.roleId=rfp.roleId and rfp.tenantId=ugr.tenantId";
		roleFuncPermissionList = userRoleDao.listAll(listHql, new Object[]{tenantId, userId});
		String[] permissionCodesB = new String[roleFuncPermissionList.size()];
		for (int i=0; i<roleFuncPermissionList.size(); i++ ) {
			RoleFuncPermission roleFuncPermission = roleFuncPermissionList.get(i);
			permissionCodesB[i] = roleFuncPermission.getPermissionCode();
		}
		
		String[] permissionCodes = (String[])ArrayUtils.addAll(permissionCodesA, permissionCodesB);
		
		return permissionCodes;
	}
	
	/* (non-Javadoc)
	 * @see com.litt.saap.system.biz.IUserBizService#findByTenant(int)
	 */
	public List<TenantUserVo> findByTenant(int tenantId)
	{
		String listHql = "select new map(userInfo as userInfo, tenantMember as tenantMember) from UserInfo userInfo, TenantMember tenantMember where userInfo.id=tenantMember.userId and tenantMember.tenantId=?";
		List<Map<String, Object>> rsList = userInfoDao.listAll(listHql, new Object[]{tenantId});		
		
		List<TenantUserVo> retList = new ArrayList<TenantUserVo>(rsList.size());
		for (Map<String, Object> map : rsList) {
			
			UserInfo userInfo = (UserInfo)map.get("userInfo");
			TenantMember tenantMember = (TenantMember)map.get("tenantMember");
			
			TenantUserVo tenantUser = new TenantUserVo();
			BeanCopier.copy(userInfo, tenantUser);
			
			tenantUser.setTenantId(tenantId);
			
			retList.add(tenantUser);
		}
		return retList;
	}

}
