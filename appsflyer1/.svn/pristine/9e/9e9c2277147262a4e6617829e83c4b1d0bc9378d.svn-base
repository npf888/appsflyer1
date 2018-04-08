<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<script src="${pageContext.request.contextPath}/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>

<div class="widget-header">
  <div class="widget-toolbar no-border">
  <ul class="nav nav-tabs" id="recent-tab">
	<li <c:if test="${one == null || one.functionType==1}" > class="active" </c:if>  id="winner"><a data-toggle="tab" href="#winner-tab"
		aria-expanded="true">买一送一</a></li>

	<li <c:if test="${one.activityType==2}" > class="active" </c:if>  id="lucky"><a data-toggle="tab" href="#lucky-tab"
		aria-expanded="false">暂无</a></li>


  </ul>
  </div>
</div>

<form class="form-horizontal" action="#" method="post" id="form" enctype="multipart/form-data">

	<h2 id="title" style="margin-left:50px;padding-bottom:30px">功能配置</h2>
    <div id="type-container">
    	<input type="hidden" name="id" value="${one.id }"/>
    </div>
	
	<div class="control-group">
		<label class="control-label" for="form-field-accout">功能类型</label>
		<div class="controls">
			<select name="functionType">
				<option value="1">买一送一</option>
			</select>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label" for="form-field-accout">功能名称:</label>
		<div class="controls">
			<input type="text" name="title" value="${one.title}"  class="validation" requiry="true">
		</div>
	</div>
	
	<%-- <div class="control-group">
		<label class="control-label" for="form-field-accout">功能名称:</label>
		<div class="controls">
			<input type="text" name="title_cn" value="${one.title_cn}"  class="validation" requiry="true">
		</div>
	</div>
	
	
	<div class="control-group">
		<label class="control-label" for="form-field-accout">功能名称:</label>
		<div class="controls">
			<input type="text" name="title_tw" value="${one.title_tw}"  class="validation" requiry="true">
		</div>
	</div> --%>



	<div class="control-group">
		<label class="control-label" for="form-field-accout">开始时间:</label>
		<div class="controls">
			<input type="text"  id="d4311" name="startTime" value="${startTime}" class="Wdate" onClick="WdatePicker({maxDate:'#F{$dp.$D(\'d4312\')}',dateFmt:'yyyy-MM-dd'})" style="width:80px" requiry="true"/>
			截止时间：<input type="text"  id="d4312" name="endTime" value="${endTime}" class="Wdate"  onClick="WdatePicker({minDate:'#F{$dp.$D(\'d4311\')}',realDateFmt:'yyyy-MM-dd',dateFmt:'yyyy-MM-dd'})" style="width:80px" requiry="true"/> 
		注：包括截止日期那一天
		</div>
	</div>




	<div class="control-group">
		<label class="control-label" for="form-field-accout">Short Description:</label>
		<div class="controls">
			<textarea rows="1" cols="10" style="width: 50%" name="descrip">${one.descrip}</textarea>
		</div>
	</div>
	<%-- <div class="control-group">
		<label class="control-label" for="form-field-accout">描述（简体）:</label>
		<div class="controls">
			<textarea rows="1" cols="10" style="width: 50%" name="descrip_cn">${one.descrip_cn}</textarea>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label" for="form-field-accout">描述（繁体）:</label>
		<div class="controls">
			<textarea rows="1" cols="10" style="width: 50%" name="descrip_tw">${one.descrip_tw}</textarea>
		</div>
	</div>
 --%>


	<!-- 功能的图片 -->
	<div class="control-group">
		<label class="control-label" for="form-field-accout">图片:</label>
		<div class="controls">
		 
			<input type="" id="pic" name="pic" >
			<input class="selectImg" type="button" input_name="pic" value="选择图片">			
		</div>
	</div>
	
<!-- 
	<div class="control-group">
		<label class="control-label" for="form-field-accout">功能图片(简体):</label>
		<div class="controls">
			<input type="" id="pic_cn" name="pic_cn">
			<input class="selectImg" type="button" input_name="pic_cn" value="选择图片">
		</div>
	</div>
	
	<div class="control-group">
		<label class="control-label" for="form-field-accout">功能图片(繁体):</label>
		<div class="controls">
			<input type="" id="pic_tw" name="pic_tw">
			<input class="selectImg" type="button" input_name="pic_tw" value="选择图片">
		</div>
	</div> -->
	
	
	
	<!-- 大厅的图片 -->
	<!-- <div class="control-group">
		<label class="control-label" for="form-field-accout">大厅图片(English):</label>
		<div class="controls">
		 
			<input type="" id="hall_pic" name="hall_pic" >
			<input class="selectImg" type="button" input_name="hall_pic" value="选择图片">			
		</div>
	</div>

	<div class="control-group">
		<label class="control-label" for="form-field-accout">大厅图片(简体):</label>
		<div class="controls">
			<input type="" id="hall_pic_cn" name="hall_pic_cn">
			<input class="selectImg" type="button" input_name="hall_pic_cn" value="选择图片">
		</div>
	</div>
	
	<div class="control-group">
		<label class="control-label" for="form-field-accout">大厅图片(繁体):</label>
		<div class="controls">
			<input type="" id="hall_pic_tw" name="hall_pic_tw">
			<input class="selectImg" type="button" input_name="hall_pic_tw" value="选择图片">
		</div>
	</div> -->
	
	
	<!--  买一赠一 的专有条件 -->
	 <div class="form-group">
     <label class="col-sm-3 control-label">分类：${condition1 == '1'}</label>
      <div class="col-sm-4">
          <select  name="condition_categroy" class="selectpicker show-tick form-control" multiple data-live-search="false">
                  <option value="1" <c:if test="${condition1 == '1'}">selected="selected"</c:if> >筹码</option>
                  <option value="2" <c:if test="${condition2 == '2'}">selected="selected"</c:if>  >物品</option>
                  <option value="3" <c:if test="${condition3 == '3'}">selected="selected"</c:if> >礼包</option>
                  <option value="4" <c:if test="${condition4 == '4'}">selected="selected"</c:if> >功能性付费</option>
          </select>
      </div>
    </div>
	<!-- <div class="control-group">
		<label class="control-label" for="form-field-accout">小类</label>
		<div class="controls">
		
	/** 1 筹码 **/
		筹码：	<input type="checkbox"  name="condition_small_categroy" value="1">
	/** 2、周卡 **/
		周卡 ：<input type="checkbox"  name="condition_small_categroy" value="2" >
	/** 3、月卡 **/
		月卡 ：<input type="checkbox"  name="condition_small_categroy" value="3">
	/** 4、门票**/
		门票：<input type="checkbox"  name="condition_small_categroy" value="4">
	/** 5、改名卡**/
		改名卡：<input type="checkbox"  name="condition_small_categroy" value="5">
	/** 6、付费转盘**/
		付费转盘：<input type="checkbox"  name="condition_small_categroy" value="6">
	/** 7、翻倍转盘**/
		翻倍转盘：<input type="checkbox"  name="condition_small_categroy" value="7">
	/** 8、奖券**/
		奖券：<input type="checkbox"  name="condition_small_categroy" value="8">
	/** 9、俱乐部礼包**/
		俱乐部礼包：<input type="checkbox"  name="condition_small_categroy" value="19">
	/** 10、金库（小金猪）**/
		金库（小金猪）：<input type="checkbox"  name="condition_small_categroy" value="10">
	/** 11、新手礼包**/
		新手礼包：<input type="checkbox"  name="condition_small_categroy" value="11">
	/**12、经验加成卡**/
		经验加成卡：<input type="checkbox"  name="condition_small_categroy" value="12">
	/**13、俱乐部改名卡**/
		俱乐部改名卡：<input type="checkbox"  name="condition_small_categroy" value="13">
	/**14、周特惠礼包**/
		周特惠礼包：<input type="checkbox"  name="condition_small_categroy" value="14">
	/**15、月特惠礼包**/
		月特惠礼包：<input type="checkbox"  name="condition_small_categroy" value="15">
	/**16、大奖转盘**/
		大奖转盘：<input type="checkbox"  name="condition_small_categroy" value="16">
		</div>
	</div>
	 -->
	 <div class="form-actions">
		<input type="button" class="btn" id="submitEle" value="提交" onclick="goSubmit();">
	</div>
</form>
<div id="select_image" style="width:80%;background-color:red;position:fixed;top:200px;left:200px;right:auto;display:none">
	<c:forEach items="${images}" var="img">
		<img class="image" src="${G_ip}/activity/${img.image_name}" style="height:150px; margin:10px;float:left;" value="${img.image_name}" ></img>
	</c:forEach>
	<button value="关闭" id="select_image_close">关闭</button>
</div>
<script >
	$(function(){
		$("#form").bind("submit",function(){
			var options = {
	                url: 'function.do?method=updateFunction',
	                type: 'post',
	                dataType: 'text',
	                data: $("#form").serialize(),
	                success: function (data) {
	                	nav('function.do?method=queryFunction','Compensation');
	                }
	         };
	         $.ajax(options);
	         return false;
		});
		
		$('.selectImg').unbind().click(function(){
			var selectid=$(this).attr("input_name");
			$('#select_image').show();
			$('.image').unbind().click(function()
			{
				$('#'+selectid).siblings("img").remove();
				$('#'+selectid).val($(this).attr("value"));	
				$('#'+selectid).parent().append("<img src='"+$(this).attr("src")+"'style='height:80px'>");
				
				$('#select_image').hide();			
			});
			
		});
		$("#select_image_close").click(function(){
			$('#select_image').hide();		
		});
	});
	
	function goSubmit(){
		$('#form').submit();
	}

</script>




