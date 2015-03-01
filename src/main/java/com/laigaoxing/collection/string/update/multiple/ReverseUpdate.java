package com.laigaoxing.collection.string.update.multiple;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.laigaoxing.collection.string.UpdateMultiple;

public class ReverseUpdate implements UpdateMultiple{

	@Override
	public List<String[]> execute(List<String[]> data) {
		List<String[]> rst = new ArrayList<String[]>();
		Collections.reverse(rst);
		return rst;
	}

}
