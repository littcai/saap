package com.litt.saap.system.biz.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.litt.core.exception.BusiCodeException;
import com.litt.core.security.EncryptFailedException;
import com.litt.core.security.MessageDigestTool;
import com.litt.core.shield.vo.ILoginVo;
import com.litt.core.util.ArrayUtils;
import com.litt.core.util.BeanCopier;
import com.litt.core.util.DateUtils;
import com.litt.core.util.StringUtils;
import com.litt.saap.core.common.SaapConstants;
import com.litt.saap.core.common.SaapConstants.TenantMemberStatus;
import com.litt.saap.core.common.SaapConstants.TenantStatus;
import com.litt.saap.core.module.tenant.config.TenantDefConfig;
import com.litt.saap.core.module.tenant.config.TenantRoleConfig;
import com.litt.saap.core.module.tenant.config.TenantTypeConfig;
import com.litt.saap.core.module.tenant.config.TenantTypeConfigManager;
import com.litt.saap.core.web.util.LoginUtils;
import com.litt.saap.system.biz.ITenantBizService;
import com.litt.saap.system.bo.TenantActiveBo;
import com.litt.saap.system.bo.TenantQuitBo;
import com.litt.saap.system.dao.RoleFuncPermissionDao;
import com.litt.saap.system.dao.TenantDao;
import com.litt.saap.system.dao.TenantMemberDao;
import com.litt.saap.system.dao.UserRoleDao;
import com.litt.saap.system.dao.UserStateDao;
import com.litt.saap.system.po.Role;
import com.litt.saap.system.po.RoleFuncPermission;
import com.litt.saap.system.po.Tenant;
import com.litt.saap.system.po.TenantMember;
import com.litt.saap.system.po.TenantOrder;
import com.litt.saap.system.po.UserInfo;
import com.litt.saap.system.po.UserRole;
import com.litt.saap.system.po.UserState;
import com.litt.saap.system.service.IRoleService;
import com.litt.saap.system.service.ITenantMemberService;
import com.litt.saap.system.service.ITenantOrderService;
import com.litt.saap.system.service.ITenantService;
import com.litt.saap.system.service.IUserInfoService;
import com.litt.saap.system.vo.PermissionTreeVo;
import com.litt.saap.system.vo.TenantVo;

/**
 * 租户业务服务层实现.
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
 * @since 2013-11-14
 * @version 1.0
 */
public class TenantBizServiceImpl implements ITenantBizService {
	
	private static final Logger logger = LoggerFactory.getLogger(TenantBizServiceImpl.class);
	
	@Resource
	private ITenantOrderService tenantOrderService;	
	@Resource
	private ITenantService tenantService;	
	@Resource
	private TenantDao tenantDao;
	@Resource
	private TenantMemberDao tenantMemberDao;
	@Resource
	private ITenantMemberService tenantMemberService;
	@Resource
	private IRoleService roleService;
	@Resource
	private RoleFuncPermissionDao roleFuncPermissionDao;
	@Resource
	private UserStateDao userStateDao;
	@Resource
	private UserRoleDao userRoleDao;
	@Resource
	private IUserInfoService userInfoService;
	
	public void saveMember(UserInfo userInfo)
	{
		String loginId = userInfo.getLoginId();
		ILoginVo loginVo = LoginUtils.getLoginVo();
		
		int tenantId = LoginUtils.getTenantId();
		//data validte
		this.validate(0, loginId);
		//保存用户信息
		userInfo.setStatus(SaapConstants.UserStatus.NORMAL);
		userInfo.setLocale(loginVo.getLocale());
		userInfo.setTimezone(loginVo.getTimezone());
		userInfo.setTheme(loginVo.getTheme());
		Integer userId = userInfoService.save(userInfo);
		//加入到租户
		TenantMember tenantMember = new TenantMember();
		tenantMember.setTenantId(tenantId);
		tenantMember.setAppId(SaapConstants.PLATFORM_APP_ID);
		tenantMember.setUserId(userId);
		tenantMember.setIsAdmin(false);
		tenantMember.setStatus(TenantMemberStatus.NORMAL);
		tenantMember.setCreateUserId(userId);
		tenantMember.setCreateDatetime(new Date());
		tenantMember.setUpdateUserId(userId);
		tenantMember.setUpdateDatetime(tenantMember.getCreateDatetime());
		tenantMemberService.save(tenantMember);
		//
		//设置用户的当前租户为新激活的租户
		UserState userState = userStateDao.load(userId);		
		userState.setCurrentTenantId(tenantId);
		userStateDao.update(userState);
		
		//添加默认的个人事务角色（自动赋予权限）
		UserRole userRole = new UserRole(0, userId, SaapConstants.DEFAULT_ROLE_ID, loginVo.getOpId().intValue());
		userRoleDao.save(userRole);
		//默认绑定租户成员角色权限
		Role role = roleService.load(tenantId, SaapConstants.DefaultRoleName.MEMBER, 9);
		userRole = new UserRole(tenantId, userId, role.getId(), loginVo.getOpId().intValue());
		userRoleDao.save(userRole);
	}
	
	public void updateMember(TenantMember tenantMember, UserInfo userInfo, String newpassword)
	{
		//data validte
		this.validate(userInfo.getId(), userInfo.getLoginId());
				
		//MD5加密密码
		if(!StringUtils.isEmpty(newpassword))
		{
			try
			{
				String encryptPassoword = MessageDigestTool.encryptMD5(newpassword);
				userInfo.setPassword(encryptPassoword);
			}
			catch (EncryptFailedException e)
			{
				throw new BusiCodeException("error.login.failed", e);
			}	
		}
		
		userInfoService.update(userInfo);
		tenantMemberService.update(tenantMember);
	}
	
	public void deleteMember(Integer tenantMemberId)
	{
		TenantMember tenantMember = tenantMemberService.load(tenantMemberId);
		tenantMember.setStatus(TenantMemberStatus.DELETED);
		tenantMemberService.update(tenantMember);
		userInfoService.deleteLogic(tenantMember.getUserId());
	}
	
	public void deleteMemberBatch(Integer[] ids)
	{
		for (Integer id : ids) {
			this.deleteMember(id);
		}
	}
	
	public void doResumeMember(Integer tenantMemberId)
	{
		TenantMember tenantMember = tenantMemberService.load(tenantMemberId);
		tenantMember.setStatus(TenantMemberStatus.NORMAL);
		tenantMemberService.update(tenantMember);
		userInfoService.doResume(tenantMember.getUserId());
	}
	
	/* (non-Javadoc)
	 * @see com.litt.saap.system.biz.impl.ITenantBizService#activate(java.lang.String, java.lang.Integer)
	 */
	public TenantActiveBo doActivate(String orderNo, Integer userId, Locale locale)
	{
		//激活订单本身
		tenantOrderService.doActivate(orderNo);
		
		TenantOrder tenantOrder = tenantOrderService.loadByNo(orderNo);
		//激活租户空间
		String bagCode = tenantOrder.getBagCode();	//此处应该从订单中获得购买了哪个类型的产品包		
		
		TenantDefConfig tenantDefConfig = TenantTypeConfigManager.getInstance().getTenantDefConfig(bagCode);
				
		Tenant tenant = new Tenant();
		tenant.setTenantCode(tenantOrder.getTenantCode());
		tenant.setTenantAlias(tenantOrder.getTenantAlias());	
		tenant.setBagCode(bagCode);
		tenant.setStatus(TenantStatus.NORMAL);	//直接激活		
		tenant.setId(tenant.getCreateBy());
		tenant.setMaxMembers(tenantDefConfig.getMaxMembers());	
		tenant.setIsolatedMode(tenantOrder.getIsolatedMode());
		tenant.setTrialDays(30);	//默认试用30天
		Date expiredDate = DateUtils.getBeAfMonth(tenantOrder.getQuantity());	//单位一个月，数量为时长
		tenant.setExpiredDate(expiredDate);		
		tenant.setCreateBy(userId);
		tenant.setCreateDatetime(new Date());
		tenant.setUpdateDatetime(tenant.getCreateDatetime());
		
		Integer tenantId = tenantDao.save(tenant);
		//保存租户成员
		TenantMember tenantMember = new TenantMember();
		tenantMember.setAppId(SaapConstants.PLATFORM_APP_ID);
		tenantMember.setTenantId(tenantId);
		tenantMember.setUserId(userId);
		tenantMember.setIsAdmin(true);
		tenantMember.setCreateUserId(userId);
		tenantMember.setCreateDatetime(tenant.getCreateDatetime());
		tenantMember.setUpdateUserId(userId);
		tenantMember.setUpdateDatetime(tenantMember.getCreateDatetime());
		tenantMember.setStatus(TenantMemberStatus.NORMAL);
		
		tenantMemberDao.save(tenantMember);		
		
		//对租户插入默认角色
		Integer adminRoleId = null;
		String[] adminPermissions = new String[0];
		TenantRoleConfig[] roleConfig = tenantDefConfig.getRoles();
		for (TenantRoleConfig tenantRoleConfig : roleConfig) {
			//插入角色的权限
			String[] permissions = tenantDefConfig.getPermissions();
			
			Role role = new Role();
			role.setTenantId(tenantId);
			role.setName(tenantRoleConfig.getCode());	//BeanManager.getMessage("tenant.config.role."+tenantRoleConfig.getCode(), locale)
			role.setStatus(9);		
			roleService.save(role, permissions);
			Integer roleId = role.getId();
			
			//取到管理员的权限，赋给租户拥有者
			if(tenantRoleConfig.isAdmin())
			{
				adminRoleId = roleId;
				adminPermissions = permissions;
			}
		}		
		
		//设置用户的当前租户为新激活的租户
		UserState userState = userStateDao.load(userId);		
		userState.setCurrentTenantId(tenantId);
		userStateDao.update(userState);
		
		//绑定用户和角色
		UserRole userRole = new UserRole(tenantId, userId, adminRoleId, userId);
		userRoleDao.save(userRole);
		
		//更新订单中的租户ID
		tenantOrder.setTenantId(tenantId);
		tenantOrderService.update(tenantOrder);
		
		TenantVo tenantVo = new TenantVo(tenant.getId(), tenant.getTenantCode(), tenant.getTenantAlias(), true, tenant.getExpiredDate());
		TenantActiveBo ret = new TenantActiveBo(tenantVo, adminRoleId, adminPermissions);
		
		return ret;
	}
	
	/**
	 * 升级用户权限.
	 * 
	 * 系统升级后，可能增加或移除的一些功能，需要对现有用户的权限做相应的升级操作。
	 *
	 * @param tenantConfig the tenant config
	 * @return the tenant active bo
	 */
	public void doUpgradePermission(TenantTypeConfig tenantConfig)
	{
		logger.info("Upgrade tenant permissions start...");
		List<Tenant> tenantList = tenantDao.listAll();
		for (Tenant tenant : tenantList) {
			logger.info("Upgrade tenant permissions for:{}...", new Object[]{tenant.getId(), tenant.getTenantAlias()});
			
			Integer tenantId = tenant.getId();
			List<Role> roleList = roleService.listByTenant(tenantId);
			
			TenantDefConfig tenantDefConfig = TenantTypeConfigManager.getInstance().getTenantDefConfig(tenant.getBagCode());
			
			for (Role role : roleList) {
				Integer roleId = role.getId();			
				//获取所有的Role及对应的permission
				List<RoleFuncPermission> permissionList = roleFuncPermissionDao.listByTenantAndRole(tenantId, roleId);
				String[] dbPermissions = new String[permissionList.size()];
				
				List<RoleFuncPermission> deleteList = new ArrayList<RoleFuncPermission>();
				int index=0;
				for (RoleFuncPermission roleFuncPermission : permissionList) 
				{
					dbPermissions[index] = roleFuncPermission.getPermissionCode();
					if(!tenantDefConfig.containsPermission(roleFuncPermission.getPermissionCode()))
					{
						//该权限需删除
						logger.info("remove permission:{}", new Object[]{roleFuncPermission.getPermissionCode()});
						deleteList.add(roleFuncPermission);
					}
					index++;
				}
				//把新增的权限加到现有角色上
				if(role.getStatus()==9)	//系统内置角色
				{			
					TenantDefConfig newTenantDefConfig = tenantConfig.getTenantDef(tenant.getBagCode());
					
					//TenantRoleConfig oldRoleConfig = tenantDefConfig.getRoleByCode(role.getName());	//TODO 系统覆盖更新没法获取旧的配置，改为与数据库对比
					TenantRoleConfig newRoleConfig = newTenantDefConfig.getRoleByCode(role.getName());
					//对比两个配置，获得新的权限编号
					List<String> addPermissionList = new ArrayList<String>();
					String[] oldPermissions = dbPermissions;
					String[] newPermissions = newRoleConfig.getPermissions();
					for (String permissionCode : newPermissions) {
						if(!ArrayUtils.contains(oldPermissions, permissionCode))
						{
							logger.info("add permission:{}", new Object[]{permissionCode});
							addPermissionList.add(permissionCode);
						}
					}
					//保存新的			
					if(!addPermissionList.isEmpty())
					{
						RoleFuncPermission[] newEntities = new RoleFuncPermission[addPermissionList.size()]; 
						int i=0;
						for (String permissionCode : addPermissionList) {
							RoleFuncPermission entity = new RoleFuncPermission(tenantId, roleId, permissionCode);
							newEntities[i] = entity;
							i++;
						}
						roleFuncPermissionDao.saveBatch(newEntities);
					}
					
				}
			}					
		}
		logger.info("Upgrade tenant permissions end...");
		//重新加载当前配置实例，变成最新的
		TenantTypeConfigManager.getInstance().reload();
	}
	
	/**
	 * 注销租户空间.
	 *
	 * @param userId 用户ID
	 * @param tenantId 租户ID
	 * @return the tenant quit bo
	 */
	public TenantQuitBo doDeactivate(Integer userId, Integer tenantId)
	{		
		Tenant tenant = tenantDao.load(tenantId);
		if(tenant==null || tenant.getCreateBy()!=userId)	
		{
			throw new BusiCodeException("tenant.func.deactivate.error.notCreator");
		}
		//退出用户是租户创建人，则允许是解散租户
		TenantVo tenantVo = BeanCopier.copy(tenant, TenantVo.class);
		TenantQuitBo tenantQuit = getTenantQuitBo(userId, tenantVo);		
		
		//TODO 需要通知已经在线的租户成员空间被注销，并删除他们的权限
		List<TenantMember> tenantMemberList = tenantMemberDao.listByTenant(tenantId);
		for (TenantMember tenantMember : tenantMemberList) {
			//if(!tenantMember.getUserId().equals(tenant.getCreateUserId()))	//保留创建人，其他成员均删除
			{
				//删除用户角色
				userRoleDao.deleteByTenantUser(tenantId, tenantMember.getUserId());
				//删除成员信息
				tenantMemberDao.delete(tenantMember);
			}
		}		
		//最终设置租户状态为删除
		tenant.setStatus(TenantStatus.CANCELED);
		tenantService.update(tenant);
		
		return tenantQuit;
	}
	
	/**
	 * 用户退出指定租户.
	 * 
	 * @param tenantId
	 * @param userId
	 * @return
	 * @see com.litt.saap.system.biz.ITenantBizService#doQuit(int, int)
	 */
	public TenantQuitBo doQuit(int tenantId, int userId)
	{		
		TenantVo tenantVo = tenantService.findById(tenantId);
		TenantMember tenantMember = tenantMemberDao.loadByUserAndTenant(userId, tenantId);
		if(tenantMember==null)	//如果不是该租户成员，则直接返回
			return null;
		//退出用户是租户创建人，则是解散租户
		if(tenantMember.getCreateUserId()==userId)	
		{
			throw new BusiCodeException("tenantMember.error.creatorQuit");
		}
		//检查是否还剩其他管理员存在，没有的话禁止退出，否则无法对租户进行管理		
		if(tenantMember.getIsAdmin())
		{
			int adminCount = tenantMemberDao.countAdminByTenant(tenantId);
			if(adminCount==1)
				throw new BusiCodeException("tenant.error.lastAdmin");
		}
		//执行退出操作
		tenantMemberDao.delete(tenantMember);
		//读取该用户在该租户空间下的权限，需要删除
		TenantQuitBo tenantQuit = getTenantQuitBo(userId, tenantVo);
		
		//删除用户角色
		userRoleDao.deleteByTenantUser(tenantId, userId);
		return tenantQuit;
	}

	/**
	 * @param tenantId
	 * @param userId
	 * @param tenantVo
	 * @return
	 */
	private TenantQuitBo getTenantQuitBo(int userId, TenantVo tenantVo) {
		int tenantId = tenantVo.getId();
		List<UserRole> userRoleList = userRoleDao.listByTenantUser(tenantId, userId);
		Integer[] roleIds = new Integer[userRoleList.size()];
		int index = 0;
		for (UserRole userRole : userRoleList) {
			roleIds[index] = userRole.getRoleId();
			index++;
		}				
		
		TenantDefConfig tenantDefConfig = TenantTypeConfigManager.getInstance().getTenantDefConfig(tenantVo.getBagCode());
		String[] permissions = tenantDefConfig.getPermissions();
				
		TenantQuitBo tenantQuit = new TenantQuitBo(tenantVo, roleIds, permissions);
		return tenantQuit;
	}
	
	/**
	 * 数据有效性检查.
	 *
	 * @param id the id
	 * @param name the name
	 */
	private void validate(Integer id, String loginId)
	{		
		int tenantId = LoginUtils.getTenantId();
		
		String countHql = "select count(u) from UserInfo u, TenantMember m where m.tenantId=? and m.userId=u.id and u.loginId=? and (u.id>? or u.id<?)";
		boolean invalid = tenantMemberDao.count(countHql, new Object[]{tenantId, loginId, id, id})>0;		
		if(invalid)
		{
			throw new BusiCodeException("userInfo.error.loginIdDuplicated");
		}
		
	}
	
	/**
	 * 获得租户权限集.
	 * 格式：domain.module.func
	 * 	   domain.subDomain.module.func
	 * @param tenantId the tenant id
	 */
	public PermissionTreeVo findTenantPermissionTree(int tenantId)
	{
		Tenant tenant = tenantService.load(tenantId);
		TenantDefConfig tenantDefConfig = TenantTypeConfigManager.getInstance().getTenantDefConfig(tenant.getBagCode());
		String[] permissions = tenantDefConfig.getPermissions();
		
		PermissionTreeVo tree = PermissionTreeVo.newRoot();
		Map<String, PermissionTreeVo> cache = new HashMap<String, PermissionTreeVo>(permissions.length);
		
		for (String code : permissions) {
			String[] codeArr = {""};
			if(code.indexOf(".") <= 0)
				codeArr[0] = code;
			else
				codeArr = code.split("\\.");
			
			//域（domain）
			if(codeArr.length == 1)
			{
				PermissionTreeVo domain = PermissionTreeVo.newDomain(code);
				tree.add(domain);
				cache.put(code, domain);
			}
			
			//模块(module/subDomain)
			else if(codeArr.length == 2)	//4位可能是module，也可能是二层domain
			{
				PermissionTreeVo module = PermissionTreeVo.newModule(code);
				//String domainCode = StringUtils.substring(code, 0, 2);
				PermissionTreeVo domain = cache.get(codeArr[0]);
				if(domain!=null)
				{
					domain.add(module);
					cache.put(code, module);
				}
			}
			
			//操作功能（func/module）
			else if(codeArr.length == 3)	//6位可能是func，也可能是module
			{
				PermissionTreeVo func = PermissionTreeVo.newFunc(code);
				String domainCode = codeArr[0];
				String moduleCode = codeArr[1];
				PermissionTreeVo module = cache.get(domainCode + "." + moduleCode);
				if(module!=null)
				{
					module.add(func);
					cache.put(code, func);
				}
			}
			
			//操作功能（func）
			else if(codeArr.length == 4)	//8位只能是func（目前仅支持3层菜单）
			{
				PermissionTreeVo func = PermissionTreeVo.newFunc(code);
				String domainCode = codeArr[0];
				String subDomainCode = codeArr[1];
				String moduleCode = codeArr[2];
				
				PermissionTreeVo subDomain = cache.get(domainCode + "." + subDomainCode);
				if(subDomain!=null)
				{
					if(subDomain.getType()!=PermissionTreeVo.DOMAIN)	//更新类型为domain
					{
						subDomain.setType(PermissionTreeVo.DOMAIN);
					}
					
					PermissionTreeVo module = cache.get(domainCode + "." + subDomainCode + "." + moduleCode);
					if(module!=null)
					{
						if(module.getType()!=PermissionTreeVo.MODULE)	//更新类型为domain
						{
							module.setType(PermissionTreeVo.MODULE);
						}						
						module.add(func);
						cache.put(code, func);
					}
				}
			}
		}
		
		return tree;
	}
	
	/**
	 * 获得租户权限集.
	 *
	 * @param tenantId the tenant id
	 */
	public PermissionTreeVo findTenantPermissionTree(int tenantId, int roleId)
	{
		
		Tenant tenant = tenantService.load(tenantId);
		TenantDefConfig tenantDefConfig = TenantTypeConfigManager.getInstance().getTenantDefConfig(tenant.getBagCode());
		String[] permissions = tenantDefConfig.getPermissions();
		
		PermissionTreeVo tree = PermissionTreeVo.newRoot();
		Map<String, PermissionTreeVo> cache = new HashMap<String, PermissionTreeVo>(permissions.length);
		
		for (String code : permissions) {
			String[] codeArr = {""};
			if(code.indexOf(".") <= 0)
				codeArr[0] = code;
			else
				codeArr = code.split("\\.");
			
			//域（domain）
			if(codeArr.length == 1)
			{
				PermissionTreeVo domain = PermissionTreeVo.newDomain(code);
				tree.add(domain);
				cache.put(code, domain);
			}
			
			//模块(module/subDomain)
			else if(codeArr.length == 2)	//4位可能是module，也可能是二层domain
			{
				PermissionTreeVo module = PermissionTreeVo.newModule(code);
				//String domainCode = StringUtils.substring(code, 0, 2);
				PermissionTreeVo domain = cache.get(codeArr[0]);
				if(domain!=null)
				{
					domain.add(module);
					cache.put(code, module);
				}
			}
			
			//操作功能（func/module）
			else if(codeArr.length == 3)	//6位可能是func，也可能是module
			{
				PermissionTreeVo func = PermissionTreeVo.newFunc(code);
				String domainCode = codeArr[0];
				String moduleCode = codeArr[1];
				PermissionTreeVo module = cache.get(domainCode + "." + moduleCode);
				if(module!=null)
				{
					module.add(func);
					cache.put(code, func);
				}
			}
			
			//操作功能（func）
			else if(codeArr.length == 4)	//8位只能是func（目前仅支持3层菜单）
			{
				PermissionTreeVo func = PermissionTreeVo.newFunc(code);
				String domainCode = codeArr[0];
				String subDomainCode = codeArr[1];
				String moduleCode = codeArr[2];
				
				PermissionTreeVo subDomain = cache.get(domainCode + "." + subDomainCode);
				if(subDomain!=null)
				{
					if(subDomain.getType()!=PermissionTreeVo.DOMAIN)	//更新类型为domain
					{
						subDomain.setType(PermissionTreeVo.DOMAIN);
					}
					
					PermissionTreeVo module = cache.get(domainCode + "." + subDomainCode + "." + moduleCode);
					if(module!=null)
					{
						if(module.getType()!=PermissionTreeVo.MODULE)	//更新类型为domain
						{
							module.setType(PermissionTreeVo.MODULE);
						}						
						module.add(func);
						cache.put(code, func);
					}
				}
			}
		}
		
		//读取角色已经拥有的权限，设置选中状态
		List<RoleFuncPermission> dbPermissionList = roleFuncPermissionDao.listByTenantAndRole(tenantId, roleId);
				
		for (RoleFuncPermission roleFuncPermission : dbPermissionList) {
			PermissionTreeVo node = cache.get(roleFuncPermission.getPermissionCode());
			if(node!=null)
			{
				node.setChecked(true);
			}
		}
		
		return tree;
	}

}
