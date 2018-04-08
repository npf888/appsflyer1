package com.agent.agent.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.agent.agent.pojo.HumanInfo;
import com.ami.api.exception.APIException;
import com.ami.api.web.dao.BaseDao;

@Repository
public class BazooPlayerDao extends BaseDao{

	private static Logger logger = Logger.getLogger(BazooPlayerDao.class);
	String sqlHumanInfo = " select id,passportId,img,name,girlFlag,level,diamond,gold,coupon,charm,curExp,sceneId,lastLoginIp,lastLoginTime,lastLogoutTime,totalMinute,onlineStatus,idleTime,createTime,isPay,gameview,newguide,addfriendIds,receivecode,watchNum,countries,age,slotRotate,slotWin,slotSingleWin,slotWinNum,slotRoomId,slotId,friendId,snginfo,requestFriendIds,newGuyGift,couponExtraChip,couponDurationDate,regularTime,todayView,clubId,clubSignTs,clubDonateTs,doubleExpEndTime,watchTime,clientVersion,bazooRoom,bazooGold ";

	public HumanInfo getHumanInfoByPassportId(long passportId) {
		StringBuilder sqlBuilder = new StringBuilder(sqlHumanInfo);
		sqlBuilder.append(" from texas.t_human_info where passportId="+passportId);
		try {
			List<HumanInfo> humanInfoList = this.db.query(sqlBuilder.toString(), HumanInfo.class);
			if(humanInfoList != null && humanInfoList.size()>0){
				return humanInfoList.get(0);
			}
		} catch (APIException e) {
			logger.error("查询getHumanInfoByPassportId的信息失败",e);
		}
		return null;
	}

}
