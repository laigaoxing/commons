package com.laigaoxing.collection.string;

/**
 * csv行的匹配
 * @author lichicheng
 *
 */
public interface MatchingValve {
	
	/**
	 * 匹配行
	 * @param line 
	 * @return true:成功匹配  false:不匹配
	 */
	public boolean matching(String[] line);
	
}
