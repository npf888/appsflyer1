<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/WEB-INF/struts-tags.tld" %>
<div id="ami_main">
<div class="row-fluid">	
			<form class="form-inline" action="game.do?method=queryWeeklySignin" id="searchForm">
				<label>Useraccount</label>
				<input  type="text" class="input-medium"  name="useraccount" value="${account }"  id="account" ></input>				
				<label >Username</label>
				<input type="text" class="input-medium" value="${username }" name="username"  ></input>
				<button type="button"  style="margin-left:10px" class="btn btn-mini btn-info" onClick="go(1)">检索</button>
			</form>
</div>
<hr/>

<div class="row-fluid">
	
		<div class="ami_table_header"><span class="ami_table_header_title">签到</span>

		</div>
		<table id="table_report" class="table table-striped table-bordered table-hover">
			<thead>			
				<tr>
				<th>No.</th>
					<th>ID</th>
					<th>玩家角色ID</th>
					<th>签到信息</th>
					<th>更新时间</th>
					<th>创建时间</th>			
				</tr>				
			</thead>
			<tbody>				
			 <c:forEach var="user" items="${PAGER.items}" varStatus="status">
					
				<tr id="tr_${user.id }">
					
					 <td>${status.index+1+ PAGER.start}</td>
					 <td>${user.id}</a></td>
					 <td><a onclick="openNewWinow('player.do?method=loadByID&id=${user.charid}')">${user.charid}</td>
					 <td>${user.signinpack }</td>
	                 <td>${user.update_time} </td>
	                 <td>${user.create_time}</td>

					<td>
						<div class='hidden-phone visible-desktop btn-group'>
						<c:if test="${user.status != 'blocked'}">							
							<button class='btn btn-mini btn-info' onclick="removeBlock('${user.uuid}')"><i class='icon-ban-circle'></i></button>
						</c:if>
						<c:if test="${user.status == 'blocked'}">
						<button class='btn btn-mini btn-info' onclick="doBlock('${user.uuid}')"><i class='icon-ok-sign'></i></button>
						</c:if>
							<button class='btn btn-mini btn-danger' onclick="doDelete('${user.uuid }')"><i class='icon-trash'></i></button>						
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