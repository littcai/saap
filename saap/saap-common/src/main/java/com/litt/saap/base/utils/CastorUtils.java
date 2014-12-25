package com.litt.saap.base.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.xml.sax.InputSource;

import com.litt.core.util.ResourceUtils;
import com.litt.saap.base.SaasConstants;

@SuppressWarnings("unchecked")
public class CastorUtils {
	
	private ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
	
	private CastorUtils(){}
	
	public static CastorUtils getInstance(){
		return new CastorUtils();
	}
	
	public <T> List<T> listByCastor(Class<T> clazz, String mappingFilePath, String confFilePath){
		try {
			InputStream mappingInput = CastorUtils.class.getResourceAsStream(mappingFilePath);
			InputSource mappingIS = new InputSource(new InputStreamReader(mappingInput, SaasConstants.CHARSET));
			Mapping mapping = new Mapping();
			mapping.loadMapping(mappingIS);
			
			List<T> configs = new ArrayList<T>();
			
			Resource[] resources = resolver.getResources(confFilePath); 
			for (Resource resource : resources) {
				InputSource confIS = new InputSource(new InputStreamReader(resource.getInputStream(), SaasConstants.CHARSET));
				Unmarshaller unmarshaller = new Unmarshaller(clazz);
				//禁用ID校验，castor对于局部对象的Identity属性有误
				unmarshaller.setProperty("org.exolab.castor.xml.lenient.id.validation", "true");
				unmarshaller.setMapping(mapping);
				T config = (T)unmarshaller.unmarshal(confIS);
				configs.add(config);
			}
			
			return configs;
		} catch (Exception e) {
			throw new RuntimeException("mappingFilePath:"+mappingFilePath+", confFilePath:"+confFilePath, e);
		} 
	}
	
	public <T> T loadByCastor(Class<T> clazz, String mappingFilePath, String confFilePath){
		try {
			InputStream mappingInput = CastorUtils.class.getResourceAsStream(mappingFilePath);
			InputSource mappingIS = new InputSource(new InputStreamReader(mappingInput, SaasConstants.CHARSET));
			Mapping mapping = new Mapping();
			mapping.loadMapping(mappingIS);
			
			InputStream confInput = CastorUtils.class.getResourceAsStream(confFilePath);
			InputSource confIS = new InputSource(new InputStreamReader(confInput, SaasConstants.CHARSET));
			Unmarshaller unmarshaller = new Unmarshaller(clazz);
			//禁用ID校验，castor对于局部对象的Identity属性有误
			unmarshaller.setProperty("org.exolab.castor.xml.lenient.id.validation", "true");
			unmarshaller.setMapping(mapping);
			T config = (T)unmarshaller.unmarshal(confIS);
			
			return config;
		} catch (Exception e) {
			throw new RuntimeException("mappingFilePath:"+mappingFilePath+", confFilePath:"+confFilePath, e);
		} 
	}
	
	public <T> void updateByCastor(T object, String mappingFilePath, String confFilePath){
		try {
			InputStream mappingInput = CastorUtils.class.getResourceAsStream(mappingFilePath);
			InputSource mappingIS = new InputSource(new InputStreamReader(mappingInput));
			Mapping mapping = new Mapping();
			mapping.loadMapping(mappingIS);
			
			File confFile = ResourceUtils.getFile(confFilePath);
			Marshaller marshaller = new Marshaller(new FileWriter(confFile)); 			
			marshaller.setMapping(mapping);
			marshaller.marshal(object);
		} catch (Exception e) {
			throw new RuntimeException("mappingFilePath:"+mappingFilePath+", confFilePath:"+confFilePath, e);
		}
	}

}
