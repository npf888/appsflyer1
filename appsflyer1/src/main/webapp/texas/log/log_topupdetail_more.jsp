<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/WEB-INF/struts-tags.tld" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ page language="java" import="com.ami.texas.log.vo.RechargeVO" %>
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
.cloro-DarkGoldenrod{
background:#B8860B;
color:#fff;
}
 
</style>

<div id="ami_main">
	<div class="row-fluid" id="div1">	
			<form class="form-inline" action="log.do?method=topupdetailMore&type=${params.type}" method="post" id="searchForm">
			当前时间:	<input type="text"   name="date" value="${params.date}" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy_MM_dd'})" style="width:100px" requiry="true"/>
 				<font color="blue">国籍</font>：		<input type="radio" name="country" value="0"
						style="color:red" <c:if test="${params.country ==0 }"> checked</c:if> /> 否
					<input type="radio" name="country" value="1"
						style="color:red" <c:if test="${params.country ==1 }"> checked</c:if> /> 是  |
						
			<font color="blue">- 性别</font>:		<input type="radio" name="girl" value="0"
						<c:if test="${params.girl ==0 }"> checked</c:if> /> 否
					<input type="radio" name="girl" value="1"
						<c:if test="${params.girl ==1 }"> checked</c:if> /> 是   |
						
			<font color="blue">- 年龄</font>:		<input type="radio" name="age" value="0" 
						<c:if test="${params.age ==0 }"> checked</c:if> /> 否
					<input type="radio" name="age" value="1" 
						<c:if test="${params.age ==1 }"> checked</c:if> /> 是   |
				<button type="button"  style="margin-left:10px" class="btn btn-mini btn-info" onClick="go(1)">检索</button>	
				<button type="button"  style="margin-left:10px" class="btn btn-mini btn-info" onClick="goback()">返回</button>	
			</form>
			
	</div>
<hr/>

<div class="row-fluid">
	<div class="table-responsive" style="overflow-x:scroll">
		<div class="ami_table_header"><span class="ami_table_header_title">${params.type}充值明显</span>

		</div>
		
		<table id="table_report" class="table table-striped table-bordered table-hover dataTables-example">
		
			<thead>			
				<tr>	
					<th class="cloro-Green">渠道</th>
					<%
						Map<String,Object> map1  = (Map<String,Object>)request.getAttribute("params");
						if(Integer.valueOf((String)map1.get("country")).intValue() == 1){
							out.print("<th class='cloro-Green'>国籍</th>");
						}
						if(Integer.valueOf((String)map1.get("girl")).intValue() == 1){
							out.print("<th class='cloro-Green'>性别</th>");
						}
						if(Integer.valueOf((String)map1.get("age")).intValue() == 1){
							out.print("<th class='cloro-Green'>年龄</th>");
						}
					%>
					<th class="cloro-Green">充值</th>
				</tr>				
			</thead>
			<tbody style="position:relative">		
			<%
						
						List<RechargeVO> list  = (List<RechargeVO>)request.getAttribute("sum_daily");	
						for(RechargeVO map:list){
							out.print("<tr>");
							if(map.getChannelType().equals("0")){
								out.print("<td>默认渠道</td>");
								
							}else if(map.getChannelType().equals("1")){
								out.print("<td>谷歌</td>");
								
							}else if(map.getChannelType().equals("2")){
								out.print("<td>IOS</td>");
								
							}else if(map.getChannelType().equals("3")){
								out.print("<td>越南 AC</td>");
								
							}else if(map.getChannelType().equals("13")){
								out.print("<td>优米  金沙娱乐</td>");
								
							}else if(map.getChannelType().equals("14")){
								out.print("<td>GAME_VIEW</td>");
								
							}else if(map.getChannelType().equals("15")){
								out.print("<td>amazon</td>");
								
							}else if(map.getChannelType().equals("16")){
								out.print("<td>土耳其</td>");
								
							}else if(map.getChannelType().equals("17")){
								out.print("<td>天拖谷歌</td>");
								
							}else if(map.getChannelType().equals("18")){
								out.print("<td>天拖IOS</td>");
							}
							if(map.getIpCountries()!=null){
								out.print("<td>"+map.getIpCountries()+"</td>");
							}
							if(map.getGirl() != null){
								if(map.getGirl().intValue() == 1){
									out.print("<td>男</td>");
								}else{
									out.print("<td>女</td>");
								}
							}
							if(map.getAge() != null){
								out.print("<td>"+map.getAge()+"</td>");
							}
							Double money = Double.valueOf(map.getMoney());
							out.print("<td>"+money/100+"</td>");
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
function goback()
{
	var url = "log.do?method=topupdetail";
	$.post(url,function (data){
	
	$('#PAGECONTENT').html(data);
	
	});
}
</script>