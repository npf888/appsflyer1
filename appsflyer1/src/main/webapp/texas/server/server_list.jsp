<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/WEB-INF/struts-tags.tld" %>
<div id="ami_main">
<div class="row-fluid">	
			<form class="form-inline" action="game.do?method=queryNews" id="searchForm">
				<label>Useraccount</label>
				<input  type="text" class="input-medium"  name="useraccount" value="${account }"  id="account" ></input>				
				<label >Username</label>
				<input type="text" class="input-medium" value="${username }" name="username"  ></input>
				<button type="button"  style="margin-left:10px" class="btn btn-mini btn-info" onClick="go(1)">检索</button>
			</form>
</div>	
<hr/>

<div class="row-fluid">
	
		<div class="ami_table_header"><span class="ami_table_header_title">服务器列表</span>

		</div>
		<input type="hidden" name="server" value="${G_url}">
		<table id="table_report" class="table table-striped table-bordered table-hover">
			<thead>			
				<tr>
					<th>Server ID</th>
					<th>IP Address</th>
					<th>Port</th>
					<th>Nums</th>
					<th>Status</th>
					<th>管理</th>
				</tr>				
			</thead>
			<tbody>				
			 <c:forEach var="server" items="${servers}" varStatus="status">
					
				<tr>
					<td>${server.serverId}</td>
					<td>${server.ip}</td>
					<td>${server.port}</td>
					<td>${server.nums}</td>
				<c:if test="${server.status==1}">
					<td>运行中</td>
				</c:if>
				<c:if test="${server.status==0}">
					<td>测试中</td>
				</c:if>
				<c:if test="${server.status==2}">
					<td>已停机</td>
				</c:if>

					<td>
						<div class='hidden-phone visible-desktop btn-group'>
						<c:if test="${server.status != 1}">							
							<button class='btn btn-mini btn-info' onclick="removeTest('${server.serverId}')">转为运行</button>
						</c:if>
						<c:if test="${server.status == 1}">
							<button class='btn btn-mini btn-info' onclick="doTest('${server.serverId}')">转为测试</button>
						</c:if>					
						</div>
					</td>
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

function doTest(id)
{
	bootbox.confirm("确定要转为测试服么?", function(result) {
		if(result) {
			
			$('.ami_Mask').show();
			var url="server.do?method=update&serverId="+id+"&state=0";
			$.post(url, function(data){
				$('.ami_Mask').hide();
				bootbox.alert("data");						
			});						
		}
	});		
}

function removeTest(id)
{
	bootbox.confirm("确定运行服务器?", function(result) {
		if(result) {
			
			$('.ami_Mask').show();
			var url="server.do?method=update&serverId="+id+"&state=1";
			$.post(url, function(data){
				$('.ami_Mask').hide();
				bootbox.alert("data");						
			});						
		}
	});		
}
</script>