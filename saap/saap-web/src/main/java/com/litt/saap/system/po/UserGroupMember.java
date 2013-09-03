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

	/**
	 * 用户组ID.
	 */
	private int groupId;

	/**
	 * 用户ID.
	 */
	private int userId;

	/**
	 * 类型
	        1：创建人
	        2：普通成员
	        3：管理员.
	 */
	private int type;

	/**
	 * 状态
	        1：正常
	        2：锁定.
	 */
	private int status;

	/**
	 * 创建时间.
	 */
	private Date createDatetime;

	public UserGroupMember() {
	}

	public UserGroupMember(int groupId, int userId, int type, int status,
			Date createDatetime) {
		this.groupId = groupId;
		this.userId = userId;
		this.type = type;
		this.status = status;
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
	 * 取得 类型
	        1：创建人
	        2：普通成员
	        3：管理员.
	 * @return 类型
	        1：创建人
	        2：普通成员
	        3：管理员
	 */
	public int getType() {
		return this.type;
	}

	/**
	 * 设置 类型
	        1：创建人
	        2：普通成员
	        3：管理员.
	 * @param type 类型
	        1：创建人
	        2：普通成员
	        3：管理员
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**  
	 * 取得 状态
	        1：正常
	        2：锁定.
	 * @return 状态
	        1：正常
	        2：锁定
	 */
	public int getStatus() {
		return this.status;
	}

	/**
	 * 设置 状态
	        1：正常
	        2：锁定.
	 * @param status 状态
	        1：正常
	        2：锁定
	 */
	public void setStatus(int status) {
		this.status = status;
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

}
