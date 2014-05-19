package com.litt.saap.crm.service;

import com.litt.core.dao.page.IPageList;
import com.litt.core.dao.ql.PageParam;

import com.litt.saap.crm.po.CustEvent;

/**
 * 
 * Event service interface.
 * <pre><b>Description：</b>
 *    Customer Events Management
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">Bob.cai</a>
 * @since 2014-05-19
 * @version 1.0
 */
public interface ICustEventService
{ 

   	/**
	 * Save.
	 * @param custEvent CustEvent
	 * @return id
	 */
	public Integer save(CustEvent custEvent);
	
   	/**
	 * Update.
	 * @param custEvent CustEvent
	 */
	public void update(CustEvent custEvent);				
   
   	/**
	 * Delete by id.
	 * @param id id
	 */
	public void delete(Integer id);	
	
	/**
	 * Delete by instance.
	 * @param id id
	 */
	public void delete(CustEvent custEvent);
	
	/**
	 * Batch delete by ids.
	 * @param ids ids
	 */
	public void deleteBatch(Integer[] ids);
	
	/**
	 * Load by id.
	 * @param id id
	 * @return CustEvent
	 */
	public CustEvent load(Integer id);	
	
	/**
	 * list by page.
	 * 
	 * @param pageParam params
	 * @return IPageList IPageList
	 */
	public IPageList listPage(PageParam pageParam);	

}