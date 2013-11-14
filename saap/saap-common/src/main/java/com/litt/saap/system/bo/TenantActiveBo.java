package com.litt.saap.system.bo;

import com.litt.saap.system.vo.TenantVo;

/**
 * 租户开通后返回的信息.
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
public class TenantActiveBo {
	
	private TenantVo tenant;
	
	private Integer roleId;
	
	private String[] permissionCodes;	
	
	public TenantActiveBo(){}

	/**
	 * @param tenant
	 * @param roleId
	 * @param permissionCodes
	 */
	public TenantActiveBo(TenantVo tenant, Integer roleId,
			String[] permissionCodes) {
		this.tenant = tenant;
		this.roleId = roleId;
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

	/**
	 * @return the roleId
	 */
	public Integer getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
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

}
