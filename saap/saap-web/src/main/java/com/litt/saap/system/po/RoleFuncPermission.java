package com.litt.saap.system.po;

import java.io.Serializable;

/**
 * 角色功能权限表<br>
 * 表名：role_func_permission<br>
 * @author Hibernate Tools 3.2.4.GA
 * @version 1.0
 * @since 2013-10-21 14:00:40
 */
public class RoleFuncPermission implements Serializable {
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
	 * 角色ID.
	 */
	private int roleId;

	/**
	 * 权限编号.
	 */
	private String permissionCode;

	public RoleFuncPermission() {
	}

	public RoleFuncPermission(int tenantId, int roleId, String permissionCode) {
		this.tenantId = tenantId;
		this.roleId = roleId;
		this.permissionCode = permissionCode;
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

	/**  
	 * 取得 权限编号.
	 * @return 权限编号
	 */
	public String getPermissionCode() {
		return this.permissionCode;
	}

	/**
	 * 设置 权限编号.
	 * @param permissionCode 权限编号
	 */
	public void setPermissionCode(String permissionCode) {
		this.permissionCode = permissionCode;
	}

}
