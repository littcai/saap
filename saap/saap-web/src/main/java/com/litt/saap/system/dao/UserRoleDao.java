package com.litt.saap.system.dao;


import java.util.List;

import com.litt.core.dao.GenericHibernateDao;
import com.litt.saap.system.po.UserRole;

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
 * @since 2013-8-29
 * @version 1.0
 */
public class UserRoleDao extends GenericHibernateDao<UserRole, Integer> {
	
	
	public List<UserRole> listByTenantUser(int tenantId, int userId)
	{
		String listHql = "from UserRole where tenantId=? and userId=?";
		return super.listAll(listHql, new Object[]{tenantId, userId});
	}
	
	public void deleteByTenantUser(int tenantId, int userId)
	{
		super.execute("delete from UserRole where tenantId=? and userId=?", new Object[]{tenantId, userId});
	}

}
