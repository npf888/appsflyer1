package com.ami.api.db;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;

import com.ami.api.utill.PropertyUtill;

/**
 * 获取数据源 <一句话功能简述> <功能详细描述>
 * 
 * @author 朱伟亮
 * @version [版本号, Apr 13, 2012]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class DBDataSource extends BasicDataSource
{
    
    private final static Logger logger = Logger.getLogger(DBDataSource.class);
    
    /**
     * 数据库所在机器IP
     */
    public static String URL = "";
    
    public static String DRIVER = "";
    
    /**
     * 数据库帐号
     */
    public static String USERNAME = "";
    
    /**
     * #数据库密码
     */
    public static String USERPWD = "";
    
    /**
     * #最大可用连接数
     */
    public static int maxActive = 30;
    
    /**
     * 连接池中可以最大的保持不被释放的连接数据 <功能详细描述>
     * 
     * @see [类、类#方法、类#成员]
     */
    public static int maxIdle = 10;
    
    /**
     * 最长等待时间 10秒
     */
    public static int MaxWait = 10000;
    
    /**
     * #初始连接数
     */
    public static int initialSize = 2;
    
    public DBDataSource()
    {
        
    }
    
    public DBDataSource(String DRIVER, String url, String USERNAME,
        String USERPWD)
    {
        DBDataSource.DRIVER = DRIVER;
        DBDataSource.URL = url;
        DBDataSource.USERNAME = USERNAME;
        DBDataSource.USERPWD = USERPWD;
        DBDataSource.initialSize = 5;
        
    }
    
    public void init(String path)
        throws IOException
    {
        
        PropertyUtill c = new PropertyUtill(path);
        DRIVER = c.getValue("driver");
        URL = c.getValue("url");
        USERNAME = c.getValue("userName");
        USERPWD = c.getValue("userPwd");
        
    }
    
    /**
     * 获取数据源<一句话功能简述> <功能详细描述>
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public DataSource getDataSource()
    {
        
        DataSource ds = null;
        
        super.setDriverClassName(DRIVER);
        super.setUrl(URL);
        super.setUsername(USERNAME);
        super.setPassword(USERPWD);
        super.setInitialSize(initialSize);
        super.setMaxActive(maxActive);
        super.setMaxIdle(maxIdle);
        super.setMaxWait(MaxWait);
        
        try
        {
            ds = super.createDataSource();
        }
        catch (SQLException e)
        {
            logger.debug("getDataSource SQLException:", e);
        }
        
        return ds;
    }
    
}