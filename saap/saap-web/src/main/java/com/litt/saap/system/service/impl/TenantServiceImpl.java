package com.litt.saap.system.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.litt.core.exception.BusiCodeException;
import com.litt.core.util.BeanCopier;
import com.litt.saap.core.common.SaapConstants;
import com.litt.saap.core.common.SaapConstants.TenantMemberStatus;
import com.litt.saap.system.dao.RoleDao;
import com.litt.saap.system.dao.TenantDao;
import com.litt.saap.system.dao.TenantMemberDao;
import com.litt.saap.system.dao.UserRoleDao;
import com.litt.saap.system.po.Role;
import com.litt.saap.system.po.Tenant;
import com.litt.saap.system.po.TenantMember;
import com.litt.saap.system.po.UserRole;
import com.litt.saap.system.service.ITenantService;
import com.litt.saap.system.vo.TenantVo;

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
 * @since 2013-11-19
 * @version 1.0
 */
public class TenantServiceImpl implements ITenantService {
	
	@Resource
	private TenantDao tenantDao;
	
	@Resource
	private TenantMemberDao tenantMemberDao;
	
	@Resource
	private UserRoleDao userRoleDao;
	
	@Resource
	private RoleDao roleDao;
	
	/**
	 * Update.
	 *
	 * @param tenant the tenant
	 */
	public void update(Tenant tenant)
	{
		tenant.setUpdateDatetime(new Date());
		tenantDao.update(tenant);
	}
	
	
	/* (non-Javadoc)
	 * @see com.litt.saap.system.service.impl.ITenantService#doJoin(int, int, int)
	 */
	public void doJoin(int tenantId, int userId, int roleId)
	{
		Role role = roleDao.load(roleId);
		if(role.getTenantId()!=tenantId)
			throw new BusiCodeException("join.error.notTenantRole");
		Tenant tenant = tenantDao.load(tenantId);
		int memberCount = tenantMemberDao.countByTenant(tenantId);
		if(memberCount>=tenant.getMaxMembers())
		{
			throw new BusiCodeException("tenant.error.maxMembers");
		}
		//校验用户是否已是成员
		TenantMember member = tenantMemberDao.loadByUserAndTenant(userId, tenantId);
		if(member!=null)
		{
			throw new BusiCodeException("tenant.error.isJoined");
		}
		//确认加入
		TenantMember newMember = new TenantMember();
		newMember.setAppId(SaapConstants.PLATFORM_APP_ID);
		newMember.setTenantId(tenantId);
		newMember.setUserId(userId);
		newMember.setIsAdmin(false);
		newMember.setCreateUserId(userId);
		newMember.setCreateDatetime(tenant.getCreateDatetime());
		newMember.setUpdateUserId(userId);
		newMember.setUpdateDatetime(newMember.getCreateDatetime());
		newMember.setStatus(TenantMemberStatus.NORMAL);
		
		tenantMemberDao.save(newMember);
		
		//绑定用户和角色
		UserRole userRole = new UserRole(tenantId, userId, roleId, userId);
		userRoleDao.save(userRole);
	}
	
	/**
	 * 检查用户是否已是某个租户的成员.
	 *
	 * @param tenantId the tenant id
	 * @param userId the user id
	 * @return true, if is tenant member
	 */
	public boolean isTenantMember(int tenantId, int userId)
	{
		return tenantMemberDao.isTenantMember(tenantId, userId);
	}
	
	public TenantVo findById(Integer tenantId)
	{
		Tenant po = load(tenantId);
		return convertVo(po);
	}


	/**
	 * @param tenantId
	 * @return
	 */
	public Tenant load(Integer tenantId) {
		return tenantDao.load(tenantId);
	}


	/**
	 * @param po
	 * @return
	 */
	private TenantVo convertVo(Tenant po) {
		if(po==null)
			return null;
		else {
			return BeanCopier.copy(po, TenantVo.class);
		}
	}
	
	/**
	 * 查找用户是其成员的租户信息.
	 *
	 * @param userId the user id
	 * @return the list
	 */
	public List<TenantVo> findByMemberId(int userId)
	{
		List<Tenant> tenantList = tenantDao.listByMember(userId);		
		List<TenantVo> tenantVoList = new ArrayList<TenantVo>(tenantList.size());
		for (Tenant tenant : tenantList) {
			TenantVo vo = this.convertVo(tenant);
			if(vo.getCreateUserId()==userId)
			{
				vo.setIsAdmin(true);
			}
			tenantVoList.add(vo);
		}
		return tenantVoList;
	}

}
