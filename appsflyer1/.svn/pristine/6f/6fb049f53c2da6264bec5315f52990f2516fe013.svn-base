package com.ami.weixin.course.service;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ami.api.exception.APIException;
import com.ami.api.utill.TripleDES;
import com.ami.common.HttpClientUtil;
import com.ami.sys.admin.bean.AdminBean;
import com.ami.sys.admin.dao.AdminDao;
import com.ami.weixin.course.dao.CoreDao;
import com.ami.weixin.course.pojo.resp.TextRespMessage;
import com.ami.weixin.course.util.Constant;
import com.ami.weixin.course.util.CopyOfMessageUtil;
import com.ami.weixin.course.util.MessageUtil;

@Component
public class CoreService {
	
	private static Logger logger = Logger.getLogger(CoreService.class);
	@Autowired
	private CoreDao coreDao;
	@Autowired
	private AdminDao adminDao;
	@Autowired
	private WatchingService watchingService;
	
	@Autowired
	private WarningService warningService;
	
	public  String processRequest(HttpServletRequest request) {
		  String respMessage =  "";
	        try {  
	        	  // 回复文本消息  
	            TextRespMessage textRespMessage = getParseXml(request); 
	            logger.info( "fromUserName-openID:"+textRespMessage.getFromUserName()+" -- toUserName:"+textRespMessage.getToUserName()+" -- content :"+textRespMessage.getContent());
	          //根据用户传递文字判断处理内容
	            String[] ss = getParamInfo(textRespMessage.getContent());
	            /**
	             * 充值  微信公众号用户发送的信息格式： [ 充值  位置[例如:土耳其]  用户名   密码   用户ID 金额 ]
	             */
	            if(Constant.recharge.equals(ss[0])){
	            	respMessage = recharge(ss,request);
	            	logger.info("back     info      recharge    finish........the message"+respMessage);
	            	
	            	/**
		             * 查看单个在线  微信公众号用户发送的信息格式：[ 查看单个在线  地区   用户ID ]
		             */
	            }else if(Constant.view_online.equals(ss[0])){
	            	respMessage = checkViewByPassportId(ss,request);
	            	logger.info("back     info      view    finish........the message"+respMessage);
	            	
	            	/**
	            	 * 开启机器人  微信公众号用户发送的信息格式：[ 开启机器人  地区   ]
	            	 */
	            }else if(Constant.robot_start.equals(ss[0])){
	            	respMessage = startShutRobot(ss,request);
	            	logger.info("back     info      start_robot    finish........the message"+respMessage);
	            	
	            	/**
	            	 * 关闭机器人  微信公众号用户发送的信息格式：[ 关闭机器人  地区   ]
	            	 */
	            }else if(Constant.robot_shutdown.equals(ss[0])){
	            	respMessage = startShutRobot(ss,request);
	            	logger.info("back     info      shut_robot    finish........the message"+respMessage);
	            	/**
	            	 * 开启所有机器人  微信公众号用户发送的信息格式：[ 开启机器人  地区   ]
	            	 */
	            }else if(Constant.robot_start_all.equals(ss[0])){
	            	respMessage = startShutAllRobot(ss,request);
	            	logger.info("back     info      start_all_robot    finish........the message"+respMessage);
	            	
	            	/**
	            	 * 关闭所有机器人  微信公众号用户发送的信息格式：[ 关闭机器人  地区   ]
	            	 */
	            }else if(Constant.robot_shutdown_all.equals(ss[0])){
	            	respMessage = startShutAllRobot(ss,request);
	            	logger.info("back     info      shut_all_robot    finish........the message"+respMessage);
	            	
	            	/**
		             * 如果什么也不做，就是保存openID，如果已经存在，就不做处理
		             */
	            }else{
	            	respMessage = dealWtihOpenID(textRespMessage);
	            }
	            /**
	             * 返回值
	             */
	            respMessage=getRespMessage(textRespMessage,respMessage);
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	        return respMessage;  
	}
	private String getRespMessage(TextRespMessage textRespMessage, String respMessage) {
		String from = textRespMessage.getFromUserName();
    	String to = textRespMessage.getToUserName();
    	textRespMessage.setFromUserName(to);
    	textRespMessage.setToUserName(from);
    	textRespMessage.setContent(respMessage);
        respMessage = MessageUtil.textMessageToXml(textRespMessage); 
		return respMessage;
	}
	private String[] getParamInfo(String str){
		String[] ss = new String[1];
        if(StringUtils.isNotBlank(str)){
        	ss = str.split("\\s+");
        }else{
        	ss[0]="none";
        }
        return ss;
	}
	//开启关闭所有机器人
	private String startShutAllRobot(String[] ss,HttpServletRequest request){
		//验证
		if(ss.length != 1){
			return Constant.robot_num_wrong;
		}
		HashMap<String,String> params = new HashMap<String,String>();
		params.put("active", ss[0]);
		handleAllGameServerRemoteUrl(request,params);
		return Constant.robot_start_success;
	}
	//开启关闭机器人
	private String startShutRobot(String[] ss,HttpServletRequest request) {
		//验证
		if(ss.length != 2){
			return Constant.robot_num_wrong;
		}
		HashMap<String,String> params = new HashMap<String,String>();
		params.put("active", ss[0]);
		return handleGameServerRemoteUrl(ss[1],request,params);
	}
	//查看在线
	private String checkViewByPassportId(String[] ss,HttpServletRequest request) {
		//验证
		String validateInfo = checkViewValidate(ss);
		if(!Constant.validate_ok.equals(validateInfo)){
			return validateInfo;
		}
		HashMap<String,String> params = new HashMap<String,String>();
		params.put("active", ss[0]);
		params.put("passportId", ss[2]);
		String gameServerplayer = handleGameServerRemoteUrl(ss[1],request,params);
		//如果在游戏服务器上没有查到该用户，就去 数据库里查
		logger.info("gameServerplayer--------------------"+gameServerplayer);
		if(Constant.player_is_null.equals(gameServerplayer)){
			String result = handleBackstageRemoteUrl(ss[1],request,params);
			logger.info("player_is_null   -------result-------------"+result);
			return result;
		}else if(StringUtils.isNotBlank(gameServerplayer)){
			return gameServerplayer;
		}else{
			//最终没有就是没有查到
			return Constant.player_final_is_null;
		}
	}
	private String checkViewValidate(String[] ss) {
		if(ss.length != 3){
			return Constant.view_area_wrong;
		}
		Pattern pattern = Pattern.compile("[0-9]*"); 
		Matcher isNum2 = pattern.matcher(ss[2]);
		if(!isNum2.matches()){
			return Constant.view_id_error; 
		}
	    return Constant.validate_ok;
	}
	//充值
	private String recharge(String[] ss,HttpServletRequest request) throws UnsupportedEncodingException {
		//验证
		String validateInfo = toValidateRecharge(ss);
		if(!Constant.validate_ok.equals(validateInfo)){
			return validateInfo;
		}
		//本地
		if(Constant.area_singapore.equals(ss[1])){
			//权限本地验证
			if(!judgeAuthority(ss[2],ss[3])){
	    		return Constant.recharge_authority_no;
	    	}
			return saveGold(ss[5],ss[4]);
		///所有远程
		}else{
			HashMap<String,String> params = new HashMap<String,String>();
			params.put("active", ss[0]);
			params.put("passportId", ss[4]);
			params.put("gold", ss[5]);
			return handleBackstageRemoteUrl(ss[1],request,params);
		}
		
	}
	public String saveGold(String passportId,String gold) {
		return coreDao.saveGold(Integer.valueOf(gold),Integer.valueOf(passportId));
	}
	/**
	 * 本地充值
	 * @param ss
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	private String toValidateRecharge(String[] ss) throws UnsupportedEncodingException {
		if(ss.length < 6){
			return Constant.recharge_info_error_num;
		}
		//先判断此用户有没有权限去充值，所有后台的管理人员才有权限
    	String username = ss[2];
    	String password = ss[3];
    	String passportId = ss[4];
		String gold = ss[5];
    	logger.info("one::"+new String(ss[0].getBytes("UTF-8"),"gbk")+"place:::"+ss[1]+" username::"+ss[2]+" pwssword::"+ss[3]+" passportID::"+ss[4]+" gold::"+ss[5]);
    	String rResult = validateParam(passportId, gold);
		if(!"ok".equals(rResult)){
			return rResult;
		}
		return Constant.validate_ok;
	}
	
	private String validateParam(String passportId,String gold){
		Pattern pattern = Pattern.compile("[0-9]*"); 
		Matcher isNum2 = pattern.matcher(passportId);
		if(!isNum2.matches()){
			return Constant.recharge_id_error; 
		}
	    Matcher isNum = pattern.matcher(gold);
	    if(!isNum.matches() ){
	       return Constant.recharge_gold_error; 
	    } 
	    int intGold = Integer.valueOf(gold);
	    if(intGold >= 100000){
	    	return Constant.recharge_gold_too_long_error;
	    }
	    return Constant.recharge_ok;
	}
	/**
	 * 用户充值判断用户有没有权限
	 * @param textRespMessage
	 */
	private boolean judgeAuthority(String username,String password) {
		 // 鍔犲瘑
        String pwd = TripleDES.getEncString(username + password);
		try {
			AdminBean AdminBean =  adminDao.loadByAccountPwd(username,pwd);
			if(AdminBean != null){
				return true;
			}
			return false;
		} catch (APIException e) {
			e.printStackTrace();
			return false;
		}
		
	}

	/**
	 * 保存用的openID
	 * @param textRespMessage
	 * @return
	 */
	private String dealWtihOpenID(TextRespMessage textRespMessage) {
		// 默认返回的文本消息内容  
        String respContent = "璇锋眰澶勭悊寮傚父锛岃绋嶅�欏皾璇曪紒";  
       // 文本消息  
        if (textRespMessage.getMsgType().equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {  
            respContent = "娆㈣繋鎮ㄧ殑鍏虫敞锛�";
         // 事件推送  
        }else if (textRespMessage.getMsgType().equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {  
        	 // 事件类型  
            String eventType = textRespMessage.getEvent(); 
         // 订阅  
            if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {  
                respContent = "璋㈣阿鎮ㄧ殑鍏虫敞锛�";  
            }  
         // 取消订阅  
            else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {  
            	 // TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息   
            }  
         // 自定义菜单点击事件  
            else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {  
            	// TODO 自定义菜单权没有开放，暂不处理该类消息  
            }  
        }  
        coreDao.saveUserInfo(textRespMessage);
        textRespMessage.setContent(respContent);  
        return respContent;
		
	}

	/**
	 * 解析request中的xml 变为对象
	 * @param request
	 * @return
	 * @throws Exception
	 */
	
	
	private TextRespMessage getParseXml(HttpServletRequest request) throws Exception{
		// xml请求解析  
		Map<String, String> requestMap = null;
		try{
			System.out.print(MessageUtil.EVENT_TYPE_CLICK);
			CopyOfMessageUtil.parseXml1();
			MessageUtil.parseXml1();
			requestMap = MessageUtil.parseXml(request);  
		}catch(Exception e){
			e.printStackTrace();
		}

		// 发送方帐号（open_id） 
        String fromUserName = requestMap.get("FromUserName");  
        // 公众帐号  
        String toUserName = requestMap.get("ToUserName");  
     // 消息类型  
        String msgType = requestMap.get("MsgType");  
        // 内容
        String content = requestMap.get("Content");  

        // 回复文本消息  
        TextRespMessage textRespMessage = new TextRespMessage();  
        textRespMessage.setToUserName(toUserName);  
        textRespMessage.setFromUserName(fromUserName);  
        textRespMessage.setCreateTime(new Date().getTime());  
        textRespMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);  
        textRespMessage.setFuncFlag(0); 
        textRespMessage.setEvent(textRespMessage.getEvent());
        textRespMessage.setContent(content);
        return textRespMessage;
	}

	
	
	/**
	 * 远程调用 game_server服务器POST
	 * @param ss
	 * @param request
	 * @return
	 */
	public void handleAllGameServerRemoteUrl(HttpServletRequest request,HashMap<String,String> params){
		Map<String,String> backstageMap = Constant.getGameServerUrlMap(request);
		for(Entry<String,String> entry: backstageMap.entrySet()){
			String key = entry.getKey();
			String value = entry.getValue();
			//判断是哪个地区，就调用哪个地区的url
			try{
				handleGameServerRemoteUrl(value,params);
			}catch(Exception e){
				continue;
			}
		}
	}
	/**
	 * 远程调用 game_server 所有 服务器POST
	 * @param ss
	 * @param request
	 * @return
	 */
	public String handleGameServerRemoteUrl(String area,HttpServletRequest request,HashMap<String,String> params){
		Map<String,String> backstageMap = Constant.getGameServerUrlMap(request);
		for(Entry<String,String> entry: backstageMap.entrySet()){
			String key = entry.getKey();
			String value = entry.getValue();
			//判断是哪个地区，就调用哪个地区的url
			if(key.equals(area)){
				String resu = handleGameServerRemoteUrl(value,params);
				if(StringUtils.isBlank(resu)){
					return Constant.service_visit_error+"--"+key+"--"+value;
				}
				return resu;
			}
		}
		return Constant.area_wrong;
	}
	
	private String handleGameServerRemoteUrl(String url,HashMap<String,String> params) {
		return HttpClientUtil.postUrl(url, params);
	}
	/**
	 * 远程调用  后台服务器POST
	 * @param ss
	 * @param request
	 * @return
	 */
	public String handleBackstageRemoteUrl(String area,HttpServletRequest request,HashMap<String,String> params){
		Map<String,String> backstageMap = Constant.getbackstageUrlMap(request);
		for(Entry<String,String> entry: backstageMap.entrySet()){
			String key = entry.getKey();
			String value = entry.getValue();
			//判断是哪个地区，就调用哪个地区的url
			logger.info("-------------kkkkey:"+key+"-----------value---"+value);
			if(key.equals(area)){
				String resu = handleBackstageRemoteUrl(value,params);
				logger.info("-------------resu:"+resu+"-----------value--params-"+params.toString());
				if(StringUtils.isBlank(resu)){
					return Constant.service_visit_error+"--"+key+"--"+value;
				}
				return resu;
			}
		}
		return Constant.area_wrong;
	}
	private String handleBackstageRemoteUrl(String url,HashMap<String,String> params) {
		return HttpClientUtil.postUrl(url, params);
	}
	
}
