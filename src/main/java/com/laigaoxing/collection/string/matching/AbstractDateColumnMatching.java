package com.laigaoxing.collection.string.matching;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

public abstract class AbstractDateColumnMatching extends AbstractColumnMatching{

	Logger log = Logger.getLogger(AbstractDateColumnMatching.class);
	
	//列时间格式化字符
	String dateFormat;

	@Override
	protected boolean matching(String val) {
		try {
			Date thisDate = new SimpleDateFormat(this.dateFormat).parse(val);
			return this.matching(thisDate);
		} catch (ParseException e) {
			log.error(String.format("时间格式化错误:%s,format:%s", val,dateFormat));
			throw new RuntimeException(e.getMessage());
		}
	}
	
	/**
	 * 对应列时间值
	 * @param date
	 * @return
	 */
	protected abstract boolean matching(Date date);

	public AbstractDateColumnMatching setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
		return this;
	}
	
}
