package com.litt.saap.system.po;

import java.util.Date;

import java.io.Serializable;

/**
 * 用户扩展表<br>
 * 表名：user_ext<br>
 * @author Hibernate Tools 3.2.4.GA
 * @version 1.0
 * @since 2013-9-2 13:12:34
 */
public class UserExt implements Serializable {
	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 用户ID.
	 */
	private int id;

	/**
	 * 用户等级.
	 */
	private Integer level;

	/**
	 * 用户来源.
	 */
	private String source;

	/**
	 * 生日.
	 */
	private Date birthday;

	/**
	 * 邮政编码.
	 */
	private String zipCode;

	/**
	 * 联系地址.
	 */
	private String address;

	/**
	 * 区.
	 */
	private String district;

	/**
	 * 城市.
	 */
	private String city;

	/**
	 * 省份.
	 */
	private String state;

	/**
	 * 国家.
	 */
	private String country;

	/**
	 * 安全问题.
	 */
	private String securityQuestion;

	/**
	 * 答案.
	 */
	private String answer;

	public UserExt() {
	}

	public UserExt(int id) {
		this.id = id;
	}

	public UserExt(int id, Integer level, String source, Date birthday,
			String zipCode, String address, String district, String city,
			String state, String country, String securityQuestion, String answer) {
		this.id = id;
		this.level = level;
		this.source = source;
		this.birthday = birthday;
		this.zipCode = zipCode;
		this.address = address;
		this.district = district;
		this.city = city;
		this.state = state;
		this.country = country;
		this.securityQuestion = securityQuestion;
		this.answer = answer;
	}

	/**  
	 * 取得 用户ID.
	 * @return 用户ID
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * 设置 用户ID.
	 * @param id 用户ID
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**  
	 * 取得 用户等级.
	 * @return 用户等级
	 */
	public Integer getLevel() {
		return this.level;
	}

	/**
	 * 设置 用户等级.
	 * @param level 用户等级
	 */
	public void setLevel(Integer level) {
		this.level = level;
	}

	/**  
	 * 取得 用户来源.
	 * @return 用户来源
	 */
	public String getSource() {
		return this.source;
	}

	/**
	 * 设置 用户来源.
	 * @param source 用户来源
	 */
	public void setSource(String source) {
		this.source = source;
	}

	/**  
	 * 取得 生日.
	 * @return 生日
	 */
	public Date getBirthday() {
		return this.birthday;
	}

	/**
	 * 设置 生日.
	 * @param birthday 生日
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	/**  
	 * 取得 邮政编码.
	 * @return 邮政编码
	 */
	public String getZipCode() {
		return this.zipCode;
	}

	/**
	 * 设置 邮政编码.
	 * @param zipCode 邮政编码
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	/**  
	 * 取得 联系地址.
	 * @return 联系地址
	 */
	public String getAddress() {
		return this.address;
	}

	/**
	 * 设置 联系地址.
	 * @param address 联系地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**  
	 * 取得 区.
	 * @return 区
	 */
	public String getDistrict() {
		return this.district;
	}

	/**
	 * 设置 区.
	 * @param district 区
	 */
	public void setDistrict(String district) {
		this.district = district;
	}

	/**  
	 * 取得 城市.
	 * @return 城市
	 */
	public String getCity() {
		return this.city;
	}

	/**
	 * 设置 城市.
	 * @param city 城市
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**  
	 * 取得 省份.
	 * @return 省份
	 */
	public String getState() {
		return this.state;
	}

	/**
	 * 设置 省份.
	 * @param state 省份
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**  
	 * 取得 国家.
	 * @return 国家
	 */
	public String getCountry() {
		return this.country;
	}

	/**
	 * 设置 国家.
	 * @param country 国家
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**  
	 * 取得 安全问题.
	 * @return 安全问题
	 */
	public String getSecurityQuestion() {
		return this.securityQuestion;
	}

	/**
	 * 设置 安全问题.
	 * @param securityQuestion 安全问题
	 */
	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}

	/**  
	 * 取得 答案.
	 * @return 答案
	 */
	public String getAnswer() {
		return this.answer;
	}

	/**
	 * 设置 答案.
	 * @param answer 答案
	 */
	public void setAnswer(String answer) {
		this.answer = answer;
	}

}
