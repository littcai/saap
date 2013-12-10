package com.litt.saap.teamwork.po;

import java.io.Serializable;

/**
 * 任务标签关系表<br>
 * 表名：task_tag<br>
 * @author Hibernate Tools 3.2.4.GA
 * @version 1.0
 * @since 2013-12-4 15:49:52
 */
public class TaskTag implements Serializable {
	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 */
	private Integer id;

	/**
	 */
	private int tenantId;

	/**
	 */
	private int taskId;

	/**
	 */
	private int tagId;

	public TaskTag() {
	}

	public TaskTag(int tenantId, int taskId, int tagId) {
		this.tenantId = tenantId;
		this.taskId = taskId;
		this.tagId = tagId;
	}

	/**  
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**  
	 */
	public int getTenantId() {
		return this.tenantId;
	}

	/**
	 */
	public void setTenantId(int tenantId) {
		this.tenantId = tenantId;
	}

	/**  
	 */
	public int getTaskId() {
		return this.taskId;
	}

	/**
	 */
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	/**  
	 */
	public int getTagId() {
		return this.tagId;
	}

	/**
	 */
	public void setTagId(int tagId) {
		this.tagId = tagId;
	}

}
