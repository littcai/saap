package com.litt.saap.core.module.exp;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import net.sf.jxls.transformer.XLSTransformer;

import org.apache.commons.io.IOUtils;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFPicture;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.litt.core.exception.BusiException;
import com.litt.core.io.util.FileUtils;


/**
 * ExcelExtExpWriter.
 * 
 * <pre><b>Descr:</b>
 *    Based on jxls
 * </pre>
 * 
 * <pre><b>Changelog:</b>
 *    
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">Caiyuan</a>
 * @since 2014年12月4日
 * @version 1.0
 */
public class ExcelExtExpWriter {
  
  private final static Logger logger = LoggerFactory.getLogger(ExcelExtExpWriter.class);
  
  private File targetFile;
  
  /** 数据上下文. */
  private Map<String, Object> context = new HashMap<String, Object>();  
  
  /** 需要动态插入的图片 */
  private File[] imageFiles;
  
  private Workbook workbook;
  
  /** 一个sheet只能建一个HSSFPatriarch. */
  private Map<String, HSSFPatriarch> patriarchMap = new HashMap<String, HSSFPatriarch>();
  
  public ExcelExtExpWriter(String targetPath, String targetFileName)
  {
    this.targetFile = new File(targetPath, targetFileName);
    if(!targetFile.getParentFile().exists())
    {
      FileUtils.createDirectory(targetFile.getParentFile());
    }
  }
  
  public void addData(String name, Object data)
  {
    this.context.put(name, data);
  }
  
  public File build(File templateFile) throws IOException, InvalidFormatException
  {
    FileInputStream fileinputstream = new FileInputStream(templateFile);
    return this.build(fileinputstream);
  }
  
  public File build(InputStream templateInputStream) throws IOException, InvalidFormatException
  {
    XLSTransformer transformer = new XLSTransformer();
    
    this.workbook =  transformer.transformXLS(templateInputStream, context);
    //TODO 这里需要补充动态插入图片的逻辑
    
    //写入目标文件
    OutputStream os = new BufferedOutputStream(new FileOutputStream(targetFile), 64);
    try {
      workbook.write(os);
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
  
  /**
   * 仅支持JPEG和PNG
   * @param imageFile
   */
  private void writeImage(Sheet sheet, File imageFile, int width, int height, int x, int y) throws IOException
  {
    HSSFPatriarch patriarch = patriarchMap.get(sheet.getSheetName());
    if(patriarch==null)
    {
      patriarch = (HSSFPatriarch)sheet.createDrawingPatriarch();
      patriarchMap.put(sheet.getSheetName(), patriarch);
    }
    ByteArrayOutputStream bos = new ByteArrayOutputStream();  
    BufferedImage bufferedImage = ImageIO.read(imageFile);
    ImageIO.write(bufferedImage, "JPEG", bos);  
    /*
     * dx1 - the x coordinate within the first cell.
     * dy1 - the y coordinate within the first cell.
     * dx2 - the x coordinate within the second cell.
     * dy2 - the y coordinate within the second cell.
     * col1 - the column (0 based); of the first cell.
     * row1 - the row (0 based); of the first cell.
     * col2 - the column (0 based); of the second cell.
     * row2 - the row (0 based); of the second cell.
     */
    HSSFClientAnchor anchor = new HSSFClientAnchor(0,0,width,height,(short)x, (short)y, (short)0, (short)0);  
    //0 = Move and size with Cells, 2 = Move but don't size with cells, 3 = Don't move or size with cells.
    anchor.setAnchorType(2);
    
    HSSFPicture picture = patriarch.createPicture(anchor, workbook.addPicture(bos.toByteArray(),HSSFWorkbook.PICTURE_TYPE_JPEG));
    //picture.resize();
  }

  /**
   * @param args
   */
  public static void main(String[] args) throws Exception
  {
    List<Map<String, Object>> dataList2 = new ArrayList<Map<String, Object>>();
    Map<String, Object> rowMap = new HashMap<String, Object>();
    rowMap.put("index", 1);
    dataList2.add(rowMap);
    
    ExcelExtExpWriter writer = new ExcelExtExpWriter("d:\\", "test.xls");
    
    writer.addData("inventoryOutItems", dataList2);
    writer.build(new File("e:\\InventoryOutToExcelExt.xlsx"));
  }

}
