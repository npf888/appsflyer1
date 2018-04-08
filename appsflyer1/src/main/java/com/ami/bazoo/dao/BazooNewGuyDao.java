package com.ami.bazoo.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.ami.api.exception.APIException;
import com.ami.api.web.dao.BaseMysqlDao;
import com.ami.bazoo.pojo.BazooNewGuy;
@Component
public class BazooNewGuyDao extends BaseMysqlDao{
	private static Logger logger = Logger.getLogger(BazooDao.class);
	
	
	public List<BazooNewGuy>  getBazooNewGuy() {
		String sql = " select type,process,count(id) num from texas.t_human_bazoo_new_guy where type = 1 group by process "
					+ " union all "
					+ " select type,process,count(id) num from texas.t_human_bazoo_new_guy where type = 2 group by process "
					+ " union all "
					+ " select type,process,count(id) num from texas.t_human_bazoo_new_guy where type = 3 group by process "
					+ " union all "
					+ "select type,process,count(id) num from texas.t_human_bazoo_new_guy where type = 4 group by process";
		try {
			List<BazooNewGuy> bazooNewGuyList =  this.db.query(sql, BazooNewGuy.class);
			return bazooNewGuyList;
		} catch (APIException e) {
			logger.error("查询打点信息失败：", e);
		}
		List<BazooNewGuy> bazooNewGuyList = new ArrayList<BazooNewGuy>();
		return bazooNewGuyList;
	}
}
