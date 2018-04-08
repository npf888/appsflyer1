<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/WEB-INF/struts-tags.tld" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" import="com.ami.texas.common.vo.SlotName" %>
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
			<form class="form-inline" action="log.do?method=slotspindetail&type=${params.type}" method="post" id="searchForm">
			<input type="hidden"  id="d4310" value="${min_date}" class="Wdate"/>
			<input type="hidden"  id="d4313" value="${max_date}" class="Wdate"/>
			开始时间:	<input type="text"  id="d4311" name="start" value="${start_date}" class="Wdate" onClick="WdatePicker({minDate:'#F{$dp.$D(\'d4310\')}',maxDate:'#F{$dp.$D(\'d4312\')}',dateFmt:'yyyy_MM_dd'})" style="width:100px" requiry="true"/>
			截止时间：<input type="text"  id="d4312" name="end" value="${end_date}" class="Wdate"  onClick="WdatePicker({minDate:'#F{$dp.$D(\'d4311\')}',maxDate:'#F{$dp.$D(\'d4313\')}',realDateFmt:'yyyy_MM_dd',dateFmt:'yyyy_MM_dd'})" style="width:100px" requiry="true"/> 

			<label>facebook</label>
					<select name="facebook">
						<option
							<c:if test="${params.facebook != '-1'}">
							 selected="selected"
							</c:if>
							value="1">是</option>
						<option 
							<c:if test="${params.facebook == '-1'}">
								selected="selected"
							</c:if>
							value="-1">否</option>
						<option 
							<c:if test="${params.facebook == '2'}">
								selected="selected"
							</c:if>
							value="2">全部</option>
					</select>
				<label>性别</label>
					<select name="sex">
							<option
								<c:if test="${params.sex == '1'}">
								 selected="selected"
								</c:if>
								value="1">男</option>
							<option 
								<c:if test="${params.sex == '0'}">
									selected="selected"
								</c:if>
								value="0">女</option>
							<option 
								<c:if test="${params.sex == '2'}">
									selected="selected"
								</c:if>
								value="2">全部</option>
					</select>
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
					-->
					<th>日期</th>
					<th>横向相加</th>

					<c:forEach items="${slotNames }" var="slotname">
						<th class="cloro-Green">${slotname.langDesc }</th>
					</c:forEach>					
					
					
				</tr>				
			</thead>
			<tbody style="position:relative">		
				
					<%
						
						List<Map<String, Object>> list  = (List<Map<String, Object>>)request.getAttribute("sum_daily");	
						for(Map<String, Object> map:list){
							out.print("<tr  class='gradeA'>");
							out.print("<td>"+map.get("date")+"</td>");
							out.print("<td>"+map.get("total")+"</td>");
								List<SlotName> sns = (List<SlotName>)request.getAttribute("slotNames");	
								for(SlotName sn: sns){
									Long v = 0l;
									if(map.get(""+sn.getId()) != null){
										v=(Long)map.get(""+sn.getId());
									}
									out.print("<td>"+v+"</td>");
								}
							out.print("</tr>");
								
						}
					%>
						
					
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