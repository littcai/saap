package com.litt.saap.system.po;

import java.util.Date;

import java.io.Serializable;

/**
 * 应用租用表<br>
 * 表名：tenant<br>
 * @author Hibernate Tools 3.2.4.GA
 * @version 1.0
 * @since 2013-10-17 14:07:31
 */
public class Tenant implements Serializable {
	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 序号.
	 */
	private Integer id;

	/**
	 * 应用编号.
	 */
	private String tenantCode;

	/**
	 * 应用别名.
	 */
	private String tenantAlias;
	
	/** 功能包编号. */
	private String bagCode;

	/**
	 * 租用类型
	        1：完全共享型
	        2：独立Schema
	        3：独立数据库.
	 */
	private int isolatedMode;

	/**
	 * 状态
	        -3：到期
	        -2：禁用
	        -1：删除
	        0：尚未激活
	        1：启用.
	 */
	private int status;

	/**
	 * 创建用户ID.
	 */
	private int createUserId;

	/**
	 * 创建时间.
	 */
	private Date createDatetime;

	/**
	 * 更新时间.
	 */
	private Date updateDatetime;

	/**
	 * 最大成员数.
	 */
	private int maxMembers;

	/**
	 * 试用期限.
	 */
	private int trialDays;

	/**
	 * 到期日.
	 */
	private Date expiredDate;
	
	/** The logo url. */
	private String logoUrl;

	public Tenant() {
	}

	public Tenant(String tenantCode, String tenantAlias, String bagCode, int isolatedMode,
			int status, int createUserId, Date createDatetime,
			Date updateDatetime, int maxMembers, int trialDays,
			Date expiredDate, int price) {
		this.tenantCode = tenantCode;
		this.tenantAlias = tenantAlias;
		this.bagCode = bagCode;
		this.isolatedMode = isolatedMode;
		this.status = status;
		this.createUserId = createUserId;
		this.createDatetime = createDatetime;
		this.updateDatetime = updateDatetime;
		this.maxMembers = maxMembers;
		this.trialDays = trialDays;
		this.expiredDate = expiredDate;
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
	 * 取得 应用编号.
	 * @return 应用编号
	 */
	public String getTenantCode() {
		return this.tenantCode;
	}

	/**
	 * 设置 应用编号.
	 * @param tenantCode 应用编号
	 */
	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}

	/**  
	 * 取得 应用别名.
	 * @return 应用别名
	 */
	public String getTenantAlias() {
		return this.tenantAlias;
	}

	/**
	 * 设置 应用别名.
	 * @param tenantAlias 应用别名
	 */
	public void setTenantAlias(String tenantAlias) {
		this.tenantAlias = tenantAlias;
	}

	/**  
	 * 取得 租用类型
	        1：完全共享型
	        2：独立Schema
	        3：独立数据库.
	 * @return 租用类型
	        1：完全共享型
	        2：独立Schema
	        3：独立数据库
	 */
	public int getIsolatedMode() {
		return this.isolatedMode;
	}

	/**
	 * 设置 租用类型
	        1：完全共享型
	        2：独立Schema
	        3：独立数据库.
	 * @param isolatedMode 租用类型
	        1：完全共享型
	        2：独立Schema
	        3：独立数据库
	 */
	public void setIsolatedMode(int isolatedMode) {
		this.isolatedMode = isolatedMode;
	}

	/**  
	 * 取得 状态
	        -3：到期
	        -2：禁用
	        -1：删除
	        0：尚未激活
	        1：启用.
	 * @return 状态
	        -3：到期
	        -2：禁用
	        -1：删除
	        0：尚未激活
	        1：启用
	 */
	public int getStatus() {
		return this.status;
	}

	/**
	 * 设置 状态
	        -3：到期
	        -2：禁用
	        -1：删除
	        0：尚未激活
	        1：启用.
	 * @param status 状态
	        -3：到期
	        -2：禁用
	        -1：删除
	        0：尚未激活
	        1：启用
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**  
	 * 取得 创建用户ID.
	 * @return 创建用户ID
	 */
	public int getCreateUserId() {
		return this.createUserId;
	}

	/**
	 * 设置 创建用户ID.
	 * @param createUserId 创建用户ID
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

	/**  
	 * 取得 更新时间.
	 * @return 更新时间
	 */
	public Date getUpdateDatetime() {
		return this.updateDatetime;
	}

	/**
	 * 设置 更新时间.
	 * @param updateDatetime 更新时间
	 */
	public void setUpdateDatetime(Date updateDatetime) {
		this.updateDatetime = updateDatetime;
	}

	/**  
	 * 取得 最大成员数.
	 * @return 最大成员数
	 */
	public int getMaxMembers() {
		return this.maxMembers;
	}

	/**
	 * 设置 最大成员数.
	 * @param maxMembers 最大成员数
	 */
	public void setMaxMembers(int maxMembers) {
		this.maxMembers = maxMembers;
	}

	/**  
	 * 取得 试用期限.
	 * @return 试用期限
	 */
	public int getTrialDays() {
		return this.trialDays;
	}

	/**
	 * 设置 试用期限.
	 * @param trialDays 试用期限
	 */
	public void setTrialDays(int trialDays) {
		this.trialDays = trialDays;
	}

	/**  
	 * 取得 到期日.
	 * @return 到期日
	 */
	public Date getExpiredDate() {
		return this.expiredDate;
	}

	/**
	 * 设置 到期日.
	 * @param expiredDate 到期日
	 */
	public void setExpiredDate(Date expiredDate) {
		this.expiredDate = expiredDate;
	}

	/**
	 * @return the bagCode
	 */
	public String getBagCode() {
		return bagCode;
	}

	/**
	 * @param bagCode the bagCode to set
	 */
	public void setBagCode(String bagCode) {
		this.bagCode = bagCode;
	}

	/**
	 * @return the logoUrl
	 */
	public String getLogoUrl() {
		return logoUrl;
	}

	/**
	 * @param logoUrl the logoUrl to set
	 */
	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

}
