package com.litt.saap.system.biz;

import java.util.Locale;

import com.litt.saap.system.bo.TenantActiveBo;
import com.litt.saap.system.bo.TenantQuitBo;

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