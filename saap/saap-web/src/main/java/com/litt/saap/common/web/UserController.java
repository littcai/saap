package com.litt.saap.common.web;

import java.util.List;
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

import com.litt.core.common.Utility;
import com.litt.core.exception.BusiCodeException;
import com.litt.core.shield.vo.ILoginVo;
import com.litt.core.util.ValidateUtils;
import com.litt.core.web.util.WebUtils;
import com.litt.saap.common.vo.LoginUserVo;
import com.litt.saap.core.common.SaapConstants.IsolatedMode;
import com.litt.saap.core.common.SaapConstants.TenantOrderType;
import com.litt.saap.core.module.tenant.config.TenantDefConfig;
import com.litt.saap.core.module.tenant.config.TenantTypeConfigManager;
import com.litt.saap.core.web.util.LoginUtils;
import com.litt.saap.system.biz.ITenantBizService;
import com.litt.saap.system.biz.IUserBizService;
import com.litt.saap.system.bo.TenantActiveBo;
import com.litt.saap.system.bo.TenantQuitBo;
import com.litt.saap.system.po.Role;
import com.litt.saap.system.po.TenantOrder;
import com.litt.saap.system.po.UserInfo;
import com.litt.saap.system.po.UserState;
import com.litt.saap.system.service.IRoleService;
import com.litt.saap.system.service.ITenantOrderService;
import com.litt.saap.system.service.ITenantService;
import com.litt.saap.system.service.IUserInfoService;
import com.litt.saap.system.vo.TenantVo;

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
 * @since 2014-1-17
 * @version 1.0
 */
@Controller
@RequestMapping(value="/user")
public class UserController {
	
	@Resource
	private IUserInfoService userInfoService;
	@Resource
	private IUserBizService userBizService;
	@Resource
	private ITenantService tenantService;
	@Resource
	private ITenantBizService tenantBizService;
	@Resource
	private ITenantOrderService tenantOrderService;
	@Resource
	private IRoleService roleService;
	@Resource
	private MessageSource messageSource;
	
	/**
	 * 修改密码.
	 *
	 * @param id 找回密码ID
	 * @param password 新密码
	 * @param request the request
	 * @param response the response
	 * @return the model and view
	 * @throws Exception the exception
	 */
	@RequestMapping(value="updatePassword.json")
	public ModelAndView updatePassword(@RequestParam String newPassword, @RequestParam String rpassword
			, HttpServletRequest request, HttpServletResponse response) throws Exception
	{				
		String loginIp = WebUtils.getRemoteIp(request);
		LoginUserVo loginUser = (LoginUserVo)LoginUtils.getLoginVo(request);
		//从请求中获取查询条件	
		userBizService.updatePassword(loginUser.getOpId().intValue(), newPassword, loginIp);
		
		return new ModelAndView("jsonView");
	}
	
	/**
	 * 邀请用户.
	 *
	 * @param id 找回密码ID
	 * @param password 新密码
	 * @param request the request
	 * @param response the response
	 * @return the model and view
	 * @throws Exception the exception
	 */
	@RequestMapping(value="invite.do")
	public ModelAndView toInvite(HttpServletRequest request, HttpServletResponse response) throws Exception
	{			
		int tenantId = LoginUtils.getTenantId();		
		List<Role> roleList = roleService.listByTenant(tenantId);
		
		return new ModelAndView("/common/invite").addObject("roleList", roleList);
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
	@RequestMapping(value="invite.json")
	public ModelAndView invite(@RequestParam(required=false) String[] emails, @RequestParam(required=false) Integer[] roleIds
			, @RequestParam(required=false) String comment
			, HttpServletRequest request, HttpServletResponse response) throws Exception
	{				
		String loginIp = WebUtils.getRemoteIp(request);
		Integer inviterUserId = LoginUtils.getLoginOpId().intValue();		
		
		Locale locale = LoginUtils.getLocale(request);
		TimeZone timeZone = TimeZone.getDefault();
		Theme theme = LoginUtils.getTheme(request);
		//从请求中获取查询条件	
		if(emails==null || roleIds==null)
		{
			throw new BusiCodeException("invite.error.inputEmpty");
		}
		for (int i=0; i< emails.length; i++) {
			String email = emails[i];
			Integer targetRoleId = roleIds[i];
			if(!ValidateUtils.isEmpty(email))
				userBizService.doInvite(inviterUserId, targetRoleId, email, comment, locale, timeZone, theme);
		}
		
		return new ModelAndView("jsonView");
	}
	
	/**
	 * 退出租户.
	 * 根据订单开通相应的租户
	 * TODO 该方法仅做测试使用
	 *
	 * @param orderNo the order no
	 * @param request 请求对象
	 * @param response 响应对象
	 * @return 视图
	 * @throws Exception the exception
	 */
	@RequestMapping(value="quitTenant.json")
	public ModelAndView quitTenant(HttpServletRequest request, HttpServletResponse response) throws Exception
	{				
		String loginIp = WebUtils.getRemoteIp(request);
		
		Locale locale = LoginUtils.getLocale(request);
		
		LoginUserVo loginUser = (LoginUserVo)LoginUtils.getLoginVo(request);	
		int userId = loginUser.getOpId().intValue();
		//退出租户	
		TenantQuitBo tenantQuitBo = tenantBizService.doQuit(loginUser.getTenantId(), userId);
		
		//如果当前登录用户是开通租户的用户，则立即更新登录用户的租户权限，必须再重新登录
		if(loginUser.getOpId().equals(userId))
		{
			loginUser.setTenant(null);
			Integer[] roleIds = tenantQuitBo.getRoleIds();
			for (Integer roleId : roleIds) {
				loginUser.removeRoleId(roleId);
			}
			loginUser.removePermissions(tenantQuitBo.getPermissionCodes());
		}
		
		//HttpSession session = request.getSession();
		//LoginUtils.setLoginSession(session, loginUser);
		//跳转到消息页面，显示激活成功的信息
		String message = messageSource.getMessage("tenant.func.quit.success", new Object[]{tenantQuitBo.getTenant().getTenantAlias()}, locale);
		String redirectUrl = "index";	//跳转到首页
		
		return new ModelAndView("jsonView").addObject("message", message).addObject("redirectUrl", redirectUrl);
	}
	
	/**
	 * 跳转到用户档案.
	 *
	 * @param request the request
	 * @param response the response
	 * @return the model and view
	 * @throws Exception the exception
	 */
	@RequestMapping(value="profile.do")
	public ModelAndView profile() throws Exception
	{				
		LoginUserVo loginVo = (LoginUserVo)LoginUtils.getLoginVo();	
		Locale locale = loginVo.toLocale();
		
		UserInfo userInfo = userInfoService.load(loginVo.getOpId().intValue());
		UserState userState = userInfoService.loadUserState(userInfo.getId());
		
		TenantVo currentTenant = tenantService.findById(loginVo.getTenantId());
		
		List<TenantVo> tenantList = tenantService.findByMemberId(loginVo.getOpId().intValue()); 
		
		return new ModelAndView("/common/profile")
					.addObject("userInfo", userInfo)
					.addObject("userState", userState)
					.addObject("currentTenant", currentTenant)
					.addObject("tenantList", tenantList);		
	}
	
	/**
	 * 工作空间价格表.
	 *
	 * @return the model and view
	 * @throws Exception the exception
	 */
	@RequestMapping(value="priceTable.do")
  public ModelAndView priceTable() throws Exception
  {           
    TenantDefConfig[] tenantDefList = TenantTypeConfigManager.getInstance().getConfig().getTenantDefList();    
    
    return new ModelAndView("/common/priceTable")
          .addObject("tenantDefList", tenantDefList);   
  }
	
  @RequestMapping(value="order.do")
  public ModelAndView order(@RequestParam String tenantTypeCode) throws Exception
  {           
    TenantDefConfig tenantDef = TenantTypeConfigManager.getInstance().getTenantDefConfig(tenantTypeCode);
    
    return new ModelAndView("/common/order")
          .addObject("tenantDef", tenantDef);   
  }
  
  @RequestMapping(value="saveOrder.json")
  public ModelAndView saveOrder(@RequestParam String tenantTypeCode
      , @RequestParam String tenantCode, @RequestParam String tenantAlias, @RequestParam int quantity
      , HttpServletRequest request) throws Exception
  {          
	  LoginUserVo loginUser = (LoginUserVo)LoginUtils.getLoginVo();	
	  int userId = LoginUtils.getLoginOpId().intValue();
	  
	TenantDefConfig tenantDef = TenantTypeConfigManager.getInstance().getTenantDefConfig(tenantTypeCode);
    //TODO 隔离级别暂时为共享，后续可在页面增加选项，同时价格上需要区分
    Integer orderId = tenantOrderService.save(TenantOrderType.NEW, tenantCode, tenantAlias, tenantTypeCode, IsolatedMode.NO_ISOLATION, tenantDef.getPrice(), quantity, userId);
    TenantOrder tenantOrder = tenantOrderService.load(orderId);
    
    String orderNo = tenantOrder.getOrderNo();
    //FIXME 模拟付费
    tenantOrderService.doPaid(orderNo, "alipay");
    
    //FIXME 激活租户
	TenantActiveBo tenantActiveBo = tenantBizService.doActivate(orderNo, userId, loginUser.toLocale());		
	if(loginUser.getTenant()==null)	//如果当前用户尚未加入某个租户空间，则以开通的为准
	{
		//如果当前登录用户是开通租户的用户，则立即更新登录用户的租户权限，不必再重新登录
		if(loginUser.getOpId().intValue()==userId)
		{
			loginUser.setTenant(tenantActiveBo.getTenant());
			loginUser.addRoleId(tenantActiveBo.getRoleId());
			loginUser.addPermissions(tenantActiveBo.getPermissionCodes());
		}
		LoginUtils.setLoginSession(request.getSession(), loginUser);
	}
    return new ModelAndView("jsonView");   
  }
	
	/**
	 * 更新登录用户信息.
	 *
	 * @param request the request
	 * @param response the response
	 * @return the model and view
	 * @throws Exception the exception
	 */
	@RequestMapping(value="update.json")
	public ModelAndView update(HttpServletRequest request, HttpServletResponse response) throws Exception
	{						
		LoginUserVo loginUser = (LoginUserVo)LoginUtils.getLoginVo(request);
		//从请求中获取查询条件
		String userName = request.getParameter("userName");
		String nickName = request.getParameter("nickName");
		int gender = Utility.parseInt(request.getParameter("gender"));
		String email = request.getParameter("email");
		String mobile = request.getParameter("mobile");
		
		String locale = request.getParameter("locale");
		int timezone = Utility.parseInt(request.getParameter("timezone"));
		String theme = request.getParameter("theme");
		
		userBizService.update(loginUser.getOpId().intValue(), userName, nickName, gender, email, mobile, locale, timezone, theme);
		//更新session中缓存
		loginUser.setOpName(userName);
		loginUser.setLocale(locale);
		loginUser.setTimezone(timezone);
		loginUser.setTheme(theme);
		
		loginUser.setNickName(nickName);
		loginUser.setGender(gender);
		loginUser.setEmail(email);
		loginUser.setMobile(mobile);
		//更新到session
		LoginUtils.setLoginSession(request.getSession(), loginUser);
		
		//如果语言、时区、主题变化，需要刷新页面
		LoginUtils.changeLocale(locale, request, response);
		LoginUtils.changeTheme(theme, request);
		
		return new ModelAndView("jsonView");
	}
	
	/**
	 * 切换租户空间.
	 *
	 * @param request the request
	 * @param response the response
	 * @return the model and view
	 * @throws Exception the exception
	 */
	@RequestMapping(value="switchTenant.do")
	public ModelAndView toSwitchTenant(HttpServletRequest request, HttpServletResponse response) throws Exception
	{			
		Locale locale = LoginUtils.getLocale(request);		
		ILoginVo loginVo = LoginUtils.getLoginVo();		
		List<TenantVo> tenantList = tenantService.findByMemberId(loginVo.getOpId().intValue()); 
		
		return new ModelAndView("/common/switchTenant").addObject("tenantList", tenantList);		
	}
	
	/**
	 * 切换租户空间.
	 *
	 * @param id 找回密码ID
	 * @param password 新密码
	 * @param request the request
	 * @param response the response
	 * @return the model and view
	 * @throws Exception the exception
	 */
	@RequestMapping(value="switchTenant.json")
	public ModelAndView switchTenant(@RequestParam Integer tenantId
			, HttpServletRequest request, HttpServletResponse response) throws Exception
	{						
		LoginUserVo loginUser = (LoginUserVo)LoginUtils.getLoginVo(request);
		//从请求中获取查询条件
		userBizService.doSwitchCurrentTenant(loginUser, tenantId);
		
		LoginUtils.setLoginSession(request.getSession(), loginUser);
		
		return new ModelAndView("jsonView");
	}
	
	/**
	 * 登录用户刷新.
	 *
	 * @param request the request
	 * @param response the response
	 * @return the model and view
	 * @throws Exception the exception
	 */
	@RequestMapping(value="reload.json")
	public ModelAndView reload(HttpServletRequest request, HttpServletResponse response) throws Exception
	{						
		LoginUserVo loginUser = (LoginUserVo)LoginUtils.getLoginVo(request);
		
		
		LoginUtils.setLoginSession(request.getSession(), loginUser);
		
		return new ModelAndView("jsonView");
	}

}
