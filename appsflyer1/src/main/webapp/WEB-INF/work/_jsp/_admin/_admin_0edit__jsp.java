/*
 * JSP generated by Resin-3.0.19 (built Mon, 15 May 2006 04:50:47 PDT)
 */

package _jsp._admin;
import javax.servlet.*;
import javax.servlet.jsp.*;
import javax.servlet.http.*;
import java.util.*;

public class _admin_0edit__jsp extends com.caucho.jsp.JavaPage{
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
      _caucho_expr_0.print(out, pageContext, false);
      out.write(_jsp_string1, 0, _jsp_string1.length);
      _caucho_expr_1.print(out, pageContext, false);
      out.write(_jsp_string2, 0, _jsp_string2.length);
      _caucho_expr_2.print(out, pageContext, false);
      out.write(_jsp_string3, 0, _jsp_string3.length);
      if (_caucho_expr_3.evalBoolean(pageContext)) {
        out.write(_jsp_string4, 0, _jsp_string4.length);
      }
      out.write(_jsp_string5, 0, _jsp_string5.length);
      if (_caucho_expr_4.evalBoolean(pageContext)) {
        out.write(_jsp_string6, 0, _jsp_string6.length);
      }
      out.write(_jsp_string7, 0, _jsp_string7.length);
      if (_caucho_expr_3.evalBoolean(pageContext)) {
        out.write(_jsp_string4, 0, _jsp_string4.length);
      }
      out.write(_jsp_string8, 0, _jsp_string8.length);
      _caucho_expr_5.print(out, pageContext, false);
      out.write(_jsp_string9, 0, _jsp_string9.length);
      _caucho_expr_6.print(out, pageContext, false);
      out.write(_jsp_string10, 0, _jsp_string10.length);
      _caucho_expr_7.print(out, pageContext, false);
      out.write(_jsp_string11, 0, _jsp_string11.length);
      if (_jsp_loop_0 == null)
        _jsp_loop_0 = new com.caucho.jsp.IteratorLoopSupportTag();
      java.util.Iterator _jsp_iter_1 = com.caucho.jstl.el.ForEachTag.getIterator(_caucho_expr_8.evalObject(pageContext));
      _jsp_loop_0.init(0, Integer.MAX_VALUE, 1);
      Object _jsp_oldVar_1 = pageContext.getAttribute("one");
      while (_jsp_iter_1.hasNext()) {
        Object _jsp_i_1 = _jsp_iter_1.next();
        pageContext.setAttribute("one", _jsp_i_1);
        _jsp_loop_0.setCurrent(_jsp_i_1, _jsp_iter_1.hasNext());
        out.write(_jsp_string12, 0, _jsp_string12.length);
        _caucho_expr_9.print(out, pageContext, false);
        out.write(_jsp_string13, 0, _jsp_string13.length);
        _caucho_expr_10.print(out, pageContext, false);
        out.write(_jsp_string14, 0, _jsp_string14.length);
      }
      pageContext.pageSetOrRemove("one", _jsp_oldVar_1);
      out.write(_jsp_string15, 0, _jsp_string15.length);
      if (_jsp_loop_0 == null)
        _jsp_loop_0 = new com.caucho.jsp.IteratorLoopSupportTag();
      java.util.Iterator _jsp_iter_2 = com.caucho.jstl.el.ForEachTag.getIterator(_caucho_expr_11.evalObject(pageContext));
      _jsp_loop_0.init(0, Integer.MAX_VALUE, 1);
      Object _jsp_oldVar_2 = pageContext.getAttribute("one");
      while (_jsp_iter_2.hasNext()) {
        Object _jsp_i_2 = _jsp_iter_2.next();
        pageContext.setAttribute("one", _jsp_i_2);
        _jsp_loop_0.setCurrent(_jsp_i_2, _jsp_iter_2.hasNext());
        out.write(_jsp_string16, 0, _jsp_string16.length);
        _caucho_expr_9.print(out, pageContext, false);
        out.write(_jsp_string13, 0, _jsp_string13.length);
        _caucho_expr_10.print(out, pageContext, false);
        out.write(_jsp_string14, 0, _jsp_string14.length);
      }
      pageContext.pageSetOrRemove("one", _jsp_oldVar_2);
      out.write(_jsp_string17, 0, _jsp_string17.length);
      _caucho_expr_12.print(out, pageContext, false);
      out.write(_jsp_string18, 0, _jsp_string18.length);
      if (_caucho_expr_13.evalBoolean(pageContext)) {
        out.write(_jsp_string19, 0, _jsp_string19.length);
      }
      out.write(_jsp_string20, 0, _jsp_string20.length);
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
    depend = new com.caucho.vfs.Depend(appDir.lookup("admin/admin_Edit.jsp"), 5852221838346775611L, false);
    com.caucho.jsp.JavaPage.addDepend(_caucho_depends, depend);
  }
  private final static com.caucho.el.Expr _caucho_expr_0 =
    new com.caucho.el.IdExpr("method");
  private final static com.caucho.el.Expr _caucho_expr_1 =
    new com.caucho.el.PathExpr(new com.caucho.el.ArrayExpr(new com.caucho.el.IdExpr("member"), new com.caucho.el.StringLiteral("id")), "member.id");
  private final static com.caucho.el.Expr _caucho_expr_2 =
    new com.caucho.el.PathExpr(new com.caucho.el.ArrayExpr(new com.caucho.el.IdExpr("member"), new com.caucho.el.StringLiteral("account")), "member.account");
  private final static com.caucho.el.Expr _caucho_expr_3 =
    new com.caucho.el.EqExpr(6, new com.caucho.el.IdExpr("method"), new com.caucho.el.StringLiteral("doAdd"));
  private final static com.caucho.el.Expr _caucho_expr_4 =
    new com.caucho.el.EqExpr(6, new com.caucho.el.IdExpr("method"), new com.caucho.el.StringLiteral("doEdit"));
  private final static com.caucho.el.Expr _caucho_expr_5 =
    new com.caucho.el.PathExpr(new com.caucho.el.ArrayExpr(new com.caucho.el.IdExpr("member"), new com.caucho.el.StringLiteral("username")), "member.username");
  private final static com.caucho.el.Expr _caucho_expr_6 =
    new com.caucho.el.PathExpr(new com.caucho.el.ArrayExpr(new com.caucho.el.IdExpr("member"), new com.caucho.el.StringLiteral("phone")), "member.phone");
  private final static com.caucho.el.Expr _caucho_expr_7 =
    new com.caucho.el.PathExpr(new com.caucho.el.ArrayExpr(new com.caucho.el.IdExpr("member"), new com.caucho.el.StringLiteral("email")), "member.email");
  private final static com.caucho.el.Expr _caucho_expr_8 =
    new com.caucho.el.IdExpr("roles");
  private final static com.caucho.el.Expr _caucho_expr_9 =
    new com.caucho.el.PathExpr(new com.caucho.el.ArrayExpr(new com.caucho.el.ArrayExpr(new com.caucho.el.IdExpr("one"), new com.caucho.el.StringLiteral("value")), new com.caucho.el.StringLiteral("id")), "one.value.id");
  private final static com.caucho.el.Expr _caucho_expr_10 =
    new com.caucho.el.PathExpr(new com.caucho.el.ArrayExpr(new com.caucho.el.ArrayExpr(new com.caucho.el.IdExpr("one"), new com.caucho.el.StringLiteral("value")), new com.caucho.el.StringLiteral("rolename")), "one.value.rolename");
  private final static com.caucho.el.Expr _caucho_expr_11 =
    new com.caucho.el.IdExpr("roles_old");
  private final static com.caucho.el.Expr _caucho_expr_12 =
    new com.caucho.el.PathExpr(new com.caucho.el.ArrayExpr(new com.caucho.el.IdExpr("member"), new com.caucho.el.StringLiteral("grouplist")), "member.grouplist");
  private final static com.caucho.el.Expr _caucho_expr_13 =
    new com.caucho.el.EqExpr(6, new com.caucho.el.IdExpr("iscancel"), new com.caucho.el.StringLiteral("1"));

  private final static char []_jsp_string3;
  private final static char []_jsp_string12;
  private final static char []_jsp_string9;
  private final static char []_jsp_string11;
  private final static char []_jsp_string19;
  private final static char []_jsp_string6;
  private final static char []_jsp_string4;
  private final static char []_jsp_string8;
  private final static char []_jsp_string1;
  private final static char []_jsp_string13;
  private final static char []_jsp_string14;
  private final static char []_jsp_string5;
  private final static char []_jsp_string16;
  private final static char []_jsp_string2;
  private final static char []_jsp_string20;
  private final static char []_jsp_string17;
  private final static char []_jsp_string0;
  private final static char []_jsp_string18;
  private final static char []_jsp_string15;
  private final static char []_jsp_string10;
  private final static char []_jsp_string7;
  static {
    _jsp_string3 = "\"  class=\"validation\" requiry=\"true\">\r\n		</div>\r\n	  </div>\r\n	 \r\n	  <div class=\"control-group\">\r\n		<label class=\"control-label\" >\u5bc6\u7801</label>		\r\n		<div class=\"controls\">\r\n		  <input type=\"password\" name=\"pwd\"  ".toCharArray();
    _jsp_string12 = "\r\n                     	<option value=\"".toCharArray();
    _jsp_string9 = "\"  class=\"validation\"  requiry=\"true\"  >\r\n		</div>\r\n	  </div>\r\n	 \r\n	\r\n	  </div>\r\n	  <div class=\"control-group\">\r\n		<label class=\"control-label\" >\u8054\u7cfb\u7535\u8bdd</label>\r\n		<div class=\"controls\">\r\n		  <input type=\"text\" name=\"phone\"  value=\"".toCharArray();
    _jsp_string11 = "\"   >		 \r\n		</div>\r\n	  </div>\r\n	  <div class=\"control-group\">\r\n		<label class=\"control-label\">\u89d2\u8272</label>\r\n		<div class=\"controls  \">			\r\n				<select id=\"list1\" multiple=\"multiple\"  style=\"width:120px; height:150px\" ondblclick=\"moveOption('list1', 'list2')\" >\r\n				".toCharArray();
    _jsp_string19 = "\r\n			&nbsp; &nbsp; &nbsp;\r\n			<button class=\"btn btn-small\" type=\"reset\" onclick=\"goback()\"><i class=\"icon-undo\"></i>\u53d6\u6d88</button>\r\n			".toCharArray();
    _jsp_string6 = " placeholder=\"\u5982\u679c\u4e0d\u4fee\u5bc6\u7801\uff0c\u6b64\u9879\u4fdd\u6301\u4e3a\u7a7a\"".toCharArray();
    _jsp_string4 = " class=\"validation\" ".toCharArray();
    _jsp_string8 = "  requiry=\"true\" >\r\n		</div>\r\n	  </div>\r\n	  \r\n	  <div class=\"control-group\">\r\n		<label class=\"control-label\" >\u59d3\u540d</label>\r\n		<div class=\"controls\">\r\n		  <input type=\"text\" name=\"username\"  value=\"".toCharArray();
    _jsp_string1 = "&id=".toCharArray();
    _jsp_string13 = "\">".toCharArray();
    _jsp_string14 = "</option>\r\n                     	".toCharArray();
    _jsp_string5 = "   ".toCharArray();
    _jsp_string16 = "\r\n                     		<option value=\"".toCharArray();
    _jsp_string2 = "\" method=\"post\" id=\"form\">\r\n	 \r\n	  \r\n	  <div class=\"control-group\">\r\n		<label class=\"control-label\" for=\"form-field-accout\">\u8d26\u53f7</label>\r\n		<div class=\"controls\">\r\n		  <input type=\"text\" name=\"account\" value=\"".toCharArray();
    _jsp_string20 = "\r\n	  </div>\r\n </form>\r\n<script>\r\n\r\n	\r\n	$(document).ready(function () {\r\n	    //\u5b9a\u4e49\u8868\u5355\u63d0\u4ea4\u524d\u4e0e\u63d0\u4ea4\u540e\u7684\u5904\u7406\u65b9\u6cd5\u53ca\u8d85\u65f6\u76f8\u5173\r\n	    var options = {\r\n	                beforeSubmit:showRequest,\r\n	                success:showResponse,\r\n	                timeout:3000\r\n	            };\r\n	    $('#form').submit(function() { \r\n	     // submit the form \r\n	     $(this).ajaxSubmit(options); \r\n	     // return false to prevent normal browser submit and page navigation \r\n	     return false; \r\n	    });\r\n	});\r\n	\r\n\r\n\r\nfunction showRequest(formData, jqForm, options) \r\n{\r\nvalidation_result=true;\r\n	\r\n	$('.validation').each(function(){\r\n		\r\n		validat($(this));\r\n	});\r\n	\r\n	if(!validation_result)\r\n	{\r\n			\r\n		return false;\r\n	}\r\n	return true;\r\n}\r\nfunction showResponse(responseText, statusText, xhr, $form)  \r\n{ \r\n	bootbox.alert(\"\u7528\u6237\u4fe1\u606f\u7f16\u8f91\u6210\u529f\",\r\n	function() {\r\n		nav('admin.do?method=query','\u7528\u6237\u7ba1\u7406');\r\n		});\r\n}\r\n\r\n\r\n\r\n$('.validation').blur(function(){\r\n	\r\n	validat($(this));\r\n	\r\n});\r\n\r\n\r\nfunction validat(obj)\r\n{\r\n	if(obj.attr('requiry')&&obj.val()=='')\r\n	{\r\n		obj.siblings().remove();\r\n		obj.parent().parent().removeClass('error');\r\n		obj.after(\"&nbsp;<i class='icon-remove-sign' ></i><span class='help-inline' >\u5fc5\u586b\u9879\uff01</span>\");\r\n		obj.parent().parent().addClass('error');\r\n		\r\n		validation_result=false;\r\n		\r\n	}else\r\n	{\r\n		obj.parent().parent().removeClass('error');\r\n		obj.siblings().remove();\r\n	}\r\n}\r\n\r\n\r\nfunction moveOption(e1, e2)\r\n{\r\n\r\n	e1=$('#'+e1)[0];\r\n\r\n	e2=$('#'+e2)[0];\r\n\r\n   try\r\n   {\r\n      for(var i = 0; i < e1.options.length; i ++ )\r\n      {\r\n         if(e1.options[i].selected)\r\n         {\r\n            var e = e1.options[i];\r\n            e2.options.add(new Option(e.text, e.value));\r\n            e1.remove(i);\r\n            i = i - 1;\r\n         }\r\n      }\r\n 	// alert(getvalue($('#list2')[0]));\r\n      $('#role_hid').val(getvalue($('#list2')[0]))\r\n   }\r\n   catch(e)\r\n   {\r\n   }\r\n}\r\nfunction getvalue(geto)\r\n{\r\n   var allvalue = \"\";\r\n   for(var i = 0; i < geto.options.length; i ++ )\r\n   {\r\n      allvalue += geto.options[i].value + \"|\";\r\n   }\r\n   \r\n   if(allvalue.length>1)\r\n   {\r\n   		 allvalue = allvalue.substr(0,allvalue.length-1);\r\n   }\r\n   \r\n   return allvalue;\r\n}\r\nfunction moveAllOption(e1, e2)\r\n{\r\n	e1=$('#'+e1)[0];\r\n\r\n	e2=$('#'+e2)[0];\r\n	\r\n   try\r\n   {\r\n      for(var i = 0; i < e1.options.length; i ++ )\r\n      {\r\n         var e = e1.options[i];\r\n         e2.options.add(new Option(e.text, e.value));\r\n         e1.remove(i);\r\n         i = i - 1;\r\n      }\r\n      $('#role_hid').val(getvalue($('#list2')[0]))\r\n   }\r\n   catch(e)\r\n   {\r\n\r\n   }\r\n}\r\n\r\n\r\n</script>\r\n\r\n\r\n\r\n".toCharArray();
    _jsp_string17 = "\r\n				</select>		\r\n				<input type=\"hidden\"  name=\"grouplist\" id=\"role_hid\" size=\"40\" value=\"".toCharArray();
    _jsp_string0 = "\r\n\r\n<form class=\"form-horizontal\" action=\"admin.do?method=".toCharArray();
    _jsp_string18 = "\" />	\r\n		</div>\r\n	  </div>\r\n	  \r\n	\r\n	  \r\n	  <div class=\"form-actions\">\r\n			<button class=\"btn btn-small btn-info\" type=\"submit\" id=\"submit\"><i class=\"icon-ok\"></i>\u63d0\u4ea4</button>	\r\n			&nbsp; &nbsp; &nbsp;\r\n			<button class=\"btn btn-small\" type=\"reset\"><i class=\"icon-undo\"></i> Reset</button>\r\n			".toCharArray();
    _jsp_string15 = "\r\n				</select>			\r\n				<div class=\"btn-group btn-group-vertical\">\r\n					<input type=\"button\" value=\"\u6dfb\u52a0\" class=\"btn btn-minier\" onClick=\"moveOption('list1', 'list2')\" />\r\n					<input type=\"button\" value=\"\u5168\u9009\" class=\"btn btn-minier\" onClick=\"moveAllOption('list1','list2')\" />\r\n					<input type=\"button\" value=\"\u5220\u9664\" class=\"btn btn-minier\" onClick=\"moveOption('list2', 'list1')\" />\r\n					<input type=\"button\" value=\"\u5168\u5220\" class=\"btn btn-minier\" onClick=\"moveAllOption('list2', 'list1')\" />\r\n				</div>			\r\n				<select multiple=\"multiple\"  style=\"width:100px;height:150px;\" id=\"list2\"  ondblclick=\"moveOption('list2', 'list1')\">>\r\n					".toCharArray();
    _jsp_string10 = "\"   class=\"validation\" requiry=\"true\"  >\r\n         </div>\r\n	  </div>\r\n	 \r\n	  \r\n	  <div class=\"control-group\">\r\n		<label class=\"control-label\" >E-mail</label>\r\n		<div class=\"controls\">\r\n		  <input type=\"text\" name=\"email\"  value=\"".toCharArray();
    _jsp_string7 = " requiry=\"true\" >\r\n		</div>\r\n	  </div>\r\n	  <div class=\"control-group\">\r\n		<label class=\"control-label\" >\u91cd\u590d\u5bc6\u7801</label>\r\n		<div class=\"controls\">\r\n		  <input type=\"password\" ".toCharArray();
  }
}
