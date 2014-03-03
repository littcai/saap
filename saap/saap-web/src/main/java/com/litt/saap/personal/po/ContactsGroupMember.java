package com.litt.saap.personal.po;

import java.util.Date;

import java.io.Serializable;

/**
 * <br>
 * Table:contacts_group_member<br>
 * @author Hibernate Tools 3.4.0.CR1
 * @version 1.0
 * @since 2014-2-28 17:23:18
 */
public class ContactsGroupMember implements Serializable {
	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 序号.
	 */
	private Integer id;

	/**
	 * 联系人ID.
	 */
	private int contactsId;

	/**
	 * 分组ID.
	 */
	private int groupId;
	
	/** The create by. */
	private int createBy;

	/**
	 * 创建时间.
	 */
	private Date createDatetime;

	public ContactsGroupMember() {
	}

	public ContactsGroupMember(int contactsId, int groupId, int createBy, Date createDatetime) {
		this.contactsId = contactsId;
		this.groupId = groupId;
		this.createBy = createBy;
		this.createDatetime = createDatetime;
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
	 * Get 联系人ID.
	 * @return 联系人ID
	 */
	public int getContactsId() {
		return this.contactsId;
	}

	/**
	 * Set 联系人ID.
	 * @param contactsId 联系人ID
	 */
	public void setContactsId(int contactsId) {
		this.contactsId = contactsId;
	}

	/**  
	 * Get 分组ID.
	 * @return 分组ID
	 */
	public int getGroupId() {
		return this.groupId;
	}

	/**
	 * Set 分组ID.
	 * @param groupId 分组ID
	 */
	public void setGroupId(int groupId) {
		this.groupId = groupId;
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

}
