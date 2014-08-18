package com.litt.saap.system.po;

import java.math.BigDecimal;
import java.util.Date;

import java.io.Serializable;

/**
 * <br>
 * Table:tenant_order<br>
 * @author Hibernate Tools 3.4.0.CR1
 * @version 1.0
 * @since 2014-5-9 12:39:33
 */
public class TenantOrder implements Serializable {
	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 序号.
	 */
	private Integer id;

	/**
	 * 订单编号.
	 */
	private String orderNo;

	/**
	 * 订单类型.
	 */
	private int orderType;

	/**
	 * 租户ID.
	 */
	private int tenantId;

	/**
	 * 租户编号.
	 */
	private String tenantCode;

	/**
	 * 租户别名.
	 */
	private String tenantAlias;

	/**
	 * 功能包编号.
	 */
	private String bagCode;

	/**
	 * 隔离级别.
	 */
	private int isolatedMode;

	/**
	 * 价格.
	 */
	private BigDecimal price;

	/**
	 * 购买份数.
	 */
	private int quantity;

	/**
	 * 状态.
	 */
	private int status;

	/**
	 * 创建人.
	 */
	private int createBy;

	/**
	 * 创建时间.
	 */
	private Date createDatetime;

	/**
	 * 付款通道.
	 */
	private String payChannel;

	/**
	 * 付款时间.
	 */
	private Date payDatetime;
	
	/**
	 * 生效时间.
	 */
	private Date activateDatetime;

	public TenantOrder() {
	}

	public TenantOrder(String orderNo, int orderType, int tenantId,
			String tenantCode, String tenantAlias, String bagCode,
			int isolatedMode, BigDecimal price, int quantity, int status,
			int createBy, Date createDatetime) {
		this.orderNo = orderNo;
		this.orderType = orderType;
		this.tenantId = tenantId;
		this.tenantCode = tenantCode;
		this.tenantAlias = tenantAlias;
		this.bagCode = bagCode;
		this.isolatedMode = isolatedMode;
		this.price = price;
		this.quantity = quantity;
		this.status = status;
		this.createBy = createBy;
		this.createDatetime = createDatetime;
	}

	public TenantOrder(String orderNo, int orderType, int tenantId,
			String tenantCode, String tenantAlias, String bagCode,
			int isolatedMode, BigDecimal price, int quantity, int status,
			int createBy, Date createDatetime, String payChannel,
			Date payDatetime) {
		this.orderNo = orderNo;
		this.orderType = orderType;
		this.tenantId = tenantId;
		this.tenantCode = tenantCode;
		this.tenantAlias = tenantAlias;
		this.bagCode = bagCode;
		this.isolatedMode = isolatedMode;
		this.price = price;
		this.quantity = quantity;
		this.status = status;
		this.createBy = createBy;
		this.createDatetime = createDatetime;
		this.payChannel = payChannel;
		this.payDatetime = payDatetime;
	}

	/**  
	 * Get 序号.
	 * @return 序号
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * Set 序号.
	 * @param id 序号
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**  
	 * Get 订单编号.
	 * @return 订单编号
	 */
	public String getOrderNo() {
		return this.orderNo;
	}

	/**
	 * Set 订单编号.
	 * @param orderNo 订单编号
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	/**  
	 * Get 订单类型.
	 * @return 订单类型
	 */
	public int getOrderType() {
		return this.orderType;
	}

	/**
	 * Set 订单类型.
	 * @param orderType 订单类型
	 */
	public void setOrderType(int orderType) {
		this.orderType = orderType;
	}

	/**  
	 * Get 租户ID.
	 * @return 租户ID
	 */
	public int getTenantId() {
		return this.tenantId;
	}

	/**
	 * Set 租户ID.
	 * @param tenantId 租户ID
	 */
	public void setTenantId(int tenantId) {
		this.tenantId = tenantId;
	}

	/**  
	 * Get 租户编号.
	 * @return 租户编号
	 */
	public String getTenantCode() {
		return this.tenantCode;
	}

	/**
	 * Set 租户编号.
	 * @param tenantCode 租户编号
	 */
	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}

	/**  
	 * Get 租户别名.
	 * @return 租户别名
	 */
	public String getTenantAlias() {
		return this.tenantAlias;
	}

	/**
	 * Set 租户别名.
	 * @param tenantAlias 租户别名
	 */
	public void setTenantAlias(String tenantAlias) {
		this.tenantAlias = tenantAlias;
	}

	/**  
	 * Get 功能包编号.
	 * @return 功能包编号
	 */
	public String getBagCode() {
		return this.bagCode;
	}

	/**
	 * Set 功能包编号.
	 * @param bagCode 功能包编号
	 */
	public void setBagCode(String bagCode) {
		this.bagCode = bagCode;
	}

	/**  
	 * Get 隔离级别.
	 * @return 隔离级别
	 */
	public int getIsolatedMode() {
		return this.isolatedMode;
	}

	/**
	 * Set 隔离级别.
	 * @param isolatedMode 隔离级别
	 */
	public void setIsolatedMode(int isolatedMode) {
		this.isolatedMode = isolatedMode;
	}

	/**  
	 * Get 价格.
	 * @return 价格
	 */
	public BigDecimal getPrice() {
		return this.price;
	}

	/**
	 * Set 价格.
	 * @param price 价格
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	/**  
	 * Get 购买份数.
	 * @return 购买份数
	 */
	public int getQuantity() {
		return this.quantity;
	}

	/**
	 * Set 购买份数.
	 * @param quantity 购买份数
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**  
	 * Get 状态.
	 * @return 状态
	 */
	public int getStatus() {
		return this.status;
	}

	/**
	 * Set 状态.
	 * @param status 状态
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**  
	 * Get 创建人.
	 * @return 创建人
	 */
	public int getCreateBy() {
		return this.createBy;
	}

	/**
	 * Set 创建人.
	 * @param createBy 创建人
	 */
	public void setCreateBy(int createBy) {
		this.createBy = createBy;
	}

	/**  
	 * Get 创建时间.
	 * @return 创建时间
	 */
	public Date getCreateDatetime() {
		return this.createDatetime;
	}

	/**
	 * Set 创建时间.
	 * @param createDatetime 创建时间
	 */
	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	/**  
	 * Get 付款通道.
	 * @return 付款通道
	 */
	public String getPayChannel() {
		return this.payChannel;
	}

	/**
	 * Set 付款通道.
	 * @param payChannel 付款通道
	 */
	public void setPayChannel(String payChannel) {
		this.payChannel = payChannel;
	}

	/**  
	 * Get 付款时间.
	 * @return 付款时间
	 */
	public Date getPayDatetime() {
		return this.payDatetime;
	}

	/**
	 * Set 付款时间.
	 * @param payDatetime 付款时间
	 */
	public void setPayDatetime(Date payDatetime) {
		this.payDatetime = payDatetime;
	}

	/**
	 * @return the activateDatetime
	 */
	public Date getActivateDatetime() {
		return activateDatetime;
	}

	/**
	 * @param activateDatetime the activateDatetime to set
	 */
	public void setActivateDatetime(Date activateDatetime) {
		this.activateDatetime = activateDatetime;
	}

}
