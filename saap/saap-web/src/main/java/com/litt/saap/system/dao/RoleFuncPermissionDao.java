package com.litt.saap.system.dao;

import java.util.List;

import com.litt.core.dao.GenericHibernateDao;
import com.litt.saap.system.po.RoleFuncPermission;

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
public class RoleFuncPermissionDao extends GenericHibernateDao<RoleFuncPermission, Integer> {
	
	public List<RoleFuncPermission> listByTenantAndRole(Integer tenantId, Integer roleId)
	{
		String listHql = "from RoleFuncPermission where tenantId=? and roleId=?";
		return super.listAll(listHql, new Object[]{tenantId, roleId});
	}
	

}
