package com.ami.feedback.action;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ami.api.common.Pager;
import com.ami.api.exception.APIException;
import com.ami.api.web.action.BaseAction;
import com.ami.common.UploadUtil;
import com.ami.feedback.service.FeedbackService;

/**
 * 报表统计分析
 * 
 * @author zhuweiliang
 * @version [版本号, 2013-5-29]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Scope("prototype")
@Component
public class FeedbackAction extends BaseAction
{
    
    private static final long serialVersionUID = 1L;
    
    private static Logger logger = Logger.getLogger(FeedbackAction.class);
    
    @Autowired
    private FeedbackService feedbackService;
    
    private File image; //上传的文件  
    private String imageFileName; //文件名称  
    private String imageContentType; //文件类型  
      
    public File getImage() {  
        return image;  
    }  
    public void setImage(File image) {  
        this.image = image;  
    }  
    public String getImageFileName() {  
        return imageFileName;  
    }  
    public void setImageFileName(String imageFileName) {  
        this.imageFileName = imageFileName;  
    }  
    public String getImageContentType() {  
        return imageContentType;  
    }  
    public void setImageContentType(String imageContentType) {  
        this.imageContentType = imageContentType;  
    }  
    
    /**
     * 添加
     * 
     * @throws IOException
     * @see [类、类#方法、类#成员]
     */
    public void doAdd()
        throws IOException
    {
        
        Map<String, String> response = new HashMap<String, String>();
        
        try
        {
            // 注：此字段 需要和页面的 表单的 name 以及 数据库的字段名 都要保持一致
            // 1， 定制需要往数据库插入的字段
            String insert[] = new String[]
            {"feedback_id", "content"};
            
            // 2， 从页面获取 对应的表单值
            Map<String, Object> params = this.reqToMap(insert);
            params.put("role", "GM");
            params.put("feedback_id", Integer.parseInt(params.get("feedback_id").toString()));
            // 3， 调用逻辑层 进行添加
            feedbackService.add(params);
            
            Map<String, Object> status = new HashMap<String, Object>();
            status.put("status", 2);            
            feedbackService.update(status, params.get("feedback_id").toString());
            
            print("Replyed");
            
        }
        catch (Exception e)
        {
            print("Fail"+e.getMessage());            
            logger.error("add Exception", e);
            
        }
    }

    /**
     * 查询
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String query()
    {
        String field[] = new String[]
        {"appid", "user_id","mac_id","status"};
        
        Map<String, String> params = this.reqToStrMap(field);
        
        this.set("params", params);
        
        Pager pager = new Pager(this.getReq());
        String currentPage = this.get("currentPage");
        this.set("currentPage", currentPage);
        try
        {     
            pager = feedbackService.queryForPage(params, pager);
            this.set("PAGER", pager);
        }
        catch (APIException e)
        {
            logger.error("query error", e);
        }
        
        String user_id = this.getReq().getParameter("user_id");
        String mac_id = this.getReq().getParameter("mac_id");
        String status = this.getReq().getParameter("status");
        this.set("user_id", user_id);
        this.set("mac_id", mac_id);
        this.set("status", status);
        return "query";
    }
    
    /**
     * 根据id加载
     * 
     * @return
     * @throws APIException
     * @see [类、类#方法、类#成员]
     */
    public String loadByID() throws APIException
    {
    	  String currentPage = this.get("currentPage");
          this.set("currentPage", currentPage);
        String id=this.get("id");
       
        Map<String, Object> result = feedbackService.loadByID(id);

        this.set("one", result);
        
        return "toReply";
    }
    
    /**
     * 根据id删除
     * 
     * @throws IOException
     * @see [类、类#方法、类#成员]
     */
    public void delByID() throws IOException
    {
        String id=this.get("id");
        try{
            feedbackService.delByID(id);
            print("Deleted");
        }catch(Exception e)
        {
           print("Error: " +e);   
        }
    }
    /**
     * 用户访问的页面 链接
     * http://47.88.241.10:9901//feedback.do?method=addDataPage&appId=10000&userId=10064&macId=00000000000000E0
     * @return
     * @throws IOException
     */
    public String addDataPage() throws IOException
    {
    	String language = this.get("language");
    	if(StringUtils.isBlank(language)){
    		language="en";
    	}
    	this.set("language", language);
    	return "addDataPage";
    }
    /**
     * 用户远程调用 ， 插入数据
     * 
     * @throws IOException
     * @see [类、类#方法、类#成员]
     */
    public void addData() throws IOException
    {
    	
    	try{
    		String imgpath = UploadUtil.uploadImage(this.getReq(), image,imageFileName,imageContentType);
    		String message = this.getReq().getParameter("message");
    		if(StringUtils.isBlank(message)){
    			print("the message can't be null");
    			return;
    		}
    		feedbackService.addData(this.getReq(),imgpath);
    		print("success");
    	}catch(Exception e)
    	{
    		print("error: " +e);   
    	}
    }
   
/**
 * 用户根据macId 获取所有接口问题的接口
 * @throws IOException 
 */
    public void getQuestionList() throws IOException{
    	JSONObject jb = new JSONObject();
    	try{
    		String macId = this.getReq().getParameter("macId");
    		if(StringUtils.isBlank(macId)){
    			jb.put("error", "the macId can't be null");
    			print(jb.toJSONString());
    			return;
    		}
    		List<HashMap<String, Object>> reList =  feedbackService.getQuestionList(macId.trim());
    		String rJson = JSON.toJSONString(reList);
    		print(rJson);
    	}catch(Exception e)
    	{
    		jb.put("error", e);
    		print(jb.toJSONString());   
    	}
    }
    /**
     * 
     * @return
     * @throws APIException
     * @throws IOException 
     */
    public void getQuestionByFeedbackId() throws APIException, IOException
    {
       
        JSONObject jb = new JSONObject();
		try {
			String id = this.get("id");
			if(StringUtils.isBlank(id)){
    			jb.put("error", "the macId can't be null");
    			print(jb.toJSONString());
    			return;
    		}
			List<HashMap<String, Object>> result = feedbackService.getQuestionByFeedbackId(id.trim());
			String rJson = JSON.toJSONString(result);
			print(rJson);
		} catch (IOException e) {
			e.printStackTrace();
			print(jb.toJSONString());
		}
    }
    
    public void addReplyData() throws IOException
    {
    	
    	try{
    		feedbackService.addReplyData(this.getReq());
    		print("success");
    	}catch(Exception e)
    	{
    		print("Error: " +e);   
    	}
    }
    
}
