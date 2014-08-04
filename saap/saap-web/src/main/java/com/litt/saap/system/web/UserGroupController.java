package com.litt.saap.system.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import com.litt.saap.common.vo.TenantUserVo;
import com.litt.saap.core.model.CheckItem;
import com.litt.saap.core.web.util.LoginUtils;
import com.litt.saap.system.biz.IUserBizService;
import com.litt.saap.system.po.UserGroup;
import com.litt.saap.system.po.UserGroupMember;
import com.litt.saap.system.service.IUserGroupService;
import com.litt.saap.system.service.impl.IUserGroupMemberService;
import com.litt.core.dao.page.IPageList;
import com.litt.core.common.Utility;
import com.litt.core.web.util.WebUtils;
import com.litt.core.dao.ql.PageParam;
import com.litt.core.exception.NotLoginException;
import com.litt.core.module.annotation.Func;
import com.litt.core.web.mvc.action.BaseController;

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
public class UserGroupController extends BaseController
{
	private final static Logger logger = LoggerFactory.getLogger(UserGroupController.class);

	@Resource
	private IUserGroupService userGroupService;
	@Resource
	private IUserGroupMemberService userGroupMemberService;
	@Resource
	private IUserBizService userBizService;
	
	/**
	 * default page.
	 * 
	 * @param request the request
	 * @param respnose the respnose
	 * 
	 * @return ModelAndView
	 */	
	@Func(funcCode="04", moduleCode="9003", enableLog=false) 
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
		IPageList pageList = userGroupService.listPage(pageParam);		
		
		//return params to response
		modelMap.addAttribute("pageParam", pageParam);	
		modelMap.addAttribute("pageList", pageList);	
		
    return new ModelAndView("/system/userGroup/index");	
	
	} 	
	
	/**
	 * Add Page.
	 * 
	 * @return ModelAndView
	 */	
	@Func(funcCode="01", moduleCode="9003", enableLog=false)  
	@RequestMapping
	public ModelAndView add() 
	{        
  	return new ModelAndView("/system/userGroup/add");
  }
	
	/**
	 * Edit Page.
	 * 
	 * @param id 
	 * 
	 * @return ModelAndView
	 */
	@Func(funcCode="02", moduleCode="9003", enableLog=false)  
	@RequestMapping 
	public ModelAndView edit(@RequestParam Integer id) 
	{ 
		UserGroup userGroup = userGroupService.load(id);		
        return new ModelAndView("/system/userGroup/edit").addObject("userGroup", userGroup);
    }	
    
	/**
	 * Show Page.
	 * 
	 * @param id 
	 * 
	 * @return ModelAndView
	 */
	@Func(funcCode="04", moduleCode="9003", enableLog=false)  
	@RequestMapping 
	public ModelAndView show(@RequestParam Integer id) 
	{ 
		UserGroup userGroup = userGroupService.load(id);		
        return new ModelAndView("/system/userGroup/show").addObject("userGroup", userGroup);
    }   
    
    /**
	 * Save.
	 * @param request 
	 * @param modelMap
	 * @throws Exception 
	 */
	@Func(funcCode="01",moduleCode="9003")
	@RequestMapping 
	public void save(WebRequest request, ModelMap modelMap) throws Exception
	{	
		UserGroup userGroup = new UserGroup();
		BeanUtils.populate(userGroup, request.getParameterMap());			
		userGroupService.save(userGroup);
	}
	
	/**
	 * Update.
	 * @param request 
	 * @param modelMap
	 * @throws Exception 
	 */
	@Func(funcCode="02",moduleCode="9003")
	@RequestMapping 
	public void update(WebRequest request, ModelMap modelMap) throws Exception
	{
		UserGroup userGroup = userGroupService.load(Utility.parseInt(request.getParameter("id")));
		BeanUtils.populate(userGroup, request.getParameterMap());
		userGroupService.update(userGroup);
	}
	
	/**
	 * Delete.
	 * @param id id
	 * @throws Exception 
	 */
	@Func(funcCode="03",moduleCode="9003")
	@RequestMapping 
	public void delete(@RequestParam Integer id) throws Exception
	{
		userGroupService.delete(id);
	}

	/**
	 * Delete Batch.
	 * @param id id
	 * @throws Exception 
	 */
	@Func(funcCode="03",moduleCode="9003")
	@RequestMapping 
	public void deleteBatch(@RequestParam(value="ids[]") Integer[] ids) throws Exception
	{
		userGroupService.deleteBatch(ids);
	}
	
	@RequestMapping 
	public ModelAndView getTenantMembers(@RequestParam Integer userGroupId)
	{
	  int tenantId = LoginUtils.getTenantId();
	  
	  List<TenantUserVo> tenantUserList = userBizService.findByTenant(tenantId);
	  List<UserGroupMember> memberList = userGroupMemberService.listByGroup(userGroupId);
	  	  
    List<CheckItem<TenantUserVo>> tenantUserCheckItemList = new ArrayList<CheckItem<TenantUserVo>>();
    for (TenantUserVo tenantUser : tenantUserList) {
      boolean isChecked = false;
      for (UserGroupMember userGroupMember : memberList) {
        if(tenantUser.getId().equals(userGroupMember.getUserId()))
        {
          isChecked = true;
          break;
        } 
      }
      CheckItem<TenantUserVo> checkItem = new CheckItem<TenantUserVo>(isChecked, tenantUser);
      tenantUserCheckItemList.add(checkItem);
    }
    return new ModelAndView("jsonView").addObject("tenantUserList", tenantUserCheckItemList);
	}
	
	/**
   * Add Page.
   * 
   * @return ModelAndView
   */ 
  @Func(funcCode="01", moduleCode="9003", enableLog=false)  
  @RequestMapping
  public ModelAndView addMember() 
  {        
    return new ModelAndView("/system/userGroup/addMember");
  }  
  
  /**
   * Show Page.
   * 
   * @param id 
   * 
   * @return ModelAndView
   */
  @Func(funcCode="04", moduleCode="9003", enableLog=false)  
  @RequestMapping 
  public ModelAndView showMembers(@RequestParam Integer id) 
  { 
    UserGroup userGroup = userGroupService.load(id);
    
    List<Map<String, Object>> memberList = userGroupMemberService.listWithUserInfoByGroup(id);
    
    return new ModelAndView("/system/userGroup/showMembers")
            .addObject("userGroup", userGroup)
            .addObject("memberList", memberList);
  }   
  
  /**
   * Save.
   * @param request 
   * @param modelMap
   * @throws Exception 
   */
  @Func(funcCode="01",moduleCode="9003")
  @RequestMapping 
  public void saveMemberBatch(@RequestParam Integer groupId, @RequestParam(value="userIds[]") Integer[] userIds) throws Exception
  { 
    userGroupMemberService.saveBatch(groupId, userIds);
  }
  
  /**
   * Delete.
   * @param id id
   * @throws Exception 
   */
  @Func(funcCode="03",moduleCode="9003")
  @RequestMapping 
  public void deleteMember(@RequestParam Integer id) throws Exception
  {
    userGroupMemberService.delete(id);
  }
  

  /**
   * Delete Batch.
   * @param id id
   * @throws Exception 
   */
  @Func(funcCode="03",moduleCode="9003")
  @RequestMapping 
  public void deleteMemberBatch(@RequestParam(value="ids[]") Integer[] ids) throws Exception
  {
    userGroupMemberService.deleteBatch(ids);
  }

}
