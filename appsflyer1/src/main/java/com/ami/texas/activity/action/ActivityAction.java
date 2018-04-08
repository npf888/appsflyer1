package com.ami.texas.activity.action;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ami.api.common.AppConstant;
import com.ami.api.common.Pager;
import com.ami.api.exception.APIException;
import com.ami.api.utill.DateTools;
import com.ami.api.utill.HttpURLConnectionUtil;
import com.ami.api.utill.StringTool;
import com.ami.api.web.action.BaseAction;
import com.ami.common.ActivityRewardRule;
import com.ami.common.RandRewardData;
import com.ami.texas.activity.pojo.ActivityForm;
import com.ami.texas.activity.pojo.ActivityVO;
import com.ami.texas.activity.pojo.DetailVO;
import com.ami.texas.activity.service.ActivityService;
import com.ami.texas.img.service.ImgService;

/**
 * 娲诲姩閰嶇疆鐩稿叧
 * 
 * @author Cici
 * @version [鐗堟湰鍙�, 2013-5-29]
 * @see [鐩稿叧绫�/鏂规硶]
 * @since [浜у搧/妯″潡鐗堟湰]
 */
@Scope("prototype")
@Component
public class ActivityAction extends BaseAction
{
    @Autowired
    ActivityService activityService;
    
    @Autowired
    ImgService  imgService;
    
    private static Logger logger = Logger.getLogger(ActivityAction.class);
    
    /**
     * 鑾峰彇鎵�鏈夋椿鍔�
     * 
     * @return
     * @throws APIException 
     * @see [绫汇�佺被#鏂规硶銆佺被#鎴愬憳]
     */
    public String queryActivity() throws APIException
    {
        
        Pager pager = new Pager(this.getReq());
        
        // 璋冪敤閫昏緫绫� 鏌ヨ鍒扮粨鏋�
        pager = activityService.queryActivity(pager);
        
        this.set("PAGER", pager);
        
        return "queryActivity";
    }
    
    /**
     * 活动的详情
     */
    public String detail(){
    	String activityId = this.get("activityId");
    	if(StringUtils.isBlank(activityId)){
    		return "detail";
    	}
    	
    	//先把 activity_condition 查出来 
    	List<HashMap<String,Object>> conditionList =activityService.getActivityCondition();
    	ActivityVO activityVO = activityService.detail(activityId);
    	this.set("activity", activityVO);
    	String rulePack = activityVO.getRulePack();
    	if(StringUtils.isNotBlank(rulePack)){
    		List<ActivityRewardRule> ActivityRewardRuleList = JSONObject.parseArray(rulePack, ActivityRewardRule.class);
    		
    		List<DetailVO> DetailVOList = new ArrayList<DetailVO>();
    		for(ActivityRewardRule rule:ActivityRewardRuleList){
    			DetailVO DetailVO = new DetailVO();
    			//最大领取次数
    			DetailVO.setMaxDrows(String.valueOf(rule.getMaxDrows()));
    			//用户要达到的要求
    			HashMap<String,Integer> paramMap = rule.getParamsMap();
    			String params = "";
    			for(Entry<String,Integer> entry:paramMap.entrySet()){
    				String key = entry.getKey();
    				for(HashMap<String,Object>  condition:conditionList){
    					String condition_id = (String)condition.get("condition_id");
    					if(condition_id.equals(key)){
    						String title = (String)condition.get("title");
    						params+=title+"-";
    						break;
    					}
    				}
    				int value = entry.getValue();
    				params+=key+"="+value+";";
    			}
    			DetailVO.setParamsMap(params);
    			
    			//给予用户的奖励
    			List<RandRewardData> randRewardDataList = rule.getRewardList();
    			String rewardList = "";
    			for(RandRewardData RandRewardData:randRewardDataList){
    				int rewardId = RandRewardData.getRewardId();
    				int vippoint = RandRewardData.getVippoint();
    				int rewardCount = RandRewardData.getRewardCount();
    				if(rewardId == 3){
    					rewardList+="rewardId=金币-"+rewardId+";";
    				}else if(rewardId == 4){
    					rewardList+="rewardId=魅力值-"+rewardId+";";
    				}else if(rewardId == 5){
    					rewardList+="rewardId=经验值-"+rewardId+";";
    				}else if(rewardId == 2){
    					rewardList+="rewardId=点券-"+rewardId+";";
    				}else if(rewardId == 1){
    					rewardList+="rewardId=钻石-"+rewardId+";";
    				}else{
    					rewardList+="rewardId=空-"+rewardId+";";
    				}
    				rewardList+="vippoint=奖励VIP点数-"+vippoint+";";
    				rewardList+="rewardCount=奖励值-"+rewardCount;
    			}
    			DetailVO.setRewardList(rewardList);
    			DetailVOList.add(DetailVO);
    		}
    		this.set("rulePackList", DetailVOList);
    	}
    	return "detail";
    }
    /**
     * 淇敼娲诲姩淇℃伅
     * 
     * @throws IOException
     * @see [绫汇�佺被#鏂规硶銆佺被#鎴愬憳]
     */
    public void doEditActivity()
        throws IOException
    {
        Map<String, String> response = new HashMap<String, String>();
        try
        {
            String field[] = new String[]
            {"title", "start_time", "end_time", "description"};
            
            Map<String, Object> parameters = this.reqToMap(field);
            
            String id = this.get("id");
            
            activityService.updateActivity(parameters, id);
            
            this.set("id", "id");
            
            response.put("result", "success");
            response.put("id", id);
        }
        catch (Exception e)
        {
            response.put("result", "fail");
            response.put("error", e.getMessage());
            
            logger.error("add Exception", e);
        }
        printJosn(response);
    }

    /**
     * 鑾峰彇淇敼鎴栨坊鍔犲姩浣�
     * 
     * @return
     * @throws Exception
     * @see [绫汇�佺被#鏂规硶銆佺被#鎴愬憳]
     */
    public String toEditActivity()
        throws Exception
    {
        String id = this.get("id");
        
        String method = "doAddActivity";
        Map<String, Object> triggers = activityService.queryTriggers();
        this.set("triggers", triggers);
        
        Map<String, Object> rewards = activityService.queryRewards();
        this.set("rewards", rewards);
        
        List<HashMap<String, Object>> images = imgService.query();
        this.set("images", images);
        
        if (!StringTool.isEmpty(id))
        {
            Map<String, Object> one = activityService.loadActivityByID(id);
            method = "doEditActivity";
            this.set("one", one);
        }
        
        this.set("method", method);
        this.set("a_type", this.get("a_type"));
        return "updateActivity";
    }

   
    /**
     * 璺宠浆鍒版坊鍔燾ondition椤甸潰
     * 
     * @return
     * @see [绫汇�佺被#鏂规硶銆佺被#鎴愬憳]
     */
    public String toAddCondition()
    {
        
        return "toAddCondition";
    }

    /**
     * 娣诲姞瑙﹀彂鏉′欢/濂栧姳
     * 
     * @throws IOException
     * @see [绫汇�佺被#鏂规硶銆佺被#鎴愬憳]
     */
    public void doAddCondition() throws IOException
    {
        Map<String, String> response = new HashMap<String, String>();
        try
        {
       
            String insert[] = new String[]
            {"id", "title",  "type", "activity_type_id"};
                      
            Map<String, Object> params = this.reqToMap(insert);
            
            if(null != this.get("description"))
            {
                params.put("description", this.get("description"));
            }
            activityService.doAddCondition(params);
            response.put("result", "Condition Added");
        }
        catch (Exception e)
        {
            response.put("result", e.getMessage());
        }
        printJosn(response);
    }
       
    /**
     * 鏍规嵁id鍒犻櫎娲诲姩
     * @throws ParseException 
     * @throws APIException 
     * @throws IOException 
     * 
     * @see [绫汇�佺被#鏂规硶銆佺被#鎴愬憳]
     */
    public void doDelete() throws APIException, ParseException, IOException
    {
        String id = this.get("id");
        String result = activityService.doDelete(id);
        print(result);
    }
    
    /**
     * 鏍规嵁娲诲姩绫诲瀷鑾峰彇瑙﹀彂鏉′欢
     * <鍔熻兘璇︾粏鎻忚堪>
     * @throws IOException 
     * @throws APIException 
     * @see [绫汇�佺被#鏂规硶銆佺被#鎴愬憳]
     */
    public void getTriggersByType() throws IOException, APIException
    {
    	this.getReq().setCharacterEncoding("UTF-8");
    	this.getRes().setCharacterEncoding("UTF-8");
        String activity_type=this.get("activity_type");
                
        Map<String, Object> typeTrigger = activityService.getTriggersByType(activity_type);
        printJosn(typeTrigger);
    }
    

    public static void main(String args[]) throws Exception
    {
        ActivityAction ac = new ActivityAction();
        ac.generateActivityJsonData();
        
    }
    
    public void generateActivityJsonDataold() throws Exception
    {
    	ActivityRewardRule rewardRule = new ActivityRewardRule();
    	RandRewardData rewardData = new RandRewardData();
    	   	
    	
        JSONObject activity_detail = new JSONObject();
        
        Map<String, Object> parameters = new HashMap<String, Object>();
        
        /*parameters.put("activityType",1001);
        parameters.put("title", "9鏈�");
        parameters.put("activityDesc","9鏈堝厖鍊煎ぇ閰");
        String startTime = "2015-08-03" + " 00:00:00";
        String endTime = "2015-09-08" + " 23:59:59";
        parameters.put("daily", 0);
        parameters.put("rulePack","1001,1003,;23,23,|1004,1004,;4,5,|5+1001,1003,;23,234,|1004,;523,|5");
        parameters.put("pic", "");
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        Long start_time = sf.parse(startTime).getTime();
        Long end_time = sf.parse(endTime).getTime();       
        parameters.put("startTime",start_time);
        parameters.put("endTime",end_time);
        parameters.put("id", 0);*/
        
        parameters.put("activityType",Integer.parseInt(this.get("activityType")));
        parameters.put("title", this.get("title"));
        parameters.put("title_cn", this.get("title_cn"));
        parameters.put("title_tw", this.get("title_tw"));
        parameters.put("activityDesc",this.get("activityDesc"));
        parameters.put("activityDesc_cn",this.get("activityDesc_cn"));
        parameters.put("activityDesc_tw",this.get("activityDesc_tw"));
        
        parameters.put("daily", Integer.parseInt(this.get("daily")));
        parameters.put("pic", this.get("pic"));
        parameters.put("pic_cn", this.get("pic_cn"));
        parameters.put("pic_tw", this.get("pic_tw"));
        parameters.put("pageLink", Integer.parseInt(this.get("pageLink")));
        parameters.put("id", 0);
        
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
        String startTime = this.get("startTime") + " 00:00:00";
        String endTime = this.get("endTime") + " 23:59:59";
        Long start_time = DateTools.timeStr2Long(startTime,"yyyy-MM-dd HH:mm:ss");
        Long end_time =  DateTools.timeStr2Long(endTime,"yyyy-MM-dd HH:mm:ss");   
        parameters.put("startTime",start_time);
        parameters.put("endTime",end_time);
      
        List<Object> rule_pack = new ArrayList<Object>();
       
       
        RandRewardData reward_data = new RandRewardData();
        
        String rulepack = this.get("rulePack").toString();
        String[] temp1 = rulepack.split("\\+");
        
        for (int i = 0; i < temp1.length; i++)
        {
            ActivityRewardRule reward_rule = new ActivityRewardRule();
            String[] part = temp1[i].split("\\|");
            
            //灏佽paramsMap
            String[] rule_set = part[0].split(";");
            HashMap<String, Integer> rule_param = new HashMap<String, Integer>();
            String[] rule_id = rule_set[0].split(",");
            String[] rule_amount = rule_set[1].split(",");
            for (int k = 0; k < rule_id.length; k++)
            {    
                rule_param.put(rule_id[k], Integer.parseInt(rule_amount[k]));
            }
            reward_rule.setParamsMap(rule_param);
            
            //灏佽RewardList
            String[] reward_set = part[1].split(";");
            List<RandRewardData> reward = new ArrayList<RandRewardData>();
            String[] reward_id = reward_set[0].split(",");
            String[] reward_amount = reward_set[1].split(",");
            for (int k = 0; k < reward_id.length; k++)
            {
                reward_data.setRewardId(Integer.parseInt(reward_id[k]));
                reward_data.setRewardCount(Integer.parseInt(reward_amount[k]));
                
                reward.add(reward_data);
            }
            reward_rule.setRewardList(reward);
            
            //灏佽MaxDrows
            int maxDrows = Integer.parseInt(part[2]);
            reward_rule.setMaxDrows(maxDrows);
                        
            rule_pack.add(reward_rule);
        }
        
        parameters.put("rulePack", rule_pack);
                
        activity_detail =JSON.parseObject(JSON.toJSONString(parameters));
        String url =  AppConstant.SERVER_URL+"api/activity/update.json";
        JSONObject result = HttpURLConnectionUtil.post(url, activity_detail);
        printJosn(result);
    }
    public void generateActivityJsonData() throws Exception
    {
    	
    	ActivityForm from = new ActivityForm();
    	
    	TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
    	String startTime = this.get("startTime") + " 00:00:00";
    	String endTime = this.get("endTime") + " 23:59:59";
    	Long start_time = DateTools.timeStr2Long(startTime,"yyyy-MM-dd HH:mm:ss");
    	Long end_time =  DateTools.timeStr2Long(endTime,"yyyy-MM-dd HH:mm:ss");   
    	
    	from.setActivityType(Integer.parseInt(this.get("activityType")));
    	from.setTitle(this.get("title"));
    	from.setTitle_cn(this.get("title_cn"));
    	from.setTitle_tw(this.get("title_tw"));
    	from.setActivityDesc(this.get("activityDesc"));
    	from.setActivityDesc_cn(this.get("activityDesc_cn"));
    	from.setActivityDesc_tw(this.get("activityDesc_tw"));
    	from.setDaily(Integer.parseInt(this.get("daily")));
    	from.setPic(this.get("pic"));
    	from.setPic_cn(this.get("pic_cn"));
    	from.setPic_tw(this.get("pic_tw"));
    	from.setHall_pic(this.get("hall_pic"));
    	from.setHall_pic_cn(this.get("hall_pic_cn"));
    	from.setHall_pic_tw(this.get("hall_pic_tw"));
    	from.setPageLink(Integer.parseInt(this.get("pageLink")));
    	from.setId(0);
    	from.setStartTime(start_time);
    	from.setEndTime(end_time);
    	
    	
    	List<ActivityRewardRule> rulePackList = new ArrayList<ActivityRewardRule>();
    	
    	
    	
    	
    	String rulepack = this.get("rulePack").toString();
    	if(StringUtils.isNotBlank(rulepack)){
    		String[] temp1 = rulepack.split("\\+");
    	
	    	for (int i = 0; i < temp1.length; i++)
	    	{
	    		ActivityRewardRule reward_rule = new ActivityRewardRule();
	    		String[] part = temp1[i].split("\\|");
	    		
	    		//灏佽paramsMap
	    		String[] rule_set = part[0].split(";");
	    		HashMap<String, Integer> rule_param = new HashMap<String, Integer>();
	    		String[] rule_id = rule_set[0].split(",");
	    		String[] rule_amount = rule_set[1].split(",");
	    		for (int k = 0; k < rule_id.length; k++)
	    		{    
	    			rule_param.put(rule_id[k], Integer.parseInt(rule_amount[k]));
	    		}
	    		reward_rule.setParamsMap(rule_param);
	    		
	    		//灏佽RewardList
	    		String[] reward_set = part[1].split(";");
	    		List<RandRewardData> reward = new ArrayList<RandRewardData>();
	    		String[] reward_id = reward_set[0].split(",");
	    		String[] reward_amount = reward_set[1].split(",");
	    		for (int k = 0; k < reward_id.length; k++)
	    		{
	    			RandRewardData reward_data = new RandRewardData();
	    			reward_data.setRewardId(Integer.parseInt(reward_id[k]));
	    			reward_data.setRewardCount(Integer.parseInt(reward_amount[k]));
	    			reward_data.setVippoint(Integer.parseInt(reward_amount[k+reward_id.length]));
	    			reward.add(reward_data);
	    		}
	    		reward_rule.setRewardList(reward);
	    		
	    		//灏佽MaxDrows
	    		int maxDrows = Integer.parseInt(part[2]);
	    		reward_rule.setMaxDrows(maxDrows);
	    		
	 
	    		rulePackList.add(reward_rule);
	    	}
    	}
    	
    	from.setRulePack(rulePackList);
    	
    	/**
    	 * 杩欓噷閾炬帴go璇█鐨勬湇鍔″櫒
    	 */
    	
    	JSONObject result =activityService.saveOrUpdate(from);
    	printJosn(result);
    }
    /**
     * 鑾峰彇鐩稿簲鑰佽檸鏈轰笅鐨勭浉搴斿浘鏍�
     * @throws APIException 
     * @throws IOException 
     * @see [绫汇�佺被#鏂规硶銆佺被#鎴愬憳]
     */
    public void getIconByType() throws APIException, IOException
    {
        String type=this.get("type");
                
        List<HashMap<String, Object>> result =   activityService.getIconByType(type);
        Map<String,Object> ll = new HashMap<String,Object>();
        ll.put("list", result);
        printJosn(ll);
    }
    
    /**
     * 删除活动
     * @throws IOException 
     */
    
    public void deleteActivity() throws IOException{
    	String id=this.get("id");
	 	JSONObject result =activityService.deleteActivity(id);
     	printJosn(result);
    }
    //获取所有的老虎机
    public void getAllSlots() throws IOException{
    	JSONArray result =activityService.getAllSlots();
    	printJosn(result);
    }
    //获取第一个老虎机的 所有ICON ,主要用于初始化
    public void getAllSlotIocns() throws IOException{
    	JSONArray result =activityService.getAllSlotIocns();
    	printJosn(result);
    }
}

