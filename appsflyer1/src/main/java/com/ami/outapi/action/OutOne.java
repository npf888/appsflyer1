package com.ami.outapi.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.ami.api.web.action.BaseAction;

/**
 * 提供对外访问的接口
 * @author JavaServer
 *
 */
@SuppressWarnings("serial")
@Scope("prototype")
@Component
public class OutOne extends BaseAction{
	
	public void sendJson(HttpServletResponse response,String jsonString) throws IOException{
		PrintWriter out = response.getWriter();  
		out.println(jsonString);  
	    out.flush();  
	    out.close();
	}
	    
	public void getOneThing() throws IOException{
		JSONObject jb = new JSONObject();
		jb.put("aa", "aaa");
		jb.put("bb", "bbb");
		jb.put("cc", "ccc");
		sendJson(this.getRes(),jb.toJSONString());
	}
}
