package com.litt.saap.assistant.webservice;

import java.util.Map;

import org.springframework.web.context.request.WebRequest;

import com.litt.core.dao.page.IPageList;
import com.litt.core.dao.ql.PageParam;
import com.litt.saap.common.vo.LoginUserVo;

public interface IQuickViewWebService {

	/**
	 * List page.
	 *
	 * @param sql the sql
	 * @param pageParam the page param
	 * @return the i page list
	 */
	public IPageList listPage(String sql, PageParam pageParam);
	
	 /**
   * Generate.
   *
   * @param quickViewCode the quick view code
   * @param loginUserVo the login user vo
   * @param request the request
   * @return the map
   */
  public Map<String, Object> generate(String quickViewCode, LoginUserVo loginUserVo, WebRequest request);

}