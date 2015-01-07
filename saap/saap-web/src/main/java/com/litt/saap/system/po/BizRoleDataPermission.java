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
 * Table:biz_role_data_permission<br>
 * @author Hibernate Tools 3.4.0.CR1
 * @version 1.0
 * @since 2015-1-7 11:18:10
 */
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "biz_role_data_permission")
public class BizRoleDataPermission implements Serializable {
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
	 * 过滤字段名.
	 */
	private String fieldName;

	/**
	 * 过滤字段值.
	 */
	private String fieldValue;

	public BizRoleDataPermission() {
	}

	public BizRoleDataPermission(int tenantId, int bizRoleId, String fieldName,
			String fieldValue) {
		this.tenantId = tenantId;
		this.bizRoleId = bizRoleId;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
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
	 * Get 过滤字段名.
	 * @return 过滤字段名
	 */

	@Column(name = "FIELD_NAME", nullable = false, length = 50)
	public String getFieldName() {
		return this.fieldName;
	}

	/**
	 * Set 过滤字段名.
	 * @param fieldName 过滤字段名
	 */
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	/**  
	 * Get 过滤字段值.
	 * @return 过滤字段值
	 */

	@Column(name = "FIELD_VALUE", nullable = false, length = 200)
	public String getFieldValue() {
		return this.fieldValue;
	}

	/**
	 * Set 过滤字段值.
	 * @param fieldValue 过滤字段值
	 */
	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}

}
