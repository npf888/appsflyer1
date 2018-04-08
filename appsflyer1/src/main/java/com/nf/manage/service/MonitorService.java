package com.nf.manage.service;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.nf.manage.dao.bean.MailSubscribeConfig;
import com.nf.manage.dao.bean.MailSubscribeConfigExample;
import com.nf.manage.dao.bean.ServerConfig;
import com.nf.manage.dao.mapper.MailSubscribeConfigMapper;
import com.nf.manage.dao.mapper.ServerConfigMapper;
import com.nf.utils.MailUtils;

@Service
public class MonitorService {
	
	private static final Logger logger = Logger.getLogger(MonitorService.class);
	
	@Resource
	private ServerConfigMapper serverConfigMapper;
	@Resource
	private MailSubscribeConfigMapper mailSubscribeConfigMapper;
	
	@PostConstruct
	public void doMonitor() {
		probeLoginServer();
	}

	private void probeLoginServer() {
		TimerTask tt = new TimerTask() {
			@Override
			public void run() {
				List<ServerConfig> l = serverConfigMapper.selectAllLoginServer();
				CloseableHttpResponse httpResponse = null;
				CloseableHttpClient closeHttpClient = null;
				// 创建HttpClient对象
				closeHttpClient = HttpClients.createDefault();
				for (ServerConfig sc : l) {
					boolean needNotify = false;
					StringBuilder errorStack = new StringBuilder();
					long x = 0;
						try {
							// 发送Post请求
							HttpPost httpPost = new HttpPost("http://"+sc.getIp()+":"+sc.getPort()+"/api/account/visitorLogin");
							httpPost.addHeader(HTTP.CONTENT_TYPE, "text/html");
							// 设置Post参数
							List<NameValuePair> params = new ArrayList<>();
							params.add(new BasicNameValuePair("deviceMac", "monitor only"));
							params.add(new BasicNameValuePair("deviceId", "monitor only"));
							params.add(new BasicNameValuePair("macAddress", "monitor only"));
							params.add(new BasicNameValuePair("androidId", "monitor only"));
							// 转换参数并设置编码格式
							httpPost.setEntity(new UrlEncodedFormEntity(params, Consts.UTF_8));

							long start = System.currentTimeMillis();
							// 执行Post请求 得到Response对象
							httpResponse = closeHttpClient.execute(httpPost);
							// httpResponse.getStatusLine() 响应头信息
							logger.info(httpResponse.getStatusLine());
							
							HttpEntity httpEntity = httpResponse.getEntity();
							if (httpEntity != null) {
								// 响应输入流
								InputStream is = httpEntity.getContent();
								// 转换为字符输入流
								StringBuilder sb = new StringBuilder();
								@SuppressWarnings("resource")
								Scanner scanner = new Scanner(new InputStreamReader(is, Consts.UTF_8));
								while(scanner.hasNextLine())
								{
									sb.append(scanner.nextLine());
								}
								logger.info("motinor try to log in: "+sb.toString());
								// 关闭输入流
								is.close();
							}
							x = System.currentTimeMillis()-start;
							if(x>60*1000)
							{
								needNotify = true;
							}
					}
					catch (Exception e) {
						logger.error("", e);
						
						errorStack.append(e.toString());
						errorStack.append(MailUtils.lineEnd);
						for(StackTraceElement ste : e.getStackTrace())
						{
							errorStack.append(ste.toString());
							errorStack.append(MailUtils.lineEnd);
						}
						needNotify = true;
					}
					
					if(needNotify)
					{
						try {
							MailSubscribeConfigExample mde = new MailSubscribeConfigExample();
							mde.createCriteria().andSubcribeIdEqualTo(1);
							List<MailSubscribeConfig> subscribers = mailSubscribeConfigMapper.selectByExample(mde);
							HashSet<String> s = new HashSet<>();
							for (MailSubscribeConfig mc : subscribers) {
								s.add(mc.getMailAddress());
							}
							StringBuilder content = new StringBuilder();
							content.append("monitor address: " + InetAddress.getLocalHost().getHostAddress());
							content.append("登录服耗时：" + x);
							content.append(MailUtils.lineEnd);
							content.append(MailUtils.lineEnd);
							if (errorStack != null && errorStack.length() > 0) {
								content.append(MailUtils.lineEnd);
								content.append(errorStack);
							}

							MailUtils.sendSmtpMail(MailUtils.sys_mail_sender_account_server,
									MailUtils.sys_mail_sender_account, "监控系统", sc.getDesc() + "报警", content.toString(),
									s, null);
						} catch (Exception e) {
							logger.error("", e);
						}
					}
				}
			}
		};
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(tt, 0L, 2 * 60 * 1000L);

	}
}