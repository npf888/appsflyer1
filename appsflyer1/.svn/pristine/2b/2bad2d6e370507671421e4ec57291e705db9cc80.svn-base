package com.ami.texas.activity.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.ami.api.common.Pager;
import com.ami.api.exception.APIException;
import com.ami.api.web.dao.BaseMysqlDao;
@Component
public class FunctionDao extends BaseMysqlDao{

	 private static Logger logger = Logger.getLogger(FunctionDao.class);

	public Pager queryFunction(Pager pager) {
		 StringBuffer sql = new StringBuffer(" select id,functionType,title,descrip,pic,startTime,endTime,conditions,createTime  "); 
         sql.append(" from texas.t_function order by createTime desc");           

     try {
		pager = this.db.queryPage(sql.toString(), new Object[]
		 {}, pager);
	 } catch (APIException e) {
		e.printStackTrace();
	 }
     
     return pager;
	}

	public boolean queryFunctionTypeExist() {
		try {
			List<HashMap<String,Object>> list = this.db.query("select * from texas.t_function where  endTime >= DATE_FORMAT(NOW(),'%Y-%m-%d')");
			if(list != null && list.size() >0){
				return true;
			}
			return false;
		} catch (APIException e) {
			e.printStackTrace();
		}
		return true;
	}

	public List<HashMap<String,Object>> getFunctionById(String id) {
		try {
			return this.db.query("select * from texas.t_function where  id="+id);
		} catch (APIException e) {
			e.printStackTrace();
		}
		return null;
		
	}
}
