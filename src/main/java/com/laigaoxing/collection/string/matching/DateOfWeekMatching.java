package com.laigaoxing.collection.string.matching;

import java.util.Calendar;
import java.util.Date;

/**
 * 日期匹配
 * @author lichicheng
 *
 */
public class DateOfWeekMatching extends AbstractDateColumnMatching{

	int[] weekDays;
	
	@Override
	protected boolean matching(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		for(int i : weekDays){
			int a = i + 1;
			if(a == 8){
				a = 1;
			}
			if(c.get(Calendar.DAY_OF_WEEK) == a)
				return true;
		}
		return false;
	}
	

	/**
	 * 匹配星期几
	 * 1-7对应周一至周日
	 * @param weekDays
	 */
	public DateOfWeekMatching setWeekDays(int... weekDays) {
		this.weekDays = weekDays;
		return this;
	}

}
