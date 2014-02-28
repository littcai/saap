package com.litt.saap.system.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.litt.core.module.annotation.Func;
import com.litt.core.web.mvc.action.BaseController;
import com.litt.saap.core.web.util.LoginUtils;
import com.litt.saap.system.po.Tenant;
import com.litt.saap.system.service.ITenantService;

/**
 * TenantController.
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
 * @since 2014年2月27日
 * @version 1.0
 */
@Controller
public class TenantController extends BaseController 
{
	private final static Logger logger = LoggerFactory.getLogger(TenantController.class);
	
	@Resource
	private ITenantService tenantService;
	
	@Func(funcCode="04",moduleCode="9001", enableLog=false)
	@RequestMapping
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response)
	{
		Tenant tenant = tenantService.load(LoginUtils.getTenantId());
		
		return new ModelAndView("/system/tenant/index").addObject("tenant", tenant);
	}
	

}
