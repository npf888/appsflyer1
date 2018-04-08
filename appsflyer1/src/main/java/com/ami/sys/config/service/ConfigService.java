package com.ami.sys.config.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ami.api.common.AppConstant;
import com.ami.api.common.Pager;
import com.ami.api.exception.APIException;
import com.ami.api.utill.DateTools;
import com.ami.sys.config.bean.SysDictionaryBean;
import com.ami.sys.config.dao.SysDictionaryDao;

/**
 * 配置业务逻辑类
 * 
 * @author 朱伟亮
 * @version [版本号, Apr 20, 2012]
 * @see [相关类/方法]
 * @since [Q3-LIG/配置扣款]
 */
@Component
public class ConfigService
{
    private static Logger logger = Logger.getLogger(ConfigService.class);
    
    @Autowired
    SysDictionaryDao sysDictionary;
    
    /**
     * <一句话功能简述> <功能详细描述>
     * 
     * @param params
     * @throws APIException
     * @see [类、类#方法、类#成员]
     */
    public void doAddsysDictionary(Map<String, Object> params)
        throws APIException
    {
        sysDictionary.add(params);
        
        // 更新内存
        loadSysDictionary();
    }
    
    /**
     * 根据字典数据类型加载 数据
     * 
     * @see [类、类#方法、类#成员]
     */
    public String getDictionary(String type)
    {
        
        List<SysDictionaryBean> data = AppConstant.DMap.get(type);
        
        StringBuffer result = new StringBuffer();
        result.append("[");
        for (SysDictionaryBean ttt : data)
        {
            String key = ttt.getDtype() + "-" + ttt.getDid();
            
            result.append("{'id':'").append(key).append("','name':'").append(ttt.getDtitle()).append("'},");
        }
        
        result.deleteCharAt(result.length() - 1);
        
        result.append("]");
        
        return result.toString();
    }
    
    /**
     * 查询出所有配置项
     * 
     * @return key: type value configList {key:configid,value :{key,value}}
     * @throws APIException
     * @see [类、类#方法、类#成员]
     */
    public void loadSysDictionary()
        throws APIException
    {
        Map<String, List<SysDictionaryBean>> result = new HashMap<String, List<SysDictionaryBean>>();
        
        Map<String, String> allDictionnary = new HashMap<String, String>();
        
        List<SysDictionaryBean> list = sysDictionary.queryConfig();
        
        for (SysDictionaryBean systemConfigBean : list)
        {
            String dtype = systemConfigBean.getDtype();
            
            // 全局所有
            allDictionnary.put(dtype + "-" + systemConfigBean.getDid(), systemConfigBean.getDtitle());
            
            List<SysDictionaryBean> sdbList = result.get(dtype);
            
            if (null == sdbList)
            {
                sdbList = new ArrayList<SysDictionaryBean>();
                result.put(dtype, sdbList);
            }
            
            sdbList.add(systemConfigBean);
        }
        // {key:configid,value :{key,value}}
        AppConstant.DKey = allDictionnary;
        
        // key:type
        AppConstant.DMap = result;
        
        ServletContext servletContext = ServletActionContext.getServletContext();
        
        if (null != servletContext)
        {
            updateSession(servletContext);
        }
        
        // System.out.println(TdsConstant.DKey );
        
        // System.out.println("\n"+TdsConstant.DMap );
    }
    
    public void updateSession(ServletContext servletContext)
    {
        servletContext.setAttribute("DKey", AppConstant.DKey);
        servletContext.setAttribute("DMap", AppConstant.DMap);
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
        List<HashMap<String, Object>> list = sysDictionary.queryDictionary_type();
        
        return list;
    }
    
    public Pager queryDictionary(Map<String, String> param, Pager pager)
        throws APIException
    {
        return sysDictionary.queryForPage(param, pager);
    }
    
    /**
     * 更新操作员信息
     * 
     * @param params 修改的字段以及value
     * @param id 用户唯一ID
     * @return
     * @throws APIException
     * @see [类、类#方法、类#成员]
     */
    public void update(Map<String, Object> parameters, String id)
        throws APIException
    {
        
        // 创建时间
        parameters.put("time_stamp", DateTools.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
        
        // 调用Dao 更新
        sysDictionary.update(parameters, id);
        
        // 更新内存
        loadSysDictionary();
        
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
        sysDictionary.delByID(id);
        
        // 更新内存
        loadSysDictionary();
    }
}
