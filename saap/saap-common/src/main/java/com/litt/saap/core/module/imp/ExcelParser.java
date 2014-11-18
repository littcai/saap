package com.litt.saap.core.module.imp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ExcelParser.
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
 * @since 2014年6月12日
 * @version 1.0
 */
public class ExcelParser {
	
	private final static Logger logger = LoggerFactory.getLogger(ExcelParser.class);
	
	/** The file. */
	private File file;
	
	/** 导入字段. */
	private String[] fieldNames;	
	
	/** 导入数据. */
	private List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
	
	/**
	 * Instantiates a new excel parser.
	 *
	 * @param filePath the file path
	 */
	public ExcelParser(String filePath)
	{
		this.file = new File(filePath);
	}
	
	/**
	 * Instantiates a new excel parser.
	 *
	 * @param file the file
	 */
	public ExcelParser(File file)
	{
		this.file = file;
	}
	
	/**
	 * @param filePath
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void parse() throws IOException {
		
		FileInputStream fileinputstream = new FileInputStream(file);
		POIFSFileSystem poifsfilesystem = new POIFSFileSystem(fileinputstream);
		HSSFWorkbook hssfworkbook = new HSSFWorkbook(poifsfilesystem);
		try {
			HSSFSheet hssfsheet = hssfworkbook.getSheetAt(0);
			
			int rowNum = hssfsheet.getLastRowNum();
			System.out.println("total row num:" + rowNum);
			
			//取第一行标题行
			HSSFRow headRow = hssfsheet.getRow(0);
			int colNum = headRow.getLastCellNum();
			
			this.fieldNames = new String[colNum];
			for (int i = 0; i < colNum; i++) {
				HSSFCell cell = headRow.getCell(i);
				String value = cell.getStringCellValue();			
				fieldNames[i] = StringUtils.substringBefore(value, "\n");
				System.out.println("field name:"+fieldNames[i]);
			}
			//取数据
			for (int i = 1; i <= rowNum; i++) {
				System.out.println("row:"+i);
				Map<String, Object> rowMap = new HashMap<String, Object>();
				
				HSSFRow row = hssfsheet.getRow(i);
				for (int j = 0; j < colNum; j++) {
					//System.out.println("cell:"+j+" of "+row.getLastCellNum());
					HSSFCell cell = row.getCell(j);
					if (cell ==null) {
						rowMap.put(fieldNames[j], "");
						continue;
					}
					
					int cellType = cell.getCellType();
					int dataFormat = cell.getCellStyle().getDataFormat();
					if(i==1)
					logger.debug("Cell:{}, type:{}, dataFormat:{}", new Object[]{j, cellType, cell.getCellStyle().getDataFormat()});
					
					if(HSSFCell.CELL_TYPE_STRING == cellType)
					{
						String value = cell.getStringCellValue();
						rowMap.put(fieldNames[j], StringUtils.isEmpty(value)?"":value);
					}
					else if(HSSFCell.CELL_TYPE_NUMERIC == cellType)
					{
						if (HSSFDateUtil.isCellDateFormatted(cell))
						{
							//1、判断是否是数值格式  
//							if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){  
//							    short format = cell.getCellStyle().getDataFormat();  
//							    SimpleDateFormat sdf = null;  
//							    if(format == 14 || format == 31 || format == 57 || format == 58){  
//							        //日期  
//							        sdf = new SimpleDateFormat("yyyy-MM-dd");  
//							    }else if (format == 20 || format == 32) {  
//							        //时间  
//							        sdf = new SimpleDateFormat("HH:mm");  
//							    }  
//							    double value = cell.getNumericCellValue();  
//							    Date date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value);  
//							    result = sdf.format(date);  
//							}  
							
							Date value = cell.getDateCellValue();		
							if(value!=null)
								rowMap.put(fieldNames[j], value);	
						} 
						else
						{
							double d = cell.getNumericCellValue();
							if(dataFormat == 49)
							{
								DecimalFormat df = new DecimalFormat("#");								
								rowMap.put(fieldNames[j], df.format(d));
							}
							else	
							{
							  NumberFormat nf = NumberFormat.getInstance();   
							  nf.setGroupingUsed(false);    
							  rowMap.put(fieldNames[j], nf.format(d));
							}							
						}
					}					
				}	
				dataList.add(rowMap);
			}
		}
		finally{ 
			IOUtils.closeQuietly(fileinputstream);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		ExcelParser parser = new ExcelParser("/Users/littcai/Documents/InventoryInExcelToDB.xls");
		parser.parse();
		
//		List formats = HSSFDataFormat.getBuiltinFormats();
//		for (Object object : formats) {
//			System.out.println(object);
//		}
	}

	/**
	 * @return the fieldNames
	 */
	public String[] getFieldNames() {
		return fieldNames;
	}

	/**
	 * @return the dataList
	 */
	public List<Map<String, Object>> getDataList() {
		return dataList;
	}

	/**
	 * @return the file
	 */
	public File getFile() {
		return file;
	}

	/**
	 * @param file the file to set
	 */
	public void setFile(File file) {
		this.file = file;
	}

}
