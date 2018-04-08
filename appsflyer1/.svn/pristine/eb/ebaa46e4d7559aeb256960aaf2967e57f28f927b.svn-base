package com.ami.bazoo.dao;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.ami.api.common.AppConstant;
import com.ami.api.web.dao.BaseMysqlDao;
import com.ami.bazoo.pojo.BazooRoomVO;
import com.ami.common.HttpClientUtil;

@Component
public class BazooDao extends BaseMysqlDao{
	 private static Logger logger = Logger.getLogger(BazooDao.class);

	public List<BazooRoomVO> queryList(HttpServletRequest httpServletRequest) {
		String url = AppConstant.SERVER_URL_HTTP+"/api/bazooRoom";
		String roomNumber = httpServletRequest.getParameter("roomNumber");
		if(StringUtils.isNotBlank(roomNumber)){
			url+="?roomNumber="+roomNumber;
		}
		String result = HttpClientUtil.get(url);
		logger.info("----"+url);
		if(result.contains("message")){
			return null;
		}
		List<BazooRoomVO> list= JSONArray.parseArray(result, BazooRoomVO.class);
		return list;
	}

	public void shutDownThread(String passportId) {
		String url = AppConstant.SERVER_URL_HTTP+"/api/bazooRoom";
		HashMap<String,String> params = new HashMap<String,String>();
		params.put("option", "shutDownThread");
		params.put("passportId", passportId);
		String result = HttpClientUtil.postUrl(url, params);
	}
	public void delete(String passportId,String roomNumber) {
		String url = AppConstant.SERVER_URL_HTTP+"/api/bazooRoom";
		HashMap<String,String> params = new HashMap<String,String>();
		params.put("option", "delete");
		params.put("passportId", passportId);
		params.put("roomNumber", roomNumber);
		String result = HttpClientUtil.postUrl(url, params);
	}


}
