package com.ami.sys.adminlog.action;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ami.api.common.Pager;
import com.ami.api.web.action.BaseAction;
import com.ami.sys.adminlog.service.AdminLogService;

/**
 * 
 * <一句话功能简述> <功能详细描述>
 * 
 * @author zhanglei
 * @version [版本号, 2013-7-23]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Scope("prototype")
@Component
public class AdminLogAction extends BaseAction
{
    
    private static final long serialVersionUID = 1L;
    
    private static Logger logger = Logger.getLogger(AdminLogAction.class);
    
    @Autowired
    private AdminLogService adminLogService;
    
    /**
     * 查寻登陆日志
     * 
     * @return string
     * @throws Exception
     * @see [类、类#方法、类#成员]
     */
    public String toQuery_logrecord()
        throws Exception
    {
        logger.debug("toQuery_logrecord");
        
        // 获取开始时间
        String starttime = this.get("login_time");
        // 获取截止时间
        String endtime = this.get("logout_time");
        
        // 返回到页面端
        this.set("login_time", starttime);
        this.set("logout_time", endtime);
        
        Pager pager = new Pager(this.getReq());
        
        // 调用service方法
        pager = adminLogService.queryLogrecord(starttime, endtime, pager);
        
        this.set("PAGER", pager);
        return "logrecordquery";
    }
    
}
