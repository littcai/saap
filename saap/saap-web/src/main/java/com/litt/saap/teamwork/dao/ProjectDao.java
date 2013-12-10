package com.litt.saap.teamwork.dao;

import org.slf4j.Logger;     
import org.slf4j.LoggerFactory;  

import com.litt.core.dao.GenericHibernateDao;

import com.litt.saap.teamwork.po.Project;

/**
 * 
 * Project Dao.
 * <pre><b>Description：</b>
 *    Project Management
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">Bob.cai</a>
 * @since 2013-12-04
 * @version 1.0
 */
public class ProjectDao extends GenericHibernateDao<Project, Integer>
{
	/** Logger. */
    private static final Logger logger = LoggerFactory.getLogger(ProjectDao.class);    
    

}