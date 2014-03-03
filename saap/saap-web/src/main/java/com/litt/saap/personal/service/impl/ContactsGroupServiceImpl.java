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
import com.litt.saap.personal.dao.ContactsGroupDao;
import com.litt.saap.personal.dao.ContactsGroupMemberDao;
import com.litt.saap.personal.po.ContactsGroup;
import com.litt.saap.personal.service.IContactsGroupService;

/**
 * 
 * ContactsGroup service impl.
 * <pre><b>Description：</b>
 *    ContactsGroup
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">Bob.cai</a>
 * @since 2013-09-18
 * @version 1.0
 */
public class ContactsGroupServiceImpl implements IContactsGroupService
{ 
	/** Logger. */
    private static final Logger logger = LoggerFactory.getLogger(ContactsGroupServiceImpl.class);
    
    @Resource
    private ContactsGroupDao contactsGroupDao;
    
    @Resource
    private ContactsGroupMemberDao contactsGroupMemberDao;

   	/**
	 * @param contactsGroup
	 * @return
	 * @throws NotLoginException
	 * @see com.litt.saap.personal.service.IContactsGroupService#save(com.litt.saap.personal.po.ContactsGroup)
	 */
	@Override
	public Integer save(ContactsGroup contactsGroup) throws NotLoginException
	{
		ILoginVo loginVo = LoginUtils.getLoginVo();
		
		contactsGroup.setCreateBy(loginVo.getOpId().intValue());
		contactsGroup.setCreateDatetime(new Date());
		contactsGroup.setUpdateDatetime(contactsGroup.getCreateDatetime());
		
		return contactsGroupDao.save(contactsGroup);
	}
	
   	/**
	 * @param contactsGroup
	 * @throws NotLoginException
	 * @see com.litt.saap.personal.service.IContactsGroupService#update(com.litt.saap.personal.po.ContactsGroup)
	 */
	@Override
	public void update(ContactsGroup contactsGroup) throws NotLoginException
	{				
		this.validatePermission(contactsGroup);		
		
		contactsGroup.setUpdateDatetime(new Date());
		contactsGroupDao.update(contactsGroup);
	}			
   
   	/**
	 * @param id
	 * @throws NotLoginException
	 * @see com.litt.saap.personal.service.IContactsGroupService#delete(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws NotLoginException
	{
		ContactsGroup contactsGroup = this.load(id);
		this.delete(contactsGroup);
	}
	
	/**
	 * @param contactsGroup
	 * @throws NotLoginException
	 * @see com.litt.saap.personal.service.IContactsGroupService#delete(com.litt.saap.personal.po.ContactsGroup)
	 */
	@Override
	public void delete(ContactsGroup contactsGroup) throws NotLoginException 
	{		
		this.validatePermission(contactsGroup);	
		
		contactsGroupMemberDao.deleteByContactsGroup(contactsGroup.getId());
		
		contactsGroupDao.delete(contactsGroup);
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
	private void validatePermission(ContactsGroup contactsGroup) throws NotLoginException 
	{
		ILoginVo loginVo = LoginUtils.getLoginVo();		
		
		if(contactsGroup.getCreateBy()!=loginVo.getOpId().intValue())
		{
			throw new BusiCodeException("error.biz.permissionDenied", loginVo.toLocale());
		}
	}		
	
	/**
	 * @param id
	 * @return
	 * @see com.litt.saap.personal.service.IContactsGroupService#load(java.lang.Integer)
	 */
	@Override
	public ContactsGroup load(Integer id)
	{
		return contactsGroupDao.load(ContactsGroup.class, id);
	}
	
	/**
	 * @param pageParam
	 * @return
	 * @see com.litt.saap.personal.service.IContactsGroupService#listPage(com.litt.core.dao.ql.PageParam)
	 */
	@Override
	public IPageList listPage(PageParam pageParam)
	{
		String listHql = "select obj from ContactsGroup obj"
			+ "-- and obj.createBy={userId}"
			+ "-- and obj.name={name}"
			;	
		return contactsGroupDao.listPage(listHql, pageParam);
	}
	
	/**
	 * @param contactsId
	 * @return
	 * @see com.litt.saap.personal.service.IContactsGroupService#listByContacts(int)
	 */
	@Override
	public List<ContactsGroup> listByContacts(int contactsId)
	{
		String listHql = "select a from ContactsGroup a, ContactsGroupMember b where b.contactsId=? and a.id=b.groupId";
		return contactsGroupDao.listAll(listHql, new Object[]{contactsId});
	}
	
	/**
	 * 查询联系人拥有的组.
	 *
	 * @param contactsId 联系人ID
	 * @return the list
	 */
	public List<ContactsGroup> listByUser(int userId)
	{
		String listHql = "from ContactsGroup where createBy=?";
		return contactsGroupDao.listAll(listHql, new Object[]{userId});
	}
}