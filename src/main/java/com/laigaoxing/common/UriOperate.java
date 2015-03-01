package com.laigaoxing.common;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.log4j.Logger;

public class UriOperate {
	
	static Logger logger = Logger.getLogger("dataservicefile");
	
	static String encod = "utf-8";

	public static String getHtml(String webUri) throws IOException{
		return getHtml(webUri,encod);
	}
	
	public static String getHtml(String webUri,String encod) throws IOException{
		URL url = new URL(webUri);
		URLConnection con = url.openConnection();
		con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:12.0) Gecko/20100101 Firefox/12.0");
		con.setConnectTimeout(1000);
		for(String k : con.getHeaderFields().keySet()){
			logger.debug(k + ":" + con.getHeaderFields().get(k));
		}
		return TxtFileOperate.getTxt(con.getInputStream(), encod);
		
	}
	
	public static void main(String[] args) throws IOException{
		String s= getHtml("http://www.woool.com");
		//System.out.println(s);
	}
}
