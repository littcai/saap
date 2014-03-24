package com.litt.saap.system.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.litt.core.dao.page.IPageList;
import com.litt.core.dao.ql.PageParam;
import com.litt.saap.core.common.SaapConstants.RoleStatus;
import com.litt.saap.core.web.util.LoginUtils;
import com.litt.saap.system.dao.RoleDao;
import com.litt.saap.system.po.Role;
import com.litt.saap.system.service.IRoleService;

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
public class RoleServiceImpl implements IRoleService {
	
	@Resource
	private RoleDao roleDao;
	
	/* (non-Javadoc)
	 * @see com.litt.saap.system.service.impl.IRoleService#save(com.litt.saap.system.po.Role)
	 */
	public Role save(Role role)
	{
		role.setStatus(RoleStatus.NORMAL);
		role.setTenantId(LoginUtils.getTenantId());
		role.setCreateBy(LoginUtils.getLoginOpId().intValue());
		role.setCreateDatetime(new Date());
		role.setUpdateBy(role.getCreateBy());
		role.setUpdateDatetime(role.getCreateDatetime());
		
		roleDao.save(role);
		return role;
	}
	
	/**
	 * Update.
	 * @param role Role
	 */
	public void update(Role role) 
	{
		//校验租户权限
		LoginUtils.validateTenant(role.getTenantId());
		
		role.setUpdateBy(LoginUtils.getLoginOpId().intValue());
		role.setUpdateDatetime(new Date());
	
		roleDao.update(role);
	}			
   
   	/**
	 * Delete by id.
	 * @param id id
	 */
	public void delete(Integer id) 
	{
		Role role = this.load(id);
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
	public void delete(Role role) 
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
	 * Resume by id.
	 * @param id id
	 */
	public void doResume(Integer id) 
	{
		Role role = this.load(id);
		this.doResume(role);
	}
	
	/**
	 * Resume by instance.
	 * @param id id
	 */
	public void doResume(Role role) 
	{
		//校验租户权限
		LoginUtils.validateTenant(role.getTenantId());
	    if( role.getStatus()==RoleStatus.LOGIC_DELETED)
	    {
			role.setStatus(RoleStatus.NORMAL);
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
	public Role load(int roleId)
	{
		Role role = roleDao.load(roleId);
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
	public Role load(int tenantId, String name, int status)
	{
		String hql = "from Role where tenantId=? and name=? and status=?";
		return roleDao.uniqueResult(hql, new Object[]{tenantId, name, status}, Role.class);
	}
	
	/**
	 * List by tenant.
	 *
	 * @param tenantId the tenant id
	 * @return the list
	 */
	public List<Role> listByTenant(int tenantId)
	{
		String listHql = "from Role where tenantId=?";
		return roleDao.listAll(listHql, new Object[]{tenantId});
	}
	
	/**
	 * list by page.
	 * 
	 * @param pageParam params
	 * @return IPageList IPageList
	 */
	public IPageList listPage(PageParam pageParam)
	{
		String listHql = "select obj from Role obj"
			+ "-- and obj.tenantId={tenantId}"
			+ "-- and obj.name like {name%}"
			;	
		return roleDao.listPage(listHql, pageParam);
	}

}
