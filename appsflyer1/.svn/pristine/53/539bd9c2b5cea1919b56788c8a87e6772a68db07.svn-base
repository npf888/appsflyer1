package com.ami.texas.img.dao;

import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.List;
import com.ami.api.web.dao.BaseMysqlDao;
import com.ami.api.exception.APIException;

/**
 * 
 * 活动配置相关
 * 
 * @author  Netherfire
 * @version  [版本号, Aug 12, 2015]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component
public class ImgDao extends BaseMysqlDao
{
    /**
     * 获取所有活动
     * 
     * @param pager
     * @return
     * @throws APIException
     * @see [类、类#方法、类#成员]
     */
    public List<HashMap<String, Object>> query() 
        throws APIException
    { 
        String sql = "select id, image_name from texas_gm.t_image order by create_time DESC";        

        List<HashMap<String, Object>> result = this.db.query(sql);
        return result;
    }
    
    public void delByID(String id) throws APIException
    {
    	String sql = "delete from texas_gm.t_image where id=? ";
    	this.db.update(sql, new Object[]{id});
    }
}
