package com.laigaoxing.collection.string.matching;

import java.util.ArrayList;
import java.util.List;

import com.laigaoxing.collection.string.MatchingValve;
import com.laigaoxing.collection.string.matching.AbstractColumnMatching.Condition;

/**
 * 匹配类配置执行
 * @author lichicheng
 *
 */
public class MatchingExecutor {
	public MatchingExecutor(){}
	
	public MatchingExecutor(MatchingValve matchingLine){
		this.matching = new ArrayList<MatchingValve>();
		this.matching.add(matchingLine);
	}
	
	public MatchingExecutor(MatchingValve matchingLine,Boolean filter){
		this(matchingLine);
		this.filter = filter;
	}
	
	List<MatchingValve> matching;
	boolean filter = true;//过滤与选择标识，为true时，过滤匹配数据，等于false时，过滤非匹配数据
	Condition condition = Condition.OR;//是否需要所有匹配器都满足条件
	public List<MatchingValve> getMatching() {
		return matching;
	}
	public void setMatching(List<MatchingValve> matching) {
		this.matching = matching;
	}
	public MatchingExecutor addMatching(MatchingValve matchingLine){
		if(this.matching == null){
			this.matching = new ArrayList<MatchingValve>();
		}
		this.matching.add(matchingLine);
		return this;
	}
	
	public void setFilter(boolean filter) {
		this.filter = filter;
	}
	
	private boolean isFilter(String[] line){
		if (matching == null) {
			throw new RuntimeException("matching不能为null");
		}
		int matchingNum = matching.size();
		int i = 0;
		boolean tmpRst = false;
		if (condition == Condition.OR) {
			for (; i < matching.size(); i++) {
				if (matching.get(i).matching(line)) {
					tmpRst = true;
					matchingNum--;
					break;
				}
			}
		}else {
			for (; i < matching.size(); i++) {
				if (matching.get(i).matching(line)) {
					matchingNum--;
				}else{
					tmpRst = false;
					break;
				}
			}
			if (matchingNum == 0) {
				tmpRst = true;
			}
		}
		if(filter){
			return tmpRst;
		}else{
			return !tmpRst;
		}
	}
	
	public List<String[]> matching(List<String[]> lines){
		List<String[]> rst = new ArrayList<String[]>(lines.size());
		for(String[] line : lines){
			if(!this.isFilter(line)){
				rst.add(line);
			}
		}
		return rst;
	}

	/**
	 * 所有匹配关联 
	 * Condition.OR(默认值，一个匹配器满足则通过)
	 * Condition.AND(所有匹配器满足才通过)
	 * @param condition
	 */
	public void setCondition(Condition condition) {
		this.condition = condition;
	}
	
}
