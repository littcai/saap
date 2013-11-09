package com.litt.saap.system.service;

import java.util.List;
import java.util.Locale;

import com.litt.core.shield.vo.ILoginVo;
import com.litt.saap.common.vo.LoginUserVo;
import com.litt.saap.system.po.Menu;
import com.litt.saap.system.vo.MenuTreeNodeVo;

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
 * @since 2013-10-21
 * @version 1.0
 */
public interface IMenuService {

	/**
	 * 保存菜单管理
	 * @param menu Menu对象
	 * @return 主键
	 */
	public Integer save(Menu menu);

	/**
	 * 修改菜单管理
	 * @param menu Menu对象
	 */
	public void update(Menu menu);

	/**
	 * 根据主键删除菜单
	 * @param id 主键
	 */
	public void delete(Integer id);

	/**
	 * 删除菜单.
	 * @param id 主键
	 */
	public void delete(Menu menu);

	/**
	 * 根据主键查找菜单管理
	 * @param id 主键
	 * @return Menu对象
	 */
	public Menu load(Integer id);

	/**
	 * 根据菜单编号查找菜单.
	 * 
	 * @param menuCode 菜单编号
	 * 
	 * @return Menu对象
	 */
	public Menu load(String menuCode);

	/**
	 * 根据菜单Id获得子菜单信息
	 * @param menuId 菜单ID
	 * @return List Menu对象
	 */
	public List<Menu> listSubMenu(Integer menuId);

	/**
	 * 主键获取菜单对象，查看子节点个数
	 * @param menuId 主键
	 * @return int 子节点个数
	 */
	public int findSubMenuCount(Integer menuId);

	/**
	 * 按操作员权限查询菜单.
	 * 
	 * @param loginVo 登录对象
	 * 
	 * @return Menu对象列表
	 */
	public List<Menu> listByOpPermission(LoginUserVo loginVo);

	/**
	 * 按操作员权限查询菜单.
	 * 
	 * @param loginVo 登录对象
	 * 
	 * @return Menu对象列表
	 */
	public MenuTreeNodeVo findTreeByOpPermission(LoginUserVo loginVo);
	
	/**
	 * 按操作员权限查询菜单.
	 *
	 * @param loginVo 登录对象
	 * @param moduleCode 模块编号
	 * @return Menu对象列表
	 */
	public MenuTreeNodeVo findDomainTreeByOpPermission(LoginUserVo loginVo, String moduleCode);

	/**
	 * 查询操作员拥有的根节点菜单.
	 * 
	 * @param loginVo 登录对象
	 * 
	 * @return List<Menu>
	 */
	public List<Menu> listRootByOpPermission(ILoginVo loginVo);

	/**
	 * 按操作员权限查询菜单.
	 * 
	 * @param domainMenuCode 域菜单编号
	 * @param loginVo 登录对象
	 * 
	 * @return Menu对象列表
	 */
	public List listByOpPermission(ILoginVo loginVo, String domainMenuCode);

	/**
	 * 根据模块编号与功能项编号获得导航菜单.
	 * 
	 * @param moduleCode 模块编号
	 * @param funcCode 功能项编号
	 * @return
	 */
	public List listNav(Locale locale, String moduleCode, String funcCode);

}