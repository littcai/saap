package com.litt.saap.system.vo;

import java.io.Serializable;
import java.util.Date;

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
 * @since 2013-10-14
 * @version 1.0
 */
public class TenantVo implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private String code;
	
	private String appCode;
	
	/** 别名. */
	private String appAlias;
	
	/** 是否租户管理员. */
	private boolean isAdmin;
	
	/** 租赁到期日. */
	private Date expiredDate;

	/**
	 * 
	 */
	public TenantVo() {
	}	

	/**
	 * @param id
	 * @param code
	 * @param appCode
	 * @param isAdmin
	 * @param expiredDate
	 */
	public TenantVo(Integer id, String code, String appCode, String appAlias, boolean isAdmin,
			Date expiredDate) {
		this.id = id;
		this.code = code;
		this.appCode = appCode;
		this.appAlias = appAlias;
		this.isAdmin = isAdmin;
		this.expiredDate = expiredDate;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the appCode
	 */
	public String getAppCode() {
		return appCode;
	}

	/**
	 * @param appCode the appCode to set
	 */
	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}
	
	/**
	 * @return the isAdmin
	 */
	public boolean getIsAdmin() {
		return isAdmin;
	}

	/**
	 * @param isAdmin the isAdmin to set
	 */
	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}	

	/**
	 * @return the expiredDate
	 */
	public Date getExpiredDate() {
		return expiredDate;
	}

	/**
	 * @param expiredDate the expiredDate to set
	 */
	public void setExpiredDate(Date expiredDate) {
		this.expiredDate = expiredDate;
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
	 * @return the appAlias
	 */
	public String getAppAlias() {
		return appAlias;
	}

	/**
	 * @param appAlias the appAlias to set
	 */
	public void setAppAlias(String appAlias) {
		this.appAlias = appAlias;
	}
	
}
