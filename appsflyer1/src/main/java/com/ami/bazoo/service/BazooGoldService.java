package com.ami.bazoo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ami.api.common.Pager;
import com.ami.bazoo.dao.BazooGoldDao;
import com.ami.bazoo.pojo.BazooParam;

@Service
public class BazooGoldService {

	@Autowired
	private BazooGoldDao BazooGoldDao;

	public Pager bazooGold(Pager pager,BazooParam param) {
		return BazooGoldDao.bazooGold(pager,param);
	}



}
