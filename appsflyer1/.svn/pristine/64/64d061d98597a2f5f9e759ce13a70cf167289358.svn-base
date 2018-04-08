package com.ami.texas.condition.service;

import java.text.ParseException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ami.api.common.Pager;
import com.ami.api.exception.APIException;
import com.ami.api.utill.DateTools;
import com.ami.texas.condition.dao.ConditionDao;

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
public class ConditionService 
{
    @Autowired
    ConditionDao conditionDao;
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
        pager = conditionDao.query(pager);
        return pager;
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
        conditionDao.addCondition(params);
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
    public void doDelete(String id) throws APIException, ParseException
    {
        conditionDao.doDelete(id);           
    }
}
