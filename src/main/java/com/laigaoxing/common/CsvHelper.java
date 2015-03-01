package com.laigaoxing.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * csv 格式文件帮助类
 * @author Administrator
 *
 */
public class CsvHelper {
	
	static String encod_defaul = "utf8";
	
	/**
	 * 转换csv到map集合
	 * @param content 文本字符串
	 * @param startLine 开始行
	 * @param number 返回行数
	 * @param colKey 列索引与对应的key
	 * @param analysis 解析方式
	 * @return List<Map<String,String>> 
	 * @throws IOException
	 */
	public static List<Map<String,String>> asMapsByCsvTxt(String content,int startLine,int number,Map<Integer,String> colKey,AnalysisCsv analysis){
		List<String[]> lines = asListByCsvTxt(content, startLine, number, analysis);
		return convertToMap(lines,colKey);
	}
	
	public static List<Map<String,String>> asMapsByCsvTxt(String content,int startLine,int number,Map<Integer,String> colKey){
		return asMapsByCsvTxt(content, startLine, number, colKey,new CsvHelper().new CommaAnalysis());
	}

	public static List<Map<String,String>> asMapsByCsvTxt(String content,Map<Integer,String> colKey){
		return asMapsByCsvTxt(content, 0, Integer.MAX_VALUE, colKey,new CsvHelper().new CommaAnalysis());
	}
	
	public static List<Map<String,String>> asMapsByCsvTxt(String content,Map<Integer,String> colKey,AnalysisCsv analysis){
		return asMapsByCsvTxt(content, 0, Integer.MAX_VALUE, colKey,analysis);
	}
	
	public static List<Map<String,String>> asMapsByCsvTxt(String content,int startLine,Map<Integer,String> colKey,AnalysisCsv analysis) {
		return asMapsByCsvTxt(content, startLine, Integer.MAX_VALUE, colKey,analysis);
	}
	
	public static List<Map<String,String>> asMapsByCsvTxt(String content,int startLine,Map<Integer,String> colKey) {
		return asMapsByCsvTxt(content, startLine, Integer.MAX_VALUE, colKey,new CsvHelper().new CommaAnalysis());
	}
	
	public static Map<String,String> asMapByCsvTxt(String content,int line,Map<Integer,String> colKey){
		return asMapsByCsvTxt(content, line, 1, colKey,new CsvHelper().new CommaAnalysis()).get(0);
	}
	
	public static Map<String,String> asMapByCsvTxt(String content,int line,Map<Integer,String> colKey,AnalysisCsv analysis) {
		return asMapsByCsvTxt(content, line, 1, colKey,analysis).get(0);
	}
	
	/**
	 * 以第一行做为Key
	 * @param content
	 * @param startLine
	 * @param number
	 * @param analysis
	 * @return
	 */
	public static List<Map<String,String>> asMapsByCsvTxt(String content,int startLine,int number,AnalysisCsv analysis){
		List<String[]> lines = asListByCsvTxt(content, startLine, number, analysis);
		String l = StringHelper.line(content, 1);
		return convertToMap(lines,colsValue(l, analysis));
	}
	
	public static List<Map<String,String>> asMapsByCsvTxt(String content,int startLine,int number){
		return asMapsByCsvTxt(content, startLine, number,new CsvHelper().new CommaAnalysis());
	}
	
	public static List<Map<String,String>> asMapsByCsvTxt(String content,int startLine,AnalysisCsv analysis) {
		return asMapsByCsvTxt(content, startLine, Integer.MAX_VALUE,analysis);
	}
	
	public static List<Map<String,String>> asMapsByCsvTxt(String content,int startLine) {
		return asMapsByCsvTxt(content, startLine, Integer.MAX_VALUE,new CsvHelper().new CommaAnalysis());
	}
	
	public static Map<String,String> asMapByCsvTxt(String content,int line){
		return asMapsByCsvTxt(content, line, 1,new CsvHelper().new CommaAnalysis()).get(0);
	}
	
	public static Map<String,String> asMapByCsvTxt(String content,int line,AnalysisCsv analysis) {
		return asMapsByCsvTxt(content, line, 1,analysis).get(0);
	}
	
	/**
	 * 转换csv格式文本到list集合，元素为列有集合
	 * @param content 文本字符串
	 * @param startLine 开始行
	 * @param number 返回行数
	 * @param analysis 解析方式
	 * @return
	 * @throws IOException
	 */
	public static List<String[]> asListByCsvTxt(String content,int startLine,int number,AnalysisCsv analysis){
		String[] lines = StringHelper.getLines(content, startLine, number);
		return analysisLines(lines,analysis);
	}
	
	public static List<String[]> asListByCsvTxt(String content,int startLine,int number) {
		return asListByCsvTxt(content,startLine,number, new CsvHelper().new CommaAnalysis());
	}

	public static List<String[]> asListByCsvTxt(String content,AnalysisCsv analysis) {
		return asListByCsvTxt(content,1,Integer.MAX_VALUE, analysis);
	}
	public static List<String[]> asListByCsvTxt(String content) {
		return asListByCsvTxt(content,1,Integer.MAX_VALUE, new CsvHelper().new CommaAnalysis());
	}
	
	public static String[] asLineArrayByCsvTxt(String content,int line,AnalysisCsv analysis)  {
		return asListByCsvTxt(content,line,1, analysis).get(0);
	}
	
	public static String[] asLineArrayByCsvTxt(String content,int line)  {
		return asListByCsvTxt(content,line,1, new CsvHelper().new CommaAnalysis()).get(0);
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
	public static List<Map<String,String>> asMaps(String path,String encod,int startLine,int number,Map<Integer,String> colKey,AnalysisCsv analysis) throws IOException{
		List<String[]> lines = asList(path, encod, startLine, number, analysis);
		return convertToMap(lines,colKey);
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
	public static List<Map<String,String>> asMaps(String path,String encod,int startLine,int number,AnalysisCsv analysis) throws IOException{
		String l = FileHelper.line(path, encod, 1);
		return asMaps(path, encod, startLine+1, number,colsValue(l, analysis), analysis);
	}
	
	public static List<Map<String,String>> asMaps(String path) throws IOException{
		return asMaps(path,encod_defaul, 1, Integer.MAX_VALUE , new CsvHelper().new CommaAnalysis());
	}
	
	public static List<Map<String,String>> asMaps(String path,AnalysisCsv analysis) throws IOException{
		return asMaps(path,encod_defaul, 1, Integer.MAX_VALUE , analysis);
	}

	public static List<Map<String,String>> asMaps(String path,String encod) throws IOException{
		return asMaps(path,encod , 1, Integer.MAX_VALUE, new CsvHelper().new CommaAnalysis());
	}
	public static List<Map<String,String>> asMaps(String path,String encod_defaul,AnalysisCsv analysis) throws IOException{
		return asMaps(path,encod_defaul , 1, Integer.MAX_VALUE, analysis);
	}
	
	public static List<Map<String,String>> asMaps(String path,String encod,int line) throws IOException{
		return asMaps(path,encod , line, 1, new CsvHelper().new CommaAnalysis());
	}

	public static List<Map<String,String>> asMaps(String path,Map<Integer,String> colKey,int startLine) throws IOException{
		return asMaps(path,encod_defaul, startLine, Integer.MAX_VALUE ,colKey, new CsvHelper().new CommaAnalysis());
	}
	
	public static Map<String,String> asMap(String path,String encod,int line,AnalysisCsv analysis) throws IOException{
		return asMaps(path,encod , line, 1, analysis).get(0);
	}
	
	public static Map<String,String> asMap(String path,int line) throws IOException{
		return asMaps(path,encod_defaul, line, 1  , new CsvHelper().new CommaAnalysis()).get(0);
	}
	
	public static Map<String,String> asLineMap(String path,int line,AnalysisCsv analysis) throws IOException{
		return asMaps(path,encod_defaul, line, 1  ,analysis).get(0);
	}

	public static List<Map<String,String>> asMaps(String path,String encod,int startLine,int number) throws IOException{
		return asMaps(path,encod , startLine, number, new CsvHelper().new CommaAnalysis());
	}
	
	public static List<Map<String,String>> asMaps(String path,int startLine,int number) throws IOException{
		return asMaps(path,encod_defaul, startLine, number , new CsvHelper().new CommaAnalysis());
	}
	
	public static List<Map<String,String>> asMaps(String path,int startLine,int number,AnalysisCsv analysis) throws IOException{
		return asMaps(path,encod_defaul, startLine, number , analysis);
	}
	
	public static List<Map<String,String>> asMaps(String path,Map<Integer,String> colKey) throws IOException{
		return asMaps(path,encod_defaul, 1, Integer.MAX_VALUE ,colKey, new CsvHelper().new CommaAnalysis());
	}
	
	public static List<Map<String,String>> asMaps(String path,Map<Integer,String> colKey,AnalysisCsv analysis) throws IOException{
		return asMaps(path,encod_defaul, 1, Integer.MAX_VALUE ,colKey, analysis);
	}

	public static List<Map<String,String>> asMaps(String path,String encod,Map<Integer,String> colKey) throws IOException{
		return asMaps(path,encod , 1, Integer.MAX_VALUE, colKey,new CsvHelper().new CommaAnalysis());
	}
	public static List<Map<String,String>> asMaps(String path,String encod,Map<Integer,String> colKey,AnalysisCsv analysis) throws IOException{
		return asMaps(path,encod , 1, Integer.MAX_VALUE,colKey, analysis);
	}
	
	public static Map<String,String> asMap(String path,String encod,int line,Map<Integer,String> colKey) throws IOException{
		return asMaps(path,encod , line, 1, colKey,new CsvHelper().new CommaAnalysis()).get(0);
	}
	
	public static Map<String,String> asMap(String path,String encod,int line,Map<Integer,String> colKey,AnalysisCsv analysis) throws IOException{
		return asMaps(path,encod , line, 1, colKey,analysis).get(0);
	}
	
	public static Map<String,String> asMap(String path,int line,Map<Integer,String> colKey) throws IOException{
		return asMaps(path,encod_defaul, line, 1 ,colKey , new CsvHelper().new CommaAnalysis()).get(0);
	}
	
	public static Map<String,String> asMap(String path,int line,Map<Integer,String> colKey,AnalysisCsv analysis) throws IOException{
		return asMaps(path,encod_defaul, line, 1  ,analysis).get(0);
	}

	public static List<Map<String,String>> asMaps(String path,String encod,int startLine,int number,Map<Integer,String> colKey) throws IOException{
		return asMaps(path,encod , startLine, number, colKey,new CsvHelper().new CommaAnalysis());
	}
	
	public static List<Map<String,String>> asMaps(String path,int startLine,int number,Map<Integer,String> colKey) throws IOException{
		return asMaps(path,encod_defaul, startLine, number ,colKey, new CsvHelper().new CommaAnalysis());
	}
	
	public static List<Map<String,String>> asMaps(String path,String encod,int startLine,Map<Integer,String> colKey) throws IOException{
		return asMaps(path,encod , startLine, Integer.MAX_VALUE,colKey, new CsvHelper().new CommaAnalysis());
	}
	
	public static List<Map<String,String>> asMaps(String path,int startLine,Map<Integer,String> colKey) throws IOException{
		return asMaps(path,encod_defaul, startLine,  Integer.MAX_VALUE , colKey,new CsvHelper().new CommaAnalysis());
	}
	
	public static List<Map<String,String>> asMaps(String path,int startLine,int number,Map<Integer,String> colKey,AnalysisCsv analysis) throws IOException{
		return asMaps(path,encod_defaul, startLine, number ,colKey,analysis);
	}
	
	public static List<Map<String,String>> asMaps(String path,int startLine,Map<Integer,String> colKey,AnalysisCsv analysis) throws IOException{
		return asMaps(path,encod_defaul, startLine, Integer.MAX_VALUE ,colKey,analysis);
	}
	
	public static List<Map<String,String>> asMaps(String path,String encod,int startLine,Map<Integer,String> colKey,AnalysisCsv analysis) throws IOException{
		return asMaps(path,encod, startLine, Integer.MAX_VALUE ,colKey,analysis);
	}
	
	/**
	 * 转换csv到list集合，元素为列有集合
	 * @param path 文件路径
	 * @param encod_defaul 文件编码
	 * @param startLine 开始行
	 * @param number 返回行数
	 * @param analysis 解析方式
	 * @return
	 * @throws IOException
	 */
	public static List<String[]> asList(String path,String encod,int startLine,int number,AnalysisCsv analysis) throws IOException{
		String[] lines = FileHelper.readLines(path, encod,startLine,number);
		return analysisLines(lines, analysis);
	}
	
	public static List<String[]> asList(String path,int startLine,int number,AnalysisCsv analysis) throws IOException{
		return asList(path,encod_defaul ,startLine,number,analysis);
	}
	
	public static List<String[]> asList(String path,int startLine,int number) throws IOException{
		return asList(path,encod_defaul,startLine,number ,new CsvHelper().new CommaAnalysis());
	}
	public static List<String[]> asList(String path,String encod ,int startLine) throws IOException{
		return asList(path,encod,startLine,Integer.MAX_VALUE ,new CsvHelper().new CommaAnalysis());
	}
	public static List<String[]> asList(String path,int line) throws IOException{
		return asList(path,encod_defaul ,line,Integer.MAX_VALUE,new CsvHelper().new CommaAnalysis());
	}
	public static List<String[]> asList(String path,String encod) throws IOException{
		return asList(path,encod ,1,Integer.MAX_VALUE,new CsvHelper().new CommaAnalysis());
	}
	public static String[] asLines (String path,String encod,int line) throws IOException{
		return asList(path,encod ,line,1,new CsvHelper().new CommaAnalysis()).get(0);
	}
	public static String[] asLines (String path,String encod,int line,AnalysisCsv analysis) throws IOException{
		return asList(path,encod ,line,1,new CsvHelper().new CommaAnalysis()).get(0);
	}
	
	public static List<String[]> asList(String path,int line,AnalysisCsv analysis) throws IOException{
		return asList(path,encod_defaul,line,Integer.MAX_VALUE ,analysis);
	}
	
	public static List<String[]> asList(String path,String encod,AnalysisCsv analysis) throws IOException{
		return asList(path,encod,1,Integer.MAX_VALUE ,analysis);
	}
	
	public static List<String[]> asList(String path,int startLine,int line,String encod) throws IOException{
		return asList(path,encod,startLine ,line,new CsvHelper().new CommaAnalysis());
	}
	
	public static List<String[]> asList(String path) throws IOException{
		return asList(path,1,Integer.MAX_VALUE,new CsvHelper().new CommaAnalysis());
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
	public class CommaAnalysis implements AnalysisCsv{

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
	public class RegexAnalysis implements AnalysisCsv{

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
		return new CsvHelper().new CommaAnalysis();
	}
	
	public static AnalysisCsv regexAnaly(){
		return new CsvHelper().new RegexAnalysis();
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
}