
/**
 * 给URL后 自动增加 随机数
 */
function geturl(url)
{
	url+="&random="+Math.random();
	
	return url;
}

function exportFile()
{
		
		$("#export_file").modal("show");
		
		var params = $('#searchForm').formSerialize();
		
		var url = $('#searchForm').attr('action')+"&op=-1" ;
		
		$.post(url,params,function (data){
			
			setTimeout(function(){
				
				$('#exporting').hide();
	 			$('#exportLabel').html("数据导出成功，点击下载！");
	 			$('#fileName').html(data.substring(data.indexOf("/")+1));
	 			$('#fileName').attr('href',"download.do?method=donwload&fileName="+data);
			}),2000});
}

String.prototype.endWith=function(str){     
	  var reg=new RegExp(str+"$");     
	  return reg.test(this);        
	}


/**
 * 图片上传 
 * 
 * 上传成功后 会将图片显示出来
 * 
 * fid  //文件选择框的id属性
 */
function ajaxPicUpload(fid,suffix){

	if(''==suffix)
	{
		suffix="jpg";
	}
	$.ajaxFileUpload({
			url : 'uploadFile.do?method=uploadFile&suffix='+suffix, //需要链接到服务器地址
			secureuri : false,
			fileElementId : fid, //文件选择框的id属性
			dataType : 'json', //服务器返回的格式，可以是json
			success : function(data, status) //相当于java中try语句块的用法
			{
				//file 置空
				$('#'+fid).val('');
				//隐藏需要上传的
				$('#'+fid+'_hid').val(data.url);
				$('#'+fid+'_img').attr('src',data.url);
			},
			error : function(data, status, e) //相当于java中catch语句块的用法
			{
				alert('上传失败');
			}
		}

		);

	}


/**
 * 给file  img 绑定事件
 */
function bindclick(){
	
	$('.uploadfile').unbind();
	
	//上传福建
	$('.uploadfile').change(function(){
		
		var suffix = $(this).val().substring($(this).val().lastIndexOf('.')+1);
		//alert(suffix)
		if(!(suffix=='jpg'||suffix=='docx'||suffix=='doc'))
		{
			alert('只能上传jpg 图片和doc、docx Word文档');	
			return;
		}
		
		
		ajaxUpload($(this),suffix);
		
	});
	
	$('.uploadimg').unbind();
	$('.uploadimg').click(function(){
		
		//console.log(123);
		
		$(this).prev().prev().click();
		
		
		
	});
}
/**
 * 图片上传 
 * 
 * 上传成功后 会将图片显示出来
 * 
 * fid  //文件选择框的id属性
 */
function ajaxUpload(obj,suffix){
	//obj.attr('id',Math.random());
	var hid=obj.next();
	var img = hid.next();
	console.log(obj.next());
	$.ajaxFileUpload({
			url : 'uploadFile.do?method=uploadFile&suffix='+suffix, //需要链接到服务器地址
			secureuri : false,
			fileElementId : obj.attr('id'), //文件选择框的id属性
			dataType : 'json', //服务器返回的格式，可以是json
			success : function(data, status)
			{
				//file 置空
				//对顺序有要求
				obj.val('');
				//hid
				hid.val(data.url);
				//img
				if('doc'==suffix||'docx'==suffix)
				{
					img.attr('src','img/word.png');
				}else
				{
					img.attr('src',data.url);
				}
				
				
			},
			error : function(data, status, e) //相当于java中catch语句块的用法
			{
				alert('上传失败');
			}
		}

		);

	}


/**
 * 在本页面打开新的窗口 支持回退
 */
function openNewWinow(url,title)
{
	url=geturl(url);
	$('.ami_Mask').show();
	//主窗口隐错
	$('#ami_main').hide();
	$('#ami_newwindow').show();
	//找到新窗口
	$.get(url,function(data){
		
		$('#ami_newwindow').html(data);
		$('.ami_Mask').hide();
		
	});
	$('#page_title').html(title);
	$('#page_title_nav').html(title);
}
/**
 * 回退
 */
function goback()
{
	$('.ami_Mask').show();
	//主窗口隐错
	$('#ami_main').show();
	$('#ami_newwindow').html('');
	$('#ami_newwindow').hide();
	//主窗口隐错
	$('.ami_Mask').hide();
}



/****** 校验*************/


function validat(obj)
{
	if(obj.attr('requiry')&&obj.val()=='')
	{
		obj.siblings().remove();
		obj.parent().parent().removeClass('error');
		obj.after("&nbsp;<i class='icon-remove-sign' ></i><span class='help-inline' >必填项！</span>");
		obj.parent().parent().addClass('error');
		
		validation_result=false;
		
	}else
	{
		obj.parent().parent().removeClass('error');
		obj.siblings().remove();
	}
}

