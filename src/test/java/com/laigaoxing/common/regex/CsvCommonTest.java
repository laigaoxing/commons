package com.laigaoxing.common.regex;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.laigaoxing.common.CsvCommon;

public class CsvCommonTest {

	@Test public void t() throws IOException{
		CsvCommon csvCommon = CsvCommon.newInstance(CsvCommon.class.getResourceAsStream("/a.csv"),"gbk");
		
		List<String[]> list = csvCommon.asList();
		
		for(String[] l : list){
			System.out.println(Arrays.toString(l));
		}
	}
	
}
