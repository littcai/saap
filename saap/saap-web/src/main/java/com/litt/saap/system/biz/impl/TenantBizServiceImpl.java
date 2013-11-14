package com.litt.saap.system.biz.impl;

import java.util.Date;

import javax.annotation.Resource;

import com.litt.core.util.DateUtils;
import com.litt.saap.core.common.SaapConstants;
import com.litt.saap.core.common.SaapConstants.TenantMemberStatus;
import com.litt.saap.core.common.SaapConstants.TenantStatus;
import com.litt.saap.core.module.tenant.config.TenantConfigManager;
import com.litt.saap.core.module.tenant.config.TenantDefConfig;
import com.litt.saap.system.biz.ITenantBizService;
import com.litt.saap.system.bo.TenantActiveBo;
import com.litt.saap.system.dao.RoleFuncPermissionDao;
import com.litt.saap.system.dao.TenantDao;
import com.litt.saap.system.dao.TenantMemberDao;
import com.litt.saap.system.dao.UserRoleDao;
import com.litt.saap.system.po.Role;
import com.litt.saap.system.po.RoleFuncPermission;
import com.litt.saap.system.po.Tenant;
import com.litt.saap.system.po.TenantMember;
import com.litt.saap.system.po.UserRole;
import com.litt.saap.system.service.impl.IRoleService;
import com.litt.saap.system.vo.TenantVo;

/**
 * 租户业务服务层实现.
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
 * @since 2013-11-14
 * @version 1.0
 */
public class TenantBizServiceImpl implements ITenantBizService {
	
	@Resource
	private TenantDao tenantDao;
	@Resource
	private TenantMemberDao tenantMemberDao;
	@Resource
	private IRoleService roleService;
	@Resource
	private RoleFuncPermissionDao roleFuncPermissionDao;
	@Resource
	private UserRoleDao userRoleDao;
	
	/* (non-Javadoc)
	 * @see com.litt.saap.system.biz.impl.ITenantBizService#activate(java.lang.String, java.lang.Integer)
	 */
	public TenantActiveBo doActivate(String orderNo, Integer userId)
	{
		TenantDefConfig tenantDefConfig = TenantConfigManager.getInstance().getTenantDefConfig("basic");
				
		Tenant tenant = new Tenant();
		tenant.setCode(orderNo);
		tenant.setAppCode(SaapConstants.PLATFORM_APP_CODE);
		tenant.setAppAlias("默认别名");
		tenant.setStatus(TenantStatus.NORMAL);	//直接激活		
		tenant.setId(tenant.getCreateUserId());
		tenant.setMaxMembers(tenantDefConfig.getMaxMembers());		
		Date expiredDate = DateUtils.getBeAfMonth(1);
		tenant.setExpiredDate(expiredDate);		//默认都一个月
		tenant.setCreateUserId(userId);
		tenant.setCreateDatetime(new Date());
		tenant.setUpdateDatetime(tenant.getCreateDatetime());
		
		Integer tenantId = tenantDao.save(tenant);
		//保存租户成员
		TenantMember tenantMember = new TenantMember();
		tenantMember.setAppId(SaapConstants.PLATFORM_APP_ID);
		tenantMember.setTenantId(tenantId);
		tenantMember.setUserId(userId);
		tenantMember.setIsAdmin(true);
		tenantMember.setCreateUserId(userId);
		tenantMember.setCreateDatetime(tenant.getCreateDatetime());
		tenantMember.setUpdateUserId(userId);
		tenantMember.setUpdateDatetime(tenantMember.getCreateDatetime());
		tenantMember.setStatus(TenantMemberStatus.NORMAL);
		
		tenantMemberDao.save(tenantMember);
		
		
		//对租户插入默认角色
		Role tenantAdmin = new Role();
		tenantAdmin.setTenantId(tenantId);
		tenantAdmin.setName("tenantAdmin");
		tenantAdmin.setStatus(1);		
		roleService.save(tenantAdmin);
		Integer roleId = tenantAdmin.getId();
		//插入角色的权限
		String[] permissions = tenantDefConfig.getPermissions();
		for (String permissionCode : permissions) {
			RoleFuncPermission entity = new RoleFuncPermission(tenantId, roleId, permissionCode);
			roleFuncPermissionDao.save(entity);
		}
		//绑定用户和角色
		UserRole userRole = new UserRole(tenantId, userId, roleId);
		userRoleDao.save(userRole);
		
		TenantVo tenantVo = new TenantVo(tenant.getId(), tenant.getCode(), tenant.getAppCode(), true, tenant.getExpiredDate());
		TenantActiveBo ret = new TenantActiveBo(tenantVo, roleId, permissions);
		
		return ret;
	}

}
