package com.ami.texas.compensation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ami.api.common.Pager;
import com.ami.api.exception.APIException;
import com.ami.texas.compensation.dao.CompensationDao;

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
public class CompensationService {

    @Autowired
    CompensationDao compensationDao;
    
    /**
     * 获取所有全服奖励
     * 
     * @param pager
     * @return
     * @throws APIException
     * @see [类、类#方法、类#成员]
     */
    public Pager query(Pager pager)
        throws APIException
    {       
        pager = compensationDao.query(pager);
        return pager;
    }
    
    /**
     * 获取所有全服奖励
     * 
     * @param pager
     * @return
     * @throws APIException
     * @see [类、类#方法、类#成员]
     */
    public Pager queryUser(Pager pager)
        throws APIException
    {       
        pager = compensationDao.queryUser(pager);
        return pager;
    }
	
}
