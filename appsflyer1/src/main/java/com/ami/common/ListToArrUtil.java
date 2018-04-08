package com.ami.common;

import java.util.List;

/**
 * 集合 和 数组 之间的转换
 * @author JavaServer
 *
 */
public class ListToArrUtil {

	
	public static Object[] getArrFromList(List<Object> objList){
		
		Object[] objectArr = new Object[objList.size()];
		if(objList != null && objList.size()>0){
			for(int i=0;i<objList.size();i++){
				objectArr[i]=objList.get(i);
			}
		}
		return objectArr;
	}
}
