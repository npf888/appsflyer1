package com.ami.weixin.course.util;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

public class Constant {
	/**
	 * 服务器最大人数
	 */
	public static final int waring_max_num=500;
	/**
	 * public
	 */
	public static final String active="active";
	public static final String area_singapore="新加坡";
	public static final String area_turkey="土耳其";
	public static final String area_wrong="地区错误";
	public static final String active_not_clear="行动不明确";
	public static final String validate_ok="通过";
	/**
	 * 充值的 返回值
	 */
	public static final String recharge ="充值";
	public static final String recharge_success ="充值成功";
	public static final String recharge_failed ="充值失败";
	public static final String recharge_info_error_num ="输入信息有误[信息个数不足，例如：充值  位置[例如:土耳其]  用户名   密码   用户ID 金额],请重新输入";
	public static final String recharge_authority_no ="用户没有权限充值";
	public static final String recharge_id_error ="输入信息有误[用户ID错误],请重新输入";
	public static final String recharge_gold_error ="输入信息有误[金额错误],请重新输入";
	public static final String recharge_gold_too_long_error ="输入信息有误[金额太大,最大为100000],请重新输入";
	public static final String recharge_ok ="ok";
	
	/**
	 * 查看信息
	 */
	public static final String view_online ="查看单个在线";
	public static final String view_area_wrong ="请输入正确格式[例如:查看在线  地区[土耳其] 10001]";
	public static final String view_id_error ="输入信息有误[用户ID错误],请重新输入";
	public static final String view_all_online = "查看所有在线";
	/**
	 * 机器人
	 */
	public static final String robot_start_all="开启所有机器人";
	public static final String robot_start="开启机器人";
	public static final String robot_start_success="开启成功";
	public static final String robot_shutdown_all="关闭所有机器人";
	public static final String robot_shutdown="关闭机器人";
	public static final String robot_shutdown_success="关闭成功";
	public static final String robot_num_wrong ="请输入正确格式[例如:开启/关闭机器人 地区[土耳其]]";
	/**
	 * 异常通知
	 */
	public static final String exception_notice="exception_notice";
	public static final String exception_notice_ok="异常通知完毕";
	public static final String exception_notice_wrong="异常通知不存在";
	/**
	 * 用户 player
	 */
	public static final String player_is_null="用户为空";
	public static final String player_final_is_null="没有用户信息";
	
	/**
	 * 服务器访问失败
	 */
	public static final String service_visit_error="服务器访问失败";
	
	/**
	 * 获取 game_server.properties 和 backstage.properties中的  地区-url的Map
	 * @param request
	 * @return
	 */
	
	private static UrlProperties getUrlProperties(HttpServletRequest request){
		ServletContext servletContext = request.getSession().getServletContext();
		return (UrlProperties)servletContext.getAttribute("urlProperties");
	}
	
	public static Map<String,String> getGameServerUrlMap(HttpServletRequest request){
		return getUrlProperties(request).getGameServerUrlMap();
	}
	public static Map<String,String> getbackstageUrlMap(HttpServletRequest request){
		return getUrlProperties(request).getBackstageUrlMap();
	}
	
}
