<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/WEB-INF/struts-tags.tld" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div id="ami_main">
<div class="row-fluid">	
			<form class="form-inline" action="game.do?method=queryBaccart" id="searchForm">
				<label>Useraccount</label>
				<input  type="text" class="input-medium"  name="useraccount" value="${account }"  id="account" ></input>				
				<label >Username</label>
				<input type="text" class="input-medium" value="${username }" name="username"  ></input>
				<button type="button"  style="margin-left:10px" class="btn btn-mini btn-info" onClick="go(1)">检索</button>
			</form>
</div>	
<hr/>

<div class="row-fluid">
	
		<div class="ami_table_header"><span class="ami_table_header_title">百家乐</span>

		</div>
		<table id="table_report" class="table table-striped table-bordered table-hover">
			<thead>			
				<tr>
				<th>No.</th>
					
					<th>玩家角色ID</th>
					<th>上庄数</th>
					<th>游戏数</th>
					<th> 赢次数</th>
					<th>明灯</th>
					<th>自动</th>
					<th>输次数</th>
					<th>更新时间</th>
					<th>创建时间</th>			
				</tr>				
			</thead>
			<tbody>				
			 <c:forEach var="user" items="${PAGER.items}" varStatus="status">
					
				<tr id="tr_${user.id }">
					
					<td>${status.index+1+ PAGER.start}</td>
					<td>${user.charid}</td>
					<td>${user.bankerNum}</td>
					<td>${user.gameNum}</td>
					<td>${user.winNum}</td>
					<td>${user.beaconNum}</td>
					<td>${user.isAuto}</td>
					<td>${user.lostNum}</td>
					
	                 <td>${user.update_time} </td>
	                 <td>${user.create_time}</td>

				</tr>
				
			  </c:forEach>
					
			</tbody>
		</table>		
		<jsp:include page="/common/page.jsp"></jsp:include>
		
	</div>	
</div>

