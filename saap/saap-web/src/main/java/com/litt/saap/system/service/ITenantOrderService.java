package com.litt.saap.system.service;

import java.math.BigDecimal;

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

	public Integer save(int orderType, String tenantCode, String tenantAlias, String bagCode, int isolatedMode, BigDecimal price, int quantity, int createBy);
	
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
	 * 付费.
	 *
	 * @param orderNo the order no
	 */
	public void doPaid(String orderNo, String payChannel);
	
	/**
	 * 激活订单.
	 *
	 * @param orderNo the order no
	 */
	public void doActivate(String orderNo);
	
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