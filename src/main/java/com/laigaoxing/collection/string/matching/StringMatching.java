package com.laigaoxing.collection.string.matching;

import org.apache.commons.lang.StringUtils;

/**
 * 普通字符匹配
 * @author lichicheng
 *
 */
public class StringMatching extends AbstractColumnMatching {

	String val;

	String regex;
	
	@Override
	protected boolean matching(String val) {
		if(val == null || regex == null){
			throw new RuntimeException("val或regex属性必须设置一项");
		}
		if(StringUtils.isNotBlank(val)){
			if(this.val.equals(val)){
				return true;
			}
		}
		if(StringUtils.isNotBlank(regex)){
			if(this.val.matches(this.regex)){
				return true;
			}
		}
		return false;
	}

	public StringMatching setVal(String val) {
		this.val = val;
		return this;
	}
	
	/**
	 * 设置正则匹配，调用String.matches()
	 * @param regex
	 */
	public StringMatching setRegex(String regex) {
		this.regex = regex;
		return this;
	}
	
}