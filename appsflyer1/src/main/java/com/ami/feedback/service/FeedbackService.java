package com.ami.feedback.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ami.api.common.Pager;
import com.ami.api.exception.APIException;
import com.ami.api.utill.DateTools;
import com.ami.feedback.dao.FeedbackDao;


/**
 * 
 * 联系点
 * 
 * @author
 * @version [版本号, 2013-7-10]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component
public class FeedbackService
{
    
    @Autowired
    private FeedbackDao feedbackDao;
    
    /**
     * 添加
     * 
     * @param params 参数 key value
     * @return
     * @throws APIException
     * @see [类、类#方法、类#成员]
     */
    public String add(Map<String, Object> params)
        throws APIException
    {
        
        String date = DateTools.getCurrentDate("yyyy-MM-dd HH:mm:ss");
        
        // 2, 更新时间
        params.put("time_stamp", date);
        
        // 唯一ID
        String id = feedbackDao.add(params);
        
        return id;
    }
    
    /**
     * 更新操作员信息
     * 
     * @param params 修改的字段以及value
     * @param id 用户唯一ID
     * @return
     * @throws APIException
     * @see [类、类#方法、类#成员]
     */
    public void update(Map<String, Object> parameters, String id)
        throws APIException
    {

        // 调用Dao 更新
        feedbackDao.update(parameters, id);
        
    }
    
    /**
     * 根据ID 查询
     * 
     * @param id
     * @return
     * @throws APIException
     * @see [类、类#方法、类#成员]
     */
    public Map<String, Object> loadByID(String id)
        throws APIException
    {
        Map<String, Object> result = feedbackDao.loadByID(id);
        List<HashMap<String, Object>> reply = feedbackDao.getResponse(result.get("id").toString());
        result.put("reply", reply);
        
        return result;
    }
    public List<HashMap<String, Object>> getQuestionByFeedbackId(String id)throws APIException{
    	return feedbackDao.getQuestionByFeedbackId(id);
     }
    
    /**
     * 查询号码
     * 
     * @param param
     * @param pager
     * @return
     * @throws APIException
     * @see [类、类#方法、类#成员]
     */
    public Pager queryForPage(Map<String, String> param, Pager pager)
        throws APIException
    {
        
        pager = feedbackDao.queryForPage(param, pager);
        
        for (Object obj : pager.getItems())
        {
            Map<String, Object> o = (Map<String, Object>)obj;
            
            List<HashMap<String, Object>> reply = feedbackDao.getResponse(o.get("id").toString());
            o.put("reply",reply);
        }
        
        return pager;
    }
    
    /**
     * 根据id删除
     * 
     * @param id
     * @throws APIException
     * @see [类、类#方法、类#成员]
     */
    public void delByID(String id) throws APIException
    {
        feedbackDao.delByID(id);
    }

	public void addData(HttpServletRequest request,String imgpath) {
		 feedbackDao.addData(request,imgpath);
		
	}

	public List<HashMap<String, Object>> getQuestionList(String macId) {
		return feedbackDao.getQuestionList(macId);
	}

	public void addReplyData(HttpServletRequest req) {
		feedbackDao.addReplyData(req);
		
	}

}
