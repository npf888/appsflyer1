<!-- Log 页面 -->
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<base href="${pageContext.request.contextPath}/" />
<link rel="stylesheet" type="text/css" href="/js/Clockpicker/bootstrap-clockpicker.min.css">
<style>
.cloro-Goldenrod{
background:#DC143C;
color:#fff;
}
.cloro-DarkGoldenrod{
background:#2E8B57;
color:#fff;
}
</style>
<div id="ami_main">
	<div class="row-fluid" id="div1">	
			<form class="form-inline" action="log.do?method=loginRate" id="searchForm">

					<label>日期：</label>
			  		<input type="text" id="d4312" name="date" value="${date}"  class="Wdate"  onClick="WdatePicker({startDate:'%y-%M-01 00',dateFmt:'yyyy_MM_dd'})" /> 
					<button type="button"  style="margin-left:10px" class="btn btn-mini btn-info" onClick="go(1)">检索</button>
			</form>
	</div>
<hr/>

<div class="row-fluid">
	
		<div class="ami_table_header"><span class="ami_table_header_title">登录比</span>

		</div>
		<table id="table_report" class="table table-striped table-bordered table-hover">
			<thead>			
				<tr>
					<th>一阶登陆比分子</th>
					<th>一阶登陆比分母</th>
					<th>一阶登陆比</th>
					
					<th>二阶登录比分子</th>
					<th>二阶登录比分母</th>
					<th>二阶登录比</th>
				</tr>				
			</thead>
			<tbody>				
				<tr >
					<td>${oneRate.numerator}</td>
					<td>${oneRate.denominator}</td>
					<td>${oneRate.oneLoginRate}</td>
					
					<td>${twoRate.numerator}</td>
					<td>${twoRate.denominator}</td>
					<td>${twoRate.twoLoginRate}</td>
				</tr>
			</tbody>
		</table>		
		<jsp:include page="/common/page.jsp"></jsp:include>
		
	</div>	
</div>
<div id="ami_newwindow" style="display: none;">

</div>
<script type="text/javascript">
$('#start_time').clockpicker({
    placement: 'bottom',
    align: 'left',
    autoclose: true
});
$('#end_time').clockpicker({
    placement: 'bottom',
    align: 'left',
    autoclose: true
});
</script>