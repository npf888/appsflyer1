package com.ami.texas.gamenew.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ami.api.common.Pager;
import com.ami.texas.gamenew.dao.GamenewDao;
import com.ami.texas.gamenew.pojo.CollectPO;
import com.ami.texas.gamenew.pojo.CollectVO;
@Component
public class GamenewService {
	@Autowired
    private GamenewDao gamenewDao;

	public Pager queryCollect(Pager pager, String account, String username) {
		Pager rPager = gamenewDao.queryCollect(pager, account, username);
		List<CollectPO> collectVOList = rPager.getItems();
		List<CollectVO> collectViewList = new ArrayList<CollectVO>();
		for(CollectPO collectVO:collectVOList){
			CollectVO collectView = new CollectVO();
			collectView.setId(collectVO.getId());
			collectView.setName(collectVO.getName());
			collectView.setLevel(collectVO.getLevel());
			collectView.setDebris(collectVO.getDebris());
			collectView.setSlotspin(collectVO.getSlotspin());
			collectView.setSlotpoint(collectVO.getSlotpoint());
			collectView.setCreateTime(collectVO.getCreateTime());
			collectView.setUpdateTime(collectVO.getUpdateTime());
			collectView.filterTotalNum(collectVO.getCarIds());
			
			collectView.filterGoldNum(collectVO.getCarIds());
			collectView.filterSilverNum(collectVO.getCarIds());
			collectView.filterCuprumNum(collectVO.getCarIds());
			collectViewList.add(collectView);
		}
		rPager.setItems(collectViewList);
		return rPager;
	}

	public List<CollectPO> querycollect() {
		return gamenewDao.querycollect();
		
	}

	public Pager queryWorldBoss(Pager pager, String account, String bossType,String startDate,String endDate) {
		return gamenewDao.queryWorldBoss(pager, account, bossType,startDate,endDate);
	}

	public Pager queryWorldBossDetail(Pager pager, String bossId, String date) {
		return gamenewDao.queryWorldBossDetail(pager, bossId, date);
	}

	public Pager queryTournament(Pager pager, String account, String username,String date) {
		return gamenewDao.queryTournament(pager, account, username,date);
	}

	public Pager queryMoreDetail(Pager pager, String tournamentId, String date) {
		return gamenewDao.queryTournament(pager, tournamentId,date);
	}

	public Pager queryWorldBossDetailOne(Pager pager, String bossId, String date) {
		return gamenewDao.queryWorldBossDetailOne(pager, bossId, date);
	}
}
