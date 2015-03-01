package com.laigaoxing.face;

import org.apache.log4j.Logger;

public class ShowImpl implements ShowInfo {

	Logger log = Logger.getLogger(this.getClass());
	
	@Override
	public void show(String s) {
		log.debug("display str is :".concat(s));
		System.out.println(s);
	}
	

}
