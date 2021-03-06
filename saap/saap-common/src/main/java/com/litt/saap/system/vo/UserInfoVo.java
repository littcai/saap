package com.litt.saap.system.vo;

import java.io.Serializable;
import java.util.Date;

import com.litt.saap.common.vo.IUserInfo;

/**
 * 用户信息对象.
 * 
 * <pre><b>描述：</b>
 *    
 * </pre>
 * 
 * <pre><b>修改记录：</b>
 *    
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">蔡源</a>
 * @since 2013-10-17
 * @version 1.0
 */
public class UserInfoVo implements Serializable, IUserInfo {
	
	/**
	 * 序号.
	 */
	private Integer id;

	/**
	 * 登录ID.
	 */
	private String loginId;
	
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
	 * @return
	 * @see com.litt.saap.common.vo.IUserInfo#getId()
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return
	 * @see com.litt.saap.common.vo.IUserInfo#getLoginId()
	 */
	public String getLoginId() {
		return loginId;
	}

	/**
	 * @param loginId the loginId to set
	 */
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	/**
	 * @return
	 * @see com.litt.saap.common.vo.IUserInfo#getUserType()
	 */
	public int getUserType() {
		return userType;
	}

	/**
	 * @param userType the userType to set
	 */
	public void setUserType(int userType) {
		this.userType = userType;
	}

	/**
	 * @return
	 * @see com.litt.saap.common.vo.IUserInfo#getUserName()
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return
	 * @see com.litt.saap.common.vo.IUserInfo#getNickName()
	 */
	public String getNickName() {
		return nickName;
	}

	/**
	 * @param nickName the nickName to set
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	/**
	 * @return
	 * @see com.litt.saap.common.vo.IUserInfo#getGender()
	 */
	public int getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(int gender) {
		this.gender = gender;
	}

	/**
	 * @return
	 * @see com.litt.saap.common.vo.IUserInfo#getEmail()
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return
	 * @see com.litt.saap.common.vo.IUserInfo#getMobile()
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return
	 * @see com.litt.saap.common.vo.IUserInfo#getLocale()
	 */
	public String getLocale() {
		return locale;
	}

	/**
	 * @param locale the locale to set
	 */
	public void setLocale(String locale) {
		this.locale = locale;
	}

	/**
	 * @return
	 * @see com.litt.saap.common.vo.IUserInfo#getTimezone()
	 */
	public int getTimezone() {
		return timezone;
	}

	/**
	 * @param timezone the timezone to set
	 */
	public void setTimezone(int timezone) {
		this.timezone = timezone;
	}

	/**
	 * @return
	 * @see com.litt.saap.common.vo.IUserInfo#getTheme()
	 */
	public String getTheme() {
		return theme;
	}

	/**
	 * @param theme the theme to set
	 */
	public void setTheme(String theme) {
		this.theme = theme;
	}

	/**
	 * @return
	 * @see com.litt.saap.common.vo.IUserInfo#getHeadImgUrl()
	 */
	public String getHeadImgUrl() {
		return headImgUrl;
	}

	/**
	 * @param headImgUrl the headImgUrl to set
	 */
	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}

	/**
	 * @return
	 * @see com.litt.saap.common.vo.IUserInfo#getCreateDatetime()
	 */
	public Date getCreateDatetime() {
		return createDatetime;
	}

	/**
	 * @param createDatetime the createDatetime to set
	 */
	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	/**
	 * @return
	 * @see com.litt.saap.common.vo.IUserInfo#getStatus()
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}


}
