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
import java.math.BigDecimal;

/**
 * <br>
 * Table:document<br>
 * @author Hibernate Tools 3.4.0.CR1
 * @version 1.0
 * @since 2014-8-21 8:03:18
 */
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "document")
public class Document implements Serializable {
	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 序号.
	 */
	private Integer id;

	/**
	 * UID.
	 */
	private String uid;

	/**
	 * 租户ID.
	 */
	private int tenantId;

	/**
	 * 模块编号.
	 */
	private String moduleCode;

	/**
	 * 记录ID.
	 */
	private int recordId;

	/**
	 * 编号.
	 */
	private String code;

	/**
	 * 名称.
	 */
	private String name;

	/**
	 * 类型.
	 */
	private String ext;

	/**
	 * 文档摘要.
	 */
	private String brief;
	
	private String srcFileName;
	
	private String fileName;
	
	private String filePath;
	
	private BigDecimal fileSize = new BigDecimal(0);

	/**
	 * 修订号.
	 */
	private int revision;

	/**
	 * 创建人.
	 */
	private int createBy;

	/**
	 * 创建时间.
	 */
	private Date createDatetime;

	/**
	 * 更新人.
	 */
	private int updateBy;

	/**
	 * 更新时间.
	 */
	private Date updateDatetime;

	public Document() {
	}

	public Document(String uid, int tenantId, String moduleCode, int recordId,
			String code, String name, String ext,
			int revision, int createBy, Date createDatetime, int updateBy,
			Date updateDatetime) {
		this.uid = uid;
		this.tenantId = tenantId;
		this.moduleCode = moduleCode;
		this.recordId = recordId;
		this.code = code;
		this.name = name;
		this.ext = ext;
		this.revision = revision;
		this.createBy = createBy;
		this.createDatetime = createDatetime;
		this.updateBy = updateBy;
		this.updateDatetime = updateDatetime;
	}

	public Document(String uid, int tenantId, String moduleCode, int recordId,
			String code, String name, String ext,
			String brief, int revision, int createBy, Date createDatetime,
			int updateBy, Date updateDatetime) {
		this.uid = uid;
		this.tenantId = tenantId;
		this.moduleCode = moduleCode;
		this.recordId = recordId;
		this.code = code;
		this.name = name;
		this.ext = ext;
		this.brief = brief;
		this.revision = revision;
		this.createBy = createBy;
		this.createDatetime = createDatetime;
		this.updateBy = updateBy;
		this.updateDatetime = updateDatetime;
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
	 * Get UID.
	 * @return UID
	 */

	@Column(name = "UID", nullable = false, length = 36)
	public String getUid() {
		return this.uid;
	}

	/**
	 * Set UID.
	 * @param uid UID
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
	 * Get 编号.
	 * @return 编号
	 */

	@Column(name = "CODE", nullable = false, length = 50)
	public String getCode() {
		return this.code;
	}

	/**
	 * Set 编号.
	 * @param code 编号
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**  
	 * Get 名称.
	 * @return 名称
	 */

	@Column(name = "NAME", nullable = false, length = 100)
	public String getName() {
		return this.name;
	}

	/**
	 * Set 名称.
	 * @param name 名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**  
	 * Get 类型.
	 * @return 类型
	 */

	@Column(name = "EXT", nullable = false, length = 10)
	public String getExt() {
		return this.ext;
	}

	/**
	 * Set 类型.
	 * @param ext 类型
	 */
	public void setExt(String ext) {
		this.ext = ext;
	}

	/**  
	 * Get 文档摘要.
	 * @return 文档摘要
	 */

	@Column(name = "BRIEF", length = 200)
	public String getBrief() {
		return this.brief;
	}

	/**
	 * Set 文档摘要.
	 * @param brief 文档摘要
	 */
	public void setBrief(String brief) {
		this.brief = brief;
	}

	/**  
	 * Get 修订号.
	 * @return 修订号
	 */

	@Column(name = "REVISION", nullable = false)
	public int getRevision() {
		return this.revision;
	}

	/**
	 * Set 修订号.
	 * @param revision 修订号
	 */
	public void setRevision(int revision) {
		this.revision = revision;
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
	 * @return the srcFileName
	 */
	@Column(name = "SRC_FILE_NAME", nullable = false, length = 100)
	public String getSrcFileName() {
		return srcFileName;
	}

	/**
	 * @param srcFileName the srcFileName to set
	 */
	public void setSrcFileName(String srcFileName) {
		this.srcFileName = srcFileName;
	}

	/**
	 * @return the fileName
	 */
	@Column(name = "FILE_NAME", nullable = false, length = 100)
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the filePath
	 */
	@Column(name = "FILE_PATH", nullable = false, length = 200)
	public String getFilePath() {
		return filePath;
	}

	/**
	 * @param filePath the filePath to set
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * @return the fileSize
	 */
	@Column(name = "FILE_SIZE", nullable = false, length = 10, precision=2)
	public BigDecimal getFileSize() {
		return fileSize;
	}

	/**
	 * @param fileSize the fileSize to set
	 */
	public void setFileSize(BigDecimal fileSize) {
		this.fileSize = fileSize;
	}

}
