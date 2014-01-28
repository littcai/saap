package com.litt.saap.common;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.BitSet;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.litt.core.io.util.IOUtils;
import com.litt.core.util.StringUtils;

/**
 * .
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
 * @since 2014-1-12
 * @version 1.0
 */
public class ChargeCodeDemo {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory.getLogger(ChargeCodeDemo.class);	
	
	/**
	 * 根据chargeCode第一位将大集合拆成小集合，这样剩5位的FFFFF，最大1百万多，在int范围内
	 * 需要大内存
	 */
	private BitSet[] chargeCodeSets = new BitSet[16];	
	
	//private Integer[][] chargeCodesArray = new Integer[16][];
	
//	private Map<Integer, List<Integer>> chargeCodesMap = new HashMap<Integer, List<Integer>>(16);
	
	public void init() throws IOException
	{
		logger.info("Initializing charge codes...");
		for (int i=0; i<16; i++) {
			chargeCodeSets[i] = new BitSet(268435455);	
		}	
		
//		for (int i=0; i<16; i++) {
//			chargeCodesArray[i] = new Integer[268435455];	
//		}
		
//		for (int i=0; i<16; i++) {
//			chargeCodesMap.put(i, new LinkedList<Integer>());	
//		}
		
		File file = new File("c:\\chargecode1.csv");
		InputStreamReader read = new InputStreamReader(new FileInputStream(file), "UTF-8");
		BufferedReader reader = new BufferedReader(read);
		String line;
		while ((line = reader.readLine()) != null) 
		{
			String[] array = StringUtils.split(line, ',');
			this.setChargeCodeSet(array[1]);
		}
		IOUtils.closeQuietly(reader);
		
		logger.info("Initialized charge codes...");
	}
	
	private void setChargeCodeSet(String chargeCode)
	{
		int prefix = Integer.parseInt(StringUtils.substring(chargeCode, 0, 1), 16);
		int suffix = Integer.parseInt(StringUtils.substring(chargeCode, 1), 16);
		//System.out.println(StringUtils.substring(chargeCode, 1)+":"+suffix);
		BitSet codeSet = chargeCodeSets[prefix]; 		
		codeSet.set(suffix);
		
//		Integer[] chargeCodes = chargeCodesArray[prefix];
//		chargeCodes[suffix] = 1;
		
//		List<Integer> chargeCodes = chargeCodesMap.get(prefix);
//		chargeCodes.add(suffix);		
	}
	
	
	/**
	 * Gen codes.
	 *
	 * @param date the date
	 * @return the string[]
	 */
	public String[] genCodes(Date date, int dayCount, int totalCount)
	{	
		logger.info("Start gen charge codes...");
		String[] ret = new String[totalCount];
		String keyStr = "-"+new DateTime(date).toString("yyyy-MM-dd")+"-"+dayCount;		
	
		for(int i=0; i<totalCount; ++i) 
		{
            String specString = i + keyStr;
            String activeCodeHex;
            do {
                activeCodeHex = DigestUtils.md5Hex(specString).toUpperCase().substring(12,20);
                if (!checkCode(activeCodeHex)) {
                    break;
                }
                specString+="*";
            } while( true );
            setChargeCodeSet(activeCodeHex);
            ret[i] = activeCodeHex;
		}    
		logger.info("End gen charge codes...");
		return ret;
	}
	
	public void writeFile(String filePath, String[] chargeCodes) throws IOException
	{
		File file = new File(filePath);
		BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
		for (String string : chargeCodes) {
			bw.append(string);
			bw.newLine();
		}
		bw.flush();
		IOUtils.closeQuietly(bw);
	}
	
	/**
	 * 检查是否存在.
	 *
	 * @param code the code
	 * @return true, if successful
	 */
	public boolean checkCode(String code)
	{
		int prefix = Integer.parseInt(StringUtils.substring(code, 0, 1), 16);
		int suffix = Integer.parseInt(StringUtils.substring(code, 1), 16);
		BitSet codeSet = chargeCodeSets[prefix]; 		
		return codeSet.get(suffix);
		
//		Integer[] chargeCodes = chargeCodesArray[prefix];
//		return chargeCodes[suffix] != null;
		
//		List<Integer> chargeCodes = chargeCodesMap.get(prefix);
//		return chargeCodes.contains(suffix);
	}
	
	
	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		
		long memory = Runtime.getRuntime().freeMemory();
		long startTime = System.currentTimeMillis();
		
		ChargeCodeDemo demo = new ChargeCodeDemo();
		demo.init();
		
		String[] chargeCodes = demo.genCodes(new Date(), 30, 200000);
		//demo.writeFile("C:\\chargecode.txt", chargeCodes);
		
		System.out.println(System.currentTimeMillis()-startTime);
		System.out.println(memory - Runtime.getRuntime().freeMemory());
		
		System.out.println(chargeCodes.length);
		
		
		
//		List<String> list = new ArrayList<String>();
//		Map<String, Integer> map = new HashMap<String, Integer>();
//		long memory = Runtime.getRuntime().freeMemory();
//		long startTime = System.currentTimeMillis();
//		File file = new File("c:\\chargecode1.csv");
//		InputStreamReader read = new InputStreamReader(new FileInputStream(file), "UTF-8");
//		BufferedReader reader = new BufferedReader(read);
//		String line;
//		
//		Date endDate = new DateTime().plusDays(30).toDate();
//	    String endDateStr = FormatDateTime.format(endDate, "yyyy-MM-dd");
//
//	    String keyStr = "-"+endDateStr.trim()+"-"+30;
//		
//		String[] cards = new String[2000000];
//		
//		for(int i=0;i<cards.length;++i) {
//
//         String specString = i +keyStr;
//         String activeCodeHex = DigestUtils.md5Hex(specString).toUpperCase().substring(12,20);
//         cards[i] = activeCodeHex;  
//		}
//		
//		
//		
//		List<String> repeatList = new ArrayList<String>();
//		List<Integer> idList = new ArrayList<Integer>();
//		
//		int count = 0;
//			while ((line = reader.readLine()) != null) 
//			{
//				//System.out.println(line);
//				list.add(line);
//				String[] array = StringUtils.split(line, ',');
//				
//				if(map.containsKey(array[1]))
//				{
//					//System.out.println(line+":"+array[2]+","+map.get(array[1])+","+array[3]);
//					repeatList.add(array[1]);
//					idList.add(Integer.valueOf(array[0]));
//					System.out.println("SELECT * FROM CMSCHARGECODE WHERE CHARGE_CODE='"+ array[1]  +"'");
//					count++;
//				}
//				else {
//					map.put(array[1], Integer.valueOf(array[2]));
//				}
//			}
//			System.out.println(count);
//			reader.close();read.close();
//			
//			for(int i=0;i<cards.length;++i) {
//				if(map.containsKey(cards[i]))
//				{
//					System.out.println("重复:"+cards[i]);
//				}
//				
//			}
//			
//			System.out.println(System.currentTimeMillis()-startTime);
//		System.out.println(memory - Runtime.getRuntime().freeMemory());
//		//遍历重复的，计算新不重复的
//		int index = 0;
//		for (String  repeat : repeatList) {
//			String newValue = Long.toHexString(Long.valueOf(repeat, 16) + 1).toUpperCase();
//			if(!map.containsKey(newValue))
//			{
//				//System.out.println(repeat+":"+newValue);
//				
//				
//				//System.out.println("UPDATE CMSCHARGECODE SET CHARGE_CODE='"+ newValue  +"' WHERE CHARGE_CODE='"+ repeat  +"' AND ID="+ idList.get(index)  +"");
//			}
//			else {
//				System.out.println(repeat+":");
//			}
//			index++;
//		}
		
		// TODO Auto-generated method stub

	}

}
