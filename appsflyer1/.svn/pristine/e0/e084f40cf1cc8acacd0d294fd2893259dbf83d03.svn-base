<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<div id="ami_main">
<div class="page-header position-relative">
		<form class="form-inline" action="adminLog.do?method=toQuery_logrecord" id="searchForm">		
            
       		   起始：<input type="text"  id="d4311" name="login_time" value="${login_time }"  class="Wdate" style="width: 150px;margin-right: 10px;padding-left: 5px" onClick="WdatePicker({maxDate:'#F{$dp.$D(\'d4312\')}',startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
			    结束：<input type="text" id="d4312" name="logout_time" value="${logout_time }"  class="Wdate" style="width: 150px;margin-right: 10px;padding-left: 5px" onClick="WdatePicker({minDate:'#F{$dp.$D(\'d4311\')}',startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss'})" /> 


           	<button type="button"  style="margin-left:10px" class="btn btn-mini btn-info" onclick="go(1)">检索</button>
		</form>
	</div>
<div class="row-fluid">	
		<div class="ami_table_header"><span class="ami_table_header_title">日志列表</span></div>
		<table id="table_report" class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<th>序号</th>
					<th>账号</th>
					<th>姓名</th>
					<th>IP</th>
					<th>操作内容</th>
                    <th>操作时间</th>
				</tr>				
			</thead>
			<tbody>				
			
				  <c:forEach var="user" items="${PAGER.items}" varStatus="status">
				<tr>
					<td>${status.index+1+ PAGER.start}</td>					
					<td><c:if test="${ empty user.adminid }"><span class="red">N/A</span></c:if>${user.adminid}</td>
	                <td>${user.username}</td>
	                <td>${user.remoteip}</td>
					<td>${user.oprate}</td>
	                <td>${user.time_stamp }</td>
				</tr>
				
			  </c:forEach>
				
			</tbody>
		</table>		
		<jsp:include page="/common/page.jsp"></jsp:include>
		
	</div>	
</div>
<div id="ami_newwindow" style="display: none;">

</div>


<script type="text/javascript" src="js/bootbox.min.js"></script>


<script type="text/javascript">
$('[data-rel=tooltip]').tooltip();
function go(page)
{
	var params = $('#searchForm').formSerialize();
	
	var url = $('#searchForm').attr('action') +"&currentPage="+page;
	$.post(url,params,function (data){
	
	$('#PAGECONTENT').html(data);	
	});
}
</script>