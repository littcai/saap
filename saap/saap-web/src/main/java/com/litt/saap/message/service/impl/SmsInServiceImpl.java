package com.litt.saap.message.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;     
import org.slf4j.LoggerFactory; 

import com.litt.core.dao.page.IPageList;
import com.litt.core.dao.ql.PageParam;
import com.litt.saap.core.web.util.LoginUtils;
import com.litt.core.exception.BusiException;
import com.litt.core.service.BaseService;
import com.litt.saap.message.service.ISmsInService;

import com.litt.saap.message.po.SmsIn;
import com.litt.saap.message.dao.SmsInDao;

/**
 * 
 * SMS In service impl.
 * <pre><b>Description：</b>
 *    SMS In Management
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">Bob.cai</a>
 * @since 2014-02-17
 * @version 1.0
 */
public class SmsInServiceImpl implements ISmsInService
{ 
	/** Logger. */
    private static final Logger logger = LoggerFactory.getLogger(SmsInServiceImpl.class);
    
    @Resource
    private SmsInDao smsInDao;

   	/**
	 * Save.
	 * @param smsIn SmsIn
	 * @return id
	 */
	public Integer save(SmsIn smsIn)
	{
		return smsInDao.save(smsIn);
	}
	
   	/**
	 * Update.
	 * @param smsIn SmsIn
	 */
	public void update(SmsIn smsIn) 
	{
		//校验租户权限
		LoginUtils.validateTenant(smsIn.getTenantId());
	
		smsInDao.update(smsIn);
	}			
   
   	/**
	 * Delete by id.
	 * @param id id
	 */
	public void delete(Integer id) 
	{
		SmsIn smsIn = this.load(id);
		this.delete(smsIn);
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
	public void delete(SmsIn smsIn) 
	{
		//校验租户权限
		LoginUtils.validateTenant(smsIn.getTenantId());
	
		smsInDao.delete(smsIn);
	}
	
	/**
	 * Load by id.
	 * @param id id
	 * @return SmsIn
	 */
	public SmsIn load(Integer id) 
	{
		SmsIn smsIn = smsInDao.load(id);
		//校验租户权限
		LoginUtils.validateTenant(smsIn.getTenantId());
	
		return smsIn;
	}
	
	/**
	 * list by page.
	 * 
	 * @param pageParam params
	 * @return IPageList IPageList
	 */
	public IPageList listPage(PageParam pageParam)
	{
		String listHql = "select obj from SmsIn obj"
			+ "-- and obj.tenantId={tenantId}"
			+ "-- and obj.sender={sender}"
			+ "-- and obj.receiver={receiver}"
			;	
		return smsInDao.listPage(listHql, pageParam);
	}
}