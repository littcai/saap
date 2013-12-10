package com.litt.saap.teamwork.service;

import com.litt.core.dao.page.IPageList;
import com.litt.core.dao.ql.PageParam;

import com.litt.saap.teamwork.po.Task;

/**
 * 
 * Task service interface.
 * <pre><b>Description：</b>
 *    Task Management
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">Bob.cai</a>
 * @since 2013-12-04
 * @version 1.0
 */
public interface ITaskService
{ 

   	/**
	 * Save.
	 * @param task Task
	 * @return id
	 */
	public Integer save(Task task);
	
   	/**
	 * Update.
	 * @param task Task
	 */
	public void update(Task task);				
   
   	/**
	 * Delete by id.
	 * @param id id
	 */
	public void delete(Integer id);	
	
	/**
	 * Delete by instance.
	 * @param id id
	 */
	public void delete(Task task);
	
	/**
	 * Load by id.
	 * @param id id
	 * @return Task
	 */
	public Task load(Integer id);	
	
	/**
	 * list by page.
	 * 
	 * @param pageParam params
	 * @return IPageList IPageList
	 */
	public IPageList listPage(PageParam pageParam);	

}