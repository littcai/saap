package com.litt.saap.base.po;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "province")
public class Province implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "provinceId", unique = true, nullable = false, length = 20)
	private String provinceId;
	
	@Column(name = "provinceName", nullable = false, length = 50)
	private String provinceName;
	
	
	public String getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	
}
