package com.litt.saap.common.web;

import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.context.Theme;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.litt.core.exception.NotLoginException;
import com.litt.core.shield.vo.ILoginVo;
import com.litt.saap.core.web.util.LoginUtils;
import com.litt.saap.system.po.Menu;
import com.litt.saap.system.service.IMenuService;

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
 * @since 2013-8-14
 * @version 1.0
 */
@Controller
@RequestMapping(value="/")
public class CommonController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory.getLogger(CommonController.class);	
	
	@Resource
	private IMenuService menuService;	
	
	@RequestMapping(value="index")
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response)
	{	
		if(LoginUtils.isAutoLogin(request) && LoginUtils.isUserLogin(request))	//自动登录并且会话已登录，则跳转到主页
		{
			return new ModelAndView("redirect:/main.do");
		}
		else {
			return new ModelAndView("redirect:/login/index.do");
		}		
	}
	
	@RequestMapping(value="main.do")
	public ModelAndView main(HttpServletRequest request)
	{
		Theme theme = LoginUtils.getTheme(request);
		return new ModelAndView("/common/main");
	}
	
	@RequestMapping(value="message.do")
	public ModelAndView message(HttpServletRequest request)
	{
		return new ModelAndView("/common/message");
	}
	
	@RequestMapping(value="menu.do")
	public ModelAndView menu(@RequestParam String menuCode)
	{
		Menu menu = menuService.load(menuCode);
		String url = menu.getMenuUrl();
		return new ModelAndView("redirect:"+url);
	}
	
	/**
	 * 接收用户反馈的系统异常.
	 * @param comment 用户填写的异常发生时的场景
	 * @param message 异常消息
	 * @return
	 */
	@RequestMapping(value="sendError.json")
	public ModelAndView sendError(@RequestParam String errorCode, @RequestParam String url,
			@RequestParam(required=false) String comment, @RequestParam(required=false) String message)
	{
		String loginId = "";
		try 
		{
			ILoginVo loginUserVo = LoginUtils.getLoginVo();
			loginId = loginUserVo.getLoginId();
		} catch (NotLoginException e) {
			
		}
		logger.error("User response for {} error on page:{}, user:{}, comment:{}, message:{}", new Object[]{errorCode, url, loginId, comment, message});
		
		return new ModelAndView("jsonView");
	}

}
