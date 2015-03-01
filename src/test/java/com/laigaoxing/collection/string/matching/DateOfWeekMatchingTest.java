package com.laigaoxing.collection.string.matching;

import java.util.Calendar;

import junit.framework.Assert;

import org.junit.Test;

public class DateOfWeekMatchingTest {
	
	DateOfWeekMatching dm = new DateOfWeekMatching();
	
	@Test public void matching(){
		Calendar curr = Calendar.getInstance();
		curr.set(2014, 11, 11, 0, 0, 0);
		dm.setWeekDays(4);
		boolean b = dm.matching(curr.getTime());
		Assert.assertEquals(b, true);
		
		curr.set(2014, 11,12);
		b = dm.matching(curr.getTime());
		Assert.assertEquals(b, false);
		
		curr.set(2014, 11, 14);
		dm.setWeekDays(7);
		b = dm.matching(curr.getTime());
		Assert.assertEquals(b, true);
	}
	
	
}
