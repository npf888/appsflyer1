<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="s" uri="/WEB-INF/struts-tags.tld" %>
<%

	String path = request.getContextPath();
	
	Object o = request.getSession().getAttribute("_loginAgentInfo");
	
	//不存在用户登录会话,跳出当前页登录页,让用户登录
	
	if (null == o) {
		out.println("<script type=\"text/javascript\">alert('登录已超时，请您重新登录!')</script>");
		response.sendRedirect(path+"/agent/login.jsp");
	}

%>
 <s:if test="%{#session.groupright.indexOf('102003')!=-1}"></s:if> 
<!DOCTYPE html>
<html lang="zh">
	<head>
		<meta charset="utf-8" />
		<title>无双吹牛 币商 管理系统</title>
		<meta name="description" content="overview & stats" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<base href="${pageContext.request.contextPath}/" />
		<!-- basic styles -->
		<link href="css/bootstrap.min.css" rel="stylesheet" />
		<link href="css/bootstrap-responsive.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="css/font-awesome.min.css" />
		<!--[if IE 7]>
		  <link rel="stylesheet" href="css/font-awesome-ie7.min.css" />
		<![endif]-->
		<!-- page specific plugin styles -->
		
		<!-- ace styles -->
		<link rel="stylesheet" href="css/ace.min.css" />
		<link rel="stylesheet" href="css/ace-responsive.min.css" />
		<link rel="stylesheet" href="css/ace-skins.min.css" />
		<link rel="stylesheet" href="css/chosen.css" />
		<link rel="stylesheet" href="agent/agentJs/media.css" />
		<!--[if lt IE 9]>
		  <link rel="stylesheet" href="css/ace-ie.min.css" />
		<![endif]-->
		<link rel="stylesheet" href="images/ami.css"/>
		
		<link rel="stylesheet" href="img/chosen1.css" />
		 <link rel="Stylesheet" href="js/dtree/dtree.css" type="text/css" />
		  <link rel="Stylesheet" href="js/lightbox/css/lightbox.css" type="text/css" />
		 <link rel="stylesheet" href="js/ztree/zTreeStyle/zTreeStyle.css" type="text/css" media="screen" />
		<link rel="stylesheet" href="js/ui/themes/base/jquery.ui.all.css">
		
    <!-- Data Tables -->
    <link href="css/dataTables/dataTables.bootstrap.css" rel="stylesheet">
    <link href="css/dataTables/dataTables.responsive.css" rel="stylesheet">
    <link href="css/dataTables/dataTables.tableTools.min.css" rel="stylesheet">

		
	</head>
	<body>

		<!-- 顶部导航--->
		
		<%@ include file="header.jsp"%>
		
		<!-- 顶部导航 end--->
		
		<!--.fluid-container#main-container begin -->
		<div class="main-container container-fluid" >
			<a href="#" id="menu-toggler"><span></span></a><!-- menu toggler -->


			<!-- 左边导航 --->
			<%@ include file="left.jsp"%>
			<!-- 左边航  end--->
			
			
			<!-- 右边整体内容--->
			
						 
			<div id="main-content" class="main-content">

					<div class="breadcrumbs" id="breadcrumbs">

						<ul class="breadcrumb">

							<li><i class="icon-home"></i> 币商后台<span class="divider"><i class="icon-angle-right"></i></span></li>

							<li class="active" id="page_title_nav">Dashboard</li>
						</ul><!--.breadcrumb-->
						
					</div><!--#breadcrumbs-->
	
			
						<!-- PAGE CONTENT BEGINS HERE -->	
						<div class="page-content"  id="PAGECONTENT" >
							
						 </div>
						 
						 <div class="page-content"  id="div_frame" style="display: none;padding: 0px">
							 <iframe name="frame"id="frame" width="100%" height="500px" frameborder=0></iframe>
						 </div>
						<!-- PAGE CONTENT ENDS HERE -->
						 
						 
						 <!--/row-->
				
					  
					
			</div><!-- #main-content -->
			<!-- 右边整体内容 end--->
			
		
		</div><!--/.fluid-container#main-container end -->
		
		<div class=" modal-backdrop fade in ami_Mask" style="opacity:0.4" id="ami_MaskDiv">
							<div class="ami_Maskcontent"></div>
						
			 </div>

		
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.9.1.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
		<!-- page specific plugin scripts -->
		
		

		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.slimscroll.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.sparkline.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.validate.min.js"></script>
		<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker_2/WdatePicker.js"></script>
		<script src="${pageContext.request.contextPath}/js/chosen.jquery.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/jquery.knob.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/jquery.autosize-min.js"></script>
		<script src="${pageContext.request.contextPath}/js/jquery.inputlimiter.1.3.1.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/jquery.maskedinput.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/bootbox.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/ami.js"></script>
		<script src="${pageContext.request.contextPath}/js/ztree/jquery.ztree.core-3.5.min.js"></script>
		<!-- ace scripts -->
		<script src="${pageContext.request.contextPath}/js/ace-elements.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/ace.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/ajaxfileupload.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/ui/jquery.ui.core.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/ui/jquery.ui.widget.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/ui/jquery.ui.position.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/ui/jquery.ui.menu.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/ui/jquery.ui.autocomplete.js"></script>
	    <!-- Data Tables -->
    <script src="${pageContext.request.contextPath}/js/dataTables/jquery.dataTables.js"></script>
    <script src="${pageContext.request.contextPath}/js/dataTables/dataTables.bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/js/dataTables/dataTables.responsive.js"></script>
    <script src="${pageContext.request.contextPath}/js/dataTables/dataTables.tableTools.min.js"></script>
		<!-- inline scripts related to this page -->

	
		<script>
			var title='';
			var page_title=title;
			
			$('.leaf').click(function()
			{

 				$('.active').removeClass('active');
 				//$('.open').removeClass('open');
				 $(this).parent().addClass('active');
				var sid = $(this).parent().attr('sid').substr(0,3);
				 $("[sid='"+sid+"']").addClass('open active');
				
				
				var url = $(this).attr('url');
				var title=$(this).attr('title');
				
				 nav(url,title);
				 if(window.screen.width<640){
				 	$('#sidebar').toggle();
				 }
				 
				
			});
			function nav(url,title)
			{	
				
				$('.ami_Mask').show();
				url+="&random="+Math.random();
				//$('#page_title').html(title);
				$('#page_title_nav').html(title);
				
				if(url.indexOf('map')!=-1)
				{
					$('#div_frame').show();
					$('#PAGECONTENT').hide();
					$('#frame').attr('src',url);
					$('.ami_Mask').hide();	
					
				}else
				{
					$('#PAGECONTENT').show();
					$('#div_frame').hide();
					$.ajax({
						  url: url,
						  success: function(data)
							  		{
										$('.ami_Mask').hide();	
										$('#PAGECONTENT').html(data)
									},
						  error:function(data){
							  $('.ami_Mask').hide();	
							 $('#PAGECONTENT').html(data.responseText)
							  
						  
						  	},
						  dataType: 'html'
						});
				}
				
				
			}
	
		
		//左边导航伸缩控制
 		$('[data-rel="tooltip"]').tooltip();
		
		//默认选中第一个菜单
		//$('.leaf').each(function(){ $(this).click();return false; })
		
		
		
	function autoHeight(){
		var mainHeight=$(window).height()-$('.navbar').height();
		var rightContentHeight =  mainHeight-$('.breadcrumbs').height()-8;

		$('#frame').height(rightContentHeight+"px");
		
		
	}
	$(document).ready(function(){
	
		autoHeight();

	})
	$(window).resize(function(){autoHeight()});
</script>
<script type="text/javascript">
function goBack(url)
{
	
	$.post(url,function (data){
		$('#PAGECONTENT').html(data);	
	});
}
</script>
<script src="js/dtree/dtree.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/js/Charts/FusionCharts.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Charts/assets/prettify/prettify.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Charts/assets/ui/js/json2.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/js/Charts/assets/ui/js/lib.js" ></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/lightbox/js/lightbox-2.6.min.js" ></script>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/Clockpicker/bootstrap-clockpicker.min.js"></script>
    <!-- Data Tables -->
    <script charset="utf-8" src="${pageContext.request.contextPath}/js/dataTables/jquery.dataTables.js"></script>
    <script charset="utf-8" src="${pageContext.request.contextPath}/js/dataTables/dataTables.bootstrap.js"></script>
    <script charset="utf-8" src="${pageContext.request.contextPath}/js/dataTables/dataTables.responsive.js"></script>
    <script charset="utf-8" src="${pageContext.request.contextPath}/js/dataTables/dataTables.tableTools.min.js"></script>
	</body>
</html>


