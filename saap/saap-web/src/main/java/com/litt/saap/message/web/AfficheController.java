package com.litt.saap.message.web;

import java.util.Date;

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
import com.litt.saap.core.web.util.LoginUtils;
import com.litt.saap.message.po.Affiche;
import com.litt.saap.message.service.IAfficheService;

/**
 * 
 * Affiche controller.
 * <pre><b>Description：</b>
 *    Affiche Management.
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">Bob.cai</a>
 * @since 2014-06-26
 * @version 1.0
 */
@Controller
public class AfficheController extends BaseController
{
	private final static Logger logger = LoggerFactory.getLogger(AfficheController.class);

	@Resource
	private IAfficheService afficheService;
	
	/**
	 * default page.
	 * 
	 * @param request the request
	 * @param respnose the respnose
	 * 
	 * @return ModelAndView
	 */	
	@Func(funcCode="04", moduleCode="8003", enableLog=false) 
	@RequestMapping 
	public ModelAndView index(WebRequest request, ModelMap modelMap) throws NotLoginException
	{	
		LoginUserVo loginUserVo = (LoginUserVo)super.getLoginVo();
		
		//get params from request
		Integer type = Utility.parseInt(request.getParameter("type"), null);
		String title = request.getParameter("title");
		Boolean isChecked = Utility.parseBoolean(request.getParameter("isChecked"), null);
		Date expiredDate = Utility.parseDate(request.getParameter("expiredDate"));
				
		//package the params
		PageParam pageParam = WebUtils.getPageParam(request);
		pageParam.addCond("tenantId", loginUserVo.getTenantId());
    pageParam.addCond("isDeleted", false);
		pageParam.addCond("type", type);	
		pageParam.addCond("title", title);	
		pageParam.addCond("isChecked", isChecked);  
		pageParam.addCond("expiredDate", expiredDate);  	
		//Get page result
		IPageList pageList = afficheService.listPage(pageParam);		
		
		//return params to response
		modelMap.addAttribute("pageParam", pageParam);	
		modelMap.addAttribute("pageList", pageList);	
		
    return new ModelAndView("/message/affiche/index");	
	
	} 	
	
	/**
	 * Add Page.
	 * 
	 * @return ModelAndView
	 */	
	@Func(funcCode="01", moduleCode="8003", enableLog=false)  
	@RequestMapping
	public ModelAndView add() 
	{        
  	return new ModelAndView("/message/affiche/add");
  }
	
	/**
	 * Edit Page.
	 * 
	 * @param id 
	 * 
	 * @return ModelAndView
	 */
	@Func(funcCode="02", moduleCode="8003", enableLog=false)  
	@RequestMapping 
	public ModelAndView edit(@RequestParam Integer id) 
	{ 
		Affiche affiche = afficheService.load(id);		
        return new ModelAndView("/message/affiche/edit").addObject("affiche", affiche);
    }	
    
	/**
	 * Show Page.
	 * 
	 * @param id 
	 * 
	 * @return ModelAndView
	 */
	@Func(funcCode="04", moduleCode="8003", enableLog=false)  
	@RequestMapping 
	public ModelAndView show(@RequestParam Integer id) 
	{ 
		Affiche affiche = afficheService.load(id);		
    return new ModelAndView("/message/affiche/show").addObject("affiche", affiche);
  }   
	
	/**
	 * 获得最新的N项，用于主页显示.
	 *
	 * @param pageSize the page size
	 * @return the top n
	 */
	@RequestMapping 
	public ModelAndView getTopN(@RequestParam(defaultValue="10") int pageSize)
	{
	  int tenantId = LoginUtils.getTenantId();
	  PageParam pageParam = new PageParam(0, pageSize, "updateDatetime", "desc");
    pageParam.addCond("tenantId", tenantId);
    pageParam.addCond("isChecked", true);  
    pageParam.addCond("expiredDate", new Date());  
    
    IPageList pageList = afficheService.listPageWithGlobal(pageParam);
    
    return new ModelAndView("jsonView").addObject("rsList", pageList.getRsList());
	}
    
    /**
	 * Save.
	 * @param request 
	 * @param modelMap
	 * @throws Exception 
	 */
	@Func(funcCode="01",moduleCode="8003")
	@RequestMapping 
	public void save(WebRequest request, ModelMap modelMap) throws Exception
	{	
		Affiche affiche = new Affiche();
		BeanUtils.populate(affiche, request.getParameterMap());			
		afficheService.save(affiche);
	}
	
	/**
	 * Update.
	 * @param request 
	 * @param modelMap
	 * @throws Exception 
	 */
	@Func(funcCode="02",moduleCode="8003")
	@RequestMapping 
	public void update(WebRequest request, ModelMap modelMap) throws Exception
	{
		Affiche affiche = afficheService.load(Utility.parseInt(request.getParameter("id")));
		BeanUtils.populate(affiche, request.getParameterMap());
		afficheService.update(affiche);
	}
	
	/**
	 * Delete.
	 * @param id id
	 * @throws Exception 
	 */
	@Func(funcCode="03",moduleCode="8003")
	@RequestMapping 
	public void delete(@RequestParam Integer id) throws Exception
	{
		afficheService.delete(id);
	}

	/**
	 * Delete Batch.
	 * @param id id
	 * @throws Exception 
	 */
	@Func(funcCode="03",moduleCode="8003")
	@RequestMapping 
	public void deleteBatch(@RequestParam(value="ids[]") Integer[] ids) throws Exception
	{
		afficheService.deleteBatch(ids);
	}

}
