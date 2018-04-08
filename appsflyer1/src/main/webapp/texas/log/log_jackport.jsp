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
.color-red{
background:#ed5565;
color:#fff;
}
.cloro-Green{
background:#18a689;
color:#fff;
}

.cloro-Magenta{
background:#EE82EE;
color:#fff;
}

.cloro-Goldenrod{
background:#f7a54a;
color:#fff;
}

</style>

<div id="ami_main">
	<div class="row-fluid" id="div1">	
			<form class="form-inline" action="log.do?method=jackport&type=${params.type}" method="post" id="searchForm">
			<input type="hidden"  id="d4310" value="${min_date}" class="Wdate"/>
			<input type="hidden"  id="d4313" value="${max_date}" class="Wdate"/>
			开始时间:	<input type="text"  id="d4311" name="start" value="${start_date}" class="Wdate" onClick="WdatePicker({minDate:'#F{$dp.$D(\'d4310\')}',maxDate:'#F{$dp.$D(\'d4312\')}',dateFmt:'yyyy_MM_dd'})" style="width:100px" requiry="true"/>
			截止时间：<input type="text"  id="d4312" name="end" value="${end_date}" class="Wdate"  onClick="WdatePicker({minDate:'#F{$dp.$D(\'d4311\')}',maxDate:'#F{$dp.$D(\'d4313\')}',realDateFmt:'yyyy_MM_dd',dateFmt:'yyyy_MM_dd'})" style="width:100px" requiry="true"/> 

				<button type="button"  style="margin-left:10px" class="btn btn-mini btn-info" onClick="go(1)">检索</button>	
			</form>
			
	</div>
<hr/>
<div class="row-fluid">
	<div class="table-responsive" style="overflow-x:scroll">
		<div class="ami_table_header"><span class="ami_table_header_title">${params.type} 每日总览</span>

		</div>
		<table id="table_report" class="table table-striped table-bordered table-hover dataTables-example">
			<thead>			
				<tr>	
					<!--<th style="position:absolute;background-color:#f1f1f1;height:136px;width:62px;border-right:1px solid #DDD">日期</th>
					--><th>日期</th>
					
					
					<th class="color-red">百家乐彩金返还</th>
					<th class="color-red">德州彩金返还</th>
					<th class="color-red">老虎机彩金返还</th>
					
				</tr>				
			</thead>
			<tbody style="position:relative">		
				
			 <c:forEach items="${sum_daily}" var="one">
				
				<tr id="tr_${user.id }" class="gradeA">
					<!--<td style="position:absolute;border-right:1px solid #DDD;background-color:#f1f1f1">${one.date}</td>-->
					<td>${one.date}</td>
					<td>${one['502']}</td><!-- 百家乐彩金返还 -->
					<td>${one['39']}</td><!--德州彩金返还  -->
					<td>${one['503']}</td><!--老虎机彩金返还  -->
					
					
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