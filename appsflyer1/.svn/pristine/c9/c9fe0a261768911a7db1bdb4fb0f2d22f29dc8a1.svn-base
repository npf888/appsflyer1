package com.ami.texas.gamenew.action;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ami.api.common.Pager;
import com.ami.api.web.action.BaseAction;
import com.ami.texas.game.service.GameService;
import com.ami.texas.gamenew.service.GamenewService;

/**
 * 
 * 新的后台需求，    世界boss
 * @author JavaServer
 *
 */
@Controller
public class WorldBossAction extends BaseAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(WorldBossAction.class);
    
    @Autowired
    GamenewService gamenewService;
    
    
    /**
     * 收集系统
     * @return
     * @throws Exception
     */
    public String query()
            throws Exception
        {
            logger.debug("query");
            
            String account = this.get("useraccount");
            
            String bossType = this.get("bossType");
            String startDate = this.get("startDate");
            String endDate = this.get("endDate");
            this.set("account", account);
            this.set("bossType", bossType);
            this.set("startDate", startDate);
            this.set("endDate", endDate);
            
            Pager pager = new Pager(this.getReq());
            
            pager = gamenewService.queryWorldBoss(pager, account, bossType,startDate,endDate);
            
            this.set("PAGER", pager);
            
            return "query";
        }
    /**
     * 所有的细节
     * @return
     * @throws Exception
     */
    public String queryWorldBossDetail()
    		throws Exception
    {
    	logger.debug("query");
    	
    	String bossId = this.get("bossId");
    	String date = this.get("date");
    	this.set("bossId", bossId);
    	this.set("date", date);
    	
    	Pager pager = new Pager(this.getReq());
    	
    	pager = gamenewService.queryWorldBossDetail(pager, bossId, date);
    	
    	this.set("PAGER", pager);
    	
    	return "queryWorldBossDetail";
    }
    
    /**
     * 细到每个人-最小单位是人 ，以人分组
     * @return
     * @throws Exception
     */
    public String queryWorldBossDetailOne()
    		throws Exception
    {
    	logger.debug("query");
    	
    	String bossId = this.get("bossId");
    	String date = this.get("date");
    	this.set("bossId", bossId);
    	this.set("date", date);
    	
    	Pager pager = new Pager(this.getReq());
    	
    	pager = gamenewService.queryWorldBossDetailOne(pager, bossId, date);
    	
    	this.set("PAGER", pager);
    	
    	return "queryWorldBossDetailOne";
    }
}
