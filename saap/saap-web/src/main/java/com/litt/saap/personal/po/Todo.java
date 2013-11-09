package com.litt.saap.personal.po;

import java.util.Date;

import java.io.Serializable;

/**
 * 代办事项<br>
 * 表名：todo<br>
 * @author Hibernate Tools 3.2.4.GA
 * @version 1.0
 * @since 2013-9-12 17:34:13
 */
public class Todo implements Serializable {
	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 序号.
	 */
	private Integer id;

	/**
	 * 内容.
	 */
	private String content;

	/**
	 * 状态
	        -2：推迟
	        -1：取消
	        1：未开始
	        2：正在进行
	        3：完成.
	 */
	private int status;

	/**
	 * 是否需要提醒.
	 */
	private boolean isNeedRemind;

	/**
	 * 提醒时间.
	 */
	private Date remindDatetime;

	/**
	 * 代办时间.
	 */
	private Date todoDatetime;

	/**
	 * 创建用户.
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
	 * 图标.
	 */
	private String iconUrl;

	public Todo() {
	}

	public Todo(String content, int status, boolean isNeedRemind,
			Date todoDatetime, int createUserId, Date createDatetime,
			Date updateDatetime) {
		this.content = content;
		this.status = status;
		this.isNeedRemind = isNeedRemind;
		this.todoDatetime = todoDatetime;
		this.createUserId = createUserId;
		this.createDatetime = createDatetime;
		this.updateDatetime = updateDatetime;
	}

	public Todo(String content, int status, boolean isNeedRemind,
			Date remindDatetime, Date todoDatetime, int createUserId,
			Date createDatetime, Date updateDatetime, String iconUrl) {
		this.content = content;
		this.status = status;
		this.isNeedRemind = isNeedRemind;
		this.remindDatetime = remindDatetime;
		this.todoDatetime = todoDatetime;
		this.createUserId = createUserId;
		this.createDatetime = createDatetime;
		this.updateDatetime = updateDatetime;
		this.iconUrl = iconUrl;
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
	 * 取得 状态
	        -2：推迟
	        -1：取消
	        1：未开始
	        2：正在进行
	        3：完成.
	 * @return 状态
	        -2：推迟
	        -1：取消
	        1：未开始
	        2：正在进行
	        3：完成
	 */
	public int getStatus() {
		return this.status;
	}

	/**
	 * 设置 状态
	        -2：推迟
	        -1：取消
	        1：未开始
	        2：正在进行
	        3：完成.
	 * @param status 状态
	        -2：推迟
	        -1：取消
	        1：未开始
	        2：正在进行
	        3：完成
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**  
	 * 取得 是否需要提醒.
	 * @return 是否需要提醒
	 */
	public boolean isIsNeedRemind() {
		return this.isNeedRemind;
	}

	/**
	 * 设置 是否需要提醒.
	 * @param isNeedRemind 是否需要提醒
	 */
	public void setIsNeedRemind(boolean isNeedRemind) {
		this.isNeedRemind = isNeedRemind;
	}

	/**  
	 * 取得 提醒时间.
	 * @return 提醒时间
	 */
	public Date getRemindDatetime() {
		return this.remindDatetime;
	}

	/**
	 * 设置 提醒时间.
	 * @param remindDatetime 提醒时间
	 */
	public void setRemindDatetime(Date remindDatetime) {
		this.remindDatetime = remindDatetime;
	}

	/**  
	 * 取得 代办时间.
	 * @return 代办时间
	 */
	public Date getTodoDatetime() {
		return this.todoDatetime;
	}

	/**
	 * 设置 代办时间.
	 * @param todoDatetime 代办时间
	 */
	public void setTodoDatetime(Date todoDatetime) {
		this.todoDatetime = todoDatetime;
	}

	/**  
	 * 取得 创建用户.
	 * @return 创建用户
	 */
	public int getCreateUserId() {
		return this.createUserId;
	}

	/**
	 * 设置 创建用户.
	 * @param createUserId 创建用户
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
	 * 取得 图标.
	 * @return 图标
	 */
	public String getIconUrl() {
		return this.iconUrl;
	}

	/**
	 * 设置 图标.
	 * @param iconUrl 图标
	 */
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

}
