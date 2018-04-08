package com.ami.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ami.api.web.action.BaseAction;

@Scope("prototype")
@Component
public class DownloadAction extends BaseAction
{
    private static final long serialVersionUID = 1L;
    
    private static Logger logger = Logger.getLogger(DownloadAction.class);
    
    public String donwload()
    {
        String fileName = this.get("fileName");
        this.set("fileName", fileName);
        return "download";
    }
    
    /**
     * 下载文件
     * 
     * @return
     * @throws FileNotFoundException
     * @see [类、类#方法、类#成员]
     */
    public InputStream getDownloadFile()
        throws FileNotFoundException
    {
        // 找到要下载的文件名
        String fileName = this.get("fileName");
        logger.info("download fileName=" + fileName);
        
        InputStream is = new FileInputStream(CommonFunction.getExportTempPath() + fileName);
        
        return is;
    }
    
    /**
     * 下载生成的文件名
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    
    public String getFileName()
    {
        String fileName = this.get("fileName");
        
        return fileName;
    }
    
}
