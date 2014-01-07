package com.litt.saap.teamwork.service;

import com.litt.core.dao.page.IPageList;
import com.litt.core.dao.ql.PageParam;

import com.litt.saap.teamwork.po.KnowledgeSpace;

/**
 * 
 * KnowledgeBase service interface.
 * <pre><b>Description：</b>
 *    KnowledgeBase
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">Bob.cai</a>
 * @since 2014-01-03
 * @version 1.0
 */
public interface IKnowledgeSpaceService
{ 

   	/**
	 * Save.
	 * @param knowledgeSpace KnowledgeSpace
	 * @return id
	 */
	public Integer save(KnowledgeSpace knowledgeSpace);
	
   	/**
	 * Update.
	 * @param knowledgeSpace KnowledgeSpace
	 */
	public void update(KnowledgeSpace knowledgeSpace);				
   
   	/**
	 * Delete by id.
	 * @param id id
	 */
	public void delete(Integer id);	
	
	/**
	 * Delete by instance.
	 * @param id id
	 */
	public void delete(KnowledgeSpace knowledgeSpace);
	
	/**
	 * Batch delete by ids.
	 * @param ids ids
	 */
	public void deleteBatch(Integer[] ids);
	
	/**
	 * Load by id.
	 * @param id id
	 * @return KnowledgeSpace
	 */
	public KnowledgeSpace load(Integer id);	
	
	/**
	 * list by page.
	 * 
	 * @param pageParam params
	 * @return IPageList IPageList
	 */
	public IPageList listPage(PageParam pageParam);	

}