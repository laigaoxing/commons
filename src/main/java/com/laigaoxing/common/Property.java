package com.laigaoxing.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

public class Property {

	Logger logger = Logger.getLogger(this.getClass());
	
	static Map<String, String> params = new HashMap<String, String>();

	private static Property pt = null;

	public Property() {

	}

	public void init() {
		InputStream in = this.getClass().getResourceAsStream(
				"/properties/config.txt");
		try {
			String txt = TxtFileOperate.getTxt(in, "utf-8");
			String[] ts = txt.split("[\\r\\n]+");
			for (String s : ts) {
				if(s.charAt(0) == '#'){
					continue;
				}
				logger.debug("loading params ".concat(s.split("=")[0]).concat(",")
						.concat("value ").concat(s.split("=")[1]));
				params.put(s.split("=")[0], s.split("=")[1]);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static String getProperty(String key) {
		if (pt == null) {
			pt = new Property();
			pt.init();
		}
		return params.get(key);
	}

	public static void main(String[] args) {
		String s = Property.getProperty("home");
		System.out.println(s);
	}
}
