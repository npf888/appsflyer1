package com.ami.sys.admin.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ami.api.common.AppConstant;
import com.ami.api.exception.APIException;
import com.ami.api.utill.StringTool;
import com.ami.api.web.action.BaseAction;
import com.ami.sys.admin.bean.AdminBean;
import com.ami.sys.admin.service.AdminService;
import com.ami.sys.right.bean.RightBean;
import com.ami.sys.right.service.RightService;

/**
 * 
 * 根据用户名查询该用户的密码，判断是否和输入密码一致
 * 
 * @author zhuweiliang
 * @version [版本号, 2013-5-29]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Scope("prototype")
@Component
public class LoginAction extends BaseAction
{
    
    private static final long serialVersionUID = 1L;
    
    private static Logger logger = Logger.getLogger(LoginAction.class);
    
    @Autowired
    private AdminService adminService;
    
    @Autowired
    private RightService rightService;
    
    /**
     * 返回登录页面
     */
    public String toSignIn()
    {
        return "toSignIn";
    }
    
    /**
     * 注销
     * 
     * @return
     * @throws APIException
     * @see [类、类#方法、类#成员]
     */
    public String loginOut()
        throws APIException
    {
        this.set("msg", "注销登陆成功");
        this.getReq().getSession().removeAttribute(AppConstant.LOGININFO_KEY);
        
        return "loginOut";
    }
    
    /**
     * 登陆
     * 
     * @return
     * @throws APIException
     * @see [类、类#方法、类#成员]
     */
    public String login()
        throws APIException
    {
        
        String path = this.getReq().getContextPath();
        String siteurl = this.getReq().getScheme() + "://" + this.getReq().getServerName() + ":" + this.getReq().getServerPort() + path + "/";
        
        this.set("path", path, "s");
        
        this.set("siteurl", siteurl, "s");
        
        String rtnString = "";
        
        // 关闭验证码
        // String piccode = this.get("piccode");
        // String picdode_2 =
        // (String)this.getReq().getSession().getAttribute("piccode");
        //
        // if (StringTool.isEmpty(picdode_2))
        // {
        // this.set("msg", "会话已超时请重新登陆");
        // return "login";
        // }
        //
        // if (!picdode_2.equals(piccode))
        // {
        // this.set("msg", "验证码不正确");
        // return "login";
        // }
        
        // memberService.get();
        String acount = StringTool.getValue(this.get("acount"));
        
        String pwd = StringTool.getValue(this.get("pwd"));
        
        AdminBean loginInfo = adminService.loadByAccountPwd(acount, pwd);
        
        if (loginInfo != null)
        {
            
            this.set("G_url", AppConstant.SERVER_URL,"s");
            this.set("G_ip", AppConstant.SERVER_ip,"s");
            
            // 将登陆信息存入seesion
            this.set(AppConstant.LOGININFO_KEY, loginInfo, "s");
            
            String state = this.get("state");
            
            logger.debug("是否保存cookie:state:" + state);
            
            if (null != state && "yes".equalsIgnoreCase(state))
            {
                this.setCookie(AppConstant.COOK_USER_NAME, StringTool.getValue(loginInfo.getAccount()));
                this.setCookie(AppConstant.COOK_USER_PWD, StringTool.getValue(loginInfo.getPwd()));
            }
            
            logger.info("login---Urealname:" + StringTool.getValue(loginInfo.getUsername()));
            
            // 加载权限
            String groupList = loadRight(loginInfo);
            
            // 创建菜单
            reateMenu(groupList, loginInfo);
            
            // 获取到通知消息
            // getNotification(loginInfo);
            
            
            
            rtnString = "toIndex";
            
        }
        else
        {
            // 检查判断错误次数
            checkErrorTimes();
            
            rtnString = "login";
            
        }
        return rtnString;
    }
    
    /**
     * 获取到通知消息
     * 
     * @param loginInfo
     * @throws APIException
     * @see [类、类#方法、类#成员]
     */
    public void getNotification(AdminBean loginInfo)
        throws APIException
    {
        String departmentid = loginInfo.getDepartment();
        
        Map<String, String> param = new HashMap<String, String>();
        
        param.put("departmentid", departmentid);
        
        // List<HashMap<String, Object>> notifications =
        // jobService.top10(param);
        //
        // this.set("notifications", notifications, "s");
    }
    
    /**
     * 检查失败的次数
     * 
     * @see [类、类#方法、类#成员]
     */
    private void checkErrorTimes()
    {
        String erroTimesStr = this.getCookie("erroTimes");
        int erroTimes = 0;
        if (!"".equals(erroTimesStr))
        {
            erroTimes = Integer.parseInt(erroTimesStr);
            erroTimes++;
            this.setCookie("erroTimes", erroTimes + "");
            
        }
        else
        {
            erroTimes = 1;
            this.setCookie("erroTimes", erroTimes + "");
        }
        
        this.set("erroTimes", erroTimes);
        this.set("msg", "帐号或者用户名错误");
    }
    
    /**
     * 加载用户权限
     * 
     * @param loginInfo
     * @see [类、类#方法、类#成员]
     */
    private String loadRight(AdminBean loginInfo)
    {
        
        Map<String, Map<String, Object>> roleMap;
        
        if ("admin".equals(loginInfo.getAccount()))
        {
            roleMap = rightService.loadAdminRole();
            
        }
        else
        {
            // 从application中获取所有角色列表
            roleMap = (Map<String, Map<String, Object>>)this.get("roleMap", "s");
        }
        
        // 操作员的角色列表
        String groupright_str = StringTool.getValue(loginInfo.getGrouplist());
        
        String groupright_arr[] = groupright_str.split("\\|");
        
        StringBuffer groupright = new StringBuffer();
        
        for (String id : groupright_arr)
        {
            Map<String, Object> map = roleMap.get(id);
            
            if (null != map)
            {
                groupright.append(map.get("groupright"));
                groupright.append(",");
            }
        }
        
        this.set("groupright", groupright, "s");
        
        return groupright.toString();
    }
    
    public void reateMenu(String groupList, AdminBean loginInfo)
    {
        List<RightBean> list = (List<RightBean>)this.get("allNavMap", "obj");
        
        StringBuffer menuStr = new StringBuffer();
        
        for (RightBean rightBean : list)
        {
            // 权限过滤
            if (!groupList.contains("," + rightBean.getSid() + ",") && !loginInfo.getAccount().equals("dev"))
            {
                continue;
            }
            createSubmenu(rightBean, menuStr, groupList, loginInfo);
            
        }
        
        this.set("menu", menuStr.toString(), "s");
    }
    
    /**
     * 创建子菜单
     * 
     * @param parent
     * @param menuStr
     * @see [类、类#方法、类#成员]
     */
    public void createSubmenu(RightBean parent, StringBuffer menuStr, String groupList, AdminBean loginInfo)
    {
        boolean ishasChild = null != parent.getChildren() && !parent.getChildren().isEmpty();
        menuStr.append("<li sid='" + parent.getSid() + "' >");
        
        String dropdownIcon = "<i class='icon-double-angle-right' ></i> ";
        
        if ("0".equals(parent.getNtype()))
        {
            dropdownIcon = "<i class='icon-desktop' ></i> ";
        }
        
        if (ishasChild)
        {
            menuStr.append("<a  class='dropdown-toggle' > " + dropdownIcon);
            menuStr.append(" <span class='menu-text'> ").append(parent.getTitle()).append(" </span> <b class='arrow icon-angle-down'></b> </a>");
        }
        else
        {
            menuStr.append("<a  title='" + parent.getTitle() + "' url='" + parent.getUrl() + "' class='leaf' > <i class='icon-double-angle-right'></i> " + parent.getTitle() + "</a> ");
        }
        
        if (null != parent.getChildren() && !parent.getChildren().isEmpty())
        {
            menuStr.append("<ul class='submenu'>");
            for (RightBean bean : parent.getChildren())
            {
                // 权限过滤
                if (!groupList.contains("," + bean.getSid() + ",") && !loginInfo.getAccount().equals("dev"))
                {
                    continue;
                }
                createSubmenu(bean, menuStr, groupList, loginInfo);
            }
            menuStr.append("</ul>");
            
        }
        menuStr.append("</li>");
    }
    
    public void resetPwd()
    {
        String account = this.get("account");
        String pwd = this.get("pwd");
        
        adminService.resetPwd(account, pwd);
        
    }
    
}
