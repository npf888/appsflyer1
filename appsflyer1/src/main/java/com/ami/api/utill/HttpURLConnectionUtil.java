package com.ami.api.utill;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;


public class HttpURLConnectionUtil
{
    public static JSONObject postNew(String urlstr, String activity_detail)
        throws Exception
    {
        URL url = null;
        HttpURLConnection http = null;
        OutputStreamWriter osw = null;
        JSONObject json  = new JSONObject();
        int code = 1;
        BufferedReader in = null;
        StringBuffer resp = new StringBuffer();
        InputStream input = null;
        try
        {
            
            url = new URL(urlstr);
            http = (HttpURLConnection)url.openConnection();
            http.setDoInput(true);
            http.setDoOutput(true);
            http.setUseCaches(false);
            http.setConnectTimeout(50000);// 设置连接超时
            // 如果在建立连接之前超时期满，则会引发一个
            // java.net.SocketTimeoutException。超时时间为零表示无穷大超时。
            http.setReadTimeout(50000);// 设置读取超时
            // 如果在数据可读取之前超时期满，则会引发一个
            // java.net.SocketTimeoutException。超时时间为零表示无穷大超时。
            http.setRequestMethod("POST");
            //http.setRequestProperty("Content-Type", "text/xml; charset=UTF-8");
            http.setRequestProperty("Content-Type", "application/json");
            http.connect();
            
         
            osw = new OutputStreamWriter(http.getOutputStream(), "utf-8");
            osw.write(activity_detail);
            osw.flush();
            
            if (http.getResponseCode() == 200)
            {
                try
                {
                    input = http.getInputStream();
                    in =new BufferedReader(new InputStreamReader(input, "utf-8"));
                    String inputLine;
                    while ((inputLine = in.readLine()) != null)
                    {
                        resp.append(inputLine).append("\r\n");
                    }
                    
                    json = JSON.parseObject(resp.toString());
                    code = (Integer)json.get("errorCode");
                    System.out.println(code);
                }
                catch (UnsupportedEncodingException e)
                {
                    e.printStackTrace();
                }
            }
            else
            {
                throw new Exception("Request Failed, Error" + http.getResponseCode());
            }
        }
        catch (Exception e)
        {
            throw e;
        }
        finally
        {
            
            if (null != osw)
            {
                
                try
                {
                    osw.close();
                }
                catch (IOException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            
            if (null != in)
            {
                
                try
                {
                    in.close();
                }
                catch (IOException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            
            if (http != null)
            {
                http.disconnect();
            }
            
        }
        
        return json;
    }
    public static JSONObject post(String urlstr, JSONObject activity_detail)
    		throws Exception
    {
    	URL url = null;
    	HttpURLConnection http = null;
    	OutputStreamWriter osw = null;
    	JSONObject json  = new JSONObject();
    	int code = 1;
    	BufferedReader in = null;
    	StringBuffer resp = new StringBuffer();
    	InputStream input = null;
    	try
    	{
    		
    		url = new URL(urlstr);
    		http = (HttpURLConnection)url.openConnection();
    		http.setDoInput(true);
    		http.setDoOutput(true);
    		http.setUseCaches(false);
    		http.setConnectTimeout(50000);// 设置连接超时
    		// 如果在建立连接之前超时期满，则会引发一个
    		// java.net.SocketTimeoutException。超时时间为零表示无穷大超时。
    		http.setReadTimeout(50000);// 设置读取超时
    		// 如果在数据可读取之前超时期满，则会引发一个
    		// java.net.SocketTimeoutException。超时时间为零表示无穷大超时。
    		http.setRequestMethod("POST");
    		http.setRequestProperty("Content-Type", "text/xml; charset=UTF-8");
    		http.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
    		http.connect();
    		
    		
    		osw = new OutputStreamWriter(http.getOutputStream(), "utf-8");
    		osw.write(activity_detail.toString());
    		osw.flush();
    		
    		if (http.getResponseCode() == 200)
    		{
    			try
    			{
    				input = http.getInputStream();
    				in =new BufferedReader(new InputStreamReader(input, "utf-8"));
    				String inputLine;
    				while ((inputLine = in.readLine()) != null)
    				{
    					resp.append(inputLine).append("\r\n");
    				}
    				
    				json = JSON.parseObject(resp.toString());
    				code = (Integer)json.get("errorCode");
    				System.out.println(code);
    			}
    			catch (UnsupportedEncodingException e)
    			{
    				e.printStackTrace();
    			}
    		}
    		else
    		{
    			throw new Exception("Request Failed, Error" + http.getResponseCode());
    		}
    	}
    	catch (Exception e)
    	{
    		throw e;
    	}
    	finally
    	{
    		
    		if (null != osw)
    		{
    			
    			try
    			{
    				osw.close();
    			}
    			catch (IOException e)
    			{
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    		}
    		
    		if (null != in)
    		{
    			
    			try
    			{
    				in.close();
    			}
    			catch (IOException e)
    			{
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    		}
    		
    		if (http != null)
    		{
    			http.disconnect();
    		}
    		
    	}
    	
    	return json;
    }
    
    public static JSONObject get(String urlstr)
        throws Exception
    {
        URL url = null;
        HttpURLConnection http = null;
        JSONObject json = new JSONObject();
        BufferedReader in = null;
        StringBuffer resp = new StringBuffer();
        InputStream input = null;
        try
        {
            url = new URL(urlstr);
            http = (HttpURLConnection)url.openConnection();
            if (http.getResponseCode() == 200)
            {
                try
                {
                    input = http.getInputStream();
                    in =
                        new BufferedReader(
                            new InputStreamReader(input, "utf-8"));
                    String inputLine;
                    while ((inputLine = in.readLine()) != null)
                    {
                        resp.append(inputLine).append("\r\n");
                    }
                    
                    json = JSON.parseObject(resp.toString());
                }
                catch (UnsupportedEncodingException e)
                {
                    e.printStackTrace();
                }
            }
            else
            {
                throw new Exception("Get Activity Request Failed, Error"
                    + http.getResponseCode());
            }
        }
        catch (Exception e)
        {
            throw e;
        }

    return json;
}
    
    public static JSONObject postFile(String urlstr, File content)
        throws Exception
    {
        URL url = null;
        HttpURLConnection http = null;
        OutputStreamWriter osw = null;
        FileInputStream fis =null;
        JSONObject json = new JSONObject();
        int code = 1;
        BufferedReader in = null;
        StringBuffer resp = new StringBuffer();
        InputStream input = null;
        try
        {
            
            url = new URL(urlstr);
            http = (HttpURLConnection)url.openConnection();
            http.setDoInput(true);
            http.setDoOutput(true);
            http.setUseCaches(false);
            http.setConnectTimeout(50000);// 设置连接超时
            // 如果在建立连接之前超时期满，则会引发一个
            // java.net.SocketTimeoutException。超时时间为零表示无穷大超时。
            http.setReadTimeout(50000);// 设置读取超时
            // 如果在数据可读取之前超时期满，则会引发一个
            // java.net.SocketTimeoutException。超时时间为零表示无穷大超时。
            http.setRequestMethod("POST");
            
            http.setRequestProperty("Content-Type", "multipart/form-data");
            http.connect();
            osw = new OutputStreamWriter(http.getOutputStream());
            
            fis = new FileInputStream(content);
            byte[] temp = new byte[4*1024*1024*1024];
            int bytesread;
            while((bytesread = fis.read(temp)) != -1)
            {
                osw.write(temp.toString(), 0, bytesread);
            }
                      
            osw.flush();
            
            if (http.getResponseCode() == 200)
            {
                try
                {
                    input = http.getInputStream();
                    in =
                        new BufferedReader(
                            new InputStreamReader(input, "utf-8"));
                    String inputLine;
                    while ((inputLine = in.readLine()) != null)
                    {
                        resp.append(inputLine).append("\r\n");
                    }
                    
                    json = JSON.parseObject(resp.toString());
                    code = (Integer)json.get("errorCode");
                    System.out.println(code);
                }
                catch (UnsupportedEncodingException e)
                {
                    e.printStackTrace();
                }
            }
            else
            {
                throw new Exception("Post Request Failed, Error"
                    + http.getResponseCode());
            }
        }
        catch (Exception e)
        {
            throw e;
        }
        finally
        {
            
            if (null != osw)
            {
                
                try
                {
                    osw.close();
                }
                catch (IOException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            
            if (null != in)
            {
                
                try
                {
                    in.close();
                }
                catch (IOException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            
            if (http != null)
            {
                http.disconnect();
            }
            
        }
        
        return json;
    }

}
