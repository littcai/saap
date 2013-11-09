package com.litt.saap.personal.service;

import com.litt.core.dao.page.IPageList;
import com.litt.core.dao.ql.PageParam;
import com.litt.core.exception.NotLoginException;

import com.litt.saap.personal.po.Contacts;

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

}