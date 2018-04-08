<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<style>
.bor{border:1px dashed #F00;width:1000px;height:150px;margin-top:10px} 
</style>

 <c:forEach var="bazooRoomVO" items="${list}" varStatus="status">
	<div class="bor" style="color:blue">
	<a href="#" onclick="deleteRoom(${bazooRoomVO.playerVOS[0].passportId },${bazooRoomVO.roomNumber})">删除当前房间</a><br/>
                          当前名称:              
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
		<c:if test="${bazooRoomVO.roomNumber.modeType !=1 && bazooRoomVO.roomNumber.modeType !=2 && bazooRoomVO.roomNumber.modeType !=3  && bazooRoomVO.roomNumber.modeType !=4}">
				"错误模式"                      <br/>
		</c:if>
                           当前房间：${bazooRoomVO.roomNumber.modeType}_${bazooRoomVO.roomNumber.pubOrPri}_${bazooRoomVO.roomNumber.bet}_${bazooRoomVO.roomNumber.roomNum}<br/>
		当前状态：${bazooRoomVO.state}			 <br/>
                           是否已满：${bazooRoomVO.fullOrNot}       <br/>

		用户IDS::
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
			<c:forEach var="taskVO" items="${bazooRoomVO.bazooTaskVOS}" varStatus="status">
	       		 [ (房间： ${taskVO.roomNubmer }) - (是否停止：${taskVO.stop }) - (执行时间：${taskVO.timeStr}) - (下一个人：${taskVO.idsStr}) ]
			</c:forEach>
				
	</div>
 </c:forEach>
 
 
 
 
<script type="text/javascript">
function shutDownThread(passportId){
	if(confirm("确定要关闭"+passportId+"用户线程吗")){
		$.post("${pageContext.request.contextPath}/bazoo.do?method=shutDownThread",{passportId:passportId},function(data){
			$('#PAGECONTENT').html(data)
		});
	}
}
function deleteRoom(passportId){
	if(confirm("确定要删除当前 房间吗")){
		$.post("${pageContext.request.contextPath}/bazoo.do?method=delete",{passportId:passportId,roomNumber:roomNumber},function(data){
			$('#PAGECONTENT').html(data)
		});
	}
}
</script>
