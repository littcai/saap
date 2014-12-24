package com.litt.saap.assistant.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.context.request.WebRequest;

import com.litt.core.common.BeanManager;
import com.litt.core.dao.BaseJdbcDao;
import com.litt.core.dao.IResultsetTransformer;
import com.litt.core.dao.page.IPageList;
import com.litt.core.dao.ql.PageParam;
import com.litt.core.util.StringUtils;
import com.litt.core.web.util.WebUtils;
import com.litt.saap.assistant.service.IQuickViewService;
import com.litt.saap.assistant.webservice.IQuickViewWebService;
import com.litt.saap.common.vo.LoginUserVo;
import com.litt.saap.common.vo.TenantUserVo;
import com.litt.saap.core.module.dynamicform.model.Field;
import com.litt.saap.core.module.quickview.QuickViewManager;
import com.litt.saap.core.module.quickview.model.QuickView;
import com.litt.saap.core.module.quickview.model.dataset.SQLDataset;
import com.litt.saap.core.module.quickview.model.search.Search;
import com.litt.saap.core.module.quickview.model.table.Column;
import com.litt.saap.core.module.quickview.model.table.Table;
import com.litt.saap.system.webservice.IUserWebService;
import com.mysql.jdbc.log.LogUtils;

/**
 * @author littcai
 *
 */
public class QuickViewServiceImpl implements IQuickViewService, IQuickViewWebService {
	
	@Resource
	private BaseJdbcDao jdbDao;
	@Resource
	private IUserWebService userWebService;
	
	/* (non-Javadoc)
	 * @see com.litt.saap.assistant.service.impl.IQuickViewService#listPage(java.lang.String, com.litt.core.dao.ql.PageParam)
	 */
	@Override
	public IPageList listPage(String sql, PageParam pageParam)
	{
		IPageList pageList = jdbDao.listPage(sql, pageParam);
		
		return pageList;
	}
	
	/**
	 * Generate.
	 *
	 * @param quickViewCode the quick view code
	 * @param loginUserVo the login user vo
	 * @param request the request
	 * @return the map
	 */
	public Map<String, Object> generate(String quickViewCode, LoginUserVo loginUserVo, WebRequest request)
	{
	  Map<String, Object> resultMap = new HashMap<String, Object>();
	  
	  final int tenantId = loginUserVo.getTenantId();
	  final Locale locale = loginUserVo.toLocale();
	  //读取快速视图定义
    QuickView quickView = QuickViewManager.getInstance().getQuickView(quickViewCode);
    
    //处理查询条件
    Search search = quickView.getSearch();
    List<Field> fieldList = search.getAllFieldList();
    PageParam pageParam = WebUtils.getPageParam(request);
    pageParam.addCond("tenantId", loginUserVo.getTenantId());   
    pageParam.addCond("isDeleted", false);
    for (Field field : fieldList) {
      String paramValue = request.getParameter(field.getKey());
      if(StringUtils.isEmpty(paramValue))
        continue;
      
      pageParam.addCond(field.getKey(), paramValue);
    }
    //处理数据源
    SQLDataset dataset = (SQLDataset)quickView.getDataset();
    IPageList pageList = this.listPage(dataset.getSql(), pageParam);
    //处理数据表格
    final Table table = quickView.getTable();
    pageList.setResultsetTransformer(new IResultsetTransformer(){

      @Override
      public List transform(List srcList) {
        for (int i = 0; i<srcList.size(); i++) 
        {
          Map<String, Object> rowMap = (Map<String, Object>)srcList.get(i);
          List<Column> columnList = table.takeConverterColumnList();
          for (Column column : columnList) {
            String converter = column.getConverter();
            //参数字典的转换
            if(StringUtils.startsWith(converter, "dictparam") && rowMap.containsKey(column.getName()))
            {
              String value = rowMap.get(column.getName()).toString();
              value = BeanManager.getMessage(converter+"."+value, locale);
              rowMap.put(column.getName(), value);
            }
            else if(StringUtils.startsWith(converter, "user") && rowMap.containsKey(column.getName()))
            {
              Integer userId = (Integer)rowMap.get(column.getName());
              TenantUserVo user = userWebService.findById(tenantId, userId);
              rowMap.put(column.getName(), user.getUserName());
            }
            else if(StringUtils.startsWith(converter, "i18n"))
            {
              String key = StringUtils.substringAfter(converter, "."); 
              Object value = rowMap.get(column.getName());
              if(value instanceof Boolean)
              {  
                Boolean booleanValue  = (Boolean)value;
                if(booleanValue)
                {
                  value = BeanManager.getMessage(key+".1", locale);
                }
                else {
                  value = BeanManager.getMessage(key+".0", locale);
                }
              }
              else {
                value = BeanManager.getMessage(key+"."+value, locale);
              }
              rowMap.put(column.getName(), value);
            }
          }
        } 
        return srcList;
      }
    });
    
    //return params to response
    resultMap.put("search", search);
    resultMap.put("pageParam", pageParam);  
    resultMap.put(dataset.getId(), pageList); 
    resultMap.put("table", table);
    
    return resultMap;
	}
	

}
