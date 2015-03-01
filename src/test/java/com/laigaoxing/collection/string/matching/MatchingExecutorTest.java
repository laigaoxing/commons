package com.laigaoxing.collection.string.matching;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.laigaoxing.collection.string.MatchingValve;
import com.laigaoxing.collection.string.matching.AbstractColumnMatching.Condition;

public class MatchingExecutorTest {

	List<String[]> lines = new ArrayList<String[]>();
	MatchingValve m1 = new ColumnSizeMatching().setFixNum(6);
	MatchingValve m2 = new DateOfWeekMatching().setWeekDays(3).
			setDateFormat("yyyy-MM-dd").setColumnIndexes(5);
	MatchingValve m3 = new RangeNumValMatching().setMinNum(13.0).setColumnIndexes(1);
	
	@Before public void buildData(){
		lines.add(new String[]{"2013-02-01","19.0","20","1.2","jeff"});
		lines.add(new String[]{"2014-03-03","18.4","21","0.8","kuiking"});
		lines.add(new String[]{"2015-03-01","18.5","20.88","0.12","jeff"});
		lines.add(new String[]{"1990-02-24","17","21.02","0.8","jeff"});
		lines.add(new String[]{"1997-01-05","16.4","11","0.3","jeff",""});
		lines.add(new String[]{"2009-10-30","17","17.9","0.7","kuiking"});
	}
	
	@Test public void isFilter() throws Exception{
		MatchingExecutor me = new MatchingExecutor();
		
		Method method = me.getClass().getDeclaredMethod("isFilter", String[].class);
		String[] l = new String[]{"12","jeff","nothing","12.4","2013-01-02","三台县一小后坝子"};
		method.setAccessible(true);
		me.addMatching(m1).addMatching(m2);
		Object o = method.invoke(me, (Object)l);
		Assert.assertEquals(true, o);
		
		me.addMatching(m3);
		o = method.invoke(me, (Object)l);
		Assert.assertEquals(true, o);
		
		me.setCondition(Condition.AND);
		o = method.invoke(me, (Object)l);
		Assert.assertEquals(false, o);
		
	}
	
	@Test public void matching(){
		MatchingExecutor me = new MatchingExecutor();
		me.addMatching(m1);
		List<String[]> list = me.matching(lines);
		Assert.assertEquals(list.size(), 5);
		System.out.println(list.size());
	}
}
