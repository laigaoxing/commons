package com.laigaoxing.test;

import org.junit.Test;

import com.laigaoxing.common.ArraysOperate;

public class TestArraysOperate {
	@Test
	public void objArraysTest(){
		Integer[] ints = new Integer[]{4,2,4};
		ArraysOperate.output(ints);
		String s= ArraysOperate.outputStr(ints,"-");
		System.out.println(s);
	}
}
