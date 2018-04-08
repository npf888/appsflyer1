package com.ami.texas.game.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ami.api.common.Pager;
import com.ami.api.exception.APIException;
import com.ami.texas.game.dao.GameDao;
import com.ami.texas.game.pojo.DailyTaskVO;

/**
 * 
 * 游戏相关数据
 * 
 * @author  Netherfire
 * @version  [版本号, Aug 12, 2015]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component
public class GameService 
{
    @Autowired
    GameDao gameDao;
    /**
     * 分页查询月卡信息
     * 
     * @param pager
     * @param useraccount 用户账号
     * @param username 用户姓名
     * @return
     * @throws APIException
     * @see [类、类#方法、类#成员]
     */
    public Pager queryMonthCard(Pager pager, String useraccount, String username)
        throws APIException
    {       
        pager = gameDao.queryMonthCard(pager, useraccount, username);
        return pager;
    }
    
    /**
     * 分页查询月卡信息
     * 
     * @param pager
     * @param useraccount 用户账号
     * @param username 用户姓名
     * @return
     * @throws APIException
     * @see [类、类#方法、类#成员]
     */
    public Pager queryWeekCard(Pager pager, String useraccount, String username)
        throws APIException
    {       
        pager = gameDao.queryWeekCard(pager, useraccount, username);
        return pager;
    }
    
    /**
     * 分页查询月卡信息
     * 
     * @param pager
     * @param useraccount 用户账号
     * @param username 用户姓名
     * @return
     * @throws APIException
     * @see [类、类#方法、类#成员]
     */
    public Pager queryTexas(Pager pager, String useraccount, String username)
        throws APIException
    {       
        pager = gameDao.queryTexas(pager, useraccount, username);
        return pager;
    }
    
    /**
     * 分页查询跑马灯信息
     * 
     * @param pager
     * @param useraccount 用户账号
     * @param username 用户姓名
     * @return
     * @throws APIException
     * @see [类、类#方法、类#成员]
     */
    public Pager queryNews(Pager pager, String useraccount, String username)
        throws APIException
    {       
        pager = gameDao.queryNews(pager, useraccount, username);
        return pager;
    }
    
    /**
     * 分页查询签到信息
     * 
     * @param pager
     * @param useraccount 用户账号
     * @param username 用户姓名
     * @return
     * @throws APIException
     * @see [类、类#方法、类#成员]
     */
    public Pager queryWeeklySignin(Pager pager, String useraccount, String username)
        throws APIException
    {       
        pager = gameDao.queryWeeklySignin(pager, useraccount, username);
        return pager;
    }
    
    /**
     * 分页查询任务信息
     * 
     * @param pager
     * @param useraccount 用户账号
     * @param username 用户姓名
     * @return
     * @throws APIException
     * @see [类、类#方法、类#成员]
     */
    public Pager queryTask(Pager pager, String useraccount, String username)
        throws APIException
    {
        pager = gameDao.queryTask(pager, useraccount, username);
        return pager;
    }
    
    /**
     * 分页查询任务信息
     * 
     * @param pager
     * @param useraccount 用户账号
     * @param username 用户姓名
     * @return
     * @throws APIException
     * @see [类、类#方法、类#成员]
     */
    public Pager queryBaccart(Pager pager, String useraccount, String username)
        throws APIException
    {
        pager = gameDao.queryBaccart(pager, useraccount, username);
        return pager;
    }
    
    /**
     * 分页查询任务信息
     * 
     * @param pager
     * @param useraccount 用户账号
     * @param username 用户姓名
     * @return
     * @throws APIException
     * @see [类、类#方法、类#成员]
     */
    public Pager queryBaccartRoom(Pager pager, String useraccount, String username)
        throws APIException
    {
        pager = gameDao.queryBaccartRoom(pager, useraccount, username);
        return pager;
    }
    
    /**
     * 分页查询其它
     * 
     * @param pager
     * @return
     * @throws APIException
     * @see [类、类#方法、类#成员]
     */
    public Pager queryOther(Pager pager)
        throws APIException
    {
        pager = gameDao.queryOther(pager);
        return pager;
    }

	public List<DailyTaskVO> queryTaskStatistics(String useraccount,
			String username,String date) throws APIException {
		return gameDao.queryTaskStatistics(useraccount, username,date);
	}

}
