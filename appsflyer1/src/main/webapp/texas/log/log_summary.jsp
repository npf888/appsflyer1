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
<hr/>
<div class="row-fluid">
	<div class="table-responsive" style="overflow-x:scroll">
		<div class="row-fluid" id="div1">	
				 <form class="form-inline" action="log.do?method=summary&type=${params.type}" method="post" id="searchForm">
			<%--	<input type="hidden"  id="d4310" value="${min_date}" class="Wdate"/>
				<input type="hidden"  id="d4313" value="${max_date}" class="Wdate"/>
				开始时间:	<input type="text"  id="d4311" name="start" value="${start_date}" class="Wdate" onClick="WdatePicker({minDate:'#F{$dp.$D(\'d4310\')}',maxDate:'#F{$dp.$D(\'d4312\')}',dateFmt:'yyyy_MM_dd'})" style="width:100px" requiry="true"/>
				截止时间：<input type="text"  id="d4312" name="end" value="${end_date}" class="Wdate"  onClick="WdatePicker({minDate:'#F{$dp.$D(\'d4311\')}',maxDate:'#F{$dp.$D(\'d4313\')}',realDateFmt:'yyyy_MM_dd',dateFmt:'yyyy_MM_dd'})" style="width:100px" requiry="true"/> 
 --%>	
 		开始时间:	<input type="text"   name="start" value="${start_date}" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy_MM_dd'})" style="width:100px" requiry="true"/>
		截止时间：<input type="text"  name="end" value="${end_date}" class="Wdate"  onClick="WdatePicker({dateFmt:'yyyy_MM_dd'})" style="width:100px" requiry="true"/> 
					<button type="button"  style="margin-left:10px" class="btn btn-mini btn-info" onClick="goPage(1)">检索</button>	
				</form>
				
		</div>
		<div class="ami_table_header"><span class="ami_table_header_title">${params.type} 每日总览</span>
		<span class="ami_table_header_title"> LTV : ${LTV }</span>
		</div>
		<table id="table_report" class="table table-striped table-bordered table-hover dataTables-example">
			<thead>			
				<tr>	
					<!--<th style="position:absolute;background-color:#f1f1f1;height:136px;width:62px;border-right:1px solid #DDD">日期</th>
					--><th>日期</th>
					   <th>DAU</th>
					   <th>DNU</th>
					   <th>ReturningUser</th>
					   <!-- <th>次留</th> -->
					   <th>Income</th>
					   <th>Net Income</th>
					   <th>ARPDAU</th>
					   <th>Pay Rate</th>
					   <th>付费用户数</th>
					   <th>订单数</th>
					   <th>首付费人数</th>
					   <th>Spin</th>

				</tr>				
			</thead>
			<tbody style="position:relative">		
				
			 <c:forEach items="${sum_daily}" var="one">
				
				<tr id="tr_${user.id }" class="gradeA">
					<!--<td style="position:absolute;border-right:1px solid #DDD;background-color:#f1f1f1">${one.date}</td>-->
					<td>${one.date}</td>
					
					<td>
						<c:if test="${one['1'] == null }">0</c:if>${one['1']}</td><!--每日登录的用户(DAU)  -->
					<td>
						<c:if test="${one['2'] == null }">0</c:if>${one['2']}</td><!--每日新注册用户(DNU)  -->
					<td>${one['1']-one['2']}</td><!--每日登录的老用户  -->
					
					<!-- <td>${one['107'] }</td>次留 -->
					<td><fmt:formatNumber type="number" value="${one['3']/100}" maxFractionDigits="2"/></td><!--income   -->
					<td><fmt:formatNumber type="number" value="${one['3']/100*0.7}" maxFractionDigits="2"/></td><!-- net icome -->
					
					<c:choose>
						<c:when test="${one['1'] ==0 || one['1']==null}">
							<td>0</td>
						</c:when>
						<c:otherwise>
							<td><fmt:formatNumber type="number" value="${one['3']/100*0.7/one['1']}" maxFractionDigits="2"/></td><!-- ARPDAU  -->
						</c:otherwise>
					</c:choose>
					
					<td>${one.pr }</td><!-- Pay Rate -->
					<!-- -------------------------------------------------------- -->
					<c:choose>
						<c:when test="${one['5'] ==0 || one['5']==null}">
							<td>0</td>
						</c:when>
						<c:otherwise>
							<td><fmt:formatNumber type="number" value="${one['5'] }" maxFractionDigits="2"/></td><!--付费用户   -->
					   </c:otherwise>
					</c:choose>
					
					<td><fmt:formatNumber type="number" value="${one['4']}" maxFractionDigits="2"/></td><!--订单数  -->
					<td><fmt:formatNumber type="number" value="${one['6']}" maxFractionDigits="2"/></td><!-- 首付费人数  -->
					
					<!-- -------------------------------------------------------- -->
					 
					<td>
						平均spin: <fmt:formatNumber type="number" value="${one['8']*0.1*10 /32 }" maxFractionDigits="2"/> 
					
					
						<c:choose>
							<c:when test="${one['8'] ==0  || one['8']==null}">
					     	  赢率：<fmt:formatNumber type="number" value="0" maxFractionDigits="2"/> 
						            大赢率：<fmt:formatNumber type="number" value="0" maxFractionDigits="2"/>
						            平均消耗：<fmt:formatNumber type="number" value="0" maxFractionDigits="2"/> 
							</c:when>
							<c:otherwise>
					     	  赢率：<fmt:formatNumber type="number" value="${one['9']*0.1*10 / one['8']}" maxFractionDigits="2"/> 
						            大赢率：<fmt:formatNumber type="number" value="${one['10']*0.1*10 / one['8']}" maxFractionDigits="2"/>
						            平均消耗：<fmt:formatNumber type="number" value="${one['12']*0.1*10 / one['8']}" maxFractionDigits="2"/> 
							
							</c:otherwise>
						</c:choose>
					
					
					
						<c:choose>
							<c:when test="${one['12'] ==0  || one['12']==null}">
								赔率：<td>0</td>
							</c:when>
							<c:otherwise>
								赔率：<fmt:formatNumber type="number" value="${one['11']*0.1*10/one['12'] }" maxFractionDigits="2"/> 
							</c:otherwise>
						</c:choose>
						
					</td><!-- Spin  -->
					    
					
					
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

function goPage(page)
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