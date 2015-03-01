package com.laigaoxing.common.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import com.laigaoxing.common.Constant;

public class ReaderChar extends InputStreamArray{

	
	public ReaderChar(String path) throws FileNotFoundException {
		super(path);
		// TODO Auto-generated constructor stub
	}
	
	public ReaderChar(String path,String encod) throws FileNotFoundException{
		super(path);
		this.encod = encod;
	}
	
	
	public ReaderChar(int[] bs) throws FileNotFoundException {
		super(bs);
		// TODO Auto-generated constructor stub
	}
	
	public ReaderChar(int[] bs,String encod) throws FileNotFoundException {
		super(bs);
		this.encod = encod;
		// TODO Auto-generated constructor stub
	}
	
	String encod;
	
	String defaultEncod = "utf8";
	
	public int readChar() throws IOException {
		int b;
		int next = -1;
		if((b=read())!=-1){
			if(next != -1){
				int rst = next;
				next = -1;
				return rst;
			}
			if(b==13){
				next = read();
				if(next == 10){
					return Constant.LINE.charAt(0);
				}
			}
			if(b<127){
				return b;
			}
			if(encod != null && encod.equals("gbk")){
				return new String(new byte[]{(byte)b,(byte)read()},"gbk").charAt(0);
			//utf8
			}else{
				if(utfLen(b)==2){
					return new String(new byte[]{(byte)b,(byte)read()}).charAt(0);
				}else if(utfLen(b)==3){
					return new String(new byte[]{(byte)b,(byte)read(),(byte)read()}).charAt(0);
				}else if(utfLen(b)==4){
					return new String(new byte[]{(byte)b,(byte)read(),(byte)read(),(byte)read()}).charAt(0);
				}
			}
			return b;
		}
		return -1;
	}
	
	public int utfLen(int c){
		c = (byte)c;
//		System.out.println(Integer.toBinaryString(c));
//		System.out.println(Integer.toBinaryString(~c));
		if(~c >= 32){
			return 2;
		}
		if(~c >= 16){
			return 3;
		}
		if(~c >= 8){
			return 4;
		}
		return -1;
	}
	
	public static void main(String[] args) throws IOException {
		ReaderChar mr = new ReaderChar("E:\\project_source\\newcrawl\\autoupdate\\autoupdate.txt","gbk");
		//ArraysOperate.output("李".getBytes());
		int c = 0;
		while((c=mr.readChar())!=-1){
			System.out.print((char)c);
		}
	}
	
}
