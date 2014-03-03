package com.litt.saap.personal.vo;

import com.litt.saap.personal.po.ContactsGroup;

/**
 * ContactsGroupVo.
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
 * @since 2014年3月3日
 * @version 1.0
 */
public class ContactsGroupVo extends ContactsGroup {
	
	/** The members. */
	private int members;

	/**
	 * @return the members
	 */
	public int getMembers() {
		return members;
	}

	/**
	 * @param members the members to set
	 */
	public void setMembers(int members) {
		this.members = members;
	}

}
