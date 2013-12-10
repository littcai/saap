package com.litt.saap.teamwork.po;

import java.util.Date;

import java.io.Serializable;

/**
 * 项目<br>
 * 表名：project<br>
 * @author Hibernate Tools 3.2.4.GA
 * @version 1.0
 * @since 2013-12-4 15:49:52
 */
public class Project implements Serializable {
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
	 * 名称.
	 */
	private String name;

	/**
	 * 详细描述.
	 */
	private String descr;

	/**
	 * 状态.
	 */
	private int status;

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

	public Project() {
	}

	public Project(int tenantId, String name, int status, String tags,
			int createUserId, Date createDatetime, int updateUserId,
			Date updateDatetime) {
		this.tenantId = tenantId;
		this.name = name;
		this.status = status;
		this.tags = tags;
		this.createUserId = createUserId;
		this.createDatetime = createDatetime;
		this.updateUserId = updateUserId;
		this.updateDatetime = updateDatetime;
	}

	public Project(int tenantId, String name, String descr, int status,
			String tags, int createUserId, Date createDatetime,
			int updateUserId, Date updateDatetime) {
		this.tenantId = tenantId;
		this.name = name;
		this.descr = descr;
		this.status = status;
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
	 * 取得 详细描述.
	 * @return 详细描述
	 */
	public String getDescr() {
		return this.descr;
	}

	/**
	 * 设置 详细描述.
	 * @param descr 详细描述
	 */
	public void setDescr(String descr) {
		this.descr = descr;
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
