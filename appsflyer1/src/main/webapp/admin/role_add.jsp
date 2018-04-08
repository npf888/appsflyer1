<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<script type="text/javascript" src="../../js/jquery.form.js"></script>
<form class="form-horizontal" action="right.do?method=doAdd" method="post" id="form">
	<input type="hidden" name="groupright" id="groupright" value="${role.groupright }"/>
	<input type="hidden" name="id" id="id" value="${role.id }"/>
	
	  <div class="control-group">
		<label class="control-label" for="form-field-roleName">角色名称</label>
		<div class="controls">
		  <input type="text" id="form-field-roleName" name="rolename" value="${role.rolename }" placeholder="role name.." onmouseout="checkRoleName()" >
		  <span class="help-inline" id="errorRoleName" style="display:none" >不能超过6位字符</span>
		</div>
	  </div>
	  <div class="control-group">
		<label class="control-label" for="form-field-roleDesc">角色描述</label>		
		<div class="controls">
		  <input type="text" id="form-field-roleDesc" name="des" value="${role.des }"  placeholder="role description.." onmouseout="checkRoleDesc()" >
		  <span class="help-inline" id="errorRoleDesc" style="display:none">不得少于6个字符</span>
		</div>
	  </div>
	  <div class="control-group">
		<label class="control-label" for="form-field-authority">权限</label>
		<div class="controls">
		   	<div>
        		<div id="dtree" class="tree tree-selectable"></div>
		</div>
	  </div>
	  <div class="form-actions">
			<button class="btn btn-small btn-info" type="submit"><i class="icon-ok"></i>提交</button>	
			&nbsp; &nbsp; &nbsp;
			<button class="btn btn-small" type="reset"><i class="icon-undo"></i> Reset</button>
			&nbsp; &nbsp; &nbsp;
			<button class="btn btn-small" type="reset" onclick="goback()"><i class="icon-undo"></i>取消</button>
	  </div>
 </form>
  
  
	
	<script>
	
	
$(function(){

		var temp_id=999;
		var temp_parentid=0;
		a = new dTree('a', 'dtree');
		a.config.check=true;
		a.add("0", "-1", '权限树');
		<c:forEach var="nav" items="${rightList}">
		 a.add("${nav.sid}", "${nav.pid}", "${nav.title}",'','','navDital');
		 </c:forEach>
		a.draw();
		a.toggleNode = function(id) {};
			a.selectNode = function(node) {
			temp_parentid=node.id ;		
		};
		var groupright = $('#groupright').val();
		if(groupright!='')
		{
			groupright=","+groupright+",";
			
			 $('input[name="check"]').each(function(){    
			   var val = $(this).val();
			   
			   if(groupright.indexOf(','+val+',')!=-1)
			   {
			    $(this).attr("checked", true);
			   }
			  
			  });
		}			    		        


		
});

$('.ami_checkbox').click(function(){

		var chk_value =[];    
		  $('input[name="check"]:checked').each(function(){ 
		
		   chk_value.push($(this).val());    
		   
		  });   
		  
		  
		  $('#groupright').val(chk_value.toString());
		  
});

		
		
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
	var groupright = $('#groupright').val();
	if(groupright.length==0 )
	{
		 bootbox.alert("至少选择一个权限");
		 return false;
	}  
	$('.ami_Mask').show();
	return true;
}

function showResponse(responseText, statusText, xhr, $form)  
{ 
	$('.ami_Mask').hide();

	bootbox.alert("角色息添加成功",
	function() {
		
		nav('right.do?method=query','角色管理');
		
		});
}
function checkRoleName()
{
	var roleName = $('#form-field-roleName').val(); 
	 if(roleName.length==0)
	 {
    	 $('#errorRoleName').show();
    	 $('#errorRoleName').html('不能为空');
    	 $('#form-field-roleName').parent().parent().addClass('error');
	 }else if(roleName.length>6){
    	 $('#errorRoleName').show();
    	 $('#errorRoleName').html('不能超过6位字符');
    	 $('#form-field-roleName').parent().parent().addClass('error');
	 }else{
	     $('#errorRoleName').hide();
	     $('#form-field-roleName').parent().parent().removeClass('error');
	 }
	}
function checkRoleDesc()
{
	var roleDesc = $('#form-field-roleDesc').val(); 
	 if(roleDesc.length==0)
	 {
    	 $('#errorRoleDesc').show();
    	 $('#errorRoleDesc').html('不能为空');
    	 $('#form-field-roleDesc').parent().parent().addClass('error');
	 }else if(roleDesc.length<6&&roleDesc.length>0){
    	 $('#errorRoleDesc').show();
    	 $('#errorRoleDesc').html('不能少于6位字符');
    	 $('#form-field-roleDesc').parent().parent().addClass('error');
	 }else{
	     $('#errorRoleDesc').hide();
	     $('#form-field-roleDesc').parent().parent().removeClass('error');
	 }
	}
	</script>