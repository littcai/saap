package com.litt.saap.common.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
 * @since 2013-9-11
 * @version 1.0
 */
@Controller
@RequestMapping("/rest") 
public class CommonRestService {
	
	@RequestMapping(value="/check/{id}", method = RequestMethod.GET) 
	public ModelAndView check(@PathVariable String id)
	{		
		return new ModelAndView("jsonView").addObject("id", id);
	}

}
