package com.litt.saap.message.dao;

import org.slf4j.Logger;     
import org.slf4j.LoggerFactory;  

import com.litt.core.dao.GenericHibernateDao;

import com.litt.saap.message.po.SmsOut;

/**
 * 
 * SMS Dao.
 * <pre><b>Description：</b>
 *    SMS Management
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">Bob.cai</a>
 * @since 2014-02-17
 * @version 1.0
 */
public class SmsOutDao extends GenericHibernateDao<SmsOut, Integer>
{
	/** Logger. */
    private static final Logger logger = LoggerFactory.getLogger(SmsOutDao.class);    
    

}