package com.litt.saap.system.po;

import java.io.Serializable;

/**
 * 用户角色关系表<br>
 * 表名：user_role<br>
 * @author Hibernate Tools 3.2.4.GA
 * @version 1.0
 * @since 2013-10-21 14:00:40
 */
public class UserRole implements Serializable {
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
	 * 用户ID.
	 */
	private int userId;

	/**
	 * 角色ID.
	 */
	private int roleId;

	public UserRole() {
	}

	public UserRole(int tenantId, int userId, int roleId) {
		this.tenantId = tenantId;
		this.userId = userId;
		this.roleId = roleId;
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
	 * 取得 角色ID.
	 * @return 角色ID
	 */
	public int getRoleId() {
		return this.roleId;
	}

	/**
	 * 设置 角色ID.
	 * @param roleId 角色ID
	 */
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

}
