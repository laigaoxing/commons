package com.laigaoxing.common;

import java.util.regex.Matcher;

public interface TextUpdate {
	/**
	 * 更换字符串
	 * @param m Matcher
	 * @return 更换的字符串
	 */
	public String execute(Matcher m);
	
}
