package com.litt.saap.teamwork.po;

import java.util.Date;

import java.io.Serializable;

/**
 * 任务<br>
 * 表名：task<br>
 * @author Hibernate Tools 3.2.4.GA
 * @version 1.0
 * @since 2013-12-4 15:49:52
 */
public class Task implements Serializable {
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
	 * 优先级
	        0：Suggestion
	        1：Minor
	        2：Major
	        3：Critical
	        4：Blocked.
	 */
	private int priority;

	/**
	 * 状态
	        -2：中止
	        -1：取消
	        0：创建
	        1：进行中
	        2：暂停
	        3：完成.
	 */
	private int status;

	/**
	 * 受任人.
	 */
	private int assignee;

	/**
	 * 标签集.
	 */
	private String tags;

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

	public Task() {
	}

	public Task(int tenantId, int projectId, String title, String content,
			int priority, int status, int assignee, String tags,
			int createUserId, Date createDatetime, int updateUserId,
			Date updateDatetime) {
		this.tenantId = tenantId;
		this.projectId = projectId;
		this.title = title;
		this.content = content;
		this.priority = priority;
		this.status = status;
		this.assignee = assignee;
		this.tags = tags;
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
	 * 取得 优先级
	        0：Suggestion
	        1：Minor
	        2：Major
	        3：Critical
	        4：Blocked.
	 * @return 优先级
	        0：Suggestion
	        1：Minor
	        2：Major
	        3：Critical
	        4：Blocked
	 */
	public int getPriority() {
		return this.priority;
	}

	/**
	 * 设置 优先级
	        0：Suggestion
	        1：Minor
	        2：Major
	        3：Critical
	        4：Blocked.
	 * @param priority 优先级
	        0：Suggestion
	        1：Minor
	        2：Major
	        3：Critical
	        4：Blocked
	 */
	public void setPriority(int priority) {
		this.priority = priority;
	}

	/**  
	 * 取得 状态
	        -2：中止
	        -1：取消
	        0：创建
	        1：进行中
	        2：暂停
	        3：完成.
	 * @return 状态
	        -2：中止
	        -1：取消
	        0：创建
	        1：进行中
	        2：暂停
	        3：完成
	 */
	public int getStatus() {
		return this.status;
	}

	/**
	 * 设置 状态
	        -2：中止
	        -1：取消
	        0：创建
	        1：进行中
	        2：暂停
	        3：完成.
	 * @param status 状态
	        -2：中止
	        -1：取消
	        0：创建
	        1：进行中
	        2：暂停
	        3：完成
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**  
	 * 取得 受任人.
	 * @return 受任人
	 */
	public int getAssignee() {
		return this.assignee;
	}

	/**
	 * 设置 受任人.
	 * @param assignee 受任人
	 */
	public void setAssignee(int assignee) {
		this.assignee = assignee;
	}

	/**  
	 * 取得 标签集.
	 * @return 标签集
	 */
	public String getTags() {
		return this.tags;
	}

	/**
	 * 设置 标签集.
	 * @param tags 标签集
	 */
	public void setTags(String tags) {
		this.tags = tags;
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
