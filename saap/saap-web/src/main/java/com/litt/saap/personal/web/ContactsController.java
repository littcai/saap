package com.litt.saap.personal.web;

import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.litt.core.common.BeanManager;
import com.litt.core.common.Utility;
import com.litt.core.dao.page.IPageList;
import com.litt.core.dao.ql.PageParam;
import com.litt.core.exception.NotLoginException;
import com.litt.core.module.annotation.Func;
import com.litt.core.util.ValidateUtils;
import com.litt.core.web.mvc.action.BaseController;
import com.litt.core.web.util.WebUtils;
import com.litt.saap.core.web.model.MessageBox;
import com.litt.saap.core.web.util.LoginUtils;
import com.litt.saap.personal.po.Contacts;
import com.litt.saap.personal.service.IContactsService;

/**
 * 
 * Contacts controller.
 * <pre><b>Description：</b>
 *    Contacts
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">Bob.cai</a>
 * @since 2013-09-18
 * @version 1.0
 */
@Controller
public class ContactsController extends BaseController
{
	private final static Logger logger = LoggerFactory.getLogger(ContactsController.class);

	@Resource
	private IContactsService contactsService;
	
	/**
	 * default page.
	 * 
	 * @param request the request
	 * @param respnose the respnose
	 * 
	 * @return ModelAndView
	 */	
	@RequestMapping 
	public ModelAndView index(Locale locale, WebRequest request, ModelMap modelMap) throws NotLoginException
	{	
		
		//get params from request
		String name = request.getParameter("name");	
					
		//return params to response
		modelMap.addAttribute("name", name);	
		
		PageParam pageParam = WebUtils.getPageParam(request);
		pageParam.addCond("createUserId", super.getLoginOpId().intValue());
		
		IPageList pageList = contactsService.listPage(pageParam);
		
		if(!ValidateUtils.isEmpty(request.getParameter("result")))
		{
			String actionType = request.getParameter("actionType");
			MessageBox messageBox = new MessageBox(super.getMessage("contacts.func."+actionType+".success", locale));
			modelMap.addAttribute("messageBox", messageBox);
		}
        return new ModelAndView("/personal/contacts/index").addObject("pageParam", pageParam).addObject("pageList", pageList);
	}   
	
	/**
	 * Add Page.
	 * 
	 * @return ModelAndView
	 */	
	@RequestMapping
	public ModelAndView add() 
	{        
        return new ModelAndView("/personal/contacts/add");
    }
	
	/**
	 * Edit Page.
	 * 
	 * @param id 
	 * 
	 * @return ModelAndView
	 */
	@RequestMapping 
	public ModelAndView edit(@RequestParam Integer id) 
	{ 
		Contacts contacts = contactsService.load(id);		
        return new ModelAndView("/personal/contacts/edit").addObject("contacts", contacts);
    }	
    
	/**
	 * Show Page.
	 * 
	 * @param id 
	 * 
	 * @return ModelAndView
	 */
	@RequestMapping 
	public ModelAndView show(@RequestParam Integer id) 
	{ 
		Contacts contacts = contactsService.load(id);		
        return new ModelAndView("/personal/contacts/show").addObject("contacts", contacts);
    }   
    
    /**
	 * Save.
	 * @param request 
	 * @param modelMap
	 * @throws Exception 
	 */
	@Func(funcCode="01",moduleCode="0314")
	@RequestMapping 
	public void save(WebRequest request, ModelMap modelMap) throws Exception
	{	
		Contacts contacts = new Contacts();
		BeanUtils.populate(contacts, request.getParameterMap());			
		contactsService.save(contacts);		
	}
	
	/**
	 * Update.
	 * @param request 
	 * @param modelMap
	 * @throws Exception 
	 */
	@Func(funcCode="02",moduleCode="0314")
	@RequestMapping 
	public void update(WebRequest request, ModelMap modelMap) throws Exception
	{
		Contacts contacts = contactsService.load(Utility.parseInt(request.getParameter("id")));
		BeanUtils.populate(contacts, request.getParameterMap());
		contactsService.update(contacts);
	}
	
	/**
	 * Delete.
	 * @param id id
	 * @throws Exception 
	 */
	@Func(funcCode="03",moduleCode="0314")
	@RequestMapping 
	public void delete(@RequestParam Integer id) throws Exception
	{
		contactsService.delete(id);
	}


}
