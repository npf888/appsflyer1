
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
						<tr>
							<th class=" ">id</th>
							<th class=" ">玩家ID</th>
							<th class=" ">玩家名称</th>
							<th class=" ">性别</th>
							<th class=" ">等级</th>
							<th class=" ">金币</th>
							<th class=" ">房间</th>
							<th class=" ">操作</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${list}" var="humanInfo">
							<tr id="tr_${humanInfo.id}">
								<td>${humanInfo.id}</td>
								<td>${humanInfo.passportId}</td>
								<td>${humanInfo.name}</td>
								<td>${humanInfo.girlFlag}</td>
								<td>${humanInfo.level}</td>
								<td>${humanInfo.gold}</td>
								<td>${humanInfo.bazooRoom}</td>

								<td>
									<div class='hidden-phone visible-desktop btn-group  '>
										<button class='btn btn-mini btn-info'
											onclick="openNewWinow('${pageContext.request.contextPath}/agent.do?method=toAddGoldPage&givePassportId=${humanInfo.passportId }','给予金币');">
											给予金币
										</button>
									</div>
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
<script>
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
		//找到新窗口
		$.get(url, function(data) {

			$('#ami_newwindow').html(data);
			$('.ami_Mask').hide();

		});
		$('#page_title').html(title);
		$('#page_title_nav').html(title);
	}
	
</script>
