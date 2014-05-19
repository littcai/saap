package com.litt.saap.crm.web;

import java.util.List;

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
import com.litt.saap.crm.po.CustContacts;
import com.litt.saap.crm.po.Customer;
import com.litt.saap.crm.service.ICustContactsService;
import com.litt.saap.crm.service.ICustomerService;
import com.litt.saap.personal.po.Contacts;

/**
 * 
 * Contacts controller.
 * <pre><b>Description：</b>
 *    Customer Contacts Management
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">Bob.cai</a>
 * @since 2013-10-30
 * @version 1.0
 */
@Controller
public class CustContactsController extends BaseController
{
	private final static Logger logger = LoggerFactory.getLogger(CustContactsController.class);

	@Resource
	private ICustContactsService custContactsService;
	
	@Resource
	private ICustomerService customerService;
	
	/**
	 * default page.
	 * 
	 * @param request the request
	 * @param respnose the respnose
	 * 
	 * @return ModelAndView
	 */	
	@Func(funcCode="04", moduleCode="1102", enableLog=false) 
	@RequestMapping 
	public ModelAndView index(WebRequest request, ModelMap modelMap) throws NotLoginException
	{	
		LoginUserVo loginUserVo = (LoginUserVo)super.getLoginVo();
		
		//get params from request		
		String customerName = request.getParameter("s_customerName");
		
		String name = request.getParameter("s_name");
		Integer gender = Utility.parseInt(request.getParameter("gender"));
		String mobile = request.getParameter("mobile");
		String email = request.getParameter("email");
				
		//package the params
		PageParam pageParam = WebUtils.getPageParam(request);
		pageParam.addCond("tenantId", loginUserVo.getTenantId());	
		pageParam.addCond("customerName", customerName);	
		pageParam.addCond("name", name);	
		pageParam.addCond("gender", gender);
		pageParam.addCond("mobile", mobile);
		pageParam.addCond("email", email);
		pageParam.addCond("isDeleted", false);	
		//Get page result
		IPageList pageList = custContactsService.listPage(pageParam);		
		
		//return params to response
		modelMap.addAttribute("pageParam", pageParam);	
		modelMap.addAttribute("pageList", pageList);	
		
		List<Customer> customerList = customerService.listAll();		
		modelMap.addAttribute("customerList", customerList);	
		
		return new ModelAndView("/crm/custContacts/index");	
	
	} 	
	
	/**
	 * Add Page.
	 * 
	 * @return ModelAndView
	 */	
	@Func(funcCode="01", moduleCode="1102", enableLog=false)  
	@RequestMapping
	public ModelAndView add() 
	{        
		List<Customer> customerList = customerService.listAll();	
  	return new ModelAndView("/crm/custContacts/add").addObject("customerList", customerList);
  }
	
	/**
	 * Edit Page.
	 * 
	 * @param id 
	 * 
	 * @return ModelAndView
	 */
	@Func(funcCode="02", moduleCode="1102", enableLog=false)  
	@RequestMapping 
	public ModelAndView edit(@RequestParam Integer id) 
	{ 
		CustContacts custContacts = custContactsService.load(id);		
		List<Customer> customerList = customerService.listAll();	
        return new ModelAndView("/crm/custContacts/edit").addObject("custContacts", custContacts).addObject("customerList", customerList);
    }	
    
	/**
	 * Show Page.
	 * 
	 * @param id 
	 * 
	 * @return ModelAndView
	 */
	@Func(funcCode="04", moduleCode="1102", enableLog=false)  
	@RequestMapping 
	public ModelAndView show(@RequestParam Integer id) 
	{ 
		CustContacts custContacts = custContactsService.load(id);		
		Customer customer = customerService.load(custContacts.getCustomerId());
		
        return new ModelAndView("/crm/custContacts/show").addObject("custContacts", custContacts).addObject("customer", customer);
    }   
    
    /**
	 * Save.
	 * @param request 
	 * @param modelMap
	 * @throws Exception 
	 */
	@Func(funcCode="01",moduleCode="1102")
	@RequestMapping 
	public void save(WebRequest request, ModelMap modelMap) throws Exception
	{	
		CustContacts custContacts = new CustContacts();
		BeanUtils.populate(custContacts, request.getParameterMap());			
		custContactsService.save(custContacts);
	}
	
	/**
	 * Update.
	 * @param request 
	 * @param modelMap
	 * @throws Exception 
	 */
	@Func(funcCode="02",moduleCode="1102")
	@RequestMapping 
	public void update(WebRequest request, ModelMap modelMap) throws Exception
	{
		CustContacts custContacts = custContactsService.load(Utility.parseInt(request.getParameter("id")));
		BeanUtils.populate(custContacts, request.getParameterMap());
		custContactsService.update(custContacts);
	}
	
	/**
	 * Delete.
	 * @param id id
	 * @throws Exception 
	 */
	@Func(funcCode="03",moduleCode="1102")
	@RequestMapping 
	public void delete(@RequestParam Integer id) throws Exception
	{
		custContactsService.delete(id);
	}
	
	/**
	 * Delete.
	 * @param id id
	 * @throws Exception 
	 */
	@Func(funcCode="03",moduleCode="1102")
	@RequestMapping 
	public void deleteBatch(@RequestParam Integer[] ids) throws Exception
	{
		custContactsService.deleteBatch(ids);
	}
	
	/**
	 * Gets the contact list.
	 *
	 * @return the contact list
	 */
	@RequestMapping 
	public ModelAndView getContactsList(@RequestParam Integer customerId)
	{
		List<CustContacts> custContactsList = custContactsService.listByCustomer(customerId);
		return new ModelAndView("jsonView").addObject("custContactsList", custContactsList);
	}


}
