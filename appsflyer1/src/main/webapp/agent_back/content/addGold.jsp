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
		<label class="control-label">接收金币的用户ID</label>
		<div class="controls">
			<input type="text" name="givePassportId" value="${givePassportId}"  requiry="true">
		</div>
	</div>
	
	
	<div class="control-group">
		<label class="control-label">给予金币数量</label>
		<div class="controls">
			<input type="text" name="gold" value="${gold}"  requiry="true">
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
	$.post('${pageContext.request.contextPath}/agent.do?method=toAddGold&'+$("#form").serialize(), function(data) {
	});
	bootbox.alert("保存成功",
		function() {
			nav('goldChange.do?method=list','销售记录');
		});
}


</script>



