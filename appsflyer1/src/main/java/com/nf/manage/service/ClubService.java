package com.nf.manage.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ami.api.common.Pager;
import com.ami.texas.gamenew.action.ClubAction.QueryParams;
import com.google.gson.Gson;
import com.nf.manage.consts.RedisKey;
import com.nf.manage.service.beans.ClubData;
import com.nf.manage.service.beans.ClubMemberData;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

@Service
public class ClubService {
	@Autowired
	private ShardedJedisPool shardedPool;
	private Gson g = new Gson();;
	SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
	
	SimpleDateFormat day = new SimpleDateFormat("YYYY-MM-dd");
	
	@SuppressWarnings("unchecked")
	public void getAllClubData(Pager pager, QueryParams qp) throws ParseException
	{
		ShardedJedis sj = shardedPool.getResource();
		Map<String, String> map = sj.hgetAll(RedisKey.CLUB_INFO_HASH);
		Stream<ClubData> s = map.values().stream().map(e->g.fromJson(e, ClubData.class));
		if(StringUtils.isNotEmpty(qp.clubName))
		{
			s = s.filter(e->e.name.contains(qp.clubName));
		}
		if(StringUtils.isNotEmpty(qp.from))
		{
			Date d = day.parse(qp.from);
			s = s.filter(e->e.create_time.after(d));
		}
		if(StringUtils.isNotEmpty(qp.to))
		{
			Date d = day.parse(qp.to);
			s = s.filter(e->e.create_time.before(d));
		}
		if(StringUtils.isNotEmpty(qp.level) && !qp.level.equals("0"))
		{
			int x = Integer.parseInt(qp.level);
			int min = 0;
			int max = 0;
			switch(x)
			{
			case 1:
			{
				min = 1;
				max = 5;
				break;
			}
			case 2:
			{
				min = 6;
				max = 10;
				break;
			}
			case 3:
			{
				min = 11;
				max = 15;
				break;
			}
			case 4:
			{
				min = 16;
				max = 20;
				break;
			}
			case 5:
			{
				min = 21;
				max = 25;
				break;
			}
			}
			int fmin = min;
			int fmax = max;
			s = s.filter(e->e.level>=fmin && e.level<=fmax);
		}
		if(StringUtils.isNotEmpty(qp.money) && !qp.money.equals("0"))
		{
			int x = Integer.parseInt(qp.money);
			int min = 0;
			int max = 0;
			switch(x)
			{
			case 1:
			{
				min = 1;
				max = 5;
				break;
			}
			case 2:
			{
				min = 6;
				max = 10;
				break;
			}
			case 3:
			{
				min = 11;
				max = 15;
				break;
			}
			case 4:
			{
				min = 16;
				max = 20;
				break;
			}
			case 5:
			{
				min = 21;
				max = 25;
				break;
			}
			}
			int fmin = min;
			int fmax = max;
			s = s.filter(e->e.money>=fmin && e.money<=fmax);
		}
		if(StringUtils.isNotEmpty(qp.huoyue) && !qp.huoyue.equals("0"))
		{
			int x = Integer.parseInt(qp.huoyue);
			int min = 0;
			int max = 0;
			switch(x)
			{
			case 1:
			{
				min = 1;
				max = 5;
				break;
			}
			case 2:
			{
				min = 6;
				max = 10;
				break;
			}
			case 3:
			{
				min = 11;
				max = 15;
				break;
			}
			case 4:
			{
				min = 16;
				max = 20;
				break;
			}
			case 5:
			{
				min = 21;
				max = 25;
				break;
			}
			}
			int fmin = min;
			int fmax = max;
			s = s.filter(e->e.huoyue>=fmin && e.huoyue<=fmax);
		}
		
			s = s.sorted(new Comparator<ClubData>() {
			@Override
			public int compare(ClubData o1, ClubData o2) {
				return o1.level-o2.level;
			}
		});//.forEach(e->e.setCreateTime(sdf.format(e.getCreate_time())));
		pager.setTotalCount(map.size());
		pager.setItems(s.skip(pager.getStart()).limit(pager.getLimit()).collect(Collectors.toList()));
		pager.getItems().forEach(e->{
			ClubData cd = (ClubData)e;
			cd.setCreateTime(sdf.format(cd.getCreate_time()));
			cd.setMemberNum(sj.hlen(RedisKey.CLUB_MEMBER_INFO_HASH__+cd.getId()).intValue());
			switch(cd.getLevel())// 按策划表格club.xml来
			{
			case 1:
			{
				cd.setMemberMax(10);
				break;
			}
			case 2:
			{
				cd.setMemberMax(15);
				break;
			}
			case 3:
			{
				cd.setMemberMax(20);
				break;
			}
			case 4:
			{
				cd.setMemberMax(25);
				break;
			}
			case 5:
			{
				cd.setMemberMax(30);
				break;
			}
			case 6:
			{
				cd.setMemberMax(35);
				break;
			}
			case 7:
			{
				cd.setMemberMax(40);
				break;
			}
			case 8:
			{
				cd.setMemberMax(45);
				break;
			}
			case 9:
			{
				cd.setMemberMax(50);
				break;
			}
			case 10:
			{
				cd.setMemberMax(55);
				break;
			}
			}
		});
	}
	
	public List<ClubMemberData> membersInfo(String clubId)
	{
		Map<String, String> map = shardedPool.getResource().hgetAll(RedisKey.CLUB_MEMBER_INFO_HASH__+clubId);
		return map.values().stream().map(e->{
			ClubMemberData cmd = g.fromJson(e, ClubMemberData.class);
			cmd.setJoinTimeString(sdf.format(cmd.getJoinTime()));
			switch(cmd.getZhiwu())
			{
			case 1:
			{
				cmd.setZhiwuString("主席");
				break;
			}
			case 2:
			{
				cmd.setZhiwuString("副主席");
				break;
			}
			case 3:
			{
				cmd.setZhiwuString("主管");
				break;
			}
			case 4:
			{
				cmd.setZhiwuString("普通成员");
				break;
			}
			}
			return cmd;
		}).collect(Collectors.toList());
	}
}
