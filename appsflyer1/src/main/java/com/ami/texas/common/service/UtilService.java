package com.ami.texas.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.ami.texas.common.dao.UDao;
import com.ami.texas.common.vo.SlotName;

/**
 * 数据工具类服务
 * @author JavaServer
 *
 */
@Service
public class UtilService {

	@Autowired
	private UDao UDao;
	
	public JSONArray getAllSlots() {
		return UDao.getAllSlots();
	}
	public List<SlotName> getAllSlotIdNames() {
		return UDao.getAllSlotIdNames();
	}

	public JSONArray getAllSlotIocns() {
		return UDao.getAllSlotIocns();
	}
	
}
