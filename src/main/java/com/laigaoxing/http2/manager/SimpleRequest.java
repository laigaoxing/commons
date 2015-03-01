package com.laigaoxing.http2.manager;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.message.BasicNameValuePair;

/**
 * 用户http请求参数设定
 * @author lichicheng
 *
 */
public class SimpleRequest extends HttpEntityEnclosingRequestBase{


	String method = "GET";
	public SimpleRequest(){}
	List<NameValuePair> params = new ArrayList<NameValuePair>();
	public SimpleRequest(final String uri){
	 	super();
        super.setURI(URI.create(uri));
	}
	
	/**
	 * 添加参数
	 */
	public void addRequestParameter(String name,String value){
		if(this.getEntity() == null){
			HttpEntity entity = null;
			try {
				entity = new UrlEncodedFormEntity(params);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				System.out.println("设置参数出错");
				return;
			}
			super.setEntity(entity);
		}
		params.add(new BasicNameValuePair(name, value));
	}
	
	@Override
	public String getMethod() {
		return method;
	}
	
	
	public void withPost(){
		method = "POST";
	}
	
	public void withGet(){
		method = "GET";
	}
}
