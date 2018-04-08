package com.ami.mail.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ami.feedback.dao.FeedbackDao;
import com.ami.feedback.pojo.FeedbackVO;

/**
 * 反馈信息发邮件
 * @author JavaServer
 *
 */
@Service
public class FeedbackMailService {

	@Autowired
	private FeedbackDao feedbackDao;
	
	public List<FeedbackVO>  getFeedback(){
		try {
			List<FeedbackVO> feedbackList = feedbackDao.queryFeedbackList();
			return feedbackList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
