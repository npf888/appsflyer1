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
			<form class="form-inline" action="log.do?method=slotLog&type=${params.type}" method="post" id="searchForm">
			<input type="hidden"  id="d4310" value="${min_date}" class="Wdate"/>
			<input type="hidden"  id="d4313" value="${max_date}" class="Wdate"/>
			开始时间:	<input type="text"  id="d4311" name="start" value="${start_date}" class="Wdate" onClick="WdatePicker({minDate:'#F{$dp.$D(\'d4310\')}',maxDate:'#F{$dp.$D(\'d4312\')}',dateFmt:'yyyy_MM_dd'})" style="width:100px" requiry="true"/>
			截止时间：<input type="text"  id="d4312" name="end" value="${end_date}" class="Wdate"  onClick="WdatePicker({minDate:'#F{$dp.$D(\'d4311\')}',maxDate:'#F{$dp.$D(\'d4313\')}',realDateFmt:'yyyy_MM_dd',dateFmt:'yyyy_MM_dd'})" style="width:100px" requiry="true"/> 

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
					--><th>日期</th>

					
					<th class="cloro-Green">初级classic花费</th>
					<th class="color-red">初级classic获得</th>
					<th class="color-red">初级classic小游戏获得</th>
					<th class="cloro-Goldenrod">初级classic盈利</th>
					
					<th class="cloro-Green">中级classic花费</th>
					<th class="color-red">中级classic获得</th>
					<th class="color-red">中级classic小游戏获得</th>
					<th class="cloro-Goldenrod">中级classic盈利</th>
					
					<th class="cloro-Green">高级classic花费</th>
					<th class="color-red">高级classic获得</th>
					<th class="color-red">高级classic小游戏获得</th>
					<th class="cloro-Goldenrod">高级classic盈利</th>
					
					<th class="cloro-Green">专业classic花费</th>
					<th class="color-red">专业classic获得</th>
					<th class="color-red">专业classic小游戏获得</th>
					<th class="cloro-Goldenrod">专业classic盈利</th>
					
					<th class="cloro-Green">初级四大美人花费</th>
					<th class="color-red">初级四大美人获得</th>
					<th class="color-red">初级四大美人小游戏获得</th>
					<th class="cloro-Goldenrod">初级四大美人盈利</th>
					
					<th class="cloro-Green">中级四大美人花费</th>
					<th class="color-red">中级四大美人获得</th>
					<th class="color-red">中级四大美人小游戏获得</th>
					<th class="cloro-Goldenrod">中级四大美人盈利</th>
					
					<th class="cloro-Green">高级四大美人花费</th>
					<th class="color-red">高级四大美人获得</th>
					<th class="color-red">高级四大美人小游戏获得</th>
					<th class="cloro-Goldenrod">高级四大美人盈利</th>
					
					<th class="cloro-Green">专业四大美人花费</th>
					<th class="color-red">专业四大美人获得</th>
					<th class="color-red">专业四大美人小游戏获得</th>
					<th class="cloro-Goldenrod">专业四大美人盈利</th>
					
					<th class="cloro-Green">初级水果花费</th>
					<th class="color-red">初级水果获得</th>
					<th class="color-red">初级水果小游戏获得</th>
					<th class="cloro-Goldenrod">初级水果盈利</th>
					
					<th class="cloro-Green">中级水果花费</th>
					<th class="color-red">中级水果获得</th>
					<th class="color-red">中级水果小游戏获得</th>
					<th class="cloro-Goldenrod">中级水果盈利</th>
					
					<th class="cloro-Green">高级水果花费</th>
					<th class="color-red">高级水果获得</th>
					<th class="color-red">高级水果小游戏获得</th>
					<th class="cloro-Goldenrod">高级水果盈利</th>
					
					<th class="cloro-Green">专业水果花费</th>
					<th class="color-red">专业水果获得</th>
					<th class="color-red">专业水果小游戏获得</th>
					<th class="cloro-Goldenrod">专业水果盈利</th>
					
					<th class="cloro-Green">初级沙滩花费</th>
					<th class="color-red">初级沙滩获得</th>
					<th class="color-red">初级沙滩小游戏获得</th>
					<th class="cloro-Goldenrod">初级沙滩盈利</th>
					
					<th class="cloro-Green">中级沙滩花费</th>
					<th class="color-red">中级沙滩获得</th>
					<th class="color-red">中级沙滩小游戏获得</th>
					<th class="cloro-Goldenrod">中级沙滩盈利</th>
					
					<th class="cloro-Green">高级沙滩花费</th>
					<th class="color-red">高级沙滩获得</th>
					<th class="color-red">高级沙滩小游戏获得</th>
					<th class="cloro-Goldenrod">高级沙滩盈利</th>
					
					<th class="cloro-Green">专业沙滩花费</th>
					<th class="color-red">专业沙滩获得</th>
					<th class="color-red">专业沙滩小游戏获得</th>
					<th class="cloro-Goldenrod">专业沙滩盈利</th>
					
					<th class="cloro-Green">初级吸血鬼花费</th>
					<th class="color-red">初级吸血鬼获得</th>
					<th class="color-red">初级吸血鬼小游戏获得</th>
					<th class="cloro-Goldenrod">初级吸血鬼盈利</th>
					
					<th class="cloro-Green">中级吸血鬼花费</th>
					<th class="color-red">中级吸血鬼获得</th>
					<th class="color-red">中级吸血鬼小游戏获得</th>
					<th class="cloro-Goldenrod">中级吸血鬼盈利</th>
					
					<th class="cloro-Green">高级吸血鬼花费</th>
					<th class="color-red">高级吸血鬼获得</th>
					<th class="color-red">高级吸血鬼小游戏获得</th>
					<th class="cloro-Goldenrod">高级吸血鬼盈利</th>
					
					<th class="cloro-Green">专业吸血鬼花费</th>
					<th class="color-red">专业吸血鬼获得</th>
					<th class="color-red">专业吸血鬼小游戏获得</th>
					<th class="cloro-Goldenrod">专业吸血鬼盈利</th>
					
					<th class="cloro-Green">初级埃及艳后花费</th>
					<th class="color-red">初级埃及艳后获得</th>
					<th class="color-red">初级埃及艳后小游戏获得</th>
					<th class="cloro-Goldenrod">初级埃及艳后盈利</th>
					
					<th class="cloro-Green">中级埃及艳后花费</th>
					<th class="color-red">中级埃及艳后获得</th>
					<th class="color-red">中级埃及艳后小游戏获得</th>
					<th class="cloro-Goldenrod">中级埃及艳后盈利</th>
					
					<th class="cloro-Green">高级埃及艳后花费</th>
					<th class="color-red">高级埃及艳后获得</th>
					<th class="color-red">高级埃及艳后小游戏获得</th>
					<th class="cloro-Goldenrod">高级埃及艳后盈利</th>
					
					<th class="cloro-Green">专业埃及艳后花费</th>
					<th class="color-red">专业埃及艳后获得</th>
					<th class="color-red">专业埃及艳后小游戏获得</th>
					<th class="cloro-Goldenrod">专业埃及艳后盈利</th>
					
					<th class="cloro-Green">初级美人鱼花费</th>
					<th class="color-red">初级美人鱼获得</th>
					<th class="color-red">初级美人鱼小游戏获得</th>
					<th class="cloro-Goldenrod">初级美人鱼盈利</th>
					
					<th class="cloro-Green">中级美人鱼花费</th>
					<th class="color-red">中级美人鱼获得</th>
					<th class="color-red">中级美人鱼小游戏获得</th>
					<th class="cloro-Goldenrod">中级美人鱼盈利</th>
					
					<th class="cloro-Green">高级美人鱼花费</th>
					<th class="color-red">高级美人鱼获得</th>
					<th class="color-red">高级美人鱼小游戏获得</th>
					<th class="cloro-Goldenrod">高级美人鱼盈利</th>
					
					<th class="cloro-Green">专业美人鱼花费</th>
					<th class="color-red">专业美人鱼获得</th>
					<th class="color-red">专业美人鱼小游戏获得</th>
					<th class="cloro-Goldenrod">专业美人鱼盈利</th>
					
					<!-- ============================================= -->
					
					<th class="cloro-Green">初级澳门女神花费</th>
					<th class="color-red">初级澳门女神获得</th>
					<th class="color-red">初级澳门女神小游戏获得</th>
					<th class="cloro-Goldenrod">初级澳门女神盈利</th>
					
					<th class="cloro-Green">中级澳门女神花费</th>
					<th class="color-red">中级澳门女神获得</th>
					<th class="color-red">中级澳门女神小游戏获得</th>
					<th class="cloro-Goldenrod">中级澳门女神盈利</th>
					
					<th class="cloro-Green">高级澳门女神花费</th>
					<th class="color-red">高级澳门女神获得</th>
					<th class="color-red">高级澳门女神小游戏获得</th>
					<th class="cloro-Goldenrod">高级澳门女神盈利</th>
					
					<th class="cloro-Green">专业澳门女神花费</th>
					<th class="color-red">专业澳门女神获得</th>
					<th class="color-red">专业澳门女神小游戏获得</th>
					<th class="cloro-Goldenrod">专业澳门女神盈利</th>
					<!-- ============================================= -->
					
					<th class="cloro-Green">初级白蛇传花费</th>
					<th class="color-red">初级白蛇传获得</th>
					<th class="color-red">初级白蛇传小游戏获得</th>
					<th class="cloro-Goldenrod">初级白蛇传盈利</th>
					
					<th class="cloro-Green">中级白蛇传花费</th>
					<th class="color-red">中级白蛇传获得</th>
					<th class="color-red">中级白蛇传小游戏获得</th>
					<th class="cloro-Goldenrod">中级白蛇传盈利</th>
					
					<th class="cloro-Green">高级白蛇传花费</th>
					<th class="color-red">高级白蛇传获得</th>
					<th class="color-red">高级白蛇传小游戏获得</th>
					<th class="cloro-Goldenrod">高级白蛇传盈利</th>
					
					<th class="cloro-Green">专业白蛇传花费</th>
					<th class="color-red">专业白蛇传获得</th>
					<th class="color-red">专业白蛇传小游戏获得</th>
					<th class="cloro-Goldenrod">专业白蛇传盈利</th>
					<!-- ============================================= -->
					
					<th class="cloro-Green">初级马来网红花费</th>
					<th class="color-red">初级马来网红获得</th>
					<th class="color-red">初级马来网红小游戏获得</th>
					<th class="cloro-Goldenrod">初级马来网红盈利</th>
					
					<th class="cloro-Green">中级马来网红花费</th>
					<th class="color-red">中级马来网红获得</th>
					<th class="color-red">中级马来网红小游戏获得</th>
					<th class="cloro-Goldenrod">中级马来网红盈利</th>
					
					<th class="cloro-Green">高级马来网红花费</th>
					<th class="color-red">高级马来网红获得</th>
					<th class="color-red">高级马来网红小游戏获得</th>
					<th class="cloro-Goldenrod">高级马来网红盈利</th>
					
					<th class="cloro-Green">专业马来网红花费</th>
					<th class="color-red">专业马来网红获得</th>
					<th class="color-red">专业马来网红小游戏获得</th>
					<th class="cloro-Goldenrod">专业马来网红盈利</th>
					
				</tr>				
			</thead>
			<tbody style="position:relative">		
				
			 <c:forEach items="${sum_daily}" var="one">
				
				<tr id="tr_${user.id }" class="gradeA">
					<!--<td style="position:absolute;border-right:1px solid #DDD;background-color:#f1f1f1">${one.date}</td>-->
					<td>${one.date}</td>

					<!-- 1 -->
					<td>${one['51']}</td>
					<td>${one['116']}</td>
					<td>${one['181']}</td>
					<td>${one['51']-one['116']-one['181']}</td>
					
					<td>${one['52']}</td>
					<td>${one['117']}</td>
					<td>${one['182']}</td>
					<td>${one['52']-one['117']-one['182']}</td>
					
					<td>${one['53']}</td>
					<td>${one['118']}</td>
					<td>${one['183']}</td>
					<td>${one['53']-one['118']-one['183']}</td>
					
					<td>${one['54']}</td>
					<td>${one['119']}</td>
					<td>${one['184']}</td>
					<td>${one['54']-one['119']-one['184']}</td>
					
					<!-- 2 -->
					<td>${one['55']}</td>
					<td>${one['120']}</td>
					<td>${one['185']}</td>
					<td>${one['55']-one['120']-one['185']}</td>
					
					<td>${one['56']}</td>
					<td>${one['121']}</td>
					<td>${one['186']}</td>
					<td>${one['56']-one['121']-one['186']}</td>
					
					<td>${one['57']}</td>
					<td>${one['122']}</td>
					<td>${one['187']}</td>
					<td>${one['57']-one['122']-one['187']}</td>
					
					<td>${one['58']}</td>
					<td>${one['123']}</td>
					<td>${one['188']}</td>
					<td>${one['58']-one['123']-one['188']}</td>
					
					<!-- 3 -->
					<td>${one['59']}</td>
					<td>${one['124']}</td>
					<td>${one['189']}</td>
					<td>${one['59']-one['124']-one['189']}</td>
					
					<td>${one['60']}</td>
					<td>${one['125']}</td>
					<td>${one['190']}</td>
					<td>${one['60']-one['125']-one['190']}</td>
					
					<td>${one['61']}</td>
					<td>${one['126']}</td>
					<td>${one['191']}</td>
					<td>${one['61']-one['126']-one['191']}</td>
					
					<td>${one['62']}</td>
					<td>${one['127']}</td>
					<td>${one['192']}</td>
					<td>${one['62']-one['127']-one['192']}</td>
					
					<!-- 4 -->
					<td>${one['63']}</td>
					<td>${one['128']}</td>
					<td>${one['193']}</td>
					<td>${one['63']-one['128']-one['193']}</td>
					
					<td>${one['64']}</td>
					<td>${one['129']}</td>
					<td>${one['194']}</td>
					<td>${one['64']-one['129']-one['194']}</td>
					
					<td>${one['65']}</td>
					<td>${one['130']}</td>
					<td>${one['195']}</td>
					<td>${one['65']-one['130']-one['195']}</td>
					
					<td>${one['66']}</td>
					<td>${one['131']}</td>
					<td>${one['196']}</td>
					<td>${one['66']-one['131']-one['196']}</td>
					
					<!-- 5  -->
					<td>${one['67']}</td>
					<td>${one['132']}</td>
					<td>${one['197']}</td>
					<td>${one['67']-one['132']-one['197']}</td>
					
					<td>${one['68']}</td>
					<td>${one['133']}</td>
					<td>${one['198']}</td>
					<td>${one['68']-one['133']-one['198']}</td>
					
					<td>${one['69']}</td>
					<td>${one['134']}</td>
					<td>${one['199']}</td>
					<td>${one['69']-one['134']-one['199']}</td>
					
					<td>${one['70']}</td>
					<td>${one['135']}</td>
					<td>${one['200']}</td>
					<td>${one['70']-one['135']-one['200']}</td>
					
					<!-- 6  -->
					<td>${one['71']}</td>
					<td>${one['136']}</td>
					<td>${one['201']}</td>
					<td>${one['71']-one['136']-one['201']}</td>
					
					<td>${one['72']}</td>
					<td>${one['137']}</td>
					<td>${one['202']}</td>
					<td>${one['72']-one['137']-one['202']}</td>
					
					<td>${one['73']}</td>
					<td>${one['138']}</td>
					<td>${one['203']}</td>
					<td>${one['73']-one['138']-one['203']}</td>
					
					<td>${one['74']}</td>
					<td>${one['139']}</td>
					<td>${one['204']}</td>
					<td>${one['74']-one['139']-one['204']}</td>
					
					<!-- 7  -->
					<td>${one['75']}</td>
					<td>${one['140']}</td>
					<td>${one['205']}</td>
					<td>${one['75']-one['140']-one['205']}</td>
					
					<td>${one['76']}</td>
					<td>${one['141']}</td>
					<td>${one['206']}</td>
					<td>${one['76']-one['141']-one['206']}</td>
					
					<td>${one['77']}</td>
					<td>${one['142']}</td>
					<td>${one['207']}</td>
					<td>${one['77']-one['142']-one['207']}</td>
					
					<td>${one['78']}</td>
					<td>${one['143']}</td>
					<td>${one['208']}</td>
					<td>${one['78']-one['143']-one['208']}</td>
					
					<!-- 8 -->
					
					<td>${one['79']}</td>
					<td>${one['144']}</td>
					<td>${one['209']}</td>
					<td>${one['79']-one['144']-one['209']}</td>
					
					<td>${one['80']}</td>
					<td>${one['145']}</td>
					<td>${one['210']}</td>
					<td>${one['80']-one['145']-one['210']}</td>
					
					<td>${one['81']}</td>
					<td>${one['146']}</td>
					<td>${one['211']}</td>
					<td>${one['81']-one['146']-one['211']}</td>
					
					<td>${one['82']}</td>
					<td>${one['147']}</td>
					<td>${one['212']}</td>  
					<td>${one['82']-one['147']-one['212']}</td>
					
					<!-- 9 -->
					<td>${one['83']}</td>
					<td>${one['148']}</td>
					<td>${one['213']}</td>
					<td>${one['83']-one['148']-one['213']}</td>
					
					<td>${one['84']}</td>
					<td>${one['149']}</td>
					<td>${one['214']}</td>
					<td>${one['84']-one['149']-one['214']}</td>
					
					<td>${one['85']}</td>
					<td>${one['150']}</td>
					<td>${one['215']}</td>
					<td>${one['85']-one['150']-one['215']}</td>
					
					<td>${one['86']}</td>
					<td>${one['151']}</td>
					<td>${one['216']}</td>  
					<td>${one['86']-one['151']-one['216']}</td>
					<!-- 10 -->
					<td>${one['87']}</td>
					<td>${one['152']}</td>
					<td>${one['217']}</td>
					<td>${one['87']-one['152']-one['217']}</td>
					
					<td>${one['88']}</td>
					<td>${one['153']}</td>
					<td>${one['218']}</td>
					<td>${one['88']-one['153']-one['218']}</td>
					
					<td>${one['89']}</td>
					<td>${one['154']}</td>
					<td>${one['219']}</td>
					<td>${one['89']-one['154']-one['219']}</td>
					
					<td>${one['90']}</td>
					<td>${one['155']}</td>
					<td>${one['220']}</td>  
					<td>${one['90']-one['155']-one['220']}</td>
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