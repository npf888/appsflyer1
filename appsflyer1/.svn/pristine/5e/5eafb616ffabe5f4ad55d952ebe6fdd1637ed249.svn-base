package com.ami.bazoo.util;

import org.apache.commons.lang3.StringUtils;

public class DeviceUtil {

	/**
	 * 改变一下 DeviceMac 主要用于显示
	 */
	public static String changeDeviceMac(String deviceMac){
		if(StringUtils.isNotBlank(deviceMac)){
			String robot = deviceMac.split("_")[0];
			if(robot.equals("Robot")){
				deviceMac="<font style=\"color:red;\">"+deviceMac+"</font>";
			}
		}
		return deviceMac;
	}
}
