/*
 * JSP generated by Resin-3.0.19 (built Mon, 15 May 2006 04:50:47 PDT)
 */

package _jsp._texas._log;
import javax.servlet.*;
import javax.servlet.jsp.*;
import javax.servlet.http.*;
import java.util.*;

public class _log_0channel__jsp extends com.caucho.jsp.JavaPage{
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
    response.setContentType("text/html; charset=UTF-8");
    request.setCharacterEncoding("UTF-8");
    com.caucho.jsp.IteratorLoopSupportTag _jsp_loop_0 = null;
    org.apache.taglibs.standard.tag.rt.fmt.FormatNumberTag _jsp_FormatNumberTag_0 = null;
    try {
      out.write(_jsp_string0, 0, _jsp_string0.length);
      _caucho_expr_0.print(out, pageContext, false);
      out.write(_jsp_string1, 0, _jsp_string1.length);
      if (_jsp_loop_0 == null)
        _jsp_loop_0 = new com.caucho.jsp.IteratorLoopSupportTag();
      _jsp_loop_0.setParent((javax.servlet.jsp.tagext.Tag) null);
      java.util.Iterator _jsp_iter_1 = com.caucho.jstl.el.ForEachTag.getIterator(_caucho_expr_1.evalObject(pageContext));
      _jsp_loop_0.init(0, Integer.MAX_VALUE, 1);
      Object _jsp_oldVar_1 = pageContext.getAttribute("MapData");
      while (_jsp_iter_1.hasNext()) {
        Object _jsp_i_1 = _jsp_iter_1.next();
        pageContext.setAttribute("MapData", _jsp_i_1);
        _jsp_loop_0.setCurrent(_jsp_i_1, _jsp_iter_1.hasNext());
        out.write(_jsp_string2, 0, _jsp_string2.length);
        _caucho_expr_2.print(out, pageContext, false);
        out.write(_jsp_string3, 0, _jsp_string3.length);
        _caucho_expr_3.print(out, pageContext, false);
        out.write(_jsp_string3, 0, _jsp_string3.length);
        _caucho_expr_4.print(out, pageContext, false);
        out.write(_jsp_string3, 0, _jsp_string3.length);
        _caucho_expr_5.print(out, pageContext, false);
        out.write(_jsp_string3, 0, _jsp_string3.length);
        if (_jsp_FormatNumberTag_0 == null) {
          _jsp_FormatNumberTag_0 = new org.apache.taglibs.standard.tag.rt.fmt.FormatNumberTag();
          _jsp_FormatNumberTag_0.setPageContext(pageContext);
          _jsp_FormatNumberTag_0.setParent((javax.servlet.jsp.tagext.Tag) _jsp_loop_0);
          _jsp_FormatNumberTag_0.setType("number");
          _jsp_FormatNumberTag_0.setMaxFractionDigits(2);
        }

        _jsp_FormatNumberTag_0.setValue(_caucho_expr_6.evalObject(pageContext));
        _jsp_FormatNumberTag_0.doEndTag();
        out.write(_jsp_string4, 0, _jsp_string4.length);
      }
      pageContext.pageSetOrRemove("MapData", _jsp_oldVar_1);
      out.write(_jsp_string5, 0, _jsp_string5.length);
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
    depend = new com.caucho.vfs.Depend(appDir.lookup("texas/log/log_channel.jsp"), 8295855278225571311L, false);
    com.caucho.jsp.JavaPage.addDepend(_caucho_depends, depend);
    depend = new com.caucho.vfs.Depend(appDir.lookup("WEB-INF/tlds/fmt.tld"), 7849922606946648492L, false);
    com.caucho.jsp.JavaPage.addDepend(_caucho_depends, depend);
    com.caucho.jsp.JavaPage.addDepend(_caucho_depends, new com.caucho.make.ClassDependency(org.apache.taglibs.standard.tag.rt.fmt.FormatNumberTag.class, 2490015424821522243L));
  }
  private final static com.caucho.el.Expr _caucho_expr_0 =
    new com.caucho.el.IdExpr("slotwinnerTime");
  private final static com.caucho.el.Expr _caucho_expr_1 =
    new com.caucho.el.IdExpr("listData");
  private final static com.caucho.el.Expr _caucho_expr_2 =
    new com.caucho.el.PathExpr(new com.caucho.el.ArrayExpr(new com.caucho.el.IdExpr("MapData"), new com.caucho.el.StringLiteral("countries")), "MapData.countries");
  private final static com.caucho.el.Expr _caucho_expr_3 =
    new com.caucho.el.PathExpr(new com.caucho.el.ArrayExpr(new com.caucho.el.IdExpr("MapData"), new com.caucho.el.StringLiteral("DVU")), "MapData.DVU");
  private final static com.caucho.el.Expr _caucho_expr_4 =
    new com.caucho.el.PathExpr(new com.caucho.el.ArrayExpr(new com.caucho.el.IdExpr("MapData"), new com.caucho.el.StringLiteral("DNU")), "MapData.DNU");
  private final static com.caucho.el.Expr _caucho_expr_5 =
    new com.caucho.el.PathExpr(new com.caucho.el.ArrayExpr(new com.caucho.el.IdExpr("MapData"), new com.caucho.el.StringLiteral("DPU")), "MapData.DPU");
  private final static com.caucho.el.Expr _caucho_expr_6 =
    new com.caucho.el.BinaryExpr(4, new com.caucho.el.BinaryExpr(3, new com.caucho.el.BinaryExpr(3, new com.caucho.el.PathExpr(new com.caucho.el.ArrayExpr(new com.caucho.el.IdExpr("MapData"), new com.caucho.el.StringLiteral("Income")), "MapData.Income"), new com.caucho.el.DoubleLiteral(0.1)), new com.caucho.el.LongLiteral(10L)), new com.caucho.el.LongLiteral(100L));

  private final static char []_jsp_string1;
  private final static char []_jsp_string0;
  private final static char []_jsp_string3;
  private final static char []_jsp_string2;
  private final static char []_jsp_string5;
  private final static char []_jsp_string4;
  static {
    _jsp_string1 = " \u4eca\u65e5\u603b\u89c8</span>\r\n\r\n		</div>\r\n		<table id=\"table_report\" class=\"table table-striped table-bordered table-hover dataTables-example\">\r\n			<thead>			\r\n				<tr>	\r\n					   <th>Country</th>\r\n					   <th>DAU</th>\r\n					   <th>DNU</th>\r\n					   <th>DPU</th>\r\n					   <th>Income</th>\r\n					   \r\n				</tr>				\r\n			</thead>\r\n			<tbody style=\"position:relative\">		\r\n				\r\n			 ".toCharArray();
    _jsp_string0 = "\r\n\r\n\r\n\r\n\r\n\r\n<link rel=\"stylesheet\" type=\"text/css\" href=\"/js/Clockpicker/bootstrap-clockpicker.min.css\">\r\n    <!-- Data Tables -->\r\n    <link href=\"css/dataTables/dataTables.bootstrap.css\" rel=\"stylesheet\">\r\n    <link href=\"css/dataTables/dataTables.responsive.css\" rel=\"stylesheet\">\r\n    <link href=\"css/dataTables/dataTables.tableTools.min.css\" rel=\"stylesheet\">\r\n<style>\r\n.dataTables_wrapper {\r\n    padding-bottom: 30px;\r\n}	\r\n.dataTables_length {\r\n    float: left;\r\n}\r\n\r\n\r\n</style>\r\n\r\n<div id=\"ami_main\">\r\n	<div class=\"row-fluid\" id=\"div1\">	\r\n			\r\n			\r\n	</div>\r\n<hr/>\r\n<div class=\"row-fluid\">\r\n	<div class=\"table-responsive\" style=\"overflow-x:scroll\">\r\n		<div class=\"ami_table_header\"><span class=\"ami_table_header_title\">".toCharArray();
    _jsp_string3 = "</td>\r\n			    <td>".toCharArray();
    _jsp_string2 = "\r\n			 <tr>\r\n			    <td>".toCharArray();
    _jsp_string5 = "\r\n					\r\n			</tbody>\r\n		</table>		\r\n	</div>\r\n	</div>	\r\n</div>\r\n\r\n<div id=\"ami_newwindow\" style=\"display: none;\">\r\n\r\n</div>\r\n<script type=\"text/javascript\" src=\"/js/Clockpicker/bootstrap-clockpicker.min.js\"></script>\r\n    <!-- Data Tables -->\r\n    <script charset=\"utf-8\" src=\"js/dataTables/jquery.dataTables.js\"></script>\r\n    <script charset=\"utf-8\" src=\"js/dataTables/dataTables.bootstrap.js\"></script>\r\n    <script charset=\"utf-8\" src=\"js/dataTables/dataTables.responsive.js\"></script>\r\n    <script charset=\"utf-8\" src=\"js/dataTables/dataTables.tableTools.min.js\"></script>\r\n\r\n<script charset=\"utf-8\" >\r\n$(document).ready(function() {\r\n    $('.dataTables-example').DataTable({\r\n        \"dom\": 'lTfigpt',\r\n        \"order\":[[0, \"desc\"]],\r\n        buttons: [\r\n                  'copy','excel'\r\n              ],\r\n        \"tableTools\": {\r\n            \"sSwfPath\": \"js/dataTables/swf/copy_csv_xls_pdf.swf\"\r\n        }\r\n    });\r\n});\r\n\r\nfunction go(page)\r\n{\r\n	$('.ami_Mask').show();\r\n	var params = $('#searchForm').formSerialize();\r\n	\r\n	var url = $('#searchForm').attr('action') +\"&currentPage=\"+page;\r\n	$.post(url,params,function (data){\r\n	\r\n	$('#PAGECONTENT').html(data);\r\n	\r\n	$('.ami_Mask').hide();\r\n	\r\n	});\r\n}\r\n</script>".toCharArray();
    _jsp_string4 = "</td>\r\n			 </tr>\r\n			  ".toCharArray();
  }
}
