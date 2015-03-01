package com.laigaoxing.collection.string.matching;

import java.util.Date;
import org.apache.log4j.Logger;

/**
 * 列时间类型范围匹配
 * @author lichicheng
 *
 */
public class RangeDateValMatching extends AbstractDateColumnMatching{
	
	Logger log = Logger.getLogger(RangeDateValMatching.class);
	
	public RangeDateValMatching(String dateFormat){
		super.dateFormat = dateFormat;
	}
	
	Date minDate;//取最小时间值
	
	Date maxDate;//取最大时间值

	@Override
	protected boolean matching(Date thisDate) {
		if(minDate == null && maxDate == null){
			throw new RuntimeException("RangeDateValMatching:参数minDate与maxDate必须传入一项");
		}
		if(maxDate != null){
			if(thisDate.getTime() > maxDate.getTime()){
				return false;
			}
		}
		if(minDate != null){
			if(thisDate.getTime() < minDate.getTime()){
				return false;
			}
		}
		return true;
	}

	public RangeDateValMatching setMinDate(Date minDate) {
		this.minDate = minDate;
		return this;
	}

	public RangeDateValMatching setMaxDate(Date maxDate) {
		this.maxDate = maxDate;
		return this;
	}


}
