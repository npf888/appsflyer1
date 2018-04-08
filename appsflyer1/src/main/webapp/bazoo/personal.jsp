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
			<form class="form-inline" action="bazooPersonal.do?method=bazooPersonal" id="searchForm">

					<label>玩家账号ID</label>
					<input class="input-medium" style="width: 100px" name="accountId" value="${params.accountId}"></input>
					
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
					<th>是否机器人</th>
					<th>用户ID</th>
					<th>模式</th>
					<th>玩的总次数</th>
					<th>赢得次数</th>
					
					<th>胜率</th>
					<th style="display:none;">单局最高</th>
					<th style="display:none;">连胜</th>
					<th style="display:none;">通杀</th>						
					<th style="display:none;">最大牌型</th>						
					
					<th style="display:none;">豹子数</th>
					<th style="display:none;">天盈利</th>
					<th style="display:none;">周盈利</th>
					<th style="display:none;">月盈利</th>
			
				</tr>				
			</thead>
			<tbody>				
			 <c:forEach var="user" items="${PAGER.items}" varStatus="status">
					
				<tr id="tr_${user.id }">
				
					<td>${status.index+1+ PAGER.start}</td>
					<td>${user.deviceMac}</td>
					<td><a onclick="openNewWinow('bazooPersonal.do?method=bazooPersonal&accountId=${user.passportId}')">${user.passportId}</a></td>
					<c:if test="${user.modeType == 1}">
						<td>经典模式</td>
					</c:if>
					<c:if test="${user.modeType == 2}">
						<td>牛牛模式</td>
					</c:if>
					<c:if test="${user.modeType == 3}">
						<td>梭哈模式</td>
					</c:if>
					<td>${user.numberOfGame}</td>
					<td>${user.winTimes}</td>
					<td>${user.rateOfWinning}%</td>
					<td style="display:none;">${user.singleTopGold}</td>
					<td style="display:none;">${user.aWinningStreak}</td>
					<td style="display:none;">${user.passToKill}</td>
					<td style="display:none;">${user.bigPatterns}</td>
					<td style="display:none;">${user.pantherNumber}</td>
					<td style="display:none;">${user.dayProfit}</td>
					<td style="display:none;">${user.weekProfit}</td>
					<td style="display:none;">${user.monthProfit}</td>
						
					
					
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