package com.laigaoxing.collection.string.update.single;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;
import com.laigaoxing.collection.string.update.AbstractColumnUpdate;

/**
 * 时间列重新格式化
 * @author lichicheng
 *
 */
public class DateColumnUpdate extends AbstractColumnUpdate{
	
	Logger log = Logger.getLogger(DateColumnUpdate.class);

	Locale locale = Locale.CHINA;
	
	private String format[] = null;
	private String toFormat = null;

	public DateColumnUpdate(String[] dateFormat,String toDateFormat){
		this.format = dateFormat;
		this.toFormat = toDateFormat;
	}
	
	public DateColumnUpdate(String dateFormat,String toDateFormat){
		this.format = new String[]{dateFormat};
		this.toFormat = toDateFormat;
	}
	
	public DateColumnUpdate(){}
	
	@Override
	protected String update(String[] line, int columnIndex) {
		String dateStr = line[columnIndex-1];
		String	rst = convertDate(dateStr, toFormat, locale, format);
		return rst;
	}

	public DateColumnUpdate setDateFormat(String[] dateFormat) {
		this.format = dateFormat;
		return this;
	}
	
	public DateColumnUpdate addDateFormat(String dateFormat) {
		if(this.format == null){
			this.format = new String[]{dateFormat};
		}else{
			String[] r = new String[this.format.length +1];
			System.arraycopy(this.format, 0, r, 0, this.format.length);
			r[r.length-1] = dateFormat;
			this.format = r;
		}
		return this;
	}

	public DateColumnUpdate setToDateForamt(String toDateForamt) {
		this.toFormat = toDateForamt;
		return this;
	}

	public DateColumnUpdate setLocale(Locale locale) {
		this.locale = locale;
		return this;
	}

	String convertDate(String strDate,String format,Locale local,String[] formats){
		Date date = null;
		Locale.setDefault(local);
		try {
			date = DateUtils.parseDate(strDate, formats);
		} catch (ParseException e) {
			log.error(String.format("时间格式化错误：%s", e.getMessage()));
		}
		return DateFormatUtils.format(date, format);
	}
}
