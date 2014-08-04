package com.litt.saap.system.dao;

import com.litt.core.dao.GenericHibernateDao;
import com.litt.saap.personal.po.ContactsGroupMember;
import com.litt.saap.system.po.UserGroupMember;

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
public class UserGroupMemberDao extends GenericHibernateDao<UserGroupMember, Integer> {
	
	public void deleteByUserGroup(int groupId)
	{
		this.delete(UserGroupMember.class, "groupId", groupId);
	}
	
	public void deleteByUser(int tenantId, int userId)
  {
    super.execute("delete from UserGroupMember where tenantId=? and userId=?", new Object[]{tenantId, userId});
  }
	
	public int countByGroupAndUserId(int tenantId, int groupId, int userId)
	{
	  String countHql = "select count(t) from UserGroupMember t where t.tenantId=? and t.groupId=? and t.userId=?";
	  return super.count(countHql, new Object[]{tenantId, groupId, userId});
	}
	
}
