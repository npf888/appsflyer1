package com.agent.agent.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agent.agent.dao.GoldChangeDao;
import com.agent.agent.pojo.GoldChange;

@Service
public class GoldChangeService {

	@Autowired
	private GoldChangeDao goldChangeDao;
	
	
	public List<GoldChange> getGoldChangeByPassport(long passpword,String date){
		return goldChangeDao.getGoldChangeByPassport(passpword, date);
	}
}
