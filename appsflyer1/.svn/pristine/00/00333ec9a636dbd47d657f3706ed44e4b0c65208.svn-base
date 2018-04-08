<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/WEB-INF/struts-tags.tld" %>
<head>
	<link rel="stylesheet" href="/css/feedback.css">
</head>


<form class="form-horizontal"  action="feedback.do?method=doAdd" method="post" style="" id="form" enctype="multipart/form-data">
<div class="form-horizontal bailform">
	<div id="accordion1" class="accordion">
		<div class="accordion-group">
		<div style="width:45%;float:left;border: 1px solid silver">
			<div class="accordion-heading">
				<a href="#collapseOne" data-parent="#accordion1" data-toggle="collapse" class="accordion-toggle">
					Feedback Topic
				</a>
			</div>

			<div class="accordion-body in collapse" id="collapseOne" style="height: auto;">				
				<label class="control-label " for="form-field-accout">App ID: </label>															
				<div class="controls">
					<span> ${one.app_id}</span>														
				</div>											
			</div>
			
			<div class="accordion-body in collapse" id="collapseOne" style="height: auto;">				
				<label class="control-label " for="form-field-accout">User ID: </label>															
				<div class="controls">
					<span> ${one.user_id}</span>														
				</div>											
			</div>

			<div class="accordion-body in collapse" id="collapseOne" style="width:50%;height: auto;">				
				<label class="control-label " for="form-field-accout">Message: </label>															
				<div class="controls">
					<span> ${one.message}</span>														
				</div>											
			</div>
			
			<div class="accordion-body in collapse" id="collapseOne" style="height: auto;">				
				<label class="control-label " for="form-field-accout">Image: </label>															
				<div class="controls">
					<img style="width:300px;border:1px solid silver;border-radius:10px;" src="${one.image}"/>												
				</div>											
			</div>
		</div>
	<div style="width:45%;float:left;margin-left:10px;height:400px">
		<div class="accordion-heading">
			<a href="#collapseOne" data-parent="#accordion1" data-toggle="collapse" class="accordion-toggle">
				Follow Up
			</a>
		</div>
	<div id="container" class="container" style="width:100%">		
		<div style="clear:both;max-width:80%;float:right;text-align:right;margin-top:10px;margin-bottom:10px">
			<div>${one.create_time}</div>
				<div class="left">
					${one.message}
				</div><br>
		</div>	
		
		<c:if test="${not empty one.reply}">			
		   <c:forEach items="${one.reply}" var="response">
			<c:if test="${response.role=='USER'}">
			<div style="clear:both;max-width:80%;float:right;text-align:right;margin-bottom:10px">
			<div>${response.time_stamp}</div>
				<div class="left">
					${response.content}
				</div><br>
			</div>	
			</c:if>
			
			<c:if test="${response.role=='GM'}">			
			<div style="clear: both;max-width:80%;text-align:left;margin-bottom:10px">
			  <div>${response.time_stamp}</div>
				<div class="right">
					${response.content}
				</div><br>
			</div>
			</c:if>
		  </c:forEach>	

		</c:if>
		<div id="new-reply" style="clear:both;max-width:80%;float:right;text-align:right;display:none">
			<div class="left">
			
			</div><br>
		</div>	
	 
	 	<input type="hidden" name="feedback_id" value="${one.id}">		
	</div>	
	
	<div style="background-color:#C4EFC4;border-radius:10px">
		<input type="text" id="content" style="border-radius:10px;width:80%;margin-left:10px;margin-top:10px;margin-bottom:10px" name="content">
		&nbsp<input type="submit" class="myButton" id="submit" style="width:70px;margin-top:10px;margin-bottom:10px;" value="回复">
	</div>	
		
	<div>
		<a onclick="openNewWinow('feedback.do?method=query&currentPage=${currentPage }')">
			<input type="button" class="myButton"  style="width:70px;margin-top:10px;margin-bottom:10px;" value="返回">
		</a>
	</div>
	</div>	
		</div>		
	</div>
</div>

</form>
<script>

$(document).ready(function () {
    var d = $('#container');
    d.scrollTop(d.prop("scrollHeight"));
    //定义表单提交前与提交后的处理方法及超时相关
    var options = {
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

function showResponse(responseText, statusText, xhr, $form)  
{ 
	bootbox.alert(responseText,
	function() {
		nav('feedback.do?method=query','App list');
	});
}

</script>

