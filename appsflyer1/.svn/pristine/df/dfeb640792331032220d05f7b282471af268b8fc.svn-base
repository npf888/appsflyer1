package com.agent.agent.action;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.agent.agent.pojo.BazooAgent;
import com.agent.agent.service.AgentLoginService;
import com.ami.api.common.AppConstant;
import com.ami.api.exception.APIException;
import com.ami.api.utill.StringTool;
import com.ami.api.web.action.BaseAction;
import com.ami.sys.admin.bean.AdminBean;
import com.ami.sys.admin.service.AdminService;
import com.ami.sys.right.bean.RightBean;
import com.ami.sys.right.service.RightService;
/**
 * 代理商管理后台  登录页面
 * @author JavaServer
 *
 */
@SuppressWarnings("serial")
@Scope("prototype")
@Component
public class AgentLoginAction extends BaseAction{
	
	private static Logger logger = Logger.getLogger(AgentLoginAction.class);
	
	@Autowired
	private AgentLoginService agentLoginService;
	@Autowired
	private RightService rightService;
	@Autowired
	private AdminService adminService;
	
	public String login(){
		
		return "login";
	}
	
	public String toLogin()throws APIException
    {
        
        String path = this.getReq().getContextPath();
        String siteurl = this.getReq().getScheme() + "://" + this.getReq().getServerName() + ":" + this.getReq().getServerPort() + path + "/";
        
        this.set("path", path, "s");
        
        this.set("siteurl", siteurl, "s");
        
        String rtnString = "";
        
        String username = StringTool.getValue(this.get("username"));
        
        String password = StringTool.getValue(this.get("password"));
        
        
        BazooAgent bazooAgent = agentLoginService.getAgentUser(username, password);
        
        BazooAgent exitBazooAgent = (BazooAgent)this.getReq().getSession().getAttribute(AppConstant.LOGININFO_AGENT_KEY);
        
        if(exitBazooAgent == null && bazooAgent==null){
        	return "login";
        }
        //这里写的 固定了 不能变
        AdminBean loginInfo = adminService.loadByAccountPwd("agent", "123456");
        
        if (loginInfo != null)
        {
            
            this.set("G_url", AppConstant.SERVER_URL,"s");
            this.set("G_ip", AppConstant.SERVER_ip,"s");
            
            // 将登陆信息存入seesion
            this.set(AppConstant.LOGININFO_AGENT_KEY, bazooAgent, "s");
            
            String state = this.get("state");
            
            logger.debug("是否保存cookie:state:" + state);
            
            if (null != state && "yes".equalsIgnoreCase(state))
            {
                this.setCookie(AppConstant.COOK_USER_NAME, StringTool.getValue(bazooAgent.getUsername()));
                this.setCookie(AppConstant.COOK_USER_PWD, StringTool.getValue(bazooAgent.getPasswords()));
            }
            
            if(bazooAgent != null){
            	logger.info("agentLogin---Urealname:" + StringTool.getValue(bazooAgent.getUsername()));
            }
            
            // 加载权限
            String groupList = loadRight(loginInfo);
            
            // 创建菜单
           reateMenu(groupList, loginInfo);
            
            
            rtnString = "index";
            
        }
        else
        {
            rtnString = "login";
            
        }
		
		return rtnString;
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
        this.getReq().getSession().removeAttribute(AppConstant.LOGININFO_AGENT_KEY);
        
        return "loginOut";
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
	
}
