<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/WEB-INF/struts-tags.tld" %>

<hr/>
			<form class="form-inline" action="feedback.do?method=query&user_id=${user_id }&status=${status}" id="searchForm">
			状态:	<select id="status_p">
					<option value="" <c:if test="${status==''}">selected = "selected"</c:if> >全部</option>
					<option value="0" <c:if test="${status==0}">selected = "selected"</c:if> >新回复</option>
					<option value="1" <c:if test="${status==1}">selected = "selected"</c:if> >其他</option>
					<option value="2" <c:if test="${status==2}">selected = "selected"</c:if> >已回复</option>
				</select>
				<button type="button"  style="margin-left:10px" class="btn btn-mini btn-info" onClick="goSelect()">倒序检索</button>
			</form>

<div id="ami_main">
<div class="row-fluid">
	<span class="ami_table_header_title">Feedback List</span>

		<table id="table_report" class="table table-striped table-bordered table-hover">
			<thead>			
				<tr>
					<th>ID</th>
					<th>App ID</th>
					<th>User ID</th>
					<th>Mac ID</th>	
					<th style="width:20%">Message</th>
					<th>Feedback Type</th>
					<th>image</th>
					
					<th>Status</th>	
					<th>channel</th>
					<th>Create Time</th>

					<th>管理</th>
				</tr>				
			</thead>
			<tbody>				
				  <c:forEach var="one" items="${PAGER.items}" varStatus="status">
					
				<tr id="tr_${one.id }">
										
					<td><a onclick="openNewWinow('feedback.do?method=loadByID&iscancel=1&id=${one.id}&currentPage=${currentPage }')">${one.id}</a></td>
					<td><a onclick="openNewWinow('feedback.do?method=query&appid=${one.app_id}')">${one.app_id}</a></td>
	                 <td><a onclick="openNewWinow('feedback.do?method=query&user_id=${one.user_id}&appid=${one.app_id}')">${one.user_id}</a></td>
	                 <td><a onclick="openNewWinow('feedback.do?method=query&mac_id=${one.mac_id}&appid=${one.app_id}')">${one.mac_id}</a></td>
	                 <td><a onclick="openNewWinow('feedback.do?method=loadByID&iscancel=1&id=${one.id}&currentPage=${currentPage }')">${one.message}</a></td>
	                 <td>${one.question_type}</td>
	                                 
	                 <td><a href="${one.image}">${one.image}</a></td>
	                 <c:if test="${one.status==0}">
	                 	<td>新留言    <a onclick="openNewWinow('feedback.do?method=loadByID&iscancel=1&id=${one.id}&currentPage=${currentPage }')">回复</a></td>
	                 </c:if>
	                 <c:if test="${one.status==1}">
	                 	<td>新回复    <a onclick="openNewWinow('feedback.do?method=loadByID&iscancel=1&id=${one.id}&currentPage=${currentPage }')">回复</a></td>
	                 </c:if>
	                 <c:if test="${one.status==2}">
	                 	<td>已回复</td>
	                 </c:if>
	                 <td>${one.channel}</td>
	                 <td>${one.create_time}</td>
	                
					<td>
						<div class='hidden-phone visible-desktop btn-group'>						
							<button class='btn btn-mini btn-danger' onclick="doDelete('${one.id }')"><i class='icon-trash'></i></button>						
						</div>
					</td>
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
$(function(){
	var user_id = '${user_id }';
	var status = '${status }';
	$('#searchForm').attr("action","feedback.do?method=query&user_id="+user_id+"&status="+status);
});
	function openframe(url,title)
	{
		url=geturl(url);
		$('#frame').attr('src','');
		$('#page_title_nav').html(title);
		
		$('#div_frame').show();
		$('#PAGECONTENT').hide();
		$('#frame').attr('src',url);
		$('.ami_Mask').hide();	
	}

function doDelete(id)
{
	
	bootbox.confirm("Delete this Feedback?", function(result) {
		if(result) {
			
			$('.ami_Mask').show();
			var url="feedback.do?method=delByID&id="+id;
			$.get(url, function(data){
				
				$('.ami_Mask').hide();
				$('#tr_'+id).remove();
				bootbox.alert(data);	
				
			});						
		}
	});	
}
function goSelect(){
	var url = $('#searchForm').attr('action');
	if(url.substr(url.length-1,url.length) == "="){
		url=url+$("#status_p").val();
		$('#searchForm').attr('action',url);
	}else{
		url=url.substr(0,url.length-1)+$("#status_p").val();
		$('#searchForm').attr('action',url);
	}
	go();
}
</script>