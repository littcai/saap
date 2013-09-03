package com.litt.saap.system.po;

import java.util.Date;

import java.io.Serializable;

/**
 * 租赁用户表<br>
 * 表名：tenant_user<br>
 * @author Hibernate Tools 3.2.4.GA
 * @version 1.0
 * @since 2013-8-29 15:57:06
 */
public class TenantUser implements Serializable {
	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 序号.
	 */
	private Integer id;

	/**
	 * 租赁ID.
	 */
	private int tenantId;

	/**
	 * 应用ID.
	 */
	private int appId;

	/**
	 * 是否管理员.
	 */
	private boolean isAdmin;

	/**
	 * 状态
	        0：未认证
	        1：通过认证
	        2：锁定.
	 */
	private int status;

	/**
	 * 上次登录IP.
	 */
	private String lastLoginIp;

	/**
	 * 上次登录时间.
	 */
	private Date lastLoginDatetime;

	public TenantUser() {
	}

	public TenantUser(int tenantId, int appId, boolean isAdmin, int status) {
		this.tenantId = tenantId;
		this.appId = appId;
		this.isAdmin = isAdmin;
		this.status = status;
	}

	public TenantUser(int tenantId, int appId, boolean isAdmin, int status,
			String lastLoginIp, Date lastLoginDatetime) {
		this.tenantId = tenantId;
		this.appId = appId;
		this.isAdmin = isAdmin;
		this.status = status;
		this.lastLoginIp = lastLoginIp;
		this.lastLoginDatetime = lastLoginDatetime;
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
	 * 取得 租赁ID.
	 * @return 租赁ID
	 */
	public int getTenantId() {
		return this.tenantId;
	}

	/**
	 * 设置 租赁ID.
	 * @param tenantId 租赁ID
	 */
	public void setTenantId(int tenantId) {
		this.tenantId = tenantId;
	}

	/**  
	 * 取得 应用ID.
	 * @return 应用ID
	 */
	public int getAppId() {
		return this.appId;
	}

	/**
	 * 设置 应用ID.
	 * @param appId 应用ID
	 */
	public void setAppId(int appId) {
		this.appId = appId;
	}

	/**  
	 * 取得 是否管理员.
	 * @return 是否管理员
	 */
	public boolean isIsAdmin() {
		return this.isAdmin;
	}

	/**
	 * 设置 是否管理员.
	 * @param isAdmin 是否管理员
	 */
	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	/**  
	 * 取得 状态
	        0：未认证
	        1：通过认证
	        2：锁定.
	 * @return 状态
	        0：未认证
	        1：通过认证
	        2：锁定
	 */
	public int getStatus() {
		return this.status;
	}

	/**
	 * 设置 状态
	        0：未认证
	        1：通过认证
	        2：锁定.
	 * @param status 状态
	        0：未认证
	        1：通过认证
	        2：锁定
	 */
	public void setStatus(int status) {
		this.status = status;
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

}
