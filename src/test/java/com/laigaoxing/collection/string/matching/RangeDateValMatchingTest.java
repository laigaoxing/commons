package com.laigaoxing.collection.string.matching;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import junit.framework.Assert;

import org.junit.Test;

public class RangeDateValMatchingTest {
	
	@Test public void maching() throws ParseException{
		RangeDateValMatching rm = new RangeDateValMatching("yyyy/MM/dd");
		Date tmpDate = new SimpleDateFormat("yyyy/MM/dd").parse("2013/12/29");
		rm.setColumnIndexes(5);
		Calendar maxTimer = Calendar.getInstance();
		maxTimer.set(2014, 0, 1, 0, 0, 0);
		//maxDate
		rm.setMaxDate(maxTimer.getTime());
		boolean b = rm.matching(tmpDate);
		Assert.assertEquals(b, true);
		maxTimer.set(Calendar.YEAR,2013);
		rm.setMaxDate(maxTimer.getTime());
		b = rm.matching(tmpDate);
		Assert.assertEquals(b, false);

		//minDate
		rm.setMaxDate(null);
		
		Calendar minTimer = Calendar.getInstance();
		minTimer.set(2014, 0, 1, 0, 0, 0);
		rm.setMinDate(minTimer.getTime());
		b = rm.matching(tmpDate);
		Assert.assertEquals(b, false);
		
		minTimer.set(2013, 11, 1, 0, 0, 0);
		rm.setMinDate(minTimer.getTime());
		b = rm.matching(tmpDate);
		Assert.assertEquals(b, true);
		
		//minDate与maxDate
		
		minTimer.set(2014, 0, 1);
		rm.setMinDate(minTimer.getTime());
		
		maxTimer.set(2014, 11, 31);
		rm.setMaxDate(maxTimer.getTime());
		b = rm.matching(tmpDate);
		Assert.assertEquals(b, false);
		
		minTimer.set(2013, 4, 4);
		rm.setMinDate(minTimer.getTime());
		b = rm.matching(tmpDate);
		Assert.assertEquals(b, true);
		
		
		
	}
	
}
