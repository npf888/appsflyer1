package com.ami.sys.admin.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ami.api.common.Pager;
import com.ami.api.exception.APIException;
import com.ami.api.utill.StringTool;
import com.ami.api.web.action.BaseAction;
import com.ami.sys.admin.bean.AdminBean;
import com.ami.sys.admin.service.AdminService;

/**
 * 
 * 系统管理员
 * 
 * @author zhuweiliang
 * @version [版本号, 2013-5-29]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Scope("prototype")
@Component
public class AdminAction extends BaseAction
{
    
    private static final long serialVersionUID = 1L;
    
    private static Logger logger = Logger.getLogger(AdminAction.class);
    
    @Autowired
    private AdminService adminService;
    
    /**
     * 返回登录页面
     */
    public String toSignIn()
    {
        return "toSignIn";
    }
    
    /**
     * 校验账号是否存在
     * 
     * @throws IOException
     * @throws APIException
     */
    public void checkAccount()
        throws IOException, APIException
    {
        
        String account = this.get("account");
        HashMap<String, String> par = new HashMap<String, String>();
        par.put("account", account);
        Object o = this.table.load("sys_admin_appsflyer.pre_common_member", par);
        
        if (null == o)
        {
            
            this.print("{state:1}");
        }
        else
        {
            this.print("{state:0}");
        }
        
    }
    
    /**
     * 添加用户
     */
    public void doAdd()
        throws Exception
    {
        logger.debug("doAdd");
        
        // 注：此字段 需要和页面的 表单的 name 以及 数据库的字段名 都要保持一致
        // 1， 定制需要往数据库插入的字段
        String insert[] = new String[]
        {"account", "username", "pwd", "email", "idcard","phone", "grouplist", "unit", "department"};
        
        // 2， 从页面获取 对应的表单值
        Map<String, Object> params = this.reqToMap(insert);
        
        // 3， 调用逻辑层 进行添加
        adminService.add(params);
        
        print("success");
    }
    
    /**
     * 删除用户
     */
    public void doDelete()
        throws Exception
    {
        String id = this.get("id");
        
        adminService.delByID(id);
        
        print("success");
    }
    
    /**
     * 编辑用户
     */
    public void doEdit()
        throws Exception
    {
        logger.debug("doEdit");
        
        // // 注：此字段 需要和页面的 表单的 name 以及 数据库的字段名 都要保持一致
        // 1， 定制需要往数据库更新的字段
        String field[] =
        {"username", "phone", "grouplist","idcard","department"};
        
        Map<String, Object> parameters = this.reqToMap(field);
        
        String id = this.get("id");
        String account = this.get("account");
        String pwd = this.get("pwd");
        
        adminService.update(parameters, account, pwd, id);
        
        AdminBean admin = this.getLoginInfo();
        
        admin.setUsername(parameters.get("username").toString());
        
        print("success");
    }
    
    /**
     * 查询 用户列表
     */
    public String query()
        throws Exception
    {
        logger.debug("query");
        
        // 用户账号
        String account = this.get("account");
        
        // 用户姓名
        String username = this.get("username");
        // 返回到页面
        this.set("account", account);
        this.set("username", username);
        
        Pager pager = new Pager(this.getReq());
        
        // 调用逻辑类 查询到结果
        pager = adminService.queryForPage(pager, account, username);
        
        this.set("PAGER", pager);
        
        return "query";
    }
    
    public String toAdd()
        throws Exception
    {
        
        // 是否支持和显示返回按钮
        String iscancel = this.get("iscancel");
        this.set("iscancel", iscancel);
        
        return "toAdd";
    }
    
    /**
     * 跳转到编辑页面
     */
    public String toEdit()
        throws Exception
    {
        
        String method = "doAdd";
        
        // 用户唯一ID
        String id = this.get("id");
        
        // 调用业务逻辑层 查询出用户信息
        Map<String, Object> o = adminService.loadAdminByID(id);
        
        // 内存中取到 角色列表
        HashMap<String, Object> roleMap = (HashMap<String, Object>)this.get("roleMap", "obj");
        
        // 所有权限里 去掉已经有的权限
        HashMap<String, Object> roles = new HashMap<String, Object>();
        roles.putAll(roleMap);
        
        // 已经有的权限
        HashMap<String, Object> roles_old = new HashMap<String, Object>();
        
        if (!StringTool.isEmpty(id))
        {
           
            // 找到用户权限
            String groupList_str = StringTool.getValue(o.get("grouplist"));
            String groupList[] = groupList_str.split("\\|");
            
            for (String string : groupList)
            {
                roles_old.put(string, roleMap.get(string));
                roles.remove(string);
                
            }
            
            method = "doEdit";
        }
        
        this.set("roles", roles);
        this.set("roles_old", roles_old);
        this.set("member", o);
        this.set("method", method);
        //this.set("dei_registerpart", AppConstant.DKey.get("15"));
        
        return "toEdit";
    }
    
    public void modpwd()
        throws Exception
    {
        
        AdminBean mb = getLoginInfo();
        
        String oldpwd = this.get("oldpwd");
        
        String newpwd = this.get("pwd");
        
        // 加密
        String pwd = adminService.encryption(mb.getAccount(), oldpwd);
        
        if (!mb.getPwd().equals(pwd))
        {
            print("fail");
            return;
        }
        
        adminService.updatePwd(mb.getAccount(), newpwd, mb.getId());
        
        mb.setPwd(pwd);
        
        print("success");
        
    }
    
    /**
     * 检查旧密码是否正确
     * 
     * @throws IOException
     * 
     * @see [类、类#方法、类#成员]
     */
    public void checkOldPwd()
        throws IOException
    {
        
        AdminBean mb = getLoginInfo();
        
        String oldpwd = this.get("oldpwd");
        
        // 加密
        String pwd = adminService.encryption(mb.getAccount(), oldpwd);
        
        if (mb.getPwd().equals(pwd))
        {
            print("success");
        }
        else
        {
            print("原始密码不正确！");
        }
    }
    
    /**
     * 检查账户是否重复
     * 
     * @return
     * @throws APIException
     * @see [类、类#方法、类#成员]
     */
    public void checkAccout()
        throws IOException
    {
        String account = this.get("account");
        try
        {
            String check = adminService.checkAccout(account);
            if ("false".equals(check))
            {
                print("false");
            }
            else
            {
                print("success");
            }
            
        }
        catch (APIException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
}
