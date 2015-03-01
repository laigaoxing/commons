package com.laigaoxing.collection.string.update.single;

import junit.framework.Assert;

import org.junit.Test;

import com.laigaoxing.collection.string.update.single.CountUpdate;

public class CountUpdateTest {

	CountUpdate cu = new CountUpdate();
	
	@Test public void update(){
		String[] line = new String[]{"120","jeff","100","89","32"};
		cu.setColumnIndexes(1);
		cu.setExpression("[3]+[4]+[5]");
		String[] tmp = cu.execute(line);
		Assert.assertEquals(tmp[0], "221.0");
		tmp = cu.execute(line);
		Assert.assertEquals(tmp[0], "221.0");
		
		cu.setExpression("[this]-[3]");
		tmp = cu.execute(line);
		Assert.assertEquals(tmp[0], "20.0");
		tmp = cu.execute(line);
		Assert.assertEquals(tmp[0], "20.0");
		
		line = new String[]{"12","4.4","12.33","73.21"};
		cu.setDecimalPlace(1);
		cu.setExpression("[1]-[3]").setColumnIndexes(1);
		tmp = cu.execute(line);
		Assert.assertEquals(tmp[0], "-0.3");
	}
	
}
