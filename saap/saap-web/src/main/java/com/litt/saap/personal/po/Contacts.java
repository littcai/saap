package com.litt.saap.personal.po;

import java.util.Date;

import java.io.Serializable;

/**
 * 联系人<br>
 * 表名：contacts<br>
 * @author Hibernate Tools 3.2.4.GA
 * @version 1.0
 * @since 2013-10-15 11:44:08
 */
public class Contacts implements Serializable {
	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 序号.
	 */
	private Integer id;

	/**
	 * 名称.
	 */
	private String name;

	/**
	 * 性别
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
	 * 创建人.
	 */
	private int createUserId;

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

	public Contacts() {
	}

	public Contacts(String name, byte gender, String mobile, String email,
			String phone, String fax, String address, String zipCode,
			int createUserId, Date createDatetime, Date updateDatetime) {
		this.name = name;
		this.gender = gender;
		this.mobile = mobile;
		this.email = email;
		this.phone = phone;
		this.fax = fax;
		this.address = address;
		this.zipCode = zipCode;
		this.createUserId = createUserId;
		this.createDatetime = createDatetime;
		this.updateDatetime = updateDatetime;
	}

	public Contacts(String name, byte gender, String mobile, String email,
			String phone, String fax, String address, String zipCode,
			int createUserId, Date createDatetime, Date updateDatetime,
			String remark) {
		this.name = name;
		this.gender = gender;
		this.mobile = mobile;
		this.email = email;
		this.phone = phone;
		this.fax = fax;
		this.address = address;
		this.zipCode = zipCode;
		this.createUserId = createUserId;
		this.createDatetime = createDatetime;
		this.updateDatetime = updateDatetime;
		this.remark = remark;
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
	 * 取得 性别
	        0:unknown
	        1:male
	        2:female.
	 * @return 性别
	        0:unknown
	        1:male
	        2:female
	 */
	public byte getGender() {
		return this.gender;
	}

	/**
	 * 设置 性别
	        0:unknown
	        1:male
	        2:female.
	 * @param gender 性别
	        0:unknown
	        1:male
	        2:female
	 */
	public void setGender(byte gender) {
		this.gender = gender;
	}

	/**  
	 * 取得 手机号.
	 * @return 手机号
	 */
	public String getMobile() {
		return this.mobile;
	}

	/**
	 * 设置 手机号.
	 * @param mobile 手机号
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**  
	 * 取得 电子邮件.
	 * @return 电子邮件
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * 设置 电子邮件.
	 * @param email 电子邮件
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**  
	 * 取得 联系电话.
	 * @return 联系电话
	 */
	public String getPhone() {
		return this.phone;
	}

	/**
	 * 设置 联系电话.
	 * @param phone 联系电话
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**  
	 * 取得 传真号.
	 * @return 传真号
	 */
	public String getFax() {
		return this.fax;
	}

	/**
	 * 设置 传真号.
	 * @param fax 传真号
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}

	/**  
	 * 取得 地址.
	 * @return 地址
	 */
	public String getAddress() {
		return this.address;
	}

	/**
	 * 设置 地址.
	 * @param address 地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**  
	 * 取得 邮编.
	 * @return 邮编
	 */
	public String getZipCode() {
		return this.zipCode;
	}

	/**
	 * 设置 邮编.
	 * @param zipCode 邮编
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	/**  
	 * 取得 创建人.
	 * @return 创建人
	 */
	public int getCreateUserId() {
		return this.createUserId;
	}

	/**
	 * 设置 创建人.
	 * @param createUserId 创建人
	 */
	public void setCreateUserId(int createUserId) {
		this.createUserId = createUserId;
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

	/**  
	 * 取得 备注.
	 * @return 备注
	 */
	public String getRemark() {
		return this.remark;
	}

	/**
	 * 设置 备注.
	 * @param remark 备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

}
