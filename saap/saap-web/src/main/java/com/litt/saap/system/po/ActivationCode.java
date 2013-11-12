package com.litt.saap.system.po;

import java.io.Serializable;
import java.util.Date;

/**
 * 找回密码<br>
 * 表名：forget_password<br>
 * @author Hibernate Tools 3.2.4.GA
 * @version 1.0
 * @since 2013-9-24 15:35:34
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
	 * 安全密钥.
	 */
	private String securityKey;

	/**
	 * 过期时间.
	 */
	private Date expiredDatetime;

	public ActivationCode() {
	}

	public ActivationCode(String id, int userId, String securityKey,
			Date expiredDatetime) {
		this.id = id;
		this.userId = userId;
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
