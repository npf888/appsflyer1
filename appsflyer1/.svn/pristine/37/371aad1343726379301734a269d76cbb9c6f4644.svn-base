package com.ami.appsflyer.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ami.api.common.Pager;
import com.ami.api.exception.APIException;
import com.ami.appsflyer.dao.AppsflyerDao;

/**
 * 
 * 联系点
 * 
 * @author
 * @version [版本号, 2013-7-10]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component
public class AppsflyerService
{
    
    @Autowired
    private AppsflyerDao matDao;
    
    /**
     * 查询号码
     * 
     * @param param
     * @param pager
     * @return
     * @throws APIException
     * @see [类、类#方法、类#成员]
     */
    public Pager queryForPage(Map<String, String> param, Pager pager)
        throws APIException
    {
        
        pager = matDao.queryForPage(param, pager);
        
        return pager;
    }
   
}
