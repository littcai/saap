package com.litt.core.test.unitils.ext;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.dbunit.dataset.CompositeDataSet;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.XmlDataSet;
import org.unitils.core.UnitilsException;
import org.unitils.dbunit.datasetfactory.DataSetFactory;
import org.unitils.dbunit.util.MultiSchemaDataSet;


/** 
 * 
 * XML数据集扩展.
 * 
 * <pre><b>描述：</b>
 *     Unitils默认仅支持Flat格式的XML数据集，且XML解析是其内部实现的而不是FlatXmlDataSet。
 * 通过扩展DataSetFactory实现同时支持Flat格式和MultiRow格式的XML数据集读取。
 * 配置方法：
 * 修改unitils.properties
 * 1、DbUnitModule.DataSet.factory.default=com.litt.core.test.unitils.ext.MultiRowXmlDataSetFactory
 * 	1.1、DbUnitModule.ExpectedDataSet.factory.default=com.litt.core.test.unitils.ext.MultiRowXmlDataSetFactory
 * 3、增加DbUnitModule.DataSet.xml.format属性，属性值可以是“MultiRow”或"Flat"，默认为Flat格式
 * </pre>
 * 
 * <pre><b>修改记录：</b>
 *    
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">蔡源</a>
 * @since 2013-7-30
 * @version 1.0
 *
 */
public class MultiRowXmlDataSetFactory implements DataSetFactory
{
	private String defaultSchemaName;
	private String format;

	/* (non-Javadoc)
	 * @see org.unitils.dbunit.datasetfactory.DataSetFactory#createDataSet(java.io.File[])
	 */
	@Override
	public MultiSchemaDataSet createDataSet(File... dataSetFiles)
	{ 
		MultiSchemaDataSet multiSchemaDataSet = null;
		try {
			CompositeDataSet globalFlatXmlDataSet = buildUniqueDataSet(dataSetFiles);

			multiSchemaDataSet = new MultiSchemaDataSet();
			multiSchemaDataSet.setDataSetForSchema(defaultSchemaName, globalFlatXmlDataSet);

		} catch (Exception e) {
			throw new UnitilsException("Unable to create DbUnit dataset for data set files: "
					+ Arrays.toString(dataSetFiles), e);
		}
		return multiSchemaDataSet;      
	}

	/* (non-Javadoc)
	 * @see org.unitils.dbunit.datasetfactory.DataSetFactory#getDataSetFileExtension()
	 */
	@Override
	public String getDataSetFileExtension()
	{ 
		return "xml";
	}

	/* (non-Javadoc)
	 * @see org.unitils.dbunit.datasetfactory.DataSetFactory#init(java.util.Properties, java.lang.String)
	 */
	@Override
	public void init(Properties configuration, String defaultSchemaName)
	{
		this.defaultSchemaName = defaultSchemaName;
		this.format = configuration.getProperty("DbUnitModule.DataSet.xml.format", "Flat");		
	}
	
	/**
	 * Builds the unique data set.
	 *
	 * @param dataSetFiles the data set files
	 * @return the composite data set
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws DataSetException the data set exception
	 */
	private CompositeDataSet buildUniqueDataSet(File... dataSetFiles) throws IOException, DataSetException
	{
		if("MultiRow".equals(format))
		{
			List<XmlDataSet> xmlDataSets = buildXmlDataSets(dataSetFiles);
			CompositeDataSet globalXmlDataSet = new CompositeDataSet(xmlDataSets.toArray(new XmlDataSet[0]));
			return globalXmlDataSet;
		}
		else 
		{
			List<FlatXmlDataSet> xmlDataSets = buildFlatXmlDataSets(dataSetFiles);
			CompositeDataSet globalXmlDataSet = new CompositeDataSet(xmlDataSets.toArray(new FlatXmlDataSet[0]));
			return globalXmlDataSet;
		}		
	}
	
	/**
	 * Builds the xml data sets.
	 *
	 * @param dataSetFiles the data set files
	 * @return the list
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws DataSetException the data set exception
	 */
	private List<XmlDataSet> buildXmlDataSets(File... dataSetFiles) throws IOException, DataSetException
	{
		ArrayList<XmlDataSet> xmlDataSets = new ArrayList<XmlDataSet>();
		for (File dataSetFile : dataSetFiles)
		{
			xmlDataSets.add(new XmlDataSet(new FileInputStream(dataSetFile)));
		}
		return xmlDataSets;
	}
	
	/**
	 * Builds the flat xml data sets.
	 *
	 * @param dataSetFiles the data set files
	 * @return the list
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws DataSetException the data set exception
	 */
	private List<FlatXmlDataSet> buildFlatXmlDataSets(File... dataSetFiles) throws IOException, DataSetException
	{
		ArrayList<FlatXmlDataSet> flatXmlDataSets = new ArrayList<FlatXmlDataSet>();
		for (File dataSetFile : dataSetFiles)
		{
			flatXmlDataSets.add(new FlatXmlDataSet(dataSetFile, true));
		}
		return flatXmlDataSets;
	}

}