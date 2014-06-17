package com.litt.saap.crm.dao;

import org.slf4j.Logger;     
import org.slf4j.LoggerFactory;  

import com.litt.core.dao.GenericHibernateDao;

import com.litt.saap.crm.po.CustEvent;

/**
 * 
 * Event Dao.
 * <pre><b>Description：</b>
 *    Customer Events Management
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">Bob.cai</a>
 * @since 2014-05-19
 * @version 1.0
 */
public class CustEventDao extends GenericHibernateDao<CustEvent, Integer>
{
	/** Logger. */
    private static final Logger logger = LoggerFactory.getLogger(CustEventDao.class);    
    

}