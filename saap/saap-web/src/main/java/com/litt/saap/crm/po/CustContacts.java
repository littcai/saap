package com.litt.saap.crm.po;

import java.util.Date;

import java.io.Serializable;

/**
 * <br>
 * Table:cust_contacts<br>
 * @author Hibernate Tools 3.4.0.CR1
 * @version 1.0
 * @since 2014-5-19 13:46:04
 */
public class CustContacts implements Serializable {
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
	 * 名称.
	 */
	private String name;

	/**
	 * 性别(0002)
	        0:unknown
	        1:male
	        2:female.
	 */
	private byte gender;

	/**
	 * 手机号.
	 */
	private String mobile;

	/**
	 * 电子邮件.
	 */
	private String email;

	/**
	 * 联系电话.
	 */
	private String phone;

	/**
	 * 传真号.
	 */
	private String fax;

	/**
	 * 地址.
	 */
	private String address;

	/**
	 * 邮编.
	 */
	private String zipCode;

	/**
	 */
	private int createBy;

	/**
	 * 创建时间.
	 */
	private Date createDatetime;

	/**
	 * 更新时间.
	 */
	private Date updateDatetime;

	/**
	 * 备注.
	 */
	private String remark;

	/**
	 * 头像URL.
	 */
	private String headImgUrl;

	public CustContacts() {
	}

	public CustContacts(int tenantId, int customerId, String name, byte gender,
			String mobile, String email, String phone, String fax,
			String address, String zipCode, int createBy, Date createDatetime,
			Date updateDatetime) {
		this.tenantId = tenantId;
		this.customerId = customerId;
		this.name = name;
		this.gender = gender;
		this.mobile = mobile;
		this.email = email;
		this.phone = phone;
		this.fax = fax;
		this.address = address;
		this.zipCode = zipCode;
		this.createBy = createBy;
		this.createDatetime = createDatetime;
		this.updateDatetime = updateDatetime;
	}

	public CustContacts(int tenantId, int customerId, String name, byte gender,
			String mobile, String email, String phone, String fax,
			String address, String zipCode, int createBy, Date createDatetime,
			Date updateDatetime, String remark, String headImgUrl) {
		this.tenantId = tenantId;
		this.customerId = customerId;
		this.name = name;
		this.gender = gender;
		this.mobile = mobile;
		this.email = email;
		this.phone = phone;
		this.fax = fax;
		this.address = address;
		this.zipCode = zipCode;
		this.createBy = createBy;
		this.createDatetime = createDatetime;
		this.updateDatetime = updateDatetime;
		this.remark = remark;
		this.headImgUrl = headImgUrl;
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
	 * Get 名称.
	 * @return 名称
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Set 名称.
	 * @param name 名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**  
	 * Get 性别(0002)
	        0:unknown
	        1:male
	        2:female.
	 * @return 性别(0002)
	        0:unknown
	        1:male
	        2:female
	 */
	public byte getGender() {
		return this.gender;
	}

	/**
	 * Set 性别(0002)
	        0:unknown
	        1:male
	        2:female.
	 * @param gender 性别(0002)
	        0:unknown
	        1:male
	        2:female
	 */
	public void setGender(byte gender) {
		this.gender = gender;
	}

	/**  
	 * Get 手机号.
	 * @return 手机号
	 */
	public String getMobile() {
		return this.mobile;
	}

	/**
	 * Set 手机号.
	 * @param mobile 手机号
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**  
	 * Get 电子邮件.
	 * @return 电子邮件
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * Set 电子邮件.
	 * @param email 电子邮件
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**  
	 * Get 联系电话.
	 * @return 联系电话
	 */
	public String getPhone() {
		return this.phone;
	}

	/**
	 * Set 联系电话.
	 * @param phone 联系电话
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**  
	 * Get 传真号.
	 * @return 传真号
	 */
	public String getFax() {
		return this.fax;
	}

	/**
	 * Set 传真号.
	 * @param fax 传真号
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}

	/**  
	 * Get 地址.
	 * @return 地址
	 */
	public String getAddress() {
		return this.address;
	}

	/**
	 * Set 地址.
	 * @param address 地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**  
	 * Get 邮编.
	 * @return 邮编
	 */
	public String getZipCode() {
		return this.zipCode;
	}

	/**
	 * Set 邮编.
	 * @param zipCode 邮编
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
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
	 * Get 备注.
	 * @return 备注
	 */
	public String getRemark() {
		return this.remark;
	}

	/**
	 * Set 备注.
	 * @param remark 备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**  
	 * Get 头像URL.
	 * @return 头像URL
	 */
	public String getHeadImgUrl() {
		return this.headImgUrl;
	}

	/**
	 * Set 头像URL.
	 * @param headImgUrl 头像URL
	 */
	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}

}
