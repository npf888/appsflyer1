
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/WEB-INF/struts-tags.tld"%>
<div id="ami_main">
	<div class="row-fluid" id="div1">	
			<form class="form-inline" action="${pageContext.request.contextPath}/goldChange.do?method=list" id="searchForm">

					<label>日期：</label>
			  		<input type="text" readonly="readonly"  name="date" id="date" value="${date}"  class="Wdate"  onClick="WdatePicker({startDate:'%y-%M-01 00',dateFmt:'yyyy_MM_dd'})" /> 
					
					<button type="button"  style="margin-left:10px" class="btn btn-mini btn-info" onClick="go()">检索</button>
			</form>
	</div>
<hr/>
	<div class="row-fluid">
		<div class="row-fluid" style="background-color: #FFFFFF">
			<div class="span8">
				<div class="ami_table_header">
					<span class="ami_table_header_title">金币变化</span> 
					<span
						class="ami_table_header_btn">
					</span>
				</div>

				<table id="table_bug_report"
					class="table table-striped table-bordered table-hover">
					<thead>
						<tr class="arrth">
							<th class="hidden-xs arr">id</th>
							<th class=" arr">代理商ID</th>
							<th class="hidden-xs arr">原因</th>
							<th class="hidden-xs arr">原因内容</th>
							<th class=" arr">增加或者减少</th>
							<th class=" arr">剩余金币</th>
							<th class="hidden-xs arr">创建时间</th>
							<th class="visible-xs">操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${list}" var="agent">
							<tr id="tr_${agent.id}">
								<td class="hidden-xs arr">${agent.id}</td>
								<td class="arr">${agent.passportId}</td>
								<td class="hidden-xs arr">${agent.reason}</td>
								<td class="hidden-xs arr">${agent.param}</td>
								<td class="arr">${agent.goldDelta}</td>
								<td class="arr">${agent.goldLeft}</td>
								<td class="hidden-xs arr">${agent.createTime}</td>
								<td class="visible-xs">
									<button class="btn btn-primary btn-open">查看</button>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

			</div>
			<!--/span-->
		</div>
		<!--/row-->
	</div>
</div>

<div id="ami_newwindow" style="display: none;"></div>
<div class="more-box">
	<div class="more-bg"></div>
	<div class="more-inner">
		<ul>
			
		</ul>
	</div>
</div>
<script>
$('.btn-open').click(function(){
	var len=$('.arrth').find('th').length;
	var str='';
	$('.more-inner').empty();
	for(b=0;b<len;b++){
		str+="<li><b>"+$('.arrth').find('th').eq(b).text()+"</b>:<span>"+$(this).parents().siblings('.arr').eq(b).text()+"</span></li>"
	}
	
	$('.more-inner').append(str);
	$('.more-box').show();
});

$('.more-bg').click(function(){
	$('.more-box').hide();
})
function go()
{
	$('.ami_Mask').show();
	var params = $('#searchForm').formSerialize();
	
	var url = $('#searchForm').attr('action') ;
	$.post(url,params,function (data){
	
	$('#PAGECONTENT').html(data);
	
	$('.ami_Mask').hide();
	
	});
}

</script>
