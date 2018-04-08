package com.ami.texas.condition.dao;

import org.springframework.stereotype.Component;
import java.util.Map;

import com.ami.api.web.dao.BaseMysqlDao;
import com.ami.api.common.Pager;
import com.ami.api.exception.APIException;

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
public class ConditionDao extends BaseMysqlDao
{
    /**
     * 获取所有
     * 
     * @param pager
     * @return
     * @throws APIException
     * @see [类、类#方法、类#成员]
     */
    public Pager query(Pager pager) 
        throws APIException
    { 
        String sql = "select * from texas_activity.activity_condition ";           

        pager = this.db.queryPage(sql, new Object[]
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
     * 删除
     * 
     * @param id
     * @throws NumberFormatException
     * @throws APIException
     * @see [类、类#方法、类#成员]
     */
    public void doDelete(String id) throws NumberFormatException, APIException
    {
        String sql = "delete from texas_activity.activity_condition where id = ?";
        this.db.update(sql, new Object[]{Integer.parseInt(id)});
    }

}
