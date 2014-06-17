package com.litt.saap.common.vo;

import java.util.Date;

/**
 * IUserInfo.
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
 * @since 2014年6月3日
 * @version 1.0
 */
public interface IUserInfo {

	/**
	 * @return the id
	 */
	public Integer getId();

	/**
	 * @return the loginId
	 */
	public String getLoginId();

	/**
	 * @return the userType
	 */
	public int getUserType();

	/**
	 * @return the userName
	 */
	public String getUserName();

	/**
	 * @return the nickName
	 */
	public String getNickName();

	/**
	 * @return the gender
	 */
	public int getGender();

	/**
	 * @return the email
	 */
	public String getEmail();

	/**
	 * @return the mobile
	 */
	public String getMobile();

	/**
	 * @return the locale
	 */
	public String getLocale();

	/**
	 * @return the timezone
	 */
	public int getTimezone();

	/**
	 * @return the theme
	 */
	public String getTheme();

	/**
	 * @return the headImgUrl
	 */
	public String getHeadImgUrl();

	/**
	 * @return the createDatetime
	 */
	public Date getCreateDatetime();

	/**
	 * @return the status
	 */
	public int getStatus();

}