package com.laigaoxing.http2;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.laigaoxing.common.FileHelper;
import com.laigaoxing.common.FolderOperate;

public class HttpClientOperate {
	
	static String tempSavePath = "d:\\temp_html\\";
	
	private static Logger log = LoggerFactory.getLogger(HttpClientOperate.class);
	
	private static void cahceResponse(HttpRequestBase request,boolean downNew) throws IOException{
		String uri = request.getURI().toString();
		String filePath = getCachePath(uri);
		if(downNew || !FileHelper.exists(filePath)){

			log.info(String.format("处理uri:%s", uri));
			
			HttpClient client = new DefaultHttpClient();
			client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,3000);
			client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,2000);
			
	        request.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:12.0) Gecko/20100101 Firefox/12.0");
			HttpResponse res = client.execute(request);
			if(res.getStatusLine().getStatusCode()!=200)
				throw new IOException("status:" + res.getStatusLine().getStatusCode());
			
			if(!FolderOperate.exists(FolderOperate.getFilePath(filePath))){
				FolderOperate.createFolder(filePath);
			}
			IOUtils.copy(res.getEntity().getContent(), new FileOutputStream(filePath));
		}
	}
	
	public static InputStream getResponse(HttpRequestBase request,boolean downNew) throws IOException{
		cahceResponse(request,downNew);
		String filePath = getCachePath(request.getURI().toString());
		return new FileInputStream(filePath);
	}
	
	public static InputStream getResponse(HttpRequestBase request) throws IOException{
		return getResponse(request,false);
	}
	
	private static String getCachePath(String uri) throws MalformedURLException{
		URL url = new URL(uri);
		String uriname = url.getFile();
		String host = host(uri);
		uriname = uriname.replace("?", "_").replace("/", "_");
		if(uriname.equals("") || "_".equals(uriname)){
			uriname = FileHelper.getFileName(uri.substring(0,uri.length()-1));
		}
		String filePath = tempSavePath + host + "\\"+ uriname;
		return filePath;
	}
	
	private static String host(String uri) throws MalformedURLException{
		URL u = null;
		u = new URL(uri);
		return u.getHost();
	}
	
	public static void main(String[] args) throws IOException, URISyntaxException {
		//HttpCache.getResponse("http://news.163.com/photoview/00AP0001/82571.html#p=AFLQJVI400AP0001");
		URI uri = new URI("http://news.163.com/photoview/00AP0001/82571.html#p=AFLQJVI400AP0001");
		System.out.println(uri.toString());
	}
}
