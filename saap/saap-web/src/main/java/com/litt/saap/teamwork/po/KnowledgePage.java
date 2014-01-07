package com.litt.saap.teamwork.po;

import java.util.Date;

import java.io.Serializable;

/**
 * 知识库<br>
 * 表名：knowledge_page<br>
 * @author Hibernate Tools 3.2.4.GA
 * @version 1.0
 * @since 2014-1-3 11:41:42
 */
public class KnowledgePage implements Serializable {
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
	 * 空间.
	 */
	private int spaceId;

	/**
	 * 父页面ID.
	 */
	private int parentId;

	/**
	 * 主题.
	 */
	private String subject;

	/**
	 * 摘要.
	 */
	private String brief;

	/**
	 * 关键字.
	 */
	private String keywords;

	/**
	 * 内容.
	 */
	private String content;

	/**
	 * 创建人.
	 */
	private int createUserId;

	/**
	 * 创建时间.
	 */
	private Date createDatetime;

	/**
	 * 更新人.
	 */
	private int updateUserId;

	/**
	 * 更新时间.
	 */
	private Date updateDatetime;

	public KnowledgePage() {
	}

	public KnowledgePage(int tenantId, int spaceId, int parentId,
			String subject, String brief, String keywords, int createUserId,
			Date createDatetime, int updateUserId, Date updateDatetime) {
		this.tenantId = tenantId;
		this.spaceId = spaceId;
		this.parentId = parentId;
		this.subject = subject;
		this.brief = brief;
		this.keywords = keywords;
		this.createUserId = createUserId;
		this.createDatetime = createDatetime;
		this.updateUserId = updateUserId;
		this.updateDatetime = updateDatetime;
	}

	public KnowledgePage(int tenantId, int spaceId, int parentId,
			String subject, String brief, String keywords, String content,
			int createUserId, Date createDatetime, int updateUserId,
			Date updateDatetime) {
		this.tenantId = tenantId;
		this.spaceId = spaceId;
		this.parentId = parentId;
		this.subject = subject;
		this.brief = brief;
		this.keywords = keywords;
		this.content = content;
		this.createUserId = createUserId;
		this.createDatetime = createDatetime;
		this.updateUserId = updateUserId;
		this.updateDatetime = updateDatetime;
	}

	/**  
	 * 取得 序号.
	 * @return 序号
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * 设置 序号.
	 * @param id 序号
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**  
	 * 取得 租户ID.
	 * @return 租户ID
	 */
	public int getTenantId() {
		return this.tenantId;
	}

	/**
	 * 设置 租户ID.
	 * @param tenantId 租户ID
	 */
	public void setTenantId(int tenantId) {
		this.tenantId = tenantId;
	}

	/**  
	 * 取得 空间.
	 * @return 空间
	 */
	public int getSpaceId() {
		return this.spaceId;
	}

	/**
	 * 设置 空间.
	 * @param spaceId 空间
	 */
	public void setSpaceId(int spaceId) {
		this.spaceId = spaceId;
	}

	/**  
	 * 取得 父页面ID.
	 * @return 父页面ID
	 */
	public int getParentId() {
		return this.parentId;
	}

	/**
	 * 设置 父页面ID.
	 * @param parentId 父页面ID
	 */
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	/**  
	 * 取得 主题.
	 * @return 主题
	 */
	public String getSubject() {
		return this.subject;
	}

	/**
	 * 设置 主题.
	 * @param subject 主题
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**  
	 * 取得 摘要.
	 * @return 摘要
	 */
	public String getBrief() {
		return this.brief;
	}

	/**
	 * 设置 摘要.
	 * @param brief 摘要
	 */
	public void setBrief(String brief) {
		this.brief = brief;
	}

	/**  
	 * 取得 关键字.
	 * @return 关键字
	 */
	public String getKeywords() {
		return this.keywords;
	}

	/**
	 * 设置 关键字.
	 * @param keywords 关键字
	 */
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	/**  
	 * 取得 内容.
	 * @return 内容
	 */
	public String getContent() {
		return this.content;
	}

	/**
	 * 设置 内容.
	 * @param content 内容
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**  
	 * 取得 创建人.
	 * @return 创建人
	 */
	public int getCreateUserId() {
		return this.createUserId;
	}

	/**
	 * 设置 创建人.
	 * @param createUserId 创建人
	 */
	public void setCreateUserId(int createUserId) {
		this.createUserId = createUserId;
	}

	/**  
	 * 取得 创建时间.
	 * @return 创建时间
	 */
	public Date getCreateDatetime() {
		return this.createDatetime;
	}

	/**
	 * 设置 创建时间.
	 * @param createDatetime 创建时间
	 */
	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	/**  
	 * 取得 更新人.
	 * @return 更新人
	 */
	public int getUpdateUserId() {
		return this.updateUserId;
	}

	/**
	 * 设置 更新人.
	 * @param updateUserId 更新人
	 */
	public void setUpdateUserId(int updateUserId) {
		this.updateUserId = updateUserId;
	}

	/**  
	 * 取得 更新时间.
	 * @return 更新时间
	 */
	public Date getUpdateDatetime() {
		return this.updateDatetime;
	}

	/**
	 * 设置 更新时间.
	 * @param updateDatetime 更新时间
	 */
	public void setUpdateDatetime(Date updateDatetime) {
		this.updateDatetime = updateDatetime;
	}

}
