package com.laigaoxing.collection.string;

import java.util.List;

/**
 * csv多行修改
 * @author lichicheng
 *
 */
public interface UpdateMultiple {

	/**
	 * 得到新的csv数据
	 * @param data csv数据
	 * @return
	 */
	public List<String[]> execute(List<String[]> data);
	
}
