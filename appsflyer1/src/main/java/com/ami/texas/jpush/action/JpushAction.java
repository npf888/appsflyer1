package com.ami.texas.jpush.action;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ami.api.web.action.BaseAction;
import com.nf.manage.dao.bean.JpushConfig;
import com.nf.manage.service.JpushService;


/**
 * 推送配置
 *
 */
@Scope("prototype")
@Component
public class JpushAction extends BaseAction
{
    /**
     * 注释内容
     */
    private static final long serialVersionUID = 1L;

    @Autowired
    private JpushService jpushService;

    private static Logger logger = Logger.getLogger(JpushAction.class);
    
    
    
    /**
     * 推送消息配置
     * @return
     */
    public String queryConfig()
    {
    	this.set("jpushConfig", jpushService.getAllConfig());
    	return "queryConfig";
    	
    }
    public String toEdit() throws Exception
        {
	    	String s = this.get("id");
			int id = Integer.parseInt(s);
			JpushConfig jc = jpushService.getConfigById(id);
			this.set("config", jc);
            return "toEdit";
        }
    public void saveConfig() throws IOException
    {
    	JpushConfig jc = new JpushConfig();
    	jc.setContent(this.get("content"));
    	jc.setDescription(this.get("description"));
    	jc.setFilterParams(this.get("filterParams"));
    	jc.setId(Integer.parseInt(this.get("id")));
    	jc.setJpushType(Integer.parseInt(this.get("jpushType")));
    	jc.setNotificationType(this.get("notificationType"));
    	jc.setPriority(Integer.parseInt(this.get("priority")));
    	jc.setSendTime(this.get("sendTime"));
    	jc.setTitle(this.get("title"));
    	jpushService.updateConfig(jc);
    	print("success");
    }
    /**
     * 立即推送
     */
    public void pushNow()
    {
    	try{
    		String s = this.get("id");
    		int id = Integer.parseInt(s);
    		jpushService.pushNow(id);
    		print("推送消息成功");
    	}catch (Exception e)
    	{
    		logger.error("", e);
    	}
    }
    
}

