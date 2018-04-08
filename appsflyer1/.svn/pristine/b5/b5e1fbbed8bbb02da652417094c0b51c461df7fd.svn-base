<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!-- Modal 弹出框内容 begin -->
<div id="dialog_selectDepartMent" class="modal hide fade department" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  
  <div class="modal-body">
 	 <ul id="DepartMenttree" class="ztree" style="width:100%;height:340px;background:#FFFFEE;overflow:scroll" ></ul>
  </div>
  
</div>						 
									





<script language="JavaScript">

var obj;

		var setting = {
			data: {
				key: {
					title:"t"
				},
				simpleData: {
					enable: true
				}
			},
			callback: {
				onClick: onClick
			}
		};

		var zNodes =[
			<c:forEach items="${DPList }" var="one">
				{ id:'${one.sid}', pId:'${one.pid}', name:"${one.title}",long_title:"${one.long_title}", open:false},
			</c:forEach>
			
			
		];

	
		//obj 点击的文本框本身
		function onClick(event, treeId, treeNode, clickFlag) {
		//	console.log(treeNode.getParentNode().id);
			var title=getRoot(treeNode);
			obj.val(treeNode.long_title);
			obj.next().val(treeNode.id);
			
			$("#dialog_selectDepartMent").modal("hide");	
		}		

	$(document).ready(function(){
			$.fn.zTree.init($("#DepartMenttree"), setting, zNodes);
			
		});



//回调 找到自己的所有父节点
function getRoot(currentNode)
{

	//首先拿到自己的name
	var nodeName=currentNode.name;
	if(currentNode.level==0)
	{
		//如果自己已经跟节点 则直接返回
		return nodeName;
	}else
	{
		nodeName=  getRoot(currentNode.getParentNode())+"-"+nodeName;
	}
	return nodeName;
}
function selectDepartMentshow(html_obj)
{
	obj=$(html_obj);
	$('#dialog_selectDepartMent').modal('show');
	
}
</script>
