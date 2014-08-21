package com.litt.saap.assistant.service;

import com.litt.core.dao.page.IPageList;
import com.litt.core.dao.ql.PageParam;
import com.litt.saap.assistant.po.Document;

/**
 * 
 * Document service interface.
 * <pre><b>Description：</b>
 *    Document
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">Bob.cai</a>
 * @since 2014-08-20
 * @version 1.0
 */
public interface IDocumentService
{ 

   	/**
	 * Save.
	 * @param document Document
	 * @return id
	 */
	public Integer save(Document document);
	
   	/**
	 * Update.
	 * @param document Document
	 */
	public void update(Document document);				
   
   	/**
	 * Delete by id.
	 * @param id id
	 */
	public void delete(Integer id);	
	
	/**
	 * Delete by instance.
	 * @param id id
	 */
	public void delete(Document document);
	
	/**
	 * Batch delete by ids.
	 * @param ids ids
	 */
	public void deleteBatch(Integer[] ids);
	
	/**
	 * Load by id.
	 * @param id id
	 * @return Document
	 */
	public Document load(Integer id);	
	
	/**
	 * list by page.
	 * 
	 * @param pageParam params
	 * @return IPageList IPageList
	 */
	public IPageList listPage(PageParam pageParam);	

}