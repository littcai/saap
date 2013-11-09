package com.litt.saap.system.po;

import java.util.Date;

import java.io.Serializable;

/**
 * 租赁成员表<br>
 * 表名：tenant_member<br>
 * @author Hibernate Tools 3.2.4.GA
 * @version 1.0
 * @since 2013-10-17 13:19:47
 */
public class TenantMember implements Serializable {
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
	 * 用户ID.
	 */
	private Integer userId;

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
	 * 创建用户.
	 */
	private int createUserId;

	/**
	 * 创建时间.
	 */
	private Date createDatetime;

	/**
	 * 更新人.
	 */
	private int updateUserId;

	/**
	 * 更新时间.
	 */
	private Date updateDatetime;

	public TenantMember() {
	}

	public TenantMember(int tenantId, int appId, boolean isAdmin, int status,
			int createUserId, Date createDatetime, int updateUserId,
			Date updateDatetime) {
		this.tenantId = tenantId;
		this.appId = appId;
		this.isAdmin = isAdmin;
		this.status = status;
		this.createUserId = createUserId;
		this.createDatetime = createDatetime;
		this.updateUserId = updateUserId;
		this.updateDatetime = updateDatetime;
	}

	public TenantMember(int tenantId, int appId, Integer userId,
			boolean isAdmin, int status, int createUserId, Date createDatetime,
			int updateUserId, Date updateDatetime) {
		this.tenantId = tenantId;
		this.appId = appId;
		this.userId = userId;
		this.isAdmin = isAdmin;
		this.status = status;
		this.createUserId = createUserId;
		this.createDatetime = createDatetime;
		this.updateUserId = updateUserId;
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
	 * 取得 用户ID.
	 * @return 用户ID
	 */
	public Integer getUserId() {
		return this.userId;
	}

	/**
	 * 设置 用户ID.
	 * @param userId 用户ID
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
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
	 * 取得 创建用户.
	 * @return 创建用户
	 */
	public int getCreateUserId() {
		return this.createUserId;
	}

	/**
	 * 设置 创建用户.
	 * @param createUserId 创建用户
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
	 * 取得 更新人.
	 * @return 更新人
	 */
	public int getUpdateUserId() {
		return this.updateUserId;
	}

	/**
	 * 设置 更新人.
	 * @param updateUserId 更新人
	 */
	public void setUpdateUserId(int updateUserId) {
		this.updateUserId = updateUserId;
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
