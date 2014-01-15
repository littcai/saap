package com.litt.saap.system.dao;

import java.util.List;

import com.litt.core.dao.GenericHibernateDao;
import com.litt.saap.system.po.Tenant;
import com.litt.saap.system.po.TenantMember;

/**
 * 租户信息.
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
 * @since 2013-10-17
 * @version 1.0
 */
public class TenantDao extends GenericHibernateDao<Tenant, Integer> {
	
	/**
	 * List by MEMBER.
	 *
	 * @param tenantId the tenant id
	 * @return the list
	 */
	public List<Tenant> listByMember(int userId)
	{
		return super.listAll("select t from Tenant t, TenantMember t1 where t1.userId=? and t.id=t1.tenantId", new Object[]{userId});
	}

}
