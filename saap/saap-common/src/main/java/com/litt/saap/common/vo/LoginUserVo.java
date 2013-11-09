package com.litt.saap.common.vo;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.litt.core.shield.vo.BaseLoginVo;
import com.litt.core.shield.vo.ILoginVo;
import com.litt.saap.system.vo.TenantVo;

/**
 * 
 * 
 * 登录用户对象.
 * 
 * <pre><b>描述：</b>
 *    该对象用来存储当前登录用户的相关信息
 * </pre>
 * 
 * <pre><b>修改记录：</b>
 *    
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">蔡源</a>
 * @since 2013-09-02
 * @version 1.0
 *
 */
public class LoginUserVo extends BaseLoginVo implements ILoginVo
{
	/**
	 * The Constant serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/** 租户信息. */
	private TenantVo tenant;	
	
	/**
	 * 构造函数.
	 * 
	 * @param userId 登录用户ID
	 * @param loginId 登录名
	 * @param userName 操作员名称
	 * @param loginIp 登录IP
	 */
	public LoginUserVo(Long userId, String loginId, String userName, String loginIp)
	{
		super(userId, loginId, userName, loginIp);
	}
	
	/**
	 * @return the tenant
	 */
	public Integer getTenantId() {
		return tenant==null?-1:tenant.getId();
	}

	/**
	 * Sets the tenant.
	 *
	 * @param tenant the new tenant
	 */
	public void setTenant(TenantVo tenant)
	{
		this.tenant = tenant;
	}

	/**
	 * @return the tenant
	 */
	public TenantVo getTenant() {
		return tenant;
	}
}
