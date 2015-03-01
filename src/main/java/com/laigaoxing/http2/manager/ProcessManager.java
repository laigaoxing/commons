//package com.laigaoxing.http2.manager;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.util.LinkedList;
//import java.util.Queue;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.laigaoxing.http.mng.ProcessException;
//public class ProcessManager {
//	
//	Queue<ProcessModel> queue = new LinkedList<ProcessModel>();
//	
//	Queue<ProcessModel> errorQueue = new LinkedList<ProcessModel>();
//	
//	Logger log = LoggerFactory.getLogger(ProcessManager.class);
//	int sumProcess = 0;
//	
//	
//	public void executeProcess(){
//		executeProcess(false);
//	}
//
//	/**
//	 * 开始执行
//	 */
//	public void executeProcess(boolean downNew){
//		ProcessModel model = null;
//		while((model = queue.poll()) != null){
//			String uri = model.getUri();
//			//model.getRequest()
//			InputStream in = null;
//			try {
//				in = HttpCache.getResponse(uri,downNew);
//			} catch (IOException e) {
//				if(model.getFailCount()==3){
//					errorQueue.add(model);
//				}else{
//					queue.add(model);
//				}
//				model.addFailCount();
//				log.error(String.format("http请求失败:%s", e.getMessage()));
//				continue;
//			}
//			ResponseProcess process = model.getProcess();
//			HttpRequest request = null;
//			HttpResponse response = null;
//			try {
//				process.process(request,response);
//			} catch (ProcessException e) {
//				errorQueue.add(model);
//				log.error(String.format("响应处理失败：%s", e.getMessage()));
//			}
//
//			
//		}
//		if(errorQueue.size() == 0){
//			log.info("全部执行成功: " + sumProcess);
//		}else{
//			log.info(String.format("未全部执行,共要处理uri：%s,失败数:%s",sumProcess,errorQueue.size()));
//		}
//	}
//
//
//}
