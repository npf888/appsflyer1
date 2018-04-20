package com.ami.texas.player.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ami.api.common.Pager;
import com.ami.api.exception.APIException;
import com.ami.api.web.action.BaseAction;
import com.ami.texas.player.service.PlayerService;
import com.ami.weixin.course.service.CoreService;
import com.ami.weixin.course.service.WarningService;

/**
 * 报表统计分析
 * 
 * @author zhuweiliang
 * @version [版本号, 2013-5-29]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Scope("prototype")
@Component
public class PlayerAction extends BaseAction
{
    
    private static final long serialVersionUID = 1L;
    
    private static Logger logger = Logger.getLogger(PlayerAction.class);
    
    @Autowired
    PlayerService playerService;

    

	@Autowired
	private CoreService coreService;
	
	
	@Autowired
	private WarningService warningService;
	
//	private static Logger logger = Logger.getLogger(CoreAction.class);
	
	public String doGetPost() throws IOException, APIException{   
		 logger.debug("query");
	        
	        // 用户账号
	        String account = this.get("useraccount");
	        
	        // 用户姓名
	        String username = this.get("username");
	        // 返回到页面
	        this.set("account", account);
	        this.set("username", username);
	        
	        Pager pager = new Pager(this.getReq());
	        
	        // 调用逻辑类 查询到结果
	        pager = playerService.queryPlayer(pager, account, username,"2");
	        
	        this.set("PAGER", pager);
	        
	        return "queryPlayer";  
		
	}
    
    
    
    
    /**
     * 获取所有玩家
     * 
     * @return
     * @throws Exception
     * @see [类、类#方法、类#成员]
     */
    public String queryPlayer()
        throws Exception
    {
        logger.debug("query");
        
        // 用户账号
        String account = this.get("useraccount");
        
        // 用户姓名
        String username = this.get("username");
        String isFacebook = this.get("isFacebook");
        // 返回到页面
        if(StringUtils.isBlank(isFacebook)){
        	isFacebook="2";
        	this.set("isFacebook", "2");
        }else{
        	this.set("isFacebook", isFacebook);
        }
        this.set("account", account);
        this.set("username", username);
        
        Pager pager = new Pager(this.getReq());
        //这个page 和 type 是用于德州信息 跳转页面的
        String currentPage = this.get("currentPage");
        String type = this.get("type");
        // 调用逻辑类 查询到结果
        pager = playerService.queryPlayer(pager, account, username,isFacebook);
        
        this.set("PAGER", pager);
        this.set("currentPage", currentPage);
        this.set("type", type);
        return "queryPlayer";
    }
    
    /**
     * 大额筹码玩家排序
     * @return
     * @throws Exception
     */
    public String queryPlayerBigdata()throws Exception{
        logger.debug("query");
        
     
        
        Pager pager = new Pager(this.getReq());
        
        // 调用逻辑类 查询到结果
        pager = playerService.queryPlayerBigdata(pager);
        
        this.set("PAGER", pager);
    	
    	return "queryPlayerBigdata";
    }
    
    /**
     * 获取玩家背包信息
     * 
     * @return
     * @throws Exception
     * @see [类、类#方法、类#成员]
     */
    public String queryBag()
        throws Exception
    {
        logger.debug("query");
        
        // 用户账号
        String account = this.get("useraccount");
        
        // 用户姓名
        String username = this.get("username");
        // 返回到页面
        this.set("account", account);
        this.set("username", username);
        
        Pager pager = new Pager(this.getReq());
        
        // 调用逻辑类 查询到结果
        pager = playerService.queryBag(pager, account, username);
        
        this.set("PAGER", pager);
        
        return "queryBag";
    }
    
    /**
     * 获取玩家好友关系信息
     * 
     * @return
     * @throws Exception
     * @see [类、类#方法、类#成员]
     */
    public String queryFriend()
        throws Exception
    {
        logger.debug("query");
        
        // 用户账号
        String account = this.get("useraccount");
        
        // 用户姓名
        String username = this.get("username");
        // 返回到页面
        this.set("account", account);
        this.set("username", username);
        
        Pager pager = new Pager(this.getReq());
        
        // 调用逻辑类 查询到结果
        pager = playerService.queryFriend(pager, account, username);
        
        this.set("PAGER", pager);
        
        return "queryFriend";
    }
    
    /**
     * 获取好友请求
     * 
     * @return
     * @throws Exception
     * @see [类、类#方法、类#成员]
     */
    public String queryFriendRequest()
        throws Exception
    {
        logger.debug("query");
        
        // 用户账号
        String account = this.get("useraccount");
        
        // 用户姓名
        String username = this.get("username");
        // 返回到页面
        this.set("account", account);
        this.set("username", username);
        
        Pager pager = new Pager(this.getReq());
        
        // 调用逻辑类 查询到结果
        pager = playerService.queryFriendRequest(pager, account, username);
        
        this.set("PAGER", pager);
        
        return "queryFriendRequest";
    }
    
    /**
     * 获取邮件列表
     * 
     * @return
     * @throws Exception
     * @see [类、类#方法、类#成员]
     */
    public String queryEmail()
        throws Exception
    {
        logger.debug("query");
        
        // 发送用户账号
    	String sendUserid = this.get("sendUserid");
    	
    	// 发送用户姓名
    	String sendUsername = this.get("sendUsername");
    	
        // 用户账号
        String account = this.get("useraccount");
        
        // 用户姓名
        String username = this.get("username");
        // 返回到页面
        this.set("sendUserid", sendUserid);
        this.set("sendUsername", sendUsername);
        this.set("account", account);
        this.set("username", username);
        
        Pager pager = new Pager(this.getReq());
        
        // 调用逻辑类 查询到结果
        pager = playerService.queryEmail(pager,sendUserid,sendUsername, account, username);
        
        this.set("PAGER", pager);
        
        return "queryEmail";
    }
    /**
     * 获取邮件列表 中 礼物部分
     * 
     * @return
     * @throws Exception
     * @see [类、类#方法、类#成员]
     */
    public String queryOnlyEmail()
    		throws Exception
    {
    	logger.debug("query");
    	
    	// 发送用户账号
    	String sendUserid = this.get("sendUserid");
    	
    	// 发送用户姓名
    	String sendUsername = this.get("sendUsername");
    	
    	
    	// 接收用户账号
    	String account = this.get("useraccount");
    	
    	// 接收用户姓名
    	String username = this.get("username");
    	// 返回到页面
        this.set("sendUserid", sendUserid);
        this.set("sendUsername", sendUsername);
    	this.set("account", account);
    	this.set("username", username);
    	
    	Pager pager = new Pager(this.getReq());
    	
    	// 调用逻辑类 查询到结果
    	pager = playerService.queryGiftEmail(pager,sendUserid,sendUsername, account, username);
    	
    	this.set("PAGER", pager);
    	
    	return "queryOnlyEmail";
    }
    
    /**
     * 获取Vip列表
     * 
     * @return
     * @throws Exception
     * @see [类、类#方法、类#成员]
     */
    public String queryVip()
        throws Exception
    {
        // 用户账号
        String account = this.get("useraccount");
        
        // 用户姓名
        String username = this.get("username");
        // 返回到页面
        this.set("account", account);
        this.set("username", username);
        
        Pager pager = new Pager(this.getReq());
        
        // 调用逻辑类 查询到结果
        pager = playerService.queryVip(pager, account, username);
        
        this.set("PAGER", pager);
        
        return "queryVip";
    }
    
    /**
     * 获取付费订单
     * 
     * @return
     * @throws Exception
     * @see [类、类#方法、类#成员]
     */
    public String queryRecharge()
        throws Exception
    {
        // 用户账号
        String account = this.get("useraccount");
        
        // 用户姓名
        String username = this.get("username");
        // 订单状态
        String status = this.get("status");
        // 返回到页面
        this.set("status", status);
        this.set("account", account);
        this.set("username", username);
        
        Pager pager = new Pager(this.getReq());
        
        // 调用逻辑类 查询到结果
        pager = playerService.queryRecharge(pager, account, username,status);
        
        this.set("PAGER", pager);
        
        return "queryRecharge";
    }
    
    /**
     * 
     * @return
     * @throws Exception
     */
    public String getlevel() throws Exception{
    	
    	List<HashMap<String,Object>> list = playerService.queryInterval();
    	 this.set("levelData", list);
    	return "getlevel";
    }
    
    /**
     * 获取所有玩家
     * 
     * @return
     * @throws Exception
     * @see [类、类#方法、类#成员]
     */
    public String queryPlayerLevel()
        throws Exception
    {
        logger.debug("query");
        
        // 用户账号
        String level = this.get("levelDate");
        
        // 返回到页面
        this.set("account", "");
        this.set("username", "");
        
        Pager pager = new Pager(this.getReq());
        
        // 调用逻辑类 查询到结果
        pager = playerService.queryPlayerlevel(pager, level);
        
        this.set("PAGER", pager);
        
        return "queryPlayerLevel";
    }
    
    
    
}
