
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/WEB-INF/struts-tags.tld"%>
<div id="ami_main">
<div class="row-fluid">	
		<form class="form-inline" action="${pageContext.request.contextPath}/agentAll.do?method=all" id="searchForm">
			<label>用户ID|昵称:</label>
			<input  type="text" class="input-medium"  name="searchColumn" value="${searchColumn }"  id="searchColumn" ></input>				
			<button type="button"  style="margin-left:10px" class="btn btn-mini btn-info" onClick="go()">检索</button>
		</form>
</div>
<hr/>
	<div class="row-fluid">
		<div class="row-fluid" style="background-color: #FFFFFF">
			<div class="span8">
				<div class="ami_table_header">
					<span class="ami_table_header_title">所有代理商</span> 
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
							<th class=" ">代理商昵称</th>
							<th class=" ">用户名</th>
							<th class=" ">密码</th>
							<th class=" ">金币</th>
							<th class=" ">电话</th>
							<th class=" ">身份证</th>
							<th class=" ">地址</th>
							<th class=" ">微信号码</th>
							<th class=" ">qq号码</th>
							<th class=" ">父ID</th>
							<th class=" ">状态</th>
							<th class=" ">创建时间</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${list}" var="agent">
							<tr id="tr_${agent.id}">
								<td>${agent.id}</td>
								<td>${agent.passportId}</td>
								<td>${agent.nickname}</td>
								<td>${agent.username}</td>
								<td>${agent.passwords}</td>
								<td>${agent.gold}</td>
								<td>${agent.telephone}</td>
								<td>${agent.identity}</td>
								<td>${agent.address}</td>
								<td>${agent.wx}</td>
								<td>${agent.qq}</td>
								<td>${agent.parentId}</td>
								<td>${agent.state}</td>
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
