package com.ami.sys.adminlog.dao;

import org.springframework.stereotype.Component;

import com.ami.api.common.Pager;
import com.ami.api.exception.APIException;
import com.ami.api.utill.DateTools;
import com.ami.api.utill.StringTool;
import com.ami.api.web.dao.BaseDao;

/**
 * 
 * 查询用户信息
 * 
 * @author zhang
 * @version [版本号, 2013-5-30]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component
public class AdminLogDao extends BaseDao
{
    /**
     * 查询登陆日志
     * 
     * @param starttime 开始时间
     * @param endtime 截止时间
     * @param pager
     * @return
     * @throws APIException
     * @see [类、类#方法、类#成员]
     */
    public Pager queryForPager(String starttime, String endtime, Pager pager)
        throws APIException
    {
        StringBuffer sql =
            new StringBuffer().append("select  a.adminid, a.oprate,a.time_stamp,a.result,a.remoteip,b.username ")
                .append("  from  sys_admin_texas.admin_log  a,sys_admin_texas.pre_common_member b ")
                .append(" where  a.adminid=b.account ");
        if (!StringTool.isEmpty(starttime))
        {
            sql.append(" and a.time_stamp >=" + "'" + starttime + "'");
        }
        if (!StringTool.isEmpty(endtime))
        {
            sql.append(" and a.time_stamp<=" + "'" + endtime + "'");
        }
        sql.append(" order by a.time_stamp desc");
        
        pager = this.db.queryPage(sql.toString(), new Object[]
        {}, pager);
        return pager;
    }
    
    /**
     * 记log
     * 
     * @param adminid 操作员ID
     * @param desc 操作行为
     * @param remotip 远程IP
     * @param result 操作结果
     * @throws APIException
     * @see [类、类#方法、类#成员]
     */
    public void addLog(String adminid, String desc, String remotip,
        String result)
        throws APIException
    {
        String time_stamp = DateTools.getCurrentDate("yyyy-MM-dd HH:mm:ss");
        String sql =
            "insert into sys_admin_texas.admin_log(adminid,oprate,time_stamp,result,remoteip) values(?,?,?,?,?);";
        
        Object par[] = new Object[]
        {adminid, desc, time_stamp, result, remotip};
        
        this.db.update(sql, par);
        
    }
    
}
