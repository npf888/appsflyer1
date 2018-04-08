<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/WEB-INF/struts-tags.tld" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div id="ami_main">
<div class="row-fluid">	
			<form class="form-inline" action="game.do?method=queryTaskStatistics" id="searchForm">
			<%-- 	<label>Useraccount</label>
				<input  type="text" class="input-medium"  name="useraccount" value="${account }"  id="account" ></input>				
				<label >Username</label>
				<input type="text" class="input-medium" value="${username }" name="username"  ></input> --%>
				<label >Date</label>
				<input type="text"  name="date" value="${date}" class="Wdate" 
					onClick="WdatePicker({dateFmt:'yyyy_MM_dd'})" 
					style="width:100px" requiry="true"/>
			
				<button type="button"  style="margin-left:10px" class="btn btn-mini btn-info" onClick="go()">检索</button>
				
			</form>
</div>	
<hr/>

<div class="row-fluid">
	
		<div class="ami_table_header"><span class="ami_table_header_title">任务</span>

		</div>
		<table id="table_report" class="table table-striped table-bordered table-hover">
			<thead>			
				<tr>
					<th>任务ID</th>
					<th>完成次数</th>
					<th>人数</th>
				</tr>				
			</thead>
			<tbody>				
			 <c:forEach var="task" items="${dailyTaskVOS}" varStatus="status">
					
				<tr id="tr_${task.taskId }">
					<td>${task.taskId }</td>
					<td>${task.finished }</td>
					<td>${task.peopleNums}</td>
				</tr>
				
			  </c:forEach>
					
			</tbody>
		</table>		
		<jsp:include page="/common/page.jsp"></jsp:include>
		
	</div>	
</div>
<div id="ami_newwindow" style="display: none;">

</div>
<script type="text/javascript">

function go()
{
	var params = $('#searchForm').formSerialize();
	
	var url = $('#searchForm').attr('action');
	$.post(url,params,function (data){
		
		 $('#PAGECONTENT').html(data);
	});
}

</script>