package com.litt.saap.system.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.litt.core.dao.page.IPageList;
import com.litt.core.dao.ql.PageParam;
import com.litt.core.service.BaseService;
import com.litt.saap.system.dao.UserCommentDao;
import com.litt.saap.system.po.UserComment;
import com.litt.saap.system.service.IUserCommentService;

/**
 * 
 * Comment service impl.
 * <pre><b>Description：</b>
 *    User Coomment
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">Bob.cai</a>
 * @since 2013-11-19
 * @version 1.0
 */
public class UserCommentServiceImpl extends BaseService implements IUserCommentService
{ 
	/** Logger. */
    private static final Logger logger = LoggerFactory.getLogger(UserCommentServiceImpl.class);
    
    @Resource
    private UserCommentDao userCommentDao;

   	/**
	 * Save.
	 * @param userComment UserComment
	 * @return id
	 */
	public Integer save(UserComment userComment)
	{
		return userCommentDao.save(userComment);
	}
	
   	/**
	 * Update.
	 * @param userComment UserComment
	 */
	public void update(UserComment userComment)
	{
		userCommentDao.update(userComment);
	}			
   
   	/**
	 * Delete by id.
	 * @param id id
	 */
	public void delete(Integer id)
	{
		userCommentDao.delete(UserComment.class, "id", id);
	}
	
	/**
	 * Delete by instance.
	 * @param id id
	 */
	public void delete(UserComment userComment)
	{
		userCommentDao.delete(userComment);
	}
	
	/**
	 * Load by id.
	 * @param id id
	 * @return UserComment
	 */
	public UserComment load(Integer id)
	{
		return userCommentDao.load(UserComment.class, id);
	}
	
	/**
	 * list by page.
	 * 
	 * @param pageParam params
	 * @return IPageList IPageList
	 */
	public IPageList listPage(PageParam pageParam)
	{
		String listHql = "select obj from UserComment obj"
			+ "-- and obj.name={name}"
			;	
		return userCommentDao.listPage(listHql, pageParam);
	}
}