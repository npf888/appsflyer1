package com.ami.texas.player.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ami.api.common.Pager;
import com.ami.api.exception.APIException;
import com.ami.texas.player.dao.PlayerDao;

/**
 * 
 * 玩家信息 逻辑类
 * 
 * @author zhuweiliang
 * @version [版本号, 2013-7-10]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component
public class PlayerService
{
    @Autowired
    PlayerDao playerDao;
    
    /**
     * 分页查询玩家信息
     * 
     * @param pager
     * @param useraccount 用户账号
     * @param username 用户姓名
     * @return
     * @throws APIException
     * @see [类、类#方法、类#成员]
     */
    public Pager queryPlayer(Pager pager, String useraccount, String username,String isFacebook)
        throws APIException
    {       
        pager = playerDao.queryPlayer(pager, useraccount, username,isFacebook);
        return pager;
    }
    /**
     * 大额玩家筹码
     * @param pager
     * @param useraccount
     * @param username
     * @return
     * @throws APIException
     */
    public Pager queryPlayerBigdata(Pager pager)
    		throws APIException
    {       
    	pager = playerDao.queryPlayerBigdata(pager);
    	return pager;
    }
    /**
     * 分页查询玩家信息
     * 
     * @param pager
     * @param useraccount 用户账号
     * @param username 用户姓名
     * @return
     * @throws APIException
     * @see [类、类#方法、类#成员]
     */
    public Pager queryPlayerlevel(Pager pager, String level)
    		throws APIException
    {       
    	pager = playerDao.queryPlayerlevel(pager,level);
    	return pager;
    }
    
    /**
     * 分页查询玩家背包信息
     * 
     * @param pager
     * @param useraccount 用户账号
     * @param username 用户姓名
     * @return
     * @throws APIException
     * @see [类、类#方法、类#成员]
     */
    public Pager queryBag(Pager pager, String useraccount, String username)
        throws APIException
    {       
        pager = playerDao.queryBag(pager, useraccount, username);
        return pager;
    }
    
    /**
     * 分页查询好友请求
     * 
     * @param pager
     * @param useraccount 用户账号
     * @param username 用户姓名
     * @return
     * @throws APIException
     * @see [类、类#方法、类#成员]
     */
    public Pager queryFriend(Pager pager, String useraccount, String username)
        throws APIException
    {       
        pager = playerDao.queryFriend(pager, useraccount, username);
        return pager;
    }
    
    /**
     * 分页查询好友关系
     * 
     * @param pager
     * @param useraccount 用户账号
     * @param username 用户姓名
     * @return
     * @throws APIException
     * @see [类、类#方法、类#成员]
     */
    public Pager queryFriendRequest(Pager pager, String useraccount, String username)
        throws APIException
    {       
        pager = playerDao.queryFriendRequest(pager, useraccount, username);
        return pager;
    }
    
    /**
     * 分页查询邮件列表
     * 
     * @param pager
     * @param useraccount 用户账号
     * @param username 用户姓名
     * @return
     * @throws APIException
     * @see [类、类#方法、类#成员]
     */
    public Pager queryEmail(Pager pager, String sendUserid, String sendUsername,String useraccount, String username)
        throws APIException
    {       
        pager = playerDao.queryEmail(pager,sendUserid,sendUsername, useraccount, username,"1=1");
        return pager;
    }
    /**
     * 分页查询邮件列表 中的 赠送的礼物
     * 
     * @param pager
     * @param useraccount 用户账号
     * @param username 用户姓名
     * @return
     * @throws APIException
     * @see [类、类#方法、类#成员]
     */
    public Pager queryGiftEmail(Pager pager,String sendUserid, String sendUsername, String useraccount, String username)
    		throws APIException
    {       
    	pager = playerDao.queryEmail(pager, sendUserid,  sendUsername,useraccount, username,"mailType=4");
    	return pager;
    }
    
    /**
     * 分页查询Vip
     * 
     * @param pager
     * @param useraccount 用户账号
     * @param username 用户姓名
     * @return
     * @throws APIException
     * @see [类、类#方法、类#成员]
     */
    public Pager queryVip(Pager pager, String useraccount, String username)
        throws APIException
    {       
        pager = playerDao.queryVip(pager, useraccount, username);
        return pager;
    }
    
    /**
     * 分页查询付费订单
     * 
     * @param pager
     * @param useraccount 用户账号
     * @param username 用户姓名
     * @return
     * @throws APIException
     * @see [类、类#方法、类#成员]
     */
    public Pager queryRecharge(Pager pager, String useraccount, String username,String status)
        throws APIException
    {       
        pager = playerDao.queryRecharge(pager, useraccount, username,status);
        return pager;
    }

	public List<HashMap<String, Object>> queryInterval() throws APIException{
		
		return playerDao.queryInterval();
	}
    
}
