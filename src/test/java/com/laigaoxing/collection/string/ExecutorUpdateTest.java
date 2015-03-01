package com.laigaoxing.collection.string;

import java.util.ArrayList;
import java.util.List;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import com.laigaoxing.collection.string.ExecutorUpdate;
import com.laigaoxing.collection.string.MatchingValve;
import com.laigaoxing.collection.string.UpdateMultiple;
import com.laigaoxing.collection.string.UpdateSingle;
import com.laigaoxing.collection.string.matching.ColumnSizeMatching;
import com.laigaoxing.collection.string.matching.DateOfWeekMatching;
import com.laigaoxing.collection.string.matching.NumberMatching;
import com.laigaoxing.collection.string.matching.RangeNumValMatching;
import com.laigaoxing.collection.string.update.multiple.RowColumnIntersectionUpdate;
import com.laigaoxing.collection.string.update.multiple.SubRowUpdate;
import com.laigaoxing.collection.string.update.single.ColumnIndexSwapUpdate;
import com.laigaoxing.collection.string.update.single.ConnectUpdate;
import com.laigaoxing.collection.string.update.single.CountUpdate;

public class ExecutorUpdateTest {
	ExecutorUpdate uce = new ExecutorUpdate();
	
	List<String[]> lines = new ArrayList<String[]>();
	MatchingValve m1 = new ColumnSizeMatching().setFixNum(6);
	MatchingValve m2 = new DateOfWeekMatching().setWeekDays(3).
			setDateFormat("yyyy-MM-dd").setColumnIndexes(5);
	MatchingValve m3 = new RangeNumValMatching().setMinNum(13.0).setColumnIndexes(1);
	
	UpdateSingle us1 = new ConnectUpdate().setExpression("[1] 00:00").setColumnIndexes(1);
	UpdateSingle us2 = new ColumnIndexSwapUpdate().setSwapIndexes(1,2);
	UpdateSingle us3 = new CountUpdate().setExpression("[this]+[3]").setAppend(true).setColumnIndexes(2);
	
	UpdateMultiple um1 = new RowColumnIntersectionUpdate();
	UpdateMultiple um2 = new SubRowUpdate().setStart(2);
	
	@Before public void buildData(){
		lines.add(new String[]{"date","a","b","c","d"});
		lines.add(new String[]{"2013-02-01","19.0","20","1.2","22"});//周5
		lines.add(new String[]{"2014-03-03","18.4","21","0.8","12.8"});//周1
		lines.add(new String[]{"2015-03-01","18.5","20.88","0.12","12"});//周7
		lines.add(new String[]{"1990-02-24","17","21.02","0.8","22",""});//周6
		lines.add(new String[]{"1997-01-01","16.4","11","0.3","16"});//周3
		lines.add(new String[]{"2009-10-21","17","17.9","0.7","5"});//周3
	}

	@Test public void execute(){
		//单独处理
		uce.addMatching(m1);
		List<String[]> data = uce.execute(lines);
		Assert.assertEquals(data.size(), lines.size()-1);
		
		uce.clear();
		uce.addUpdateSingle(us1);
		data = uce.execute(lines);
		Assert.assertEquals(data.get(0)[0], "date 00:00");
		Assert.assertEquals(data.get(data.size()-1)[0], "2009-10-21 00:00");
		
		uce.clear();
		uce.addUpdateMultiple(um2);
		data = uce.execute(lines);
		Assert.assertEquals(data.size(), 5);

		//交叉处理，并乱序添加处理流程
		uce.clear();
		uce.addUpdateMultiple(um2);
		uce.addMatching(m1);
		uce.addUpdateSingle(us3);
		((DateOfWeekMatching)m2).setColumnIndexes(1);
		uce.addMatching(m2,false);
		
		data = uce.execute(lines);
		Assert.assertEquals(data.size(), 2);
		Assert.assertEquals(data.get(0)[0], "1997-01-01");
		uce.addMatching(new NumberMatching().setNum(27.4).setColumnIndexes(6));
		data = uce.execute(lines);
		Assert.assertEquals(data.size(), 1);
		Assert.assertEquals(data.get(0)[5], "34.9");
	}
	
}
