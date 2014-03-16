package com.litt.saap.system.biz;

import java.util.Locale;

import com.litt.saap.core.module.tenant.config.TenantTypeConfig;
import com.litt.saap.core.module.tenant.config.TenantDefConfig;
import com.litt.saap.system.bo.TenantActiveBo;
import com.litt.saap.system.bo.TenantQuitBo;
import com.litt.saap.system.po.TenantMember;
import com.litt.saap.system.po.UserInfo;

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
 * @since 2013-11-14
 * @version 1.0
 */
public interface ITenantBizService {
	
	public void saveMember(UserInfo userInfo);
	
	public void updateMember(TenantMember tenantMember, UserInfo userInfo);
	
	public void deleteMember(Integer tenantMemberId);
	
	public void deleteMemberBatch(Integer[] ids);

	/**
	 * 根据订单号激活.
	 * 
	 * FIXME 订单号已经能知道是哪个用户，但是订单模块没做，因此用户ID还需传入
	 *
	 * @param orderNo the order no
	 * @param userId the user id
	 */
	public TenantActiveBo doActivate(String orderNo, Integer userId, Locale locale);
	
	/**
	 * 升级用户权限.
	 * 
	 * 系统升级后，可能增加或移除的一些功能，需要对现有用户的权限做相应的升级操作。
	 *
	 * @param tenantConfig the tenant config
	 * @return the tenant active bo
	 */
	public void doUpgradePermission(TenantTypeConfig tenantConfig);
	
	/**
	 * 注销租户空间.
	 *
	 * @param userId 用户ID
	 * @param tenantId 租户ID
	 */
	public TenantQuitBo doDeactivate(Integer userId, Integer tenantId);
	
	/**
	 * Do quit.
	 *
	 * @param tenantId the tenant id
	 * @param userId the user id
	 * @return the tenant quit bo
	 */
	public TenantQuitBo doQuit(int tenantId, int userId);

}