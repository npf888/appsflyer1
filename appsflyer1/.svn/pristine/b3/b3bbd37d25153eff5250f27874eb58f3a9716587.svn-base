
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/WEB-INF/struts-tags.tld"%>
<div id="ami_main">
	<div class="row-fluid" id="div1">	
			<form class="form-inline" action="${pageContext.request.contextPath}/goldChange.do?method=list" id="searchForm">

					<label>日期：</label>
			  		<input type="text" readonly="readonly"  name="date" id="date" value="${date}"  class="Wdate"  onClick="WdatePicker({startDate:'%y-%M-01 00',dateFmt:'yyyy_MM_dd'})" /> 
					
					<button type="button"  style="margin-left:10px" class="btn btn-mini btn-info" onClick="go()">检索</button>
			</form>
	</div>
<hr/>
	<div class="row-fluid">
		<div class="row-fluid" style="background-color: #FFFFFF">
			<div class="span8">
				<div class="ami_table_header">
					<span class="ami_table_header_title">金币变化</span> 
					<span
						class="ami_table_header_btn">
					</span>
				</div>

				<table id="table_bug_report"
					class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th class=" ">id</th>
							<th class=" ">代理商ID</th>
							<th class=" ">原因</th>
							<th class=" ">原因内容</th>
							<th class=" ">增加或者减少</th>
							<th class=" ">剩余金币</th>
							<th class=" ">创建时间</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${list}" var="agent">
							<tr id="tr_${agent.id}">
								<td>${agent.id}</td>
								<td>${agent.passportId}</td>
								<td>${agent.reason}</td>
								<td>${agent.param}</td>
								<td>${agent.goldDelta}</td>
								<td>${agent.goldLeft}</td>
								<td>${agent.createTime}</td>

							</tr>
						</c:forEach>
					</tbody>
				</table>

			</div>
			<!--/span-->
		</div>
		<!--/row-->
	</div>
</div>

<div id="ami_newwindow" style="display: none;"></div>
<script>
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

</script>
