package com.laigaoxing.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.laigaoxing.common.FolderOperate;
import com.laigaoxing.common.RegexOperate;
import com.laigaoxing.common.TextUpdate;

/**
 * 查找jsp页面中的javascript引用，添加一个新的版本号
 * @author lichicheng
 *
 */
public class AddJavascriptVersion {
	
	private String sourcePath;
	private String saveAsPath;
	private boolean isCover;
	private String[] filterJs;
	
	public void execute() throws IOException{
		//查找所有jsp
		List<File> flist = FolderOperate.searchFile(sourcePath, ".*?jsp$",true);
		AddJavascriptVersion.Execute exe = new Execute();
		exe.setFilterJs(filterJs);
		exe.setCover(isCover);
		if(StringUtils.isNotBlank(saveAsPath)){
			exe.setSavePath(saveAsPath);
		}
		exe.setRootPath(sourcePath);
		//单张页面查找script引用，并加上一个新的标记
		for(File f : flist){
			exe.update(f);
		}
	}
	
	public static class Execute {
		
		final Logger log = LoggerFactory.getLogger(Execute.class);
		String rootPath;//查找到的要修改的文件最顶层目录
		String savePath;
		String[] filterJs=new String[0];
		static String flag = "";
		String encode="utf8";
		boolean isCover=true;
		RegexOperate ro1 = RegexOperate.getInstance("<script.*?((</script>)|(/>))", Pattern.CASE_INSENSITIVE);
		final RegexOperate ro2 = RegexOperate.getInstance("src=['\"]([^'\"]*)['\"]", Pattern.CASE_INSENSITIVE);
		private boolean isFilter(String fpath){
			for(String s :filterJs){
				if(fpath.matches(".*?"+s+"$")){
					return true;
				}
			}
			return false;
		}
		
		static{
			flag = Calendar.getInstance().getTimeInMillis()+"";
		}
		public void setSavePath(String savePath) {
			File f = new File(savePath);
			this.savePath = f.getPath();
		}
		public void setEncode(String encode){
			this.encode = encode;
		}
		public void setFilterJs(String[] filterJs) {
			this.filterJs = filterJs;
		}
		public void setCover(boolean isCover) {
			this.isCover = isCover;
		}
		public void setRootPath(String rootPath) {
			File f = new File(rootPath);
			this.rootPath = f.getPath();
		}
		private String update(String content){
			
			

			content = ro1.replaceText(content, new TextUpdate() {

				@Override
				public String execute(Matcher m) {
					String scriptTxt = m.group();
					System.out.println(m.group());
					scriptTxt = ro2.replaceText(scriptTxt, new TextUpdate() {

						@Override
						public String execute(Matcher m) {
							String src = m.group(1);
							// 删除已有标记
							src = src.replaceAll("\\?.*$", "");
							// 非过滤js
							if (!isFilter(src)) {
								log.info(src);
								src = src + "?r=" + flag;
							}
							return String.format("src=\"%s\"", src);
						}
					});
					return scriptTxt;
				}

			});
			return content;
		}
		
		private void update(final File file) throws UnsupportedEncodingException, FileNotFoundException, IOException{
			
			
			String content = IOUtils
					.toString(new FileInputStream(file), encode);
			content=this.update(content);
			OutputStreamWriter osw = null;
			if (isCover) {
				osw = new OutputStreamWriter(new FileOutputStream(
						file.getPath()), "utf8");
			} else {
				if (StringUtils.isBlank(savePath)) {
					throw new RuntimeException("当设置cover属性为false时，必须设置保存路径。");
				}
				String appendPath = file.getPath().replace(this.rootPath, "");
				String path = savePath + appendPath;
				FolderOperate.createFolder(path);
				File outFile = new File(path);
				osw = new OutputStreamWriter(new FileOutputStream(outFile),
						"utf8");
			}
			IOUtils.write(content, osw);
			osw.close();
		}
	}


	public String getSourcePath() {
		return sourcePath;
	}

	public void setSourcePath(String sourcePath) {
		this.sourcePath = sourcePath;
	}

	public String getSaveAsPath() {
		return saveAsPath;
	}

	public void setSaveAsPath(String saveAsPath) {
		this.saveAsPath = saveAsPath;
	}

	public boolean isCover() {
		return isCover;
	}

	public void setCover(boolean isCover) {
		this.isCover = isCover;
	}

	public String[] getFilterJs() {
		return filterJs;
	}

	public void setFilterJs(String[] filterJs) {
		this.filterJs = filterJs;
	}
	
	
}
