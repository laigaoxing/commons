package com.laigaoxing.common.regex;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import com.laigaoxing.common.RegexOperate;
import com.laigaoxing.common.TextUpdate;

/**
 * 具体修改接口
 * @author jeff
 *
 */
public abstract class StringResult {
	
	/**
	 * 正则找到的字符串
	 * @param m
	 * @return
	 */
	public abstract String dispose(Matcher m);
	
	/**
	 * 搜索字符的正则
	 * @return
	 */
	public abstract String regex();
	
	public String replaceText(String rst) throws IOException{
		RegexOperate rx = RegexOperate.getInstance(regex());
		rst = rx.replaceText(rst, new TextUpdate() {
			
			@Override
			public String execute(Matcher arg0) {
				return dispose(arg0);
			}
			
		});
		return rst;
	}
	
	public List<String> searchText(String rst) throws IOException{
		RegexOperate rx = RegexOperate.getInstance(regex());
		return rx.searchText(rst, new TextUpdate() {
			
			@Override
			public String execute(Matcher arg0) {
				return dispose(arg0);
			}
			
		});
	}
}
