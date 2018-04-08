<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/WEB-INF/struts-tags.tld" %>
<div id="ami_main">
<div class="row-fluid">	
			<form class="form-inline" action="config.do?method=dictionary" id="searchForm">
				<label>类型</label>
				<select name="dtypeid" >
					<c:forEach items="${dictionary_type}" var="one">
						<c:if test="${one.issys==0 ||_loginInfo.account=='dev'&&one.issys==1}">
							<option value="${one.dtypeid }" <c:if test="${params.dtypeid==one.dtypeid }">selected</c:if> >${one.dtye_title}</option>
						</c:if>
						
					</c:forEach>
				</select>
				
				<label>名字</label>
				
				<input type="text" class="input-medium" value="${params.dtitle }" name="dtitle"  ></input>
				
				<button type="button"  style="margin-left:10px" class="btn btn-mini btn-info" onClick="go(1)">检索</button>
			</form>
</div>	
<hr/>

<div class="row-fluid">
	
		<div class="ami_table_header"><span class="ami_table_header_title">字典列表</span>
		
		<span class="ami_table_header_btn">
		<s:if test="%{#session.groupright.indexOf('205001001')!=-1 || #session._loginInfo.account=='dev'}">
			<button class="btn btn-mini btn-info " data-rel="tooltip" data-placement="left" title="" data-original-title="增加配置" id="add">+</button>
		</s:if>
		</span>
		
		</div>
		<table id="table_report" class="table table-striped table-bordered table-hover">
			<thead>
			
				<tr>
					<th>序号</th>
					<th>数据项</th>
					<th>基本操作</th>
				</tr>				
			</thead>
			<tbody>				
			
				  <c:forEach var="user" items="${PAGER.items}" varStatus="status">
					
				<tr id="tr_${user.id }">
					
					<td>${status.index+1+ PAGER.start}</td>
					<td>${user.dtitle}</td>
					<td>
						<div class='hidden-phone visible-desktop btn-group'>							
						<s:if test="%{#session.groupright.indexOf('205001002')!=-1}">
							<button class='btn btn-mini btn-info modcontent' dataid='${user.id }' nameid='${user.dtitle}' ><i class='icon-edit'></i></button>
						</s:if>
						<s:if test="%{#session.groupright.indexOf('205001003')!=-1}">
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


<!-- Modal 弹出框内容 begin -->
<div id="mod_proj" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    <h3 id="myModalLabel">内容修改</h3>
  </div>
  <div class="modal-body">
   <form class="form-inline" action="userlogin.do?method=query_historyInfo" id="searchForm">		

            <div class="controls" style="width:440px; overflow:hidden;">
         	    <label style="float:left;overflow:hidden; display:inline;letter-spacing:8px;" class="offset1" for="start_time">原数据 </label>
				<input readonly type="text" id="meaningReadonly" value="" style="width:300px;float:left;overflow:hidden; display:inline">

			</div>
			<br/>
			

			<div class="controls" style="overflow: hidden; width:440px;">
                <label style=" float:left; overflow:hidden; display:inline;letter-spacing:8px;" class="offset1" for="start_time">新数据</label>
         	    <input id="content_id" type="text"  value="" style="width:300px; float:left;overflow:hidden; display:inline">
         	    <span id="judgeContent" style="display:none" value=""> </span>

            </div>
		</form>
  </div>
  <div class="modal-footer">
    <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
    <button class="btn btn-primary" id="save">修改并保存</button>
  </div>
</div>

<!-- Modal 弹出框内容  end -->


<!-- Modal 弹出增加框内容 begin -->
<div id="add_proj" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    <h3 id="myModalLabel">添加配置</h3>
  </div>
  <div class="modal-body">
   <form class="form-inline" action="userlogin.do?method=query_historyInfo" id="searchForm">		

				
				
			<div class="controls" style="width:440px; overflow:hidden;">
         	    <label style="float:left;overflow:hidden; display:inline;letter-spacing:8px;" class="offset1" for="start_time">类型 </label>
				<select id="dtypeid" style="width:310px;float:left;overflow:hidden; display:inline">
					<c:forEach items="${dictionary_type}" var="one">
						<c:if test="${one.issys==0 }">
							<option value="${one.dtypeid }" <c:if test="${params.dtypeid==one.dtypeid }"></c:if> >${one.dtye_title}</option>
						</c:if>
						<c:if test="${_loginInfo.account=='dev'&&one.issys==1 }">
							<option value="${one.dtypeid }" <c:if test="${params.dtypeid==one.dtypeid }"></c:if> >${one.dtye_title}</option>
						</c:if>
					</c:forEach>
				</select>

			</div>
			
			<br/>
            <div class="controls" style="width:440px; overflow:hidden;">
         	    <label style="float:left;overflow:hidden; display:inline;letter-spacing:8px;" class="offset1" for="start_time">数据 </label>
				<input  type="text" id="add_content" value="" style="width:300px;float:left;overflow:hidden; display:inline">

			</div>
			<br/>
			

		</form>
  </div>
  <div class="modal-footer">
    <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
    <button class="btn btn-primary" id="add_save">保存</button>
  </div>
</div>


<!-- Modal 弹出框内容  end -->



<script type="text/javascript">

$('[data-rel=tooltip]').tooltip();


$("a[data-toggle='modal']").click(function(){alert(222)})

function doDelete(id)
{
	
	bootbox.confirm("你确定删除吗?", function(result) {
		if(result) {
			
			$('.ami_Mask').show();
			
			$.get('config.do?method=doDelete&id='+id,function (data){
				
				$('.ami_Mask').hide();
				$('#tr_'+id).remove();
				
				bootbox.alert("	删除成功！！");
				
				
			});
			
			
		}
	});
	
}


///添加
//用于存储点击修改按钮所在的记录的唯一ID
var dataid='';

//给修改图标添加点击事件的监听，点击图标后，能够弹出层 
$('#add').click(function(){

	
	$('#add_content').val('');
	 
	$("#add_proj").modal("show");	
});

//弹出层 保存按钮 事件
$('#add_save').click(function(){

	//取到选择的 类型
	var dtypeid = $('#dtypeid').val();
	
	var add_content = $('#add_content').val();
	
	var url="config.do?method=doAddDictionary&dtype="+dtypeid+"&dtitle="+add_content;
	
	$.get(url,function(data){
	
	if('success'==data)
		{
			$('#con_'+dataid).html(content_id);
			$("#add_proj").modal("hide");	
			bootbox.alert("编辑成功");
			
		}else if('exists'==data){
			$("#add_proj").modal("hide");	
			bootbox.alert("对照内容已存在");
		}else
		{ 
			bootbox.alert("编辑失败");
		}
	});
	
})
///////添加end


//用于存储点击修改按钮所在的记录的唯一ID
var dataid='';

//给修改图标添加点击事件的监听，点击图标后，能够弹出层 
$('.modcontent').click(function(){

	//取到图标自定义属性 dataid 即数据的唯一ID 
	dataid = $(this).attr('dataid');
	
	//获取元素内容
	$('#con_'+dataid).html();
	
	//设置文本框的value
	 $('#content_id').val($('#con_'+dataid).html());
	//设置隐藏域的value
	 $('#judgeContent').html($('#con_'+dataid).html());
	
	 //得到name的值
	 var namevalue =$(this).attr('nameid');
	 //赋值给'含义'控件
	 $('#meaningReadonly').val(namevalue);
	$("#mod_proj").modal("show");	
});

//弹出层 保存按钮 事件
$('#save').click(function(){

	var content_id = $('#content_id').val();
	
	var judgeContent = $('#judgeContent').html();
	
	var url="config.do?method=doEditDictionary&id="+dataid+"&dtitle="+content_id;
	
	$.get(url,function(data){
	
	if('success'==data)
		{
			$('#con_'+dataid).html(content_id);
			$("#mod_proj").modal("hide");	
			bootbox.alert("编辑成功");
		
		}else if('exists'==data){
			$("#mod_proj").modal("hide");	
			bootbox.alert("对照内容已存在");
		}else
		{ 
			bootbox.alert("编辑失败");
		}
	});
	
})
</script>