<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/WEB-INF/struts-tags.tld" %>
<div id="ami_main">
<div class="row-fluid">	
			<form class="form-inline" action="player.do?method=queryPlayer" id="searchForm">
				<label>Useraccount</label>
				<input  type="text" class="input-medium"  name="useraccount" value="${account}"  id="account" ></input>				
				<label >Username</label>
				<input type="text" class="input-medium" value="${username }" name="username"  ></input>
				<label >isFacebook</label>
				<select name="isFacebook">
					<option
						<c:if test="${isFacebook != '1'}">
						 selected="selected"
						</c:if>
						value="333">是</option>
						<!-- 上边 这里value值任意  不要等于 -1 和 2 就行-->
					<option 
						<c:if test="${isFacebook == '-1'}">
							selected="selected"
						</c:if>
						value="-1">否</option>
					<option 
						<c:if test="${isFacebook == '2'}">
							selected="selected"
						</c:if>
						value="2">全部</option>
				</select>
				<button type="button"  style="margin-left:10px" class="btn btn-mini btn-info" onClick="go(1)">检索</button>
		<c:if test="${type == 1}">
			<button type="button"  style="margin-left:10px" class="btn btn-mini btn-info" onClick="go(${currentPage})">返回</button>
		</c:if>
			</form>
</div>	
<hr/>

   <div class="table-responsive">

	
		<div class="ami_table_header"><span class="ami_table_header_title">User List</span>

		</div>
		<table id="table_report"class="table table-striped table-bordered table-hover dataTables-example" >
			<thead>			
				<tr>
				<th>No.</th>
					<th>账号ID</th>
					<th>isFacebook</th>
					<th>Name</th>
					<th>Level</th>
					<th>VIP等级</th>	
					<th>钻石</th>
					<th>筹码</th>
					<th>银行筹码</th>
					<th>银行密码</th>
					<th>魅力值</th>
					<th>当前经验</th>
					<th>上次登录时间</th>
					<th>上次登出时间</th>
					<th>累积在线时长</th>
					<th>在线状态</th>
					<th>创建时间</th>
					<th>是否显示</th>
					<th>管理</th>
				</tr>				
			</thead>
			<tbody>				
			 <c:forEach var="user" items="${PAGER.items}" varStatus="status">
					
				<tr id="tr_${user.id }">
					
					<td>${status.index+1+ PAGER.start}</td>
					<td><a onclick="openNewWinow('player.do?method=queryPlayer&useraccount=${user.id}')">${user.passportid}</a></td>
					<c:if test="${user.facebookId == '-1'}">
						<td>游客</td>
						</c:if>
					<c:if test="${user.facebookId != '-1'}">
						<td>faceBook用户</td>
					</c:if>
					<td>${user.name}</td>
	                 <td>${user.level} </td>
	                 <td>${user.viplevel}</td>
	                 <td>${user.diamond }</td>
	                 <td>${user.gold}</td>
	                 <td>${user.bankGold}</td>
	                 <td>${user.bankPassword}</td>
	                 <td>${user.charm}</td>
	                 <td>${user.curexp}</td>
	                 <td>${user.last_login_time}</td>
	                 <td>${user.last_logout_time }</td>
	                 <td>${user.totalminute}</td>
	                 <td>${user.onlinestatus}</td>
	                 <td>${user.create_time}</td>
	                 <td>
					 <c:if test="${!user.bazooAgentDisplay}">
						<button onclick="displayAgent(1,${user.passportid})">显示</button>
					 </c:if>
					 <c:if test="${user.bazooAgentDisplay}">
						<button onclick="displayAgent(0,${user.passportid})">不显示</button>
					 </c:if>
									
								</td>
					<td>
						<div class='hidden-phone visible-desktop btn-group'>
						<c:if test="${user.status != 'blocked'}">							
							<button class='btn btn-mini btn-info' onclick="removeBlock('${user.uuid}')"><i class='icon-ban-circle'></i></button>
						</c:if>
						<c:if test="${user.status == 'blocked'}">
						<button class='btn btn-mini btn-info' onclick="doBlock('${user.uuid}')"><i class='icon-ok-sign'></i></button>
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

function displayAgent(display,passportId){
	
	$.post("${pageContext.request.contextPath}/agentAll.do?method=changeAgentDisplay",{display:display,passportId:passportId},function(data){});
	bootbox.alert("保存成功",
			function() {
				nav('${pageContext.request.contextPath}/player.do?method=queryPlayer','全部代理商 ');
				});  
}
</script>