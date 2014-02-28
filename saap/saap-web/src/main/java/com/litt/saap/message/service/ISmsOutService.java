package com.litt.saap.message.service;

import com.litt.core.dao.page.IPageList;
import com.litt.core.dao.ql.PageParam;
import com.litt.saap.message.po.SmsOut;

/**
 * 
 * SMS service interface.
 * <pre><b>Description：</b>
 *    SMS Management
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">Bob.cai</a>
 * @since 2014-02-17
 * @version 1.0
 */
public interface ISmsOutService
{ 

   	/**
	 * Save.
	 * @param smsOut SmsOut
	 * @return id
	 */
	public Integer save(SmsOut smsOut);
	
	/**
	 * 异步发送短信.
	 *
	 * @param smsOuts the sms outs
	 */
	public void doAsycSend(SmsOut[] smsOuts);
	
	/**
	 * 重发短信.
	 *
	 * @param id the id
	 */
	public void doResend(Integer id);
	
   	/**
	 * Update.
	 * @param smsOut SmsOut
	 */
	public void update(SmsOut smsOut);				
   
   	/**
	 * Delete by id.
	 * @param id id
	 */
	public void delete(Integer id);	
	
	/**
	 * Delete by instance.
	 * @param id id
	 */
	public void delete(SmsOut smsOut);
	
	/**
	 * Batch delete by ids.
	 * @param ids ids
	 */
	public void deleteBatch(Integer[] ids);
	
	/**
	 * Load by id.
	 * @param id id
	 * @return SmsOut
	 */
	public SmsOut load(Integer id);	
	
	/**
	 * list by page.
	 * 
	 * @param pageParam params
	 * @return IPageList IPageList
	 */
	public IPageList listPage(PageParam pageParam);	

}