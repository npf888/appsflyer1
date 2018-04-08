<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

	<form class="form-horizontal"  id="form" enctype="multipart/form-data">
		 
		  
		 <div class="control-group">
			<label class="control-label" for="form-field-accout">Image name:</label>
			<div class="controls">
			  <input type="text" name="game_name" class="validation" requiry="true">
			</div>
		 </div>
		 
		 <div class="control-group">
			<label class="control-label" for="form-field-accout">Upload game Image:</label>
			<div class="controls">
			  <input type="file" id="image_path" name="content" accept="image/*" onchange="previewImage(event)">
			  <img id="preview" style="max-height:100px;width:auto;border:1px solid silver;border-radius:10px"/>
			</div>
		  </div>

		  <div class="form-actions">
				<button class="btn btn-small btn-info" type="submit" id="submit"><i class="icon-ok"></i>提交</button>	
				&nbsp; &nbsp; &nbsp;
				<button class="btn btn-small" type="reset"><i class="icon-undo"></i> Reset</button>
				<c:if test="${iscancel=='1'}">
				&nbsp; &nbsp; &nbsp;
				<button class="btn btn-small" type="reset" onclick="goback()"><i class="icon-undo"></i>取消</button>
				</c:if>
		  </div>
	 </form>
	 <div>
	 	<c:forEach items="${imgs}" var="img">
	 	<div style="float:left; margin:10px; ">
	 		<img style="float:left;height:100px" src="${G_ip}/activity/${img.image_name}"></img>
	 		<button style="border-radius:10px" class='btn btn-mini btn-danger' onclick="doDelete('${img.id }')"><i class='icon-trash'></i></button>
	 	</div>
	 	</c:forEach>
	 </div>
	<script>
		
		/* $(document).ready(function () {
		    //定义表单提交前与提交后的处理方法及超时相关
		    var options = {
		                beforeSubmit:showRequest,
		                success:showResponse,
		                timeout:3000
		            };
		    $('#form').submit(function() { 
		     // submit the form 
		     $(this).ajaxSubmit(options); 
		     $('.ami_Mask').show();
		     // return false to prevent normal browser submit and page navigation 
		     return false; 
		    });
		}); */
		
		
		
		 $(function () {
	            $("#submit").click(function () {
	                var formData = new FormData();
	                formData.append("content", document.getElementById("image_path").files[0]);  
	                $.ajax({
	                    url: "${G_url}api/upload.json",
	                    type: "POST",
	                    data: formData,
	                    async: false,    
	                    cache: false,    
	                    contentType: false,    
	                    processData: false, 
	                    dataType : "json",
	                    success: function (data) {
	                    	$.get("img.do?method=queryImg", function(data){
	                    		$('.ami_Mask').hide();	
	                    		$('#PAGECONTENT').html(data)					
	                    	});	
	                    },
	                    error: function(XMLHttpRequest, textStatus, errorThrown) {
	                    }
	                });
	            });
	        });
		
	function previewImage(event)
	{
		var output = document.getElementById('preview');
	    output.src = URL.createObjectURL(event.target.files[0]);
	}

	function showResponse(responseText, statusText, xhr, $form)  
	{ 
		var response=jQuery.parseJSON(responseText);
		$('.ami_Mask').hide();
		if(response.errorCode == 0)
		{
			bootbox.alert("Image Uploaded",
			function() {
				nav('img.do?method=queryImg','上传图片');
			});
		}
		else
		{
		bootbox.alert("Error!",
			function() {
				history.back();
				});
		}
	}
	
	function doDelete(id) {

		bootbox.confirm("确定删除这个图片么?", function(result) {
			if (result) {

				$('.ami_Mask').show();
				var url = "img.do?method=delByID&id=" + id;
				$.get(url, function(data) {
					$('.ami_Mask').hide();
					$('#tr_' + id).remove();
					$.get("img.do?method=queryImg", function(data){
                		$('.ami_Mask').hide();	
                		$('#PAGECONTENT').html(data)					
                	});	
				});
			}
		});
	}
</script>



