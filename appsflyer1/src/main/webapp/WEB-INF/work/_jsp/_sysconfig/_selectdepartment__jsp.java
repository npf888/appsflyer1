/*
 * JSP generated by Resin-3.0.19 (built Mon, 15 May 2006 04:50:47 PDT)
 */

package _jsp._sysconfig;
import javax.servlet.*;
import javax.servlet.jsp.*;
import javax.servlet.http.*;
import java.util.*;

public class _selectdepartment__jsp extends com.caucho.jsp.JavaPage{
  private boolean _caucho_isDead;
  
  public void
  _jspService(javax.servlet.http.HttpServletRequest request,
              javax.servlet.http.HttpServletResponse response)
    throws java.io.IOException, javax.servlet.ServletException
  {
    javax.servlet.http.HttpSession session = request.getSession(true);
    com.caucho.server.webapp.Application _jsp_application = _caucho_getApplication();
    javax.servlet.ServletContext application = _jsp_application;
    com.caucho.jsp.PageContextImpl pageContext = com.caucho.jsp.QJspFactory.allocatePageContext(this, _jsp_application, request, response, null, session, 8192, true, false);
    javax.servlet.jsp.JspWriter out = pageContext.getOut();
    javax.servlet.ServletConfig config = getServletConfig();
    javax.servlet.Servlet page = this;
    response.setContentType("text/html");
    com.caucho.jsp.IteratorLoopSupportTag _jsp_loop_0 = null;
    try {
      out.write(_jsp_string0, 0, _jsp_string0.length);
      if (_jsp_loop_0 == null)
        _jsp_loop_0 = new com.caucho.jsp.IteratorLoopSupportTag();
      java.util.Iterator _jsp_iter_1 = com.caucho.jstl.el.ForEachTag.getIterator(_caucho_expr_0.evalObject(pageContext));
      _jsp_loop_0.init(0, Integer.MAX_VALUE, 1);
      Object _jsp_oldVar_1 = pageContext.getAttribute("one");
      while (_jsp_iter_1.hasNext()) {
        Object _jsp_i_1 = _jsp_iter_1.next();
        pageContext.setAttribute("one", _jsp_i_1);
        _jsp_loop_0.setCurrent(_jsp_i_1, _jsp_iter_1.hasNext());
        out.write(_jsp_string1, 0, _jsp_string1.length);
        _caucho_expr_1.print(out, pageContext, false);
        out.write(_jsp_string2, 0, _jsp_string2.length);
        _caucho_expr_2.print(out, pageContext, false);
        out.write(_jsp_string3, 0, _jsp_string3.length);
        _caucho_expr_3.print(out, pageContext, false);
        out.write(_jsp_string4, 0, _jsp_string4.length);
        _caucho_expr_4.print(out, pageContext, false);
        out.write(_jsp_string5, 0, _jsp_string5.length);
      }
      pageContext.pageSetOrRemove("one", _jsp_oldVar_1);
      out.write(_jsp_string6, 0, _jsp_string6.length);
    } catch (java.lang.Throwable _jsp_e) {
      pageContext.handlePageException(_jsp_e);
    } finally {
      com.caucho.jsp.QJspFactory.freePageContext(pageContext);
    }
  }

  private java.util.ArrayList _caucho_depends = new java.util.ArrayList();

  public java.util.ArrayList _caucho_getDependList()
  {
    return _caucho_depends;
  }

  public void _caucho_addDepend(com.caucho.make.PersistentDependency depend)
  {
    super._caucho_addDepend(depend);
    com.caucho.jsp.JavaPage.addDepend(_caucho_depends, depend);
  }

  public boolean _caucho_isModified()
  {
    if (_caucho_isDead)
      return true;
    if (com.caucho.util.CauchoSystem.getVersionId() != 7932908523769947432L)
      return true;
    for (int i = _caucho_depends.size() - 1; i >= 0; i--) {
      com.caucho.make.Dependency depend;
      depend = (com.caucho.make.Dependency) _caucho_depends.get(i);
      if (depend.isModified())
        return true;
    }
    return false;
  }

  public long _caucho_lastModified()
  {
    return 0;
  }

  public void destroy()
  {
      _caucho_isDead = true;
      super.destroy();
  }

  public void init(com.caucho.vfs.Path appDir)
    throws javax.servlet.ServletException
  {
    com.caucho.vfs.Path resinHome = com.caucho.util.CauchoSystem.getResinHome();
    com.caucho.vfs.MergePath mergePath = new com.caucho.vfs.MergePath();
    mergePath.addMergePath(appDir);
    mergePath.addMergePath(resinHome);
    com.caucho.loader.DynamicClassLoader loader;
    loader = (com.caucho.loader.DynamicClassLoader) getClass().getClassLoader();
    String resourcePath = loader.getResourcePathSpecificFirst();
    mergePath.addClassPath(resourcePath);
    com.caucho.vfs.Depend depend;
    depend = new com.caucho.vfs.Depend(appDir.lookup("sysconfig/selectDepartMent.jsp"), 3491643559999858836L, false);
    com.caucho.jsp.JavaPage.addDepend(_caucho_depends, depend);
  }
  private final static com.caucho.el.Expr _caucho_expr_0 =
    new com.caucho.el.IdExpr("DPList");
  private final static com.caucho.el.Expr _caucho_expr_1 =
    new com.caucho.el.PathExpr(new com.caucho.el.ArrayExpr(new com.caucho.el.IdExpr("one"), new com.caucho.el.StringLiteral("sid")), "one.sid");
  private final static com.caucho.el.Expr _caucho_expr_2 =
    new com.caucho.el.PathExpr(new com.caucho.el.ArrayExpr(new com.caucho.el.IdExpr("one"), new com.caucho.el.StringLiteral("pid")), "one.pid");
  private final static com.caucho.el.Expr _caucho_expr_3 =
    new com.caucho.el.PathExpr(new com.caucho.el.ArrayExpr(new com.caucho.el.IdExpr("one"), new com.caucho.el.StringLiteral("title")), "one.title");
  private final static com.caucho.el.Expr _caucho_expr_4 =
    new com.caucho.el.PathExpr(new com.caucho.el.ArrayExpr(new com.caucho.el.IdExpr("one"), new com.caucho.el.StringLiteral("long_title")), "one.long_title");

  private final static char []_jsp_string6;
  private final static char []_jsp_string5;
  private final static char []_jsp_string4;
  private final static char []_jsp_string1;
  private final static char []_jsp_string2;
  private final static char []_jsp_string3;
  private final static char []_jsp_string0;
  static {
    _jsp_string6 = "\r\n			\r\n			\r\n		];\r\n\r\n	\r\n		//obj \u70b9\u51fb\u7684\u6587\u672c\u6846\u672c\u8eab\r\n		function onClick(event, treeId, treeNode, clickFlag) {\r\n		//	console.log(treeNode.getParentNode().id);\r\n			var title=getRoot(treeNode);\r\n			obj.val(treeNode.long_title);\r\n			obj.next().val(treeNode.id);\r\n			\r\n			$(\"#dialog_selectDepartMent\").modal(\"hide\");	\r\n		}		\r\n\r\n	$(document).ready(function(){\r\n			$.fn.zTree.init($(\"#DepartMenttree\"), setting, zNodes);\r\n			\r\n		});\r\n\r\n\r\n\r\n//\u56de\u8c03 \u627e\u5230\u81ea\u5df1\u7684\u6240\u6709\u7236\u8282\u70b9\r\nfunction getRoot(currentNode)\r\n{\r\n\r\n	//\u9996\u5148\u62ff\u5230\u81ea\u5df1\u7684name\r\n	var nodeName=currentNode.name;\r\n	if(currentNode.level==0)\r\n	{\r\n		//\u5982\u679c\u81ea\u5df1\u5df2\u7ecf\u8ddf\u8282\u70b9 \u5219\u76f4\u63a5\u8fd4\u56de\r\n		return nodeName;\r\n	}else\r\n	{\r\n		nodeName=  getRoot(currentNode.getParentNode())+\"-\"+nodeName;\r\n	}\r\n	return nodeName;\r\n}\r\nfunction selectDepartMentshow(html_obj)\r\n{\r\n	obj=$(html_obj);\r\n	$('#dialog_selectDepartMent').modal('show');\r\n	\r\n}\r\n</script>\r\n".toCharArray();
    _jsp_string5 = "\", open:false},\r\n			".toCharArray();
    _jsp_string4 = "\",long_title:\"".toCharArray();
    _jsp_string1 = "\r\n				{ id:'".toCharArray();
    _jsp_string2 = "', pId:'".toCharArray();
    _jsp_string3 = "', name:\"".toCharArray();
    _jsp_string0 = "\r\n\r\n\r\n\r\n<!-- Modal \u5f39\u51fa\u6846\u5185\u5bb9 begin -->\r\n<div id=\"dialog_selectDepartMent\" class=\"modal hide fade department\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"myModalLabel\" aria-hidden=\"true\">\r\n  \r\n  <div class=\"modal-body\">\r\n 	 <ul id=\"DepartMenttree\" class=\"ztree\" style=\"width:100%;height:340px;background:#FFFFEE;overflow:scroll\" ></ul>\r\n  </div>\r\n  \r\n</div>						 \r\n									\r\n\r\n\r\n\r\n\r\n\r\n<script language=\"JavaScript\">\r\n\r\nvar obj;\r\n\r\n		var setting = {\r\n			data: {\r\n				key: {\r\n					title:\"t\"\r\n				},\r\n				simpleData: {\r\n					enable: true\r\n				}\r\n			},\r\n			callback: {\r\n				onClick: onClick\r\n			}\r\n		};\r\n\r\n		var zNodes =[\r\n			".toCharArray();
  }
}