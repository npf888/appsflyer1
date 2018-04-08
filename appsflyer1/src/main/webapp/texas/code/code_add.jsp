<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<form class="form-horizontal" action="code.do?method=editCode" method="post" id="form">
	  
	  <div class="control-group" style="display:none;">
		<label class="control-label" for="form-field-accout">ID</label>
		<div class="controls">
		  <input type="text" name="id"  class="validation" requiry="true" value="${one.id}" />
		</div>
	  </div>
	   <c:if test="${one!=null }">
		  <div class="control-group">
			<label class="control-label" for="form-field-accout">兑换码</label>
			<div class="controls">
			  <input type="text" name="code"  class="validation" requiry="true" value="${one.conversionCode}" />
			</div>
		  </div>
	   </c:if>
	   
	  <c:if test="${one==null }">
		  <div class="control-group">
			<label class="control-label" for="form-field-accout">兑换码数量</label>
			<div class="controls">
			  <input type="text" name="codeNum"  class="validation" requiry="true"  />
			</div>
		  </div>
	  </c:if>
	  <div class="control-group">
		<label class="control-label" for="form-field-accout">金币</label>
		<div class="controls">
		  <input type="text" name="gold"    class="validation" requiry="true"value="${one.gold}" />
		</div>
	  </div>
	 
	   <div class="control-group">
			<label class="control-label" >结束日期</label>		
			<div class="controls">
			 <input type="text"  name="endTime" value="${one.endTime}" class="Wdate"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width:100px" requiry="true"/>
	  		</div>
	  </div>
	  <div class="control-group">
		<label class="control-label" >类型</label>		
		<div class="controls">
		  <input type="radio" name="codeType" value="0" <c:if test="${ one == null ||one.codeType==0 }">checked</c:if> >普通
		  <input type="radio" name="codeType" value="1" <c:if test="${ one.codeType==1}">checked</c:if> >特殊
	  	</div>
	  </div>
	 
	  
	  <div class="form-actions">
			<button class="btn btn-small btn-info" type="button" id="submit" ><i class="icon-ok"></i>提交</button>	
			&nbsp; &nbsp; &nbsp;
			<button class="btn btn-small" type="reset"><i class="icon-undo"></i> Reset</button>
			<button class="btn btn-small btn-info" type="button" onclick="goBack('code.do?method=queryCode')"><i class="icon-undo"></i>返回</button>
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
	    var checkSubmitFlg = false;
	    $("#submit").click(function(){
	    	$('#submit').attr("disabled",true);
	    	if(checkSubmitFlg == true){
	    		return;
	    	}
	    	checkSubmitFlg=true;
	    	var url = "code.do?method=toEdit";
	    	var id = $("input[name='id']").val();
	    	var code = $("input[name='code']").val();
	    	var codeNum = $("input[name='codeNum']").val();
	    	var gold = $("input[name='gold']").val();
	    	var endTime = $("input[name='endTime']").val();
	    	var codeType = $("input[name='codeType']:checked").val();
    		 $.ajax({
   			  url: url,
   			  data:{id:id,code:code,codeNum:codeNum,gold:gold,endTime:endTime,codeType:codeType},
   			  success: function(data)
   				  		{
   							checkSubmitFlg=false;
   							$('.ami_Mask').hide();	
   							var ss = "<h1>"+data+"</br><button class='btn btn-small' onclick='goBack(\"code.do?method=queryCode\")'>返回"
   	   						//+ "<a href='addgold.do?method=addgold' class='leaf' >返回</a>"
   	   						sleep(1500);
   	   					$('#submit').attr("disabled","disabled"); 
   							goBack('code.do?method=queryCode');
   						},
   			  error:function(data){
   				  $('.ami_Mask').hide();	
   				checkSubmitFlg=false;
   				$('#submit').attr("disabled","disabled"); 
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
	

	function sleep(numberMillis) { 
		var now = new Date(); 
		var exitTime = now.getTime() + numberMillis; 
		while (true) { 
			now = new Date(); 
			if (now.getTime() > exitTime) 
				return; 
		} 
	}
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



