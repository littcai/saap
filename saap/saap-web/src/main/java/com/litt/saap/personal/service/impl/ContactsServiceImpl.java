package com.litt.saap.personal.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.litt.core.dao.page.IPageList;
import com.litt.core.dao.ql.PageParam;
import com.litt.core.exception.BusiCodeException;
import com.litt.core.exception.NotLoginException;
import com.litt.core.service.BaseService;
import com.litt.core.shield.vo.ILoginVo;
import com.litt.saap.core.web.util.LoginUtils;
import com.litt.saap.personal.dao.ContactsDao;
import com.litt.saap.personal.dao.ContactsGroupMemberDao;
import com.litt.saap.personal.po.Contacts;
import com.litt.saap.personal.po.ContactsGroupMember;
import com.litt.saap.personal.service.IContactsService;

/**
 * 
 * Contacts service impl.
 * <pre><b>Description：</b>
 *    Contacts
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">Bob.cai</a>
 * @since 2013-09-18
 * @version 1.0
 */
public class ContactsServiceImpl implements IContactsService
{ 
	/** Logger. */
    private static final Logger logger = LoggerFactory.getLogger(ContactsServiceImpl.class);
    
    @Resource
    private ContactsDao contactsDao;
    
    @Resource
    private ContactsGroupMemberDao contactsGroupMemberDao;

   	/**
	 * Save.
	 * @param contacts Contacts
	 * @return id
	 */
	public Integer save(Contacts contacts) throws NotLoginException
	{
		ILoginVo loginVo = LoginUtils.getLoginVo();
		
		contacts.setCreateBy(loginVo.getOpId().intValue());
		contacts.setCreateDatetime(new Date());
		contacts.setUpdateDatetime(contacts.getCreateDatetime());
		
		return contactsDao.save(contacts);
	}
	
   	/**
	 * Update.
	 * @param contacts Contacts
	 */
	public void update(Contacts contacts) throws NotLoginException
	{				
		this.validatePermission(contacts);		
		
		contacts.setUpdateDatetime(new Date());
		contactsDao.update(contacts);
	}			
   
   	/**
	 * Delete by id.
	 * @param id id
	 */
	public void delete(Integer id) throws NotLoginException
	{
		Contacts contacts = this.load(id);
		this.delete(contacts);
	}
	
	/**
	 * Delete by instance.
	 * @param id id
	 */
	public void delete(Contacts contacts) throws NotLoginException 
	{		
		this.validatePermission(contacts);	
		
		contactsGroupMemberDao.deleteByContacts(contacts.getId());
		
		contactsDao.delete(contacts);
	}
	
	/**
	 * Batch delete by ids.
	 * @param ids ids
	 */
	public void deleteBatch(Integer[] ids) 
	{
		if(ids!=null)
		{
			for (Integer id : ids) {
				this.delete(id);
			}
		}
	}
	
	/**
	 * @param todo
	 * @throws NotLoginException
	 */
	private void validatePermission(Contacts contacts) throws NotLoginException 
	{
		ILoginVo loginVo = LoginUtils.getLoginVo();		
		
		if(contacts.getCreateBy()!=loginVo.getOpId().intValue())
		{
			throw new BusiCodeException("error.biz.permissionDenied", loginVo.toLocale());
		}
	}		
	
	/**
	 * Load by id.
	 * @param id id
	 * @return Contacts
	 */
	public Contacts load(Integer id)
	{
		return contactsDao.load(Contacts.class, id);
	}
	
	/**
	 * list by page.
	 * 
	 * @param pageParam params
	 * @return IPageList IPageList
	 */
	public IPageList listPage(PageParam pageParam)
	{
		String listHql = "select obj from Contacts obj"
			+ "-- and obj.createUserId={userId}"
			+ "-- and obj.name={name}"
			+ "-- and obj.mobile={mobile}"
			+ "-- and obj.email={email}"
			;	
		return contactsDao.listPage(listHql, pageParam);
	}
	
	/**
	 * 查询用户个人通讯录.
	 *
	 * @param userId the user id
	 * @return the list
	 */
	public List<Contacts> listByUser(int userId)
	{
		String listHql = "from Contacts where createUserId=?";
		return contactsDao.listAll(listHql, new Object[]{userId});
	}
}