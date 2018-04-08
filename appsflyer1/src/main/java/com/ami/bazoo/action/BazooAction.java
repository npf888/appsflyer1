package com.ami.bazoo.action;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ami.api.exception.APIException;
import com.ami.api.web.action.BaseAction;
import com.ami.bazoo.pojo.BazooRoomVO;
import com.ami.bazoo.pojo.BazooTaskVO;
import com.ami.bazoo.service.BazooService;
import com.ami.common.TimeUtil;

/**
 * 
 * 吹牛后台
 * @author JavaServer
 *
 */
@SuppressWarnings("serial")
@Scope("prototype")
@Component
public class BazooAction extends BaseAction{

	@Autowired
	private BazooService bazooService;
	/**
	 * 图形
	 * @return
	 * @throws APIException
	 */
	 public String queryList() throws APIException
	    {
	        
	        
		 	List<BazooRoomVO> list = bazooService.queryList(this.getReq());
		 	if(list != null && list.size()>0){
		 		for(BazooRoomVO vo:list){
		 			List<BazooTaskVO> taskList = vo.getBazooTaskVOS();
		 			for(BazooTaskVO task:taskList){
		 				task.setTimeStr(TimeUtil.formatLongTOStr(task.getTime()));
		 				String idStr = "";
		 				for(Long id:task.getNextPlayerIds()){
		 					idStr+=id+",";
		 				}
		 				task.setIdsStr(idStr.substring(0, idStr.length()-1));
		 			}
		 		}
		 	}
	        this.set("list", list);
	        return "index";
	    }
	 
	 /**
	  * 列表
	  * @return
	  * @throws APIException
	  */
	 public String queryIndexList() throws APIException
	 {
		String roomNumber = this.get("roomNumber");
		if(StringUtils.isNotBlank(roomNumber)){
			this.set("roomNumber",roomNumber);
		}
		 
		 List<BazooRoomVO> list = bazooService.queryList(this.getReq());
		 try{
			 if(list != null && list.size()>0){
				 for(BazooRoomVO vo:list){
					 List<BazooTaskVO> taskList = vo.getBazooTaskVOS();
					 for(BazooTaskVO task:taskList){
						 task.setTimeStr(TimeUtil.formatLongTOStr(task.getTime()));
						 String idStr = "";
						 for(Long id:task.getNextPlayerIds()){
							 idStr+=id+",";
						 }
						 if(idStr.length()-1 > 0){
							 task.setIdsStr(idStr.substring(0, idStr.length()-1));
						 }
					 }
				 }
			 }
			 this.set("list", list);
			 
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 return "indexList";
	 }
	 public String shutDownThread() throws APIException
	 {
		 
		 String passportId = this.get("passportId");
		 bazooService.shutDownThread(passportId);
		 return "redirectIndex";
		 
	 }
	 public String delete() throws APIException
	 {
		 
		 String passportId = this.get("passportId");
		 String roomNumber = this.get("roomNumber");
		 bazooService.delete(passportId,roomNumber);
		 
		 return "redirectIndex";
	 }
	 /**
	  * 列表
	  * @return
	  * @throws APIException
	  */
	 public String deleteL() throws APIException
	 {
		 
		 String passportId = this.get("passportId");
		 String roomNumber = this.get("roomNumber");
		 bazooService.delete(passportId,roomNumber);
		 
		 return "redirectIndexList";
	 }
	 
	 
	 
	 
}
