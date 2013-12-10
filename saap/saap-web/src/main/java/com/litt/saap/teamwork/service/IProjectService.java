package com.litt.saap.teamwork.service;

import com.litt.core.dao.page.IPageList;
import com.litt.core.dao.ql.PageParam;

import com.litt.saap.teamwork.po.Project;

/**
 * 
 * Project service interface.
 * <pre><b>Description：</b>
 *    Project Management
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">Bob.cai</a>
 * @since 2013-12-04
 * @version 1.0
 */
public interface IProjectService
{ 

   	/**
	 * Save.
	 * @param project Project
	 * @return id
	 */
	public Integer save(Project project);
	
   	/**
	 * Update.
	 * @param project Project
	 */
	public void update(Project project);				
   
   	/**
	 * Delete by id.
	 * @param id id
	 */
	public void delete(Integer id);	
	
	/**
	 * Delete by instance.
	 * @param id id
	 */
	public void delete(Project project);
	
	/**
	 * Load by id.
	 * @param id id
	 * @return Project
	 */
	public Project load(Integer id);	
	
	/**
	 * list by page.
	 * 
	 * @param pageParam params
	 * @return IPageList IPageList
	 */
	public IPageList listPage(PageParam pageParam);	

}