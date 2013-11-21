package com.litt.saap.system.service;

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
	public boolean isTenantMember(int userId);

}