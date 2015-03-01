/**   
 * @Title: TestHolidayChecker.java 
 * @Package com.laigaoxing.test 
 * @Description: TODO
 * @author jeff  
 * @date 2012-2-14 下午7:42:31 
 * @version V1.0
 * @Copyright (c) MaiShi 2012 
 */
package com.laigaoxing.test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.Test;
import com.laigaoxing.common.TxtFileOperate;

/**
 * @ClassName: TestHolidayChecker
 * @Description: TODO
 */
public class TestHolidayChecker {

	public static String convertStr(List<Calendar> lc, int startYear,
			int endYear) {
		int flagyear = startYear;
		int  flagS = 1;
		StringBuffer sb = new StringBuffer();
		Calendar c = Calendar.getInstance();
		c.set(startYear, 0, 1);
		Calendar e = Calendar.getInstance();
		e.set(endYear + 1, 0, 1);
		e.add(Calendar.DATE, -1);
		HashSet<String> hcalen = new HashSet<String>();
		for (Calendar c1 : lc) {
			hcalen.add(new SimpleDateFormat("yyyyMMdd").format(c1.getTime()));
		}
		
		sb.append("{");
		while (true) {
			if( c.get(Calendar.YEAR) > flagyear){
				if(flagS % 2 == 1){
					sb.append("}\r\n{");
				}else{
					sb.append("}\r\n{");
				}
				flagyear = c.get(Calendar.YEAR);
				flagS++;
				
			}
			if (hcalen.contains(new SimpleDateFormat("yyyyMMdd").format(c.getTime()))) {
				sb.append("\'H\'");
			} else {
				sb.append("\'W\'");
			}
			sb.append(",");
			
			c.add(Calendar.DATE, 1);
			if (c.getTimeInMillis() >= e.getTimeInMillis()) {
				break;
			}
		}
		sb.append("}");
		return sb.toString();
	}
}
