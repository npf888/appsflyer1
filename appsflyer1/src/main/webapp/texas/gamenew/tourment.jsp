<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/WEB-INF/struts-tags.tld" %>
<div id="ami_main">
<div class="row-fluid">	
			<form class="form-inline" action="tourment.do?method=query" id="searchForm">
				<label>Useraccount</label>
				<input  type="text" class="input-medium"  name="useraccount" value="${account }"  id="account" ></input>				
				<label >Username</label>
				<input type="text" class="input-medium" value="${username }" name="username"  ></input>
				<label >date</label>
				<input type="text"   name="date" value="${date}" class="Wdate"  onClick="WdatePicker({realDateFmt:'yyyy-MM-dd',dateFmt:'yyyy-MM-dd'})" style="width:100px" requiry="true"/>
				<button type="button"  style="margin-left:10px" class="btn btn-mini btn-info" onClick="go(1)">检索</button>
			</form>
</div>	
<hr/>

<div class="row-fluid">
	
		<div class="ami_table_header"><span class="ami_table_header_title">竞赛系统</span>

		</div>
		<table id="table_report" class="table table-striped table-bordered table-hover">
			<thead>			
				<tr>
					<!-- <th>用户ID</th>
					<th>用户名称</th>
					<th>等级</th>
					<th>理由</th> -->
					
					
					<th>竞赛的ID</th>
					<th>竞赛的类型</th>
					<th>老虎机类型</th>
					<th>累计的总金币</th>
								
					<th>奖池中的总金币</th>			
					<th>分钱比例</th>			
					<th>最终获得的钱</th>			
					<th>创建时间</th>			
					<th>更多细节</th>			
				</tr>				
			</thead>
			<tbody>				
			 <c:forEach var="user" items="${PAGER.items}" varStatus="status">
					
				<tr >
					
					<%-- <td>${user.accountId}</td>
					<td>${user.accountName}</td>
					<td>${user.level}</td>
					<td>${user.reason}</td> --%>
					
					
					<td>${user.tournamentId}</td>
					<td>${user.tournamentType}</td>
	                 <td>${user.slotType} </td>
	                 <td>${user.totalReward} </td>
	                 
	                 <td>${user.rewards} </td>
	                 <td>${user.reward} </td>
	                 <td>${user.obtainedReward} </td>
	                 <td>${user.createTime}</td>
	                  <td><a onclick="moreDetail('tourment.do?method=queryMoreDetail&tournamentId=${user.tournamentId }&date=${user.createTime}')">更多详情</a></td>

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