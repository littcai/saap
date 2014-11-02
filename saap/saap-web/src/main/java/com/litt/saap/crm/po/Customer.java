package com.litt.saap.crm.po;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.litt.core.po.BasePo;

import java.io.Serializable;

/**
 * <br>
 * Table:customer<br>
 * @author Hibernate Tools 3.4.0.CR1
 * @version 1.0
 * @since 2014-9-18 22:14:38
 */
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "customer")
public class Customer extends BasePo implements Serializable {
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
	 * 编号.
	 */
	private String code;

	/**
	 * 上级单位：通过该字段形成客户关系网.
	 */
	private int parentId;

	/**
	 * 是否叶子节点.
	 */
	private boolean isLeaf;

	/**
	 * 名称.
	 */
	private String name;

	/**
	 * 联系电话.
	 */
	private String phone;

	/**
	 * 电子邮件.
	 */
	private String email;

	/**
	 * 传真.
	 */
	private String fax;

	/**
	 * 联系地址.
	 */
	private String address;

	/**
	 * 邮政编码.
	 */
	private String zipCode;

	/**
	 * 网址.
	 */
	private String website;

	/**
	 * 备注.
	 */
	private String remark;

	/**
	 */
	private int chargeBy;

	/**
	 * 默认联系人ID.
	 */
	private Integer contactsId;

	/**
	 */
	private int createBy;

	/**
	 * 创建时间.
	 */
	private Date createDatetime;

	/**
	 */
	private int updateBy;

	/**
	 * 上次修改时间.
	 */
	private Date updateDatetime;

	/**
	 * 客户等级（3102）
	        1：1星
	        2：2星
	        3：3星
	        4：4星
	        5：5星.
	 */
	private Integer grade;

	/**
	 * 客户状态（3103）
	        1：潜在
	        2：有意向
	        3：失败
	        4：已流失
	        5：已成交
	        6：维护
	        7：重点维护.
	 */
	private Integer status;

	/**
	 * LOGO存放路径.
	 */
	private String logoUrl;

	/**
	 * 是否已删除.
	 */
	private boolean isDeleted;

	/**
	 * 账期.
	 */
	private int paymentDays;

	/**
	 * 发票邮寄地址.
	 */
	private String mailingAddress;

	/**
	 * 开票地址.
	 */
	private String billingAddress;

	/**
	 * 开票全称.
	 */
	private String billingFullName;

	/**
	 * 银行名称.
	 */
	private String bankName;

	/**
	 * 帐号.
	 */
	private String accountNo;

	/**
	 * 税号.
	 */
	private String taxNo;

	/**
	 * 开票注意点.
	 */
	private String billingRemark;

	public Customer() {
	}

	public Customer(int tenantId, String code, int parentId, boolean isLeaf,
			String name, int chargeBy, int createBy, Date createDatetime,
			int updateBy, Date updateDatetime, boolean isDeleted,
			int paymentDays) {
		this.tenantId = tenantId;
		this.code = code;
		this.parentId = parentId;
		this.isLeaf = isLeaf;
		this.name = name;
		this.chargeBy = chargeBy;
		this.createBy = createBy;
		this.createDatetime = createDatetime;
		this.updateBy = updateBy;
		this.updateDatetime = updateDatetime;
		this.isDeleted = isDeleted;
		this.paymentDays = paymentDays;
	}

	public Customer(int tenantId, String code, int parentId, boolean isLeaf,
			String name, String phone, String email, String fax,
			String address, String zipCode, String website, String remark,
			int chargeBy, Integer contactsId, int createBy,
			Date createDatetime, int updateBy, Date updateDatetime,
			Integer grade, Integer status, String logoUrl, boolean isDeleted,
			int paymentDays, String mailingAddress, String billingAddress,
			String billingFullName, String bankName, String accountNo,
			String taxNo, String billingRemark) {
		this.tenantId = tenantId;
		this.code = code;
		this.parentId = parentId;
		this.isLeaf = isLeaf;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.fax = fax;
		this.address = address;
		this.zipCode = zipCode;
		this.website = website;
		this.remark = remark;
		this.chargeBy = chargeBy;
		this.contactsId = contactsId;
		this.createBy = createBy;
		this.createDatetime = createDatetime;
		this.updateBy = updateBy;
		this.updateDatetime = updateDatetime;
		this.grade = grade;
		this.status = status;
		this.logoUrl = logoUrl;
		this.isDeleted = isDeleted;
		this.paymentDays = paymentDays;
		this.mailingAddress = mailingAddress;
		this.billingAddress = billingAddress;
		this.billingFullName = billingFullName;
		this.bankName = bankName;
		this.accountNo = accountNo;
		this.taxNo = taxNo;
		this.billingRemark = billingRemark;
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
	 * Get 上级单位：通过该字段形成客户关系网.
	 * @return 上级单位：通过该字段形成客户关系网
	 */

	@Column(name = "PARENT_ID", nullable = false)
	public int getParentId() {
		return this.parentId;
	}

	/**
	 * Set 上级单位：通过该字段形成客户关系网.
	 * @param parentId 上级单位：通过该字段形成客户关系网
	 */
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	/**  
	 * Get 是否叶子节点.
	 * @return 是否叶子节点
	 */

	@Column(name = "IS_LEAF", nullable = false)
	public boolean getIsLeaf() {
		return this.isLeaf;
	}

	/**
	 * Set 是否叶子节点.
	 * @param isLeaf 是否叶子节点
	 */
	public void setIsLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}

	/**  
	 * Get 名称.
	 * @return 名称
	 */

	@Column(name = "NAME", nullable = false, length = 400)
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
	 * Get 联系电话.
	 * @return 联系电话
	 */

	@Column(name = "PHONE", length = 100)
	public String getPhone() {
		return this.phone;
	}

	/**
	 * Set 联系电话.
	 * @param phone 联系电话
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**  
	 * Get 电子邮件.
	 * @return 电子邮件
	 */

	@Column(name = "EMAIL", length = 100)
	public String getEmail() {
		return this.email;
	}

	/**
	 * Set 电子邮件.
	 * @param email 电子邮件
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**  
	 * Get 传真.
	 * @return 传真
	 */

	@Column(name = "FAX", length = 100)
	public String getFax() {
		return this.fax;
	}

	/**
	 * Set 传真.
	 * @param fax 传真
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}

	/**  
	 * Get 联系地址.
	 * @return 联系地址
	 */

	@Column(name = "ADDRESS", length = 400)
	public String getAddress() {
		return this.address;
	}

	/**
	 * Set 联系地址.
	 * @param address 联系地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**  
	 * Get 邮政编码.
	 * @return 邮政编码
	 */

	@Column(name = "ZIP_CODE", length = 6)
	public String getZipCode() {
		return this.zipCode;
	}

	/**
	 * Set 邮政编码.
	 * @param zipCode 邮政编码
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	/**  
	 * Get 网址.
	 * @return 网址
	 */

	@Column(name = "WEBSITE", length = 400)
	public String getWebsite() {
		return this.website;
	}

	/**
	 * Set 网址.
	 * @param website 网址
	 */
	public void setWebsite(String website) {
		this.website = website;
	}

	/**  
	 * Get 备注.
	 * @return 备注
	 */

	@Column(name = "REMARK", length = 2000)
	public String getRemark() {
		return this.remark;
	}

	/**
	 * Set 备注.
	 * @param remark 备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**  
	 */

	@Column(name = "CHARGE_BY", nullable = false)
	public int getChargeBy() {
		return this.chargeBy;
	}

	/**
	 */
	public void setChargeBy(int chargeBy) {
		this.chargeBy = chargeBy;
	}

	/**  
	 * Get 默认联系人ID.
	 * @return 默认联系人ID
	 */

	@Column(name = "CONTACTS_ID")
	public Integer getContactsId() {
		return this.contactsId;
	}

	/**
	 * Set 默认联系人ID.
	 * @param contactsId 默认联系人ID
	 */
	public void setContactsId(Integer contactsId) {
		this.contactsId = contactsId;
	}

	/**  
	 */

	@Column(name = "CREATE_BY", nullable = false)
	public int getCreateBy() {
		return this.createBy;
	}

	/**
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
	 */

	@Column(name = "UPDATE_BY", nullable = false)
	public int getUpdateBy() {
		return this.updateBy;
	}

	/**
	 */
	public void setUpdateBy(int updateBy) {
		this.updateBy = updateBy;
	}

	/**  
	 * Get 上次修改时间.
	 * @return 上次修改时间
	 */

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATE_DATETIME", nullable = false, length = 19)
	public Date getUpdateDatetime() {
		return this.updateDatetime;
	}

	/**
	 * Set 上次修改时间.
	 * @param updateDatetime 上次修改时间
	 */
	public void setUpdateDatetime(Date updateDatetime) {
		this.updateDatetime = updateDatetime;
	}

	/**  
	 * Get 客户等级（3102）
	        1：1星
	        2：2星
	        3：3星
	        4：4星
	        5：5星.
	 * @return 客户等级（3102）
	        1：1星
	        2：2星
	        3：3星
	        4：4星
	        5：5星
	 */

	@Column(name = "GRADE")
	public Integer getGrade() {
		return this.grade;
	}

	/**
	 * Set 客户等级（3102）
	        1：1星
	        2：2星
	        3：3星
	        4：4星
	        5：5星.
	 * @param grade 客户等级（3102）
	        1：1星
	        2：2星
	        3：3星
	        4：4星
	        5：5星
	 */
	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	/**  
	 * Get 客户状态（3103）
	        1：潜在
	        2：有意向
	        3：失败
	        4：已流失
	        5：已成交
	        6：维护
	        7：重点维护.
	 * @return 客户状态（3103）
	        1：潜在
	        2：有意向
	        3：失败
	        4：已流失
	        5：已成交
	        6：维护
	        7：重点维护
	 */

	@Column(name = "STATUS")
	public Integer getStatus() {
		return this.status;
	}

	/**
	 * Set 客户状态（3103）
	        1：潜在
	        2：有意向
	        3：失败
	        4：已流失
	        5：已成交
	        6：维护
	        7：重点维护.
	 * @param status 客户状态（3103）
	        1：潜在
	        2：有意向
	        3：失败
	        4：已流失
	        5：已成交
	        6：维护
	        7：重点维护
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**  
	 * Get LOGO存放路径.
	 * @return LOGO存放路径
	 */

	@Column(name = "LOGO_URL", length = 200)
	public String getLogoUrl() {
		return this.logoUrl;
	}

	/**
	 * Set LOGO存放路径.
	 * @param logoUrl LOGO存放路径
	 */
	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	/**  
	 * Get 是否已删除.
	 * @return 是否已删除
	 */

	@Column(name = "IS_DELETED", nullable = false)
	public boolean getIsDeleted() {
		return this.isDeleted;
	}

	/**
	 * Set 是否已删除.
	 * @param isDeleted 是否已删除
	 */
	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	/**  
	 * Get 账期.
	 * @return 账期
	 */

	@Column(name = "PAYMENT_DAYS", nullable = false)
	public int getPaymentDays() {
		return this.paymentDays;
	}

	/**
	 * Set 账期.
	 * @param paymentDays 账期
	 */
	public void setPaymentDays(int paymentDays) {
		this.paymentDays = paymentDays;
	}

	/**  
	 * Get 发票邮寄地址.
	 * @return 发票邮寄地址
	 */

	@Column(name = "MAILING_ADDRESS", length = 400)
	public String getMailingAddress() {
		return this.mailingAddress;
	}

	/**
	 * Set 发票邮寄地址.
	 * @param mailingAddress 发票邮寄地址
	 */
	public void setMailingAddress(String mailingAddress) {
		this.mailingAddress = mailingAddress;
	}

	/**  
	 * Get 开票地址.
	 * @return 开票地址
	 */

	@Column(name = "BILLING_ADDRESS", length = 400)
	public String getBillingAddress() {
		return this.billingAddress;
	}

	/**
	 * Set 开票地址.
	 * @param billingAddress 开票地址
	 */
	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}

	/**  
	 * Get 开票全称.
	 * @return 开票全称
	 */

	@Column(name = "BILLING_FULL_NAME", length = 100)
	public String getBillingFullName() {
		return this.billingFullName;
	}

	/**
	 * Set 开票全称.
	 * @param billingFullName 开票全称
	 */
	public void setBillingFullName(String billingFullName) {
		this.billingFullName = billingFullName;
	}

	/**  
	 * Get 银行名称.
	 * @return 银行名称
	 */

	@Column(name = "BANK_NAME", length = 100)
	public String getBankName() {
		return this.bankName;
	}

	/**
	 * Set 银行名称.
	 * @param bankName 银行名称
	 */
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	/**  
	 * Get 帐号.
	 * @return 帐号
	 */

	@Column(name = "ACCOUNT_NO", length = 100)
	public String getAccountNo() {
		return this.accountNo;
	}

	/**
	 * Set 帐号.
	 * @param accountNo 帐号
	 */
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	/**  
	 * Get 税号.
	 * @return 税号
	 */

	@Column(name = "TAX_NO", length = 100)
	public String getTaxNo() {
		return this.taxNo;
	}

	/**
	 * Set 税号.
	 * @param taxNo 税号
	 */
	public void setTaxNo(String taxNo) {
		this.taxNo = taxNo;
	}

	/**  
	 * Get 开票注意点.
	 * @return 开票注意点
	 */

	@Column(name = "BILLING_REMARK", length = 2000)
	public String getBillingRemark() {
		return this.billingRemark;
	}

	/**
	 * Set 开票注意点.
	 * @param billingRemark 开票注意点
	 */
	public void setBillingRemark(String billingRemark) {
		this.billingRemark = billingRemark;
	}

}
