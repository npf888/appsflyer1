<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/WEB-INF/struts-tags.tld" %>
<div id="ami_main">
<div class="row-fluid">	
			<form class="form-inline" action="worldBoss.do?method=query" id="searchForm">
				<label>Useraccount</label>
				<input  type="text" class="input-medium"  name="useraccount" value="${account }"  id="account" ></input>				
				<label >bossType</label>
				<input type="text" class="input-medium" value="${bossType }" name="bossType"  ></input>
				<button type="button"  style="margin-left:10px" class="btn btn-mini btn-info" onClick="go(1)">检索</button>
			</form>
</div>	
<hr/>

<div class="row-fluid">
	
		<div class="ami_table_header"><span class="ami_table_header_title">收集系统</span>

		</div>
		<table id="table_report" class="table table-striped table-bordered table-hover">
			<thead>			
				<tr>
					<th>开始时间</th>
					<th>结束时间</th>
					<th>bossID</th>
					<th>boss类型</th>
					<th>boss持续时间</th>
					<th>技能持续时间</th>
					
					<th>wild连线伤害</th>
					<th>恢复最大血量百分数</th>
					<th>伤害减免百分数</th>
					<th>免伤概率</th>
					
					<th>基础血量</th>			
					<th>变化血量血量</th>			
					<th>增长血量</th>		
						
					<th>击杀奖励金币</th>			
					<th>未击杀奖励金币</th>
								
					<th>是否被杀</th>			
					<th style="display:none;">排行榜</th>			
					<th>更多详情(everyone)</th>			
					<th>更多详情(deepdeTail)</th>			
				</tr>				
			</thead>
			<tbody>				
			 <c:forEach var="user" items="${PAGER.items}" varStatus="status">
					
				<tr >
					
					<td>${user.startTime}</td>
					<td>${user.endTime}</td>
					<td>${user.bossId}</td>
					<td>${user.type}</td>
					<td>${user.continueTime}分钟</td>
					<td>${user.airtime}秒</td>
					
					<td>${user.wildreduce}</td>
					<td>${user.recover1}</td>
	                <td>${user.damagereduce} </td>
	                <td>${user.avoid}</td>
	                
	                <td>${user.blood}</td>
	                <td>${user.changingBlood}</td>
	                <td>${user.increaseBlood}</td>
	                
	                <td>${user.rewardNum1}</td>
	                <td>${user.rewardNum2}</td>
	                <c:if test="${user.beKilled==0 }">
	                	<td>自然结束</td>
	                </c:if>
	                <c:if test="${user.beKilled==1 }">
	                	<td><font color="red">被击毙</font></td>
	                </c:if>
	                <td style="display:none;">${user.attackRank}</td>
	                <td><a onclick="moreDetail('worldBoss.do?method=queryWorldBossDetailOne&bossId=${user.bossId }&date=${user.startTime}')">更多详情(one)</a></td>
	                <td><a onclick="moreDetail('worldBoss.do?method=queryWorldBossDetail&bossId=${user.bossId }&date=${user.startTime}')">更多详情(more)</a></td>

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

function doBlock(id)
{
	bootbox.confirm("Are you sure to block this user account?", function(result) {
		if(result) {
			
			$('.ami_Mask').show();
			var url="affiliate.do?method=doBlock&uuid="+id;
			$.get(url, function(data){
				$('.ami_Mask').hide();
				bootbox.alert("	User Blocked!");						
			});						
		}
	});		
}
function doDelete(id)
{
	
	bootbox.confirm("Delete this user account?", function(result) {
		if(result) {
			
			$('.ami_Mask').show();
			var url="affiliate.do?method=doDelete&uuid="+id;
			$.get(url, function(data){
				alert(data);
				$('.ami_Mask').hide();
				$('#tr_'+id).remove();
				bootbox.alert("	Removed!");						
			});						
		}
	});	
}
/**
 * 跳转到 更多详情的页面
 */
function moreDetail(url){
	$.get(url, function(data){
		$('.ami_Mask').hide();	
		$('#PAGECONTENT').html(data)					
	});		
}
</script>