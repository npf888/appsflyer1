package com.ami.api.init;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.ServletContextAware;

import com.ami.api.common.AppConstant;
import com.ami.api.common.SystemTask;
import com.ami.api.db.DBDataSource;
import com.ami.api.db.DBManagerService;
import com.ami.api.db.DBManagerService_Mysql;
import com.ami.api.db.DBManagerService_mdsp;
import com.ami.api.exception.APIException;
import com.ami.common.schedule.ScheduleImpl;
import com.ami.mail.ScheduleSendMail;
import com.ami.sys.right.service.RightService;
import com.ami.weixin.course.schedule.MyTask1;
import com.ami.weixin.course.schedule.MyTask2;
import com.ami.weixin.course.util.UrlProperties;

/**
 * 
 * 
 * @author zhuweiliang
 */

public class SystemInit implements ServletContextAware
{
    private final static Logger logger = Logger.getLogger(SystemInit.class);
    
    private ApplicationInitInte applicationInitInte;
    
    private ServletContext servletContext;
    
    @Autowired
    private SystemTask systemTask;
    
    @Autowired
    private DBManagerService db;
    
    @Autowired
    private DBManagerService_Mysql mysql_db;
    
    @Autowired
    private DBManagerService_mdsp feedback_db;
    
    @Autowired
    RightService rightService;
    @Autowired
    MyTask2 myTask2;
    @Autowired
    MyTask1 myTask1;
    @Autowired
    ScheduleSendMail scheduleSendMail;
    
    public SystemInit()
    {
    }
    
    @Override
    public void setServletContext(ServletContext arg0)
    {
        this.servletContext = arg0;
        
        contextInitialized(arg0);
    }
    
    public void contextInitialized(ServletContext arg0)
    {
        try
        {
        	ClassLoader classloader = arg0.getClassLoader();
            
            Log4jInit.init(classloader.getResource("deploy/log4j.properties").getPath());
            
            
            String dbconfPath = classloader.getResource("deploy/system.properties").getPath();
            logger.info("load path -------dbconfPath--------:::"+dbconfPath);
            
            
            String mysql_db_path = classloader.getResource("deploy/system_mysql.properties").getPath();
            logger.info("load path -------mysql_db_path--------:::"+mysql_db_path);

            
            String mdsp_feedback_path = classloader.getResource("deploy/mdsp_feedback.properties").getPath();
            logger.info("load path -------mdsp_feedback_path--------:::"+mdsp_feedback_path);
            
            String backstage = classloader.getResource("conf/backstage.properties").getPath();
            logger.info("load path -------backstage--------:::"+backstage);
            
            String game_server = classloader.getResource("conf/game_server.properties").getPath();
            logger.info("load path -------game_server--------:::"+game_server);
            
            
            initDataSource(dbconfPath);
            
            initaccountDataSource(mysql_db_path);
            
            initfeedbackDataSource(mdsp_feedback_path);
            
            
            intiRole();
            
           
            
            initSystem(dbconfPath);
            
           
            
            
            ScheduledExecutorService writeScheduler = Executors.newSingleThreadScheduledExecutor();
            
            final ScheduledFuture creatFile = writeScheduler.scheduleAtFixedRate(systemTask, 15, 10, TimeUnit.SECONDS);
            
            applicationInitInte.contextInitialized(servletContext,dbconfPath);
            setTimeDS();
            UrlProperties urlProperties = new UrlProperties(backstage,game_server);
            urlProperties.setBackstageUrls();
            urlProperties.setGameServerUrls();
            servletContext.setAttribute("urlProperties", urlProperties);
            logger.info("urlProperties-BackstageUrlMap:::"+urlProperties.getBackstageUrlMap().size());
            logger.info("urlProperties-GameServerUrlMap:::"+urlProperties.getGameServerUrlMap().size());
        }
        catch (Exception e)
        {
            logger.error("system start fail ", e);
            System.exit(0);
            
        }
    }
    private void setTimeDS(){
    	
    	//用来调度发送邮件的  每隔 12 小时发送一次邮件  12*60*60*1000
    	new ScheduleImpl(scheduleSendMail,12*60*60*1000).start();
    	/**
    	 * 1 监视定时发送消息 在线人数，新增人数
    	 */
    	//设置定时器 myTask1
//    	new ScheduleImpl(myTask1,900000).start();
    	
    	/**
    	 * 2 定时监听 在线人数，如果到达一定人数，就发警报
    	 */
    	//设置定时器 myTask2
//    	new ScheduleImpl(myTask2,60000).start();
    }
    /**
     * 
     * @param ctx
     * @param dbconfPath
     * @throws IOException
     */
    private void initDataSource(String dbconfPath)
        throws IOException
    {
        
        DBDataSource dbsource = new DBDataSource();
     
        dbsource.init(dbconfPath);
        
        db.setDataSource(dbsource.getDataSource());
        
        logger.info("1 ,load database info success!!!");
        
    }
    
    /**
     * 
     * @param ctx
     * @param dbconfPath
     * @throws IOException
     * @throws SQLException
     */
    private void initaccountDataSource(String dbconfPath)
        throws IOException, SQLException
    {
        
        DBDataSource dbsource_2 = new DBDataSource();
        dbsource_2.init(dbconfPath);
        
        mysql_db.setDataSource(dbsource_2.getDataSource());
        
        logger.info("2 ,load mysql database info success!!!");
        
    }
    
	private void initfeedbackDataSource(String dbconfPath) throws IOException, SQLException 
	{

		DBDataSource dbsource_3 = new DBDataSource();
		dbsource_3.init(dbconfPath);

		feedback_db.setDataSource(dbsource_3.getDataSource());

		logger.info("3 ,load feedback database info success!!!");

	}
    
    /**
     * 
     * @param path
     * @throws IOException
     */
    public void initSystem(String path)
        throws IOException
    {
        
        // PropertyUtill c = new PropertyUtill(path);
        
    }
    
    /**
     * 
     * @param servletContext
     * @param ctx
     * @throws APIException
     */
    private void intiRole()
        throws APIException
    {
        
        Map<String, Map<String, Object>> roleMap = rightService.intiRole();
        
        AppConstant.roleMap = roleMap;
        
        servletContext.setAttribute("roleMap", rightService.intiRole());
        
        servletContext.setAttribute("allNavMap", rightService.loadAllNav());
    }
    
    public void setApplicationInitInte(ApplicationInitInte applicationInitInte)
    {
        this.applicationInitInte = applicationInitInte;
    }
    
}
