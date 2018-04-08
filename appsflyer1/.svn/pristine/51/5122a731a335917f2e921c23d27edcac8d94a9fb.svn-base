package com.ami.texas.common.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ami.api.exception.APIException;
import com.ami.api.web.dao.BaseMysqlDao;
import com.ami.texas.common.vo.SlotName;

@Repository
public class UDao extends BaseMysqlDao{
	public JSONArray getAllSlots() {
		try {
			List<HashMap<String, Object>> slotNames = this.db.query("select type,langDesc from texas_activity.t_slot_list group by type");
			JSONArray slotJSON = (JSONArray)JSONObject.toJSON(slotNames);
			return slotJSON;
		} catch (APIException e) {
			e.printStackTrace();
		}
		return null;
	}
	public List<SlotName> getAllSlotIdNames() {
		try {
			List<SlotName> slotNames = this.db.query("select id,langDesc from texas_activity.t_slot_name",SlotName.class);
			return slotNames;
		} catch (APIException e) {
			e.printStackTrace();
		}
		return null;
	}

	public JSONArray getAllSlotIocns() {
		try {
			List<HashMap<String, Object>> slotNames = this.db.query("select turn,lang_desc as langDesc from texas_activity.t_slot_icon where slots_num = 1");
			JSONArray slotJSON = (JSONArray)JSONObject.toJSON(slotNames);
			return slotJSON;
		} catch (APIException e) {
			e.printStackTrace();
		}
		return null;
	}
}
