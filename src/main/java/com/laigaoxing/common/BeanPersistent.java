package com.laigaoxing.common;

import java.beans.Beans;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.MethodUtils;

import com.laigaoxing.test.pojo.Student;

/**
 * bean持久化
 * 保存于文件
 * 类的全名，get参数，http请求参数
 * @author lichicheng
 *
 */
public class BeanPersistent {
	
	String savePath = "";
	
	public static <T> String save(List<T> list){
		
		return "";
	}
	
	public static <T> String save(T obj){
		return "";
	}
	
	public static <T> List<T> getListBean(String id,Class<T> cls){
		return new ArrayList<T>();
	}
	
	public static <T> T getBean(String id,Class<T> cls){
		Object obj = null;
		
		return (T)obj;
	}
	
	public static void main(String[] args) {
		Student stu = new Student();
		stu.setAge(12);
	}
}
