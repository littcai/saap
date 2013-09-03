package com.litt.saap.system.po;

import java.util.Date;

import java.io.Serializable;

/**
 * 应用租用表<br>
 * 表名：tenant<br>
 * @author Hibernate Tools 3.2.4.GA
 * @version 1.0
 * @since 2013-8-29 15:57:06
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
	 * 应用ID.
	 */
	private int appId;

	/**
	 * 应用别名.
	 */
	private String appAlias;

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
	 * 试用期限.
	 */
	private int trialDays;

	/**
	 * 租期.
	 */
	private Date tenancy;

	/**
	 * 购买价格.
	 */
	private int price;

	public Tenant() {
	}

	public Tenant(int appId, String appAlias, int isolatedMode, int status,
			int createUserId, Date createDatetime, Date updateDatetime,
			int trialDays, Date tenancy, int price) {
		this.appId = appId;
		this.appAlias = appAlias;
		this.isolatedMode = isolatedMode;
		this.status = status;
		this.createUserId = createUserId;
		this.createDatetime = createDatetime;
		this.updateDatetime = updateDatetime;
		this.trialDays = trialDays;
		this.tenancy = tenancy;
		this.price = price;
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
	 * 取得 应用ID.
	 * @return 应用ID
	 */
	public int getAppId() {
		return this.appId;
	}

	/**
	 * 设置 应用ID.
	 * @param appId 应用ID
	 */
	public void setAppId(int appId) {
		this.appId = appId;
	}

	/**  
	 * 取得 应用别名.
	 * @return 应用别名
	 */
	public String getAppAlias() {
		return this.appAlias;
	}

	/**
	 * 设置 应用别名.
	 * @param appAlias 应用别名
	 */
	public void setAppAlias(String appAlias) {
		this.appAlias = appAlias;
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
	 * 取得 租期.
	 * @return 租期
	 */
	public Date getTenancy() {
		return this.tenancy;
	}

	/**
	 * 设置 租期.
	 * @param tenancy 租期
	 */
	public void setTenancy(Date tenancy) {
		this.tenancy = tenancy;
	}

	/**  
	 * 取得 购买价格.
	 * @return 购买价格
	 */
	public int getPrice() {
		return this.price;
	}

	/**
	 * 设置 购买价格.
	 * @param price 购买价格
	 */
	public void setPrice(int price) {
		this.price = price;
	}

}
