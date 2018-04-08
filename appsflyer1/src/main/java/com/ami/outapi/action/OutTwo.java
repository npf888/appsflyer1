package com.ami.outapi.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ami.api.web.action.BaseAction;

@SuppressWarnings("serial")
@Scope("prototype")
@Component
public class OutTwo extends BaseAction{

	private Map<String,String> dataMap;
	public Map<String, String> getDataMap() {
		return dataMap;
	}
	public void setDataMap(Map<String, String> dataMap) {
		this.dataMap = dataMap;
	}
	
	
	
	public String getOneThing() throws IOException{
		dataMap  = new HashMap<String,String>();
		dataMap.put("aa", "aaa");
		dataMap.put("bb", "bbb");
		dataMap.put("cc", "ccc");
		return "json";
	}
	
	
}
