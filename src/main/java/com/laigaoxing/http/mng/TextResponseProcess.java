package com.laigaoxing.http.mng;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

public abstract class TextResponseProcess implements ResponseProcess{

	String encode="utf8";
	
	public TextResponseProcess(){}
	public TextResponseProcess(String encode){
		this.encode = encode;
	}
	
	@Override
	public void process(InputStream response,ProcessOperate operate) throws ProcessException {
		try {
			process(IOUtils.toString(response, encode),operate);
		} catch (IOException e) {
			throw new ProcessException(e.getMessage());
		}
	}

	public abstract void process(String text,ProcessOperate operate) throws ProcessException;

	public String getEncode() {
		return encode;
	}
	public void setEncode(String encode) {
		this.encode = encode;
	}

}
