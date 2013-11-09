package com.litt.saap.personal.po;

import java.util.Date;

import java.io.Serializable;

/**
 * 个人日程计划表<br>
 * 表名：calendar<br>
 * @author Hibernate Tools 3.2.4.GA
 * @version 1.0
 * @since 2013-9-13 13:47:20
 */
public class Calendar implements Serializable {
	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 序号.
	 */
	private Integer id;

	/**
	 * 主题.
	 */
	private String subject;

	/**
	 * 类型（3801）
	        1：电话
	        2：Email
	        3：会议
	        4：拜访
	        5：直邮
	        6：短信.
	 */
	private int type;

	/**
	 * 开始时间.
	 */
	private Date startDatetime;

	/**
	 * 结束时间.
	 */
	private Date endDatetime;

	/**
	 * 状态（3802）
	        1：尚未开始
	        2：处理中
	        3：暂停
	        4：已完成
	        5：延迟
	        6：取消.
	 */
	private int status;

	/**
	 * 内容.
	 */
	private String content;

	/**
	 * 是否提醒.
	 */
	private boolean isRemind;

	/**
	 * 提醒时间（提前分钟数）.
	 */
	private Integer remindMinutes;

	/**
	 * 提醒时间
	        计算后的时间，用于查询.
	 */
	private Date remindTime;

	/**
	 * 创建人.
	 */
	private int createUserId;

	/**
	 * 创建时间.
	 */
	private Date createDatetime;

	/**
	 * 更新时间.
	 */
	private Date updateDatetime;

	/**
	 * 是否可重复.
	 */
	private boolean repeatable;

	/**
	 * 重复周期
	        1、日
	        2、周
	        3、月.
	 */
	private Integer repeatPeriod;

	public Calendar() {
	}

	public Calendar(String subject, int type, Date startDatetime,
			Date endDatetime, int status, boolean isRemind, int createUserId,
			Date createDatetime, boolean repeatable) {
		this.subject = subject;
		this.type = type;
		this.startDatetime = startDatetime;
		this.endDatetime = endDatetime;
		this.status = status;
		this.isRemind = isRemind;
		this.createUserId = createUserId;
		this.createDatetime = createDatetime;
		this.repeatable = repeatable;
	}

	public Calendar(String subject, int type, Date startDatetime,
			Date endDatetime, int status, String content, boolean isRemind,
			Integer remindMinutes, Date remindTime, int createUserId,
			Date createDatetime, Date updateDatetime, boolean repeatable,
			Integer repeatPeriod) {
		this.subject = subject;
		this.type = type;
		this.startDatetime = startDatetime;
		this.endDatetime = endDatetime;
		this.status = status;
		this.content = content;
		this.isRemind = isRemind;
		this.remindMinutes = remindMinutes;
		this.remindTime = remindTime;
		this.createUserId = createUserId;
		this.createDatetime = createDatetime;
		this.updateDatetime = updateDatetime;
		this.repeatable = repeatable;
		this.repeatPeriod = repeatPeriod;
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
	 * 取得 类型（3801）
	        1：电话
	        2：Email
	        3：会议
	        4：拜访
	        5：直邮
	        6：短信.
	 * @return 类型（3801）
	        1：电话
	        2：Email
	        3：会议
	        4：拜访
	        5：直邮
	        6：短信
	 */
	public int getType() {
		return this.type;
	}

	/**
	 * 设置 类型（3801）
	        1：电话
	        2：Email
	        3：会议
	        4：拜访
	        5：直邮
	        6：短信.
	 * @param type 类型（3801）
	        1：电话
	        2：Email
	        3：会议
	        4：拜访
	        5：直邮
	        6：短信
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**  
	 * 取得 开始时间.
	 * @return 开始时间
	 */
	public Date getStartDatetime() {
		return this.startDatetime;
	}

	/**
	 * 设置 开始时间.
	 * @param startDatetime 开始时间
	 */
	public void setStartDatetime(Date startDatetime) {
		this.startDatetime = startDatetime;
	}

	/**  
	 * 取得 结束时间.
	 * @return 结束时间
	 */
	public Date getEndDatetime() {
		return this.endDatetime;
	}

	/**
	 * 设置 结束时间.
	 * @param endDatetime 结束时间
	 */
	public void setEndDatetime(Date endDatetime) {
		this.endDatetime = endDatetime;
	}

	/**  
	 * 取得 状态（3802）
	        1：尚未开始
	        2：处理中
	        3：暂停
	        4：已完成
	        5：延迟
	        6：取消.
	 * @return 状态（3802）
	        1：尚未开始
	        2：处理中
	        3：暂停
	        4：已完成
	        5：延迟
	        6：取消
	 */
	public int getStatus() {
		return this.status;
	}

	/**
	 * 设置 状态（3802）
	        1：尚未开始
	        2：处理中
	        3：暂停
	        4：已完成
	        5：延迟
	        6：取消.
	 * @param status 状态（3802）
	        1：尚未开始
	        2：处理中
	        3：暂停
	        4：已完成
	        5：延迟
	        6：取消
	 */
	public void setStatus(int status) {
		this.status = status;
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
	 * 取得 是否提醒.
	 * @return 是否提醒
	 */
	public boolean isIsRemind() {
		return this.isRemind;
	}

	/**
	 * 设置 是否提醒.
	 * @param isRemind 是否提醒
	 */
	public void setIsRemind(boolean isRemind) {
		this.isRemind = isRemind;
	}

	/**  
	 * 取得 提醒时间（提前分钟数）.
	 * @return 提醒时间（提前分钟数）
	 */
	public Integer getRemindMinutes() {
		return this.remindMinutes;
	}

	/**
	 * 设置 提醒时间（提前分钟数）.
	 * @param remindMinutes 提醒时间（提前分钟数）
	 */
	public void setRemindMinutes(Integer remindMinutes) {
		this.remindMinutes = remindMinutes;
	}

	/**  
	 * 取得 提醒时间
	        计算后的时间，用于查询.
	 * @return 提醒时间
	        计算后的时间，用于查询
	 */
	public Date getRemindTime() {
		return this.remindTime;
	}

	/**
	 * 设置 提醒时间
	        计算后的时间，用于查询.
	 * @param remindTime 提醒时间
	        计算后的时间，用于查询
	 */
	public void setRemindTime(Date remindTime) {
		this.remindTime = remindTime;
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

	/**  
	 * 取得 是否可重复.
	 * @return 是否可重复
	 */
	public boolean isRepeatable() {
		return this.repeatable;
	}

	/**
	 * 设置 是否可重复.
	 * @param repeatable 是否可重复
	 */
	public void setRepeatable(boolean repeatable) {
		this.repeatable = repeatable;
	}

	/**  
	 * 取得 重复周期
	        1、日
	        2、周
	        3、月.
	 * @return 重复周期
	        1、日
	        2、周
	        3、月
	 */
	public Integer getRepeatPeriod() {
		return this.repeatPeriod;
	}

	/**
	 * 设置 重复周期
	        1、日
	        2、周
	        3、月.
	 * @param repeatPeriod 重复周期
	        1、日
	        2、周
	        3、月
	 */
	public void setRepeatPeriod(Integer repeatPeriod) {
		this.repeatPeriod = repeatPeriod;
	}

}
