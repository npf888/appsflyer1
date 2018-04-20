package com.agent.agent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agent.agent.dao.GiftDao;
import com.ami.api.common.Pager;

@Service
public class GiftService {

	@Autowired
	private GiftDao giftDao;

	public Pager allSendGift(Pager pager,Long sendId) {
		return giftDao.allSendGift(pager,sendId);
	}

	public Pager allReceiveGift(Pager pager,Long recId) {
		return giftDao.allReceiveGift(pager,recId);
	}
	
	
	
}
