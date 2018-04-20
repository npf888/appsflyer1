package com.ami.texas.player.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.ami.api.common.Pager;
import com.ami.api.exception.APIException;
import com.ami.api.utill.StringTool;
import com.ami.api.web.dao.BaseMysqlDao;
import com.ami.common.TimeUtil;
import com.ami.common.TopUpData;

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
public class PlayerDao extends BaseMysqlDao
{
    
    /**
     * 鑾峰彇鎵�鏈夌敤鎴�
     * 
     * @param pager
     * @param useraccount
     * @param username
     * @return
     * @throws APIException
     * @see [绫汇�佺被#鏂规硶銆佺被#鎴愬憳]
     */
    public Pager queryPlayer(Pager pager, String userid, String username,String isFacebook)
        throws APIException
    { 
        StringBuffer sql = new StringBuffer(" ");
        sql.append(" select t1.id, t1.passportid, t1.name, t1.level, t1.diamond, t1.gold, t1.charm, t1.curexp, t1.sceneid,t1.bazooAgentDisplay,t1.bankGold,t1.bankPassword, ");
        sql.append(" t2.facebookId,");
        sql.append(" if(t1.lastlogintime=0, '-' , FROM_UNIXTIME(t1.lastlogintime/1000,'%Y-%m-%d %H:%i:%s')) as last_login_time, ");
        sql.append(" if(t1.lastlogouttime=0, '-' , FROM_UNIXTIME(t1.lastlogouttime/1000,'%Y-%m-%d %H:%i:%s')) as last_logout_time, ");
        sql.append(" t1.totalminute, t1.onlinestatus, if(t1.createtime=0, '-' , FROM_UNIXTIME(t1.createtime/1000,'%Y-%m-%d %H:%i:%s')) AS create_time ");
        sql.append(" from texas.t_human_info t1 left join texas.t_user_info t2  on t2.id=t1.passportId  where 1=1 ");
        
        if (!StringTool.isEmpty(userid))
        {
            sql.append(" and t1.passportid like '%");
            sql.append(userid);
            sql.append("%'");
        }
        if (!StringTool.isEmpty(username))
        {
            sql.append(" and t1.name" +
            		" like '%");
            sql.append(username);
            sql.append( "%'");
        }
        //涓婅竟鏄櫘閫氱敤鎴�
        if (isFacebook.equals("-1"))
        {
        	sql.append(" and t2.facebookId = -1");
        
        //杩欒竟鏄� facebook 鐢ㄦ埛
        }else if(!isFacebook.equals("-1") && !isFacebook.equals("2")){
        	sql.append(" and t2.facebookId != -1");
        	
        }
        
        pager = this.db.queryPage(sql.toString(), new Object[]
        {}, pager);
        
        return pager; 
    }
    /**
     * 澶ч鐜╁绛圭爜
     * @param pager
     * @param userid
     * @param username
     * @return
     * @throws APIException
     */
    public Pager queryPlayerBigdata(Pager pager)
    		throws APIException
    { 
    	
    	String sql = "select * from (select SUM(cost) as total,charId ,COUNT(*) as totalgoldNum from texas.t_human_recharge_order where orderStatus = 1 GROUP BY charId ) AS a  where  1=1 ORDER BY total DESC ";
    	
    
    	
    	
    	
    	 pager = this.db.queryPage(sql.toString(), new TopUpData[]{}, pager);
    	
    	 //瑙掕壊ID 绱鍏呭�兼鏁�
    	 List<Map> list = pager.getItems();
    	
    	 StringBuffer sb = new StringBuffer();
    	 
    	 List<HashMap<String, Object>> table_names =  queryTables("gold");
    	 
    	 
    	 for(int i = 0;i < list.size();i++ ){
    		 Map map = list.get(i);
    		 Long charId = (Long)map.get("charId");
    		 if(i == list.size()-1){
    			 sb.append(charId); 
    		 }else{
    			 sb.append(charId).append(",");
    		 }
    		 
    		 for(HashMap<String, Object> mtable : table_names){
    			 String tableName = (String)mtable.get("table_name");
    			 
    			 String sql3 = "select level,createTime from texas_log."+tableName+" where reason = 40 and account_id="+charId+" order by createTime DESC";
    			 
    			 List<HashMap<String, Object>> list2 = this.db.query(sql3);
    			 
    			 if(!list2.isEmpty()){
    				 HashMap<String, Object> mm = list2.get(0);
    				 map.put("topupLevel", mm.get("level"));
    				 map.put("topupTime", TimeUtil.formatYMDHMTime( (Long)mm.get("createTime")));
    				 break;
    			 }
    			 
    		 }
    		 
    	 }
    	 
    	 String ids = sb.toString();
    	 if(StringUtils.isBlank(ids)){
    		 ids="null";
    	 }
    	 StringBuffer sql2 = new StringBuffer(" ");
    	 sql2.append(" select id, passportid, name, level, gold,lastLoginIp, ");
    	 sql2.append(" if(lastlogintime=0, '-' , FROM_UNIXTIME(lastlogintime/1000,'%Y-%m-%d %H:%i:%s')) as last_login_time, ");
    	 sql2.append(" if(lastlogouttime=0, '-' , FROM_UNIXTIME(lastlogouttime/1000,'%Y-%m-%d %H:%i:%s')) as last_logout_time, ");
    	 sql2.append(" totalminute, if(createtime=0, '-' , FROM_UNIXTIME(createtime/1000,'%Y-%m-%d %H:%i:%s')) AS create_time ");
    	 sql2.append(" from texas.t_human_info where  passportid in ("+ids+")");
    	
    	 List<HashMap<String, Object>> list2 = this.db.query(sql2.toString());
    	 
    	 for(Map mapp : list){
    		 Long charId = (Long)mapp.get("charId");
    		 for(HashMap<String, Object> mmp : list2){
    			Long passportid = (Long) mmp.get("passportid");
    			if(String.valueOf(passportid).equals(String.valueOf(charId))){
    				mapp.put("id", mmp.get("id"));
    				mapp.put("passportid", passportid);
    				mapp.put("name", mmp.get("name"));
    				mapp.put("level", mmp.get("level"));
    				mapp.put("gold", mmp.get("gold"));
    				mapp.put("lastLoginIp", mmp.get("lastLoginIp"));
    				mapp.put("last_login_time", mmp.get("last_login_time"));
    				mapp.put("last_logout_time", mmp.get("last_logout_time"));
    				mapp.put("totalminute", mmp.get("totalminute"));
    				mapp.put("create_time", mmp.get("create_time"));
    			}
    		 }
    	 }
    	 
    	 
    	return pager; 
    }
    
    /**
     * 閫夋嫨鎵�鏈夊寘鍚壒瀹歱attern鐨勮〃鍚�
     * 
     * @param type
     * @return
     * @throws APIException
     */
	public List<HashMap<String, Object>> queryTables(String type) throws APIException {
		
		String sql = "select table_name from INFORMATION_SCHEMA.tables where table_schema = 'texas_log' and table_name like '"+type+"%' order by table_name DESC";
		List<HashMap<String, Object>> table_names = this.db.query(sql);
		/*for(HashMap<String, Object> map: table_names)
		{
			map.put("date", map.get("table_name").toString().substring(map.get("table_name").toString().length()-10));
			map.remove("table_name");
		}*/
		
		return table_names;
	}
	
    public Pager queryPlayerlevel(Pager pager, String level)
    		throws APIException
    { 
    	StringBuffer sql = new StringBuffer(" ");
    	sql.append(" select id, passportid, name, level, diamond, gold, charm, curexp, sceneid, ");
    	sql.append(" if(lastlogintime=0, '-' , FROM_UNIXTIME(lastlogintime/1000,'%Y-%m-%d %H:%i:%s')) as last_login_time, ");
    	sql.append(" if(lastlogouttime=0, '-' , FROM_UNIXTIME(lastlogouttime/1000,'%Y-%m-%d %H:%i:%s')) as last_logout_time, ");
    	sql.append(" totalminute, onlinestatus, if(createtime=0, '-' , FROM_UNIXTIME(createtime/1000,'%Y-%m-%d %H:%i:%s')) AS create_time ");
    	sql.append(" from texas.t_human_info where  1=1 ");
    	
    	if (!StringTool.isEmpty(level))
    	{
    		sql.append(" and level = ");
    		sql.append(level);
    		
    	}
    	
    	
    	pager = this.db.queryPage(sql.toString(), new Object[]
    			{}, pager);
    	
    	return pager; 
    }
    
    /**
     * 鑾峰彇鐜╁鑳屽寘淇℃伅
     * 
     * @param pager
     * @param useraccount
     * @param username
     * @return
     * @throws APIException
     * @see [绫汇�佺被#鏂规硶銆佺被#鎴愬憳]
     */
    public Pager queryBag(Pager pager, String userid, String username) 
        throws APIException
    { 
        StringBuffer sql = new StringBuffer(" ");
        sql.append(" SELECT id, charid, items, ");
        sql.append(" if(updatetime=0, '-' , FROM_UNIXTIME(updatetime/1000,'%Y-%m-%d %H:%i:%s')) as update_time, ");
        sql.append(" if(createtime=0, '-' , FROM_UNIXTIME(createtime/1000,'%Y-%m-%d %H:%i:%s')) as create_time FROM texas.t_human_bag where 1=1");
        
        
        if (!StringTool.isEmpty(userid))
        {
            sql.append(" and charid like '%");
            sql.append(userid);
            sql.append("%'");
        }
        if (!StringTool.isEmpty(username))
        {
            sql.append(" and username like '%");
            sql.append(username);
            sql.append( "%'");
        }
        
        
        pager = this.db.queryPage(sql.toString(), new Object[]
        {}, pager);
        
        return pager; 
    }
    
    /**
     * 鑾峰彇濂藉弸鍏崇郴淇℃伅
     * 
     * @param pager
     * @param useraccount
     * @param username
     * @return
     * @throws APIException
     * @see [绫汇�佺被#鏂规硶銆佺被#鎴愬憳]
     */
    public Pager queryFriend(Pager pager, String userid, String username) 
        throws APIException
    { 
        StringBuffer sql = new StringBuffer(" ");
        sql.append(" SELECT id, charid, friendid, ");
        sql.append(" if(updatetime=0, '-' , FROM_UNIXTIME(updatetime/1000,'%Y-%m-%d %H:%i:%s')) as update_time, ");
        sql.append(" if(createtime=0, '-' , FROM_UNIXTIME(createtime/1000,'%Y-%m-%d %H:%i:%s')) as create_time FROM texas.t_human_friend where 1=1");
        
        
        if (!StringTool.isEmpty(userid))
        {
            sql.append(" and charid like '%");
            sql.append(userid);
            sql.append("%'");
        }
        if (!StringTool.isEmpty(username))
        {
            sql.append(" and username like '%");
            sql.append(username);
            sql.append( "%'");
        }
        
        
        pager = this.db.queryPage(sql.toString(), new Object[]
        {}, pager);
        
        return pager; 
    }
    
    /**
     * 鑾峰彇濂藉弸璇锋眰鍒楄〃
     * 
     * @param pager
     * @param useraccount
     * @param username
     * @return
     * @throws APIException
     * @see [绫汇�佺被#鏂规硶銆佺被#鎴愬憳]
     */
    public Pager queryFriendRequest(Pager pager, String userid, String username) 
        throws APIException
    { 
        StringBuffer sql = new StringBuffer(" ");
        sql.append(" SELECT id, charid, sendid, ");
        sql.append(" if(updatetime=0, '-' , FROM_UNIXTIME(updatetime/1000,'%Y-%m-%d %H:%i:%s')) as update_time, ");
        sql.append(" if(createtime=0, '-' , FROM_UNIXTIME(createtime/1000,'%Y-%m-%d %H:%i:%s')) as create_time FROM texas.t_human_friend_request where 1=1");
        
        
        if (!StringTool.isEmpty(userid))
        {
            sql.append(" and charid like '%");
            sql.append(userid);
            sql.append("%'");
        }
        if (!StringTool.isEmpty(username))
        {
            sql.append(" and username like '%");
            sql.append(username);
            sql.append( "%'");
        }
        
        
        pager = this.db.queryPage(sql.toString(), new Object[]
        {}, pager);
        
        return pager; 
    }
    
    /**
     * 鑾峰彇閭欢鍒楄〃
     * 
     * @param pager
     * @param useraccount
     * @param username
     * @return
     * @throws APIException
     * @see [绫汇�佺被#鏂规硶銆佺被#鎴愬憳]
     */
    public Pager queryEmail(Pager pager,String sendUserid, String sendUsername, String userid, String username,String condition) 
        throws APIException
    { 
        StringBuffer sql = new StringBuffer(" ");
        sql.append(" SELECT id, charid, sendid, sendname, recid, recname, title, content, attachmentpack, mailstatus,deleted,");
        sql.append(" if(updatetime=0, '-' , FROM_UNIXTIME(updatetime/1000,'%Y-%m-%d %H:%i:%s')) as update_time, ");
        sql.append(" if(createtime=0, '-' , FROM_UNIXTIME(createtime/1000,'%Y-%m-%d %H:%i:%s')) as create_time FROM texas.t_mail_info where "+condition);
        
        
        if (!StringTool.isEmpty(userid))
        {
            sql.append(" and charid like '%");
            sql.append(userid);
            sql.append("%'");
        }
        if (!StringTool.isEmpty(username))
        {
            sql.append(" and recName like '%");
            sql.append(username);
            sql.append( "%'");
        }
        if (!StringTool.isEmpty(sendUserid))
        {
        	sql.append(" and sendId like '%");
        	sql.append(sendUserid);
        	sql.append("%'");
        }
        if (!StringTool.isEmpty(sendUsername))
        {
        	sql.append(" and sendName like '%");
        	sql.append(sendUsername);
        	sql.append( "%'");
        }
        
        sql.append(" order by create_time DESC ");
        
        pager = this.db.queryPage(sql.toString(), new Object[]
        {}, pager);
        
        return pager; 
    }
    
    /**
     * 鑾峰彇VIP鍒楄〃
     * 
     * @param pager
     * @param useraccount
     * @param username
     * @return
     * @throws APIException
     * @see [绫汇�佺被#鏂规硶銆佺被#鎴愬憳]
     */
    public Pager queryVip(Pager pager, String userid, String username) 
        throws APIException
    { 
        StringBuffer sql = new StringBuffer(" ");
        sql.append(" SELECT id, charid, level, ");
        sql.append(" if(gettime=0, '-' , FROM_UNIXTIME(gettime/1000,'%Y-%m-%d %H:%i:%s')) as gettime, ");
        sql.append(" if(buytime=0, '-' , FROM_UNIXTIME(buytime/1000,'%Y-%m-%d %H:%i:%s')) as buytime, ");
        sql.append(" if(updatetime=0, '-' , FROM_UNIXTIME(updatetime/1000,'%Y-%m-%d %H:%i:%s')) as update_time, ");
        sql.append(" if(createtime=0, '-' , FROM_UNIXTIME(createtime/1000,'%Y-%m-%d %H:%i:%s')) as create_time FROM texas.t_human_vip where 1=1");
                
        if (!StringTool.isEmpty(userid))
        {
            sql.append(" and charid like '%");
            sql.append(userid);
            sql.append("%'");
        }
        if (!StringTool.isEmpty(username))
        {
            sql.append(" and username like '%");
            sql.append(username);
            sql.append( "%'");
        }
        sql.append(" order by level DESC ");
        pager = this.db.queryPage(sql.toString(), new Object[]
        {}, pager);
        
        return pager; 
    }
    
    /**
     * 鑾峰彇浠樿垂璁㈠崟
     * 
     * @param pager
     * @param useraccount
     * @param username
     * @return
     * @throws APIException
     * @see [绫汇�佺被#鏂规硶銆佺被#鎴愬憳]
     */
    public Pager queryRecharge(Pager pager, String userid, String username,String status) 
        throws APIException
    { 
        StringBuffer sql = new StringBuffer(" ");
        sql.append(" SELECT id, charid, orderstatus, productid, channelId,channelOrderId,cost,");
        sql.append(" if(updatetime=0, '-' , FROM_UNIXTIME(updatetime/1000,'%Y-%m-%d %H:%i:%s')) as update_time, ");
        sql.append(" if(createtime=0, '-' , FROM_UNIXTIME(createtime/1000,'%Y-%m-%d %H:%i:%s')) as create_time FROM texas.t_human_recharge_order ");
        sql.append(" where 1=1 ");
                
        if (!StringTool.isEmpty(userid))
        {
            sql.append(" and charid like '%");
            sql.append(userid);
            sql.append("%'");
        }
        if (!StringTool.isEmpty(username))
        {
            sql.append(" and username like '%");
            sql.append(username);
            sql.append( "%'");
        }
        if (!StringTool.isEmpty(status))
        {
        	sql.append(" and orderstatus like '%");
        	sql.append(status);
        	sql.append( "%'");
        }
        sql.append(" order by createTime desc");
        pager = this.db.queryPage(sql.toString(), new Object[]
        {}, pager);
        
        return pager; 
    }

	public List<HashMap<String, Object>> queryInterval()throws APIException {
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		for(int i = 1 ;i <=150;i++){
			String sql = "select level,count(*)  as dataValue from texas.t_human_info where `level`="+i;
			List<HashMap<String, Object>> list1 = this.db.query(sql);
			for(HashMap<String, Object> map : list1){
				if(map.get("level") == null){
					map.put("level", i);
				}
				list.add(map);
			}
		}
		return list;
	}
}
