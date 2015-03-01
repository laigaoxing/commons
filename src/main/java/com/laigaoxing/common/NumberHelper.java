package com.laigaoxing.common;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class NumberHelper {

	//TODO
	public static String formatDouble(double d,String fmt){
		return null;
	}
	
	/**
	 * 只要是数字，或者去掉空格、结尾%后是数字，就转换
	 * 
	 * @param src
	 * @return 数字
	 */
	public static String convertToDecimal(String src) {
		if (null == src || "".equals(src)) {
			return "";
		}

		int len = src.length();
		String last = src.substring(len - 1);// 最后一个字符
		String str = src;

		if ("%".equals(last)) {// 如果以 % 结尾，截取掉
			str = src.substring(0, len - 1);
		}
		str = str.trim();

		BigDecimal decimal = null;
		try {
			decimal = new BigDecimal(str);
		} catch (NumberFormatException e) {
			return src;// 不能转换为数字，返回原字符串
		}

		decimal = decimal.divide(new BigDecimal(100));
		return decimal.toString();
	}

	/**
	 * 严格转换，只有以%号结尾的才转换
	 * 
	 * @param src
	 * @return
	 */
	public static String convertStrictlyToDecimal(String src) {
		if (null == src || "".equals(src)) {
			return "";
		}

		int len = src.length();
		String last = src.substring(len - 1);// 最后一个字符
		String str = src;

		if ("%".equals(last)) {// 如果以 % 结尾，截取掉
			str = src.substring(0, len - 1);
		} else {
			return src;
		}
		str = str.trim();

		BigDecimal decimal = null;
		try {
			decimal = new BigDecimal(str);
		} catch (NumberFormatException e) {
			return src;// 不能转换为数字，返回原字符串
		}

		decimal = decimal.divide(new BigDecimal(100));
		return decimal.toString();
	}

	/**
	 * 转化成百分比
	 * 
	 * @param src
	 * @return 不能转换为数字则返回原字符串，否则返回乘 100 后的字符串
	 */
	public static String convertToPercent(String src) {
		if (null == src || "".equals(src)) {
			return "";
		}

		BigDecimal decimal = null;
		try {
			decimal = new BigDecimal(src);
		} catch (NumberFormatException e) {
			return src;// 不能转换为数字，返回原字符串
		}

		decimal = decimal.multiply(new BigDecimal(100));
		return decimal.toString();
	}

	/**
	 * 判断字符串是不是数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumber(String str) {
		boolean result = false;
		if (null == str) {
			return result;
		}
		str = str.trim();
		try {
			new BigDecimal(str);
			result = true;// 可以转换
		} catch (NumberFormatException e) {
		}
		return result;
	}
	public static double avg(double... args){
		double sum = 0.0;
		for(double arg : args){
			sum += arg;
		}
		sum = sum / args.length;
		return Double.valueOf(new java.text.DecimalFormat("0.0000").format(sum));
	}
	
	/**
	 * 去掉百分号
	 * 
	 * @param src
	 * @return
	 */
	public static String removePercentSign(String src) {
		if (null == src || "".equals(src)) {
			return "";
		}

		int len = src.length();
		String last = src.substring(len - 1);// 最后一个字符
		String str = src;

		if ("%".equals(last)) {// 如果以 % 结尾，截取掉
			str = src.substring(0, len - 1);
		}
		return str.trim();
	}
	
	public static void main(String[] args) {
		double a = 0.33*1.3;
		System.out.println(a);
		System.out.println(Math.round(a));
		System.out.println(Math.rint(0.265));
		System.out.println(new BigDecimal(a).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
		System.out.println(Math.PI);
		System.out.println(new DecimalFormat("#.##").format(Math.PI));
	}
}
