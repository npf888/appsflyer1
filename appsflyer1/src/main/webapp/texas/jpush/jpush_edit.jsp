<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<form class="form-horizontal" action="news.do?method=saveConfig" method="post" id="form">
	 
	  
	  <div class="control-group">
		<label class="control-label" for="form-field-accout">id</label>
		<div class="controls">
		  <input type="text" name="id" value="${config.id}" readonly="readonly" requiry="true">
		</div>
	  </div>

	<div class="control-group">
		<label class="control-label">推送类型</label>
		<div class="controls">
			<select id="jpushType" name="jpushType" >
				<option value="0">所有人</option>
				<option value="1">在线奖励可领取</option>
				<option value="2">玩家未签到</option>
				<option value="3">收到新的好友申请</option>
				<option value="4">登录天数(暂时无效)</option>
				<option value="5">未登录天数</option>
				<option value="6">充值次数</option>
				<option value="7">点开计费点未充值的次数</option>
				<option value="9">金币存储量百分比达到</option>
				<option value="10">所在工会有人购买了俱乐部礼包</option>
				<option value="11">有可领奖邮件</option>
				<option value="12">等级</option>
				<option value="13">版本号(暂时无效)</option>
				<option value="14">24小时更新过版本(暂时无效)</option>
			</select>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">描述</label>
		<div class="controls">
			<input type="text" name="description" value="${config.description}" requiry="true">
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">标题</label>
		<div class="controls">
			<input type="text" name="title" value="${config.title}" requiry="true">
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">内容</label>
		<div class="controls">
			<input type="text" name="content" value="${config.content}" requiry="true">
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">优先级</label>
		<div class="controls">
			<input type="text" name="priority" value="${config.priority}" requiry="true">
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">额外显示类型</label>
		<div class="controls">
			<input type="text" name="notificationType" value="${config.notificationType}" requiry="true">
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">发送时间</label>
		<div class="controls">
			<input type="text" name="sendTime" value="${config.sendTime}" requiry="true">
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">过滤条件</label>
		<div class="controls">
			<input type="text" name="filterParams" value="${config.filterParams}">
		</div>
	</div>


	  <div class="form-actions">
			<button class="btn btn-small btn-info" id="submit" onclick="save()">保存</button>	
			&nbsp; &nbsp; &nbsp;
			<button class="btn btn-small" type="reset" onclick="goback()"><i class="icon-undo"></i>取消</button>
	  </div>
 </form>
<script>
$('#jpushType').val("${config.jpushType}"); 
// 	$(document).ready(function () {
// 	    //定义表单提交前与提交后的处理方法及超时相关
// 	    var options = {
// // 	                beforeSubmit:showRequest,
// 	                success:showResponse,
// 	                timeout:3000
// 	            };
// 	    $('#form').submit(function() { 
// 	     // submit the form 
// 	     $(this).ajaxSubmit(options); 
// 	     // return false to prevent normal browser submit and page navigation 
// 	     alert();
// 	     return false; 
// 	    });
	    
	    
// 	    $('#jpushType').val("${config.jpushType}"); 
	   
// 	});
	
function save()
{
// 	alert($("#form").serialize());
	$.post('jpush.do?method=saveConfig&'+$("#form").serialize(), function(data) {
	});
	goback();
	bootbox.alert("保存成功",
			function() {
				nav('jpush.do?method=queryConfig','推送');
				});
}





</script>



