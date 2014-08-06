package com.litt.saap.message.po;

import java.util.Date;

import java.io.Serializable;

/**
 * <br>
 * Table:feedback<br>
 * @author Hibernate Tools 3.4.0.CR1
 * @version 1.0
 * @since 2014-6-26 12:33:04
 */
public class Feedback implements Serializable {
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
	 * 内容.
	 */
	private String content;

	/**
	 * 回复.
	 */
	private String reply;

	/**
	 * 创建人.
	 */
	private int createBy;

	/**
	 * 创建时间.
	 */
	private Date createDatetime;

	/**
	 * 回复人.
	 */
	private Integer replyBy;

	/**
	 * 回复时间.
	 */
	private Date replyDatetime;

	public Feedback() {
	}

	public Feedback(int tenantId, int type, String content, int createBy,
			Date createDatetime) {
		this.tenantId = tenantId;
		this.type = type;
		this.content = content;
		this.createBy = createBy;
		this.createDatetime = createDatetime;
	}

	public Feedback(int tenantId, int type, String content, String reply,
			int createBy, Date createDatetime, Integer replyBy,
			Date replyDatetime) {
		this.tenantId = tenantId;
		this.type = type;
		this.content = content;
		this.reply = reply;
		this.createBy = createBy;
		this.createDatetime = createDatetime;
		this.replyBy = replyBy;
		this.replyDatetime = replyDatetime;
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
	 * Get 回复.
	 * @return 回复
	 */
	public String getReply() {
		return this.reply;
	}

	/**
	 * Set 回复.
	 * @param reply 回复
	 */
	public void setReply(String reply) {
		this.reply = reply;
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
	 * Get 回复人.
	 * @return 回复人
	 */
	public Integer getReplyBy() {
		return this.replyBy;
	}

	/**
	 * Set 回复人.
	 * @param replyBy 回复人
	 */
	public void setReplyBy(Integer replyBy) {
		this.replyBy = replyBy;
	}

	/**  
	 * Get 回复时间.
	 * @return 回复时间
	 */
	public Date getReplyDatetime() {
		return this.replyDatetime;
	}

	/**
	 * Set 回复时间.
	 * @param replyDatetime 回复时间
	 */
	public void setReplyDatetime(Date replyDatetime) {
		this.replyDatetime = replyDatetime;
	}

}
