package com.litt.saap.personal.bo;

import java.io.Serializable;
import java.util.List;

import com.litt.saap.personal.vo.ContactsGroupVo;
import com.litt.saap.personal.vo.ContactsVo;


/**
 * ContactsGroupBo.
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
 * @since 2014年3月4日
 * @version 1.0
 */
public class ContactsGroupBo implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private ContactsGroupVo contactsGroup;
	
	private List<ContactsVo> contactsList;
	
	public ContactsGroupBo(){};

	public ContactsGroupBo(ContactsGroupVo contactsGroup,
			List<ContactsVo> contactsList) {
		super();
		this.contactsGroup = contactsGroup;
		this.contactsList = contactsList;
		this.contactsGroup.setMembers(this.contactsList.size());
	}

	/**
	 * @return the contactsGroup
	 */
	public ContactsGroupVo getContactsGroup() {
		return contactsGroup;
	}

	/**
	 * @param contactsGroup the contactsGroup to set
	 */
	public void setContactsGroup(ContactsGroupVo contactsGroup) {
		this.contactsGroup = contactsGroup;
	}

	/**
	 * @return the contactsList
	 */
	public List<ContactsVo> getContactsList() {
		return contactsList;
	}

	/**
	 * @param contactsList the contactsList to set
	 */
	public void setContactsList(List<ContactsVo> contactsList) {
		this.contactsList = contactsList;
		this.contactsGroup.setMembers(this.contactsList.size());
	}

}
