package com.ami.statistics.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.ami.api.common.AppConstant;
import com.ami.api.common.Pager;
import com.ami.api.exception.APIException;
import com.ami.common.HttpClientUtil;
import com.ami.common.TimeUtil;
import com.ami.statistics.dao.StatisticsDao;
import com.ami.statistics.pojo.THumanInfoSimple2VO;
import com.ami.statistics.pojo.THumanInfoSimpleVO;

@Component
public class StatisticsService {
	private static Logger logger = Logger.getLogger(StatisticsService.class);
	@Autowired
	private StatisticsDao statisticsDao;
	
	public  List<THumanInfoSimpleVO> levelAndTotalTime(HttpServletRequest req) {
		
		return statisticsDao.levelAndTotalTime(req);
	}

	public List<THumanInfoSimple2VO> totalTimeNum(HttpServletRequest req) {
		return statisticsDao.totalTimeNum(req);
	}

	public Map<String,Long>  humanSpendTimeOnSlot(Map<String, Object> params,String preTableName) throws APIException {
		String startStr = (String)params.get("dateStr");
		Date start = TimeUtil.formatStrTODate(startStr);
		long startLong = start.getTime();
		//组装sql语句
		String	sql = "select "
					+ " slot_type as slotType,"
					+ " slot_id as slotId ,"
					+ " char_id as chartId,"
					+ " in_time as inTime"
					+ " from texas_log."+preTableName+"_"+startStr;
		String sql2 = " select id,langDesc from texas_activity.t_slot_name ";
		Map<Integer,String> nameMap = new HashMap<Integer,String>();
		List<HashMap<String, Object>> resultName = statisticsDao.queryData(sql2);
		for(int i=0;i<resultName.size();i++){
			Long id = (Long)resultName.get(i).get("id");
			String name = (String)resultName.get(i).get("langDesc");
			nameMap.put(id.intValue(), name);
		}
		List<HashMap<String, Object>> slotChartList = statisticsDao.queryData(sql);
		if(slotChartList == null || slotChartList.size() == 0){
			return null;
		}
		//第一步 再拆 以 slotId 为基准
		Map<Integer,List<HashMap<String,Object>>> slotMap = new HashMap<Integer,List<HashMap<String,Object>>>();
		List<Integer> slotIdList = new ArrayList<Integer>();
		int slotId = 0;
		for(HashMap<String,Object> slotChart:slotChartList){
			Long slotIdIn = (Long)slotChart.get("slotId");
			List<HashMap<String,Object>> temp = null;
			if(slotIdIn != slotId){
				slotId = slotIdIn.intValue();
				temp = new ArrayList<HashMap<String,Object>>();
				temp.add(slotChart);
				slotMap.put(slotId, temp);
				slotIdList.add(slotIdIn.intValue());
			}else{
				slotMap.get(slotId).add(slotChart);
			}
		}
		//第二步 再拆  以 chartId 为准
		Map<String,Long> slotTimes = new HashMap<String,Long>();
		for(int x = 0;x<slotIdList.size();x++){
			List<HashMap<String,Object>> chartList = slotMap.get(slotIdList.get(x));
			Map<Integer,List<HashMap<String,Object>>> chartMap = new HashMap<Integer,List<HashMap<String,Object>>>();
			List<Integer> chartIdList = new ArrayList<Integer>();
			int chartId = 0;
			for(HashMap<String,Object> chart:chartList){
				Long chartIdIn = (Long)chart.get("chartId");
				List<HashMap<String,Object>> temp = null;
				if(chartIdIn != chartId){
					chartId = chartIdIn.intValue();
					temp = new ArrayList<HashMap<String,Object>>();
					temp.add(chart);
					chartMap.put(chartId, temp);
					chartIdList.add(chartIdIn.intValue());
				}else{
					chartMap.get(chartId).add(chart);
				}
			}
			//一台老虎机 多个人
			Long time = 0l;
			for(int y=0;y<chartIdList.size();y++){
				List<HashMap<String,Object>> oneChartList = chartMap.get(chartIdList.get(y));
				//最后一步计算 (一个老虎机 一个人 玩了多少次 计算在这个老虎机的总时间)
				for(int m=0;m<oneChartList.size();m++){
					if(m == oneChartList.size()-1){
						break;
					}
					HashMap<String,Object> preNext = oneChartList.get(m);
					HashMap<String,Object> next = oneChartList.get(m+1);
					long preInTime = (Long)preNext.get("inTime");
					long nextInTime = (Long)next.get("inTime");
					long tempTime = nextInTime-preInTime;
					//如果 差值小于 10分钟，就算数，否则 不算数
					if(tempTime <= 10*60*1000){
						logger.info(tempTime/(1000)+"--intime_pre:"+preInTime+"-----intime_next:"+nextInTime);
						time += tempTime;
					}
				}
			}
			slotTimes.put(nameMap.get(slotIdList.get(x)),time/(60*1000)>0?time/(60*1000):0);
		}
		
		return slotTimes;
	}

	public Pager order(Pager pager, String channelId,String charId,String name) {
		return statisticsDao.order(pager,channelId,charId,name);
	}

	public JSONObject online(Pager pager, String channelId, String account,
			String username) {
		String url = AppConstant.SERVER_URL_HTTP+"api/online";
		HashMap<String,String> params = new HashMap<String,String>();
		params.put("channelId", channelId);
		params.put("account", account);
		params.put("username", username);
		String limit = String.valueOf(pager.getLimit());
		String start = String.valueOf(pager.getStart());
		params.put("start", start);
		params.put("limit", limit);
		
		String jsonStr = HttpClientUtil.postUrl(url,params);
		if(StringUtils.isNotBlank(jsonStr)){
			JSONObject jb = JSONObject.parseObject(jsonStr);
			return jb;
		}
		return null;
	}
}
