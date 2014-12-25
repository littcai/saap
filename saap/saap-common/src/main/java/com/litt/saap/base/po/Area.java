package com.litt.saap.base.po;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "area")
public class Area implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "areaId", unique = true, nullable = false, length = 20)
	private String areaId;
	
	@Column(name = "areaName", nullable = false, length = 50)
	private String areaName;
	
	@Column(name = "cityId", nullable = false, length = 20)
	private String cityId;
	
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
}
