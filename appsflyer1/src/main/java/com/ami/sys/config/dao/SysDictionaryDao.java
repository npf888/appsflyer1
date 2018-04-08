package com.ami.sys.config.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ami.api.common.Pager;
import com.ami.api.exception.APIException;
import com.ami.api.utill.StringTool;
import com.ami.api.web.dao.BaseDao;
import com.ami.sys.config.bean.SysDictionaryBean;

@Component
public class SysDictionaryDao extends BaseDao
{
    /**
     * 查询出所有配置项
     * 
     * @return
     * @throws APIException
     * @see [类、类#方法、类#成员]
     */
    public List<SysDictionaryBean> queryConfig()
        throws APIException
    {
        String sql = "select * from sys_admin_texas.sys_dictionary";
        
        List<SysDictionaryBean> result = this.db.query(sql, SysDictionaryBean.class);
        
        return result;
        
    }
    
    /**
     * 查询出所有配置项
     * 
     * @return
     * @throws APIException
     * @see [类、类#方法、类#成员]
     */
    public List<HashMap<String, Object>> queryDictionary_type()
        throws APIException
    {
        String sql = "select * from sys_admin_texas.sys_dictionary_type";
        
        List<HashMap<String, Object>> result = this.db.query(sql);
        
        return result;
        
    }
    
    /**
     * 分页查询
     * 
     * @param param
     * @param pager
     * @return
     * @throws APIException
     */
    public Pager queryForPage(Map<String, String> param, Pager pager)
        throws APIException
    {
        
        String dtypeid = param.get("dtypeid");
        
        String sql = "select * from sys_admin_texas.sys_dictionary where dtype='" + dtypeid + "'";
        
        String dtitle = param.get("dtitle");
        if (!StringTool.isEmpty(dtitle))
        {
            sql += " and dtitle like '" + dtitle + "' order by ";
        }
        
        pager = this.db.queryPage(sql.toString(), new Object[]
        {}, pager);
        
        return pager;
        
    }
    
    /**
     * 根据用户ID 修改用户信息
     * 
     * @param params 修改的字段以及value
     * @param id 用户唯一ID
     * @return
     * @throws APIException
     * @see [类、类#方法、类#成员]
     */
    public void update(Map<String, Object> params, String id)
        throws APIException
    {
        this.table.update("sys_admin_texas.sys_dictionary", params, Integer.parseInt(id));
        
    }
    
    /**
     * 添加维护字典数据
     * 
     * @param params
     * @throws APIException
     * @see [类、类#方法、类#成员]
     */
    public void add(Map<String, Object> params)
        throws APIException
    {
        String dtype = params.get("dtype").toString();
        
        String dtitle = params.get("dtitle").toString();
        
        String sql =
            "insert into sys_admin_texas.sys_dictionary (dtype,did,orderby,dtitle,time_stamp)  values('" + dtype + "',(select  maxid+1  from sys_admin_texas.sys_dictionary_type where dtypeid='" + dtype + "' ),1,'" + dtitle + "','')";
        
        this.db.update("update sys_admin_texas.sys_dictionary_type set maxid=maxid+1 where dtypeid='" + dtype + "'" );
        
        this.db.update(sql);
        
       
        
        
    }
    
    /**
     * 根据ID 删除用户信息
     * 
     * @param id
     * @throws APIException
     * @see [类、类#方法、类#成员]
     */
    public void delByID(String id)
        throws APIException
    {
        
        String sql = "delete from sys_admin_texas.sys_dictionary where id = ?";
        
        this.db.update(sql, new Object[]
        {Long.parseLong(id)});
        
    }
}
