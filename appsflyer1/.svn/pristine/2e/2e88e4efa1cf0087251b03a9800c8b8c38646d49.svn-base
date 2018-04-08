package com.ami.texas.activity.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ami.api.common.AppConstant;
import com.ami.api.common.Pager;
import com.ami.common.HttpClientUtil;
import com.ami.texas.activity.dao.FunctionDao;

@Component
public class FunctionService {
	 @Autowired
	 FunctionDao functionDao;

	public Pager queryFunction(Pager pager) {
		pager = functionDao.queryFunction(pager);
        return pager;
	}

	public void updateFunction(HttpServletRequest req) {
		
		
		
		String id = req.getParameter("id");
		String title = req.getParameter("title");
		String startTime = req.getParameter("startTime");
		String endTime = req.getParameter("endTime");
		String descrip = req.getParameter("descrip");
		String pic = req.getParameter("pic");
		String functionType = req.getParameter("functionType");
		String[] condition_categroy = req.getParameterValues("condition_categroy");
//		req.getParameter("condition_small_categroy");
		HashMap<String,String> params = new HashMap<String,String>();
		params.put("id", id);
		params.put("title", title);
		params.put("startTime", startTime);
		params.put("endTime", endTime);
		params.put("descrip", descrip);
		params.put("pic", pic);
		params.put("functionType", functionType);
		//如果条件为空 就返回
		if(condition_categroy == null || condition_categroy.length == 0){
			return;
		}
		String cons = "";
		for(String con :condition_categroy){
			cons+=con+",";
		}
		cons=cons.substring(0, cons.length()-1);
		params.put("conditionCategroy", cons);
		
		
		
		boolean ifExist = functionDao.queryFunctionTypeExist();
		//如果是 充值翻倍的 功能 ，就 只能有一条
		if(Integer.valueOf(functionType).intValue()==1 && ifExist && StringUtils.isBlank(id)){
			return;
		}
		HttpClientUtil.postUrl(AppConstant.SERVER_URL_HTTP+"api/updateFunction", params);
		
		
	}

	public void deleteFunction(HttpServletRequest req) {
		String id = req.getParameter("id");
		if(StringUtils.isNotBlank(id)){
			HttpClientUtil.get(AppConstant.SERVER_URL_HTTP+"api/updateFunction?id="+id);
		}
		
	}

	public List<HashMap<String,Object>> getFunctionById(String id) {
		return functionDao.getFunctionById(id);
	}
}
