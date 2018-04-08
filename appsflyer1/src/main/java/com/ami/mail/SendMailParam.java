package com.ami.mail;

import java.util.ArrayList;
import java.util.List;

import com.ami.mail.pojo.AllResourceVO;

public class SendMailParam {

    // 发件人的 邮箱 和 密码（替换为自己的邮箱和密码）
    // PS: 某些邮箱服务器为了增加邮箱本身密码的安全性，给 SMTP 客户端设置了独立密码（有的邮箱称为“授权码”）, 
    //     对于开启了独立密码的邮箱, 这里的邮箱密码必需使用这个独立密码（授权码）。
	//开启授权 smtp服务的密码：yy123456789
    /*public static String myEmailAccount = "youyan121@126.com";
    public static String myEmailPassword = "yy123456789";*/
    public static String myEmailAccount = "vincent.niu@y2tek.com";
    public static String myEmailPassword = "npf_61553546";

    // 发件人邮箱的 SMTP 服务器地址, 必须准确, 不同邮件服务器地址不同, 一般(只是一般, 绝非绝对)格式为: smtp.xxx.com
    // 网易163邮箱的 SMTP 服务器地址为: smtp.163.com
    public static String myEmailSMTPHost = "smtp.mxhichina.com";

    private List<String> receiveMailAccounts = new ArrayList<String>();
    
    private List<AllResourceVO> resources = new ArrayList<AllResourceVO>();

    
    
	public List<String> getReceiveMailAccounts() {
		return receiveMailAccounts;
	}

	public void setReceiveMailAccounts(List<String> receiveMailAccounts) {
		this.receiveMailAccounts = receiveMailAccounts;
	}

	public void addSource(AllResourceVO resource){
		this.getResources().add(resource);
	}

	public List<AllResourceVO> getResources() {
		return resources;
	}

	public void setResources(List<AllResourceVO> resources) {
		this.resources = resources;
	}

	
	
}
