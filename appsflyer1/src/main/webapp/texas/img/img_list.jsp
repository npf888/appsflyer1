<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div id="ami_main">
<div class="row-fluid">	
			<form class="form-inline" action="activity.do?method=queryActivity" id="searchForm">
				<label>Useraccount</label>
				<input  type="text" class="input-medium"  name="useraccount" value="${account }"  id="account" ></input>				
				<label >Username</label>
				<input type="text" class="input-medium" value="${username }" name="username"  ></input>
				<button type="button"  style="margin-left:10px" class="btn btn-mini btn-info" onClick="go(1)">检索</button>
			</form>
</div>	
<hr/>

<div class="row-fluid">
	
		<div class="ami_table_header"><span class="ami_table_header_title">活动列表</span>
			<span class="ami_table_header_btn">		
			<button class="btn btn-mini btn-info " data-rel="tooltip" data-placement="left" title="" id="add">添加活动</button>
			</span>
		</div>
		<table id="table_report" class="table table-striped table-bordered table-hover">
			<thead>			
				<tr>
				<th>No.</th>
					<th>Image Preview</th>
					<th>标题</th>

				</tr>				
			</thead>
			<tbody>				
			 <c:forEach var="activity" items="${PAGER.items}" varStatus="status">
					
				<tr id="tr_${activity.id }">
					
					<td>${status.index+1+ PAGER.start}</td>
					<td><a onclick="openNewWinow('activity.do?method=loadByID&id=${activity.id}')">${activity.id}</a></td>


					<td>				
						<div class='hidden-phone visible-desktop btn-group'>						
							<button class='btn btn-mini btn-info' onclick="openframe('activity.do?method=toEditActivity&id=${activity.id }&iscancel=1','Edit Activity info');"><i class='icon-edit'></i></button>
							<button class='btn btn-mini btn-danger' onclick="doDelete('${activity.id }')"><i class='icon-trash'></i></button>						
						</div>
					</td>
				</tr>
				
			  </c:forEach>
					
			</tbody>
		</table>		
		<jsp:include page="/common/page.jsp"></jsp:include>
		
	</div>	
</div>