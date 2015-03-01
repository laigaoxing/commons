package com.laigaoxing.collection.string.update;

import junit.framework.Assert;

import org.junit.Test;

import com.laigaoxing.collection.string.update.AbstractColumnUpdate;

public class AbstractColumnUpdateTest {
	@Test public void execute(){
		AbstractColumnUpdate abstractMCU = new AbstractColumnUpdate() {
			
			@Override
			protected String update(String[] line, int columnIndex) {
				return line[columnIndex-1]+"1";
			}
			
		};
		String[] l = new String[]{"jeek","001","25"};
		abstractMCU.setColumnIndexes(1,2);
		String[] tmp = abstractMCU.execute(l);
		Assert.assertEquals(tmp[0], "jeek1");
		Assert.assertEquals(tmp[1], "0011");
		

		abstractMCU.setColumnIndexes(3);
		tmp = abstractMCU.execute(l);
		Assert.assertEquals(tmp[2], "251");
		
		abstractMCU.setAppend(true);
		abstractMCU.setColumnIndexes(2,3);
		tmp =abstractMCU.execute(l);
		Assert.assertEquals(tmp.length, 5);
		Assert.assertEquals(tmp[3], "0011");
	}
	
	
	
}
