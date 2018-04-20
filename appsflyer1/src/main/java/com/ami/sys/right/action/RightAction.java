package com.ami.sys.right.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ami.api.common.AppConstant;
import com.ami.api.exception.APIException;
import com.ami.api.utill.StringTool;
import com.ami.api.web.action.BaseAction;
import com.ami.sys.right.bean.RightBean;
import com.ami.sys.right.service.RightService;
import com.opensymphony.xwork2.ModelDriven;

@Scope("prototype")
@Component
public class RightAction extends BaseAction implements ModelDriven
{
    
    private static final long serialVersionUID = 1L;
    
    private static final Logger logger = Logger.getLogger(RightAction.class);
    
    @Autowired
    private RightService rightService;
    
    public void loadNav()
        throws IOException
    {
        try
        {
            List<RightBean> right = rightService.loadAllNav();
            logger.debug("size =" + right.size());
        }
        catch (APIException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        print("success");
    }
    
    public void checkRoleName()
        throws APIException, IOException
    {
        String id = this.get("id");
        if (null != id)
        {
            this.print("{state:1}");
        }
        String name = this.get("rolename");
        HashMap<String, String> par = new HashMap<String, String>();
        par.put("rolename", name);
        Object o = this.table.load("pre_common_rolevisual", par);
        
        if (null == o)
        {
            this.print("{state:1}");
            
        }
        else
        {
            this.print("{state:0}");
        }
        
    }
    
    public String doAdd()
        throws Exception
    {
        String id = this.get("id");
        
        String insert[] = new String[]
        {"rolename", "des", "groupright"};
        
        Map<String, Object> obj = this.reqToMap(insert);
        
        if (!StringTool.isEmpty(id))
        {
            rightService.update(obj, id);
        }
        else
        {
            rightService.insert(obj);
            
        }
        
        this.getApp().setAttribute("roleMap", rightService.intiRole());
        
        return "query";
    }
    
    public void doDelete()
        throws IOException
    {
        String id = this.get("id");
        
        try
        {
            rightService.del(id);
            
            this.getApp().setAttribute("roleMap", rightService.intiRole());
            
        }
        catch (APIException e)
        {
            print("error");
        }
        
        print("success");
    }
    
    public String doEdit()
        throws Exception
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    public String query()
        throws Exception
    {
        return "query";
    }
    
    public String toAdd()
        throws Exception
    {
        // Map<String, Map<String, Object>> navMap = rightService.loadAllNav();
        // this.set("allNavMap", navMap);
        List<RightBean> rightList = rightService.loadAllRight();
        
        this.set("rightList", rightList);
        return "toAdd";
    }
    
    public String toEdit()
        throws Exception
    {
        String id = this.get("id");
        
        Map<String, Object> o = rightService.loadByID(id);
        
        this.set("role", o);
        
        List<RightBean> rightList = rightService.loadAllRight();
        this.set("rightList", rightList);
        
        return "toAdd";
    }
    
    public String toMain()
        throws Exception
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
	public Object getModel()
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    
    
    public void refresh()
            throws Exception
    {
    	ServletContext servletContext = this.getReq().getSession().getServletContext();
        Map<String, Map<String, Object>> roleMap = rightService.intiRole();
        AppConstant.roleMap = roleMap;
        servletContext.setAttribute("roleMap", rightService.intiRole());
        servletContext.setAttribute("allNavMap", rightService.loadAllNav());
    }
}
