package com.litt.saap.system.dao;

import org.slf4j.Logger;     
import org.slf4j.LoggerFactory;  

import com.litt.core.dao.GenericHibernateDao;

import com.litt.saap.system.po.TenantOrder;

/**
 * 
 * Order Dao.
 * <pre><b>Description：</b>
 *    Order Management
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">Bob.cai</a>
 * @since 2014-05-14
 * @version 1.0
 */
public class TenantOrderDao extends GenericHibernateDao<TenantOrder, Integer>
{
	/** Logger. */
    private static final Logger logger = LoggerFactory.getLogger(TenantOrderDao.class);    
    

}