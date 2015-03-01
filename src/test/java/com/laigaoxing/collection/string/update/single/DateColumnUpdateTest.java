package com.laigaoxing.collection.string.update.single;

import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

import junit.framework.Assert;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;

public class DateColumnUpdateTest {
	
	DateColumnUpdate dcu = new DateColumnUpdate();
	
	@Test public void execute(){
		String[] line = new String[]{"2012","2014/12/23"};
		dcu.setColumnIndexes(2);
		dcu.setDateFormat(new String[]{"yyyy/MM/dd"});
		dcu.setToDateForamt("yy-MM-dd");
		String[] tmp = dcu.execute(line);
		Assert.assertEquals(tmp[1], "14-12-23");
		
		
		line = new String[]{"Jan 5, 2015","12.5"};
		dcu.setColumnIndexes(1);
		dcu.setLocale(Locale.ENGLISH);
		dcu.addDateFormat("MMM d, yyyy");
		tmp = dcu.execute(line);
		Assert.assertEquals(tmp[0], "15-01-05");
		
	}
	
}
