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
	
		<div class="ami_table_header"><span class="ami_table_header_title">全服奖励列表</span>
			<span class="ami_table_header_btn">		
			<button class="btn btn-mini btn-info " data-rel="tooltip" data-placement="left" title="" onclick="addNewFull();" id="add">添加全服奖励</button>
			</span>
		</div>
		<table id="table_report" class="table table-striped table-bordered table-hover">
			<thead>			
				<tr>
				<th>No.</th>
					<th>标题</th>
					<th>内容</th>
					<th>创建时间</th>
					<th>更新时间</th>
					<th>管理</th>		
				</tr>				
			</thead>
			<tbody>				
			 <c:forEach var="one" items="${PAGER.items}" varStatus="status">
					
				<tr id="tr_${one.id }">
					<td>${status.index+1+ PAGER.start}</td>
					<td>${one.title}</td>
					<td>${one.content}</td>
					<td>${one.create_time }</td>
					<td>${one.update_time }</td>

					<td>				
						<div class='hidden-phone visible-desktop btn-group'>						
							<button class='btn btn-mini btn-info' onclick="openframe('compensation.do?method=toEdit&id=${one.id }&iscancel=1','Edit Activity info');"><i class='icon-edit'></i></button>
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

	$('[data-rel=tooltip]').tooltip();
	/* $('#add').click(function(){	
		openNewWinow(geturl('compensation.do?method=toEdit&iscancel=1'),'Add Compensation');			
	}); */
	function addNewFull(){
		openNewWinow(geturl('compensation.do?method=toEdit&iscancel=1'),'Add Compensation');			
	}

	function edit(id) {
		bootbox.confirm("Are you sure to block this user account?", function(
				result) {
			if (result) {

				$('.ami_Mask').show();
				var url = "app.do?method=doBlock&uuid=" + id;
				$.get(url, function(data) {
					$('.ami_Mask').hide();
					bootbox.alert("	User Blocked!");
				});
			}
		});
	}
	function doDelete(id) {

		bootbox.confirm("Delete this?"+id, function(result) {
			if (result) {
				$('.ami_Mask').show();
				$.post("${G_url}api/compensation/delete.json", {id:id})
				.done(function(data){
					console.log(data);
					bootbox.alert(data);
				});	
				};
			});
	}
</script>