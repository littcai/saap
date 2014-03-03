package com.litt.saap.personal.dao;

import java.util.List;

import com.litt.core.dao.GenericHibernateDao;
import com.litt.saap.personal.po.ContactsGroupMember;

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
public class ContactsGroupMemberDao extends GenericHibernateDao<ContactsGroupMember, Integer> {
	
	/**
	 * List by contacts.
	 *
	 * @param contactsId the contacts id
	 * @return the list
	 */
	public List<ContactsGroupMember> listByContacts(int contactsId)
	{
		return super.listAll("from ContactsGroupMember where contactsId=?", new Object[]{contactsId});
	}
	
	/**
	 * Delete by contacts.
	 *
	 * @param contactsId the contacts id
	 */
	public void deleteByContacts(int contactsId)
	{
		this.delete(ContactsGroupMember.class, "contactsId", contactsId);
	}
	
	public void deleteByContactsGroup(int contactsGroupId)
	{
		this.delete(ContactsGroupMember.class, "groupId", contactsGroupId);
	}

}
