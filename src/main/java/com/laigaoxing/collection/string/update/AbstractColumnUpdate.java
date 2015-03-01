package com.laigaoxing.collection.string.update;

import com.laigaoxing.collection.string.UpdateSingle;

/**
 * 多列修改String[]
 * @author lichicheng
 *
 */
public abstract class AbstractColumnUpdate implements UpdateSingle{
	
	int[] columnIndexes;
	
	boolean append = false;
	
	/**
	 * 修改数据
	 * @param line
	 * @return 对应columnIndexes索引修改后的值
	 */
	protected abstract String update(String[] line,int columnIndex);

	@Override
	public String[] execute(String[] line) {
		if(append == true){
			String[] newLine = new String[line.length + columnIndexes.length];
			System.arraycopy(line, 0, newLine, 0, line.length);
			for(int i=0;i<columnIndexes.length;i++){
				newLine[line.length+i] = update(line, columnIndexes[i]);
			}
			return newLine;
		}else{
			String[] tmp = line.clone();
			for(int i=0;i<columnIndexes.length;i++){
				tmp[columnIndexes[i]-1] =  update(tmp, columnIndexes[i]);
			}
			return tmp;
		}
	}

	public AbstractColumnUpdate setColumnIndexes(int... columnIndexes) {
		this.columnIndexes = columnIndexes;
		return this;
	}
	
	public AbstractColumnUpdate setAppend(boolean append){
		this.append = append;
		return this;
	}
}
