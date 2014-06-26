package com.litt.saap.message.dao;

import org.slf4j.Logger;     
import org.slf4j.LoggerFactory;  

import com.litt.core.dao.GenericHibernateDao;

import com.litt.saap.message.po.Feedback;

/**
 * 
 * Feedback Dao.
 * <pre><b>Description：</b>
 *    Feedback Management.
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">Bob.cai</a>
 * @since 2014-06-26
 * @version 1.0
 */
public class FeedbackDao extends GenericHibernateDao<Feedback, Integer>
{
	/** Logger. */
    private static final Logger logger = LoggerFactory.getLogger(FeedbackDao.class);    
    

}