package com.litt.saap.crm.service;

import com.litt.core.dao.page.IPageList;
import com.litt.core.dao.ql.PageParam;

import com.litt.saap.crm.po.CustContacts;

/**
 * 
 * Contacts service interface.
 * <pre><b>Description：</b>
 *    Customer Contacts Management
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">Bob.cai</a>
 * @since 2013-10-29
 * @version 1.0
 */
public interface ICustContactsService
{ 

   	/**
	 * Save.
	 * @param custContacts CustContacts
	 * @return id
	 */
	public Integer save(CustContacts custContacts);
	
   	/**
	 * Update.
	 * @param custContacts CustContacts
	 */
	public void update(CustContacts custContacts);				
   
   	/**
	 * Delete by id.
	 * @param id id
	 */
	public void delete(Integer id);	
	
	/**
	 * Batch delete by ids.
	 * @param ids ids
	 */
	public void deleteBatch(Integer[] ids);
	
	/**
	 * Delete by instance.
	 * @param id id
	 */
	public void delete(CustContacts custContacts);
	
	/**
	 * Load by id.
	 * @param id id
	 * @return CustContacts
	 */
	public CustContacts load(Integer id);	
	
	/**
	 * list by page.
	 * 
	 * @param pageParam params
	 * @return IPageList IPageList
	 */
	public IPageList listPage(PageParam pageParam);	

}