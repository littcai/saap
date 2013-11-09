package com.litt.saap.personal.dao;

import org.slf4j.Logger;     
import org.slf4j.LoggerFactory;  

import com.litt.core.dao.GenericHibernateDao;

import com.litt.saap.personal.po.Note;

/**
 * 
 * Note Dao.
 * <pre><b>Description：</b>
 *    Personal Note
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">Bob.cai</a>
 * @since 2013-10-29
 * @version 1.0
 */
public class NoteDao extends GenericHibernateDao<Note, Integer>
{
	/** Logger. */
    private static final Logger logger = LoggerFactory.getLogger(NoteDao.class);    
    

}