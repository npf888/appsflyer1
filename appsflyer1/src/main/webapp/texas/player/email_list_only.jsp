<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/WEB-INF/struts-tags.tld" %>
<div id="ami_main">
<div class="row-fluid">	
			<form class="form-inline" action="player.do?method=queryOnlyEmail" id="searchForm">
				<label>发件人ID</label>
				<input  type="text" class="input-medium"  name="sendUserid" value="${sendUserid }"  id="sendUserid" ></input>				
				<label>发件人名称</label>
				<input type="text" class="input-medium" value="${sendUsername }" name="sendUsername"  ></input>
				<font color="red">---</font>
				<label>收件人ID</label>
				<input  type="text" class="input-medium"  name="useraccount" value="${account }"  id="account" ></input>				
				<label>收件人名称</label>
				<input type="text" class="input-medium" value="${username }" name="username"  ></input>
				<button type="button"  style="margin-left:10px" class="btn btn-mini btn-info" onClick="go(1)">检索</button>
			</form>
</div>	
<hr/>

<div class="row-fluid">
	
		<div class="ami_table_header"><span class="ami_table_header_title">电子邮件</span>

		</div>
		<table id="table_report" class="table table-striped table-bordered table-hover">
			<thead>			
				<tr>
				<th>No.</th>
					<th>发礼物用户ID|（名字）</th>
					<th>收礼物ID|（姓名）</th>
					<th>标题</th>
					<th>内容</th>
					<th>附件内容</th>
					<!-- <th>邮件状态</th> -->
					<th>是否删除</th>
					<th>更新时间</th>
					<th>创建时间</th>			
				</tr>				
			</thead>
			<tbody>				
			 <c:forEach var="user" items="${PAGER.items}" varStatus="status">
					
				<tr id="tr_${user.id }">
					
					<td>${status.index+1+ PAGER.start}</td>
					<td>${user.sendid}|（${user.sendname}）</td>
					<td>${user.recid}|（${user.recname}）</td>
					<td>${user.title}</td>
					<td>${user.content}</td>
					<td>${user.attachmentpack}</td>
					<%-- <td>${user.mailstatus}</td> --%>
					
					<c:if test="${user.deleted == true}">
		                <td><font color="red">已删除</font></td>
					</c:if>
					<c:if test="${user.deleted == false}">
		                <td><font color="blue">未删除</font></td>
					</c:if>
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