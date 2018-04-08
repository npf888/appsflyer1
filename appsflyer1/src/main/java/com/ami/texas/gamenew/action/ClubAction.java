package com.ami.texas.gamenew.action;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ami.api.common.Pager;
import com.ami.api.web.action.BaseAction;
import com.nf.manage.service.ClubService;
import com.nf.manage.service.beans.ClubMemberData;

/**
 * 
 * 新的后台需求，俱乐部
 * @author JavaServer
 *
 */
@Controller
public class ClubAction extends BaseAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(ClubAction.class);
    
    @Autowired
    ClubService clubService;
    
    
    public String query() throws Exception
    {
        logger.debug("query");
        Pager pager = new Pager(this.getReq());
        String clubName = this.get("clubName").trim();
        String from = this.get("from").trim();
        String to = this.get("to").trim();
        String level = this.get("level").trim();
        String money = this.get("money").trim();
        String huoyue = this.get("huoyue").trim();
        QueryParams qp = new QueryParams(clubName, from, to, level, money, huoyue);
        
        clubService.getAllClubData(pager, qp);
        this.set("PAGER", pager);
        return "query";
    }
    
    public String membersInfo() throws Exception
    {
        logger.debug("membersInfo");
        List<ClubMemberData> datas = clubService.membersInfo(this.get("clubId"));
        this.set("datas", datas);
        return "membersInfo";
    }
    
    
    public static class QueryParams
    {
    	public String clubName;
    	public String from;
    	public String to;
    	public String level;
    	public String money;
    	public String huoyue;
    	QueryParams(String clubName, String from, String to, String level, String money, String huoyue)
    	{
    		this.clubName = clubName;
    		this.from = from;
    		this.to = to;
    		this.level = level;
    		this.money = money;
    		this.huoyue = huoyue;
    	}
    }
}
