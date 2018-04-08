<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/WEB-INF/struts-tags.tld" %>
<div id="ami_main">
<script type="text/javascript" src="js/jquery-1.9.1.min.js" charset="UTF-8"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/bootstrap-datepicker.min.js" charset="UTF-8"></script>

<div class="row-fluid">	
			<form class="form-inline" action="game.do?method=queryNews" id="searchForm">
				<label>名称</label>
				<input  type="text" class="input-medium"  id="clubName" ></input>				
				<label >创建时间</label>
				<input type="text" id="from"></input>
				<span>到</span>
				<input type="text" class="input-medium" id="to"></input>
				
				<select id="level">
				<option value="0">忽略</option>
				<option value="1">1-5级</option>
				<option value="2">6-10级</option>
				<option value="3">11-15级</option>
				<option value="4">16-20级</option>
				<option value="5">21-25级</option>
				</select>

				<select id="money">
				<option value="0">忽略</option>
				<option value="1">0-50k</option>
				<option value="2">50k-500k</option>
				<option value="3">500k-1M</option>
				<option value="4">＞1M</option>
				</select>
				
				<select id="hueyue">
				<option value="0">忽略</option>
				<option value="1">0-500</option>
				<option value="2">500-1000</option>
				<option value="3">1000-2000</option>
				</select>
				
				<button type="button"  style="margin-left:10px" class="btn btn-mini btn-info" onClick="search()">检索</button>
			</form>
</div>	
<hr/>

<div class="row-fluid">
	
		<div class="ami_table_header"><span class="ami_table_header_title">俱乐部系统</span>

		</div>
		<table id="table_report" class="table table-striped table-bordered table-hover">
			<thead>			
				<tr>
<!-- 				<th>序号</th> -->
					<th>名称</th>
					<th>类型</th>
					<th>等级</th>
					<th>经验</th>
					<th>本期捐献</th>
					<th>总贡献</th>
					<th>活跃</th>
					<th>创建时间</th>
					<th>段位限制</th>	
					<th>成员数量</th>	
					<th>成员最大数量</th>	
					<th>操作</th>	
				</tr>				
			</thead>
			<tbody>				
			 <c:forEach var="ci" items="${PAGER.items}" varStatus="status">
					
				<tr id="${ci.id }" >
					<td>${ci.name}</td>
					<td>${ci.club_type}</td>
					<td>${ci.level}</td>
					<td>${ci.exp}</td>
					<td>${ci.money}</td>
					<td>${ci.totalGongXian}</td>
					<td>${ci.huoyue}</td>
	                <td>${ci.createTime}</td>
	                <td>${ci.duanweiLimit}</td>
	                <td>${ci.memberNum}</td>
	                <td>${ci.memberMax}</td>
					<td><a onclick="openNewWinow('club.do?method=membersInfo&clubId=${ci.id}')">查看成员</a></td>
				</tr>
			  </c:forEach>
			</tbody>
		</table>		
		<jsp:include page="/common/page.jsp"></jsp:include>
		
	</div>	
</div>
<div id="ami_newwindow" style="display: none;">

</div>

<script>
$('#from').datepicker({format:'yyyy-mm-dd'});
$('#to').datepicker({format:'yyyy-mm-dd'});
function search()
{
	$.post('club.do?method=query&clubName='+$('#clubName').val()
								+'&from='+$('#from').val()
								+'&to='+$('#to').val()
								+'&level='+$('#level').val()
								+'&money='+$('#money').val()
								+'&hueyue='+$('#hueyue').val(), function(data) {
		$('#PAGECONTENT').html(data);
	});
}
</script>