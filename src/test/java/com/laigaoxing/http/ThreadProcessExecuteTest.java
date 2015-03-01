package com.laigaoxing.http;

import java.util.Random;

import com.laigaoxing.http.mng.ProcessException;
import com.laigaoxing.http.mng.ProcessMng;
import com.laigaoxing.http.mng.ProcessOperate;
import com.laigaoxing.http.mng.TextResponseProcess;

/**
 * 多个http请求处理程序，多线程问题测试
 * @author lichicheng
 *
 */
public class ThreadProcessExecuteTest {
	
	public static void main(String[] args) {
		ProcessMng mng = new ProcessMng();
		
		mng.addProcess("http://localhost:8080/webs1/services/TestApi?wsdl", new TestProcess1());
		mng.addProcess("http://localhost:8080/webs1/services/TestApi?wsdl", new TestProcess1());
		mng.setInterval(0, 0);
		mng.executeProcess();
	}
	
	
	public static class TestProcess1 extends TextResponseProcess{

		@Override
		public void process(String text, ProcessOperate operate)
				throws ProcessException {
			for(int i=0;i<10;i++){
				operate.addProcess("http://localhost:8080/webs2/services/", new TestProcess2());
			}
			System.out.println("execute1:" + this);
		}
		
	}
	
	public static class TestProcess2 extends TextResponseProcess{

		@Override
		public void process(String text, ProcessOperate operate)
				throws ProcessException {
			int r = new Random().nextInt(5);
			if(r==4){
				throw new ProcessException("执行失败了");
			}
			System.out.println("execute2:" + this);
		}
		
	}
	
}
