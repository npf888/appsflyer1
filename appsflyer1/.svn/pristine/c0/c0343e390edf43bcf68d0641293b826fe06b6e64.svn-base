<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/WEB-INF/struts-tags.tld" %>
<div id="ami_main">

<hr/>

   <div class="table-responsive">

	
		<div class="ami_table_header"><span class="ami_table_header_title">User List</span>

		</div>
		<table id="table_report"class="table table-striped table-bordered table-hover dataTables-example" >
			<thead>			
				<tr>
				    <th>No.</th>
					<th>账号ID</th>
					<th>Name</th>
					<th>Level</th>
					
					<th>筹码</th>
					
					<th>累计充值金额</th>
					<th>累计充值次数</th>
					<th>最后一次充值时间</th>
					<th>最后一次充值等级</th>
					
					<th>上次登陆IP</th>
					<th>上次登录时间</th>
					<th>上次登出时间</th>
					<th>累积在线时长</th>
					<th>创建时间</th>
				</tr>				
			</thead>
			<tbody>				
			 <c:forEach var="user" items="${PAGER.items}" varStatus="status">
					
				<tr id="tr_${user.id }">
					
					<td>${status.index+1+ PAGER.start}</td>
					<td><a onclick="openNewWinow('player.do?method=queryPlayer&useraccount=${user.id}')">${user.passportid}</a></td>
					<td>${user.name}</td>
	                 <td>${user.level} </td>
	                
	                 <td>${user.gold}</td>
	                 
	                 <td>${user.total}</td>
	                 <td>${user.totalgoldNum}</td>
	                 <td>${user.topupTime}</td>
	                 <td>${user.topupLevel}</td>
	               
	                 <td>${user.lastLoginIp}</td>
	                 <td>${user.last_login_time}</td>
	                 <td>${user.last_logout_time }</td>
	                 <td>${user.totalminute}</td>
	                 <td>${user.create_time}</td>
					
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

$(document).ready(function(){


});
function doBlock(id)
{
	bootbox.confirm("Are you sure to block this user account?", function(result) {
		if(result) {
			
			$('.ami_Mask').show();
			var url="player.do?method=doBlock&uuid="+id;
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