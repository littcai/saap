package com.litt.saap.personal.service;

import java.util.List;

import com.litt.core.dao.page.IPageList;
import com.litt.core.dao.ql.PageParam;
import com.litt.core.exception.NotLoginException;
import com.litt.saap.personal.po.Contacts;
import com.litt.saap.personal.vo.ContactsVo;

/**
 * 
 * Contacts service interface.
 * <pre><b>Description：</b>
 *    Contacts
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">Bob.cai</a>
 * @since 2013-09-18
 * @version 1.0
 */
public interface IContactsService
{ 

   	/**
	 * Save.
	 * @param contacts Contacts
	 * @return id
	 */
	public Integer save(Contacts contacts) throws NotLoginException;
	
   	/**
	 * Update.
	 * @param contacts Contacts
	 */
	public void update(Contacts contacts) throws NotLoginException;				
   
   	/**
	 * Delete by id.
	 * @param id id
	 */
	public void delete(Integer id) throws NotLoginException;	
	
	/**
	 * Delete by instance.
	 * @param id id
	 */
	public void delete(Contacts contacts) throws NotLoginException ;
	
	/**
	 * Batch delete by ids.
	 * @param ids ids
	 */
	public void deleteBatch(Integer[] ids) ;
	
	/**
	 * Do imp.
	 *
	 * @param contacts the contacts
	 * @return the integer
	 */
	public Integer doImp(Contacts contacts);
	
	/**
	 * Validate exist.
	 *
	 * @param mobile the mobile
	 * @return the integer
	 */
	public boolean validateExist(String mobile);
	
	/**
	 * Load by id.
	 * @param id id
	 * @return Contacts
	 */
	public Contacts load(Integer id);	
	
	/**
	 * list by page.
	 * 
	 * @param pageParam params
	 * @return IPageList IPageList
	 */
	public IPageList listPage(PageParam pageParam);	
	
	/**
	 * 查询用户个人通讯录.
	 *
	 * @param userId the user id
	 * @return the list
	 */
	public List<Contacts> listByUser(int userId);
	
	public List<Contacts> listByGroup(int groupId);
	
	/**
	 * 查询没有分组的联系人.
	 *
	 * @param userId the user id
	 * @return the list
	 */
	public List<Contacts> listNoGroupByUser(int userId);
	
	/**
	 * Find by group.
	 *
	 * @param groupId the group id
	 * @return the list
	 */
	public List<ContactsVo> findByGroup(int groupId);
}