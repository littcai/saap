package com.litt.saap.crm.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.litt.core.dao.page.IPageList;
import com.litt.core.dao.ql.PageParam;
import com.litt.saap.core.web.util.LoginUtils;
import com.litt.saap.crm.dao.CustContactsDao;
import com.litt.saap.crm.po.CustContacts;
import com.litt.saap.crm.service.ICustContactsService;
import com.litt.saap.crm.webservice.ICustContactsWebService;

/**
 * 
 * Contacts service impl.
 * <pre><b>Description：</b>
 *    Customer Contacts Management
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">Bob.cai</a>
 * @since 2013-10-29
 * @version 1.0
 */
public class CustContactsServiceImpl implements ICustContactsService, ICustContactsWebService
{ 
	/** Logger. */
    private static final Logger logger = LoggerFactory.getLogger(CustContactsServiceImpl.class);
    
    @Resource
    private CustContactsDao custContactsDao;

   	/**
	 * Save.
	 * @param custContacts CustContacts
	 * @return id
	 */
	public Integer save(CustContacts custContacts)
	{
		custContacts.setTenantId(LoginUtils.getTenantId());
		custContacts.setCreateDatetime(new Date());
		custContacts.setCreateBy(LoginUtils.getLoginOpId().intValue());
		custContacts.setUpdateDatetime(custContacts.getCreateDatetime());
		
		return custContactsDao.save(custContacts);
	}
	
   	/**
	 * Update.
	 * @param custContacts CustContacts
	 */
	public void update(CustContacts custContacts)
	{
		//校验租户权限
		LoginUtils.validateTenant(custContacts.getTenantId());
		
		custContacts.setUpdateDatetime(new Date());
		custContactsDao.update(custContacts);
	}			
   
   	/**
	 * Delete by id.
	 * @param id id
	 */
	public void delete(Integer id)
	{
		CustContacts custContacts = this.load(id);
		
		custContactsDao.delete(custContacts);
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
	 * Delete by instance.
	 * @param id id
	 */
	public void delete(CustContacts custContacts)
	{
		//校验租户权限
		LoginUtils.validateTenant(custContacts.getTenantId());
		custContactsDao.delete(custContacts);
	}
	
	/**
	 * Load by id.
	 * @param id id
	 * @return CustContacts
	 */
	public CustContacts load(Integer id)
	{
		CustContacts custContacts = custContactsDao.load(id);
		//校验租户权限
		LoginUtils.validateTenant(custContacts.getTenantId());
		return custContacts;
	}
	
	/**
	 * list by page.
	 * 
	 * @param pageParam params
	 * @return IPageList IPageList
	 */
	public IPageList listPage(PageParam pageParam)
	{
		String listHql = "select new map(obj as custContacts, t1 as customer) from CustContacts obj, Customer t1"
			+ "-- and obj.tenantId={tenantId}"		
			+ "-- and obj.customerId=t1.id"	
			+ "-- and obj.name like {name%}"
			+ "-- and obj.mobile like {mobile%}"
			+ "-- and obj.email={email}"
			+ "-- and t1.name like {customerName%}"
			;	
		return custContactsDao.listPage(listHql, pageParam);
	}
	
	/**
	 * List by customer.
	 *
	 * @param customerId the customer id
	 * @return the list
	 */
	public List<CustContacts> listByCustomer(Integer customerId)
	{
		String listHql = "from CustContacts where tenantId=? and customerId=?";
		return custContactsDao.listAll(listHql, new Object[]{LoginUtils.getTenantId(), customerId});
	}
}