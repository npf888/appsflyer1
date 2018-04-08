<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<body >
	<br/>

<form class="form-horizontal" action="condition.do?method=doAddCondition" method="post" id="form" enctype="multipart/form-data">
	 
	  <h2 style="margin-left:100px;padding-bottom:30px">添加触发条件或奖励</h2>
	  <div class="control-group">
		<label class="control-label" for="form-field-accout">ID:</label>
		<div class="controls">
		  <input type="text" name="condition_id" class="validation" requiry="true">
		</div>
	  </div>
	 
	  <div class="control-group">
		<label class="control-label" for="form-field-accout">Title:</label>
		<div class="controls">
		  <input type="text" name="title" value="${one.points}"  class="validation" requiry="true">
		</div>
	  </div>
	  
	  
	  <div class="control-group">
		<label class="control-label" >类型:</label>
		<div class="controls">
		
		<select id="type_select" name="type">
			<option value="T">触发条件</option>
			<option value="R">奖励</option>
		</select>
		</div>
	  </div>
	  	 	  
	 <div id="activity_type" class="control-group">
		<label class="control-label" for="form-field-accout">选择活动类型:</label>
		<div class="controls">
		   <select id="activity_type_id" name="activity_type_id" style="display:block">
			    <option value="">请选择活动类型</option>
				<option value="1">大玩家</option>
				<option value="2">幸运牌型</option>
				<option value="3">充值</option>
		   </select>
		</div>
	  </div>
	  
	 <div class="control-group">
		<label class="control-label" for="form-field-accout">Description:</label>
		<div class="controls">
		  <input type="text" name="description" class="validation">
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
<script>

	
	$(document).ready(function () {
		$('#type_select').change(function(){
			if('R' === $(this).val())
			{
				$('#activity_type_id').prop('selectedIndex',0);
				$('#activity_type').hide();		
			}
			else{
				$('#activity_type').show();	
			}
		});
	    //定义表单提交前与提交后的处理方法及超时相关
	    var options = {
	                beforeSubmit:showRequest,
	                success:showResponse,
	                timeout:3000
	            };
	    $('#form').submit(function() { 
	     // submit the form 
	     $(this).ajaxSubmit(options); 
	     // return false to prevent normal browser submit and page navigation 
	     return false; 
	    });
	});
	


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
function showResponse(responseText, statusText, xhr, $form)  
{ 
	bootbox.alert("添加成功",
	function() {
		nav('condition.do?method=queryCondition','Condition');
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



