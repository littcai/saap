package com.litt.saap.system.po;

import java.util.Date;

import java.io.Serializable;

/**
 * 用户组成员<br>
 * 表名：user_group_member<br>
 * @author Hibernate Tools 3.2.4.GA
 * @version 1.0
 * @since 2013-8-29 17:55:22
 */
public class UserGroupMember implements Serializable {
	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 序号.
	 */
	private Integer id;
	
	/** The tenant id. */
	private int tenantId;

	/**
	 * 用户组ID.
	 */
	private int groupId;

	/**
	 * 用户ID.
	 */
	private int userId;

	/**
	 * 创建用户.
	 */
	private int createBy;

	/**
	 * 创建时间.
	 */
	private Date createDatetime;

	public UserGroupMember() {
	}

	public UserGroupMember(int tenantId, int groupId, int userId, int createBy,
			Date createDatetime) {
		this.tenantId = tenantId;
		this.groupId = groupId;
		this.userId = userId;
		this.createBy = createBy;
		this.createDatetime = createDatetime;
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
	 * 取得 用户组ID.
	 * @return 用户组ID
	 */
	public int getGroupId() {
		return this.groupId;
	}

	/**
	 * 设置 用户组ID.
	 * @param groupId 用户组ID
	 */
	public void setGroupId(int groupId) {
		this.groupId = groupId;
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
	 * @return the tenantId
	 */
	public int getTenantId() {
		return tenantId;
	}

	/**
	 * @param tenantId the tenantId to set
	 */
	public void setTenantId(int tenantId) {
		this.tenantId = tenantId;
	}

	/**
	 * @return the createBy
	 */
	public int getCreateBy() {
		return createBy;
	}

	/**
	 * @param createBy the createBy to set
	 */
	public void setCreateBy(int createBy) {
		this.createBy = createBy;
	}

}
