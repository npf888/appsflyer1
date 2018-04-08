package com.ami.sys.admin.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ami.api.common.Pager;
import com.ami.api.exception.APIException;
import com.ami.api.utill.StringTool;
import com.ami.api.web.dao.BaseDao;
import com.ami.sys.admin.bean.AdminBean;

/**
 * 
 * 鏌ヨ鐢ㄦ埛淇℃伅
 * 
 * @author zhang
 * @version [鐗堟湰鍙�, 2013-5-30]
 * @see [鐩稿叧绫�/鏂规硶]
 * @since [浜у搧/妯″潡鐗堟湰]
 */
@Component
public class AdminDao extends BaseDao
{
    
    /**
     * 娣诲姞鐢ㄦ埛
     * 
     * @param params 鍙傛暟 key value
     * @return
     * @throws APIException
     * @see [绫汇�佺被#鏂规硶銆佺被#鎴愬憳]
     */
    public String add(Map<String, Object> params)
        throws APIException
    {
        
//        String id = this.getSequence("sys_admin_texas." + AppConstant.seq_pre_common_member_id) + "";
       List<HashMap<String,Object>> list  = this.db.query("select id from sys_admin_texas.pre_common_member order by id desc limit 0,1");
       String id =  String.valueOf(Integer.valueOf(String.valueOf(list.get(0).get("id")))+1);
       params.put("id", id);
        
       this.table.insert("sys_admin_texas.pre_common_member", params);
        
       return id;
    }
//    select id,account  from sys_admin_texas.pre_common_member
    
    /**
     * 鍒ゆ柇璐︽埛鏄惁閲嶅
     * 
     * @param params 鍙傛暟 key value
     * @return
     * @throws APIException
     * @see [绫汇�佺被#鏂规硶銆佺被#鎴愬憳]
     */
    public List<HashMap<String, Object>> checkAccout(String account)
        throws APIException
    {
        String sql= "select *  from  sys_admin_texas.pre_common_member  where account='"+account+"'";
        List<HashMap<String, Object>> list = this.db.query(sql);
        return list;
    } 
    
    
    
    
    /**
     * 鏍规嵁鐢ㄦ埛ID 淇敼鐢ㄦ埛淇℃伅
     * 
     * @param params 淇敼鐨勫瓧娈典互鍙妚alue
     * @param id 鐢ㄦ埛鍞竴ID
     * @return
     * @throws APIException
     * @see [绫汇�佺被#鏂规硶銆佺被#鎴愬憳]
     */
    public void update(Map<String, Object> params, String id)
        throws APIException
    {
        this.table.update("sys_admin_texas.pre_common_member", params, id);
        
    }
    
    /**
     * 鍒濆鍖栧瘑鐮�
     * 
     * @param account
     * @param pwd
     * @see [绫汇�佺被#鏂规硶銆佺被#鎴愬憳]
     */
    public void resetPwd(String account, String pwd)
    {
        String sql = "update sys_admin_texas.pre_common_member set pwd=? where account=? ";
        
        try
        {
            this.db.update(sql, new Object[]
            {pwd, account});
        }
        catch (APIException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    /**
     * 鏍规嵁ID 鍒犻櫎鐢ㄦ埛淇℃伅
     * 
     * @param id
     * @throws APIException
     * @see [绫汇�佺被#鏂规硶銆佺被#鎴愬憳]
     */
    public void delByID(String id)
        throws APIException
    {
        
        this.table.delete("sys_admin_texas.pre_common_member", id);
    }
    
    /**
     * 鏍规嵁璐﹀彿鍜屽瘑鐮� 鏌ヨ淇℃伅
     * 
     * @return
     * @throws APIException
     * @see [绫汇�佺被#鏂规硶銆佺被#鎴愬憳]
     */
    public AdminBean loadByAccountPwd(String acount, String pwd)
        throws APIException
    {
        // 鏌ヨ鏉′欢
        Object ojb[] = new Object[]
        {acount, pwd};
        
        // DB
        AdminBean loginInfo = this.db.load("select * from sys_admin_texas.pre_common_member where account = ? and pwd = ?", ojb, AdminBean.class);
        
        return loginInfo;
    }
    
    public List<HashMap<String, Object>> query()
        throws APIException
    {
        
        List<HashMap<String, Object>> list = this.db.query("select * from sys_admin_texas.pre_common_member where account!='admin' and account!='dev' order by time_stamp desc");

        return list;
        
    }
    
    /**
     * 鏍规嵁鐢ㄦ埛ID 鏌ヨ鐢ㄦ埛淇℃伅
     * 
     * @param id 鐢ㄦ埛鍞竴ID
     * @return
     * @throws APIException
     * @see [绫汇�佺被#鏂规硶銆佺被#鎴愬憳]
     */
    public Map<String, Object> loadAdminByID(String id)
        throws APIException
    {
        Map<String, Object> o = this.table.load("sys_admin_texas.pre_common_member", id);
        
        return o;
    }
    
    public Pager queryForPage(Pager pager, String useraccount, String username)
        throws APIException
    {

        
        String sql = "select * from sys_admin_texas.pre_common_member  where  account!='admin' and account!='dev'";
        
        if (!StringTool.isEmpty(useraccount))
        {
            sql += " and account like '%" + useraccount + "%'";
        }
        if (!StringTool.isEmpty(username))
        {
            sql += " and username like '%" + username + "%'";
        }
        
        sql += " order by time_stamp desc";
        
        pager = this.db.queryPage(sql, new Object[]
        {}, pager);
        
        return pager;
        
    }
    

}
