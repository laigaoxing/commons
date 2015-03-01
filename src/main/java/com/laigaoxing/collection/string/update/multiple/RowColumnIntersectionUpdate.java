package com.laigaoxing.collection.string.update.multiple;

import java.util.ArrayList;
import java.util.List;

import com.laigaoxing.collection.string.UpdateMultiple;

/**
 * 得到行与列交集的值
 * 1.近回长度为开始行的列数
 * 2.String[]长度为3，[0]：列的值[1]：行的值：[2]：列与行的相交值
 * 如参数:
 * [[no	,a		,b		,c		,d],
 * 	[1	,date	,china	,jpan	,us],
 * 	[2	,07		,1.2	,1.1	,3],
 * 	[3	,08		,32		,22		,1],
 * 	[4	,09		,32		,23		,1]]
 * 
 *  beginRow:1
 *  columnIndex:1
 *  结果：
 * [[1,a,date], [1,b,china], [1,c,jpan], [1,d,us],
 * 	[2,a,07], [2,b,1.2], [2,c,1.1], [2,d,3],
 * 	[3,a,08], [3,b,32], [3,c,22], [3,d,1],
 * 	[4,a,09], [4,b,32], [4,c,23], [4,d,1]]
 * 
 * 	beginRow:2
 *  columnIndex:2
 * 	结果：
 * [[07,china,1.2],	[07,jpan,1.1], [07,us,3],
 * 	[08,china,32], [08,jpan,22], [08,us,1],
 * 	[09,china,32], [09,jpan,23], [09,us,1]]
 * @author lichicheng
 *
 */
public class RowColumnIntersectionUpdate implements UpdateMultiple{

	int beginRow=1;//相交行
	
	int columnIndex=1;//相交列
	
	@Override
	public List<String[]> execute(List<String[]> data) {
		List<String[]> rst = new ArrayList<String[]>();
		for(int i=beginRow;i<data.size();i++){
			for(int j=columnIndex;j<data.get(i-1).length;j++){
				String[] newLine = new String[3];
				newLine[0] = data.get(i)[columnIndex-1];
				newLine[1] = data.get(beginRow-1)[j];
				newLine[2] = data.get(i)[j];
				rst.add(newLine);
			}
		}
		return rst;
	}

	public int getBeginRow() {
		return beginRow;
	}

	public RowColumnIntersectionUpdate setBeginRow(int beginRow) {
		this.beginRow = beginRow;
		return this;
	}

	public int getColumnIndex() {
		return columnIndex;
	}

	public RowColumnIntersectionUpdate setColumnIndex(int columnIndex) {
		this.columnIndex = columnIndex;
		return this;
	}
	
}
