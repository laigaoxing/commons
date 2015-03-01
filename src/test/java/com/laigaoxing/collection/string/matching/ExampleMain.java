package com.laigaoxing.collection.string.matching;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import org.nfunk.jep.JEP;

import com.laigaoxing.collection.string.MatchingValve;

public class ExampleMain {
	public static void main(String[] args) {
		DecimalFormat df = new DecimalFormat("#.##");
		double d= 1.33+0.33;
		System.out.println(d);
		System.out.println(df.format(d));
		System.out.println(new BigDecimal(1.1).divide(new BigDecimal(1),2,BigDecimal.ROUND_HALF_UP).doubleValue());
		System.out.println(12.00);
	}
	
	public void t1(){

		String[] l = new String[]{"178","2013/12","0.8","9"};
		List<MatchingValve> matchings = new ArrayList<MatchingValve>();
		RangeNumValMatching rvMatch1 = new RangeNumValMatching();
		rvMatch1.setColumnIndexes(3);
		rvMatch1.setMinNum(0.9);
		matchings.add(rvMatch1);
		RangeNumValMatching rvMatch2 = new RangeNumValMatching();
		rvMatch2.setColumnIndexes(4);
		rvMatch2.setMinNum(7.0);
		matchings.add(rvMatch2);
		for(MatchingValve ml : matchings){
			if(!ml.matching(l)){
				System.out.println("未匹配成功");
				break;
			}
		}
		
	
	}
}
