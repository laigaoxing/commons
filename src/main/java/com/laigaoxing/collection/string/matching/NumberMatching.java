package com.laigaoxing.collection.string.matching;

/**
 * 数字匹配
 * @author lichicheng
 *
 */
public class NumberMatching extends AbstractColumnMatching{

	double num = 0.0;
	
	@Override
	protected boolean matching(String val) {
		Double d = Double.valueOf(val);
		return d == num;
	}

	public NumberMatching setNum(double num) {
		this.num = num;
		return this;
	}

}
