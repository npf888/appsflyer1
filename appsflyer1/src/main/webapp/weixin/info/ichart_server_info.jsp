<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>每日在线人数</title>
		<meta name="Description" content="The ichartjs's gallery center,ichartjs 示例中心" />
		<%@ include file="source.jsp" %>
		<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
		<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
		<%@ taglib prefix="s" uri="/WEB-INF/struts-tags.tld" %>
		<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
		<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
		<script type="text/javascript">
		var flows = '${flow}';
		if(flows == null || flows == ""){
			flows = "0";
		}
		
		var labelses = '${labels}';
		if(labelses == null || labelses == ""){
			labelses = "0";
		}
		$(function(){
			var flow = flows.split(",");
			var labels = labelses.split(",");
			var data = [
			         	{
			         		name : 'PV',
			         		value:flow,
			         		color:'#ec4646',
			         		line_width:2
			         	}
			         ];
	        
			/* var labels = ["00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24"]; */
			
			
			
			var chart = new iChart.LineBasic2D({
				render : 'canvasDiv',
				data: data,
				align:'center',
				title : {
					text:'${area}： 时间-在线人数',
					font : '微软雅黑',
					fontsize:24,
					color:'#b4b4b4'
				},
				subtitle : {
					text:'',
					font : '微软雅黑',
					color:'#b4b4b4'
				},
				footnote : {
					text:'ichartjs.com',
					font : '微软雅黑',
					fontsize:11,
					fontweight:600,
					padding:'0 28',
					color:'#b4b4b4'
				},
				width : 1700,
				height : 800,
				shadow:true,
				shadow_color : '#202020',
				shadow_blur : 8,
				shadow_offsetx : 0,
				shadow_offsety : 0,
				background_color:'#2e2e2e',
				tip:{
					enable:true,
					shadow:true,
					listeners:{
						 //tip:提示框对象、name:数据名称、value:数据值、text:当前文本、i:数据点的索引
						parseText:function(tip,name,value,text,i){
							return "<span style='color:#005268;font-size:12px;'>"+labels[i]+":00访问量约:<br/>"+
							"</span><span style='color:#005268;font-size:20px;'>"+value+"人</span>";
						}
					}
				},
				crosshair:{
					enable:true,
					line_color:'#ec4646'
				},
				sub_option : {
					smooth : true,
					label:false,
					hollow:false,
					hollow_inside:false,
					point_size:8
				},
				coordinate:{
					width:1400,
					height:700,
					striped_factor : 0.18,
					grid_color:'#4e4e4e',
					axis:{
						color:'#252525',
						width:[0,0,4,4]
					},
					scale:[{
						 position:'left',	
						 start_scale:0,
						 end_scale:600,
						 scale_space:100,
						 scale_size:2,
						 scale_enable : false,
						 label : {color:'#9d987a',font : '微软雅黑',fontsize:11,fontweight:600},
						 scale_color:'#9f9f9f'
					},{
						 position:'bottom',	
						 label : {color:'#9d987a',font : '微软雅黑',fontsize:11,fontweight:600},
						 scale_enable : false,
						 labels:labels
					}]
				}
			});
			//利用自定义组件构造左侧说明文本
			chart.plugin(new iChart.Custom({
					drawFn:function(){
						//计算位置
						var coo = chart.getCoordinate(),
							x = coo.get('originx'),
							y = coo.get('originy'),
							w = coo.width,
							h = coo.height;
						//在左上侧的位置，渲染一个单位的文字
						chart.target.textAlign('start')
						.textBaseline('bottom')
						.textFont('600 11px 微软雅黑')
						.fillText('访问量(人)',x-40,y-12,false,'#9d987a')
						.textBaseline('top')
						.fillText('(时间)',x+w+12,y+h+10,false,'#9d987a');
						
					}
			}));
		//开始画图
		chart.draw();
	});
		
	function go()
	{
		$('.ami_Mask').show();
		var params = $('#searchForm').formSerialize();
		
		var url = $('#searchForm').attr('action');
		$.post(url,params,function (data){
			
			 $('#PAGECONTENT').html(data);
			
			$('.ami_Mask').hide(); 
		});
	}
	</script>
</head>
<body>
<div class="">
	<div class="row-fluid" >
		<form class="form-inline" action="${path}/simplet.do?method=viewNumList" method="post" id="searchForm">
			开始时间:&nbsp;&nbsp;&nbsp;&nbsp; 
			<input type="text"  name="startTime" value="${startTime}" class="Wdate" 
			onClick="WdatePicker({dateFmt:'yyyy_MM_dd'})" 
			style="width:100px" requiry="true"/>
			
			&nbsp;&nbsp;&nbsp;&nbsp;
			
			结束时间:&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="text"   name="endTime" value="${endTime}" class="Wdate" 
			onClick="WdatePicker({dateFmt:'yyyy_MM_dd'})" 
			style="width:100px" requiry="true"/>
			
			&nbsp;&nbsp;&nbsp;&nbsp;
			
			地区:&nbsp;&nbsp;&nbsp;&nbsp;
			<select name="area">
				<c:forEach var="rArea" items="${areas }">
					<option 
						<c:if test="${area == rArea.area }">
							selected="selected"
						</c:if>
						value="${rArea.area}"
					>${rArea.area}</option>
				
				</c:forEach>
			</select>
			
			&nbsp;&nbsp;&nbsp;&nbsp;
			<button type="button"  style="margin-left:10px" class="btn btn-mini btn-info" onclick="go();">检索</button>	
		</form>
	</div>
	<div id='canvasDiv'>
	</div>
</div>
</body>
</html>

