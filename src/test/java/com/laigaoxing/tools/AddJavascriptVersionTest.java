package com.laigaoxing.tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;

import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import com.laigaoxing.common.StringHelper;
import com.laigaoxing.common.TxtFileOperate;
import com.laigaoxing.tools.AddJavascriptVersion.Execute;

public class AddJavascriptVersionTest {

	@Test public void updateWithStr() throws Exception{
		AddJavascriptVersion.Execute execute = new AddJavascriptVersion.Execute();
		
		Class cls = Execute.class;
		Method method = cls.getDeclaredMethod("update", String.class);
		method.setAccessible(true);
		String html = TxtFileOperate.getTxt(AddJavascriptVersion.class.getResourceAsStream("textJsp"),"utf8");
		
		html = (String)method.invoke(execute, html);
		System.out.println(AddJavascriptVersion.class.getResource("textJsp").getPath());
		String s= StringHelper.line(html, 7);
		Assert.assertTrue(s.startsWith("<SCRIPT type=\"text/javascript\" src=\"/jquery.js?r="));
		s = StringHelper.line(html, 9);
		Assert.assertTrue(s.matches("<script src=\"http://www.163.com/123.js\\?r=\\d+\"></script>"));
		s = StringHelper.line(html, 10);
		Assert.assertTrue(s.matches("<script type=\"text/javascript\" src=\"<%=path%>/jquery.js\\?r=\\d+\"></script>"));
		s = StringHelper.line(html, 11);
		Assert.assertTrue(s.matches("<script type=\"text/javascript\" src=\"text.js\\?r=\\d+\"/>"));
		System.out.println(html);
	}
}
