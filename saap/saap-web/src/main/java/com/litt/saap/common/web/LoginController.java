package com.litt.saap.common.web;

import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.propertyeditors.LocaleEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.context.Theme;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ThemeResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.litt.core.common.BeanManager;
import com.litt.core.exception.BusiException;
import com.litt.core.exception.NotLoginException;
import com.litt.core.shield.vo.ILoginVo;
import com.litt.core.web.servlet.LoginCaptchaServlet;
import com.litt.saap.core.web.util.LoginUtils;
import com.litt.saap.system.service.IUserInfoService;

/** 
 * 
 * 操作员登录控制器.
 * 
 * <pre><b>描述：</b>
 *    操作员登录、退出、安全退出等 
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
@Controller
@RequestMapping(value="/login")
public class LoginController {
	
	@Resource
	private IUserInfoService userInfoService;
	
	/**
	 * The theme resolver.
	 */
	@Resource  
    private ThemeResolver themeResolver; 	
	
	@RequestMapping(value="index.do")
	public ModelAndView index(HttpServletRequest request,HttpServletResponse response)
	{
		if(LoginUtils.isUserLogin(request))
		{
			return new ModelAndView("redirect:/main.do");
		}
		else{
			Locale locale = LoginUtils.getLocale(request);
			Theme theme = LoginUtils.getTheme(request);
			return new ModelAndView("/theme/"+theme.getName()+"/login").addObject("locale", locale);
		}
	}

	/**
	 * 登录.
	 *
	 * @param loginId 用户名
	 * @param password 密码
	 * @param isAutoLogin 是否自动登录
	 * @param locale 显示语言
	 * @param request 请求对象
	 * @param response 响应对象
	 * @return 视图
	 * @throws Exception the exception
	 */
	@RequestMapping(value="login.json")
	public ModelAndView login(@RequestParam String loginId, @RequestParam String password
			, @RequestParam(required=false) boolean isAutoLogin, @RequestParam(required=false) String locale
			, HttpServletRequest request, HttpServletResponse response) throws Exception
	{				
		//从请求中获取查询条件			
		boolean isValid = LoginCaptchaServlet.validateCaptcha(request);	//验证操作员登录认证码
		if(!isValid)
		{
			throw new BusiException(BeanManager.getMessage("login.error.captcha", LoginUtils.getLocale(locale)));
		}
		return this.loginDirect(loginId, password, isAutoLogin, locale, request, response);
	}
	
	/**
	 * 登录(不带验证码，仅用于测试).
	 *
	 * @param loginId 用户名
	 * @param password 密码
	 * @param isAutoLogin 是否自动登录
	 * @param locale 界面语言
	 * @param request 请求对象
	 * @param response 响应对象
	 * @return 视图
	 * @throws Exception the exception
	 */
	@RequestMapping(value="loginDirect.json")
	public ModelAndView loginDirect(@RequestParam String loginId, @RequestParam String password
			, @RequestParam(required=false) boolean isAutoLogin, @RequestParam(required=false) String locale,
			HttpServletRequest request, HttpServletResponse response) throws Exception
	{			
		LoginUtils.changeLocale(locale, request);
		
		//从请求中获取查询条件
		String loginIp = request.getRemoteAddr();		
		ILoginVo loginVo = userInfoService.doLogin(loginId, password, loginIp, isAutoLogin, LoginUtils.getLocale(locale));		
		HttpSession session = request.getSession();
		LoginUtils.setLoginSession(session, loginVo);
		if(isAutoLogin)
		{		
			//LoginUtils.setAutoLoginCookie(response, loginVo, loginVo.getAutoLoginToken());	//设置自动登录
		}		
		return new ModelAndView("jsonView");
	}	
	
	/**
	 * 退出.
	 *
	 * @param request the request 请求对象
	 * @param response the response 响应对象
	 * @return 视图
	 * @throws Exception the exception
	 */
	@RequestMapping(value="logout.do")
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception
	{	
		try
		{
			ILoginVo loginVo = LoginUtils.getLoginVo(request);
			if(loginVo!=null)
			{
				String autoLoginToken = LoginUtils.getAutoLoginToken(request);
				userInfoService.doLogout(loginVo.getOpId().intValue(), loginVo.getLoginIp(), autoLoginToken);
			}
		}
		catch (NotLoginException e)
		{
			
		}		
		LoginUtils.removeAutoLoginCookie(response);	//清理自动登录
		LoginUtils.removeLoginSession(request.getSession());
		response.sendRedirect(request.getContextPath()+"/");	//跳转到根目录
		return null;
	}
	
	/**
	 * Change locale.
	 *
	 * @param locale the locale
	 * @param request the request
	 * @param response the response
	 * @throws Exception the exception
	 */
	@RequestMapping
	public void changeLocale(@RequestParam(required=false) String locale,
			HttpServletRequest request, HttpServletResponse response) throws Exception
	{			
		LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);  
		if (localeResolver == null) {  
			throw new IllegalStateException("No LocaleResolver found: not in a DispatcherServlet request?");  
		} 
		LocaleEditor localeEditor = new LocaleEditor();  
		localeEditor.setAsText(locale);
		localeResolver.setLocale(request, response, (Locale)localeEditor.getValue()); 	
		
		LoginUtils.changeLocale(locale, request);
	}
	
	
	/**
	 * Change theme.
	 *
	 * @param theme the theme
	 * @param request the request
	 * @param response the response
	 */
	@RequestMapping 
    public void changeTheme(@RequestParam String theme,
    		HttpServletRequest request, HttpServletResponse response) { 
        themeResolver.setThemeName(request, response, theme);         
        LoginUtils.changeTheme(theme, request);
    }  
}
