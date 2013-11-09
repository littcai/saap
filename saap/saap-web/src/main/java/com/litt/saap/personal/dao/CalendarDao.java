package com.litt.saap.personal.dao;

import org.slf4j.Logger;     
import org.slf4j.LoggerFactory;  

import com.litt.core.dao.GenericHibernateDao;

import com.litt.saap.personal.po.Calendar;

/**
 * 
 * Calendar Dao.
 * <pre><b>Description：</b>
 *    Calendar
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">Bob.cai</a>
 * @since 2013-09-12
 * @version 1.0
 */
public class CalendarDao extends GenericHibernateDao<Calendar, Integer>
{
	/** Logger. */
    private static final Logger logger = LoggerFactory.getLogger(CalendarDao.class);    
    

}