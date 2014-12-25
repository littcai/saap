package com.litt.saap.base.meta;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.litt.saap.base.po.Area;
import com.litt.saap.base.po.City;
import com.litt.saap.base.po.Province;

public class GeographyMetaData implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private List<Province> provinceList = new ArrayList<Province>();
	private List<City> cityList = new ArrayList<City>();
	private List<Area> areaList = new ArrayList<Area>();
	
	public List<Province> getProvinceList() {
		return provinceList;
	}
	public void setProvinceList(List<Province> provinceList) {
		this.provinceList = provinceList;
	}
	public List<City> getCityList() {
		return cityList;
	}
	public void setCityList(List<City> cityList) {
		this.cityList = cityList;
	}
	public List<Area> getAreaList() {
		return areaList;
	}
	public void setAreaList(List<Area> areaList) {
		this.areaList = areaList;
	}
	
}
