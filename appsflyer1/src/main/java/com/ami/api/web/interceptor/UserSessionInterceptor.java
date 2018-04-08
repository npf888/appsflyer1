package com.ami.api.web.interceptor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class UserSessionInterceptor extends AbstractInterceptor
{
    
    private static final long serialVersionUID = -2185920708747626659L;
    
    private static final Log logger = LogFactory.getLog(UserSessionInterceptor.class);
    
    @Override
    public String intercept(ActionInvocation invocation)
        throws Exception
    {
       /* ActionContext ac = invocation.getInvocationContext();
        
        ServletContext servletContext = (ServletContext)ac.get(ServletActionContext.SERVLET_CONTEXT);
        WebApplicationContext wc = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        
        logger.info("Interceptor UserSessionInterceptor ... ");
        
        if (wc == null)
        {
            logger.error("ApplicationContext could not be found.");
            return "error";
            
        }
        
        AdminBean loginInfo = (AdminBean)ac.getSession().get(AppConstant.LOGININFO_KEY);
        
        if (null == loginInfo)
        {
            logger.error("用户session失效！");
            return "sessionError";
        }
        
        logger.info("User: [" + loginInfo.getUsername() + "]");*/
        
        return invocation.invoke();
    }
    
}
