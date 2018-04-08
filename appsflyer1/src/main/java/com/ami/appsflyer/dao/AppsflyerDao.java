package com.ami.appsflyer.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ami.api.common.Pager;
import com.ami.api.exception.APIException;
import com.ami.api.utill.StringTool;
import com.ami.api.web.dao.BaseDao;

/**
 * 
 * 联系点管理
 * 
 * @author zhuweiliang
 * @version [版本号, 2013-10-14]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component
public class AppsflyerDao extends BaseDao
{
    
    // ////END ge_en.common_character******************////
    
    // //// ge_en.common_character******************////
    /**
     * 分页查询
     * 
     * @param param
     * @param pager
     * @return
     * @throws APIException
     */
    public Pager queryForPage(Map<String, String> param, Pager pager)
        throws APIException
    {
        
        StringBuffer sql = getSql(param);
        
        pager = this.db.queryPage(sql.toString(), new Object[]
        {}, pager);
        
        return pager;
        
    }
    
    private StringBuffer getSql(Map<String, String> param)
    {
        
        String table = "appsflyer.android_fast";
        
        String app_id = param.get("app_id");
        String ac = param.get("ac");
        
        if ("hsfb".equals(ac))
        {
            app_id="id964664459";
        }
        
        if (app_id.startsWith("id"))
        {
            table = "appsflyer.ios_fast";
        }
        
        StringBuffer sql = new StringBuffer("select * from " + table + "  where  ");
       
        sql.append("   app_id = '").append(app_id).append("'  ");
        
        // device_id
        String imei = param.get("imei");
        if (!StringTool.isEmpty(imei))
        {
            if (app_id.startsWith("id"))
            {
                sql.append(" and  idfa  = '").append(imei).append("'  ");
            }
            else
            {
                sql.append(" and  imei = '").append(imei).append("'  ");
            }
        }
        
        String start_date = param.get("start_date");
        if (!StringTool.isEmpty(start_date))
        {
            sql.append(" and  event_time >=  '").append(start_date).append("'  ");
        }
        
        String event_name = param.get("event_name");
        if (!StringTool.isEmpty(event_name))
        {
            sql.append(" and  lower(event_name) =  '").append(event_name.toLowerCase()).append("'  ");
        }
        
        String end_date = param.get("end_date");
        if (!StringTool.isEmpty(end_date))
        {
            sql.append(" and  event_time <=  '").append(end_date).append("'  ");
        }
        
        if ("hsfb".equals(ac))
        {
            sql.append(" and  media_source='Facebook Ads'");
            
        }
        
        sql.append(" order by event_time_us desc  ");
        return sql;
    }
    
    public List<HashMap<String, Object>> queryMatForExport(Map<String, String> param)
        throws APIException
    {
        
        StringBuffer sql = getSql(param);
        
        List<HashMap<String, Object>> list = this.db.query(sql.toString(), new Object[]
        {});
        
        return list;
        
    }
    
    /******************
     * MAT 统计
     * 
     * @throws APIException
     *****************/
    
    public List<HashMap<String, Object>> count(Map<String, String> params)
        throws APIException
    {
        
        String site_id = params.get("site_id");
        
        StringBuffer sql = new StringBuffer();
        
        sql.append("select substring(created,0,11) as  create_date ,count(0),publisher_name from mat.mat_logs where site_id=?");
        
        String start_date = params.get("start_date");
        if (!StringTool.isEmpty(start_date))
        {
            sql.append(" and  created >=  '").append(start_date).append("'  ");
        }
        
        String end_date = params.get("end_date");
        if (!StringTool.isEmpty(end_date))
        {
            sql.append(" and  created <=  '").append(end_date + " 59:59:59").append("'  ");
        }
        
        String notnull_google_aid = params.get("notnull_google_aid");
        if (!StringTool.isEmpty(notnull_google_aid))
        {
            sql.append(" and  google_aid !='' ");
        }
        
        String deviceid_androidid = params.get("deviceid_androidid");
        if (!StringTool.isEmpty(deviceid_androidid))
        {
            sql.append(" and  device_id !='' or os_id !='' or google_aid !='' ");
        }
        
        String notnull_ios_ifa = params.get("notnull_ios_ifa");
        if (!StringTool.isEmpty(notnull_ios_ifa))
        {
            sql.append(" and  ios_ifa !='' ");
        }
        
        sql.append("  group by create_date ,publisher_name order by create_date desc ");
        
        List<HashMap<String, Object>> list = this.db.query(sql.toString(), new Object[]
        {site_id.substring(3)});
        
        return list;
        
    }
    
    public List<HashMap<String, Object>> countNotnull(Map<String, String> params)
        throws APIException
    {
        String site_id = params.get("site_id");
        
        StringBuffer sql = new StringBuffer();
        
        sql.append("select substring(created,0,11) as  create_date , count(0) from own.common_character where  where site_id=?");
        
        String start_date = params.get("start_date");
        if (!StringTool.isEmpty(start_date))
        {
            sql.append(" and  created >=  '").append(start_date).append("'  ");
        }
        
        String end_date = params.get("end_date");
        if (!StringTool.isEmpty(end_date))
        {
            sql.append(" and  created <=  '").append(end_date + " 59:59:59").append("'  ");
        }
        
        String notnull_google_aid = params.get("notnull_google_aid");
        if (!StringTool.isEmpty(notnull_google_aid))
        {
            sql.append(" and  google_aid !='' ");
        }
        
        String deviceid_androidid = params.get("deviceid_androidid");
        if (!StringTool.isEmpty(deviceid_androidid))
        {
            sql.append(" and  device_id !='' or os_id !='' or google_aid !='' ");
        }
        
        String notnull_ios_ifa = params.get("notnull_ios_ifa");
        if (!StringTool.isEmpty(notnull_ios_ifa))
        {
            sql.append(" and  ios_ifa !='' ");
        }
        
        sql.append("   group  order by create_date desc ");
        
        List<HashMap<String, Object>> list = this.db.query(sql.toString(), new Object[]
        {site_id.substring(3)});
        
        return list;
    }
    
}
