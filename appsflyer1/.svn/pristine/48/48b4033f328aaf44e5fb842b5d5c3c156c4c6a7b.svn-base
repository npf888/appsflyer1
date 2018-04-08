package com.ami.texas.activity.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ami.api.common.Pager;
import com.ami.api.exception.APIException;
import com.ami.api.web.action.BaseAction;
import com.ami.common.TimeUtil;
import com.ami.texas.activity.service.FunctionService;
import com.ami.texas.img.service.ImgService;

@Scope("prototype")
@Component
public class FunctionAction extends BaseAction{
	@Autowired
	FunctionService functionService;
	@Autowired
	ImgService  imgService;
	
	private static Logger logger = Logger.getLogger(FunctionAction.class);
	
	
	
	/**
	 * 鏌ヨ鎵�鏈夊姛鑳�
	 * @return
	 * @throws APIException
	 */
	public String queryFunction() throws APIException
    {
        
        Pager pager = new Pager(this.getReq());
        
        pager = functionService.queryFunction(pager);
        
        this.set("PAGER", pager);
        
        return "queryFunction";
    }
	
	/**
	 * @return
	 */
	public String updateFunctionPage(){
		String id = this.getReq().getParameter("id");
		if(StringUtils.isNotBlank(id)){
			List<HashMap<String,Object>>  functionEle = functionService.getFunctionById(this.getReq().getParameter("id"));
			this.set("one", functionEle.get(0));
			Date startTIme = (Date)functionEle.get(0).get("startTime");
			Date endTIme = (Date)functionEle.get(0).get("endTime");
			String conditions = (String)functionEle.get(0).get("conditions");
			if(StringUtils.isNotBlank(conditions)){
				String[] conditionArr = conditions.split(",");
				for(int i=0;i<conditionArr.length;i++){
					String key = "condition"+(i+1);
					this.set(key, conditionArr[i]);
				}
			}
			this.set("startTime", TimeUtil.formatDate(startTIme));
			this.set("endTime", TimeUtil.formatDate(endTIme));
		}
		List<HashMap<String, Object>> images;
		try {
			images = imgService.query();
			this.set("images", images);
		} catch (APIException e) {
			e.printStackTrace();
		}
		return "updateFunction";
	}
	
	/**
	 * @return
	 * @throws APIException
	 */
	public void updateFunction() throws APIException
	{
		
		
		functionService.updateFunction(this.getReq());
		
		
		
	}
	/**
	 * @return
	 * @throws APIException
	 */
	public String deleteFunction() throws APIException
	{
		
		
		functionService.deleteFunction(this.getReq());
		
		return "success";
		
	}
}
