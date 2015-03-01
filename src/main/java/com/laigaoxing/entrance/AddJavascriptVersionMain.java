package com.laigaoxing.entrance;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.commons.lang.StringUtils;

import com.laigaoxing.tools.AddJavascriptVersion;

public class AddJavascriptVersionMain {

	public static void main(String[] args) throws IOException {
		Properties pr = new Properties();
		pr.load(new FileInputStream(System.getProperty("user.dir") + "/mark_resource_version.properties"));
		AddJavascriptVersion exe = new AddJavascriptVersion();
		if(StringUtils.isNotBlank(pr.getProperty("save_as_path"))){
			exe.setSaveAsPath(pr.getProperty("save_as_path"));
		}
		exe.setSourcePath(pr.getProperty("source_path"));
		exe.setFilterJs(pr.getProperty("filter_js").split(","));
		exe.setCover(false);
		if("1".equals(pr.getProperty("cover"))){
			exe.setCover(true);
		}
		exe.execute();
		
	}
	
}
