﻿package com.ami.api.common;

import java.util.List;
import java.util.Map;

import com.ami.sys.config.bean.SysDictionaryBean;

public class AppConstant
{
    /**
     * 系统路径
     */
    public static String systemDir;
    
    public static String dataPath = "data";
       
    public static String apkPath = "data/apk/";
    
    public static String webroot = "service/ROOT/";
    
    // Cookie 相关
    public static String COOK_USER_NAME = "cook_ami_user_name";
    
    public static String COOK_USER_PWD = "cook_mai_user_pwd";
    
    public static int server_jet = 16*60*60;
    
    public static String SERVER_ip ="http://116.62.175.124";//正服47.88.169.228
    
    public static String SERVER_URL = SERVER_ip + ":81/";//"http://158.85.174.2:8080/";
    	
    /**
     * 修改活动的目标 游戏服务器（添加活动时，让目标服务器的内存里的活动也加上）
     */
    public static String SERVER_URL_HTTP = "http://116.62.175.124:9999/";//"http://47.88.241.10:8888/"
    
    public static String SERVER_URLGM = SERVER_ip + ":8080/";
    
    /**
     * 加密密钥
     */
    public static String DesEncrypt_key = "MYKfdsfsaEY11fdasfdwa";
    
    // 用户LoginInfo信息保存再session中的key值
    public final static String LOGININFO_KEY = "_loginInfo";
    public final static String LOGININFO_AGENT_KEY = "_loginAgentInfo";
    
    public final static boolean DEBUG = false; // 开发调试模式
    
    /**
     * 存储角色
     * 
     * key 角色ID VALUE 记录
     */
    public static Map<String, Map<String, Object>> roleMap;
    
    /**
     * 存储权限
     * 
     * key 角色ID VALUE 记录
     */
    public static Map<String, Map<String, Object>> allNavMap;
    
    /**
     * 系统管理员序列
     */
    public static String seq_pre_common_member_id = "pre_common_member_id_seq";
    
    // 以字典类型为key
    public static Map<String, List<SysDictionaryBean>> DMap;
    
    // 以字典 ID 为key
    public static Map<String, String> DKey;
    
    
   
    
    
    
}
