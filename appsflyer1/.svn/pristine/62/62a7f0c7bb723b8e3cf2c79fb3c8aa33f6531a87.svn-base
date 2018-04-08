
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/WEB-INF/struts-tags.tld"%>
<div id="ami_main">
	<div class="row-fluid">
		<div class="row-fluid" style="background-color: #FFFFFF">
			<div class="span8">
				<div class="ami_table_header">
					<span class="ami_table_header_title">推送配置</span> <span
						class="ami_table_header_btn">
						<button class="btn btn-mini btn-info " data-rel="tooltip"
							data-placement="left" title="" data-original-title="新增推送"
							id="add">+</button>
					</span>
				</div>

				<table id="table_bug_report"
					class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th class=" ">id</th>
							<th class=" ">推送类型描述</th>
							<th class=" ">推送标题</th>
							<th class=" ">推送内容</th>
							<th class=" ">优先级</th>
							<th class=" ">额外显示类型</th>
							<th class=" ">发送时间</th>
							<th class=" ">过滤条件</th>
							<th class=" ">操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${jpushConfig}" var="config">
							<tr id="tr_${config.id}">
								<td>${config.id}</td>
<%-- 								<td>${config.jpushType}</td> --%>
								<td>${config.description}</td>
								<td>${config.title}</td>
								<td>${config.content}</td>
								<td>${config.priority}</td>
								<td>${config.notificationType}</td>
								<td>${config.sendTime}</td>
								<td>${config.filterParams}</td>

								<td>
									<div class='hidden-phone visible-desktop btn-group  '>
										<button class='btn btn-mini btn-info'
											onclick="openNewWinow('jpush.do?method=toEdit&id=${config.id }','推送条件编辑');">
											修改配置
										</button>
										<button class='btn btn-mini btn-danger bootbox-confirm'
											onclick="doPush('${config.id}')">
											立即推送
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

 		//openNewWinow('right.do?method=toAdd', '新建发送条件');

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

	function doPush(id) {
		bootbox.confirm("确定推送吗?", function(result) {
			if (result) {
				$('.ami_Mask').show();
				$.get('jpush.do?method=pushNow&id=' + id, function(data) {
					$('.ami_Mask').hide();
					bootbox.alert("	开始发送！");
				});
			}
		});
	}
</script>
