package com.litt.saap.system.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.litt.core.dao.page.IPageList;
import com.litt.core.dao.ql.PageParam;
import com.litt.core.util.ArrayUtils;
import com.litt.saap.core.common.SaapConstants.RoleStatus;
import com.litt.saap.core.web.util.LoginUtils;
import com.litt.saap.system.dao.BizRoleDao;
import com.litt.saap.system.dao.RoleFuncPermissionDao;
import com.litt.saap.system.po.BizRole;
import com.litt.saap.system.po.Role;
import com.litt.saap.system.po.RoleFuncPermission;
import com.litt.saap.system.service.IBizRoleService;

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
 * @since 2013-11-14
 * @version 1.0
 */
public class BizRoleServiceImpl implements IBizRoleService {
	
	@Resource
	private BizRoleDao bizRoleDao;
	@Resource
	private RoleFuncPermissionDao roleFuncPermissionDao;
	
	/* (non-Javadoc)
	 * @see com.litt.saap.system.service.impl.IRoleService#save(com.litt.saap.system.po.Role)
	 */
	public BizRole save(BizRole role, String[] permissionCodes)
	{		
		role.setCreateBy(LoginUtils.getLoginOpId().intValue());
		role.setCreateDatetime(new Date());
		role.setUpdateBy(role.getCreateBy());
		role.setUpdateDatetime(role.getCreateDatetime());
		
		Integer roleId = bizRoleDao.save(role);
		
//		List<RoleFuncPermission> permissionList = new ArrayList<RoleFuncPermission>(permissionCodes.length);
//		for (String permissionCode : permissionCodes) {
//			RoleFuncPermission permission = new RoleFuncPermission();
//			permission.setTenantId(role.getTenantId());
//			permission.setRoleId(roleId);
//			permission.setPermissionCode(permissionCode);
//			
//			permissionList.add(permission);
//		}
//		roleFuncPermissionDao.saveBatch(permissionList);
		
		return role;
	}
	
	/**
	 * Update.
	 * @param role Role
	 */
	public void update(BizRole role, String[] permissionCodes) 
	{
		update(role);
		
		List<RoleFuncPermission> permissionList = roleFuncPermissionDao.listByTenantAndRole(role.getTenantId(), role.getId());
		
		//判重，不存在的删除，新增的追加
		List<RoleFuncPermission> delList = new ArrayList<RoleFuncPermission>();
		List<RoleFuncPermission> newList = new ArrayList<RoleFuncPermission>();
		
		String[] dbPermissionCodes = new String[permissionList.size()];
		//检查旧的
		for (int i=0;i<permissionList.size();i++) {
			RoleFuncPermission roleFuncPermission = permissionList.get(i);
			dbPermissionCodes[i] = roleFuncPermission.getPermissionCode();
			
			boolean isExist = ArrayUtils.contains(permissionCodes, roleFuncPermission.getPermissionCode());
			if(!isExist)
			{
				delList.add(roleFuncPermission);
			}
		}
		//检查新的
		for (String permissionCode : permissionCodes) {
			boolean isExist = ArrayUtils.contains(dbPermissionCodes, permissionCode);
			if(!isExist)
			{
				RoleFuncPermission permission = new RoleFuncPermission();
				permission.setTenantId(role.getTenantId());
				permission.setRoleId(role.getId());
				permission.setPermissionCode(permissionCode);
				newList.add(permission);
			}
		}
		
		//删除旧的
		roleFuncPermissionDao.deleteBatch(delList);
		//保存新的
		roleFuncPermissionDao.saveBatch(newList);
	}

	/**
	 * @param role
	 */
	private void update(BizRole role) {
		//校验租户权限
		LoginUtils.validateTenant(role.getTenantId());
		
		role.setUpdateBy(LoginUtils.getLoginOpId().intValue());
		role.setUpdateDatetime(new Date());
	
		bizRoleDao.update(role);
	}			
   
   	/**
	 * Delete by id.
	 * @param id id
	 */
	public void delete(Integer id) 
	{
		BizRole role = this.load(id);
		this.delete(role);
	}
	
	/**
	 * Batch delete by ids.
	 * @param ids ids
	 */
	public void deleteBatch(Integer[] ids) 
	{
		if(ids!=null)
		{
			for (Integer id : ids) {
				this.delete(id);
			}
		}
	}
	
	/**
	 * Delete by instance.
	 * @param id id
	 */
	public void delete(BizRole role) 
	{
		//校验租户权限
		LoginUtils.validateTenant(role.getTenantId());
	    if(role.getStatus() != RoleStatus.SYSTEM_DEFINED)
	    {
			role.setStatus(RoleStatus.LOGIC_DELETED);
			this.update(role);
	    }
		//roleDao.delete(role);
	}
	
	/**
	 * Load.
	 *
	 * @param roleId the role id
	 * @return the role
	 */
	public BizRole load(int roleId)
	{
		BizRole role = bizRoleDao.load(roleId);
		//校验租户权限
		LoginUtils.validateTenant(role.getTenantId());
	
		return role;
	}
	
	/**
	 * load member by tenant.
	 *
	 * @param tenantId the tenant id
	 * @return the list
	 */
	public BizRole load(int tenantId, String name, int status)
	{
		String hql = "from BizRole where tenantId=? and name=? and status=?";
		return bizRoleDao.uniqueResult(hql, new Object[]{tenantId, name, status}, Role.class);
	}
	
	/**
	 * List by tenant.
	 *
	 * @param tenantId the tenant id
	 * @return the list
	 */
	public List<BizRole> listByTenant(int tenantId)
	{
		String listHql = "from BizRole where tenantId=?";
		return bizRoleDao.listAll(listHql, new Object[]{tenantId});
	}
	
	/**
	 * list by page.
	 * 
	 * @param pageParam params
	 * @return IPageList IPageList
	 */
	public IPageList listPage(PageParam pageParam)
	{
		String listHql = "select obj from BizRole obj"
			+ "-- and obj.tenantId={tenantId}"
			+ "-- and obj.name like {name%}"
			;	
		return bizRoleDao.listPage(listHql, pageParam);
	}

}
