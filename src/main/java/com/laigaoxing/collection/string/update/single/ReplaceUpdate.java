package com.laigaoxing.collection.string.update.single;

import com.laigaoxing.collection.string.update.AbstractColumnUpdate;

/**
 * 列值替换
 * @author lichicheng
 *
 */
public class ReplaceUpdate extends AbstractColumnUpdate{

	String matchingRegex;
	
	String repVal;
	
	@Override
	protected String update(String[] line, int columnIndex) {
		return line[columnIndex-1].replaceAll(matchingRegex, repVal);
	}

	public ReplaceUpdate setMatchingRegex(String matchingRegex) {
		this.matchingRegex = matchingRegex;
		return this;
	}


	public ReplaceUpdate setRepVal(String repVal) {
		this.repVal = repVal;
		return this;
	}
	
	/**
	 * 设置替换属性
	 * @param repRgx 匹配正则 
	 * @param repVal 替换值
	 */
	public ReplaceUpdate setReplace(String matchingRgx,String repVal){
		this.matchingRegex = matchingRgx;
		this.repVal = repVal;
		return this;
	}

}
