<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/WEB-INF/struts-tags.tld" %>
<div id="ami_main">
<div class="row-fluid">	
			<form class="form-inline" action="collect.do?method=query" id="searchForm">
				<label>Useraccount</label>
				<input  type="text" class="input-medium"  name="useraccount" value="${account }"  id="account" ></input>				
				<label >Username</label>
				<input type="text" class="input-medium" value="${username }" name="username"  ></input>
				<button type="button"  style="margin-left:10px" class="btn btn-mini btn-info" onClick="go(1)">检索</button>
				<button type="button"  style="margin-left:10px" class="btn btn-mini btn-info" onClick="excel()">导出excel</button>
			</form>
</div>	
<hr/>

<div class="row-fluid">
	
		<div class="ami_table_header"><span class="ami_table_header_title">收集系统</span>

		</div>
		<table id="table_report" class="table table-striped table-bordered table-hover">
			<thead>			
				<tr>
					<th style="display:none;">ID</th>
					<th>姓名</th>
					<th>等级</th>
					<th>拥有的奖券总数</th>
					<th>拥有的金券数量</th>
					<th>拥有的银券数量</th>
					<th>拥有的铜券数量</th>
					
					<th style="display:none;">碎片</th>
					<th style="display:none;">老虎机点击次数</th>
					<th style="display:none;">老虎机点击位置</th>
					
					<th>更新时间</th>
					<th>创建时间</th>			
				</tr>				
			</thead>
			<tbody>				
			 <c:forEach var="user" items="${PAGER.items}" varStatus="status">
					
				<tr id="tr_${user.id }">
					<td style="display:none;">${user.id}</td>
					<td>${user.name}</td>
					<td>${user.level}</td>
					
					<td>${user.totalNum}</td>
					<td>${user.goldNum}</td>
					<td>${user.silverNum}</td>
					<td>${user.cuprumNum}</td>
					
					
					
					<td style="display:none;">${user.debris}</td>
					<td style="display:none;">${user.slotspin}</td>
					<td style="display:none;">${user.slotpoint}</td>
	                 <td>${user.updateTime} </td>
	                 <td>${user.createTime}</td>

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

function doBlock(id)
{
	bootbox.confirm("Are you sure to block this user account?", function(result) {
		if(result) {
			
			$('.ami_Mask').show();
			var url="affiliate.do?method=doBlock&uuid="+id;
			$.get(url, function(data){
				$('.ami_Mask').hide();
				bootbox.alert("	User Blocked!");						
			});						
		}
	});		
}
function doDelete(id)
{
	
	bootbox.confirm("Delete this user account?", function(result) {
		if(result) {
			
			$('.ami_Mask').show();
			var url="affiliate.do?method=doDelete&uuid="+id;
			$.get(url, function(data){
				alert(data);
				$('.ami_Mask').hide();
				$('#tr_'+id).remove();
				bootbox.alert("	Removed!");						
			});						
		}
	});	
}

function excel(){
	$.get("collect.do?method=excel", function(data){
	});	
}
</script>