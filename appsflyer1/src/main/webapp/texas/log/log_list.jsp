<!-- Log 页面 -->
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page language="java" import="com.ami.texas.common.util.LogReasons" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<base href="${pageContext.request.contextPath}/" />
<link rel="stylesheet" type="text/css" href="/js/Clockpicker/bootstrap-clockpicker.min.css">
<style>
.cloro-Goldenrod{
background:#DC143C;
color:#fff;
}
.cloro-DarkGoldenrod{
background:#2E8B57;
color:#fff;
}
</style>
<div id="ami_main">
	<div class="row-fluid" id="div1">	
			<form class="form-inline" action="log.do?method=queryLog&type=${params.type}" id="searchForm">

					<input type="hidden" name="type" value="${params.type}"/>
					<label>日期：</label>
			  		<input type="text" id="d4312" name="date" value="${params.date}"  class="Wdate"  onClick="WdatePicker({startDate:'%y-%M-01 00',dateFmt:'yyyy_MM_dd'})" /> 
					
					<label>选择时间段：</label>
					
					<input class="form-control" name="start" id="start_time" style="width:60px" value="${params.start}" >		
					--
					<input class="form-control" name="end" id="end_time" style="width:60px" value="${params.end}"/>
					
					<label>玩家账号ID</label>
					<input class="input-medium" style="width: 100px" name="account_id" value="${params.account_id}"></input>
					
					
					<button type="button"  style="margin-left:10px" class="btn btn-mini btn-info" onClick="go(1)">检索</button>
			</form>
	</div>
<hr/>

<div class="row-fluid">
	
		<div class="ami_table_header"><span class="ami_table_header_title">${params.type} Logs</span>

		</div>
		<table id="table_report" class="table table-striped table-bordered table-hover">
			<thead>			
				<tr>
				<th>No.</th>
					<th>日志类型</th>
					<th>时间</th>
					<th>创建时间</th>
					<th>玩家账号ID</th>
					<th>玩家账号名</th>
					<th>等级</th>
					
					<c:if test="${params.type == 'chat'}">
						<th>频道</th>
						<th>接受者名字</th>
						<th>内容</th>
					</c:if>
					
					<c:if test="${params.type == 'gold'}">
						<th>筹码变化</th>
						<th>筹码剩余</th>
					</c:if>
					
					<c:if test="${params.type == 'diamond'}">
						<th>平台币变化</th>
						<th>平台币剩余</th>
					</c:if>
					
					<c:if test="${params.type == 'monthcard'||type=='weekcard'}">
						<th>持续时间(天数)</th>
					</c:if>
					
					<c:if test="${params.type == 'vip'}">
						<th>vip等级</th>
					</c:if>
					
					<c:if test="${params.type == 'recharge'}">
						<th>订单号</th>
						<th>产品编号</th>
					</c:if>
					
					<c:if test="${params.type == 'sign_in'}">
						<th>连续签到</th>
						<th>累计签到</th>
					</c:if>
										
					<c:if test="${params.type == 'texas_room'}">
						<th>房间ID</th>
						<th>模式ID</th>
						<th>类型ID</th>
					</c:if>
					
					<c:if test="${params.type == 'baccarat_room'}">
						<th>房间ID</th>
					</c:if>
					<c:if test="${params.type == 'slot_room'}">
						<th>老虎机ID</th>
					</c:if>
					
					<th>原因</th>						
					<th>区ID</th>
					<th>备注</th>
			
				</tr>				
			</thead>
			<tbody>				
			 <c:forEach var="user" items="${PAGER.items}" varStatus="status">
					
				<tr id="tr_${user.id }">
				
					<td>${status.index+1+ PAGER.start}</td>
					<td>${user.log_type}</td>
					<td>${user.log_date }</td>
					<td>${user.create_date}</td>
					<td><a onclick="openNewWinow('log.do?method=queryLog&type=${params.type}&account_id=${user.account_id}')">${user.account_id}</a></td>
					<td>${user.account_name}</td>
					<td>${user.level }</td>
					
					<c:if test="${params.type == 'chat'}">
						<td>${user.channel }</td>
						<td>${user.rec_char_name }</td>
						<td>${user.content}</td>
					</c:if>
					<c:if test="${params.type == 'gold'}">
						<th>${user.gold_delta}</th>
						<th>${user.gold_left }</th>
					</c:if>
					
					<c:if test="${params.type == 'diamond'}">
						<th>${user.diamond_delta}</th>
						<th>${user.diamond_left}</th>
					</c:if>
					
					<c:if test="${params.type == 'monthcard'||type=='weekcard'}">
						<th>${user.duration_day }</th>
					</c:if>
					
					<c:if test="${params.type == 'vip'}">
						<th>${user.vip_level}</th>
					</c:if>
					
					<c:if test="${params.type == 'recharge'}">
						<th>${user.order_id}</th>
						<th>${user.product_id }</th>
					</c:if>
					
					<c:if test="${params.type == 'sign_in'}">
						<th>${user.days}</th>
						<th>${user.culumative}</th>
					</c:if>
										
					<c:if test="${params.type == 'texas_room'}">
						<th>${user.room_id}</th>
						<th>${user.mode_id}</th>
						<th>${user.type_id}</th>
					</c:if>
					
					<c:if test="${params.type == 'baccarat_room'}">
						<th>${user.room_id}</th>
					</c:if>
					<c:if test="${params.type == 'slot_room'}">
						<th>${user.room_id}</th>
					</c:if>
					
						
					<c:choose>
							  <c:when test="${user.reason == 0}">
							  <td class="cloro-Goldenrod">${user.reason}</td>
							  </c:when>
							  <c:otherwise>
							  <td class="cloro-DarkGoldenrod">${user.reason}</td>
							  </c:otherwise>
					</c:choose>					
					<td>${user.region_id}</td>
					
					<td>${user.param}</td>
					
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
$('#start_time').clockpicker({
    placement: 'bottom',
    align: 'left',
    autoclose: true
});
$('#end_time').clockpicker({
    placement: 'bottom',
    align: 'left',
    autoclose: true
});
</script>