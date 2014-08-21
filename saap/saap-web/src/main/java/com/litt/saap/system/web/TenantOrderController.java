package com.litt.saap.system.web;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.litt.saap.common.vo.LoginUserVo;
import com.litt.saap.core.common.SaapConstants;
import com.litt.saap.core.common.SaapConstants.IsolatedMode;
import com.litt.saap.core.web.util.LoginUtils;
import com.litt.saap.system.po.TenantOrder;
import com.litt.saap.system.service.ITenantOrderService;
import com.litt.core.dao.page.IPageList;
import com.litt.core.common.Utility;
import com.litt.core.web.util.WebUtils;
import com.litt.core.dao.ql.PageParam;
import com.litt.core.exception.NotLoginException;
import com.litt.core.module.annotation.Func;
import com.litt.core.web.mvc.action.BaseController;

/**
 * 
 * Order controller.
 * <pre><b>Description：</b>
 *    Order Management
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">Bob.cai</a>
 * @since 2014-05-14
 * @version 1.0
 */
@Controller
public class TenantOrderController extends BaseController
{
	private final static Logger logger = LoggerFactory.getLogger(TenantOrderController.class);

	@Resource
	private ITenantOrderService tenantOrderService;
	
	/**
	 * default page.
	 * 
	 * @param request the request
	 * @param respnose the respnose
	 * 
	 * @return ModelAndView
	 */	
	@Func(funcCode="query", moduleCode="tenant.tenantOrder", enableLog=false, enablePermission=false) 
	@RequestMapping 
	public ModelAndView index(WebRequest request, ModelMap modelMap) throws NotLoginException
	{	
		LoginUserVo loginUserVo = (LoginUserVo)super.getLoginVo();
		
		//get params from request
		String code = request.getParameter("code");
		String name = request.getParameter("name");
				
		//package the params
		PageParam pageParam = WebUtils.getPageParam(request);
		pageParam.addCond("tenantId", loginUserVo.getTenantId());
		pageParam.addCond("code", code);	
		pageParam.addCond("name", name);	
		pageParam.addCond("isDeleted", false);	
		//Get page result
		IPageList pageList = tenantOrderService.listPage(pageParam);		
		
		//return params to response
		modelMap.addAttribute("pageParam", pageParam);	
		modelMap.addAttribute("pageList", pageList);	
		
    return new ModelAndView("/system/tenantOrder/index");	
	
	} 	
	
	/**
	 * Add Page.
	 * 
	 * @return ModelAndView
	 */	
	@Func(funcCode="add", moduleCode="tenant.tenantOrder", enableLog=false, enablePermission=false)  
	@RequestMapping
	public ModelAndView add() 
	{        
  	return new ModelAndView("/system/tenantOrder/add");
  }
	
	/**
	 * Edit Page.
	 * 
	 * @param id 
	 * 
	 * @return ModelAndView
	 */
	@Func(funcCode="edit", moduleCode="tenant.tenantOrder", enableLog=false, enablePermission=false)  
	@RequestMapping 
	public ModelAndView edit(@RequestParam Integer id) 
	{ 
		TenantOrder tenantOrder = tenantOrderService.load(id);		
        return new ModelAndView("/system/tenantOrder/edit").addObject("tenantOrder", tenantOrder);
    }	
    
	/**
	 * Show Page.
	 * 
	 * @param id 
	 * 
	 * @return ModelAndView
	 */
	@Func(funcCode="query", moduleCode="tenant.tenantOrder", enableLog=false, enablePermission=false)  
	@RequestMapping 
	public ModelAndView show(@RequestParam Integer id) 
	{ 
		TenantOrder tenantOrder = tenantOrderService.load(id);		
        return new ModelAndView("/system/tenantOrder/show").addObject("tenantOrder", tenantOrder);
    }   
    
    /**
	 * Save.
	 * @param request 
	 * @param modelMap
	 * @throws Exception 
	 */
	@Func(funcCode="add",moduleCode="tenant.tenantOrder", enablePermission=false)
	@RequestMapping 
	public ModelAndView save(@RequestParam String tenantCode, @RequestParam String tenantAlias, @RequestParam String bagCode
			, @RequestParam BigDecimal price, @RequestParam int quantity) throws Exception
	{	
		int isolatedMode = IsolatedMode.NO_ISOLATION;	//TODO 目前均不隔离，需要具体实现
		int createBy = LoginUtils.getLoginOpId().intValue();
		
		Integer tenantOrderId = tenantOrderService.save(SaapConstants.TenantOrderType.NEW, tenantCode, tenantAlias, bagCode, isolatedMode, price, quantity, createBy);
		TenantOrder tenantOrder = tenantOrderService.load(tenantOrderId);
		return new ModelAndView("jsonView").addObject("tenantOrder", tenantOrder);
	}
	
	/**
	 * Update.
	 * @param request 
	 * @param modelMap
	 * @throws Exception 
	 */
	@Func(funcCode="edit",moduleCode="tenant.tenantOrder", enablePermission=false)
	@RequestMapping 
	public void update(WebRequest request, ModelMap modelMap) throws Exception
	{
		TenantOrder tenantOrder = tenantOrderService.load(Utility.parseInt(request.getParameter("id")));
		BeanUtils.populate(tenantOrder, request.getParameterMap());
		tenantOrderService.update(tenantOrder);
	}
	
	/**
	 * Delete.
	 * @param id id
	 * @throws Exception 
	 */
	@Func(funcCode="delete",moduleCode="tenant.tenantOrder", enablePermission=false)
	@RequestMapping 
	public void delete(@RequestParam Integer id) throws Exception
	{
		tenantOrderService.delete(id);
	}

	/**
	 * Delete Batch.
	 * @param id id
	 * @throws Exception 
	 */
	@Func(funcCode="delete",moduleCode="tenant.tenantOrder", enablePermission=false)
	@RequestMapping 
	public void deleteBatch(@RequestParam(value="ids[]") Integer[] ids) throws Exception
	{
		tenantOrderService.deleteBatch(ids);
	}

}
