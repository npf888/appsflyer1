<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/WEB-INF/struts-tags.tld" %>
 <link href="http://cdn.bootcss.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet">
 <style type='text/css'>
      body {
        background-color: #CCC;
      }
 </style>
<div id="ami_main">
<hr/>
<div id="ami_main">
<hr/>

<div class="row-fluid">
	
		<div class="ami_table_header">
			<span class="ami_table_header_title">服务器人数信息</span>
		</div>
		<table id="table_report" class="table table-striped table-bordered table-hover">
			<thead>			
				<tr>
					<th>地区</th>
					<th>在线人数</th>
					<th>IP地址</th>
				</tr>				
			</thead>
			<tbody>		
				
			 <c:forEach var="server" items="${ipNumVOList}" varStatus="status">
					
				<tr>
					<td>${server.place}</td>
					<td>${server.onLineNum}</td>
					<td>${server.ip}</td>
				</tr>
				
			  </c:forEach>
					
			</tbody>
		</table>		
	</div>	
</div>

<script type="text/javascript">


</script>