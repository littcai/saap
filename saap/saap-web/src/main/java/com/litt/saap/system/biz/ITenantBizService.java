package com.litt.saap.system.biz;

import com.litt.saap.system.bo.TenantActiveBo;

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
	public TenantActiveBo doActivate(String orderNo, Integer userId);

}