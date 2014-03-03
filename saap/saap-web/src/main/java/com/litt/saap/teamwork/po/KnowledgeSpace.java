package com.litt.saap.teamwork.po;

import java.util.Date;

import java.io.Serializable;

/**
 * 知识库空间<br>
 * 表名：knowledge_space<br>
 * @author Hibernate Tools 3.2.4.GA
 * @version 1.0
 * @since 2014-1-3 11:41:42
 */
public class KnowledgeSpace implements Serializable {
	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 */
	private Integer id;

	/**
	 */
	private int tenantId;

	/**
	 */
	private String code;

	/**
	 */
	private String name;

	/**
	 */
	private String descr;

	/**
	 */
	private int createUserId;

	/**
	 */
	private Date createDatetime;

	/**
	 */
	private int updateUserId;

	/**
	 */
	private Date updateDatetime;

	public KnowledgeSpace() {
	}

	public KnowledgeSpace(int tenantId, String code, String name,
			int createUserId, Date createDatetime, int updateUserId,
			Date updateDatetime) {
		this.tenantId = tenantId;
		this.code = code;
		this.name = name;
		this.createUserId = createUserId;
		this.createDatetime = createDatetime;
		this.updateUserId = updateUserId;
		this.updateDatetime = updateDatetime;
	}

	public KnowledgeSpace(int tenantId, String code, String name, String descr,
			int createUserId, Date createDatetime, int updateUserId,
			Date updateDatetime) {
		this.tenantId = tenantId;
		this.code = code;
		this.name = name;
		this.descr = descr;
		this.createUserId = createUserId;
		this.createDatetime = createDatetime;
		this.updateUserId = updateUserId;
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
	 */
	public int getTenantId() {
		return this.tenantId;
	}

	/**
	 */
	public void setTenantId(int tenantId) {
		this.tenantId = tenantId;
	}

	/**  
	 */
	public String getCode() {
		return this.code;
	}

	/**
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**  
	 */
	public String getName() {
		return this.name;
	}

	/**
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**  
	 */
	public String getDescr() {
		return this.descr;
	}

	/**
	 */
	public void setDescr(String descr) {
		this.descr = descr;
	}

	/**  
	 */
	public int getCreateUserId() {
		return this.createUserId;
	}

	/**
	 */
	public void setCreateUserId(int createUserId) {
		this.createUserId = createUserId;
	}

	/**  
	 */
	public Date getCreateDatetime() {
		return this.createDatetime;
	}

	/**
	 */
	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	/**  
	 */
	public int getUpdateUserId() {
		return this.updateUserId;
	}

	/**
	 */
	public void setUpdateUserId(int updateUserId) {
		this.updateUserId = updateUserId;
	}

	/**  
	 */
	public Date getUpdateDatetime() {
		return this.updateDatetime;
	}

	/**
	 */
	public void setUpdateDatetime(Date updateDatetime) {
		this.updateDatetime = updateDatetime;
	}

}
