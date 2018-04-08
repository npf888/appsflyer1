package com.ami.sys.common.function;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ami.api.common.AppConstant;
import com.ami.api.utill.DateTools;
import com.ami.api.utill.StringTool;

@Component
public class SysCommonFunction
{
    
    /**
     * 保留两位小数
     * 
     * @param num
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String DecimalFormatTo2(long num)
    {
        if (0 == num)
        {
            return "0.00";
        }
        DecimalFormat df = new DecimalFormat("#.00");
        
        return df.format(num);
        
    }
    
    /**
     * 保留两位小数
     * 
     * @param num
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String DecimalFormatTo2(Object num)
    {
        
        DecimalFormat df = new DecimalFormat("#.00");
        String ss = df.format(num);
        
        if (".00".equals(ss))
        {
            return "0.00";
        }
        
        return ss;
        
    }
    
    /**
     * List<HashMap<String, Object>> 数据写到csv 文件
     * 
     * @param list 数据
     * @param fileName 文件名全路径
     * @param field 字段
     * @throws IOException
     * @see [类、类#方法、类#成员]
     */
    public static void ListMapToCsv(List<HashMap<String, Object>> list, String fileName, String field[])
        throws IOException
    {
        ListMapToCsv(list, fileName, field, null);
    }
    
    /**
     * List<HashMap<String, Object>> 数据写到csv 文件
     * 
     * @param list 数据
     * @param fileName 文件名全路径
     * @param field 字段
     * @throws IOException
     * @see [类、类#方法、类#成员]
     */
    public static void ListMapToCsv(List<String> list, String fileName)
        throws IOException
    {
        File file = new File(fileName);
        // 创建文件夹
        if (!file.getParentFile().exists())
        {
            file.getParentFile().mkdirs();
        }
        
        FileWriter fos = new FileWriter(fileName);
        
        StringBuffer sb = new StringBuffer();
        
        int index = 0;
        
        for (String field : list)
        {
            index++;
            
            sb.append(field);
            
            sb.append("\n");
            
            if (index >= 100)
            {
                fos.write(sb.toString());
                fos.flush();
                index = 0;
                sb = new StringBuffer();
            }
            
        }
        
        sb.deleteCharAt(sb.length() - 1);
        
        fos.write(sb.toString());
        fos.flush();
        
        fos.close();
    }
    
    /**
     * List<HashMap<String, Object>> 数据写到csv 文件
     * 
     * @param list 数据
     * @param fileName 文件名全路径
     * @param field 字段
     * @throws IOException
     * @see [类、类#方法、类#成员]
     */
    public static void ListMapToCsv(List<HashMap<String, Object>> list, String fileName, String field[], String fieldTitle[])
        throws IOException
    {
        File file = new File(fileName);
        // 创建文件夹
        if (!file.getParentFile().exists())
        {
            file.getParentFile().mkdirs();
        }
        
        OutputStreamWriter fos = new OutputStreamWriter(new FileOutputStream(fileName), "GBK");
        
        StringBuffer sb = new StringBuffer();
        
        // 如果有title
        if (null != fieldTitle)
        {
            // 写title
            for (String string : fieldTitle)
            {
                sb.append(string).append(",");
            }
            sb.append("\n");
        }
        
        int index = 0;
        
        for (Map<String, Object> hashMap : list)
        {
            index++;
            
            for (String f : field)
            {
                String value = (null == hashMap.get(f) ? "" : hashMap.get(f).toString());
                
                sb.append(value).append(",");
                
            }
            
            sb.deleteCharAt(sb.length() - 1);
            sb.append("\n");
            
            if (index >= 100)
            {
                fos.write(sb.toString());
                fos.flush();
                index = 0;
                sb = new StringBuffer();
            }
            
        }
        if (sb.length() > 0)
        {
            sb.deleteCharAt(sb.length() - 1);
        }
        
        fos.write(sb.toString());
        fos.flush();
        
        fos.close();
    }
    
    /**
     * 获取临时文件路劲
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String getExportTempPath()
    {
        //
        return AppConstant.systemDir + AppConstant.webroot;
        
    }
    
    /**
     * 上传图片
     * 
     * @throws IOException
     * @see [类、类#方法、类#成员]
     */
    public static String uploadFile(File uploadFile)
        throws IOException
    {
       return  uploadFile(uploadFile, "jpg");
    }
    
    /**
     * 上传图片
     * 
     * @throws IOException
     * @see [类、类#方法、类#成员]
     */
    public static String uploadFile(File uploadFile, String suffix)
        throws IOException
    {
        
        if (null == uploadFile)
        {
            return "";
        }
        if(StringTool.isEmpty(suffix))
        {
            suffix="jpg";
        }
        String dir = "attached/pic/";
        String filepathstr = AppConstant.systemDir + AppConstant.webroot + dir;
        
        File filepath = new File(filepathstr);
        
        if (!filepath.exists())
        {
            filepath.mkdirs();
        }
        
        String fileName = DateTools.getCurrentDate("yyyyMMddHHmmss") + "." + suffix;
        
        File file = new File(filepathstr + fileName);
        
        uploadFile.renameTo(file);
        
        return dir + fileName;
        
    }
    
}
