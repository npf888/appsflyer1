package com.ami.texas.activity.service;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ami.api.common.Pager;
import com.ami.api.exception.APIException;
import com.ami.api.utill.DateTools;
import com.ami.texas.activity.dao.ActivityDao;
import com.ami.texas.activity.pojo.ActivityForm;
import com.ami.texas.activity.pojo.ActivityVO;

/**
 * 
 * 活动配置相关
 * <功能详细描述>
 * 
 * @author  Cici
 * @version  [版本号, Aug 24, 2015]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component
public class ActivityService 
{
    @Autowired
    ActivityDao activityDao;
    /**
     * 分页查询月卡信息
     * 
     * @param pager
     * @param useraccount 用户账号
     * @param username 用户姓名
     * @return
     * @throws APIException
     * @see [类、类#方法、类#成员]
     */
    public Pager queryActivity(Pager pager)
        throws APIException
    {       
        pager = activityDao.queryActivity(pager);
        return pager;
    }

    /**
     * 修改活动信息
     * 
     * @param parameters
     * @param id
     * @throws APIException
     * @see [类、类#方法、类#成员]
     */
    public void updateActivity(Map<String, Object> parameters, String id)
        throws APIException
    {   
        // 调用Dao 更新
        activityDao.updateActivity(parameters, id);
        
    }
    
    /**
     * 根据id获取活动
     * 
     * @param id
     * @return
     * @throws APIException
     * @see [类、类#方法、类#成员]
     */
    public Map<String, Object> loadActivityByID(String id) throws APIException
    {
        Map<String, Object> result = activityDao.loadActivityByID(id);
        
        return result;
    }
    
    /**
     * 添加触发条件/奖励
     * 
     * @param params
     * @throws APIException
     * @see [类、类#方法、类#成员]
     */
    public void doAddCondition(Map<String, Object> params) throws APIException
    {
        String date = DateTools.getCurrentDate("yyyy-MM-dd HH:mm:ss");
        // 2, 更新时间
        params.put("create_time", date);
        activityDao.addCondition(params);
    }
    
    /**
     * 根据活动id删除活动
     * 
     * @param id
     * @return
     * @throws APIException
     * @throws ParseException
     * @see [类、类#方法、类#成员]
     */
    public String doDelete(String id) throws APIException, ParseException
    {
        String date = DateTools.getCurrentDate("yyyy-MM-dd HH:mm:ss"); 
        Long now = DateTools.timeStr2Long(date, "yyyy-MM-dd HH:mm:ss");
        Map<String, Object> result = activityDao.loadActivityByID(id);

            activityDao.doDelete(id);
            return"删除成功";

    }

    /**
     * 获取所有触发条件
     * 
     * @return
     * @throws APIException
     * @see [类、类#方法、类#成员]
     */
    public Map<String, Object> queryTriggers() throws APIException
    {
        List<HashMap<String, Object>> list = activityDao.queryConditions("T");
        
        Map<String, Object> role = new HashMap<String, Object>();
        
        for (HashMap<String, Object> hashMap : list)
        {
            role.put(hashMap.get("id") + "", hashMap.get("title"));
        }
        return role;
    }

    /**
     * 获取所有奖励
     * 
     * @return
     * @throws APIException
     * @see [类、类#方法、类#成员]
     */
    public Map<String, Object> queryRewards() throws APIException
    {
        List<HashMap<String, Object>> list = activityDao.queryConditions("R");
        
        Map<String, Object> role = new HashMap<String, Object>();
        
        for (HashMap<String, Object> hashMap : list)
        {
            role.put(hashMap.get("condition_id") + "", hashMap.get("title"));
        }
        return role;
    }
    
    /**
     * 根据活动类型获取触发条件
     * 
     * @return
     * @throws APIException
     * @see [类、类#方法、类#成员]
     */
    public Map<String, Object> getTriggersByType(String activity_type) throws APIException
    {
        List<HashMap<String, Object>> list = activityDao.getTriggersByType(activity_type);
        
        Map<String, Object> role = new HashMap<String, Object>();
        
        for (HashMap<String, Object> hashMap : list)
        {
            role.put(hashMap.get("condition_id") + "", hashMap.get("title"));
        }
        return role;
    }

	public List<HashMap<String, Object>> getIconByType(String type) throws APIException {
		List<HashMap<String, Object>> list = activityDao.getIconByType(type);
		return list;
	}
	/**
	 * 保存 添加 或修改的 活动
	 * @param type
	 * @return
	 * @throws APIException
	 */
	public JSONObject saveOrUpdate(ActivityForm from) throws APIException {
		return activityDao.saveOrUpdate(from);
	}

	public JSONObject deleteActivity(String id) {
		return activityDao.deleteActivity(id);
	}

	public JSONArray getAllSlots() {
		return activityDao.getAllSlots();
	}

	public JSONArray getAllSlotIocns() {
		return activityDao.getAllSlotIocns();
	}

	public ActivityVO detail(String activityId) {
		return activityDao.detail(activityId);
	}

	public List<HashMap<String,Object>> getActivityCondition() {
		return activityDao.getActivityCondition();
		
	}
}
