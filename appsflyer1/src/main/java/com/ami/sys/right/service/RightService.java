package com.ami.sys.right.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ami.api.common.AppConstant;
import com.ami.api.exception.APIException;
import com.ami.sys.right.bean.RightBean;
import com.ami.sys.right.dao.RightDao;

/**
 * 权限业务逻辑层
 * 
 * @author zhuweiliang
 * @version [版本号, 2013-7-5]
 * @see [相关类/方法]
 * @since [IMSS/权限控制]
 */
@Component
public class RightService
{
    
    @Autowired
    private RightDao rightDao;
    
    /**
     * 查询所有权限
     * 
     * @return
     * @throws APIException
     */
    public Map<String, Map<String, Object>> intiRole()
        throws APIException
    {
        Map<String, Map<String, Object>> map = rightDao.intiRole();
        
        AppConstant.roleMap = map;
        
        return map;
        
    }
    
    public List<RightBean> loadAllRight()
    {
        List<RightBean> list = null;
        try
        {
            list = rightDao.loadAllNav();
        }
        catch (APIException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return list;
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
        List<RightBean> list = rightDao.loadAllNav();
        
        List<RightBean> result = new ArrayList<RightBean>();
        
        for (int i = 0; i < list.size(); i++)
        {
            RightBean bean = list.get(i);
            
            // 如果不是菜单 则继续
            if (!"0".equals(bean.getNtype()) && !"-1".equals(bean.getNtype()))
            {
                continue;
            }
            
            int level = bean.getSid().length() / 3;
            
            // 一级菜单直接插入
            if (1 == level)
            {
                result.add(bean);
                continue;
            }
            
            RightBean parent = getParent(bean, result);
            if (null == parent)
            {
                continue;
            }
            List<RightBean> child = parent.getChildren();
            if (null == child)
            {
                child = new ArrayList<RightBean>();
                parent.setChildren(child);
            }
            
            child.add(bean);
            
        }
        
        return result;
        
    }
    
    public RightBean getParent(RightBean bean, List<RightBean> all)
    {
        if (null == all)
        {
            return null;
        }
        
        for (RightBean parent : all)
        {
            String sid = parent.getSid();
            String pid = bean.getPid();
            
            if (sid.equals(pid)) // 如果是父亲 则直接返回
            {
                return parent;
            }
            else if (pid.startsWith(sid)) // // 如果是爷爷 继续往下找
            {
                // 递归调用
                return getParent(bean, parent.getChildren());
            }
            
        }
        
        return null;
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
        Map<String, Object> o = rightDao.loadByID(id);
        
        return o;
    }
    
    public void update(Map<String, Object> params, String id)
        throws APIException
    {
        rightDao.update(params, id);
    }
    
    public void insert(Map<String, Object> params)
        throws APIException
    {
        rightDao.insert(params);
    }
    
    public void del(String id)
        throws APIException
    {
        rightDao.del(id);
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
    {
        try
        {
            return rightDao.loadAdminRole();
        }
        catch (APIException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return new HashMap<String, Map<String, Object>>();
    }
    
}
