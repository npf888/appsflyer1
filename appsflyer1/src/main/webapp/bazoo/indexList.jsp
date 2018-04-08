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
			<form class="form-inline" action="bazoo.do?method=queryIndexList" id="searchForm">

					<label>房间号</label>
					<input class="input-medium" style="width: 100px" name="roomNumber" value="${roomNumber}"></input>
					
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
					<th>房间号</th>
					<th>模式</th>
					<th>当前所有用户</th>
					<th>是否已满</th>
					<th>当前房间运行信息</th>
					<th>操作</th>
					
				</tr>				
			</thead>
			<tbody>				
			 <c:forEach var="bazooRoomVO" items="${list}" varStatus="status">
					
				<tr id="tr_${user.id }">
				
					<td>${status.index+1+ PAGER.start}</td>
					<td>${bazooRoomVO.roomNumber}</td>
					<td>
						<c:if test="${bazooRoomVO.roomNumber.modeType==1}">
								"经典模式"                      <br/>
						</c:if>
						<c:if test="${bazooRoomVO.roomNumber.modeType==2}">
								"牛牛模式"                      <br/>
						</c:if>
						<c:if test="${bazooRoomVO.roomNumber.modeType==3}">
								"梭哈模式"                      <br/>
						</c:if>
						<c:if test="${bazooRoomVO.roomNumber.modeType==4}">
								"单挑模式"                      <br/>
						</c:if>
					</td>
					<td>
						<c:forEach var="playerVO" items="${bazooRoomVO.playerVOS}" varStatus="status">
				       		 [ ${playerVO.passportId }-
				       		 <c:if test="${playerVO.inRoom==0}">
								"已退出"             
							 </c:if>
				       		 <c:if test="${playerVO.inRoom==1}">
								"未退出"               
							 </c:if>
							 ],
						</c:forEach>
					</td>
					<td>${bazooRoomVO.fullOrNot}</td>
					<td>
						<c:forEach var="taskVO" items="${bazooRoomVO.bazooTaskVOS}" varStatus="status">
				       		 [ (房间： ${taskVO.roomNubmer }) - (是否停止：${taskVO.stop }) - (执行时间：${taskVO.timeStr}) - (下一个人：${taskVO.idsStr}) ]
						</c:forEach>
					</td>
					<td>
						<a href="#" onclick="deleteRoom(${bazooRoomVO.playerVOS[0].passportId },${bazooRoomVO.roomNumber})">删除当前房间</a><br/>
					</td>
					
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

function deleteRoom(passportId){
	if(confirm("确定要删除当前 房间吗")){
		$.post("${pageContext.request.contextPath}/bazoo.do?method=deleteL",{passportId:passportId,roomNumber:roomNumber},function(data){
			$('#PAGECONTENT').html(data)
		});
	}
}
</script>