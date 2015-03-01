package com.laigaoxing.common;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import com.laigaoxing.common.io.ReaderChar;
import com.laigaoxing.common.regex.SearchTxt;

/**
 * 文本文件操作类
 * 
 * @author jeff
 * 
 */
public class TxtFileOperate {
	private static String currentPath; 

	private static String defaultEncod = "UTF-8";
	
	private String encod;
	
	private String path;
	
	/**
	 * 每次读入查找文件字符个数
	 */
	private int eachFindSize = 128;
	
	private InputStreamReader inReader;
	
	private BufferedReader reader;
	
	private int searchLine = 0;
	
	private List<String> searchs = new ArrayList<String>();
	
	private String endString = "";
	
	private TxtFileOperate(String path,String encod) throws IOException{
		inReader = new InputStreamReader(new FileInputStream(path),encod);
		reader = new BufferedReader(inReader);
		this.path = path;
		this.encod = encod;
	}
	
	private TxtFileOperate(String path) throws IOException{
		this(path,defaultEncod);
	}
	
	public static TxtFileOperate newObj(String path,String encod) throws IOException{
		return new TxtFileOperate( path, encod);
	}
	
	public static TxtFileOperate newObj(String path) throws IOException{
		return new TxtFileOperate( path);
	}
	
	public static String getText(String path) throws IOException {
		return getText(path, defaultEncod);
	}

	public static void writeTextFile(String str, String path)
			throws IOException {
		writeTextFile(str, path, defaultEncod);
	}
	
	public static void writeTextFile(String str) throws IOException {
		currentPath = FileCommon.getFilePath(currentPath).concat("_copy_").concat(File.separator).concat(FileCommon.getFileName(currentPath));
		writeTextFile(str, currentPath, defaultEncod);
	}
	
	public static void writeTextFileNotBakcUp(String str) throws IOException {
		writeTextFile(str, currentPath, defaultEncod);
	}

	/**
	 * 得到文件内容
	 * @param path 文件路径
	 * @param encod 编码
	 * @return 文件内容
	 * @throws UnsupportedEncodingException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static String getText(String path, String encod) throws IOException {
		currentPath = path;
		return getTxt(new FileInputStream(path), encod);
	}
	/**
	 * 得到流的文本内容
	 * @param io
	 * @param encod 编码级别
	 * @return
	 * @throws IOException
	 */
	public static String getTxt(InputStream io,String encod) throws IOException{
		StringBuffer buf = new StringBuffer();
		InputStreamReader reader = new InputStreamReader(io, encod);
		char[] chars = new char[1024];
		int length = 0;
		while ((length = reader.read(chars)) > -1) {
			buf.append(chars, 0, length);
		}
		reader.close();
		return buf.toString();
	}
	/**
	 * 得到流的文本内容,默认为系统编码级别
	 * @param io
	 * @return
	 * @throws IOException
	 */
	public static String getTxt(InputStream io) throws IOException{
		StringBuffer buf = new StringBuffer();
		InputStreamReader reader = new InputStreamReader(io, defaultEncod);
		char[] chars = new char[1024];
		int length = 0;
		while ((length = reader.read(chars)) > -1) {
			buf.append(chars, 0, length);
		}
		reader.close();
		return buf.toString();
	}
	
	/**
	 * 写入文本到文件
	 * @param str 写入内容
	 * @param path 文件路径
	 * @param encod 编码
	 * @throws IOException
	 */
	public static void writeTextFile(String str, String path, String encod)
			throws IOException {
		FileCommon.createFolder(path);
		OutputStreamWriter fileWriter = new OutputStreamWriter(new FileOutputStream(path), encod);
		fileWriter.append(str);
		fileWriter.flush();
		fileWriter.close();
	}
	/**
	 * 读取文本对应行数
	 * @param start 开始行
	 * @param line 行数
	 * @return
	 * @throws IOException
	 */
	public String readLine(int start,int line) throws IOException{
		StringBuffer rst = new StringBuffer();
		encod = encod == null ? defaultEncod : encod;
		InputStreamReader reader = new InputStreamReader(new FileInputStream(path), encod);
		int c = 0;
		int next = 0;
		int lineNo =0;
		boolean isappend = false;
		if(start == 1){
			isappend = true;
		}
		while((c = reader.read())!=-1){
			//遇见换行符
			if(c==13 || c ==10){
				lineNo++;
				if(isappend){
					rst.append(Constant.LINE);
				}
				if((lineNo==start-1)){
					isappend = true;
				}
				if((line + start > -1) &&(lineNo>=line+start-1)){
					break;
				}
				if(c==13){
					next= reader.read();
					if(next !=10 && isappend){
						rst.append((char)next);
					}
				}
				continue;
			}
			//指定行开始
			if(isappend){
				rst.append((char)c);
				if(next == -1){
					break;
				}
			}
		}
		return rst.toString();
	}
	
	public static String[] readLines(String path,String encod,int start,int line) throws IOException{
		List<String> rst = new ArrayList<String>();
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(new FileInputStream(path), encod));
		String lineStr = null;
		int lineNo =0;
		boolean isappend = false;
		if(start == 1){
			isappend = true;
		}
		while((lineStr = reader.readLine())!= null){
			if((lineNo==start-1)){
				isappend = true;
			}
			if((line + start > -1) &&(lineNo>=line+start-1)){
				break;
			}
			if(isappend){
				rst.add(lineStr);
			}
			lineNo++;
		}
		String[] rstArr = new String[rst.size()];
		rst.toArray(rstArr);
		return rstArr;
	}

	public static String readLine(String path,String encod,int start,int line) throws IOException{
		TxtFileOperate operate = TxtFileOperate.newObj(path,encod);
		return operate.readLine(start,line);
	}
	
	public static String readLine(String path,String encod,int lineNo) throws IOException{
		TxtFileOperate operate = TxtFileOperate.newObj(path,encod);
		return operate.readLine(lineNo,1);
	}
	
	public static String[] readLines(String path,int start,int line) throws IOException{
		return readLines(path,defaultEncod,start,line);
	}
	
	/**
	 * 从第一行读取line行
	 * @param line
	 * @return
	 * @throws IOException
	 */
	public String readLine(int line) throws  IOException{
		return readLine(1,line);
	}
	
	/**
	 * 从第几行开始读取到文件尾末
	 * @param start
	 * @return
	 * @throws IOException
	 */
	public String readLineEnd(int start) throws  IOException{
		return readLine(start,Integer.MAX_VALUE);
	}
	/**
	 * 从文件末尾读取文本
	 * @param start 开始行
	 * @param line 读取行数
	 * @return
	 * @throws IOException
	 */
	public String readStartFromLastLine(int start,int line) throws IOException{
		StringBuffer rst = new StringBuffer();
		RandomAccessFile raf = new RandomAccessFile(path, "r");
		raf.seek(raf.length()-1);
		int b =0 ;
		int i=0;
		int[] bs = new int[512];
		int next;
		int lineNo =0;
		boolean isappend = false;
		while((b =raf.read())!=-1){
			if(b==13 || b==10){
				if(isappend){
					int[] bscopy = new int[i];
					System.arraycopy(bs, 0, bscopy, 0, i);
					bscopy = ArraysOperate.reverse(bscopy);
					ReaderChar mr = new ReaderChar(bscopy,"utf8");
					int c = -1;
					while((c = mr.readChar())!=-1){
						rst.append((char)c);
					}
					rst.append(Constant.LINE);
				}
				raf.seek(raf.getFilePointer()-2);
				i=0;
				next = raf.read();
				if((lineNo==start-1)){
					isappend = true;
				}
				if((line + start > -1) &&(lineNo>=line+start-1)){
					break;
				}
				if(next != 13){
					bs[i] = next;
				}
				bs = new int[512];
				i++;
				raf.seek(raf.getFilePointer()-2);
				lineNo++;
				continue;
			}
			bs[i] = b;
			i++;
			raf.seek(raf.getFilePointer()-2);
		}
		return rst.toString();
	}
	
	/**
	 * 多行查找文本
	 * @param endFindRgx 分隔查找字符串起始行
	 * @param rgx 区配的正则
	 * @return
	 * @throws IOException
	 */
	public String readSearch(String endFindRgx,String rgx) throws IOException{
		StringBuffer sb = new StringBuffer();
		sb.append(endString);
 		endString = "";
		String l = null;
		if(searchs.size() ==0){
			while((l=reader.readLine())!=null){
				if(searchLine < eachFindSize){
					sb.append(l);
					sb.append(Constant.LINE);
				}else{
					if(l.matches(endFindRgx)){
						endString= l;
						break;
					}else{
						sb.append(l);
						sb.append(Constant.LINE);
					}
				}
				searchLine++;
			}
			searchs = SearchTxt.findAll(sb.toString(), rgx);
			searchLine = 0;
		}
		if(searchs.size()!=0){
			//得到将要搜索的字符串
			return searchs.remove(0);
		}else if(!"".equals(endString)){
			return readSearch(endFindRgx, rgx);
		}else{
			return null;
		}
	}
	
	/**
	 * 单行查找文本
	 * @param rgx 正则
	 * @return
	 * @throws IOException
	 */
	public String readSearch(String rgx,int group) throws IOException{
		String l = null;
		String s = null;
		while((l=reader.readLine())!=null){
			if(!(s=SearchTxt.find(l, rgx,group)).equals("")){
				return s;
			}
		}
		return null;
	}
	
	public String readSearch(String rgx) throws IOException{
		return readSearch(rgx,0);
	}
	
	public static boolean isUTF8(File f) throws IOException {
		InputStream in = new FileInputStream(f);
		byte[] bs = new byte[3];
		in.read(bs);
		in.close();
		if (bs[0] == -17 && bs[1] == -69 && bs[2] == -65) {
			return true;
		}
		return false;
	}
	
	
	/**
	 * 分隔文件
	 * @throws IOException 
	 */
	public static void splitFile(String path,String size) throws IOException{
		long filebytes = 0;
		if(size.indexOf("M")!=-1){
			filebytes = Integer.valueOf(size.substring(0,size.indexOf("M")))  * 1024 * 1024;
		}
		int i=0;
		int currSize = 0;
		BufferedInputStream fr = new BufferedInputStream(new FileInputStream(path));
		
		BufferedOutputStream fo =new BufferedOutputStream( new FileOutputStream(FileCommon.getFilePath(path) + FileCommon.getFileName(path)  + i));
		int c = 0;
		 while((c = fr.read())!=-1){
			if(currSize >= filebytes && (c == 10 || c ==13)){
				i++;
				fo.flush();
				fo.close();
				fo = new BufferedOutputStream(new FileOutputStream(FileCommon.getFilePath(path) + FileCommon.getFileName(path)  + i));
				currSize=0;
				continue;
			}
			fo.write(c);
			currSize++;
		}
	}
}
