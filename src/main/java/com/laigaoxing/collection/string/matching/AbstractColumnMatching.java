package com.laigaoxing.collection.string.matching;

import com.laigaoxing.collection.string.MatchingValve;


/**
 * 多列匹配，即所列都要与之匹配
 * @author lichicheng
 *
 */
public abstract class AbstractColumnMatching implements MatchingValve{
	
	int[] columnIndexes;//多列匹配，该属性与columnIndex选其一
	
	Condition condition = Condition.AND;
	
	@Override
	public boolean matching(String[] line) {

		if(columnIndexes.length ==1){
			return matching(line[columnIndexes[0]-1]);
		}
		
		if(condition == Condition.AND){
			for(int i : columnIndexes){
				if(!matching(line[i-1])){
					return false;
				}
			}
			return true;
		}else{
			for(int i : columnIndexes){
				if(matching(line[i])){
					return true;
				}
			}
			return false;
		}
	}
	
	protected abstract boolean matching(String val);
	
	/**
	 * 设置要进行匹配的列
	 * @param columnIndex
	 */
	public AbstractColumnMatching setColumnIndexes(int... columnIndexes) {
		this.columnIndexes = columnIndexes;
		return this;
	}
	/**
	 * 当设置了columnIndexes[]时，可设置匹配条件 Condition
	 * or 其中某一列满足条件就满足
	 * and 必须所有列满足条件才逄是满足
	 * */
	public AbstractColumnMatching setCondition(Condition condition) {
		this.condition = condition;
		return this;
	}

	public static enum Condition{
		OR,AND
	}
}
