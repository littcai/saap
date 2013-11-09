package com.litt.saap.system.biz.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import org.joda.time.DateTime;

import com.litt.core.exception.BusiException;
import com.litt.core.exception.ErrorCode;
import com.litt.core.shield.vo.AutoLoginToken;
import com.litt.core.util.ArrayUtils;
import com.litt.core.util.BeanCopier;
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
import com.litt.saap.system.po.RoleFuncPermission;
import com.litt.saap.system.po.Tenant;
import com.litt.saap.system.po.TenantMember;
import com.litt.saap.system.po.UserInfo;
import com.litt.saap.system.po.UserRole;
import com.litt.saap.system.po.UserState;
import com.litt.saap.system.service.IUserInfoService;
import com.litt.saap.system.vo.TenantVo;

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
