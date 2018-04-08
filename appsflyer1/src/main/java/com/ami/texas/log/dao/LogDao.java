package com.ami.texas.log.dao;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.ami.api.common.Pager;
import com.ami.api.exception.APIException;
import com.ami.api.web.dao.BaseMysqlDao;
import com.ami.common.TimeUtil;
import com.ami.mail.pojo.Country;
import com.ami.mail.pojo.NewPeopleVO;
import com.ami.texas.log.action.LogAction;
import com.ami.texas.log.vo.RechargeVO;
import com.ami.texas.log.vo.SlotAgeVO;
import com.ami.texas.log.vo.SlotPayVO;
import com.ami.texas.log.vo.SlotSexVO;
import com.ami.texas.log.vo.SpinDetailVO;

import android.R.integer;
/**
 * 
 * 鐜╁淇℃伅
 * 
 * @author  Netherfire
 * @version  [鐗堟湰鍙�, Aug 12, 2015]
 * @see  [鐩稿叧绫�/鏂规硶]
 * @since  [浜у搧/妯″潡鐗堟湰]
 */
@Component
public class LogDao extends BaseMysqlDao
{
    private static final long serialVersionUID = 1L;
    private static Logger logger = Logger.getLogger(LogAction.class);
    /**
     * 鑾峰彇鎵�鏈夌敤鎴�
     * 
     * @param pager
     * @param useraccount
     * @param username
     * @return
     * @throws APIException
     * @throws SQLException 
     * @see [绫汇�佺被#鏂规硶銆佺被#鎴愬憳]
     */
    public Pager queryLog(Pager pager, Map<String, Object> params)
        throws APIException, SQLException
    { 
        StringBuffer sql = new StringBuffer(" SELECT id, log_type, region_id, server_id, account_id, account_name, level, reason, param, ");
        sql.append(" if(createtime=0, '-' , FROM_UNIXTIME(createtime/1000,'%Y-%m-%d %H:%i:%s')) as create_date, ");
        sql.append(" if(log_time=0, '-' , FROM_UNIXTIME(log_time/1000,'%Y-%m-%d %H:%i:%s')) as log_date  ");
        
        String type = params.get("type").toString();
        String date = params.get("date").toString();
        
        if(type.equals("monthcard") || type.equals("weekcard"))
        {
            sql.append(", duration/86400000 as duration_day ");
        }
        else if(type.equals("chat"))
        {
            sql.append(", channel, rec_char_name, content ");
        }
        else if(type.equals("gold"))
        {
            sql.append(", gold_delta, gold_left ");
        }
        else if(type.equals("diamond"))
        {
            sql.append(", diamond_delta, diamond_left");
        }
        else if(type.equals("vip"))
        {
            sql.append(", vip_level ");
        }
        else if(type.equals("recharge"))
        {
            sql.append(", order_id, product_id ");
        }
        else if(type.equals("sign_in"))
        {
            sql.append(", days, culumative ");
        }
        else if(type.equals("texas_room"))
        {
            sql.append(", room_id, mode_id, type_id ");
        }
        else if(type.equals("baccarat_room"))
        {
            sql.append(", room_id ");
        }
        else if(type.equals("slot_room"))
        {
        	sql.append(", room_id ");
        }
        String tablename = "texas_log."+type+"_log_"+date;
        sql.append(" from " + tablename);
        
        String start = params.get("start").toString();
        String end = params.get("end").toString();
              
        sql.append(" where FROM_UNIXTIME(log_time/1000,'%Y_%m_%d %H:%i:%s') >= ? ");
        
        sql.append(" and FROM_UNIXTIME(log_time/1000,'%Y_%m_%d %H:%i:%s') <= ? ");
        
        String account_id = params.get("account_id").toString();
        try{
	        if(account_id.isEmpty())
	        {
	            pager = this.db.queryPage(sql.toString(), new Object[]
	                    {date+" "+start, date+" "+end}, pager); 
	        }
	        else
	        {
	            sql.append(" and account_id = ? ");
	            pager = this.db.queryPage(sql.toString(), new Object[]
	                    {date+" "+start, date+" "+end, account_id}, pager);
	        }
        }catch(Exception e){
        	pager = new Pager();
        	return pager;
        }
        
        
        /*//鑾峰彇鎸囧畾鏃堕棿鍖洪棿鍐呯殑鎵�鏈夎〃鍚�
        List<String> date = DateTools.getAllDatesBetweenDates(start, end);
        for(String temp:date)
        {
            String tablename = "texas_log."+type+"_log_"+temp;
            String sql = sql1.toString()+" from texas_log.chat_log_2015_08_14 ";
            pager.add(this.db.queryPage(sql, new Object[]
                                                        {}, pager_temp));
            //鏌ョ湅琛ㄥ悕鏄惁瀛樺湪
            /*DatabaseMetaData dbm = this.db.getConnection().getMetaData();
            if(null != dbm.getTables(null, null, tablename, null))
            {
                //鑻ュ瓨鍦�  鍒欒鏁版嵁
                String sql = sql1.toString() + " from " +tablename;
                pager.add(this.db.queryPage(sql, new Object[]
                        {}, pager));
            }
        }*/
        return pager; 
    }
    public List<HashMap<String, Object>> dailySum(List<HashMap<String, Object>> date_list) throws APIException
    {
    	String sql = "";
    	for(HashMap<String, Object> map:date_list){
    		String dateSingle = map.get("date").toString();
    		sql+="(select '"+dateSingle+"' as date, reason, ifnull(sum(gold_delta),0) as gold from texas_log.gold_log_"+dateSingle+" group by reason ) union all"
    				+" (select '"+dateSingle+"' as date, 999999999 as reason, ifnull(SUM(gold_left),0) as gold from (select * from (select * from texas_log.gold_log_"+dateSingle
    				+ " where account_id not IN (63253,10012) ORDER BY createTime DESC) as t GROUP BY account_id) as tt)    union all";
    	}
    	if(sql.length()<6){
    		return null;
    	}
    	sql = sql.substring(0,sql.length()-11);
    	List<HashMap<String, Object>> list = null;
    	try{
    		list = this.db.query(sql);
    	}catch(Exception e){
    		e.printStackTrace();
    		return null;
    	}
    	
    	return list;
    }
    /**
     * 鑰佹柟娉曚笉鐢ㄤ簡
     * @param date
     * @return
     * @throws APIException
     */
    public List<HashMap<String, Object>> dailySum_COPY(String date) throws APIException
    {
    	try{
	    	String sql = "select reason, sum(gold_delta) as gold from texas_log.gold_log_"+date+" group by reason ";
	    	List<HashMap<String, Object>> list = this.db.query(sql);
	    	String sql2 = "select SUM(gold_left) as gold from (select * from (select * from texas_log.gold_log_"+date+" where account_id not IN (63253,10012) ORDER BY createTime DESC) as t GROUP BY account_id) as tt";
	    	List<HashMap<String, Object>> list2 = this.db.query(sql2);
	    	if(list2 != null){
	    		for(HashMap<String, Object> map : list2){
	    			map.put("reason", 999999999);
	    			list.add(map);
	    		}
	    	}
	    	return list;
    	}catch(Exception e){
    		return null;
    	}
    }
    
    
    public List<HashMap<String, Object>> dailySum1(String tableName) throws APIException
    {
    	try{
    		String sql = "select reason, sum(gold_delta) as gold from texas_log."+tableName+" group by reason ";
    		List<HashMap<String, Object>> list = this.db.query(sql);
    		String sql2 = "select SUM(gold_left) as gold from (select * from (select * from texas_log."+tableName+" where account_id not IN (63253,10012) ORDER BY createTime DESC) as t GROUP BY account_id) as tt";
    		List<HashMap<String, Object>> list2 = this.db.query(sql2);
    		if(list2 != null){
    			for(HashMap<String, Object> map : list2){
    				map.put("reason", 999999999);
    				list.add(map);
    			}
    		}
    		return list;
    	}catch(Exception e){
    		return null;
    	}
    }
    /**
     * 鑰佽檸鏈簊pin鏄庣粏
     * @param date
     * @return
     * @throws APIException
     */
    public List<HashMap<String, Object>> slotspindetail(String date,Integer facebook,Integer sex) throws APIException
    {
    	 
    	 String sql = "select value as reason ,ifnull(count(*),0) as dataValue "
    	 		+ " from texas_log.data_overview_log_"+date+" t1 "
    	 		+ " left join texas.t_user_info t2 on t1.account_id=t2.id "
    	 		+ " where t1.reason = 8 ";
		    	 //sex 女
		    	 if(sex != null && sex.intValue() == 0){
		    		 sql+=" and t1.girl = 0 ";
		    		 //sex 男
		    	 }else if(sex != null && sex.intValue() == 1){
		    		 sql+=" and t1.girl = 1 ";
		    	 }
    	 		//是facebook
    	 		if(facebook != null && facebook.intValue() == 1){
    	 			sql+=" and t2.facebookId != -1 ";
    	 		//不是facebook 是普通用户	
    	 		}else if(facebook != null && facebook.intValue() == -1){
    	 			sql+=" and t2.facebookId = -1 ";
    	 		}
    	 		
    	 		sql+= "GROUP BY t1.value";
    	 List<HashMap<String, Object>> list1 = null;
    	 try{
    		 list1 = this.db.query(sql);
    	 }catch(Exception e){
    		 return null;
    	 }
    	 
    	return list1;
    }
    public List<SpinDetailVO> slotspindetailMore(Map<String, Object> params) throws APIException
    {
    	String date = params.get("date").toString();
    	int facebook = Integer.valueOf((String)params.get("facebook"));
    	int country = Integer.valueOf((String)params.get("country"));
    	int girl = Integer.valueOf((String)params.get("girl"));
    	int age = Integer.valueOf((String)params.get("age"));
    	
    	String sql = " select t1.value as reason";
    			if(country == 1){
    				sql+= " ,ifnull(t1.ip_countries,'') ipCountries ";
    			}
    			if(girl ==1){
    				sql+= " ,t1.girl ";
    			}
    			if(age == 1){
    				sql+= " ,t1.age ";
    			}
    			sql+= " ,ifnull(count(*),0) as dataValue from texas_log.data_overview_log_"+date+" t1"
    					+ "  left join texas.t_user_info t2 on t1.account_id=t2.id "
    					+ " where t1.reason = 8 ";
    			if(facebook == 0){
    				sql+=" and t2.facebookId < 0";
    			}else if(facebook == 1){
    				sql+=" and t2.facebookId > 0";
    			}
    					sql+= " group by t1.value ";
    			
    			if(country ==1){
    				sql+= " ,t1.ip_countries ";
    			}
    			if(girl ==1){
    				sql+= " ,t1.girl ";
    			}
    			if(age ==1){
    				sql+= " ,t1.age ";
    			}
    	List<SpinDetailVO> list1 = null;
    	try{
    		list1 = this.db.query(sql,SpinDetailVO.class);
    	}catch(Exception e){
    		return null;
    	}
    	
    	return list1;
    }
    /**
     * 鍏呭�兼槑缁�
     * @param date
     * @return
     * @throws APIException
     */
    public HashMap<String, Object> topupdetail(String date,Object country) throws APIException
    {
    	//原先的sql
//    	String sql = "select cost,param from texas_log.recharge_log_"+date+" where reason=1";
    	
    	String sql = "select sum(money) as  cost,channel_type as channelType from texas_log.new_recharge_log_"+date+" where 1=1 group by channel_type";
    	if(country != null){
    		if(StringUtils.isNotBlank(country.toString())){
    			sql+="  and countries like '%"+country+"%'";
    		}
    	}
    	List<HashMap<String, Object>> list1 = null;
    	try{
    		System.out.println("-============================"+sql);
    		list1 = this.db.query(sql);
    	}catch(Exception e){
    		return null;
    	}
    	
    	HashMap<String, Object> mapData = new HashMap<String, Object>();
    	
    	for(HashMap<String, Object> map : list1){
    		BigDecimal cost = (BigDecimal)map.get("cost");
    		Integer channelType = (Integer)map.get("channelType");
    		
  			mapData.put(String.valueOf(channelType), cost.doubleValue());
    	}
    
    	return mapData;
    }
    public List<RechargeVO> topupdetailMore(Map<String, Object> params) throws APIException
    {
    	String date = params.get("date").toString();
    	int country = Integer.valueOf((String)params.get("country"));
    	int girl = Integer.valueOf((String)params.get("girl"));
    	int age = Integer.valueOf((String)params.get("age"));
    	
    	String sql = " select sum(money) as  cost,param,channel_type as channelType ";
    	
    			if(country ==1){
    				sql+= " ,ifnull(ip_countries,'') ipCountries ";
    			}
    			if(girl ==1){
    				sql+= " ,girl ";
    			}
    			if(age ==1){
    				sql+= " ,age ";
    			}
    			sql+= " from texas_log.new_recharge_log_"+date+" where 1=1 group by  channelType ";
    			
    			if(country ==1){
    				sql+= " ,ip_countries ";
    			}
    			if(girl ==1){
    				sql+= " ,girl ";
    			}
    			if(age ==1){
    				sql+= " ,age ";
    			}
    	List<RechargeVO> list2 = new ArrayList<RechargeVO>();
    	List<HashMap<String,Object>> list1 = null;
    	try{
    		list1 = this.db.query(sql);//,RechargeVO.class);
    		for(HashMap<String,Object> map:list1){
    			RechargeVO rechargeVO = new RechargeVO();
    			if(map.get("channelType") != null){
    				Integer ChannelType = (Integer)map.get("channelType");
    				rechargeVO.setChannelType(String.valueOf(ChannelType));
    			}
    			if(map.get("ipCountries") != null){
    				rechargeVO.setIpCountries((String)map.get("ipCountries"));
    			}
    			if(map.get("age") != null){
    				rechargeVO.setAge((Integer)map.get("age"));
    			}
    			if(map.get("girl") != null){
    				rechargeVO.setGirl((Integer)map.get("girl"));
    			}
    			if(map.get("cost") != null){
    				BigDecimal cost = (BigDecimal)map.get("cost");
    				rechargeVO.setMoney(Long.valueOf(cost.longValue()));
    			}
    			list2.add(rechargeVO);
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    		return null;
    	}
    	
    	return list2;
    }
    
    
    /**
     * 鐜板湪鐨勬煡璇�
     * @param date
     * @return
     * @throws APIException
     */
    public List<HashMap<String, Object>> dailySummary(List<HashMap<String, Object>> date_list) throws APIException{
    	String sql = "";
    	logger.info("-----------------date_list::"+date_list.size());
    	
    	
    	if(date_list == null || date_list.size()==0){
    		return null;
    	}
    	for(HashMap<String, Object> tDate:date_list){
    		String date = tDate.get("date").toString();
    		
    		
    		sql += "(select '"+date+"' as date, reason, IFNULL(sum(value),0) as dataValue from texas_log.data_overview_log_"+date+" where reason not in(1,2,3,4,5,6,7)  group by reason) union all"  //
    				
    				+"(select '"+date+"' as date, reason, IFNULL(count(DISTINCT account_id),0)  as dataValue from texas_log.player_login_log_"+date+"   group by reason ) union all"   //用户登录总数
    				
					+"(select '"+date+"' as date, 5 as reason,IFNULL(count(DISTINCT account_id),0)  as dataValue from texas_log.new_recharge_log_"+date+" ) union all"   //付费总人数
					
					+"(select '"+date+"' as date, 106 as reason,IFNULL(count(DISTINCT account_id),0)  as dataValue from texas_log.new_recharge_log_"+date+" where reason = 2 ) union all"  //付费新人数量
					
					+"(select '"+date+"' as date, 3 as reason,IFNULL(sum(money),0)  as dataValue from texas_log.new_recharge_log_"+date+" ) union all"	  //总收入美金	
					
					+"(select '"+date+"' as date, 4 as reason, IFNULL(count(*),0)  as dataValue from texas_log.new_recharge_log_"+date+" ) union all"   //订单数
					
					+"(select '"+date+"' as date, 6 as reason,IFNULL(count(*),0)  as dataValue from texas_log.new_recharge_log_"+date+"  where reason = 2) union all"  //新人付款的总人数
					
					+"(select '"+date+"' as date, 105 as reason,IFNULL(count(*),0)  as dataValue from texas_log.player_login_log_"+date+"  where reason = 2) union all"  //当天新注册总人数
					
					+"(select '"+date+"' as date, 7 as reason ,IFNULL(sum(money),0)  as dataValue from texas_log.new_recharge_log_"+date+"  where reason = 1) union all"  //老用户付款的总收入
					
					+"(select '"+date+"' as date, 104 as reason, IFNULL(count(*),0)  as dataValue from texas.t_human_info)   union all";  //

    		
    	}
    	if(sql.length()<6){
    		return null;
    	}
    	logger.info("------23-----------sql::"+sql);
    	sql = sql.substring(0,sql.length()-9);
    	logger.info("------32-----------sql::"+sql);
    	List<HashMap<String, Object>> list = null;
    	try{
    		list = this.db.query(sql);
    	}catch(Exception e){
    		logger.info("dailySummary --- 报错了",e);
    		return null;
    	}
    	return list;
    	
    }
    
    /**
     * 获取ltv
     * @param date_list
     * @return
     * @throws APIException
     */
    public double ltv(List<HashMap<String, Object>> date_list) throws APIException{
    	String sql1 = "";
    	String sql2 = "";
    	logger.info("-----------------date_list::"+date_list.size());
    	
    	double incomInnit = 0;  //总收入
    	
    	int playerNum = 0; //玩家数量
    	
    	if(date_list == null || date_list.size()==0){
    		return 0;
    	}
    	
    	List<HashMap<String, Object>> list1 = null;
    	
    	List<HashMap<String, Object>> list2 = null;
    	
    	for(HashMap<String, Object> tDate:date_list){
    		
    		String date = tDate.get("date").toString();
			
    		sql1 = "select IFNULL(sum(money),0)  as income from texas_log.new_recharge_log_"+date;
    				
    		sql2 =	"select IFNULL(count(DISTINCT account_id),0)  as num from texas_log.player_login_log_"+date;
			
    		try{
        		list1 = this.db.query(sql1);
        		
        		list2 = this.db.query(sql2);
        		
        		if(list1!= null){
        	    	for(HashMap<String, Object> map : list1){
        	    		incomInnit += Double.valueOf(map.get("income").toString());
        	    	}
            	}
        		
        		if(list2!= null){
        	    	for(HashMap<String, Object> map : list2){
        	    		playerNum += Integer.valueOf(map.get("num").toString());
        	    	}
            	}
        		
        	}catch(Exception e){
        		logger.info("dailySummary --- 报错了",e);
        		return 0;
        	}
    		
    	}
    	
    	return incomInnit/playerNum;
    }
    
    
    /**
     * 鍘熷厛鐨勬煡璇�
     * @param date
     * @return
     * @throws APIException
     */
    public List<HashMap<String, Object>> dailySummary_Copy(String date) throws APIException{
    	try{
	    	String sql = "select reason, sum(value) as dataValue from texas_log.data_overview_log_"+date+" where reason not in(1,2,3,4,5,6,7)  group by reason ";
	    	
	    	List<HashMap<String, Object>> list = this.db.query(sql);
	    	
	    	String sql1 = "select reason, count(DISTINCT account_id)  as dataValue from texas_log.player_login_log_"+date+"  group by reason ";
	    	
	    	List<HashMap<String, Object>> list1 = this.db.query(sql1);
	    	
	    	for(HashMap<String, Object> map : list1){
	    		list.add(map);
	    	}
	    	
	    	//鎴愬姛鐨勮鍗曟暟閲�
	    	String sql2 = "select count(*)  as dataValue from texas_log.new_recharge_log_"+date;
	    	
	    	List<HashMap<String, Object>> list2 = this.db.query(sql2);
	    	if(list2!= null){
	    		for(HashMap<String, Object> map : list2){
	    			map.put("reason", 5);
	    			list.add(map);
	    		}
	    	}
	    	
	    	//褰撳ぉ鏀跺叆缇庨噾鏁�
	    	String sql3 = "select sum(money)  as dataValue from texas_log.new_recharge_log_"+date;
	    	
	    	List<HashMap<String, Object>> list3 = this.db.query(sql3);
	    	if(list3!= null){
	    		for(HashMap<String, Object> map : list3){
	    			map.put("reason", 3);
	    			list.add(map);
	    		}
	    	}
	    	
	    	//褰撳ぉ鐨勪粯璐逛汉鏁�
	    	String sql4 = "select count(DISTINCT account_id)  as dataValue from texas_log.new_recharge_log_"+date;
	    	
	    	List<HashMap<String, Object>> list4 = this.db.query(sql4);
	    	if(list4!= null){
	    		for(HashMap<String, Object> map : list4){
	    			map.put("reason", 4);
	    			list.add(map);
	    		}
	    	}
	    	
	    	//鏂扮敤鎴烽浠�
	    	String sql5 = "select sum(money)  as dataValue from texas_log.new_recharge_log_"+date+" where reason = 2";
	    	
	    	List<HashMap<String, Object>> list5 = this.db.query(sql5);
	    	if(list5!= null){
	    		for(HashMap<String, Object> map : list5){
	    			map.put("reason", 6);
	    			list.add(map);
	    		}
	    	}
	    	
	    	//鑰佺敤鎴烽浠�
	    	String sql6 = "select sum(money)  as dataValue from texas_log.new_recharge_log_"+date+" where reason = 1";
	    	
	    	List<HashMap<String, Object>> list6 = this.db.query(sql6);
	    	if(list6!= null){
	    		for(HashMap<String, Object> map : list6){
	    			map.put("reason", 7);
	    			list.add(map);
	    		}
	    	}
	    	
	      String sql7 = "select count(*)  as dataValue from texas.t_human_info ";
	    	
	    	List<HashMap<String, Object>> list7 = this.db.query(sql7);
	
	    	if(list7 != null){
	    		for(HashMap<String, Object> map : list7){
	    			map.put("reason", 104);
	    			list.add(map);
	    		}
	    	}
	    	
	    	
	    	return list;
	    	
    	}catch(Exception e){
    		logger.info("");
    		return null;
    	}
    }
    
    
    public List<HashMap<String, Object>> slotwinner(String date,String winner) throws APIException{
    	
    	List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
    	
    	//ID 璧㈠緱閽�
    	String sql11 = "select account_id,sum(value) as win11 from texas_log.data_overview_log_"+date+"  where reason=11 ";
    	if(StringUtils.isNoneBlank(winner)){
    		sql11+= " and account_id like '%"+winner+"%'";
    	}
    		sql11+= " GROUP BY account_id ORDER BY sum(value) DESC";
    	
    	
    	String sql8 = "select account_id,sum(value)  as win8 from texas_log.data_overview_log_"+date+"  where reason=8 ";
    	if(StringUtils.isNoneBlank(winner)){
    		sql8+= " and account_id like '%"+winner+"%'";
    	}
    		sql8+= " GROUP BY account_id ORDER BY count(value) DESC";
    	
    	String sql9 = "select account_id,sum(value)  as win9 from texas_log.data_overview_log_"+date+"  where reason=9 ";
    	if(StringUtils.isNoneBlank(winner)){
    		sql9+= " and account_id like '%"+winner+"%'";
    	}
    		sql9+= " GROUP BY account_id ORDER BY sum(value) DESC";
    		
    	String sql10 = "select account_id,sum(value)  as win10 from texas_log.data_overview_log_"+date+"  where reason=10 ";
    	if(StringUtils.isNoneBlank(winner)){
    		sql10+= " and account_id like '%"+winner+"%'";
    	}
    		sql10+= " GROUP BY account_id ORDER BY sum(value) DESC";
    	
    	String sql12 = "select account_id,sum(value)  as win12 from texas_log.data_overview_log_"+date+"  where reason=12 ";
    	if(StringUtils.isNoneBlank(winner)){
    		sql12+= " and account_id like '%"+winner+"%'";
    	}
    		sql12+= " GROUP BY account_id ORDER BY sum(value) DESC";
    	List<HashMap<String, Object>> list8 = null;
    	List<HashMap<String, Object>> list9 = null;
    	List<HashMap<String, Object>> list10 = null;
    	List<HashMap<String, Object>> list11 = null;
    	List<HashMap<String, Object>> list12 = null;
    	try{
    		 list8 = this.db.query(sql8);
    		 list9 = this.db.query(sql9);
    		 list10 = this.db.query(sql10);
    		 list11 = this.db.query(sql11);
    		 list12 = this.db.query(sql12);
    	}catch(Exception e){
    		return null;
    	}
    	
    	if(list11 != null){
    		for(HashMap<String, Object> map : list11){
    			HashMap<String, Object> mm = new HashMap<String, Object>();
    			
    			long account_id = (Long)map.get("account_id");
    			
    			if(list8 != null){
    				sendMapWin(list8,mm,"win8",account_id);
    			}
    			if(list9 != null){
    				sendMapWin(list9,mm,"win9",account_id);
    			}
    			if(list10 != null){
    				sendMapWin(list10,mm,"win10",account_id);
    			}
    			if(list12 != null){
    				sendMapWin(list12,mm,"win12",account_id);
    			}
    			 map.putAll(mm);
    			
    		}
    	}else{
    		return list;
    	}
    	
    	return list11;

    }
    
    private void sendMapWin(List<HashMap<String, Object>> list8,HashMap<String, Object> mm,String key,long account_id){
    	for(HashMap<String, Object> m : list8){
    		long account_id8 = (Long)m.get("account_id");
			if(account_id == account_id8){
				mm.put(key, m.get(key));
				break;
			}
		}
    }
    
	public List<HashMap<String, Object>> slotMaxSpin(String date,String player) throws APIException{
		
		
		
        List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
    	
    	//ID 璧㈠緱閽�
    	String sql11 = "select account_id,sum(value) as win11 from texas_log.data_overview_log_"+date+"  where reason=11 ";
    	if(StringUtils.isNoneBlank(player)){
    		sql11+= " and account_id like '%"+player+"%'";
    	}
    		sql11+= "GROUP BY account_id ORDER BY sum(value) DESC";
    	
    	String sql8 = "select account_id,sum(value)  as win8 from texas_log.data_overview_log_"+date+"  where reason=8 ";
		if(StringUtils.isNoneBlank(player)){
			sql8+= " and account_id like '%"+player+"%'";
    	}
    		sql8+= "GROUP BY account_id ORDER BY count(value) DESC";
    	
    	String sql9 = "select account_id,sum(value)  as win9 from texas_log.data_overview_log_"+date+"  where reason=9 ";
		if(StringUtils.isNoneBlank(player)){
			sql9+= " and account_id like '%"+player+"%'";
    	}
    		sql9+= "GROUP BY account_id ORDER BY sum(value) DESC";
    	String sql10 = "select account_id,sum(value)  as win10 from texas_log.data_overview_log_"+date+"  where reason=10 ";
		if(StringUtils.isNoneBlank(player)){
			sql10+= " and account_id like '%"+player+"%'";
    	}
    		sql10+= "GROUP BY account_id ORDER BY sum(value) DESC";
    	
    	String sql12 = "select account_id,sum(value)  as win12 from texas_log.data_overview_log_"+date+"  where reason=12 ";
		if(StringUtils.isNoneBlank(player)){
			sql12+= " and account_id like '%"+player+"%'";
    	}
    		sql12+= "GROUP BY account_id ORDER BY sum(value) DESC";
    		List<HashMap<String, Object>> list8 = null;
    		List<HashMap<String, Object>> list9 = null;
    		List<HashMap<String, Object>> list10 = null;
    		List<HashMap<String, Object>> list11 = null;
    		List<HashMap<String, Object>> list12 = null;
    	try{
    		 list8 = this.db.query(sql8);
    		 list9 = this.db.query(sql9);
    		 list10 = this.db.query(sql10);
    		 list11 = this.db.query(sql11);
    		 list12 = this.db.query(sql12);
    		
    	}catch(Exception e){
    		
    	}
    	
    	if(list8 != null){
    		for(HashMap<String, Object> map : list8){
    			HashMap<String, Object> mm = new HashMap<String, Object>();
    			
    			long account_id = (Long)map.get("account_id");
    			
    			if(list11 != null){
    				sendMapWin(list11,mm,"win11",account_id);
    			}
    			if(list9 != null){
    				sendMapWin(list9,mm,"win9",account_id);
    			}
    			if(list10 != null){
    				sendMapWin(list10,mm,"win10",account_id);
    			}
    			if(list12 != null){
    				sendMapWin(list12,mm,"win12",account_id);
    			}
    			 map.putAll(mm);
    			
    		}
    	}else{
    		return list;
    	}
    	
    	return list8;
	}
    
	
    
    /**
     * 閫夋嫨鎵�鏈夊寘鍚壒瀹歱attern鐨勮〃鍚�
     * 
     * @param type
     * @return
     * @throws APIException
     */
	public List<HashMap<String, Object>> queryTables(String type) throws APIException {
		
		String sql = "select table_name from INFORMATION_SCHEMA.tables where table_schema = 'texas_log' and table_name like '"+type+"%' order by table_name";
		try{
			return this.db.query(sql);
		}catch(Exception e){
			return null;
		}
	}

	
    public Map<String,List<HashMap<String, Object>>> online(String time){
		try{
	    	String sql = "select time_dian,value_role from texas_log.player_onle_log_"+time;
	    	
	    	String sql1 = "select time_dian,SUM(money) as valueData from texas_log.new_recharge_log_"+time+" GROUP BY time_dian";
	    	long startTime = TimeUtil.formatStrTODate(time).getTime();
	    	long endTime = TimeUtil.formatAddDayReDate(TimeUtil.formatStrTODate(time), 1).getTime();
//	    	String sqlDNU = " select ifnull(count(id),0) as num from texas.t_human_info where createTime >="+startTime+" and createTime <="+endTime;
	    	String sqlDNU = " select count(DISTINCT account_id) as num from texas_log.player_login_log_"+time+" where reason=2  ";
	    	
	    	List<HashMap<String, Object>> listDNU = this.db.query(sqlDNU);
	    	logger.info("--------------sqlDNU=="+sqlDNU);
	    	logger.info("--------------listDNU.num=="+listDNU.get(0).get("num"));
	    	List<HashMap<String, Object>> list = this.db.query(sql);
	    	
	    	List<HashMap<String, Object>> list1 = this.db.query(sql1);
	    	
	    	List<HashMap<String, Object>> list2 = new ArrayList<HashMap<String, Object>>();
	    	
	    	 for(int i = 23;i>=0;i--){
	    		 HashMap<String, Object> mm = new HashMap<String, Object>();
    			 mm.put("time_dian", String.valueOf(i));
	    		 for(HashMap<String, Object> map : list){
	    			 if(map.containsKey("time_dian")){
	    				 int timeD = (Integer)map.get("time_dian");
	    				 if(timeD == i){
	    					 mm.put("value_role", map.get("value_role"));
	    					 break;
	    				 }
	    				
	    				 
	    			 }
	    		 }
	    		 for(HashMap<String, Object> map : list1){
	    			 if(map.containsKey("time_dian")){
	    				 int timeD = (Integer)map.get("time_dian");
	    				 if(timeD == i){
	    					 mm.put("valueData", map.get("valueData"));
	    					 break;
	    				 }
	    				
	    			 
	    			 }
	    		 }
	    		 
	    		 list2.add(mm);
	    	 }
	    	Map<String,List<HashMap<String, Object>>> reMap = new HashMap<String,List<HashMap<String, Object>>>();
	    	reMap.put("list2", list2);
	    	reMap.put("listDNU", listDNU);
			return reMap;
		}catch(Exception e){
			Map<String,List<HashMap<String, Object>>> reMap = new HashMap<String,List<HashMap<String, Object>>>();
			List<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("num", 0);
			list.add(map);
			reMap.put("list2", list);
			List<HashMap<String, Object>> listDNU = new ArrayList<HashMap<String,Object>>();
			listDNU.add(new HashMap<String,Object>());
	    	reMap.put("listDNU",listDNU);
			return reMap;
		}
	 }

    /**
     * 璁惧鍒嗗竷缁熻
     * @param time
     * @return
     * @throws APIException
     */
	public List<HashMap<String, Object>> equipment(String time,String equipment)  throws APIException {
		 List<HashMap<String, Object>> all = new ArrayList<HashMap<String, Object>>();
			
			//鍥藉鏁伴噺
			String sql = "select device_mode  from texas_log.player_login_log_"+time+"  GROUP BY device_mode";
			List<HashMap<String, Object>> list  = null;
			try{
				if(StringUtils.isBlank(equipment)){
					list = this.db.query(sql);
				}else{
					list=new ArrayList<HashMap<String,Object>>();
					HashMap<String,Object> device = new HashMap<String,Object>();
					device.put("device_mode", equipment);
					list.add(device);
				}
			}catch(Exception e){
				return null;
			}
			
			if(list != null){
				String sql1 = "";
				for(HashMap<String, Object> map : list){
					HashMap<String, Object> mm = new HashMap<String, Object>();
					 String device_mode = (String)map.get("device_mode");
					// DVU
				    sql1 += " (select '1' DVU, '-' DNU, '-' Income,ifnull(device_mode,'0') device_mode,ifnull(count(DISTINCT account_id),0) as valueData from texas_log.player_login_log_"+time+" where reason=1 ";
				    		if(StringUtils.isBlank(device_mode)){
				    			sql1+= " and device_mode is null )  union all";
				    		}else{
				    			sql1+= " and device_mode = '"+device_mode+"')  union all";
				    		}
				    //DNU
				    sql1 += " (select '-' DVU, '1' DNU, '-' Income,ifnull(device_mode,'0') device_mode,ifnull(count(DISTINCT account_id),0) as valueData from texas_log.player_login_log_"+time+" where reason=2 ";
						    if(StringUtils.isBlank(device_mode)){
				    			sql1+= " and device_mode is null )  union all";
				    		}else{
				    			sql1+= " and device_mode = '"+device_mode+"')  union all";
				    		}
				    //Income
				    sql1 += " (select '-' DVU, '-' DNU, '1' Income,ifnull(device_mode,'0') device_mode ,ifnull(SUM(money),0) as valueData from texas_log.new_recharge_log_"+time+" where 1=1 ";
						    if(StringUtils.isBlank(device_mode)){
				    			sql1+= " and device_mode is null )  union all";
				    		}else{
				    			sql1+= " and device_mode = '"+device_mode+"')  union all";
				    		}
				  
				}
				if(sql.length()<6){
		    		return null;
		    	}
				sql1 = sql1.substring(0,sql1.length()-6);
				List<HashMap<String, Object>> list1 = null;
				try{
					list1 = this.db.query(sql1);
				}catch(Exception e){
					return null;
				}
				//首先按国家分出来
				String cou = "-";
				for(HashMap<String, Object> map :list1){
					String device_mode = (String)map.get("device_mode");
					BigDecimal valueData = (BigDecimal)map.get("valueData");
					if("0".equals(device_mode) && valueData.intValue() == 0){
						continue;
					}
					if("0".equals(device_mode)){
						device_mode="模拟器";
					}
					if(!cou.equals(device_mode)){
						cou = device_mode;
						//组装 这里创建一个新的
						HashMap<String,Object> dataMap = new HashMap<String,Object>();
						assembleEquipmentMap(0,map,dataMap);
						all.add(dataMap);
					}else{
						for(HashMap<String,Object> reMap :all){
							String reCou = reMap.get("device_mode").toString();
							if(reCou.equals(device_mode)){
								assembleEquipmentMap(1,map,reMap);
							}
						}
					}
				}
			}
		
		return all;
	}
	private HashMap<String,Object> assembleEquipmentMap(int type,HashMap<String, Object> map,HashMap<String,Object> dataMap) {
		//不存在的情况下 要放 国家
		if(type == 0){
			String device_mode = (String)map.get("device_mode");
			if("0".equals(device_mode)){
				device_mode="模拟器";
			}
			dataMap.put("device_mode",device_mode);
		}
		String DVU = (String)map.get("DVU");
		if(DVU.equals("1")){
			dataMap.put("DVU", map.get("valueData"));
		}
		String DNU = (String)map.get("DNU");
		if(DNU.equals("1")){
			dataMap.put("DNU", map.get("valueData"));
		}
		String Income = (String)map.get("Income");
		if(Income.equals("1")){
			dataMap.put("Income", map.get("valueData"));
		}
		return dataMap;
		
	}
	public List<HashMap<String, Object>> equipment_COPY(String time,String equipment)  throws APIException {
		List<HashMap<String, Object>> all = new ArrayList<HashMap<String, Object>>();
		
		//鍥藉鏁伴噺
		String sql = "select device_mode  from texas_log.player_login_log_"+time+"  GROUP BY device_mode";
		List<HashMap<String, Object>> list  = null;
		try{
			if(StringUtils.isBlank(equipment)){
				list = this.db.query(sql);
			}else{
				list=new ArrayList<HashMap<String,Object>>();
				HashMap<String,Object> device = new HashMap<String,Object>();
				device.put("device_mode", equipment);
				list.add(device);
			}
		}catch(Exception e){
			return null;
		}
		
		if(list != null){
			
			for(HashMap<String, Object> map : list){
				HashMap<String, Object> mm = new HashMap<String, Object>();
				String device_mode = (String)map.get("device_mode");
				//鍥藉  鐧婚檰鏁伴噺
				String sql1 = "select count(DISTINCT account_id) as valueData from texas_log.player_login_log_"+time+" where reason=1 and device_mode = '"+device_mode+"'";
				List<HashMap<String, Object>> list1 = null;
				try{
					list1 = this.db.query(sql1);
				}catch(Exception e){
					return null;
				}
				Object obj1 = list1.get(0).get("valueData");
				if(obj1 != null){
					long valueData = (Long)(obj1);
					mm.put("DVU", valueData);
				}else{
					mm.put("DVU", 0);
				}
				//鍥藉  鍒涘缓鏁伴噺
				String sql2 = "select count(DISTINCT account_id) as valueData from texas_log.player_login_log_"+time+" where reason=2 and device_mode = '"+device_mode+"'";
				List<HashMap<String, Object>> list2 = this.db.query(sql2);
				Object obj2 = list2.get(0).get("valueData");
				if(obj2 != null){
					long valueData = (Long)(obj2);
					mm.put("DNU", valueData);
				}else{
					mm.put("DNU", 0);
				}
				
				//鍥藉 閽辩殑鏁伴噺
				String sql3 = "select SUM(money) as valueData from texas_log.new_recharge_log_"+time+" where device_mode = '"+device_mode+"'";
				List<HashMap<String, Object>> list3 = this.db.query(sql3);
				
				Object obj3 = list3.get(0).get("valueData");
				if(obj3 != null){
					BigDecimal valueData = (BigDecimal)(obj3);
					mm.put("Income", valueData.longValue());
				}else{
					mm.put("Income", 0);
				}
				
				
				
				if(device_mode.equals("0")){
					device_mode = "妯℃嫙鍣�";
				}
				mm.put("device_mode", device_mode);
				all.add(mm);
			}
		}
		
		return all;
	}

	public List<HashMap<String, Object>> versionClient(String time) throws APIException {
		 List<HashMap<String, Object>> all = new ArrayList<HashMap<String, Object>>();
			
			//鍥藉鏁伴噺
			String sql = "select client_version  from texas_log.player_login_log_"+time+"  GROUP BY client_version";
			List<HashMap<String, Object>> list = null;
			try{
				list = this.db.query(sql);
			}catch(Exception e){
				return null;
			}
			
			if(list != null){
				
				for(HashMap<String, Object> map : list){
					HashMap<String, Object> mm = new HashMap<String, Object>();
					 String client_version = (String)map.get("client_version");
					//鍥藉  鐧婚檰鏁伴噺
				    String sql1 = "select count(DISTINCT account_id) as valueData from texas_log.player_login_log_"+time+" where reason=1 and client_version = '"+client_version+"'";
				    List<HashMap<String, Object>> list1 = null;
				    try{
				    	list1 = this.db.query(sql1);
				    }catch(Exception e){
				    	return null;
				    }
				    Object obj1 = list1.get(0).get("valueData");
					  if(obj1 != null){
					    	long valueData = (Long)(obj1);
					    	mm.put("DVU", valueData);
					    }else{
					    	mm.put("DVU", 0);
					    }
				    //鍥藉  鍒涘缓鏁伴噺
				    String sql2 = "select count(DISTINCT account_id) as valueData from texas_log.player_login_log_"+time+" where reason=2 and client_version = '"+client_version+"'";
				    List<HashMap<String, Object>> list2 = this.db.query(sql2);
				    Object obj2 = list2.get(0).get("valueData");
					  if(obj2 != null){
					    	long valueData = (Long)(obj2);
					    	mm.put("DNU", valueData);
					   }else{
					    	mm.put("DNU", 0);
					  }
				    
				  //鍥藉 閽辩殑鏁伴噺
				  String sql3 = "select SUM(money) as valueData from texas_log.new_recharge_log_"+time+" where client_version = '"+client_version+"'";
				  List<HashMap<String, Object>> list3 = this.db.query(sql3);
				  
				  Object obj3 = list3.get(0).get("valueData");
				  if(obj3 != null){
					  BigDecimal valueData = (BigDecimal)(obj3);
				    	mm.put("Income", valueData.longValue());
				    }else{
				    	mm.put("Income", 0);
				    }
				  
		
				  
				 if(client_version.equals("0")){
					 client_version = "妯℃嫙鍣�";
				 }
				 mm.put("client_version", client_version);
				 all.add(mm);
				}
			}
		
		return all;
	}

	public List<HashMap<String, Object>> channel(String time,String country) throws APIException{
		
		//鍥藉鏁伴噺
		String sql = "select countries  from texas_log.player_login_log_"+time+"  GROUP BY countries";
		List<HashMap<String, Object>> list = null;
		try{
			if(StringUtils.isBlank(country)){
				list = this.db.query(sql);
			}else{
				HashMap<String,Object> cou = new HashMap<String,Object>();
				cou.put("countries", country);
				list = new ArrayList<HashMap<String,Object>>();
				list.add(cou);
			}
		}catch(Exception e){
			return null;
		}
		
		List<HashMap<String,Object>> relist = new ArrayList<HashMap<String,Object>>();
		if(list != null){
			String sql1 = "";
			for(HashMap<String, Object> map : list){
				HashMap<String, Object> mm = new HashMap<String, Object>();
				 String countries = (String)map.get("countries");
				////国家  登陆数量  DVU
			    sql1 += " (select '1' DVU, '-' DNU, '-' Income,'-' DPU,ifnull(countries,'0') countries,ifnull(count(DISTINCT account_id),0) as valueData from texas_log.player_login_log_"+time+" where reason=1 ";
			    		if(StringUtils.isBlank(countries)){
			    			sql1+= " and countries is null )  union all";
			    		}else{
			    			sql1+= "and countries = '"+countries+"')  union all";
			    		}
			    //国家  创建数量  DNU
			    sql1+= " (select '-' DVU, '1' DNU, '-' Income,'-' DPU,ifnull(countries,'0') countries,ifnull(count(DISTINCT account_id),0) as valueData from texas_log.player_login_log_"+time+" where reason=2 ";
					    if(StringUtils.isBlank(countries)){
			    			sql1+= " and countries is null )  union all";
			    		}else{
			    			sql1+= "and countries = '"+countries+"')  union all";
			    		}
			    //国家 钱的数量  Income
			    sql1+= " (select '-' DVU, '-' DNU, '1' Income,'-' DPU,ifnull(countries,'0') countries,ifnull(SUM(money),0) as valueData from texas_log.new_recharge_log_"+time+" where 1=1 ";
					    if(StringUtils.isBlank(countries)){
			    			sql1+= " and countries is null )  union all";
			    		}else{
			    			sql1+= "and countries = '"+countries+"')  union all";
			    		}
			    //国家 钱的数量  DPU
			    sql1+= " (select '-' DVU, '-' DNU, '-' Income,'1' DPU,ifnull(countries,'0') countries,ifnull(COUNT(DISTINCT account_id),0) as valueData from texas_log.new_recharge_log_"+time+" where 1=1 ";
					    if(StringUtils.isBlank(countries)){
			    			sql1+= " and countries is null )  union all";
			    		}else{
			    			sql1+= "and countries = '"+countries+"')  union all";
			    		}
			}
			if(sql.length()<6){
	    		return null;
	    	}
			sql1 = sql1.substring(0,sql1.length()-6);
			List<HashMap<String, Object>> list1 = null;
			try{
				list1 = this.db.query(sql1);
			}catch(Exception e){
				return null;
			}
			//首先按国家分出来
			String cou = "-";
			for(HashMap<String, Object> map :list1){
				String countries = (String)map.get("countries");
				BigDecimal valueData = (BigDecimal)map.get("valueData");
				if("0".equals(countries) && valueData.intValue() == 0){
					continue;
				}
				if("0".equals(countries)){
					countries="模拟器";
				}
				if(!cou.equals(countries)){
					cou = countries;
					//组装 这里创建一个新的
					HashMap<String,Object> dataMap = new HashMap<String,Object>();
					assembleMap(0,map,dataMap);
					relist.add(dataMap);
				}else{
					for(HashMap<String,Object> reMap :relist){
						String reCou = reMap.get("countries").toString();
						if(reCou.equals(countries)){
							assembleMap(1,map,reMap);
						}
					}
				}
			}
			
		}
		
		return relist;
	}
	private HashMap<String,Object> assembleMap(int type,HashMap<String, Object> map,HashMap<String,Object> dataMap) {
		//不存在的情况下 要放 国家
		if(type == 0){
			String countries = (String)map.get("countries");
			if("0".equals(countries)){
				countries="模拟器";
			}
			dataMap.put("countries",countries);
		}
		String DVU = (String)map.get("DVU");
		if(DVU.equals("1")){
			dataMap.put("DVU", map.get("valueData"));
		}
		String DNU = (String)map.get("DNU");
		if(DNU.equals("1")){
			dataMap.put("DNU", map.get("valueData"));
		}
		String Income = (String)map.get("Income");
		if(Income.equals("1")){
			dataMap.put("Income", map.get("valueData"));
		}
		String DPU = (String)map.get("DPU");
		if(DPU.equals("1")){
			dataMap.put("DPU", map.get("valueData"));
		}
		return dataMap;
		
	}
	public List<HashMap<String, Object>> channel_COPY(String time,String country) throws APIException{
		List<HashMap<String, Object>> all = new ArrayList<HashMap<String, Object>>();
		
		//鍥藉鏁伴噺
		String sql = "select countries  from texas_log.player_login_log_"+time+"  GROUP BY countries";
		List<HashMap<String, Object>> list = null;
		try{
			if(StringUtils.isBlank(country)){
				list = this.db.query(sql);
			}else{
				HashMap<String,Object> cou = new HashMap<String,Object>();
				cou.put("countries", country);
				list = new ArrayList<HashMap<String,Object>>();
				list.add(cou);
			}
		}catch(Exception e){
			return null;
		}
		
		if(list != null){
			
			for(HashMap<String, Object> map : list){
				HashMap<String, Object> mm = new HashMap<String, Object>();
				String countries = (String)map.get("countries");
				//鍥藉  鐧婚檰鏁伴噺
				String sql1 = "select count(DISTINCT account_id) as valueData from texas_log.player_login_log_"+time+" where reason=1 and countries = '"+countries+"'";
				List<HashMap<String, Object>> list1 = null;
				try{
					list1 = this.db.query(sql1);
				}catch(Exception e){
					return null;
				}
				Object obj1 = list1.get(0).get("valueData");
				if(obj1 != null){
					long valueData = (Long)(obj1);
					mm.put("DVU", valueData);
				}else{
					mm.put("DVU", 0);
				}
				//鍥藉  鍒涘缓鏁伴噺
				String sql2 = "select count(DISTINCT account_id) as valueData from texas_log.player_login_log_"+time+" where reason=2 and countries = '"+countries+"'";
				List<HashMap<String, Object>> list2 = this.db.query(sql2);
				Object obj2 = list2.get(0).get("valueData");
				if(obj2 != null){
					long valueData = (Long)(obj2);
					mm.put("DNU", valueData);
				}else{
					mm.put("DNU", 0);
				}
				
				//鍥藉 閽辩殑鏁伴噺
				String sql3 = "select SUM(money) as valueData from texas_log.new_recharge_log_"+time+" where countries = '"+countries+"'";
				List<HashMap<String, Object>> list3 = this.db.query(sql3);
				
				Object obj3 = list3.get(0).get("valueData");
				if(obj3 != null){
					BigDecimal valueData = (BigDecimal)(obj3);
					mm.put("Income", valueData.longValue());
				}else{
					mm.put("Income", 0);
				}
				
				//鍥藉 閽辩殑鏁伴噺
				String sql4 = "select COUNT(DISTINCT account_id) as valueData from texas_log.new_recharge_log_"+time+" where countries = '"+countries+"'";
				List<HashMap<String, Object>> list4 = this.db.query(sql4);
				Object obj4 = list4.get(0).get("valueData");
				if(obj4 != null){
					long valueData = (Long)(obj4);
					mm.put("DPU", valueData);
				}else{
					mm.put("DPU", 0);
				}
				
				if(countries.equals("")){
					countries = "妯℃嫙鍣�";
				}
				mm.put("countries", countries);
				all.add(mm);
			}
		}
		
		return all;
	}

	public List<HashMap<String, Object>> orderDetails(String time,String player) throws APIException{
		
		//璁㈠崟鏁版嵁
	    String sql = "select account_id,money,countries,level,createTime,game_order_id  from texas_log.new_recharge_log_"+time;
	    if(StringUtils.isNoneBlank(player)){
	    	sql += " where account_id like'%"+player+"%'";
	    }
	    List<HashMap<String, Object>> list = null;	
	    try{
	    	
	    	list = this.db.query(sql);
	    }catch(Exception e){
	    	return null;
	    }
	    
	    if(list == null){
	    	list = new ArrayList<HashMap<String, Object>>();
	    }else{
	    	for(HashMap<String, Object> map : list){
	    		Long createTime = (Long)map.get("createTime");
	    		SimpleDateFormat simpledate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				
	    		map.put("createTime", simpledate.format(new Date(createTime)));
	    	}
	    }
		return list;
	}
	public List<HashMap<String, Object>> retained(String time,Object country,Object device,List<HashMap<String, Object>> date_list) throws APIException, ParseException{
	
		int deviceInt = Integer.valueOf(String.valueOf(device));
		
		List<HashMap<String, Object>> all = new ArrayList<HashMap<String, Object>>();
		
		 //姣忓ぉ鏂板鍔犵殑鏁伴噺
	    String newRole = "select count(DISTINCT account_id) as valueData from texas_log.player_login_log_"+time+" where reason=2  ";
	    /**
	     * 0：所有， 不加sql
	     * 1：安卓，
	     * 2：IOS
	     */
	    if(deviceInt == 1){
	    	newRole+=" and device_mode !='iPhone' and device_mode !='iPad'";
	    }else if(deviceInt == 2){
	    	newRole+="  and (device_mode ='iPhone' or device_mode ='iPad')";
	    }
	    if(country != null){
	    	String coun = country.toString();
	    	if(StringUtils.isNotBlank(coun)){
	    		newRole+=" and countries like '%"+coun+"%'";
	    	}
	    }
//		if(country != null){
//			newRole += " and countries'%"+country.toString()+"%'";
//		}
	    List<HashMap<String, Object>> listnewRole  = null;
	    try{
	    	listnewRole = this.db.query(newRole);
	    }catch(Exception e){
	    	return null;
	    }
	    if(listnewRole!= null){
    		for(HashMap<String, Object> map : listnewRole){
    			map.put("reason", 1);
    			all.add(map);
    		}
    	}
		
	    //璧峰鏃堕棿
	    long startTime = TimeUtil.getYMDHMTime(time);
	    long startTime1 = startTime+24*60*60*1000;
	    long startTime3 = startTime+24*60*60*1000*2;
	    long startTime4 = startTime+24*60*60*1000*3;
	    long startTime5 = startTime+24*60*60*1000*4;
	    long startTime7 = startTime+24*60*60*1000*6;
	    long startTime10 = startTime+24*60*60*1000*9;
	    long startTime15 = startTime+24*60*60*1000*14;
	    
	    long startTime30 = startTime+24*60*60*1000*24;
	   
	    
	    
	    String time1 = TimeUtil.formatYMDHMTime(startTime1);
	    String time3 = TimeUtil.formatYMDHMTime(startTime3);
	    String time4 = TimeUtil.formatYMDHMTime(startTime4);
	    String time5 = TimeUtil.formatYMDHMTime(startTime5);
	    String time7 = TimeUtil.formatYMDHMTime(startTime7);
	    String time10 = TimeUtil.formatYMDHMTime(startTime10);
	    String time15 = TimeUtil.formatYMDHMTime(startTime15);
	    
	    String time33 = TimeUtil.formatYMDHMTime(startTime30);
	    long startTime33 = TimeUtil.getYMDHMTime(time33);
	    
	    String time30 = TimeUtil.formatYMDHMTime(startTime33+24*60*60*1000*6);
	    
	    long timetime = TimeUtil.getYMDHMTime(TimeUtil.formatYMDHMTime(System.currentTimeMillis()));
	    
	    
	  
	    
		//娆℃棩鐣欏瓨
		String nextDay = "select count(DISTINCT(account_id)) as  valueData  from texas_log.player_keep_log_"+time1+"   where login_day = 1";
		 if(deviceInt == 1){
		    	newRole+=" and device_mode !='iPhone' and device_mode !='iPad'";
		    }else if(deviceInt == 2){
		    	newRole+="  and (device_mode ='iPhone' or device_mode ='iPad')";
		    }
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
				if(startTime1 <= timetime && getvalidity(time1,date_list)){
					try{
						list = this.db.query(nextDay);
					}catch(Exception e){
						return null;
					}
				}else{
					HashMap<String, Object> map = new HashMap<String, Object>();
					map.put("valueData", 0);
					list.add(map);
				}
		
		if(list!= null){
    		for(HashMap<String, Object> map : list){
    			map.put("reason", 2);
    			all.add(map);
    		}
    	}
		//3鏃ョ暀瀛�
		String nextDay3 = "select count(DISTINCT(account_id)) as  valueData  from texas_log.player_keep_log_"+time3+"   where login_day = 2";
		 if(deviceInt == 1){
		    	newRole+=" and device_mode !='iPhone' and device_mode !='iPad'";
		    }else if(deviceInt == 2){
		    	newRole+="  and (device_mode ='iPhone' or device_mode ='iPad')";
		    }
		List<HashMap<String, Object>> list3 = new ArrayList<HashMap<String, Object>>();
		if(startTime3 <= timetime && getvalidity(time3,date_list)){
			list3 = this.db.query(nextDay3);
		}else{
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("valueData", 0);
			list3.add(map);
		}
		
		if(list3!= null){
    		for(HashMap<String, Object> map : list3){
    			map.put("reason", 3);
    			all.add(map);
    		}
    	}
		//4鏃ョ暀瀛�
		String nextDay4 = "select count(DISTINCT(account_id)) as  valueData  from texas_log.player_keep_log_"+time4+"   where login_day = 3";
		 if(deviceInt == 1){
		    	newRole+=" and device_mode !='iPhone' and device_mode !='iPad'";
		    }else if(deviceInt == 2){
		    	newRole+="  and (device_mode ='iPhone' or device_mode ='iPad')";
		    }
		List<HashMap<String, Object>> list4 = new ArrayList<HashMap<String, Object>>();
		if(startTime4 <= timetime && getvalidity(time4,date_list)){
			list4 = this.db.query(nextDay4);
		}else{
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("valueData", 0);
			list4.add(map);
		}
		
		if(list4!= null){
    		for(HashMap<String, Object> map : list4){
    			map.put("reason", 4);
    			all.add(map);
    		}
    	}
		//5鏃ョ暀瀛�
		String nextDay5 = "select count(DISTINCT(account_id)) as  valueData  from texas_log.player_keep_log_"+time5+"   where login_day = 4";
		 if(deviceInt == 1){
		    	newRole+=" and device_mode !='iPhone' and device_mode !='iPad'";
		    }else if(deviceInt == 2){
		    	newRole+="  and (device_mode ='iPhone' or device_mode ='iPad')";
		    }
		List<HashMap<String, Object>> list5 = new ArrayList<HashMap<String, Object>>();
		if(startTime5 <= timetime && getvalidity(time5,date_list)){
			list5 = this.db.query(nextDay5);
		}else{
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("valueData", 0);
			list5.add(map);
		}
		
		if(list5!= null){
    		for(HashMap<String, Object> map : list5){
    			map.put("reason", 5);
    			all.add(map);
    		}
    	}
		//7鏃ョ暀瀛�
		String nextDay7 = "select count(DISTINCT(account_id)) as  valueData  from texas_log.player_keep_log_"+time7+"   where login_day = 6";
		 if(deviceInt == 1){
		    	newRole+=" and device_mode !='iPhone' and device_mode !='iPad'";
		    }else if(deviceInt == 2){
		    	newRole+="  and (device_mode ='iPhone' or device_mode ='iPad')";
		    }
		List<HashMap<String, Object>> list7 = new ArrayList<HashMap<String, Object>>();
		if(startTime7 <= timetime && getvalidity(time7,date_list)){
			list7 = this.db.query(nextDay7);
		}else{
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("valueData", 0);
			list7.add(map);
		}
		
		if(list7!= null){
    		for(HashMap<String, Object> map : list7){
    			map.put("reason", 6);
    			all.add(map);
    		}
    	}
		//10鏃ョ暀瀛�
		String nextDay10 = "select count(DISTINCT(account_id)) as  valueData  from texas_log.player_keep_log_"+time10+"   where login_day = 9";
		 if(deviceInt == 1){
		    	newRole+=" and device_mode !='iPhone' and device_mode !='iPad'";
		    }else if(deviceInt == 2){
		    	newRole+="  and (device_mode ='iPhone' or device_mode ='iPad')";
		    }
		List<HashMap<String, Object>> list10 = new ArrayList<HashMap<String, Object>>();
		if(startTime10 <= timetime && getvalidity(time10,date_list)){
			list10 = this.db.query(nextDay10);
		}else{
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("valueData", 0);
			list10.add(map);
		}
		
		if(list10!= null){
    		for(HashMap<String, Object> map : list10){
    			map.put("reason", 7);
    			all.add(map);
    		}
    	}
		//15鏃ョ暀瀛�
		String nextDay15 = "select count(DISTINCT(account_id)) as  valueData  from texas_log.player_keep_log_"+time15+"   where login_day = 14";
		 if(deviceInt == 1){
		    	newRole+=" and device_mode !='iPhone' and device_mode !='iPad'";
		    }else if(deviceInt == 2){
		    	newRole+="  and (device_mode ='iPhone' or device_mode ='iPad')";
		    }
		List<HashMap<String, Object>> list15 = new ArrayList<HashMap<String, Object>>();
		if(startTime15 <= timetime && getvalidity(time15,date_list)){
			list15 = this.db.query(nextDay15);
		}else{
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("valueData", 0);
			list15.add(map);
		}
		
		if(list15!= null){
    		for(HashMap<String, Object> map : list15){
    			map.put("reason", 8);
    			all.add(map);
    		}
    	}
		//30鏃ョ暀瀛�
		String nextDay30 = "select count(DISTINCT(account_id)) as  valueData  from texas_log.player_keep_log_"+time30+"   where login_day = 30";
		 if(deviceInt == 1){
		    	newRole+=" and device_mode !='iPhone' and device_mode !='iPad'";
		    }else if(deviceInt == 2){
		    	newRole+="  and (device_mode ='iPhone' or device_mode ='iPad')";
		    }
		List<HashMap<String, Object>> list30 = new ArrayList<HashMap<String, Object>>();
		if(startTime30 <= timetime && getvalidity(time30,date_list)){
			list30 = this.db.query(nextDay30);
		}else{
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("valueData", 0);
			list30.add(map);
		}
		
		if(list30!= null){
    		for(HashMap<String, Object> map : list30){
    			map.put("reason", 9);
    			all.add(map);
    		}
    	}
		logger.info("------------------------------time:"+time);
		return all;
	}

	private boolean getvalidity(String time,List<HashMap<String, Object>> date_list){
		for(HashMap<String, Object> map : date_list){
			String date = (String)map.get("date");
			if(time.equals(date)){
				return true;
			}
		}
		return false;
	}
	public List<HashMap<String, Object>> ichartOnline(String date) {
		try{
			String sql = "select time_dian,value_role,countries,ip_countries as ipCountries from texas_log.player_onle_log_"+date;
			return this.db.query(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	public List<NewPeopleVO>  queryPerCountryPeople(List<HashMap<String, Object>> date_list) {
		String newRole = "";
		for(HashMap<String, Object> map:date_list){
			 //璧峰鏃堕棿
			String date = map.get("date").toString();
		    long startTime = TimeUtil.getYMDHMTime(date);
		    long startTime1 = startTime+24*60*60*1000;
		    long startTime3 = startTime+24*60*60*1000*2;
		    long startTime4 = startTime+24*60*60*1000*3;
		    long startTime5 = startTime+24*60*60*1000*4;
		    long startTime7 = startTime+24*60*60*1000*6;
		    long startTime10 = startTime+24*60*60*1000*9;
		    long startTime15 = startTime+24*60*60*1000*14;
		    long startTime30 = startTime+24*60*60*1000*24;
		   
		    
		    
		    String time1 = TimeUtil.formatYMDHMTime(startTime1);
		    String time3 = TimeUtil.formatYMDHMTime(startTime3);
		    String time4 = TimeUtil.formatYMDHMTime(startTime4);
		    String time5 = TimeUtil.formatYMDHMTime(startTime5);
		    String time7 = TimeUtil.formatYMDHMTime(startTime7);
		    String time10 = TimeUtil.formatYMDHMTime(startTime10);
		    String time15 = TimeUtil.formatYMDHMTime(startTime15);
		    
		    String time33 = TimeUtil.formatYMDHMTime(startTime30);
		    long startTime33 = TimeUtil.getYMDHMTime(time33);
		    
		    String time30 = TimeUtil.formatYMDHMTime(startTime33+24*60*60*1000*6);
		    
		    long timetime = TimeUtil.getYMDHMTime(TimeUtil.formatYMDHMTime(System.currentTimeMillis()));
			
			newRole += " (select t1.date,t1.countries,t1.valueData as peopleNum,t1.ipCountries, t1.channel_type channelType, "
					+ " ifnull(t2.valueData,'0') as remain1, "
					+ " ifnull(t3.valueData,'0') as remain3, "
					+ " ifnull(t4.valueData,'0') as remain4, "
					+ " ifnull(t5.valueData,'0') as remain5, "
					+ " ifnull(t6.valueData,'0') as remain7, "
					+ " ifnull(t7.valueData,'0') as remain10, "
					+ " ifnull(t8.valueData,'0') as remain15, "
					+ " ifnull(t9.valueData,'0') as remain30 "
					+  " from "
					+  "  ( select '"+date+"' as date,countries,ip_countries ipCountries,channel_type,ifnull( count(DISTINCT account_id),'0') as valueData from texas_log.player_login_log_"+date+" where reason=2 group by ip_countries,channel_type ) t1 "
					+  " left join ";
					if(startTime1 <= timetime && getvalidity(time1,date_list)){
						newRole+=  " ( select countries,ip_countries ipCountries,ifnull(count(DISTINCT(account_id)),0) as  valueData  from texas_log.player_keep_log_"+time1+"   where login_day = 1 group by ip_countries,channel_type) t2 ";
					}else{
						newRole+=   " ( select countries,ip_countries ipCountries,'0' as  valueData  from texas_log.player_login_log_"+date+"   group by ip_countries,channel_type) t2 ";
						
					}
					newRole+=  " on t1.ipCountries=t2.ipCountries left join ";
					
					
					if(startTime3 <= timetime && getvalidity(time3,date_list)){
						newRole+=  " (select countries,ip_countries ipCountries,ifnull(count(DISTINCT(account_id)),'0') as  valueData  from texas_log.player_keep_log_"+time3+"   where login_day = 2 group by ip_countries,channel_type) t3 ";
					}else{
						newRole+=   " ( select countries,ip_countries ipCountries,'0' as  valueData  from texas_log.player_login_log_"+date+"   group by ip_countries,channel_type) t3 ";
					}
					newRole+=  " on t1.ipCountries=t3.ipCountries left join ";
					
					
					if(startTime4 <= timetime && getvalidity(time4,date_list)){
						newRole+=  " (select countries,ip_countries ipCountries,ifnull(count(DISTINCT(account_id)),'0') as  valueData  from texas_log.player_keep_log_"+time4+"   where login_day = 3 group by ip_countries,channel_type) t4 ";
					}else{
						newRole+=   " ( select countries,ip_countries ipCountries,'0' as  valueData  from texas_log.player_login_log_"+date+"   group by ip_countries,channel_type) t4 ";
					}
					newRole+=  " on t1.ipCountries=t4.ipCountries left join ";
							
					
					if(startTime5 <= timetime && getvalidity(time5,date_list)){
						newRole+=  " (select countries,ip_countries ipCountries,ifnull(count(DISTINCT(account_id)),'0') as  valueData  from texas_log.player_keep_log_"+time5+"   where login_day = 4 group by ip_countries,channel_type) t5 ";
					}else{
						newRole+=   " ( select countries,ip_countries ipCountries,'0' as  valueData  from texas_log.player_login_log_"+date+"   group by ip_countries,channel_type) t5 ";
					}
					newRole+=  " on t1.ipCountries=t5.ipCountries left join ";
							
							
					if(startTime7 <= timetime && getvalidity(time7,date_list)){
						newRole+=   " (select countries,ip_countries ipCountries,ifnull(count(DISTINCT(account_id)),'0') as  valueData  from texas_log.player_keep_log_"+time7+"   where login_day = 6 group by ip_countries,channel_type) t6 ";
					}else{
						newRole+=   " ( select countries,ip_countries ipCountries,'0' as  valueData  from texas_log.player_login_log_"+date+"   group by ip_countries,channel_type) t6 ";
					}
					newRole+=  " on t1.ipCountries=t6.ipCountries left join ";
							
							
					if(startTime10 <= timetime && getvalidity(time10,date_list)){
						newRole+=  " (select countries,ip_countries ipCountries,ifnull(count(DISTINCT(account_id)),'0') as  valueData  from texas_log.player_keep_log_"+time10+"   where login_day = 9 group by ip_countries,channel_type) t7 ";
					}else{
						newRole+=   " ( select countries,ip_countries ipCountries,'0' as  valueData  from texas_log.player_login_log_"+date+"   group by ip_countries,channel_type) t7 ";
					}
					newRole+=  " on t1.ipCountries=t7.ipCountries left join ";
							
							
					if(startTime15 <= timetime && getvalidity(time15,date_list)){
						newRole+=  " (select countries,ip_countries ipCountries,ifnull(count(DISTINCT(account_id)),'0') as  valueData  from texas_log.player_keep_log_"+time15+"   where login_day = 14 group by ip_countries,channel_type) t8 ";
					}else{
						newRole+=   " ( select countries,ip_countries ipCountries,'0' as  valueData  from texas_log.player_login_log_"+date+"   group by ip_countries,channel_type) t8 ";
					}
					newRole+=  " on t1.ipCountries=t8.ipCountries left join ";
							
							
					if(startTime30 <= timetime && getvalidity(time30,date_list)){
						newRole+=  " (select countries,ip_countries ipCountries,ifnull(count(DISTINCT(account_id)),'0') as  valueData  from texas_log.player_keep_log_"+time30+"   where login_day = 30 group by ip_countries,channel_type) t9 ";
					}else{
						newRole+=   " ( select countries,ip_countries ipCountries,'0' as  valueData  from texas_log.player_login_log_"+date+"   group by ip_countries,channel_type) t9 ";
					}
					newRole+=  " on t1.ipCountries=t9.ipCountries  "
							
					+  " )     union all";
		}
		newRole=newRole.substring(0,newRole.length()-11);
		
		try {
			System.out.println(newRole);
			logger.info(newRole);
//			List<HashMap<String,Object>> peopleList = this.db.query(newRole);
			List<NewPeopleVO> newPeopleXlsx= this.db.query(newRole,NewPeopleVO.class);
			return newPeopleXlsx;
		} catch (APIException e) {
			e.printStackTrace();
		}
		return null;
	}
	public List<Country> getAllCountry() {
		String sql = " select id,country,langDesc,icon,nameId from texas_activity.country ";
		try {
			List<Country> countryList= this.db.query(sql,Country.class);
			return countryList;
		} catch (APIException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 一阶  二阶
	 * @param date
	 * @param date_list
	 * @param start
	 * @param week
	 * @return
	 */
	private String loginRate(String date,
			List<HashMap<String, Object>> date_list,int start,int week){
		 long timetime = TimeUtil.getYMDHMTime(TimeUtil.formatYMDHMTime(System.currentTimeMillis()));
		    long startTime = TimeUtil.getYMDHMTime(date);
		    String sql = "";
		    if(week < 2){
		    	 sql= "( select distinct account_id from texas_log.player_login_log_"+date+" where reason=2 ) union all ";
		    }
		    for(int i=start;i<6*week;i++){
		    	long startTime1 = startTime+24*60*60*1000*(i+1);
		    	String time1 = TimeUtil.formatYMDHMTime(startTime1);
		    	if(startTime1 <= timetime && getvalidity(time1,date_list)){
		    		sql+= " ( select distinct account_id from texas_log.player_keep_log_"+time1+"   where login_day = "+(i+1)+" )   union all ";
				}
		    }
		    //第二周把最后一天加上
		    if(week >= 2){
		    	long startTime1 = startTime+24*60*60*1000*(week*6+1);
		    	String time1 = TimeUtil.formatYMDHMTime(startTime1);
		    	if(startTime1 <= timetime && getvalidity(time1,date_list)){
		    		sql+= " ( select distinct account_id from texas_log.player_keep_log_"+time1+"   where login_day = "+(week*6+1)+" )   union all ";
				}
		    }
		    if(StringUtils.isBlank(sql)){
		    	return null;
		    }
		    int lastIndex = sql.lastIndexOf("union all");
		    sql = sql.substring(0,lastIndex);
		    return sql;
	}
	//一阶
	public Map<String,String> oneLoginRate(String date,
			List<HashMap<String, Object>> date_list)  {
		Map<String,String> result = new HashMap<String,String>();
		result.put("oneLoginRate", "0");
		result.put("numerator", "0");
		result.put("denominator", "0");
		String sql = loginRate(date,date_list,0,1);
		if(StringUtils.isBlank(sql)){
			return result;
		}
	    try {
	    	//大于等于3天的
	    	String sql1 = " select t1.account_id,t1.loginNum from (select t1.account_id,count(t1.account_id) loginNum from ("+sql+") t1 group by t1.account_id) t1 where t1.loginNum >=3  order by t1.account_id";
	    	List<HashMap<String,Object>> list1 = new ArrayList<HashMap<String,Object>>();
			list1 = this.db.query(sql1);
			//大于等于2天的
			String sql2 = " select t1.account_id,t1.loginNum from (select t1.account_id,count(t1.account_id) loginNum from ("+sql+") t1 group by t1.account_id) t1  where t1.loginNum >=2  order by t1.account_id";
			List<HashMap<String,Object>> list2 = new ArrayList<HashMap<String,Object>>();
			list2 = this.db.query(sql2);
			double l1 = Double.valueOf(list1.size());
			double l2 = Double.valueOf(list2.size());
			double rate= 0;
			if(l2 != 0){
				rate= l1/l2;
			}
			result.put("oneLoginRate", rate+"");
			result.put("numerator", l1+"");
			result.put("denominator", l2+"");
			return result;
		} catch (APIException e) {
			e.printStackTrace();
		}
	    return result;
	}
	//二阶
	public Map<String,String> twoLoginRate(String date,
			List<HashMap<String, Object>> date_list) {
		Map<String,String> result = new HashMap<String,String>();
		result.put("twoLoginRate", "0");
		result.put("numerator", "0");
		result.put("denominator", "0");
		String sql = loginRate(date,date_list,0,1);
		String ssql = loginRate(date,date_list,6,2);
		
		if(StringUtils.isBlank(sql)){
			return result;
		}
	    try {
	    	//第一周大于等于3天的
	    	String sql1 = " select t1.account_id,t1.loginNum from (select t1.account_id,count(t1.account_id) loginNum from ("+sql+") t1  group by t1.account_id) t1 where t1.loginNum >=3  order by t1.account_id";
	    	List<HashMap<String,Object>> list1 = new ArrayList<HashMap<String,Object>>();
			list1 = this.db.query(sql1);
			//第二周 还有登陆的
			String accountIds = "";
			for(HashMap<String,Object> map:list1){
				long account_id = (long)map.get("account_id");
				accountIds+=account_id+",";
			}
			if(StringUtils.isBlank(accountIds)){
				return result;
			}
			accountIds = accountIds.substring(0,accountIds.lastIndexOf(","));
			String ssql1 = "select t1.account_id,count(t1.account_id) loginNum from ("+ssql+") t1 where t1.account_id in ("+accountIds+") group by t1.account_id order by t1.account_id";
			List<HashMap<String,Object>> list11 = new ArrayList<HashMap<String,Object>>();
			list11 = this.db.query(ssql1);
			logger.info("twoLoginRate--------------ssql1="+ssql1);
			//大于等于2天的
			String sql2 = " select t1.account_id,t1.loginNum from (select t1.account_id,count(t1.account_id) loginNum from ("+sql+") t1 group by t1.account_id ) t1 where t1.loginNum >=3 order by t1.account_id";
			List<HashMap<String,Object>> list2 = new ArrayList<HashMap<String,Object>>();
			logger.info("twoLoginRate--------------sql2="+sql2);
			list2 = this.db.query(sql2);
			double l1 = Double.valueOf(list11.size());
			double l2 = Double.valueOf(list2.size());
			double rate= 0;
			if(l2 != 0){
				rate= l1/l2;
			}
			result.put("twoLoginRate", rate+"");
			result.put("numerator", l1+"");
			result.put("denominator", l2+"");
			return result;
		} catch (APIException e) {
			e.printStackTrace();
		}
	    return result;
		
	}
	public List<SlotAgeVO> queryslotAge(String type) {
		String sql = "select t1.charId,t1.slotName,t1.type,IFNULL(t2.age,-1) as age, "
					 +" count(t2.age)  as num  "
					 +" from "
					 +"  ( "
					 +"  select  "
					 +" 		 t1.slotType as type , "
					 +" 	 t3.langDesc as slotName, "
					 +"      t3.type as tType,"
					 +" 	 t2.charId "
					 +" 	 from texas.t_slot t1  "
					 +" 	 LEFT JOIN texas.t_human_slot t2 on t1.id=t2.slotId  "
					 +" 	 left join texas_activity.t_slot_list t3 on t1.slotType = t3.id "
					 +" ) t1   "
					 +"  LEFT JOIN  texas.t_human_info t2 on t1.charId= t2.passportId "
					 +" where t2.age is not null ";
					 
					 if(StringUtils.isNotBlank(type)){
						 sql +=" and t1.tType =1  ";
					 }
					 sql+="  GROUP BY t1.type,t2.age";
		
		try {
			List<SlotAgeVO> SlotAgeVOS = this.db.query(sql,SlotAgeVO.class);
			return SlotAgeVOS;
		} catch (APIException e) {
			e.printStackTrace();
		}
		return null;
	}
	public List<SlotSexVO> queryslotSex(String sType) {
		
		String sql = "  select t2.charId, t3.langDesc as slotName,t1.slotType as type ,"
				    +"  IFNULL(t4.girlFlag,-1) as sex,"
					+"  count(t4.id) as num"
					+"  from texas.t_slot t1 "
					+"  LEFT JOIN texas.t_human_slot t2 on t1.id=t2.slotId "
					+"  left join texas_activity.t_slot_list t3 on t1.slotType = t3.id"
				    +"  LEFT JOIN  texas.t_human_info t4 on t2.charId= t4.passportId"
				    +"  where t4.girlFlag is not null";
				   if(StringUtils.isNotBlank(sType)){
					   sql += " and t3.type="+sType;
				   }
					sql+="  GROUP BY t1.slotType,t4.girlFlag";
	
		try {
			List<SlotSexVO> SlotSexVOS = this.db.query(sql,SlotSexVO.class);
			return SlotSexVOS;
		} catch (APIException e) {
			e.printStackTrace();
		}
		return null;
	}
	public List<SlotPayVO> queryslotPay(String chartId) {
		String sql = 
				 " select "
				+" t1.channelId,"
				+" t1.productId as pid,"
				+" t1.orderStatus,"
				+" t1.charId,"
				+" t3.langDesc,"
				+" t3.productId,"
				+" t3.itemNum,"
				+" t3.num as dollar,"
				+" t3.vipPoint,"
				+" t2.name,"
				+" t2.girlFlag,"
				+" t2.countries,"
				+" t1.createTime,"
				+" t1.updateTime"
				+" from texas.t_human_recharge_order t1 "
				+" LEFT JOIN texas.t_human_info t2 on t1.charId=t2.passportId "
				+" LEFT JOIN texas_activity.t_recharge t3 on t1.channelId=t3.channelId and t1.productId=t3.pid"
				
				+" where t1.orderStatus=1 ";
		if(StringUtils.isNotBlank(chartId)){
			sql += " and (t1.charId='"+chartId+"' or t2.countries like '%"+chartId+"%')";
		}
			sql += " order by t1.createTime desc ";
		try {
			List<SlotPayVO> slotPayVOS = this.db.query(sql,SlotPayVO.class);
			for(SlotPayVO slotPayVO:slotPayVOS){
				slotPayVO.setTimeStr();
			}
			return slotPayVOS;
		} catch (APIException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}