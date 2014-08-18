package com.litt.saap.core.module.tenant.config;

import java.math.BigDecimal;
import java.util.Arrays;

import com.litt.core.util.ArrayUtils;

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
	
	/** 价格. */
	private BigDecimal price;
	
	/** 最大成员数. */
	private int maxMembers;
	
	/** 最大存储容量. */
	private int maxStorage;
	
	/** 模块数. */
	private int modules;
	
	/** 支持. */
	private String support;
	
	/** 拥有功能模块权限. */
	private String[] permissions;
	
	/** 默认角色及其对应权限. */
	private TenantRoleConfig[] roles;
	
	/**
	 * 是否包含权限.
	 *
	 * @param permissionCode the permission code
	 * @return true, if successful
	 */
	public boolean containsPermission(String permissionCode)
	{
		return ArrayUtils.contains(permissions, permissionCode);
	}
	
	public TenantRoleConfig getRoleByCode(String code)
	{
		for (TenantRoleConfig role : roles) {
			if(role.getCode().equals(code))
				return role;
		}
		return null;
	}
	
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

  
  /**
   * @return the price
   */
  public BigDecimal getPrice()
  {
    return price;
  }

  
  /**
   * @param price the price to set
   */
  public void setPrice(BigDecimal price)
  {
    this.price = price;
  }

  
  /**
   * @return the maxStorage
   */
  public int getMaxStorage()
  {
    return maxStorage;
  }

  
  /**
   * @param maxStorage the maxStorage to set
   */
  public void setMaxStorage(int maxStorage)
  {
    this.maxStorage = maxStorage;
  }

  
  /**
   * @return the support
   */
  public String getSupport()
  {
    return support;
  }

  
  /**
   * @param support the support to set
   */
  public void setSupport(String support)
  {
    this.support = support;
  }

  
  /**
   * @return the modules
   */
  public int getModules()
  {
    return modules;
  }

  
  /**
   * @param modules the modules to set
   */
  public void setModules(int modules)
  {
    this.modules = modules;
  }

	

}
