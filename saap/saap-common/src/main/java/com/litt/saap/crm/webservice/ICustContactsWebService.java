package com.litt.saap.crm.webservice;

import java.util.List;

import com.litt.saap.crm.vo.CustContactsVo;


/**
 * ICustContactsWebService.
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
public interface ICustContactsWebService {

  /**
   * Find.
   *
   * @param id the id
   * @return CustContactsVo
   */
  public CustContactsVo find(Integer id);
  
  public List<CustContactsVo> findByCustomer(Integer customerId);
}
