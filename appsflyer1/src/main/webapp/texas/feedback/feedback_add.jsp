<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/WEB-INF/struts-tags.tld" %>
<html lang="zh">
	<head>
		<meta charset="utf-8"> 
	    <title id="title_1"></title>
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	    
		<!-- <link href="../../css/bootstrap.min.css" rel="stylesheet" /> -->
		 <link rel="stylesheet" href="https://apps.bdimg.com/libs/bootstrap/3.2.0/css/bootstrap.min.css">
	    <script type="text/javascript" src="../../js/jquery.form.js"></script>
	    <script type="text/javascript" src="../../js/jquery-1.9.1.min.js"></script>
	    <script type="text/javascript" src="../../js/bootstrap.min.js"></script>
	    <script type="text/javascript" src="../../js/bootstrap.min.js"></script>
	    
    </head>
    
<body>
   
   <div class="container">
	  <div class="jumbotron">
	    <h1 id="title_2">反馈</h1>
	    <ul class="nav navbar-nav">
            <li><a href="#" onclick="getAskQuestion();" id="title_3">问题反馈</a></li>
            <li><a href="#" onclick="getQuestion();" id="title_4">我的问题</a></li>
            <li>
            	<p class="text-muted">
            	    <input name="language_change" type="radio" value="en" <c:if test="${language=='en'}">checked</c:if> onclick="changeLan();"/>
            			<img src="/appsflyer1/img/icon_us.png"  alt="US" height="27" width="27"/>
            		<%-- <input name="language_change" type="radio" value="ch" <c:if test="${language=='ch'}">checked</c:if> onclick="changeLan();"/>
            			<img src="/appsflyer1/img/icon_cn.png"  alt="US" height="27" width="27"/>  --%>
            		<input name="language_change" type="radio" value="turkey" <c:if test="${language=='turkey'}">checked</c:if> onclick="changeLan();"/>
            			<img src="/appsflyer1/img/icon_tr.png"  alt="US" height="27" width="27"/> 
	             	<%-- <select  class="span2" onchange="changeLan();" id="language_change">
	            		<option value="en" <c:if test="${language=='en'}">selected = "selected"</c:if>  
	            		 style="background-image:/img/icon_us.png" /></option>
	            		<option value="ch" <c:if test="${language=='ch'}">selected = "selected"</c:if> 
	            		style="background-image:/img/icon_ch.png" /></option>
	            	</select>  --%>
            	</p>
            
            </li>
        </ul>
	  </div>
	  <div class="row"></div>
	  <div class="row" >
		    <div class="col-sm-4" id="askQuestion">
					<form class="form-horizontal" role="form" name="formen" enctype="multipart/form-data">
					  <div class="form-group">
						<label for="questionType" class="col-sm-2 control-label" id="lable_1" >问题类型</label>
					    <div class="col-sm-10">
						    <select class="form-control" id="questionType">
						      <option value="Login Problem" id="content_1">登录问题</option>
						      <option value="Purchase Problem" id="content_2">充值问题</option>
						      <option value="Game Activity Problem" id="content_3">活动问题</option>
						      <option value="Bug Report" id="content_4">bug问题</option>
						      <option value="Report Player" id="content_5">举报</option>
						      <option value="Other Problem" id="content_6">其他建议</option>
						    </select>
					    </div>
					  </div>
					  
					  <div class="form-group">
						    <label for="message" class="col-sm-2 control-label" id="lable_2">反馈信息</label>
					    <div class="col-sm-10">
					      <textarea  class="form-control" rows="20" cols="50" id="message" name="message" placeholder="请输信息"></textarea>
					    </div>
					  </div>
					  
					   <div class="form-group">
					     	<label for="image" class="col-sm-2 control-label" id="lable_3">上传图片</label>
					      <div class="col-sm-10">
						     <!-- <input type="file" id="image"> -->
						    <input type="file" name="image" id="image" style="display:none" 
									onChange="document.formen.path.value=this.value" /> 
								<input name="path" readonly /> 
								<input type="button" value="Browse" id="file_1" onclick="document.formen.image.click()" />
						     <p class="help-block" id="content_7">上传图片</p>
						    </div>
					  </div>
					  
					  <div class="form-group">
					    <div class="col-sm-offset-2 col-sm-10">
					      <button type="button" class="btn btn-default" onclick="submitForm();" id="submit_1">提交</button>
					    </div>
					  </div>
					</form>
		    </div>
		    <div class="col-sm-4" id="question" >
		    </div>
		    <div class="col-sm-4" id="reply" >
		    	<font style="color:grep;font-weight:bold">回复框：</font>
		    	<div id="replyContent"  style="border:1px solid #F00;height:300px;width:500px;overflow:auto;float:left;text-align:left;margin-bottom:10px;display:inline">
		    		
		    		
		    		
		    		<div style="clear:both;max-width:80%;float:right;text-align:right;margin-bottom:10px">
						<div>bb</div>
						<div class="left">
							bbbbbbbbbbbbbbbbbbbbbbbbbbb
						</div><br>
					</div>	
		    		
		    		
		    	</div>
		    	<div style="background-color:#C4EFC4;border-radius:10px;float:left;text-align:left;margin-bottom:10px;display:inline">
					<textarea  id="content" style="border-radius:10px;width:80%;margin-left:10px;margin-top:10px;margin-bottom:10px" cols="30" rows="5" name="content"></textarea>
					&nbsp<input type="submit" class="myButton" onclick="submitReply();" style="width:70px;margin-top:10px;margin-bottom:10px;" id="reply_1" value="回复">
					&nbsp<input type="submit" class="myButton" onclick="getQuestion();" style="width:70px;margin-top:10px;margin-bottom:10px;" id="reply_2" value="返回">
				</div>	
		    </div>
	  	</div>
	</div>
	
	<script type="text/javascript">
	function changeLan(){
		var language = $("input[name='language_change']:checked").val();
		var url = document.URL;
		url=url.replace("#","");
		var index = url.lastIndexOf("language");
		if(index >0){
			url = url.substr(0,url.lastIndexOf("=")+1);
			window.location.href=url+language;
		}else{
			window.location.href=url+"&language="+language;
		}
		
	}
		//多语言的参数
		var languageChoose = "${language}";
		var lanObj=null;
		var index = 0;
		//传递到库里的参数
		var appId = "";
		var userId = "";
		var macId = "";
		var channel = "";
	
		var feedbackId="";
		
	$(function(){
		//多语言
		lan();chooseLan(languageChoose);initPage();
		$("#question").hide();
		$("#reply").hide();
		var url = document.URL;
		url=url.replace("#","");
		var strs= new Array(); //定义一数组
		strs=url.split("?");
		var param = strs[1];
		var perParam= new Array(); //定义一数组
		perParam = param.split("&");
		for(var i=0;i<perParam.length;i++){
			var kv= new Array();
			kv = perParam[i].split("=");
			if(kv[0] == "appId"){
				appId=kv[1];
			}
			if(kv[0] == "userId"){
				userId=kv[1];
			}
			if(kv[0] == "macId"){
				macId=kv[1];
			}
			if(kv[0] == "channel"){
				channel=kv[1];
			}
		}
	}); 
	
	function getAskQuestion(){
		$("#askQuestion").show();
		$("#question").hide();
		$("#reply").hide();
	}
	//获取 用户macId 对应的所有问题
	function getQuestion(){
		$("#question").empty();
		$.get("/feedback.do?method=getQuestionList&channel="+channel+"&macId="+macId,function(data){
			var obj = eval('(' + data + ')');
			var str = "";
			for(var i=0;i<obj.length;i++){
				str += "<a href='#' onclick='reply("+obj[i].id+")'><p> "+(i+1)+" :"+obj[i].message+"</p> </br></a>";
			}
			$("#question").append(str);
		});
		$("#question").show();
		$("#askQuestion").hide();
		$("#reply").hide();
	}
	//每个问题的对话框
	function reply(id){
		feedbackId = id;
		$("#replyContent").empty();
		$("#content").val("");
		$.get("/feedback.do?method=getQuestionByFeedbackId&id="+id,function(data){
			var obj = eval('(' + data + ')');
			var str = "";
			for(var i=0;i<obj.length;i++){
				if(obj[i].role=="USER"){
					str+=" <div style=\"clear:both;max-width:80%;float:left;text-align:left;margin-bottom:10px\">"
					   +" <div>USER "+obj[i].time_stamp+"</div>"
					   +" <div class=\"right\">"
					   +obj[i].content
					   +" </div><br>"
					   +" </div>";
					
				}else{
					str+=" <div style=\"clear:both;max-width:80%;float:right;text-align:left;margin-bottom:10px\">"
					   +" <div>GM "+obj[i].time_stamp+"</div>"
					   +" <div class=\"right\">"
					   +obj[i].content
					   +" </div><br>"
					   +" </div>";
					
				}
				
			}
			$("#replyContent").append(str); 
		});
		$("#reply").show();
		$("#question").hide();
		$("#askQuestion").hide();
	}
	/**
		提交数据
	**/
	
	function submitForm(){
		var image = $("#image").val();
		var questionType = $("#questionType").val();
		var message = $("#message").val();
		/* $.post(
			"/feedback.do?method=addData",
			{
				"questionType":questionType,
				"message":message,
				"appId":appId,
				"userId":userId,
				"macId":macId
			},
			function(data){
				getQuestion();
			}
		); */
		var formData = new FormData();
		formData.append("questionType",questionType);
		formData.append("message",message);
		formData.append("appId",appId);
		formData.append("userId",userId);
		formData.append("macId",macId);
		formData.append("channel",channel);
		if(document.getElementById("image").files[0] != null){
			formData.append("image",document.getElementById("image").files[0]);
		}
		 $.ajax({
             url: "/feedback.do?method=addData",
             type: "POST",
             data:formData,
             /**
             *必须false才会自动加上正确的Content-Type
             */
             contentType: false,
             /**
             * 必须false才会避开jQuery对 formdata 的默认处理
             * XMLHttpRequest会对 formdata 进行正确的处理
             */
             processData: false,
             success: function (data) {
            	if("success" == data){
	            	 getQuestion();
            	}else{
            		alert(data);
            	}
             },
             error: function (data) {
            	 alert(lanObj.exception_1[index]);
             }
         });
	}
	//回复提交答案
	function submitReply(){
		var content = $("#content").val();
		if (content == null || content == "" || content.trim() == "")
		{
			alert(lanObj.exception_2[index]);
			return;
		} 
		$.post(
			"/feedback.do?method=addReplyData",
			{
				"feedbackId":feedbackId,
				"role":"USER",
				//"satisfaction":satisfaction,
				"content":content
			},
			function(data){
				reply(feedbackId);
			}
		);
	}
	//初始化页面
	function initPage(){
		$("#title_1").empty();$("#title_1").append(lanObj.title_1[index]);
		$("#title_2").empty();$("#title_2").append(lanObj.title_2[index]);
		$("#title_3").empty();$("#title_3").append(lanObj.title_3[index]);
		$("#title_4").empty();$("#title_4").append(lanObj.title_4[index]);
		$("#lable_1").empty();$("#lable_1").append(lanObj.lable_1[index]);
		$("#lable_2").empty();$("#lable_2").append(lanObj.lable_2[index]);
		$("#lable_3").empty();$("#lable_3").append(lanObj.lable_3[index]);
		$("#content_1").empty();$("#content_1").append(lanObj.content_1[index]);
		$("#content_2").empty();$("#content_2").append(lanObj.content_2[index]);
		$("#content_3").empty();$("#content_3").append(lanObj.content_3[index]);
		$("#content_4").empty();$("#content_4").append(lanObj.content_4[index]);
		$("#content_5").empty();$("#content_5").append(lanObj.content_5[index]);
		$("#content_6").empty();$("#content_6").append(lanObj.content_6[index]);
		$("#content_7").empty();$("#content_7").append(lanObj.content_7[index]);
		$("#submit_1").empty();$("#submit_1").append(lanObj.submit_1[index]);
		$("#reply_1").attr("value",lanObj.reply_1[index]);
		$("#reply_2").attr("value",lanObj.reply_2[index]);
		$("#message").attr("placeholder",lanObj.input_1[index]);
		$("#file_1").attr("value",lanObj.file_1[index]);
	}
	//选择是那种 
	function chooseLan(languageChoose){
		var lanArray = lanObj.language;
		for(var i=0;i<lanArray.length;i++){
			var l = lanArray[i];
			if(languageChoose == l){
				index=i;
			}
		}
	}
	function lan(){
		lanObj = {
				"language":
					[
						 "en",
						 "ch",
						 "turkey"
						
					 ],
				"title_1":
					[
						  "User Feedback",
						  "用户反馈",
						  "Kullanıcı Geri Bildirimi"
					  ],
				"title_2":
					[
						  "Feedback",
						  "反馈",
						  "Geri bildirim"
						  
					  ],
				"title_3":
					[
						  "Question Feedback",
						  "问题反馈",
						  "Soru Geri Bildirimi"
					  ],
				"title_4":
					[
						  "My Question",
						  "我的问题",
						  "Sorum",
					  ],
				"lable_1":
					[
						  "Question Type:",
						  "问题类型:",
						  "Soru tipi:"
					  ],
				"lable_2":
					[
						  "Feedback info:",
						  "反馈信息:",
						  "Geri bildirim bilgisi:"
					  ],
				"lable_3":
					[
						  "Upload picture:",
						  "上传图片:",
						  "Resmi yükle:"
					  ],
				"content_1":
					[
						  "Login Question",
						  "登录问题",
						  "Giriş Sorunu",
					  ],
				"content_2":
					[
						  "Recharge Question",
						  "充值问题",
						  "Şarj Sorunu"
					  ],
				"content_3":
					[
						  "Activity Question",
						  "活动问题",
						  "Etkinlik Sorunu"
					  ],
				"content_4":
					[
						  "Bug Question",
						  "bug问题",
						  "Bug Sorunu"
					  ],
				"content_5":
					[
						  "Report",
						  "举报",
						  "Rapor"
					  ],
				"content_6":
					[
						  "Other Suggest",
						  "其他建议",
						  "Diğer Öneri"
					  ],
				"content_7":
					[
						  "Upload Picture",
						  "上传图片",
						  "Resmi yükle",
					  ],
				"submit_1":
					[
						  "submit",
						  "提交",
						  "Gönder"
						  
					  ],
				"exception_1":
					[
						  "Service Question",
						  "服务器问题",
						  "Servis Sorunu"
					  ],
				"exception_2":
					[
						  "Answer can't be null",
						  "回复信息 不能为空",
						  "Cevap boş olamaz"
					  ],
				"input_1":
					[
						  "Please Incoming Message",
						  "请输入信息",
						  "Lütfen içeriği giriniz"
					  ],
				"file_1":
					[
						  "Browse",
						  "浏览",
						  "Gözat"
					  ],
				"reply_1":
					[
						  "Reply",
						  "回复",
						  "Cevapla"
					  ],
				"reply_2":
					[
						  "Back",
						  "返回",
						  "Geri"
					  ],
		}
	}
	</script>
</body>
</html>

