<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/WEB-INF/struts-tags.tld" %>
<div id="ami_main">
<div class="row-fluid">	

</div>	
<hr/>
<div class="row-fluid">
	
		<div class="ami_table_header"><span class="ami_table_header_title">俱乐部成员列表</span>

		</div>
		<table id="table_report" class="table table-striped table-bordered table-hover">
			<thead>			
				<tr>
					<th>id</th>
					<th>姓名</th>
					<th>等级</th>
					<th>加入俱乐部时间</th>
					<th>俱乐部职位</th>
					<th>个人贡献</th>
					<th>个人活跃</th>
				</tr>				
			</thead>
			<tbody>				
			 <c:forEach var="user" items="${datas}">
					
				<tr >
					<td>${user.id}</td>
					<td>${user.name}</td>
					<td>${user.level}</td>
					<td>${user.joinTimeString}</td>
					<td>${user.zhiwuString}</td>
					<td>${user.gongxian}</td>
					<td>${user.huoyue}</td>
				</tr>
			  </c:forEach>
			</tbody>
		</table>		
		<div class="form-actions">
			<button class="btn btn-small" type="reset" onclick="goback()"><i class="icon-undo"></i>跳回</button>
	  </div>
		
	</div>	
</div>
<div id="ami_newwindow" style="display: none;"></div>

