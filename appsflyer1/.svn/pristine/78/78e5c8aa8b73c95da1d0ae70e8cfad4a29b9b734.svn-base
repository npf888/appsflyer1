package com.ami.mail.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ami.api.utill.DateTools;
import com.ami.mail.pojo.RemainVO;
import com.ami.texas.log.service.LogService;
/**
 * 留存数据
 * @author JavaServer
 *
 */
@Service
public class RemainService {
	@Autowired
	private  LogService logService;
	
	public   List<RemainVO> getRemainVOList(){
    	
    	Map<String, Object> params = new HashMap<String,Object>();
		params.put("start", DateTools.addDate(System.currentTimeMillis(), -30, DateTools.DAY, "yyyy_MM_dd"));
		params.put("end", DateTools.getCurrentDate("yyyy_MM_dd"));
    	//查询符合时间规则的数据表
		try{
			List<HashMap<String, Object>> date_list = logService.queryTablesDate("player_keep_log");
	    	List<HashMap<String, Object>> list = logService.retained(params, date_list);
	    	List<RemainVO> xlsx = new ArrayList<RemainVO>();
	    	DecimalFormat    df   = new DecimalFormat("######0.00");   
	    	for(HashMap<String, Object> map:list){
	    		RemainVO remainVO = new RemainVO();
	    		remainVO.setDate((String)map.get("date"));
	    		long newGuy = (long)map.get("1");
	    		remainVO.setNewGuy(newGuy+"");
	    		if(map.get("2")!=null){
	    			long t = (long)map.get("2");
	    			double tr = (double)t/(double)newGuy;
	    			String sts = df.format(tr);
	    			remainVO.setDay2Remain(sts);
	    		}else{
	    			remainVO.setDay2Remain("0");
	    		}
	    		
	    		if(map.get("3")!=null){
	    			long t = (long)map.get("3");
	    			double tr = (double)t/(double)newGuy;
	    			String sts = df.format(tr);
	    			remainVO.setDay3Remain(sts);
	    		}else{
	    			remainVO.setDay3Remain("0");
	    		}
	    		
	    		if(map.get("4")!=null){
	    			long t = (long)map.get("4");
	    			double tr = (double)t/(double)newGuy;
	    			String sts = df.format(tr);
	    			remainVO.setDay4Remain(sts);
	    		}else{
	    			remainVO.setDay4Remain("0");
	    		}
	    		
	    		if(map.get("5")!=null){
	    			long t = (long)map.get("5");
	    			double tr = (double)t/(double)newGuy;
	    			String sts = df.format(tr);
	    			remainVO.setDay5Remain(sts);
	    		}else{
	    			remainVO.setDay5Remain("0");
	    		}
	    		
	    		if(map.get("6")!=null){
	    			long t = (long)map.get("6");
	    			double tr = (double)t/(double)newGuy;
	    			String sts = df.format(tr);
	    			remainVO.setDay7Remain(sts);
	    		}else{
	    			remainVO.setDay7Remain("0");
	    		}
	    		
	    		if(map.get("7")!=null){
	    			long t = (long)map.get("7");
	    			double tr = (double)t/(double)newGuy;
	    			String sts = df.format(tr);
	    			remainVO.setDay10Remain(sts);
	    		}else{
	    			remainVO.setDay10Remain("0");
	    		}
	    		
	    		if(map.get("8")!=null){
	    			long t = (long)map.get("8");
	    			double tr = (double)t/(double)newGuy;
	    			String sts = df.format(tr);
	    			remainVO.setDay15Remain(sts);
	    		}else{
	    			remainVO.setDay15Remain("0");
	    		}
	    		if(map.get("9")!=null){
	    			long t = (long)map.get("9");
	    			double tr = (double)t/(double)newGuy;
	    			String sts = df.format(tr);
	    			remainVO.setDay30Remain(sts);
	    		}else{
	    			remainVO.setDay30Remain("0");
	    		}
	    		
	    		xlsx.add(remainVO);
	    	}
	    	return xlsx;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
}
