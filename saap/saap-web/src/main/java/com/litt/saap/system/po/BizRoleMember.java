package com.litt.saap.system.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

import java.io.Serializable;

/**
 * <br>
 * Table:biz_role_member<br>
 * @author Hibernate Tools 3.4.0.CR1
 * @version 1.0
 * @since 2015-1-6 12:56:22
 */
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "biz_role_member")
public class BizRoleMember implements Serializable {
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
	 * 业务角色ID.
	 */
	private int bizRoleId;

	/**
	 * 用户ID.
	 */
	private Integer userId;

	public BizRoleMember() {
	}

	public BizRoleMember(int tenantId, int bizRoleId) {
		this.tenantId = tenantId;
		this.bizRoleId = bizRoleId;
	}

	public BizRoleMember(int tenantId, int bizRoleId, Integer userId) {
		this.tenantId = tenantId;
		this.bizRoleId = bizRoleId;
		this.userId = userId;
	}

	/**  
	 * Get 序号.
	 * @return 序号
	 */
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	/**
	 * Set 序号.
	 * @param id 序号
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**  
	 * Get 租户ID.
	 * @return 租户ID
	 */

	@Column(name = "TENANT_ID", nullable = false)
	public int getTenantId() {
		return this.tenantId;
	}

	/**
	 * Set 租户ID.
	 * @param tenantId 租户ID
	 */
	public void setTenantId(int tenantId) {
		this.tenantId = tenantId;
	}

	/**  
	 * Get 业务角色ID.
	 * @return 业务角色ID
	 */

	@Column(name = "BIZ_ROLE_ID", nullable = false)
	public int getBizRoleId() {
		return this.bizRoleId;
	}

	/**
	 * Set 业务角色ID.
	 * @param bizRoleId 业务角色ID
	 */
	public void setBizRoleId(int bizRoleId) {
		this.bizRoleId = bizRoleId;
	}

	/**  
	 * Get 用户ID.
	 * @return 用户ID
	 */

	@Column(name = "USER_ID")
	public Integer getUserId() {
		return this.userId;
	}

	/**
	 * Set 用户ID.
	 * @param userId 用户ID
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}
