package com.litt.saap.system.service.impl;

import java.math.BigDecimal;
import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.litt.core.dao.page.IPageList;
import com.litt.core.dao.ql.PageParam;
import com.litt.core.exception.BusiCodeException;
import com.litt.core.format.FormatDateTime;
import com.litt.saap.core.common.SaapConstants.TenantOrderStatus;
import com.litt.saap.core.common.SaapConstants.TenantOrderType;
import com.litt.saap.system.dao.TenantOrderDao;
import com.litt.saap.system.po.TenantOrder;
import com.litt.saap.system.service.ITenantOrderService;

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
	
	/* (non-Javadoc)
	 * @see com.litt.saap.system.service.ITenantOrderService#save(int, java.lang.String, java.lang.String, java.lang.String, int, java.math.BigDecimal, int, int)
	 */
	public Integer save(int orderType, String tenantCode, String tenantAlias, String bagCode, int isolatedMode, BigDecimal price, int quantity, int createBy)
	{
		//数据校验
		/*
		 * 租户编号必须唯一：新订单，并且状态会是可激活的
		 */
		String countHql = "select count(t) from TenantOrder t where t.orderType=1 and t.tenantCode=? and (t.status=-2 or t.status=-1 or t.status=1)";
		int count = tenantOrderDao.count(countHql, new Object[]{tenantCode});
		if(count>0)
			throw new BusiCodeException("tenantOrder.func.new.error.codeDuplicated", new Object[]{tenantCode});
		
		Date curDate = new Date();
		TenantOrder tenantOrder = new TenantOrder();
		//生成规则：年月日时分秒+用户ID
		String orderNo = FormatDateTime.formatDateTimeNum(curDate) + createBy;
		tenantOrder.setOrderNo(orderNo);
		tenantOrder.setOrderType(orderType);
		tenantOrder.setTenantId(-1);
		tenantOrder.setTenantCode(tenantCode);
		tenantOrder.setTenantAlias(tenantAlias);
		tenantOrder.setBagCode(bagCode);
		tenantOrder.setIsolatedMode(isolatedMode);
		tenantOrder.setPrice(price);
		tenantOrder.setQuantity(quantity);
		tenantOrder.setStatus(TenantOrderStatus.TOBE_PAY);
		tenantOrder.setCreateBy(createBy);
		tenantOrder.setCreateDatetime(curDate);
		
		Integer id = tenantOrderDao.save(tenantOrder);
		
		return id;
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
	 * 付费.
	 *
	 * @param orderNo the order no
	 */
	public void doPaid(String orderNo, String payChannel)
	{
		TenantOrder tenantOrder = this.loadByNo(orderNo);
		if(tenantOrder==null)
			throw new BusiCodeException("tenantOrder.error.notExist");
		
		tenantOrder.setStatus(TenantOrderStatus.TOBE_ACTIVATE);
		tenantOrder.setPayChannel(payChannel);
		tenantOrder.setPayDatetime(new Date());
		this.update(tenantOrder);
	}
	
	/**
	 * 激活订单.
	 *
	 * @param orderNo the order no
	 */
	public void doActivate(String orderNo)
	{
		TenantOrder tenantOrder = this.loadByNo(orderNo);
		if(tenantOrder==null)
			throw new BusiCodeException("tenantOrder.error.notExist");
		
		if(TenantOrderStatus.TOBE_PAY ==  tenantOrder.getStatus())
			throw new BusiCodeException("tenantOrder.func.activate.error.notPaid");
		else if(TenantOrderStatus.ACTIVATED ==  tenantOrder.getStatus())
			throw new BusiCodeException("tenantOrder.func.activate.error.alreadyActivated");
		else if(TenantOrderStatus.CANCELED ==  tenantOrder.getStatus())
			throw new BusiCodeException("tenantOrder.func.activate.error.cancelled");
		else if(TenantOrderStatus.INVALID ==  tenantOrder.getStatus())
			throw new BusiCodeException("tenantOrder.func.activate.error.invalid");
		
		tenantOrder.setStatus(TenantOrderStatus.ACTIVATED);
		tenantOrder.setActivateDatetime(new Date());
		this.update(tenantOrder);
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