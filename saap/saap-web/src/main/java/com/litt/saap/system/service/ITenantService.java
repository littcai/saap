package com.litt.saap.system.service;

import java.util.List;

import com.litt.saap.system.po.Tenant;
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
 * @since 2013-11-20
 * @version 1.0
 */
public interface ITenantService {
	
	/**
	 * Update.
	 *
	 * @param tenant the tenant
	 */
	public void update(Tenant tenant);
	
	/**
	 * Update.
	 *
	 * @param tenantId the tenant id
	 * @param appAlias the app alias
	 */
	public void update(int tenantId, String appAlias);

	/**
	 * 加入租户.
	 *
	 * @param tenantId the tenant id
	 * @param userId the user id
	 * @param roleId the role id
	 */
	public void doJoin(int tenantId, int userId, int roleId);
	
	/**
	 * 检查用户是否已是某个租户的成员.
	 *
	 * @param userId the user id
	 * @return true, if is tenant member
	 */
	public boolean isTenantMember(int tenantId, int userId);
	
	/**
	 * @param tenantId
	 * @return
	 */
	public Tenant load(Integer tenantId);
	
	public TenantVo findById(Integer tenantId);
	
	/**
	 * 查找用户是其成员的租户信息.
	 *
	 * @param userId the user id
	 * @return the list
	 */
	public List<TenantVo> findByMemberId(int userId);

}