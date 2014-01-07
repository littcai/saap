package com.litt.saap.teamwork.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.litt.core.dao.page.IPageList;
import com.litt.core.dao.ql.PageParam;
import com.litt.core.service.BaseService;
import com.litt.saap.core.web.util.LoginUtils;
import com.litt.saap.teamwork.dao.TaskDao;
import com.litt.saap.teamwork.po.Task;
import com.litt.saap.teamwork.service.ITaskService;

/**
 * 
 * Task service impl.
 * <pre><b>Description：</b>
 *    Task Management
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">Bob.cai</a>
 * @since 2013-12-04
 * @version 1.0
 */
public class TaskServiceImpl implements ITaskService
{ 
	/** Logger. */
    private static final Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);
    
    @Resource
    private TaskDao taskDao;

   	/**
	 * Save.
	 * @param task Task
	 * @return id
	 */
	public Integer save(Task task)
	{
		task.setTenantId(LoginUtils.getTenantId());
		task.setCreateUserId(LoginUtils.getLoginOpId().intValue());
		task.setCreateDatetime(new Date());		
		task.setUpdateUserId(task.getCreateUserId());
		task.setUpdateDatetime(task.getCreateDatetime());
		return taskDao.save(task);
	}
	
   	/**
	 * Update.
	 * @param task Task
	 */
	public void update(Task task)
	{
		task.setUpdateUserId(LoginUtils.getLoginOpId().intValue());
		task.setUpdateDatetime(new Date());
		taskDao.update(task);
	}			
   
   	/**
	 * Delete by id.
	 * @param id id
	 */
	public void delete(Integer id)
	{
		taskDao.delete(Task.class, "id", id);
	}
	
	/**
	 * Delete by instance.
	 * @param id id
	 */
	public void delete(Task task)
	{
		taskDao.delete(task);
	}
	
	/**
	 * Load by id.
	 * @param id id
	 * @return Task
	 */
	public Task load(Integer id)
	{
		return taskDao.load(Task.class, id);
	}
	
	/**
	 * list by page.
	 * 
	 * @param pageParam params
	 * @return IPageList IPageList
	 */
	public IPageList listPage(PageParam pageParam)
	{
		String listHql = "select obj from Task obj"
			+ "-- and obj.name={name}"
			;	
		return taskDao.listPage(listHql, pageParam);
	}
}