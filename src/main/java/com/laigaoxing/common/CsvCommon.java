package com.laigaoxing.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * csv 格式文件帮助类
 * @author lichicheng
 *
 */
public class CsvCommon {
	
	private String encode = "utf8";
	private String content = null;
	private InputStream in = null;
	private AnalysisCsv analysisCsv = commaAnaly();
	
	/*
	 * 参数包括
	 * 1.数据源，FileInputStream|Text|FilePath|File
	 * 2.文件编码
	 * 3.开始读取行
	 * 4.读取行数
	 * 5.解析方式
	 * 6.colKey
	 * 1,2为基础参数，在实例对像时调用
	 * 参数,5,6为属性参数，调用方法前执行
	 * 方法参数只包括 3与4
	 */
	
	private CsvCommon(){
		
	}
	
	public static CsvCommon newInstance(String text){
		CsvCommon csvCommon = new CsvCommon();
		csvCommon.content = text;
		return csvCommon;
	}
	public static CsvCommon newInstance(InputStream in){
		CsvCommon csvCommon = new CsvCommon();
		csvCommon.in = in;
		return csvCommon;
	}
	
	public static CsvCommon newInstance(InputStream in,String encode){
		CsvCommon csvCommon = new CsvCommon();
		csvCommon.in = in;
		csvCommon.encode = encode;
		return csvCommon;
	}
	
	public static CsvCommon newInstance(File file) throws FileNotFoundException{
		CsvCommon csvCommon = new CsvCommon();
		csvCommon.in = new FileInputStream(file);
		return csvCommon;
	}
	
	public static CsvCommon newInstance(File file,String encode) throws FileNotFoundException{
		CsvCommon csvCommon = new CsvCommon();
		csvCommon.in = new FileInputStream(file);
		csvCommon.encode = encode;
		return csvCommon;
	}

	
	/**
	 * 转换csv到map集合
	 * @param path csv文件地址
	 * @param startLine 开始行数，1作为起始行
	 * @param number 返回行条数
	 * @param encod_defaul 文件编码
	 * @param Map<Integer,String> 返回的列与对应的key
	 * @param analysis 解析方式
	 * @return
	 * @throws IOException 
	 */
	public List<Map<String,String>> asMaps(int startLine,int number,Map<Integer,String> columnMapping) throws IOException{
		List<String[]> lines = asList(startLine, number);
		return convertToMap(lines, columnMapping);
	}
	
	/**
	 *  转换csv到map集合，使用文件第一行作为对应key	
	 * @param path csv文件地址
	 * @param startLine 开始行数，1作为起始行
	 * @param number 返回行条数
	 * @param encod_defaul 文件编码
	 * @param analysis 解析方式
	 * @throws IOException
	 */
	public List<Map<String,String>> asMaps(int startLine,int number) throws IOException{
		String l = null;
		if(in != null){
			l = FileHelper.line(in, encode,0, 1);
		}else if(this.content != null){
			l = StringHelper.line(content, 0);
		}else{
			throw new RuntimeException("至少设置一项数据源");
		}
		return asMaps(startLine+1, number, colsValue(l, analysisCsv));
	}
	
	public List<Map<String,String>> asMaps() throws IOException{
		return asMaps(1, Integer.MAX_VALUE);
	}

	
	public List<Map<String,String>> asMaps(int line) throws IOException{
		return asMaps(line, 1);
	}

	public List<Map<String,String>> asMaps(int startLine,Map<Integer,String> columnMapping) throws IOException{
		return asMaps(startLine, Integer.MAX_VALUE, columnMapping);
	}
	

	public List<Map<String,String>> asMaps(Map<Integer,String> colKey) throws IOException{
		return asMaps(1, Integer.MAX_VALUE ,colKey);
	}
	
	public Map<String,String> asMap(int line) throws IOException{
		return asMaps(line, 1).get(0);
	}
	
	public List<String[]> asList() throws IOException{
		return asList(1,Integer.MAX_VALUE);
	}
	
	public List<String[]> asList(int startLine) throws IOException{
		return asList(startLine,Integer.MAX_VALUE);
	}
	
	public List<String[]> asList(int startLine,int number) throws IOException{
		String[] lines = null;
		if(in != null){
			lines = FileHelper.readLines(in, encode,startLine,number);
		}else if(this.content != null){
			lines = StringHelper.getLines(content, startLine, number);
		}else{
			throw new RuntimeException("至少设置一项数据源");
		}
		return analysisLines(lines, analysisCsv);
	}
	
	/**
	 * 解析单行csv文件
	 * @author jeff
	 *
	 */
	public interface AnalysisCsv{
		String[] analysis(String line);
	}
	/**
	 * 使用java.lang.String的split分隔","解析csv文件
	 * 
	 * @author jeff
	 *
	 */
	public static class CommaAnalysis implements AnalysisCsv{

		@Override
		public String[] analysis(String line) {
			String[] rst = line.split(",");
			trim(rst);
			return rst;
		}
		
	}
	/**
	 * 使用正则解析csv格式文件，支持使用以双引号加","为单元格的文件
	 * @author jeff
	 *
	 */
	public static class RegexAnalysis implements AnalysisCsv{

		@Override
		public String[] analysis(String line) {
			List<String> rst = new ArrayList<String>();
			Matcher m = p.matcher(line);
			while(m.find()){
				if(m.group(1)==null){
					rst.add(m.group(2));
				}else{
					rst.add(m.group(1));
				}
			}
			String[] rstArr = new String[rst.size()];
			rst.toArray(rstArr);
			trim(rstArr);
			return rstArr;
		}
		
	}
	
	static Pattern p = Pattern.compile("(?:\"([^\"]+)\")|(?:(?:^|,?)([^\",]+)(?:,?|$))");
	
	
	private static void trim(String[] cols){
		for(int i=0;i<cols.length;i++){
			cols[i] = cols[i].trim();
		}
	}
	
	private static List<Map<String,String>> convertToMap(List<String[]> lines,Map<Integer,String> colKey){
		if(colKey.size() == 0){return null;}
		List<Map<String,String>> rst = new ArrayList<Map<String,String>>();
		for(String[] cols : lines){
			Map<String,String> line = new LinkedHashMap<String, String>();
			for(Integer k : colKey.keySet()){
				try{
					line.put(colKey.get(k), cols[k]);
				}catch(IndexOutOfBoundsException e){
					line.put(colKey.get(k), "");
				}
	      	}
			rst.add(line);
		}
		return rst;
	}
	
	private static List<String[]> analysisLines(String[] lines,AnalysisCsv analysis){
		List<String[]> rst = new ArrayList<String[]>();
		for(String l : lines){
			rst.add(analysis.analysis(l));
		}
		return rst;
	}

	private static Map<Integer,String> colsValue(String l,AnalysisCsv analysis){
		Map<Integer,String> colKey = new HashMap<Integer, String>();
		String[] cols = analysis.analysis(l);
		for(int i=0;i<cols.length;i++){
			colKey.put(i, cols[i]);
		}
		return colKey;
	}
	public static AnalysisCsv commaAnaly(){
		return new CommaAnalysis();
	}
	
	public static AnalysisCsv regexAnaly(){
		return new RegexAnalysis();
	}
	
	public static String asString(List<Map<String,String>> list,String colNames) throws IOException {
		StringBuffer sb = new StringBuffer();
		sb.append(colNames);
		sb.append(System.getProperty("line.separator"));
		String[] a = colNames.split(",");
		for (int i = 0; i < list.size(); i++) {
			Map map = list.get(i);
			for (int j = 0; j < a.length; j++) {
				Object obj = map.get(a[j]);
				if (null != obj) {
					sb.append((String) obj + ",");
				} else {
					sb.append(",");
				}
			}
			sb.append(System.getProperty("line.separator"));
		
		}
		return sb.toString();
		
	}

	public void setAnalysisCsv(AnalysisCsv analysisCsv) {
		this.analysisCsv = analysisCsv;
	}

}