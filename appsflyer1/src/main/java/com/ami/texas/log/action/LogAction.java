package com.ami.texas.log.action;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.ami.api.common.Pager;
import com.ami.api.exception.APIException;
import com.ami.api.utill.DateTools;
import com.ami.api.utill.StringTool;
import com.ami.api.web.action.BaseAction;
import com.ami.common.TimeUtil;
import com.ami.mail.pojo.Country;
import com.ami.mail.pojo.NewPeopleVO;
import com.ami.texas.common.service.UtilService;
import com.ami.texas.common.vo.SlotName;
import com.ami.texas.log.service.LogService;
import com.ami.texas.log.vo.RechargeVO;
import com.ami.texas.log.vo.SlotAgeVO;
import com.ami.texas.log.vo.SlotPayVO;
import com.ami.texas.log.vo.SlotSexVO;
import com.ami.texas.log.vo.SpinDetailVO;
import com.ami.weixin.course.service.LocationService;

/**
 * 报表统计分析
 * 
 * @author zhuweiliang
 * @version [版本号, 2013-5-29]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Scope("prototype")
@Component
public class LogAction extends BaseAction
{
    
    private static final long serialVersionUID = 1L;
    
    private static Logger logger = Logger.getLogger(LogAction.class);
    
    @Autowired
    LogService logService;
    @Autowired
    LocationService locationService;
    @Autowired
    UtilService utilService;

    /**
     * 获取logs
     * 
     * @return
     * @throws Exception
     * @see [类、类#方法、类#成员]
     */
    public String queryLog()
        throws Exception
    {
        logger.debug("query");
        
        String field[] =
            new String[]
            {"start", "end", "date", "type", "account_id"};
        
        Map<String, Object> params = this.reqToMap(field);

        this.set("params", params);
            
        // 选择的日期
        String date = params.get("date").toString(); 
        if(date.isEmpty())
        {
            String now = DateTools.getCurrentDate("yyyy_MM_dd");
            params.put("date", now) ;
        }
        
        //给默认起始时间 
        String start = params.get("start").toString(); 
        if(start.isEmpty())
        {
            params.put("start", "00:00:00") ;
        }
        
        //给默认结束时间
        String end = params.get("end").toString(); 
        if(end.isEmpty())
        {
            params.put("end", "23:59:59") ;
        }
        
        // 返回到页面
        this.set("params", params);
        Pager pager = new Pager(this.getReq());
        
        try{
        // 调用逻辑类 查询到结果
        pager = logService.queryLog(pager, params);
        
        this.set("PAGER", pager);
        }catch(APIException e)
        {
            logger.error("query error",e);
        }
        return "log";
    }
    
    /**
     * 获取各reason的总和
     * @return
     * @throws APIException 
     */
    public String sumLog() throws APIException
    {
    	
        String field[] =
            new String[]
            {"start", "end", "type"};
        
        Map<String, Object> params = this.reqToMap(field);
        if(StringTool.isEmpty(this.get("start")))
        {
        	params.put("start", DateTools.addDate(System.currentTimeMillis(), -30, DateTools.DAY, "yyyy_MM_dd"));
        }
        if(StringTool.isEmpty(this.get("end")))
        {
        	params.put("end", DateTools.getCurrentDate("yyyy_MM_dd"));
        }
        this.set("params", params);
            	
        // 选择的日期

        List<HashMap<String, Object>> date_list = logService.queryTablesDate("gold");
       
        this.set("min_date", date_list.get(0).get("date"));
        this.set("max_date", date_list.get(date_list.size()-1).get("date"));
        List<Map<String, Object>> list = logService.sumLog(params, date_list);
        Map<String,Object> firstList =  logService.getFirstLine(list);
        this.set("date", date_list);
        Collections.reverse(list);
        List<Long> listdata = getList(list);
        this.set("firstList",firstList);
        this.set("sum_daily",list);
        this.set("size",list.size());
        this.set("n1",listdata.get(0));
        this.set("n2",listdata.get(1));
        this.set("n3",listdata.get(1)-listdata.get(0));
//        this.set("start_date", date_list.get(0).get("date"));
//        this.set("end_date", date_list.get(date_list.size()-1).get("date"));
        this.set("start_date", params.get("start").toString());
        this.set("end_date", params.get("end").toString());
        
    	return "sumLog";
    }
   

	private List<Long> getList( List<Map<String, Object>> listData){
    	List<Long> list = new 	ArrayList<Long>();
    	long n1 = 0;
    	long n2 = 0;
    	for(Map<String, Object> map : listData){
    		n1 = n1 + (getLong(map,"5")+ getLong(map,"24")+ getLong(map,"18")+ getLong(map,"19")+ getLong(map,"6")+ getLong(map,"501")+ getLong(map,"38")+ getLong(map,"37")+ getLong(map,"505"));
    		if((getLong(map,"34")-getLong(map,"35")-getLong(map,"36")) != 0){
    			n2 = n2+(getLong(map,"500")+getLong(map,"16")+getLong(map,"14")+getLong(map,"1")-getLong(map,"2")-getLong(map,"26")
    					+getLong(map,"29")+getLong(map,"27")-getLong(map,"28")+(getLong(map,"34")-getLong(map,"35")-getLong(map,"36")));
    		}else{
    			n2 = n2+(getLong(map,"500")+getLong(map,"16")+getLong(map,"14")+getLong(map,"1")-getLong(map,"2")-getLong(map,"26")
    					+getLong(map,"29")+getLong(map,"27")-getLong(map,"28")+getLongData(map,51,86)-getLongData(map,116,151)-getLongData(map,181,216));
    		}
    		
    	}
    	list.add(n1);
    	list.add(n2);
    	return list;
    }
	
	private long getLongData(Map<String, Object> map,int start,int end){
		long n = 0;
		
		for(int i = start;i <= end;i++){
			n = n+getLong(map,String.valueOf(i));
		}
		return n;
	}
	
	private long getLong(Map<String, Object> map,String key){
		BigDecimal bg = new BigDecimal(0);;
		try{
			if(map.containsKey(key)){
				
				bg.add(new BigDecimal((long)(map.get(key)==null?0:map.get(key)))); //= (BigDecimal)(map.get(key)==null?0:map.get(key));
				
			}
		}catch(Exception e){
			System.out.println("=================          "+key);
		}
		
		return bg.longValue();
	}
    
    
    
    /**
     * 老虎机统计明细
     * @return
     * @throws APIException 
     */
    public String slotLog() throws APIException
    {
    	
    	String field[] =
    			new String[]
    					{"end"};
    	
    	Map<String, Object> params = this.reqToMap(field);
    	if(StringTool.isEmpty(this.get("end")))
    	{
    		params.put("end", DateTools.getCurrentDate("yyyy_MM_dd"));
    	}
    	this.set("params", params);
    	// 选择的日期
    	String table = "gold_log_"+params.get("end");
    	Map<String, Object> one = logService.sumLog1(table);
    	this.set("one",one);
    	this.set("end_date", params.get("end"));
    	
    	return "slotLog";
    }
    //上个方法的复制（备份）
    public String slotLog_copy() throws APIException
    {
    	
    	String field[] =
    			new String[]
    					{"start", "end", "type"};
    	
    	Map<String, Object> params = this.reqToMap(field);
    	if(StringTool.isEmpty(this.get("start")))
    	{
    		params.put("start", DateTools.addDate(System.currentTimeMillis(), -30, DateTools.DAY, "yyyy_MM_dd"));
    	}
    	if(StringTool.isEmpty(this.get("end")))
    	{
    		params.put("end", DateTools.getCurrentDate("yyyy_MM_dd"));
    	}
    	this.set("params", params);
    	
    	// 选择的日期
    	
    	List<HashMap<String, Object>> date_list = logService.queryTablesDate("gold");
    	
    	this.set("min_date", date_list.get(0).get("date"));
    	this.set("max_date", date_list.get(date_list.size()-1).get("date"));
    	
    	List<Map<String, Object>> list = logService.sumLog(params, date_list);
    	this.set("date", date_list);
    	Collections.reverse(list);
    	this.set("sum_daily",list);
    	/*this.set("start_date", date_list.get(0).get("date"));
    	this.set("end_date", date_list.get(date_list.size()-1).get("date"));
    	*/
    	this.set("start_date", params.get("start").toString());
        this.set("end_date", params.get("end").toString());
    	return "slotLog";
    }
    /**
     * 老虎机彩金明细返还
     * @return
     * @throws APIException 
     */
    public String jackport() throws APIException
    {
    	
    	String field[] =
    			new String[]
    					{"start", "end", "type"};
    	
    	Map<String, Object> params = this.reqToMap(field);
    	if(StringTool.isEmpty(this.get("start")))
    	{
    		params.put("start", DateTools.addDate(System.currentTimeMillis(), -30, DateTools.DAY, "yyyy_MM_dd"));
    	}
    	if(StringTool.isEmpty(this.get("end")))
    	{
    		params.put("end", DateTools.getCurrentDate("yyyy_MM_dd"));
    	}
    	this.set("params", params);
    	
    	// 选择的日期
    	
    	List<HashMap<String, Object>> date_list = logService.queryTablesDate("gold");
    	
    	this.set("min_date", date_list.get(0).get("date"));
    	this.set("max_date", date_list.get(date_list.size()-1).get("date"));
    	
    	List<Map<String, Object>> list = logService.sumLog(params, date_list);
    	this.set("date", date_list);
    	Collections.reverse(list);
    	this.set("sum_daily",list);
    	/*this.set("start_date", date_list.get(0).get("date"));
    	this.set("end_date", date_list.get(date_list.size()-1).get("date"));*/
    	this.set("start_date", params.get("start").toString());
        this.set("end_date", params.get("end").toString());
    	
    	return "jackport";
    }
    
    /**
     * 留存统计
     * @return
     * @throws APIException
     * @throws ParseException 
     */
    public String retained()throws APIException, ParseException{
    	//device 0:所有，1：安卓，2：iOS
        String field[] = new String[]{"start", "end","device"};
    	
    	Map<String, Object> params = this.reqToMap(field);
    	if(StringTool.isEmpty(this.get("start")))
    	{
    		params.put("start", DateTools.addDate(System.currentTimeMillis(), -30, DateTools.DAY, "yyyy_MM_dd"));
    	}
    	if(StringTool.isEmpty(this.get("end")))
    	{
    		params.put("end", DateTools.getCurrentDate("yyyy_MM_dd"));
    	}
    	if(StringTool.isEmpty(this.get("device"))){
    		params.put("device", 0);
    	}
    	String country = this.get("country");
    	if(StringUtils.isNoneBlank(country)){
    		params.put("country", country);
    	}
    	this.set("params", params);
    	
    	//查询符合时间规则的数据表
    	List<HashMap<String, Object>> date_list = logService.queryTablesDate("player_keep_log");
    	
    	this.set("min_date", date_list.get(0).get("date"));
    	this.set("max_date", date_list.get(date_list.size()-1).get("date"));
    	
    	List<HashMap<String, Object>> list = logService.retained(params, date_list);
    	
    	this.set("date", date_list);
    	Collections.reverse(list);
    	this.set("sum_daily",list);
    	this.set("country",country);
    	/*this.set("start_date", date_list.get(0).get("date"));
    	this.set("end_date", date_list.get(date_list.size()-1).get("date"));*/
    	this.set("start_date", params.get("start").toString());
        this.set("end_date", params.get("end").toString());
    	return "retained";
    }
    /**
     * 留存统计
     * @return
     * @throws APIException
     * @throws ParseException 
     */
    public String retainedCountry()throws APIException, ParseException{
    	String field[] = new String[]{"start", "end"};
    	
    	Map<String, Object> params = this.reqToMap(field);
    	if(StringTool.isEmpty(this.get("start")))
    	{
    		params.put("start", DateTools.addDate(System.currentTimeMillis(), -30, DateTools.DAY, "yyyy_MM_dd"));
    	}
    	if(StringTool.isEmpty(this.get("end")))
    	{
    		params.put("end", DateTools.getCurrentDate("yyyy_MM_dd"));
    	}
    	String country = this.get("country");
    	if(StringUtils.isNoneBlank(country)){
    		params.put("country", country);
    	}
    	this.set("params", params);
    	
    	//查询符合时间规则的数据表
    	List<HashMap<String, Object>> date_list = logService.queryTablesDate("player_keep_log");
    	
    	this.set("min_date", date_list.get(0).get("date"));
    	this.set("max_date", date_list.get(date_list.size()-1).get("date"));
    	date_list = logService.chooseDates(params,date_list);
    	List<NewPeopleVO>  list = logService.queryPerCountryPeople(date_list);
    	List<Country> countrys = logService.getAllCountry();
    	for(NewPeopleVO one:list){
    		for(Country cou:countrys){
    			if(one.getCountries() != null && one.getCountries().equals(cou.getCountry())){
    				one.setCountries(cou.getLangDesc());
    				break;
    			}
    		}
    		for(Country cou:countrys){
    			if(one.getIpCountries() != null && one.getIpCountries().equals(cou.getCountry())){
    				one.setIpCountries(cou.getLangDesc());
    				break;
    			}
    		}
    		//等于2就是IOS
    		if(one.getChannelType() != null && one.getChannelType().equals("18")){
    			one.setChannelType("ios");
    		}else{
    			one.setChannelType("android");
    		}
    	}
    	this.set("date", date_list);
    	Collections.reverse(list);
    	this.set("sum_daily",list);
    	this.set("country",country);
    	/*this.set("start_date", date_list.get(0).get("date"));
    	this.set("end_date", date_list.get(date_list.size()-1).get("date"));*/
    	this.set("start_date", params.get("start").toString());
    	this.set("end_date", params.get("end").toString());
    	return "retainedCountry";
    }
    
    /**
     * 数据总览
     * @return
     * @throws APIException 
     */
    public String summary() throws APIException{
    	logger.info("-----------------summary::");
    	String field[] = new String[]{"start", "end"};
    	Map<String, Object> params = this.reqToMap(field);
    	if(StringTool.isEmpty(this.get("start")))
    	{
    		params.put("start", DateTools.addDate(System.currentTimeMillis(), -30, DateTools.DAY, "yyyy_MM_dd"));
    	}
    	if(StringTool.isEmpty(this.get("end")))
    	{
    		params.put("end", DateTools.getCurrentDate("yyyy_MM_dd"));
    	}
    	this.set("params", params);
    	
    	//查询符合时间规则的数据表
    	List<HashMap<String, Object>> date_list = logService.queryTablesDate("data_overview_log");
    	
    	this.set("min_date", date_list.get(0).get("date"));
    	this.set("max_date", date_list.get(date_list.size()-1).get("date"));
    	
    	List<Map<String, Object>> list = logService.summaryLog(params, date_list);
    	
    	
    	Map<String, Object> map1 = new HashMap<>();
    	double five1 = 0;
    	double one1 = 0;
    	double payRate = 0;
    	if(list.size() != 0) {
    		
    		for (Map<String, Object> map : list) {
    			BigDecimal five = (BigDecimal) map.get("5");
    			BigDecimal one = (BigDecimal) map.get("1");
    			if (one == null) {
					one1 = 0;
					payRate = 0;
				}else {
					one1 = one.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
					payRate = five1/one1;
				}
    			if (five == null) {
					five1 = 0;
					payRate = 0;
				}else {
					five1 = five.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
					payRate = five1/one1;
				}
    			
    			DecimalFormat df = new DecimalFormat("0.00%");
    			String pr = df.format(payRate);
    			
    			map.put("pr", pr);
    			
    		}
    	}
    	
    	this.set("date", date_list);
    	Collections.reverse(list);
    	this.set("sum_daily",list);
    	
		
    	
    	/*this.set("start_date", date_list.get(0).get("date"));
    	this.set("end_date", date_list.get(date_list.size()-1).get("date"));*/
    	this.set("start_date", params.get("start").toString());
        this.set("end_date", params.get("end").toString());
        
        double a = logService.ltv(date_list);
        
        BigDecimal b = new BigDecimal(a);
        double ltv =  b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue(); 
        java.text.DecimalFormat df = new java.text.DecimalFormat("0.00");
        
        this.set("LTV", df.format(ltv/100));
        
    	
    	return "summary";
    }
    

	/**
     * 老虎机spin统计明细
     * @return
     * @throws APIException
     */
    public String slotspindetail()throws APIException{
        String field[] = new String[]{"start", "end","facebook","sex"};
    	
    	Map<String, Object> params = this.reqToMap(field);
    	if(StringTool.isEmpty(this.get("start")))
    	{
    		params.put("start", DateTools.addDate(System.currentTimeMillis(), -1, DateTools.DAY, "yyyy_MM_dd"));
    	}
    	if(StringTool.isEmpty(this.get("end")))
    	{
    		params.put("end", DateTools.getCurrentDate("yyyy_MM_dd"));
    	}
    	
    	if(StringTool.isEmpty(this.get("facebook")))
    	{
    		params.put("facebook", 2);
    	}
    	
    	if(StringTool.isEmpty(this.get("sex")))
    	{
    		params.put("sex", 2);
    	}
    	
    	
    	this.set("params", params);
    	
    	//查询符合时间规则的数据表
    	List<HashMap<String, Object>> date_list = logService.queryTablesDate("data_overview_log");
    	
    	this.set("min_date", date_list.get(0).get("date"));
    	this.set("max_date", date_list.get(date_list.size()-1).get("date"));
    	
    	List<Map<String, Object>> list = logService.slotspindetail(params, date_list);
    	
    	
    	List<SlotName> slotNames = utilService.getAllSlotIdNames();
    	this.set("slotNames", slotNames);
    	this.set("date", date_list);
    	Collections.reverse(list);
    	this.set("sum_daily",list);
    	/*this.set("start_date", date_list.get(0).get("date"));
    	this.set("end_date", date_list.get(date_list.size()-1).get("date"));*/
    	this.set("start_date", params.get("start").toString());
        this.set("end_date", params.get("end").toString());
 	   
 	   return "slotspindetail";
    }
    
    /**
     * 老虎机spin统计明细 多了 性别 年龄 国籍
     * @return
     * @throws APIException
     */
    public String slotspindetailMore()throws APIException{
        String field[] = new String[]{"start", "end"};
    	
    	Map<String, Object> params = this.reqToMap(field);
    	if(StringTool.isEmpty(this.get("date")))
    	{
    		params.put("date", DateTools.getCurrentDate("yyyy_MM_dd"));
    	}else{
    		params.put("date", this.get("date"));
    	}
    	
    	if(!StringTool.isEmpty(this.get("facebook")))
    	{
    		params.put("facebook", this.get("facebook"));
    	}else{
    		params.put("facebook", "0");
    	}
    	 if(!StringTool.isEmpty(this.get("country")))
  	   {
  		   params.put("country", this.get("country"));
  	   }else{
  		   params.put("country", "0");
  	   }
  	   if(!StringTool.isEmpty(this.get("girl")))
  	   {
  		   params.put("girl", this.get("girl"));
  	   }else{
  		   params.put("girl", "0");
  	   }
  	   if(!StringTool.isEmpty(this.get("age")))
  	   {
  		   params.put("age", this.get("age"));
  	   }else{
  		   params.put("age", "0");
  	   }
  	   
    	this.set("params", params);
    	
    	List<SpinDetailVO> list = logService.slotspindetailMore(params);
    	if(list == null){
    		list = new ArrayList<SpinDetailVO>();
    	} 
    	
    	List<SlotName> slotNames = utilService.getAllSlotIdNames();
    	this.set("slotNames", slotNames);
    	this.set("sum_daily",list);
 	    return "slotspindetailMore";
    }
    
    /**
     * 当天老虎机最大赢家
     * @return
     * @throws APIException
     */
    public String slotwinner() throws APIException{
    	
    	String date = this.get("date");
    	if(StringUtils.isBlank(date)){
    		date = DateTools.getCurrentDate("yyyy_MM_dd");
    	}
    	this.set("slotwinnerTime", date);
    	String winner = this.get("winner");
    	
    	//查询符合时间规则的数据表
    	//List<HashMap<String, Object>> date_list = logService.queryTablesDate("data_overview_log");
    	
    	
    	List<HashMap<String, Object>> listData = logService.slotwinner(date,winner);
    	this.set("listData",listData);
    	this.set("date",date);
    	this.set("winner",winner);
    	return "slotwinner";
    }
    /**
     * 当天老虎机最大Spin
     * @return
     * @throws APIException
     */
    public String slotMaxSpin() throws APIException{
    	
    	String date = this.get("date");
    	if(StringUtils.isBlank(date)){
    		date = DateTools.getCurrentDate("yyyy_MM_dd");
    	}
    	this.set("slotMaxSpinTime", date);
    	String player = this.get("player");
    	

    	List<HashMap<String, Object>> listData = logService.slotMaxSpin(date,player);
    	this.set("listData",listData);
    	this.set("date",date);
    	this.set("player",player);
    	
    	return "slotMaxSpin";
    }
    
    /**
     * 在线人数统计
     * @return
     * @throws APIException
     */
    public String online()throws APIException{
    	String date = this.get("date");
    	if(StringUtils.isBlank(date)){
    		date = DateTools.getCurrentDate("yyyy_MM_dd");
    	}
    	this.set("slotMaxSpinTime", date);
    	
    	Map<String,List<HashMap<String, Object>>> reMap = logService.online(date);
    	List<HashMap<String, Object>> listData = reMap.get("list2");
    	List<HashMap<String, Object>> listDNU = reMap.get("listDNU");
    	this.set("listData",listData);
    	this.set("listDNU",listDNU.get(0));
    	this.set("date",date);
    	
    	return "online";
    }
    
    /**
     * 
     * @return
     */
    public String ichartOnline(){
    	String date = this.get("date");
    	if(StringUtils.isBlank(date)){
    		date = DateTools.getCurrentDate("yyyy_MM_dd");
    	}
    	String country = this.get("country");
    	this.set("date", date);
    	this.set("cou", country);
    	List<Country> countryList = logService.getAllCountry();
    	this.set("countryList", countryList);
    	List<HashMap<String, Object>> reMap = logService.ichartOnline(date);
    	String labels = "";
		String flow = "";
		if(reMap != null && reMap.size()>0){
			for(HashMap<String, Object> map:reMap){
				//根据国家筛选，country为空 则不筛选
				if(StringUtils.isBlank(country)){
					flow +=map.get("value_role")+",";
				}else{
					String json = (String)map.get("ipCountries");
					if(StringUtils.isBlank(json)){
						flow += 0+",";
					}else{
						JSONObject jb = JSONObject.parseObject(json);
						if(jb.containsKey(country)){
							int countryNum = jb.getIntValue(country);
							flow += countryNum+",";
						}else{
							flow += 0+",";
						}
					}
				}
				labels+=map.get("time_dian")+",";
			}
			flow=flow.substring(0, flow.length()-1);
			labels=labels.substring(0, labels.length()-1);
		}
		this.getReq().setAttribute("labels", labels);
		this.getReq().setAttribute("flow", flow);
    	return "ichartOnline";
    }
    /**
     * 设备统计
     * @return
     * @throws APIException
     */
    public String equipment()throws APIException{
    	
    	String date = this.get("date");
    	if(StringUtils.isBlank(date)){
    		date = DateTools.getCurrentDate("yyyy_MM_dd");
    	}
    	this.set("slotMaxSpinTime", date);
    	String equipment = this.get("equipment");
    	
    	List<HashMap<String, Object>> listData = logService.equipment(date,equipment);
    	this.set("listData",listData);
    	this.set("date",date);
    	this.set("equipment",equipment);
    	
    	return "equipment";
    }
    
    public String versionClient()throws APIException{
    	
    	String time = DateTools.getCurrentDate("yyyy_MM_dd");
    	this.set("slotMaxSpinTime", time);
    	
    	List<HashMap<String, Object>> listData = logService.versionClient(time);
    	this.set("listData",listData);
    	
    	return "versionClient";
    }
    
    public String channel()throws APIException{
    	String date = this.get("date");
    	if(StringUtils.isBlank(date)){
    		date = DateTools.getCurrentDate("yyyy_MM_dd");
    	}
    	String country = this.get("country");
    	this.set("slotMaxSpinTime", date);
    	
    	List<HashMap<String, Object>> listData = logService.channel(date,country);
    	this.set("listData",listData);
    	this.set("date",date);
    	this.set("country",country);
    	return "channel";
    }
    
    public String orderDetails()throws APIException{
    	String date = this.get("date");
    	if(StringUtils.isBlank(date)){
    		date = DateTools.getCurrentDate("yyyy_MM_dd");
    	}
    	String player = this.get("player");
    	this.set("slotMaxSpinTime", date);
    	
    	List<HashMap<String, Object>> listData = logService.orderDetails(date,player);
    	this.set("listData",listData);
    	this.set("date",date);
    	this.set("player",player);
    	return "orderDetails";
    }
    
    /**
     * 充值明细返回
     * @return
     * @throws APIException
     */
   public String topupdetail()throws APIException{
	   String field[] = new String[]{"start", "end"};
   	
   	Map<String, Object> params = this.reqToMap(field);
   	if(StringTool.isEmpty(this.get("start")))
   	{
   		params.put("start", DateTools.addDate(System.currentTimeMillis(), -30, DateTools.DAY, "yyyy_MM_dd"));
   	}
   	if(StringTool.isEmpty(this.get("end")))
   	{
   		params.put("end", DateTools.getCurrentDate("yyyy_MM_dd"));
   	}
   	String country = this.get("country");
   	if(StringUtils.isNotBlank(country)){
   		params.put("country", country);
   	}
   	this.set("params", params);
   	
   	//查询符合时间规则的数据表
   	List<HashMap<String, Object>> date_list = logService.queryTablesDate("new_recharge_log");
   	
   	this.set("min_date", date_list.get(0).get("date"));
   	this.set("max_date", date_list.get(date_list.size()-1).get("date"));
   	
   	List<Map<String, Object>> list = logService.topupdetail(params, date_list);
   	
   	this.set("date", date_list);
   	Collections.reverse(list);
   	this.set("sum_daily",list);
   	this.set("country",country);
   /*	this.set("start_date", date_list.get(0).get("date"));
   	this.set("end_date", date_list.get(date_list.size()-1).get("date"));*/
   	this.set("start_date", params.get("start").toString());
    this.set("end_date", params.get("end").toString());
	   return "topupdetail";
   }
   public String topupdetailMore()throws APIException{
	   String field[] = new String[]{"start", "end"};
	   
	   Map<String, Object> params = this.reqToMap(field);
	   if(StringTool.isEmpty(this.get("date")))
	   {
		   params.put("date", DateTools.getCurrentDate("yyyy_MM_dd"));
	   }else{
		   params.put("date", this.get("date"));
	   }
	   
	   if(!StringTool.isEmpty(this.get("country")))
  	   {
  		   params.put("country", this.get("country"));
  	   }else{
  		   params.put("country", "0");
  	   }
  	   if(!StringTool.isEmpty(this.get("girl")))
  	   {
  		   params.put("girl", this.get("girl"));
  	   }else{
  		   params.put("girl", "0");
  	   }
  	   if(!StringTool.isEmpty(this.get("age")))
  	   {
  		   params.put("age", this.get("age"));
  	   }else{
  		   params.put("age", "0");
  	   }
	   this.set("params", params);
	   
	   List<RechargeVO> list = logService.topupdetailMore(params);
	   this.set("sum_daily",list);
	   return "topupdetailMore";
   }
   /**
    *  一阶登录比：新增用户首周内登录天数>=3的用户数量/新增用户首周内登录天数>=2的用户数量;
    *  二阶登录比：新增用户首周内登录天数>=3且次周内有登录的用户数量/新增用户首周内登录天数>=3的用户数量；
    * @return
    * @throws APIException
    */
   public String loginRate(){
	   try {
		   String date = this.get("date");
		   if(StringUtils.isBlank(date)){
			   date = TimeUtil.formatYMDHMTime(new Date().getTime());
		   }
			List<HashMap<String, Object>> date_list = logService.queryTablesDate("player_keep_log");
			Map<String,String> oneRate = logService.oneLoginRate(date_list,date);
			Map<String,String> twoRate= logService.twoLoginRate(date_list,date);
			this.set("date", date);
			this.set("oneRate", oneRate);
			this.set("twoRate", twoRate);
		} catch (APIException e) {
			e.printStackTrace();
		}
	   return "loginRate";
   }
  /**
   * 
   * 以下的三个 都是 统计  texas 库中的表  而不是 texas_log　中的表
   * 
   */
   
   //老虎机年龄段的分布
   public String slotAge(){
	   try {
		   String sType = this.get("sType");
		   List<SlotAgeVO> slotAgeVOS = logService.queryslotAge(sType);
		   this.set("slotAgeVOS", slotAgeVOS);
		   this.set("sType", sType);
	   } catch (Exception e) {
		   e.printStackTrace();
	   }
	   return "slotAge";
   }
   	//每台老虎机男女 人数
   public String slotSex(){
	   try {
		   String sType = this.get("sType");
		   List<SlotSexVO> slotSexVOS = logService.queryslotSex(sType);
		   this.set("slotSexVOS", slotSexVOS);
		   this.set("sType", sType);
	   } catch (Exception e) {
		   e.printStackTrace();
	   }
	   return "slotSex";
   }
   
   //统计各国的付费用户，付费用户的行为（购买的商品）
   public String slotPay(){
	   try {
		   String chartId = this.get("chartId");
		   List<SlotPayVO> slotPayVOS = logService.queryslotPay(chartId);
		   this.set("slotPayVOS", slotPayVOS);
		   this.set("chartId", chartId);
	   } catch (Exception e) {
		   e.printStackTrace();
	   }
	   return "slotPay";
   }
  
}