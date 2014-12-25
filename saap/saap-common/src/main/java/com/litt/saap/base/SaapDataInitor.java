package com.litt.saap.base;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.litt.saap.base.castor.CastorFactory;
import com.litt.saap.base.castor.ICastor;
import com.litt.saap.base.meta.EntityResource;
import com.litt.saap.base.meta.Resources;
import com.litt.saap.base.utils.CastorUtils;

public class SaapDataInitor {
	static Log logger = LogFactory.getLog(SaapDataInitor.class);
	
	private static ScheduledExecutorService executor = null;
	
	private static SaapDataInitor instance = new SaapDataInitor();
	
	private SaapDataInitor(){
		executor = Executors.newScheduledThreadPool(5);
	}
	
	public static SaapDataInitor getInstance(){
		return instance;
	}
	
	public void init(){
		try {
			List<Resources> resourcesList = CastorUtils.getInstance().listByCastor(Resources.class, SaasConstants.DATA_MAPPING_FILE, SaasConstants.DATA_CONF_FILE);
			if(null != resourcesList){
				for (Resources resources : resourcesList) {
					List<EntityResource> entityResourceList = resources.getEntityResourceList();
					
					for (EntityResource entityResource : entityResourceList) {
						logger.info(entityResource.toString());
						if(!entityResource.isEnable()) continue;
						
						ICastor castor = null;
						try {
							castor = CastorFactory.getFactory().getCastor(entityResource.getImplClazz());
						} catch (Exception e) {
							logger.error(e.getMessage(), e);
						}
						if(null == castor) continue;
						
						//同步
						if(!entityResource.isAsync()){
							try{
								castor.cast(entityResource);
							}catch(Exception e){
								logger.error(e.getMessage(), e);
							}
						}
						
						//异步
						else{
							executor.submit(new CastorRunnable(castor, entityResource));
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	private class CastorRunnable implements Runnable {
		private ICastor castor = null;
		private EntityResource entityResource = null;
		
		private CastorRunnable(ICastor castor, EntityResource entityResource){
			this.castor = castor;
			this.entityResource = entityResource;
		}
		
		@Override
		public void run() {
			try {
				castor.cast(entityResource);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
		
	}
	
}
