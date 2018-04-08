package com.ami.common;

import java.io.IOException;
import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.ami.api.common.AppConstant;
import com.ami.api.init.ApplicationInitInte;
import com.ami.api.utill.PropertyUtill;

/**
 * 
 * 应用程序初始化
 * 
 * @author zhuweiliang
 * @version [版本号, Apr 17, 2012]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */

@Component
public class LettersInit implements ApplicationInitInte
{
    private final static Logger logger = Logger.getLogger(LettersInit.class);
    
    private ServletContext context;
    
    /**
     * 初始化监听器,解析配置文件
     * 
     * @throws IOException
     */
    @Override
	public void contextInitialized(ServletContext context,String systemPath)
        throws Exception
    {
        
        this.context = context;
        
        // 初始化系统配置
//        String systemPath = AppConstant.systemDir + "/deploy/system.properties";
        
        initGtp(systemPath);
        
    }
    
    /**
     * 初始化系统配置项
     * 
     * @param path
     * @throws IOException
     */
    public void initGtp(String path)
        throws IOException
    {
        
        PropertyUtill c = new PropertyUtill(path);
        
        logger.debug("initImss success");
    }
    
}
