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

<div class="row-fluid">
	
		<div class="ami_table_header">
			<span class="ami_table_header_title">玩家每日在线时长分布</span>
		</div>
		<table id="table_report" class="table table-striped table-bordered table-hover">
			<thead>			
				<tr>
					<th>时长分布（分钟）</th>
					<th>在线人数</th>
				</tr>				
			</thead>
			<tbody>		
				
			 <c:forEach var="single" items="${simple2VOS}" varStatus="status">
					
				<tr>
					<td>${single.totalMinute}</td>
					<td>${single.num}</td>
				</tr>
				
			  </c:forEach>
					
			</tbody>
		</table>		
	</div>	
</div>


