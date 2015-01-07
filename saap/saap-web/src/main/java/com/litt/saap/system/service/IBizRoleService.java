package com.litt.saap.system.service;

import java.util.List;

import com.litt.core.dao.page.IPageList;
import com.litt.core.dao.ql.PageParam;
import com.litt.saap.system.po.BizRole;

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
public interface IBizRoleService {

	/**
	 * Save.
	 *
	 * @param role the role
	 * @param permissionCodes the permission codes
	 * @return the role
	 */
	public BizRole save(BizRole role, String[] permissionCodes);
	
	/**
	 * Update.
	 *
	 * @param role Role
	 * @param permissionCodes the permission codes
	 */
	public void update(BizRole role, String[] permissionCodes);	
	
	/**
	 * Delete by id.
	 * @param id id
	 */
	public void delete(Integer id);	
	
	/**
	 * Delete by instance.
	 * @param id id
	 */
	public void delete(BizRole role);
	
	/**
	 * Batch delete by ids.
	 * @param ids ids
	 */
	public void deleteBatch(Integer[] ids);
	
	/**
	 * Load.
	 *
	 * @param roleId the role id
	 * @return the role
	 */
	public BizRole load(int roleId);
	
	/**
	 * load member by tenant.
	 *
	 * @param tenantId the tenant id
	 * @return the list
	 */
	public BizRole load(int tenantId, String name, int status);
	
	/**
	 * List by tenant.
	 *
	 * @param tenantId the tenant id
	 * @return the list
	 */
	public List<BizRole> listByTenant(int tenantId);
	
	/**
	 * list by page.
	 * 
	 * @param pageParam params
	 * @return IPageList IPageList
	 */
	public IPageList listPage(PageParam pageParam);	

}