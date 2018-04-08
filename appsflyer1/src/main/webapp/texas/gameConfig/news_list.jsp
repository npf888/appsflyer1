<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/WEB-INF/struts-tags.tld" %>
<%@ taglib   uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>  
<div id="ami_main">
<div class="row-fluid">	
			<form class="form-inline" action="news.do?method=queryNews" id="searchForm">
				<label>Useraccount</label>
				<input  type="text" class="input-medium"  name="useraccount" value="${account }"  id="account" ></input>				
				<label >Username</label>
				<input type="text" class="input-medium" value="${username }" name="username"  ></input>
				<button type="button"  style="margin-left:10px" class="btn btn-mini btn-info" onClick="go(1)">检索</button>
			</form>
</div>	
<hr/>

<div class="row-fluid">
	
		<div class="ami_table_header"><span class="ami_table_header_title">跑马灯</span>
			<span class="ami_table_header_btn">		
			<button class="btn btn-mini btn-info " data-rel="tooltip" data-placement="left" title="" id="add">添加跑马灯</button>
			</span>
		</div>
		<table id="table_report" class="table table-striped table-bordered table-hover">
			<thead>			
				<tr>
				<th>No.</th>
					<th>ID</th>
					<th>跑马灯内容</th>
					<th>开始时间</th>
					<th>结束时间</th>
					<th>每日开始时间</th>
					<th>每日结束时间</th>
					<th>间隔时间</th>	
					<th>更新时间</th>
					<th>创建时间</th>
					<th>管理</th>		
				</tr>				
			</thead>
			<tbody>				
			 <c:forEach var="news" items="${PAGER.items}" varStatus="status">
				<tr id="tr_${news.id }">
					
					<td>${status.index+1+ PAGER.start}</td>
					<td><a onclick="openNewWinow('news.do?method=loadByID&id=${news.id}')">${news.id}</a></td>
					<td>${news.content}</td>
					<td>${news.start_time}</td>
					<td>${news.end_time}</td>
					<td>
					<fmt:formatNumber value="${(news.dailystarttime/60000-news.dailystarttime/60000%60)/60%24}" pattern="##" minIntegerDigits="2"></fmt:formatNumber>:<fmt:formatNumber value="${news.dailystarttime/60000%60}" pattern="##" minIntegerDigits="2" ></fmt:formatNumber>
					</td>
					<td>
					<fmt:formatNumber value="${(news.dailyendtime/60000-news.dailyendtime/60000%60)/60%24}" pattern="##" minIntegerDigits="2" ></fmt:formatNumber>:<fmt:formatNumber value="${news.dailyendtime/60000%60}" pattern="##" minIntegerDigits="2" ></fmt:formatNumber></td>
					<td>${news.intervaltime}</td>
	                <td>${news.update_time}</td>
	                <td>${news.create_time}</td>

					<td>				
						<div class='hidden-phone visible-desktop btn-group'>						
							<button class='btn btn-mini btn-info' onclick="openframe('news.do?method=toEditnews&id=${news.id }&iscancel=1','Edit news info');"><i class='icon-edit'></i></button>
							<button class='btn btn-mini btn-danger' onclick="doDelete('${news.id }')"><i class='icon-trash'></i></button>						
						</div>
					</td>
				</tr>
				
			  </c:forEach>
			  <h5 style="color:orange">注：所显示时间都是中国时间</h5>	
			</tbody>
		</table>		
		<jsp:include page="/common/page.jsp"></jsp:include>
		
	</div>	
</div>
<div id="ami_newwindow" style="display: none;">

</div>

<script type="text/javascript">

	$('[data-rel=tooltip]').tooltip();

	$('#add').click(function(){	
		openNewWinow(geturl('news.do?method=toEditNews&iscancel=1'),'添加跑马灯');			
	});


	function doDelete(id) {
		bootbox.confirm("删除这个跑马灯?", function(result) {
			if (result) {

				$('.ami_Mask').show();
				var url = "news.do?method=deleteNews&&id=" + id;
				$.get(url, function(data) {

					$('.ami_Mask').hide();
					$('#tr_' + id).remove();
					bootbox.alert(data);
				});
			}
		});
	}
</script>