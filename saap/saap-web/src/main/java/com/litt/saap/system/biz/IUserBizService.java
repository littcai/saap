package com.litt.saap.system.biz;

import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import org.springframework.ui.context.Theme;

import com.litt.core.exception.BusiException;
import com.litt.saap.common.vo.LoginUserVo;
import com.litt.saap.common.vo.TenantUserVo;
import com.litt.saap.system.po.ActivationCode;
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
 * @since 2013-10-17
 * @version 1.0
 */
public interface IUserBizService {
	
	/**
	 * 用户注册.
	 *
	 * @param loginId the login id
	 * @param password the password
	 * @param email the email
	 * @param loginIp the login ip
	 * @param locale the locale
	 * @param timeZone the time zone
	 * @param theme the theme
	 */
	public UserInfoVo doRegister(String loginId, String password, String email, String loginIp, Locale locale, TimeZone timeZone, Theme theme);
	
	/**
	 * 邀请用户加入.
	 *
	 * @param userId 发起用户
	 * @param email the email
	 * @param locale the locale
	 * @param timeZone the time zone
	 * @param theme the theme
	 */
	public void doInvite(Integer inviterUserId, Integer targetRoleId, String email, String comment, Locale locale, TimeZone timeZone, Theme theme);
	
	/**
	 * 加入.
	 *
	 * @param code the code 激活码
	 * @param source the source 发起用户邮件地址
	 * @param account the account 目标用户邮件地址
	 * @return the login user vo
	 */
	public LoginUserVo doJoin(String loginId, String password, String email, String loginIp, String code, Locale locale, TimeZone timeZone, Theme theme);
	
	/**
	 * 用户账号激活.
	 *
	 * @param code the code
	 */
	public LoginUserVo doActivate(String code, String loginIp);
	
	/**
	 * 启用新事务删除用户.
	 *
	 * @param userId the user id
	 */
	public void newTranDeleteUser(ActivationCode activationCode);
	
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
	
	/**
	 * Do login.
	 *
	 * @param loginId the login id
	 * @param password the password
	 * @param loginIp the login ip
	 * @param isAutoLogin the is auto login
	 * @param locale the locale
	 * @return the login vo
	 * @throws BusiException the busi exception
	 */
	public LoginUserVo doLogin(String loginId, String password, String loginIp, boolean isAutoLogin, Locale locale) throws BusiException;

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
	 * 切换用户当前首选的租户空间.
	 *
	 * @param loginUser the login user
	 * @param tenantId the tenant id
	 */
	public void doSwitchCurrentTenant(LoginUserVo loginUser, int tenantId);
	
	/**
	 * 发现租户下所有用户.
	 *
	 * @param tenantId the tenant id
	 * @return the list
	 */
	public List<TenantUserVo> findByTenant(int tenantId);
	
	

}