package com.litt.saap.base.po;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "city")
public class City implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "cityId", unique = true, nullable = false, length = 20)
	private String cityId;
	
	@Column(name = "cityName", nullable = false, length = 50)
	private String cityName;
	
	@Column(name = "provinceId", nullable = false, length = 20)
	private String provinceId;
	
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	public String getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
}
