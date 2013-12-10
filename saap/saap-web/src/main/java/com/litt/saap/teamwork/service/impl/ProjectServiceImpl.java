package com.litt.saap.teamwork.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.litt.core.dao.page.IPageList;
import com.litt.core.dao.ql.PageParam;
import com.litt.core.service.BaseService;
import com.litt.saap.teamwork.dao.ProjectDao;
import com.litt.saap.teamwork.po.Project;
import com.litt.saap.teamwork.service.IProjectService;

/**
 * 
 * Project service impl.
 * <pre><b>Description：</b>
 *    Project Management
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">Bob.cai</a>
 * @since 2013-12-04
 * @version 1.0
 */
public class ProjectServiceImpl implements IProjectService
{ 
	/** Logger. */
    private static final Logger logger = LoggerFactory.getLogger(ProjectServiceImpl.class);
    
    @Resource
    private ProjectDao projectDao;

   	/**
	 * Save.
	 * @param project Project
	 * @return id
	 */
	public Integer save(Project project)
	{
		return projectDao.save(project);
	}
	
   	/**
	 * Update.
	 * @param project Project
	 */
	public void update(Project project)
	{
		projectDao.update(project);
	}			
   
   	/**
	 * Delete by id.
	 * @param id id
	 */
	public void delete(Integer id)
	{
		projectDao.delete(Project.class, "id", id);
	}
	
	/**
	 * Delete by instance.
	 * @param id id
	 */
	public void delete(Project project)
	{
		projectDao.delete(project);
	}
	
	/**
	 * Load by id.
	 * @param id id
	 * @return Project
	 */
	public Project load(Integer id)
	{
		return projectDao.load(Project.class, id);
	}
	
	/**
	 * list by page.
	 * 
	 * @param pageParam params
	 * @return IPageList IPageList
	 */
	public IPageList listPage(PageParam pageParam)
	{
		String listHql = "select obj from Project obj"
			+ "-- and obj.name={name}"
			;	
		return projectDao.listPage(listHql, pageParam);
	}
}