package com.laigaoxing.test;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.apache.log4j.Logger;

import sun.misc.Cache;


import com.laigaoxing.common.ArraysOperate;
import com.laigaoxing.common.FileHelper;
import com.laigaoxing.common.FolderOperate;
import com.laigaoxing.common.TxtFileOperate;
import com.laigaoxing.common.UriOperate;


public class Main {
	
	static Logger log = Logger.getLogger(Main.class);
	
	public static void main(String[] args) throws IOException{
		Calendar s = Calendar.getInstance();
		URL url = new URL("http://192.168.1.13/v1/fund/index?_type=json&openid=sherwin&sign_type=MD5&sign=6ab02fb5f32ecc38f30ff45b9229c22a");
		String t= TxtFileOperate.getTxt(url.openStream(),"utf8");
		TxtFileOperate.writeTextFile("C:\\Users\\jeff\\Desktop\\1.txt",t);
		Calendar e = Calendar.getInstance();
		System.out.println((e.getTimeInMillis()-s.getTimeInMillis())/1000);
	}
	
	/**
	 * 查询传入path路径下所有文件夹不存在对应文件名的目录
	 * @param path
	 * @param names
	 * @return
	 */
	public List<File> notExist(String path,String... names){
		List<File> rst = new ArrayList<File>();
		File f = new File(path);
		File[] file = f.listFiles(new FileFilter() {
			
			@Override
			public boolean accept(File pathname) {
				if(pathname.isFile()){
					return false;
				}
				return true;
			}
		});
		for(File tmp : file){
			String[] files = tmp.list();
			boolean isAppend = true;
			for(String name : files){
				for(String existFile : names){
					if(name.equals(existFile)){
						isAppend =false;
						continue;
					}
				}
			}
			if(isAppend){
				rst.add(new File(tmp.getPath() ));
			}
		}
		return rst;
	}
	
	public void get(Object obj){
		Class<?> c = obj.getClass();
		if(c.isArray()){
			if(c.getName().equals("[C")){
				char[] objs = (char[]) obj;
			}else if(c.getName().equals("[Z")){
				boolean[] objs = (boolean[]) obj;
			}else if(c.getName().equals("[B")){
				byte[] objs = (byte[]) obj;
			}else if(c.getName().equals("[Z")){
				
			}else if(c.getName().equals("[Z")){
				
			}else if(c.getName().equals("[Z")){
				
			}else if(c.getName().equals("[Z")){
				
			}
		}
		try {
			RandomAccessFile in = new RandomAccessFile("","");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("       ");
	}
}
