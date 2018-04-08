package com.ami.department.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ami.api.exception.APIException;
import com.ami.api.web.dao.BaseDao;

@Component
public class DepartmentDao extends BaseDao
{
    /**
     * 加载所有部门信息
     * 
     * @return
     * @throws APIException 
     * @see [类、类#方法、类#成员]
     */
    public List<HashMap<String, Object>> loadAllDepartment() throws APIException
    {
        
        List<HashMap<String, Object>> list = this.db.query("select * from  sys_admin_texas.sys_department order by pid ,displayorder desc ,sid");
        
        return list;
        
    }
}
