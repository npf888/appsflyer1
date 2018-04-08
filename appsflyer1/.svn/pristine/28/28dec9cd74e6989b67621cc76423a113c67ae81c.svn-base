<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>



<form class="form-horizontal" action="#" method="post" id="form">
	 

	<div class="control-group">
		<label class="control-label">当前用户ID</label>
		<div class="controls">
			<input type="text" name="costPassportId" value="${costPassportId}"  requiry="true">
		</div>
	</div>
	
	
	
	<div class="control-group">
		<label class="control-label">接收红包的用户ID</label>
		<div class="controls">
			<input type="text" name="givePassportId" value="${givePassportId}"  requiry="true">
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">当前红包的面值</label>
		<div class="controls">
			<input type="text" name="redPackageValue" value="100000"  readonly="readonly">
		</div>
	</div>
	
	
	<div class="control-group">
		<label class="control-label">给予红包数量</label>
		<div class="controls">
			<input type="text" name="number" value="${number}"  requiry="true">
		</div>
	</div>


 </form>
  <div class="form-actions">
		<button class="btn btn-small btn-info" id="submit" onclick="save()">增加</button>	
		&nbsp; &nbsp; &nbsp;
		<button class="btn btn-small" type="reset" onclick="goback()"><i class="icon-undo"></i>取消</button>
  </div>
<script>
function save()
{
	$.post('${pageContext.request.contextPath}/agent.do?method=sendRedPackage&'+$("#form").serialize(), function(data) {
		var dataObj=eval("("+data+")");
		if(dataObj.sendRedPackage == 0){//有发送红包权限
			bootbox.alert("保存成功",
				function() {
					nav('goldChange.do?method=list','销售记录');
				});
			
		}else{//没有发送红包权限
			bootbox.alert("没有发送红包权限,需要上级代理商开启",
				function() {
					nav('agent.do?method=noticeRedPackage&givePassportId=${givePassportId}','发送红包');
				});
			
		}
	});
}


</script>



