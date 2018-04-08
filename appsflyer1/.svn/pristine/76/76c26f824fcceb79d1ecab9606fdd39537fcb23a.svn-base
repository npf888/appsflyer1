package com.ami.feedback.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.ami.api.common.Pager;
import com.ami.api.exception.APIException;
import com.ami.api.utill.StringTool;
import com.ami.api.web.dao.BaseMdspDao;
import com.ami.common.TimeUtil;
import com.ami.feedback.pojo.FeedbackVO;

/**
 * 
 * 用户管理
 * 
 * @author zhuweiliang
 * @version [版本号, 2013-10-14]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component
public class FeedbackDao extends BaseMdspDao//BaseMdspDao//BaseDao
{
    
    /**
     * 添加文章
     * 
     * @param params 参数 key value
     * @return
     * @throws APIException
     * @see [类、类#方法、类#成员]
     */
    public String add(Map<String, Object> params)
        throws APIException
    {
        
        this.table.insert("reply", params);
        
        return "";
    }
    
    /**
     * 根据用户ID 修改用户信息
     * 
     * @param params 修改的字段以及value
     * @param id 用户唯一ID
     * @return
     * @throws APIException
     * @see [类、类#方法、类#成员]
     */
    public void update(Map<String, Object> params, String id)
        throws APIException
    {
        this.table.update("feedback", params, Integer.parseInt(id));
        
    }
    
    /**
     * 根据ID 删除用户信息
     * 
     * @param id
     * @throws APIException
     * @see [类、类#方法、类#成员]
     */
    public void delByID(String id)
        throws APIException
    {
        
        String sql = "delete from feedback where id = ?";
        
        this.db.update(sql, new Object[]
        {Integer.parseInt(id)});
        
    }
    
    /**
     * 根据ID 加载唯一条数据
     * 
     * @param id
     * @return
     * @throws APIException
     */
    public Map<String, Object> loadByID(String id)
        throws APIException
    {
        String sql = "select * from feedback where id= ?";
        
        return this.db.load(sql, new Object[]
        {Integer.parseInt(id)});
        
    }
    
    /**
     * 分页查询
     * 
     * @param param
     * @param pager
     * @return
     * @throws APIException
     */
    public Pager queryForPage(Map<String, String> param, Pager pager)
        throws APIException
    {
        String app_id = param.get("appid");
        String user_id = param.get("user_id");
        String mac_id = param.get("mac_id");
        String status = param.get("status");
        
        StringBuffer sql = new StringBuffer("select * from feedback where 1=1 ");
        if(!StringTool.isEmpty(app_id))
        {
            sql.append(" and app_id= '"+app_id+"'");
        }
        
        if(!StringTool.isEmpty(user_id))
        {
            sql.append( " and user_id= '"+user_id+"'");
        }
        if(!StringTool.isEmpty(mac_id))
        {
        	sql.append( " and mac_id= '"+mac_id+"'");
        }
        if(!StringTool.isEmpty(status))
        {
        	sql.append( " and status= '"+status+"'");
        }
        
        sql.append( " order by");
        if(!StringTool.isEmpty(status))
        {
        	sql.append( "  create_time DESC");
        }else{
        	sql.append( "  status, create_time DESC ");
        }
        pager = this.db.queryPage(sql.toString(), new Object[]
        {}, pager);
        
        return pager;        
    }
    
    
    /**
     * 获取回复
     * @throws APIException 
     */
    public List<HashMap<String, Object>> getResponse(String feedback_id) throws APIException
    {
        String sql = "select * from reply where feedback_id=? ";
        return this.db.query(sql, new Object[]{Integer.parseInt(feedback_id)});
    }
    public void addData(HttpServletRequest request,String imgpath) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("app_id", request.getParameter("appId"));
		param.put("user_id", request.getParameter("userId"));
		param.put("mac_id", request.getParameter("macId"));
		param.put("question_type", request.getParameter("questionType"));
		param.put("message", request.getParameter("message").trim());
		if(StringUtils.isNotBlank(imgpath)){
			param.put("image", imgpath);
		}
		String channel = request.getParameter("channel");
		if(StringUtils.isNotBlank(channel)){
			param.put("channel", channel.trim());
		}else{
			param.put("channel", "singapore");//默认新加坡
		}
		param.put("additional", request.getParameter("additional"));
		param.put("status",0);
		param.put("create_time", TimeUtil.format(new Date()));
		param.put("user_name", request.getParameter("userName"));
		param.put("time_stamp", TimeUtil.format(new Date()));
		try {
			this.table.insert("feedback", param);
		} catch (APIException e) {
			e.printStackTrace();
		}
		
	}

	public List<HashMap<String, Object>> getQuestionList(String macId) {
        try {
        	String sql = "select id,mac_id,message from feedback where mac_id like'%"+macId+"%' order by time_stamp desc";
	        return this.db.query(sql);
		} catch (APIException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<HashMap<String, Object>> getQuestionByFeedbackId(String id) {
	     try {
	    	 String sql = "select id, feedback_id, role, time_stamp, satisfaction, content from reply where feedback_id=? order by time_stamp asc";
			return this.db.query(sql, new Object[]{Integer.valueOf(id)});
		} catch (APIException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	 public void addReplyData(HttpServletRequest request) {
			Map<String,Object> param = new HashMap<String,Object>();
			param.put("feedback_id", Integer.valueOf(request.getParameter("feedbackId")));
			param.put("role", request.getParameter("role"));
//			param.put("satisfaction", request.getParameter("satisfaction"));
			param.put("content", request.getParameter("content").trim());
			param.put("time_stamp", TimeUtil.format(new Date()));
			try {
				this.table.insert("reply", param);
			} catch (APIException e) {
				e.printStackTrace();
			}
			
		}
	 
	 
	 
	  /**
	     * 发送邮件的地方用到，查询所有的反馈
	     * 
	     * @param param
	     * @param pager
	     * @return
	     * @throws APIException
	     */
	    public List<FeedbackVO> queryFeedbackList()
	    {
	    	try{
	        
		        StringBuffer sql = new StringBuffer(" select id,"
									        		+ " app_id appId,"
									        		+ " user_id userId,"
									        		+ " question_type questionType,"
									        		+ " message,"
									        		+ " image,"
									        		+ " additional,"
									        		+ " status,"
									        		+ " create_time as createTime,"
									        		+ " user_name userName,"
									        		+ " time_stamp as timeStamp  "
									        		+ " from feedback  order by status, create_time DESC");
		        
		        List<FeedbackVO>  feedbackList = this.db.query(sql.toString(), FeedbackVO.class);
		        
		        return feedbackList;   
	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}
	    	return null;
	    }
}
