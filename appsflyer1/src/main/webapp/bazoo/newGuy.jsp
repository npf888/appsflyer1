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
	
<hr/>

<div class="row-fluid">
	

		</div>
		<table id="table_report" class="table table-striped table-bordered table-hover">
			<thead>			
				<tr>
					<th>No.</th>
					<th>模式</th>
					<th>步数</th>
					<th>人数</th>
				</tr>				
			</thead>
			<tbody>				
			 <c:forEach var="newguy" items="${newGuys}" varStatus="status">
					
				<tr >
				
					<td>${status.index+1}</td>
					<td>
					<c:if test="${newguy.type == 1}">
						<font color="green">经典模式</font>
					</c:if>
					<c:if test="${newguy.type == 2}">
						<font color="#D00000">牛牛模式</font>
					</c:if>
					<c:if test="${newguy.type == 3}">
						<font color="#C71585">梭哈模式</font>
					</c:if>
					<c:if test="${newguy.type == 4}">
						<font color="#202020">红黑模式</font>
					</c:if>
					
					</td>
					<td>${newguy.process}</td>
					<td>${newguy.num}</td>
				</tr>
				
			  </c:forEach>
					
			</tbody>
		</table>		
		<jsp:include page="/common/page.jsp"></jsp:include>
		
	</div>	
<div id="ami_newwindow" style="display: none;">

</div>
