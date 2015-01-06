package com.litt.saap.system.po;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import java.io.Serializable;

/**
 * <br>
 * Table:biz_role<br>
 * @author Hibernate Tools 3.4.0.CR1
 * @version 1.0
 * @since 2015-1-6 12:56:22
 */
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "biz_role")
public class BizRole implements Serializable {
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

	/**
	 * 创建人.
	 */
	private int createBy;

	/**
	 * 创建时间.
	 */
	private Date createDatetime;

	/**
	 * 更新人.
	 */
	private int updateBy;

	/**
	 * 更新时间.
	 */
	private Date updateDatetime;

	public BizRole() {
	}

	public BizRole(int tenantId, String name, int status, int createBy,
			Date createDatetime, int updateBy, Date updateDatetime) {
		this.tenantId = tenantId;
		this.name = name;
		this.status = status;
		this.createBy = createBy;
		this.createDatetime = createDatetime;
		this.updateBy = updateBy;
		this.updateDatetime = updateDatetime;
	}

	public BizRole(int tenantId, String name, int status, String remark,
			int createBy, Date createDatetime, int updateBy, Date updateDatetime) {
		this.tenantId = tenantId;
		this.name = name;
		this.status = status;
		this.remark = remark;
		this.createBy = createBy;
		this.createDatetime = createDatetime;
		this.updateBy = updateBy;
		this.updateDatetime = updateDatetime;
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
	 * Get 名称.
	 * @return 名称
	 */

	@Column(name = "NAME", nullable = false, length = 50)
	public String getName() {
		return this.name;
	}

	/**
	 * Set 名称.
	 * @param name 名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**  
	 * Get 状态.
	 * @return 状态
	 */

	@Column(name = "STATUS", nullable = false)
	public int getStatus() {
		return this.status;
	}

	/**
	 * Set 状态.
	 * @param status 状态
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**  
	 * Get 备注.
	 * @return 备注
	 */

	@Column(name = "REMARK", length = 200)
	public String getRemark() {
		return this.remark;
	}

	/**
	 * Set 备注.
	 * @param remark 备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**  
	 * Get 创建人.
	 * @return 创建人
	 */

	@Column(name = "CREATE_BY", nullable = false)
	public int getCreateBy() {
		return this.createBy;
	}

	/**
	 * Set 创建人.
	 * @param createBy 创建人
	 */
	public void setCreateBy(int createBy) {
		this.createBy = createBy;
	}

	/**  
	 * Get 创建时间.
	 * @return 创建时间
	 */

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_DATETIME", nullable = false, length = 19)
	public Date getCreateDatetime() {
		return this.createDatetime;
	}

	/**
	 * Set 创建时间.
	 * @param createDatetime 创建时间
	 */
	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	/**  
	 * Get 更新人.
	 * @return 更新人
	 */

	@Column(name = "UPDATE_BY", nullable = false)
	public int getUpdateBy() {
		return this.updateBy;
	}

	/**
	 * Set 更新人.
	 * @param updateBy 更新人
	 */
	public void setUpdateBy(int updateBy) {
		this.updateBy = updateBy;
	}

	/**  
	 * Get 更新时间.
	 * @return 更新时间
	 */

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATE_DATETIME", nullable = false, length = 19)
	public Date getUpdateDatetime() {
		return this.updateDatetime;
	}

	/**
	 * Set 更新时间.
	 * @param updateDatetime 更新时间
	 */
	public void setUpdateDatetime(Date updateDatetime) {
		this.updateDatetime = updateDatetime;
	}

}
