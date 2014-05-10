package com.litt.saap.system.po;

/**
 * TenantDictParam.
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
 * @since 2014年5月5日
 * @version 1.0
 */
public class TenantDictParam extends DictParam {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	private int tenantId;

	/**
	 * @return the tenantId
	 */
	public int getTenantId() {
		return tenantId;
	}

	/**
	 * @param tenantId the tenantId to set
	 */
	public void setTenantId(int tenantId) {
		this.tenantId = tenantId;
	}

}
