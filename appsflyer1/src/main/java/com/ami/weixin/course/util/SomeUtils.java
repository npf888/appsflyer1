package com.ami.weixin.course.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SomeUtils {

	/**
	 * 去掉换行、回车、制表符
	 * @return
	 */
	public static String removeSymbol(String str){
		Pattern p = Pattern.compile("\\s*|\t|\r|\n");
        Matcher m = p.matcher(str);	
        String rStr = m.replaceAll("");
        return rStr;
	}
	
	
}
