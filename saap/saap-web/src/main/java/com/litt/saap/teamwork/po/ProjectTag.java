package com.litt.saap.teamwork.po;

import java.io.Serializable;

/**
 * 项目标签关系表<br>
 * 表名：project_tag<br>
 * @author Hibernate Tools 3.2.4.GA
 * @version 1.0
 * @since 2013-12-4 15:49:52
 */
public class ProjectTag implements Serializable {
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
	private int projectId;

	/**
	 */
	private int tagId;

	public ProjectTag() {
	}

	public ProjectTag(int tenantId, int projectId, int tagId) {
		this.tenantId = tenantId;
		this.projectId = projectId;
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
	public int getProjectId() {
		return this.projectId;
	}

	/**
	 */
	public void setProjectId(int projectId) {
		this.projectId = projectId;
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
