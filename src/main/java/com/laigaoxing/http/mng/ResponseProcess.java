package com.laigaoxing.http.mng;

import java.io.IOException;
import java.io.InputStream;

/**
 * 响应流处理类
 * @author lichicheng
 *
 */
public interface ResponseProcess {
	
	public void process(InputStream response,ProcessOperate operate) throws ProcessException;
	
}
