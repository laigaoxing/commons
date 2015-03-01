package com.laigaoxing.common.regex;

import junit.framework.Assert;

import org.junit.Test;

import com.laigaoxing.common.TxtResult;

public class SearchTxtTest {
	
	String txt = "abc123kkk 12 01385 北京市\nkeirkdwe 23 032812 西安市";
	
	@Test public void find(){
		String s = SearchTxt.find(txt, "\\s\\d\\d\\s");
		Assert.assertEquals(" 12 ", s);

	}
	
	@Test public void findWithGroup(){
		String s = SearchTxt.find(txt, "\\s(\\d\\d)\\s", 1);
		Assert.assertEquals("12", s);
	}
	
	@Test public void finds(){
		String[] s = SearchTxt.finds(txt, "([^\\s]*)\\s([^\\s]*)\\s([^\\s]*)");
		Assert.assertEquals(s.length, 4);
		Assert.assertEquals(s[3], "01385");
	}
	
	@Test public void exe(){
		final String[] r = new String[]{"12","12","01","38","23","03","28","12"};
		SearchTxt.exe(txt, "\\d\\d", new TxtResult() {
			int i=0;
			@Override
			public void returnRst(String[] m) {
				Assert.assertEquals(m[0], r[i]);
				i++;
			}
			
		});
		
	}
	
}
