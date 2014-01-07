package com.litt.saap.system.biz.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import com.litt.core.exception.BusiCodeException;
import com.litt.core.util.ArrayUtils;
import com.litt.core.util.BeanCopier;
import com.litt.core.util.DateUtils;
import com.litt.saap.core.common.SaapConstants;
import com.litt.saap.core.common.SaapConstants.TenantMemberStatus;
import com.litt.saap.core.common.SaapConstants.TenantStatus;
import com.litt.saap.core.module.tenant.config.TenantConfig;
import com.litt.saap.core.module.tenant.config.TenantConfigManager;
import com.litt.saap.core.module.tenant.config.TenantDefConfig;
import com.litt.saap.core.module.tenant.config.TenantRoleConfig;
import com.litt.saap.system.biz.ITenantBizService;
import com.litt.saap.system.bo.TenantActiveBo;
import com.litt.saap.system.bo.TenantQuitBo;
import com.litt.saap.system.dao.RoleFuncPermissionDao;
import com.litt.saap.system.dao.TenantDao;
import com.litt.saap.system.dao.TenantMemberDao;
import com.litt.saap.system.dao.UserRoleDao;
import com.litt.saap.system.po.Role;
import com.litt.saap.system.po.RoleFuncPermission;
import com.litt.saap.system.po.Tenant;
import com.litt.saap.system.po.TenantMember;
import com.litt.saap.system.po.UserRole;
import com.litt.saap.system.service.IRoleService;
import com.litt.saap.system.service.ITenantService;
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
	private ITenantService tenantService;	
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
	public TenantActiveBo doActivate(String orderNo, Integer userId, Locale locale)
	{
		String bagCode = "basic";	//此处应该从订单中获得购买了哪个类型的产品包
		
		//有效性检查，如果一个用户已经属于一个租户，则无法再激活第二个
		boolean isTenantMember = tenantMemberDao.isTenantMember(userId);
		if(isTenantMember)
			throw new BusiCodeException("tenant.error.isTenantMember");
		TenantDefConfig tenantDefConfig = TenantConfigManager.getInstance().getTenantDefConfig(bagCode);
				
		Tenant tenant = new Tenant();
		tenant.setCode(orderNo);
		tenant.setAppCode(SaapConstants.PLATFORM_APP_CODE);
		tenant.setAppAlias("默认别名");	//FIXME	这个值后面由用户填入
		tenant.setBagCode(bagCode);
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
		Integer adminRoleId = null;
		String[] adminPermissions = new String[0];
		TenantRoleConfig[] roleConfig = tenantDefConfig.getRoles();
		for (TenantRoleConfig tenantRoleConfig : roleConfig) {
			Role role = new Role();
			role.setTenantId(tenantId);
			role.setName(tenantRoleConfig.getCode());	//BeanManager.getMessage("tenant.config.role."+tenantRoleConfig.getCode(), locale)
			role.setStatus(9);		
			roleService.save(role);
			Integer roleId = role.getId();
			//插入角色的权限
			String[] permissions = tenantDefConfig.getPermissions();
			for (String permissionCode : permissions) {
				RoleFuncPermission entity = new RoleFuncPermission(tenantId, roleId, permissionCode);
				roleFuncPermissionDao.save(entity);
			}
			//取到管理员的权限，赋给租户拥有者
			if(tenantRoleConfig.isAdmin())
			{
				adminRoleId = roleId;
				adminPermissions = permissions;
			}
		}		
		//绑定用户和角色
		UserRole userRole = new UserRole(tenantId, userId, adminRoleId);
		userRoleDao.save(userRole);
		
		TenantVo tenantVo = new TenantVo(tenant.getId(), tenant.getCode(), tenant.getAppCode(), tenant.getAppAlias(), true, tenant.getExpiredDate());
		TenantActiveBo ret = new TenantActiveBo(tenantVo, adminRoleId, adminPermissions);
		
		return ret;
	}
	
	/**
	 * 升级用户权限.
	 * 
	 * 系统升级后，可能增加或移除的一些功能，需要对现有用户的权限做相应的升级操作。
	 *
	 * @param tenantConfig the tenant config
	 * @return the tenant active bo
	 */
	public void doUpgradePermission(TenantConfig tenantConfig)
	{
		List<Tenant> tenantList = tenantDao.listAll();
		for (Tenant tenant : tenantList) {
			Integer tenantId = tenant.getId();
			List<Role> roleList = roleService.listByTenant(tenantId);
			
			TenantDefConfig tenantDefConfig = TenantConfigManager.getInstance().getTenantDefConfig(tenant.getBagCode());
			
			for (Role role : roleList) {
				Integer roleId = role.getId();			
				//获取所有的Role及对应的permission
				List<RoleFuncPermission> permissionList = roleFuncPermissionDao.listByTenantAndRole(tenantId, roleId);
				
				List<RoleFuncPermission> deleteList = new ArrayList<RoleFuncPermission>();
				for (RoleFuncPermission roleFuncPermission : permissionList) 
				{
					if(!tenantDefConfig.containsPermission(roleFuncPermission.getPermissionCode()))
					{
						//该权限需删除
						deleteList.add(roleFuncPermission);
					}
				}
				//把新增的权限加到现有角色上
				if(role.getStatus()==9)	//系统内置角色
				{			
					TenantDefConfig newTenantDefConfig = tenantConfig.getTenantDef(tenant.getBagCode());
					
					TenantRoleConfig oldRoleConfig = tenantDefConfig.getRoleByCode(role.getName());
					TenantRoleConfig newRoleConfig = newTenantDefConfig.getRoleByCode(role.getName());
					//对比两个配置，获得新的权限编号
					List<String> addPermissionList = new ArrayList<String>();
					String[] oldPermissions = oldRoleConfig.getPermissions();
					String[] newPermissions = newRoleConfig.getPermissions();
					for (String permissionCode : newPermissions) {
						if(!ArrayUtils.contains(oldPermissions, permissionCode))
						{
							addPermissionList.add(permissionCode);
						}
					}
					//保存新的			
					if(!addPermissionList.isEmpty())
					{
						RoleFuncPermission[] newEntities = new RoleFuncPermission[addPermissionList.size()]; 
						int i=0;
						for (String permissionCode : addPermissionList) {
							RoleFuncPermission entity = new RoleFuncPermission(tenantId, roleId, permissionCode);
							newEntities[i] = entity;
							i++;
						}
						roleFuncPermissionDao.saveBatch(newEntities);
					}
					
				}
			}					
		}
		//重新加载当前配置实例，变成最新的
		TenantConfigManager.getInstance().reload();
	}
	
	/**
	 * 注销租户空间.
	 *
	 * @param userId 用户ID
	 * @param tenantId 租户ID
	 * @return the tenant quit bo
	 */
	public TenantQuitBo doDeactivate(Integer userId, Integer tenantId)
	{		
		Tenant tenant = tenantDao.load(tenantId);
		if(tenant==null || tenant.getCreateUserId()!=userId)	
		{
			throw new BusiCodeException("tenant.action.deactivate.error.notCreator");
		}
		//退出用户是租户创建人，则允许是解散租户
		TenantVo tenantVo = BeanCopier.copy(tenant, TenantVo.class);
		TenantQuitBo tenantQuit = getTenantQuitBo(userId, tenantVo);		
		
		//TODO 需要通知已经在线的租户成员空间被注销，并删除他们的权限
		List<TenantMember> tenantMemberList = tenantMemberDao.listByTenant(tenantId);
		for (TenantMember tenantMember : tenantMemberList) {
			//if(!tenantMember.getUserId().equals(tenant.getCreateUserId()))	//保留创建人，其他成员均删除
			{
				//删除用户角色
				userRoleDao.deleteByTenantUser(tenantId, tenantMember.getUserId());
				//删除成员信息
				tenantMemberDao.delete(tenantMember);
			}
		}		
		//最终设置租户状态为删除
		tenant.setStatus(TenantStatus.CANCELED);
		tenantService.update(tenant);
		
		return tenantQuit;
	}
	
	public TenantQuitBo doQuit(int tenantId, int userId)
	{		
		TenantVo tenantVo = tenantService.findById(tenantId);
		TenantMember tenantMember = tenantMemberDao.loadByUserAndTenant(userId, tenantId);
		if(tenantMember==null)	//如果不是该租户成员，则直接返回
			return null;
		//退出用户是租户创建人，则是解散租户
		if(tenantMember.getCreateUserId()==userId)	
		{
			
		}
		//检查是否还剩其他管理员存在，没有的话禁止退出，否则无法对租户进行管理		
		if(tenantMember.getIsAdmin())
		{
			int adminCount = tenantMemberDao.countAdminByTenant(tenantId);
			if(adminCount==1)
				throw new BusiCodeException("tenant.error.lastAdmin");
		}
		//执行退出操作
		tenantMemberDao.delete(tenantMember);
		//读取该用户在该租户空间下的权限，需要删除
		TenantQuitBo tenantQuit = getTenantQuitBo(userId, tenantVo);
		
		//删除用户角色
		userRoleDao.deleteByTenantUser(tenantId, userId);
		return tenantQuit;
	}

	/**
	 * @param tenantId
	 * @param userId
	 * @param tenantVo
	 * @return
	 */
	private TenantQuitBo getTenantQuitBo(int userId, TenantVo tenantVo) {
		int tenantId = tenantVo.getId();
		List<UserRole> userRoleList = userRoleDao.listByTenantUser(tenantId, userId);
		Integer[] roleIds = new Integer[userRoleList.size()];
		int index = 0;
		for (UserRole userRole : userRoleList) {
			roleIds[index] = userRole.getRoleId();
			index++;
		}				
		
		TenantDefConfig tenantDefConfig = TenantConfigManager.getInstance().getTenantDefConfig("basic");
		String[] permissions = tenantDefConfig.getPermissions();
				
		TenantQuitBo tenantQuit = new TenantQuitBo(tenantVo, roleIds, permissions);
		return tenantQuit;
	}
	
	

}
