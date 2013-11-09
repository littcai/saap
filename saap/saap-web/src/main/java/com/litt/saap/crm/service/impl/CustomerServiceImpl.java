package com.litt.saap.crm.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.litt.core.dao.BaseJdbcDao;
import com.litt.core.dao.page.IPageList;
import com.litt.core.dao.ql.PageParam;
import com.litt.core.exception.NotLoginException;
import com.litt.core.shield.vo.ILoginVo;
import com.litt.saap.core.web.util.LoginUtils;
import com.litt.saap.crm.dao.CustomerDao;
import com.litt.saap.crm.po.Customer;
import com.litt.saap.crm.service.ICustomerService;

/**
 * 客户基本信息管理.
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
 * @since 2013-10-15
 * @version 1.0
 */
public class CustomerServiceImpl implements ICustomerService {
	
	/** 日志工具. */
    private final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);
    
    @Resource
    private CustomerDao customerDao;
    
    @Resource
    private BaseJdbcDao jdbcDao;
    
    /* (non-Javadoc)
	 * @see com.litt.saap.crm.service.impl.ICustomerService#save(com.litt.saap.crm.po.Customer, com.litt.core.shield.vo.ILoginVo)
	 */
	public Integer save(Customer customer) throws NotLoginException
	{		
		ILoginVo loginVo = LoginUtils.getLoginVo();
		int tenantId = LoginUtils.getTenantId();
		//if(this.isExist(customer.getCode(), customer.getId()))
		//	throw new BusiException("客户编号已存在！");
//		if(this.isNameExist(customer.getName(), customer.getId()))
//			throw new BusiException("客户名称已存在！");		
		
//		if(customer.getParentId()==null || customer.getParentId().longValue()==0)
//			customer.setParentId(0L);
//		else 
//		{
//			Customer parent = this.load(customer.getParentId());
//			if(parent.getIsLeaf())
//			{
//				parent.setIsLeaf(false);
//				hibernateDao.update(parent);						
//			}
//		}
		
		customer.setTenantId(tenantId);
		customer.setCreateUserId(loginVo.getOpId().intValue());
		customer.setCreateDatetime(new Date());	
		customer.setUpdateUserId(customer.getCreateUserId());
		customer.setUpdateDatetime(customer.getCreateDatetime());
		return customerDao.save(customer);
	}
	
	/**
	 * Update.
	 * @param customer Customer
	 */
	public void update(Customer customer) throws NotLoginException
	{		
		//校验租户权限
		LoginUtils.validateTenant(customer.getTenantId());
		
		ILoginVo loginVo = LoginUtils.getLoginVo();		
		
		customer.setUpdateUserId(loginVo.getOpId().intValue());
		customer.setUpdateDatetime(customer.getCreateDatetime());
		customerDao.update(customer);
	}
   
   	/**
	 * Delete by id.
	 * @param id id
	 */
	public void delete(Integer id) throws NotLoginException
	{	
		Customer customer = this.load(id);
		
		customerDao.delete(customer);
	}
	
	/**
	 * Delete by instance.
	 * @param id id
	 */
	public void delete(Customer customer) throws NotLoginException
	{
		//校验租户权限
		LoginUtils.validateTenant(customer.getTenantId());
		
		customerDao.delete(customer);
	}
	
	/**
	 * Load by id.
	 * @param id id
	 * @return Customer
	 */
	public Customer load(Integer id)throws NotLoginException
	{
		Customer customer = customerDao.load(id);
		
		//校验租户权限
		LoginUtils.validateTenant(customer.getTenantId());
		
		return customer;
	}
	
	/**
	 * List all.
	 *
	 * @return the list
	 */
	public List<Customer> listAll() throws NotLoginException
	{
		int tenantId = LoginUtils.getTenantId();
		
		String listHql = "from Customer where tenantId=?";
		return customerDao.listAll(listHql, new Object[]{tenantId});
	}	
	
	/* (non-Javadoc)
	 * @see com.litt.saap.crm.service.impl.ICustomerService#listPage(com.litt.core.dao.ql.PageParam)
	 */
	public IPageList listPage(PageParam pageParam) throws NotLoginException
	{
		int tenantId = LoginUtils.getTenantId();
		pageParam.addCond("tenantId", tenantId);
		
		String listSql = "SELECT OBJ.*, O.USER_NAME AS CHARGE_USER_NAME, T2.NAME AS CONTACTS_NAME FROM CUSTOMER OBJ LEFT JOIN USER_INFO O ON OBJ.CHARGE_USER_ID=O.ID LEFT JOIN CUST_CONTACTS T2 ON OBJ.CONTACTS_ID=T2.ID"
			+ "-- AND OBJ.TENANT_ID={tenantId}"
			+ "-- AND OBJ.IS_DELETED={isDeleted}"
			+ "-- AND OBJ.CODE LIKE {%code%}"
			+ "-- AND OBJ.NAME LIKE {%name%}"
			;	
		return jdbcDao.listPage(listSql, pageParam);
	}

}
