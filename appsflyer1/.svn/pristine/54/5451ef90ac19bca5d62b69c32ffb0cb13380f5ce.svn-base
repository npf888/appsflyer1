package com.ami.api.web.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.ami.api.common.AppConstant;
import com.ami.api.db.DBManagerService;
import com.ami.api.db.Table;
import com.ami.api.utill.StringTool;
import com.ami.sys.admin.bean.AdminBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 系统action 基础类 <一句话功能简述> <功能详细描述>
 * 
 * @author zhuweiliang
 * @version [版本号, Apr 18, 2012]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public abstract class BaseAction extends ActionSupport
{
    
    private static final long serialVersionUID = -5876980456199846559L;
    
    private static final Log logger = LogFactory.getLog(BaseAction.class);
    
    public static final String SUCCESS = "success";
    
    @Autowired
    protected DBManagerService db;
    
    @Autowired
    protected Table table;
    
    /**
     * 设置登录时反回所要跳转的页面
     */
    protected String returnPage;
    
    // private Waiter waiter;
    /**
     * 当前调用的方法名，调用Action此属性必须设置 例如：main.do?method=toAdd
     * 表示要调用main这个Action中的toAdd方法
     */
    protected String method = "";
    
    /**
     * 返回到错误页面 位置在common/systemError.jsp
     */
    public static final String RESULT_ERROR = "systemError";
    
    /**
     * 新增或编辑返回到成功页面 位置在common/editSuccess.jsp，同时重新查询主界面上半区。
     */
    public static final String RESULT_EDITSUCCESS = "editSuccess";
    
    /**
     * 返回到添加成功页面 位置在common/addSuccess.jsp
     */
    public static final String RESULT_ADDSUCCESS = "addSuccess";
    
    /**
     * 返回到添加成功页面 位置在common/addSuccess.jsp
     */
    public static final String RESULT_DELETEFAILURE = "deleteFailure";
    
    /**
     * 新增或编辑返回到成功页面 位置在common/editSuccessForm.jsp，同时重新查询主界面上半区。
     */
    public static final String RESULT_EDITSUCCESSFORM = "editSuccessForm";
    
    /**
     * 用于指定增加或编辑成功后返回的页面 例如：main.do?method=toAdmin
     */
    protected String returnURL;
    
    /**
     * 用于指定添加成功后继续添加下一个的页面 例如：main.do?method=toAdd
     */
    protected String addURL;
    
    /**
     * 用于指定继续添加下一个需要根据初始化的参数列表 例如：mediaType;advposItem;mediumid
     */
    protected String addInitParams;
    
    /**
     * 当前Action的完整URI ,这个属性是执行Action时自动赋值的，可以在页面上获取 例如 ： /SCSM/main.do
     */
    protected String currentActionURI;
    
    /**
     * 当前Action的名字,这个属性是执行Action时自动赋值的，可以在页面上获取 例如：main
     */
    protected String currentActionName;
    
    /**
     * 编辑成功后，刷新的iframe的名字
     */
    protected String reloadPageName;
    
    /**
     * 编辑成功后，要刷页面form的ID
     */
    protected String parentFormId;
    
    private String successFlag;
    
    /*
     * (non-Javadoc)
     * 
     * @see com.opensymphony.xwork2.ActionSupport#execute()
     */
    @Override
	public String execute()
    {
        
        try
        {
            super.execute();
            
            successFlag = executeMethod(getMethod());
            return successFlag;
        }
        
        catch (Exception e)
        {
            if (null != e.getCause())
            {
                logger.error(e.getCause(), e);
                
                this.getReq().setAttribute("exception", e.getCause().getMessage());
                
            }
            else
            {
                // logger.error(e.getCause().getMessage());
                e.printStackTrace();
                this.getReq().setAttribute("exception", e.getMessage());
            }
            
            // this.addActionError(this.getText("error.msg"));
            return RESULT_ERROR;
        }
    }
    
    /**
     * 处理指定的Action方法请求
     * 
     * @param method
     * @return
     * @throws Exception
     */
    protected String executeMethod(String method)
        throws Exception
    {
        if (StringTool.isEmpty(method))
        {
            return "toLogin";
        }
        
        String cp_ext_serverid = this.getReq().getParameter("cp_ext_serverid");
        if (!StringTool.isEmpty(cp_ext_serverid))
        {
            this.getReq().getSession().setAttribute("cp_ext_serverid", cp_ext_serverid);
        }
        
        this.setCurrentActionName(ActionContext.getContext().getName());
        String url = ServletActionContext.getRequest().getRequestURI();
        
        // String myAllurl = this.get("myAllurl");
        //
        // String currentURL = this.getCurrentActionName() + ".do?method=" +
        // method;
        
        this.setCurrentActionURI(url);
        Class[] c = null;
        Method m = this.getClass().getMethod(method, c);
        Object[] o = null;
        String result = (String)m.invoke(this, o);
        return result;
    }
    
    /**
     * 从request getAttribute getParameter getSession 依次获取值
     * 
     * @param key
     * @param isObject 枚举：obj 返回Object,str 返回 String
     * @return
     */
    public Object get(String key, String obj)
    {
        
        Object o = null;
        
        if ((o = this.getReq().getAttribute(key)) != null)
        {
            // return o;
        }
        else if ((o = this.getReq().getParameter(key)) != null)
        {
            // return o;
        }
        else if ((o = this.getReq().getSession().getAttribute(key)) != null)
        {
            // return o;
        }
        else if ((o = this.getReq().getSession().getServletContext().getAttribute(key)) != null)
        {
            // return o;
        }
        
        if ("obj".equals(obj))
        {
            return o;
        }
        else if ("str".equals(obj))
        {
            return StringTool.getValue(o);
        }
        return o;
    }
    
    /**
     * 从request getAttribute getParameter getSession 依次获取值
     * 
     * @param key
     * @return
     */
    public String get(String key)
    {
        String o = null;
        
        o = get(key, "str").toString();
        
        return o;
    }
    
    /**
     * 获取登录用户session信息
     * 
     * @return
     */
    public AdminBean getLoginInfo()
    {
        HttpServletRequest request = ServletActionContext.getRequest();
        AdminBean loginInfo = new AdminBean();
        if (request.getSession().getAttribute(AppConstant.LOGININFO_KEY) != null)
        {
            loginInfo = (AdminBean)request.getSession().getAttribute(AppConstant.LOGININFO_KEY);
            
        }
        return loginInfo;
    }
    
    /**
     * 所有数据权限 功能
     * 
     * 
     * @param right 数据权限码
     * @param param
     * @see [类、类#方法、类#成员]
     */
    public void alldata(String right, Map<String, String> param)
    {
        String groupright = this.get("groupright");
        AdminBean admin = this.getLoginInfo();
        // 如果拥有所有数据权限 则返回
        if (groupright.indexOf(right) == -1)
        {
            param.put("department", admin.getDepartment());
        }
    }
    
    /**
     * 给request 设置属性
     * 
     * @param key key
     * @param o value
     */
    public void set(String key, Object o)
    {
        this.getReq().setAttribute(key, o);
    }
    
    /**
     * 给 非request 设置属性
     * 
     * @param key key
     * @param o value
     * @param type 枚举 s ：给session 设置属性
     */
    public void set(String key, Object o, String type)
    {
        if ("s".equals(type))
        {
            this.getReq().getSession().setAttribute(key, o);
        }
        
    }
    
    protected Map<String, Object> reqToMap(String arr[])
    {
        Map<String, Object> obj = new HashMap<String, Object>();
        
        for (String string : arr)
        {
            obj.put(string, this.get(string));
        }
        
        return obj;
    }
    
    protected Map<String, String> reqToStrMap(String arr[])
    {
        Map<String, String> obj = new HashMap<String, String>();
        
        for (String string : arr)
        {
            obj.put(string, this.get(string));
        }
        
        return obj;
    }
    
    /**
     * 取cookie的值
     * 
     * @param key
     * @return
     */
    public String getCookie(String key)
    {
        Cookie[] cookie = this.getReq().getCookies();
        if (null == cookie)
        {
            return "";
        }
        
        for (int i = 0; i < cookie.length; i++)
        {
            Cookie c = cookie[i];
            
            if (key.equals(c.getName()))
            {
                return c.getValue();
            }
            
        }
        
        return "";
        
    }
    
    /**
     * 取cookie的值
     * 
     * @param key
     * @return
     */
    public void setCookie(String key, String value)
    {
        Cookie c = new Cookie(key, value);
        this.getRes().addCookie(c);
        
    }
    
    /**
     * 获取request
     * 
     * @return
     */
    public HttpServletRequest getReq()
    {
        HttpServletRequest request = ServletActionContext.getRequest();
        
        return request;
    }
    
    /**
     * 获取response
     * 
     * @return
     */
    public HttpServletResponse getRes()
    {
        HttpServletResponse response = ServletActionContext.getResponse();
        return response;
    }
    
    /**
     * 获取response
     * 
     * @return
     */
    public ServletContext getApp()
    {
        
        return this.getReq().getSession().getServletContext();
    }
    
    /**
     * 获取PrintWriter
     * 
     * @return
     * @throws IOException
     */
    public PrintWriter getWriter()
        throws IOException
    {
        PrintWriter out = this.getRes().getWriter();
        return out;
    }
    
    /**
     * 直接打印信息
     * 
     * @return
     * @throws IOException
     */
    public void print(String message, String character)
        throws IOException
    {
        // this.getRes().setContentType("text/html");
        this.getRes().setCharacterEncoding(character);
        PrintWriter out = this.getRes().getWriter();
        // System.out.println(StringTool.setEnCodingGBK(message));
        out.print(message);
        out.flush();
        out.close();
        
        // return out;
    }
    
    /**
     * 直接打印信息
     * 
     * @return
     * @throws IOException
     */
    public void print(String message)
        throws IOException
    {
        print(message, "UTF-8");
        // return out;
    }
    
    /**
     * 直接打印信息
     * 
     * @return
     * @throws IOException
     */
    public void printJosn(Object obj)
        throws IOException
    {
        
        print(JSON.toJSONString(obj), "UTF-8");
        // return out;
    }
    
    /**
     * 直接打印信息
     * 
     * @return
     * @throws IOException
     */
    public void printJosnArray(Object obj)
        throws IOException
    {
        
        print(JSON.toJSONString(obj), "UTF-8");
        // return out;
    }
    
    public String getMethod()
    {
        return method;
    }
    
    public void setMethod(String method)
    {
        this.method = method;
    }
    
    public String getReturnURL()
    {
        return returnURL;
    }
    
    public void setReturnURL(String returnURL)
    {
        this.returnURL = returnURL;
    }
    
    public String getAddURL()
    {
        return addURL;
    }
    
    public void setAddURL(String addURL)
    {
        this.addURL = addURL;
    }
    
    public String getAddInitParams()
    {
        return addInitParams;
    }
    
    public void setAddInitParams(String addInitParams)
    {
        this.addInitParams = addInitParams;
    }
    
    public void setCurrentActionURI(String currentActionURI)
    {
        this.currentActionURI = currentActionURI;
    }
    
    public String getReloadPageName()
    {
        return reloadPageName;
    }
    
    public void setReloadPageName(String reloadPageName)
    {
        this.reloadPageName = reloadPageName;
    }
    
    /**
     * 获取当前执行的Action的URI 例如：/SCSM/test/simpleUser.do
     * 
     * @return
     */
    public String getCurrentActionURI()
    {
        return currentActionURI;
    }
    
    /**
     * 获取当前执行的Action的名字 例如：simpleUser
     * 
     * @return
     */
    public String getCurrentActionName()
    {
        return currentActionName;
    }
    
    /**
     * @param currentActionName
     */
    public void setCurrentActionName(String currentActionName)
    {
        this.currentActionName = currentActionName;
    }
    
    /**
     * 获取当前请求的的URI
     * 
     * @return
     */
    protected String getCurrentRequestURI()
    {
        String uri = ServletActionContext.getRequest().getRequestURI();
        String contextPath = ServletActionContext.getRequest().getContextPath();
        if (contextPath != null && contextPath.length() > 0)
            uri = uri.substring(contextPath.length() + 1);
        else
            uri = uri.substring(1);
        return uri;
    }
    
    protected String getBasePath()
    {
        return ServletActionContext.getRequest().getScheme() + "://" + ServletActionContext.getRequest().getServerName() + ":" + ServletActionContext.getRequest().getServerPort();
    }
    
    /**
     * web应用上下文
     */
    protected String ctx = new String("");
    
    public String getCtx()
    {
        if (StringTool.isEmpty(ctx))
        {
            ctx = this.getReq().getContextPath();
        }
        return ctx;
    }
    
    public String getSuccessFlag()
    {
        return successFlag;
    }
    
    public void setSuccessFlag(String successFlag)
    {
        this.successFlag = successFlag;
    }
    
    public void test()
    {
        // this.getReq().get
        
        HttpSession session = this.getReq().getSession();
        session.removeAttribute("");
        Enumeration names = session.getAttributeNames();
        while (names.hasMoreElements())
        {
            Object o = names.nextElement();
            System.out.println(session.getAttribute((String)o));
        }
    }
    
    public String getReturnPage()
    {
        if (returnPage == null)
        {
            returnPage = "info";
        }
        return returnPage;
    }
    
    public String getParentFormId()
    {
        return parentFormId;
    }
    
    public void setParentFormId(String parentFormId)
    {
        this.parentFormId = parentFormId;
    }
}
