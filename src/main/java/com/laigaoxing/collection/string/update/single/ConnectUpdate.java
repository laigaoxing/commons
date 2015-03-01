package com.laigaoxing.collection.string.update.single;

import java.util.regex.Matcher;
import com.laigaoxing.collection.string.update.AbstractColumnUpdate;
import com.laigaoxing.common.regex.StringUpdate;

/**
 * 字符连接修改，可针对于指定某个字符，或其它列
 * @author lichicheng
 *
 */
public class ConnectUpdate extends AbstractColumnUpdate{

	String expression;
	
	@Override
	protected String update(final String[] line, int columnIndex) {
		String rst = new StringUpdate() {
			
			@Override
			public String regex() {
				return "\\[([0-9]+)\\]";
			}
			
			@Override
			public String dispose(Matcher m) {
				int index = Integer.valueOf(m.group(1));
				return line[index-1];
			}
		}.replaceText(expression);
		rst = rst.replace("[this]", line[columnIndex-1]);
		return rst;
	}
	

	/**
	 * 连接表达式
	 * 注意：
	 * 与其它列拼接用[index]表示，本字段用[this]表示
	 * ['203','jeek','12','35']
	 * 例：[2]qq，则表示第二列的值拼接'qq'，即修改成对应列的值为jeekqq
	 * @param expression
	 */
	public ConnectUpdate setExpression(String expression) {
		this.expression = expression;
		return this;
	}

	
}
