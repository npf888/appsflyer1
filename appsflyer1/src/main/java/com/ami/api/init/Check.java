package com.ami.api.init;

import com.ami.api.utill.DateTools;
import com.ami.api.utill.GetMacAddress;
import com.ami.api.utill.PropertyUtill;
import com.ami.api.utill.TripleDES;

public class Check
{
    
    public static void main(String[] args)
        throws Exception
    {
        String dir = "resin/modules/c/src/common/version.h.r981";
        
        check(dir);
    }
    
    public static boolean check(String usrdir)
        throws Exception
    {
        try
        {
            PropertyUtill c = new PropertyUtill(usrdir);
            
            String t = c.getValue("t");
            
            String y = c.getValue("y");
            
            System.out.println("---->" + t);
            
            byte ss[] = TripleDES.hexStr2ByteArr(t);
            
            String con2 = new String(TripleDES.decrypt(ss, TripleDES.key.getBytes()));
            
            System.out.println("---->" + con2);
            
            String current = DateTools.getCurrentDate(DateTools.DATE_24_YYYYMMDDHHMMSS);
            
            long dis = Long.parseLong(con2.trim()) - Long.parseLong(current);
            
            String mac1 = GetMacAddress.getLinuxMACAddress();
            
            byte[] ttt = TripleDES.encrypt(mac1.getBytes("UTF-8"), TripleDES.key.getBytes());
            
            String des = TripleDES.byteArr2HexStr(ttt);
            
            
            if (dis > 0 && (y == null || y.length() == 0))
            {
                
                c.setValue("y", des);
                c.saveFile(usrdir, "");
                return true;
            }
            else
            {
                if (!y.endsWith(des))
                {
                    throw new Exception("--");
                }
            }
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new Exception(e);
        }
        
        return false;
    }
    
}
