package com.litt.saap.core.web.tag.el;

import java.util.List;

import com.litt.core.common.BeanManager;
import com.litt.saap.common.vo.LoginUserVo;
import com.litt.saap.system.service.IMenuService;
import com.litt.saap.system.service.IUserInfoService;
import com.litt.saap.system.vo.MenuTreeNodeVo;
import com.litt.saap.system.vo.UserInfoVo;

/**
 * Web相关静态EL实现.
 * 
 * <pre><b>描述：</b>
 *    通过EL封装后台实现为静态方法
 * </pre>
 * 
 * <pre><b>修改记录：</b>
 *    
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">蔡源</a>
 * @since 2013-10-28
 * @version 1.0
 */
public class WebEL {
	
	public static List<MenuTreeNodeVo> getMenuTree(LoginUserVo loginVo)
	{
		if(loginVo==null)
			return null;		
		IMenuService menuService = BeanManager.getBean("menuService", IMenuService.class);
		MenuTreeNodeVo ret = menuService.findTreeByOpPermission(loginVo);		
		return ret.getSubList();
	}
	
	public static MenuTreeNodeVo getDomainMenuTree(LoginUserVo loginVo, String moduleCode)
	{
		if(loginVo==null)
			return null;		
		IMenuService menuService = BeanManager.getBean("menuService", IMenuService.class);
		
		MenuTreeNodeVo ret = menuService.findDomainTreeByOpPermission(loginVo, moduleCode);		
		return ret;
	}
	
	/**
	 * 获取用户信息.
	 *
	 * @param userId the user id
	 * @return the user
	 */
	public static UserInfoVo getUser(Integer userId)
	{
		if(userId==null || userId.intValue()<=0)
			return null;
		IUserInfoService userInfoService = BeanManager.getBean("userInfoService", IUserInfoService.class);
		return userInfoService.find(userId);
		
	}

}
