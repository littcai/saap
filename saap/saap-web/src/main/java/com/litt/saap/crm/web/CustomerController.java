package com.litt.saap.crm.web;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
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
import com.litt.core.shield.vo.ILoginVo;
import com.litt.core.web.mvc.action.BaseController;
import com.litt.core.web.util.WebUtils;
import com.litt.saap.common.vo.LoginUserVo;
import com.litt.saap.common.vo.TenantUserVo;
import com.litt.saap.core.web.util.LoginUtils;
import com.litt.saap.crm.po.Customer;
import com.litt.saap.crm.service.ICustomerService;
import com.litt.saap.system.biz.IUserBizService;

/**
 * .
 * 
 * <pre><b>描述：</b>
 *    
 * </pre>
 * 
 * <pre><b>修改记录：</b>
 *    
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">蔡源</a>
 * @since 2013-10-15
 * @version 1.0
 */
@Controller
public class CustomerController extends BaseController {
	
	@Resource
	private ICustomerService customerService;
	
	@Resource
	private IUserBizService userBizService;
	
	/**
	 * 默认页面.
	 * 
	 * @param request the request 请求对象
	 * @param respnose the respnose 响应对象
	 * 
	 * @return 视图
	 */	
	@Func(funcCode="04",moduleCode="1101", enableLog=false)
	@RequestMapping 
	public ModelAndView index(WebRequest request, ModelMap modelMap) throws NotLoginException
	{	
		LoginUserVo loginUserVo = (LoginUserVo)super.getLoginVo();
		
		//从请求中获取查询条件		
		String code = request.getParameter("code");
		String name = request.getParameter("name");
				
		//封装到分页参数对象中
		PageParam pageParam = WebUtils.getPageParam(request);
		pageParam.addCond("tenantId", loginUserVo.getTenantId());
		pageParam.addCond("code", code);	
		pageParam.addCond("name", name);	
		pageParam.addCond("isDeleted", false);	
		//分页查询
		IPageList pageList = customerService.listPage(pageParam);		
        return new ModelAndView("/crm/customer/index").addObject("pageParam", pageParam).addObject("pageList", pageList);
	}  
	
	/**
	 * Add Page.
	 * 
	 * @return ModelAndView
	 */	
	@Func(funcCode="01",moduleCode="1101", enableLog=false)
	@RequestMapping
	public ModelAndView add() 
	{        
		List<TenantUserVo> chargeUserList = userBizService.findByTenant(LoginUtils.getTenantId());
		
        return new ModelAndView("/crm/customer/add").addObject("chargeUserList", chargeUserList);
    }
	
	/**
	 * Edit Page.
	 * 
	 * @param id 
	 * 
	 * @return ModelAndView
	 */
	@Func(funcCode="02",moduleCode="1101", enableLog=false)
	@RequestMapping 
	public ModelAndView edit(@RequestParam Integer id) throws NotLoginException 
	{ 
		Customer customer = customerService.load(id);		
		//TODO 这里要检验用户权限		
		
		List<TenantUserVo> chargeUserList = userBizService.findByTenant(LoginUtils.getTenantId());
		
        return new ModelAndView("/crm/customer/edit").addObject("customer", customer).addObject("chargeUserList", chargeUserList);
    }	
    
	/**
	 * Show Page.
	 * 
	 * @param id 
	 * 
	 * @return ModelAndView
	 */
	@Func(funcCode="04",moduleCode="1101", enableLog=false)
	@RequestMapping 
	public ModelAndView show(@RequestParam Integer id) throws NotLoginException
	{ 
		Customer customer = customerService.load(id);		
        return new ModelAndView("/crm/customer/show").addObject("customer", customer);
    }   
    
    /**
	 * Save.
	 * @param request 
	 * @param modelMap
	 * @throws Exception 
	 */
	@Func(funcCode="01",moduleCode="1101")
	@RequestMapping 
	public void save(WebRequest request, ModelMap modelMap) throws Exception
	{	
		Customer customer = new Customer();
		BeanUtils.populate(customer, request.getParameterMap());	
		
		customer.setCreateUserId(super.getLoginOpId().intValue());
		customerService.save(customer);		
	}
	
	/**
	 * Update.
	 * @param request 
	 * @param modelMap
	 * @throws Exception 
	 */
	@Func(funcCode="02",moduleCode="1101")
	@RequestMapping 
	public void update(WebRequest request, ModelMap modelMap) throws Exception
	{
		Customer customer = customerService.load(Utility.parseInt(request.getParameter("id")));
		BeanUtils.populate(customer, request.getParameterMap());
		
		customer.setUpdateUserId(super.getLoginOpId().intValue());
		
		customerService.update(customer);
	}
	
	/**
	 * Delete.
	 * @param id id
	 * @throws Exception 
	 */
	@Func(funcCode="03",moduleCode="1101")
	@RequestMapping 
	public void delete(@RequestParam Integer id) throws Exception
	{
		customerService.delete(id);
	}

}
