package com.litt.saap.system.po;

import java.util.Date;

import java.io.Serializable;

/**
 * 用户操作日志<br>
 * 表名：user_op_log<br>
 * @author Hibernate Tools 3.2.4.GA
 * @version 1.0
 * @since 2013-8-29 15:57:06
 */
public class UserOpLog implements Serializable {
	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 序号.
	 */
	private Integer id;

	/**
	 * 用户ID.
	 */
	private int userId;

	/**
	 * 应用ID.
	 */
	private int appId;

	/**
	 * 登录名.
	 */
	private String loginId;

	/**
	 * 模块编号.
	 */
	private String moduleCode;

	/**
	 * 功能编号.
	 */
	private String funcCode;

	/**
	 * 操作时间.
	 */
	private Date opDatetime;

	public UserOpLog() {
	}

	public UserOpLog(int userId, int appId, String loginId, String moduleCode,
			String funcCode, Date opDatetime) {
		this.userId = userId;
		this.appId = appId;
		this.loginId = loginId;
		this.moduleCode = moduleCode;
		this.funcCode = funcCode;
		this.opDatetime = opDatetime;
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
	 * 取得 登录名.
	 * @return 登录名
	 */
	public String getLoginId() {
		return this.loginId;
	}

	/**
	 * 设置 登录名.
	 * @param loginId 登录名
	 */
	public void setLoginId(String loginId) {
		this.loginId = loginId;
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
	 * 取得 功能编号.
	 * @return 功能编号
	 */
	public String getFuncCode() {
		return this.funcCode;
	}

	/**
	 * 设置 功能编号.
	 * @param funcCode 功能编号
	 */
	public void setFuncCode(String funcCode) {
		this.funcCode = funcCode;
	}

	/**  
	 * 取得 操作时间.
	 * @return 操作时间
	 */
	public Date getOpDatetime() {
		return this.opDatetime;
	}

	/**
	 * 设置 操作时间.
	 * @param opDatetime 操作时间
	 */
	public void setOpDatetime(Date opDatetime) {
		this.opDatetime = opDatetime;
	}

}
