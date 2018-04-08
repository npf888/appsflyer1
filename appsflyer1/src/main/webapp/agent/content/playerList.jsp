
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/WEB-INF/struts-tags.tld"%>
<div id="ami_main">
<div class="row-fluid">	
		<form class="form-inline" action="${pageContext.request.contextPath}/bazooPlayer.do?method=list" id="searchForm">
			<label>用户ID|昵称:</label>
			<input  type="text" class="input-medium"  name="passportId" value="${passportId }"  id="searchColumn" ></input>				
			<button type="button"  style="margin-left:10px" class="btn btn-mini btn-info" onClick="go();">检索</button>
		</form>
</div>	
<hr/>
	<div class="row-fluid">
		<div class="row-fluid" style="background-color: #FFFFFF">
			<div class="span8">
				<div class="ami_table_header">
					<span class="ami_table_header_title">玩家</span> 
					<span
						class="ami_table_header_btn">
					</span>
				</div>

				<table id="table_bug_report"
					class="table table-striped table-bordered table-hover">
					<thead>
						<tr class="arrth">
							<th class=" arr">id</th>
							<th class=" arr">玩家ID</th>
							<th class=" arr">玩家名称</th>
							<th class=" arr">性别</th>
							<th class="hidden-xs arr">等级</th>
							<th class="hidden-xs arr">金币</th>
							<th class="hidden-xs arr">房间</th>
							<th class="hidden-xs arr">操作</th>
							<th class="visible-xs">操作</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${list}" var="humanInfo">
							<tr id="tr_${humanInfo.id}">
								<td class=" arr">${humanInfo.id}</td>
								<td class=" arr">${humanInfo.passportId}</td>
								<td class=" arr">${humanInfo.name}</td>
								<td class=" arr">${humanInfo.girlFlag}</td>
								<td class="hidden-xs arr">${humanInfo.level}</td>
								<td class="hidden-xs arr">${humanInfo.gold}</td>
								<td class="hidden-xs arr">${humanInfo.bazooRoom}</td>

								<td class="hidden-xs">
									<div class='hidden-phone visible-desktop btn-group  '>
										<button class='btn btn-mini btn-info'
											onclick="openNewWinow('${pageContext.request.contextPath}/agent.do?method=toAddGoldPage&givePassportId=${humanInfo.passportId }','给予金币');">
											给予金币
										</button>
										
									</div>
									<div class='hidden-phone visible-desktop btn-group  '>
										<button class='btn btn-mini btn-info'
											onclick="openNewWinow('${pageContext.request.contextPath}/agent.do?method=noticeRedPackage&givePassportId=${humanInfo.passportId }','发送红包');">
											发送红包
										</button>
									</div>
								</td>
								<td class="visible-xs">
									<button class="btn btn-primary btn-open">查看</button>
								</td>
							</tr>
					</c:forEach>
					</tbody>
				</table>

			</div>
			<!--/span-->
		</div>
		<!--/row-->
	</div>
</div>

<div id="ami_newwindow" style="display: none;"></div>
<div class="more-box">
	<div class="more-bg"></div>
	<div class="more-inner">
		<ul>
			
		</ul>
	</div>
</div>
<script>

$('.btn-open').click(function(){
	var len=$('.arrth').find('th').length;
	var str='';
	$('.more-inner').empty();
	for(b=0;b<len;b++){
		str+="<li><b>"+$('.arrth').find('th').eq(b).text()+"</b>:<span>"+$(this).parents().siblings('.arr').eq(b).text()+"</span></li>"
	}
	var passportId = 0;
	for(b=0;b<len;b++){
		if(b==1){
			passportId=$(this).parents().siblings('.arr').eq(b).text();
			break;
		}
	}
		str+="<li><span><button class='btn btn-mini btn-info' onclick=\"openNewWinow('${pageContext.request.contextPath}/agent.do?method=toAddGoldPage&givePassportId="+passportId+"');\">给予金币</button></span></li><br/>"
		str+="<li><span><button class='btn btn-mini btn-info' onclick=\"openNewWinow('${pageContext.request.contextPath}/agent.do?method=noticeRedPackage&givePassportId="+passportId+"');\">发送红包</button></span></li>"
	
	$('.more-inner').append(str);
	$('.more-box').show();
});

$('.more-bg').click(function(){
	$('.more-box').hide();
})

function go()
{
	$('.ami_Mask').show();
	var params = $('#searchForm').formSerialize();
	
	var url = $('#searchForm').attr('action') ;
	$.post(url,params,function (data){
	
	$('#PAGECONTENT').html(data);
	
	$('.ami_Mask').hide();
	
	});
}

	/**
	 * 在本页面打开新的窗口 支持回退
	 */
	function openNewWinow(url, title) {
		$('.ami_Mask').show();
		//主窗口隐错
		$('#ami_main').hide();
		$('#ami_newwindow').show();
		$('.more-box').hide();
		//找到新窗口
		$.get(url, function(data) {

			$('#ami_newwindow').html(data);
			$('.ami_Mask').hide();

		});
		$('#page_title').html(title);
		$('#page_title_nav').html(title);
	}
	
</script>
