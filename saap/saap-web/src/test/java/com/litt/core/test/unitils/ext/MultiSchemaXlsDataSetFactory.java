package com.litt.core.test.unitils.ext;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.commons.io.FilenameUtils;
import org.dbunit.database.AmbiguousTableNameException;
import org.dbunit.dataset.DefaultDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.excel.XlsDataSet;
import org.unitils.core.UnitilsException;
import org.unitils.dbunit.datasetfactory.DataSetFactory;
import org.unitils.dbunit.util.MultiSchemaDataSet;

import com.litt.core.util.StringUtils;


/** 
 * 
 * Excel数据集支持（支持多schema）.
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
 * @since 2013-7-30
 * @version 1.0
 *
 */
public class MultiSchemaXlsDataSetFactory implements DataSetFactory
{
	private String defaultSchemaName;

	/* (non-Javadoc)
	 * @see org.unitils.dbunit.datasetfactory.DataSetFactory#createDataSet(java.io.File[])
	 */
	@Override
	public MultiSchemaDataSet createDataSet(File... dataSetFiles)
	{ 
		Map<String, List<ITable>> tableMap = getTables(dataSetFiles);  
        MultiSchemaDataSet dataSets = new MultiSchemaDataSet();  
        for (Entry<String, List<ITable>> entry : tableMap.entrySet()) 
        {  
            List<ITable> tables = entry.getValue();  
                 
            try
			{
				DefaultDataSet ds = new DefaultDataSet(tables.toArray(new ITable[] {}));  
				dataSets.setDataSetForSchema(entry.getKey(), ds);
			}
			catch (AmbiguousTableNameException e)
			{
				throw new UnitilsException(e);
			}                 
        }  
        return dataSets;          
	}

	/* (non-Javadoc)
	 * @see org.unitils.dbunit.datasetfactory.DataSetFactory#getDataSetFileExtension()
	 */
	@Override
	public String getDataSetFileExtension()
	{ 
		return "xls";
	}

	/* (non-Javadoc)
	 * @see org.unitils.dbunit.datasetfactory.DataSetFactory#init(java.util.Properties, java.lang.String)
	 */
	@Override
	public void init(Properties configuration, String defaultSchemaName)
	{
		this.defaultSchemaName = defaultSchemaName;
	}
	
	/**
	 * 通过dbunit读取xls
	 * @param dataSetFiles
	 * @return
	 */
	private Map<String, List<ITable>> getTables(File... dataSetFiles) 
	{
        Map<String, List<ITable>> tableMap = new HashMap<String, List<ITable>>();
        // 需要根据schema把Table重新组合一下
        try {
            for (File file : dataSetFiles) {
            	/*
            	 * 从文件名中截取获得schema 
            	 */
            	String fileBaseName =  FilenameUtils.getBaseName(file.getName());
            	String[] temp = StringUtils.split(fileBaseName, '.');
            	
                IDataSet dataSet = new XlsDataSet(new FileInputStream(file));
                String[] tableNames = dataSet.getTableNames();
                for (String tableName : tableNames) {
                    String schema = this.defaultSchemaName;       
                    if (temp.length == 2) {
                        schema = temp[0];                        
                    } 
                    ITable table = dataSet.getTable(tableName);
                    if (!tableMap.containsKey(schema)) {
                        tableMap.put(schema, new ArrayList<ITable>());
                    }
                    tableMap.get(schema).add(table);
                }
            }
        } catch (Exception e) {
            throw new UnitilsException("Unable to create DbUnit dataset for data set files: "
                    + Arrays.toString(dataSetFiles), e);
        }
        return tableMap;
    }
	
	

}
