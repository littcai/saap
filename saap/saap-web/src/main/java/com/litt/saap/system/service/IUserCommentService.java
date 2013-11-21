package com.litt.saap.system.service;

import com.litt.core.dao.page.IPageList;
import com.litt.core.dao.ql.PageParam;

import com.litt.saap.system.po.UserComment;

/**
 * 
 * Comment service interface.
 * <pre><b>Description：</b>
 *    User Coomment
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">Bob.cai</a>
 * @since 2013-11-19
 * @version 1.0
 */
public interface IUserCommentService
{ 

   	/**
	 * Save.
	 * @param userComment UserComment
	 * @return id
	 */
	public Integer save(UserComment userComment);
	
   	/**
	 * Update.
	 * @param userComment UserComment
	 */
	public void update(UserComment userComment);				
   
   	/**
	 * Delete by id.
	 * @param id id
	 */
	public void delete(Integer id);	
	
	/**
	 * Delete by instance.
	 * @param id id
	 */
	public void delete(UserComment userComment);
	
	/**
	 * Load by id.
	 * @param id id
	 * @return UserComment
	 */
	public UserComment load(Integer id);	
	
	/**
	 * list by page.
	 * 
	 * @param pageParam params
	 * @return IPageList IPageList
	 */
	public IPageList listPage(PageParam pageParam);	

}