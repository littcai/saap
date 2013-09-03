package com.litt.saap.system.po;

import java.util.Date;

import java.io.Serializable;

/**
 * 用户信息表<br>
 * 表名：user_info<br>
 * @author Hibernate Tools 3.2.4.GA
 * @version 1.0
 * @since 2013-9-2 15:38:07
 */
public class UserInfo implements Serializable {
	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 序号.
	 */
	private Integer id;

	/**
	 * 登录ID.
	 */
	private String loginId;

	/**
	 * 密码.
	 */
	private String password;

	/**
	 * 用户类型.
	 */
	private int userType;

	/**
	 * 用户名.
	 */
	private String userName;

	/**
	 * 昵称.
	 */
	private String nickName;

	/**
	 * 性别
	        0:Unknown
	        1:Male
	        2:Female.
	 */
	private int gender;

	/**
	 * 电子邮件.
	 */
	private String email;

	/**
	 * 手机号.
	 */
	private String mobile;

	/**
	 * 状态(1001)
	        0：未审核
	        1：正常
	        2：注销
	        3：删除
	        4：锁定.
	 */
	private int status;

	/**
	 * 语言.
	 */
	private String locale;

	/**
	 * 时区.
	 */
	private int timezone;

	/**
	 * 主题.
	 */
	private String theme;

	/**
	 * 头像地址.
	 */
	private String headImgUrl;

	/**
	 * 创建时间.
	 */
	private Date createDatetime;

	/**
	 * 更新时间.
	 */
	private Date updateDatetime;

	public UserInfo() {
	}

	public UserInfo(String loginId, String password, int userType,
			String userName, String nickName, int gender, int status,
			String locale, int timezone, String theme, Date createDatetime) {
		this.loginId = loginId;
		this.password = password;
		this.userType = userType;
		this.userName = userName;
		this.nickName = nickName;
		this.gender = gender;
		this.status = status;
		this.locale = locale;
		this.timezone = timezone;
		this.theme = theme;
		this.createDatetime = createDatetime;
	}

	public UserInfo(String loginId, String password, int userType,
			String userName, String nickName, int gender, String email,
			String mobile, int status, String locale, int timezone,
			String theme, String headImgUrl, Date createDatetime,
			Date updateDatetime) {
		this.loginId = loginId;
		this.password = password;
		this.userType = userType;
		this.userName = userName;
		this.nickName = nickName;
		this.gender = gender;
		this.email = email;
		this.mobile = mobile;
		this.status = status;
		this.locale = locale;
		this.timezone = timezone;
		this.theme = theme;
		this.headImgUrl = headImgUrl;
		this.createDatetime = createDatetime;
		this.updateDatetime = updateDatetime;
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
	 * 取得 登录ID.
	 * @return 登录ID
	 */
	public String getLoginId() {
		return this.loginId;
	}

	/**
	 * 设置 登录ID.
	 * @param loginId 登录ID
	 */
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	/**  
	 * 取得 密码.
	 * @return 密码
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * 设置 密码.
	 * @param password 密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**  
	 * 取得 用户类型.
	 * @return 用户类型
	 */
	public int getUserType() {
		return this.userType;
	}

	/**
	 * 设置 用户类型.
	 * @param userType 用户类型
	 */
	public void setUserType(int userType) {
		this.userType = userType;
	}

	/**  
	 * 取得 用户名.
	 * @return 用户名
	 */
	public String getUserName() {
		return this.userName;
	}

	/**
	 * 设置 用户名.
	 * @param userName 用户名
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**  
	 * 取得 昵称.
	 * @return 昵称
	 */
	public String getNickName() {
		return this.nickName;
	}

	/**
	 * 设置 昵称.
	 * @param nickName 昵称
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	/**  
	 * 取得 性别
	        0:Unknown
	        1:Male
	        2:Female.
	 * @return 性别
	        0:Unknown
	        1:Male
	        2:Female
	 */
	public int getGender() {
		return this.gender;
	}

	/**
	 * 设置 性别
	        0:Unknown
	        1:Male
	        2:Female.
	 * @param gender 性别
	        0:Unknown
	        1:Male
	        2:Female
	 */
	public void setGender(int gender) {
		this.gender = gender;
	}

	/**  
	 * 取得 电子邮件.
	 * @return 电子邮件
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * 设置 电子邮件.
	 * @param email 电子邮件
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**  
	 * 取得 手机号.
	 * @return 手机号
	 */
	public String getMobile() {
		return this.mobile;
	}

	/**
	 * 设置 手机号.
	 * @param mobile 手机号
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**  
	 * 取得 状态(1001)
	        0：未审核
	        1：正常
	        2：注销
	        3：删除
	        4：锁定.
	 * @return 状态(1001)
	        0：未审核
	        1：正常
	        2：注销
	        3：删除
	        4：锁定
	 */
	public int getStatus() {
		return this.status;
	}

	/**
	 * 设置 状态(1001)
	        0：未审核
	        1：正常
	        2：注销
	        3：删除
	        4：锁定.
	 * @param status 状态(1001)
	        0：未审核
	        1：正常
	        2：注销
	        3：删除
	        4：锁定
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**  
	 * 取得 语言.
	 * @return 语言
	 */
	public String getLocale() {
		return this.locale;
	}

	/**
	 * 设置 语言.
	 * @param locale 语言
	 */
	public void setLocale(String locale) {
		this.locale = locale;
	}

	/**  
	 * 取得 时区.
	 * @return 时区
	 */
	public int getTimezone() {
		return this.timezone;
	}

	/**
	 * 设置 时区.
	 * @param timezone 时区
	 */
	public void setTimezone(int timezone) {
		this.timezone = timezone;
	}

	/**  
	 * 取得 主题.
	 * @return 主题
	 */
	public String getTheme() {
		return this.theme;
	}

	/**
	 * 设置 主题.
	 * @param theme 主题
	 */
	public void setTheme(String theme) {
		this.theme = theme;
	}

	/**  
	 * 取得 头像地址.
	 * @return 头像地址
	 */
	public String getHeadImgUrl() {
		return this.headImgUrl;
	}

	/**
	 * 设置 头像地址.
	 * @param headImgUrl 头像地址
	 */
	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
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

}
