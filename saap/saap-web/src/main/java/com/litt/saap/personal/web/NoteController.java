package com.litt.saap.personal.web;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.context.Theme;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.litt.core.web.mvc.action.BaseController;
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
 * @since 2013-9-10
 * @version 1.0
 */
@Controller
public class NoteController extends BaseController {
	
	@RequestMapping
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response)
	{
		return new ModelAndView("/personal/note/index");		
	}
}
