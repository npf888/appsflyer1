package com.ami.texas.gamenew.action;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ami.api.common.Pager;
import com.ami.api.web.action.BaseAction;
import com.ami.common.TimeUtil;
import com.ami.texas.gamenew.service.GamenewService;

/**
 * 
 * 鏂扮殑鍚庡彴闇�姹傦紝 鑰佽檸鍙婄珵璧�  
 * @author JavaServer
 *
 */
@Controller
public class TournamentAction extends BaseAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(TournamentAction.class);
    
    @Autowired
    GamenewService gamenewService;
    
    
    /**
     * 鏀堕泦绯荤粺
     * @return
     * @throws Exception
     */
    public String query()
            throws Exception
        {
            logger.debug("query");
            
            String account = this.get("useraccount");
            
            String username = this.get("username");
            String date = this.get("date");
            if(StringUtils.isEmpty(date)){
            	date=TimeUtil.formatDate(new Date());
            }
            this.set("account", account);
            this.set("username", username);
            this.set("date", date);
            
            Pager pager = new Pager(this.getReq());
            
            pager = gamenewService.queryTournament(pager, account, username,date);
            
            this.set("PAGER", pager);
            
            return "query";
        }
    public String queryMoreDetail()
    		throws Exception
    {
    	logger.debug("query");
    	
    	String tournamentId = this.get("tournamentId");
    	String date = this.get("date");
    	
    	this.set("tournamentId", tournamentId);
    	this.set("date", date);
    	
    	Pager pager = new Pager(this.getReq());
    	
    	pager = gamenewService.queryMoreDetail(pager, tournamentId,date);
    	
    	this.set("PAGER", pager);
    	
    	return "queryMoreDetail";
    }
}
