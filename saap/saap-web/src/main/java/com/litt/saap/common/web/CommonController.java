package com.litt.saap.common.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.context.Theme;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.litt.saap.core.web.util.LoginUtils;

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
	
	@RequestMapping(value="index")
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response)
	{	
		if(LoginUtils.isUserLogin(request))
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
		return new ModelAndView("/theme/"+theme.getName()+"/main");
	}

}
