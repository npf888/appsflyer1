package com.ami.texas.game.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ami.api.common.Pager;
import com.ami.api.web.action.BaseAction;
import com.ami.common.TimeUtil;
import com.ami.texas.game.pojo.DailyTaskVO;
import com.ami.texas.game.service.GameService;
import com.ami.texas.player.action.PlayerAction;

/**
 * 鎶ヨ〃缁熻鍒嗘瀽
 * 
 * @author zhuweiliang
 * @version [鐗堟湰鍙�, 2013-5-29]
 * @see [鐩稿叧绫�/鏂规硶]
 * @since [浜у搧/妯″潡鐗堟湰]
 */
@Scope("prototype")
@Component
public class GameAction extends BaseAction
{
    private static final long serialVersionUID = 1L;
    
    private static Logger logger = Logger.getLogger(PlayerAction.class);
    
    @Autowired
    GameService GameService;

    /**
     * 鑾峰彇鏈堝崱鍒楄〃
     * 
     * @return
     * @throws Exception
     * @see [绫汇�佺被#鏂规硶銆佺被#鎴愬憳]
     */
    public String queryMonthCard()
        throws Exception
    {
        logger.debug("query");
        
        // 鐢ㄦ埛璐﹀彿
        String account = this.get("useraccount");
        
        // 鐢ㄦ埛濮撳悕
        String username = this.get("username");
        // 杩斿洖鍒伴〉闈�
        this.set("account", account);
        this.set("username", username);
        
        Pager pager = new Pager(this.getReq());
        
        // 璋冪敤閫昏緫绫� 鏌ヨ鍒扮粨鏋�
        pager = GameService.queryMonthCard(pager, account, username);
        
        this.set("PAGER", pager);
        
        return "queryMonthCard";
    }
    
    /**
     * 鑾峰彇鍛ㄥ崱鍒楄〃
     * 
     * @return
     * @throws Exception
     * @see [绫汇�佺被#鏂规硶銆佺被#鎴愬憳]
     */
    public String queryWeekCard()
        throws Exception
    {
        logger.debug("query");
        
        // 鐢ㄦ埛璐﹀彿
        String account = this.get("useraccount");
        
        // 鐢ㄦ埛濮撳悕
        String username = this.get("username");
        // 杩斿洖鍒伴〉闈�
        this.set("account", account);
        this.set("username", username);
        
        Pager pager = new Pager(this.getReq());
        
        // 璋冪敤閫昏緫绫� 鏌ヨ鍒扮粨鏋�
        pager = GameService.queryWeekCard(pager, account, username);
        
        this.set("PAGER", pager);
        
        return "queryWeekCard";
    }
    
    /**
     * 寰峰窞娓告垙淇℃伅鍒楄〃
     * 
     * @return
     * @throws Exception
     * @see [绫汇�佺被#鏂规硶銆佺被#鎴愬憳]
     */
    public String queryTexas()
        throws Exception
    {
        logger.debug("query");
        
        // 鐢ㄦ埛璐﹀彿
        String account = this.get("useraccount");
        
        // 鐢ㄦ埛濮撳悕
        String username = this.get("username");
        // 杩斿洖鍒伴〉闈�
        this.set("account", account);
        this.set("username", username);
        
        Pager pager = new Pager(this.getReq());
        String currentPage = this.get("currentPage");
        // 璋冪敤閫昏緫绫� 鏌ヨ鍒扮粨鏋�
        pager = GameService.queryTexas(pager, account, username);
        
        this.set("PAGER", pager);
        this.set("currentPage", currentPage);
        
        return "queryTexas";
    }
    
    /**
     * 鑾峰彇璺戦┈鐏俊鎭�
     * 
     * @return
     * @throws Exception
     * @see [绫汇�佺被#鏂规硶銆佺被#鎴愬憳]
     */
    public String queryNews()
        throws Exception
    {
        logger.debug("query");
        
        // 鐢ㄦ埛璐﹀彿
        String account = this.get("useraccount");
        
        // 鐢ㄦ埛濮撳悕
        String username = this.get("username");
        // 杩斿洖鍒伴〉闈�
        this.set("account", account);
        this.set("username", username);
        
        Pager pager = new Pager(this.getReq());
        
        // 璋冪敤閫昏緫绫� 鏌ヨ鍒扮粨鏋�
        pager = GameService.queryNews(pager, account, username);
        
        this.set("PAGER", pager);
        
        return "queryNews";
    }
    
 
    
    /**
     * 鑾峰彇绛惧埌淇℃伅
     * 
     * @return
     * @throws Exception
     * @see [绫汇�佺被#鏂规硶銆佺被#鎴愬憳]
     */
    public String queryWeeklySignin()
        throws Exception
    {
        logger.debug("query");
        
        // 鐢ㄦ埛璐﹀彿
        String account = this.get("useraccount");
        
        // 鐢ㄦ埛濮撳悕
        String username = this.get("username");
        // 杩斿洖鍒伴〉闈�
        this.set("account", account);
        this.set("username", username);
        
        Pager pager = new Pager(this.getReq());
        
        // 璋冪敤閫昏緫绫� 鏌ヨ鍒扮粨鏋�
        pager = GameService.queryWeeklySignin(pager, account, username);
        
        this.set("PAGER", pager);
        
        return "querySignin";
    }
    
    /**
     * 鑾峰彇浠诲姟淇℃伅
     * 
     * @return
     * @throws Exception
     * @see [绫汇�佺被#鏂规硶銆佺被#鎴愬憳]
     */
    public String queryTask()
        throws Exception
    {
        logger.debug("query");
        
        // 鐢ㄦ埛璐﹀彿
        String account = this.get("useraccount");
        
        // 鐢ㄦ埛濮撳悕
        String username = this.get("username");
        // r
        // 杩斿洖鍒伴〉闈�
        this.set("account", account);
        this.set("username", username);
        
        Pager pager = new Pager(this.getReq());
        
        // 璋冪敤閫昏緫绫� 鏌ヨ鍒扮粨鏋�
        pager = GameService.queryTask(pager, account, username);
        
        this.set("PAGER", pager);
        
        return "queryTask";
    }
    /**
     * 缁熻 浠诲姟淇℃伅锛堟柊澧炵殑 2017-05-02锛�
     * 
     * 姣忔棩浠诲姟瀹屾垚鎯呭喌锛�1娆� 澶氬皯浜猴紝2娆″灏戜汉锛�3娆″灏戜汉锛�
     * 
     * @author 鐗涢箯椋�
     * @return
     * @throws Exception
     * @see [绫汇�佺被#鏂规硶銆佺被#鎴愬憳]
     */
    public String queryTaskStatistics()
    		throws Exception
    {
    	logger.debug("query");
    	
    	// 鐢ㄦ埛璐﹀彿
    	String account = this.get("useraccount");
    	
    	// 鐢ㄦ埛濮撳悕
    	String username = this.get("username");
    	//鐢ㄦ埛閫夋嫨鐨勬棩鏈�
    	String date = this.get("date");
    	// 杩斿洖鍒伴〉闈�
    	this.set("account", account);
    	this.set("username", username);
    	if(StringUtils.isNotBlank(date)){
    		this.set("date", date);
    	}else{
    		this.set("date", TimeUtil.formatYMDHMTime(new Date().getTime()));
    	}
    	
    	
    	// 璋冪敤閫昏緫绫� 鏌ヨ鍒扮粨鏋�
    	List<DailyTaskVO> dailyTaskVOS = GameService.queryTaskStatistics(account, username,date);
    	if(dailyTaskVOS != null && dailyTaskVOS.size()>0){
    		this.set("dailyTaskVOS", dailyTaskVOS);
    	}else{
    		this.set("dailyTaskVOS", new ArrayList<DailyTaskVO>());
    	}
    	return "queryTaskStatistics";
    }
    
    
    /**
     *
     * 
     * @return
     * @throws Exception
     * @see [绫汇�佺被#鏂规硶銆佺被#鎴愬憳]
     */
    public String queryBaccart()
        throws Exception
    {
        logger.debug("query");
        
        // 鐢ㄦ埛璐﹀彿
        String account = this.get("useraccount");
        
        // 鐢ㄦ埛濮撳悕
        String username = this.get("username");
        // 杩斿洖鍒伴〉闈�
        this.set("account", account);
        this.set("username", username);
        
        Pager pager = new Pager(this.getReq());
        
        // 璋冪敤閫昏緫绫� 鏌ヨ鍒扮粨鏋�
        pager = GameService.queryBaccart(pager, account, username);
        
        this.set("PAGER", pager);
        
        return "queryBaccart";
    }
    
    
    /**
     *
     * 
     * @return
     * @throws Exception
     * @see [绫汇�佺被#鏂规硶銆佺被#鎴愬憳]
     */
    public String queryBaccartRoom()
        throws Exception
    {
        logger.debug("query");
        
        // 鐢ㄦ埛璐﹀彿
        String account = this.get("useraccount");
        
        // 鐢ㄦ埛濮撳悕
        String username = this.get("username");
        // 杩斿洖鍒伴〉闈�
        this.set("account", account);
        this.set("username", username);
        
        Pager pager = new Pager(this.getReq());
        
        // 璋冪敤閫昏緫绫� 鏌ヨ鍒扮粨鏋�
        pager = GameService.queryBaccartRoom(pager, account, username);
        
        this.set("PAGER", pager);
        
        return "queryBaccartRoom";
    }
    
    /**
     * 鑾峰彇鍏跺畠鏉備贡淇℃伅
     * 
     * @return
     * @throws Exception
     * @see [绫汇�佺被#鏂规硶銆佺被#鎴愬憳]
     */
    public String queryOther()
        throws Exception
    {
        Pager pager = new Pager(this.getReq());
        
        // 璋冪敤閫昏緫绫� 鏌ヨ鍒扮粨鏋�
        pager = GameService.queryOther(pager);
        
        this.set("PAGER", pager);
        
        return "queryOther";
    }
    
    
}