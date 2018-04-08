package com.ami.mail;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ami.common.TimeUtil;
import com.ami.mail.pojo.AllResourceVO;
import com.ami.mail.service.AllResourceService;
import com.ami.weixin.course.schedule.Task;
import com.mysql.fabric.xmlrpc.base.Array;
import com.nf.utils.MailUtils;
import com.nf.utils.MailUtils.MailAttachmentWrapper;

/**
 * 发送邮件主类
 * @author JavaServer
 *
 */
@Service
public class ScheduleSendMail extends Task{
	private final static Logger logger = Logger.getLogger(ScheduleSendMail.class);
	
	@Autowired
	private  AllResourceService allResourceService;
	
	@Override
	public void execute() {
		try {
			
			SendMail sendMail = new SendMail();
			//设置数据源
			List<AllResourceVO> allResource= allResourceService.getAllResource();
			if(allResource != null && allResource.size()>0){
				for(AllResourceVO resource:allResource){
					sendMail.addSource(resource);
				}
			}
			//设置接收人员
			sendMail.setReceiveMailAccounts(allResourceService.getReceivePeople());
			sendMail.send();
			logger.info(TimeUtil.format(new Date())+"---------------------------分界线------------------------------------------------------------------------------------------------------------");
			sendMail.getReceiveMailAccounts().clear();
			allResourceService.getAllResource().clear();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		try {
			Set<String> ids = new HashSet<>();
			ids.add("eric.dong@y2tek.com");
			File f = new File("C:\\Users\\Dong Ruipeng\\Desktop\\gold排名.xlsx");
			byte[] bytes = new byte[(int)f.length()];
			new FileInputStream(f).read(bytes);
			MailAttachmentWrapper maw = new MailAttachmentWrapper("data.xlsx", MailUtils.MAIL_ATTACHMENT_TYPE_EXCEL, bytes);
			List<MailAttachmentWrapper> l = new ArrayList<>();
			l.add(maw);
			MailUtils.sendSmtpMail(MailUtils.sys_mail_sender_account_server, MailUtils.sys_mail_sender_account, "系统监控", "登录服报警", "NOT working", ids, l);
			logger.info(TimeUtil.format(new Date())
					+ "---------------------------分界线------------------------------------------------------------------------------------------------------------");
//			allResourceService.getAllResource().clear();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
