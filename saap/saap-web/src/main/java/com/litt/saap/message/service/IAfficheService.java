package com.litt.saap.message.service;

import com.litt.core.dao.page.IPageList;
import com.litt.core.dao.ql.PageParam;

import com.litt.saap.message.po.Affiche;

/**
 * 
 * Affiche service interface.
 * <pre><b>Description：</b>
 *    Affiche Management.
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">Bob.cai</a>
 * @since 2014-06-26
 * @version 1.0
 */
public interface IAfficheService
{ 

   	/**
	 * Save.
	 * @param affiche Affiche
	 * @return id
	 */
	public Integer save(Affiche affiche);
	
   	/**
	 * Update.
	 * @param affiche Affiche
	 */
	public void update(Affiche affiche);				
   
   	/**
	 * Delete by id.
	 * @param id id
	 */
	public void delete(Integer id);	
	
	/**
	 * Delete by instance.
	 * @param id id
	 */
	public void delete(Affiche affiche);
	
	/**
	 * Batch delete by ids.
	 * @param ids ids
	 */
	public void deleteBatch(Integer[] ids);
	
	/**
	 * Load by id.
	 * @param id id
	 * @return Affiche
	 */
	public Affiche load(Integer id);	
	
	/**
	 * list by page.
	 * 
	 * @param pageParam params
	 * @return IPageList IPageList
	 */
	public IPageList listPage(PageParam pageParam);	
	
	/**
	 * List page with global.
	 *
	 * @param pageParam the page param
	 * @return the i page list
	 */
	public IPageList listPageWithGlobal(PageParam pageParam);

}