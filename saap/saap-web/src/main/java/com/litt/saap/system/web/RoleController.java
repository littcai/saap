package com.litt.saap.system.web;

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
import com.litt.saap.core.module.tenant.config.TenantTypeConfigManager;
import com.litt.saap.core.web.util.LoginUtils;
import com.litt.saap.system.biz.ITenantBizService;
import com.litt.saap.system.po.Role;
import com.litt.saap.system.service.IRoleService;
import com.litt.saap.system.vo.PermissionTreeVo;
import com.litt.saap.system.vo.TenantVo;
import com.litt.core.dao.page.IPageList;
import com.litt.core.common.Utility;
import com.litt.core.web.util.WebUtils;
import com.litt.core.dao.ql.PageParam;
import com.litt.core.exception.NotLoginException;
import com.litt.core.module.annotation.Func;
import com.litt.core.web.mvc.action.BaseController;

/**
 * 
 * Roles controller.
 * <pre><b>Description：</b>
 *    Role and privilege Management
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">Bob.cai</a>
 * @since 2014-02-27
 * @version 1.0
 */
@Controller
public class RoleController extends BaseController
{
	private final static Logger logger = LoggerFactory.getLogger(RoleController.class);

	@Resource
	private IRoleService roleService;
	@Resource
	private ITenantBizService tenantBizService;
	
	/**
	 * default page.
	 * 
	 * @param request the request
	 * @param respnose the respnose
	 * 
	 * @return ModelAndView
	 */	
	@Func(funcCode="04", moduleCode="9004", enableLog=false) 
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
		IPageList pageList = roleService.listPage(pageParam);		
		
		//return params to response
		modelMap.addAttribute("pageParam", pageParam);	
		modelMap.addAttribute("pageList", pageList);	
		
    return new ModelAndView("/system/role/index");	
	
	} 	
	
	/**
	 * Add Page.
	 * 
	 * @return ModelAndView
	 */	
	@Func(funcCode="01", moduleCode="9004", enableLog=false)  
	@RequestMapping
	public ModelAndView add() 
	{        
		PermissionTreeVo permissionTree = tenantBizService.findTenantPermissionTree(LoginUtils.getTenantId());
		
		return new ModelAndView("/system/role/add").addObject("permissionTree", permissionTree);
    }
	
	/**
	 * Edit Page.
	 * 
	 * @param id 
	 * 
	 * @return ModelAndView
	 */
	@Func(funcCode="02", moduleCode="9004", enableLog=false)  
	@RequestMapping 
	public ModelAndView edit(@RequestParam Integer id) 
	{ 
		Role role = roleService.load(id);
		PermissionTreeVo permissionTree = tenantBizService.findTenantPermissionTree(LoginUtils.getTenantId(), id);
        return new ModelAndView("/system/role/edit").addObject("role", role).addObject("permissionTree", permissionTree);
    }	
    
	/**
	 * Show Page.
	 * 
	 * @param id 
	 * 
	 * @return ModelAndView
	 */
	@Func(funcCode="04", moduleCode="9004", enableLog=false)  
	@RequestMapping 
	public ModelAndView show(@RequestParam Integer id) 
	{ 
		Role role = roleService.load(id);		
        return new ModelAndView("/system/role/show").addObject("role", role);
    }   
    
    /**
	 * Save.
	 * @param request 
	 * @param modelMap
	 * @throws Exception 
	 */
	@Func(funcCode="01",moduleCode="9004")
	@RequestMapping 
	public void save(WebRequest request, ModelMap modelMap) throws Exception
	{	
		String[] permissionCodes = request.getParameterValues("permissionCodes");
		
		Role role = new Role();
		BeanUtils.populate(role, request.getParameterMap());			
		roleService.save(role, permissionCodes);
	}
	
	/**
	 * Update.
	 * @param request 
	 * @param modelMap
	 * @throws Exception 
	 */
	@Func(funcCode="02",moduleCode="9004")
	@RequestMapping 
	public void update(WebRequest request, ModelMap modelMap) throws Exception
	{
		String[] permissionCodes = request.getParameterValues("permissionCodes");
		
		Role role = roleService.load(Utility.parseInt(request.getParameter("id")));
		BeanUtils.populate(role, request.getParameterMap());
		roleService.update(role, permissionCodes);
	}
	
	/**
	 * Delete.
	 * @param id id
	 * @throws Exception 
	 */
	@Func(funcCode="03",moduleCode="9004")
	@RequestMapping 
	public void delete(@RequestParam Integer id) throws Exception
	{
		roleService.delete(id);
	}

	/**
	 * Delete Batch.
	 * @param id id
	 * @throws Exception 
	 */
	@Func(funcCode="03",moduleCode="9004")
	@RequestMapping 
	public void deleteBatch(@RequestParam(value="ids[]") Integer[] ids) throws Exception
	{
		roleService.deleteBatch(ids);
	}
	
	/**
	 * Resume.
	 * @param id id
	 * @throws Exception 
	 */
	@Func(funcCode="05",moduleCode="9004")
	@RequestMapping 
	public void resume(@RequestParam Integer id) throws Exception
	{
		roleService.doResume(id);
	}

}
