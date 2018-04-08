<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<style>
.bor{border:1px dashed #F00;width:600px;height:200px;margin-top:10px} 
</style>
<div id="ami_main">
<div class="row-fluid">	
</div>	
<hr/>

<div class="row-fluid">
	
		<div class="ami_table_header"><span class="ami_table_header_title">活动列表--<font color="grey" size="20">
			<c:if test="${activity.activityType==1}">
						大玩家
					</c:if>
					<c:if test="${activity.activityType==2}">
						幸运牌行
					</c:if>
					<c:if test="${activity.activityType==3}">
						充值
					</c:if>
					<c:if test="${activity.activityType==4}">
						4-无
					</c:if>
					<c:if test="${activity.activityType==5}">
						每日登陆
					</c:if>
					<c:if test="${activity.activityType==6}">
						经验活动
					</c:if>
					<c:if test="${activity.activityType==7}">
						幸运老虎机
					</c:if>
					<c:if test="${activity.activityType==8}">
						冲级活动
					</c:if>
					<c:if test="${activity.activityType==9}">
						连续游戏送大礼
					</c:if>
					<c:if test="${activity.activityType==10}">
						消耗返利 
					</c:if>
					<c:if test="${activity.activityType==11}">
						广交好友
					</c:if>
					<c:if test="${activity.activityType==12}">
						情有独钟
					</c:if>
					<c:if test="${activity.activityType==13}">
						我是大赢家
					</c:if>
					<c:if test="${activity.activityType==14}">
						极限追求 
					</c:if>
					<c:if test="${activity.activityType==15}">
						累计充值(美元)
					</c:if>
					<c:if test="${activity.activityType==16}">
						连续累计充值(特指天数)
					</c:if>
					<c:if test="${activity.activityType==17}">
						全服累计充值
					</c:if>
					<c:if test="${activity.activityType==18}">
						累计充值(次数)
					</c:if>
		
		</font><font color="blue" size="6">--${activity.title}--${activity.activityDesc}</font></span>
			<span class="ami_table_header_btn">		
			<button class="btn btn-mini btn-info " data-rel="tooltip" data-placement="left" title="" onclick="addActivity();" id="add">添加活动</button>
			</span>
		</div>
		<table id="table_report" class="table table-striped table-bordered table-hover">
			<thead>			
				<tr>
					<th>活动内容</th>
					
				</tr>				
			</thead>
			<tbody>				
					
				<tr id="tr_${activity.id }">
					
					<td>
						<c:forEach var="item" items="${rulePackList }">
							<div class="bor">
						
								1、最多领取值：${item.maxDrows}<br/>
								<br/>
							    2、条件：${item.paramsMap}<br/>
								<br/>
								3、奖励：${item.rewardList}
								<br/>
								<br/>
							</div>
						</c:forEach>

					</td>
				</tr>
				
					
			</tbody>
		</table>		
		<jsp:include page="/common/page.jsp"></jsp:include>
		
	</div>	
</div>
<div id="ami_newwindow" style="display: none;">

</div>

<script type="text/javascript">

function openframe(url,title)
{
	$('#PAGECONTENT').show();
	$('#div_frame').hide();
	$.ajax({
		  url: url,
		  success: function(data)
			  		{
						$('.ami_Mask').hide();	
						$('#PAGECONTENT').html(data)
					},
		  error:function(data){
			  $('.ami_Mask').hide();	
			 $('#PAGECONTENT').html(data.responseText)
			  
		  
		  	},
		  dataType: 'html'
		});
	function addActivity(){
		openNewWinow(geturl('activity.do?method=toEditActivity&iscancel=1'),'Add Activity');
	}

	function edit(id) {
		bootbox.confirm("Are you sure to block this user account?", function(
				result) {
			if (result) {

				$('.ami_Mask').show();
				var url = "app.do?method=doBlock&uuid=" + id;
				$.get(url, function(data) {
					$('.ami_Mask').hide();
					bootbox.alert("	User Blocked!");
				});
			}
		});
	}
	function doDelete(id) {

		bootbox.confirm("Delete this Activity?", function(result) {
			if (result) {

				$('.ami_Mask').show();
				var url = "activity.do?method=deleteActivity";
				$.post(url, {id:id},function(data) {
					$('.ami_Mask').hide();
					$('#tr_' + id).remove();
					bootbox.alert(data);
				});
			}
		});
	}
</script>