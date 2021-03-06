package com.litt.saap.crm.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.litt.core.dao.BaseJdbcDao;
import com.litt.core.dao.page.IPageList;
import com.litt.core.dao.ql.CondParam;
import com.litt.core.dao.ql.PageParam;
import com.litt.core.exception.BusiCodeException;
import com.litt.core.exception.NotLoginException;
import com.litt.core.shield.vo.ILoginVo;
import com.litt.core.util.ArrayUtils;
import com.litt.core.util.BeanCopier;
import com.litt.saap.assistant.po.Attachment;
import com.litt.saap.assistant.service.IAttachmentService;
import com.litt.saap.core.web.util.LoginUtils;
import com.litt.saap.crm.dao.CustomerDao;
import com.litt.saap.crm.po.Customer;
import com.litt.saap.crm.service.ICustomerService;
import com.litt.saap.crm.vo.CustomerVo;
import com.litt.saap.crm.webservice.ICustomerWebService;

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
public class CustomerServiceImpl implements ICustomerService, ICustomerWebService {
	
	/** 日志工具. */
    private final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);
    
    @Resource
    private CustomerDao customerDao;
    
    @Resource
    private BaseJdbcDao jdbcDao;
    
    @Resource
    private IAttachmentService attachmentService;
    
    /* (non-Javadoc)
	 * @see com.litt.saap.crm.service.impl.ICustomerService#save(com.litt.saap.crm.po.Customer, com.litt.core.shield.vo.ILoginVo)
	 */
	public Integer save(Customer customer) throws NotLoginException
	{		
		ILoginVo loginVo = LoginUtils.getLoginVo();
		int tenantId = LoginUtils.getTenantId();
		this.validate(customer.getId(), customer.getCode(), customer.getName());
		
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
		customer.setCreateBy(loginVo.getOpId().intValue());
		customer.setCreateDatetime(new Date());	
		customer.setUpdateBy(customer.getCreateBy());
		customer.setUpdateDatetime(customer.getCreateDatetime());
		return customerDao.save(customer);
	}
	
	/**
	 * Save.
	 *
	 * @param customer the customer
	 * @param attachmentIds the attachment ids
	 */
	public void save(Customer customer, String[] attachmentUids)
	{
		Integer customerId = this.save(customer);
		attachmentService.updateRecordIdBatch(attachmentUids, customerId);
	}
	
	/**
	 * Update.
	 * @param customer Customer
	 */
	public void update(Customer customer) throws NotLoginException
	{		
		//校验租户权限
		LoginUtils.validateTenant(customer.getTenantId());
		//校验数据有效性
		this.validate(customer.getId(), customer.getCode(), customer.getName());
		//TODO 校验父节点有效性
		
		
		ILoginVo loginVo = LoginUtils.getLoginVo();		
		
		customer.setUpdateBy(loginVo.getOpId().intValue());
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
	 * 数据有效性检查.
	 *
	 * @param id the id
	 * @param name the name
	 */
	private void validate(Integer id, String code, String name)
	{		
		int tenantId = LoginUtils.getTenantId();
		
		String countHql = "select count(*) from Customer where tenantId=? and code=? and (id>? or id<?)";
		boolean invalid = customerDao.count(countHql, new Object[]{tenantId, code, id, id})>0;		
		if(invalid)
		{
			throw new BusiCodeException("customer.error.codeDuplicated");
		}
		
		countHql = "select count(*) from Customer where tenantId=? and name=? and (id>? or id<?)";
		invalid = customerDao.count(countHql, new Object[]{tenantId, name, id, id})>0;		
		if(invalid)
		{
			throw new BusiCodeException("customer.error.nameDuplicated");
		}
	}
	
	/**
	 * Load by id.
	 * @param id id
	 * @return Customer
	 */
	public Customer load(Integer id)
	{
		Customer customer = customerDao.load(id);
		if(customer==null)
			return null;
		//校验租户权限
		LoginUtils.validateTenant(customer.getTenantId());
		
		return customer;
	}
	
	/**
	 * Find.
	 *
	 * @param id the id
	 * @return the customer vo
	 */
	public CustomerVo find(Integer id)
	{
	  Customer customer = this.load(id);
	  if(customer==null)
	    return null;
	  return customer.toVo(CustomerVo.class);
	}
	
	/**
	 * List all.
	 *
	 * @return the list
	 */
	public List<Customer> listAll() 
	{
		int tenantId = LoginUtils.getTenantId();
		
		String listHql = "from Customer where tenantId=?";
		return customerDao.listAll(listHql, new Object[]{tenantId});
	}	
	
	/**
	 * List all.
	 *
	 * @param code the code
	 * @param name the name
	 * @param includeMe the include me
	 * @param customerId the customer id
	 * @return the list
	 */
	public List<Customer> listBy(String code, String name, boolean includeMe, Integer customerId) 
	{
		int tenantId = LoginUtils.getTenantId();
		
		CondParam condParam = new CondParam();
		condParam.addCond("tenantId", tenantId);
		condParam.addCond("code", code);
		condParam.addCond("name", name);
		
		String listHql = "from Customer"
				+"-- and tenantId={tenantId}"
				+"-- and code like {code%}"
				+"-- and name like {%name%}"
				+"-- and id>{customerId} and id<{customerId}"
				;
		if(!includeMe)	//过滤掉自己
		{
			condParam.addCond("customerId", customerId);
		}
		return customerDao.listAll(listHql, condParam);
	}	
	
	public List<CustomerVo> findBy(String code, String name, boolean includeMe, Integer customerId)
	{
	  List<Customer> poList = this.listBy(code, name, includeMe, customerId);
	  List<CustomerVo> voList = BeanCopier.copyList(poList, CustomerVo.class);
	  return voList;
	}
	
	/* (non-Javadoc)
	 * @see com.litt.saap.crm.service.impl.ICustomerService#listPage(com.litt.core.dao.ql.PageParam)
	 */
	public IPageList listPage(PageParam pageParam) throws NotLoginException
	{
		int tenantId = LoginUtils.getTenantId();
		pageParam.addCond("tenantId", tenantId);
		
		String listSql = "SELECT OBJ.*, O.USER_NAME AS CHARGE_USER_NAME, T2.NAME AS CONTACTS_NAME FROM CUSTOMER OBJ LEFT JOIN USER_INFO O ON OBJ.CHARGE_BY=O.ID LEFT JOIN CUST_CONTACTS T2 ON OBJ.CONTACTS_ID=T2.ID"
			+ "-- AND OBJ.TENANT_ID={tenantId}"
			+ "-- AND OBJ.IS_DELETED={isDeleted}"
			+ "-- AND OBJ.CODE LIKE {%code%}"
			+ "-- AND OBJ.NAME LIKE {%name%}"
			;	
		return jdbcDao.listPage(listSql, pageParam);
	}

}
