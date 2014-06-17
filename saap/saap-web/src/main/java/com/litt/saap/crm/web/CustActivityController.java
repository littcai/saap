package com.litt.saap.crm.web;

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
import com.litt.saap.core.web.util.LoginUtils;
import com.litt.saap.crm.po.CustActivity;
import com.litt.saap.crm.service.ICustActivityService;
import com.litt.core.dao.page.IPageList;
import com.litt.core.common.Utility;
import com.litt.core.web.util.WebUtils;
import com.litt.core.dao.ql.PageParam;
import com.litt.core.exception.NotLoginException;
import com.litt.core.module.annotation.Func;
import com.litt.core.web.mvc.action.BaseController;

/**
 * 
 * Activity controller.
 * <pre><b>Description：</b>
 *    Customer Activities Management
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">Bob.cai</a>
 * @since 2014-05-19
 * @version 1.0
 */
@Controller
public class CustActivityController extends BaseController
{
	private final static Logger logger = LoggerFactory.getLogger(CustActivityController.class);

	@Resource
	private ICustActivityService custActivityService;
	
	/**
	 * default page.
	 * 
	 * @param request the request
	 * @param respnose the respnose
	 * 
	 * @return ModelAndView
	 */	
	@Func(funcCode="04", moduleCode="1103", enableLog=false) 
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
		IPageList pageList = custActivityService.listPage(pageParam);		
		
		//return params to response
		modelMap.addAttribute("pageParam", pageParam);	
		modelMap.addAttribute("pageList", pageList);	
		
    return new ModelAndView("/crm/custActivity/index");	
	
	} 	
	
	/**
	 * Add Page.
	 * 
	 * @return ModelAndView
	 */	
	@Func(funcCode="01", moduleCode="1103", enableLog=false)  
	@RequestMapping
	public ModelAndView add() 
	{        
  	return new ModelAndView("/crm/custActivity/add");
  }
	
	/**
	 * Edit Page.
	 * 
	 * @param id 
	 * 
	 * @return ModelAndView
	 */
	@Func(funcCode="02", moduleCode="1103", enableLog=false)  
	@RequestMapping 
	public ModelAndView edit(@RequestParam Integer id) 
	{ 
		CustActivity custActivity = custActivityService.load(id);		
        return new ModelAndView("/crm/custActivity/edit").addObject("custActivity", custActivity);
    }	
    
	/**
	 * Show Page.
	 * 
	 * @param id 
	 * 
	 * @return ModelAndView
	 */
	@Func(funcCode="04", moduleCode="1103", enableLog=false)  
	@RequestMapping 
	public ModelAndView show(@RequestParam Integer id) 
	{ 
		CustActivity custActivity = custActivityService.load(id);		
        return new ModelAndView("/crm/custActivity/show").addObject("custActivity", custActivity);
    }   
    
    /**
	 * Save.
	 * @param request 
	 * @param modelMap
	 * @throws Exception 
	 */
	@Func(funcCode="01",moduleCode="1103")
	@RequestMapping 
	public void save(WebRequest request, ModelMap modelMap) throws Exception
	{	
		int tenantId = LoginUtils.getTenantId();
		int userId = LoginUtils.getLoginOpId().intValue();
		
		CustActivity custActivity = new CustActivity();
		BeanUtils.populate(custActivity, request.getParameterMap());
		
		custActivity.setTenantId(tenantId);
		custActivity.setCreateBy(userId);
		
		custActivityService.save(custActivity);
	}
	
	/**
	 * Update.
	 * @param request 
	 * @param modelMap
	 * @throws Exception 
	 */
	@Func(funcCode="02",moduleCode="1103")
	@RequestMapping 
	public void update(WebRequest request, ModelMap modelMap) throws Exception
	{
		int userId = LoginUtils.getLoginOpId().intValue();
		
		CustActivity custActivity = custActivityService.load(Utility.parseInt(request.getParameter("id")));
		BeanUtils.populate(custActivity, request.getParameterMap());
		custActivityService.update(custActivity);
	}
	
	/**
	 * Delete.
	 * @param id id
	 * @throws Exception 
	 */
	@Func(funcCode="03",moduleCode="1103")
	@RequestMapping 
	public void delete(@RequestParam Integer id) throws Exception
	{
		custActivityService.delete(id);
	}

	/**
	 * Delete Batch.
	 * @param id id
	 * @throws Exception 
	 */
	@Func(funcCode="03",moduleCode="1103")
	@RequestMapping 
	public void deleteBatch(@RequestParam(value="ids[]") Integer[] ids) throws Exception
	{
		custActivityService.deleteBatch(ids);
	}

}
