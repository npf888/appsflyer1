
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/WEB-INF/struts-tags.tld"%>
<div id="ami_main">
	<div class="row-fluid">
		<div class="row-fluid" style="background-color: #FFFFFF">
			<div class="span8">
				<div class="ami_table_header">
					<span class="ami_table_header_title">自己</span> 
					<span
						class="ami_table_header_btn">
						<button class="btn btn-mini btn-info " data-rel="tooltip"
							data-placement="left" title="" data-original-title="添加子代理商"
							id="add">增加代理商</button>
					</span>
				</div>

				<table id="table_bug_report"
					class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th class=" ">id</th>
							<th class=" ">代理商ID</th>
							<th class=" ">代理商昵称</th>
							<th class=" ">用户名</th>
							<th class=" ">密码</th>
							<th class=" ">金币</th>
							<th class=" ">电话</th>
							<th class=" ">身份证</th>
							<th class=" ">地址</th>
							<th class=" ">微信号码</th>
							<th class=" ">qq号码</th>
							<th class=" ">父ID</th>
							<th class=" ">状态</th>
							<th class=" ">创建时间</th>
							<th class=" ">操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${list}" var="agent">
							<tr id="tr_${agent.id}">
								<td>${agent.id}</td>
								<td>${agent.passportId}</td>
								<td>${agent.nickname}</td>
								<td>${agent.username}</td>
								<td>${agent.passwords}</td>
								<td>${agent.gold}</td>
								<td>${agent.telephone}</td>
								<td>${agent.identity}</td>
								<td>${agent.address}</td>
								<td>${agent.wx}</td>
								<td>${agent.qq}</td>
								<td>${agent.parentId}</td>
								<td>${agent.state}</td>
								<td>${agent.createTime}</td>

								<td>
									<div class='hidden-phone visible-desktop btn-group  '>
										<button class='btn btn-mini btn-info'
											onclick="openNewWinow('${pageContext.request.contextPath}/agent.do?method=editSelfInfo&id=${agent.id }','修改个人信息');">
											修改个人信息
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
	$('#add').click(function() {

 		openNewWinow('${pageContext.request.contextPath}/agent.do?method=addPage', '添加子代理	');

	});

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
	/**
	 * 回退
	 */
	function goback() {
		$('.ami_Mask').show();
		//主窗口隐错
		$('#ami_main').show();
		$('#ami_newwindow').html('');
		$('#ami_newwindow').hide();
		//主窗口隐错
		$('.ami_Mask').hide();
	}

</script>
