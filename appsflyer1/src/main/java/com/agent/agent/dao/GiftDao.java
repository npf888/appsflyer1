package com.agent.agent.dao;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.ami.api.common.Pager;
import com.ami.api.exception.APIException;
import com.ami.api.web.dao.BaseDao;
import com.ami.mail.pojo.MailInfo;

@Repository
public class GiftDao extends BaseDao{

	public Pager allSendGift(Pager pager,Long sendId) {
		String sql = " select "
				+ " id,"
				+ " charId,"
				+ " sendId,"
				+ " sendName,"
				+ " recId,"
				+ " recName,"
				+ " title,"
				+ " content,"
				+ " mailType,"
				+ " mailStatus,"
				+ " hasAttachment,"
				+ " attachmentPack,"
				+ " if(createTime=0, '2000-12-12 00:00:00' , FROM_UNIXTIME(createTime/1000,'%Y-%m-%d %H:%i:%s')) as createTime,"
				+ " if(updateTime=0, '2000-12-12 00:00:00' , FROM_UNIXTIME(updateTime/1000,'%Y-%m-%d %H:%i:%s')) as updateTime,"
				+ " deleted,"
				+ " head"
				+ " from texas.t_mail_info where mailType=4 and charId=?";
		try {
			return this.db.queryPage(sql, new Object[]{sendId}, pager, MailInfo.class);
		} catch (APIException e) {
			e.printStackTrace();
		}
		pager.setItems(new ArrayList<MailInfo>());
		return pager;
	}

	public Pager allReceiveGift(Pager pager,Long recId) {
		String sql = " select "
				+ " id,"
				+ " charId,"
				+ " sendId,"
				+ " sendName,"
				+ " recId,"
				+ " recName,"
				+ " title,"
				+ " content,"
				+ " mailType,"
				+ " mailStatus,"
				+ " hasAttachment,"
				+ " attachmentPack,"
				+ " if(createTime=0, '2000-12-12 00:00:00' , FROM_UNIXTIME(createTime/1000,'%Y-%m-%d %H:%i:%s')) as createTime,"
				+ " if(updateTime=0, '2000-12-12 00:00:00' , FROM_UNIXTIME(updateTime/1000,'%Y-%m-%d %H:%i:%s')) as updateTime,"
				+ " deleted,"
				+ " head"
				+ " from texas.t_mail_info where mailType=4 and recId=?";
		try {
			return this.db.queryPage(sql, new Object[]{recId}, pager, MailInfo.class);
		} catch (APIException e) {
			e.printStackTrace();
		}
		pager.setItems(new ArrayList<MailInfo>());
		return pager;
	}

}
