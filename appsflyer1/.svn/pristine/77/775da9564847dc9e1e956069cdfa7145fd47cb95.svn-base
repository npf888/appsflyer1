package com.ami.gold.service;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ami.common.HttpClientUtil;
import com.ami.gold.dao.AddgoldDao;


@Component
public class AddgoldService {

	@Autowired
	private AddgoldDao addgoldDao;

	public String addgoldOverrideOrIncrese(HttpServletRequest req) {
		String passportId = req.getParameter("passportId");
		String gold = req.getParameter("gold");
		String level = req.getParameter("level");
		String type = req.getParameter("type");
		String url = "http://127.0.0.1:8888/api/changeHumanLevel";
		HashMap<String,String> params = new HashMap<String,String>();
		params.put("passportId", passportId);
		params.put("gold", gold);
		params.put("level", level);
		params.put("type", type);
		String reStr = HttpClientUtil.postUrl(url, params);
		return reStr;
	}
}
