package com.ami.department.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ami.api.exception.APIException;
import com.ami.common.DdgbConstant;
import com.ami.department.dao.DepartmentDao;

/**
 * 配置业务逻辑类
 * 
 * @author 朱伟亮
 * @version [版本号, Apr 20, 2012]
 * @see [相关类/方法]
 * @since [Q3-LIG/配置扣款]
 */
@Component
public class DepartmentService
{
    private static Logger logger = Logger.getLogger(DepartmentService.class);
    
    @Autowired
    DepartmentDao departmentDao;
    
    /**
     * 加载所有部门信息
     * 
     * @return
     * @throws APIException
     * @see [类、类#方法、类#成员]
     */
    public List<HashMap<String, Object>> loadAllDepartment()
        throws APIException
    {
        List<HashMap<String, Object>> list = departmentDao.loadAllDepartment();
        
        return list;
    }
    
    /**
     * 加载所有部门信息
     * 
     * @return key=sid value=record
     * @throws APIException
     * @see [类、类#方法、类#成员]
     */
    public void loadAllDepartment_Map()
        throws APIException
    {
        
        List<HashMap<String, Object>> list = departmentDao.loadAllDepartment();
        
        Map<String, HashMap<String, Object>> result = new HashMap<String, HashMap<String, Object>>();
        
        for (HashMap<String, Object> hashMap : list)
        {
            String sid = hashMap.get("sid").toString();
            
            String pid = hashMap.get("pid").toString();
            
            String long_title = hashMap.get("title").toString();
            
            // 找到父节点
            HashMap<String, Object> parentMap = result.get(pid);
            
            if (null != parentMap && !"0".equals(parentMap.get("islongtitle").toString()))
            {
                // 取到父节点title
                String parmentTitle = parentMap.get("title") + "";
                
                long_title = parmentTitle + "-" + long_title;
                
            }
            
            hashMap.put("long_title", long_title);
            
            result.put(sid, hashMap);
        }
        
        DdgbConstant.DPKey = result;
        
        DdgbConstant.DPList = list;
        
        ServletContext servletContext = ServletActionContext.getServletContext();
        
        if (null != servletContext)
        {
            updateSession(servletContext);
        }
        
    }
    
 
    
    public void updateSession(ServletContext servletContext)
    {
        servletContext.setAttribute("DPKey", DdgbConstant.DPKey);
        
        servletContext.setAttribute("DPList", DdgbConstant.DPList);
    }
    
}
