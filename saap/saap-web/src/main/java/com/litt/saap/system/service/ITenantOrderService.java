package com.litt.saap.system.service;

import com.litt.core.dao.page.IPageList;
import com.litt.core.dao.ql.PageParam;

import com.litt.saap.system.po.TenantOrder;

/**
 * 
 * Order service interface.
 * <pre><b>Description：</b>
 *    Order Management
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">Bob.cai</a>
 * @since 2014-05-14
 * @version 1.0
 */
public interface ITenantOrderService
{ 

   	/**
	 * Save.
	 * @param tenantOrder TenantOrder
	 * @return id
	 */
	public Integer save(TenantOrder tenantOrder);
	
   	/**
	 * Update.
	 * @param tenantOrder TenantOrder
	 */
	public void update(TenantOrder tenantOrder);				
   
   	/**
	 * Delete by id.
	 * @param id id
	 */
	public void delete(Integer id);	
	
	/**
	 * Delete by instance.
	 * @param id id
	 */
	public void delete(TenantOrder tenantOrder);
	
	/**
	 * Batch delete by ids.
	 * @param ids ids
	 */
	public void deleteBatch(Integer[] ids);
	
	/**
	 * Load by id.
	 * @param id id
	 * @return TenantOrder
	 */
	public TenantOrder load(Integer id);	
	
	/**
	 * Load by id.
	 * @param id id
	 * @return TenantOrder
	 */
	public TenantOrder loadByNo(String orderNo);	
	
	/**
	 * list by page.
	 * 
	 * @param pageParam params
	 * @return IPageList IPageList
	 */
	public IPageList listPage(PageParam pageParam);	

}