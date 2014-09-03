package com.litt.saap.assistant.service.impl;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.litt.core.common.Utility;
import com.litt.core.dao.page.IPageList;
import com.litt.core.dao.ql.PageParam;
import com.litt.core.exception.BusiCodeException;
import com.litt.core.io.util.FileUtils;
import com.litt.core.uid.UUID;
import com.litt.core.util.BeanCopier;
import com.litt.core.util.StringUtils;
import com.litt.saap.assistant.dao.AttachmentDao;
import com.litt.saap.assistant.po.Attachment;
import com.litt.saap.assistant.service.IAttachmentService;
import com.litt.saap.assistant.vo.AttachmentVo;
import com.litt.saap.assistant.webservice.IAttachmentWebService;
import com.litt.saap.core.common.SaapConstants;
import com.litt.saap.core.web.util.LoginUtils;

/**
 * 
 * Attachment service impl.
 * <pre><b>Description：</b>
 *    Attachment Management.
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">Bob.cai</a>
 * @since 2014-04-23
 * @version 1.0
 */
public class AttachmentServiceImpl implements IAttachmentService, IAttachmentWebService
{ 
	/** Logger. */
    private static final Logger logger = LoggerFactory.getLogger(AttachmentServiceImpl.class);
    
    /** 持久化文件保存根目录. */  
    private String homePath;
    
    @Resource
    private AttachmentDao attachmentDao;
    
    /**
     * 保存文件到租户.
     * 未关联具体记录。
     * 如一开始上传文件时，记录尚未保存
     *
     * @param srcFile the src file
     * @param tenantId the tenant id
     * @param moduleCode the module code
     * @return the integer
     * 
     */
	public AttachmentVo save(File srcFile, String moduleCode, int tenantId)
	{
		return this.save(srcFile, moduleCode, tenantId, 0);
	}
	
	public AttachmentVo save(File srcFile, String moduleCode, int tenantId, int recordId)
	{
	  return this.save(srcFile, srcFile.getName(), moduleCode, tenantId, recordId);
	}
	
	
	/**
	 * 保存文件到租户.
	 *
	 * @param srcFile the src file
	 * @param srcName the src name
	 * @param moduleCode the module code
	 * @param tenantId the tenant id
	 * @param recordId the record id
	 * @return the attachment vo
	 */
	public AttachmentVo save(File srcFile, String srcName, String moduleCode, int tenantId, int recordId)
	{
		if(StringUtils.isEmpty(moduleCode))
			throw new IllegalArgumentException("Module code is not assigned");
		
		int createBy = LoginUtils.getLoginOpId().intValue();
		
		//将文件保存到附件目录
		//计算文件的文件名，根据时间+3随机数+用户ID生成，确保唯一性
		String srcFileName = StringUtils.isEmpty(srcName)?srcFile.getName():srcName;
		String fileSuffix = FilenameUtils.getExtension(srcFileName);
		String destFileName = moduleCode + "-" + recordId + "-" + createBy + "-" + FileUtils.currentToFileName();
		if(!StringUtils.isEmpty(fileSuffix))
			destFileName += "."+fileSuffix;
		//将文件保存到租户空间的附件目录
		String attachmentPath = Integer.toString(tenantId) + File.separator + SaapConstants.ATTACHMENT_PATH;
		File destDir = new File(homePath, attachmentPath);
		FileUtils.createDirectory(destDir);	//租户空间目录不存在则创建
		//将文件重命名后移动进去
		File destFile = new File(destDir, destFileName);
		
		if(!srcFile.renameTo(destFile))	
		{
			logger.error("Save attachment file failed");
			throw new BusiCodeException("attachment.error.saveFileFailed");
		}	
		
		Attachment attachment = new Attachment();
		attachment.setUid(UUID.randomUUID().toString());
		attachment.setTenantId(tenantId);
		attachment.setRecordId(recordId);
		attachment.setModuleCode(moduleCode);
		attachment.setFileName(destFileName);
		attachment.setDisplayName(srcFileName);
		attachment.setFilePath(attachmentPath);
		attachment.setFileSize(FileUtils.humanReadableByteCount(destFile.length()));
		attachment.setCreateBy(createBy);
		attachment.setCreateDatetime(new Date());
		attachment.setUpdateBy(createBy);
		attachment.setUpdateDatetime(attachment.getCreateDatetime());
		
		attachmentDao.save(attachment);
		
		AttachmentVo vo = BeanCopier.copy(attachment, AttachmentVo.class);
		return vo;
	}
	
	/**
	 * 更新附件的记录ID，关联具体记录.
	 * 
	 *
	 * @param id the id
	 * @param recordId the record id
	 */
	public void updateRecordId(Integer id, Integer recordId)
	{
		Attachment attachment = this.load(id);
		attachment.setRecordId(recordId);
		//重新计算文件名
		String fileName = attachment.getFileName();
		String[] strings = StringUtils.split(fileName, '-');
		fileName = strings[0] + "-" + recordId + "-" + strings[2] + "-" + strings[3];  
		attachment.setFileName(fileName);
		this.update(attachment);
	}
	
   	/**
	 * Update.
	 * @param attachment Attachment
	 */
	public void update(Attachment attachment) 
	{
		//校验租户权限
		LoginUtils.validateTenant(attachment.getTenantId());
		attachment.setUpdateBy(LoginUtils.getLoginOpId().intValue());
		attachment.setUpdateDatetime(new Date());
		attachmentDao.update(attachment);
	}		
	
	/**
	 * 批量更新附件的关联记录值.
	 *
	 * @param attachmentIds the attachment ids
	 * @param recordId the record id
	 */
	public void updateRecordIdBatch(Integer[] attachmentIds, Integer recordId)
	{
		if(attachmentIds==null)
			return;
		
		Attachment[] attachments = new Attachment[attachmentIds.length];
		for (int i=0;i<attachmentIds.length;i++) {
			Integer attachmentId = attachmentIds[i];
			Attachment attachment = this.load(attachmentId);
			LoginUtils.validateTenant(attachment.getTenantId());
			
			attachment.setRecordId(recordId);
			attachments[i] = attachment;
		}
		attachmentDao.updateBatch(attachments);
	}
	
	/**
	 * 批量更新附件的关联记录值.
	 *
	 * @param attachmentIds the attachment ids
	 * @param recordId the record id
	 */
	public void updateRecordIdBatch(String[] attachmentUids, Integer recordId)
	{
		if(attachmentUids==null)
			return;
		
		Attachment[] attachments = new Attachment[attachmentUids.length];
		for (int i=0;i<attachmentUids.length;i++) {
			String attachmentUid = attachmentUids[i];
			Attachment attachment = this.loadByUid(attachmentUid);
			LoginUtils.validateTenant(attachment.getTenantId());
			
			attachment.setRecordId(recordId);
			attachments[i] = attachment;
		}
		attachmentDao.updateBatch(attachments);
	}
   
   	/**
	 * Delete by id.
	 * @param id id
	 */
	public void delete(Integer id) 
	{
		Attachment attachment = this.load(id);
		this.delete(attachment);
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
	public void delete(Attachment attachment) 
	{
		//校验租户权限
		LoginUtils.validateTenant(attachment.getTenantId());
		
		//删除文件
		File dir = new File(this.homePath, attachment.getFilePath());
		if(!dir.exists())
		{
			throw new BusiCodeException("attachment.error.pathNotFound");
		}
		File file = new File(dir, attachment.getFileName());			
		FileUtils.deleteQuietly(file);
		logger.debug("Attachment file:{} is deleted", new Object[]{file.getPath()});
		//删除记录
		attachmentDao.delete(attachment);
	}
	
	/**
	 * Delete by.
	 *
	 * @param moduleCode the module code
	 * @param tenantId the tenant id
	 * @param recordId the record id
	 * @param fileName the file name
	 */
	public void deleteByName(Integer tenantId, String moduleCode, Integer recordId, String fileName)
	{
		String listHql = "from Attachment where tenantId=? and recordId=? and moduleCode=? and fileName=?";
		List<Attachment> poList = attachmentDao.listAll(listHql, new Object[]{tenantId, recordId, moduleCode, fileName});
		for (Attachment attachment : poList) {
			this.delete(attachment);
		}
	}
	
	public void deleteByUid(String uid)
	{
		String listHql = "from Attachment where uid=?";
		Attachment attachment = attachmentDao.uniqueResult(listHql, new Object[]{uid}, Attachment.class);
		this.delete(attachment);
	}
	
	/**
	 * Delete by record.
	 *
	 * @param moduleCode the module code
	 * @param tenantId the tenant id
	 * @param recordId the record id
	 */
	public void deleteByRecord(Integer tenantId, String moduleCode, Integer recordId)
  {
    String listHql = "from Attachment where tenantId=? and recordId=? and moduleCode=?";
    List<Attachment> poList = attachmentDao.listAll(listHql, new Object[]{tenantId, recordId, moduleCode});
    for (Attachment attachment : poList) {
      this.delete(attachment);
    }
  }
	
	/**
	 * Load by id.
	 * @param id id
	 * @return Attachment
	 */
	public Attachment load(Integer id) 
	{
		Attachment attachment = attachmentDao.load(id);
		//校验租户权限
		LoginUtils.validateTenant(attachment.getTenantId());
	
		return attachment;
	}	
	
	/**
	 * Load by uid.
	 *
	 * @param uid the uid
	 * @return Attachment
	 */
	public Attachment loadByUid(String uid) 
	{
		Attachment attachment = attachmentDao.load(Attachment.class, "uid", uid);
		//校验租户权限
		LoginUtils.validateTenant(attachment.getTenantId());
	
		return attachment;
	}	
	
	/**
	 * list by page.
	 * 
	 * @param pageParam params
	 * @return IPageList IPageList
	 */
	public IPageList listPage(PageParam pageParam)
	{
		String listHql = "select obj from Attachment obj"
			+ "-- and obj.tenantId={tenantId}"
			;	
		return attachmentDao.listPage(listHql, pageParam);
	}
	
	public List<Attachment> listByRecord(String moduleCode, Integer tenantId, Integer recordId)
	{
		String listHql = "from Attachment where tenantId=? and recordId=? and moduleCode=?";
		return attachmentDao.listAll(listHql, new Object[]{tenantId, recordId, moduleCode});
	}
	
	public List<AttachmentVo> findByRecord(String moduleCode, Integer tenantId, Integer recordId)
	{
		List<Attachment> poList = this.listByRecord(moduleCode, tenantId, recordId);		
		List<AttachmentVo> voList = BeanCopier.copyList(poList, AttachmentVo.class);
		return voList;
	}

	/**
	 * @return the homePath
	 */
	public String getHomePath() {
		return homePath;
	}

	/**
	 * @param homePath the homePath to set
	 */
	public void setHomePath(String homePath) {
		this.homePath = homePath;
	}
}