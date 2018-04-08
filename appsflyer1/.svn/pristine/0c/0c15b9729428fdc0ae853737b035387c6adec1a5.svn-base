package com.ami.texas.server.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ami.api.common.AppConstant;
import com.ami.api.utill.HttpURLConnectionUtil;
import com.ami.api.web.action.BaseAction;



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
public class ServerAction extends BaseAction
{
    /**
     * 注释内容
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     * 
     * @return
     * @throws Exception 
     * @see [类、类#方法、类#成员]
     */
    public String queryServer() throws Exception
    {
        String url = AppConstant.SERVER_URL+"api/server/list.json";
        JSONObject result = HttpURLConnectionUtil.get(url);
        
        List<Map<String, Object>> server = new ArrayList<Map<String, Object>>();
        
        JSONArray temp = result.getJSONArray("result");       
        for(int i=0; i < temp.size();i++)
        {
            server.add(temp.getJSONObject(i));
        }

        this.set("servers", server);
        return "queryServer";
    }

    public void update() throws Exception
    {        
        JSONObject content = new JSONObject();
        content.put("serverId", this.get("serverId"));
        content.put("state", Integer.parseInt(this.get("state")));
        
        String url = AppConstant.SERVER_URL+"api/server/update.json";
        JSONObject result = HttpURLConnectionUtil.post(url, content);
        System.out.println(result);
    }
   
}

