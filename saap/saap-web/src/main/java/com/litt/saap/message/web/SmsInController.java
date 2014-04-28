package com.litt.saap.message.web;

import java.util.Date;

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
import com.litt.saap.message.po.SmsIn;
import com.litt.saap.message.service.ISmsInService;
import com.litt.core.dao.page.IPageList;
import com.litt.core.common.Utility;
import com.litt.core.util.DateUtils;
import com.litt.core.web.util.WebUtils;
import com.litt.core.dao.ql.PageParam;
import com.litt.core.exception.NotLoginException;
import com.litt.core.module.annotation.Func;
import com.litt.core.web.mvc.action.BaseController;

/**
 * 
 * SMS In controller.
 * <pre><b>Description：</b>
 *    SMS In Management
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">Bob.cai</a>
 * @since 2014-02-17
 * @version 1.0
 */
@Controller
public class SmsInController extends BaseController
{
	private final static Logger logger = LoggerFactory.getLogger(SmsInController.class);

	@Resource
	private ISmsInService smsInService;
	
	/**
	 * default page.
	 * 
	 * @param request the request
	 * @param respnose the respnose
	 * 
	 * @return ModelAndView
	 */	
	@Func(funcCode="04", moduleCode="8002", enableLog=false) 
	@RequestMapping 
	public ModelAndView index(WebRequest request, ModelMap modelMap) throws NotLoginException
	{	
		LoginUserVo loginUserVo = (LoginUserVo)super.getLoginVo();
		
		//get params from request
		String searchField = request.getParameter("s_searchField");
		String searchValue = request.getParameter("s_searchValue");
		
		Date startDate = Utility.parseDate(request.getParameter("startDate"));
		Date endDate = Utility.parseDate(request.getParameter("endDate"));
				
		//package the params
		PageParam pageParam = WebUtils.getPageParam(request);
		pageParam.addCond("tenantId", loginUserVo.getTenantId());
		pageParam.addCond(searchField, searchValue);
		pageParam.addCond("startDate", DateUtils.getStartOfDay(startDate));	
		pageParam.addCond("endDate", DateUtils.getEndOfDay(endDate));
		pageParam.addCond("isDeleted", false);	
		//Get page result
		IPageList pageList = smsInService.listPage(pageParam);		
		
		//return params to response
		modelMap.addAttribute("pageParam", pageParam);	
		modelMap.addAttribute("pageList", pageList);	
		
    return new ModelAndView("/message/smsIn/index");	
	
	} 	
	
	/**
	 * Add Page.
	 * 
	 * @return ModelAndView
	 */	
	@Func(funcCode="01", moduleCode="8002", enableLog=false)  
	@RequestMapping
	public ModelAndView add() 
	{        
  	return new ModelAndView("/message/smsIn/add");
  }
	
	/**
	 * Edit Page.
	 * 
	 * @param id 
	 * 
	 * @return ModelAndView
	 */
	@Func(funcCode="02", moduleCode="8002", enableLog=false)  
	@RequestMapping 
	public ModelAndView edit(@RequestParam Integer id) 
	{ 
		SmsIn smsIn = smsInService.load(id);		
        return new ModelAndView("/message/smsIn/edit").addObject("smsIn", smsIn);
    }	
    
	/**
	 * Show Page.
	 * 
	 * @param id 
	 * 
	 * @return ModelAndView
	 */
	@Func(funcCode="04", moduleCode="8002", enableLog=false)  
	@RequestMapping 
	public ModelAndView show(@RequestParam Integer id) 
	{ 
		SmsIn smsIn = smsInService.load(id);		
        return new ModelAndView("/message/smsIn/show").addObject("smsIn", smsIn);
    }   
    
    /**
	 * Save.
	 * @param request 
	 * @param modelMap
	 * @throws Exception 
	 */
	@Func(funcCode="01",moduleCode="8002")
	@RequestMapping 
	public void save(WebRequest request, ModelMap modelMap) throws Exception
	{	
		SmsIn smsIn = new SmsIn();
		BeanUtils.populate(smsIn, request.getParameterMap());			
		smsInService.save(smsIn);
	}
	
	/**
	 * Update.
	 * @param request 
	 * @param modelMap
	 * @throws Exception 
	 */
	@Func(funcCode="02",moduleCode="8002")
	@RequestMapping 
	public void update(WebRequest request, ModelMap modelMap) throws Exception
	{
		SmsIn smsIn = smsInService.load(Utility.parseInt(request.getParameter("id")));
		BeanUtils.populate(smsIn, request.getParameterMap());
		smsInService.update(smsIn);
	}
	
	/**
	 * Delete.
	 * @param id id
	 * @throws Exception 
	 */
	@Func(funcCode="03",moduleCode="8002")
	@RequestMapping 
	public void delete(@RequestParam Integer id) throws Exception
	{
		smsInService.delete(id);
	}

	/**
	 * Delete Batch.
	 * @param id id
	 * @throws Exception 
	 */
	@Func(funcCode="03",moduleCode="8002")
	@RequestMapping 
	public void deleteBatch(@RequestParam(value="ids[]") Integer[] ids) throws Exception
	{
		smsInService.deleteBatch(ids);
	}

	
}
