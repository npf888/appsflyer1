<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<form class="form-horizontal" action="compensation.do?method=doEdit&id=${one.id}" method="post" id="form" enctype="multipart/form-data">

	<h2 id="title" style="margin-left:50px;padding-bottom:30px">全服补偿</h2>

	
	<div class="control-group">
		<label class="control-label" for="form-field-accout">标题:</label>
		<div class="controls">
			<input type="text" name="title" value="${one.title}"  class="validation" requiry="true">
		</div>
	</div>
	
	<div class="control-group">
		<label class="control-label" for="form-field-accout">内容:</label>
		<div class="controls">
			<input type="text" name="content" value="${one.content}"  class="validation" requiry="true">
		</div>
	</div>

	
	<input type="hidden" name="itemlist" value="${one.itempack}"  id="rewardlist">

	<div  class="control-group">
		<label class="control-label" for="form-field-accout">奖励:</label>
	    <div class="controls">
		<div id="jiangli_group_div" style="background: #C2D6D6; margin-left:20px; width:350px; padding:5px">	
			<div class="s_reward" >
			<input type="button" value="+" id="btn_add" style="float:right; bottom:0;"/> 
			奖励：
			<select class="s_1" style="width:150px;" >
			<c:forEach items="${rewards}" var="reward">
				<option value="${reward.getKey()}" title="${reward.getValue()}" >${reward.getValue()}</option>
			</c:forEach>
		</select>
		数量：
		<input class="s_2" style="width:70px;" type="text">
		<br>
		</div>	  
		</div>
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
	<div class="s_reward" >
	奖励：
	<select class="s_1" style="width: 100px;" name="rewardid">
	<c:forEach items="${rewards}" var="reward">
		<option value="${reward.getKey() }" title="${reward.getValue()}">${reward.getValue()}</option>
	</c:forEach>
	</select> 
	数量：
	<input class="s_2" style="width: 70px;" name="rewardcount" type="text"> 
	<input type="button" value="删除" class="s_3" /></div>
</div>

<script>
$(document).ready(function () {

	//定义表单提交前与提交后的处理方法及超时相关
	var options = {
		beforeSubmit:showRequest,
		success:showResponse,
		timeout:3000
	};
	$('#form').submit(function() { 

		var rewardlist = "";
		$('#jiangli_group_div .s_reward').each(function(){
			
			rewardlist += $(this).find(".s_1").val() +":"+$(this).find(".s_2").val() + ";";

		});
		$('#rewardlist').val(rewardlist);

	// submit the form 
	$(this).ajaxSubmit(options); 
	$('.ami_Mask').show();
	// return false to prevent normal browser submit and page navigation 
	return false; 
	});
	
	//添加多个 条件和 数量
	$('#btn_add').click(function() {
		$('#jiangli_group_div').append($('#jiangli_tpl').html());
		add_event();
	});

});

	function add_event() {
		//删除
		$('#form .s_3').click(function() {
			//alert(3333);
			$(this).parent().remove();
		});
		
	}

	$('#reset').click(function(){
		number = 1;
		$('#condition_number').text("规则"+number);
		$('#addTrigger').val("添加活动规则" +number);
		$('#added-rule').html("");
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
		$('.ami_Mask').hide();
		if(responseText == 0)
		{
		bootbox.alert("Compensation Added",
			function() {
				nav('compensation.do?method=query','Compensation');
			});
		}
		else
			{
			bootbox.alert("Something Wrong: "+ responseText);
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
</script>



