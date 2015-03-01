package com.laigaoxing.collection.string;

import java.util.ArrayList;
import java.util.List;

import com.laigaoxing.collection.string.matching.MatchingExecutor;

/**
 * 执行修改
 * 
 * @author lichicheng
 * 
 */
public class ExecutorUpdate {

	List<MatchingExecutor> matching;
	
	List<UpdateSingle> updateSingle;
	
	List<UpdateMultiple> updateMultiple;
	
	/**
	 *	数据处理执行顺序标识
	 *	value：flag,index
	 *	flag：1.matching 2.updateSingle 3.updateMultiple
	 *	index：对应的索引值
	 */
	private List<String> processSequence = new ArrayList<String>();

	public List<String[]> execute(List<String[]> lines) {
		List<String[]>  rst = new ArrayList<String[]>();
		rst.addAll(lines);
		for(String val : processSequence){
			String[] conf = val.split(",");
			if("1".equals(conf[0])){
				MatchingExecutor me = matching.get(Integer.valueOf(conf[1]));
				rst = me.matching(rst);
			}else if("2".equals(conf[0])){
				UpdateSingle us = updateSingle.get(Integer.valueOf(conf[1]));
				for(int i=0;i<rst.size();i++){
					rst.set(i, us.execute(rst.get(i)));
				}
			}else if("3".endsWith(conf[0])){
				UpdateMultiple um = updateMultiple.get(Integer.valueOf(conf[1]));
				rst = um.execute(rst);
			}
		}
		return rst;
	}
	
	private void addProcessFlag(int flag){
		int num = 0;
		if(flag == 1){
			if(matching != null){
				num = matching.size();
			}
		}else if(flag == 2){
			if(updateSingle != null){
				num = updateSingle.size();
			}
		}else if(flag == 3){
			if(updateMultiple != null){
				num = updateMultiple.size();
			}
		}
		processSequence.add(String.format("%s,%s", flag, num));
	}

	public ExecutorUpdate addUpdateMultiple(UpdateMultiple updateMultiple){
		addProcessFlag(3);
		if(this.updateMultiple == null){
			this.updateMultiple = new ArrayList<UpdateMultiple>();
		}
		this.updateMultiple.add(updateMultiple);
		return this;
	}
	
	public ExecutorUpdate addUpdateSingle(UpdateSingle updateSingle) {
		addProcessFlag(2);
		if (this.updateSingle == null) {
			this.updateSingle = new ArrayList<UpdateSingle>();
		}
		this.updateSingle.add(updateSingle);
		return this;
	}
	
	public ExecutorUpdate addMatching(MatchingValve matchingValve,Boolean filter) {
		addProcessFlag(1);
		if (matching == null) {
			matching = new ArrayList<MatchingExecutor>();
		}
		matching.add(new MatchingExecutor(matchingValve, filter));
		return this;
	}
	
	public ExecutorUpdate addMatching(MatchingValve matchingValve) {
		addMatching(matchingValve,true);
		return this;
	}
	
	public void clear(){
		processSequence.clear();
		if(matching != null)
			matching.clear();
		if(updateSingle != null)
			updateSingle.clear();
		if(updateMultiple != null)
		updateMultiple.clear();
	}
	
	/**
	 * 得到当前处理类数量
	 * @return
	 */
	public int processCount(){
		return processSequence.size();
	}

}
