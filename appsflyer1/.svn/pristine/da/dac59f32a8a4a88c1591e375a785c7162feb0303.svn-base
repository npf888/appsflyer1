package com.ami.mail.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ami.common.TimeUtil;
import com.ami.mail.pojo.OneTwoStepVO;
import com.ami.texas.log.service.LogService;

/**
 * 一阶 二阶 登录比
 * @author JavaServer
 *
 */
@Service
public class StepService {

	@Autowired
	private LogService logService;
	
	public List<OneTwoStepVO>  getSteps(){
		try {
			List<OneTwoStepVO> stepList = new ArrayList<OneTwoStepVO>();
			//一阶
			List<HashMap<String, Object>> date_list = logService.queryTablesDate("player_keep_log");
			Map<String,String>  oneLoginRate = logService.oneLoginRate(date_list, TimeUtil.formatChangeYmdhmDay(new Date(),-7));
			//二阶
			Map<String,String>  twoLoginRate = logService.twoLoginRate(date_list, TimeUtil.formatChangeYmdhmDay(new Date(),-7));
			
			OneTwoStepVO oneStepVO = new OneTwoStepVO();
			oneStepVO.setName("one_step_rate");
			oneStepVO.setLoginRate(oneLoginRate.get("oneLoginRate"));
			oneStepVO.setNumerator(oneLoginRate.get("numerator"));
			oneStepVO.setDenominator(oneLoginRate.get("denominator"));
			stepList.add(oneStepVO);
			OneTwoStepVO twoStepVO = new OneTwoStepVO();
			twoStepVO.setName("two_step_rate");
			twoStepVO.setLoginRate(twoLoginRate.get("twoLoginRate"));
			twoStepVO.setNumerator(twoLoginRate.get("numerator"));
			twoStepVO.setDenominator(twoLoginRate.get("denominator"));
			stepList.add(twoStepVO);
			return stepList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
