package com.litt.saap.system.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.litt.core.util.StringUtils;

/**
 * PermissionTreeVo.
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
 * @since 2014年3月25日
 * @version 1.0
 */
public class PermissionTreeVo implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	public static final int ROOT = 0;
	public static final int DOMAIN = 1;
	public static final int MODULE = 2;
	public static final int FUNC = 3;
	
	/** 类型. */
	private int type = ROOT;
	
	/** 编号. */
	private String code = "";
	
	/**用于页面展示，id或class中不能带.号**/
	private String idCode = "";
	
	private boolean isChecked = false;
	
	/** 子节点集合. */
	private List<PermissionTreeVo> subList = new ArrayList<PermissionTreeVo>();	
	
	/**
	 * Instantiates a new permission tree vo.
	 *
	 * @param type the type
	 * @param code the code
	 */
	public PermissionTreeVo(int type, String code) {
		this.type = type;
		this.code = code;
		this.idCode = code;
		if(code.indexOf(".") > 0){
			this.idCode = idCode.replaceAll("\\.", "");
		}
	}
	
	public static PermissionTreeVo newRoot()
	{
		return new PermissionTreeVo(ROOT, "");
	}

	public static PermissionTreeVo newDomain(String code)
	{
		return new PermissionTreeVo(DOMAIN, code);
	}
	
	public static PermissionTreeVo newModule(String code)
	{
		return new PermissionTreeVo(MODULE, code);
	}
	
	public static PermissionTreeVo newFunc(String code)
	{
		return new PermissionTreeVo(FUNC, code);
	}
		
	/**
	 * Take sub.
	 *
	 * @param code the code
	 * @return the permission tree vo
	 */
	public PermissionTreeVo takeSub(String code)
	{
		if(!subList.isEmpty())
		{	
			for (PermissionTreeVo sub : subList) {
				if(sub.getCode().equals(code))
					return sub;
				else if(StringUtils.startsWith(code, sub.getCode()) && !sub.getIsLeaf())
				{
					return this.takeSub(sub, code);
				}
			}
		}
		return null;
	}
	
	/**
	 * Take sub.
	 *
	 * @param node the node
	 * @param code the code
	 * @return the permission tree vo
	 */
	private PermissionTreeVo takeSub(PermissionTreeVo node, String code)
	{
		for (PermissionTreeVo sub : node.getSubList()) {
			if(sub.getCode().equals(code))
				return sub;
			else if(StringUtils.startsWith(code, sub.getCode()) && !sub.getIsLeaf())
			{
				return this.takeSub(sub, code);
			}
		}
		return null;
	}
	
	/**
	 * 是否叶子节点.
	 *
	 * @return the checks if is leaf
	 */
	public boolean getIsLeaf()
	{
		return subList.isEmpty();
	}
	
	/**
	 * Adds the.
	 *
	 * @param sub the sub
	 * @return the permission tree vo
	 */
	public PermissionTreeVo add(PermissionTreeVo sub)
	{
		subList.add(sub);
		return this;
	}

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the subList
	 */
	public List<PermissionTreeVo> getSubList() {
		return subList;
	}

	/**
	 * @param subList the subList to set
	 */
	public void setSubList(List<PermissionTreeVo> subList) {
		this.subList = subList;
	}

	/**
	 * @return the isChecked
	 */
	public boolean isChecked() {
		return isChecked;
	}

	/**
	 * @param isChecked the isChecked to set
	 */
	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}

	public String getIdCode() {
		return idCode;
	}

	public void setIdCode(String idCode) {
		this.idCode = idCode;
	}

}
