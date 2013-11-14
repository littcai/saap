package com.litt.saap.common.web;

import java.util.Locale;
import java.util.TimeZone;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.context.Theme;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ThemeResolver;

import com.litt.core.common.BeanManager;
import com.litt.core.exception.BusiException;
import com.litt.core.exception.NotLoginException;
import com.litt.core.shield.vo.ILoginVo;
import com.litt.core.util.ValidateUtils;
import com.litt.core.web.servlet.LoginCaptchaServlet;
import com.litt.core.web.util.WebUtils;
import com.litt.saap.common.vo.LoginUserVo;
import com.litt.saap.core.web.util.LoginUtils;
import com.litt.saap.system.biz.ITenantBizService;
import com.litt.saap.system.biz.IUserBizService;
import com.litt.saap.system.bo.TenantActiveBo;
import com.litt.saap.system.po.ActivationCode;
import com.litt.saap.system.po.UserInfo;
import com.litt.saap.system.service.IMenuService;
import com.litt.saap.system.service.IUserInfoService;
import com.litt.saap.system.vo.MenuTreeNodeVo;

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
	
	@Resource
	private IUserBizService userBizService;
	
	@Resource
	private ITenantBizService tenantBizService;
	
	@Resource
	private IMenuService menuService;	
	
	@Resource
	private MessageSource messageSource;
	
	/**
	 * The theme resolver.
	 */
	@Resource  
    private ThemeResolver themeResolver; 	
	
	@RequestMapping(value="index.do")
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response)
	{
		if(LoginUtils.isUserLogin(request))
		{
			return new ModelAndView("redirect:/main.do");
		}
		else{
			String timeout = null;
			if(request.getSession().getAttribute("timeout")!=null)
			{
				Locale locale = LoginUtils.getLocale(request);
				timeout = messageSource.getMessage("login.error.timeout", null, locale);
				request.getSession().removeAttribute("timeout");
			}
			
			Locale locale = LoginUtils.getLocale(request);				
			LoginUtils.changeLocale(locale, request, response);
			Theme theme = LoginUtils.getTheme(request);
			return new ModelAndView("/common/login").addObject("locale", locale).addObject("timeout", timeout);
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
			throw new BusiException(BeanManager.getMessage("error.login.captcha", LoginUtils.getLocale(request)));			
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
		if(!ValidateUtils.isEmpty(locale))
			LoginUtils.changeLocale(locale, request, response);
		
		//从请求中获取查询条件
		String loginIp =  WebUtils.getRemoteIp(request);		
		ILoginVo loginVo = userBizService.doLogin(loginId, password, loginIp, isAutoLogin, LoginUtils.getLocale(request));		
		HttpSession session = request.getSession();
		LoginUtils.setLoginSession(session, loginVo);
		if(isAutoLogin)
		{		
			LoginUtils.setAutoLoginCookie(response, loginVo, loginVo.getAutoLoginToken());	//设置自动登录
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
	 * 用户注册.
	 *
	 * @param loginId 用户名
	 * @param password 密码
	 * @param email the email
	 * @param request 请求对象
	 * @param response 响应对象
	 * @return 视图
	 * @throws Exception the exception
	 */
	@RequestMapping(value="register.json")
	public ModelAndView register(@RequestParam String loginId, @RequestParam String password, @RequestParam String email
			, HttpServletRequest request, HttpServletResponse response) throws Exception
	{				
		String loginIp = WebUtils.getRemoteIp(request);
		
		Locale locale = LoginUtils.getLocale(request);		
		TimeZone timeZone = TimeZone.getDefault();
		Theme theme = LoginUtils.getTheme(request);
		//从请求中获取查询条件	
		userBizService.doRegister(loginId, password, email, loginIp, locale, timeZone, theme);		
		
		return new ModelAndView("jsonView");
	}
	
	/**
	 * 用户激活.
	 *
	 * @param email the email
	 * @param code the code
	 * @param request 请求对象
	 * @param response 响应对象
	 * @return 视图
	 * @throws Exception the exception
	 */
	@RequestMapping(value="activate.do")
	public ModelAndView activate(@RequestParam String code
			, HttpServletRequest request, HttpServletResponse response) throws Exception
	{				
		String loginIp = WebUtils.getRemoteIp(request);
		
		Locale locale = LoginUtils.getLocale(request);
		
		//从请求中获取查询条件	
		LoginUserVo loginUser = userBizService.doActivate(code, loginIp);	
				
		//进行自动登录
		HttpSession session = request.getSession();
		LoginUtils.setLoginSession(session, loginUser);
		//跳转到消息页面，显示激活成功的信息
		String message = messageSource.getMessage("activate.success", null, locale);
		String redirectUrl = "index";	//跳转到首页
		
		return new ModelAndView("/common/message").addObject("message", message).addObject("redirectUrl", redirectUrl);
	}
	
	/**
	 * Forget password.
	 *
	 * @param email the email
	 * @param request the request
	 * @param response the response
	 * @return the model and view
	 * @throws Exception the exception
	 */
	@RequestMapping(value="forgetPassword.json")
	public ModelAndView forgetPassword(@RequestParam String email
			, HttpServletRequest request, HttpServletResponse response) throws Exception
	{				
		String loginIp = WebUtils.getRemoteIp(request);
		
		Locale locale = LoginUtils.getLocale(request);
		//从请求中获取查询条件	
		userBizService.doForgetPassword(email, loginIp, locale);
		
		return new ModelAndView("jsonView");
	}
	
	/**
	 * 重置密码.
	 *
	 * @param id 找回密码ID
	 * @param password 新密码
	 * @param request the request
	 * @param response the response
	 * @return the model and view
	 * @throws Exception the exception
	 */
	@RequestMapping(value="resetPassword.do")
	public ModelAndView toResetPassword(HttpServletRequest request, HttpServletResponse response) throws Exception
	{				
		String token = request.getParameter("token");
		Theme theme = LoginUtils.getTheme(request);
		
		Locale locale = LoginUtils.getLocale(request);
		ActivationCode forgetPassword = userInfoService.loadForgetPassword(token, locale);			
		UserInfo userInfo = userInfoService.load(forgetPassword.getUserId());
		
		return new ModelAndView("/common/resetPassword").addObject("token", token).addObject("userInfo", userInfo);
	}
	
	/**
	 * 重置密码.
	 *
	 * @param id 找回密码ID
	 * @param password 新密码
	 * @param request the request
	 * @param response the response
	 * @return the model and view
	 * @throws Exception the exception
	 */
	@RequestMapping(value="resetPassword.json")
	public ModelAndView resetPassword(@RequestParam String id, @RequestParam String password
			, HttpServletRequest request, HttpServletResponse response) throws Exception
	{				
		String loginIp = WebUtils.getRemoteIp(request);
		
		Locale locale = LoginUtils.getLocale(request);
		//从请求中获取查询条件	
		userBizService.doResetPassword(id, password, loginIp, locale);
		
		return new ModelAndView("jsonView");
	}
	
	/**
	 * 开通租户.
	 * 根据订单开通相应的租户
	 * TODO 该方法仅做测试使用
	 *
	 * @param orderNo the order no
	 * @param request 请求对象
	 * @param response 响应对象
	 * @return 视图
	 * @throws Exception the exception
	 */
	@RequestMapping(value="activateTenant.do")
	public ModelAndView activateTenant(@RequestParam String orderNo, @RequestParam Integer userId
			, HttpServletRequest request, HttpServletResponse response) throws Exception
	{				
		String loginIp = WebUtils.getRemoteIp(request);
		
		Locale locale = LoginUtils.getLocale(request);
				
		//激活租户	
		TenantActiveBo tenantActiveBo = tenantBizService.doActivate(orderNo, userId);		
		
		LoginUserVo loginUser = (LoginUserVo)LoginUtils.getLoginVo(request);
		//如果当前登录用户是开通租户的用户，则立即更新登录用户的租户权限，必须再重新登录
		if(loginUser.getOpId().equals(userId))
		{
			loginUser.setTenant(tenantActiveBo.getTenant());
			loginUser.addRoleId(tenantActiveBo.getRoleId());
			loginUser.addPermissions(tenantActiveBo.getPermissionCodes());
		}
		
		//HttpSession session = request.getSession();
		//LoginUtils.setLoginSession(session, loginUser);
		//跳转到消息页面，显示激活成功的信息
		String message = messageSource.getMessage("activate.success", null, locale);
		String redirectUrl = "index";	//跳转到首页
		
		return new ModelAndView("/common/message").addObject("message", message).addObject("redirectUrl", redirectUrl);
	}
	
	
	/**
	 * 获得登录用户的授权菜单.
	 *
	 * @param id 找回密码ID
	 * @param password 新密码
	 * @param request the request
	 * @param response the response
	 * @return the model and view
	 * @throws Exception the exception
	 */
	@RequestMapping(value="getMenuTree.json")
	public ModelAndView getMenuTree(HttpServletRequest request) throws Exception
	{				
		String loginIp = WebUtils.getRemoteIp(request);
		
		ILoginVo loginVo = LoginUtils.getLoginVo();
		
		Locale locale = LoginUtils.getLocale(request);
		
		MenuTreeNodeVo menuTree = menuService.findTreeByOpPermission((LoginUserVo)loginVo);
		
		return new ModelAndView("jsonView").addObject("menuTree", menuTree);
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
		LoginUtils.changeLocale(locale, request, response);
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
