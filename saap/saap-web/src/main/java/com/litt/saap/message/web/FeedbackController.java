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
import com.litt.saap.core.web.util.LoginUtils;
import com.litt.saap.message.po.Feedback;
import com.litt.saap.message.service.IFeedbackService;
import com.litt.core.dao.page.IPageList;
import com.litt.core.common.Utility;
import com.litt.core.web.util.WebUtils;
import com.litt.core.dao.ql.PageParam;
import com.litt.core.exception.NotLoginException;
import com.litt.core.module.annotation.Func;
import com.litt.core.web.mvc.action.BaseController;

/**
 * 
 * Feedback controller.
 * <pre><b>Description：</b>
 *    Feedback Management.
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">Bob.cai</a>
 * @since 2014-06-26
 * @version 1.0
 */
@Controller
public class FeedbackController extends BaseController
{
	private final static Logger logger = LoggerFactory.getLogger(FeedbackController.class);

	@Resource
	private IFeedbackService feedbackService;

	
	/**
	 * default page.
	 * 
	 * @param request the request
	 * @param respnose the respnose
	 * 
	 * @return ModelAndView
	 */	
	@Func(funcCode="query", moduleCode="message.feedback", enableLog=false) 
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
		IPageList pageList = feedbackService.listPage(pageParam);		
		
		//return params to response
		modelMap.addAttribute("pageParam", pageParam);	
		modelMap.addAttribute("pageList", pageList);	
		
    return new ModelAndView("/message/feedback/index");	
	
	} 	
	
	/**
	 * Add Page.
	 * 
	 * @return ModelAndView
	 */	
	@Func(funcCode="add", moduleCode="message.feedback", enableLog=false)  
	@RequestMapping
	public ModelAndView add() 
	{        
  	return new ModelAndView("/message/feedback/add");
  }
	
	/**
	 * Edit Page.
	 * 
	 * @param id 
	 * 
	 * @return ModelAndView
	 */
	@Func(funcCode="edit", moduleCode="message.feedback", enableLog=false)  
	@RequestMapping 
	public ModelAndView edit(@RequestParam Integer id) 
	{ 
		Feedback feedback = feedbackService.load(id);		
        return new ModelAndView("/message/feedback/edit").addObject("feedback", feedback);
    }	
    
	/**
	 * Show Page.
	 * 
	 * @param id 
	 * 
	 * @return ModelAndView
	 */
	@Func(funcCode="query", moduleCode="message.feedback", enableLog=false)  
	@RequestMapping 
	public ModelAndView show(@RequestParam Integer id) 
	{ 
		Feedback feedback = feedbackService.load(id);		
    return new ModelAndView("/message/feedback/show").addObject("feedback", feedback);
  } 
	
	@RequestMapping 
  public ModelAndView getTopN(@RequestParam(defaultValue="8") int pageSize, @RequestParam(defaultValue="0") int pageIndex, @RequestParam(required=false) Integer type) 
  { 
	  int tenantId = LoginUtils.getTenantId();
    PageParam pageParam = new PageParam(pageIndex, pageSize, "createDatetime", "desc");
    pageParam.addCond("tenantId", tenantId);
    pageParam.addCond("type", type); 
	  
    IPageList pageList = feedbackService.listPage(pageParam); 
    return new ModelAndView("jsonView").addObject("feedbackList", pageList.getRsList());
  } 

	@Func(funcCode="add",moduleCode="message.feedback")
	@RequestMapping 
	public void save(@RequestParam(required=false) String moduleCode, @RequestParam String currentUrl, @RequestParam int type, @RequestParam String content) throws Exception
	{
	  LoginUserVo loginUserVo = (LoginUserVo)super.getLoginVo();
	  
	  feedbackService.save(loginUserVo.getTenantId(), loginUserVo.getOpId().intValue(), moduleCode, currentUrl, type, content);
	}

	
	/**
	 * Update.
	 * @param request 
	 * @param modelMap
	 * @throws Exception 
	 */
	@Func(funcCode="edit",moduleCode="message.feedback")
	@RequestMapping 
	public void update(WebRequest request, ModelMap modelMap) throws Exception
	{
		Feedback feedback = feedbackService.load(Utility.parseInt(request.getParameter("id")));
		BeanUtils.populate(feedback, request.getParameterMap());
		feedbackService.update(feedback);
	}
	
	/**
	 * Delete.
	 * @param id id
	 * @throws Exception 
	 */
	@Func(funcCode="delete",moduleCode="message.feedback")
	@RequestMapping 
	public void delete(@RequestParam Integer id) throws Exception
	{
		feedbackService.delete(id);
	}

	/**
	 * Delete Batch.
	 * @param id id
	 * @throws Exception 
	 */
	@Func(funcCode="delete",moduleCode="message.feedback")
	@RequestMapping 
	public void deleteBatch(@RequestParam(value="ids[]") Integer[] ids) throws Exception
	{
		feedbackService.deleteBatch(ids);
	}

}
