package com.laigaoxing.common.regex;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.laigaoxing.common.Constant;
import com.laigaoxing.common.RegexOperate;
import com.laigaoxing.common.TxtFileOperate;
import com.laigaoxing.common.TxtResult;

public class SearchTxt {
	
	public static void exe(String source,String regex,TxtResult tr){
		RegexOperate rx = RegexOperate.getInstance(regex);
		rx.searchText(source, tr);
	}
	
	public static List<String> findAll(String source,String regex,int group){
		List<String> rst = new ArrayList<String>();
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(source);
		while(m.find()){
			rst.add(m.group(group));
		}
		return rst;
	}
	
	public static List<String[]> findsAll(String source,String regex){
		List<String[]> rst = new ArrayList<String[]>();
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(source);
		while(m.find()){
			String[] line = new String[m.groupCount() + 1];
			for(int i=0;i<m.groupCount()+1;i++){
				line[i] = m.group(i);
			}
			rst.add(line);
		}
		return rst;
	}
	
	public static String find(String source,String regex,int group){
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(source);
		if(m.find()){
			return m.group(group);
		}
		return "";
	}
	
	public static String[] finds(String source,String regex){
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(source);
		String[] rst = null;
		if (m.find()) {
			rst = new String[m.groupCount() + 1];
			for(int i=0;i<m.groupCount()+1;i++){
				rst[i] = m.group(i);
			}
		}
		return rst;
	}
	
	public static String find(String source,String regex){
		return find(source,regex,0);
	}
	
	public static List<String> findAll(String source,String regex){
		return findAll(source,regex,0);
	}
	
	public static String findAllToString(String source,String regex,int group){
		StringBuffer rst = new StringBuffer();
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(source);
		while(m.find()){
			rst.append(m.group(group));
			rst.append(Constant.LINE);
		}
		return rst.toString();
	}
	
	public static String findAllToString(String source,String regex){
		return findAllToString(source,regex,0);
	}
	
	/**
	 * 逐行查找匹配字符串
	 * @param path 文件路径
	 * @throws IOException 
	 */
	public static List<String> findAllEveryLine(String path,String regex,int group,String encod) throws IOException{
		List<String> rst = new ArrayList<String>();
		TxtFileOperate to = TxtFileOperate.newObj(path,encod);
		String l= "";
		while((l=to.readSearch(regex,group))!=null){
			rst.add(l);
		}
		return rst;
	}
	
	public static List<String> findAllEveryLine(String path,String regex,int group) throws IOException{
		return findAllEveryLine(path,regex,group);
	}
	public static List<String> findAllEveryLine(String path,String regex,String encod) throws IOException{
		return findAllEveryLine(path,regex,0,encod);
	}
	
	public static List<String> findAllEveryLine(String path,String regex) throws IOException{
		return findAllEveryLine(path,regex,0, "utf8");
	}
	
	public static String findAllEveryLineToStr(String path,String regex,int group,String encod) throws IOException{
		StringBuffer rst = new StringBuffer();
		TxtFileOperate to = TxtFileOperate.newObj(path,encod);
		String l= "";
		while((l=to.readSearch(regex,group))!=null){
			rst.append(l);
			rst.append(Constant.LINE);
		}
		return rst.toString();
	}
	
	public static String findAllEveryLineToStr(String path,String regex,int group) throws IOException{
		return findAllEveryLineToStr(path,regex,group,"utf8");
	}
	
	public static String findAllEveryLineToStr(String path,String regex,String encod) throws IOException{
		return findAllEveryLineToStr(path,regex,0,encod);
	}
	
	public static String findAllEveryLineToStr(String path,String regex) throws IOException{
		return findAllEveryLineToStr(path,regex,0,"utf8");
	}
}
