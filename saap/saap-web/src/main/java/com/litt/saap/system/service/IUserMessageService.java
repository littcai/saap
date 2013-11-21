package com.litt.saap.system.service;

import com.litt.core.dao.page.IPageList;
import com.litt.core.dao.ql.PageParam;

import com.litt.saap.system.po.UserMessage;

/**
 * 
 * Message service interface.
 * <pre><b>Description：</b>
 *    User Message
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">Bob.cai</a>
 * @since 2013-11-19
 * @version 1.0
 */
public interface IUserMessageService
{ 

   	/**
	 * Save.
	 * @param userMessage UserMessage
	 * @return id
	 */
	public Integer save(UserMessage userMessage);
	
   	/**
	 * Update.
	 * @param userMessage UserMessage
	 */
	public void update(UserMessage userMessage);				
   
   	/**
	 * Delete by id.
	 * @param id id
	 */
	public void delete(Integer id);	
	
	/**
	 * Delete by instance.
	 * @param id id
	 */
	public void delete(UserMessage userMessage);
	
	/**
	 * Load by id.
	 * @param id id
	 * @return UserMessage
	 */
	public UserMessage load(Integer id);	
	
	/**
	 * list by page.
	 * 
	 * @param pageParam params
	 * @return IPageList IPageList
	 */
	public IPageList listPage(PageParam pageParam);	

}