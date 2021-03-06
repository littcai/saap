package com.litt.saap.personal.web;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.litt.core.common.Utility;
import com.litt.core.dao.page.IPageList;
import com.litt.core.dao.ql.PageParam;
import com.litt.core.exception.NotLoginException;
import com.litt.core.module.annotation.Func;
import com.litt.core.web.mvc.action.BaseController;
import com.litt.core.web.util.WebUtils;
import com.litt.saap.common.vo.LoginUserVo;
import com.litt.saap.system.po.UserComment;
import com.litt.saap.system.service.IUserCommentService;

/**
 * 
 * Comment controller.
 * <pre><b>Description：</b>
 *    User Coomment
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">Bob.cai</a>
 * @since 2013-11-19
 * @version 1.0
 */
@Controller
public class UserCommentController extends BaseController
{
	private final static Logger logger = LoggerFactory.getLogger(UserCommentController.class);

	@Resource
	private IUserCommentService userCommentService;
	
	/**
	 * default page.
	 * 
	 * @param request the request
	 * @param respnose the respnose
	 * 
	 * @return ModelAndView
	 */	
	@Func(funcCode="query", moduleCode="personal.userComment", enableLog=false) 
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
		IPageList pageList = userCommentService.listPage(pageParam);		
		
		//return params to response
		modelMap.addAttribute("pageParam", pageParam);	
		modelMap.addAttribute("pageList", pageList);	
		
    return new ModelAndView("/personal/userComment/index");	
	
	} 	
	
	/**
	 * Add Page.
	 * 
	 * @return ModelAndView
	 */	
	@Func(funcCode="add", moduleCode="personal.userComment", enableLog=false)  
	@RequestMapping
	public ModelAndView add() 
	{        
  	return new ModelAndView("/personal/userComment/add");
  }
	
	/**
	 * Edit Page.
	 * 
	 * @param id 
	 * 
	 * @return ModelAndView
	 */
	@Func(funcCode="edit", moduleCode="personal.userComment", enableLog=false)  
	@RequestMapping 
	public ModelAndView edit(@RequestParam Integer id) 
	{ 
		UserComment userComment = userCommentService.load(id);		
        return new ModelAndView("/personal/userComment/edit").addObject("userComment", userComment);
    }	
    
	/**
	 * Show Page.
	 * 
	 * @param id 
	 * 
	 * @return ModelAndView
	 */
	@Func(funcCode="query", moduleCode="personal.userComment", enableLog=false)  
	@RequestMapping 
	public ModelAndView show(@RequestParam Integer id) 
	{ 
		UserComment userComment = userCommentService.load(id);		
        return new ModelAndView("/personal/userComment/show").addObject("userComment", userComment);
    }   
    
    /**
	 * Save.
	 * @param request 
	 * @param modelMap
	 * @throws Exception 
	 */
	@Func(funcCode="add",moduleCode="personal.userComment")
	@RequestMapping 
	public void save(WebRequest request, ModelMap modelMap) throws Exception
	{	
		UserComment userComment = new UserComment();
		BeanUtils.populate(userComment, request.getParameterMap());			
		userCommentService.save(userComment);
	}
	
	/**
	 * Update.
	 * @param request 
	 * @param modelMap
	 * @throws Exception 
	 */
	@Func(funcCode="edit",moduleCode="personal.userComment")
	@RequestMapping 
	public void update(WebRequest request, ModelMap modelMap) throws Exception
	{
		UserComment userComment = userCommentService.load(Utility.parseInt(request.getParameter("id")));
		BeanUtils.populate(userComment, request.getParameterMap());
		userCommentService.update(userComment);
	}
	
	/**
	 * Delete.
	 * @param id id
	 * @throws Exception 
	 */
	@Func(funcCode="delete",moduleCode="personal.userComment")
	@RequestMapping 
	public void delete(@RequestParam Integer id) throws Exception
	{
		userCommentService.delete(id);
	}


}
