package com.laigaoxing.common.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class InputStreamArray extends InputStream {
	
	int[] bs;

	public InputStreamArray(int[] bs) {
		this.bs = bs;
	}

	public InputStreamArray(String path) throws FileNotFoundException {
		this.rs = new FileInputStream(path);
	}

	int i=-1;
	private FileInputStream rs;

	@Override
	public int read() throws IOException {
		if(rs == null){
			i++;
			return bs.length > i ? bs[i] : -1;
			
		}
		return rs.read();
	}

}
