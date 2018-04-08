package com.ami.api.init;

import javax.servlet.ServletContext;

/**
 * 
 * 应用程序级 初始化接口
 * 
 * @author  zhuweiliang
 * @version  [版本号, 2013-9-25]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface ApplicationInitInte
{

	void contextInitialized(ServletContext context, String systemPath)
			throws Exception;
}
