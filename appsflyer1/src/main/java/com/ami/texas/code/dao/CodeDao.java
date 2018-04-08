package com.ami.texas.code.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ami.api.common.AppConstant;
import com.ami.api.common.Pager;
import com.ami.api.exception.APIException;
import com.ami.api.web.dao.BaseMysqlDao;
import com.ami.common.HttpClientUtil;
import com.ami.common.TimeUtil;
import com.ami.texas.code.pojo.Conversioncode;

@Component
public class CodeDao extends BaseMysqlDao{
	 private static Logger logger = Logger.getLogger(CodeDao.class);
	public Pager queryCode(Pager pager, HttpServletRequest httpServletRequest) {

        try {
        	String code = (String)httpServletRequest.getParameter("code");
        	String sql = " select id,conversionCode,gold,"
        			+ " FROM_UNIXTIME(endTime/1000,'%Y-%m-%d') as endTime,"
        			+ " isdelete,"
        			+ " FROM_UNIXTIME(updateTime/1000,'%Y-%m-%d %H:%i:%s') as updateTime,"
        			+ " FROM_UNIXTIME(createTime/1000,'%Y-%m-%d %H:%i:%s') as createTime,"
        			+ " codeType from texas.t_conversioncode where 1=1 ";
        	if(StringUtils.isNotBlank(code)){
        		sql+=" and conversionCode like '%"+code+"%'";
        	}
			pager = this.db.queryPage(sql.toString(), new Object[]{}, pager,Conversioncode.class);
		} catch (APIException e) {
			e.printStackTrace();
		}
        
        return pager; 
	}
	@Transactional
	public void toEdit(HttpServletRequest req) {
		String id = (String)req.getParameter("id");
		String ee = req.getParameter("endTime");
		long endTime = TimeUtil.formatStrTODateff(req.getParameter("endTime")).getTime();
		try {
			if(StringUtils.isNotBlank(id)){
				//远程修改
				HashMap<String,String> remoteParam = new HashMap<String,String>();
				remoteParam.put("status","2");//修改
				remoteParam.put("code",req.getParameter("code"));
				remoteParam.put("endTime",String.valueOf(endTime));
				remoteParam.put("gold",req.getParameter("gold"));
				remoteParam.put("codeType",req.getParameter("codeType"));
				String reResult = HttpClientUtil.postUrl(AppConstant.SERVER_URL_HTTP+"api/receivecode", remoteParam);
				logger.info(AppConstant.SERVER_URL_HTTP+"api/receivecode::-------------------return result:"+reResult);
			}else{
				//自己生成
				/*String now = String.valueOf(new Date().getTime());
				String code = now.substring(0,now.length()-3);*/
				HashMap<String,String> remoteParam = new HashMap<String,String>();
				remoteParam.put("status","1");//增加
				remoteParam.put("codeNum",req.getParameter("codeNum"));
				remoteParam.put("endTime",String.valueOf(endTime));
				remoteParam.put("gold",req.getParameter("gold"));
				remoteParam.put("codeType",req.getParameter("codeType"));
				String reResult = HttpClientUtil.postUrl(AppConstant.SERVER_URL_HTTP+"api/receivecode", remoteParam);
				logger.info(AppConstant.SERVER_URL_HTTP+"api/receivecode::-------------------return result:"+reResult);
				//远程修改
			}
			
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void toDelete(HttpServletRequest httpServletRequest) {
		String id = (String)httpServletRequest.getParameter("id");
		String code = (String)httpServletRequest.getParameter("code");
		if(StringUtils.isBlank(id)){
			return;
		}
		if(StringUtils.isBlank(code)){
			return;
		}
		HashMap<String,Object> param = new HashMap<String,Object>();
		param.put("isdelete","1");
		try {
			/*this.table.update("texas.t_conversioncode", param,Integer.valueOf(id));*/
			//删除内存
			HashMap<String,String> remoteParam = new HashMap<String,String>();
			remoteParam.put("code",code);
			remoteParam.put("state","1");
			remoteParam.put("status","3");//编辑（该状态为不可用）
			HttpClientUtil.postUrl(AppConstant.SERVER_URL_HTTP+"api/receivecode", remoteParam);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Conversioncode queryOneCode(HttpServletRequest req) {
		 try {
	        	String id = (String)req.getParameter("id");
	        	String sql = " select id,conversionCode,gold,"
	        			+ " FROM_UNIXTIME(endTime/1000,'%Y-%m-%d') as endTime,"
	        			+ " isdelete,"
	        			+ " FROM_UNIXTIME(updateTime/1000,'%Y-%m-%d %H:%i:%s') as updateTime,"
	        			+ " FROM_UNIXTIME(createTime/1000,'%Y-%m-%d %H:%i:%s') as createTime,"
	        			+ " codeType from texas.t_conversioncode where id= "+id;
				List<Conversioncode> conversioncodes = this.db.query(sql.toString(),Conversioncode.class);
				if(conversioncodes != null && conversioncodes.size()>0){
					return conversioncodes.get(0);
				}
			} catch (APIException e) {
				e.printStackTrace();
			}
	        
	        return null; 
	}
	//导出所有未使用的
	public List<Conversioncode> queryAllNotUse() {
		String sql = " select id,conversionCode,gold,"
    			+ " FROM_UNIXTIME(endTime/1000,'%Y-%m-%d') as endTime,"
    			+ " isdelete,"
    			+ " FROM_UNIXTIME(updateTime/1000,'%Y-%m-%d %H:%i:%s') as updateTime,"
    			+ " FROM_UNIXTIME(createTime/1000,'%Y-%m-%d %H:%i:%s') as createTime,"
    			+ " codeType from texas.t_conversioncode where isdelete=0 ";
		try {
			List<Conversioncode> conversioncodeList = this.db.query(sql, Conversioncode.class);
			return conversioncodeList;
		} catch (APIException e) {
			e.printStackTrace();
		}
		return new ArrayList<Conversioncode>();
	}

}
