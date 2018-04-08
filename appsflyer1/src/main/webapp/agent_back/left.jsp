			<%@ taglib prefix="s" uri="/WEB-INF/struts-tags.tld" %>
			<style>
				.sidebar-shortcuts-large{background:#6fb3e0; height:40px; line-height:40px; color:#FFFFFF; font-weight:bold;}
				.sidebar-shortcuts-large img{float:left; overflow:hidden; display:inline; margin:13px 0 0 15px;}
			</style>
			<div id="sidebar" class="sidebar">
				<div id="sidebar-shortcuts" class="sidebar-shortcuts">
					<div id="sidebar-shortcuts-large" class="sidebar-shortcuts-large" >
						<img src="img/menus.jpg" />
					</div>
					<div id="sidebar-shortcuts-mini" class="sidebar-shortcuts-mini">
						<span class="btn btn-success"></span>
						<span class="btn btn-info"></span>
						<span class="btn btn-warning"></span>
						<span class="btn btn-danger"></span>					
					</div>
				</div><!-- #sidebar-shortcuts -->


				<ul class="nav nav-list">
					
	
					${menu }
					

				</ul>
				<!--/.nav-list-->



				<div class="sidebar-collapse" id="sidebar-collapse">
					<i class="icon-double-angle-left"></i>
				</div>
			</div><!--/#sidebar-->
			
			
			