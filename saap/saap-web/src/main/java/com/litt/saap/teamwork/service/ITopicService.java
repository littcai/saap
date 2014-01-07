package com.litt.saap.teamwork.service;

import com.litt.core.dao.page.IPageList;
import com.litt.core.dao.ql.PageParam;

import com.litt.saap.teamwork.po.Topic;

/**
 * 
 * Discussion service interface.
 * <pre><b>Description：</b>
 *    Discussion
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">Bob.cai</a>
 * @since 2014-01-03
 * @version 1.0
 */
public interface ITopicService
{ 

   	/**
	 * Save.
	 * @param topic Topic
	 * @return id
	 */
	public Integer save(Topic topic);
	
   	/**
	 * Update.
	 * @param topic Topic
	 */
	public void update(Topic topic);				
   
   	/**
	 * Delete by id.
	 * @param id id
	 */
	public void delete(Integer id);	
	
	/**
	 * Delete by instance.
	 * @param id id
	 */
	public void delete(Topic topic);
	
	/**
	 * Batch delete by ids.
	 * @param ids ids
	 */
	public void deleteBatch(Integer[] ids);
	
	/**
	 * Load by id.
	 * @param id id
	 * @return Topic
	 */
	public Topic load(Integer id);	
	
	/**
	 * list by page.
	 * 
	 * @param pageParam params
	 * @return IPageList IPageList
	 */
	public IPageList listPage(PageParam pageParam);	

}