package com.litt.saap.teamwork.po;

import java.util.Date;

import java.io.Serializable;

/**
 * 讨论<br>
 * 表名：topic<br>
 * @author Hibernate Tools 3.2.4.GA
 * @version 1.0
 * @since 2014-1-3 11:41:42
 */
public class Topic implements Serializable {
	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 序号.
	 */
	private Integer id;

	/**
	 * 租户ID.
	 */
	private int tenantId;

	/**
	 * 项目ID.
	 */
	private int projectId;

	/**
	 * 标题.
	 */
	private String title;

	/**
	 * 内容.
	 */
	private String content;

	/**
	 * 创建人.
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

	public Topic() {
	}

	public Topic(int tenantId, int projectId, String title, String content,
			int createUserId, Date createDatetime, int updateUserId,
			Date updateDatetime) {
		this.tenantId = tenantId;
		this.projectId = projectId;
		this.title = title;
		this.content = content;
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
	 * 取得 租户ID.
	 * @return 租户ID
	 */
	public int getTenantId() {
		return this.tenantId;
	}

	/**
	 * 设置 租户ID.
	 * @param tenantId 租户ID
	 */
	public void setTenantId(int tenantId) {
		this.tenantId = tenantId;
	}

	/**  
	 * 取得 项目ID.
	 * @return 项目ID
	 */
	public int getProjectId() {
		return this.projectId;
	}

	/**
	 * 设置 项目ID.
	 * @param projectId 项目ID
	 */
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	/**  
	 * 取得 标题.
	 * @return 标题
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * 设置 标题.
	 * @param title 标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**  
	 * 取得 内容.
	 * @return 内容
	 */
	public String getContent() {
		return this.content;
	}

	/**
	 * 设置 内容.
	 * @param content 内容
	 */
	public void setContent(String content) {
		this.content = content;
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
