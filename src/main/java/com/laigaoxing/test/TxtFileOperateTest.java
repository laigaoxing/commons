package com.laigaoxing.test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.io.StringReader;
import java.util.Arrays;
import java.util.Collections;

import org.junit.Test;
import com.laigaoxing.common.ArraysOperate;
import com.laigaoxing.common.Constant;
import com.laigaoxing.common.TxtFileOperate;
import com.laigaoxing.common.io.ReaderChar;

public class TxtFileOperateTest {
	
	String path  = "d:\\li\\chi\\soft\\good.mp3";
	String pathunit = "E:\\project_source\\newcrawl\\log_analysis\\ms_crawler_6.14.log";
	String pathwin = "D:\\work_resource\\backup\\logger\\ms_crawler.log.2012-05-30";
	String big = "E:\\project_source\\makshi\\indices\\simple_code\\formatCodeh";
	@Test public void getFilePathTest(){
//		System.out.println(TxtFileOperate.getFilePath(path));
	}
	
	@Test public void readLine() throws FileNotFoundException, IOException{
		TxtFileOperate tfo = TxtFileOperate.newObj(pathwin);
		String rst = tfo.readStartFromLastLine(5, 200);
		System.out.println(rst);
		//ArraysOperate.output(rst.getBytes()," ",20);
	}
	
	@Test public void splitFile() throws IOException{
		int[] bs = {154, 184, 228, 174, 189, 231, 166, 142, 229, 142, 141, 229, 126, 56, 55, 50, 48, 48, 34,0,0};
		int[] bs1 =new int[19];
		System.arraycopy(bs,0, bs1, 0, 5);
		System.out.println(bs1[2]);
	}
	
	@Test public void searchFileInBigFile() throws IOException{
		String path = "D:\\work_resource\\dataimport_source\\sql_data_source\\indices\\logs\\";
		TxtFileOperate to = TxtFileOperate.newObj(path + "all.txt","gbk");
		String l = "";
		int i=0;
		while(null != (l=to.readSearch(".*?字典表未配置此代码指.*","(?s)body--.*?字典表未配置此代码指标[^\r\n]*\r\n"))){
			System.out.println(l);
			System.out.println();
			i++;
		}
		System.out.println(i);
	}
	
	@Test public void readLines(){
		
	}
}
