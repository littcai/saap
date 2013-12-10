package com.litt.saap.system.bo;

import com.litt.saap.system.vo.TenantVo;

/**
 * 用户退出租户服务返回的信息.
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
public class TenantQuitBo {
	
	private TenantVo tenant;
	
	private Integer[] roleIds;
	
	private String[] permissionCodes;	
	
	public TenantQuitBo(){}

	/**
	 * @param tenantId
	 * @param roleId
	 * @param permissionCodes
	 */
	public TenantQuitBo(TenantVo tenant, Integer[] roleId,
			String[] permissionCodes) {
		this.tenant = tenant;
		this.roleIds = roleId;
		this.permissionCodes = permissionCodes;
	}

	/**
	 * @return the roleIds
	 */
	public Integer[] getRoleIds() {
		return roleIds;
	}

	/**
	 * @param roleIds the roleIds to set
	 */
	public void setRoleIds(Integer[] roleIds) {
		this.roleIds = roleIds;
	}

	/**
	 * @return the permissionCodes
	 */
	public String[] getPermissionCodes() {
		return permissionCodes;
	}

	/**
	 * @param permissionCodes the permissionCodes to set
	 */
	public void setPermissionCodes(String[] permissionCodes) {
		this.permissionCodes = permissionCodes;
	}

	/**
	 * @return the tenant
	 */
	public TenantVo getTenant() {
		return tenant;
	}

	/**
	 * @param tenant the tenant to set
	 */
	public void setTenant(TenantVo tenant) {
		this.tenant = tenant;
	}

	

}
