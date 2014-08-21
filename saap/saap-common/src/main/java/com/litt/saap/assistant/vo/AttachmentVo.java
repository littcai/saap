package com.litt.saap.assistant.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * AttachmentVo.
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
 * @since 2014年4月28日
 * @version 1.0
 */
public class AttachmentVo implements Serializable {
	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 序号.
	 */
	private Integer id;
	
	/** The uid. */
	private String uid;

	/**
	 * 租户ID.
	 */
	private int tenantId;

	/**
	 * 记录ID.
	 */
	private int recordId;

	/**
	 * 模块编号.
	 */
	private String moduleCode;

	/**
	 * 显示名称.
	 */
	private String displayName;

	/**
	 * 文件名.
	 */
	private String fileName;

	/**
	 * 文件路径.
	 */
	private String filePath;
	
	/** The file size. */
	private String fileSize;

	/**
	 * 创建时间.
	 */
	private Date createDatetime;

	/**
	 * 创建人.
	 */
	private int createBy;

	/**
	 * 更新时间.
	 */
	private Date updateDatetime;

	/**
	 * 更新人.
	 */
	private int updateBy;

	public AttachmentVo() {
	}

	public AttachmentVo(String uid, int tenantId, int recordId, String moduleCode,
			String displayName, String fileName, String filePath, String fileSize,
			Date createDatetime, int createBy, Date updateDatetime, int updateBy) {
		this.uid = uid;
		this.tenantId = tenantId;
		this.recordId = recordId;
		this.moduleCode = moduleCode;
		this.displayName = displayName;
		this.fileName = fileName;
		this.filePath = filePath;
		this.fileSize = fileSize;
		this.createDatetime = createDatetime;
		this.createBy = createBy;
		this.updateDatetime = updateDatetime;
		this.updateBy = updateBy;
	}

	/**  
	 * Get 序号.
	 * @return 序号
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * Set 序号.
	 * @param id 序号
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**  
	 * Get 租户ID.
	 * @return 租户ID
	 */
	public int getTenantId() {
		return this.tenantId;
	}

	/**
	 * Set 租户ID.
	 * @param tenantId 租户ID
	 */
	public void setTenantId(int tenantId) {
		this.tenantId = tenantId;
	}

	/**  
	 * Get 记录ID.
	 * @return 记录ID
	 */
	public int getRecordId() {
		return this.recordId;
	}

	/**
	 * Set 记录ID.
	 * @param recordId 记录ID
	 */
	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}

	/**  
	 * Get 模块编号.
	 * @return 模块编号
	 */
	public String getModuleCode() {
		return this.moduleCode;
	}

	/**
	 * Set 模块编号.
	 * @param moduleCode 模块编号
	 */
	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}

	/**  
	 * Get 显示名称.
	 * @return 显示名称
	 */
	public String getDisplayName() {
		return this.displayName;
	}

	/**
	 * Set 显示名称.
	 * @param displayName 显示名称
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/**  
	 * Get 文件名.
	 * @return 文件名
	 */
	public String getFileName() {
		return this.fileName;
	}

	/**
	 * Set 文件名.
	 * @param fileName 文件名
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**  
	 * Get 文件路径.
	 * @return 文件路径
	 */
	public String getFilePath() {
		return this.filePath;
	}

	/**
	 * Set 文件路径.
	 * @param filePath 文件路径
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	/**  
	 * Get 创建时间.
	 * @return 创建时间
	 */
	public Date getCreateDatetime() {
		return this.createDatetime;
	}

	/**
	 * Set 创建时间.
	 * @param createDatetime 创建时间
	 */
	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	/**  
	 * Get 创建人.
	 * @return 创建人
	 */
	public int getCreateBy() {
		return this.createBy;
	}

	/**
	 * Set 创建人.
	 * @param createBy 创建人
	 */
	public void setCreateBy(int createBy) {
		this.createBy = createBy;
	}

	/**  
	 * Get 更新时间.
	 * @return 更新时间
	 */
	public Date getUpdateDatetime() {
		return this.updateDatetime;
	}

	/**
	 * Set 更新时间.
	 * @param updateDatetime 更新时间
	 */
	public void setUpdateDatetime(Date updateDatetime) {
		this.updateDatetime = updateDatetime;
	}

	/**  
	 * Get 更新人.
	 * @return 更新人
	 */
	public int getUpdateBy() {
		return this.updateBy;
	}

	/**
	 * Set 更新人.
	 * @param updateBy 更新人
	 */
	public void setUpdateBy(int updateBy) {
		this.updateBy = updateBy;
	}

	/**
	 * @return the fileSize
	 */
	public String getFileSize() {
		return fileSize;
	}

	/**
	 * @param fileSize the fileSize to set
	 */
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	/**
	 * @return the uid
	 */
	public String getUid() {
		return uid;
	}

	/**
	 * @param uid the uid to set
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}
}
