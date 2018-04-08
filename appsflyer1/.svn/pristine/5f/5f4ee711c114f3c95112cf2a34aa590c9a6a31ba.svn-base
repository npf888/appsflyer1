<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/WEB-INF/struts-tags.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
			<form class="form-inline" action="log.do?method=slotSex" method="post" id="searchForm">
			老虎机类型:	<input type="text"   name="sType"  onClick="" value="${sType}" style="width:100px" />

				<button type="button"  style="margin-left:10px" class="btn btn-mini btn-info" onClick="go(1)">检索</button>	
			</form>
		</div>
		<table id="table_report" class="table table-striped table-bordered table-hover dataTables-example">
			<thead>			
				<tr>	
					   <th>老虎机名称</th>
					   <th>老虎机ID</th>
					   <th>用户性别</th>
					   <th>人数</th>
				</tr>				
			</thead>
			<tbody style="position:relative">		
				
			 <c:forEach items="${slotSexVOS}" var="MapData">
			 <tr>
			    <td>${MapData.slotName}</td>
			    <td>${MapData.type}</td>
			    <c:if test="${MapData.sex==0 }"> 
			    <td>	女</td>
			    </c:if>
			    <c:if test="${MapData.sex==1 }"> 
			     <td>	男</td>
			    </c:if>
			    <td>${MapData.num} </td>
			 </tr>
			  </c:forEach>
					
			</tbody>
		</table>		
	</div>
	</div>	

<div id="ami_newwindow" style="display: none;">

</div>

<script charset="utf-8" >
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