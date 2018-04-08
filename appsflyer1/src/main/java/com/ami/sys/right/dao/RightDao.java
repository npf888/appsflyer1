package com.ami.sys.right.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ami.api.exception.APIException;
import com.ami.api.web.dao.BaseDao;
import com.ami.sys.right.bean.RightBean;

/**
 * 
 * 权限 Dao
 * 
 * @author zhuweiliang
 * @version [版本号, 2013-7-5]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component
public class RightDao extends BaseDao
{
    
    /**
     * 查询所有权限
     * 
     * key 角色ID VALUE 记录
     * 
     * @return
     * @throws APIException
     */
    public Map<String, Map<String, Object>> intiRole()
        throws APIException
    {
        
        Map<String, Map<String, Object>> roleMap = this.table.query("select * from sys_admin_texas.pre_common_rolevisual where id!=1");
        
        return roleMap;
    }
    /**
     * 查询所有权限
     * 
     * key 角色ID VALUE 记录
     * 
     * @return
     * @throws APIException
     */
    public Map<String, Map<String, Object>> loadAdminRole()
        throws APIException
    {
        
        Map<String, Map<String, Object>> roleMap = this.table.query("select * from sys_admin_texas.pre_common_rolevisual where id=1");
        
        return roleMap;
    }
    
    /**
     * 加载所有导航列表
     * 
     * @return
     * @throws APIException
     */
    public List<RightBean> loadAllNav()
        throws APIException
    {
        
        List<RightBean> navMap = this.db.query("select * from sys_admin_texas.pre_common_nav   order by displayorder ,sid  ", RightBean.class);
        
        return navMap;
        
    }
    
    /**
     * 根据ID 加载
     * 
     * @return
     * @throws APIException
     */
    public Map<String, Object> loadByID(String id)
        throws APIException
    {
        Map<String, Object> o = this.table.load("sys_admin_texas.pre_common_rolevisual", id);
        
        return o;
    }
    
    public void update(Map<String, Object> params, String id)
        throws APIException
    {
        table.update("sys_admin_texas.pre_common_rolevisual", params, Long.parseLong(id));
    }
    
    public void insert(Map<String, Object> params)
        throws APIException
    {
        table.insert("sys_admin_texas.pre_common_rolevisual", params);
    }
    
    public void del(String id)
        throws APIException
    {
        String sql = "delete from sys_admin_texas.pre_common_rolevisual  where id = " + id;
        
        this.db.update(sql);
    }
    
}
