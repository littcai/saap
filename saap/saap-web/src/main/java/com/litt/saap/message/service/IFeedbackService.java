package com.litt.saap.message.service;

import com.litt.core.dao.page.IPageList;
import com.litt.core.dao.ql.PageParam;

import com.litt.saap.message.po.Feedback;

/**
 * 
 * Feedback service interface.
 * <pre><b>Description：</b>
 *    Feedback Management.
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">Bob.cai</a>
 * @since 2014-06-26
 * @version 1.0
 */
public interface IFeedbackService
{ 

   	/**
	 * Save.
	 * @param feedback Feedback
	 * @return id
	 */
	public Integer save(Feedback feedback);
	
   	/**
	 * Update.
	 * @param feedback Feedback
	 */
	public void update(Feedback feedback);				
   
   	/**
	 * Delete by id.
	 * @param id id
	 */
	public void delete(Integer id);	
	
	/**
	 * Delete by instance.
	 * @param id id
	 */
	public void delete(Feedback feedback);
	
	/**
	 * Batch delete by ids.
	 * @param ids ids
	 */
	public void deleteBatch(Integer[] ids);
	
	/**
	 * Load by id.
	 * @param id id
	 * @return Feedback
	 */
	public Feedback load(Integer id);	
	
	/**
	 * list by page.
	 * 
	 * @param pageParam params
	 * @return IPageList IPageList
	 */
	public IPageList listPage(PageParam pageParam);	

}