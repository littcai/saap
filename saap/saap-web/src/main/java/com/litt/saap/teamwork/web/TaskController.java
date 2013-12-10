package com.litt.saap.teamwork.web;

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
import com.litt.saap.teamwork.po.Task;
import com.litt.saap.teamwork.service.ITaskService;

import com.litt.core.dao.page.IPageList;
import com.litt.core.common.Utility;
import com.litt.core.web.util.WebUtils;
import com.litt.core.dao.ql.PageParam;
import com.litt.core.exception.NotLoginException;
import com.litt.core.module.annotation.Func;
import com.litt.core.web.mvc.action.BaseController;

/**
 * 
 * Task controller.
 * <pre><b>Description：</b>
 *    Task Management
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">Bob.cai</a>
 * @since 2013-12-04
 * @version 1.0
 */
@Controller
public class TaskController extends BaseController
{
	private final static Logger logger = LoggerFactory.getLogger(TaskController.class);

	@Resource
	private ITaskService taskService;
	
	/**
	 * default page.
	 * 
	 * @param request the request
	 * @param respnose the respnose
	 * 
	 * @return ModelAndView
	 */	
	@Func(funcCode="04", moduleCode="1202", enableLog=false) 
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
		IPageList pageList = taskService.listPage(pageParam);		
		
		//return params to response
		modelMap.addAttribute("pageParam", pageParam);	
		modelMap.addAttribute("pageList", pageList);	
		
    return new ModelAndView("/teamwork/task/index");	
	
	} 	
	
	/**
	 * Add Page.
	 * 
	 * @return ModelAndView
	 */	
	@Func(funcCode="01", moduleCode="1202", enableLog=false)  
	@RequestMapping
	public ModelAndView add() 
	{        
  	return new ModelAndView("/teamwork/task/add");
  }
	
	/**
	 * Edit Page.
	 * 
	 * @param id 
	 * 
	 * @return ModelAndView
	 */
	@Func(funcCode="02", moduleCode="1202", enableLog=false)  
	@RequestMapping 
	public ModelAndView edit(@RequestParam Integer id) 
	{ 
		Task task = taskService.load(id);		
        return new ModelAndView("/teamwork/task/edit").addObject("task", task);
    }	
    
	/**
	 * Show Page.
	 * 
	 * @param id 
	 * 
	 * @return ModelAndView
	 */
	@Func(funcCode="04", moduleCode="1202", enableLog=false)  
	@RequestMapping 
	public ModelAndView show(@RequestParam Integer id) 
	{ 
		Task task = taskService.load(id);		
        return new ModelAndView("/teamwork/task/show").addObject("task", task);
    }   
    
    /**
	 * Save.
	 * @param request 
	 * @param modelMap
	 * @throws Exception 
	 */
	@Func(funcCode="01",moduleCode="1202")
	@RequestMapping 
	public void save(WebRequest request, ModelMap modelMap) throws Exception
	{	
		Task task = new Task();
		BeanUtils.populate(task, request.getParameterMap());			
		taskService.save(task);
	}
	
	/**
	 * Update.
	 * @param request 
	 * @param modelMap
	 * @throws Exception 
	 */
	@Func(funcCode="02",moduleCode="1202")
	@RequestMapping 
	public void update(WebRequest request, ModelMap modelMap) throws Exception
	{
		Task task = taskService.load(Utility.parseInt(request.getParameter("id")));
		BeanUtils.populate(task, request.getParameterMap());
		taskService.update(task);
	}
	
	/**
	 * Delete.
	 * @param id id
	 * @throws Exception 
	 */
	@Func(funcCode="03",moduleCode="1202")
	@RequestMapping 
	public void delete(@RequestParam Integer id) throws Exception
	{
		taskService.delete(id);
	}


}