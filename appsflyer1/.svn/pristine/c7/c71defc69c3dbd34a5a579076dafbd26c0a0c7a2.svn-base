package com.ami.texas.baccarat.action;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.ami.api.common.AppConstant;
import com.ami.api.common.Pager;
import com.ami.api.utill.HttpURLConnectionUtil;
import com.ami.api.utill.StringTool;
import com.ami.api.web.action.BaseAction;
import com.ami.texas.game.service.GameService;
import com.ami.texas.player.action.PlayerAction;


/**
 * 活动配置相关
 * 
 * @author Cici
 * @version [版本号, 2013-5-29]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Scope("prototype")
@Component
public class BaccaratAction extends BaseAction
{
	
    private static Logger logger = Logger.getLogger(PlayerAction.class);
    
    @Autowired
    GameService GameService;
    
    /**
     * 
     * 
     * @return
     * @throws Exception 
     * @see [类、类#方法、类#成员]
     */
    public String queryBaccartRoom() throws Exception
    {
        logger.debug("query");
        
        // 用户账号
        String account = this.get("useraccount");
        
        // 用户姓名
        String username = this.get("username");
        // 返回到页面
        this.set("account", account);
        this.set("username", username);
        
        Pager pager = new Pager(this.getReq());
        
        // 调用逻辑类 查询到结果
        pager = GameService.queryBaccartRoom(pager, account, username);
        
        this.set("PAGER", pager);
        
        return "queryBaccartRoom";
    }

    public void update() throws Exception
    {        
        JSONObject content = new JSONObject();
        content.put("roomid", Integer.parseInt(this.get("roomid")));
        
        String url = "";
        
        if(!StringTool.isEmpty(this.get("closed")))
        {
        	content.put("closed", Integer.parseInt(this.get("closed")));
        	url = AppConstant.SERVER_URL+"api/baccarat/room/close.json";
        }
        
        if(!StringTool.isEmpty(this.get("jackpot"))){
        	content.put("jackpot", Integer.parseInt(this.get("jackpot")));
        	url = AppConstant.SERVER_URL+"api/baccarat/room/data.json";
        }
        
        if(!StringTool.isEmpty(this.get("stock"))){
        	content.put("stock", Integer.parseInt(this.get("stock")));
        	url = AppConstant.SERVER_URL+"api/baccarat/room/data.json";
        }

        JSONObject result = HttpURLConnectionUtil.post(url, content);
        System.out.println(result);
    }
}
