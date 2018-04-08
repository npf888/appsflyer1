package com.ami.texas.news.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ami.api.common.Pager;
import com.ami.api.exception.APIException;
import com.ami.texas.news.dao.NewsDao;

/**
 * 
 * 活动配置相关
 * <功能详细描述>
 * 
 * @author  Cici
 * @version  [版本号, Aug 24, 2015]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component
public class NewsService 
{
    @Autowired
    NewsDao newsDao;
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
    public Pager queryNews(Pager pager)
        throws APIException
    {       
        pager = newsDao.queryNews(pager);
        return pager;
    }

    /**
     * 修改活动信息
     * 
     * @param parameters
     * @param id
     * @throws APIException
     * @see [类、类#方法、类#成员]
     */
    public void updateActivity(Map<String, Object> parameters, String id)
        throws APIException
    {   
        // 调用Dao 更新
        newsDao.updateActivity(parameters, id);
        
    }
    
    /**
     * 根据id获取活动
     * 
     * @param id
     * @return
     * @throws APIException
     * @see [类、类#方法、类#成员]
     */
    public Map<String, Object> loadActivityByID(String id) throws APIException
    {
        Map<String, Object> result = newsDao.loadActivityByID(id);
        
        return result;
    }
    
    /**
     * 根据id删除跑马灯
     * <功能详细描述>
     * @param id
     * @throws APIException 
     * @see [类、类#方法、类#成员]
     */
    public void deleteNews(int id) throws APIException
    { 
        newsDao.deleteNews(id);
    }

	public void updateNotice(Map<String, Object> parameters)  throws APIException{
		newsDao.updateNotice(parameters);
	}

	public long getLastNoticeId() throws APIException {
		return newsDao.getLastNoticeId();
	}
}
