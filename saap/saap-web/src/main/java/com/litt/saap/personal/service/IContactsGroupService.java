package com.litt.saap.personal.service;

import java.util.List;

import com.litt.core.dao.page.IPageList;
import com.litt.core.dao.ql.PageParam;
import com.litt.core.exception.NotLoginException;
import com.litt.saap.personal.po.ContactsGroup;

/**
 * IContactsGroupService.
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
public interface IContactsGroupService {

	/**
	 * Save.
	 * @param contactsGroup ContactsGroup
	 * @return id
	 */
	public Integer save(ContactsGroup contactsGroup)
			throws NotLoginException;

	/**
	 * Update.
	 * @param contactsGroup ContactsGroup
	 */
	public void update(ContactsGroup contactsGroup)
			throws NotLoginException;

	/**
	 * Delete by id.
	 * @param id id
	 */
	public void delete(Integer id) throws NotLoginException;

	/**
	 * Delete by instance.
	 * @param id id
	 */
	public void delete(ContactsGroup contactsGroup)
			throws NotLoginException;
	
	/**
	 * Batch delete by ids.
	 * @param ids ids
	 */
	public void deleteBatch(Integer[] ids) ;
	
	/**
	 * Do imp.
	 *
	 * @param groupName the group name
	 * @return the integer
	 */
	public Integer doImp(String groupName);

	/**
	 * Load by id.
	 * @param id id
	 * @return ContactsGroup
	 */
	public ContactsGroup load(Integer id);

	/**
	 * list by page.
	 * 
	 * @param pageParam params
	 * @return IPageList IPageList
	 */
	public IPageList listPage(PageParam pageParam);

	/**
	 * 查询联系人拥有的组.
	 *
	 * @param contactsId 联系人ID
	 * @return the list
	 */
	public List<ContactsGroup> listByContacts(int contactsId);
	
	/**
	 * 查询联系人拥有的组.
	 *
	 * @param contactsId 联系人ID
	 * @return the list
	 */
	public List<ContactsGroup> listByUser(int userId);

}