package com.laigaoxing.collection.string.update.multiple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

public class RowColumnIntersectionUpdateTest {
	@Test public void t(){
		RowColumnIntersectionUpdate rci = new RowColumnIntersectionUpdate();
		List<String[]> data = new ArrayList<String[]>();
		data.add(new String[]{"no","a","b","c","d"});
		data.add(new String[]{"1","date","china","jpan","us"});
		data.add(new String[]{"2","07","1.2","1.1","3"});
		data.add(new String[]{"3","08","32","22","1"});
		data.add(new String[]{"4","09","32","23","1"});
		rci.setColumnIndex(1);
		rci.setBeginRow(1);
		List<String[]> rst = rci.execute(data);
		Assert.assertEquals(Arrays.toString(rst.get(0)), "[1, a, date]");
		
		rci.setColumnIndex(2);
		rci.setBeginRow(2);
		rst = rci.execute(data);
		Assert.assertEquals(Arrays.toString(rst.get(0)), "[07, china, 1.2]");
		Assert.assertEquals(Arrays.toString(rst.get(3)), "[08, china, 32]");
		Assert.assertEquals(Arrays.toString(rst.get(rst.size()-1)), "[09, us, 1]");
	}
}
