package com.ami.api.utill;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * 
 * 字符串操作常用工具类
 * 
 */

public final class StringTool
{
    
    /**
     * <默认构�?�函�?>
     */
    private StringTool()
    {
    }
    
    /**
     * 判断�?个字符串是否为空，包括长度等�?0
     * 
     * @param str �?要判断的字符
     * @return 为空返回true,非空返回false
     */
    public static boolean isBlank(String str)
    {
        // 对象为NULL
        if (null == str)
        {
            return true;
        }
        // 长度�?0
        else if ("".equals(str.trim()))
        {
            return true;
        }
        return false;
    }
    
    /**
     * �?测一个字符串是否全部由数字组�?
     * 
     * @param str String变量
     * @return true表示是，false表示�?
     */
    public static boolean isNumber(String str)
    {
        
        // 如果str为空或�?�null，返回false
        if (null == str || "".equals(str.trim()))
        {
            
            return false;
        }
        
        if (!str.matches("^(\\d)+$"))
        {
            
            return false;
        }
        
        // �?测�?�过，返回true
        return true;
    }
    
    /**
     * 将空值和null值处理为空字符串
     * 
     * @param value 输入�?
     * @return 返回value值或者空字符�?
     */
    
    public static String getValue(Object value)
    {
        if (null == value)
        {
            return "";
        }
        
        if (null == value || "null".equals(value))
        {
            value = "";
        }
        else
        {
            return value.toString().trim();
        }
        
        return "";
    }
    
    /**
     * 如果字符串长度小于给定长度，按字符串长度截取字符�?
     * 
     * @param beforeString 原始字符�?
     * @param len 要求长度
     * @return afterString 处理后的字符�?
     * @see [类�?�类#方法、类#成员]
     */
    public static String getLenString(String beforeString, int len)
    {
        String afterString = "";
        
        if (beforeString.length() <= len)
        {
            afterString = beforeString;
        }
        else
        {
            afterString = beforeString.substring(len);
        }
        
        return afterString;
    }
    
    /**
     * 字符串数组转�?<�?句话功能�?�?> 如果参数为null则返回空字符串数组，否则返回字符串数�?<功能详细描述>
     * 
     * @param parames 输入字符�?
     * @return String[] 转换后的字符�?
     * @see [类�?�类#方法、类#成员]
     */
    public static String[] convertArrayString(String[] parames)
    {
        if (null == parames)
        {
            parames = new String[]
            {""};
        }
        
        return parames;
    }
    
    /**
     * 去掉字符串前后空格，如果字符串为null就返�?""
     * 
     * @param str String 源字符串
     * @return String 生成字符�?
     */
    public static String trimString(String str)
    {
        return null == str ? "" : str.trim();
    }
    
    /**
     * 判断字符串是否为null并且去掉空格后是否为""
     * 
     * @param str String 源字符串
     * @return String 生成字符�?
     */
    public static boolean isEmpty(String str)
    {
        boolean b = true;
        if (null != str && !"".equals(str.trim()))
        {
            b = false;
        }
        return b;
    }
    
    /**
     * 将转义字符替换为特殊字符 转换"&", "<", ">", "\"", "'"字符
     * 
     * @param text String
     * @return string
     */
    public static String convertSpecialChars(String text)
    {
        // 如果为空，直接返�?
        if (isBlank(text))
        {
            return text;
        }
        String tempStr = text;
        tempStr = tempStr.replace("&amp;", "&");
        tempStr = tempStr.replace("&lt;", "<");
        tempStr = tempStr.replace("&gt;", ">");
        tempStr = tempStr.replace("&quot;", "\"");
        tempStr = tempStr.replace("&apos", "'");
        return tempStr;
    }
    
    /**
     * 将换特殊字符替换为转义字�?
     * <p>
     * 如果字符串中含有�?&�?<�?>”等字符，进行转义，
     * <p>
     * 如果字符串为null，则返回空字符串
     * 
     * @param text 原始字符�?
     * @return 替换之后的字符串
     * @see [类�?�类#方法、类#成员]
     */
    public static String replaceParticularChars(String text)
    {
        /*
         * String str=""; if(text!=null) { int len = text.length(); StringBuffer
         * temp = new StringBuffer(); for (int j = 0; j < len; j++) { char c =
         * text.charAt(j); if ('&' == c && ((!(j + 4 > len) &&
         * !"&lt;".equals(text.substring(j, j + 4)) || (j + 4 > len)) && (!(j +
         * 4 > len) && !"&gt;".equals(text.substring(j, j + 4)) || (j + 4 >
         * len)) && (!(j + 6 > len) && !"&quot;".equals(text.substring(j, j +
         * 6)) || (j + 6 > len)) && (!(j + 6 > len) &&
         * !"&apos;".equals(text.substring(j, j + 6)) || (j + 6 > len)) && (!(j
         * + 5 > len) && !"&amp;".equals(text.substring(j, j + 5)) || (j + 5 >
         * len)))) { temp.append("&amp;"); } else { temp.append(c); } }
         * 
         * str = temp.toString() .replaceAll("<", "&lt;") .replaceAll(">",
         * "&gt;") .replaceAll("\"", "&quot;") .replaceAll("'", "&apos;"); }
         */
        return getValue(text);
        
    }
    
    /**
     * 字符串数组的toString方法
     * 
     * @param strArray 待toString的字符串数组
     * @return 输出的toString
     * @see [类�?�类#方法、类#成员]
     */
    public static String strArrayToString(String[] strArray)
    {
        // 如果为空，直接返�?
        if (null == strArray)
        {
            return null;
        }
        StringBuffer strBuf = new StringBuffer();
        
        strBuf.append('{');
        for (int i = 0; i < strArray.length; i++)
        {
            strBuf.append(strArray[i]);
            if (i != strArray.length - 1)
            {
                strBuf.append(',');
            }
        }
        strBuf.append('}');
        
        return strBuf.toString();
    }
    
    /**
     * 字符串转数组
     * 
     * @param field 字符数组
     * @param conv 分隔�?
     * @return
     */
    public static String arryToString(String field[], String conv)
    {
        
        StringBuffer sb = new StringBuffer("");
        
        if (null == field)
        {
            return "";
        }
        
        for (String str : field)
        {
            if ("".equals(str))
            {
                continue;
            }
            sb.append(str);
            sb.append(conv);
        }
        
        return sb.substring(0, sb.lastIndexOf(conv)).toString();
    }
    
    /**
     * 把字符串转换成大�?
     * 
     * @param str String 源字符串
     * @return String 生成字符�?
     */
    public static String toUpperCase(String str)
    {
        return getValue(str).toUpperCase(Locale.CHINESE);
    }
    
    /**
     * 数�?�字符串转换成整�?,转换失败,取默认�?�tVal;
     * 
     * @param str 待转换字符串
     * @param tVal 默认�?
     * @return int 返回�?
     * @see [类�?�类#方法、类#成员]
     */
    public static int strToNum(String str, int tVal)
    {
        try
        {
            return Integer.parseInt(getValue(str));
        }
        catch (NumberFormatException ex)
        {
            return tVal;
        }
    }
    
    /**
     * 判断参数是否为空
     * 
     * @param parame 输入参数
     * @return boolean 参数为空返回false,参数不为空返回true
     * @see [类�?�类#方法、类#成员]
     */
    public static boolean parmeIsNotEmpty(String parame)
    {
        // 设置默认参数不为空，返回true
        boolean isNotEmpty = true;
        
        // 如果parame为空,则返回false
        if (null == parame || "".equals(parame.trim()) || "null".equals(parame.trim()))
        {
            isNotEmpty = false;
        }
        
        return isNotEmpty;
    }
    
    /**
     * 对象数组toString--lijiangbo add
     * 
     * @param objs Object[] 对象数组
     * @return String 对象数组的字符串表达
     */
    public static String arrayToString(Object[] objs)
    {
        if (null != objs)
        {
            StringBuffer objsBuffer = new StringBuffer();
            objsBuffer.append('{');
            for (int i = 0; i < objs.length; i++)
            {
                objsBuffer.append(objs[i]);
                if (i < objs.length - 1)
                {
                    objsBuffer.append(',');
                }
            }
            objsBuffer.append('}');
            return objsBuffer.toString();
        }
        return "{null}";
    }
    
    /**
     * 从GBK编码转换成ISO-8859-1
     * 
     * @param param 要转换的字符�?
     * @return String 转换后的字符�?
     */
    public static String setEnCodingIso8859(String param)
    {
        try
        {
            return new String(getValue(param).getBytes("GBK"), "ISO-8859-1");
        }
        catch (UnsupportedEncodingException e)
        {
            
        }
        
        return getValue(param);
    }
    
    /**
     * 从ISO-8859-1编码转换成GBK
     * 
     * @param param 要转换的字符�?
     * @return String 转换后的字符�?
     */
    public static String setEnCodingGBK(String param)
    {
        try
        {
            return new String(getValue(param).getBytes("UTF-8"), "GBK");
        }
        catch (UnsupportedEncodingException e)
        {
            
        }
        
        return getValue(param);
    }
    
    /**
     * 根据URL 获取参数
     * 
     * @param url
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static Map<String, String> getParamMap(String url)
    {
        if (url.indexOf("?") != -1)
        {
            url = url.substring(url.indexOf("?") + 1);
        }
        
        String params[] = url.split("&");
        Map<String, String> para = new HashMap<String, String>();
        for (int i = 0; i < params.length; i++)
        {
            String key = null;
            String value = null;
            
            if (params[i].indexOf("=") != -1)
            {
                key = params[i].substring(0, params[i].indexOf("="));
                value = params[i].substring(params[i].indexOf("=") + 1, params[i].length());
                para.put(key, value);
            }
            
        }
        
        return para;
    }

}