<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div id="ami_main">

<hr/>

<div class="row-fluid">
	
		<div class="ami_table_header"><span class="ami_table_header_title">触发条件和奖励列表</span>
			<span class="ami_table_header_btn">		
			<button class="btn btn-mini btn-info " data-rel="tooltip" data-placement="left" title="" id="add">添加</button>
			</span>
		</div>
		<table id="table_report" class="table table-striped table-bordered table-hover">
			<thead>			
				<tr>
					<th>No.</th>
					<th>ID</th>
					<th>标题</th>
					<th>类型</th>
					<th>活动类型id</th>
					<th>管理</th>		
				</tr>				
			</thead>
			<tbody>				
			 <c:forEach var="condition" items="${PAGER.items}" varStatus="status">
					
				<tr id="tr_${condition.id }">
					
					<td>${condition.id}</td>
					<th>${condition.condition_id}</th>
					<td>${condition.title}</td>
					<th>${condition.type}</th>
					<th>${condition.activity_type_id }</th>
					<td>				
						<div class='hidden-phone visible-desktop btn-group'>												
							<button class='btn btn-mini btn-danger' onclick="doDelete('${condition.id }')"><i class='icon-trash'></i></button>						
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
	$('#add').click(function(){	
		openNewWinow(geturl('condition.do?method=toAddCondition&iscancel=1'),'Add Condition');			
	});

	function doDelete(id) {

		bootbox.confirm("Delete this Condition?", function(result) {
			if (result) {

				$('.ami_Mask').show();
				var url = "condition.do?method=doDelete&id=" + id;
				$.get(url, function(data) {
					$('.ami_Mask').hide();
					$('#tr_' + id).remove();
					bootbox.alert(data);
				});
			}
		});
	}
</script>