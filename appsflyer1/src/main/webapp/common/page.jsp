<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<c:if test="${ !empty PAGER.totalCount&&PAGER.totalCount!=0  }">
<div class="row-fluid" style="padding: 12px 0 6px;background-color: #eff3f8;border-bottom: 1px solid #DDD; margin-top: -20px">	
	<div  style="margin:3px 0 0 20px;float:left;" >${PAGER.start+1}-${PAGER.start+PAGER.limit>PAGER.totalCount?PAGER.totalCount:PAGER.limit}/${PAGER.totalCount }</div>
	<div style="text-align: right;margin-right: 20px;float:right">${PAGER.pageHtml}</div>
</div>
</c:if>
<c:if test="${ empty PAGER.totalCount||PAGER.totalCount==0 }"> 
<div class="row-fluid" style="padding: 12px 0 6px;background-color: #eff3f8;border-bottom: 1px solid #DDD; margin-top: -20px;text-align: center;">
 暂无数据
 </div>
</c:if>
<script>

function go(page)
{
	$('.ami_Mask').show();
	var params = $('#searchForm').formSerialize();
	
	var url = $('#searchForm').attr('action') +"&currentPage="+page;
	$.post(url,params,function (data){
	
	$('#PAGECONTENT').html(data);
	
	$('.ami_Mask').hide();
	
	});
}
</script>