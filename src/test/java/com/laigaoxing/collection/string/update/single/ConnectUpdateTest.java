package com.laigaoxing.collection.string.update.single;

import org.junit.Test;
import junit.framework.Assert;
import com.laigaoxing.collection.string.update.single.ConnectUpdate;

public class ConnectUpdateTest {

	ConnectUpdate cu = new ConnectUpdate();
	
	@Test public void update(){
		String[] line = new String[]{"17","kui","23","军属大院"};
		cu.setExpression("name:[2]");
		cu.setColumnIndexes(2);
		String[] s = cu.execute(line);
		Assert.assertEquals(s[1], "name:kui");
		cu.setColumnIndexes(new int[]{1,3});
		cu.setExpression("no:[this]");
		s = cu.execute(line);
		Assert.assertEquals(s[0], "no:17");
		Assert.assertEquals(s[2], "no:23");
	}
}
