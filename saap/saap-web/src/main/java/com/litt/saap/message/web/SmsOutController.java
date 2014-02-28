package com.litt.saap.message.web;

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
import com.litt.core.util.StringUtils;
import com.litt.core.web.mvc.action.BaseController;
import com.litt.core.web.util.WebUtils;
import com.litt.saap.common.vo.LoginUserVo;
import com.litt.saap.core.web.util.LoginUtils;
import com.litt.saap.message.po.SmsOut;
import com.litt.saap.message.service.ISmsOutService;
import com.litt.saap.personal.po.Contacts;
import com.litt.saap.personal.service.IContactsService;

/**
 * 
 * SMS controller.
 * <pre><b>Description：</b>
 *    SMS Management
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">Bob.cai</a>
 * @since 2014-02-17
 * @version 1.0
 */
@Controller
public class SmsOutController extends BaseController
{
	private final static Logger logger = LoggerFactory.getLogger(SmsOutController.class);

	@Resource
	private ISmsOutService smsOutService;
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
	@Func(funcCode="04", moduleCode="8001", enableLog=false) 
	@RequestMapping 
	public ModelAndView index(WebRequest request, ModelMap modelMap) throws NotLoginException
	{	
		LoginUserVo loginUserVo = (LoginUserVo)super.getLoginVo();
		
		//get params from request
		String searchField = request.getParameter("s_searchField");
		String searchValue = request.getParameter("s_searchValue");
				
		//package the params
		PageParam pageParam = WebUtils.getPageParam(request);
		pageParam.addCond("tenantId", loginUserVo.getTenantId());
		pageParam.addCond(searchField, searchValue);	
		pageParam.addCond("isDeleted", false);	
		//Get page result
		IPageList pageList = smsOutService.listPage(pageParam);		
		
		//return params to response
		modelMap.addAttribute("pageParam", pageParam);	
		modelMap.addAttribute("pageList", pageList);	
		
    return new ModelAndView("/message/smsOut/index");	
	
	} 	
	
	/**
	 * Add Page.
	 * 
	 * @return ModelAndView
	 */	
	@Func(funcCode="01", moduleCode="8001", enableLog=false)  
	@RequestMapping
	public ModelAndView add() 
	{  
		List<Contacts> contactList = contactsService.listByUser(LoginUtils.getLoginOpId().intValue());
	  	return new ModelAndView("/message/smsOut/add").addObject("contactList", contactList);
    }
	
	/**
	 * Edit Page.
	 * 
	 * @param id 
	 * 
	 * @return ModelAndView
	 */
	@Func(funcCode="02", moduleCode="8001", enableLog=false)  
	@RequestMapping 
	public ModelAndView edit(@RequestParam Integer id) 
	{ 
		SmsOut smsOut = smsOutService.load(id);		
        return new ModelAndView("/message/smsOut/edit").addObject("smsOut", smsOut);
    }	
    
	/**
	 * Show Page.
	 * 
	 * @param id 
	 * 
	 * @return ModelAndView
	 */
	@Func(funcCode="04", moduleCode="8001", enableLog=false)  
	@RequestMapping 
	public ModelAndView show(@RequestParam Integer id) 
	{ 
		SmsOut smsOut = smsOutService.load(id);		
        return new ModelAndView("/message/smsOut/show").addObject("smsOut", smsOut);
    }   
    
    /**
	 * Save.
	 * @param request 
	 * @param modelMap
	 * @throws Exception 
	 */
	@Func(funcCode="01",moduleCode="8001")
	@RequestMapping 
	public void save(WebRequest request, ModelMap modelMap) throws Exception
	{	
		String[] receivers = StringUtils.split(request.getParameter("receiver"), ',');
		
		SmsOut[] smsOuts = new SmsOut[receivers.length];
		for (int i = 0; i < receivers.length; i++) {
					
			SmsOut smsOut = new SmsOut();
			BeanUtils.populate(smsOut, request.getParameterMap());			
			smsOut.setReceiver(receivers[i]);
			smsOuts[i] = smsOut;			
		}	
		smsOutService.doAsycSend(smsOuts);
	}
	
	/**
	 * Update.
	 * @param request 
	 * @param modelMap
	 * @throws Exception 
	 */
	@Func(funcCode="02",moduleCode="8001")
	@RequestMapping 
	public void update(WebRequest request, ModelMap modelMap) throws Exception
	{
		SmsOut smsOut = smsOutService.load(Utility.parseInt(request.getParameter("id")));
		BeanUtils.populate(smsOut, request.getParameterMap());
		smsOutService.update(smsOut);
	}
	
	/**
	 * Delete.
	 * @param id id
	 * @throws Exception 
	 */
	@Func(funcCode="03",moduleCode="8001")
	@RequestMapping 
	public void delete(@RequestParam Integer id) throws Exception
	{
		smsOutService.delete(id);
	}

	/**
	 * Delete Batch.
	 * @param id id
	 * @throws Exception 
	 */
	@Func(funcCode="03",moduleCode="8001")
	@RequestMapping 
	public void deleteBatch(@RequestParam(value="ids[]") Integer[] ids) throws Exception
	{
		smsOutService.deleteBatch(ids);
	}
	
	/**
	 * Delete.
	 *
	 * @param id the id
	 * @throws Exception the exception
	 */
	@RequestMapping 
	public void resend(@RequestParam Integer id) throws Exception
	{
		smsOutService.doResend(id);
	}

}
