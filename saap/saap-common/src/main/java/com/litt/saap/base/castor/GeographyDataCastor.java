package com.litt.saap.base.castor;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.litt.saap.base.meta.EntityResource;
import com.litt.saap.base.meta.GeographyMetaData;
import com.litt.saap.base.po.Area;
import com.litt.saap.base.po.City;
import com.litt.saap.base.po.Province;
import com.litt.saap.base.utils.CastorUtils;

public class GeographyDataCastor implements ICastor {
	static Log logger = LogFactory.getLog(GeographyDataCastor.class);
	
	@Override
	public void cast(EntityResource entityResource) {
		GeographyMetaData geographyData = CastorUtils.getInstance().loadByCastor(GeographyMetaData.class, entityResource.getConfMappingLocation(), entityResource.getConfLocation());
		
		updateProvinceList(geographyData.getProvinceList());
		
		updateCityList(geographyData.getCityList());
		
		updateAreaList(geographyData.getAreaList());
	}
	
	private void updateProvinceList(List<Province> partyList){
//		ProvinceDao provinceDao = BeanManager.getBean("provinceDao", ProvinceDao.class);
//		for (Province province : partyList) {
//			try{
//				Province entity = provinceDao.load(province.getProvinceId());
//				if(null == entity) provinceDao.save(province);
//				//else provinceDao.update(province);
//			}catch(Exception e){
//				logger.error(e.getMessage(), e);
//			}
//		}
	}
	
	private void updateCityList(List<City> cityList){
//		CityDao cityDao = BeanManager.getBean("cityDao", CityDao.class);
//		for (City city : cityList) {
//			try{
//				City entity = cityDao.load(city.getCityId());
//				if(null == entity) cityDao.save(city);
//				//else cityDao.update(city);
//			}catch(Exception e){
//				logger.error(e.getMessage(), e);
//			}
//		}
	}
	
	private void updateAreaList(List<Area> userLoginList){
//		AreaDao areaDao = BeanManager.getBean("areaDao", AreaDao.class);
//		for (Area area : userLoginList) {
//			try{
//				Area entity = areaDao.load(area.getAreaId());
//				if(null == entity) areaDao.save(area);
//				//else areaDao.update(area);
//			}catch(Exception e){
//				logger.error(e.getMessage(), e);
//			}
//		}
	}
}
