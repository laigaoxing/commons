package com.laigaoxing.http2.manager;

import org.apache.http.client.methods.HttpRequestBase;

public class ProcessModel {

	HttpRequestBase request;
	
	String uri;
	
	ResponseProcess process;

	public HttpRequestBase getRequest() {
		return request;
	}

	public void setRequest(HttpRequestBase request) {
		this.request = request;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public ResponseProcess getProcess() {
		return process;
	}

	public void setProcess(ResponseProcess process) {
		this.process = process;
	}
	
}
