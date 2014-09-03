package com.litt.saap.assistant.service;

import java.io.File;
import java.util.List;

import com.litt.core.dao.page.IPageList;
import com.litt.core.dao.ql.PageParam;
import com.litt.saap.assistant.po.Attachment;
import com.litt.saap.assistant.vo.AttachmentVo;

/**
 * 
 * Attachement service interface.
 * <pre><b>Description：</b>
 *    Attachement Management.
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">Bob.cai</a>
 * @since 2014-04-23
 * @version 1.0
 */
public interface IAttachmentService
{ 
    /**
     * 保存文件到租户.
     *
     * @param srcFile the src file
     * @param tenantId the tenant id
     * @param moduleCode the module code
     * @return the integer
     * 
     */
	public AttachmentVo save(File srcFile, String moduleCode, int tenantId);

	/**
	 * Save.
	 *
	 * @param srcFile the src file
	 * @param tenantId the tenant id
	 * @param recordId the record id
	 * @param moduleCode the module code
	 * @return the integer
	 */
	public AttachmentVo save(File srcFile, String moduleCode, int tenantId, int recordId);
	
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
  public AttachmentVo save(File srcFile, String srcName, String moduleCode, int tenantId, int recordId);
	
   	/**
	 * Update.
	 * @param attachement Attachement
	 */
	public void update(Attachment attachement);		
	
	/**
	 * 批量更新附件的关联记录值.
	 *
	 * @param attachmentIds the attachment ids
	 * @param recordId the record id
	 */
	public void updateRecordIdBatch(Integer[] attachmentIds, Integer recordId);
	
	/**
	 * 批量更新附件的关联记录值.
	 *
	 * @param attachmentUids the attachment uids
	 * @param recordId the record id
	 */
	public void updateRecordIdBatch(String[] attachmentUids, Integer recordId);
   
   	/**
	 * Delete by id.
	 * @param id id
	 */
	public void delete(Integer id);	
	
	/**
	 * Delete by instance.
	 * @param id id
	 */
	public void delete(Attachment attachement);
	
	/**
	 * Batch delete by ids.
	 * @param ids ids
	 */
	public void deleteBatch(Integer[] ids);
	
	/**
	 * Delete by.
	 *
	 * @param moduleCode the module code
	 * @param tenantId the tenant id
	 * @param recordId the record id
	 * @param fileName the file name
	 */
	public void deleteByName(Integer tenantId, String moduleCode, Integer recordId, String fileName);
	
	/**
	 * Delete by.
	 *
	 * @param uid the uid
	 */
	public void deleteByUid(String uid);
	
	/**
   * Delete by record.
   *
   * @param moduleCode the module code
   * @param tenantId the tenant id
   * @param recordId the record id
   */
  public void deleteByRecord(Integer tenantId, String moduleCode, Integer recordId);
	
	/**
	 * Load by id.
	 * @param id id
	 * @return Attachement
	 */
	public Attachment load(Integer id);	
	
	/**
	 * Load by uid.
	 *
	 * @param uid the uid
	 * @return Attachment
	 */
	public Attachment loadByUid(String uid);
	
	/**
	 * list by page.
	 * 
	 * @param pageParam params
	 * @return IPageList IPageList
	 */
	public IPageList listPage(PageParam pageParam);	
	
	/**
	 * List by record.
	 *
	 * @param moduleCode the module code
	 * @param tenantId the tenant id
	 * @param recordId the record id
	 * @return the list
	 */
	public List<Attachment> listByRecord(String moduleCode, Integer tenantId, Integer recordId);
	


}