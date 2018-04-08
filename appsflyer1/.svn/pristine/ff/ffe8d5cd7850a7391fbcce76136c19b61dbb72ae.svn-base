package com.ami.weixin.course.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class UrlProperties {
	private static Logger logger = Logger.getLogger(UrlProperties.class);
	Map<String,String> gameServerUrlMap = new HashMap<String,String>();
	Map<String,String> backstageUrlMap = new HashMap<String,String>();
	
    private String gameServerPath = "";
    private String backstagePath = "";
     
    public UrlProperties(String backstage,String game_server){
    	this.gameServerPath=game_server;
    	this.backstagePath=backstage;
    }
	public void setGameServerUrls(){
		
		try {
			Properties property = new Properties();
			FileInputStream istream = new FileInputStream(gameServerPath);
			property.load(istream);
            istream.close();
            PropertyConfigurator.configure(property);
            Set<Entry<Object, Object>> allPro= property.entrySet();
            for(Entry<Object,Object> single:allPro){
            	Object key = single.getKey();
            	Object value = single.getValue();
            	String keyStr = String.valueOf(key);
            	String valueStr = String.valueOf(value);
            	gameServerUrlMap.put(keyStr, valueStr);
            	logger.info("game_server_urls_keyStr"+keyStr);
            	logger.info("game_server_urls_valueStr"+valueStr);
            }
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void setBackstageUrls(){
		
		try {
			Properties property = new Properties();
			FileInputStream istream = new FileInputStream(backstagePath);
			property.load(istream);
            istream.close();
            PropertyConfigurator.configure(property);
            Set<Entry<Object, Object>> allPro= property.entrySet();
            for(Entry<Object,Object> single:allPro){
            	Object key = single.getKey();
            	Object value = single.getValue();
            	String keyStr = String.valueOf(key);
            	String valueStr = String.valueOf(value);
            	backstageUrlMap.put(keyStr, valueStr);
            	logger.info("game_server_urls_keyStr"+keyStr);
            	logger.info("game_server_urls_valueStr"+valueStr);
            }
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public Map<String, String> getGameServerUrlMap() {
		return gameServerUrlMap;
	}
	public Map<String, String> getBackstageUrlMap() {
		return backstageUrlMap;
	}
	public String getGameServerPath() {
		return gameServerPath;
	}
	public String getBackstagePath() {
		return backstagePath;
	}
	
	
	
	
}
