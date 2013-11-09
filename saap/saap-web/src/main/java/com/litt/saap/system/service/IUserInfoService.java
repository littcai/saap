package com.litt.saap.system.service;

import java.util.Locale;
import java.util.TimeZone;

import org.springframework.ui.context.Theme;

import com.litt.core.exception.BusiException;
import com.litt.saap.common.vo.LoginUserVo;
import com.litt.saap.system.po.ForgetPassword;
import com.litt.saap.system.po.UserInfo;

/**
 * .
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
 * @since 2013-9-2
 * @version 1.0
 */
public interface IUserInfoService {

	/**
	 * Save.
	 *
	 * @param userInfo the user info
	 * @return the integer
	 */
	public Integer save(UserInfo userInfo);

	/**
	 * Update.
	 *
	 * @param userInfo the user info
	 */
	public void update(UserInfo userInfo);

	/**
	 * Delete logic.
	 *
	 * @param userId the user id
	 */
	public void deleteLogic(Integer userId);

	/**
	 * Delete logic.
	 *
	 * @param userId the user id
	 */
	public void doResume(Integer userId, Locale locale);
	
	/**
	 * 用户注册.
	 *
	 * @param loginId 登录ID
	 * @param password 密码
	 * @param email the email
	 * @param loginIp 登陆IP
	 * @param locale 语言
	 * @param timeZone 时区
	 * @param theme 主题
	 */
	public void doRegister(String loginId, String password, String email, String loginIp, Locale locale, TimeZone timeZone, Theme theme);
	
	/**
	 * 找回密码.
	 *
	 * @param email the email
	 * @param loginIp the login ip
	 * @param locale the locale
	 */
	public void doForgetPassword(String email, String loginIp, Locale locale);
	
	/**
	 * 重置密码.
	 *
	 * @param id the id
	 * @param password 新密码
	 * @param loginIp 客户端IP
	 * @param locale the locale
	 */
	public void doResetPassword(String id, String password, String loginIp, Locale locale);

	public LoginUserVo doLogin(String loginId, String password, String loginIp,
			boolean isAutoLogin, Locale locale) throws BusiException;
	
	/**
	 * 自动登录.
	 *
	 * @param token the token
	 * @param loginIp 请求IP
	 * @param locale 语言
	 * @return LoginVo
	 */
	public LoginUserVo doAutoLogin(String token, String loginIp, Locale locale);
	
	/**
	 * Do logout.
	 *
	 * @param userId the user id
	 * @param loginIp the login ip
	 * @param autoLoginToken the auto login token
	 */
	public void doLogout(Integer userId, String loginIp, String autoLoginToken);

	/**
	 * Load.
	 *
	 * @param userId the user id
	 * @return the user info
	 */
	public UserInfo load(Integer userId);

	/**
	 * Load by login id.
	 *
	 * @param loginId the login id
	 * @return the user info
	 */
	public UserInfo loadByLoginId(String loginId);
	
	/**
	 * Load by email.
	 *
	 * @param email the email
	 * @return the user info
	 */
	public UserInfo loadByEmail(String email);
	
	/**
	 * Load forget password.
	 *
	 * @param token the token
	 * @param locale the locale
	 * @return the forget password
	 */
	public ForgetPassword loadForgetPassword(String token, Locale locale);

}