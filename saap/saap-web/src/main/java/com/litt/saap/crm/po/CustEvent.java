package com.litt.saap.crm.po;

import java.util.Date;

import java.io.Serializable;

/**
 * <br>
 * Table:cust_event<br>
 * @author Hibernate Tools 3.4.0.CR1
 * @version 1.0
 * @since 2014-5-19 13:46:04
 */
public class CustEvent implements Serializable {
	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 序号.
	 */
	private Integer id;

	/**
	 * 租户ID.
	 */
	private int tenantId;

	/**
	 * 客户ID.
	 */
	private int customerId;

	/**
	 * 记录ID.
	 */
	private int recordId;

	/**
	 * 事件类型.
	 */
	private int eventType;

	/**
	 * 内容.
	 */
	private String content;

	/**
	 * 事件发生用户.
	 */
	private int eventBy;

	/**
	 * 事件发生时间.
	 */
	private Date eventDate;

	public CustEvent() {
	}

	public CustEvent(int tenantId, int customerId, int recordId, int eventType,
			String content, int eventBy, Date eventDate) {
		this.tenantId = tenantId;
		this.customerId = customerId;
		this.recordId = recordId;
		this.eventType = eventType;
		this.content = content;
		this.eventBy = eventBy;
		this.eventDate = eventDate;
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
	 * Get 客户ID.
	 * @return 客户ID
	 */
	public int getCustomerId() {
		return this.customerId;
	}

	/**
	 * Set 客户ID.
	 * @param customerId 客户ID
	 */
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	/**  
	 * Get 记录ID.
	 * @return 记录ID
	 */
	public int getRecordId() {
		return this.recordId;
	}

	/**
	 * Set 记录ID.
	 * @param recordId 记录ID
	 */
	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}

	/**  
	 * Get 事件类型.
	 * @return 事件类型
	 */
	public int getEventType() {
		return this.eventType;
	}

	/**
	 * Set 事件类型.
	 * @param eventType 事件类型
	 */
	public void setEventType(int eventType) {
		this.eventType = eventType;
	}

	/**  
	 * Get 内容.
	 * @return 内容
	 */
	public String getContent() {
		return this.content;
	}

	/**
	 * Set 内容.
	 * @param content 内容
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**  
	 * Get 事件发生用户.
	 * @return 事件发生用户
	 */
	public int getEventBy() {
		return this.eventBy;
	}

	/**
	 * Set 事件发生用户.
	 * @param eventBy 事件发生用户
	 */
	public void setEventBy(int eventBy) {
		this.eventBy = eventBy;
	}

	/**  
	 * Get 事件发生时间.
	 * @return 事件发生时间
	 */
	public Date getEventDate() {
		return this.eventDate;
	}

	/**
	 * Set 事件发生时间.
	 * @param eventDate 事件发生时间
	 */
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

}
