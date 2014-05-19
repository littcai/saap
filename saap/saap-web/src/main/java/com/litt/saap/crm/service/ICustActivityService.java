package com.litt.saap.crm.service;

import com.litt.core.dao.page.IPageList;
import com.litt.core.dao.ql.PageParam;

import com.litt.saap.crm.po.CustActivity;

/**
 * 
 * Activity service interface.
 * <pre><b>Description：</b>
 *    Customer Activities Management
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">Bob.cai</a>
 * @since 2014-05-19
 * @version 1.0
 */
public interface ICustActivityService
{ 

   	/**
	 * Save.
	 * @param custActivity CustActivity
	 * @return id
	 */
	public Integer save(CustActivity custActivity);
	
   	/**
	 * Update.
	 * @param custActivity CustActivity
	 */
	public void update(CustActivity custActivity);				
   
   	/**
	 * Delete by id.
	 * @param id id
	 */
	public void delete(Integer id);	
	
	/**
	 * Delete by instance.
	 * @param id id
	 */
	public void delete(CustActivity custActivity);
	
	/**
	 * Batch delete by ids.
	 * @param ids ids
	 */
	public void deleteBatch(Integer[] ids);
	
	/**
	 * Load by id.
	 * @param id id
	 * @return CustActivity
	 */
	public CustActivity load(Integer id);	
	
	/**
	 * list by page.
	 * 
	 * @param pageParam params
	 * @return IPageList IPageList
	 */
	public IPageList listPage(PageParam pageParam);	

}