package com.litt.saap.system.po;

import java.util.Date;

import java.io.Serializable;

/**
 * 激活码<br>
 * 表名：activation_code<br>
 * @author Hibernate Tools 3.2.4.GA
 * @version 1.0
 * @since 2013-11-20 10:24:11
 */
public class ActivationCode implements Serializable {
	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * UUID.
	 */
	private String id;

	/**
	 * 用户ID.
	 */
	private int userId;

	/**
	 * 模块编号.
	 */
	private String moduleCode;

	/**
	 * 参数.
	 */
	private String params;

	/**
	 * 安全密钥.
	 */
	private String securityKey;

	/**
	 * 过期时间.
	 */
	private Date expiredDatetime;

	public ActivationCode() {
	}

	public ActivationCode(String id, int userId, String moduleCode,
			String params, String securityKey, Date expiredDatetime) {
		this.id = id;
		this.userId = userId;
		this.moduleCode = moduleCode;
		this.params = params;
		this.securityKey = securityKey;
		this.expiredDatetime = expiredDatetime;
	}
	
	public boolean isExpired()
	{
		return new Date().after(expiredDatetime);
	}

	/**  
	 * 取得 UUID.
	 * @return UUID
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * 设置 UUID.
	 * @param id UUID
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**  
	 * 取得 用户ID.
	 * @return 用户ID
	 */
	public int getUserId() {
		return this.userId;
	}

	/**
	 * 设置 用户ID.
	 * @param userId 用户ID
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**  
	 * 取得 模块编号.
	 * @return 模块编号
	 */
	public String getModuleCode() {
		return this.moduleCode;
	}

	/**
	 * 设置 模块编号.
	 * @param moduleCode 模块编号
	 */
	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}

	/**  
	 * 取得 参数.
	 * @return 参数
	 */
	public String getParams() {
		return this.params;
	}

	/**
	 * 设置 参数.
	 * @param params 参数
	 */
	public void setParams(String params) {
		this.params = params;
	}

	/**  
	 * 取得 安全密钥.
	 * @return 安全密钥
	 */
	public String getSecurityKey() {
		return this.securityKey;
	}

	/**
	 * 设置 安全密钥.
	 * @param securityKey 安全密钥
	 */
	public void setSecurityKey(String securityKey) {
		this.securityKey = securityKey;
	}

	/**  
	 * 取得 过期时间.
	 * @return 过期时间
	 */
	public Date getExpiredDatetime() {
		return this.expiredDatetime;
	}

	/**
	 * 设置 过期时间.
	 * @param expiredDatetime 过期时间
	 */
	public void setExpiredDatetime(Date expiredDatetime) {
		this.expiredDatetime = expiredDatetime;
	}

}
