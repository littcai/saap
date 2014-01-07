package com.litt.saap.teamwork.dao;

import org.slf4j.Logger;     
import org.slf4j.LoggerFactory;  

import com.litt.core.dao.GenericHibernateDao;

import com.litt.saap.teamwork.po.Topic;

/**
 * 
 * Discussion Dao.
 * <pre><b>Description：</b>
 *    Discussion
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">Bob.cai</a>
 * @since 2014-01-03
 * @version 1.0
 */
public class TopicDao extends GenericHibernateDao<Topic, Integer>
{
	/** Logger. */
    private static final Logger logger = LoggerFactory.getLogger(TopicDao.class);    
    

}