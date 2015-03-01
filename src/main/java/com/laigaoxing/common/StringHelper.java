package com.laigaoxing.common;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class StringHelper {

	public static boolean isBlank(String str) {
		if (null == str || "".equals(str)) {
			return true;
		}
		return false;
	}

	/**
	 * 把时间字符串中的数字提取出来。 时间字符串必须是完整的，不然时间不正确。完整如 2011/01/01,不完整如11/1/1。
	 * 
	 * @param time
	 *            时间字符串
	 * @return
	 */
	public static String toNumberString(String time) {
		int len = time.length();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < len; i++) {
			char c = time.charAt(i);
			if (c >= '0' && c <= '9') {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	/**
	 * 把 “,” 分隔的数字转换成纯数字。即 把 "1,234.56" 转换成 1234.56，就是去掉数据行中数据的引号和逗号。
	 * 
	 * @param str
	 *            一行数据
	 * @return
	 */
	public static String converDelimeterNumber(String str) {
		StringBuffer sb = new StringBuffer(str);
		boolean removeFlag = false;
		for (int i = 0; i < sb.length();) {
			if (sb.charAt(i) == '"') {// 遇到引号，切换删除标志,并删除引号
				removeFlag = (!removeFlag);
				sb.deleteCharAt(i);
				continue;
			}
			if (sb.charAt(i) == ' ' || sb.charAt(i) == ',') {
				if (removeFlag) {// 在删除标志下，删除逗号及可能的空格
					sb.deleteCharAt(i);
					continue;
				}
			}
			++i;
		}
		return sb.toString();
	}

	/**
	 * 把 形如"1,2,3"的字符串转换成数组
	 * 
	 * @param s
	 * @return
	 */
	public static int[] converColIndexes(String s) {
		String[] a = s.split(",");
		int[] colIndexes = new int[a.length];
		for (int i = 0; i < colIndexes.length; i++) {
			colIndexes[i] = Integer.parseInt(a[i]);
		}
		return colIndexes;
	}
	
	/**
	 * 获取 uri 的域名
	 * 
	 * @param uri
	 * @return
	 */
	public static String getHost(String uri) {
		URI uriObject = null;
		try {
			uriObject = new URI(uri);
		} catch (URISyntaxException e) {
		}
		return uriObject.getHost();
	}

	/**
	 * 获取 uri 的文件名
	 * 
	 * @param uri
	 * @return
	 */
	public static String getFileName(String uri) {
		int endIndex = uri.lastIndexOf("?");
		if (endIndex < 0) {
			endIndex = uri.length();
		}
		String noParamUri = uri.substring(0, endIndex);
		int begin = uri.lastIndexOf("/");
		return noParamUri.substring(begin + 1, noParamUri.length());
	}
	
	/**
	 * 
	 * @param str 转换类型 abc_name到AbcName
	 * @return
	 */
	public static String toClassName(String str){
		int flag=0;
		boolean upp = false;
		StringBuffer sbtname = new StringBuffer();
		for(char c : str.toCharArray()){
			if(flag==0 || upp){
				sbtname.append(Character.toUpperCase(c));
				upp = false;
			}else{
				sbtname.append(c);
			}
			if(c == '_'){
				upp = true;
			}
			
			flag++;
		}
		return sbtname.toString();
	}
	
	/**
	 * 返回文本对应的行数
	 * @param content
	 * @param start
	 * @param number
	 * @return
	 */
	public static String[] getLines(String content,int startLine,int number){
		if(startLine == 0){
			throw new IndexOutOfBoundsException("this is from 1 start count.");
		}
		List<String> rst = new ArrayList<String>();
		String[] lines = content.split("\r\n",Integer.MAX_VALUE);

		int count = lines.length-startLine;
		if(number < 0){
			count = count + number + 1;
			number = lines.length + number + 1;
		}
		for(int i=0;i<number && i<=count;i++){
			
			rst.add(lines[i + startLine -1]);
			
		}
		String[] rstArr = new String[rst.size()];
		rst.toArray(rstArr);
		return rstArr;
	}

	
	public static String[] getLines(String content,int startLine){
		return getLines(content,startLine,Integer.MAX_VALUE);
	}
	
	public static String getLine(String content,int startLine,int number){
		if(startLine == 0){
			throw new IndexOutOfBoundsException("this is from 1 start count.");
		}
		StringBuffer rst = new StringBuffer();
		String[] lines = content.split("\r\n|\n|\r",Integer.MAX_VALUE);
		int count = lines.length-startLine;
		if(number < 0){
			count = count + number + 1;
			number = lines.length + number + 1;
		}
		for(int i=0;i<number && i<=count;i++){
			
			rst.append(lines[i + startLine -1]);
			rst.append(Constant.LINE);
		}
		return rst.toString();
	}
	
	public static String getLine(String content,int startLine){
		return getLine(content,startLine,Integer.MAX_VALUE);
	}
	
	public static String line(String content,int line){
		return getLines(content,line,1)[0];
	}
	
	
	public static void main(String[] args){
		System.out.println(toClassName("macroscopic"));
	}
}
