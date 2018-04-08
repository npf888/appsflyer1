package com.ami.statistics.dao;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.ami.api.common.Pager;
import com.ami.api.exception.APIException;
import com.ami.api.web.dao.BaseMysqlDao;
import com.ami.statistics.pojo.OrderVO;
import com.ami.statistics.pojo.THumanInfoSimple2VO;
import com.ami.statistics.pojo.THumanInfoSimpleVO;

@Component
public class StatisticsDao extends BaseMysqlDao{

	private static Logger logger = Logger.getLogger(StatisticsDao.class);

	public List<THumanInfoSimpleVO> levelAndTotalTime(HttpServletRequest req) {
		/*String sql = " select id,passportId,img,name,girlFlag,level,diamond,gold,coupon "
				+ " ,charm,curExp,sceneId,lastLoginIp,lastLoginTime,lastLogoutTime,totalMinute "
                + " ,onlineStatus,idleTime,createTime,isPay,gameview,newguide "
                + " ,addfriendIds,receivecode,watchNum "
                + " from texas.t_human_info";*/
		//String sql = " select level,totalMinute from texas.t_human_info";
		String sql = " select level,count(id) num from texas.t_human_info group by level";
		try {
			List<THumanInfoSimpleVO> tHumanInfoVOS = this.db.query(sql, THumanInfoSimpleVO.class);
			return tHumanInfoVOS;
		} catch (APIException e) {
			e.printStackTrace();
			
		}
		
		return null;
	}
	/**
	 * 统计
	 * 玩家每日在线时长分布
	 * 30分钟以下
	 * 30-60分钟
	 * 60分钟-120分钟
	 * 120分钟以上	
	 * @author JavaServer
	 *
	 */
	public List<THumanInfoSimple2VO> totalTimeNum(HttpServletRequest req) {
		/*String sql = " select id,passportId,img,name,girlFlag,level,diamond,gold,coupon "
				+ " ,charm,curExp,sceneId,lastLoginIp,lastLoginTime,lastLogoutTime,totalMinute "
                + " ,onlineStatus,idleTime,createTime,isPay,gameview,newguide "
                + " ,addfriendIds,receivecode,watchNum "
                + " from texas.t_human_info";*/
		//String sql = " select level,totalMinute from texas.t_human_info";
		String sql1 = " (select  '00-30' as totalMinute ,count(id) num from texas.t_human_info where totalMinute <= 30)"
					+" union "
					+" (select  '30-60' as totalMinute,count(id) num from texas.t_human_info where totalMinute > 30 and totalMinute <= 60 )"
					+" union "
					+" (select  '60-120' as totalMinute,count(id) num from texas.t_human_info where totalMinute > 60 and totalMinute <= 120)"
					+" union "
					+" (select  '120-' as totalMinute  ,count(id) num from texas.t_human_info where totalMinute > 120)";
		try {
			List<THumanInfoSimple2VO> tHumanInfo2VOS1 = this.db.query(sql1, THumanInfoSimple2VO.class);
			return tHumanInfo2VOS1;
		} catch (APIException e) {
			e.printStackTrace();
			
		}
		return null;
	}
	public List<HashMap<String, Object>> queryTables(String preTableName) throws APIException {

		String sql = "select table_name from INFORMATION_SCHEMA.tables where table_schema = 'texas_log' and table_name like '"+preTableName+"%' order by table_name";
		
		return this.db.query(sql);
	}
	public List<HashMap<String, Object>> queryData(String sql) throws APIException {
		List<HashMap<String, Object>> list1 = this.db.query(sql);
		return list1;
	}
	public Pager order(Pager pager, String channelId,String charId,String name) {
		StringBuffer sql = new StringBuffer(" ");
		sql.append(" select distinct t1.channelId,"
					+" if(t1.createTime=0, '-' , FROM_UNIXTIME(t1.createTime/1000,'%Y-%m-%d %H:%i:%s')) createTime,"
					+" t3.name,"
					+" t3.passportId,"
					+" t1.charId,"
					+" t1.productId,"
					+" t1.cost,"
					+" t1.level,"
					+" t1.gold,"
					+" t2.langDesc "
					+" from texas.t_human_recharge_order t1 "
					+" LEFT JOIN texas.t_recharge t2  on t1.productId=t2.pid "
					+" LEFT JOIN texas.t_human_info t3  on t1.charId=t3.passportId "
					+" where t1.channelId = "+channelId+" "
							+ " and t1.orderStatus=1 ");
		if(!StringUtils.isEmpty(name)){
			sql.append(" and t3.name like '%"+name+"%' ");
			
		}
		if(!StringUtils.isEmpty(charId)){
			sql.append(" and t1.charId like '%"+charId+"%'");
				
		}
	
		sql.append(" order by t1.createTime desc");
		try {
			return this.db.queryPage(sql.toString(),pager, OrderVO.class);
		} catch (APIException e) {
			e.printStackTrace();
		}
		return null;
	}

}