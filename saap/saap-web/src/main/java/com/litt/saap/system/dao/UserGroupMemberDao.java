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

	/**
	 * Delete by contacts.
	 *
	 * @param contactsId the contacts id
	 */
	public void deleteByUser(int userId)
	{
		this.delete(UserGroupMember.class, "userId", userId);
	}
	
	public void deleteByUserGroup(int groupId)
	{
		this.delete(UserGroupMember.class, "groupId", groupId);
	}
	
}
