<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<form class="form-horizontal" action="addgold.do?method=addgoldOverrideOrIncrese" method="post" id="form">
	  
	  <div class="control-group">
		<label class="control-label" for="form-field-accout">用户ID</label>
		<div class="controls">
		  <input type="text" name="passportId"  class="validation" requiry="true">
		</div>
	  </div>
	  <div class="control-group">
		<label class="control-label" for="form-field-accout">金币</label>
		<div class="controls">
		  <input type="text" name="gold"    class="validation" requiry="true">
		</div>
	  </div>
	 
	   <div class="control-group">
			<label class="control-label" >等级</label>		
			<div class="controls">
			  <input type="text" name="level"  class="validation" requiry="true">
	  		</div>
	  </div>
	  <div class="control-group">
		<label class="control-label" >类型</label>		
		<div class="controls">
		  <input type="radio" name="type" value="0" checked >在原先的金币基础之上增加
		  <input type="radio" name="type" value="1"  >覆盖原先的金币
	  	</div>
	  </div>
	 
	  
	  <div class="form-actions">
			<button class="btn btn-small btn-info" type="button" id="submit"><i class="icon-ok"></i>提交</button>	
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
		function goBack(url)
		{
			
			$.post(url,function (data){
				$('#PAGECONTENT').html(data);	
			});
		}
	    //定义表单提交前与提交后的处理方法及超时相关
	    var options = {
	                beforeSubmit:showRequest,
	                success:showResponse,
	                timeout:3000
	            };
	    $("#submit").click(function(){
	    	var url = "addgold.do?method=addgoldOverrideOrIncrese";
	    	var passportId = $("input[name='passportId']").val();
	    	var gold = $("input[name='gold']").val();
	    	var level = $("input[name='level']").val();
	    	var type = $("input[name='type']").val();
    		 $.ajax({
   			  url: url,
   			  data:{type:type,gold:gold,level:level,passportId:passportId},
   			  success: function(data)
   				  		{
   							$('.ami_Mask').hide();	
   							var ss = "<h1>"+data+"</br><button class='btn btn-small' onclick='goBack(\"addgold.do?method=addgold\")'>返回"
   	   						//+ "<a href='addgold.do?method=addgold' class='leaf' >返回</a>"
   	   						+ "</button>";
   							$('#PAGECONTENT').html(ss)
   						},
   			  error:function(data){
   				  $('.ami_Mask').hide();	
   				 $('#PAGECONTENT').html(data.responseText)
   				  
   			  
   			  	},
   			  dataType: 'html'
    		});
	    });
	    /* $('#form').submit(function() {
	      submit the form 
	      $(this).ajaxSubmit(options); 
	      return false to prevent normal browser submit and page navigation 
	     return false; 
	    }); */
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



