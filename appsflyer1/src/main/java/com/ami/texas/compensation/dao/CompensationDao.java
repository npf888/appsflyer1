package com.ami.texas.compensation.dao;

import org.springframework.stereotype.Component;

import com.ami.api.common.Pager;
import com.ami.api.exception.APIException;
import com.ami.api.web.dao.BaseMysqlDao;

/**
 * 
 * 全服奖励
 * 
 * @author  Netherfire
 * @version  [版本号, Aug 12, 2015]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component
public class CompensationDao extends BaseMysqlDao
{

    /**
     * 获取所有活动
     * 
     * @param pager
     * @return
     * @throws APIException
     * @see [类、类#方法、类#成员]
     */
    public Pager query(Pager pager) 
        throws APIException
    { 
        StringBuffer sql = new StringBuffer(" select id, title, content, itempack, ");           

        sql.append(" if(updatetime=0, '-' , FROM_UNIXTIME(updatetime/1000,'%Y-%m-%d %H:%i:%s')) as update_time,");
        sql.append(" if(createtime=0, '-' , FROM_UNIXTIME(createtime/1000,'%Y-%m-%d %H:%i:%s')) as create_time");
        sql.append(" from texas.t_compensation ");
        pager = this.db.queryPage(sql.toString(), new Object[]
        {}, pager);
        
        return pager; 
    }


    /**
     * 获取所有活动
     * 
     * @param pager
     * @return
     * @throws APIException
     * @see [类、类#方法、类#成员]
     */
    public Pager queryUser(Pager pager) 
        throws APIException
    { 
        StringBuffer sql = new StringBuffer(" select id, title, content, itempack, ");           

        sql.append(" if(updatetime=0, '-' , FROM_UNIXTIME(updatetime/1000,'%Y-%m-%d %H:%i:%s')) as update_time,");
        sql.append(" if(createtime=0, '-' , FROM_UNIXTIME(createtime/1000,'%Y-%m-%d %H:%i:%s')) as create_time");
        sql.append(" from texas.t_compensation ");
        pager = this.db.queryPage(sql.toString(), new Object[]
        {}, pager);
        
        return pager; 
    }
}
