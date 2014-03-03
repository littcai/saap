package com.litt.saap.system.po;

import java.util.Date;

import java.io.Serializable;

/**
 * 用户状态信息表<br>
 * 表名：user_state<br>
 * @author Hibernate Tools 3.2.4.GA
 * @version 1.0
 * @since 2013-9-2 13:09:59
 */
public class UserState implements Serializable {
	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 序号(同用户ID).
	 */
	private int id;
	
	/** 当前租户ID. */
	private int currentTenantId;

	/**
	 * 总登录次数.
	 */
	private int totalLoginTimes;

	/**
	 * 登录重试次数.
	 */
	private int loginRetryTimes;

	/**
	 * 上次登录时间.
	 */
	private Date lastLoginDatetime;

	/**
	 * 上次登录IP.
	 */
	private String lastLoginIp;

	public UserState() {
	}

	public UserState(int id, int currentTenantId, int totalLoginTimes, int loginRetryTimes,
			Date lastLoginDatetime, String lastLoginIp) {
		this.id = id;
		this.currentTenantId = currentTenantId;
		this.totalLoginTimes = totalLoginTimes;
		this.loginRetryTimes = loginRetryTimes;
		this.lastLoginDatetime = lastLoginDatetime;
		this.lastLoginIp = lastLoginIp;
	}

	/**  
	 * 取得 序号(同用户ID).
	 * @return 序号(同用户ID)
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * 设置 序号(同用户ID).
	 * @param id 序号(同用户ID)
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**  
	 * 取得 总登录次数.
	 * @return 总登录次数
	 */
	public int getTotalLoginTimes() {
		return this.totalLoginTimes;
	}

	/**
	 * 设置 总登录次数.
	 * @param totalLoginTimes 总登录次数
	 */
	public void setTotalLoginTimes(int totalLoginTimes) {
		this.totalLoginTimes = totalLoginTimes;
	}

	/**  
	 * 取得 登录重试次数.
	 * @return 登录重试次数
	 */
	public int getLoginRetryTimes() {
		return this.loginRetryTimes;
	}

	/**
	 * 设置 登录重试次数.
	 * @param loginRetryTimes 登录重试次数
	 */
	public void setLoginRetryTimes(int loginRetryTimes) {
		this.loginRetryTimes = loginRetryTimes;
	}

	/**  
	 * 取得 上次登录时间.
	 * @return 上次登录时间
	 */
	public Date getLastLoginDatetime() {
		return this.lastLoginDatetime;
	}

	/**
	 * 设置 上次登录时间.
	 * @param lastLoginDatetime 上次登录时间
	 */
	public void setLastLoginDatetime(Date lastLoginDatetime) {
		this.lastLoginDatetime = lastLoginDatetime;
	}

	/**  
	 * 取得 上次登录IP.
	 * @return 上次登录IP
	 */
	public String getLastLoginIp() {
		return this.lastLoginIp;
	}

	/**
	 * 设置 上次登录IP.
	 * @param lastLoginIp 上次登录IP
	 */
	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	/**
	 * @return the currentTenantId
	 */
	public int getCurrentTenantId() {
		return currentTenantId;
	}

	/**
	 * @param currentTenantId the currentTenantId to set
	 */
	public void setCurrentTenantId(int currentTenantId) {
		this.currentTenantId = currentTenantId;
	}

}
