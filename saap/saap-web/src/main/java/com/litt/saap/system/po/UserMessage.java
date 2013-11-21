package com.litt.saap.system.po;

import java.util.Date;

import java.io.Serializable;

/**
 * 消息中心<br>
 * 表名：user_message<br>
 * @author Hibernate Tools 3.2.4.GA
 * @version 1.0
 * @since 2013-11-19 15:15:41
 */
public class UserMessage implements Serializable {
	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 序号.
	 */
	private Long id;

	/**
	 * 所属租户.
	 */
	private int tenantId;

	/**
	 * 所属用户.
	 */
	private int userId;

	/**
	 * 模块编号.
	 */
	private String moduleCode;

	/**
	 * 参数（JSON结构）.
	 */
	private String params;

	/**
	 * 创建用户.
	 */
	private int createUserId;

	/**
	 * 创建时间.
	 */
	private Date createDatetime;

	public UserMessage() {
	}

	public UserMessage(int tenantId, int userId, String moduleCode,
			String params, int createUserId, Date createDatetime) {
		this.tenantId = tenantId;
		this.userId = userId;
		this.moduleCode = moduleCode;
		this.params = params;
		this.createUserId = createUserId;
		this.createDatetime = createDatetime;
	}

	/**  
	 * 取得 序号.
	 * @return 序号
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * 设置 序号.
	 * @param id 序号
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**  
	 * 取得 所属租户.
	 * @return 所属租户
	 */
	public int getTenantId() {
		return this.tenantId;
	}

	/**
	 * 设置 所属租户.
	 * @param tenantId 所属租户
	 */
	public void setTenantId(int tenantId) {
		this.tenantId = tenantId;
	}

	/**  
	 * 取得 所属用户.
	 * @return 所属用户
	 */
	public int getUserId() {
		return this.userId;
	}

	/**
	 * 设置 所属用户.
	 * @param userId 所属用户
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**  
	 * 取得 模块编号.
	 * @return 模块编号
	 */
	public String getModuleCode() {
		return this.moduleCode;
	}

	/**
	 * 设置 模块编号.
	 * @param moduleCode 模块编号
	 */
	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}

	/**  
	 * 取得 参数（JSON结构）.
	 * @return 参数（JSON结构）
	 */
	public String getParams() {
		return this.params;
	}

	/**
	 * 设置 参数（JSON结构）.
	 * @param params 参数（JSON结构）
	 */
	public void setParams(String params) {
		this.params = params;
	}

	/**  
	 * 取得 创建用户.
	 * @return 创建用户
	 */
	public int getCreateUserId() {
		return this.createUserId;
	}

	/**
	 * 设置 创建用户.
	 * @param createUserId 创建用户
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

}
