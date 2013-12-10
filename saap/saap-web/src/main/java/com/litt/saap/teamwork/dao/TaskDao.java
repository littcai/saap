package com.litt.saap.teamwork.dao;

import org.slf4j.Logger;     
import org.slf4j.LoggerFactory;  

import com.litt.core.dao.GenericHibernateDao;

import com.litt.saap.teamwork.po.Task;

/**
 * 
 * Task Dao.
 * <pre><b>Description：</b>
 *    Task Management
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">Bob.cai</a>
 * @since 2013-12-04
 * @version 1.0
 */
public class TaskDao extends GenericHibernateDao<Task, Integer>
{
	/** Logger. */
    private static final Logger logger = LoggerFactory.getLogger(TaskDao.class);    
    

}