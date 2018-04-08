<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<div id="ami_main">
<div class="row-fluid">	
			<form class="form-inline" action="activity.do?method=queryActivity" id="searchForm">
				<label>Useraccount</label>
				<input  type="text" class="input-medium"  name="useraccount" value="${account }"  id="account" ></input>				
				<label >Username</label>
				<input type="text" class="input-medium" value="${username }" name="username"  ></input>
				<button type="button"  style="margin-left:10px" class="btn btn-mini btn-info" onClick="go(1)">检索</button>
			</form>
</div>	
<hr/>

<div class="row-fluid">
	
		<div class="ami_table_header"><span class="ami_table_header_title">活动列表</span>
			<span class="ami_table_header_btn">		
			<button class="btn btn-mini btn-info " data-rel="tooltip" data-placement="left" title="" onclick="addActivity();" id="add">添加活动</button>
			</span>
		</div>
		<table id="table_report" class="table table-striped table-bordered table-hover">
			<thead>			
				<tr>
				<th>No.</th>
					<th>ID</th>
					<th>活动类型</th>
					<th>标题</th>
					<th>描述</th>
					<th>活动图片</th>
					<th>活动内容</th>
					<th>开始时间</th>
					<th>结束时间</th>
					
					<th>每天重复</th>
					<th>创建时间</th>
					<th>更新时间</th>	
					<th>管理</th>		
				</tr>				
			</thead>
			<tbody>				
			 <c:forEach var="activity" items="${PAGER.items}" varStatus="status">
					
				<tr id="tr_${activity.id }">
					
					<td>${status.index+1+ PAGER.start}</td>
					<td><a onclick="openNewWinow('activity.do?method=loadByID&id=${activity.id}')">${activity.id}</a></td>
					<c:if test="${activity.activitytype==1}">
						<td>大玩家</td>
					</c:if>
					<c:if test="${activity.activitytype==2}">
						<td>幸运牌行</td>
					</c:if>
					<c:if test="${activity.activitytype==3}">
						<td>充值</td>
					</c:if>
					<c:if test="${activity.activitytype==4}">
						<td>4-无</td>
					</c:if>
					<c:if test="${activity.activitytype==5}">
						<td>每日登陆</td>
					</c:if>
					<c:if test="${activity.activitytype==6}">
						<td>经验活动</td>
					</c:if>
					<c:if test="${activity.activitytype==7}">
						<td>幸运老虎机</td>
					</c:if>
					<c:if test="${activity.activitytype==8}">
						<td>冲级活动</td>
					</c:if>
					<c:if test="${activity.activitytype==9}">
						<td>连续游戏送大礼</td>
					</c:if>
					<c:if test="${activity.activitytype==10}">
						<td>消耗返利 </td>
					</c:if>
					<c:if test="${activity.activitytype==11}">
						<td>广交好友</td>
					</c:if>
					<c:if test="${activity.activitytype==12}">
						<td>情有独钟</td>
					</c:if>
					<c:if test="${activity.activitytype==13}">
						<td>我是大赢家</td>
					</c:if>
					<c:if test="${activity.activitytype==14}">
						<td>极限追求 </td>
					</c:if>
					<c:if test="${activity.activitytype==15}">
						<td>累计充值(美元)</td>
					</c:if>
					<c:if test="${activity.activitytype==16}">
						<td>连续累计充值(特指天数)</td>
					</c:if>
					<c:if test="${activity.activitytype==17}">
						<td>全服累计充值</td>
					</c:if>
					<c:if test="${activity.activitytype==18}">
						<td>累计充值(次数)</td>
					</c:if>
					<td>${activity.title}</td>
					<td>${activity.activitydesc}</td>
					<td><img src="${activity.img_path}"/></td>
					<td><a href="#" onclick="detail(${activity.id})">点击查看详情</a></td>
					<td>${activity.start_time}</td>
					<td>${activity.end_time}</td>					
					<td>${activity.daily}</td>
					<td>${activity.create_time}</td>
					<td>${activity.update_time}</td>

					<td>				
						<div class='hidden-phone visible-desktop btn-group'>						
							<button class='btn btn-mini btn-info' onclick="openframe('activity.do?method=toEditActivity&id=${activity.id }&iscancel=1','Edit Activity info');"><i class='icon-edit'></i></button>
							<button class='btn btn-mini btn-danger' onclick="doDelete('${activity.id }')"><i class='icon-trash'></i></button>						
						</div>
					</td>
				</tr>
				
			  </c:forEach>
					
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
	//url=geturl(url);
	/* $('#frame').attr('src','');
	$('#page_title_nav').html(title);
	
	$('#div_frame').show();
	$('#PAGECONTENT').hide();
	$('#frame').attr('src',url);
	$('.ami_Mask').hide();	 */ 
}
	/* function geturl(url)
	{
		url+="&random="+Math.random();
		
		return url;
	}
	$('[data-rel=tooltip]').tooltip();
	$('#add').click(function(){	
		openNewWinow(geturl('activity.do?method=toEditActivity&iscancel=1'),'Add Activity');			
	}); */
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
	
	function detail(id){
		var url = "activity.do?method=detail&activityId="+id;
		$.get(url, function(data){
			$('.ami_Mask').hide();	
			$('#PAGECONTENT').html(data)					
		});
	}
</script>