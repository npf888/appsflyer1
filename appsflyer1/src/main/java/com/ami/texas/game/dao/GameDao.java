package com.ami.texas.game.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ami.api.common.Pager;
import com.ami.api.exception.APIException;
import com.ami.api.utill.StringTool;
import com.ami.api.web.dao.BaseMysqlDao;
import com.ami.common.TimeUtil;
import com.ami.statistics.pojo.THumanTaskTemplateVO;
import com.ami.texas.game.pojo.DailyTaskVO;
import com.ami.texas.game.pojo.THumanTaskPO;

/**
 * 
 * 娓告垙鐩稿叧鏁版嵁
 * 
 * @author  Netherfire
 * @version  [鐗堟湰鍙�, Aug 12, 2015]
 * @see  [鐩稿叧绫�/鏂规硶]
 * @since  [浜у搧/妯″潡鐗堟湰]
 */
@Component
public class GameDao extends BaseMysqlDao
{
    /**
     * 鑾峰彇鏈堝崱鍒楄〃
     * 
     * @param pager
     * @param useraccount
     * @param username
     * @return
     * @throws APIException
     * @see [绫汇�佺被#鏂规硶銆佺被#鎴愬憳]
     */
    public Pager queryMonthCard(Pager pager, String userid, String username) 
        throws APIException
    { 
        StringBuffer sql = new StringBuffer(" ");
            sql.append("SELECT id, charid, if(begintime=0, '-' , FROM_UNIXTIME(begintime/1000,'%Y-%m-%d %H:%i:%s')) as begin_time, ");
            sql.append(" duration/86400000 as duration, if(updatetime=0, '-' , FROM_UNIXTIME(updatetime/1000,'%Y-%m-%d %H:%i:%s')) as update_time, ");
            sql.append(" if(createtime=0, '-' , FROM_UNIXTIME(createtime/1000,'%Y-%m-%d %H:%i:%s')) as create_time FROM texas.t_human_month_card where  1=1 ");
        
        if (!StringTool.isEmpty(userid))
        {
            sql.append(" and charid like '%");
            sql.append(userid);
            sql.append("%'");
        }

        pager = this.db.queryPage(sql.toString(), new Object[]
        {}, pager);
        
        return pager; 
    }
    
    /**
     * 鑾峰彇鍛ㄥ崱鍒楄〃
     * 
     * @param pager
     * @param useraccount
     * @param username
     * @return
     * @throws APIException
     * @see [绫汇�佺被#鏂规硶銆佺被#鎴愬憳]
     */
    public Pager queryWeekCard(Pager pager, String userid, String username) 
        throws APIException
    { 
        StringBuffer sql = new StringBuffer(" ");
        sql.append(" SELECT id, charid,if(begintime=0, '-' , FROM_UNIXTIME(begintime/1000,'%Y-%m-%d %H:%i:%s')) as begin_time, ");
        sql.append(" duration/86400000 as duration, if(updatetime=0, '-' , FROM_UNIXTIME(updatetime/1000,'%Y-%m-%d %H:%i:%s')) as update_time, ");
        sql.append(" if(createtime=0, '-' , FROM_UNIXTIME(createtime/1000,'%Y-%m-%d %H:%i:%s')) as create_time FROM texas.t_human_week_card where  1=1 ");
        
        if (!StringTool.isEmpty(userid))
        {
            sql.append(" and charid like '%");
            sql.append(userid);
            sql.append("%'");
        }

        
        pager = this.db.queryPage(sql.toString(), new Object[]
        {}, pager);
        
        return pager; 
    }
    
    /**
     * 鑾峰彇寰峰窞娓告垙淇℃伅
     * 
     * @param pager
     * @param useraccount
     * @param username
     * @return
     * @throws APIException
     * @see [绫汇�佺被#鏂规硶銆佺被#鎴愬憳]
     */
    public Pager queryTexas(Pager pager, String userid, String username) 
        throws APIException
    { 
        StringBuffer sql = new StringBuffer(" ");
        sql.append(" SELECT id, charid, isauto, people, count, wincount, biggesthandcard, weekwincoins, daybiggestwincoins, ");
        sql.append(" if(updatetime=0, '-' , FROM_UNIXTIME(updatetime/1000,'%Y-%m-%d %H:%i:%s')) as update_time, ");
        sql.append(" if(createtime=0, '-' , FROM_UNIXTIME(createtime/1000,'%Y-%m-%d %H:%i:%s')) as create_time FROM texas.t_human_texas where 1=1 ");
        
        if (!StringTool.isEmpty(userid))
        {
            sql.append(" and charid like '%");
            sql.append(userid);
            sql.append("%'");
        }

        
        pager = this.db.queryPage(sql.toString(), new Object[]
        {}, pager);
        
        return pager; 
    }
    
    /**
     * 鑾峰彇璺戦┈鐏俊鎭�
     * 
     * @param pager
     * @param useraccount
     * @param username
     * @return
     * @throws APIException
     * @see [绫汇�佺被#鏂规硶銆佺被#鎴愬憳]
     */
    public Pager queryNews(Pager pager, String userid, String username) 
        throws APIException
    { 
        StringBuffer sql = new StringBuffer(" ");
        sql.append(" SELECT id, content, if(starttime=0, '-' , FROM_UNIXTIME(starttime/1000,'%Y-%m-%d %H:%i:%s')) as start_time, ");
        sql.append(" if(endtime=0, '-' , FROM_UNIXTIME(endtime/1000,'%Y-%m-%d %H:%i:%s')) as end_time, ");
        sql.append(" if(dailystarttime=0, '-' , FROM_UNIXTIME(dailystarttime/1000,'%H:%i:%s')) as daily_start_time, ");
        sql.append(" if(dailyendtime=0, '-' , FROM_UNIXTIME(dailyendtime/1000,'%H:%i:%s')) as daily_end_time, intervaltime/86400000 as interval_time, ");
        sql.append(" if(updatetime=0, '-' , FROM_UNIXTIME(updatetime/1000,'%Y-%m-%d %H:%i:%s')) as update_time, ");
        sql.append(" if(createtime=0, '-' , FROM_UNIXTIME(createtime/1000,'%Y-%m-%d %H:%i:%s')) as create_time   FROM texas.t_notice where 1=1");
        

        pager = this.db.queryPage(sql.toString(), new Object[]
        {}, pager);
        
        return pager; 
    }
    
    /**
     * 鑾峰彇绛惧埌淇℃伅
     * 
     * @param pager
     * @param useraccount
     * @param username
     * @return
     * @throws APIException
     * @see [绫汇�佺被#鏂规硶銆佺被#鎴愬憳]
     */
    public Pager queryWeeklySignin(Pager pager, String userid, String username) 
        throws APIException
    { 
        StringBuffer sql = new StringBuffer(" ");
        sql.append(" SELECT id, charid, signinpack, ");
        sql.append(" if(updatetime=0, '-' , FROM_UNIXTIME(updatetime/1000,'%Y-%m-%d %H:%i:%s')) as update_time, ");
        sql.append(" if(createtime=0, '-' , FROM_UNIXTIME(createtime/1000,'%Y-%m-%d %H:%i:%s')) as create_time FROM texas.t_human_week_sign_in where 1=1");
        
        
        if (!StringTool.isEmpty(userid))
        {
            sql.append(" and charid like '%");
            sql.append(userid);
            sql.append("%'");
        }

        
        
        pager = this.db.queryPage(sql.toString(), new Object[]
        {}, pager);
        
        return pager; 
    }
    
    /**
     * 鑾峰彇浠诲姟淇℃伅
     * 
     * @param pager
     * @param useraccount
     * @param username
     * @return
     * @throws APIException
     * @see [绫汇�佺被#鏂规硶銆佺被#鎴愬憳]
     */
    public Pager queryTask(Pager pager, String userid, String username) 
        throws APIException
    { 
        StringBuffer sql = new StringBuffer(" ");
        sql.append(" SELECT id, charid, dailytaskpack, taskpack, ");
        sql.append(" if(updatetime=0, '-' , FROM_UNIXTIME(updatetime/1000,'%Y-%m-%d %H:%i:%s')) as update_time, ");
        sql.append(" if(createtime=0, '-' , FROM_UNIXTIME(createtime/1000,'%Y-%m-%d %H:%i:%s')) as create_time FROM texas.t_human_task where 1=1");
        
        
        if (!StringTool.isEmpty(userid))
        {
            sql.append(" and charid like '%");
            sql.append(userid);
            sql.append("%'");
        }

        
        pager = this.db.queryPage(sql.toString(), new Object[]
        {}, pager);
        
        return pager; 
    }
    
    /**
     * 鑾峰彇浠诲姟淇℃伅
     * 
     * @param pager
     * @param useraccount
     * @param username
     * @return
     * @throws APIException
     * @see [绫汇�佺被#鏂规硶銆佺被#鎴愬憳]
     */
    public Pager queryBaccart(Pager pager, String userid, String username) 
        throws APIException
    { 
        StringBuffer sql = new StringBuffer(" ");
        sql.append(" SELECT charId, bankerNum, gameNum,winNum,beaconNum,isAuto,lostNum, ");
        sql.append(" if(updatetime=0, '-' , FROM_UNIXTIME(updatetime/1000,'%Y-%m-%d %H:%i:%s')) as update_time, ");
        sql.append(" if(createtime=0, '-' , FROM_UNIXTIME(createtime/1000,'%Y-%m-%d %H:%i:%s')) as create_time FROM texas.t_human_baccart where 1=1");
        
        
        if (!StringTool.isEmpty(userid))
        {
            sql.append(" and charid like '%");
            sql.append(userid);
            sql.append("%'");
        }

        
        pager = this.db.queryPage(sql.toString(), new Object[]
        {}, pager);
        
        return pager; 
    }
    
    /**
     * 鑾峰彇浠诲姟淇℃伅
     * 
     * @param pager
     * @param useraccount
     * @param username
     * @return
     * @throws APIException
     * @see [绫汇�佺被#鏂规硶銆佺被#鎴愬憳]
     */
    public Pager queryBaccartRoom(Pager pager, String userid, String username) 
        throws APIException
    { 
        StringBuffer sql = new StringBuffer(" ");
        sql.append(" SELECT roomId, stock, jackpot, closed, ");
        sql.append(" if(updatetime=0, '-' , FROM_UNIXTIME(updatetime/1000,'%Y-%m-%d %H:%i:%s')) as update_time, ");
        sql.append(" if(createtime=0, '-' , FROM_UNIXTIME(createtime/1000,'%Y-%m-%d %H:%i:%s')) as create_time FROM texas.t_baccart_room_model where 1=1");

        
        pager = this.db.queryPage(sql.toString(), new Object[]
        {}, pager);
        
        return pager; 
    }
    
    /**
     * 鑾峰彇鍏朵粬鏉備贡淇℃伅
     * 
     * @param pager
     * @return
     * @throws APIException
     * @see [绫汇�佺被#鏂规硶銆佺被#鎴愬憳]
     */
    public Pager queryOther(Pager pager)
        throws APIException
    {
        StringBuffer sql = new StringBuffer(" ");
        sql.append(" SELECT id, charid, currentonlinerewardid , ");
        sql.append(" if(onlinetime=0, '-' , FROM_UNIXTIME(onlinetime,'%Y-%m-%d %H:%i:%s')) as onlinetime, ");
        sql.append(" if(lastgettime=0, '-' , FROM_UNIXTIME(lastgettime/1000,'%Y-%m-%d %H:%i:%s')) as lastgettime, ");
        sql.append(" if(updatetime=0, '-' , FROM_UNIXTIME(updatetime/1000,'%Y-%m-%d %H:%i:%s')) as update_time, ");
        sql.append(" if(createtime=0, '-' , FROM_UNIXTIME(createtime/1000,'%Y-%m-%d %H:%i:%s')) as create_time FROM texas.t_human_misc ");
        
        pager = this.db.queryPage(sql.toString(), new Object[]
        {}, pager);
        
        return pager;
    }
	/**
	 * 鏂板缁熻
	 * @param pager
	 * @param useraccount
	 * @param username
	 * @return
	 * @throws APIException 
	 */
	public List<DailyTaskVO> queryTaskStatistics(String useraccount,
			String username,String date) throws APIException {
		String sql = "select id,charId, dailyTaskPack,taskPack,updateTime,createTime from texas.t_human_task where createTime>=? and createTime<?;";
		String sqlTaskTemplate = "select id,conditions from texas_activity.t_human_task_template";
		List<THumanTaskTemplateVO> taskList = this.db.query(sqlTaskTemplate, THumanTaskTemplateVO.class);
		Map<Long,Long> taskMap= new HashMap<Long,Long>();
		for(THumanTaskTemplateVO task:taskList){
			Long id = task.getId();
			Long condition = task.getConditions();
			taskMap.put(id, condition);
		}
		List<Object> param = new ArrayList<Object>();
		if(!StringUtils.isEmpty(date)){
			long nowStarlong =TimeUtil.formatStrTODate(date).getTime();
			long nowEndlong = TimeUtil.formatAddDayReDate(TimeUtil.formatStrTODate(date), 1).getTime();
			param.add(nowStarlong);
			param.add(nowEndlong);
		}else{
			Date now = new Date();
			String nowStartStr = TimeUtil.formatDate(now);
			String nowEndStr = TimeUtil.formatAddDay(now, 1);
			long nowStartlong =TimeUtil.formatStrTODateff(nowStartStr).getTime();
			long nowEndlong = TimeUtil.formatAddDayReDate(TimeUtil.formatStrTODateff(nowEndStr), 1).getTime();
			param.add(nowStartlong);
			param.add(nowEndlong);
		}
		if(!StringUtils.isEmpty(useraccount)){
			param.add(useraccount);
		}
		if(!StringUtils.isEmpty(username)){
			param.add(username);
		}
		List<THumanTaskPO> tHumanTaskPOS = this.db.query(sql,param.toArray(), THumanTaskPO.class);
		List<DailyTaskVO> dailyTaskVOS = new ArrayList<DailyTaskVO>();
		Map<String,DailyTaskVO> process = new HashMap<String,DailyTaskVO>();
		for(THumanTaskPO taskPO:tHumanTaskPOS){
			String dailyTaskPack = taskPO.getDailyTaskPack();
			JSONArray ja = JSON.parseArray(dailyTaskPack);
			if(ja != null && ja.size()>0){
				for(int i=0;i<ja.size();i++){
					JSONObject jb = (JSONObject)ja.get(i);
					if(jb.containsKey("taskId")){
						int taskId = jb.getIntValue("taskId");
						int finished = jb.getIntValue("finished");
//						if(taskId == 30052){
//							System.out.println("-------------------taskId:"+taskId);
//						}
						Long condition = taskMap.get(Long.valueOf(taskId));
						if(condition == null){
							continue;
						}
						int times = 0;
						if(finished >= condition){
							//瀹屾垚娆℃暟
							times = finished/(condition.intValue());
						}
						DailyTaskVO existDailyTaskVO = process.get(taskId+"|"+times);
						if(existDailyTaskVO != null){
							existDailyTaskVO.setPeopleNums(existDailyTaskVO.getPeopleNums()+1);
						}else{
							DailyTaskVO dailyTaskVO = new DailyTaskVO();
							dailyTaskVO.setFinished(times);
							dailyTaskVO.setPeopleNums(1);
							dailyTaskVO.setTaskId(taskId);
							process.put(taskId+"|"+times, dailyTaskVO);
						}
					}
				}
			}
		}
		for(DailyTaskVO task :process.values()){
			dailyTaskVOS.add(task);
		}
		Collections.sort(dailyTaskVOS, new Comparator<Object>() {  
            @Override
			public int compare(Object o1, Object o2) {  
            	DailyTaskVO task1 = (DailyTaskVO) o1;  
            	DailyTaskVO task2 = (DailyTaskVO) o2;  
                if (task1.getTaskId() > task2.getTaskId()) {  
                    return 1;  
                }else if(task1.getTaskId() == task2.getTaskId()){
                	if (task1.getFinished()> task2.getFinished()) {  
                		return 1;  
                	}
                	return 0;
                }
                return 0;  
            }  
        });
		return dailyTaskVOS; 
	}

}
