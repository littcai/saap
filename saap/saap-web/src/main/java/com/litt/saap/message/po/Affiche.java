package com.litt.saap.message.po;

import java.util.Date;

import java.io.Serializable;

/**
 * <br>
 * Table:affiche<br>
 * @author Hibernate Tools 3.4.0.CR1
 * @version 1.0
 * @since 2014-6-26 11:32:29
 */
public class Affiche implements Serializable {
	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 序号.
	 */
	private Integer id;

	/**
	 * 租户ID.
	 */
	private int tenantId;

	/**
	 * 类型.
	 */
	private int type;

	/**
	 * 图标类型.
	 */
	private String iconType;

	/**
	 * 标题.
	 */
	private String title;

	/**
	 * 内容.
	 */
	private String content;

	/**
	 * 过期日期.
	 */
	private Date expiredDate;

	/**
	 * 是否已审核.
	 */
	private boolean isChecked;

	/**
	 * 创建人.
	 */
	private int createBy;

	/**
	 * 创建时间.
	 */
	private Date createDatetime;

	/**
	 * 更新人.
	 */
	private int updateBy;

	/**
	 * 更新时间.
	 */
	private Date updateDatetime;

	public Affiche() {
	}

	public Affiche(int tenantId, int type, String title, String content,
			boolean isChecked, int createBy, Date createDatetime, int updateBy,
			Date updateDatetime) {
		this.tenantId = tenantId;
		this.type = type;
		this.title = title;
		this.content = content;
		this.isChecked = isChecked;
		this.createBy = createBy;
		this.createDatetime = createDatetime;
		this.updateBy = updateBy;
		this.updateDatetime = updateDatetime;
	}

	public Affiche(int tenantId, int type, String iconType, String title,
			String content, Date expiredDate, boolean isChecked, int createBy,
			Date createDatetime, int updateBy, Date updateDatetime) {
		this.tenantId = tenantId;
		this.type = type;
		this.iconType = iconType;
		this.title = title;
		this.content = content;
		this.expiredDate = expiredDate;
		this.isChecked = isChecked;
		this.createBy = createBy;
		this.createDatetime = createDatetime;
		this.updateBy = updateBy;
		this.updateDatetime = updateDatetime;
	}

	/**  
	 * Get 序号.
	 * @return 序号
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * Set 序号.
	 * @param id 序号
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**  
	 * Get 租户ID.
	 * @return 租户ID
	 */
	public int getTenantId() {
		return this.tenantId;
	}

	/**
	 * Set 租户ID.
	 * @param tenantId 租户ID
	 */
	public void setTenantId(int tenantId) {
		this.tenantId = tenantId;
	}

	/**  
	 * Get 类型.
	 * @return 类型
	 */
	public int getType() {
		return this.type;
	}

	/**
	 * Set 类型.
	 * @param type 类型
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**  
	 * Get 图标类型.
	 * @return 图标类型
	 */
	public String getIconType() {
		return this.iconType;
	}

	/**
	 * Set 图标类型.
	 * @param iconType 图标类型
	 */
	public void setIconType(String iconType) {
		this.iconType = iconType;
	}

	/**  
	 * Get 标题.
	 * @return 标题
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * Set 标题.
	 * @param title 标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**  
	 * Get 内容.
	 * @return 内容
	 */
	public String getContent() {
		return this.content;
	}

	/**
	 * Set 内容.
	 * @param content 内容
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**  
	 * Get 过期日期.
	 * @return 过期日期
	 */
	public Date getExpiredDate() {
		return this.expiredDate;
	}

	/**
	 * Set 过期日期.
	 * @param expiredDate 过期日期
	 */
	public void setExpiredDate(Date expiredDate) {
		this.expiredDate = expiredDate;
	}

	/**  
	 * Get 是否已审核.
	 * @return 是否已审核
	 */
	public boolean getIsChecked() {
		return this.isChecked;
	}

	/**
	 * Set 是否已审核.
	 * @param isChecked 是否已审核
	 */
	public void setIsChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}

	/**  
	 * Get 创建人.
	 * @return 创建人
	 */
	public int getCreateBy() {
		return this.createBy;
	}

	/**
	 * Set 创建人.
	 * @param createBy 创建人
	 */
	public void setCreateBy(int createBy) {
		this.createBy = createBy;
	}

	/**  
	 * Get 创建时间.
	 * @return 创建时间
	 */
	public Date getCreateDatetime() {
		return this.createDatetime;
	}

	/**
	 * Set 创建时间.
	 * @param createDatetime 创建时间
	 */
	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	/**  
	 * Get 更新人.
	 * @return 更新人
	 */
	public int getUpdateBy() {
		return this.updateBy;
	}

	/**
	 * Set 更新人.
	 * @param updateBy 更新人
	 */
	public void setUpdateBy(int updateBy) {
		this.updateBy = updateBy;
	}

	/**  
	 * Get 更新时间.
	 * @return 更新时间
	 */
	public Date getUpdateDatetime() {
		return this.updateDatetime;
	}

	/**
	 * Set 更新时间.
	 * @param updateDatetime 更新时间
	 */
	public void setUpdateDatetime(Date updateDatetime) {
		this.updateDatetime = updateDatetime;
	}

}
