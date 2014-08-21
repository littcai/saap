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
 * Table:document_history<br>
 * @author Hibernate Tools 3.4.0.CR1
 * @version 1.0
 * @since 2014-8-21 8:03:18
 */
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "document_history")
public class DocumentHistory implements Serializable {
	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 序号.
	 */
	private Integer id;

	/**
	 * 租户ID.
	 */
	private int tenantId;

	/**
	 * 文档ID.
	 */
	private String docId;

	/**
	 * 附件ID.
	 */
	private String attachmentId;

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

	public DocumentHistory() {
	}

	public DocumentHistory(int tenantId, String docId, String attachmentId,
			String code, String name, String ext, int revision, int createBy,
			Date createDatetime) {
		this.tenantId = tenantId;
		this.docId = docId;
		this.attachmentId = attachmentId;
		this.code = code;
		this.name = name;
		this.ext = ext;
		this.revision = revision;
		this.createBy = createBy;
		this.createDatetime = createDatetime;
	}

	public DocumentHistory(int tenantId, String docId, String attachmentId,
			String code, String name, String ext, String brief, int revision,
			int createBy, Date createDatetime) {
		this.tenantId = tenantId;
		this.docId = docId;
		this.attachmentId = attachmentId;
		this.code = code;
		this.name = name;
		this.ext = ext;
		this.brief = brief;
		this.revision = revision;
		this.createBy = createBy;
		this.createDatetime = createDatetime;
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
	 * Get 文档ID.
	 * @return 文档ID
	 */

	@Column(name = "DOC_ID", nullable = false, length = 36)
	public String getDocId() {
		return this.docId;
	}

	/**
	 * Set 文档ID.
	 * @param docId 文档ID
	 */
	public void setDocId(String docId) {
		this.docId = docId;
	}

	/**  
	 * Get 附件ID.
	 * @return 附件ID
	 */

	@Column(name = "ATTACHMENT_ID", nullable = false, length = 36)
	public String getAttachmentId() {
		return this.attachmentId;
	}

	/**
	 * Set 附件ID.
	 * @param attachmentId 附件ID
	 */
	public void setAttachmentId(String attachmentId) {
		this.attachmentId = attachmentId;
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

}
