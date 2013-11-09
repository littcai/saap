package com.litt.saap.personal.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.litt.core.dao.page.IPageList;
import com.litt.core.dao.ql.PageParam;
import com.litt.saap.personal.dao.CalendarDao;
import com.litt.saap.personal.po.Calendar;
import com.litt.saap.personal.service.ICalendarService;

/**
 * 
 * Calendar service impl.
 * <pre><b>Description：</b>
 *    Calendar
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">Bob.cai</a>
 * @since 2013-09-12
 * @version 1.0
 */
public class CalendarServiceImpl implements ICalendarService
{ 
	/** Logger. */
    private static final Logger logger = LoggerFactory.getLogger(CalendarServiceImpl.class);
    
    @Resource
    private CalendarDao calendarDao;

   	/**
	 * Save.
	 * @param calendar Calendar
	 * @return id
	 */
	public Integer save(Calendar calendar)
	{
		return calendarDao.save(calendar);
	}
	
   	/**
	 * Update.
	 * @param calendar Calendar
	 */
	public void update(Calendar calendar)
	{
		calendarDao.update(calendar);
	}			
   
   	/**
	 * Delete by id.
	 * @param id id
	 */
	public void delete(Integer id)
	{
		calendarDao.delete(Calendar.class, "id", id);
	}
	
	/**
	 * Delete by instance.
	 * @param id id
	 */
	public void delete(Calendar calendar)
	{
		calendarDao.delete(calendar);
	}
	
	/**
	 * Load by id.
	 * @param id id
	 * @return Calendar
	 */
	public Calendar load(Integer id)
	{
		return calendarDao.load(Calendar.class, id);
	}
	
	/**
	 * list by page.
	 * 
	 * @param pageParam params
	 * @return IPageList IPageList
	 */
	public IPageList listPage(PageParam pageParam)
	{
		String listHql = "select obj from Calendar obj"
			+ "-- and obj.createUserId={userId}"
			+ "-- order by obj.updateDatetime desc"	
			;	
		return calendarDao.listPage(listHql, pageParam);
	}
}