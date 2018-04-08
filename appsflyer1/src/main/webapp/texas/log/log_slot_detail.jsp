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


.color-red1{
background:#660033;
color:#fff;
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
			时间：<input type="text"   name="end" value="${end_date}" class="Wdate"  onClick="WdatePicker({realDateFmt:'yyyy_MM_dd',dateFmt:'yyyy_MM_dd'})" style="width:100px" requiry="true"/> 
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
					-->
					<th>类型</th>
					<th>老虎机名称</th>

					<th class="cloro-Green">初级-花费</th>
					<th class="cloro-Green">初级-获得</th>
					<th class="cloro-Green">初级-小游戏获得</th>
					<th class="cloro-Green">初级-盈利</th>
					
					<th class="cloro-Goldenrod">中级-花费</th>
					<th class="cloro-Goldenrod">中级-获得</th>
					<th class="cloro-Goldenrod">中级-小游戏获得</th>
					<th class="cloro-Goldenrod">中级-盈利</th>
					
					<th class="color-red">高级-花费</th>
					<th class="color-red">高级-获得</th>
					<th class="color-red">高级-小游戏获得</th>
					<th class="color-red">高级-盈利</th>
					
					<th class="color-red1">专业-花费</th>
					<th class="color-red1">专业-获得</th>
					<th class="color-red1">专业-小游戏获得</th>
					<th class="color-red1">专业-盈利</th>
					
				</tr>				
			</thead>
			<tbody style="position:relative">		
				
				
				<tr  class="gradeA">
					<!--<td style="position:absolute;border-right:1px solid #DDD;background-color:#f1f1f1">${one.date}</td>-->
					<td>1</td>
					<td>Classic</td>

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
					
				</tr>
				<tr>
					<td>2</td>
					<td>宙斯</td>
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
					
				</tr>
				<tr>
					<td>3</td>
					<td>犀牛</td>
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
				</tr>
				<tr>
					<td>4</td>
					<td>狮身人面像</td>
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
				</tr>
				<tr>	
					<td>5</td>
					<td>东方龙</td>
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
				</tr>
				<tr>
					<td>6</td>
					<td>福尔摩斯</td>
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
				</tr>
				<tr>
					<td>7</td>
					<td>斯巴达</td>
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
				</tr>
				<tr>	
					<td>8</td>
					<td>女巫魔法</td>
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
				</tr>
				<tr>
					<td>9</td>
					<td>西方龙</td>
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
				</tr>
				<tr>
					<td>10</td>
					<td>阿兹特克</td>
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
				<!-- ---------------------11-------------------------------------------- -->
				<!-- ---------------------11-------------------------------------------- -->
				
				<tr>
					<td>11</td>
					<td>动物虎</td>
					<!-- 11 -->
					<td>${one['91']}</td>
					<td>${one['5501']}</td>
					<td>${one['6001']}</td>
					<td>${one['91']-one['5501']-one['6001']}</td>
					
					<td>${one['92']}</td>
					<td>${one['5502']}</td>
					<td>${one['6002']}</td>
					<td>${one['92']-one['5502']-one['6002']}</td>
					
					<td>${one['93']}</td>
					<td>${one['5503']}</td>
					<td>${one['6003']}</td>
					<td>${one['93']-one['5503']-one['6003']}</td>
					
					<td>${one['94']}</td>
					<td>${one['5504']}</td>
					<td>${one['6004']}</td>  
					<td>${one['94']-one['5504']-one['6004']}</td>
				</tr>
				<tr>
					<td>12</td>
					<td>水果</td>
					<!-- 12 -->
					<td>${one['95']}</td>
					<td>${one['5505']}</td>
					<td>${one['6005']}</td>
					<td>${one['95']-one['5505']-one['6005']}</td>
					
					<td>${one['96']}</td>
					<td>${one['5506']}</td>
					<td>${one['6006']}</td>
					<td>${one['96']-one['5506']-one['6006']}</td>
					
					<td>${one['97']}</td>
					<td>${one['5507']}</td>
					<td>${one['6007']}</td>
					<td>${one['97']-one['5507']-one['6007']}</td>
					
					<td>${one['98']}</td>
					<td>${one['5508']}</td>
					<td>${one['6008']}</td>  
					<td>${one['98']-one['5508']-one['6008']}</td>
				</tr>
				<tr>
					<td>13</td>
					<td>忍者</td>
					<!-- 13 -->
					<td>${one['99']}</td>
					<td>${one['5509']}</td>
					<td>${one['6009']}</td>
					<td>${one['99']-one['5509']-one['6009']}</td>
					
					<td>${one['100']}</td>
					<td>${one['5510']}</td>
					<td>${one['6010']}</td>
					<td>${one['100']-one['5510']-one['6010']}</td>
					
					<td>${one['101']}</td>
					<td>${one['5511']}</td>
					<td>${one['6011']}</td>
					<td>${one['101']-one['5511']-one['6011']}</td>
					
					<td>${one['102']}</td>
					<td>${one['5512']}</td>
					<td>${one['6012']}</td>  
					<td>${one['102']-one['5512']-one['6012']}</td>
				</tr>
				<tr>
					<td>14</td>
					<td>海盗</td>
					<!-- 14 -->
					<td>${one['103']}</td>
					<td>${one['5513']}</td>
					<td>${one['6013']}</td>
					<td>${one['103']-one['5513']-one['6013']}</td>
					
					<td>${one['104']}</td>
					<td>${one['5514']}</td>
					<td>${one['6014']}</td>
					<td>${one['104']-one['5514']-one['6014']}</td>
					
					<td>${one['105']}</td>
					<td>${one['5515']}</td>
					<td>${one['6015']}</td>
					<td>${one['105']-one['5515']-one['6015']}</td>
					
					<td>${one['106']}</td>
					<td>${one['5516']}</td>
					<td>${one['6016']}</td>  
					<td>${one['106']-one['5516']-one['6016']}</td>
				</tr>
				
				<tr>
					<td>15</td>
					<td>维密</td>
					<!-- 15 -->
					<td>${one['107']}</td>
					<td>${one['5517']}</td>
					<td>${one['6017']}</td>
					<td>${one['107']-one['5517']-one['6017']}</td>
					
					<td>${one['108']}</td>
					<td>${one['5518']}</td>
					<td>${one['6018']}</td>
					<td>${one['108']-one['5518']-one['6018']}</td>
					
					<td>${one['109']}</td>
					<td>${one['5519']}</td>
					<td>${one['6019']}</td>
					<td>${one['109']-one['5519']-one['6019']}</td>
					
					<td>${one['110']}</td>
					<td>${one['5520']}</td>
					<td>${one['6020']}</td>  
					<td>${one['110']-one['5520']-one['6020']}</td>
				</tr>
				
				<tr>
					<td>16</td>
					<td>西部牛仔</td>
					<!-- 16 -->
					<td>${one['5000']}</td>
					<td>${one['5521']}</td>
					<td>${one['6021']}</td>
					<td>${one['5000']-one['5521']-one['6021']}</td>
					
					<td>${one['5001']}</td>
					<td>${one['5522']}</td>
					<td>${one['6022']}</td>
					<td>${one['5001']-one['5522']-one['6022']}</td>
					
					<td>${one['5002']}</td>
					<td>${one['5523']}</td>
					<td>${one['6023']}</td>
					<td>${one['5002']-one['5523']-one['6023']}</td>
					
					<td>${one['5003']}</td>
					<td>${one['5524']}</td>
					<td>${one['6024']}</td>  
					<td>${one['5003']-one['5524']-one['6024']}</td>
				</tr>
				
				<tr>
					<td>17</td>
					<td>日月潭</td>
					<!-- 17 -->
					<td>${one['5004']}</td>
					<td>${one['5525']}</td>
					<td>${one['6025']}</td>
					<td>${one['5004']-one['5525']-one['6025']}</td>
					
					<td>${one['5005']}</td>
					<td>${one['5526']}</td>
					<td>${one['6026']}</td>
					<td>${one['5005']-one['5526']-one['6026']}</td>
					
					<td>${one['5006']}</td>
					<td>${one['5527']}</td>
					<td>${one['6027']}</td>
					<td>${one['5006']-one['5527']-one['6027']}</td>
					
					<td>${one['5007']}</td>
					<td>${one['5528']}</td>
					<td>${one['6028']}</td>  
					<td>${one['5007']-one['5528']-one['6028']}</td>
				</tr>
				<tr>
					<td>18</td>
					<td>动物狼</td>
					<!-- 18 -->
					<td>${one['5008']}</td>
					<td>${one['5529']}</td>
					<td>${one['6029']}</td>
					<td>${one['5008']-one['5529']-one['6029']}</td>
					
					<td>${one['5009']}</td>
					<td>${one['5530']}</td>
					<td>${one['6030']}</td>
					<td>${one['5009']-one['5530']-one['6030']}</td>
					
					<td>${one['5010']}</td>
					<td>${one['5531']}</td>
					<td>${one['6031']}</td>
					<td>${one['5010']-one['5531']-one['6031']}</td>
					
					<td>${one['5011']}</td>
					<td>${one['5532']}</td>
					<td>${one['6032']}</td>  
					<td>${one['5011']-one['5532']-one['6032']}</td>
				</tr>
				
				<tr>
					<td>19</td>
					<td>水晶魔法宝石</td>
					<!-- 19 -->
					<td>${one['5012']}</td>
					<td>${one['5533']}</td>
					<td>${one['6033']}</td>
					<td>${one['5012']-one['5533']-one['6033']}</td>
					
					<td>${one['5013']}</td>
					<td>${one['5534']}</td>
					<td>${one['6034']}</td>
					<td>${one['5013']-one['5534']-one['6034']}</td>
					
					<td>${one['5014']}</td>
					<td>${one['5535']}</td>
					<td>${one['6035']}</td>
					<td>${one['5014']-one['5535']-one['6035']}</td>
					
					<td>${one['5015']}</td>
					<td>${one['5536']}</td>
					<td>${one['6036']}</td>  
					<td>${one['5015']-one['5536']-one['6036']}</td>
				</tr>
				<tr>
					<td>20</td>
					<td>沙滩</td>
					<!-- 20 -->
					<td>${one['5016']}</td>
					<td>${one['5537']}</td>
					<td>${one['6037']}</td>
					<td>${one['5016']-one['5537']-one['6037']}</td>
					
					<td>${one['5017']}</td>
					<td>${one['5538']}</td>
					<td>${one['6038']}</td>
					<td>${one['5017']-one['5538']-one['6038']}</td>
					
					<td>${one['5018']}</td>
					<td>${one['5539']}</td>
					<td>${one['6039']}</td>
					<td>${one['5018']-one['5539']-one['6039']}</td>
					
					<td>${one['5019']}</td>
					<td>${one['5540']}</td>
					<td>${one['6040']}</td>  
					<td>${one['5019']-one['5540']-one['6040']}</td>
				</tr>
				
				<!--  ---------------------------------------------------21----------------------------------------------- -->
				<!--  ---------------------------------------------------21----------------------------------------------- -->
				<tr>
					<td>21</td>
					<td>澳门女神</td>
					<!-- 21 -->
					<td>${one['5020']}</td>
					<td>${one['5541']}</td>
					<td>${one['6041']}</td>
					<td>${one['5020']-one['5541']-one['6041']}</td>
					
					<td>${one['5021']}</td>
					<td>${one['5542']}</td>
					<td>${one['6042']}</td>
					<td>${one['5021']-one['5542']-one['6042']}</td>
					
					<td>${one['5022']}</td>
					<td>${one['5543']}</td>
					<td>${one['6043']}</td>
					<td>${one['5022']-one['5543']-one['6043']}</td>
					
					<td>${one['5023']}</td>
					<td>${one['5544']}</td>
					<td>${one['6044']}</td>  
					<td>${one['5023']-one['5544']-one['6044']}</td>
				</tr>
				<tr>
					<td>22</td>
					<td>泰国象</td>
					<!-- 22 -->
					<td>${one['5024']}</td>
					<td>${one['5545']}</td>
					<td>${one['6045']}</td>
					<td>${one['5024']-one['5545']-one['6045']}</td>
					
					<td>${one['5025']}</td>
					<td>${one['5546']}</td>
					<td>${one['6046']}</td>
					<td>${one['5025']-one['5546']-one['6046']}</td>
					
					<td>${one['5026']}</td>
					<td>${one['5547']}</td>
					<td>${one['6047']}</td>
					<td>${one['5026']-one['5547']-one['6047']}</td>
					
					<td>${one['5027']}</td>
					<td>${one['5548']}</td>
					<td>${one['6048']}</td>  
					<td>${one['950270']-one['5548']-one['6048']}</td>
				</tr>
				
				<tr>
					<td>23</td>
					<td>吸血鬼</td>
					<!-- 23 -->
					<td>${one['5028']}</td>
					<td>${one['5549']}</td>
					<td>${one['6049']}</td>
					<td>${one['5028']-one['5549']-one['6049']}</td>
					
					<td>${one['5029']}</td>
					<td>${one['5550']}</td>
					<td>${one['6050']}</td>
					<td>${one['5029']-one['5550']-one['6050']}</td>
					
					<td>${one['5030']}</td>
					<td>${one['5551']}</td>
					<td>${one['6051']}</td>
					<td>${one['5030']-one['5551']-one['6051']}</td>
					
					<td>${one['5031']}</td>
					<td>${one['5552']}</td>
					<td>${one['6052']}</td>  
					<td>${one['5031']-one['5552']-one['6052']}</td>
				</tr>
				
				<tr>
					<td>24</td>
					<td>马来网红</td>
					<!-- 24 -->
					<td>${one['5032']}</td>
					<td>${one['5553']}</td>
					<td>${one['6053']}</td>
					<td>${one['5032']-one['5553']-one['6053']}</td>
					
					<td>${one['5033']}</td>
					<td>${one['5554']}</td>
					<td>${one['6054']}</td>
					<td>${one['5033']-one['5554']-one['6054']}</td>
					
					<td>${one['5034']}</td>
					<td>${one['5555']}</td>
					<td>${one['6055']}</td>
					<td>${one['5034']-one['5555']-one['6055']}</td>
					
					<td>${one['5035']}</td>
					<td>${one['5556']}</td>
					<td>${one['6056']}</td>  
					<td>${one['5035']-one['5556']-one['6056']}</td>
				</tr>
				
				
				<tr>
					<td>25</td>
					<td>埃及艳后</td>
					<!-- 25 -->
					<td>${one['5036']}</td>
					<td>${one['5557']}</td>
					<td>${one['6057']}</td>
					<td>${one['5036']-one['5557']-one['6057']}</td>
					
					<td>${one['5037']}</td>
					<td>${one['5558']}</td>
					<td>${one['6058']}</td>
					<td>${one['5037']-one['5558']-one['6058']}</td>
					
					<td>${one['5038']}</td>
					<td>${one['5559']}</td>
					<td>${one['6059']}</td>
					<td>${one['5038']-one['5559']-one['6059']}</td>
					
					<td>${one['5039']}</td>
					<td>${one['5560']}</td>
					<td>${one['6060']}</td>  
					<td>${one['5039']-one['5560']-one['6060']}</td>
				</tr>
				
				<tr>
					<td>26</td>
					<td>巴西风情</td>
					<!-- 26 -->
					<td>${one['5040']}</td>
					<td>${one['5561']}</td>
					<td>${one['6061']}</td>
					<td>${one['5040']-one['5561']-one['6061']}</td>
					
					<td>${one['5041']}</td>
					<td>${one['5562']}</td>
					<td>${one['6062']}</td>
					<td>${one['5041']-one['5562']-one['6062']}</td>
					
					<td>${one['5042']}</td>
					<td>${one['5563']}</td>
					<td>${one['6063']}</td>
					<td>${one['5042']-one['5563']-one['6063']}</td>
					
					<td>${one['5043']}</td>
					<td>${one['5564']}</td>
					<td>${one['6064']}</td>  
					<td>${one['5043']-one['5564']-one['6064']}</td>
				</tr>
				<tr>
					<td>27</td>
					<td>美人鱼</td>
					<!-- 27 -->
					<td>${one['5044']}</td>
					<td>${one['5565']}</td>
					<td>${one['6065']}</td>
					<td>${one['5044']-one['5565']-one['6065']}</td>
					
					<td>${one['5045']}</td>
					<td>${one['5566']}</td>
					<td>${one['6066']}</td>
					<td>${one['5045']-one['5566']-one['6066']}</td>
					
					<td>${one['5046']}</td>
					<td>${one['5567']}</td>
					<td>${one['6067']}</td>
					<td>${one['5046']-one['5567']-one['6067']}</td>
					
					<td>${one['5047']}</td>
					<td>${one['5568']}</td>
					<td>${one['6068']}</td>  
					<td>${one['5047']-one['5568']-one['6068']}</td>
				</tr>
				
				<tr>
					<td>28</td>
					<td>石器时代</td>
					<!-- 28 -->
					<td>${one['5048']}</td>
					<td>${one['5569']}</td>
					<td>${one['6069']}</td>
					<td>${one['5048']-one['5569']-one['6069']}</td>
					
					<td>${one['5049']}</td>
					<td>${one['5570']}</td>
					<td>${one['6070']}</td>
					<td>${one['5049']-one['5570']-one['6070']}</td>
					
					<td>${one['5050']}</td>
					<td>${one['5571']}</td>
					<td>${one['6071']}</td>
					<td>${one['5050']-one['5571']-one['6071']}</td>
					
					<td>${one['5051']}</td>
					<td>${one['5572']}</td>
					<td>${one['6072']}</td>  
					<td>${one['5051']-one['5572']-one['6072']}</td>
				</tr>
				<tr>
					<td>29</td>
					<td>海洋世界</td>
					<!-- 29 -->
					<td>${one['5052']}</td>
					<td>${one['5573']}</td>
					<td>${one['6073']}</td>
					<td>${one['5052']-one['5573']-one['6073']}</td>
					
					<td>${one['5053']}</td>
					<td>${one['5574']}</td>
					<td>${one['6074']}</td>
					<td>${one['5053']-one['5574']-one['6074']}</td>
					
					<td>${one['5054']}</td>
					<td>${one['5575']}</td>
					<td>${one['6075']}</td>
					<td>${one['5054']-one['5575']-one['6075']}</td>
					
					<td>${one['5055']}</td>
					<td>${one['5576']}</td>
					<td>${one['6076']}</td>  
					<td>${one['5055']-one['5576']-one['6076']}</td>
				</tr>
				<tr>
					<td>30</td>
					<td>小红帽</td>
					<!-- 30 -->
					<td>${one['5056']}</td>
					<td>${one['5577']}</td>
					<td>${one['6077']}</td>
					<td>${one['5056']-one['5577']-one['6077']}</td>
					
					<td>${one['5057']}</td>
					<td>${one['5578']}</td>
					<td>${one['6078']}</td>
					<td>${one['5057']-one['5578']-one['6078']}</td>
					
					<td>${one['5058']}</td>
					<td>${one['5579']}</td>
					<td>${one['6079']}</td>
					<td>${one['5058']-one['5579']-one['6079']}</td>
					
					<td>${one['5059']}</td>
					<td>${one['5580']}</td>
					<td>${one['6080']}</td>  
					<td>${one['5059']-one['5580']-one['6080']}</td>
				</tr>
				
					
			</tbody>
		</table>		
	</div>
	</div>	
</div>

<div id="ami_newwindow" style="display: none;">

</div>

<script charset="utf-8" >
$(document).ready(function() {
   /*  $('.dataTables-example').DataTable({
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