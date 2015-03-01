package com.laigaoxing.common;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FolderOperate extends FileCommon{

	private static FolderOperate fo = new FolderOperate();

	private FolderOperate() {
	}

	public static List<File> searchFile(String path, String regex, boolean repeat) {
		List<File> ls = new ArrayList<File>();
		List<File> folders = fo.getFolders(path);
		if (repeat)
			for (File f : folders) {
				if (f.isDirectory()) {
					ls.addAll(searchFile(f.getPath(), regex, repeat));
				}
			}
		ls.addAll(fo.getFiles(path, regex));
		return ls;
	}
	
	public static List<File> searchFile(String path, String regex) {
		List<File> ls = new ArrayList<File>();
		ls.addAll(fo.getFiles(path, regex));
		return ls;
	}

	private List<File> getFolders(String path) {
		File file = new File(path);
		List<File> ls = new ArrayList<File>();
		File[] fs = file.listFiles();
		Collections.addAll(ls, fs);
		return ls;
	}

	private List<File> getFiles(String path, final String regex) {
		List<File> ls = new ArrayList<File>();
		File file = new File(path);
		File[] fs = file.listFiles(new FileFilter() {

			@Override
			public boolean accept(File pathname) {
				return pathname.getName().matches(regex);
			}

		});
		Collections.addAll(ls, fs);
		return ls;
	}

	

}
