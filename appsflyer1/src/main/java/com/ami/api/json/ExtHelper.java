package com.ami.api.json;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;




/**
 * Title: ExtHelper.java 杈呭姪绫� Description: 璇ョ被鐢ㄤ簬杞崲java瀵硅薄涓篨ML鏂囦欢鏍煎紡鎴朖SON鏂囦欢鏍煎紡
 * 
 * @author weijun
 * @time: 2008.07.09
 */
public class ExtHelper {

	/**
	 * 閫氳繃List鐢熸垚JSON鏁版嵁
	 * @param recordTotal 璁板綍鎬绘暟锛屼笉涓�瀹氫笌beanList涓殑璁板綍鏁扮浉绛�
	 * @param beanList 鍖呭惈bean瀵硅薄鐨勯泦鍚�
	 * @return 鐢熸垚鐨凧SON鏁版嵁
	 */
	public static String getJsonFromList(long recordTotal , List beanList){
		TotalJson total = new TotalJson();
		total.setResults(recordTotal);
		total.setItems(beanList);
		JSONObject JsonObject = (JSONObject)JSON.toJSON(total);
		return JsonObject.toString();
	}
	/**
	 * 閫氳繃List鐢熸垚JSON鏁版嵁
	 * @param beanList 鍖呭惈bean瀵硅薄鐨勯泦鍚�
	 * @return 鐢熸垚鐨凧SON鏁版嵁
	 */
	public static String getJsonFromList(List beanList){
		return getJsonFromList(beanList.size(),beanList);
	}
	/**
	 * 閫氳繃bean鐢熸垚JSON鏁版嵁
	 * @param bean bean瀵硅薄
	 * @return 鐢熸垚鐨凧SON鏁版嵁
	 */
	public static String getJsonFromBean(Object bean){
		JSONObject JsonObject = (JSONObject)JSON.toJSON(bean);
		return JsonObject.toString();
	}
	
}