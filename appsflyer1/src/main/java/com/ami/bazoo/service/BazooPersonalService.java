package com.ami.bazoo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ami.api.common.Pager;
import com.ami.bazoo.dao.BazooPersonalDao;
import com.ami.bazoo.pojo.BazooParam;
import com.ami.bazoo.pojo.BazooPersonalEveryDayVO;

@Service
public class BazooPersonalService {

	@Autowired
	private BazooPersonalDao bazooPersonalDao;
	
	public Pager bazooPersonal(Pager pager, BazooParam param) {
		return bazooPersonalDao.bazooPersonal(pager,param);
	}

	public List<BazooPersonalEveryDayVO> bazooPersonalEveryDay(BazooParam param) {
		return bazooPersonalDao.bazooPersonalEveryDay(param);
	}

}
