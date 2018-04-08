package com.ami.api.init;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ami.api.db.DBManagerService;

/**
 * 
 * 
 * @author zhuweiliang
 * @version [版本号, 2013-6-21]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Scope("prototype")
@Component
public class CheckDB implements Runnable
{
    private static Logger logger = Logger.getLogger(CheckDB.class);
    
    @Autowired
    private DBManagerService db;
    
    @Override
    public void run()
    {
        
    }
    
}
