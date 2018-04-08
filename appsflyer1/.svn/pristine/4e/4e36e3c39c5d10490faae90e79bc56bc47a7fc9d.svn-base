<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<form class="form-horizontal" action="admin.do?method=${method }&id=${member.id }" method="post" id="form">
	 
	  
	  <div class="control-group">
		<label class="control-label" for="form-field-accout">账号</label>
		<div class="controls">
		  <input type="text" name="account" value="${member.account}"  class="validation" requiry="true">
		</div>
	  </div>
	 
	  <div class="control-group">
		<label class="control-label" >密码</label>		
		<div class="controls">
		  <input type="password" name="pwd"  <c:if test="${method =='doAdd'}"> class="validation" </c:if>   <c:if test="${method =='doEdit'}"> placeholder="如果不修密码，此项保持为空"</c:if> requiry="true" >
		</div>
	  </div>
	  <div class="control-group">
		<label class="control-label" >重复密码</label>
		<div class="controls">
		  <input type="password" <c:if test="${method =='doAdd'}"> class="validation" </c:if>  requiry="true" >
		</div>
	  </div>
	  
	  <div class="control-group">
		<label class="control-label" >姓名</label>
		<div class="controls">
		  <input type="text" name="username"  value="${member.username}"  class="validation"  requiry="true"  >
		</div>
	  </div>
	 
	
	  </div>
	  <div class="control-group">
		<label class="control-label" >联系电话</label>
		<div class="controls">
		  <input type="text" name="phone"  value="${member.phone}"   class="validation" requiry="true"  >
         </div>
	  </div>
	 
	  
	  <div class="control-group">
		<label class="control-label" >E-mail</label>
		<div class="controls">
		  <input type="text" name="email"  value="${member.email}"   >		 
		</div>
	  </div>
	  <div class="control-group">
		<label class="control-label">角色</label>
		<div class="controls  ">			
				<select id="list1" multiple="multiple"  style="width:120px; height:150px" ondblclick="moveOption('list1', 'list2')" >
				<c:forEach var="one" items="${roles}">
                     	<option value="${one.value.id }">${one.value.rolename }</option>
                     	</c:forEach>
				</select>			
				<div class="btn-group btn-group-vertical">
					<input type="button" value="添加" class="btn btn-minier" onClick="moveOption('list1', 'list2')" />
					<input type="button" value="全选" class="btn btn-minier" onClick="moveAllOption('list1','list2')" />
					<input type="button" value="删除" class="btn btn-minier" onClick="moveOption('list2', 'list1')" />
					<input type="button" value="全删" class="btn btn-minier" onClick="moveAllOption('list2', 'list1')" />
				</div>			
				<select multiple="multiple"  style="width:100px;height:150px;" id="list2"  ondblclick="moveOption('list2', 'list1')">>
					<c:forEach var="one" items="${roles_old}">
                     		<option value="${one.value.id }">${one.value.rolename }</option>
                     	</c:forEach>
				</select>		
				<input type="hidden"  name="grouplist" id="role_hid" size="40" value="${member.grouplist }" />	
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
	bootbox.alert("用户信息编辑成功",
	function() {
		nav('admin.do?method=query','用户管理');
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


function moveOption(e1, e2)
{

	e1=$('#'+e1)[0];

	e2=$('#'+e2)[0];

   try
   {
      for(var i = 0; i < e1.options.length; i ++ )
      {
         if(e1.options[i].selected)
         {
            var e = e1.options[i];
            e2.options.add(new Option(e.text, e.value));
            e1.remove(i);
            i = i - 1;
         }
      }
 	// alert(getvalue($('#list2')[0]));
      $('#role_hid').val(getvalue($('#list2')[0]))
   }
   catch(e)
   {
   }
}
function getvalue(geto)
{
   var allvalue = "";
   for(var i = 0; i < geto.options.length; i ++ )
   {
      allvalue += geto.options[i].value + "|";
   }
   
   if(allvalue.length>1)
   {
   		 allvalue = allvalue.substr(0,allvalue.length-1);
   }
   
   return allvalue;
}
function moveAllOption(e1, e2)
{
	e1=$('#'+e1)[0];

	e2=$('#'+e2)[0];
	
   try
   {
      for(var i = 0; i < e1.options.length; i ++ )
      {
         var e = e1.options[i];
         e2.options.add(new Option(e.text, e.value));
         e1.remove(i);
         i = i - 1;
      }
      $('#role_hid').val(getvalue($('#list2')[0]))
   }
   catch(e)
   {

   }
}


</script>



