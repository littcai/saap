package com.litt.saap.system.dao;

import org.slf4j.Logger;     
import org.slf4j.LoggerFactory;  

import com.litt.core.dao.GenericHibernateDao;

import com.litt.saap.system.po.UserMessage;

/**
 * 
 * Message Dao.
 * <pre><b>Description：</b>
 *    User Message
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">Bob.cai</a>
 * @since 2013-11-19
 * @version 1.0
 */
public class UserMessageDao extends GenericHibernateDao<UserMessage, Integer>
{
	/** Logger. */
    private static final Logger logger = LoggerFactory.getLogger(UserMessageDao.class);    
    

}