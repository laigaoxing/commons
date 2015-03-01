package com.laigaoxing.http2.manager;

/**
 * 响应处理类
 * @author lichicheng
 *
 */
public interface ResponseProcess {

	public void process(HttpResponse response,SimpleRequest request);
	
}
