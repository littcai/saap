package com.litt.saap.crm.po;

import java.util.Date;

import java.io.Serializable;

/**
 * 客户信息表<br>
 * 表名：customer<br>
 * @author Hibernate Tools 3.2.4.GA
 * @version 1.0
 * @since 2013-10-14 23:28:21
 */
public class Customer implements Serializable {
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
     * 负责人（操作员或部门）.
     */
    private int chargeUserId;
    
    /**
     * 默认联系人ID.
     */
    private Integer contactsId;
    
    /**
     * 创建人.
     */
    private int createUserId;
    
    /**
     * 创建时间.
     */
    private Date createDatetime;
    
    /**
     * 上次修改人.
     */
    private int updateUserId;
    
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
    

    public Customer() {
    }

	
    public Customer(int tenantId, String code, int parentId, boolean isLeaf, String name, int chargeUserId, int createUserId, Date createDatetime, int updateUserId, Date updateDatetime, boolean isDeleted) {
        this.tenantId = tenantId;
        this.code = code;
        this.parentId = parentId;
        this.isLeaf = isLeaf;
        this.name = name;
        this.chargeUserId = chargeUserId;
        this.createUserId = createUserId;
        this.createDatetime = createDatetime;
        this.updateUserId = updateUserId;
        this.updateDatetime = updateDatetime;
        this.isDeleted = isDeleted;
    }
    public Customer(int tenantId, String code, int parentId, boolean isLeaf, String name, String phone, String email, String fax, String address, String zipCode, String website, String remark, int chargeUserId, Integer contactsId, int createUserId, Date createDatetime, int updateUserId, Date updateDatetime, Integer grade, Integer status, String logoUrl, boolean isDeleted) {
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
       this.chargeUserId = chargeUserId;
       this.contactsId = contactsId;
       this.createUserId = createUserId;
       this.createDatetime = createDatetime;
       this.updateUserId = updateUserId;
       this.updateDatetime = updateDatetime;
       this.grade = grade;
       this.status = status;
       this.logoUrl = logoUrl;
       this.isDeleted = isDeleted;
    }
   
    /**  
     * 取得 序号.
     * @return 序号
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * 设置 序号.
     * @param id 序号
     */   
    public void setId(Integer id) {
        this.id = id;
    }
    /**  
     * 取得 租户ID.
     * @return 租户ID
     */
    public int getTenantId() {
        return this.tenantId;
    }

    /**
     * 设置 租户ID.
     * @param tenantId 租户ID
     */   
    public void setTenantId(int tenantId) {
        this.tenantId = tenantId;
    }
    /**  
     * 取得 编号.
     * @return 编号
     */
    public String getCode() {
        return this.code;
    }

    /**
     * 设置 编号.
     * @param code 编号
     */   
    public void setCode(String code) {
        this.code = code;
    }
    /**  
     * 取得 上级单位：通过该字段形成客户关系网.
     * @return 上级单位：通过该字段形成客户关系网
     */
    public int getParentId() {
        return this.parentId;
    }

    /**
     * 设置 上级单位：通过该字段形成客户关系网.
     * @param parentId 上级单位：通过该字段形成客户关系网
     */   
    public void setParentId(int parentId) {
        this.parentId = parentId;
    }
    /**  
     * 取得 是否叶子节点.
     * @return 是否叶子节点
     */
    public boolean isIsLeaf() {
        return this.isLeaf;
    }

    /**
     * 设置 是否叶子节点.
     * @param isLeaf 是否叶子节点
     */   
    public void setIsLeaf(boolean isLeaf) {
        this.isLeaf = isLeaf;
    }
    /**  
     * 取得 名称.
     * @return 名称
     */
    public String getName() {
        return this.name;
    }

    /**
     * 设置 名称.
     * @param name 名称
     */   
    public void setName(String name) {
        this.name = name;
    }
    /**  
     * 取得 联系电话.
     * @return 联系电话
     */
    public String getPhone() {
        return this.phone;
    }

    /**
     * 设置 联系电话.
     * @param phone 联系电话
     */   
    public void setPhone(String phone) {
        this.phone = phone;
    }
    /**  
     * 取得 电子邮件.
     * @return 电子邮件
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * 设置 电子邮件.
     * @param email 电子邮件
     */   
    public void setEmail(String email) {
        this.email = email;
    }
    /**  
     * 取得 传真.
     * @return 传真
     */
    public String getFax() {
        return this.fax;
    }

    /**
     * 设置 传真.
     * @param fax 传真
     */   
    public void setFax(String fax) {
        this.fax = fax;
    }
    /**  
     * 取得 联系地址.
     * @return 联系地址
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * 设置 联系地址.
     * @param address 联系地址
     */   
    public void setAddress(String address) {
        this.address = address;
    }
    /**  
     * 取得 邮政编码.
     * @return 邮政编码
     */
    public String getZipCode() {
        return this.zipCode;
    }

    /**
     * 设置 邮政编码.
     * @param zipCode 邮政编码
     */   
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
    /**  
     * 取得 网址.
     * @return 网址
     */
    public String getWebsite() {
        return this.website;
    }

    /**
     * 设置 网址.
     * @param website 网址
     */   
    public void setWebsite(String website) {
        this.website = website;
    }
    /**  
     * 取得 备注.
     * @return 备注
     */
    public String getRemark() {
        return this.remark;
    }

    /**
     * 设置 备注.
     * @param remark 备注
     */   
    public void setRemark(String remark) {
        this.remark = remark;
    }
    /**  
     * 取得 负责人（操作员或部门）.
     * @return 负责人（操作员或部门）
     */
    public int getChargeUserId() {
        return this.chargeUserId;
    }

    /**
     * 设置 负责人（操作员或部门）.
     * @param chargeUserId 负责人（操作员或部门）
     */   
    public void setChargeUserId(int chargeUserId) {
        this.chargeUserId = chargeUserId;
    }
    /**  
     * 取得 默认联系人ID.
     * @return 默认联系人ID
     */
    public Integer getContactsId() {
        return this.contactsId;
    }

    /**
     * 设置 默认联系人ID.
     * @param contactsId 默认联系人ID
     */   
    public void setContactsId(Integer contactsId) {
        this.contactsId = contactsId;
    }
    /**  
     * 取得 创建人.
     * @return 创建人
     */
    public int getCreateUserId() {
        return this.createUserId;
    }

    /**
     * 设置 创建人.
     * @param createUserId 创建人
     */   
    public void setCreateUserId(int createUserId) {
        this.createUserId = createUserId;
    }
    /**  
     * 取得 创建时间.
     * @return 创建时间
     */
    public Date getCreateDatetime() {
        return this.createDatetime;
    }

    /**
     * 设置 创建时间.
     * @param createDatetime 创建时间
     */   
    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }
    /**  
     * 取得 上次修改人.
     * @return 上次修改人
     */
    public int getUpdateUserId() {
        return this.updateUserId;
    }

    /**
     * 设置 上次修改人.
     * @param updateUserId 上次修改人
     */   
    public void setUpdateUserId(int updateUserId) {
        this.updateUserId = updateUserId;
    }
    /**  
     * 取得 上次修改时间.
     * @return 上次修改时间
     */
    public Date getUpdateDatetime() {
        return this.updateDatetime;
    }

    /**
     * 设置 上次修改时间.
     * @param updateDatetime 上次修改时间
     */   
    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }
    /**  
     * 取得 客户等级（3102）
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
    public Integer getGrade() {
        return this.grade;
    }

    /**
     * 设置 客户等级（3102）
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
     * 取得 客户状态（3103）
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
    public Integer getStatus() {
        return this.status;
    }

    /**
     * 设置 客户状态（3103）
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
     * 取得 LOGO存放路径.
     * @return LOGO存放路径
     */
    public String getLogoUrl() {
        return this.logoUrl;
    }

    /**
     * 设置 LOGO存放路径.
     * @param logoUrl LOGO存放路径
     */   
    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }
    /**  
     * 取得 是否已删除.
     * @return 是否已删除
     */
    public boolean getIsDeleted() {
        return this.isDeleted;
    }

    /**
     * 设置 是否已删除.
     * @param isDeleted 是否已删除
     */   
    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

}
