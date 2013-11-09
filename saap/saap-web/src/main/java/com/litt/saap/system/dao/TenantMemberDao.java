package com.litt.saap.system.dao;

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
	 * Load.
	 *
	 * @param appId the app id
	 * @param userId the user id
	 * @return the tenant member
	 */
	public TenantMember load(int userId, int appId)
	{
		String hql = "from TenantMember where userId=? and appId=?";
		return super.uniqueResult(hql, new Object[]{userId, appId}, TenantMember.class);
	}

}
