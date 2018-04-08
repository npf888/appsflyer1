package com.ami.texas.gamenew.action;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ami.api.common.Pager;
import com.ami.api.web.action.BaseAction;
import com.ami.common.ExcelHelper;
import com.ami.texas.gamenew.pojo.CollectPO;
import com.ami.texas.gamenew.service.GamenewService;

/**
 * 
 * 新的后台需求， 收集系统  
 * @author JavaServer
 *
 */
@Controller
public class CollectAction extends BaseAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(CollectAction.class);
    
    @Autowired
    GamenewService gamenewService;
    
    
    /**
     * 收集系统
     * @return
     * @throws Exception
     */
    public String query(){
            logger.debug("query");
            
            String account = this.get("useraccount");
            
            String username = this.get("username");
            this.set("account", account);
            this.set("username", username);
            
            Pager pager = new Pager(this.getReq());
            
            pager = gamenewService.queryCollect(pager, account, username);
            
            this.set("PAGER", pager);
            
            return "query";
   }
   /**
    * 导出excel
    */
   public void excel(){
	   List<CollectPO> collectVOList = gamenewService.querycollect();
	   ExcelHelper.xlsxExcel2007(collectVOList, this.getRes());
   }
}
