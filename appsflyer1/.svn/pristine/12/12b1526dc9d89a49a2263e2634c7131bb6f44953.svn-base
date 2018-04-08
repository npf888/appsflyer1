<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/WEB-INF/struts-tags.tld" %>
<div id="ami_main">
<div class="row-fluid">	
			<form class="form-inline" action="worldBoss.do?method=queryWorldBossDetail&bossId=${bossId }&date=${date}" id="searchForm">
				<label>Useraccount</label>
				<input  type="text" class="input-medium"  name="useraccount" value="${account }"  id="account" ></input>				
				<label >bossType</label>
				<input type="text" class="input-medium" value="${bossType }" name="bossType"  ></input>
				<button type="button"  style="margin-left:10px" class="btn btn-mini btn-info" onClick="go(1)">检索</button>
			</form>
</div>	
<hr/>

<div class="row-fluid">
	
		<div class="ami_table_header"><span class="ami_table_header_title">boss详情</span>

		</div>
		<table id="table_report" class="table table-striped table-bordered table-hover">
			<thead>			
				<tr>
					<th>开始时间</th>
					<th>bossId</th>
					<th>bossType</th>
					<th>boss开始结束时间</th>
					
					<th>用户ID</th>
					<th>用户昵称</th>
					<th>等级</th>
					<th>原因</th>
					
					<th>开始结束</th>
					<th>技能开始结束</th>
					<th>当前攻击的血量</th>
					<th>创建时间</th>
					
				</tr>				
			</thead>
			<tbody>				
			 <c:forEach var="user" items="${PAGER.items}" varStatus="status">
					
				<tr >
					
					<td>${user.starttime}</td>
					<td>${user.bossId}</td>
					<td>${user.bossType}</td>
					<td>${user.starttime}</td>
					
					<td>${user.accountId}</td>
					<td>${user.accountName}</td>
					<td>${user.level}</td>
					<td>${user.reason}</td>
					
					<td>${user.beginEnd}</td>
					<td>${user.bloodBeginEnd}</td>
					<td>${user.curAttackBlood}</td>
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
</script>