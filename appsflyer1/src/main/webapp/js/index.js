function autoHeight(){
	
		var mainHeight=$(window).height()-259-95;
		var rightContentHeight =  mainHeight-$('.breadcrumbs').height()-8;

		$('#main_content').css('min-height',rightContentHeight+"px");
		
		
	}
	$(document).ready(function(){
	
		autoHeight();
		
		
	})
	$(window).resize(function(){autoHeight()});