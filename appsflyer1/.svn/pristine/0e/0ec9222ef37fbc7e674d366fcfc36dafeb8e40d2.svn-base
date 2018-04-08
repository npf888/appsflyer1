<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/WEB-INF/struts-tags.tld" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<base href="${pageContext.request.contextPath}/" />
<link rel="stylesheet" type="text/css" href="/js/Clockpicker/bootstrap-clockpicker.min.css">
    <!-- Data Tables -->
    <link href="css/dataTables/dataTables.bootstrap.css" rel="stylesheet">
    <link href="css/dataTables/dataTables.responsive.css" rel="stylesheet">
    <link href="css/dataTables/dataTables.tableTools.min.css" rel="stylesheet">
<style>
.dataTables_wrapper {
    padding-bottom: 30px;
}	
.dataTables_length {
    float: left;
}


</style>

<div id="ami_main">
	<div class="row-fluid" id="div1">	
			
			
	</div>
<hr/>
<div class="row-fluid">
	<div class="table-responsive" style="overflow-x:scroll">
		<form class="form-inline" action="log.do?method=slotwinner" method="post" id="searchForm">
			时间：<input type="text"   name="date" value="${date}" class="Wdate"  onClick="WdatePicker({realDateFmt:'yyyy_MM_dd',dateFmt:'yyyy_MM_dd'})" style="width:100px" requiry="true"/>
			用户：<input type="text"   name="winner" value="${winner}"/> 
				<button type="button"  style="margin-left:10px" class="btn btn-mini btn-info" onClick="go(1)">检索</button>	
		</form>
		<div class="ami_table_header"><span class="ami_table_header_title">${slotwinnerTime} 今日总览</span>

		</div>
		<table id="table_report" class="table table-striped table-bordered table-hover dataTables-example">
			<thead>			
				<tr>	
					<!--<th style="position:absolute;background-color:#f1f1f1;height:136px;width:62px;border-right:1px solid #DDD">日期</th>
					-->
					   <th>UID</th>
					   <th>Win</th>
					   <th>Spin</th>
					   <th>WinRate</th>
					   <th>BigWinRate</th>
					   <th>AvgBet</th>
					   <th>Payout</th>
					  
				</tr>				
			</thead>
			<tbody style="position:relative">		
				
			 <c:forEach items="${listData}" var="MapData">
			 <tr>
			 
			 <td> ${MapData.account_id}</td>
			 <td> ${MapData.win11}</td>
			 <td> ${MapData['win8']}</td>
			 <td><fmt:formatNumber type="number" value="${MapData['win9']*0.1*10/MapData['win8']}" maxFractionDigits="2"/> </td>
			 <td><fmt:formatNumber type="number" value="${MapData.win10*0.1*10/MapData.win8}" maxFractionDigits="2"/></td>
			 <td><fmt:formatNumber type="number" value="${MapData.win12*0.1*10/MapData.win8}" maxFractionDigits="2"/> </td>
			 <td><fmt:formatNumber type="number" value="${MapData.win11*0.1*10/MapData.win12}" maxFractionDigits="2"/></td>
			
			
			 </tr>
			  </c:forEach>
					
			</tbody>
		</table>		
	</div>
	</div>	
</div>

<div id="ami_newwindow" style="display: none;">

</div>

<script charset="utf-8" >
$(document).ready(function() {
    $('.dataTables-example').DataTable({
        "dom": 'lTfigpt',
        "order":[[0, "desc"]],
        buttons: [
                  'copy','excel'
              ],
        "tableTools": {
            "sSwfPath": "js/dataTables/swf/copy_csv_xls_pdf.swf"
        }
    });
});

function go(page)
{
	$('.ami_Mask').show();
	var params = $('#searchForm').formSerialize();
	
	var url = $('#searchForm').attr('action') +"&currentPage="+page;
	$.post(url,params,function (data){
	
	$('#PAGECONTENT').html(data);
	
	$('.ami_Mask').hide();
	
	});
}
</script>