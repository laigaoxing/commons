package com.laigaoxing.test;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;

import com.laigaoxing.common.RegexOperate;
import com.laigaoxing.common.TextUpdate;
import com.laigaoxing.common.TxtFileOperate;

public class TxtRegexTest {
	
	public static void main(String[] args) throws IOException {
		String file = TxtFileOperate.getText("C:\\Users\\jeff\\Desktop\\company.log.2013-07-02");
		List<String> list = RegexOperate.getInstance(".*?INFO").searchText(file, new TextUpdate() {
			
			@Override
			public String execute(Matcher m) {
				System.out.println(m.group());
				return null;
			}
		});
	}
	
}
