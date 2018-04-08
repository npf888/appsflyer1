package com.ami.appsflyer.action;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ami.api.common.Pager;
import com.ami.api.exception.APIException;
import com.ami.api.web.action.BaseAction;
import com.ami.appsflyer.service.AppsflyerService;

/**
 * 报表统计分析
 * 
 * @author zhuweiliang
 * @version [版本号, 2013-5-29]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Scope("prototype")
@Component
public class AppsflyerAction extends BaseAction
{
    
    private static final long serialVersionUID = 1L;
    
    private static Logger logger = Logger.getLogger(AppsflyerAction.class);
    
    @Autowired
    private AppsflyerService matService;
    
    /**
     * 查询
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String logs()
    {
        String field[] = new String[]
        {"app_id", "imei", "start_date", "end_date", "platform","event_name","ac"};
        
        Map<String, String> params = this.reqToStrMap(field);
        
        this.set("params", params);
        
        Pager pager = new Pager(this.getReq());
        
        try
        {
            // 所有数据权限 功能
            alldata("103002005", params);
            
            pager = matService.queryForPage(params, pager);
            this.set("PAGER", pager);
        }
        catch (APIException e)
        {
            logger.error("query error", e);
        }
        
        return "logs";
    }
    
}
