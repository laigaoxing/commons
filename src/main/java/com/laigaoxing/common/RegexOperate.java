package com.laigaoxing.common;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

public class RegexOperate {

	
	Logger logger = Logger.getLogger(this.getClass());
	
	private Pattern p;

	private RegexOperate(Pattern p) {
		this.p = p;
	}

	public static RegexOperate getInstance(String rx) {
		Pattern p = Pattern.compile(rx);
		return new RegexOperate(p);
	}

	public static RegexOperate getInstance(String rx, int model) {
		Pattern p = Pattern.compile(rx, model);
		return new RegexOperate(p);
	}

	/** 找首尾空格 */
	public static final String REP_SPACE = "^\\s*|\\s$";
	/** 小数 */
	public static final String DECIMAL = "-?\\d+(.\\d+)?";

	/**
	 * 替换找到的文本
	 * 
	 * @param source
	 *            原文本
	 * @param txUpdate
	 *            替换实现类
	 * @return
	 */
	public String replaceText(String source, TextUpdate txUpdate) {
		StringBuffer sb = new StringBuffer();
		Matcher m = p.matcher(source);
		while (m.find()) {
			logger.debug("find txt:".concat(m.group()));
			String tmp = txUpdate.execute(m);
			logger.debug("replace result:".concat(tmp));
			m.appendReplacement(sb,tmp.replace("$", "\\$"));
		}
		m.appendTail(sb);
		return sb.toString();
	}

	/**
	 * 查找文本内容
	 * 
	 * @param source
	 *            原文本
	 * 
	 * @return
	 */
	public List<String> searchText(String source, TextUpdate txUpdate) {
		List<String> ls = new ArrayList<String>();
		Matcher m = p.matcher(source);
		while (m.find() ) {
			ls.add(txUpdate.execute(m));
		}
		return ls;
	}

	public void searchText(String source, TxtResult tr) {
		Matcher m = p.matcher(source);
		while (m.find()) {
			String[] rst = new String[m.groupCount() + 1];
			for(int i=0;i<m.groupCount()+1;i++){
				rst[i] = m.group(i);
			}
			tr.returnRst(rst);
			logger.debug("find txt:".concat(m.group()));
		}
	}
	
	
}
