package com.litt.saap.crm.webservice;

import java.util.List;

import com.litt.saap.crm.vo.CustomerVo;


/**
 * ICustomerWebService.
 * 
 * <pre><b>Descr:</b>
 *    
 * </pre>
 * 
 * <pre><b>Changelog:</b>
 *    
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">Caiyuan</a>
 * @since 2014年10月8日
 * @version 1.0
 */
public interface ICustomerWebService {
  
  
  public List<CustomerVo> findBy(String code, String name, boolean includeMe, Integer customerId) ;

}
