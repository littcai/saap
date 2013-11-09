package com.litt.saap.personal.po;

import java.util.Date;

import java.io.Serializable;

/**
 * 便签<br>
 * 表名：note<br>
 * @author Hibernate Tools 3.2.4.GA
 * @version 1.0
 * @since 2013-10-29 14:05:01
 */
public class Note implements Serializable {
	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 */
	private Integer id;

	/**
	 * 主题.
	 */
	private String title;

	/**
	 * 内容.
	 */
	private String content;

	/**
	 * 操作员ID.
	 */
	private int createUserId;

	/**
	 * 创建时间.
	 */
	private Date createDatetime;

	/**
	 * 最近更新时间.
	 */
	private Date updateDatetime;

	public Note() {
	}

	public Note(String title, int createUserId, Date createDatetime,
			Date updateDatetime) {
		this.title = title;
		this.createUserId = createUserId;
		this.createDatetime = createDatetime;
		this.updateDatetime = updateDatetime;
	}

	public Note(String title, String content, int createUserId,
			Date createDatetime, Date updateDatetime) {
		this.title = title;
		this.content = content;
		this.createUserId = createUserId;
		this.createDatetime = createDatetime;
		this.updateDatetime = updateDatetime;
	}

	/**  
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**  
	 * 取得 主题.
	 * @return 主题
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * 设置 主题.
	 * @param title 主题
	 */
	public void setTitle(String title) {
		this.title = title;
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
	 * 取得 操作员ID.
	 * @return 操作员ID
	 */
	public int getCreateUserId() {
		return this.createUserId;
	}

	/**
	 * 设置 操作员ID.
	 * @param createUserId 操作员ID
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
	 * 取得 最近更新时间.
	 * @return 最近更新时间
	 */
	public Date getUpdateDatetime() {
		return this.updateDatetime;
	}

	/**
	 * 设置 最近更新时间.
	 * @param updateDatetime 最近更新时间
	 */
	public void setUpdateDatetime(Date updateDatetime) {
		this.updateDatetime = updateDatetime;
	}

}
