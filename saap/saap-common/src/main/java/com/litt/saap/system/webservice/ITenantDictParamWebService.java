package com.litt.saap.system.webservice;

import java.util.List;

import com.litt.saap.system.vo.TenantDictParamVo;

/**
 * IDictParamWebService.
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
public interface ITenantDictParamWebService {

	/**
	 * 保存字典管理
	 * @param tenantDictParam DictParam对象
	 * @return 主键
	 */
	public Integer save(TenantDictParamVo tenantDictParam);

	/**
	 * 修改字典管理
	 * @param tenantDictParam DictParam对象
	 */
	public void update(TenantDictParamVo tenantDictParam);

	/**
	 * 根据主键删除字典.
	 * @param id 主键
	 */
	public void delete(Integer id);

	/**
	 * 根据主键查找字典管理
	 * @param id 主键
	 * @return DictParam对象
	 */
	public TenantDictParamVo find(Integer id);
	
	/**
	 * 根据参数类型查询参数字典信息.
	 * 
	 * @param dictType 参数类型
	 * @return List 参数字典对象
	 */
	public List<TenantDictParamVo> findByType(int tenantId, String dictType);
	
	/**
	 * 根据类型和过滤条件过滤.
	 *
	 * @param dictType the dict type
	 * @param filter the filter
	 * @return the list
	 */
	public List<TenantDictParamVo> findByTypeAndFilter(int tenantId, String dictType, String filter);
}
