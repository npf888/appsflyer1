package com.ami.api.web.action;


/**
 * action 抽象类
 * <功能详细描述>
 * 
 * @author  zhuweiliang
 * @version  [版本号, Apr 18, 2012]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public abstract class CRUDAction extends BaseAction{

	private static final long serialVersionUID = -5876980456199846559L;
	

	
	public abstract String toMain() throws Exception;
	public abstract String query() throws Exception;
	public abstract String toAdd() throws Exception;
	public abstract String toEdit() throws Exception;
	
	public abstract String doAdd() throws Exception;
	public abstract String doEdit() throws Exception;
	public abstract String doDelete() throws Exception;


}
