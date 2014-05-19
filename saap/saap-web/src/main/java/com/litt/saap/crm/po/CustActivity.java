package com.litt.saap.crm.po;

import java.util.Date;

import java.io.Serializable;

/**
 * <br>
 * Table:cust_activity<br>
 * @author Hibernate Tools 3.4.0.CR1
 * @version 1.0
 * @since 2014-5-19 13:46:04
 */
public class CustActivity implements Serializable {
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
	 * 联系人ID.
	 */
	private int contactId;

	/**
	 * 联系类型.
	 */
	private int actType;

	/**
	 * 主题.
	 */
	private String subject;

	/**
	 * 内容.
	 */
	private String content;

	/**
	 * 联系日期.
	 */
	private Date actDate;

	/**
	 */
	private int chargeBy;

	/**
	 * 创建时间.
	 */
	private Date createDatetime;

	/**
	 */
	private int createBy;

	/**
	 * 更新时间.
	 */
	private Date updateDatetime;

	/**
	 */
	private int updateBy;

	/**
	 * 下次往来日期.
	 */
	private Date nextActDate;

	public CustActivity() {
	}

	public CustActivity(int tenantId, int customerId, int contactId,
			int actType, String subject, Date actDate, int chargeBy,
			Date createDatetime, int createBy, Date updateDatetime, int updateBy) {
		this.tenantId = tenantId;
		this.customerId = customerId;
		this.contactId = contactId;
		this.actType = actType;
		this.subject = subject;
		this.actDate = actDate;
		this.chargeBy = chargeBy;
		this.createDatetime = createDatetime;
		this.createBy = createBy;
		this.updateDatetime = updateDatetime;
		this.updateBy = updateBy;
	}

	public CustActivity(int tenantId, int customerId, int contactId,
			int actType, String subject, String content, Date actDate,
			int chargeBy, Date createDatetime, int createBy,
			Date updateDatetime, int updateBy, Date nextActDate) {
		this.tenantId = tenantId;
		this.customerId = customerId;
		this.contactId = contactId;
		this.actType = actType;
		this.subject = subject;
		this.content = content;
		this.actDate = actDate;
		this.chargeBy = chargeBy;
		this.createDatetime = createDatetime;
		this.createBy = createBy;
		this.updateDatetime = updateDatetime;
		this.updateBy = updateBy;
		this.nextActDate = nextActDate;
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
	 * Get 联系人ID.
	 * @return 联系人ID
	 */
	public int getContactId() {
		return this.contactId;
	}

	/**
	 * Set 联系人ID.
	 * @param contactId 联系人ID
	 */
	public void setContactId(int contactId) {
		this.contactId = contactId;
	}

	/**  
	 * Get 联系类型.
	 * @return 联系类型
	 */
	public int getActType() {
		return this.actType;
	}

	/**
	 * Set 联系类型.
	 * @param actType 联系类型
	 */
	public void setActType(int actType) {
		this.actType = actType;
	}

	/**  
	 * Get 主题.
	 * @return 主题
	 */
	public String getSubject() {
		return this.subject;
	}

	/**
	 * Set 主题.
	 * @param subject 主题
	 */
	public void setSubject(String subject) {
		this.subject = subject;
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
	 * Get 联系日期.
	 * @return 联系日期
	 */
	public Date getActDate() {
		return this.actDate;
	}

	/**
	 * Set 联系日期.
	 * @param actDate 联系日期
	 */
	public void setActDate(Date actDate) {
		this.actDate = actDate;
	}

	/**  
	 */
	public int getChargeBy() {
		return this.chargeBy;
	}

	/**
	 */
	public void setChargeBy(int chargeBy) {
		this.chargeBy = chargeBy;
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
	 */
	public int getCreateBy() {
		return this.createBy;
	}

	/**
	 */
	public void setCreateBy(int createBy) {
		this.createBy = createBy;
	}

	/**  
	 * Get 更新时间.
	 * @return 更新时间
	 */
	public Date getUpdateDatetime() {
		return this.updateDatetime;
	}

	/**
	 * Set 更新时间.
	 * @param updateDatetime 更新时间
	 */
	public void setUpdateDatetime(Date updateDatetime) {
		this.updateDatetime = updateDatetime;
	}

	/**  
	 */
	public int getUpdateBy() {
		return this.updateBy;
	}

	/**
	 */
	public void setUpdateBy(int updateBy) {
		this.updateBy = updateBy;
	}

	/**  
	 * Get 下次往来日期.
	 * @return 下次往来日期
	 */
	public Date getNextActDate() {
		return this.nextActDate;
	}

	/**
	 * Set 下次往来日期.
	 * @param nextActDate 下次往来日期
	 */
	public void setNextActDate(Date nextActDate) {
		this.nextActDate = nextActDate;
	}

}
