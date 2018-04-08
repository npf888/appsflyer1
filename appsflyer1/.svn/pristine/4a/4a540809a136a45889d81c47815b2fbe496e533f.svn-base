package com.ami.weixin.course.service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ami.common.HttpClientUtil;
import com.ami.weixin.course.dao.CoreDao;
import com.ami.weixin.course.pojo.vo.IpNumVO;
import com.ami.weixin.course.pojo.vo.TOnlineTimeNumberVO;
import com.ami.weixin.course.util.Constant;
/**
 * 服务于 返回jsp页面的service
 * @author JavaServer
 *
 */
@Service
public class Simple2Service {
	private static Logger logger = Logger.getLogger(Simple2Service.class);
	
	@Autowired
	private WatchingService  watchingService;
	@Autowired
	private CoreDao  coreDao;
	/**
	 * 查询详情（模板消息的 的url访问的详情）
	 * @param req
	 * @return
	 */
	public List<IpNumVO> serviceDetail(HttpServletRequest req) {
		List<IpNumVO> gameServerList = getGameServerUrlMap(req);
		List<IpNumVO> backstageList = getbackstageUrlMap(req);
		List<IpNumVO> ipNumVOList = new ArrayList<IpNumVO>();
		ipNumVOList.addAll(gameServerList);
		ipNumVOList.addAll(backstageList);
		return ipNumVOList;
	}
	public List<IpNumVO> serverMaxDetail(HttpServletRequest req) {
		List<IpNumVO> gameServerList = getGameServerUrlMap(req);
		List<IpNumVO> ipNumVOList = new ArrayList<IpNumVO>();
		ipNumVOList.addAll(gameServerList);
		return ipNumVOList;
	}
	public List<IpNumVO> getbackstageUrlMap(HttpServletRequest req) {
		Map<String,String> backstageUrlMap = Constant.getbackstageUrlMap(req);
		List<IpNumVO> ipNumVOList = new ArrayList<IpNumVO>();
		for(Entry<String,String> entry: backstageUrlMap.entrySet()){
			String key = entry.getKey();
			String value = entry.getValue();
			try{
				String rStr = HttpClientUtil.get(value);
				JSONObject jb = (JSONObject)JSON.parse(rStr);
				String num = jb.getString("num");
				IpNumVO ipNumVO= new IpNumVO();
				ipNumVO.setIp(value);
				ipNumVO.setPlace("后台-"+key);
				ipNumVO.setNewNum(num);
				ipNumVO.setOnLineNum("-");
				ipNumVOList.add(ipNumVO);
			}catch(Exception e){
				try {
					logger.info("time machine area server:::"+new String(key.getBytes(),"UTF-8")+"--- url+port ::: "+value);
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
				continue;
			}
		}
		return ipNumVOList;
	}
	
	/**
	 * 
	 * @param req
	 * @return
	 */
	public List<IpNumVO> getGameServerUrlMap(HttpServletRequest req) {
		Map<String,String> gameServerUrlMap = Constant.getGameServerUrlMap(req);
		List<IpNumVO> ipNumVOList = new ArrayList<IpNumVO>();
		for(Entry<String,String> entry: gameServerUrlMap.entrySet()){
			String key = entry.getKey();
			String value = entry.getValue();
			try{
				HashMap<String,String> params = new HashMap<String,String>();
				params.put(Constant.active,Constant.view_all_online);
				String rStr = HttpClientUtil.postUrl(value,params);
				IpNumVO ipNumVO= new IpNumVO();
				ipNumVO.setIp(value);
				ipNumVO.setPlace("游戏-"+key);
				ipNumVO.setOnLineNum(rStr);
				ipNumVO.setNewNum("-");
				ipNumVOList.add(ipNumVO);
			}catch(Exception e){
				try {
					logger.info("time machine area server:::"+new String(key.getBytes(),"UTF-8")+"--- url+port ::: "+value);
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
				continue;
			}
		}
		
		return ipNumVOList;
	}
	
	public List<TOnlineTimeNumberVO> getTimeNumbers(HttpServletRequest req){
		String area = req.getParameter("area");
		String startTime = req.getParameter("startTime");
		String endTime = req.getParameter("endTime");
		return coreDao.getTimeNumber(area, startTime, endTime);
	}
	public List<HashMap<String,Object>> getAllAreas() {
		return coreDao.getAllAreas();
	}
	
}
