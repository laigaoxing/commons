package com.laigaoxing.collection.string.matching;

import junit.framework.Assert;
import org.junit.Test;
import com.laigaoxing.collection.string.matching.AbstractColumnMatching.Condition;

public class AbstractMultipleColumnMatchingTest {
	
	AbstractColumnMatching abstractMCM = new AbstractColumnMatching(){

		@Override
		protected boolean matching(String val) {		
			return val.matches("[0-9]*(\\.[0-9])?");
		}
		
	};
	
	@Test public void matching(){
		String[] line = new String[]{"12","jeek","12.4","中国"};
		abstractMCM.setColumnIndexes(1,3);
		boolean b = abstractMCM.matching(line);
		Assert.assertEquals(b, true);
		
		abstractMCM.setCondition(Condition.OR);
		abstractMCM.setColumnIndexes(1,2);
		b = abstractMCM.matching(line);
		Assert.assertEquals(b, true);
		
		abstractMCM.setCondition(Condition.AND);
		b = abstractMCM.matching(line);
		Assert.assertEquals(b, false);
		
		abstractMCM.setColumnIndexes(1);
		b = abstractMCM.matching(line);
		Assert.assertEquals(b, true);
	}
	
	public static void main(String[] args) {
		System.out.println(isNum("12.4"));
		System.out.println(isNum("12..4"));
		System.out.println(isNum("1.2.4"));
		System.out.println(isNum("124."));
		System.out.println(isNum(".124"));
	}
	
	public static boolean isNum(String val){
		char tmp = 0;
		boolean first = false;
		for(char c : val.toCharArray()){
			if(c == '.'){
				if(first){
					return false;
				}
				//连续或第一次出现.号
				if(tmp == '.' || tmp == 0){
					return false;
				}
				//最后一个字符为.号
				if(val.charAt(val.length()-1) == '.'){
					return false;
				}
				first = true;
			}else if(c < '0' || c > '9'){
				return false;
			}
			tmp = c;
		}
		return true;
	}
}
