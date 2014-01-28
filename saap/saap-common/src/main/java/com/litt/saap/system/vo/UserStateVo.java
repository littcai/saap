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
 * @since 2014-1-17
 * @version 1.0
 */
public class UserStateVo implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** 当前租户ID. */
	private int currentTenantId;

	/**
	 * 总登录次数.
	 */
	private int totalLoginTimes;

	/**
	 * 登录重试次数.
	 */
	private int loginRetryTimes;

	/**
	 * 上次登录时间.
	 */
	private Date lastLoginDatetime;

	/**
	 * 上次登录IP.
	 */
	private String lastLoginIp;
	
	/**
	 * Instantiates a new user state vo.
	 */
	public UserStateVo(){}

	/**
	 * Instantiates a new user state vo.
	 *
	 * @param currentTenantId the current tenant id
	 * @param totalLoginTimes the total login times
	 * @param loginRetryTimes the login retry times
	 * @param lastLoginDatetime the last login datetime
	 * @param lastLoginIp the last login ip
	 */
	public UserStateVo(int currentTenantId, int totalLoginTimes,
			int loginRetryTimes, Date lastLoginDatetime, String lastLoginIp) {
		this.currentTenantId = currentTenantId;
		this.totalLoginTimes = totalLoginTimes;
		this.loginRetryTimes = loginRetryTimes;
		this.lastLoginDatetime = lastLoginDatetime;
		this.lastLoginIp = lastLoginIp;
	}

	/**
	 * @return the currentTenantId
	 */
	public int getCurrentTenantId() {
		return currentTenantId;
	}

	/**
	 * @param currentTenantId the currentTenantId to set
	 */
	public void setCurrentTenantId(int currentTenantId) {
		this.currentTenantId = currentTenantId;
	}

	/**
	 * @return the totalLoginTimes
	 */
	public int getTotalLoginTimes() {
		return totalLoginTimes;
	}

	/**
	 * @param totalLoginTimes the totalLoginTimes to set
	 */
	public void setTotalLoginTimes(int totalLoginTimes) {
		this.totalLoginTimes = totalLoginTimes;
	}

	/**
	 * @return the loginRetryTimes
	 */
	public int getLoginRetryTimes() {
		return loginRetryTimes;
	}

	/**
	 * @param loginRetryTimes the loginRetryTimes to set
	 */
	public void setLoginRetryTimes(int loginRetryTimes) {
		this.loginRetryTimes = loginRetryTimes;
	}

	/**
	 * @return the lastLoginDatetime
	 */
	public Date getLastLoginDatetime() {
		return lastLoginDatetime;
	}

	/**
	 * @param lastLoginDatetime the lastLoginDatetime to set
	 */
	public void setLastLoginDatetime(Date lastLoginDatetime) {
		this.lastLoginDatetime = lastLoginDatetime;
	}

	/**
	 * @return the lastLoginIp
	 */
	public String getLastLoginIp() {
		return lastLoginIp;
	}

	/**
	 * @param lastLoginIp the lastLoginIp to set
	 */
	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

}
