<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<hr/>
<form class="form-horizontal" action="admin.do?method=modpwd" method="post" id="form">
	  
	  <div class="alert alert-error" id="tip" style="display: none;">
										<button type="button" class="close" data-dismiss="alert">
											<i class="icon-remove"></i>
										</button>

										<strong>
											<i class="icon-remove"></i>
											操作失败!
										</strong>
										原始密码不正确。
										<br>
									</div>
									
	  <div class="control-group">
		<label class="control-label" for="form-field-password">原始密码</label>		
		<div class="controls">
		<input class="tooltip-error " type="password"  name="oldpwd"  id="oldpwd" data-rel="tooltip" onblur="" title="原始密码不正确" data-trigger="focus" id="oldpwd" onmouseout="checkOldPwd()">
		 <i style="color:#FFFFFF" class=" icon-star" id="errorOldPwdStar"></i>
		  <span class="help-inline" id="errorOldPwd" style="display:none"></span>
		</div>
	  </div>
	  
	  <div class="control-group">
		<label class="control-label" for="form-field-password">密码</label>		
		<div class="controls">
		  <input type="password" name="pwd" id="form-field-password" onmouseout="checkPassword()">
		   <i style="color:#FFFFFF" class=" icon-star" id="errorPasswordStar"></i>
		   <span class="help-inline" id="errorPassword" style="display:none">不得少于6位字符</span>
		</div>
	  </div>
	  <div class="control-group">
		<label class="control-label" for="form-field-renewpassword">重复密码</label>
		<div class="controls">
		  <input type="password" id="form-field-renewpassword" onmouseout="checkReNewPassword()" >
		  <i style="color:#FFFFFF" class=" icon-star" id="errorRenewPasswordStar"></i>
		  <span class="help-inline" id="errorRenewPassword" style="display:none">你输入的和密码不一样,请重新输入</span>
		</div>
	  </div>
	 
	  <div class="form-actions">
			<button class="btn btn-small btn-info" type="submit"><i class="icon-ok"></i>提交</button>	
			&nbsp; &nbsp; &nbsp;
			<button class="btn btn-small" type="reset"><i class="icon-undo"></i> Reset</button>
			
	  </div>
 </form>
<script>


$(document).ready(function () {
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
	return true;
}
function showResponse(responseText, statusText, xhr, $form)  
{ 
	if('fail'==responseText)
	{
		$('#tip').show();
		
	}else if('success'==responseText)
	{
		$('#tip').hide();
		bootbox.alert("密码修改成功",
				function() {
					nav('/admin/admin_modpwd.jsp?1=1','修改密码');
					});
	}
	
	
	
}
function checkOldPwd()
{
	var oldpwd = $('#oldpwd').val(); 
	 if(oldpwd.length==0)
	 {
	 $('#errorOldPwdStar').css("color","#FF0000"); 
   	 $('#errorOldPwd').show();
   	 $('#errorOldPwd').html('原始密码不能为空');
   	 $('#oldpwd').parent().parent().addClass('error');
	 }else{
		 $('#errorOldPwdStar').css("color","#FFFFFF"); 
	   	 $('#errorOldPwd').hide();
	   	 $('#oldpwd').parent().parent().removeClass('error');
	 }
	}
function checkPassword()
{
	var password = $('#form-field-password').val(); 
	 if(password.length==0)
	 {
		 $('#errorPasswordStar').css("color","#FF0000"); 
    	 $('#errorPassword').show();
    	 $('#errorPassword').html('密码不能为空');
    	 $('#form-field-password').parent().parent().addClass('error');
	 }else if(password.length>0&&password.length<6){
		 $('#errorPasswordStar').css("color","#FF0000"); 
    	 $('#errorPassword').show();
    	 $('#errorPassword').html('不得少于6位字符');
    	 $('#form-field-password').parent().parent().addClass('error');
	 }else{
		 $('#errorPasswordStar').css("color","#FFFFFF"); 
	     $('#errorPassword').hide();
	     $('#form-field-password').parent().parent().removeClass('error');
	 }
	}
function checkReNewPassword()
{
	var password = $('#form-field-password').val(); 
	var renewpassword = $('#form-field-renewpassword').val(); 
	if(password.length==0)
	 {
		 $('#errorRenewPasswordStar').css("color","#FF0000"); 
	   	 $('#errorRenewPassword').show();
	   	 $('#errorRenewPassword').html('重复密码不能为空');
	   	 $('#form-field-renewpassword').parent().parent().addClass('error');
	 }else if(password!=renewpassword)
	 {
		 $('#errorRenewPasswordStar').css("color","#FF0000"); 
	   	 $('#errorRenewPassword').show();
	   	 $('#errorRenewPassword').html('你输入的和密码不一样,请重新输入');
	   	 $('#form-field-renewpassword').parent().parent().addClass('error');
	 }else{
		 $('#errorRenewPasswordStar').css("color","#FFFFFF"); 
	     $('#errorRenewPassword').hide();
	     $('#form-field-renewpassword').parent().parent().removeClass('error');
	 }
	}





</script>
