package com.laigaoxing.http.mng;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.laigaoxing.http.HttpCache;

public class ProcessMng implements ProcessOperate{
	
	Queue<ProcessModel> queue = new LinkedList<ProcessModel>();
	
	Queue<ProcessModel> errorQueue = new LinkedList<ProcessModel>();
	
	Logger log = LoggerFactory.getLogger(ProcessMng.class);
	int sumProcess = 0;
	int minSecond = 0;
	int maxSecond = 3;
	
	public void executeProcess(){
		executeProcess(false);
	}
	private String currUri;
	/**
	 * 开始执行
	 */
	public void executeProcess(boolean downNew){
		ProcessModel model = null;
		while((model = queue.poll()) != null){
			String uri = model.getUri();
			InputStream in = null;
			try {
				in = HttpCache.getResponse(uri,downNew);
			} catch (IOException e) {
				if(model.getFailCount()==3){
					errorQueue.add(model);
				}else{
					queue.add(model);
				}
				model.addFailCount();
				log.error(String.format("http请求失败:%s", e.getMessage()));
				continue;
			}
			ResponseProcess process = model.getProcess();
			currUri = model.getUri();
			try {
				process.process(in,this);
			} catch (ProcessException e) {
				errorQueue.add(model);
				log.error(String.format("响应处理失败：%s", e.getMessage()));
			}

			if(maxSecond != 0){
				try {
					Thread.sleep((minSecond + new java.util.Random().nextInt(maxSecond - minSecond)) * 1000);
				} catch (InterruptedException e) {
					log.error("定时出错:" + e.getMessage());
				}
			}
		}
		if(errorQueue.size() == 0){
			log.info("全部执行成功: " + sumProcess);
		}else{
			log.info(String.format("未全部执行,共要处理uri：%s,失败数:%s",sumProcess,errorQueue.size()));
		}
	}

	@Override
	public void addProcess(String uri, ResponseProcess processs) {
		sumProcess++;
		queue.add(new ProcessModel(uri,processs));
	}

	public void setInterval(int min,int max){
		this.minSecond = min;
		this.maxSecond = max;
	}

	@Override
	public RequestInfo getRequest() {
		RequestInfo info = new RequestInfo();
		URL url = null;
		try {
			url = new URL(currUri);
		} catch (MalformedURLException e) {
			log.error(e.getMessage());
			return null;
		}
		info.setHost(url.getProtocol() + "://" +url.getHost());
		if(url.getPort() != -1){
			info.setHost(info.getHost() + ":" + url.getPort());
		}
		info.setUri(currUri);
		return info;
	}
}
