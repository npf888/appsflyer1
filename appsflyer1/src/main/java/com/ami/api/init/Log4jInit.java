package com.ami.api.init;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;

/**
 * 
 * ��ʼ��log4j
 * 
 * @author zhuweiliang
 * @version [�汾��, Jun 6, 2012]
 * @see [�����/����]
 * @since [��Ʒ/ģ��汾]
 */
public class Log4jInit
{
    
    public static void init(String path)
    {
        Properties props = new Properties();
        try
        {
            FileInputStream istream = new FileInputStream(path);
            props.load(istream);
            istream.close();
            PropertyConfigurator.configure(props);// װ��log4j������Ϣ
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return;
        }
    }
    
}
