/**   
 * @Title: TestFolderOperate.java 
 * @Package com.laigaoxing.test 
 * @Description: TODO
 * @author jeff  
 * @date 2012-2-15 下午5:09:47 
 * @version V1.0
 * @Copyright (c) MaiShi 2012 
 */
package com.laigaoxing.test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import com.laigaoxing.common.FolderOperate;
import com.laigaoxing.common.TxtFileOperate;

/**
 * @ClassName: TestFolderOperate
 * @Description: TODO
 */
public class TestFolderOperate {
	@Test
	public void test() throws IOException {
		List<File> ls = FolderOperate.searchFile("C:\\Users\\jeff\\Desktop\\history_data", "xml", false);
		for (File s : ls) {
			System.out.println(s.toString());
		}
	}

	@Test public void createFolderTest() throws IOException{
//		String path  ="e:\\user\\galloping\\hehe1\\123.txt";
//		FolderOperate.createFolder(path);
//		File f = new File(TxtFileOperate.getFilePath(path));
//		Assert.assertTrue(f.exists());
	}
	
	
}
