package com.litt.saap.system.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;     
import org.slf4j.LoggerFactory; 

import com.litt.core.dao.page.IPageList;
import com.litt.core.dao.ql.PageParam;
import com.litt.saap.core.web.util.LoginUtils;
import com.litt.core.exception.BusiException;
import com.litt.core.service.BaseService;
import com.litt.saap.system.service.ITenantOrderService;
import com.litt.saap.system.po.TenantOrder;
import com.litt.saap.system.dao.TenantOrderDao;

/**
 * 
 * Order service impl.
 * <pre><b>Description：</b>
 *    Order Management
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">Bob.cai</a>
 * @since 2014-05-14
 * @version 1.0
 */
public class TenantOrderServiceImpl implements ITenantOrderService
{ 
	/** Logger. */
    private static final Logger logger = LoggerFactory.getLogger(TenantOrderServiceImpl.class);
    
    @Resource
    private TenantOrderDao tenantOrderDao;

   	/**
	 * Save.
	 * @param tenantOrder TenantOrder
	 * @return id
	 */
	public Integer save(TenantOrder tenantOrder)
	{
		return tenantOrderDao.save(tenantOrder);
	}
	
   	/**
	 * Update.
	 * @param tenantOrder TenantOrder
	 */
	public void update(TenantOrder tenantOrder) 
	{	
		tenantOrderDao.update(tenantOrder);
	}			
   
   	/**
	 * Delete by id.
	 * @param id id
	 */
	public void delete(Integer id) 
	{
		TenantOrder tenantOrder = this.load(id);
		this.delete(tenantOrder);
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
	public void delete(TenantOrder tenantOrder) 
	{	
		tenantOrderDao.delete(tenantOrder);
	}
	
	/**
	 * Load by id.
	 * @param id id
	 * @return TenantOrder
	 */
	public TenantOrder load(Integer id) 
	{
		TenantOrder tenantOrder = tenantOrderDao.load(id);
	
		return tenantOrder;
	}
	
	/**
	 * Load by id.
	 * @param id id
	 * @return TenantOrder
	 */
	public TenantOrder loadByNo(String orderNo)
	{
		TenantOrder tenantOrder = tenantOrderDao.load(TenantOrder.class, "orderNo", orderNo);
		return tenantOrder;
	}
	
	/**
	 * list by page.
	 * 
	 * @param pageParam params
	 * @return IPageList IPageList
	 */
	public IPageList listPage(PageParam pageParam)
	{
		String listHql = "select obj from TenantOrder obj"
			+ "-- and obj.tenantId={tenantId}"
			;	
		return tenantOrderDao.listPage(listHql, pageParam);
	}
}