package com.ami.texas.code.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ami.api.common.Pager;
import com.ami.texas.code.dao.CodeDao;
import com.ami.texas.code.pojo.Conversioncode;

@Service
public class CodeService {

	@Autowired
	private CodeDao codeDao;

	public Pager queryCode(Pager pager, HttpServletRequest httpServletRequest) {
		return codeDao.queryCode(pager,httpServletRequest);
	}

	public void toEdit(HttpServletRequest req) {
		codeDao.toEdit(req);
		
	}

	public void toDelete(HttpServletRequest httpServletRequest) {
		codeDao.toDelete(httpServletRequest);
		
	}

	public Conversioncode queryOneCode(HttpServletRequest req) {
		return codeDao.queryOneCode(req);
	}

	public List<Conversioncode> queryAllNotUse() {
		return codeDao.queryAllNotUse();
		
	}
}