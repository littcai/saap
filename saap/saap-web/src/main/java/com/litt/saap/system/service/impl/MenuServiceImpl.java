package com.litt.saap.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;

import com.litt.core.common.BeanManager;
import com.litt.core.shield.vo.ILoginVo;
import com.litt.core.util.ArrayUtils;
import com.litt.core.util.BeanCopier;
import com.litt.core.util.StringUtils;
import com.litt.saap.common.vo.LoginUserVo;
import com.litt.saap.system.dao.MenuDao;
import com.litt.saap.system.po.Menu;
import com.litt.saap.system.service.IMenuService;
import com.litt.saap.system.vo.MenuTreeNodeVo;
import com.litt.saap.system.vo.NavMenuVo;


/**
 * 
 * 菜单管理业务层实现类.
 * <pre><b>描述：</b>
 *    菜单基本信息管理
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">蔡源</a>
 * @since 2009-03-24 12:06:58
 * @version 1.0
 */
public class MenuServiceImpl implements IMenuService 
{ 
	@Resource
	private MenuDao menuDao;
	
	@Resource
	private MessageSource messageSource;
	
   	/* (non-Javadoc)
	 * @see com.litt.saap.system.service.impl.IMenuService#save(com.litt.saap.system.po.Menu)
	 */
	public Integer save(Menu menu)
	{
		return menuDao.save(menu);
	}
	
   	/* (non-Javadoc)
	 * @see com.litt.saap.system.service.impl.IMenuService#update(com.litt.saap.system.po.Menu)
	 */
	public void update(Menu menu)
	{
		menuDao.update(menu);
	}	
		
   
   	/* (non-Javadoc)
	 * @see com.litt.saap.system.service.impl.IMenuService#delete(java.lang.Integer)
	 */
	public void delete(Integer id)
	{
		Menu menu = this.load(id);
		
		this.delete(menu);
	}
	
	/* (non-Javadoc)
	 * @see com.litt.saap.system.service.impl.IMenuService#delete(com.litt.saap.system.po.Menu)
	 */
	public void delete(Menu menu)
	{
		menuDao.delete(menu);		
	}
	
	/* (non-Javadoc)
	 * @see com.litt.saap.system.service.impl.IMenuService#load(java.lang.Integer)
	 */
	public Menu load(Integer id)
	{
		return menuDao.load(id);
	}
	
	/* (non-Javadoc)
	 * @see com.litt.saap.system.service.impl.IMenuService#load(java.lang.String)
	 */
	public Menu load(String menuCode)
	{
		return menuDao.load(Menu.class, "menuCode", menuCode);
	}	

	/* (non-Javadoc)
	 * @see com.litt.saap.system.service.impl.IMenuService#listSubMenu(java.lang.Integer)
	 */
	public List<Menu> listSubMenu(Integer menuId)
	{
		String hql = "from Menu where parentId = ? order by position, menuCode";
		return menuDao.listAll(hql, new Object[]{menuId});
	}
	
	/* (non-Javadoc)
	 * @see com.litt.saap.system.service.impl.IMenuService#findSubMenuCount(java.lang.Integer)
	 */
	public int findSubMenuCount(Integer menuId)
	{
		String hql = "select count(*) from Menu where parentId = ?";
		return menuDao.count(hql,new Object[]{menuId});
	}
	
	/* (non-Javadoc)
	 * @see com.litt.saap.system.service.impl.IMenuService#listByOpPermission(com.litt.saap.common.vo.LoginUserVo)
	 */
	public List<Menu> listByOpPermission(LoginUserVo loginVo)
	{
		String listHql = "select a from Menu a";			
		listHql += " order by parentId, position, menuCode";
		
		List<Menu> menuList = menuDao.listAll(listHql);
		//检查登录用户是否有菜单权限，
		List<Menu> retList = this.getPermittedMenus(loginVo, menuList);			
		return retList;
	}	
	
	/* (non-Javadoc)
	 * @see com.litt.saap.system.service.impl.IMenuService#findTreeByOpPermission(com.litt.saap.common.vo.LoginUserVo)
	 */
	public MenuTreeNodeVo findTreeByOpPermission(LoginUserVo loginVo)
	{
		Locale locale = loginVo.toLocale();
		List<Menu> menuList = this.listByOpPermission(loginVo);
		
		Map<Integer, MenuTreeNodeVo> tempCache = new HashMap<Integer, MenuTreeNodeVo>();	//缓存，可通过menuCode快速取到menu对象
		MenuTreeNodeVo treeNode = new MenuTreeNodeVo();
		for (Menu menu : menuList) {
			MenuTreeNodeVo node = BeanCopier.copy(menu, MenuTreeNodeVo.class);
			node.setMenuName(BeanManager.getMessage("menu."+node.getMenuCode(), locale));	//国际化菜单
			
			tempCache.put(menu.getMenuId(), node);
			if(menu.getParentId()!=0 && tempCache.containsKey(menu.getParentId()))
			{
				MenuTreeNodeVo parentNode = tempCache.get(menu.getParentId());
				parentNode.addSub(node);
			}
			else {
				treeNode.addSub(node);
			}
		}		
		return treeNode;
	}
	
	/**
	 * 按操作员权限查询菜单.
	 *
	 * @param loginVo 登录对象
	 * @param moduleCode 模块编号
	 * @return Menu对象列表
	 */
	public MenuTreeNodeVo findDomainTreeByOpPermission(LoginUserVo loginVo, String moduleCode)
	{
		Locale locale = loginVo.toLocale();
		String domainMenuCode = StringUtils.substring(moduleCode, 0, 2);	
		
		List<Menu> menuList = this.listByOpPermission(loginVo, domainMenuCode);
		
		Map<Integer, MenuTreeNodeVo> tempCache = new HashMap<Integer, MenuTreeNodeVo>();	//缓存，可通过menuCode快速取到menu对象
		MenuTreeNodeVo treeNode = new MenuTreeNodeVo();
		for (Menu menu : menuList) {
			MenuTreeNodeVo node = BeanCopier.copy(menu, MenuTreeNodeVo.class);
			node.setMenuName(BeanManager.getMessage("menu."+node.getMenuCode(), locale));	//国际化菜单
			
			tempCache.put(menu.getMenuId(), node);
			if(menu.getParentId()!=0 && tempCache.containsKey(menu.getParentId()))
			{
				MenuTreeNodeVo parentNode = tempCache.get(menu.getParentId());
				parentNode.addSub(node);
			}
			else {
				treeNode.addSub(node);
			}
		}		
		return treeNode;
	}
	
	/* (non-Javadoc)
	 * @see com.litt.saap.system.service.impl.IMenuService#listRootByOpPermission(com.litt.core.shield.vo.ILoginVo)
	 */
	public List<Menu> listRootByOpPermission(ILoginVo loginVo)
	{
		if(loginVo.isAdministrator())	//如果是超级管理员，则查询所有的根菜单
		{
			return this.listSubMenu(0);	
		}
		else	//根据操作员拥有角色查询根菜单
		{
			List<Menu> menuList = this.listSubMenu(0);	
			//检查登录用户是否有菜单权限，
			List<Menu> retList = getPermittedMenus(loginVo, menuList);
			return retList;
		}
	}

	/**
	 * @param loginVo
	 * @param menuList
	 * @return
	 */
	private List<Menu> getPermittedMenus(ILoginVo loginVo, List<Menu> menuList) {
		List<Menu> retList = new ArrayList<Menu>();
		String[] permissionCodes = loginVo.getPermissionCodes();
		for (Menu menu : menuList) 
		{
			if(ArrayUtils.contains(permissionCodes, menu.getMenuCode()) || loginVo.isAdministrator())
			{
				retList.add(menu);
			}
		}
		return retList;
	}
	
	/* (non-Javadoc)
	 * @see com.litt.saap.system.service.impl.IMenuService#listByOpPermission(com.litt.core.shield.vo.ILoginVo, java.lang.String)
	 */
	public List listByOpPermission(ILoginVo loginVo, String domainMenuCode)
	{
		String listHql = "select a from Menu a where a.menuCode like ? order by a.parentId, a.position, a.menuCode";
		List<Menu> menuList = menuDao.listAll(listHql, new Object[]{domainMenuCode+"%"});
		
		//根据权限过滤
		List<Menu> retList = getPermittedMenus(loginVo, menuList);	
		return retList;		
	}
	
	/* (non-Javadoc)
	 * @see com.litt.saap.system.service.impl.IMenuService#listNav(java.util.Locale, java.lang.String, java.lang.String)
	 */
	public List listNav(Locale locale, String moduleCode, String funcCode)
	{
		List<NavMenuVo> navMenuList = new ArrayList<NavMenuVo>(3);
		//NavMenuVo rootMenu = new NavMenuVo("首页");
		//navMenuList.add(rootMenu);		
		
		//查询模块域信息		
		String[] domainCodes = StringUtils.splitStringAll(moduleCode, 2);		
		String menuCode = "";
		for(String domainCode : domainCodes)
		{
			menuCode += domainCode;
			Menu domainMenu = this.load(menuCode);				
			String localeMenuName = BeanManager.getMessage("menu."+menuCode, null, locale);
			NavMenuVo domainNavMenu = new NavMenuVo(domainMenu.getMenuName());
			navMenuList.add(domainNavMenu);
		}
		//查询当前功能项
		//Module module = moduleDao.load(Module.class, "moduleCode", moduleCode);
		//String hql = "from Func where moduleId=? and funcCode=?";
		//Func func = funcDao.uniqueResult(hql, new Object[]{module.getModuleId(), funcCode}, Func.class);
		
		//3级功能项菜单
		String localeFuncName = BeanManager.getMessage("func."+moduleCode+funcCode, null, locale);	
		NavMenuVo menu3 = new NavMenuVo(localeFuncName);
		navMenuList.add(menu3);
		return navMenuList;
	}
	
}