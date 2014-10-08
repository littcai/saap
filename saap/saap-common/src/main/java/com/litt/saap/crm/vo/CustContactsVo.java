package com.litt.saap.crm.vo;

import java.io.Serializable;
import java.util.Date;


/**
 * CustContactsVo.
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
public class CustContactsVo implements Serializable {
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
   * 客户ID.
   */
  private int customerId;

  /**
   * 名称.
   */
  private String name;

  /**
   * 性别(0002)
          0:unknown
          1:male
          2:female.
   */
  private byte gender;

  /**
   * 生日.
   */
  private Date birthday;

  /**
   * 手机号.
   */
  private String mobile;

  /**
   * 电子邮件.
   */
  private String email;

  /**
   * 联系电话.
   */
  private String phone;

  /**
   * 传真号.
   */
  private String fax;

  /**
   * 地址.
   */
  private String address;

  /**
   * 邮编.
   */
  private String zipCode;

  /**
   * 头像URL.
   */
  private String headImgUrl;

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

  /**
   * 备注.
   */
  private String remark;

  
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
   * @return the customerId
   */
  public int getCustomerId()
  {
    return customerId;
  }

  
  /**
   * @param customerId the customerId to set
   */
  public void setCustomerId(int customerId)
  {
    this.customerId = customerId;
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
   * @return the gender
   */
  public byte getGender()
  {
    return gender;
  }

  
  /**
   * @param gender the gender to set
   */
  public void setGender(byte gender)
  {
    this.gender = gender;
  }

  
  /**
   * @return the birthday
   */
  public Date getBirthday()
  {
    return birthday;
  }

  
  /**
   * @param birthday the birthday to set
   */
  public void setBirthday(Date birthday)
  {
    this.birthday = birthday;
  }

  
  /**
   * @return the mobile
   */
  public String getMobile()
  {
    return mobile;
  }

  
  /**
   * @param mobile the mobile to set
   */
  public void setMobile(String mobile)
  {
    this.mobile = mobile;
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
   * @return the headImgUrl
   */
  public String getHeadImgUrl()
  {
    return headImgUrl;
  }

  
  /**
   * @param headImgUrl the headImgUrl to set
   */
  public void setHeadImgUrl(String headImgUrl)
  {
    this.headImgUrl = headImgUrl;
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
}
