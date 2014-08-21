package com.litt.saap.assistant.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import com.litt.core.dao.page.IPageList;
import com.litt.core.dao.ql.PageParam;
import com.litt.core.uid.UUID;
import com.litt.saap.assistant.dao.DocumentDao;
import com.litt.saap.assistant.po.Document;
import com.litt.saap.assistant.service.IDocumentService;
import com.litt.saap.core.web.util.LoginUtils;


/**
 * DocumentServiceImpl.
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
 * @since 2014年8月21日
 * @version 1.0
 */
public class DocumentServiceImpl implements IDocumentService {
  
  @Resource
  private DocumentDao documentDao;
  
  /**
   * Save.
   *
   * @param document the document
   * @return the integer
   */
  public Integer save(Document document)
  {
    document.setUid(UUID.randomUUID().toString());
    document.setRevision(1);
    document.setCreateDatetime(new Date());
    document.setUpdateBy(document.getCreateBy());
    document.setUpdateDatetime(document.getUpdateDatetime());
    
    Integer docId = documentDao.save(document);
    
    
    return docId;
  }
  
  /**
   * Update.
   * @param document Document
   */
  public void update(Document document) 
  {
    //校验租户权限
    LoginUtils.validateTenant(document.getTenantId());
  
    documentDao.update(document);
  }     
   
    /**
   * Delete by id.
   * @param id id
   */
  public void delete(Integer id) 
  {
    Document document = this.load(id);
    this.delete(document);
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
  public void delete(Document document) 
  {
    //校验租户权限
    LoginUtils.validateTenant(document.getTenantId());
  
    documentDao.delete(document);
  }
  
  /**
   * Load by id.
   * @param id id
   * @return Document
   */
  public Document load(Integer id) 
  {
    Document document = documentDao.load(id);
    //校验租户权限
    LoginUtils.validateTenant(document.getTenantId());
  
    return document;
  }
  
  /**
   * list by page.
   * 
   * @param pageParam params
   * @return IPageList IPageList
   */
  public IPageList listPage(PageParam pageParam)
  {
    String listHql = "select obj from Document obj"
      + "-- and obj.tenantId={tenantId}"
      ; 
    return documentDao.listPage(listHql, pageParam);
  }

}
