package com.ami.mail.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ami.feedback.pojo.FeedbackVO;
import com.ami.mail.ExcelHelper;
import com.ami.mail.pojo.AllResourceVO;
import com.ami.mail.pojo.NewPeopleVO;
import com.ami.mail.pojo.OneTwoStepVO;
import com.ami.mail.pojo.RemainVO;


/**
 * 所有service
 * 
 * 如果需要添加更多的附件在这里边加
 * @author JavaServer
 *
 */
@Service
public class AllResourceService {

	private List<AllResourceVO> allInputStreams = new ArrayList<AllResourceVO>();

	@Autowired
	private  NewPeopleService newPeopleService;
	
	@Autowired
	private  RemainService remainService;
	
	@Autowired
	private  FeedbackMailService feedbackMailService;
	@Autowired
	private  StepService stepService;
	
	/**
	 * 所有数据源
	 * @return
	 */
	public List<AllResourceVO> getAllResource(){
		//每天新注册用户所在的国家分别有多少人
		List<NewPeopleVO> newPeopleList = newPeopleService.getRewPeopleList();
		InputStream is_newPeople = ExcelHelper.xlsxExcel2007(newPeopleList);
		if(is_newPeople!=null){
			AllResourceVO allResourceVO  = new AllResourceVO();
			allResourceVO.setIs(is_newPeople);
			allResourceVO.setName("retained.xlsx");
			this.getAllInputStreams().add(allResourceVO);
		}
		//留存统计
		List<RemainVO> remainVOList = remainService.getRemainVOList();
		InputStream is_remain = ExcelHelper.xlsxExcel2007(remainVOList);
		if(is_remain!=null){
			AllResourceVO allResourceVO  = new AllResourceVO();
			allResourceVO.setIs(is_remain);
			allResourceVO.setName("country_people_new_num.xlsx");
			this.getAllInputStreams().add(allResourceVO);
		}
		//反馈数据
		List<FeedbackVO> feedbackList = feedbackMailService.getFeedback();
		InputStream is_feedback = ExcelHelper.xlsxExcel2007(feedbackList);
		if(is_feedback!=null){
			AllResourceVO allResourceVO  = new AllResourceVO();
			allResourceVO.setIs(is_feedback);
			allResourceVO.setName("feedback.xlsx");
			this.getAllInputStreams().add(allResourceVO);
		}
		//一阶  二阶
		List<OneTwoStepVO> stepList = stepService.getSteps();
		InputStream is_step = ExcelHelper.xlsxExcel2007(stepList);
		if(is_step!=null){
			AllResourceVO allResourceVO  = new AllResourceVO();
			allResourceVO.setIs(is_step);
			allResourceVO.setName("step_rate.xlsx");
			this.getAllInputStreams().add(allResourceVO);
		}
		
		return this.getAllInputStreams();
	}

	/**
	 * 
	 *所有接受数据的人
	 */
	
	public List<String> getReceivePeople(){
		List<String> peoples = new ArrayList<String>();
		peoples.add("526607372@qq.com");
		peoples.add("vincent.niu@y2tek.com");
		peoples.add("peter.huo@y2tek.com");
		peoples.add("joe.yang@y2tek.com");
		peoples.add("seven.che@y2tek.com");
		
		peoples.add("harry.yan@y2tek.com");
		
		peoples.add("davis.guo@y2tek.com");
		peoples.add("robert.hu@y2tek.com");
		return peoples;
		
	}
	
	public List<AllResourceVO> getAllInputStreams() {
		return allInputStreams;
	}


	public void setAllInputStreams(List<AllResourceVO> allInputStreams) {
		this.allInputStreams = allInputStreams;
	}
	
	
	
	
	
	
	
	
	
}