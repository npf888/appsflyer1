<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/WEB-INF/struts-tags.tld" %>

		<hr/>
		<form class="form-inline" action="code.do?method=queryCode" id="searchForm">
		兑换码：<input  type="text" class="input-medium"  name="code" value="${code}"  id="code" ></input>
			<button type="button"  style="margin-left:10px" class="btn btn-mini btn-info" onClick="goSelect()">检索</button>
		</form>

		<div id="ami_main">
		<div class="row-fluid">
		<div class="ami_table_header"><span class="ami_table_header_title">兑换码列表</span>
			<span class="ami_table_header_btn">		
			<button class="btn btn-mini btn-info " data-rel="tooltip" data-placement="left" title="" onclick="addCode('code.do?method=editCode');" id="add">添加兑换码</button>
			</span>
			<span class="ami_table_header_btn">		
			<a class="btn btn-mini btn-info "  href="code.do?method=toExcel" >导出未使用兑换码</a>
			</span>
		</div>
		<table id="table_report" class="table table-striped table-bordered table-hover">
			<thead>			
				<tr>
					<th>ID</th>
					<th>兑换码</th>
					<th>赠送的筹码</th>
					<th>结束时间</th>	
					<th>是否有效</th>
					<th>updateTime</th>
					<th>createTime</th>
					<th>codeType</th>	
					<th>管理</th>
				</tr>				
			</thead>
			<tbody>				
				  <c:forEach var="one" items="${PAGER.items}" varStatus="status">
					
				<tr id="tr_${one.id }">
					<td>${one.id}</td>
					<td>${one.conversionCode}</td>
					<td>${one.gold}</td>
					<td>${one.endTime}</td>
						<c:if test="${one.isdelete == 0}">
						<td>
							可用
						</td>
						</c:if>
						<c:if test="${one.isdelete != 0}">
						<td style="color:red;">
							不可用
						</td>
						</c:if>
					<td>${one.updateTime}</td>
					<td>${one.createTime}</td>
						<c:if test="${one.codeType == 0}">
						<td>
							普通
						</td>
						</c:if>
						<c:if test="${one.codeType == 1}">
						<td style="color:blue;">
							特殊
						</td>
						</c:if>
					<td>
						<div class='hidden-phone visible-desktop btn-group'>
						<button class='btn btn-mini btn-info' onclick="addCode('code.do?method=editCode&id=${one.id }','Edit Code info');">
						<i class='icon-edit'></i></button>						
							<button class='btn btn-mini btn-danger' onclick="doDelete('${one.conversionCode }','${one.id }')"><i class='icon-trash'></i></button>						
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
	$('#searchForm').attr("action","code.do?method=queryCode");
});
/* function addCode(){
	openNewWinow(geturl('code.do?method=editCode'),'Add Activity');
} */
function addCode(url,title)
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
}

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

function doDelete(code,id)
{
	
	bootbox.confirm("Delete this code?", function(result) {
		if(result) {
			
			$('.ami_Mask').show();
			var url="code.do?method=toDelete&id="+id+"&code="+code;
			$.get(url, function(data){
				$('.ami_Mask').hide();
				sleep(1500);
				addCode('code.do?method=queryCode');
			});						
		}
	});	
}
function sleep(numberMillis) { 
	var now = new Date(); 
	var exitTime = now.getTime() + numberMillis; 
	while (true) { 
		now = new Date(); 
		if (now.getTime() > exitTime) 
			return; 
	} 
}
function goSelect(){
	go();
}
</script>