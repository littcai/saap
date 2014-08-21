package com.litt.saap.assistant.po;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import java.io.Serializable;

/**
 * <br>
 * Table:attachment<br>
 * @author Hibernate Tools 3.4.0.CR1
 * @version 1.0
 * @since 2014-8-21 8:13:18
 */
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "attachment")
public class Attachment implements Serializable {
	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 序号.
	 */
	private Integer id;

	/**
	 * 唯一ID.
	 */
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

	/**
	 * 文件大小.
	 */
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

	public Attachment() {
	}

	public Attachment(String uid, int tenantId, int recordId,
			String moduleCode, String displayName, String fileName,
			String filePath, String fileSize, Date createDatetime,
			int createBy, Date updateDatetime, int updateBy) {
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
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
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
	 * Get 唯一ID.
	 * @return 唯一ID
	 */

	@Column(name = "UID", nullable = false, length = 36)
	public String getUid() {
		return this.uid;
	}

	/**
	 * Set 唯一ID.
	 * @param uid 唯一ID
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}

	/**  
	 * Get 租户ID.
	 * @return 租户ID
	 */

	@Column(name = "TENANT_ID", nullable = false)
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

	@Column(name = "RECORD_ID", nullable = false)
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

	@Column(name = "MODULE_CODE", nullable = false, length = 50)
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

	@Column(name = "DISPLAY_NAME", nullable = false, length = 100)
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

	@Column(name = "FILE_NAME", nullable = false, length = 100)
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

	@Column(name = "FILE_PATH", nullable = false, length = 500)
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
	 * Get 文件大小.
	 * @return 文件大小
	 */

	@Column(name = "FILE_SIZE", nullable = false, length = 50)
	public String getFileSize() {
		return this.fileSize;
	}

	/**
	 * Set 文件大小.
	 * @param fileSize 文件大小
	 */
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	/**  
	 * Get 创建时间.
	 * @return 创建时间
	 */

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_DATETIME", nullable = false, length = 19)
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

	@Column(name = "CREATE_BY", nullable = false)
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

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATE_DATETIME", nullable = false, length = 19)
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

	@Column(name = "UPDATE_BY", nullable = false)
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

}
