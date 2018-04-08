package com.ami.bazoo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ami.bazoo.dao.BazooNewGuyDao;
import com.ami.bazoo.pojo.BazooNewGuy;
@Service
public class BazooNewGuyService {

	@Autowired
	private BazooNewGuyDao bazooNewGuyDao;
	
	public List<BazooNewGuy> getBazooNewGuy(){
		return bazooNewGuyDao.getBazooNewGuy();
	}
	
}
