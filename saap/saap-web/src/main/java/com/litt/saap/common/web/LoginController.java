package com.litt.saap.common.web;

import java.util.List;
import java.util.Locale;
import java.util.Map;
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
import com.litt.core.exception.BusiCodeException;
import com.litt.core.exception.BusiException;
import com.litt.core.exception.NotLoginException;
import com.litt.core.shield.vo.ILoginVo;
import com.litt.core.util.JsonUtils;
import com.litt.core.util.ValidateUtils;
import com.litt.core.web.servlet.LoginCaptchaServlet;
import com.litt.core.web.util.WebUtils;
import com.litt.saap.common.vo.LoginUserVo;
import com.litt.saap.core.module.tenant.config.TenantTypeConfigManager;
import com.litt.saap.core.web.util.LoginUtils;
import com.litt.saap.system.biz.ITenantBizService;
import com.litt.saap.system.biz.IUserBizService;
import com.litt.saap.system.bo.TenantActiveBo;
import com.litt.saap.system.bo.TenantQuitBo;
import com.litt.saap.system.po.ActivationCode;
import com.litt.saap.system.po.Role;
import com.litt.saap.system.po.UserInfo;
import com.litt.saap.system.po.UserState;
import com.litt.saap.system.service.IMenuService;
import com.litt.saap.system.service.IRoleService;
import com.litt.saap.system.service.ITenantService;
import com.litt.saap.system.service.IUserInfoService;
import com.litt.saap.system.service.impl.IActivationCodeService;
import com.litt.saap.system.vo.MenuTreeNodeVo;
import com.litt.saap.system.vo.TenantVo;

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
	private IRoleService roleService;
	
	@Resource
	private IUserBizService userBizService;
	
	@Resource
	private ITenantService tenantService;
	
	@Resource
	private ITenantBizService tenantBizService;
	
	@Resource
	private IActivationCodeService activationCodeService;
	
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
		//TODO 未处理时区
		
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
		if(!ValidateUtils.isEmpty(loginVo.getLocale()))
			LoginUtils.changeLocale(loginVo.getLocale(), request, response);
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
	public ModelAndView register(@RequestParam(required=false) String loginId, @RequestParam String password, @RequestParam String email
			, HttpServletRequest request, HttpServletResponse response) throws Exception
	{		
		loginId = email;	//用邮箱地址当登录ID
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
	 * 加入邀请.
	 *
	 * @param request the request
	 * @param response the response
	 * @return the model and view
	 * @throws Exception the exception
	 */
	@RequestMapping(value="join.do")
	public ModelAndView toJoin(HttpServletRequest request, HttpServletResponse response) throws Exception
	{			
		Locale locale = LoginUtils.getLocale(request);
		
		String code = request.getParameter("code");
		ActivationCode activationCode = activationCodeService.load(code);
		if(activationCode==null)	//邀请码不存在，跳转到信息提示页面
		{
			//跳转到消息页面
			String message = messageSource.getMessage("activate.error.codeNotExist", null, locale);
			String redirectUrl = "index";	//跳转到首页
			
			return new ModelAndView("/common/message").addObject("message", message).addObject("redirectUrl", redirectUrl);
		}
		else {
			//检查目标邮件用户是否已注册
			Map<String, Object> paramMap = JsonUtils.toObject(activationCode.getParams(), Map.class);
			String email = paramMap.get("email").toString();
			return new ModelAndView("/common/join").addObject("email", email).addObject("code", code);
		}		
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
	@RequestMapping(value="join.json")
	public ModelAndView join(@RequestParam String email, @RequestParam String password, @RequestParam String code
			, HttpServletRequest request, HttpServletResponse response) throws Exception
	{			
		String loginId = email;
		String loginIp = WebUtils.getRemoteIp(request);
				
		Locale locale = LoginUtils.getLocale(request);
		TimeZone timeZone = TimeZone.getDefault();
		Theme theme = LoginUtils.getTheme(request);
		
		userBizService.doJoin(loginId, password, email, loginIp, code, locale, timeZone, theme);
		
		return new ModelAndView("jsonView");
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
		
		Locale locale = LoginUtils.getLocale(request);			
		String code = request.getParameter("code");
		ActivationCode activationCode = activationCodeService.load(code);
		if(activationCode==null)	//邀请码不存在，跳转到信息提示页面
		{
			//跳转到消息页面
			String message = messageSource.getMessage("activate.error.codeNotExist", null, locale);
			String redirectUrl = "index";	//跳转到首页
			
			return new ModelAndView("/common/message").addObject("message", message).addObject("redirectUrl", redirectUrl);
		}
		else {		
			return new ModelAndView("/common/resetPassword").addObject("code", code);
		}
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
	public ModelAndView resetPassword(@RequestParam String code, @RequestParam String password
			, HttpServletRequest request, HttpServletResponse response) throws Exception
	{				
		String loginIp = WebUtils.getRemoteIp(request);
		
		Locale locale = LoginUtils.getLocale(request);
		//从请求中获取查询条件	
		userBizService.doResetPassword(code, password, loginIp);
		
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
	public ModelAndView activateTenant(@RequestParam String orderNo, @RequestParam String tenantAlias
			, HttpServletRequest request, HttpServletResponse response) throws Exception
	{				
		String loginIp = WebUtils.getRemoteIp(request);
		int userId = LoginUtils.getLoginOpId().intValue();
		
		Locale locale = LoginUtils.getLocale(request);
				
		//激活租户	
		TenantActiveBo tenantActiveBo = tenantBizService.doActivate(orderNo, userId, locale);		
		
		LoginUserVo loginUser = (LoginUserVo)LoginUtils.getLoginVo(request);
		//如果当前登录用户是开通租户的用户，则立即更新登录用户的租户权限，不必再重新登录
		if(loginUser.getOpId().intValue()==userId)
		{
			loginUser.setTenant(tenantActiveBo.getTenant());
			loginUser.addRoleId(tenantActiveBo.getRoleId());
			loginUser.addPermissions(tenantActiveBo.getPermissionCodes());
		}
		
		HttpSession session = request.getSession();
		LoginUtils.setLoginSession(session, loginUser);
		//跳转到消息页面，显示激活成功的信息
		String message = messageSource.getMessage("tenant.action.activate.success", new Object[]{tenantActiveBo.getTenant().getAppAlias()}, locale);
		String redirectUrl = "index";	//跳转到首页
		
		return new ModelAndView("/common/message").addObject("message", message).addObject("redirectUrl", redirectUrl);
	}	
	
	/**
	 * 升级租户权限.
	 * 系统功能升级后，需要对现有用户的权限进行升级（增加新的，删除不再存在的）
	 * 
	 * TODO 该方法仅做测试使用
	 *
	 * @param orderNo the order no
	 * @param request 请求对象
	 * @param response 响应对象
	 * @return 视图
	 * @throws Exception the exception
	 */
	@RequestMapping(value="upgradeTenantPermission.do")
	public ModelAndView upgradeTenantPermission(HttpServletRequest request, HttpServletResponse response) throws Exception
	{				
		String loginIp = WebUtils.getRemoteIp(request);		
			
		TenantTypeConfigManager manager = new TenantTypeConfigManager();	//重新加载配置，更新权限
		
		tenantBizService.doUpgradePermission(manager.getConfig());
		
		String message = "Upgrade success";
		String redirectUrl = "index";	//跳转到首页
		
		return new ModelAndView("/common/message").addObject("message", message).addObject("redirectUrl", redirectUrl);
	}
	
	/**
	 * 注销租户.
	 * 根据订单开通相应的租户
	 * TODO 该方法仅做测试使用
	 *
	 * @param orderNo the order no
	 * @param request 请求对象
	 * @param response 响应对象
	 * @return 视图
	 * @throws Exception the exception
	 */
	@RequestMapping(value="deactivateTenant.json")
	public ModelAndView deactivateTenant(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Locale locale = LoginUtils.getLocale(request);
		LoginUserVo loginUser = (LoginUserVo)LoginUtils.getLoginVo(request);
		TenantQuitBo tenantQuitBo = tenantBizService.doDeactivate(loginUser.getOpId().intValue(), loginUser.getTenantId());
				
		//清空当前登录用户的租户信息和租户权限
		loginUser.setTenant(null);
		Integer[] roleIds = tenantQuitBo.getRoleIds();
		for (Integer roleId : roleIds) {
			loginUser.removeRoleId(roleId);
		}		
		loginUser.removePermissions(tenantQuitBo.getPermissionCodes());
		
		return new ModelAndView("jsonView");
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
