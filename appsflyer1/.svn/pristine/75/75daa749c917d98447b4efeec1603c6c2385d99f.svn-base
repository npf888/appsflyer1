package com.ami.bazoo.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ami.bazoo.dao.BazooDao;
import com.ami.bazoo.pojo.BazooRoomVO;

@Service
public class BazooService {

	@Autowired
	private BazooDao bazooDao;

	public List<BazooRoomVO> queryList(HttpServletRequest httpServletRequest) {
		
		return bazooDao.queryList(httpServletRequest);
	}

	public void shutDownThread(String passportId) {
		bazooDao.shutDownThread(passportId);
		
	}
	public void delete(String passportId,String roomNumber) {
		bazooDao.delete(passportId,roomNumber);
		
	}

}
