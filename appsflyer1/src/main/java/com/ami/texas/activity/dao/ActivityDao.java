package com.ami.texas.activity.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ami.api.common.AppConstant;
import com.ami.api.common.Pager;
import com.ami.api.exception.APIException;
import com.ami.api.utill.HttpURLConnectionUtil;
import com.ami.api.web.dao.BaseMysqlDao;
import com.ami.common.HttpClientUtil;
import com.ami.texas.activity.pojo.ActivityForm;
import com.ami.texas.activity.pojo.ActivityVO;

/**
 * 
 * 活动配置相关
 * 
 * @author  Netherfire
 * @version  [版本号, Aug 12, 2015]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component
public class ActivityDao extends BaseMysqlDao
{
	
	 private static Logger logger = Logger.getLogger(ActivityDao.class);
    /**
     * 获取所有活动
     * 
     * @param pager
     * @return
     * @throws APIException
     * @see [类、类#方法、类#成员]
     */
    public Pager queryActivity(Pager pager) 
        throws APIException
    { 
        StringBuffer sql = new StringBuffer(" select id, activitytype, title, activitydesc, pagelink, rulepack, daily, "); 
            sql.append(" if(starttime=0, '-' , FROM_UNIXTIME(starttime/1000,'%Y-%m-%d %H:%i:%s')) as start_time,");
            sql.append(" if(endtime=0, '-' , FROM_UNIXTIME(endtime/1000,'%Y-%m-%d %H:%i:%s')) as end_time,");
            sql.append(" if(updatetime=0, '-' , FROM_UNIXTIME(updatetime/1000,'%Y-%m-%d %H:%i:%s')) as update_time,");
            sql.append(" if(createtime=0, '-' , FROM_UNIXTIME(createtime/1000,'%Y-%m-%d %H:%i:%s')) as create_time");
            sql.append(" from texas.t_activity ");           

        pager = this.db.queryPage(sql.toString(), new Object[]
        {}, pager);
        
        return pager; 
    }
    
    /**
     *添加触发条件/奖励
     * 
     * @param params
     * @return
     * @throws APIException
     * @see [类、类#方法、类#成员]
     */
    public String addCondition(Map<String, Object> params)
        throws APIException
    {
        this.table.insert(" texas_activity.activity_condition ", params);
        
        return "";
    }
    
    /**
     * 根据活动id删除活动 如果活动在一个小时之内就要开始则不能删除
     * 
     * @param id
     * @throws NumberFormatException
     * @throws APIException
     * @see [类、类#方法、类#成员]
     */
    public void doDelete(String id) throws NumberFormatException, APIException
    {
        String sql = "delete from texas.t_activity where id = ?";
        this.db.update(sql, new Object[]{Integer.parseInt(id)});
    }
    /**
     * 修改活动信息
     * 
     * @param params
     * @param id
     * @throws APIException
     * @see [类、类#方法、类#成员]
     */
    public void updateActivity(Map<String, Object> params, String id)
        throws APIException
    {
        this.table.update("texas_activity.activity", params, id);
        
    }
    
    /**
     * 根据id获取活动
     * 
     * @param id
     * @return
     * @throws APIException
     * @see [类、类#方法、类#成员]
     */
    public Map<String, Object> loadActivityByID(String id)
        throws APIException
    {
        String sql = "select * from texas.t_activity where id = ?";
        
        return this.db.load(sql, new Object[]
        {id});
    }
    
    /**
     * 获取所有触发条件和奖励
     * 
     * @return
     * @throws APIException
     * @see [类、类#方法、类#成员]
     */
    public List<HashMap<String, Object>> queryConditions(String type)
        throws APIException
    {
        StringBuffer sql =
            new StringBuffer("select condition_id, title from texas_activity.activity_condition where type=?");
        List<HashMap<String, Object>> list = this.db.query(sql.toString(), new Object[]{type});
        return list;
        
    }

    /**
     * 获取指定活动类型的触发条件
     * 
     * @return
     * @throws APIException
     * @see [类、类#方法、类#成员]
     */
    public List<HashMap<String, Object>> getTriggersByType(String activity_type)
        throws APIException
    {
        StringBuffer sql =
            new StringBuffer("select condition_id, title from texas_activity.activity_condition where activity_type_id=? and type='T'");
        List<HashMap<String, Object>> list = this.db.query(sql.toString(), new Object[]{activity_type});
        return list;
        
    }

	public List<HashMap<String, Object>> getIconByType(String type) throws APIException {
		 StringBuffer sql =
		            new StringBuffer("select lang_desc as langDesc,turn from texas_activity.t_slot_icon where slots_num=?");
         List<HashMap<String, Object>> list = this.db.query(sql.toString(), new Object[]{type});
         return list;
	}

	public JSONObject saveOrUpdate(ActivityForm from) {
		JSONObject jb = new JSONObject();
		String url  = "";
		try {
			int s = this.table.insertF("texas.t_activity", from.toFiled(from));
			List<HashMap<String,Object>> list = this.db.query("select max(id) as id from texas.t_activity");
			jb.put("errorCode", 0);
    		if(list != null && list.size()>0){
    			Map<String,Object> idMap = list.get(0);
    			long id = (long)idMap.get("id");
    			url =  AppConstant.SERVER_URL_HTTP+"api/updateActivity?id="+id;
    			HttpURLConnectionUtil.get(url);
    		}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("此访问 url 异常："+url);
		}
		return jb;
	}

	public JSONObject deleteActivity(String id) {
		JSONObject jb = new JSONObject();
		String url  = "";
		try {
			this.table.delete("texas.t_activity", id);
			url =  AppConstant.SERVER_URL_HTTP+"api/updateActivity";
			HashMap<String,String> params = new HashMap<String,String>();
			params.put("id", id);
			HttpClientUtil.postUrl(url, params);
			jb.put("errorCode", 0);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("此访问 url 异常："+url);
		}
		return jb;
	}

	public JSONArray getAllSlots() {
		try {
			List<HashMap<String, Object>> slotNames = this.db.query("select type,langDesc from texas.t_slot_list group by type");
			JSONArray slotJSON = (JSONArray)JSONObject.toJSON(slotNames);
			return slotJSON;
		} catch (APIException e) {
			e.printStackTrace();
		}
		return null;
	}

	public JSONArray getAllSlotIocns() {
		try {
			List<HashMap<String, Object>> slotNames = this.db.query("select turn,lang_desc as langDesc from texas_activity.t_slot_icon where slots_num = 1");
			JSONArray slotJSON = (JSONArray)JSONObject.toJSON(slotNames);
			return slotJSON;
		} catch (APIException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ActivityVO detail(String activityId) {
		try {
			List<ActivityVO> activityVOList = this.db.query("select * from texas.t_activity where id="+activityId,ActivityVO.class);
			if(activityVOList!= null && activityVOList.size() >0){
				return activityVOList.get(0);
			}
		} catch (APIException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<HashMap<String, Object>> getActivityCondition() {
		try {
			List<HashMap<String,Object>> aconditionList = this.db.query("select * from texas_activity.activity_condition");
			if(aconditionList!= null && aconditionList.size() >0){
				return aconditionList;
			}
		} catch (APIException e) {
			e.printStackTrace();
		}
		return null;
	}
    
}
