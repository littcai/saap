package com.litt.saap.personal.service;

import com.litt.core.dao.page.IPageList;
import com.litt.core.dao.ql.PageParam;

import com.litt.saap.personal.po.Calendar;

/**
 * 
 * Calendar service interface.
 * <pre><b>Description：</b>
 *    Calendar
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">Bob.cai</a>
 * @since 2013-09-12
 * @version 1.0
 */
public interface ICalendarService
{ 

   	/**
	 * Save.
	 * @param calendar Calendar
	 * @return id
	 */
	public Integer save(Calendar calendar);
	
   	/**
	 * Update.
	 * @param calendar Calendar
	 */
	public void update(Calendar calendar);				
   
   	/**
	 * Delete by id.
	 * @param id id
	 */
	public void delete(Integer id);	
	
	/**
	 * Delete by instance.
	 * @param id id
	 */
	public void delete(Calendar calendar);
	
	/**
	 * Load by id.
	 * @param id id
	 * @return Calendar
	 */
	public Calendar load(Integer id);	
	
	/**
	 * list by page.
	 * 
	 * @param pageParam params
	 * @return IPageList IPageList
	 */
	public IPageList listPage(PageParam pageParam);	

}