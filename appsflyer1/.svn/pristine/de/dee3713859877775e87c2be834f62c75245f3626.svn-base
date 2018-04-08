package com.ami.weixin.course.service;

import java.io.File;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.ami.mail.pojo.NewPeopleVO;
import com.ami.weixin.course.dao.LocationDao;
import com.ami.weixin.course.pojo.vo.HumanInfo;
import com.ami.weixin.course.pojo.vo.TUserLocationSimpleVO;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.City;
import com.maxmind.geoip2.record.Country;
import com.maxmind.geoip2.record.Location;
import com.maxmind.geoip2.record.Postal;
import com.maxmind.geoip2.record.Subdivision;

@Component
public class LocationService {
	@Autowired
	private LocationDao locationDao;

	public boolean saveLocation(HttpServletRequest req) {
		return locationDao.saveLocation(req);
	}

	@SuppressWarnings("unchecked")
	public String getLocation(HttpServletRequest req) {
		List<TUserLocationSimpleVO> rResults = getJW(req);
	/*	List<TUserLocationSimpleVO> rResults = (List<TUserLocationSimpleVO>)locationDao.getLocation(req);*/
		String jb = JSON.toJSONString(rResults);
		return jb;
	}
	
	private List<TUserLocationSimpleVO> getJW(HttpServletRequest req){
		try{
			/**
			 * 将IP转换成经纬度
			 */
			List<TUserLocationSimpleVO> tUserLocationSimpleVOs = new ArrayList<TUserLocationSimpleVO>();
			List<HumanInfo> ips = locationDao.getAllIPs();
			String contextPath = req.getServletContext().getRealPath("/");
			File database = new File(contextPath+"/maxmindmap/GeoLite2-City.mmdb");
//			File database = new File(courseFile+"/maxmindmap/GeoLite2-Country.mmdb");
			DatabaseReader reader = new DatabaseReader.Builder(database).build();
			
			
			for(HumanInfo human:ips){
				CityResponse response = null;
				try{
					String[] ipPort = human.getLastLoginIp().split(":");
					InetAddress ipAddress = InetAddress.getByName(ipPort[0]);
					response = reader.city(ipAddress);
				}catch(Exception e){
					continue;
				}
				
				Country country = response.getCountry();
				System.out.println(country.getIsoCode());            // 'US'
				System.out.println(country.getName());               // 'United States'
				System.out.println(country.getNames().get("zh-CN")); // '美国'
				
				Subdivision subdivision = response.getMostSpecificSubdivision();
				System.out.println(subdivision.getName());    // 'Minnesota'
				System.out.println(subdivision.getIsoCode()); // 'MN'
				
				City city = response.getCity();
				System.out.println(city.getName()); // 'Minneapolis'
				
				Postal postal = response.getPostal();
				System.out.println(postal.getCode()); // '55455'
				
				Location location = response.getLocation();
				System.out.println(location.getLatitude());  // 44.9733
				System.out.println(location.getLongitude()); // -93.2323
				
				TUserLocationSimpleVO tUserLocationSimpleVO = new TUserLocationSimpleVO();
				tUserLocationSimpleVO.setLatitude(location.getLatitude());
				tUserLocationSimpleVO.setLongitude(location.getLongitude());
				tUserLocationSimpleVOs.add(tUserLocationSimpleVO);
			}
			
			return tUserLocationSimpleVOs;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	
}
