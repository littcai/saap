package com.litt.saap.crm.service;


import java.util.List;

import com.litt.core.dao.page.IPageList;
import com.litt.core.dao.ql.PageParam;
import com.litt.core.exception.NotLoginException;
import com.litt.saap.crm.po.Customer;

/**
 * .
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
public interface ICustomerService {

	/**
	 * 保存.
	 * 
	 * @param customer Customer对象
	 * @param loginVo 登录操作员
	 * 
	 * @return 主键
	 */
	public Integer save(Customer customer);	
	
	/**
	 * Save.
	 *
	 * @param customer the customer
	 * @param attachmentIds the attachment ids
	 */
	public void save(Customer customer, String[] attachmentUids);
			
	
	/**
	 * Update.
	 * @param customer Customer
	 */
	public void update(Customer customer);				
   
   	/**
	 * Delete by id.
	 * @param id id
	 */
	public void delete(Integer id);	
	
	/**
	 * Delete by instance.
	 * @param id id
	 */
	public void delete(Customer customer) ;
	
	/**
	 * Load by id.
	 * @param id id
	 * @return Customer
	 */
	public Customer load(Integer id);	
	
	/**
	 * List all.
	 *
	 * @return the list
	 */
	public List<Customer> listAll();
	
	/**
	 * List all.
	 *
	 * @param code the code
	 * @param name the name
	 * @param includeMe the include me
	 * @param customerId the customer id
	 * @return the list
	 */
	public List<Customer> listAll(String code, String name, boolean includeMe, Integer customerId) ;
	

	/**
	 * 分页查询.
	 * 
	 * @param pageParam 查询参数
	 * @return IPageList IPageList对象
	 */
	public IPageList listPage(PageParam pageParam);

}