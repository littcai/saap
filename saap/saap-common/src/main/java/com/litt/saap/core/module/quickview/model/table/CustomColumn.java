package com.litt.saap.core.module.quickview.model.table;

import org.dom4j.Element;

import com.litt.core.common.Utility;

/**
 * 
 * CustomColumn.
 * 
 * <pre><b>Descr:</b>
 *    
 * </pre>
 * 
 * <pre><b>Changelog:</b>
 *    
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">Caiyuan</a>
 * @since 2014年7月2日
 * @version 1.0
 */
public class CustomColumn extends AbstractColumn implements IColumn
{	
	
	/** The html template. */
	private String htmlTemplate;
	
	/** The excel template. */
	private String excelTemplate;		
	
	public CustomColumn(){}
	
	public CustomColumn(Element columnNode)
	{
		this.htmlTemplate = columnNode.elementTextTrim("html");
		this.excelTemplate = columnNode.elementTextTrim("excel");
	}
	
	public boolean hasHtml()
	{
		return !Utility.isEmpty(htmlTemplate);
	}	
	
	public boolean hasExcel()
	{
		return !Utility.isEmpty(excelTemplate);
	}
	
	
	/**
	 * 
	 * @return
	 */
	public int getColumnType()
	{
		return 2;
	}

	/**
	 * @return the template.
	 */
	public String getHtmlTemplate()
	{
		return htmlTemplate;
	}

	/**
	 * @param template the template to set.
	 */
	public void setHtmlTemplate(String template)
	{
		this.htmlTemplate = template;
	}

	/**
	 * @return the excelTemplate.
	 */
	public String getExcelTemplate()
	{
		return excelTemplate;
	}

	/**
	 * @param excelTemplate the excelTemplate to set.
	 */
	public void setExcelTemplate(String excelTemplate)
	{
		this.excelTemplate = excelTemplate;
	}
	

}
