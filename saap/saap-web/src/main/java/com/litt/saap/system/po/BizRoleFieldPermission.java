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
 * Table:biz_role_field_permission<br>
 * @author Hibernate Tools 3.4.0.CR1
 * @version 1.0
 * @since 2015-1-6 12:56:22
 */
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "biz_role_field_permission")
public class BizRoleFieldPermission implements Serializable {
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
	 * 模块编号.
	 */
	private String moduleCode;

	/**
	 * 字段列表.
	 */
	private String fieldList;

	public BizRoleFieldPermission() {
	}

	public BizRoleFieldPermission(int tenantId, int bizRoleId,
			String moduleCode, String fieldList) {
		this.tenantId = tenantId;
		this.bizRoleId = bizRoleId;
		this.moduleCode = moduleCode;
		this.fieldList = fieldList;
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
	 * Get 模块编号.
	 * @return 模块编号
	 */

	@Column(name = "MODULE_CODE", nullable = false, length = 50)
	public String getModuleCode() {
		return this.moduleCode;
	}

	/**
	 * Set 模块编号.
	 * @param moduleCode 模块编号
	 */
	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}

	/**  
	 * Get 字段列表.
	 * @return 字段列表
	 */

	@Column(name = "FIELD_LIST", nullable = false, length = 1000)
	public String getFieldList() {
		return this.fieldList;
	}

	/**
	 * Set 字段列表.
	 * @param fieldList 字段列表
	 */
	public void setFieldList(String fieldList) {
		this.fieldList = fieldList;
	}

}
