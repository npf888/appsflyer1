<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/WEB-INF/struts-tags.tld" %>
<div id="ami_main">
<div class="row-fluid">	
			<form class="form-inline" action="statistics.do?method=order" id="searchForm">
				<label>channelId</label>
				<input  type="text" class="input-medium"  name="channelId" value="${channelId }"   ></input>				
				<label>Useraccount</label>
				<input  type="text" class="input-medium"  name="useraccount" value="${account }"  id="account" ></input>				
				<label >Username</label>
				<input type="text" class="input-medium" value="${username }" name="username"  ></input>
				<button type="button"  style="margin-left:10px" class="btn btn-mini btn-info" onClick="go(1)">检索</button>
			</form>
</div>	
<hr/>

<div class="row-fluid">
	
		<div class="ami_table_header"><span class="ami_table_header_title">订单用户情况</span>

		</div>
		<table id="table_report" class="table table-striped table-bordered table-hover">
			<thead>			
				<tr>
					
					
					<th>渠道ID</th>
					<th>创建时间</th>
					<th>订单用户ID</th>
					<th>用户ID</th>
					<th>用户名</th>
					
					<th>当前等级</th>			
					<th>当前金币</th>			
					<th>产品ID</th>
								
					<th>话费(美元/100)</th>			
					<th>购买商品名称</th>			
				</tr>				
			</thead>
			<tbody>				
			 <c:forEach var="user" items="${PAGER.items}" varStatus="status">
					
				<tr >
					
					
					
					<td>${user.channelId}</td>
					<td>${user.createTime}</td>
	                 <td>${user.charId} </td>
	                 <td>${user.passportId} </td>
	                 <td>${user.name} </td>
	                 
	                 <td>${user.level} </td>
	                 <td>${user.gold} </td>
	                 <td>${user.productId} </td>
	                 
	                 <td>${user.cost} </td>
	                 <td>${user.langDesc} </td>

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
function moreDetail(url){
	$.get(url, function(data){
		$('.ami_Mask').hide();	
		$('#PAGECONTENT').html(data)					
	});		
}
</script>