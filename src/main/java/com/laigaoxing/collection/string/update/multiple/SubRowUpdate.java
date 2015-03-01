package com.laigaoxing.collection.string.update.multiple;

import java.util.List;
import com.laigaoxing.collection.string.UpdateMultiple;

/**
 * 截取数据
 * @author lichicheng
 *
 */
public class SubRowUpdate implements UpdateMultiple{

	int start=0;//从第几位开始截取
	int end=0;//截取几条数据，当为负数时，表示一直截取到倒数第几位
	int len;//取几条数据
	
	@Override
	public List<String[]> execute(List<String[]> data) {
		if(start != 0 && end == 0){
			data = data.subList(start, data.size());
		}
		if(end != 0){
			if(end < 0){
				end = data.size() + end;
			}
			data = data.subList(start, end);
		}

		if(len != 0){
			data = data.subList(0, len);
		}
		return data;
	}

	public SubRowUpdate setStart(int start) {
		this.start = start;
		return this;
	}

	public SubRowUpdate setEnd(int end) {
		this.end = end;
		return this;
	}

	public SubRowUpdate setLen(int len) {
		this.len = len;
		return this;
	}
	
}
