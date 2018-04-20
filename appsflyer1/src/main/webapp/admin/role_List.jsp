 <%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/WEB-INF/struts-tags.tld"%>
<script src="../../js/jquery-1.9.1.min.js"></script>
<div id="ami_main">
<div class="row-fluid">
<div class="row-fluid" style="background-color: #FFFFFF">
<div class="span8">
<div class="ami_table_header"><span class="ami_table_header_title">角色列表</span>

<span class="ami_table_header_btn"> <s:if
	test="%{#session.groupright.indexOf('206002001')!=-1}">
	<button class="btn btn-mini btn-info " data-rel="tooltip"
		data-placement="left" title="" data-original-title="新增角色" id="add">+</button>
	<button class="btn btn-mini btn-info " data-rel="tooltip"
		data-placement="left" title="" data-original-title="刷新角色" id="refresh">刷新角色</button>
</s:if> </span></div>

<table id="table_bug_report"
	class="table table-striped table-bordered table-hover">
	<thead>
		<tr>
			<th class=" ">角色名称</th>
			<th class=" ">角色描述</th>
			<th class=" ">基本操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${roleMap}" var="one">
			<tr id="tr_${one.value.id }">
				<td>${one.value.rolename}</td>
				<td>${one.value.des}</td>
				<td class=" ">
				<div class='hidden-phone visible-desktop btn-group  '><s:if
					test="%{#session.groupright.indexOf('206002002')!=-1}">
					<button class='btn btn-mini btn-info'
						onclick="openNewWinow('right.do?method=toEdit&id=${one.value.id }&iscancel=1','用户编辑');"><i
						class='icon-edit'></i></button>
				</s:if> <s:if test="%{#session.groupright.indexOf('206002003')!=-1}">
					<button class='btn btn-mini btn-danger bootbox-confirm'
						onclick="doDelete('${one.value.id }')"><i
						class='icon-trash'></i></button>
				</s:if></div>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

</div>
<!--/span--></div>
<!--/row--></div>
</div>

<div id="ami_newwindow" style="display: none;"></div>
<script type="text/javascript" src="js/bootbox.min.js"></script>
<script>
	$('#add').click(function() {

		openNewWinow('right.do?method=toAdd', '新建角色');

	});
	$('#refresh').click(function() {

		$.get('right.do?method=toAdd');

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

	function doDelete(id) {

		bootbox.confirm("你确定删除吗?", function(result) {
			if (result) {

				$('.ami_Mask').show();

				$.get('right.do?method=doDelete&id=' + id, function(data) {

					$('.ami_Mask').hide();
					$('#tr_' + id).remove();

					bootbox.alert("	删除成功！！");

				});

			}
		});

	}
</script>
