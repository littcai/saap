package com.litt.saap.system.po;

import java.util.Date;

import java.io.Serializable;

/**
 * 标签<br>
 * 表名：tag<br>
 * @author Hibernate Tools 3.2.4.GA
 * @version 1.0
 * @since 2013-12-4 15:49:52
 */
public class Tag implements Serializable {
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
	 * 类别.
	 */
	private String type;

	/**
	 * 名称.
	 */
	private String name;

	/**
	 * 总数量.
	 */
	private int totalCount;

	/**
	 * 创建时间.
	 */
	private Date createDatetime;

	public Tag() {
	}

	public Tag(int tenantId, String type, String name, int totalCount,
			Date createDatetime) {
		this.tenantId = tenantId;
		this.type = type;
		this.name = name;
		this.totalCount = totalCount;
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
	 * 取得 类别.
	 * @return 类别
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * 设置 类别.
	 * @param type 类别
	 */
	public void setType(String type) {
		this.type = type;
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
	 * 取得 总数量.
	 * @return 总数量
	 */
	public int getTotalCount() {
		return this.totalCount;
	}

	/**
	 * 设置 总数量.
	 * @param totalCount 总数量
	 */
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
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
