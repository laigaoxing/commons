package com.laigaoxing.collection.string.update.multiple;

import java.util.List;

import com.laigaoxing.collection.string.UpdateMultiple;

/**
 * 过滤重复行
 * 发现重复行时，取第一条发现在行
 * @author lichicheng
 *
 */
public class FilterRepeatUpdate implements UpdateMultiple{

	int[] columnIndexes;//判断重复列索引

	@Override
	public List<String[]> execute(List<String[]> data) {
		
		return null;
	}
	
}
