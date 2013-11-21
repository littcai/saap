package com.litt.saap.system.po;

import java.util.Date;

import java.io.Serializable;

/**
 * 用户注释<br>
 * 表名：user_comment<br>
 * @author Hibernate Tools 3.2.4.GA
 * @version 1.0
 * @since 2013-11-19 15:15:41
 */
public class UserComment implements Serializable {
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
	 * 模块编号.
	 */
	private String moduleCode;

	/**
	 * 数据ID.
	 */
	private int dataId;

	/**
	 * 内容.
	 */
	private String content;

	/**
	 * 创建人.
	 */
	private int createUserId;

	/**
	 * 创建时间.
	 */
	private Date createDatetime;

	public UserComment() {
	}

	public UserComment(int tenantId, String moduleCode, int dataId,
			String content, int createUserId, Date createDatetime) {
		this.tenantId = tenantId;
		this.moduleCode = moduleCode;
		this.dataId = dataId;
		this.content = content;
		this.createUserId = createUserId;
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
	 * 取得 数据ID.
	 * @return 数据ID
	 */
	public int getDataId() {
		return this.dataId;
	}

	/**
	 * 设置 数据ID.
	 * @param dataId 数据ID
	 */
	public void setDataId(int dataId) {
		this.dataId = dataId;
	}

	/**  
	 * 取得 内容.
	 * @return 内容
	 */
	public String getContent() {
		return this.content;
	}

	/**
	 * 设置 内容.
	 * @param content 内容
	 */
	public void setContent(String content) {
		this.content = content;
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

}
