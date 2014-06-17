package com.litt.saap.crm.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.litt.core.dao.page.IPageList;
import com.litt.core.dao.ql.PageParam;
import com.litt.saap.core.web.util.LoginUtils;
import com.litt.saap.crm.dao.CustActivityDao;
import com.litt.saap.crm.po.CustActivity;
import com.litt.saap.crm.service.ICustActivityService;

/**
 * 
 * Activity service impl.
 * <pre><b>Description：</b>
 *    Customer Activities Management
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">Bob.cai</a>
 * @since 2014-05-19
 * @version 1.0
 */
public class CustActivityServiceImpl implements ICustActivityService
{ 
	/** Logger. */
    private static final Logger logger = LoggerFactory.getLogger(CustActivityServiceImpl.class);
    
    @Resource
    private CustActivityDao custActivityDao;

   	/**
	 * Save.
	 * @param custActivity CustActivity
	 * @return id
	 */
	public Integer save(CustActivity custActivity)
	{
		custActivity.setCreateDatetime(new Date());
		custActivity.setUpdateBy(custActivity.getCreateBy());
		custActivity.setUpdateDatetime(custActivity.getCreateDatetime());
		
		return custActivityDao.save(custActivity);
	}
	
   	/**
	 * Update.
	 * @param custActivity CustActivity
	 */
	public void update(CustActivity custActivity) 
	{
		//校验租户权限
		LoginUtils.validateTenant(custActivity.getTenantId());
	
		custActivity.setUpdateDatetime(new Date());
		custActivityDao.update(custActivity);
	}			
   
   	/**
	 * Delete by id.
	 * @param id id
	 */
	public void delete(Integer id) 
	{
		CustActivity custActivity = this.load(id);
		this.delete(custActivity);
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
	public void delete(CustActivity custActivity) 
	{
		//校验租户权限
		LoginUtils.validateTenant(custActivity.getTenantId());
	
		custActivityDao.delete(custActivity);
	}
	
	/**
	 * Load by id.
	 * @param id id
	 * @return CustActivity
	 */
	public CustActivity load(Integer id) 
	{
		CustActivity custActivity = custActivityDao.load(id);
		//校验租户权限
		LoginUtils.validateTenant(custActivity.getTenantId());
	
		return custActivity;
	}
	
	/**
	 * list by page.
	 * 
	 * @param pageParam params
	 * @return IPageList IPageList
	 */
	public IPageList listPage(PageParam pageParam)
	{
		String listHql = "select new map(obj as custActivity, t2 as customer, t3 as custContacts, chargeUser as chargeUser) from CustActivity obj, Customer t2, CustContacts t3, UserInfo chargeUser"
			+ "-- and obj.tenantId={tenantId}"
			+ "-- and obj.customerId=t2.id and obj.contactId=t3.id and obj.chargeBy=chargeUser.id"
			;	
		return custActivityDao.listPage(listHql, pageParam);
	}
}