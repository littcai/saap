package com.litt.saap.teamwork.service;

import com.litt.core.dao.page.IPageList;
import com.litt.core.dao.ql.PageParam;

import com.litt.saap.teamwork.po.KnowledgePage;

/**
 * 
 * KnowledgePage service interface.
 * <pre><b>Description：</b>
 *    KnowledgePage
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">Bob.cai</a>
 * @since 2014-01-03
 * @version 1.0
 */
public interface IKnowledgePageService
{ 

   	/**
	 * Save.
	 * @param knowledgePage KnowledgePage
	 * @return id
	 */
	public Integer save(KnowledgePage knowledgePage);
	
   	/**
	 * Update.
	 * @param knowledgePage KnowledgePage
	 */
	public void update(KnowledgePage knowledgePage);				
   
   	/**
	 * Delete by id.
	 * @param id id
	 */
	public void delete(Integer id);	
	
	/**
	 * Delete by instance.
	 * @param id id
	 */
	public void delete(KnowledgePage knowledgePage);
	
	/**
	 * Batch delete by ids.
	 * @param ids ids
	 */
	public void deleteBatch(Integer[] ids);
	
	/**
	 * Load by id.
	 * @param id id
	 * @return KnowledgePage
	 */
	public KnowledgePage load(Integer id);	
	
	/**
	 * list by page.
	 * 
	 * @param pageParam params
	 * @return IPageList IPageList
	 */
	public IPageList listPage(PageParam pageParam);	

}