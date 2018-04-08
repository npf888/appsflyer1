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
	
		<div class="ami_table_header"><span class="ami_table_header_title">功能列表</span>
			<span class="ami_table_header_btn">		
			<button class="btn btn-mini btn-info " data-rel="tooltip" data-placement="left" title="" onclick="addFunction();" id="add">添加功能</button>
			</span>
		</div>
		<table id="table_report" class="table table-striped table-bordered table-hover">
			<thead>			
				<tr>
				<th>No.</th>
					<th>功能名称</th>
					<th>ID</th>
					<th>活动类型</th>
					<th>标题</th>
					<th>描述</th>
					<th>活动图片</th>
					<th>开始时间</th>
					<th>结束时间</th>
					
					<th>条件</th>	
					<th>创建时间</th>
					<th>管理</th>		
				</tr>				
			</thead>
			<tbody>				
			 <c:forEach var="func" items="${PAGER.items}" varStatus="status">
					
				<tr id="tr_${func.id }">
					
					<td>${status.index+1+ PAGER.start}</td>
					<c:if test="${func.functionType==1}">
						<td>买一送一</td>
					</c:if>
					
					<td>${func.id}</td>
					<td>${func.functionType}</td>
					<td>${func.title}</td>
					<td>${func.descrip}</td>
					<td><img src="${G_ip}/activity/${func.pic}"/></td> 
					<td>${func.startTime}</td>
					<td>${func.endTime}</td>					
					<td>${func.conditions}</td>
<%-- 				<td><a href="#" onclick="detail(${func.id})">点击查看详情</a></td> --%>
					<td>${func.create_time}</td>
					<td>				
						<div class='hidden-phone visible-desktop btn-group'>						
							<button class='btn btn-mini btn-info' onclick="openframe('function.do?method=updateFunctionPage&id=${func.id }','Edit Function info');"><i class='icon-edit'></i></button>
							<button class='btn btn-mini btn-danger' onclick="doDelete('${func.id }')"><i class='icon-trash'></i></button>						
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

function openframe(url,title)
{
	$('#PAGECONTENT').show();
	$('#div_frame').hide();
	$.ajax({
		  url: url,
		  success: function(data)
			  		{
						$('.ami_Mask').hide();	
						$('#PAGECONTENT').html(data)
					},
		  error:function(data){
			  $('.ami_Mask').hide();	
			 $('#PAGECONTENT').html(data.responseText)
			  
		  
		  	},
		  dataType: 'html'
		});
	//url=geturl(url);
	/* $('#frame').attr('src','');
	$('#page_title_nav').html(title);
	
	$('#div_frame').show();
	$('#PAGECONTENT').hide();
	$('#frame').attr('src',url);
	$('.ami_Mask').hide();	 */ 
}
	/* function geturl(url)
	{
		url+="&random="+Math.random();
		
		return url;
	}
	$('[data-rel=tooltip]').tooltip();
	$('#add').click(function(){	
		openNewWinow(geturl('activity.do?method=toEditActivity&iscancel=1'),'Add Activity');			
	}); */
	function addFunction(){
		openNewWinow(geturl('function.do?method=updateFunctionPage&id=${func.id }'),'Add Function');
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

		bootbox.confirm("Delete this Function?", function(result) {
			if (result) {

				$('.ami_Mask').show();
				var url = "function.do?method=deleteFunction";
				$.post(url, {id:id},function(data) {
					$('.ami_Mask').hide();
					$('#tr_' + id).remove();
					bootbox.alert("删除成功");
				});
			}
		});
	}
	
	function detail(id){
		var url = "activity.do?method=detail&activityId="+id;
		$.get(url, function(data){
			$('.ami_Mask').hide();	
			$('#PAGECONTENT').html(data)					
		});
	}
</script>