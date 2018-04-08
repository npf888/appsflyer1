package com.ami.sys.adminlog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ami.api.common.Pager;
import com.ami.api.exception.APIException;
import com.ami.sys.adminlog.AdminLogConstant;
import com.ami.sys.adminlog.dao.AdminLogDao;

/**
 * 
 * 定额管理 逻辑类
 * 
 * @author
 * @version [版本号, 2013-7-10]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component
public class AdminLogService
{
    
    @Autowired
    private AdminLogDao adminLogDao;
    
    /**
     * 查询登陆日志
     * 
     * @param starttime 开始时间
     * @param endtime   截止时间
     * @return
     * @throws APIException
     * @see [类、类#方法、类#成员]
     */
    public Pager queryLogrecord(String starttime,String endtime,Pager pager)
        throws APIException
    {
        pager = adminLogDao.queryForPager(starttime,endtime,pager);
        return pager;
    }
    
    /**
     * 记录log
     * 
     * @param action 模块 action
     * @param method 方法
     * @param remotip 远程ip
     * @param result 执行结果
     * @see [类、类#方法、类#成员]
     */
    public void addLog(String adminid, String action, String method, String remotip, String result)
    {
        String key = action + "." + method;
        
        String desc = AdminLogConstant.actionMap.get(key);
        
        if (null != desc && adminid != null)
        {
            try
            {
                adminLogDao.addLog(adminid, desc, remotip, result);
            }
            catch (APIException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    
}
