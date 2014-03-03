package com.litt.saap.system.dao;

import java.util.List;

import com.litt.core.dao.GenericHibernateDao;
import com.litt.saap.system.po.TenantMember;

/**
 * 租户成员信息.
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
public class TenantMemberDao extends GenericHibernateDao<TenantMember, Integer> {
	
	/**
	 * 检查用户是否已是某个租户的成员.
	 *
	 * @param userId the user id
	 * @return true, if is tenant member
	 */
	public boolean isTenantMember(int tenantId, int userId)
	{
		String countHql = "select count(o) from TenantMember o where o.tenantId, o.userId=?";
		return super.count(countHql, new Object[]{tenantId, userId})>0;
	}
	
	/**
	 * Count by tenant.
	 *
	 * @param tenantId the tenant id
	 * @return the int
	 */
	public int countByTenant(int tenantId)
	{
		String countHql = "select count(o) from TenantMember o where o.tenantId=?";
		return super.count(countHql, new Object[]{tenantId});
	}
	
	/**
	 * Count by tenant.
	 *
	 * @param tenantId the tenant id
	 * @return the int
	 */
	public int countAdminByTenant(int tenantId)
	{
		String countHql = "select count(o) from TenantMember o where o.tenantId=? and isAdmin=1";
		return super.count(countHql, new Object[]{tenantId});
	}
	
	/**
	 * Load.
	 *
	 * @param appId the app id
	 * @param userId the user id
	 * @return the tenant member
	 */
	public TenantMember loadByUserAndTenant(int userId, int tenantId)
	{
		String hql = "from TenantMember where userId=? and tenantId=?";
		return super.uniqueResult(hql, new Object[]{userId, tenantId}, TenantMember.class);
	}	
		
	/**
	 * List by tenant.
	 *
	 * @param tenantId the tenant id
	 * @return the list
	 */
	public List<TenantMember> listByTenant(int tenantId)
	{
		return super.listAll("from TenantMember where tenantId=?", new Object[]{tenantId});
	}
	
	/**
	 * List by MEMBER.
	 *
	 * @param tenantId the tenant id
	 * @return the list
	 */
	public List<TenantMember> listByMember(int userId)
	{
		return super.listAll("from TenantMember where userId=?", new Object[]{userId});
	}

}
