package com.litt.saap.personal.biz;

import java.util.List;

import com.litt.saap.personal.po.Contacts;
import com.litt.saap.personal.po.ContactsGroupMember;

/**
 * IContactsBizService.
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
 * @since 2014年3月3日
 * @version 1.0
 */
public interface IContactsBizService {

	/**
	 * Save.
	 *
	 * @param contacts the contacts
	 * @param contactsGroupIds the contacts group ids
	 */
	public void save(Contacts contacts, Integer[] contactsGroupIds);

	/**
	 * Update.
	 *
	 * @param contacts the contacts
	 * @param contactsGroupIds the contacts group ids
	 */
	public void update(Contacts contacts, Integer[] contactsGroupIds);
	
	
	/**
	 * List member by contacts.
	 *
	 * @param contactsId the contacts id
	 * @return the list
	 */
	public List<ContactsGroupMember> listMemberByContacts(int contactsId);

}