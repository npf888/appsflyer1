package com.ami.texas.compensation.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.ami.api.common.AppConstant;
import com.ami.api.common.Pager;
import com.ami.api.exception.APIException;
import com.ami.api.utill.HttpURLConnectionUtil;
import com.ami.api.utill.StringTool;
import com.ami.api.web.action.BaseAction;
import com.ami.common.HttpClientUtil;
import com.ami.texas.activity.service.ActivityService;
import com.ami.texas.compensation.service.CompensationService;


/**
 * 鍏ㄦ湇琛ュ伩
 * 
 * @author Cici
 * @version [鐗堟湰鍙�, 2013-5-29]
 * @see [鐩稿叧绫�/鏂规硶]
 * @since [浜у搧/妯″潡鐗堟湰]
 */
@Scope("prototype")
@Component
public class CompensationAction  extends BaseAction
{

    @Autowired
    CompensationService compensationService;
    
    @Autowired
    ActivityService activityService;
    
    /**
     * 娉ㄩ噴鍐呭
     */
    private static final long serialVersionUID = 1L;

    /**
     * 鑾峰彇鍏ㄦ湇琛ュ伩鍒楄〃
     * 
     * @return
     * @throws Exception 
     * @see [绫汇�佺被#鏂规硶銆佺被#鎴愬憳]
     */
    public String query() throws Exception
    {
        Pager pager = new Pager(this.getReq());
        
        // 璋冪敤閫昏緫绫� 鏌ヨ鍒扮粨鏋�
        pager = compensationService.query(pager);
        
        this.set("PAGER", pager);
        
        return "query";
    }
   
    /**
     * 鑾峰彇鍏ㄦ湇琛ュ伩鍒楄〃
     * 
     * @return
     * @throws Exception 
     * @see [绫汇�佺被#鏂规硶銆佺被#鎴愬憳]
     */
    public String queryUser() throws Exception
    {
        Pager pager = new Pager(this.getReq());
        
        // 璋冪敤閫昏緫绫� 鏌ヨ鍒扮粨鏋�
        pager = compensationService.queryUser(pager);
        
        this.set("PAGER", pager);
        
        return "query";
    }
    
    public void doEdit() throws IOException
    {
        JSONObject jsonobj = new JSONObject();
        
        jsonobj.put("id", 0);
        
        if(!StringTool.isEmpty(this.get("id")))
        {
        	jsonobj.put("id", this.get("id"));
        }

        jsonobj.put("title", this.get("title"));
        String content = this.get("content");
        if(StringUtils.isNotBlank(content)){
        	content=content.replaceAll(",", "，");
        }
        jsonobj.put("content", content);
        
        String itemlist = this.get("itemlist");
        List<Map<String, Object>> itemPack = new ArrayList<Map<String,Object>>();
        String [] itemArray = itemlist.split(";");
        for(int i=0; i<itemArray.length;i++)
        {
        	Map<String, Object> reward = new HashMap<String, Object>();
        	String [] rewardsplit = itemArray[i].split(":");
        	reward.put("rewardId", Integer.parseInt(rewardsplit[0]));
        	reward.put("rewardCount",Integer.parseInt(rewardsplit[1]));
        	itemPack.add(reward);
        }
        jsonobj.put("itemList", itemPack);

        
//        String url = AppConstant.SERVER_URLGM+"api/compensation/update.json";
        
        String url = AppConstant.SERVER_URL_HTTP+"api/rewardNotice";
        
        String rresult;
        JSONObject result;
        try
        {
        	rresult = HttpClientUtil.postJSONUrl(url, jsonobj.toJSONString());
        	result=(JSONObject)JSONObject.parse(rresult);
        	if(result.get("errorCode") == null){
        		print("{'errorCode':1}");
        	}else{
        		print(result.get("errorCode").toString());
        	}
        }
        catch (Exception e)
        {
            print("Error!" +e);
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       
    }
    
    public String toEdit() throws APIException
    {
        Map<String, Object> rewards = activityService.queryRewards();
        this.set("rewards", rewards);
    	
    	return "edit";
    }
   
	public void doDelete() throws Exception
	{
		String id = this.get("id");
		String url = AppConstant.SERVER_URL+"api/compensation/delete.json";
        JSONObject jsonobj = new JSONObject();
        jsonobj.put("id", Integer.parseInt(id));
        
        JSONObject result = HttpURLConnectionUtil.post(url, jsonobj);
        System.out.println(result);
	}
	
	
	   
    public void doAddUserRefund() throws IOException
    {
        JSONObject jsonobj = new JSONObject();
        
        jsonobj.put("id", 0);
        
        if(!StringTool.isEmpty(this.get("id")))
        {
        	jsonobj.put("id", this.get("id"));
        }

        jsonobj.put("title", this.get("title"));
        
        jsonobj.put("charId", Integer.parseInt(this.get("charId")));
        jsonobj.put("content", this.get("content"));
        
        String itemlist = this.get("itemlist");
        List<Map<String, Object>> itemPack = new ArrayList<Map<String,Object>>();
        String [] itemArray = itemlist.split(";");
        for(int i=0; i<itemArray.length;i++)
        {
        	Map<String, Object> reward = new HashMap<String, Object>();
        	String [] rewardsplit = itemArray[i].split(":");
        	reward.put("rewardId", Integer.parseInt(rewardsplit[0]));
        	reward.put("rewardCount",Integer.parseInt(rewardsplit[1]));
        	itemPack.add(reward);
        }
        jsonobj.put("itemList", itemPack);

        //鍘熷厛go璇█鐨勬帴鍙�
//        String url = AppConstant.SERVER_URLGM+"api/human/refund.json";
        String url = AppConstant.SERVER_URL_HTTP+"api/rewardNotice";
        
        String rresult;
        JSONObject result;
        try
        {
        	rresult = HttpClientUtil.postJSONUrl(url, jsonobj.toJSONString());
        	result=(JSONObject)JSONObject.parse(rresult);
            print(result.get("errorCode").toString());
        }
        catch (Exception e)
        {
            print("Error!" +e);
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       
    }
	public String addUserCompensation() throws APIException
	{
		Map<String, Object> rewards = activityService.queryRewards();
        this.set("rewards", rewards);
		return "addUserCompensation";
	}
}
