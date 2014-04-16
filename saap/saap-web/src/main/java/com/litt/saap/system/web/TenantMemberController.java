package com.litt.saap.system.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import com.litt.core.util.ArrayUtils;
import com.litt.core.util.StringUtils;
import com.litt.core.web.mvc.action.BaseController;
import com.litt.core.web.util.WebUtils;
import com.litt.saap.common.vo.LoginUserVo;
import com.litt.saap.core.common.SaapConstants;
import com.litt.saap.core.model.CheckItem;
import com.litt.saap.core.web.util.LoginUtils;
import com.litt.saap.system.biz.ITenantBizService;
import com.litt.saap.system.biz.IUserBizService;
import com.litt.saap.system.po.Role;
import com.litt.saap.system.po.TenantMember;
import com.litt.saap.system.po.UserInfo;
import com.litt.saap.system.service.IRoleService;
import com.litt.saap.system.service.ITenantMemberService;
import com.litt.saap.system.service.IUserGroupService;
import com.litt.saap.system.service.IUserInfoService;

/**
 * 
 * Members controller.
 * <pre><b>Description：</b>
 *    Member Management
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">Bob.cai</a>
 * @since 2014-02-27
 * @version 1.0
 */
@Controller
public class TenantMemberController extends BaseController
{
	private final static Logger logger = LoggerFactory.getLogger(TenantMemberController.class);

	@Resource
	private ITenantMemberService tenantMemberService;
	@Resource
	private ITenantBizService tenantBizService;
	@Resource
	private IUserInfoService userInfoService;
	@Resource
	private IUserBizService userBizService;
	@Resource
	private IRoleService roleService;
	@Resource
	private IUserGroupService userGroupService;
	
	/**
	 * default page.
	 * 
	 * @param request the request
	 * @param respnose the respnose
	 * 
	 * @return ModelAndView
	 */	
	@Func(funcCode="04", moduleCode="9002", enableLog=false) 
	@RequestMapping 
	public ModelAndView index(WebRequest request, ModelMap modelMap) throws NotLoginException
	{	
		LoginUserVo loginUserVo = (LoginUserVo)super.getLoginVo();
		
		//get params from request
		Map<String, Object> params = WebUtils.getParametersStartingWith(request, SaapConstants.DEFAULT_SEARCH_PREFIX);
			
		String searchField = Utility.trimNull(params.get("searchField"));
		Object searchValue = params.get("searchValue");
				
		//package the params
		PageParam pageParam = WebUtils.getPageParam(request);
		pageParam.addCond("tenantId", loginUserVo.getTenantId());
		pageParam.addCond(searchField, searchValue);	
		pageParam.addCond("isDeleted", false);	
		//Get page result
		IPageList pageList = tenantMemberService.listPage(pageParam);		
		
		//return params to response
		modelMap.addAttribute("pageParam", pageParam);	
		modelMap.addAttribute("pageList", pageList);	
		
    return new ModelAndView("/system/tenantMember/index");	
	
	} 	
	
	/**
	 * Add Page.
	 * 
	 * @return ModelAndView
	 */	
	@Func(funcCode="01", moduleCode="9002", enableLog=false)  
	@RequestMapping
	public ModelAndView add() 
	{        
  	return new ModelAndView("/system/tenantMember/add");
  }
	
	/**
	 * Edit Page.
	 * 
	 * @param id 
	 * 
	 * @return ModelAndView
	 */
	@Func(funcCode="02", moduleCode="9002", enableLog=false)  
	@RequestMapping 
	public ModelAndView edit(@RequestParam Integer id) 
	{ 
		TenantMember tenantMember = tenantMemberService.load(id);		
		UserInfo userInfo = userInfoService.load(tenantMember.getUserId());
		
        return new ModelAndView("/system/tenantMember/edit")
        			.addObject("tenantMember", tenantMember)
        			.addObject("userInfo", userInfo)
        			;
    }	
    
	/**
	 * Show Page.
	 * 
	 * @param id 
	 * 
	 * @return ModelAndView
	 */
	@Func(funcCode="04", moduleCode="9002", enableLog=false)  
	@RequestMapping 
	public ModelAndView show(@RequestParam Integer id) 
	{ 
		TenantMember tenantMember = tenantMemberService.load(id);		
		UserInfo userInfo = userInfoService.load(tenantMember.getUserId());
		
		List<Role> userRoleList = userBizService.listUserRoleByTenant(tenantMember.getUserId(), tenantMember.getTenantId());
		
        return new ModelAndView("/system/tenantMember/show")
					.addObject("tenantMember", tenantMember)
					.addObject("userInfo", userInfo)
					.addObject("userRoleList", userRoleList)
					;
    }   
    
    /**
	 * Save.
	 * @param request 
	 * @param modelMap
	 * @throws Exception 
	 */
	@Func(funcCode="01",moduleCode="9002")
	@RequestMapping 
	public void save(WebRequest request, ModelMap modelMap) throws Exception
	{	
		UserInfo userInfo = new UserInfo();
		BeanUtils.populate(userInfo, request.getParameterMap());			
		tenantBizService.saveMember(userInfo);
	}
	
	/**
	 * Update.
	 * @param request 
	 * @param modelMap
	 * @throws Exception 
	 */
	@Func(funcCode="02",moduleCode="9002")
	@RequestMapping 
	public void update(WebRequest request, ModelMap modelMap) throws Exception
	{
		String newpassword = request.getParameter("newpassword");
		
		TenantMember tenantMember = tenantMemberService.load(Utility.parseInt(request.getParameter("tenantMemberId")));
		
		UserInfo userInfo = userInfoService.load(tenantMember.getUserId());
		BeanUtils.populate(userInfo, request.getParameterMap());		
		tenantBizService.updateMember(tenantMember, userInfo, newpassword);
	}
	
	/**
	 * Delete.
	 * @param id id
	 * @throws Exception 
	 */
	@Func(funcCode="03",moduleCode="9002")
	@RequestMapping 
	public void delete(@RequestParam Integer id) throws Exception
	{
		tenantBizService.deleteMember(id);
	}

	/**
	 * Delete Batch.
	 * @param id id
	 * @throws Exception 
	 */
	@Func(funcCode="03",moduleCode="9002")
	@RequestMapping 
	public void deleteBatch(@RequestParam(value="ids[]") Integer[] ids) throws Exception
	{
		tenantBizService.deleteMemberBatch(ids);
	}
	
	/**
	 * Edit Page.
	 * 
	 * @param id 
	 * 
	 * @return ModelAndView
	 */
	@Func(funcCode="02", moduleCode="9002", enableLog=false)  
	@RequestMapping 
	public ModelAndView editRole(@RequestParam Integer id) 
	{ 
		TenantMember tenantMember = tenantMemberService.load(id);		
		UserInfo userInfo = userInfoService.load(tenantMember.getUserId());
		//查询租户所有角色
		List<Role> roleList = roleService.listByTenant(tenantMember.getTenantId());
		
		//查询用户在该租户下的角色
		List<Role> userRoleList = userBizService.listUserRoleByTenant(tenantMember.getUserId(), tenantMember.getTenantId());
		
		List<CheckItem<Role>> roleCheckItemList = new ArrayList<CheckItem<Role>>();
		for (Role role : roleList) {
			boolean isChecked = false;
			for (Role userRole : userRoleList) {
				if(role.getId().equals(userRole.getId()))
				{
					isChecked = true;
					break;
				}	
			}
			CheckItem<Role> checkItem = new CheckItem<Role>(isChecked, role);
			roleCheckItemList.add(checkItem);
		}
		
        return new ModelAndView("/system/tenantMember/editRole")
        			.addObject("tenantMember", tenantMember)
        			.addObject("userInfo", userInfo)
        			.addObject("roleCheckItemList", roleCheckItemList)
        			;
    }
	
	/**
	 * Update.
	 * @param request 
	 * @param modelMap
	 * @throws Exception 
	 */
	@Func(funcCode="02",moduleCode="9002")
	@RequestMapping 
	public void updateRole(@RequestParam Integer tenantMemberId, @RequestParam Integer userId, @RequestParam(required=false, value="roleIds[]")Integer[] roleIds) throws Exception
	{			
		userBizService.updateUserRoleByTenant(userId, LoginUtils.getTenantId(), roleIds);
	}

}
