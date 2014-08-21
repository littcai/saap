package com.litt.saap.assistant.biz.impl;

import java.io.File;

import javax.annotation.Resource;

import org.apache.commons.io.FilenameUtils;

import com.litt.core.io.util.FileUtils;
import com.litt.core.uid.UUID;
import com.litt.saap.assistant.po.Document;
import com.litt.saap.assistant.service.IAttachmentService;
import com.litt.saap.assistant.service.IDocumentService;
import com.litt.saap.assistant.vo.AttachmentVo;


/**
 * DocumentBizServiceImpl.
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
public class DocumentBizServiceImpl {
  
  @Resource
  private IDocumentService documentService;
  @Resource
  private IAttachmentService attachmentService;
  
  public Integer save(String code, String name, String brief, File srcFile, String moduleCode, int tenantId, int userId)
  {    
    String ext = FilenameUtils.getExtension(srcFile.getName());
    
    Document document = new Document();
    document.setCode(code);
    document.setName(name);
    document.setBrief(brief);
    document.setExt(ext);
    document.setAttachmentId(0);
    
    document.setCreateBy(userId);
    
    Integer docId = documentService.save(document);
    
    //save attachment
    AttachmentVo attachment = attachmentService.save(srcFile, moduleCode, tenantId, docId);
    Integer attachmentId = attachment.getId();
    document.setAttachmentId(attachmentId); //update document
    
    return docId;
  }
  

}
