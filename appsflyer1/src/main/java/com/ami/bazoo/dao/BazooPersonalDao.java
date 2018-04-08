package com.ami.bazoo.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.ami.api.common.Pager;
import com.ami.api.exception.APIException;
import com.ami.api.web.dao.BaseMysqlDao;
import com.ami.bazoo.pojo.BazooParam;
import com.ami.bazoo.pojo.BazooPersonalEveryDayVO;
import com.ami.bazoo.pojo.BazooPersonalVO;
import com.ami.bazoo.util.DeviceUtil;
import com.ami.common.ListToArrUtil;

@Repository
public class BazooPersonalDao extends BaseMysqlDao{
	private static Logger logger = Logger.getLogger(BazooPersonalDao.class);
	
	String ss = " select t1.id,"
			 + " t1.passportId,"
			 + " t1.modeType,"
			 + " t1.numberOfGame,"
			 + " t1.winTimes,"
			 + " t1.singleTopGold,"
			 + " t1.rateOfWinning,"
			 + " t1.aWinningStreak,"
			 + " t1.passToKill,"
			 + " t1.bigPatterns,"
			 + " t1.pantherNumber,"
			 + " t1.dayProfit,"
			 + " t1.weekProfit,"
			 + " t1.monthProfit,"
			 + " t2.deviceMac";
	
	public Pager bazooPersonal(Pager pager, BazooParam param) {
		List<Object> paras = new ArrayList<Object>();
		StringBuilder sb = new StringBuilder(ss);
		sb.append(" from texas.t_human_bazoo_personal t1 ");
		sb.append(" left join texas.t_user_info t2 on t1.passportId=t2.id");
		sb.append(" where 1=1 ");
		String accountId = param.getAccountId();
		if(StringUtils.isNotBlank(accountId)){
			sb.append(" and t1.passportId =  "+accountId);
		}
		try {
			pager = this.db.queryPage(sb.toString(), ListToArrUtil.getArrFromList(paras), pager, BazooPersonalVO.class);
			List<BazooPersonalVO> bazooPersonalVOList = pager.getItems();
			for(BazooPersonalVO vo:bazooPersonalVOList){
				vo.setDeviceMac(DeviceUtil.changeDeviceMac(vo.getDeviceMac()));
			}
		} catch (APIException e) {
			logger.error("查询个人的比赛信息失败：", e);
		}
		return pager;
	}

	public List<BazooPersonalEveryDayVO> bazooPersonalEveryDay(BazooParam param) {
		
		String date = param.getDate();
		String accountId = param.getAccountId();
		int reason = param.getReason();
		StringBuilder sb = new StringBuilder();
		sb.append(" select id,"
				+ " account_id as accountId,"
				+ " account_name as accountName,"
				+ " is_robot as isRobot,"
				+ " room_num as roomNum,"
				+ " mode_type as modeType,"
				+ " sum(win_or_lost) as winOrLost, "
				+ " count(win_or_lost) as totalNum ");
		sb.append(" from texas_log.dice_statistics_win_lost_log_"+date+" t1 ");
		sb.append(" where 1=1 ");
		if(!StringUtils.isEmpty(accountId)){
			sb.append(" and account_id="+accountId);
		}
		if(reason < 2){
			sb.append(" and is_robot="+reason);
		}
		sb.append(" GROUP BY account_id,is_robot,room_num");
		
		try {
			List<BazooPersonalEveryDayVO> everyDayVOList = this.db.query(sb.toString(), BazooPersonalEveryDayVO.class);
			return everyDayVOList;
		} catch (APIException e) {
			logger.error("每天的统计信息查询数据库错误", e);
		}
		
		return null;
	}

}
