package com.litt.saap.personal.service;

import com.litt.core.dao.page.IPageList;
import com.litt.core.dao.ql.PageParam;
import com.litt.core.exception.NotLoginException;

import com.litt.saap.personal.po.Todo;

/**
 * 
 * Todo service interface.
 * <pre><b>Description：</b>
 *    todo list.
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">Bob.cai</a>
 * @since 2013-09-12
 * @version 1.0
 */
public interface ITodoService
{ 

   	/**
	 * Save.
	 * @param todo Todo
	 * @return id
	 */
	public Integer save(Todo todo) throws NotLoginException;
	
   	/**
	 * Update.
	 * @param todo Todo
	 */
	public void update(Todo todo) throws NotLoginException;	
	
	/**
	 * Update status.
	 *
	 * @param id the id
	 * @param status the status
	 * @throws NotLoginException the not login exception
	 */
	public void updateStatus(int id, int status) throws NotLoginException;
   
   	/**
	 * Delete by id.
	 * @param id id
	 */
	public void delete(Integer id) throws NotLoginException;	
	
	/**
	 * Load by id.
	 * @param id id
	 * @return Todo
	 */
	public Todo load(Integer id);	
	
	/**
	 * list by page.
	 * 
	 * @param pageParam params
	 * @return IPageList IPageList
	 */
	public IPageList listPage(PageParam pageParam);	

}