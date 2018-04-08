package com.ami.sys.adminlog;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 日志常量类
 * 
 * @author zhuweiliang
 * @version [版本号, 2013-7-24]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class AdminLogConstant
{
    /**
     * 操作对照 key action.method value=描述
     */
   public static Map<String, String> actionMap = new HashMap<String, String>();
    
    static
    {
        actionMap.put("appsflyer.logs", "query ---- Appsflyer-Logs");
        actionMap.put("config.dictrionary", "系统配置 ----字典表管理>>信息查询");
        actionMap.put("config.doEdit", "系统配置 ----字典表管理>>编辑");
        actionMap.put("login.login", "登录");
        actionMap.put("admin.doAdd", " 添加---用户管理>>新增用户");
    }
    
    
    
}
