package com.ami.api.common;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ami.api.db.DBDataSource;
import com.ami.api.db.DBManagerService;
import com.ami.api.exception.APIException;
import com.ami.api.init.CheckDB;

/**
 * 
 * 系统定时任务执行
 * 
 * @author zhuweiliang
 * @version [版本号, 2013-7-5]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Scope("prototype")
@Component
public class SystemTask implements Runnable
{
    
    private static Logger logger = Logger.getLogger(CheckDB.class);
    
    @Autowired
    private DBManagerService db;
    
    @Override
    public void run()
    {
        try
        {
            
            task();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        catch (Throwable e)
        {
            e.printStackTrace();
        }
        
    }
    
    //
    public void task()
    {
        try
        {
            // 
            checkdb();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
    }
    
    /**
     * 检查数据库连接是否可用，不可用 则重新创建连接
     * 
     * @see [类、类#方法、类#成员]
     */
    public void checkdb()
    {
        try
        {
            db.load("select * from sys_admin_texas.sys ");
        }
        catch (APIException e)
        {
            logger.error("DB ERROR" + e.getMessage());
            
            DBDataSource dbsource = new DBDataSource();
            try
            {
                db.setDataSource(dbsource.getDataSource());
            }
            catch (Exception e2)
            {
                
            }
        }
    }
    
}
