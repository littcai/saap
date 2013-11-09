package com.litt.saap.personal.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.litt.core.dao.page.IPageList;
import com.litt.core.dao.ql.PageParam;
import com.litt.core.exception.BusiException;
import com.litt.core.exception.ErrorCode;
import com.litt.core.exception.NotLoginException;
import com.litt.core.service.BaseService;
import com.litt.core.shield.vo.ILoginVo;
import com.litt.core.util.DateUtils;
import com.litt.saap.core.common.SaapConstants.TodoStatus;
import com.litt.saap.core.web.util.LoginUtils;
import com.litt.saap.personal.dao.TodoDao;
import com.litt.saap.personal.po.Todo;
import com.litt.saap.personal.service.ITodoService;

/**
 * 
 * Todo service impl.
 * <pre><b>Description：</b>
 *    todo list.
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">Bob.cai</a>
 * @since 2013-09-12
 * @version 1.0
 */
public class TodoServiceImpl extends BaseService implements ITodoService
{ 
	/** Logger. */
    private static final Logger logger = LoggerFactory.getLogger(TodoServiceImpl.class);
    
    @Resource
    private TodoDao todoDao;

   	/**
	 * Save.
	 * @param todo Todo
	 * @return id
	 */
	public Integer save(Todo todo) throws NotLoginException
	{
		ILoginVo loginVo = LoginUtils.getLoginVo();
		
		//如果没有设定todoDate，默认为今天
		todo.setTodoDatetime(DateUtils.getEndOfDay(new Date()));
		
		todo.setStatus(TodoStatus.NEW);
		todo.setCreateUserId(loginVo.getOpId().intValue());
		todo.setCreateDatetime(new Date());
		todo.setUpdateDatetime(todo.getCreateDatetime());
		
		return todoDao.save(todo);
	}
	
   	/**
	 * Update.
	 * @param todo Todo
	 */
	public void update(Todo todo) throws NotLoginException
	{
		validatePermission(todo);
		
		todo.setUpdateDatetime(todo.getCreateDatetime());
		
		todoDao.update(todo);
	}
	
	/**
	 * Update status.
	 *
	 * @param id the id
	 * @param status the status
	 * @throws NotLoginException the not login exception
	 */
	public void updateStatus(int id, int status) throws NotLoginException
	{
		Todo todo = this.load(id);
		todo.setStatus(status);
		
		this.update(todo);
	}
   
   	/**
	 * Delete by id.
	 * @param id id
	 */
	public void delete(Integer id) throws NotLoginException
	{
		Todo todo = this.load(id);
		this.delete(todo);
	}
	
	/**
	 * Delete by instance.
	 * @param id id
	 */
	public void delete(Todo todo) throws NotLoginException
	{
		this.validatePermission(todo);
		todoDao.delete(todo);
	}
	
	/**
	 * @param todo
	 * @throws NotLoginException
	 */
	private void validatePermission(Todo todo) throws NotLoginException {
		ILoginVo loginVo = LoginUtils.getLoginVo();		
		
		if(todo.getCreateUserId()!=loginVo.getOpId().intValue())
		{
			throw new BusiException(new ErrorCode("error.biz.permissionDenied", loginVo.toLocale()));
		}
	}		
	
	/**
	 * Load by id.
	 * @param id id
	 * @return Todo
	 */
	public Todo load(Integer id)
	{
		return todoDao.load(Todo.class, id);
	}
	
	/**
	 * list by page.
	 * 
	 * @param pageParam params
	 * @return IPageList IPageList
	 */
	public IPageList listPage(PageParam pageParam)
	{
		String listHql = "select obj from Todo obj"
			+ "-- and obj.createUserId={createUserId}"	
			+ "-- and obj.status={status}"
			+ "-- and obj.status={statusExt}"
			+ "-- and obj.todoDatetime>={startTodoTime}"
			+ "-- and obj.todoDatetime<={endTodoTime}"
			+ "-- order by todoDatetime, updateDatetime desc"
			;	
		return todoDao.listPage(listHql, pageParam);
	}
}