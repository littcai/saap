package com.litt.saap.system.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;

import com.itextpdf.text.pdf.TSAClient;
import com.litt.core.util.DateUtils;
import com.litt.saap.system.service.ISystemInfoService;
import com.litt.saap.system.vo.SystemInfoVo;

/**
 * 系统信息服务.
 * 
 * <pre><b>描述：</b>
 *    
 * </pre>
 * 
 * <pre><b>修改记录：</b>
 *    
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">蔡源</a>
 * @since 2013-9-2
 * @version 1.0
 */
public class SystemInfoServiceImpl implements ISystemInfoService {
  
  @Resource
  private JdbcTemplate jdbcTemplate;
  
  private String baseUrl;  
  
  private static final SystemInfoVo systemInfo = new SystemInfoVo();
	
	/* (non-Javadoc)
	 * @see com.litt.saap.system.service.impl.ISystemInfoService#update(com.litt.saap.system.vo.SystemInfoVo)
	 */
	public void update(SystemInfoVo vo)
	{
		
	}
	
	/* (non-Javadoc)
	 * @see com.litt.saap.system.service.impl.ISystemInfoService#getSystemInfo()
	 */
	public SystemInfoVo getSystemInfo()
	{
	  if(systemInfo.getSystemId()==null)
	  {
  	  String sql = "SELECT * FROM SYSTEM_INFO WHERE SYSTEM_ID=?";
  	  jdbcTemplate.query(sql, new Object[]{1}, new RowCallbackHandler(){
  
        @Override
        public void processRow(ResultSet rs) throws SQLException
        {
          systemInfo.setSystemId(1L);
          systemInfo.setSystemCode(rs.getString("SYSTEM_CODE"));
          systemInfo.setSystemName(rs.getString("SYSTEM_NAME"));
          systemInfo.setSystemVersion(rs.getString("SYSTEM_VERSION"));
          systemInfo.setCompanyName(rs.getString("COMPANY_NAME"));
          systemInfo.setCopyright(rs.getString("COPYRIGHT"));
          systemInfo.setLastUpdateDatetime(DateUtils.convertSqlDate2Date(rs.getDate("LAST_UPDATE_DATETIME")));
        }
  	    
  	  });	
  	}
		return systemInfo;
	}

  
  /**
   * @param baseUrl the baseUrl to set
   */
  public void setBaseUrl(String baseUrl)
  {
    this.baseUrl = baseUrl;
  }

}
