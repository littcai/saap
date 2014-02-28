package com.litt.saap.system.service;

import com.litt.core.dao.page.IPageList;
import com.litt.core.dao.ql.PageParam;

import com.litt.saap.system.po.UserGroup;

/**
 * 
 * Groups service interface.
 * <pre><b>Description：</b>
 *    Group Management
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">Bob.cai</a>
 * @since 2014-02-27
 * @version 1.0
 */
public interface IUserGroupService
{ 

   	/**
	 * Save.
	 * @param userGroup UserGroup
	 * @return id
	 */
	public Integer save(UserGroup userGroup);
	
   	/**
	 * Update.
	 * @param userGroup UserGroup
	 */
	public void update(UserGroup userGroup);				
   
   	/**
	 * Delete by id.
	 * @param id id
	 */
	public void delete(Integer id);	
	
	/**
	 * Delete by instance.
	 * @param id id
	 */
	public void delete(UserGroup userGroup);
	
	/**
	 * Batch delete by ids.
	 * @param ids ids
	 */
	public void deleteBatch(Integer[] ids);
	
	/**
	 * Load by id.
	 * @param id id
	 * @return UserGroup
	 */
	public UserGroup load(Integer id);	
	
	/**
	 * list by page.
	 * 
	 * @param pageParam params
	 * @return IPageList IPageList
	 */
	public IPageList listPage(PageParam pageParam);	

}