package com.litt.saap.core.web.util;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.LocaleUtils;
import org.springframework.beans.propertyeditors.LocaleEditor;
import org.springframework.ui.context.Theme;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.theme.SessionThemeResolver;

import com.litt.core.common.BeanManager;
import com.litt.core.common.CoreConstants;
import com.litt.core.exception.BusiCodeException;
import com.litt.core.exception.NotLoginException;
import com.litt.core.shield.security.SecurityContext;
import com.litt.core.shield.security.SecurityContextHolder;
import com.litt.core.shield.vo.ILoginVo;
import com.litt.core.util.ValidateUtils;
import com.litt.core.web.util.CookieUtils;
import com.litt.saap.common.vo.LoginUserVo;
import com.litt.saap.core.common.SaapConstants;

/** 
 * 
 * 操作员登录辅助类.
 * 
 * <pre><b>描述：</b>
 *    用来封装登录时对SESSION、COOKIE的操作 
 * </pre>
 * 
 * <pre><b>修改记录：</b>
 *    
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">蔡源</a>
 * @since 2009-4-7
 * @version 1.0
 *
 */
public class LoginUtils
{
	private LoginUtils(){};
	
	/**
	 * 将登录信息设置到SESSION中
	 * @param session 会话
	 * @param loginVo
	 */
	public static void setLoginSession(HttpSession session, ILoginVo loginVo)
	{	
		//缓存登录对象
		session.setAttribute(SaapConstants.SESSION_USER, loginVo);	
	}
	
	/**
	 * 用户是否已登录.
	 *
	 * @param request the request
	 * @return true, if is user login
	 */
	public static boolean isUserLogin(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		return session.getAttribute(SaapConstants.SESSION_USER) !=null;
	}
	
	/**
	 * 获得登录操作员信息.
	 *
	 * @param request http请求对象
	 * @return ILoginVo
	 */
	public static ILoginVo getLoginVo(HttpServletRequest request) throws NotLoginException
	{
		HttpSession session = request.getSession();
		Locale locale = getLocale(request);
		return getLoginVo(session, locale);
	}
	
	/**
	 * 获得登录操作员信息.
	 * 
	 * @param session 会话
	 * 
	 * @return ILoginVo
	 * 
	 * @throws NotLoginException 未登录异常
	 */
	public static ILoginVo getLoginVo(HttpSession session, Locale locale) throws NotLoginException
	{
		ILoginVo loginVo = (ILoginVo)session.getAttribute(SaapConstants.SESSION_USER);
		if(loginVo==null)
			throw new NotLoginException(BeanManager.getMessage("error.login.notLogin", locale));
		else 
			return loginVo;
	}
	
	/**
	 * 是否自动登录.
	 *
	 * @param request the request
	 * @return true, if is auto login
	 */
	public static boolean isAutoLogin(HttpServletRequest request)
	{
		return ValidateUtils.isEmpty(CookieUtils.getCookieValue(request, SaapConstants.COOKIE_USER_TOKEN));
	}
	
	/**
	 * Gets the auto login token.
	 *
	 * @param request the request
	 * @return the auto login token
	 */
	public static String getAutoLoginToken(HttpServletRequest request)
	{		
		return CookieUtils.getCookieValue(request, SaapConstants.COOKIE_USER_TOKEN);
	}
	
	/**
	 * 将登录信息从SESSION中删除
	 * @param session 会话
	 */
	public static void removeLoginSession(HttpSession session)
	{
		session.removeAttribute(SaapConstants.SESSION_USER);	
	}	
	
	/**
	 * 将自动登录信息设置到COOKIE中.
	 * 
	 * @param loginVo the login vo
	 * @param response 响应
	 */
	public static void setAutoLoginCookie(HttpServletResponse response, ILoginVo loginVo, String autoLoginToken)
	{		
		CookieUtils.addCookie(response, SaapConstants.COOKIE_USER_TOKEN, autoLoginToken);
		CookieUtils.addCookie(response, CoreConstants.COOKIE_LOCALE, loginVo.getLocale());	//国际化语言
	}
	
	/**
	 * 将自动登录信息设置从COOKIE中移除.
	 * 
	 * @param response 响应
	 */
	public static void removeAutoLoginCookie(HttpServletResponse response)
	{
		CookieUtils.removeCookie(response, SaapConstants.COOKIE_USER_TOKEN);
		CookieUtils.removeCookie(response, CoreConstants.COOKIE_LOCALE);
	}
	
	/**
	 * Gets the locale.
	 *
	 * @param lang the lang
	 * @return the locale
	 */
	public static Locale getLocale(String lang) 
	{
		if(ValidateUtils.isEmpty(lang))	//如果不存在则获取系统默认
			return Locale.getDefault();	
		
		return LocaleUtils.toLocale(lang);	
	}
	
	/**
	 * Gets the locale.
	 * 如果没有Locale，则取浏览器的Locale
	 *
	 * @param request the request
	 * @return the locale
	 */
	public static Locale getLocale(HttpServletRequest request) 
	{
		Locale locale = request.getLocale();
		LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
		if (localeResolver != null) {
			locale = localeResolver.resolveLocale(request);
		}	
		return locale==null?Locale.getDefault():locale;
	}	
	
	/**
	 * Gets the theme.
	 *
	 * @param request the request
	 * @return the theme
	 */
	public static Theme getTheme(HttpServletRequest request) 
	{
		Theme theme = RequestContextUtils.getTheme(request);
		return theme;
	}
	
	/**
	 * 修改显示语言.
	 * @param locale
	 * @param request
	 */
	public static void changeLocale(String lang, HttpServletRequest request, HttpServletResponse response) {
		
		LocaleEditor localeEditor = new LocaleEditor();  
		localeEditor.setAsText(lang);
		Locale locale = (Locale)localeEditor.getValue();
		
		changeLocale(locale, request, response); 	
		
		//Locale locale = getLocale(lang);
		//request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, locale);
	}

	/**
	 * @param request
	 * @param response
	 * @param locale
	 */
	public static void changeLocale(Locale locale, HttpServletRequest request, HttpServletResponse response) {
		LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);  
		if (localeResolver == null) {  
			throw new IllegalStateException("No LocaleResolver found: not in a DispatcherServlet request?");  
		} 		
		localeResolver.setLocale(request, response, locale);
	}
	
	/**
	 * 修改显示语言.
	 * @param locale
	 * @param request
	 */
	public static void changeTheme(String theme, HttpServletRequest request) {
		if(ValidateUtils.isEmpty(theme))
			throw new IllegalArgumentException("Theme can't be null");
		
		request.getSession().setAttribute(SessionThemeResolver.THEME_SESSION_ATTRIBUTE_NAME, theme);
	}
//	
//	/**
//	 * Adds the flush attribute.
//	 *
//	 * @param session the session
//	 */
//	public static void addFlushAttribute(HttpSession session, String key, Object value)
//	{
//		session.setAttribute(key, value);
//	}
//	
//	/**
//	 * Gets the flush attribute.
//	 *
//	 * @param session the session
//	 * @param key the key
//	 * @return the flush attribute
//	 */
//	public static Object getFlushAttribute(HttpSession session, String key)
//	{
//		Object value = session.getAttribute(key);
//		session.removeAttribute(key);
//		return value;
//	}
	
	/**
	 * 获取登录操作员信息.
	 * 
	 * 需配置实现SecurityContext
	 * 
	 * @return ILoginVo 登录对象
	 * 
	 * @throws NotLoginException 未登录则抛出此异常
	 */
	public static ILoginVo getLoginVo() throws NotLoginException
	{
		SecurityContext context = SecurityContextHolder.getContext();
		if(context!=null)
			return context.getLoginVo();
		else 
			throw new NotLoginException("用户尚未登录或登录已超时！");
	}
	
	/**
	 * 获得登录操作员ID.
	 * 需配置实现SecurityContext
	 * 
	 * @return 操作员ID
	 * 
	 * @throws NotLoginException 未登录则抛出此异常
	 */
	public static Long getLoginOpId() throws NotLoginException
	{
		ILoginVo loginVo = getLoginVo();
		if(loginVo!=null)
			return loginVo.getOpId();
		else
			return null;
	}
	
	/**
	 * 获得登录操作员登录ID.
	 * 需配置实现SecurityContext
	 * 
	 * @return 操作员登录ID
	 * 
	 * @throws NotLoginException 未登录则抛出此异常
	 */
	public static String getLoginId() throws NotLoginException
	{
		ILoginVo loginVo = getLoginVo();
		if(loginVo!=null)
			return loginVo.getLoginId();
		else
			return null;
	}
	
	/**
	 * 获得登录操作员登录IP.
	 * 需配置实现SecurityContext
	 * 
	 * @return 登录客户端IP
	 * 
	 * @throws NotLoginException 未登录则抛出此异常
	 */
	public static String getLoginIp() throws NotLoginException
	{
		ILoginVo loginVo = getLoginVo();
		if(loginVo!=null)
			return loginVo.getLoginIp();
		else
			return null;
	}
	
	/**
	 * 
	 * @return 租户ID
	 * 
	 * @throws NotLoginException 未登录则抛出此异常
	 */
	public static int getTenantId() throws NotLoginException
	{
		LoginUserVo loginUser = (LoginUserVo)getLoginVo();
		if(loginUser!=null)		
			return loginUser.getTenantId();
		else 
			return -1;
	}
	
	/**
	 * @param tenantId
	 * @throws NotLoginException
	 */
	public static void validateTenant(int tenantId) throws NotLoginException {
		ILoginVo loginVo = LoginUtils.getLoginVo();
		int loginTenantId = LoginUtils.getTenantId();
		if(loginTenantId!=tenantId)
		{
			throw new BusiCodeException("error.biz.unauthorized", loginVo.toLocale());
		}
	}
	
}