package com.ami.bazoo.dao;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.ami.api.common.Pager;
import com.ami.api.web.dao.BaseMysqlDao;
import com.ami.bazoo.pojo.BazooGoldVO;
import com.ami.bazoo.pojo.BazooParam;
import com.ami.bazoo.util.DeviceUtil;

@Component
public class BazooGoldDao extends BaseMysqlDao{
	 private static Logger logger = Logger.getLogger(BazooGoldDao.class);


	 String ss = " select t1.id,"
				 + " t1.log_type as logType,"
				 + " if(t1.log_time=0, '-' , FROM_UNIXTIME(t1.log_time/1000,'%Y-%m-%d %H:%i:%s')) as  logTime,"
				 + " t1.region_id as regionId,"
				 + " t1.server_id as serverId,"
				 + " t1.account_id as accountId,"
				 + " t1.account_name as accountName,"
				 + " t1.level,"
				 + " t1.reason,"
				 + " t1.param,"
				 + " t1.gold_delta as goldDelta,"
				 + " t1.gold_left as goldLeft,"
				 + " if(t1.createTime=0, '-' , FROM_UNIXTIME(t1.createTime/1000,'%Y-%m-%d %H:%i:%s')) as createTime,"
				 + " t2.deviceMac";
	 
	 
	public Pager bazooGold(Pager pager, BazooParam param) {
		String date = param.getDate();
		StringBuilder sb = new StringBuilder(ss);
		sb.append(" from texas_log.gold_log_"+date+" t1 ");
		sb.append(" left join texas.t_user_info t2 on t1.account_id=t2.id");
		sb.append(" where 1=1 ");
		String accountId = param.getAccountId();
		if(StringUtils.isNotBlank(accountId)){
			sb.append(" and t1.account_id =  "+accountId);
		}
		String roomNumber = param.getRoomNumber();
		if(StringUtils.isNotBlank(roomNumber)){
			sb.append(" and t1.param like  '%"+roomNumber+"%'");
		}
		int reason = param.getReason();
		if(reason>0){
			sb.append(" and t1.reason =  "+reason);
		}
		try{
			pager = this.db.queryPage(sb.toString(), pager, BazooGoldVO.class);
			List<BazooGoldVO> BazooGoldVOList = pager.getItems();
			for(BazooGoldVO vo:BazooGoldVOList){
				vo.setDeviceMac(DeviceUtil.changeDeviceMac(vo.getDeviceMac()));
			}
		}catch(Exception e){
			logger.error("查询金币日志失败：",e);
		}
		return pager;
	}


}
