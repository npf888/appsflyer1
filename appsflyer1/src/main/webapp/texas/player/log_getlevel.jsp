<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/WEB-INF/struts-tags.tld" %>
<div id="ami_main">
<div class="row-fluid">	
			
</div>	
<hr/>

   <div class="table-responsive">

	
		<div class="ami_table_header"><span class="ami_table_header_title">User level List</span>

		</div>
		<table id="table_report"class="table table-striped table-bordered table-hover dataTables-example" >
			<thead>			
				<tr>
				   <% 
				   for(int i = 1;i <= 150;i++){
				   
				   %>
				   <th><%=i %> 级</th>
				   <%
				   }
				    %>
				</tr>				
			</thead>
			<tbody>				
			<c:forEach var="level" items="${levelData}" varStatus="status">
			  
			  <c:choose>
			     <c:when test="${level['dataValue'] == 0}">
			     <td>${level['dataValue']}</td>
			     </c:when>
			     <c:otherwise>
			      <td> <a onclick="openNewWinow('player.do?method=queryPlayerLevel&levelDate=${level['level']}')"> ${level['dataValue']} </a></td>
			     </c:otherwise>
			  </c:choose>
			</c:forEach>
			</tbody>
		</table>		
		
		
	</div>	
</div>
<div id="ami_newwindow" style="display: none;">

</div>

<script type="text/javascript">

$(document).ready(function(){


});
function doBlock(id)
{
	bootbox.confirm("Are you sure to block this user account?", function(result) {
		if(result) {
			
			$('.ami_Mask').show();
			var url="player.do?method=doBlock&uuid="+id;
			$.get(url, function(data){
				$('.ami_Mask').hide();
				bootbox.alert("	User Blocked!");						
			});						
		}
	});		
}
function doDelete(id)
{
	
	bootbox.confirm("Delete this user account?", function(result) {
		if(result) {
			
			$('.ami_Mask').show();
			var url="affiliate.do?method=doDelete&uuid="+id;
			$.get(url, function(data){
				alert(data);
				$('.ami_Mask').hide();
				$('#tr_'+id).remove();
				bootbox.alert("	Removed!");						
			});						
		}
	});	
}

</script>