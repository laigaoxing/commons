package com.laigaoxing.common;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.regex.Matcher;

/**
 * 文件操作
 * 
 * @author jeff
 * 
 */
public abstract class FileCommon {

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
	 * @param filePath
	 */
	public static void createFolder(String filePath) {
		RegexOperate rx = RegexOperate.getInstance(".*?[\\\\|/]");
		List<String> list = rx.searchText(filePath, new TextUpdate() {
			@Override
			public String execute(Matcher m) {
				return m.group();
			}
		});
		String tempPath = "";
		for (String s : list) {
			tempPath += s;
			File f = new File(tempPath);
			if (!f.exists())
				f.mkdir();
		}
	}

	/**
	 * 写入文件
	 * @param in 输入流
	 * @param fo 输出文件
	 * @throws IOException
	 */
	public static void saveFile(InputStream in, FileOutputStream fo)
			throws IOException {
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
	 * @param saveAsPath 输出文件地址
	 * @throws IOException
	 */
	public static void saveFile(InputStream in, String saveAsPath)
			throws IOException {
		BufferedInputStream bfs = new BufferedInputStream(in);
		createFolder(saveAsPath);
		FileOutputStream fo = new FileOutputStream(saveAsPath);
		int len = 0;
		byte[] bs = new byte[1024];
		while ((len = bfs.read(bs)) != -1) {
			fo.write(bs, 0, len);
		}
		bfs.close();
		fo.close();
	}
	
	public static boolean exists(String path){
		return new File(path).exists();
	}
}
