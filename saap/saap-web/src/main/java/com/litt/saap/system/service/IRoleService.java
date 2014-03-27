package com.litt.saap.system.service;

import java.util.List;

import com.litt.core.dao.page.IPageList;
import com.litt.core.dao.ql.PageParam;
import com.litt.saap.system.po.Role;

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
public interface IRoleService {

	/**
	 * Save.
	 *
	 * @param role the role
	 * @param permissionCodes the permission codes
	 * @return the role
	 */
	public Role save(Role role, String[] permissionCodes);
	
	/**
	 * Update.
	 *
	 * @param role Role
	 * @param permissionCodes the permission codes
	 */
	public void update(Role role, String[] permissionCodes);	
	
	/**
	 * Delete by id.
	 * @param id id
	 */
	public void delete(Integer id);	
	
	/**
	 * Delete by instance.
	 * @param id id
	 */
	public void delete(Role role);
	
	/**
	 * Batch delete by ids.
	 * @param ids ids
	 */
	public void deleteBatch(Integer[] ids);
	
	/**
	 * Resume by id.
	 * @param id id
	 */
	public void doResume(Integer id) ;
	
	/**
	 * Resume by instance.
	 * @param id id
	 */
	public void doResume(Role role) ;
	
	/**
	 * Load.
	 *
	 * @param roleId the role id
	 * @return the role
	 */
	public Role load(int roleId);
	
	/**
	 * load member by tenant.
	 *
	 * @param tenantId the tenant id
	 * @return the list
	 */
	public Role load(int tenantId, String name, int status);
	
	/**
	 * List by tenant.
	 *
	 * @param tenantId the tenant id
	 * @return the list
	 */
	public List<Role> listByTenant(int tenantId);
	
	/**
	 * list by page.
	 * 
	 * @param pageParam params
	 * @return IPageList IPageList
	 */
	public IPageList listPage(PageParam pageParam);	

}