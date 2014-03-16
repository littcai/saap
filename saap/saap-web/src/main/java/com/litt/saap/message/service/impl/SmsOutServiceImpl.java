package com.litt.saap.message.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.litt.core.dao.page.IPageList;
import com.litt.core.dao.ql.PageParam;
import com.litt.saap.common.vo.LoginUserVo;
import com.litt.saap.core.module.message.model.SmsHttpResponse;
import com.litt.saap.core.module.message.model.SmsResponse;
import com.litt.saap.core.web.util.LoginUtils;
import com.litt.saap.message.dao.SmsOutDao;
import com.litt.saap.message.po.SmsOut;
import com.litt.saap.message.service.ISmsOutService;

/**
 * 
 * SMS service impl.
 * <pre><b>Description：</b>
 *    SMS Management
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">Bob.cai</a>
 * @since 2014-02-17
 * @version 1.0
 */
public class SmsOutServiceImpl implements ISmsOutService
{ 
	/** Logger. */
    private static final Logger logger = LoggerFactory.getLogger(SmsOutServiceImpl.class);
    
    @Resource
    private SmsOutDao smsOutDao;
    
    @Resource
    private RestTemplate restTemplate;
    
    private String url = "http://60.29.235.82:8081/sms-web/rest/sms/sendSms.json";

    private String username = "Shguancha";

    private String password = "RU@h@zedV3Hi";

   	/**
	 * Save.
	 * @param smsOut SmsOut
	 * @return id
	 */
	public Integer save(SmsOut smsOut)
	{
		//验证手机号是否有效，无效不保存
		String mobile = smsOut.getReceiver();
		String sender = "";
		if(NumPoolManager.isMobile(mobile))
		{
			sender = NumPoolManager.MOBILE_ACCOUNT;
		}
		else if(NumPoolManager.isTelecom(mobile))
		{
			sender = NumPoolManager.MOBILE_ACCOUNT;
		}
		else if(NumPoolManager.isUnicom(mobile))
		{
			sender = NumPoolManager.MOBILE_ACCOUNT;
		}
		else {
			return null;
		}
		
		smsOut.setTenantId(LoginUtils.getTenantId());
		smsOut.setSender(sender);//FIXME
		smsOut.setCreateBy(LoginUtils.getLoginOpId().intValue());
		smsOut.setCreateDatetime(new Date());
		smsOut.setSendFlag(false);
		smsOut.setSendDatetime(smsOut.getCreateDatetime());
		
		return smsOutDao.save(smsOut);
	}
	
	/**
	 * 异步发送短信.
	 *
	 * @param smsOuts the sms outs
	 */
	public void doAsycSend(final SmsOut[] smsOuts)
	{
		for (SmsOut smsOut : smsOuts) {
			this.save(smsOut);
		}	
		//发送短信
		Thread thread = new Thread(new Runnable() {
				
				@Override
				public void run() {
					
					for (SmsOut smsOut : smsOuts) {
						sendSms(smsOut);
					}    
				}
		});
		thread.start();	
	}
	
	/**
	 * 重发短信.
	 *
	 * @param id the id
	 */
	public void doResend(Integer id)
	{
		SmsOut smsOut = this.load(id);
		//重发短信
		this.sendSms(smsOut);
	}
	
   	/**
	 * Update.
	 * @param smsOut SmsOut
	 */
	public void update(SmsOut smsOut) 
	{
		//校验租户权限
		LoginUtils.validateTenant(smsOut.getTenantId());
	
		smsOutDao.update(smsOut);
	}			
   
   	/**
	 * Delete by id.
	 * @param id id
	 */
	public void delete(Integer id) 
	{
		SmsOut smsOut = this.load(id);
		this.delete(smsOut);
	}
	
	/**
	 * Batch delete by ids.
	 * @param ids ids
	 */
	public void deleteBatch(Integer[] ids) 
	{
		if(ids!=null)
		{
			for (Integer id : ids) {
				this.delete(id);
			}
		}
	}
	
	/**
	 * Delete by instance.
	 * @param id id
	 */
	public void delete(SmsOut smsOut) 
	{
		//校验租户权限
		LoginUtils.validateTenant(smsOut.getTenantId());
	
		smsOutDao.delete(smsOut);
	}
	
	/**
	 * Load by id.
	 * @param id id
	 * @return SmsOut
	 */
	public SmsOut load(Integer id) 
	{
		SmsOut smsOut = smsOutDao.load(id);
		//校验租户权限
		LoginUtils.validateTenant(smsOut.getTenantId());
	
		return smsOut;
	}
	
	/**
	 * list by page.
	 * 
	 * @param pageParam params
	 * @return IPageList IPageList
	 */
	public IPageList listPage(PageParam pageParam)
	{
		String listHql = "select obj from SmsOut obj"
			+ "-- and obj.tenantId={tenantId}"
			+ "-- and obj.sender={sender}"
			+ "-- and obj.receiver={receiver}"
			+ "-- and obj.createBy={createBy}"
			+ "-- and obj.sendDatetime>={startDate}"
			+ "-- and obj.sendDatetime<={endDate}"
			;	
		
		return smsOutDao.listPage(listHql, pageParam);
	}

	/**
	 * @param smsOut
	 */
	private void sendSms(SmsOut smsOut) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("mobile", smsOut.getReceiver());
		paramMap.put("content", smsOut.getContent());
		String endUrl = url + "?mobile={mobile}&content={content}";
		try {
			logger.info("try to send sms, mobile:{}, content:{}", new Object[]{smsOut.getReceiver(), smsOut.getContent()});
			
			ResponseEntity<SmsHttpResponse> remoteResponse = restTemplate.getForEntity(endUrl, SmsHttpResponse.class, paramMap);

		    //String response = restTemplate.getForObject(endUrl, String.class, paramMap);

		    //String response = restTemplate.postForObject(url, null, String.class, paramMap);

		    if(remoteResponse!=null)
		    {				
		        SmsResponse smsResponse = remoteResponse.getBody().getResponse();
//			                JsonEncoder encoder = new JsonEncoder();
//			                ObjectMapper objectMappper = encoder.getObjectMappper();
//			                JsonNode rootNode = objectMappper.readTree(response);
//			                SmsResponse smsResponse = encoder.decode(rootNode.path("response").toString(), SmsResponse.class);

		        if(smsResponse.isSuccess())
		        {
		        	//更新状态
					smsOut.setSendFlag(true);
					smsOut.setSendDatetime(new Date());
					smsOutDao.update(smsOut);
		        }			               
		    }
		}
		catch(Exception e)
		{
		    logger.error("短信发送系统错误，请稍后重试", e);
		    //更新状态
			smsOut.setSendDatetime(new Date());
			smsOutDao.update(smsOut);
		}
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}