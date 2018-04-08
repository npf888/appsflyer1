package com.agent.agent.interceptor;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.StrutsStatics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.agent.agent.pojo.BazooAgent;
import com.ami.api.common.AppConstant;
import com.ami.api.web.interceptor.BaseInterceptor;
import com.ami.sys.adminlog.service.AdminLogService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

@Component
public class BaseAgentInterceptor extends AbstractInterceptor
{
    
    private static final long serialVersionUID = -2185920708747626659L;
    
    private static final Log logger = LogFactory.getLog(BaseInterceptor.class);
    
    @Autowired
    AdminLogService adminLogService;
    
    @Override
    public String intercept(ActionInvocation invocation)
        throws Exception
    {
        ActionContext ac = invocation.getInvocationContext();
        
        HttpServletRequest request = (HttpServletRequest)ac.get(StrutsStatics.HTTP_REQUEST);
        
        String userRemoteAddr = request.getRemoteAddr();
        
        String actionName = ac.getName();
        
        String methodName = "";
        
        Map map = ac.getParameters();
        
        String[] _methodName = (String[])map.get("method");
        if (_methodName != null)
        {
            methodName = _methodName[0];
        }
        
        logger.info("\r\n------------------START REQUEST------------------");
        logger.info("start BaseInterceptor ... ");
        
        logger.info("Request from: [" + userRemoteAddr + "]");
        
        logger.info("Action=" + actionName + "?Method=" + methodName);
        
        String str = "";
        // 取到登录名
        String account = getLoginInfo(methodName, map);
        
        try
        {
    		str = Integrceptor2(invocation,actionName,methodName);
    		adminLogService.addLog(account, actionName, methodName, userRemoteAddr, "成功");
            
        }
        catch (Exception e)
        {
            adminLogService.addLog(account, actionName, methodName, userRemoteAddr, "失败：" + e.getMessage());
            throw e;
        }
        
        logger.info("end BaseInterceptor ");
        
        return str;
    }
    private String Integrceptor2(ActionInvocation invocation,String actionName, String methodName) throws Exception{
    	ActionContext ac = invocation.getInvocationContext();
        
        ServletContext servletContext = (ServletContext)ac.get(StrutsStatics.SERVLET_CONTEXT);
        WebApplicationContext wc = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        
        logger.info("Interceptor UserSessionInterceptor ... ");
        
        if (wc == null)
        {
            logger.error("ApplicationContext could not be found.");
            return "error";
            
        }
        
        BazooAgent bazooAgent = (BazooAgent)ac.getSession().get(AppConstant.LOGININFO_AGENT_KEY);
        //放开微信的接口
        if("agentLogin".equals(actionName) && "toLogin".equals(methodName)){
    		 return invocation.invoke();
    	}
        if (null == bazooAgent)
        {
            logger.error("用户session失效！");
            return "sessionAgentError";
        }else{
        	logger.info("User: [" + bazooAgent.getUsername() + "]");
        }
        
        
        return invocation.invoke();
    }
    
    /**
     * 获取登录用户session信息
     * 
     * @return
     */
    public String getLoginInfo(String methodName, Map map)
    {
        // 如果是登录 则直接返回登录名
        if ("login".equals(methodName))
        {
            String[] _acount = (String[])map.get("acount");
            if (null != _acount)
            {
                return _acount[0];
            }
            else
            {
                return null;
            }
            
        }
        
        HttpServletRequest request = ServletActionContext.getRequest();
        BazooAgent BazooAgent = new BazooAgent();
        if (request.getSession().getAttribute(AppConstant.LOGININFO_AGENT_KEY) != null)
        {
        	BazooAgent = (BazooAgent)request.getSession().getAttribute(AppConstant.LOGININFO_AGENT_KEY);
            return BazooAgent.getUsername();
        }
        else
        {
            return null;
        }
    }
    

}
