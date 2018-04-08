<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="stylesheet" type="text/css" href="js/Clockpicker/bootstrap-clockpicker.min.css">

<form class="form-horizontal" action="news.do?method=postNewsJsonData" method="post" id="form" enctype="multipart/form-data" accept-charset="UTF-8">

	<h2 style="margin-left:100px;padding-bottom:30px">添加跑马灯</h2>
    <input id="rule_pack" type="hidden" style="width:100%" name="rulePack" /> 
	<div class="control-group">
		<label class="control-label" for="form-field-accout">跑马灯内容:</label>
		<div class="controls">
			<textarea rows="2" class="validation" cols="10" style="width: 50%" name="content" requiry="true">${one.description}</textarea>
		</div>
	</div>
	<div class="control-group">
	<label class="control-label"></label>
	<div class="controls">
		<h5 style="color:orange">注：所填时间都是中国时间</h5>
	</div>
	</div>
	<div class="control-group">
		<label class="control-label" for="form-field-accout">开始日期:</label>
		<div class="controls">
			<input type="text"  id="d4311" name="startTime" value="${one.start_time}" class="Wdate" onClick="WdatePicker({maxDate:'#F{$dp.$D(\'d4312\')}',dateFmt:'yyyy-MM-dd'})" style="width:80px" requiry="true"/>
			截止日期：<input type="text"  id="d4312" name="endTime" value="${one.end_time}" class="Wdate"  onClick="WdatePicker({minDate:'#F{$dp.$D(\'d4311\')}',realDateFmt:'yyyy-MM-dd',dateFmt:'yyyy-MM-dd'})" style="width:80px" requiry="true"/> 
		注：包括截止日期那一天
		</div>
	</div>

	<div class="control-group">
		<label class="control-label" for="form-field-accout">每天开始时间:</label>
		<div class="controls">
			<input type="text"  id="start_time" name="dailyStartTime" value="${one.start_time}" class="form-control" style="width:80px" requiry="true"/>
			每天结束时间：<input type="text"  id="end_time" name="dailyEndTime" value="${one.end_time}" class="form-control" style="width:80px" requiry="true"/> 
		</div>
	</div>

	<div class="control-group">
		<label class="control-label" for="form-field-accout">间隔时间（分钟）:</label>
		<div class="controls">
			<input type="text" name="intervalTime" value="${one.title}"  style="width:40px" class="validation" requiry="true">
		</div>
	</div>

	<div class="form-actions">
		<button class="btn btn-small btn-info" type="submit" id="submit"><i class="icon-ok"></i>提交</button>	
		&nbsp; &nbsp; &nbsp;
		<button class="btn btn-small" type="reset"><i class="icon-undo"></i> Reset</button>
		<c:if test="${iscancel=='1'}">
			&nbsp; &nbsp; &nbsp;
			<button class="btn btn-small" type="reset" onclick="goback()"><i class="icon-undo"></i>取消</button>
		</c:if>
	</div>

</form>

<script type="text/javascript" src="js/Clockpicker/bootstrap-clockpicker.min.js"></script>

<script>
$(document).ready(function () {
	//定义表单提交前与提交后的处理方法及超时相关
	var options = {
		
		success:showResponse,
		timeout:3000
	};
	$('#form').submit(function() { 
	// submit the form 
	$(this).ajaxSubmit(options); 
	$('.ami_Mask').show();
	// return false to prevent normal browser submit and page navigation 
	return false; 
	});
	$('#start_time').clockpicker({
	    placement: 'bottom',
	    align: 'left',
	    autoclose: true
	});
	$('#end_time').clockpicker({
	    placement: 'bottom',
	    align: 'left',
	    autoclose: true
	});
});


function showResponse(responseText, statusText, xhr, $form)  
	{
	$('.ami_Mask').hide();
		bootbox.alert("跑马灯添加成功",
			function() {
			
				nav('news.do?method=queryNews','跑马灯列表');
			});
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
</script>



