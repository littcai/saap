package com.litt.saap.crm.dao;

import org.slf4j.Logger;     
import org.slf4j.LoggerFactory;  

import com.litt.core.dao.GenericHibernateDao;

import com.litt.saap.crm.po.CustContacts;

/**
 * 
 * Contacts Dao.
 * <pre><b>Description：</b>
 *    Customer Contacts Management
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">Bob.cai</a>
 * @since 2013-10-29
 * @version 1.0
 */
public class CustContactsDao extends GenericHibernateDao<CustContacts, Integer>
{
	/** Logger. */
    private static final Logger logger = LoggerFactory.getLogger(CustContactsDao.class);    
    

}