package com.litt.saap.personal.web;

import java.util.Locale;

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
import com.litt.saap.personal.po.ContactsGroup;
import com.litt.saap.personal.service.IContactsGroupService;

/**
 * 
 * Groups controller.
 * <pre><b>Description：</b>
 *    Group Management
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">Bob.cai</a>
 * @since 2014-02-27
 * @version 1.0
 */
@Controller
public class ContactsGroupController extends BaseController
{
	private final static Logger logger = LoggerFactory.getLogger(ContactsGroupController.class);

	@Resource
	private IContactsGroupService contactsGroupService;
	
	/**
	 * default page.
	 * 
	 * @param request the request
	 * @param respnose the respnose
	 * 
	 * @return ModelAndView
	 */	
	@Func(funcCode="01",moduleCode="0305", enableLog=false)
	@RequestMapping 
	public ModelAndView index(Locale locale, WebRequest request, ModelMap modelMap) throws NotLoginException
	{	
		
		//get params from request
		String searchField = request.getParameter("s_searchField");
		String searchValue = request.getParameter("s_searchValue");					
			
		PageParam pageParam = WebUtils.getPageParam(request);
		pageParam.addCond("createUserId", super.getLoginOpId().intValue());
		pageParam.addCond(searchField, searchValue);
		
		IPageList pageList = contactsGroupService.listPage(pageParam);
		
        return new ModelAndView("/personal/contactsGroup/index")
        		.addObject("pageParam", pageParam).addObject("pageList", pageList);
	}   
	
	/**
	 * Add Page.
	 * 
	 * @return ModelAndView
	 */	
	@Func(funcCode="01", moduleCode="0305", enableLog=false)  
	@RequestMapping
	public ModelAndView add() 
	{        
  	return new ModelAndView("/personal/contactsGroup/add");
  }
	
	/**
	 * Edit Page.
	 * 
	 * @param id 
	 * 
	 * @return ModelAndView
	 */
	@Func(funcCode="02", moduleCode="0305", enableLog=false)  
	@RequestMapping 
	public ModelAndView edit(@RequestParam Integer id) 
	{ 
		ContactsGroup contactsGroup = contactsGroupService.load(id);		
        return new ModelAndView("/personal/contactsGroup/edit").addObject("contactsGroup", contactsGroup);
    }	
    
	/**
	 * Show Page.
	 * 
	 * @param id 
	 * 
	 * @return ModelAndView
	 */
	@Func(funcCode="04", moduleCode="0305", enableLog=false)  
	@RequestMapping 
	public ModelAndView show(@RequestParam Integer id) 
	{ 
		ContactsGroup contactsGroup = contactsGroupService.load(id);		
        return new ModelAndView("/personal/contactsGroup/show").addObject("contactsGroup", contactsGroup);
    }   
    
    /**
	 * Save.
	 * @param request 
	 * @param modelMap
	 * @throws Exception 
	 */
	@Func(funcCode="01",moduleCode="0305")
	@RequestMapping 
	public void save(WebRequest request, ModelMap modelMap) throws Exception
	{	
		ContactsGroup contactsGroup = new ContactsGroup();
		BeanUtils.populate(contactsGroup, request.getParameterMap());			
		contactsGroupService.save(contactsGroup);
	}
	
	/**
	 * Update.
	 * @param request 
	 * @param modelMap
	 * @throws Exception 
	 */
	@Func(funcCode="02",moduleCode="0305")
	@RequestMapping 
	public void update(WebRequest request, ModelMap modelMap) throws Exception
	{
		ContactsGroup contactsGroup = contactsGroupService.load(Utility.parseInt(request.getParameter("id")));
		BeanUtils.populate(contactsGroup, request.getParameterMap());
		contactsGroupService.update(contactsGroup);
	}
	
	/**
	 * Delete.
	 * @param id id
	 * @throws Exception 
	 */
	@Func(funcCode="03",moduleCode="0305")
	@RequestMapping 
	public void delete(@RequestParam Integer id) throws Exception
	{
		contactsGroupService.delete(id);
	}

	/**
	 * Delete Batch.
	 * @param id id
	 * @throws Exception 
	 */
	@Func(funcCode="03",moduleCode="0305")
	@RequestMapping 
	public void deleteBatch(@RequestParam(value="ids[]") Integer[] ids) throws Exception
	{
		contactsGroupService.deleteBatch(ids);
	}

}
