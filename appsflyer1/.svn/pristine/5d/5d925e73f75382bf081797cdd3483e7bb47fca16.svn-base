package com.ami.api.utill;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/** */
/**
 * 璇诲彇properties鏂囦欢 2012锟�?3锟�?2锟�? 10:24:51
 * 
 * @author 鏈变紵锟�?
 * 
 */
public class PropertyUtill
{
    private Properties propertie;
    
    private FileInputStream inputFile;
    
    private FileOutputStream outputFile;
    
    /** */
    /**
     * 鍒濆鍖朇onfiguration锟�?
     */
    public PropertyUtill()
    {
        propertie = new Properties();
    }
    
    /** */
    /**
     * 鍒濆鍖朇onfiguration锟�?
     * 
     * @param filePath 瑕佽鍙栫殑閰嶇疆鏂囦欢鐨勮矾锟�?+鍚嶇О
     * @throws IOException
     */
    public PropertyUtill(String filePath)
        throws IOException
    {
        propertie = new Properties();
        inputFile = new FileInputStream(filePath);
        propertie.load(inputFile);
        inputFile.close();
        
    }
    
    /** */
    /**
     * 閲嶈浇鍑芥暟锛屽緱鍒発ey鐨勶拷??
     * 
     * @param key 鍙栧緱鍏讹拷?锟界殑锟�?
     * @return key鐨勶拷??
     */
    public String getValue(String key)
    {
        if (propertie.containsKey(key))
        {
            String value = propertie.getProperty(key);// 寰楀埌鏌愪竴灞烇拷?锟界殑锟�?
            return value;
        }
        else
            return "";
    }// end getValue()
    
    /** */
    /**
     * 閲嶈浇鍑芥暟锛屽緱鍒発ey鐨勶拷??
     * 
     * @param fileName properties鏂囦欢鐨勮矾锟�?+鏂囦欢锟�?
     * @param key 鍙栧緱鍏讹拷?锟界殑锟�?
     * @return key鐨勶拷??
     */
    public String getValue(String fileName, String key)
    {
        try
        {
            String value = "";
            inputFile = new FileInputStream(fileName);
            propertie.load(inputFile);
            inputFile.close();
            if (propertie.containsKey(key))
            {
                value = propertie.getProperty(key);
                return value;
            }
            else
                return value;
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
            return "";
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return "";
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return "";
        }
    }// end getValue()
    
    /** */
    /**
     * 娓呴櫎properties鏂囦欢涓墍鏈夌殑key鍜屽叾锟�?
     */
    public void clear()
    {
        propertie.clear();
    }// end clear();
    
    /** */
    /**
     * 鏀瑰彉鎴栨坊鍔犱竴涓猭ey鐨勶拷?锟斤紝褰搆ey瀛樺湪浜巔roperties鏂囦欢涓椂璇ey鐨勶拷?锟借value锟�?浠ｆ浛锟�?
     * 褰搆ey涓嶅瓨鍦ㄦ椂锛岃key鐨勶拷?锟芥槸value
     * 
     * @param key 瑕佸瓨鍏ョ殑锟�?
     * @param value 瑕佸瓨鍏ョ殑锟�?
     */
    public void setValue(String key, String value)
    {
        propertie.setProperty(key, value);
    }// end setValue()
    
    /** */
    /**
     * 灏嗘洿鏀瑰悗鐨勬枃浠舵暟鎹瓨鍏ユ寚瀹氱殑鏂囦欢涓紝璇ユ枃浠跺彲浠ヤ簨鍏堜笉瀛樺湪锟�?
     * 
     * @param fileName 鏂囦欢璺緞+鏂囦欢鍚嶇О
     * @param description 瀵硅鏂囦欢鐨勬弿锟�?
     */
    public void saveFile(String fileName, String description)
    {
        try
        {
            outputFile = new FileOutputStream(fileName);
            propertie.store(outputFile, description);
            outputFile.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
    }// end saveFile()
    
    public static void main(String[] args)
        throws IOException
    {
        PropertyUtill rc = new PropertyUtill("./config.property");// 鐩稿璺緞
        String login_url = rc.getValue("ip");// 浠ヤ笅璇诲彇properties鏂囦欢鐨勶拷??
        String host = rc.getValue("databaseName");
        String tab = rc.getValue("tab");
        
        System.out.println(login_url);
        
    }
}
