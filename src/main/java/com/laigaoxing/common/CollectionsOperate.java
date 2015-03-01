package com.laigaoxing.common;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
/**
 * 集合操作类
 * 
 * @author jeff
 * 
 */
public class CollectionsOperate {
	private static String lineSeparator = java.security.AccessController
			.doPrivileged(new sun.security.action.GetPropertyAction(
					"line.separator"));

	private static String defaultSeparate = " ";

	public static int output(Collection<?> args) {
		return output(args, defaultSeparate, 0);
	}

	public static int output(Collection<?> args, String separate) {
		return output(args, separate, 0);
	}

	public static int output(Collection<?> args, String separate, int lineNo) {
		int rst = 0;
		System.out.print(outputStr(args, separate, lineNo));
		rst = args.size();
		return rst;
	}

	public static String outputStr(Collection<?> args) {
		return outputStr(args, defaultSeparate, 0);
	}

	public static String outputStr(Collection<?> args, String separate) {
		return outputStr(args, separate, 0);
	}

	public static String outputStr(Collection<?> args, String separate, int lineNo) {
		StringBuffer sb = new StringBuffer();
		int i = 0;
		for (Object c : args) {
			sb.append(c);
			i++;
			if (args.size() != i
					&& ((lineNo != 0 && i % lineNo != 0) || lineNo == 0)) {
				sb.append(separate);
			}
			if (lineNo != 0 && i % lineNo == 0 && args.size() != i) {
				sb.append(outputLineStr());
			}
		}
		sb.append(outputLineStr());
		return sb.toString();
	}

	
	public static <T> String outputStrForPojo(Collection<T> args,Map<String,String> filterColumn) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		T t = args.iterator().next();
		Class<?> cls = t.getClass();
		Method[] mths = searchMethod(cls, filterColumn);
		StringBuffer sb = new StringBuffer();
		for (Object c : args) {
			for(Method m : mths){
				try{
					sb.append("["+m.getName() + "=" + m.invoke(c) + "]");
				}catch(NullPointerException e){
					sb.append(t.getClass() + " is null.");
					break;
				}
			}
			sb.append(outputLineStr());
		}
		sb.append(outputLineStr());
		return sb.toString();
	}
	/**
	 * 过滤掉方法
	 * @param cls
	 * @param filterMethod
	 * @return
	 */
	private static Method[] searchMethod(Class<?> cls,Map<String,String> filterMethod){
		Method[] all = cls.getDeclaredMethods();
		Method[] rst = new Method[all.length];
		int i=0;
		int j=0;
		for(Method m : all){
			if(m.getName().matches("get.*")){
				rst[i] = all[j];
				i++;
			}
			j++;
		}
		rst = Arrays.copyOfRange(rst, 0, i);
		return rst;
	}
	
	private static String outputLineStr() {
		return lineSeparator;
	}
	
}
