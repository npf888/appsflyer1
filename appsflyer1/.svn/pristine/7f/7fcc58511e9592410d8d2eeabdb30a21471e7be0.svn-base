package com.ami.mail.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ami.mail.pojo.Country;
import com.ami.mail.pojo.NewPeopleVO;
import com.ami.texas.log.service.LogService;
/**
 * 新增人数
 * @author JavaServer
 *
 */
@Service
public class NewPeopleService {
	@Autowired
	private  LogService logService;
	
	//每天新注册用户所在的国家分别有多少人
	public   List<NewPeopleVO> getRewPeopleList(){
		try{
			
			List<HashMap<String, Object>> date_list = logService.queryTablesDate("player_keep_log");
			List<Country> countrys = logService.getAllCountry();
			List<NewPeopleVO>  newPeopleList = logService.queryPerCountryPeople(date_list);
			if(newPeopleList == null || newPeopleList.size() == 0){
				return null;
			}
			List<NewPeopleVO> newPeopleXlsx = new ArrayList<NewPeopleVO>();
			DecimalFormat    df   = new DecimalFormat("######0.00"); 
			for(NewPeopleVO map:newPeopleList){
				NewPeopleVO newPeopleVO = new NewPeopleVO();
				//等于2就是IOS
				if(map.getChannelType() != null && map.getChannelType().equals("2")){
					newPeopleVO.setChannelType("ios");
				}else{
					newPeopleVO.setChannelType("android");
				}
				double newGuy = Double.valueOf(map.getPeopleNum());
				double remain1 = Double.valueOf(map.getRemain1());
				double remain3 = Double.valueOf(map.getRemain3());
				double remain4 = Double.valueOf(map.getRemain4());
				double remain5 = Double.valueOf(map.getRemain5());
				double remain7 = Double.valueOf(map.getRemain7());
				double remain10 = Double.valueOf(map.getRemain10());
				double remain15 = Double.valueOf(map.getRemain15());
				double remain30 = Double.valueOf(map.getRemain30());
				newPeopleVO.setDate(map.getDate());
				
				for(Country country:countrys){
					if(map.getCountries() != null && map.getCountries().equals(country.getCountry())){
						newPeopleVO.setCountries(country.getLangDesc());
						break;
					}
				}
				newPeopleVO.setPeopleNum(map.getPeopleNum());
				newPeopleVO.setRemain1(df.format(remain1/newGuy));
				newPeopleVO.setRemain3(df.format(remain3/newGuy));
				newPeopleVO.setRemain4(df.format(remain4/newGuy));
				newPeopleVO.setRemain5(df.format(remain5/newGuy));
				newPeopleVO.setRemain7(df.format(remain7/newGuy));
				newPeopleVO.setRemain10(df.format(remain10/newGuy));
				newPeopleVO.setRemain15(df.format(remain15/newGuy));
				newPeopleVO.setRemain30(df.format(remain30/newGuy));
				newPeopleXlsx.add(newPeopleVO);
			}
			return newPeopleXlsx;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
}
