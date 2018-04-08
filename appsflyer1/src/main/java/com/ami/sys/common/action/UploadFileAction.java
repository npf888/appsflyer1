package com.ami.sys.common.action;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.ami.api.web.action.BaseAction;
import com.ami.sys.common.function.SysCommonFunction;

/**
 * 
 * 上传图片
 * 
 * @author zhuweiliang
 * @version [版本号, 2013-5-29]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Scope("prototype")
@Component
public class UploadFileAction extends BaseAction
{
    
    private static final long serialVersionUID = 1L;
    
    private static Logger logger = Logger.getLogger(UploadFileAction.class);
    
    /**
     * 上传图片
     */
    private File pic_file;
    
    public void uploadFile()
        throws IOException
    {
        logger.info("uploadFile start");
        //
        try
        {
            String suffix= this.get("suffix");
            // 图片存储的url
            String url = SysCommonFunction.uploadFile(pic_file,suffix);
          
            
            JSONObject obj = new JSONObject();
            obj.put("error", 0);
            obj.put("url", url);
            this.getRes().setContentType("text/html");
            print(obj.toString());
            
        }
        catch (IOException e)
        {
            
            JSONObject obj = new JSONObject();
            obj.put("error", 1);
            obj.put("url", "");
            this.getRes().setContentType("text/html");
            print(obj.toString());
            
        }
        
        logger.info("uploadFile end");
    }
    
    public File getPic_file()
    {
        return pic_file;
    }
    
    public void setPic_file(File pic_file)
    {
        this.pic_file = pic_file;
    }
    
}
