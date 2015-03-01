package com.laigaoxing.collection.string.matching;

import com.laigaoxing.collection.string.MatchingValve;


/**
 * 列数匹配
 * 
 * @author lichicheng
 * 
 */
public class ColumnSizeMatching implements MatchingValve {

	int fixNum;//列数

	@Override
	public boolean matching(String[] line) {
		if (line.length == fixNum) {
			return true;
		}
		return false;
	}

	public ColumnSizeMatching setFixNum(int fixNum) {
		this.fixNum = fixNum;
		return this;
	}

}
