package com.litt.saap.core.module.tenant.config;

import java.util.Arrays;

/**
 * 租户默认角色.
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
public class TenantRoleConfig {
	
	private String code;
	
	/** 拥有功能模块权限. */
	private String[] permissions;
	
	
	public boolean isAdmin()
	{
		return "admin".equals(code);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TenantRoleConfig [code=").append(code)
				.append(", permissions=").append(Arrays.toString(permissions))
				.append("]");
		return builder.toString();
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

}
