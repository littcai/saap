package com.litt.saap.crm.po;

import java.math.BigDecimal;
import java.util.Date;

import java.io.Serializable;

/**
 * 客户特征表<br>
 * 表名：customer_feature<br>
 * @author Hibernate Tools 3.2.4.GA
 * @version 1.0
 * @since 2013-10-14 23:28:21
 */
public class CustomerFeature implements Serializable {
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
	 * 编号.
	 */
	private String code;

	/**
	 * 名称.
	 */
	private String name;

	/**
	 * 数据类型.
	 */
	private byte dataType;

	/**
	 * 数据值.
	 */
	private String dataValue;

	/**
	 * 整型值.
	 */
	private Integer dataValueInt;

	/**
	 * 日期值.
	 */
	private Date dataValueDate;

	/**
	 * 浮点值.
	 */
	private BigDecimal dataValueDecimal;

	/**
	 * 创建时间.
	 */
	private Date createDatetime;

	/**
	 * 更新时间.
	 */
	private Date updateDatetime;

	public CustomerFeature() {
	}

	public CustomerFeature(int tenantId, int customerId, String code,
			String name, byte dataType, Date createDatetime, Date updateDatetime) {
		this.tenantId = tenantId;
		this.customerId = customerId;
		this.code = code;
		this.name = name;
		this.dataType = dataType;
		this.createDatetime = createDatetime;
		this.updateDatetime = updateDatetime;
	}

	public CustomerFeature(int tenantId, int customerId, String code,
			String name, byte dataType, String dataValue, Integer dataValueInt,
			Date dataValueDate, BigDecimal dataValueDecimal,
			Date createDatetime, Date updateDatetime) {
		this.tenantId = tenantId;
		this.customerId = customerId;
		this.code = code;
		this.name = name;
		this.dataType = dataType;
		this.dataValue = dataValue;
		this.dataValueInt = dataValueInt;
		this.dataValueDate = dataValueDate;
		this.dataValueDecimal = dataValueDecimal;
		this.createDatetime = createDatetime;
		this.updateDatetime = updateDatetime;
	}

	/**  
	 * 取得 序号.
	 * @return 序号
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * 设置 序号.
	 * @param id 序号
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**  
	 * 取得 租户ID.
	 * @return 租户ID
	 */
	public int getTenantId() {
		return this.tenantId;
	}

	/**
	 * 设置 租户ID.
	 * @param tenantId 租户ID
	 */
	public void setTenantId(int tenantId) {
		this.tenantId = tenantId;
	}

	/**  
	 * 取得 客户ID.
	 * @return 客户ID
	 */
	public int getCustomerId() {
		return this.customerId;
	}

	/**
	 * 设置 客户ID.
	 * @param customerId 客户ID
	 */
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	/**  
	 * 取得 编号.
	 * @return 编号
	 */
	public String getCode() {
		return this.code;
	}

	/**
	 * 设置 编号.
	 * @param code 编号
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**  
	 * 取得 名称.
	 * @return 名称
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * 设置 名称.
	 * @param name 名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**  
	 * 取得 数据类型.
	 * @return 数据类型
	 */
	public byte getDataType() {
		return this.dataType;
	}

	/**
	 * 设置 数据类型.
	 * @param dataType 数据类型
	 */
	public void setDataType(byte dataType) {
		this.dataType = dataType;
	}

	/**  
	 * 取得 数据值.
	 * @return 数据值
	 */
	public String getDataValue() {
		return this.dataValue;
	}

	/**
	 * 设置 数据值.
	 * @param dataValue 数据值
	 */
	public void setDataValue(String dataValue) {
		this.dataValue = dataValue;
	}

	/**  
	 * 取得 整型值.
	 * @return 整型值
	 */
	public Integer getDataValueInt() {
		return this.dataValueInt;
	}

	/**
	 * 设置 整型值.
	 * @param dataValueInt 整型值
	 */
	public void setDataValueInt(Integer dataValueInt) {
		this.dataValueInt = dataValueInt;
	}

	/**  
	 * 取得 日期值.
	 * @return 日期值
	 */
	public Date getDataValueDate() {
		return this.dataValueDate;
	}

	/**
	 * 设置 日期值.
	 * @param dataValueDate 日期值
	 */
	public void setDataValueDate(Date dataValueDate) {
		this.dataValueDate = dataValueDate;
	}

	/**  
	 * 取得 浮点值.
	 * @return 浮点值
	 */
	public BigDecimal getDataValueDecimal() {
		return this.dataValueDecimal;
	}

	/**
	 * 设置 浮点值.
	 * @param dataValueDecimal 浮点值
	 */
	public void setDataValueDecimal(BigDecimal dataValueDecimal) {
		this.dataValueDecimal = dataValueDecimal;
	}

	/**  
	 * 取得 创建时间.
	 * @return 创建时间
	 */
	public Date getCreateDatetime() {
		return this.createDatetime;
	}

	/**
	 * 设置 创建时间.
	 * @param createDatetime 创建时间
	 */
	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	/**  
	 * 取得 更新时间.
	 * @return 更新时间
	 */
	public Date getUpdateDatetime() {
		return this.updateDatetime;
	}

	/**
	 * 设置 更新时间.
	 * @param updateDatetime 更新时间
	 */
	public void setUpdateDatetime(Date updateDatetime) {
		this.updateDatetime = updateDatetime;
	}

}
