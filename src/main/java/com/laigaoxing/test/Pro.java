package com.laigaoxing.test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

import com.laigaoxing.common.UriOperate;


public class Pro extends Properties{

	public Pro(){
		try {
			super.load(this.getClass().getResourceAsStream("/database.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		URL url = new URL("http://192.168.1.147:8080/open/info/open02.shtml");
		HttpURLConnection con = (HttpURLConnection)	url.openConnection();
		con.getInputStream();
		System.out.println(con.getResponseMessage());
		System.out.println(con.getHeaderFields());
	}
	
}
