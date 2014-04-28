package com.litt.saap.assistant.dao;

import org.slf4j.Logger;     
import org.slf4j.LoggerFactory;  

import com.litt.core.dao.GenericHibernateDao;

import com.litt.saap.assistant.po.Attachment;

/**
 * 
 * Attachement Dao.
 * <pre><b>Description：</b>
 *    Attachement Management.
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">Bob.cai</a>
 * @since 2014-04-23
 * @version 1.0
 */
public class AttachmentDao extends GenericHibernateDao<Attachment, Integer>
{
	/** Logger. */
    private static final Logger logger = LoggerFactory.getLogger(AttachmentDao.class);    
    

}