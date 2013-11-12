package com.litt.saap.system.biz.impl;

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
import org.springframework.aop.support.AopUtils;
import org.springframework.ui.context.Theme;

import com.litt.core.common.BeanManager;
import com.litt.core.exception.BusiCodeException;
import com.litt.core.exception.BusiException;
import com.litt.core.security.EncryptFailedException;
import com.litt.core.security.MessageDigestTool;
import com.litt.core.util.ArrayUtils;
import com.litt.core.util.Assert;
import com.litt.core.util.BeanCopier;
import com.litt.saap.common.service.IEmailService;
import com.litt.saap.common.service.ITemplateService;
import com.litt.saap.common.vo.LoginUserVo;
import com.litt.saap.common.vo.TenantUserVo;
import com.litt.saap.core.common.SaapConstants;
import com.litt.saap.system.biz.IUserBizService;
import com.litt.saap.system.dao.ActivationCodeDao;
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
import com.litt.saap.system.po.UserState;
import com.litt.saap.system.service.ISystemInfoService;
import com.litt.saap.system.service.IUserInfoService;
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
	private IUserInfoService userInfoService;
	
	@Resource
	private ActivationCodeDao activationCodeDao;
	
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
		ActivationCode activationCode = activationCodeDao.gen(vo.getId(), 24 * 60 * 60 * 1000);	//超时时间24小时
		
		Map<String, Object> propMap = new HashMap<String, Object>();
		propMap.put("email", email);
		propMap.put("url", systemInfo.getBaseUrl());
		propMap.put("code", activationCode.getId());
		propMap.put("systemName", systemInfo.getSystemName());
		
		//String content = templateService.genString("register", propMap);
				
		try {
			String content = BeanManager.getMessage("email.register.content", new Object[]{email, activationCode.getId(), systemInfo.getBaseUrl(), systemInfo.getSystemName()}, locale);
			
			emailService.sendMime(email, BeanManager.getMessage("email.register.subject", new Object[]{systemInfo.getSystemName()}, locale), content, content);
		} catch (MessagingException e) {
			logger.error("User register email sent failed.", e);
		}
		
		return vo;
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
		
		ActivationCode activationCode = activationCodeDao.load(code);
		if(activationCode==null)
			throw new BusiCodeException("activate.error.codeNotExist");
		if(activationCode.isExpired())	//激活码已过期，删除用户账号，由用户重新注册
		{
			System.out.println(AopUtils.isAopProxy(this));
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
				
				//删除激活码
				activationCodeDao.delete(activationCode);				
				
				//进行登录
				UserState userState = userStateDao.load(userInfo.getId());
				
				LoginUserVo loginUserVo = userInfoService.doLogin(userInfo, userState, loginIp, false, LocaleUtils.toLocale(userInfo.getLocale()));
				return loginUserVo;
				
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
		//删除激活码
		activationCodeDao.delete(activationCode);
		
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
		initLoginUserTenant(loginUser);		
		
		return loginUser;
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
		
			TenantVo tenantVo = new TenantVo(tenant.getId());
			tenantVo.setExpiredDate(tenant.getExpiredDate());
			
			loginUser.setTenant(tenantVo);		
			
			//获取租户权限
			//List<UserRole> userRoleList = userRoleDao.listByUserTenant(loginUser.getOpId().intValue(), tenantMember.getTenantId());
			
			String[] permissionCodes = this.getUserTenantPermission(loginUser.getOpId().intValue(), tenantMember.getTenantId());
			loginUser.initPermission(permissionCodes);
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
		List<RoleFuncPermission> roleFuncPermissionList = userRoleDao.listAll(listHql, new Object[]{userId, tenantId});		
		String[] permissionCodesA = new String[roleFuncPermissionList.size()];
		for (int i=0; i<roleFuncPermissionList.size(); i++ ) {
			RoleFuncPermission roleFuncPermission = roleFuncPermissionList.get(i);
			permissionCodesA[i] = roleFuncPermission.getPermissionCode();
		}
		
		//获取用户所在组角色的权限
		listHql = "select rfp from UserGroupMember ugm, UserGroup ug, UserGroupRole ugr, RoleFuncPermission rfp where ugm.tenantId=? and ugm.userId=? and ugm.tenantId=ug.tenantId and ugm.groupId=ug.id and ug.id=ugr.groupId and ug.tenantId=ugr.tenantId and ugr.roleId=rfp.roleId and rfp.tenantId=ugr.tenantId";
		roleFuncPermissionList = userRoleDao.listAll(listHql, new Object[]{userId, tenantId});
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
