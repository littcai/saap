package com.litt.saap.system.po;

import java.io.Serializable;

/**
 * 角色表<br>
 * 表名：role<br>
 * @author Hibernate Tools 3.2.4.GA
 * @version 1.0
 * @since 2013-10-21 14:00:40
 */
public class Role implements Serializable {
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
	 * 状态.
	 */
	private int status;

	/**
	 * 备注.
	 */
	private String remark;

	public Role() {
	}

	public Role(int tenantId, String name, byte status) {
		this.tenantId = tenantId;
		this.name = name;
		this.status = status;
	}

	public Role(int tenantId, String name, byte status, String remark) {
		this.tenantId = tenantId;
		this.name = name;
		this.status = status;
		this.remark = remark;
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
	 * 取得 备注.
	 * @return 备注
	 */
	public String getRemark() {
		return this.remark;
	}

	/**
	 * 设置 备注.
	 * @param remark 备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

}
