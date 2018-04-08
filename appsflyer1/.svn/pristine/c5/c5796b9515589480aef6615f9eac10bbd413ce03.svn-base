package com.ami.texas.img.service;

import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ami.api.exception.APIException;
import com.ami.texas.img.dao.ImgDao;

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
public class ImgService 
{
    @Autowired
    ImgDao imgDao;
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
    public  List<HashMap<String, Object>> query()
        throws APIException
    {       
    	List<HashMap<String, Object>> result = imgDao.query();
        return result;
    }
    public void delByID(String id) throws APIException
    {
    	imgDao.delByID(id);
    }
}
