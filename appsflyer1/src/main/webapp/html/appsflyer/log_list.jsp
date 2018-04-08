<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ taglib prefix="s" uri="/WEB-INF/struts-tags.tld"%>
<div id="ami_main">

	<form class="form-inline" action="appsflyer.do?method=logs"
		id="searchForm">
		<input type="hidden" name="ac" value="${params.ac }"/>
		<div class="row-fluid">
		<c:if test="${params.ac!='hsfb' }">
			<label>App_id</label> <input type="text" class="input-medium"
				name="app_id" value="${params.app_id }" style="width: 200px" placeholder="IOS 必须以id开头"></input>
		</c:if>	
		 <label>imei/idfa </label>
			
			<input type="text" class="input-medium" style="width: 200px" name="imei"
				value="${params.imei }"></input>

 			<label>event_name</label>
 
			<select name="event_name">
			<option value=""  <c:if test="${params.event_name=='' }">selected</c:if>>All</option>
			<option value="checkout"  <c:if test="${params.event_name=='checkout' }">selected</c:if>>checkout</option>
			<option value="login" <c:if test="${params.event_name=='login' }">selected</c:if>>login</option>
			<option value="tutorial" <c:if test="${params.event_name=='tutorial' }">selected</c:if>>tutorial</option>
			<option value="level_hero" <c:if test="${params.event_name=='level_hero' }">selected</c:if>>level_hero</option>
			<option value="level_building" <c:if test="${params.event_name=='level_building' }">selected</c:if>>level_building</option>
			<option value="install" <c:if test="${params.event_name=='install' }">selected</c:if>>install</option>
			</select>


			<button type="button" style="margin-left: 10px"
				class="btn btn-mini btn-info" onClick="go(1)">检索</button>
		</div>


	</form>

	<hr />

	<div class="row-fluid">

		<div class="ami_table_header">
			<span class="ami_table_header_title">Appsflyer-Logs(${PAGER.totalCount
				})</span>



		</div>
		<table id="table_report"
			class="table table-striped table-bordered table-hover">
			<thead>

				<tr>
					<th>No.</th>
					<th>app_id</th>
					<th>device_model</th>
					<th>event_time_us</th>
					<th>event_time_cn</th>
					<th>event_type</th>
					<th>event_name</th>
					<th>event_value</th>
					<th>event_value2</th>
					<th>media_source</th>
					<th>imei/idfa</th>
					<!--  <th>appsflyer_device_id</th>-->
					<th>customer_user_id</th>
					<th>app_version</th>
					<th>country</th>
					<th>check</th>
				</tr>
			</thead>
			<tbody>


				<c:forEach var="user" items="${PAGER.items}" varStatus="status">

					<tr id="tr_${user.id }" <c:if test="${ user.is_task=='-1' }">style="color:red"</c:if>>

						<td>${status.index+1+ PAGER.start}</td>
						<td>${user.app_id }</td>
						<td>${user.device_model }${user.device_name }</td>
						<td>${user.event_time_us }</td>
						<td>${user.event_time_cn }</td>
						<td>${user.event_type }</td>
						<td>${user.event_name }</td>
						<td>${user.event_value }</td>
						<td>${user.event_value_json }</td>
						<td>${user.media_source }</td>
						<td>${user.imei }${user.idfa }</td>
						<!-- <td>${user.appsflyer_device_id }</td>-->
						<td>${user.customer_user_id }</td>
						<td>${user.app_version }</td>	
						<td>${user.country_code}</td>	
						<td>${user.illegal_mesg}</td>	

					</tr>

				</c:forEach>

			</tbody>
		</table>
		<jsp:include page="/common/page.jsp"></jsp:include>

	</div>
</div>
<div id="ami_newwindow" style="display: none;"></div>

