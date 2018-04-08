<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="source.jsp" %>
		<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
		<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
		<%@ taglib prefix="s" uri="/WEB-INF/struts-tags.tld" %>
		<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
		<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
			<form class="form-inline" action="statistics.do?method=humanSpendTimeOnSlot" method="post" id="searchForm">
			时间:	<input type="text"  id="d4311" name="dateStr" value="${dateStr}" class="Wdate" 
			onClick="WdatePicker({dateFmt:'yyyy_MM_dd'})" style="width:100px" requiry="true"/>
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
					<c:forEach items="${left}" var="key">
						<th>${key}</th>
					</c:forEach>
					
				</tr>				
			</thead>
			<tbody style="position:relative">		
				
			<tr id="tr_1" class="gradeA">
				 <c:forEach items="${right}" var="value">
							<td>${value}</td>
				  </c:forEach>
			</tr>
					
			</tbody>
		</table>		
	</div>
	</div>	
</div>

<div id="ami_newwindow" style="display: none;">

</div>
<script type="text/javascript" src="/js/Clockpicker/bootstrap-clockpicker.min.js"></script>
    <!-- Data Tables -->
    <script charset="utf-8" src="js/dataTables/jquery.dataTables.js"></script>
    <script charset="utf-8" src="js/dataTables/dataTables.bootstrap.js"></script>
    <script charset="utf-8" src="js/dataTables/dataTables.responsive.js"></script>
    <script charset="utf-8" src="js/dataTables/dataTables.tableTools.min.js"></script>

<script charset="utf-8" >
$(document).ready(function() {
    /* $('.dataTables-example').DataTable({
        "dom": 'lTfigpt',
        "order":[[0, "desc"]],
        buttons: [
                  'copy','excel'
              ],
        "tableTools": {
            "sSwfPath": "js/dataTables/swf/copy_csv_xls_pdf.swf"
        }
    }); */
});

function go(page)
{	$('.ami_Mask').show();
	var params = $('#searchForm').formSerialize();
	
	var url = $('#searchForm').attr('action') +"&currentPage="+page;
	$.post(url,params,function (data){
	
	$('#PAGECONTENT').html(data);
	
	$('.ami_Mask').hide();
	
	});
}
</script>