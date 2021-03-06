<!-- Log 页面 -->
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page language="java" import="com.ami.texas.common.util.LogReasons" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<base href="${pageContext.request.contextPath}/" />
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
			<form class="form-inline" action="bazooGold.do?method=pumpWaterBazooGold" id="searchForm">

					<label>日期：</label>
			  		<input type="text" id="d4312" name="date" value="${params.date}"  class="Wdate"  onClick="WdatePicker({startDate:'%y-%M-01 00',dateFmt:'yyyy_MM_dd'})" /> 
					
					<label>玩家账号ID</label>
					<input class="input-medium" style="width: 100px" name="accountId" value="${params.accountId}"></input>
					
					<label>房间号</label>
					<input class="input-medium" style="width: 100px" name="roomNumber" value="${params.roomNumber}"></input>
					
					<label style="display:none;">原因</label>
					<input class="input-medium" style="width: 100px;display:none;" name="reason" value="${params.reason}"></input>
					
					
					<button type="button"  style="margin-left:10px" class="btn btn-mini btn-info" onClick="go(1)">检索</button>
			</form>
	</div>
<hr/>

<div class="row-fluid">
	

		</div>
		<table id="table_report" class="table table-striped table-bordered table-hover">
			<thead>			
				<tr>
				<th>No.</th>
					<th style="display:none;">日志类型</th>
					<th style="display:none;">日志时间</th>
					<th style="display:none;">区ID</th>
					<th style="display:none;">服务器ID</th>
					
					<th>是否机器人</th>
					<th>玩家账号ID</th>
					<th>玩家账号名</th>
					<th>等级</th>
					<th>原因</th>						
					<th>房间号</th>						
					
					<th>扣掉流水</th>
					<th style="display:none;">筹码剩余</th>
					<th>创建时间</th>
			
				</tr>				
			</thead>
			<tbody>				
			 <c:forEach var="user" items="${PAGER.items}" varStatus="status">
					
				<tr id="tr_${user.id }">
				
					<td>${status.index+1+ PAGER.start}</td>
					<td style="display:none;">${user.logType}</td>
					<td style="display:none;">${user.logTime }</td>
					<td style="display:none;">${user.regionId}</td>
					<td style="display:none;">${user.serverId}</td>
					<td>${user.deviceMac}</td>
					<td><a onclick="openNewWinow('bazooGold.do?method=pumpWaterBazooGold&accountId=${user.accountId}&date=${params.date}')">${user.accountId}</a></td>
					<td>${user.accountName}</td>
					<td>${user.level }</td>
				  	<td>${user.reason}</td>
					<td><a onclick="openNewWinow('bazooGold.do?method=pumpWaterBazooGold&roomNumber=${user.param}&date=${params.date}')">${user.param}</a></td>
					
					<th>${user.goldDelta}</th>
					<th style="display:none;">${user.goldLeft }</th>
					<td>${user.createTime}</td>
						
					
					
				</tr>
				
			  </c:forEach>
					
			</tbody>
		</table>		
		<jsp:include page="/common/page.jsp"></jsp:include>
		
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