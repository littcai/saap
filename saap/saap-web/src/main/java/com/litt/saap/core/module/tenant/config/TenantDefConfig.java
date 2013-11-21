package com.litt.saap.core.module.tenant.config;

import java.util.Arrays;

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
public class TenantDefConfig {
	
	/** 编号（唯一）. */
	private String code;
	
	/** 最大成员数. */
	private int maxMembers;
	
	/** 拥有功能模块权限. */
	private String[] permissions;
	
	/** 默认角色及其对应权限. */
	private TenantRoleConfig[] roles;
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TenantDefConfig [code=").append(code)
				.append(", maxMembers=").append(maxMembers)
				.append(", permissions=").append(Arrays.toString(permissions))
				.append(", roles=").append(Arrays.toString(roles))
				.append("]");
		return builder.toString();
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the maxMembers
	 */
	public int getMaxMembers() {
		return maxMembers;
	}

	/**
	 * @param maxMembers the maxMembers to set
	 */
	public void setMaxMembers(int maxMembers) {
		this.maxMembers = maxMembers;
	}

	/**
	 * @return the permissions
	 */
	public String[] getPermissions() {
		return permissions;
	}

	/**
	 * @param permissions the permissions to set
	 */
	public void setPermissions(String[] permissions) {
		this.permissions = permissions;
	}

	/**
	 * @return the roles
	 */
	public TenantRoleConfig[] getRoles() {
		return roles;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(TenantRoleConfig[] roles) {
		this.roles = roles;
	}

	

}
