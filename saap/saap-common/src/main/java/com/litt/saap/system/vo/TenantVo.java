package com.litt.saap.system.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 租户基本信息.
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
	
	private String tenantCode;
	
	/** 别名. */
	private String tenantAlias;
	
	/** 功能包编号. */
	private String bagCode;

	/**
	 * 租用类型
	        1：完全共享型
	        2：独立Schema
	        3：独立数据库.
	 */
	private int isolatedMode;

	/**
	 * 状态
	        -3：到期
	        -2：禁用
	        -1：删除
	        0：尚未激活
	        1：启用.
	 */
	private int status;

	/**
	 * 创建用户ID.
	 */
	private int createBy;

	/**
	 * 创建时间.
	 */
	private Date createDatetime;

	/**
	 * 更新时间.
	 */
	private Date updateDatetime;

	/**
	 * 最大成员数.
	 */
	private int maxMembers;	
	
	/**
   * 最大存储容量.
   */
  private int maxStorage;
	
	/** 租赁到期日. */
	private Date expiredDate;
	
	/**
   * 试用期限.
   */
  private int trialDays;
		
	/** The logo url. */
	private String logoUrl;

	
	/** 是否租户管理员. */
	private boolean isAdmin;
	
	/**
	 * 
	 */
	public TenantVo() {
	}	

	/**
	 * @param id
	 * @param code
	 * @param tenantCode
	 * @param isAdmin
	 * @param expiredDate
	 */
	public TenantVo(Integer id, String tenantCode, String tenantAlias, boolean isAdmin,
			Date expiredDate) {
		this.id = id;
		this.tenantCode = tenantCode;
		this.tenantAlias = tenantAlias;
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
	 * @return the tenantCode
	 */
	public String getTenantCode() {
		return tenantCode;
	}

	/**
	 * @param tenantCode the tenantCode to set
	 */
	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
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
	 * @return the tenantAlias
	 */
	public String getTenantAlias() {
		return tenantAlias;
	}

	/**
	 * @param tenantAlias the tenantAlias to set
	 */
	public void setTenantAlias(String tenantAlias) {
		this.tenantAlias = tenantAlias;
	}

	/**
	 * @return the bagCode
	 */
	public String getBagCode() {
		return bagCode;
	}

	/**
	 * @param bagCode the bagCode to set
	 */
	public void setBagCode(String bagCode) {
		this.bagCode = bagCode;
	}

	/**
	 * @return the isolatedMode
	 */
	public int getIsolatedMode() {
		return isolatedMode;
	}

	/**
	 * @param isolatedMode the isolatedMode to set
	 */
	public void setIsolatedMode(int isolatedMode) {
		this.isolatedMode = isolatedMode;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @return the createUserId
	 */
	public int getCreateBy() {
		return createBy;
	}

	/**
	 * @param createUserId the createUserId to set
	 */
	public void setCreateBy(int createUserId) {
		this.createBy = createUserId;
	}

	/**
	 * @return the createDatetime
	 */
	public Date getCreateDatetime() {
		return createDatetime;
	}

	/**
	 * @param createDatetime the createDatetime to set
	 */
	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	/**
	 * @return the updateDatetime
	 */
	public Date getUpdateDatetime() {
		return updateDatetime;
	}

	/**
	 * @param updateDatetime the updateDatetime to set
	 */
	public void setUpdateDatetime(Date updateDatetime) {
		this.updateDatetime = updateDatetime;
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
	 * @return the logoUrl
	 */
	public String getLogoUrl() {
		return logoUrl;
	}

	/**
	 * @param logoUrl the logoUrl to set
	 */
	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
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
   * @return the trialDays
   */
  public int getTrialDays()
  {
    return trialDays;
  }

  
  /**
   * @param trialDays the trialDays to set
   */
  public void setTrialDays(int trialDays)
  {
    this.trialDays = trialDays;
  }
	
}
