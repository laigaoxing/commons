package com.laigaoxing.collection.string.matching;


/**
 * 列数值类型范围匹配
 * @author lichicheng
 *
 */
public class RangeNumValMatching extends AbstractColumnMatching{

	Double minNum;//最小值
	
	Double maxNum;//最大值
	
	@Override
	public boolean matching(String val) {
		Double tmp = Double.valueOf(val);
		if(minNum == null && maxNum==null){
			throw new RuntimeException("minNum与maxNum属性并需设置一项");
		}
		if(minNum != null){
			if(tmp < minNum ){
				return false;
			}
		}
		if(maxNum != null){
			if(tmp > maxNum){
				return false;
			}
		}
		
		return true;
	}

	public RangeNumValMatching setMinNum(Double minNum) {
		this.minNum = minNum;
		return this;
	}

	public RangeNumValMatching setMaxNum(Double maxNum) {
		this.maxNum = maxNum;
		return this;
	}


}
