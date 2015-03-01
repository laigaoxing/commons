package com.laigaoxing.collection.string.update.single;

import java.math.BigDecimal;
import java.util.regex.Matcher;

import org.apache.commons.lang.StringUtils;
import org.nfunk.jep.JEP;

import com.laigaoxing.collection.string.update.AbstractColumnUpdate;
import com.laigaoxing.common.regex.StringUpdate;

/**
 * 列计算
 * @author lichicheng
 *
 */
public class CountUpdate extends AbstractColumnUpdate{
	
	String expression;
	
	int decimalPlace=2;
	
	private JEP jep = new JEP();
	
	/**
	 * 算术表达示，支持/*+-  
	 * 注意：
	 * 与其它列进行运算用[index]表示，本字段用[this]表示
	 * 例：修改本列值为第2列的值除以10，表达示为：[2]/10
	 * @param expression
	 */
	public CountUpdate setExpression(String expression) {
		this.expression = expression;
		return this;
	}
	
	@Override
	protected String update(final String[] line, int columnIndex) {
		if(StringUtils.isBlank(expression)){
			throw new RuntimeException("expression不能为空");
		}
		String expVal = expression.replace("[this]", line[columnIndex-1]);
		expVal = new StringUpdate() {
			
			@Override
			public String regex() {
				return "\\[([0-9]+)\\]";
			}
			
			@Override
			public String dispose(Matcher m) {
				int index = Integer.valueOf(m.group(1));
				if("".equals(line[index-1])){
					return "0.0";
				}
				return line[index-1];
			}
		}.replaceText(expVal);
		jep.parseExpression(expVal);
		return new BigDecimal(jep.getValue()).divide(new BigDecimal(1), decimalPlace, BigDecimal.ROUND_HALF_UP).doubleValue()+"";
	}

	public CountUpdate setDecimalPlace(int decimalPlace) {
		this.decimalPlace = decimalPlace;
		return this;
	}

	
}
