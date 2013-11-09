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
	public Integer save(Customer customer) throws NotLoginException;	
			
	
	/**
	 * Update.
	 * @param customer Customer
	 */
	public void update(Customer customer) throws NotLoginException;				
   
   	/**
	 * Delete by id.
	 * @param id id
	 */
	public void delete(Integer id) throws NotLoginException;	
	
	/**
	 * Delete by instance.
	 * @param id id
	 */
	public void delete(Customer customer) throws NotLoginException ;
	
	/**
	 * Load by id.
	 * @param id id
	 * @return Customer
	 */
	public Customer load(Integer id) throws NotLoginException;	
	
	/**
	 * List all.
	 *
	 * @return the list
	 */
	public List<Customer> listAll() throws NotLoginException;
	

	/**
	 * 分页查询.
	 * 
	 * @param pageParam 查询参数
	 * @return IPageList IPageList对象
	 */
	public IPageList listPage(PageParam pageParam) throws NotLoginException;

}