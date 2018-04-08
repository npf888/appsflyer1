package com.agent.agent.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.agent.agent.pojo.BazooAgent;
import com.agent.agent.pojo.HumanInfo;
import com.agent.agent.util.MD5Utils;
import com.ami.api.common.AppConstant;
import com.ami.api.common.Pager;
import com.ami.api.exception.APIException;
import com.ami.api.web.dao.BaseDao;
import com.ami.common.HttpClientUtil;

@Repository
public class AgentDao extends BaseDao{
	private static Logger logger = Logger.getLogger(AgentDao.class);
	String sqlAll = " select t1.id,t1.passportId,t1.nickname,t1.username,t1.passwords,t1.telephone,t1.identity,t1.address,t1.wx,t1.qq,t1.parentId,t1.state,t1.sendPackage,t2.gold,t1.createTime,t2.bazooAgentDisplay ";
	String sql = " select t1.id,t1.passportId,t1.nickname,t1.username,t1.passwords,t1.telephone,t1.identity,t1.address,t1.wx,t1.qq,t1.parentId,t1.state,t1.sendPackage,t2.gold,t1.createTime ";
	String sqlAgent = " select t1.id,t1.passportId,t1.nickname,t1.username,t1.passwords,t1.telephone,t1.identity,t1.address,t1.wx,t1.qq,t2.passportId as parentId,t1.state,t1.sendPackage,t1.createTime ";
	String sqlHumanInfo = " select id,passportId,img,name,girlFlag,level,diamond,gold,coupon,charm,curExp,sceneId,lastLoginIp,lastLoginTime,lastLogoutTime,totalMinute,onlineStatus,idleTime,createTime,isPay,gameview,newguide,addfriendIds,receivecode,watchNum,countries,age,slotRotate,slotWin,slotSingleWin,slotWinNum,slotRoomId,slotId,friendId,snginfo,requestFriendIds,newGuyGift,couponExtraChip,couponDurationDate,regularTime,todayView,clubId,clubSignTs,clubDonateTs,doubleExpEndTime,watchTime,clientVersion,bazooRoom,bazooGold ";

	public BazooAgent  getUserByNameAndPassword(String username,String password){
		
		try {
			StringBuilder sqlBuilder = new StringBuilder(sql);
			String md5Password = MD5Utils.getMD5(password);
			sqlBuilder.append(" from sys_admin_texas.bazoo_agent t1 left join texas.t_human_info t2 on t1.passportId=t2.passportId where t1.username = '"+username+"' and t1.passwords='"+md5Password+"'");
			List<BazooAgent> bazooAgents = this.db.query(sqlBuilder.toString(), BazooAgent.class);
			if(bazooAgents != null && bazooAgents.size() >0){
				return bazooAgents.get(0);
			}
		} catch (APIException e) {
			logger.error("�������û���¼��ѯʧ��",e);
		}
		
		return null;
	}

	public List<BazooAgent> getAgentByID(String id) {
		try {
			StringBuilder sqlBuilder = new StringBuilder(sql);
			sqlBuilder.append(" from sys_admin_texas.bazoo_agent t1 left join texas.t_human_info t2 on t1.passportId=t2.passportId where t1.parentId = "+id);
			List<BazooAgent> bazooAgents = this.db.query(sqlBuilder.toString(), BazooAgent.class);
			return bazooAgents;
		} catch (APIException e) {
			logger.error("�������û���¼��ѯʧ��",e);
		}
		
		return null;
	}


	public void saveSunAgent(Map<String,Object> bazooAgentMap) {
		try {
			this.table.insert("sys_admin_texas.bazoo_agent", bazooAgentMap);
		} catch (APIException e) {
			logger.error("����Ӵ�����ʧ��",e);
		}
	}

	public HumanInfo getAgentUserBySearchColumn(String searchColumn) {
		StringBuilder sqlBuilder = new StringBuilder(sqlHumanInfo);
		sqlBuilder.append(" from texas.t_human_info where passportId="+searchColumn+" or name='"+searchColumn+"'");
		try {
			List<HumanInfo> humanInfoList = this.db.query(sqlBuilder.toString(), HumanInfo.class);
			if(humanInfoList != null && humanInfoList.size()>0){
				return humanInfoList.get(0);
			}
		} catch (APIException e) {
			logger.error("��ѯt_huamn_info����Ϣʧ��",e);
		}
		return null;
	}
	public List<HumanInfo> getHumanInfoBySearchColumn(String searchColumn) {
		StringBuilder sqlBuilder = new StringBuilder(sqlHumanInfo);
		sqlBuilder.append(" from texas.t_human_info where passportId="+searchColumn+" or name='"+searchColumn+"'");
		try {
			List<HumanInfo> humanInfoList = this.db.query(sqlBuilder.toString(), HumanInfo.class);
			if(humanInfoList != null && humanInfoList.size()>0){
				return humanInfoList;
			}
		} catch (APIException e) {
			logger.error("��ѯt_huamn_info����Ϣʧ��",e);
		}
		return null;
	}

	public boolean judgeDouble(long passportId) {
		StringBuilder sqlBuilder = new StringBuilder(sql);
		sqlBuilder.append(" from sys_admin_texas.bazoo_agent t1 left join texas.t_human_info t2 on t1.passportId=t2.passportId where t1.passportId = "+passportId);
		try {
			List<BazooAgent> bazooAgentList = this.db.query(sqlBuilder.toString(), BazooAgent.class);
			if(bazooAgentList != null && bazooAgentList.size()>0){
				return true;
			}
		} catch (APIException e) {
			logger.error("�ж�judgeDouble����Ϣʧ��",e);
		}
		return false;
	}

	public void toAddGold(long costPassportId, long givePassportId,long giveGold) {
		StringBuilder sqlBuilder = new StringBuilder(sqlHumanInfo);
		sqlBuilder.append(" from texas.t_human_info where passportId="+costPassportId);
		try {
			List<HumanInfo> humanInfoList = this.db.query(sqlBuilder.toString(), HumanInfo.class);
			if(humanInfoList != null && humanInfoList.size()>0){
				HumanInfo humanInfo = humanInfoList.get(0);
				if(giveGold < humanInfo.getGold()){
					String url = AppConstant.SERVER_URL_HTTP+"/api/addAndCostGold";
					HashMap<String,String> params = new HashMap<String,String>();
					params.put("costPassportId", String.valueOf(costPassportId));
					params.put("givePassportId", String.valueOf(givePassportId));
					params.put("gold", String.valueOf(giveGold));
					String result = HttpClientUtil.postUrl(url, params);
				}
			}
		} catch (APIException e) {
			logger.error("��ѯt_huamn_info����Ϣʧ��",e);
		}
		
	}
	public void sendRedPackage(long costPassportId, long givePassportId,long number,long redPackageValue) {
		StringBuilder sqlBuilder = new StringBuilder(sqlHumanInfo);
		sqlBuilder.append(" from texas.t_human_info where passportId="+costPassportId);
		try {
			List<HumanInfo> humanInfoList = this.db.query(sqlBuilder.toString(), HumanInfo.class);
			if(humanInfoList != null && humanInfoList.size()>0){
				HumanInfo humanInfo = humanInfoList.get(0);
				if(number*redPackageValue < humanInfo.getGold()){
					String url = AppConstant.SERVER_URL_HTTP+"/api/noticeRedPackage";
					HashMap<String,String> params = new HashMap<String,String>();
					params.put("costPassportId", String.valueOf(costPassportId));
					params.put("givePassportId", String.valueOf(givePassportId));
					params.put("number", String.valueOf(number));
					String result = HttpClientUtil.postUrl(url, params);
				}
			}
		} catch (APIException e) {
			logger.error("��ѯt_huamn_info����Ϣʧ��",e);
		}
		
	}

	public List<BazooAgent> getAgentSelfUser(String passportId) {
		try {
			StringBuilder sqlBuilder = new StringBuilder(sql);
			sqlBuilder.append(" from sys_admin_texas.bazoo_agent t1 left join texas.t_human_info t2 on t1.passportId=t2.passportId where t1.passportId = "+passportId);
			List<BazooAgent> bazooAgents = this.db.query(sqlBuilder.toString(), BazooAgent.class);
			return bazooAgents;
		} catch (APIException e) {
			logger.error("�������û���¼��ѯʧ��",e);
		}
		
		return null;
	}

	public Pager getAgentAllUser(Pager pager, String searchColumn) {
		try {
			StringBuilder sql1 = new StringBuilder();
			StringBuilder sql2 = new StringBuilder(sqlAgent);
			sql1.append("("+sql2);
			sql1.append(" from sys_admin_texas.bazoo_agent t1 left join sys_admin_texas.bazoo_agent t2 on t1.parentId=t2.id");
			sql1.append(")");
			StringBuilder sqlBuilder = new StringBuilder(sqlAll);
			sqlBuilder.append(" from");
			sqlBuilder.append(sql1);
			sqlBuilder.append(" t1 left join texas.t_human_info t2 on t1.passportId=t2.passportId where 1=1 ");
			if(StringUtils.isNotBlank(searchColumn)){
				sqlBuilder.append(" and (t1.passportId like '%"+searchColumn+"%' "
								+ " or t1.nickname like '%"+searchColumn+"%'"
								+ " or t1.username like '%"+searchColumn+"%')");
			}
			
			
			Pager page = this.db.queryPage(sqlBuilder.toString(),pager, BazooAgent.class);
			return page;
		} catch (APIException e) {
			logger.error("�������û���¼��ѯʧ��",e);
		}
		
		return null;
	}

	public void editSunAgent(Map<String, Object> bazooAgentMap,String id) {
		try {
			this.table.update("sys_admin_texas.bazoo_agent", bazooAgentMap,Long.valueOf(id));
		} catch (APIException e) {
			logger.error("���� �༭ ���� ��Ϣʧ��",e);
		}
		
	}

	public void changeAgentAuthority(HttpServletRequest req) {
		String passportId = req.getParameter("passportId");
		String authority = req.getParameter("authority");
		HashMap<String,Object> params = new HashMap<String,Object>();
		params.put("sendPackage", authority);
		try {
			List<HashMap<String,Object>> bazooAgentList = this.db.query(" select id from sys_admin_texas.bazoo_agent where passportId="+passportId);
			Object id = bazooAgentList.get(0).get("id");
			this.table.update("sys_admin_texas.bazoo_agent", params,Long.valueOf(String.valueOf(id)));
		} catch (APIException e) {
			logger.error("���� �༭ ���� ��Ϣʧ��",e);
		}
		
	}

	public boolean getAuthority(long passportId) {
		try {
			List<HashMap<String,Object>> bazooAgentList = this.db.query(" select sendPackage from sys_admin_texas.bazoo_agent where passportId="+passportId);
			Object sendPackage = bazooAgentList.get(0).get("sendPackage");
			boolean send = (boolean)sendPackage;
			return !send;
		} catch (APIException e) {
			logger.error("���� �༭ ���� ��Ϣʧ��",e);
		}
		return false;
		
	}
	
	
	 
}
