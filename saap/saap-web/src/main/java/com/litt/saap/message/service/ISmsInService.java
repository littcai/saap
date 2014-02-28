package com.litt.saap.message.service;

import com.litt.core.dao.page.IPageList;
import com.litt.core.dao.ql.PageParam;

import com.litt.saap.message.po.SmsIn;

/**
 * 
 * SMS In service interface.
 * <pre><b>Description：</b>
 *    SMS In Management
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">Bob.cai</a>
 * @since 2014-02-17
 * @version 1.0
 */
public interface ISmsInService
{ 

   	/**
	 * Save.
	 * @param smsIn SmsIn
	 * @return id
	 */
	public Integer save(SmsIn smsIn);
	
   	/**
	 * Update.
	 * @param smsIn SmsIn
	 */
	public void update(SmsIn smsIn);				
   
   	/**
	 * Delete by id.
	 * @param id id
	 */
	public void delete(Integer id);	
	
	/**
	 * Delete by instance.
	 * @param id id
	 */
	public void delete(SmsIn smsIn);
	
	/**
	 * Batch delete by ids.
	 * @param ids ids
	 */
	public void deleteBatch(Integer[] ids);
	
	/**
	 * Load by id.
	 * @param id id
	 * @return SmsIn
	 */
	public SmsIn load(Integer id);	
	
	/**
	 * list by page.
	 * 
	 * @param pageParam params
	 * @return IPageList IPageList
	 */
	public IPageList listPage(PageParam pageParam);	

}