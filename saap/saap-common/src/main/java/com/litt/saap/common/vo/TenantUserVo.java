package com.litt.saap.common.vo;

import java.io.Serializable;

import com.litt.saap.system.vo.UserInfoVo;

// TODO: Auto-generated Javadoc
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
 * @version 1.0
 * @since 2013-10-17
 */
public class TenantUserVo extends UserInfoVo implements Serializable {
	
	/** 租户ID. */
	private int tenantId;
	
	/** 是否租户管理员. */
	private boolean isAdmin;
	
	/** 所在租户的成员状态. */
	private int memberStatus; 

	/**
	 * Gets the tenant id.
	 *
	 * @return the tenantId
	 */
	public int getTenantId() {
		return tenantId;
	}

	/**
	 * Sets the tenant id.
	 *
	 * @param tenantId the tenantId to set
	 */
	public void setTenantId(int tenantId) {
		this.tenantId = tenantId;
	}

	/**
	 * Gets the checks if is admin.
	 *
	 * @return the isAdmin
	 */
	public boolean getIsAdmin() {
		return isAdmin;
	}

	/**
	 * Sets the checks if is admin.
	 *
	 * @param isAdmin the isAdmin to set
	 */
	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	/**
	 * @return the memberStatus
	 */
	public int getMemberStatus() {
		return memberStatus;
	}

	/**
	 * @param memberStatus the memberStatus to set
	 */
	public void setMemberStatus(int memberStatus) {
		this.memberStatus = memberStatus;
	}	

}
