<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<style>
.error{
	color:red;
}
</style>
<div class="row-fluid">	
		<form class="form-inline" action="${pageContext.request.contextPath}/agent.do?method=addPage" id="searchForm">
			<label>用户ID|昵称:</label>
			<input  type="text" class="input-medium"  name="searchColumn" value="${searchColumn }"  id="searchColumn" ></input>				
			<button type="button"  style="margin-left:10px" class="btn btn-mini btn-info" onClick="go();">检索</button>
		</form>
		<label>
		<c:if test="${!doubles}"><font color="red">此用户已成为代理</font></c:if>
		</label>
</div>	
<hr/>


<form class="form-horizontal" action="#" method="post" id="form">
	 
	  <div class="control-group" style="display:none;">
		<label class="control-label" for="form-field-accout">id</label>
		<div class="controls" >
		  <input type="text" name="id" value="${agent.id}" readonly="readonly" requiry="true">
		</div>
	  </div>

	<div class="control-group">
		<label class="control-label">用户ID</label>
		<div class="controls">
			<input type="text" name="passportId" value="${agent.passportId}" readonly="readonly" requiry="true">
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">昵称</label>
		<div class="controls">
			<input type="text" name="nickname" value="${agent.nickname}" readonly="readonly" requiry="true">
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">用户名</label>
		<div class="controls">
			<input type="text" name="username" value="${agent.username}" readonly="readonly" requiry="true">
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">密码</label>
		<div class="controls">
			<input type="text" name="passwords" value="${agent.passwords}" readonly="readonly" requiry="true">
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">电话</label>
		<div class="controls">
			<input type="text" name="telephone" value="${agent.telephone}" requiry="true">
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">身份证</label>
		<div class="controls">
			<input type="text" name="identity" value="${agent.identity}" requiry="true">
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">地址</label>
		<div class="controls">
			<input type="text" name="address" value="${agent.address}" requiry="true">
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">微信号码</label>
		<div class="controls">
			<input type="text" name="wx" value="${agent.wx}">
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">qq号码</label>
		<div class="controls">
			<input type="text" name="qq" value="${agent.qq}">
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">父ID</label>
		<div class="controls">
			<input type="text" name="parentId" value="${agent.parentId}" readonly="readonly">
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">状态</label>
		<div class="controls">
			<input type="text" name="state" value="${agent.state}" readonly="readonly">
		</div>
	</div>
	
 <c:if test="${doubles}">
		<div class="form-actions">
			<button class="btn btn-small btn-info" type="submit" >保存</button>	
			&nbsp; &nbsp; &nbsp;
			<button class="btn btn-small" type="reset" onclick="goback()"><i class="icon-undo"></i>取消</button>
	  </div>
 </c:if>

 </form>
 
<script>
$.validator.setDefaults({
    submitHandler: function() {
      save();
    }
});
$(function(){
	$("#form").validate({
		rules: {
			passportId: {
				required: true,
				minlength:1,
				maxlength:20 
			},
			nickname: {
				required: true,
				minlength:1,
				maxlength:20 
			},
			username: {
				required: true,
				minlength:1,
				maxlength:20 
			},
			passwords: {
				required: true,
				minlength:5,
				maxlength:9 
			},
			telephone: {
				required: true,
				minlength:11,
				maxlength:12 
			}
		},
		messages: {
			passportId: {
				required: "Please provide a passportId",
				minlength: "Your password must be at least 1 characters long",
				maxlength: "Your password must be less 20 characters long"
			},
			nickname: {
				required: "Please provide a nickname",
				minlength: "Your password must be at least 1 characters long",
				maxlength: "Your password must be less 20 characters long"
			},
			username: {
				required: "Please provide a username",
				minlength: "Your password must be at least 1 characters long",
				maxlength: "Your password must be less 20 characters long"
			},
			passwords: {
				required: "Please provide a passwords",
				minlength: "Your password must be at least 5 characters long",
				maxlength: "Your password must be less 9 characters long"
			},
			telephone: {
				required: "Please provide a telephone number",
				minlength: "Your password must be at least 11 characters long",
				maxlength: "Your password must be less 12 characters long"
			}
		}
	});
	
});
	
function save()
{
	$.post('${pageContext.request.contextPath}/agent.do?method=saveSunAgent&'+$("#form").serialize(), function(data) {
		
	});
	bootbox.alert("保存成功",
			function() {
				nav('${pageContext.request.contextPath}/agent.do?method=list','子代理列表');
				});
	
}



function go()
{
	$('.ami_Mask').show();
	var params = $('#searchForm').formSerialize();
	
	var url = $('#searchForm').attr('action') ;
	$.post(url,params,function (data){
	
	$('#PAGECONTENT').html(data);
	
	$('.ami_Mask').hide();
	
	});
}
/**
 * 回退
 */
function goback() {
	$('.ami_Mask').show();
	//主窗口隐错
	$('#ami_main').show();
	$('#ami_newwindow').html('');
	$('#ami_newwindow').hide();
	//主窗口隐错
	$('.ami_Mask').hide();
}

</script>



