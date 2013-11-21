package com.litt.saap.system.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.litt.core.dao.page.IPageList;
import com.litt.core.dao.ql.PageParam;
import com.litt.core.service.BaseService;
import com.litt.saap.system.dao.UserMessageDao;
import com.litt.saap.system.po.UserMessage;
import com.litt.saap.system.service.IUserMessageService;

/**
 * 
 * Message service impl.
 * <pre><b>Description：</b>
 *    User Message
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">Bob.cai</a>
 * @since 2013-11-19
 * @version 1.0
 */
public class UserMessageServiceImpl extends BaseService implements IUserMessageService
{ 
	/** Logger. */
    private static final Logger logger = LoggerFactory.getLogger(UserMessageServiceImpl.class);
    
    @Resource
    private UserMessageDao userMessageDao;

   	/**
	 * Save.
	 * @param userMessage UserMessage
	 * @return id
	 */
	public Integer save(UserMessage userMessage)
	{
		return userMessageDao.save(userMessage);
	}
	
   	/**
	 * Update.
	 * @param userMessage UserMessage
	 */
	public void update(UserMessage userMessage)
	{
		userMessageDao.update(userMessage);
	}			
   
   	/**
	 * Delete by id.
	 * @param id id
	 */
	public void delete(Integer id)
	{
		userMessageDao.delete(UserMessage.class, "id", id);
	}
	
	/**
	 * Delete by instance.
	 * @param id id
	 */
	public void delete(UserMessage userMessage)
	{
		userMessageDao.delete(userMessage);
	}
	
	/**
	 * Load by id.
	 * @param id id
	 * @return UserMessage
	 */
	public UserMessage load(Integer id)
	{
		return userMessageDao.load(UserMessage.class, id);
	}
	
	/**
	 * list by page.
	 * 
	 * @param pageParam params
	 * @return IPageList IPageList
	 */
	public IPageList listPage(PageParam pageParam)
	{
		String listHql = "select obj from UserMessage obj"
			+ "-- and obj.name={name}"
			;	
		return userMessageDao.listPage(listHql, pageParam);
	}
}