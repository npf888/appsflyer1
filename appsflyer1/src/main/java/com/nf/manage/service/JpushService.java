package com.nf.manage.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ami.api.common.AppConstant;
import com.ami.common.HttpClientUtil;
import com.google.gson.Gson;
import com.nf.manage.consts.JpushConst;
import com.nf.manage.dao.bean.JpushConfig;
import com.nf.manage.dao.bean.JpushConfigExample;
import com.nf.manage.dao.bean.JpushData;
import com.nf.manage.dao.bean.JpushDataExample;
import com.nf.manage.dao.mapper.JpushConfigMapper;
import com.nf.manage.dao.mapper.JpushDataMapper;

@Service
public class JpushService {
	
	private static final Logger logger = Logger.getLogger(JpushService.class);
	
	@Resource
	private JpushConfigMapper jpushConfigMapper;
	@Resource
	private JpushDataMapper jpushDataMapper;
	
	private Timer t;
	private String refreshTime = "16:00:00";
	
	@PostConstruct
	public void restartTimer() {
		System.out.println("#####"+this.toString());
		if(t!=null)
		{
			t.cancel();
		}
		JpushDataExample jde = new JpushDataExample();
		jde.createCriteria().andStateEqualTo(0);
		long c = jpushDataMapper.countByExample(jde);
		logger.info(">>>jpush has data to be push: "+c);
		if(c==0)
		{
			List<JpushConfig> l = jpushConfigMapper.selectAll();
			for(JpushConfig jc : l)
			{
				List<JpushData> jds = filterForPush(jc);
				for(JpushData jd : jds)
				{
					jpushDataMapper.insert(jd);
				}
			}
		}
		
		t = new Timer();
		Calendar calendar = new GregorianCalendar();
		long now = calendar.getTimeInMillis();
		
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		long todayStartTs = calendar.getTimeInMillis();
		
		String[] xxx = refreshTime.split(":");
		calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(xxx[0]));
		calendar.set(Calendar.MINUTE, Integer.parseInt(xxx[1]));
		calendar.set(Calendar.SECOND, Integer.parseInt(xxx[2]));
		calendar.set(Calendar.MILLISECOND, 0);
		long todayRefreshTs = calendar.getTimeInMillis();
		long delay = 0;
		if(now<todayRefreshTs)
		{
			delay = todayRefreshTs-now;
		}
		else
		{
			delay = todayStartTs+24*3600*1000-now;
		}
		t.schedule(new TimerTask() {
			@Override
			public void run() {
				t.cancel();
				logger.info(">>>jpush will clean and generate new data");
				try
				{
					jpushDataMapper.deleteAll();
				}
				catch(Exception e)
				{
					logger.error("", e);
				}
				restartTimer();
			}
		}, delay);
		
		List<JpushConfig> l = jpushConfigMapper.selectAll();
		for(JpushConfig jc : l)
		{
			String[] time = jc.getSendTime().split(":");
			calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(time[0]));
			calendar.set(Calendar.MINUTE, Integer.parseInt(time[1]));
			calendar.set(Calendar.SECOND, Integer.parseInt(time[2]));
			calendar.set(Calendar.MILLISECOND, 0);
			long sendTs = calendar.getTimeInMillis();
			
			long dd = 0;
			if(now<sendTs)
			{
				dd = sendTs-now;
			}
			else
			{
				dd = sendTs+24*3600*1000-now;
			}
			t.schedule(new TimerTask() {
				@Override
				public void run() {
					try {
						int configId = jc.getId();
						JpushConfigExample jce = new JpushConfigExample();
						jce.createCriteria().andIdEqualTo(configId);
						List<JpushConfig> configs = jpushConfigMapper.selectByExample(jce);
						if(configs==null || configs.size()==0)
						{
							logger.info(">>>jpush will ignore configId: "+configId+", filterParams: "+jc.getFilterParams());
							return;
						}
						JpushConfig jc = configs.get(0);
						if(StringUtils.isEmpty(jc.getFilterParams()))
						{
							logger.info(">>>jpush will ignore configId: "+configId+", filterParams: "+jc.getFilterParams());
							return;
						}
						List<Long> l = jpushDataMapper.selectHighestPushByConfigId(configId);
						logger.info("will push to: "+l+", configId: "+configId);
						JpushContent c = new JpushContent();
						c.id = jc.getId();
						c.title = jc.getTitle();
						c.content = jc.getContent();
						c.passportIds = l;
						Gson g = new Gson();
						sendMessage(g.toJson(c));
						JpushData jd = new JpushData();
						jd.setState(1);
						JpushDataExample jde = new JpushDataExample();
						jde.createCriteria().andJpushConfigIdEqualTo(configId);
						jpushDataMapper.updateByExampleSelective(jd, jde);
					} catch (Exception e) {
						logger.error("", e);
					}
				}
			}, dd);
		}
	}
	
	
	public void sendMessage(String jsonStr) {
		HashMap<String,String> param = new HashMap<String,String>();
		param.put("json", jsonStr);
		HttpClientUtil.postUrl(AppConstant.SERVER_URL_HTTP+"/api/jpushServlet", param);
		
	}
	
	
	
	public List<JpushConfig> getAllConfig()
	{
		return jpushConfigMapper.selectAll();
	}
	public JpushConfig getConfigById(int pushId)
	{
		return jpushConfigMapper.selectByPrimaryKey(pushId);
	}
	public void pushNow(int pushId)
	{
		JpushConfig jc = jpushConfigMapper.selectByPrimaryKey(pushId);
		JpushContent c = new JpushContent();
		c.id = jc.getId();
		c.title = jc.getTitle();
		c.content = jc.getContent();
		if(jc.getJpushType()==JpushConst.Condition_type_all)
		{
			c.isTooAll = true;
		}
		else
		{
			for(JpushData jd : filterForPush(jc))
			{
				c.passportIds.add(jd.getPassportId());
			}
		}
		
		Gson g = new Gson();
		sendMessage(g.toJson(c));
	}
	
	public List<JpushData> filterForPush(JpushConfig jc)
	{
		List<JpushData> l = new ArrayList<>();
		if(StringUtils.isEmpty(jc.getFilterParams()))
		{
			return l;
		}
		List<Long> passporids = null;
		switch(jc.getJpushType())
		{
		case JpushConst.Condition_type_club_gift:
		{
			passporids = jpushConfigMapper.selectPlayerHasClubGift();
			break;
		}
		case JpushConst.Condition_type_friend_request:
		{
			passporids = jpushConfigMapper.selectFriendRequest();
			break;
		}
		case JpushConst.Condition_type_gold_percent:
		{
			String p = jc.getFilterParams();
			double[] x = getMinAndMaxDouble(p);
			passporids = jpushConfigMapper.selectPlayerByGoldPercent(x[0], x[1]);
			break;
		}
		case JpushConst.Condition_type_level:
		{
			String p = jc.getFilterParams();
			int[] x = getMinAndMax(p);
			passporids = jpushConfigMapper.selectPassportIdsBylevelRange(x[0], x[1]);
			break;
		}
		case JpushConst.Condition_type_logon_days:
		{
			break;
		}
		case JpushConst.Condition_type_mail_has_reward:
		{
			passporids = jpushConfigMapper.selectPlayerWithRewardMail();
			break;
		}
		case JpushConst.Condition_type_need_sign:
		{
			String p = jc.getFilterParams();
			int[] x = getMinAndMax(p);
//			GregorianCalendar gc = new GregorianCalendar();
//			gc.set(Calendar.HOUR_OF_DAY, 0);
//			gc.set(Calendar.MINUTE, 0);
//			gc.set(Calendar.SECOND, 0);
//			gc.set(Calendar.MILLISECOND, 0);
//			long todayStartTs = gc.getTimeInMillis();
			long now = System.currentTimeMillis();
			long max = now-24L*3600*1000*(x[0]);
			long min = now-24L*3600*1000*x[1];
			passporids = jpushConfigMapper.selectLastSignTsBetween(min, max);
			break;
		}
		case JpushConst.Condition_type_not_logon_days:
		{
			String p = jc.getFilterParams();
			int[] x = getMinAndMax(p);
			if(x[0]<=0)
			{
				x[0] = 1;
			}
			GregorianCalendar gc = new GregorianCalendar();
			gc.set(Calendar.HOUR_OF_DAY, 0);
			gc.set(Calendar.MINUTE, 0);
			gc.set(Calendar.SECOND, 0);
			gc.set(Calendar.MILLISECOND, 0);
			long todayStartTs = gc.getTimeInMillis();
			long max = todayStartTs-24L*3600*1000*(x[0]-1);
			long min = todayStartTs-24L*3600*1000*x[1];
			passporids = jpushConfigMapper.selectPlayerByOffLineTime(min, max);
			break;
		}
		case JpushConst.Condition_type_online_reward:
		{
			passporids = new ArrayList<>();
			for(int i=1; i<=5; i++)
			{
				List<Long> x = jpushConfigMapper.selectPlayerByOnlineReward(i);
				passporids.addAll(x);
			}
			break;
		}
		case JpushConst.Condition_type_recharge_times:
		{
			String p = jc.getFilterParams();
			int[] x = getMinAndMax(p);
			passporids = jpushConfigMapper.selectPlayerByChargeTimes(x[0], x[1]);
			break;
		}
		case JpushConst.Condition_type_open_not_recharge_times:
		{
			String p = jc.getFilterParams();
			int[] x = getMinAndMax(p);
			passporids = jpushConfigMapper.selectPlayerByOrderNotChargeTimes(x[0], x[1]);
			break;
		}
		case JpushConst.Condition_type_update_in_24hours:
		{
			break;
		}
		case JpushConst.Condition_type_version:
		{
			break;
		}
//		case JpushConst.:
//		{
//			break;
//		}
//		case JpushConst.Condition_type_update_in_24hours:
//		{
//			break;
//		}
//		case JpushConst.Condition_type_update_in_24hours:
//		{
//			break;
//		}
//		case JpushConst.Condition_type_update_in_24hours:
//		{
//			break;
//		}
//		case JpushConst.Condition_type_update_in_24hours:
//		{
//			break;
//		}
//		case JpushConst.Condition_type_update_in_24hours:
//		{
//			break;
//		}
		}
		long now = System.currentTimeMillis();
		if(passporids != null){
			HashSet<Long> ids = new HashSet<>(passporids);
			for(Long id : ids)
			{
				JpushData jd = new JpushData();
				jd.setJpushConfigId(jc.getId());
				jd.setPassportId(id);
				jd.setState(0);
				jd.setTs(now);
				l.add(jd);
			}
		}
		return l;
	}
	
	public int[] getMinAndMax(String filter)
	{
		int min = Integer.MIN_VALUE;
		int max = Integer.MAX_VALUE;
		if(filter.contains(","))
		{
			String[] ss = filter.split(",");
			for(String s : ss)
			{
				if(s.startsWith("<"))
				{
					max = Integer.parseInt(s.substring(1).trim());
				}
				else if(s.startsWith(">"))
				{
					min = Integer.parseInt(s.substring(1).trim());
				}
			}
			return new int[] {min, max};
		}
		else if(filter.startsWith("="))
		{
			int m = Integer.parseInt(filter.substring(1).trim());
			return new int[] {m-1, m+1};
		}
		else if(filter.startsWith("<"))
		{
			max = Integer.parseInt(filter.substring(1).trim());
			return new int[] {min, max};
		}
		else if(filter.startsWith(">"))
		{
			min = Integer.parseInt(filter.substring(1).trim());
			return new int[] {min, max};
		}
		return null;
	}
	
	public double[] getMinAndMaxDouble(String filter)
	{
		double min = Double.MIN_VALUE;
		double max = Double.MAX_VALUE;
		if(filter.contains(","))
		{
			String[] ss = filter.split(",");
			for(String s : ss)
			{
				if(s.startsWith("<"))
				{
					max = Double.parseDouble(s.substring(1).trim());
				}
				else if(s.startsWith(">"))
				{
					min = Double.parseDouble(s.substring(1).trim());
				}
			}
			return new double[] {min, max};
		}
		
		else if(filter.startsWith("<"))
		{
			max = Double.parseDouble(filter.substring(1).trim());
			return new double[] {min, max};
		}
		else if(filter.startsWith(">"))
		{
			min = Double.parseDouble(filter.substring(1).trim());
			return new double[] {min, max};
		}
		return null;
	}
	public void updateConfig(JpushConfig jc) {
		jpushConfigMapper.updateByPrimaryKey(jc);
		restartTimer();
	}
}