package com.litt.saap.personal.web;

import java.util.List;
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
import com.litt.core.util.ArrayUtils;
import com.litt.core.util.ValidateUtils;
import com.litt.core.web.mvc.action.BaseController;
import com.litt.core.web.util.WebUtils;
import com.litt.saap.core.web.model.MessageBox;
import com.litt.saap.core.web.util.LoginUtils;
import com.litt.saap.personal.biz.IContactsBizService;
import com.litt.saap.personal.po.Contacts;
import com.litt.saap.personal.po.ContactsGroup;
import com.litt.saap.personal.po.ContactsGroupMember;
import com.litt.saap.personal.service.IContactsGroupService;
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
	@Resource
	private IContactsGroupService contactsGroupService;
	@Resource
	private IContactsBizService contactsBizService;
	
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
		String searchField = request.getParameter("s_searchField");
		String searchValue = request.getParameter("s_searchValue");
					
			
		PageParam pageParam = WebUtils.getPageParam(request);
		pageParam.addCond("createUserId", super.getLoginOpId().intValue());
		pageParam.addCond(searchField, searchValue);
		
		IPageList pageList = contactsService.listPage(pageParam);
		
        return new ModelAndView("/personal/contacts/index")
        		.addObject("pageParam", pageParam).addObject("pageList", pageList);
	}   
	
	/**
	 * Add Page.
	 * 
	 * @return ModelAndView
	 */	
	@RequestMapping
	public ModelAndView add() 
	{    
		List<ContactsGroup> contactsGroupList = contactsGroupService.listByUser(LoginUtils.getLoginOpId().intValue());
		
        return new ModelAndView("/personal/contacts/add").addObject("contactsGroupList", contactsGroupList);
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
		List<ContactsGroup> contactsGroupList = contactsGroupService.listByUser(LoginUtils.getLoginOpId().intValue());
		
		List<ContactsGroupMember> memberList = contactsBizService.listMemberByContacts(id);
		Integer[] memberGroupIds = new Integer[memberList.size()];
		for (int i=0;i<memberList.size();i++) {
			ContactsGroupMember contactsGroupMember = memberList.get(i);
			memberGroupIds[i] = contactsGroupMember.getGroupId();
		}
		
        return new ModelAndView("/personal/contacts/edit")
        		.addObject("contacts", contacts)
        		.addObject("contactsGroupList", contactsGroupList)
        		.addObject("memberGroupIds", memberGroupIds);
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
	@Func(funcCode="01",moduleCode="0305")
	@RequestMapping 
	public void save(WebRequest request, ModelMap modelMap) throws Exception
	{	
		Contacts contacts = new Contacts();
		BeanUtils.populate(contacts, request.getParameterMap());			
		
		Integer[] contactsGroupIds = ArrayUtils.toInteger(request.getParameterValues("contactsGroupIds"));
		
		contactsBizService.save(contacts, contactsGroupIds);
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
		Contacts contacts = contactsService.load(Utility.parseInt(request.getParameter("id")));
		BeanUtils.populate(contacts, request.getParameterMap());
		
		Integer[] contactsGroupIds = ArrayUtils.toInteger(request.getParameterValues("contactsGroupIds"));
		contactsBizService.update(contacts, contactsGroupIds);
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
		contactsService.delete(id);
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

	/**
	 * Gets the contact list.
	 *
	 * @return the contact list
	 */
	@RequestMapping 
	public ModelAndView getContactList()
	{
		List<Contacts> contactList = contactsService.listByUser(LoginUtils.getLoginOpId().intValue());
		return new ModelAndView("jsonView").addObject("contactList", contactList);
	}
	

}
