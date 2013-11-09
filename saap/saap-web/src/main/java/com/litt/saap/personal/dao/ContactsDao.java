package com.litt.saap.personal.dao;

import org.slf4j.Logger;     
import org.slf4j.LoggerFactory;  

import com.litt.core.dao.GenericHibernateDao;

import com.litt.saap.personal.po.Contacts;

/**
 * 
 * Contacts Dao.
 * <pre><b>Description：</b>
 *    Contacts
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">Bob.cai</a>
 * @since 2013-09-18
 * @version 1.0
 */
public class ContactsDao extends GenericHibernateDao<Contacts, Integer>
{
	/** Logger. */
    private static final Logger logger = LoggerFactory.getLogger(ContactsDao.class);    
    

}