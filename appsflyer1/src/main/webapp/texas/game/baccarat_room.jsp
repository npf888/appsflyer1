<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/WEB-INF/struts-tags.tld" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div id="ami_main">
<div class="row-fluid">	
			<form class="form-inline" action="baccarat.do?method=queryBaccartRoom" id="searchForm">
				<label>Useraccount</label>
				<input  type="text" class="input-medium"  name="useraccount" value="${account }"  id="account" ></input>				
				<label >Username</label>
				<input type="text" class="input-medium" value="${username }" name="username"  ></input>
				<button type="button"  style="margin-left:10px" class="btn btn-mini btn-info" onClick="go(1)">检索</button>
			</form>
</div>	
<hr/>

<div class="row-fluid">
	
		<div class="ami_table_header"><span class="ami_table_header_title">百家乐房间列表</span>

		</div>
		<table id="table_report" class="table table-striped table-bordered table-hover">
			<thead>			
				<tr>
				<th>No.</th>
					
					<th>房间ID</th>
					<th>库存</th>
					<th>彩金</th>
					<th>房间状态</th>

					<th>更新时间</th>
					<th>创建时间</th>			
					<th>管理</th>
				</tr>				
			</thead>
			<tbody>				
			 <c:forEach var="one" items="${PAGER.items}" varStatus="status">
					
				<tr id="tr_${one.roomid }">
					
					<td>${status.index+1+ PAGER.start}</td>
					<td>${one.roomid}</td>
					<td>${one.stock }</td>
					<td>${one.jackpot}</td>
					<td>
					<c:if test = "${one.closed==0}">开启&nbsp;&nbsp;<button class="" onclick="changeStatus('${one.roomid}',1)">关闭</button></c:if>
					<c:if test = "${one.closed==1}">关闭&nbsp;&nbsp;<button class="" onclick="changeStatus('${one.roomid}',0)">开启</button></c:if></td>

					
	                 <td>${one.update_time} </td>
	                 <td>${one.create_time}</td>
					 <td><button id="${one.roomid}" data-toggle="modal" data-target="#myModal" stock="${one.stock}" jackpot="${one.jackpot}" class="edit-button"> Edit </button></td>
				</tr>
				
			  </c:forEach>
					
			</tbody>
		</table>		
		<jsp:include page="/common/page.jsp"></jsp:include>
  <div id="myModal" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title" id="title"></h4>
      </div>
      <div class="modal-body">
      <form class="form-horizontal">
      	<div class="control-group">
        	<label class="control-label" for="stock">更改库存:  </label>
        	<div class="controls">
        		<input type="text"  name="stock" id="stock">
        	</div>
        </div>
        <div class="control-group">
        	<label class="control-label"  for="jackpot">更改彩金： </label>
        	<div class="controls">
        		<input type="text" name="jackpot" id="jackpot">
        	</div>
        </div>
       </form>
      </div>
      <div class="modal-footer">
      	<button type="button" class="btn btn-primary" id="save">Save </button>
        <button type="button" class="btn btn-default" id="close" data-dismiss="modal">Cancel</button>
      </div>
    </div>

  </div>
</div>
	</div>	
</div>

<script>
function changeStatus(roomid,status){
	var state = "开启";
	
	if(status == 1)
	{state = "关闭";}
	console.log(roomid+"    "+status);
	bootbox.confirm("确定"+state+"百家乐房间？", function(result) {
		if(result) {
			
			$('.ami_Mask').show();
			var url = "baccarat.do?method=update&roomid="+roomid+"&closed="+status;
			$.post(url, function(data){
				$('.ami_Mask').hide();
				bootbox.alert(data);
				console.log(data);
			});						
		}
	});		
	
}
$('.edit-button').click(function(){
	
	var roomid = $(this).attr('id');
	var stock_current = $(this).attr('stock');
	var jackpot_current = $(this).attr('jackpot');
	
	$('#stock').val(stock_current);
	$('#jackpot').val(jackpot_current);
	
	$('#title').text("修改百家乐房间信息   - 房间号： "+roomid);
	$('#save').click(function(){
		
		$('#close').trigger("click");
		
		var stock_new = $('#stock').val();
		var jackpot_new = $('#jackpot').val();
		if(stock_current == stock_new && jackpot_current == jackpot_new)
		{
			return;
		}
		else{
			var url = "baccarat.do?method=update&roomid="+roomid+"&stock="+stock_new+"&jackpot="+jackpot_new;
			$.post(url, function(data){
				$('.ami_Mask').hide();
				bootbox.alert(data);
				
				console.log(data);
			});	
		}
	});
});
</script>

