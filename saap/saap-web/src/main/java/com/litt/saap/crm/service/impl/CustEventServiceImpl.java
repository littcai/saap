package com.litt.saap.crm.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;     
import org.slf4j.LoggerFactory; 

import com.litt.core.dao.page.IPageList;
import com.litt.core.dao.ql.PageParam;
import com.litt.saap.core.web.util.LoginUtils;
import com.litt.core.exception.BusiException;
import com.litt.core.service.BaseService;
import com.litt.saap.crm.service.ICustEventService;

import com.litt.saap.crm.po.CustEvent;
import com.litt.saap.crm.dao.CustEventDao;

/**
 * 
 * Event service impl.
 * <pre><b>Description：</b>
 *    Customer Events Management
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">Bob.cai</a>
 * @since 2014-05-19
 * @version 1.0
 */
public class CustEventServiceImpl implements ICustEventService
{ 
	/** Logger. */
    private static final Logger logger = LoggerFactory.getLogger(CustEventServiceImpl.class);
    
    @Resource
    private CustEventDao custEventDao;

   	/**
	 * Save.
	 * @param custEvent CustEvent
	 * @return id
	 */
	public Integer save(CustEvent custEvent)
	{
		return custEventDao.save(custEvent);
	}
	
   	/**
	 * Update.
	 * @param custEvent CustEvent
	 */
	public void update(CustEvent custEvent) 
	{
		//校验租户权限
		LoginUtils.validateTenant(custEvent.getTenantId());
	
		custEventDao.update(custEvent);
	}			
   
   	/**
	 * Delete by id.
	 * @param id id
	 */
	public void delete(Integer id) 
	{
		CustEvent custEvent = this.load(id);
		this.delete(custEvent);
	}
	
	/**
	 * Batch delete by ids.
	 * @param ids ids
	 */
	public void deleteBatch(Integer[] ids) 
	{
		if(ids!=null)
		{
			for (Integer id : ids) {
				this.delete(id);
			}
		}
	}
	
	/**
	 * Delete by instance.
	 * @param id id
	 */
	public void delete(CustEvent custEvent) 
	{
		//校验租户权限
		LoginUtils.validateTenant(custEvent.getTenantId());
	
		custEventDao.delete(custEvent);
	}
	
	/**
	 * Load by id.
	 * @param id id
	 * @return CustEvent
	 */
	public CustEvent load(Integer id) 
	{
		CustEvent custEvent = custEventDao.load(id);
		//校验租户权限
		LoginUtils.validateTenant(custEvent.getTenantId());
	
		return custEvent;
	}
	
	/**
	 * list by page.
	 * 
	 * @param pageParam params
	 * @return IPageList IPageList
	 */
	public IPageList listPage(PageParam pageParam)
	{
		String listHql = "select obj from CustEvent obj"
			+ "-- and obj.tenantId={tenantId}"
			;	
		return custEventDao.listPage(listHql, pageParam);
	}
}