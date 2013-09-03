package com.litt.saap.system.po;

import java.util.Date;

import java.io.Serializable;

/**
 * 用户组<br>
 * 表名：user_group<br>
 * @author Hibernate Tools 3.2.4.GA
 * @version 1.0
 * @since 2013-8-29 17:55:22
 */
public class UserGroup implements Serializable {
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
	 * 类型.
	 */
	private int type;

	/**
	 * 描述.
	 */
	private String description;

	/**
	 * 状态.
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
	 * 更新用户.
	 */
	private Integer updateUserId;

	/**
	 * 更新时间.
	 */
	private Date updateDatetime;

	public UserGroup() {
	}

	public UserGroup(String name, int type, String description, int status,
			int createUserId, Date createDatetime) {
		this.name = name;
		this.type = type;
		this.description = description;
		this.status = status;
		this.createUserId = createUserId;
		this.createDatetime = createDatetime;
	}

	public UserGroup(String name, int type, String description, int status,
			int createUserId, Date createDatetime, Integer updateUserId,
			Date updateDatetime) {
		this.name = name;
		this.type = type;
		this.description = description;
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
	 * 取得 类型.
	 * @return 类型
	 */
	public int getType() {
		return this.type;
	}

	/**
	 * 设置 类型.
	 * @param type 类型
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**  
	 * 取得 描述.
	 * @return 描述
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * 设置 描述.
	 * @param description 描述
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**  
	 * 取得 状态.
	 * @return 状态
	 */
	public int getStatus() {
		return this.status;
	}

	/**
	 * 设置 状态.
	 * @param status 状态
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
	 * 取得 更新用户.
	 * @return 更新用户
	 */
	public Integer getUpdateUserId() {
		return this.updateUserId;
	}

	/**
	 * 设置 更新用户.
	 * @param updateUserId 更新用户
	 */
	public void setUpdateUserId(Integer updateUserId) {
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
