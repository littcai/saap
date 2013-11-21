package com.litt.saap.system.service;

import java.util.List;

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

	public Role save(Role role);
	
	/**
	 * Load.
	 *
	 * @param roleId the role id
	 * @return the role
	 */
	public Role load(int roleId);
	
	/**
	 * List by tenant.
	 *
	 * @param tenantId the tenant id
	 * @return the list
	 */
	public List<Role> listByTenant(int tenantId);

}