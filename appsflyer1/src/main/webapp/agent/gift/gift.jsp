<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/WEB-INF/struts-tags.tld" %>
<div id="ami_main">
<div class="row-fluid">	
			<form class="form-inline" action='
			<c:if test="${sor=='send' }">
				giftAll.do?method=curDayAllSendGift&sendId=${passportId }
			</c:if>
			
			<c:if test="${sor=='receive' }">
				giftAll.do?method=curDayAllReceiveGift&recId=${passportId }
			</c:if>
			
			' id="searchForm">
				<label>Useraccount</label>
				<input  type="text" class="input-medium"  name="useraccount" value="${account}"  id="account" ></input>				
				<button type="button"  style="margin-left:10px" class="btn btn-mini btn-info" onClick="go(1)">检索</button>
				<button type="button"  style="margin-left:10px" class="btn btn-mini btn-info" onClick="openNewWinow('agent.do?method=list','全部币商')">返回</button>
			</form>
</div>	
<hr/>

   <div class="table-responsive">

	
		<div class="ami_table_header"><span class="ami_table_header_title">User List</span>

		</div>
		<table id="table_report"class="table table-striped table-bordered table-hover dataTables-example" >
			<thead>			
				<tr>
				<th>No.</th>
					<th>charId</th>
					<th>sendId</th>
					<th>sendName</th>
					<th>recId</th>
					<th>recName</th>	
					<th>title</th>
					<th>content</th>
					<th>mailType</th>
					<th>mailStatus</th>
					<th>hasAttachment</th>
					<th>attachmentPack</th>
					<th>updateTime</th>
					<th>createTime</th>
					<th>deleted</th>
					<th>head</th>
				</tr>				
			</thead>
			<tbody>				
			 <c:forEach var="mail" items="${PAGER.items}" varStatus="status">
					
				<tr id="tr_${mail.id }">
					
					<td>${status.index+1+ PAGER.start}</td>
					<td>${mail.charId}</td>
					<td>${mail.sendId}</td>
	                 <td>${mail.sendName} </td>
	                 <td>${mail.recId}</td>
	                 <td>${mail.recName }</td>
	                 <td>${mail.title}</td>
	                 <td>${mail.content}</td>
	                 <td>${mail.mailType}</td>
	                 <td>${mail.mailStatus}</td>
	                 <td>${mail.hasAttachment }</td>
	                 <td>${mail.attachmentPack}</td>
	                 <td>${mail.updateTime}</td>
	                 <td>${mail.createTime}</td>
	                 <td>
						 <c:if test="${mail.deleted == 1}">
							已删除
						 </c:if>
						 <c:if test="${mail.deleted == 0}">
							未删除
						 </c:if>
					 </td>
	                 <td>${mail.head}</td>
					 
					 
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

/**
 * 在本页面打开新的窗口 支持回退
 */
function openNewWinow(url, title) {
	$('.ami_Mask').show();
	//主窗口隐错
	$('#ami_main').hide();
	$('#ami_newwindow').show();
	$('.more-box').hide();
	//找到新窗口
	$.get(url, function(data) {

		$('#ami_newwindow').html(data);
		$('.ami_Mask').hide();

	});
	$('#page_title').html(title);
	$('#page_title_nav').html(title);
}
</script>