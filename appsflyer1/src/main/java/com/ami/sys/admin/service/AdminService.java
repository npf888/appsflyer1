package com.ami.sys.admin.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ami.api.common.AppConstant;
import com.ami.api.common.Pager;
import com.ami.api.exception.APIException;
import com.ami.api.utill.DateTools;
import com.ami.api.utill.StringTool;
import com.ami.api.utill.TripleDES;
import com.ami.sys.admin.bean.AdminBean;
import com.ami.sys.admin.dao.AdminDao;

/**
 * 
 * 用户管理 逻辑类
 * 
 * @author zhuweiliang
 * @version [版本号, 2013-7-10]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component
public class AdminService
{
    
    @Autowired
    private AdminDao admindao;
    
    /**
     * 添加用户
     * 
     * @param params 参数 key value
     * @return
     * @throws APIException
     * @see [类、类#方法、类#成员]
     */
    public String add(Map<String, Object> params)
        throws APIException
    {
        
        // 1, 对密码加密处理
        encryption(params);
        
        // 2, 创建时间
        params.put("time_stamp", DateTools.getCurrentDate("yyyyMMddHHmmss"));
        
        // 唯一ID
        String id = admindao.add(params);
        
        return id;
    }
    
    /**
     * 检查账户是否重复
     * 
     * @param params 参数 key value
     * @return
     * @throws APIException
     * @see [类、类#方法、类#成员]
     */
    public String checkAccout(String account)
        throws APIException
    {
         String check = "false";
         
    	 List<HashMap<String, Object>> list = admindao.checkAccout(account);
    	 if(list.isEmpty())
    	  {
    	    check="success";  
    	  }

    	 return check;
       
    }
    
    /**
     * 根据用户ID 查询用户信息
     * 
     * @param id 用户唯一ID
     * @return
     * @throws APIException
     * @see [类、类#方法、类#成员]
     */
    public Map<String, Object> loadAdminByID(String id)
        throws APIException
    {
        return admindao.loadAdminByID(id);
    }
    
    /**
     * 更改密码
     * 
     * @param params 修改的字段以及value
     * @param id 用户唯一ID
     * @return
     * @throws APIException
     * @see [类、类#方法、类#成员]
     */
    public void updatePwd(String account, String pwd, String id)
        throws APIException
    {
        
        Map<String, Object> params = new HashMap<String, Object>();
        
        // 对密码进行加密
        pwd = encryption(account, pwd);
        
        params.put("pwd", pwd);
        
        // 2, 创建时间
        params.put("time_stamp", DateTools.getCurrentDate("yyyyMMddHHmmss"));
        
        // 调用Dao 更新
        admindao.update(params, id);
        
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
    public void update(Map<String, Object> parameters, String account, String pwd, String id)
        throws APIException
    {
        
        // 如果密码不为空 则更改密码
        if (!StringTool.isEmpty(pwd))
        {
            // 加密
            pwd = encryption(account, pwd);
            
            parameters.put("pwd", pwd);
        }
        
        // 创建时间
        parameters.put("time_stamp", DateTools.getCurrentDate("yyyyMMddHHmmss"));
        
        // 调用Dao 更新
        admindao.update(parameters, id);
        
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
        admindao.delByID(id);
    }
    
    /**
     * 对密码进行加密处理
     * 
     * 加密逻辑 ：使用账号 和密码 拼接进行加密
     * 
     * @param params
     * @see [类、类#方法、类#成员]
     */
    private void encryption(Map<String, Object> params)
    {
        String pwd = StringTool.getValue(params.get("pwd"));
        
        String acount = StringTool.getValue(params.get("account"));
        
        // 加密
        pwd = encryption(acount, pwd);
        
        params.put("pwd", pwd);
    }
    
    /**
     * 根据账号和密码 查询信息
     * 
     * @return
     * @throws APIException
     * @see [类、类#方法、类#成员]
     */
    public AdminBean loadByAccountPwd(String acount, String pwd)
        throws APIException
    {
        
        // 是否加密
        pwd = encryption(acount, pwd);
        
        return admindao.loadByAccountPwd(acount, pwd);
    }
    
    public List<HashMap<String, Object>> query()
        throws APIException
    {
        
        return admindao.query();
        
    }
    
    /**
     * 按分页查询
     * 
     * @param pager
     * @param useraccount 用户账号
     * @param username 用户姓名
     * @return
     * @throws APIException
     * @see [类、类#方法、类#成员]
     */
    public Pager queryForPage(Pager pager, String useraccount, String username)
        throws APIException
    {
        
        pager = admindao.queryForPage(pager, useraccount, username);
        
        // 将角色ID转化成 角色
        convertRole(pager);
        
        //
        
        return pager;
    }
    
    /**
     * 将角色ID转化成 角色
     * 
     * @param pager
     * @see [类、类#方法、类#成员]
     */
    private void convertRole(Pager pager)
    {
        List<HashMap<String, Object>> members = pager.getItems();
        
        // 从application 取到角色列表
        Map<String, Map<String, Object>> roleMap = AppConstant.roleMap;
        
        // 将数据库中1|2|3 换成角色名称
        for (HashMap<String, Object> hashMap : members)
        {
            String groupList_str = StringTool.getValue(hashMap.get("groupList"));
            if (StringTool.isEmpty(groupList_str))
            {
                continue;
            }
            String groupList[] = groupList_str.split("\\|");
            for (int i = 0; i < groupList.length; i++)
            {
                Map<String, Object> row = roleMap.get(groupList[i].toString());
                if (null != row)
                {
                    groupList[i] = StringTool.getValue(row.get("rolename"));
                }
                
            }
            hashMap.put("grouplist", StringTool.arryToString(groupList, " | "));
        }
        
        pager.setItems(members);
    }
    
    /**
     * 使用账号 +密码 进行加密
     * 
     * @param account
     * @param pwd
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String encryption(String account, String pwd)
    {
        // 加密
        String pwdenc = TripleDES.getEncString(account + pwd);
        
        return pwdenc;
    }
    
    public static void main(String[] args)
    {
        String account = "admin";
        
        String pwd = "admin";
        
        AdminService as = new AdminService();
        
        System.out.println(as.encryption(account, pwd));
        
         account = "dev";
        
         pwd = "dev";

        System.out.println(as.encryption(account, pwd));
    }
    
    /**
     * 初始化密码
     * 
     * @param account
     * @param pwd
     * @see [类、类#方法、类#成员]
     */
    public void resetPwd(String account, String pwd)
    {
        pwd=encryption(account, pwd);
        admindao.resetPwd(account, pwd);
        
    }
}
