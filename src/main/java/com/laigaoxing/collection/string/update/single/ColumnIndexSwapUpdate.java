package com.laigaoxing.collection.string.update.single;

import com.laigaoxing.collection.string.UpdateSingle;

/**
 * 列之间值的交换
 * @author lichicheng
 *
 */
public class ColumnIndexSwapUpdate implements UpdateSingle{

	private int[] newIndexes;//新的索引值
	
	private int[] swapIndexes;//交换列参数
	
	@Override
	public String[] execute(String[] line) {
		String[] newLine = null;
		if(newIndexes != null){
			if(newIndexes.length != line.length){
				throw new RuntimeException("长度与实际列数不一致");
			}
			newLine = new String[line.length];
			for(int i=0;i<line.length;i++){
				newLine[i] = line[newIndexes[i]-1];
			}
		}else if(swapIndexes != null){
			if(swapIndexes.length %2 !=0){
				throw new RuntimeException("swapIndexes属性长度必需为偶数");
			}
			newLine = line.clone();
			for(int i=0;i<swapIndexes.length;i+=2){
				String tmp = newLine[swapIndexes[i]-1];
				newLine[swapIndexes[i]-1] = newLine[swapIndexes[i+1]-1];
				newLine[swapIndexes[i+1]-1] =  tmp;
			}
		}
		return newLine;
	}
	
	/**
	 * 设置新的数组顺序索引
	 * 注：长度必需与列长度一致
	 * @param newIndexes
	 */
	public ColumnIndexSwapUpdate setNewIndexes(int[] newIndexes) {
		this.newIndexes = newIndexes;
		return this;
	}

	/**
	 * 奇数位索引与偶数位索引对应的值交换
	 * 该参数个数一定为偶数
	 * 注：如newIndexes不为空，则设置该参数无效
	 * 例：
	 * val = ["12","jeek","34","上海"]
	 * swapIndexes = [1,3,2,4]
	 * 最后输出结果为["34","12","上海","jeek"]
	 * @param newIndexes
	 */
	public ColumnIndexSwapUpdate setSwapIndexes(int... swapIndexes) {
		this.swapIndexes = swapIndexes;
		return this;
	}

}
