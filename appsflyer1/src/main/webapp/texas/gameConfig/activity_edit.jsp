<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<script src="../../js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="../../js/jquery.form.js"></script>

<div class="widget-header">
  <div class="widget-toolbar no-border">
  <ul class="nav nav-tabs" id="recent-tab">
	<li <c:if test="${one == null || one.activityType==1}" > class="active" </c:if>  id="winner"><a data-toggle="tab" href="#winner-tab"
		aria-expanded="true">大玩家</a></li>

	<li <c:if test="${one.activityType==2}" > class="active" </c:if>  id="lucky"><a data-toggle="tab" href="#lucky-tab"
		aria-expanded="false">幸运牌型</a></li>

	<li <c:if test="${one.activityType==3}" > class="active" </c:if> id="charge"><a data-toggle="tab" href="#charge-tab"
		aria-expanded="false">充值</a></li>
		
		
	<li <c:if test="${one.activityType==5}" > class="active" </c:if>  id="everyday"><a data-toggle="tab" href="#charge-tab"
		aria-expanded="false">每日登录</a></li>
		
		
	<li <c:if test="${one.activityType==6}" > class="active" </c:if> id="expactivity"><a data-toggle="tab" href="#charge-tab"
		aria-expanded="false">经验活动</a></li>
		
	<li <c:if test="${one.activityType==7}" > class="active" </c:if>  id="luckySlot"><a data-toggle="tab" href="#charge-tab"
		aria-expanded="false">幸运老虎机</a></li>
	<li <c:if test="${one.activityType==8}" > class="active" </c:if>  id="levelActivity"><a data-toggle="tab" href="#charge-tab"
		aria-expanded="false">冲级活动</a></li>
	<li <c:if test="${one.activityType==9}" > class="active" </c:if>  id="ContinuousPlayForGift"><a data-toggle="tab" href="#charge-tab"
		aria-expanded="false">连续游戏送大礼</a></li>
	<li<c:if test="${one.activityType==10}" > class="active" </c:if>  id="ConsumeForGift"><a data-toggle="tab" href="#charge-tab"
		aria-expanded="false">消耗返利 </a></li>
	<li <c:if test="${one.activityType==11}" > class="active" </c:if>  id="MakeFriendsForGift"><a data-toggle="tab" href="#charge-tab"
		aria-expanded="false">广交好友 </a></li>
	<li <c:if test="${one.activityType==12}" > class="active" </c:if>  id="PreferenceFavorForGift"><a data-toggle="tab" href="#charge-tab"
		aria-expanded="false">情有独钟</a></li>
	<li <c:if test="${one.activityType==13}" > class="active" </c:if>  id="IAmWinnerForGift"><a data-toggle="tab" href="#charge-tab"
		aria-expanded="false">我是大赢家 </a></li>
	<li <c:if test="${one.activityType==14}" > class="active" </c:if>  id="ExtremePursuitForGift"><a data-toggle="tab" href="#charge-tab"
		aria-expanded="false">极限追求 </a></li>
		
	<li <c:if test="${one.activityType==15}" > class="active" </c:if>  id="grandForDollarGift"><a data-toggle="tab" href="#charge-tab"
		aria-expanded="false">累计充值(美元)</a></li>
	<li <c:if test="${one.activityType==18}" > class="active" </c:if>  id="grandForGift"><a data-toggle="tab" href="#charge-tab"
		aria-expanded="false">累计充值(次数)</a></li>
	<li <c:if test="${one.activityType==16}" > class="active" </c:if>  id="grandDayForGift"><a data-toggle="tab" href="#charge-tab"
		aria-expanded="false">连续累计充值(特指天数)</a></li>
	<li <c:if test="${one.activityType==17}" > class="active" </c:if>  id="allPeopleGrandForGift"><a data-toggle="tab" href="#charge-tab"
		aria-expanded="false">全服累计充值 </a></li>
		

  </ul>
  </div>
</div>

<form class="form-horizontal" action="activity.do?method=generateActivityJsonData" method="post" id="form" enctype="multipart/form-data">

	<h2 id="title" style="margin-left:50px;padding-bottom:30px">大玩家活动配置</h2>
    <input id="rule_pack" type="hidden" style="width:100%" name="rulePack" /> 
    <div id="type-container">
    	
    </div>
	
	<div class="control-group">
		<label class="control-label" for="form-field-accout">活动名称(English):</label>
		<div class="controls">
			<input type="text" name="title" value="${one.title}"  class="validation" requiry="true">
		</div>
	</div>
		<div class="control-group">
		<label class="control-label" for="form-field-accout">活动名称(简体):</label>
		<div class="controls">
			<input type="text" name="title_cn" value="${one.title_cn}"  class="validation" requiry="true">
		</div>
	</div>
		<div class="control-group">
		<label class="control-label" for="form-field-accout">活动名称（繁体）:</label>
		<div class="controls">
			<input type="text" name="title_tw" value="${one.title_tw}"  class="validation" requiry="true">
		</div>
	</div>

	<div class="control-group">
		<label class="control-label" for="form-field-accout">开始时间:</label>
		<div class="controls">
			<input type="text"  id="d4311" name="startTime" value="${one.start_time}" class="Wdate" onClick="WdatePicker({maxDate:'#F{$dp.$D(\'d4312\')}',dateFmt:'yyyy-MM-dd'})" style="width:80px" requiry="true"/>
			截止时间：<input type="text"  id="d4312" name="endTime" value="${one.end_time}" class="Wdate"  onClick="WdatePicker({minDate:'#F{$dp.$D(\'d4311\')}',realDateFmt:'yyyy-MM-dd',dateFmt:'yyyy-MM-dd'})" style="width:80px" requiry="true"/> 
		注：包括截止日期那一天
		</div>
	</div>

	<div class="control-group">
		<label class="control-label" for="form-field-accout">Short Description(English):</label>
		<div class="controls">
			<textarea rows="1" cols="10" style="width: 50%" name="activityDesc">${one.activityDesc}</textarea>
		</div>
	</div>
		<div class="control-group">
		<label class="control-label" for="form-field-accout">描述（简体）:</label>
		<div class="controls">
			<textarea rows="1" cols="10" style="width: 50%" name="activityDesc_cn">${one.activityDesc_cn}</textarea>
		</div>
	</div>
		<div class="control-group">
		<label class="control-label" for="form-field-accout">描述（繁体）:</label>
		<div class="controls">
			<textarea rows="1" cols="10" style="width: 50%" name="activityDesc_tw">${one.activityDesc_tw}</textarea>
		</div>
	</div>

	<div class="control-group" id="dailyID">
		<label class="control-label" for="form-field-accout">每天重复:</label>
		<div class="controls" style="padding-left:10px;margin-top:5px" >
			<input type="radio" name="daily" value="1" > 是
			<input type="radio" name="daily" value="0" checked> 否
		</div>
	</div>

	<div class="control-group">
		<label class="control-label" for="form-field-accout">选择活动跳转链接:</label>
		<div class="controls">
			<select name="pageLink">
			    <option value="0">请选择活动跳转</option>
				<option value="1">快捷商店</option>
				<option value="2">邮件</option>
				<option value="3">好友</option>
				<option value="4">玩家信息</option>
				<option value="5">成就</option>
				<option value="6">排行</option>
				<option value="7">设置</option>
				<option value="8">任务</option>
				<option value="11">活动</option>
				<option value="12">奖励列表</option>
				<option value="100">商店</option>
				<option value="101">个人信息</option>
				<option value="102">聊天</option>
				<option value="103">帮助</option>
				<option value="104">德州房间</option>
				<option value="200">德州游戏</option>
			</select>
		</div>
	</div>
	<!-- 活动的图片 -->
	<div class="control-group">
		<label class="control-label" for="form-field-accout">活动图片(English):</label>
		<div class="controls">
		 
			<input type="" id="pic" name="pic" >
			<input class="selectImg" type="button" input_name="pic" value="选择图片">			
		</div>
	</div>

	<div class="control-group">
		<label class="control-label" for="form-field-accout">活动图片(简体):</label>
		<div class="controls">
			<input type="" id="pic_cn" name="pic_cn">
			<input class="selectImg" type="button" input_name="pic_cn" value="选择图片">
		</div>
	</div>
	
	<div class="control-group">
		<label class="control-label" for="form-field-accout">活动图片(繁体):</label>
		<div class="controls">
			<input type="" id="pic_tw" name="pic_tw">
			<input class="selectImg" type="button" input_name="pic_tw" value="选择图片">
		</div>
	</div>
	<!-- 大厅的图片 -->
	<div class="control-group">
		<label class="control-label" for="form-field-accout">大厅图片(English):</label>
		<div class="controls">
		 
			<input type="" id="hall_pic" name="hall_pic" >
			<input class="selectImg" type="button" input_name="hall_pic" value="选择图片">			
		</div>
	</div>

	<div class="control-group">
		<label class="control-label" for="form-field-accout">大厅图片(简体):</label>
		<div class="controls">
			<input type="" id="hall_pic_cn" name="hall_pic_cn">
			<input class="selectImg" type="button" input_name="hall_pic_cn" value="选择图片">
		</div>
	</div>
	
	<div class="control-group">
		<label class="control-label" for="form-field-accout">大厅图片(繁体):</label>
		<div class="controls">
			<input type="" id="hall_pic_tw" name="hall_pic_tw">
			<input class="selectImg" type="button" input_name="hall_pic_tw" value="选择图片">
		</div>
	</div>
	<div  class="control-group">
		<hr>
		
		<label class="control-label" for="form-field-accout">添加活动规则:</label>
		
		<label id="condition_number" style="margin-left:20px;float:left;padding:5px"></label>
		<div id="trigger" class="controls" style="background: #C2D6D6; margin-left:20px;float:left;">
			
		</div>
			
		<div id="jiangli_group_div" class="controls" style="background: #C2D6D6; margin-left:20px; width:550px; float:left;padding:5px">		  
			奖励：
				<select class="s_1" style="width:150px;" >
					<c:forEach items="${rewards}" var="reward">
						<option value="${reward.getKey()}" title="${reward.getValue()}" >${reward.getValue()}</option>
					</c:forEach>
				</select>
			数量：
				<input class="s_2" style="width:70px;" type="text">
			VIP等级：<input class="s_4" name="vippoint" style="width:70px;" type="text">
			<input type="button" value="+" id="btn_add" style="float:right; bottom:0;"/> 
				<br>
		</div>
		
		<div class="controls" style="background: #C2D6D6; margin-left:20px;float:left;padding:5px">
		可领取次数： <input id="maxNum" type="text" style="width:40px" />
				</div>
		<input id="addTrigger" type="button" style="margin-left:10px" value=""/>
	</div>
	<hr>

	<div class="control-group">
		<label class="control-label" for="form-field-accout">已添加规则:</label>
		<div id="added-rule" class="controls">
			
		</div>
	</div>

	<div class="form-actions">
		<button class="btn btn-small btn-info" type="submit" id="submit"><i class="icon-ok"></i>提交</button>	
		&nbsp; &nbsp; &nbsp;
		<button class="btn btn-small" type="reset" id="reset"><i class="icon-undo"></i> Reset</button>
		<c:if test="${iscancel=='1'}">
			&nbsp; &nbsp; &nbsp;
			<button class="btn btn-small" type="reset" onclick="goback()"><i class="icon-undo"></i>取消</button>
		</c:if>
	</div>


</form>

<div id="jiangli_tpl" style="display: none;">
	<div>
		奖励：
		<select class="s_1" style="width: 100px;" name="rule['0']['reward']">
			<c:forEach items="${rewards}" var="reward">
				<option value="${reward.getKey() }" title="${reward.getValue()}">${reward.getValue()}</option>
			</c:forEach>
		</select> 
		数量：
		<input class="s_2" style="width: 70px;" type="text"> 
		VIP等级：
		<input class="s_4" name="vippoint" style="width:70px;" type="text">
		<input type="button" value="删除" class="s_3" />
	</div>
</div>

<div id="vip_dianshu_1" style="display: none;">
	VIP等级：
	<input class="s_4" name="vippoint" style="width:70px;" type="text">
	<br>
</div>

<div id="trigger_each" style="padding:5px">
	<input class="trigger_id" type="hidden" /> 
	<label name="trigger_title" style="float:left"> </label>
	<input class="trigger_value" type="text" style="width: 50px;float:right"/>
</div>

<div id="select_image" style="width:80%;background-color:red;position:fixed;top:200px;left:200px;right:auto;display:none">
	<c:forEach items="${images}" var="img">
		<img class="image" src="${G_ip}/activity/${img.image_name}" style="height:150px; margin:10px;float:left;" value="${img.image_name}" ></img>
	</c:forEach>
	<button value="关闭" id="select_image_close">关闭</button>
</div>

<script>
$(document).ready(function () {
var slotType = [];
var slotTypeIcon = [];
	
	//初始化 slotType 和 slotTypeIcon
	$.get("activity.do?method=getAllSlots",function(data){
		slotType= eval('(' + data + ')'); 
	});
	$.get("activity.do?method=getAllSlotIocns",function(data){
		slotTypeIcon= eval('(' + data + ')'); 
	});
	
	var number = 1;
	var paixing = ["高牌", "一对", "两对", "三张", "顺子", "同花", "葫芦", "4张", "同花顺", "皇家同花顺"];
	var littleblind = ["2", "5", "10", "15", "20", "25", "50", "100", "200", "500", "1000", "2000", "5000", "10000", "20000", "50000", "100000", "200000"];
    /* var slotType = ["classic","四大美人","水果","沙滩","吸血鬼老虎机","埃及艳后","美人鱼",
                    "澳门女神","白蛇传","马来网红","日月潭","维密","宙斯","石器时代",
                    "狮身人面像","阿兹特克文明","狼老虎机","动物猫","水晶魔法","大象","老虎",
                    "西部牛仔","东方龙","巴西风情","忍者","女巫魔法","犀牛","海洋世界","西方龙","福尔摩斯"];
	var slotTypeIcon = ["wild","scatter", "777图标", "7图标","3bar","2bar","bar","黑桃","红桃","梅花","方块"]; */
	

	$('#activity_type').val("1");
	$('#title').text("大玩家活动配置");
	$('#trigger div').remove();
	$('#type-container input').remove();
	$('#type-container').append('	<input type="hidden" id="activity_type" name="activityType" value="1"/>');
	var url="activity.do?method=getTriggersByType&activity_type="+$('#activity_type').val();
	
	$.get(geturl(url), function(response){
		 var result = $.parseJSON(response);
		 $.each(result, function(key, value)
		{	if(key == "1")
			{
			 $('#trigger').append('<div id="trigger_each" style="padding:5px;"> <input class="trigger_id" type="hidden" value='+key+'> <label name="trigger_title" style="float:left;margin-top:5px;margin-bottom:5px">'+value+':</label> <select class="trigger_value" type="text" style="width: 100px;float:right;margin:5px"><option value="1">初级场</option><option value="2">中级场</option><option value="3">高级场</option> </select></div>');
			}
		else
			{$('#trigger').append('<div id="trigger_each" style="padding:5px;"> <input class="trigger_id" type="hidden" value='+key+'> <label name="trigger_title" style="float:left;margin-top:5px;margin-bottom:5px">'+value+':</label> <input class="trigger_value" type="text" style="width: 85px;float:right;margin:5px"/></div>');}
		});
	});
	
	<%--显示每天是否重复 ：本功能不要每天重复 --%>
	showDaily();
	
$('#winner').click(function(){
	$('#activity_type').val("1");
	$('#title').text("大玩家活动配置");
	$('#trigger div').remove();
	$('#trigger br').remove();
	$('#type-container input').remove();
	$('#type-container').append('	<input type="hidden" id="activity_type" name="activityType" value="1"/>');
	var url="activity.do?method=getTriggersByType&activity_type="+$('#activity_type').val();
	$.get(geturl(url), function(response){
		 var result = $.parseJSON(response);
		 $.each(result, function(key, value)
		{if(key == "1")
		{
			 $('#trigger').append('<div id="trigger_each" style="padding:5px;"> <input class="trigger_id" type="hidden" value='+key+'> <label name="trigger_title" style="float:left;margin-top:5px;margin-bottom:5px">'+value+':</label> <select class="trigger_value" type="text" style="width: 100px;float:right;margin:5px"><option value="1">初级场</option><option value="2">中级场</option><option value="3">高级场</option> </select></div>');
			}
		else{
		$('#trigger').append('<div id="trigger_each" style="padding:5px;"> <input class="trigger_id" type="hidden" value='+key+'> <label name="trigger_title" style="float:left;margin-top:5px;margin-bottom:5px">'+value+':</label> <input class="trigger_value" type="text" style="width: 85px;float:right;margin:5px"/></div>');
		}
		});
	});
	
	<%--显示每天是否重复 ：本功能不要每天重复 --%>
	showDaily();
});
$('#lucky').click(function(){
	$('#activity_type').val("2");
	$('#title').text("幸运牌型活动配置");
	$('#trigger div').remove();
	$('#trigger br').remove();
	$('#type-container input').remove();
	$('#type-container').append('	<input type="hidden" id="activity_type" name="activityType" value="2"/>');
	var url="activity.do?method=getTriggersByType&activity_type="+$('#activity_type').val();
	$.get(geturl(url), function(response){
		 var result = $.parseJSON(response);
		 $.each(result, function(key, value)
		{
		    if(key == "3")
			{
		        var content = '<div id="trigger_each" style="padding:5px;"> <input class="trigger_id" type="hidden" value='+key+'> <label name="trigger_title" style="float:left;margin-top:5px;margin-bottom:5px">'+value+':</label> <select class="trigger_value" type="text" style="width: 100px;float:right;margin:5px">';	
				
		        for(var i=0; i<paixing.length; i++)
		        {
		        	content += '<option value='+i+'>'+paixing[i]+'</option>';
		        }
		        
		        $('#trigger').append(content+'</select></div>');
				
			}
			else
			{
				 var content = '<div id="trigger_each" style="padding:5px;"> <input class="trigger_id" type="hidden" value='+key+'> <label name="trigger_title" style="float:left;margin-top:5px;margin-bottom:5px">'+value+':</label> <select class="trigger_value" type="text" style="width: 100px;float:right;margin:5px">';	
					
			        for(var i=0; i<littleblind.length; i++)
			        {
			        	content += '<option value='+littleblind[i]+'>'+littleblind[i]+'</option>';
			        }
			        
			        $('#trigger').append(content+'</select></div>');
			}
			});
	});
	<%--显示每天是否重复 ：本功能不要每天重复 --%>
	showDaily();
});
$('#charge').click(function(){
	$('#activity_type').val("3");
	$('#title').text("充值活动配置");
	$('#trigger div').remove();
	$('#trigger br').remove();
	$('#type-container input').remove();
	$('#type-container').append('	<input type="hidden" id="activity_type" name="activityType" value="3"/>');
	var url="activity.do?method=getTriggersByType&activity_type="+$('#activity_type').val();
	$.get(geturl(url), function(response){
		 var result = $.parseJSON(response);
		 console.log(result);
		 $.each(result, function(key, value)
		{
			 $('#trigger').append('<div id="trigger_each" style="padding:5px;"> <input class="trigger_id" type="hidden" value='+key+'> <label name="trigger_title" style="float:left;margin-top:5px;margin-bottom:5px">'+value+':</label> <select class="trigger_value" type="text" id="trigger-number" style="width: 80px;float:right;margin:5px"> <option value="1">$0.99</option> <option value="2">$4.99</option><option value="3">$9.99</option><option value="4">$19.99</option><option value="5">$49.99</option><option value="6">$99.99</option> <option value="99999">99999<option></select></div>');
		});
	});
	
	<%--显示每天是否重复 ：本功能不要每天重复 --%>
	showDaily();
});

$('#everyday').click(function(){
	$('#activity_type').val("5");
	$('#title').text("每日登录活动");
	$('#trigger div').remove();
	$('#trigger br').remove();
	$('#type-container input').remove();
	$('#type-container').append('	<input type="hidden" id="activity_type" name="activityType" value="5"/>');
	var url="activity.do?method=getTriggersByType&activity_type="+$('#activity_type').val();
	$.get(geturl(url), function(response){
		 var result = $.parseJSON(response);
		 console.log(result);
		 $.each(result, function(key, value)
		{
			 $('#trigger').append('<div id="trigger_each" style="padding:5px;"> <input class="trigger_id" type="hidden" value='+key+'> <label name="trigger_title" style="float:left;margin-top:5px;margin-bottom:5px">'+value+':</label> <input class="trigger_value" type="text" style="width: 85px;float:right;margin:5px"/></div>');
		});
	});
	
	<%--显示每天是否重复 ：本功能不要每天重复 --%>
	showDaily();
});

$('#expactivity').click(function(){
	$('#activity_type').val("6");
	$('#title').text("经验活动");
	$('#trigger div').remove();
	$('#trigger br').remove();
	$('#type-container input').remove();
	$('#type-container').append('	<input type="hidden" id="activity_type" name="activityType" value="6"/>');
	var url="activity.do?method=getTriggersByType&activity_type="+$('#activity_type').val();
     
	$.get(geturl(url), function(response){
		 var result = $.parseJSON(response);
		 console.log(result);
		 //window.alert(result);
		 $.each(result, function(key, value)
		{
		$('#trigger').append('<div id="trigger_each" style="padding:5px;"> <input class="trigger_id" type="hidden" value='+key+'> <label name="trigger_title" style="float:left;margin-top:5px;margin-bottom:5px">'+value+':</label> <input class="trigger_value" type="text" style="width: 85px;float:right;margin:5px"/></div>');
		});
	});
	
	<%--显示每天是否重复 ：本功能不要每天重复 --%>
	showDaily();
});

$('#luckySlot').click(function(){
	$('#activity_type').val("7");
	$('#title').text("幸运老虎机");
	$('#trigger div').remove();
	$('#trigger br').remove();
	$('#type-container input').remove();
	$('#type-container').append('	<input type="hidden" id="activity_type" name="activityType" value="7"/>');
	var url="activity.do?method=getTriggersByType&activity_type="+$('#activity_type').val();
	$.get(geturl(url), function(response){
		 var result = $.parseJSON(response);
		 console.log(result);
		 $.each(result, function(key, value)
		{
		if(key == "8")
			{
		        var content = '<div id="trigger_each" style="padding:5px;"> <input class="trigger_id" type="hidden" value='+key+'> <label name="trigger_title" style="float:left;margin-top:5px;margin-bottom:5px">'+value+':</label> <select class="trigger_value" type="text" style="width: 100px;float:right;margin:5px">';	
		        for(var i=0; i<slotType.length; i++){
		        	content += '<option value='+slotType[i].type+'>'+slotType[i].langDesc+'</option>';
		        }
		        
		        $('#trigger').append(content+'</select></div>');
				
			}
			else
			{
				$('#trigger').append('<div id="trigger_each" style="padding:5px;"> <input class="trigger_id" type="hidden" value='+key+'> <label name="trigger_title" style="float:left;margin-top:5px;margin-bottom:5px">'+value+':</label> <input class="trigger_value" type="text" style="width: 85px;float:right;margin:5px"/></div>');
			}
		});
	});
	
	<%--显示每天是否重复 ：本功能不要每天重复 --%>
	showDaily();
});


$('#levelActivity').click(function(){
	$('#activity_type').val("8");
	$('#title').text("冲级活动");
	$('#trigger div').remove();
	$('#trigger br').remove();
	$('#type-container input').remove();
	$('#type-container').append('	<input type="hidden" id="activity_type" name="activityType" value="8"/>');
	var url="activity.do?method=getTriggersByType&activity_type="+$('#activity_type').val();
	$.get(geturl(url), function(response){
		 var result = $.parseJSON(response);
		 console.log(result);
		 $.each(result, function(key, value)
		{
			 $('#trigger').append('<div id="trigger_each" style="padding:5px;"> <input class="trigger_id" type="hidden" value='+key+'> <label name="trigger_title" style="float:left;margin-top:5px;margin-bottom:5px">'+value+':</label> <input class="trigger_value" type="text" style="width: 85px;float:right;margin:5px"/></div>');
		});
	});
	<%--显示每天是否重复 ：本功能不要每天重复 --%>
	showDaily();
});
	<%-- 
		新增
	--%>
	function hiddenDaily(){
		<%--隐藏每天是否重复 ：本功能不要每天重复 --%>
		$("input[name='daily'][value=0]").attr("checked",true); 
		$("input[name='daily'][value=1]").attr("checked",false); 
		$("#dailyID").hide();
		
		/* $("#jiangli_tpl #vip_dianshu_1").remove();
		$("#jiangli_group_div #vip_dianshu_1").remove(); */
		$("input[naem='vippoint']").val(0);
	}
	function showDaily(){
		<%--显示每天是否重复 ：本功能不要每天重复 --%>
		$("#dailyID").show();
		/* $("#jiangli_tpl #vip_dianshu_1").remove();
		$("#jiangli_group_div #vip_dianshu_1").remove(); */
		$("input[naem='vippoint']").val(0);
		
	}
	<%--连续游戏送大礼 --%>

	$('#ContinuousPlayForGift').click(function(){
		$('#activity_type').val("9");
		$('#title').text("连续游戏送大礼");
		$('#trigger div').remove();
		$('#trigger br').remove();
		$('#type-container input').remove();
		$('#type-container').append('	<input type="hidden" id="activity_type" name="activityType" value="9"/>');
		var url="activity.do?method=getTriggersByType&activity_type="+$('#activity_type').val();
		$.get(geturl(url), function(response){
			 var result = $.parseJSON(response);
			 console.log(result);
			 $.each(result, function(key, value)
			{
				 $('#trigger').append('<div id="trigger_each" style="padding:5px;"> <input class="trigger_id" type="hidden" value='+key+'> <label name="trigger_title" style="float:left;margin-top:5px;margin-bottom:5px">'+value+':</label> <input class="trigger_value" type="text" style="width: 85px;float:right;margin:5px"/></div>');
			});
		});
		
		<%--隐藏每天是否重复 ：本功能不要每天重复 --%>
		hiddenDaily();
	});
	<%--消耗返利 --%>

	$('#ConsumeForGift').click(function(){
		$('#activity_type').val("10");
		$('#title').text("消耗返利");
		$('#trigger div').remove();
		$('#trigger br').remove();
		$('#type-container input').remove();
		$('#type-container').append('	<input type="hidden" id="activity_type" name="activityType" value="10"/>');
		var url="activity.do?method=getTriggersByType&activity_type="+$('#activity_type').val();
		$.get(geturl(url), function(response){
			 var result = $.parseJSON(response);
			 console.log(result);
			 $.each(result, function(key, value)
			{
				 $('#trigger').append('<div id="trigger_each" style="padding:5px;"> <input class="trigger_id" type="hidden" value='+key+'> <label name="trigger_title" style="float:left;margin-top:5px;margin-bottom:5px">'+value+':</label> <input class="trigger_value" type="text" style="width: 85px;float:right;margin:5px"/></div>');
			});
		});
		<%--显示每天是否重复 ：本功能不要每天重复 --%>
		showDaily();
	});
	<%--广交好友 --%>

	$('#MakeFriendsForGift').click(function(){
		$('#activity_type').val("11");
		$('#title').text("广交好友");
		$('#trigger div').remove();
		$('#trigger br').remove();
		$('#type-container input').remove();
		$('#type-container').append('	<input type="hidden" id="activity_type" name="activityType" value="11"/>');
		var url="activity.do?method=getTriggersByType&activity_type="+$('#activity_type').val();
		$.get(geturl(url), function(response){
			 var result = $.parseJSON(response);
			 console.log(result);
			 $.each(result, function(key, value)
			{
				 $('#trigger').append('<div id="trigger_each" style="padding:5px;"> <input class="trigger_id" type="hidden" value='+key+'> <label name="trigger_title" style="float:left;margin-top:5px;margin-bottom:5px">'+value+':</label> <input class="trigger_value" type="text" style="width: 85px;float:right;margin:5px"/></div>');
			});
		});
		<%--显示每天是否重复 ：本功能不要每天重复 --%>
		hiddenDaily();
	});
	<%--情有独钟 --%>

	$('#PreferenceFavorForGift').click(function(){
		$('#activity_type').val("12");
		$('#title').text("情有独钟");
		$('#trigger div').remove();
		$('#trigger br').remove();
		$('#type-container input').remove();
		$('#type-container').append('	<input type="hidden" id="activity_type" name="activityType" value="12"/>');
		var url="activity.do?method=getTriggersByType&activity_type="+$('#activity_type').val();
		$.get(geturl(url), function(response){
			 var result = $.parseJSON(response);
			 console.log(result);
			 $.each(result, function(key, value)
			{
				 if(key == "14")
					{
				        var content = '<div id="trigger_each" style="padding:5px;"> <input class="trigger_id" type="hidden" value='+key+'> <label name="trigger_title" style="float:left;margin-top:5px;margin-bottom:5px">'+value+':</label> <select class="trigger_value" type="text" style="width: 100px;float:right;margin:5px">';	
				        for(var i=0; i<slotType.length; i++){
				        	content += '<option value='+slotType[i].type+'>'+slotType[i].langDesc+'</option>';
				        }
				        
				        $('#trigger').append(content+'</select></div>');
						
					}
					else
					{
						$('#trigger').append('<div id="trigger_each" style="padding:5px;"> <input class="trigger_id" type="hidden" value='+key+'> <label name="trigger_title" style="float:left;margin-top:5px;margin-bottom:5px">'+value+':</label> <input class="trigger_value" type="text" style="width: 85px;float:right;margin:5px"/></div>');
					}
			});
		});
		
		<%--隐藏每天是否重复 ：本功能不要每天重复 --%>
		hiddenDaily();
	});
	<%--我是大赢家 --%>

	$('#IAmWinnerForGift').click(function(){
		$('#activity_type').val("13");
		$('#title').text("我是大赢家");
		$('#trigger div').remove();
		$('#trigger br').remove();
		$('#type-container input').remove();
		$('#type-container').append('	<input type="hidden" id="activity_type" name="activityType" value="13"/>');
		var url="activity.do?method=getTriggersByType&activity_type="+$('#activity_type').val();
		$.get(geturl(url), function(response){
			 var result = $.parseJSON(response);
			 console.log(result);
			 $.each(result, function(key, value)
			{
				 $('#trigger').append('<div id="trigger_each" style="padding:5px;"> <input class="trigger_id" type="hidden" value='+key+'> <label name="trigger_title" style="float:left;margin-top:5px;margin-bottom:5px">'+value+':</label> <input class="trigger_value" type="text" style="width: 85px;float:right;margin:5px"/></div>');
			});
		});
		<%--显示每天是否重复 ：本功能不要每天重复 --%>
		hiddenDaily();
		
	});
	<%--极限追求--%>
	
	$('#ExtremePursuitForGift').click(function(){
		
		$('#activity_type').val("14");
		$('#title').text("极限追求");
		$('#trigger div').remove();
		$('#trigger br').remove();
		$('#type-container input').remove();
		$('#type-container').append('	<input type="hidden" id="activity_type" name="activityType" value="14"/>');
		var url="activity.do?method=getTriggersByType&activity_type="+$('#activity_type').val();
		$.get(geturl(url), function(response){
			 var result = $.parseJSON(response);
			 console.log(result);
			 $.each(result, function(key, value)
			{
				 if(key == "17"){
			        var content = '<div  style="padding:5px;"> <input class="trigger_id" type="hidden" value='+key+'> <label name="trigger_title" style="float:left;margin-top:5px;margin-bottom:5px">'+value+':</label> <select  onchange="outputSelect(this.value);" class="trigger_value" type="text" style="width: 100px;float:right;margin:5px">';	
			        for(var i=0; i<slotType.length; i++){
			        	content += '<option value='+slotType[i].type+'>'+slotType[i].langDesc+'</option>';
			        }
			        
			        $('#trigger').append(content+'</select></div></br>');
				}else if(key == "18"){
			        var content1 = '<div  style="padding:5px;"> <input class="trigger_id" type="hidden" value='+key+'> <label name="trigger_title" style="float:left;margin-top:10px;margin-bottom:10px">'+value+':</label> <select id="slotTypeIcon" class="trigger_value" type="text" style="width: 100px;float:right;margin:5px">';	
			        for(var i=0; i<slotTypeIcon.length; i++){
				        content1 += '<option value='+slotTypeIcon[i].turn+'>'+slotTypeIcon[i].langDesc+'</option>';
			        }
			        $('#trigger').append(content1+'</select></div>');
				}else{
					$('#trigger').append('<div style="padding:5px;"> <input class="trigger_id" type="hidden" value='+key+'> <label name="trigger_title" style="float:left;margin-top:5px;margin-bottom:5px">'+value+':</label> <input class="trigger_value" type="text" style="width: 85px;float:right;margin:5px"/></div>');
					
				}
			
			});
		});
		<%--显示每天是否重复 ：本功能不要每天重复 --%>
		showDaily();
		/***
		第一次加载 极限运动 的 图标菜单
		*/
		/* outputSelect(1); */
	});
	
	<%--累计充值  美元--%>
	$('#grandForDollarGift').click(function(){
		
		$('#activity_type').val("15");
		$('#title').text("累计充值");
		$('#trigger div').remove();
		$('#trigger br').remove();
		$('#type-container input').remove();
		$('#type-container').append('	<input type="hidden" id="activity_type" name="activityType" value="15"/>');
		var url="activity.do?method=getTriggersByType&activity_type="+$('#activity_type').val();
		$.get(geturl(url), function(response){
			 var result = $.parseJSON(response);
			 console.log(result);
			 $.each(result, function(key, value)
			{
				 $('#trigger').append('<div id="trigger_each" style="padding:5px;"> <input class="trigger_id" type="hidden" value='+key+'> <label name="trigger_title" style="float:left;margin-top:5px;margin-bottom:5px">'+value+':</label> <input class="trigger_value" type="text" style="width: 85px;float:right;margin:5px"/></div>');
			
			});
		});
		<%--显示每天是否重复 ：本功能不要每天重复 --%>
		showDaily();
	});
	<%--累计充值  次数--%>
	$('#grandForGift').click(function(){
		
		$('#activity_type').val("18");
		$('#title').text("累计充值");
		$('#trigger div').remove();
		$('#trigger br').remove();
		$('#type-container input').remove();
		$('#type-container').append('	<input type="hidden" id="activity_type" name="activityType" value="18"/>');
		var url="activity.do?method=getTriggersByType&activity_type="+$('#activity_type').val();
		$.get(geturl(url), function(response){
			 var result = $.parseJSON(response);
			 console.log(result);
			 $.each(result, function(key, value)
			{
				 $('#trigger').append('<div id="trigger_each" style="padding:5px;"> <input class="trigger_id" type="hidden" value='+key+'> <label name="trigger_title" style="float:left;margin-top:5px;margin-bottom:5px">'+value+':</label> <input class="trigger_value" type="text" style="width: 85px;float:right;margin:5px"/></div>');
			
			});
		});
		<%--显示每天是否重复 ：本功能不要每天重复 --%>
		showDaily();
	});
	<%--连续累计充值--%>
	$('#grandDayForGift').click(function(){
		
		$('#activity_type').val("16");
		$('#title').text("连续累计充值");
		$('#trigger div').remove();
		$('#trigger br').remove();
		$('#type-container input').remove();
		$('#type-container').append('	<input type="hidden" id="activity_type" name="activityType" value="16"/>');
		var url="activity.do?method=getTriggersByType&activity_type="+$('#activity_type').val();
		$.get(geturl(url), function(response){
			 var result = $.parseJSON(response);
			 console.log(result);
			 $.each(result, function(key, value)
			{
				 $('#trigger').append('<div id="trigger_each" style="padding:5px;"> <input class="trigger_id" type="hidden" value='+key+'> <label name="trigger_title" style="float:left;margin-top:5px;margin-bottom:5px">'+value+':</label> <input class="trigger_value" type="text" style="width: 85px;float:right;margin:5px"/></div>');
			
			});
		});
		<%--显示每天是否重复 ：本功能不要每天重复 --%>
		showDaily();
	});
	
	<%--全服累计充值--%>
	$('#allPeopleGrandForGift').click(function(){
		
		$('#activity_type').val("17");
		$('#title').text("全服累计充值");
		$('#trigger div').remove();
		$('#trigger br').remove();
		$('#type-container input').remove();
		$('#type-container').append('	<input type="hidden" id="activity_type" name="activityType" value="17"/>');
		var url="activity.do?method=getTriggersByType&activity_type="+$('#activity_type').val();
		$.get(geturl(url), function(response){
			 var result = $.parseJSON(response);
			 console.log(result);
			 $.each(result, function(key, value)
			{
				 $('#trigger').append('<div id="trigger_each" style="padding:5px;"> <input class="trigger_id" type="hidden" value='+key+'> <label name="trigger_title" style="float:left;margin-top:5px;margin-bottom:5px">'+value+':</label> <input class="trigger_value" type="text" style="width: 85px;float:right;margin:5px"/></div>');
			
			});
		});
		<%--显示每天是否重复 ：本功能不要每天重复 --%>
		showDaily();
		/* $('#jiangli_tpl').append($("#vip_dianshu_1"));
		$("#jiangli_group_div").append($("#vip_dianshu_1").html());  */
	});
	


	$('#condition_number').text("活动规则"+number+":");
	$('#addTrigger').val("添加活动规则" +number);

	//添加多个 条件和 数量
	$('#btn_add').click(function() {
		$('#jiangli_group_div').append($('#jiangli_tpl').html());
		add_event();
	});
	function add_event() {
		//删除
		$('#form .s_3').click(function() {
			//alert(3333);
			$(this).parent().remove();
		});
		
	}
	
//点击添加条件档次时 遍历所有表单 组成一个提交的字符串
$('#addTrigger').click(function(data){

	if(!$('#activity_type').val())
		{
			alert('请选择活动类型');
			return false;
		}
	
	//将所有值拼接成串
	var val_result = "";
	
	//显示已添加的规则内容
	var overview = "";
	
	$('#trigger .trigger_id').each(function(){
		val_result += $(this).val() +","; 
		
		overview += $(this).siblings("label").text();
		
		if($(this).siblings("select").find('option:selected').text())
		{
			overview += $(this).siblings("select").find('option:selected').text()+"     ";
		}
		if($(this).siblings("input").val())
		{
			overview += $(this).siblings("input").val()+"     ";
		}
		if($(this).siblings("input").val())
		{
			overview += $(this).siblings("input").val()+"     ";
		}
	});

	if(val_result)
	{
		val_result += ";" ;
	}
	overview += " | ";
	
	$('#trigger .trigger_value').each(function(){
		if(!$(this).val())
		{
			val_result = "";
			return false;
		}
		val_result += $(this).val() +","; 
	});

	if(!val_result)
	{
		alert("请检查规则数值是否填写");
		return false;
	}else
	{
		val_result += "|" ;
	}

	$('#jiangli_group_div .s_1').each(function() {
		
		val_result += $(this).val() + ",";
		
		overview += $(this).find('option:selected').attr("title")+":";
		overview += $(this).siblings('.s_2').val()+ "     ";
		overview += $(this).siblings('.s_4').val()+ "     ";
		
	});
	
	if(val_result)
	{
		val_result += ";" ;
	}
	overview += " | ";
	$('#jiangli_group_div .s_2').each(function() 
	{	
		if(!$(this).val())
		{
			alert("奖励数值不能为空，请检查");
			val_result = "";
			return false;
		}
		val_result += $(this).val() + ",";
	});
	$('#jiangli_group_div .s_4').each(function() 
	{	
		if(!$(this).val())
		{
			alert("VIP点数数值不能为空，请检查");
			val_result = "";
			return false;
		}
		val_result += $(this).val() + ",";
	});

	if($('#maxNum').val())
	{
		overview += "可领取次数： " + $('#maxNum').val();
		val_result += "|" + $('#maxNum').val();
	}
	
	if(val_result)
	{
		$('#added-rule').append("<div>规则<span class='rule_number'>"+number+"</span>:   "+overview+"&nbsp;&nbsp; <input type='button' value='删除' class='remove-added' number='"+number+"'></div> ");
		val_result += '+';
		$('#rule_pack').val($('#rule_pack').val()+val_result);
		$('#jiangli_group_div div').remove();
		$('#trigger .trigger_value').each(function(){
			$(this).val('');
		});
		$('#jiangli_group_div .s_2').val('');
		$('#jiangli_group_div .s_4').val('');
		$('#maxNum').val('');
		number++;
		$('#condition_number').text("规则"+number);
		$('#addTrigger').val("添加活动规则" +number);
		$('#trigger-number').val(number);

		$('.remove-added').unbind().click(function(){
			var new_rule_pack = "";
			
			var added_rule_number = $(this).attr('number');

			var added_rule_pack = $('#rule_pack').val();
			
			var added_rule_array = added_rule_pack.split("+");

			added_rule_array.splice(added_rule_number-1, 1);
			
			for(var i=0; i<added_rule_array.length;i++)
			{
				if(added_rule_array[i] != "")
				new_rule_pack += added_rule_array[i]+"+";
			}
			
			$('#rule_pack').val(new_rule_pack);
			$(this).parent().remove();
			number = number - 1 ;
			$('#condition_number').text("规则"+number);
			$('#addTrigger').val("添加活动规则" +number);
			$('#trigger-number').val(number);
			$('.remove-added').each(function(index)
				{
				console.log(index+1);
					$(this).attr('number', index+1);
					$(this).prev().text(index+1);
				});
		});

	}
	});





	$('#reset').click(function(){
		number = 1;
		$('#condition_number').text("规则"+number);
		$('#addTrigger').val("添加活动规则" +number);
		$('#added-rule').html("");
	});

	$('.selectImg').unbind().click(function(){
		var selectid=$(this).attr("input_name");
		$('#select_image').show();
		$('.image').unbind().click(function()
		{
			$('#'+selectid).siblings("img").remove();
			$('#'+selectid).val($(this).attr("value"));	
			$('#'+selectid).parent().append("<img src='"+$(this).attr("src")+"'style='height:80px'>");
			
			$('#select_image').hide();			
		});
		
	});
	$("#select_image_close").click(function(){
		$('#select_image').hide();		
	});
	//定义表单提交前与提交后的处理方法及超时相关
	var options = {
		success:showResponse,
		timeout:3000
	};

	function showRequest(formData, jqForm, options) 
	{
		validation_result=true;

		$('.validation').each(function(){

			validat($(this));
		});

		if(!validation_result)
		{

			return false;
		}
		return true;
	}
	
	$('#form').submit(function() { 
	// submit the form 
	$(this).ajaxSubmit(options); 
	$('.ami_Mask').show();
	// return false to prevent normal browser submit and page navigation 
	return false; 
	});

	function showResponse(responseText, statusText, xhr, $form)  
	{ 
		$('.ami_Mask').hide();
		var response=jQuery.parseJSON(responseText);
		if(response.errorCode == 0)
		{
			bootbox.alert("Activity Added",
			function() {
				nav('activity.do?method=queryActivity','activity list');
			});
		}
		else
		{
		bootbox.alert("Error!",
			function() {
				history.back();
				});
		}
	}

	$('.validation').blur(function(){

		validat($(this));

	});


	function validat(obj)
	{
		if(obj.attr('requiry')&&obj.val()=='')
		{
			obj.siblings().remove();
			obj.parent().parent().removeClass('error');
			obj.after("&nbsp;<i class='icon-remove-sign' ></i><span class='help-inline' >必填项！</span>");
			obj.parent().parent().addClass('error');

			validation_result=false;

		}else
		{
			obj.parent().parent().removeClass('error');
			obj.siblings().remove();
		}
	}
	
});
function outputSelect(value){
	var url1="activity.do?method=getIconByType&type="+value;
	$.get(geturl(url1), function(response){
		 var result = $.parseJSON(response);
		 console.log(result);
		$("#slotTypeIcon").find("option").remove();
		 $.each(result, function(key, value)
		{
			 
			 for(var i=0;i<value.length;i++){
				 $('#slotTypeIcon').append('<option value='+value[i].turn+'>'+value[i].langDesc+'</option>');
			 }
		});
	});
}


</script>



