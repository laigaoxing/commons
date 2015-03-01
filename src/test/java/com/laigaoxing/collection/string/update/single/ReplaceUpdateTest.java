package com.laigaoxing.collection.string.update.single;

import junit.framework.Assert;

import org.junit.Test;

public class ReplaceUpdateTest {
	ReplaceUpdate ru = new ReplaceUpdate();
	
	@Test public void execute(){
		String[] l = new String[]{"12","jeek","中关 12","24"};
		ru.setReplace("\\s+", "-");
		ru.setColumnIndexes(3);
		String[] tmp = ru.execute(l);
		Assert.assertEquals(tmp[2], "中关-12");
		
	}
}
