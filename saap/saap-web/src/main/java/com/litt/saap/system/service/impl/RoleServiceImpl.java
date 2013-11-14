package com.litt.saap.system.service.impl;

import javax.annotation.Resource;

import com.litt.saap.system.dao.RoleDao;
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
public class RoleServiceImpl implements IRoleService {
	
	@Resource
	private RoleDao roleDao;
	
	/* (non-Javadoc)
	 * @see com.litt.saap.system.service.impl.IRoleService#save(com.litt.saap.system.po.Role)
	 */
	public Role save(Role role)
	{
		roleDao.save(role);
		return role;
	}
	
	/**
	 * Load.
	 *
	 * @param roleId the role id
	 * @return the role
	 */
	public Role load(int roleId)
	{
		return roleDao.load(roleId);
	}

}
