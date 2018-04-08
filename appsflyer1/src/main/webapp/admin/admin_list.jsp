<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/WEB-INF/struts-tags.tld" %>
<div id="ami_main">
<div class="row-fluid">	
			<form class="form-inline" action="admin.do?method=query" id="searchForm">
				<label>账号</label>
				<input  type="text" class="input-medium"  name="account" value="${account }"  id="account" ></input>				
				<label >姓名</label>
				<input type="text" class="input-medium" value="${username }" name="username"  ></input>
				<button type="button"  style="margin-left:10px" class="btn btn-mini btn-info" onClick="go(1)">检索</button>
			</form>
</div>	
<hr/>

<div class="row-fluid">
	
		<div class="ami_table_header"><span class="ami_table_header_title">用户列表</span>
		
		<span class="ami_table_header_btn">
		<s:if test="%{#session.groupright.indexOf('206001001')!=-1}">
			<button class="btn btn-mini btn-info " data-rel="tooltip" data-placement="left" title="" data-original-title="新增管理员" id="add">+</button>
		</s:if>
		</span>
		
		</div>
		<table id="table_report" class="table table-striped table-bordered table-hover">
			<thead>
			
				<tr>
					<th>序号</th>
					<th>账号</th>
					<th>单位</th>
					<th>联系电话</th>
					<th>角色</th>
					<th>基本操作</th>
				</tr>				
			</thead>
			<tbody>				
			
				  <c:forEach var="user" items="${PAGER.items}" varStatus="status">
					
				<tr id="tr_${user.id }">
					
					<td>${status.index+1+ PAGER.start}</td>
					<td>${user.account }</td>
	                 <td>${user.username } </td>
	                 <td>${user.phone }</td>
	                 <td>${user.grouplist}</td>
					<td>
						<div class='hidden-phone visible-desktop btn-group'>							
						<s:if test="%{#session.groupright.indexOf('206001002')!=-1}">
							<button class='btn btn-mini btn-info' onclick="openNewWinow('admin.do?method=toEdit&id=${user.id }&iscancel=1','用户编辑');"><i class='icon-edit'></i></button>
						</s:if>
						<s:if test="%{#session.groupright.indexOf('206001003')!=-1}">
							<button class='btn btn-mini btn-danger' onclick="doDelete('${user.id }')"><i class='icon-trash'></i></button>						
						</s:if>
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

$('[data-rel=tooltip]').tooltip();

	
		
	
			
	$('#add').click(function(){
		
		openNewWinow('admin.do?method=toEdit&iscancel=1','新增用户');
		
	});



$("a[name='save']").click(function(){
    var paras = {};
    paras.projectName = $(this).parents("#add_proj").find("input[name='projectName']").val();
    paras.gameTitle = $(this).parents("#add_proj").find("input[name='gameTitle']").val();
    paras.description = $(this).parents("#add_proj").find("input[name='description']").val();
    //console.log(paras);
    //addProj(paras);

	alert(222);
    $("#add_proj").modal("hide");
});

$("a[data-toggle='modal']").click(function(){alert(222)})

function doDelete(id)
{
	
	bootbox.confirm("你确定删除吗?", function(result) {
		if(result) {
			
			$('.ami_Mask').show();
			
			$.get('admin.do?method=doDelete&id='+id,function (data){
				
				$('.ami_Mask').hide();
				$('#tr_'+id).remove();
				
				bootbox.alert("	删除成功！！");
				
				
			});
			
			
		}
	});
	
}

</script> 