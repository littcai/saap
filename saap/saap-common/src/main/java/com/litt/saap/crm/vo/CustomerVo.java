package com.litt.saap.crm.vo;

import java.io.Serializable;
import java.util.Date;


/**
 * CustomerVo.
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
 * @since 2014年10月8日
 * @version 1.0
 */
public class CustomerVo implements Serializable {

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

  public CustomerVo() {
  }

  public CustomerVo(int tenantId, String code, int parentId, boolean isLeaf,
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

  public CustomerVo(int tenantId, String code, int parentId, boolean isLeaf,
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
   * @return the id
   */
  public Integer getId()
  {
    return id;
  }

  
  /**
   * @param id the id to set
   */
  public void setId(Integer id)
  {
    this.id = id;
  }

  
  /**
   * @return the tenantId
   */
  public int getTenantId()
  {
    return tenantId;
  }

  
  /**
   * @param tenantId the tenantId to set
   */
  public void setTenantId(int tenantId)
  {
    this.tenantId = tenantId;
  }

  
  /**
   * @return the code
   */
  public String getCode()
  {
    return code;
  }

  
  /**
   * @param code the code to set
   */
  public void setCode(String code)
  {
    this.code = code;
  }

  
  /**
   * @return the parentId
   */
  public int getParentId()
  {
    return parentId;
  }

  
  /**
   * @param parentId the parentId to set
   */
  public void setParentId(int parentId)
  {
    this.parentId = parentId;
  }

  
  /**
   * @return the isLeaf
   */
  public boolean isLeaf()
  {
    return isLeaf;
  }

  
  /**
   * @param isLeaf the isLeaf to set
   */
  public void setLeaf(boolean isLeaf)
  {
    this.isLeaf = isLeaf;
  }

  
  /**
   * @return the name
   */
  public String getName()
  {
    return name;
  }

  
  /**
   * @param name the name to set
   */
  public void setName(String name)
  {
    this.name = name;
  }

  
  /**
   * @return the phone
   */
  public String getPhone()
  {
    return phone;
  }

  
  /**
   * @param phone the phone to set
   */
  public void setPhone(String phone)
  {
    this.phone = phone;
  }

  
  /**
   * @return the email
   */
  public String getEmail()
  {
    return email;
  }

  
  /**
   * @param email the email to set
   */
  public void setEmail(String email)
  {
    this.email = email;
  }

  
  /**
   * @return the fax
   */
  public String getFax()
  {
    return fax;
  }

  
  /**
   * @param fax the fax to set
   */
  public void setFax(String fax)
  {
    this.fax = fax;
  }

  
  /**
   * @return the address
   */
  public String getAddress()
  {
    return address;
  }

  
  /**
   * @param address the address to set
   */
  public void setAddress(String address)
  {
    this.address = address;
  }

  
  /**
   * @return the zipCode
   */
  public String getZipCode()
  {
    return zipCode;
  }

  
  /**
   * @param zipCode the zipCode to set
   */
  public void setZipCode(String zipCode)
  {
    this.zipCode = zipCode;
  }

  
  /**
   * @return the website
   */
  public String getWebsite()
  {
    return website;
  }

  
  /**
   * @param website the website to set
   */
  public void setWebsite(String website)
  {
    this.website = website;
  }

  
  /**
   * @return the remark
   */
  public String getRemark()
  {
    return remark;
  }

  
  /**
   * @param remark the remark to set
   */
  public void setRemark(String remark)
  {
    this.remark = remark;
  }

  
  /**
   * @return the chargeBy
   */
  public int getChargeBy()
  {
    return chargeBy;
  }

  
  /**
   * @param chargeBy the chargeBy to set
   */
  public void setChargeBy(int chargeBy)
  {
    this.chargeBy = chargeBy;
  }

  
  /**
   * @return the contactsId
   */
  public Integer getContactsId()
  {
    return contactsId;
  }

  
  /**
   * @param contactsId the contactsId to set
   */
  public void setContactsId(Integer contactsId)
  {
    this.contactsId = contactsId;
  }

  
  /**
   * @return the createBy
   */
  public int getCreateBy()
  {
    return createBy;
  }

  
  /**
   * @param createBy the createBy to set
   */
  public void setCreateBy(int createBy)
  {
    this.createBy = createBy;
  }

  
  /**
   * @return the createDatetime
   */
  public Date getCreateDatetime()
  {
    return createDatetime;
  }

  
  /**
   * @param createDatetime the createDatetime to set
   */
  public void setCreateDatetime(Date createDatetime)
  {
    this.createDatetime = createDatetime;
  }

  
  /**
   * @return the updateBy
   */
  public int getUpdateBy()
  {
    return updateBy;
  }

  
  /**
   * @param updateBy the updateBy to set
   */
  public void setUpdateBy(int updateBy)
  {
    this.updateBy = updateBy;
  }

  
  /**
   * @return the updateDatetime
   */
  public Date getUpdateDatetime()
  {
    return updateDatetime;
  }

  
  /**
   * @param updateDatetime the updateDatetime to set
   */
  public void setUpdateDatetime(Date updateDatetime)
  {
    this.updateDatetime = updateDatetime;
  }

  
  /**
   * @return the grade
   */
  public Integer getGrade()
  {
    return grade;
  }

  
  /**
   * @param grade the grade to set
   */
  public void setGrade(Integer grade)
  {
    this.grade = grade;
  }

  
  /**
   * @return the status
   */
  public Integer getStatus()
  {
    return status;
  }

  
  /**
   * @param status the status to set
   */
  public void setStatus(Integer status)
  {
    this.status = status;
  }

  
  /**
   * @return the logoUrl
   */
  public String getLogoUrl()
  {
    return logoUrl;
  }

  
  /**
   * @param logoUrl the logoUrl to set
   */
  public void setLogoUrl(String logoUrl)
  {
    this.logoUrl = logoUrl;
  }

  
  /**
   * @return the isDeleted
   */
  public boolean isDeleted()
  {
    return isDeleted;
  }

  
  /**
   * @param isDeleted the isDeleted to set
   */
  public void setDeleted(boolean isDeleted)
  {
    this.isDeleted = isDeleted;
  }

  
  /**
   * @return the paymentDays
   */
  public int getPaymentDays()
  {
    return paymentDays;
  }

  
  /**
   * @param paymentDays the paymentDays to set
   */
  public void setPaymentDays(int paymentDays)
  {
    this.paymentDays = paymentDays;
  }

  
  /**
   * @return the mailingAddress
   */
  public String getMailingAddress()
  {
    return mailingAddress;
  }

  
  /**
   * @param mailingAddress the mailingAddress to set
   */
  public void setMailingAddress(String mailingAddress)
  {
    this.mailingAddress = mailingAddress;
  }

  
  /**
   * @return the billingAddress
   */
  public String getBillingAddress()
  {
    return billingAddress;
  }

  
  /**
   * @param billingAddress the billingAddress to set
   */
  public void setBillingAddress(String billingAddress)
  {
    this.billingAddress = billingAddress;
  }

  
  /**
   * @return the billingFullName
   */
  public String getBillingFullName()
  {
    return billingFullName;
  }

  
  /**
   * @param billingFullName the billingFullName to set
   */
  public void setBillingFullName(String billingFullName)
  {
    this.billingFullName = billingFullName;
  }

  
  /**
   * @return the bankName
   */
  public String getBankName()
  {
    return bankName;
  }

  
  /**
   * @param bankName the bankName to set
   */
  public void setBankName(String bankName)
  {
    this.bankName = bankName;
  }

  
  /**
   * @return the accountNo
   */
  public String getAccountNo()
  {
    return accountNo;
  }

  
  /**
   * @param accountNo the accountNo to set
   */
  public void setAccountNo(String accountNo)
  {
    this.accountNo = accountNo;
  }

  
  /**
   * @return the taxNo
   */
  public String getTaxNo()
  {
    return taxNo;
  }

  
  /**
   * @param taxNo the taxNo to set
   */
  public void setTaxNo(String taxNo)
  {
    this.taxNo = taxNo;
  }

  
  /**
   * @return the billingRemark
   */
  public String getBillingRemark()
  {
    return billingRemark;
  }

  
  /**
   * @param billingRemark the billingRemark to set
   */
  public void setBillingRemark(String billingRemark)
  {
    this.billingRemark = billingRemark;
  }
  
}
