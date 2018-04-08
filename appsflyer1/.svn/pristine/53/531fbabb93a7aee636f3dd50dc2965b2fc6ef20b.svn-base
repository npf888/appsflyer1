/*
 * @(#)GeneralUUID.java	2010-01-28
 *
 * Copyright 2010 张亮
 */
package com.ami.api.utill;
import java.util.UUID;

/**
 * 
 * 这个类是定义一些用来产生UUID的方法
 * <p>
 * 注意事项
 * <p>
 * 例子代码
 * String uuID = GeneralUUID.getInstance().getUUID();
 *
 * <p>
 * @see 
 * 
 * <p>
 * 更新日志
 * <div>
 * <table border=1> 
 * <tr><td>修改人</td><td>版本号</td><td>时间</td><td>修改内容</td></tr>
 * <tr><td>张亮 </td><td>1.0.0</td><td>2010-01-28</td><td>创建</td></tr>
 * <tr><td></td><td></td><td></td><td></td></tr>
 * </table>
 * <div>
 * <p>
 * <p>
 * @author 张亮
 * <p>
 * @version 1.0.0 2010-01-28
 * <p>
 */
public class GeneralUUID {

	private static GeneralUUID _guuid = null;

	private GeneralUUID() {

	}
	
	/**
	 * 
	 * 对_guuid这个单例进行赋值
	 * <p>
	 * 注意事项
	 * <p>
	 * 例如<br/>
	 * 
	 * <p>
	 * @see 
	 * @return
	 */
	public static GeneralUUID getInstance() {
		if (_guuid == null) {
			_guuid = new GeneralUUID();
		}
		return _guuid;
	}
	
	/**
	 * 
	 * 产生一个32位的UUID
	 * <p>
	 * 注意事项
	 * <p>
	 * 例如<br/>
	 * 
	 * <p>
	 * @see 
	 * @return
	 */
	public synchronized String getUUID() {

		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	/**
	 * 
	 * 这方法是用来做单元测试的
	 * <p>
	 * 注意事项
	 * <p>
	 * 例如<br/>
	 * 
	 * <p>
	 * @see 
	 * @param args
	 */
	public static void main(String[] args) {
		
		long i1 = System.currentTimeMillis();
		for (int i = 0; i < 1000; i++) {

			System.out.println(GeneralUUID.getInstance().getUUID());
		}
		long i2 = System.currentTimeMillis();
		System.out.println(i2 - i1);
	}
}
