package com.laigaoxing.collection.string.update.single;

import junit.framework.Assert;

import org.junit.Test;

public class ColumnIndexSwapUpdateTest {

	@Test public void execute(){
		ColumnIndexSwapUpdate cisu = new ColumnIndexSwapUpdate();
		String[] line = new String[]{"1a","2b","3c","4d","5e","6f","7g"};
		cisu.setNewIndexes(new int[]{4,2,3,1,7,6,5});
		String[] tmp = cisu.execute(line);
		Assert.assertEquals(tmp[0], "4d");
		Assert.assertEquals(tmp[6], "5e");
		cisu.setNewIndexes(null);
		cisu.setSwapIndexes(new int[]{4,7,1,2});
		tmp = cisu.execute(line);
		Assert.assertEquals(tmp[0], "2b");
		Assert.assertEquals(tmp[1], "1a");
		
		Assert.assertEquals(tmp[3], "7g");
		Assert.assertEquals(tmp[6], "4d");
	}
	
}
