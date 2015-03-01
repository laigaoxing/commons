package com.laigaoxing.collection.string.matching;

import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

public class AbstractDateColumnMatchingTest {
	
	AbstractDateColumnMatching abstractDCM = new AbstractDateColumnMatching() {
		
		@Override
		protected boolean matching(Date date) {
			Calendar curr = Calendar.getInstance();
			curr.set(Calendar.YEAR, 2000);
			return curr.getTimeInMillis() > date.getTime();
		}
	};
	
	@Test public void t(){
		abstractDCM.setDateFormat("yyyy-MM-dd");
		boolean b= abstractDCM.matching("2012-03-02");
		Assert.assertEquals(b, false);
		b= abstractDCM.matching("1999-03-02");
		Assert.assertEquals(b, true);
	}
}
