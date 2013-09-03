package com.litt.saap.system.service;

import java.util.Locale;

import com.litt.core.exception.BusiException;
import com.litt.saap.common.vo.LoginVo;
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

	public LoginVo doLogin(String loginId, String password, String loginIp,
			boolean isAutoLogin, Locale locale) throws BusiException;
	
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

}