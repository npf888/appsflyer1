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
			<form class="form-inline" action="bazooPersonal.do?method=bazooPersonalEveryDay" id="searchForm">
			
					<label>日期：</label>
			  		<input type="text" id="d4312" name="date" value="${params.date}"  class="Wdate"  onClick="WdatePicker({startDate:'%y-%M-01 00',dateFmt:'yyyy_MM_dd'})" /> 
					
					<label>玩家账号ID</label>
					<input class="input-medium" style="width: 100px" name="accountId" value="${params.accountId}"></input>
					
					<label>是否机器人</label>
					<select name="isRobot">
							<option value="0" 
							
								<c:if test="${params.reason == 0}">
									selected="selected"
								</c:if>
							
							>否</option>
							
							<option value="1" 
								
								<c:if test="${params.reason == 1}">
									selected="selected"
								</c:if>	
								
							>是</option>
							<option value=""  
							<c:if test="${params.reason != 1 && params.reason != 0}">
								selected="selected"
							</c:if>
							>全部</option>
					</select>
					
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
					<th>用户ID</th>
					<th>用户名称</th>
					<th>是否机器人</th>
					<th>模式</th>
					<th>房间号</th>
					<th>赢得次数</th>
					<th>玩的总次数</th>
			
				</tr>				
			</thead>
			<tbody>				
			 <c:forEach var="user" items="${everyDayVOList}" varStatus="status">
					
				<tr id="tr_${user.id }">
				
					<td>${status.index+1}</td>
					<td>${user.accountId}</td>
					<td>${user.accountName}</td>
					
					<c:if test="${user.isRobot == 1}">
						<td><font color="red">机器人</font></td>
					</c:if>
					<c:if test="${user.isRobot == 0}">
						<td>用户</td>
					</c:if>
					
					<c:if test="${user.modeType == 1}">
						<td>经典模式</td>
					</c:if>
					<c:if test="${user.modeType == 2}">
						<td>牛牛模式</td>
					</c:if>
					<c:if test="${user.modeType == 3}">
						<td>梭哈模式</td>
					</c:if>
					<c:if test="${user.modeType == 4}">
						<td>红黑模式</td>
					</c:if>
					
					
					<td>${user.roomNum}</td>
					<td>${user.winOrLost}</td>
					<td>${user.totalNum}</td>
						
					
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