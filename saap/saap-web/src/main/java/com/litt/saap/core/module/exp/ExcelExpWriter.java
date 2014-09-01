package com.litt.saap.core.module.exp;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import com.litt.core.exception.BusiException;
import com.litt.core.io.util.FileUtils;


/**
 * ExcelWriter.
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
 * @since 2014年8月31日
 * @version 1.0
 */
public class ExcelExpWriter {
  
  private final static Logger logger = LoggerFactory.getLogger(ExcelExpWriter.class);
  
  private File targetFile;
  
  private String[] fieldNames;
  
  private List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
  
  public ExcelExpWriter(String targetPath, String targetFileName)
  {
    this.targetFile = new File(targetPath, targetFileName);
    if(!targetFile.getParentFile().exists())
    {
      FileUtils.createDirectory(targetFile.getParentFile());
    }
  }
  
  public File build(File templateFile) throws IOException
  {
    FileInputStream fileinputstream = new FileInputStream(templateFile);
    return this.build(fileinputstream);
  }
  
  public File build(InputStream templateInputStream) throws IOException
  {
    //解析Excel模板文件
    POIFSFileSystem poifsfilesystem = new POIFSFileSystem(templateInputStream);
    HSSFWorkbook hssfworkbook = new HSSFWorkbook(poifsfilesystem);
    //读取首页
    HSSFSheet templateSheet = hssfworkbook.getSheetAt(0);
    //读取模板字段
    HSSFRow headRow = templateSheet.getRow(0);
    int colNum = headRow.getLastCellNum();
    
    this.fieldNames = new String[colNum];
    for (int i = 0; i < colNum; i++) {
      HSSFCell cell = headRow.getCell(i);
      String value = cell.getStringCellValue();     
      fieldNames[i] = StringUtils.substringBefore(value, "\n");
      logger.debug("field name:"+fieldNames[i]);
    }
    //创建样式
    HSSFDataFormat format = hssfworkbook.createDataFormat();  //只有这样才能创建自定义样式，否则只能使用Excel内置格式
    HSSFCellStyle dateStyle = hssfworkbook.createCellStyle();  
    dateStyle.setDataFormat(format.getFormat("yyyy-MM-dd"));
    
    //遍历数据，写入模板
    for (int i = 0; i < dataList.size(); i++) {
      Map<String, Object> rowMap = dataList.get(i);
      HSSFRow newRow = templateSheet.createRow(i+1);  //第一行已被占用
      logger.debug("Append new row:{}", new Object[]{newRow.getRowNum()});
      for (int j = 0; j < colNum;j++) {
        String fieldName = fieldNames[j];
        if(rowMap.containsKey(fieldName))
        {
          Object value = rowMap.get(fieldName);
          if(value==null)
            continue;
          HSSFCell newCell = newRow.createCell(j);
          this.setCellData(fieldName, value, newCell, dateStyle);
        }
      }
    }
    
    //写入目标文件
    OutputStream os = new BufferedOutputStream(new FileOutputStream(targetFile), 64);
    try {
      hssfworkbook.write(os);
      os.flush();
    } catch (IOException e) {
      throw new BusiException("Write to excel error.", e);
    }
    finally
    {
      IOUtils.closeQuietly(os);
    }
    return this.targetFile;
  }
  
  private void setCellData(String fieldName, Object value, HSSFCell cell, HSSFCellStyle style)
  {
    if(value instanceof Date)
    {
      cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
      cell.setCellStyle(style);
      cell.setCellValue((Date)value);
    }
    else if(value instanceof BigDecimal)
    {
      BigDecimal decimalValue = (BigDecimal)value;
      cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
      cell.setCellValue(decimalValue.doubleValue());
    }
    else if(value instanceof Integer)
      {   
      Integer integerValue = (Integer)value;
      cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
      cell.setCellValue(integerValue.doubleValue());
      }
    else {
      cell.setCellType(HSSFCell.CELL_TYPE_STRING);
      cell.setCellValue(value.toString());
    }
  }

  public static void main(String[] args) throws Exception {
    List<Map<String, Object>> dataList = new ArrayList<Map<String,Object>>();
    {
      Map<String, Object> rowMap = new HashMap<String, Object>();
      rowMap.put("code", "20140001");
      rowMap.put("name", "测试1");
      rowMap.put("state", "在用");
      rowMap.put("unit", "套");
      rowMap.put("storageDate", new Date());
      rowMap.put("deviceValue", new BigDecimal(100.01));
      dataList.add(rowMap);
    }
    {
      Map<String, Object> rowMap = new HashMap<String, Object>();
      rowMap.put("code", "20140002");
      rowMap.put("name", "测试2");
      rowMap.put("state", "在用");
      rowMap.put("unit", "套");
      rowMap.put("storageDate", new Date());
      rowMap.put("deviceValue", new BigDecimal(200.01));
      dataList.add(rowMap);
    }
    
    ExcelExpWriter builder = new ExcelExpWriter("C:\\weaver", "AssetExport.xls");
    builder.setDataList(dataList);
    builder.build(ResourceUtils.getFile("classpath:module/exp/AssetsDBToExcel.xls"));
  }

  /**
   * @return the dataList
   */
  public List<Map<String, Object>> getDataList() {
    return dataList;
  }

  /**
   * @param dataList the dataList to set
   */
  public void setDataList(List<Map<String, Object>> dataList) {
    this.dataList = dataList;
  }

}
