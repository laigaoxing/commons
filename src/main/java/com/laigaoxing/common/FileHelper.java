package com.laigaoxing.common;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class FileHelper {

	private static String defaultEncod = "utf8";
	
	/**
	 * 获得文件夹下所有文件
	 * @param path 文件夹路径
	 * @return 文件名数组
	 */
	public static Set<String> readListFiles(String path){
		File file = new File(path);
		String[] files = file.list();
		HashSet<String> result = new HashSet<String>();
		if(files==null){throw new NullPointerException();}
		for(int i=0;i<files.length;i++){
			result.add(files[i].substring(0, files[i].lastIndexOf(".")));
		}
		return result;
	}
	
	public static boolean exists(String path){
		return new File(path).exists();
	}
	
	/**
	 * 得到流的文本内容
	 * @param io
	 * @param encod 编码级别
	 * @return
	 * @throws IOException
	 */
	public static String getText(InputStream io,String encod) throws IOException{
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
	 * 读取文件行数
	 * @param path
	 * @param encod
	 * @param start
	 * @param line
	 * @return
	 * @throws IOException
	 */
	public static String readLine(InputStream io,String encod,int start,int line) throws IOException{
		if(line < 0){
			return StringHelper.getLine(getText(io,encod), start, line);
		}
		StringBuffer rst = new StringBuffer();
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(io, encod));
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
				rst.append(lineStr);
			}
			if(isappend && lineNo > line+start-1){
				rst.append(Constant.LINE);
			}
			lineNo++;
		}
		return rst.toString();
	}
	/**
	 * 读取文件行数
	 * @param path
	 * @param encod
	 * @param start
	 * @param line
	 * @return
	 * @throws IOException
	 */
	public static String readLine(String path,String encod,int start,int line) throws IOException{
		if(line < 0){
			return StringHelper.getLine(getText(path,encod), start, line);
		}
		return readLine(new FileInputStream(path), encod,start,line);
	}
	
	public static String[] readLines(InputStream in,String encod,int start,int line) throws IOException{
		List<String> rst = new ArrayList<String>();
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(in, encod));
		String lineStr = null;
		int lineNo =0;
		boolean isappend = false;
		if(start == 1){
			isappend = true;
		}
		if(line < 0){
			return StringHelper.getLines(getText(in,encod), start, line);
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
	
	public static String[] readLines(String path,String encod,int start,int line) throws IOException{
		return readLines(new FileInputStream(path),encod,start,line);
	}
	
	public static String[] readLines(String path,int start,int line) throws IOException{
		return readLines(path, defaultEncod, start, line);
	}
	
	public static String[] readLines(String path,String encod,int line) throws IOException{
		return readLines(path, encod, line, 1);
	}
	
	public static String[] readLines(String path,int startLine) throws IOException{
		return readLines(path, defaultEncod, startLine, Integer.MAX_VALUE);
	}
	
	public static String[] readNextLineAsArray(String path,String encod,int startLine) throws IOException{
		return readLines(path, encod, startLine, Integer.MAX_VALUE);
	}
	
	public static String line(String path,int start,int line) throws IOException{
		return readLine(path, defaultEncod, start, line);
	}
	
	public static String line(InputStream in,String encode,int start,int line) throws IOException{
		return readLine(in, defaultEncod, start, line);
	}
	
	public static String line(String path,String encod,int line) throws IOException{
		return readLine(path, encod, line, 1);
	}
	
	public static String line(String path,int line) throws IOException{
		return readLine(path, defaultEncod, line, 1);
	}
	
	public static String readLine(String path,int startLine) throws IOException{
		return readLine(path, defaultEncod, startLine, Integer.MAX_VALUE);
	}
	
	public static String readLine(String path,int startLine,int line) throws IOException{
		return readLine(path, defaultEncod, startLine, line);
	}
	
	
	public static String readLine(InputStream io,int startLine) throws IOException{
		return readLine(io, defaultEncod, startLine, Integer.MAX_VALUE);
	}
	
	public static String readLine(InputStream io,int startLine,int line) throws IOException{
		return readLine(io, defaultEncod, startLine, line);
	}
	
	public static String readLine(String path,String encod,int line) throws IOException{
		return readLine(path, encod, line, Integer.MAX_VALUE);
	}
	
	public static String getText(InputStream io) throws IOException{
		return getText(io,defaultEncod);
	}

	public static String getText(String path) throws IOException {
		return getText(path, defaultEncod);
	}
	
	
	public static String getText(String path, String encod) throws IOException {
		return getText(new FileInputStream(path), encod);
	}
	
	/**
	 * 写入文本到文件
	 * @param str 写入内容
	 * @param path 文件路径
	 * @param encod 编码
	 * @throws IOException
	 */
	public static void writeTextFile(String str, String path, String encod) throws IOException {
		createFolder(getFilePath(path));
		OutputStreamWriter fileWriter = new OutputStreamWriter(new FileOutputStream(path), encod);
		fileWriter.append(str);
		fileWriter.flush();
		fileWriter.close();
	}
	
	/**
	 * 写入文本到文件
	 * @param str 写入内容
	 * @param path 文件路径
	 * @param encod 编码
	 * @throws IOException
	 */
	public static void writeTextFile(String str, String path) throws IOException {
		writeTextFile(str, path, defaultEncod);
	}
	/**
	 * 得到文件路径中的文件名
	 * 
	 * @param path
	 * @return
	 */
	public static String getFileName(String path) {
		return path.replaceAll(".*[\\\\|/]", "");
	}

	/**
	 * 得到文件路径中的文件址
	 * 
	 * @param path
	 * @return
	 */
	public static String getFilePath(String path) {
		return path.replaceAll("(?=[^\\\\|/]+$).*", "");
	}

	/**
	 * 创建文件夹
	 * 
	 * @param filePaht
	 */
	public static void createFolder(String filePaht) {
		File f = new File(filePaht);
		if (!f.exists())
			f.mkdirs();
	}

	/**
	 * 写入文件
	 * @param in 输入流
	 * @param fo 输出文件
	 * @throws IOException
	 */
	public static void saveFile(InputStream in, FileOutputStream fo) throws IOException {
		BufferedInputStream bfs = new BufferedInputStream(in);
		int len = 0;
		byte[] bs = new byte[1024];
		while ((len = bfs.read(bs)) != -1) {
			fo.write(bs, 0, len);
		}
		bfs.close();
		fo.close();
	}
	
	/**
	 * 写入文件
	 * @param in 输入流
	 * @param saveAsPath 文件保存地址
	 * @throws IOException
	 */
	public static void saveFile(InputStream in, String saveAsPath) throws IOException {
		FileOutputStream fo = new FileOutputStream(saveAsPath);
		saveFile(in,fo);
	}

	/**
	 * 写入文件
	 * @param in 输入流
	 * @param saveAsPath 文件保存地址
	 * @throws IOException
	 */
	public static void saveFile(InputStream in, File file) throws IOException {
		FileOutputStream fo = new FileOutputStream(file.getPath());
		saveFile(in,fo);
	}


}
