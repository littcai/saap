package com.litt.saap.core.web.filter;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import com.litt.core.common.BeanManager;
import com.litt.core.common.CoreConstants;
import com.litt.core.exception.BusiException;
import com.litt.core.exception.NotLoginException;
import com.litt.core.shield.vo.ILoginVo;
import com.litt.core.util.ValidateUtils;
import com.litt.core.web.util.CookieUtils;
import com.litt.core.web.util.WebUtils;
import com.litt.saap.core.web.util.LoginUtils;
import com.litt.saap.system.biz.IUserBizService;

/**
 * 用户自动登录过滤器.
 * 
 * <pre><b>描述：</b>
 *    根据cookie信息实现用户的自动登录
 * 注：该过滤器需放在OperatorHttpSessionContextIntegrationFilter之前，优先处理自动登录；
 * 注2：需依赖userBizService的实现  
 * </pre>
 * 
 * <pre><b>修改记录：</b>
 *    
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">蔡源</a>
 * @since 2013-9-29
 * @version 1.0
 */
public class UserAutoLoginFilter extends OncePerRequestFilter implements Filter {

	/** 日志工具. */
    private static final Logger logger = LoggerFactory.getLogger(UserAutoLoginFilter.class);    
	
	/* (non-Javadoc)
	 * @see org.springframework.web.filter.OncePerRequestFilter#doFilterInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();		
		
		//先判断操作员是否已登录，已登录则不尝试自动登录			
		if(!LoginUtils.isUserLogin(request))
		{			
			
			String autoLoginToken = LoginUtils.getAutoLoginToken(request);
			if(!ValidateUtils.isEmpty(autoLoginToken))	//检查是否存在自动登录信息
			{
				if(logger.isDebugEnabled())
				{
					logger.debug("try to auto login...");
				}
				//先校验证书
//				try
//				{
//					LicenseManager.validateLicense();
//				}
//				catch (LicenseException e)
//				{
//					//throw new ServletException("授权证书已失效！");
//					//以下方法用来失败后自动跳转至更新证书页面
//					response.reset();
//					response.sendRedirect(request.getContextPath()+"/install/license/upgrade.jsp");
//					return;
//				}		
				
				//由于存在Ajax等页面异步调用，请求与Web页面请求同时到达服务器，在第一个请求未处理完成时产生重复登录的问题
				Cookie lang = CookieUtils.getCookie(request, CoreConstants.COOKIE_LOCALE);	//国际化语言
				
				String loginIp =  WebUtils.getRemoteIp(request);
				try
				{
					Locale locale = LoginUtils.getLocale(lang.getValue());
					ILoginVo loginVo = this.doAutoLogin(autoLoginToken, loginIp, locale);						
					
					String requestWith = request.getHeader("x-requested-with");
					if("XMLHttpRequest".equals(requestWith))
					{
						logger.debug("Request from Ajax.");
						session.setAttribute("x-requested-with", "XMLHttpRequest");
					}
					LoginUtils.setLoginSession(session, loginVo);
					//FIXME 自动登录无法处理Locale，因为这个时候还没有被spring接管
					//LoginUtils.changeLocale(lang.getValue(), request, response);
				}
				catch (Exception e)
				{
					logger.error("Auto login failed.", e);
					LoginUtils.removeAutoLoginCookie(response);	//清理COOKIE					
				}		
			}
		}	
		else 
		{
			try 
			{
				ILoginVo sessionLoginVo = LoginUtils.getLoginVo(request);
				
				/*
				 * 用户已登录，检查是否需要强制用户下线
				 * 强制用户下线情况：
				 * 1、在线用户管理中操作了强制下线
				 * 2、用户管理中注销或删除了用户信息
				 */
				if(sessionLoginVo.isForceOffline())
				{
					LoginUtils.removeLoginSession(session);
					LoginUtils.removeAutoLoginCookie(response);
				}			
			} catch (NotLoginException e) {
				
			}
			
		}
		super.doFilter(request, response, filterChain);
	}
	
	/**
	 * 执行登录操作.
	 *
	 * @param autoLoginToken 自动登录令牌
	 * @param loginIp 登录IP
	 * @param locale 语言
	 * @return 登录用户对象
	 * @throws BusiException 登录失败时抛出的异常
	 */
	private ILoginVo doAutoLogin(String autoLoginToken, String loginIp, Locale locale) throws BusiException
	{
		IUserBizService service = BeanManager.getBean("userBizService", IUserBizService.class);
		return service.doAutoLogin(autoLoginToken, loginIp, locale);
	}

}
