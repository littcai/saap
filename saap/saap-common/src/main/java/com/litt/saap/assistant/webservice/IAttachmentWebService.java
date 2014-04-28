package com.litt.saap.assistant.webservice;

import java.io.File;
import java.util.List;

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
public interface IAttachmentWebService
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
	 * Delete by id.
	 * @param id id
	 */
	public void delete(Integer id);	
	
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
	public void deleteByName(String moduleCode, Integer tenantId, Integer recordId, String fileName);
	
	/**
	 * Delete by.
	 *
	 * @param uid the uid
	 */
	public void deleteByUid(String uid);
	
	/**
	 * Find by record.
	 *
	 * @param moduleCode the module code
	 * @param tenantId the tenant id
	 * @param recordId the record id
	 * @return the list
	 */
	public List<AttachmentVo> findByRecord(String moduleCode, Integer tenantId, Integer recordId);

}