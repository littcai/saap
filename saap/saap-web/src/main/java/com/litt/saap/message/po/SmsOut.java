package com.litt.saap.message.po;

import java.util.Date;

import java.io.Serializable;

/**
 * <br>
 * Table:sms_out<br>
 * @author Hibernate Tools 3.4.0.CR1
 * @version 1.0
 * @since 2014-2-17 14:47:59
 */
public class SmsOut implements Serializable {
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
	 * 发送人.
	 */
	private String sender;

	/**
	 * 接收人.
	 */
	private String receiver;

	/**
	 * 内容.
	 */
	private String content;

	/**
	 * 发送标志.
	 */
	private boolean sendFlag;
	
	/**
	 * 创建人.
	 */
	private int createBy;

	/**
	 * 创建时间.
	 */
	private Date createDatetime;

	/**
	 * 发送时间.
	 */
	private Date sendDatetime;

	public SmsOut() {
	}

	public SmsOut(int tenantId, String sender, String receiver, String content,
			boolean sendFlag, Date createDatetime) {
		this.tenantId = tenantId;
		this.sender = sender;
		this.receiver = receiver;
		this.content = content;
		this.sendFlag = sendFlag;
		this.createDatetime = createDatetime;
	}

	public SmsOut(int tenantId, String sender, String receiver, String content,
			boolean sendFlag, Date createDatetime, Date sendDatetime) {
		this.tenantId = tenantId;
		this.sender = sender;
		this.receiver = receiver;
		this.content = content;
		this.sendFlag = sendFlag;
		this.createDatetime = createDatetime;
		this.sendDatetime = sendDatetime;
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
	 * Get 发送人.
	 * @return 发送人
	 */
	public String getSender() {
		return this.sender;
	}

	/**
	 * Set 发送人.
	 * @param sender 发送人
	 */
	public void setSender(String sender) {
		this.sender = sender;
	}

	/**  
	 * Get 接收人.
	 * @return 接收人
	 */
	public String getReceiver() {
		return this.receiver;
	}

	/**
	 * Set 接收人.
	 * @param receiver 接收人
	 */
	public void setReceiver(String receiver) {
		this.receiver = receiver;
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
	 * Get 发送标志.
	 * @return 发送标志
	 */
	public boolean isSendFlag() {
		return this.sendFlag;
	}

	/**
	 * Set 发送标志.
	 * @param sendFlag 发送标志
	 */
	public void setSendFlag(boolean sendFlag) {
		this.sendFlag = sendFlag;
	}
	
	/**
	 * @return the createBy
	 */
	public int getCreateBy() {
		return createBy;
	}

	/**
	 * @param createBy the createBy to set
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
	 * Get 发送时间.
	 * @return 发送时间
	 */
	public Date getSendDatetime() {
		return this.sendDatetime;
	}

	/**
	 * Set 发送时间.
	 * @param sendDatetime 发送时间
	 */
	public void setSendDatetime(Date sendDatetime) {
		this.sendDatetime = sendDatetime;
	}

}
