package com.ami.texas.news.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ami.api.common.Pager;
import com.ami.api.exception.APIException;
import com.ami.api.web.dao.BaseMysqlDao;

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
public class NewsDao extends BaseMysqlDao
{
    /**
     * 获取所有活动
     * 
     * @param pager
     * @return
     * @throws APIException
     * @see [类、类#方法、类#成员]
     */
    public Pager queryNews(Pager pager) 
        throws APIException
    { 
        StringBuffer sql = new StringBuffer(" select id, content, intervaltime, dailystarttime, dailyendtime, "); 
        
        sql.append(" if(starttime=0, '-' , FROM_UNIXTIME(starttime/1000,'%Y-%m-%d %H:%i:%s')) as start_time,");
        sql.append(" if(endtime=0, '-' , FROM_UNIXTIME(endtime/1000,'%Y-%m-%d %H:%i:%s')) as end_time,");
        sql.append(" if(updatetime=0, '-' , FROM_UNIXTIME(updatetime/1000,'%Y-%m-%d %H:%i:%s')) as update_time,");
        sql.append(" if(createtime=0, '-' , FROM_UNIXTIME(createtime/1000,'%Y-%m-%d %H:%i:%s')) as create_time");
        sql.append(" from texas.t_notice where isDelete=0");             

        pager = this.db.queryPage(sql.toString(), new Object[]
        {}, pager);
        
        return pager; 
    }

    public void updateNotice(Map<String, Object> params)throws APIException{
    	
    	 this.table.insert("texas.t_notice", params);
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
        String sql = "select *  from texas_activity.activity where id = ?";
        
        return this.db.load(sql, new Object[]
        {id});
    }
    
    public void deleteNews(int id) throws APIException{
        String sql = "  from texas.t_notice where id = ?";
        this.db.update(sql, new Object[]{id});
    }

	public long getLastNoticeId() throws APIException {
		List<HashMap<String,Object>> idMap = this.db.query(" select id from texas.t_notice  order by id desc limit 0,1");
		if(idMap != null && idMap.size()>0){
			return (Long)idMap.get(0).get("id");
		}
		return 0l;
	}
}
