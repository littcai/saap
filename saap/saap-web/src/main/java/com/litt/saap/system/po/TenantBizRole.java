package com.litt.saap.system.po;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 租户业务角色定义
 * Created by simon on 2015/1/5.
 */
@Entity
@Table(name="tenant_biz_role")
public class TenantBizRole implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name="tenant_id", nullable = false)
    private Integer tenantId;
    @Column
    private String name;
    @Column(name="created_by")
    private String createdBy;
    @Column(name="created_at")
    private Date createdAt;
    @Column(name="updated_by")
    private String updatedBy;
    @Column(name="updated_at")
    private Date updatedAt;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
