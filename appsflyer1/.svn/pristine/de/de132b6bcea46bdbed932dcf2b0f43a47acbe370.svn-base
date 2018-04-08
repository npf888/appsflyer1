
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/WEB-INF/struts-tags.tld"%>
<div id="ami_main">
	<div class="row-fluid">
		<div class="row-fluid" style="background-color: #FFFFFF">
			<div class="span8">
				<div class="ami_table_header">
					<span class="ami_table_header_title">子集代理商</span> 
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
						<tr class="arrth">
							<th class=" ">id</th>
							<th class=" ">代理商ID</th>
							<th class=" ">代理商昵称</th>
							<th class="hidden-xs">用户名</th>
							<th class="hidden-xs">密码</th>
							<th class="hidden-xs">金币</th>
							<th class="hidden-xs">电话</th>
							<th class="hidden-xs">身份证</th>
							<th class="hidden-xs">地址</th>
							<th class="hidden-xs">微信号码</th>
							<th class="hidden-xs">qq号码</th>
							<th class="hidden-xs">父ID</th>
							<th class="hidden-xs">状态</th>
							<th class="hidden-xs">红包权限</th>
							<th class="hidden-xs">创建时间</th>
							<th class="hidden-xs">操作</th>
							<th class="visible-xs">操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${list}" var="agent">
							<tr id="tr_${agent.id}">
								<td class="arr">${agent.id}</td>
								<td class="arr">${agent.passportId}</td>
								<td class="arr">${agent.nickname}</td>
								<td class="hidden-xs arr">${agent.username}</td>
								<td class="hidden-xs arr">${agent.passwords}</td>
								<td class="hidden-xs arr">${agent.gold}</td>
								<td class="hidden-xs arr">${agent.telephone}</td>
								<td class="hidden-xs arr">${agent.identity}</td>
								<td class="hidden-xs arr">${agent.address}</td>
								<td class="hidden-xs arr">${agent.wx}</td>
								<td class="hidden-xs arr">${agent.qq}</td>
								<td class="hidden-xs">${agent.parentId}</td>
								<td class="hidden-xs">${agent.state}</td>
								<td class="hidden-xs">
									<c:if test="${agent.sendPackage==0}">
										<button onclick="sendRed(1,${agent.passportId})"><font color="blue">是</font></button>
									</c:if>
									<c:if test="${agent.sendPackage==1}">
										<button onclick="sendRed(0,${agent.passportId})"><font color="red">否</font></button>
									</c:if>
									
								</td>
								<td class="hidden-xs">${agent.createTime}</td>
								<td class="hidden-xs">
									<div class='hidden-phone visible-desktop btn-group  '>
										<button class='btn btn-mini btn-info'
											onclick="openNewWinow('${pageContext.request.contextPath}/agent.do?method=toAddGoldPage&givePassportId=${agent.passportId }','给予金币');">
											给予金币
										</button>
									</div>
									<div class='hidden-phone visible-desktop btn-group  '>
										<button class='btn btn-mini btn-info'
											onclick="openNewWinow('${pageContext.request.contextPath}/agent.do?method=noticeRedPackage&givePassportId=${agent.passportId }','发送红包');">
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
	$('#add').click(function() {

 		openNewWinow('${pageContext.request.contextPath}/agent.do?method=addPage', '添加子代理	');

	});
	$('.more-bg').click(function(){
		$('.more-box').hide();
	})
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

	
	
	function sendRed(authority,passportId){
		
		$.post("${pageContext.request.contextPath}/agent.do?method=changeAuthority",{authority:authority,passportId:passportId},function(data){
			
		});
		bootbox.alert("保存成功",
				function() {
					nav('${pageContext.request.contextPath}/agent.do?method=list','代理商子集 ');
					});  
	}
</script>
