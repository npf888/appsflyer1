package com.ami.api.utill;

/**
 * 
 * 基站处理函数
 * 
 * @author  zhuweiliang
 * @version  [版本号, 2013-6-24]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class CellTools
{
    public static int IntToHexToInt(String num)
    {
        int i = 0;
        try
        {
            String hex = Integer.toHexString(Integer.parseInt(num));
            
            i = Integer.parseInt(hex.substring(0, 3), 16);
            
        }
        catch (Exception e)
        {
            
        }
        
        return i;
    }
}
