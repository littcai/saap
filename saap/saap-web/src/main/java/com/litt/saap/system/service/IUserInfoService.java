package com.litt.saap.system.service;

import java.util.Locale;
import java.util.TimeZone;

import org.springframework.ui.context.Theme;

import com.litt.core.exception.BusiException;
import com.litt.saap.common.vo.LoginUserVo;
import com.litt.saap.system.po.ActivationCode;
import com.litt.saap.system.po.UserInfo;
import com.litt.saap.system.po.UserState;
import com.litt.saap.system.vo.UserInfoVo;

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
	 * 物理删除.
	 * 1、用户注册超时未激活.
	 *
	 * @param userInfo the user info
	 */
	public void delete(UserInfo userInfo);
	
	/**
	 * Delete logic.
	 *
	 * @param userId the user id
	 */
	public void delete(Integer userId);

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
	public void doResume(Integer userId);
	
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
	 * @return the user info vo
	 */
	public UserInfoVo doRegister(String loginId, String password, String email, String loginIp, Locale locale, TimeZone timeZone, Theme theme);
	

	public LoginUserVo doLogin(String loginId, String password, String loginIp,
			boolean isAutoLogin, Locale locale) throws BusiException;
	
	public LoginUserVo doLogin(UserInfo userInfo, UserState userState, String loginIp, boolean isAutoLogin, Locale locale) throws BusiException;
	
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
	 * Load user state.
	 *
	 * @param userId the user id
	 * @return the user state
	 */
	public UserState loadUserState(Integer userId);
	
}