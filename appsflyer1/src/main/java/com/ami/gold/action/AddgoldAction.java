package com.ami.gold.action;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ami.api.web.action.BaseAction;
import com.ami.gold.service.AddgoldService;

@Scope("prototype")
@Component
public class AddgoldAction extends BaseAction{

	@Autowired
	private AddgoldService addgoldService;
	
	public String addgold(){
		return "addgold";
	}
	//覆盖原先的金币  或者
	public void addgoldOverrideOrIncrese() throws IOException{
		String json = addgoldService.addgoldOverrideOrIncrese(this.getReq());
//		String address = this.getReq().getLocalAddr();
//		int port = this.getReq().getLocalPort();
//		String a = "<h1>"+json+"</br><button class='btn btn-small' >"
//				+ "<a href='http://"+address+":"+port+"/addgold.do?method=addgold' class='leaf' >返回</a>"
//				+ "</button>";
		print(json);
	}
}
