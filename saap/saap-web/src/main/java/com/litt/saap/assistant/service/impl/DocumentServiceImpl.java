package com.litt.saap.assistant.service.impl;

import java.io.File;
import java.math.BigDecimal;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.litt.core.dao.page.IPageList;
import com.litt.core.dao.ql.PageParam;
import com.litt.core.exception.BusiCodeException;
import com.litt.core.io.util.FileUtils;
import com.litt.core.uid.UUID;
import com.litt.core.util.StringUtils;
import com.litt.saap.assistant.dao.DocumentDao;
import com.litt.saap.assistant.po.Document;
import com.litt.saap.assistant.service.IDocumentService;
import com.litt.saap.core.common.SaapConstants;
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
	
	/** Logger. */
    private static final Logger logger = LoggerFactory.getLogger(DocumentServiceImpl.class);
  
  @Resource
  private DocumentDao documentDao;
  
  private String homePath;
  
  public Integer save(String code, String name, String brief, File srcFile, String moduleCode, int recordId, int tenantId, int userId)
  {    
    String ext = FilenameUtils.getExtension(srcFile.getName());
    
   
    String destFileName = moduleCode + "-" + recordId + "-" + userId + "-" + FileUtils.currentToFileName();
    if(!StringUtils.isEmpty(ext))
		destFileName += "."+ext;
    
    code = StringUtils.isEmpty(code)?destFileName : code;
    name = StringUtils.isEmpty(name)?destFileName : name;
    
    //将文件保存到租户空间的附件目录
	String docPath = Integer.toString(tenantId) + File.separator + SaapConstants.DOCUMENT_PATH;
	File destDir = new File(homePath, docPath);
	FileUtils.createDirectory(destDir);	//租户空间目录不存在则创建
	//将文件重命名后移动进去
	File destFile = new File(destDir, destFileName);
	
	if(!srcFile.renameTo(destFile))	
	{
		logger.error("Save attachment file failed");
		throw new BusiCodeException("attachment.error.saveFileFailed");
	}	
    
    Document document = new Document();
    document.setTenantId(tenantId);
    document.setModuleCode(moduleCode);
    document.setRecordId(recordId);
    document.setCode(code);
    document.setName(name);
    document.setBrief(brief);
    document.setExt(ext);
    document.setSrcFileName(srcFile.getName());
    document.setFileName(destFileName);
    document.setFilePath(docPath);
    document.setFileSize(new BigDecimal(srcFile.length()));
    
    document.setCreateBy(userId);
    
    Integer docId = this.save(document);
    
    return docId;
  }
  
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
    document.setUpdateDatetime(document.getCreateDatetime());
    
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
  
    File dir = new File(homePath, document.getFilePath());
    File file = new File(dir, document.getFileName());
    FileUtils.deleteQuietly(file);
    
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

/**
 * @param homePath the homePath to set
 */
public void setHomePath(String homePath) {
	this.homePath = homePath;
}

}
