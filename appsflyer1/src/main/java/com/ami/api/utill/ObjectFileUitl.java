package com.ami.api.utill;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.ami.api.common.AppConstant;

public class ObjectFileUitl
{
    /**
     * 将对象写入文件
     * 
     * @param obj
     * @param fileName
     * @see [类、类#方法、类#成员]
     */
    public static void writeObject(Object obj, String fileName)
    {
        fileName = AppConstant.systemDir + "/" + fileName;
        
        File file = new File(fileName);
        
        file.getParentFile().mkdirs();
        
        try
        {
            /**//* 创建一个文件写入序列化树。 */
            FileOutputStream ostream = new FileOutputStream(fileName);
            /**//* 创建输出流 */
            ObjectOutputStream p = new ObjectOutputStream(ostream);
            
            p.writeObject(obj); // 将树写入流中。
            p.flush();
            ostream.close(); // 关闭文件。
        }
        catch (Exception e)
        {
            
            e.printStackTrace();
            
        }
    }
    
    /**
     * 从文件里读取对象
     * 
     * @param fileName
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static Object readObject(String fileName)
    {
        fileName = AppConstant.systemDir + "/" + fileName;
        
        FileInputStream istream = null;
        
        try
        {
            /**//* 打开文件并设置成从中读取对象。 */
            istream = new FileInputStream(fileName);
            ObjectInputStream q = new ObjectInputStream(istream);
            return q.readObject();
        }
        catch (Exception e)
        {
            
            e.printStackTrace();
        }
        
        try
        {
            if (null != istream)
            {
                istream.close();
            }
            
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return null;
        
    }
    
}
