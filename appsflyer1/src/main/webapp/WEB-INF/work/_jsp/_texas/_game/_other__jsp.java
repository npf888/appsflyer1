/*
 * JSP generated by Resin-3.0.19 (built Mon, 15 May 2006 04:50:47 PDT)
 */

package _jsp._texas._game;
import javax.servlet.*;
import javax.servlet.jsp.*;
import javax.servlet.http.*;
import java.util.*;

public class _other__jsp extends com.caucho.jsp.JavaPage{
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
      if (_jsp_loop_0 == null)
        _jsp_loop_0 = new com.caucho.jsp.IteratorLoopSupportTag();
      java.util.Iterator _jsp_iter_1 = com.caucho.jstl.el.ForEachTag.getIterator(_caucho_expr_2.evalObject(pageContext));
      _jsp_loop_0.init(0, Integer.MAX_VALUE, 1);
      Object _jsp_status_1 = pageContext.putAttribute("status", _jsp_loop_0);
      Object _jsp_oldVar_1 = pageContext.getAttribute("user");
      while (_jsp_iter_1.hasNext()) {
        Object _jsp_i_1 = _jsp_iter_1.next();
        pageContext.setAttribute("user", _jsp_i_1);
        _jsp_loop_0.setCurrent(_jsp_i_1, _jsp_iter_1.hasNext());
        out.write(_jsp_string3, 0, _jsp_string3.length);
        _caucho_expr_3.print(out, pageContext, false);
        out.write(_jsp_string4, 0, _jsp_string4.length);
        _caucho_expr_4.print(out, pageContext, false);
        out.write(_jsp_string5, 0, _jsp_string5.length);
        _caucho_expr_3.print(out, pageContext, false);
        out.write(_jsp_string6, 0, _jsp_string6.length);
        _caucho_expr_5.print(out, pageContext, false);
        out.write(_jsp_string7, 0, _jsp_string7.length);
        _caucho_expr_6.print(out, pageContext, false);
        out.write(_jsp_string8, 0, _jsp_string8.length);
        _caucho_expr_7.print(out, pageContext, false);
        out.write(_jsp_string8, 0, _jsp_string8.length);
        _caucho_expr_8.print(out, pageContext, false);
        out.write(_jsp_string9, 0, _jsp_string9.length);
        _caucho_expr_9.print(out, pageContext, false);
        out.write(_jsp_string10, 0, _jsp_string10.length);
        _caucho_expr_10.print(out, pageContext, false);
        out.write(_jsp_string11, 0, _jsp_string11.length);
        if (_caucho_expr_11.evalBoolean(pageContext)) {
          out.write(_jsp_string12, 0, _jsp_string12.length);
          _caucho_expr_12.print(out, pageContext, false);
          out.write(_jsp_string13, 0, _jsp_string13.length);
        }
        out.write(_jsp_string14, 0, _jsp_string14.length);
        if (_caucho_expr_13.evalBoolean(pageContext)) {
          out.write(_jsp_string15, 0, _jsp_string15.length);
          _caucho_expr_12.print(out, pageContext, false);
          out.write(_jsp_string16, 0, _jsp_string16.length);
        }
        out.write(_jsp_string17, 0, _jsp_string17.length);
        _caucho_expr_12.print(out, pageContext, false);
        out.write(_jsp_string18, 0, _jsp_string18.length);
      }
      pageContext.pageSetOrRemove("user", _jsp_oldVar_1);
      pageContext.pageSetOrRemove("status", _jsp_status_1);
      out.write(_jsp_string19, 0, _jsp_string19.length);
      pageContext.include("/common/page.jsp", true);
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
    depend = new com.caucho.vfs.Depend(appDir.lookup("texas/game/other.jsp"), -4635654218897504688L, false);
    com.caucho.jsp.JavaPage.addDepend(_caucho_depends, depend);
  }
  private final static com.caucho.el.Expr _caucho_expr_0 =
    new com.caucho.el.IdExpr("account");
  private final static com.caucho.el.Expr _caucho_expr_1 =
    new com.caucho.el.IdExpr("username");
  private final static com.caucho.el.Expr _caucho_expr_2 =
    new com.caucho.el.PathExpr(new com.caucho.el.ArrayExpr(new com.caucho.el.IdExpr("PAGER"), new com.caucho.el.StringLiteral("items")), "PAGER.items");
  private final static com.caucho.el.Expr _caucho_expr_3 =
    new com.caucho.el.PathExpr(new com.caucho.el.ArrayExpr(new com.caucho.el.IdExpr("user"), new com.caucho.el.StringLiteral("id")), "user.id");
  private final static com.caucho.el.Expr _caucho_expr_4 =
    new com.caucho.el.BinaryExpr(1, new com.caucho.el.BinaryExpr(1, new com.caucho.el.PathExpr(new com.caucho.el.ArrayExpr(new com.caucho.el.IdExpr("status"), new com.caucho.el.StringLiteral("index")), "status.index"), new com.caucho.el.LongLiteral(1L)), new com.caucho.el.PathExpr(new com.caucho.el.ArrayExpr(new com.caucho.el.IdExpr("PAGER"), new com.caucho.el.StringLiteral("start")), "PAGER.start"));
  private final static com.caucho.el.Expr _caucho_expr_5 =
    new com.caucho.el.PathExpr(new com.caucho.el.ArrayExpr(new com.caucho.el.IdExpr("user"), new com.caucho.el.StringLiteral("charid")), "user.charid");
  private final static com.caucho.el.Expr _caucho_expr_6 =
    new com.caucho.el.PathExpr(new com.caucho.el.ArrayExpr(new com.caucho.el.IdExpr("user"), new com.caucho.el.StringLiteral("onlinetime")), "user.onlinetime");
  private final static com.caucho.el.Expr _caucho_expr_7 =
    new com.caucho.el.PathExpr(new com.caucho.el.ArrayExpr(new com.caucho.el.IdExpr("user"), new com.caucho.el.StringLiteral("lastgettime")), "user.lastgettime");
  private final static com.caucho.el.Expr _caucho_expr_8 =
    new com.caucho.el.PathExpr(new com.caucho.el.ArrayExpr(new com.caucho.el.IdExpr("user"), new com.caucho.el.StringLiteral("currentonlinerewardid")), "user.currentonlinerewardid");
  private final static com.caucho.el.Expr _caucho_expr_9 =
    new com.caucho.el.PathExpr(new com.caucho.el.ArrayExpr(new com.caucho.el.IdExpr("user"), new com.caucho.el.StringLiteral("update_time")), "user.update_time");
  private final static com.caucho.el.Expr _caucho_expr_10 =
    new com.caucho.el.PathExpr(new com.caucho.el.ArrayExpr(new com.caucho.el.IdExpr("user"), new com.caucho.el.StringLiteral("create_time")), "user.create_time");
  private final static com.caucho.el.Expr _caucho_expr_11 =
    new com.caucho.el.EqExpr(7, new com.caucho.el.PathExpr(new com.caucho.el.ArrayExpr(new com.caucho.el.IdExpr("user"), new com.caucho.el.StringLiteral("status")), "user.status"), new com.caucho.el.StringLiteral("blocked"));
  private final static com.caucho.el.Expr _caucho_expr_12 =
    new com.caucho.el.PathExpr(new com.caucho.el.ArrayExpr(new com.caucho.el.IdExpr("user"), new com.caucho.el.StringLiteral("uuid")), "user.uuid");
  private final static com.caucho.el.Expr _caucho_expr_13 =
    new com.caucho.el.EqExpr(6, new com.caucho.el.PathExpr(new com.caucho.el.ArrayExpr(new com.caucho.el.IdExpr("user"), new com.caucho.el.StringLiteral("status")), "user.status"), new com.caucho.el.StringLiteral("blocked"));

  private final static char []_jsp_string1;
  private final static char []_jsp_string10;
  private final static char []_jsp_string0;
  private final static char []_jsp_string8;
  private final static char []_jsp_string5;
  private final static char []_jsp_string11;
  private final static char []_jsp_string2;
  private final static char []_jsp_string20;
  private final static char []_jsp_string16;
  private final static char []_jsp_string3;
  private final static char []_jsp_string7;
  private final static char []_jsp_string9;
  private final static char []_jsp_string13;
  private final static char []_jsp_string17;
  private final static char []_jsp_string15;
  private final static char []_jsp_string4;
  private final static char []_jsp_string18;
  private final static char []_jsp_string19;
  private final static char []_jsp_string12;
  private final static char []_jsp_string14;
  private final static char []_jsp_string6;
  static {
    _jsp_string1 = "\"  id=\"account\" ></input>				\r\n				<label >Username</label>\r\n				<input type=\"text\" class=\"input-medium\" value=\"".toCharArray();
    _jsp_string10 = " </td>\r\n	                 <td>".toCharArray();
    _jsp_string0 = "\r\n\r\n\r\n\r\n<div id=\"ami_main\">\r\n<div class=\"row-fluid\">	\r\n			<form class=\"form-inline\" action=\"game.do?method=queryWeekCard\" id=\"searchForm\">\r\n				<label>Useraccount</label>\r\n				<input  type=\"text\" class=\"input-medium\"  name=\"useraccount\" value=\"".toCharArray();
    _jsp_string8 = "</td>\r\n					<td>".toCharArray();
    _jsp_string5 = "</td>\r\n					<td><a onclick=\"openNewWinow('player.do?method=loadByID&id=".toCharArray();
    _jsp_string11 = "</td>\r\n\r\n					<td>\r\n						<div class='hidden-phone visible-desktop btn-group'>\r\n						".toCharArray();
    _jsp_string2 = "\" name=\"username\"  ></input>\r\n				<button type=\"button\"  style=\"margin-left:10px\" class=\"btn btn-mini btn-info\" onClick=\"go(1)\">\u68c0\u7d22</button>\r\n			</form>\r\n</div>	\r\n<hr/>\r\n\r\n<div class=\"row-fluid\">\r\n	\r\n		<div class=\"ami_table_header\"><span class=\"ami_table_header_title\">\u5176\u5b83\u4fe1\u606f</span>\r\n\r\n		</div>\r\n		<table id=\"table_report\" class=\"table table-striped table-bordered table-hover\">\r\n			<thead>			\r\n				<tr>\r\n				<th>No.</th>\r\n					\r\n					<th>\u73a9\u5bb6\u89d2\u8272ID</th>\r\n					<th>\u5728\u7ebf\u65f6\u95f4</th>\r\n					<th>\u4e0a\u6b21\u9886\u53d6\u65f6\u95f4</th>\r\n					<th> \u5f53\u524d\u5728\u7ebf\u5956\u52b1ID</th>\r\n					<th>\u66f4\u65b0\u65f6\u95f4</th>\r\n					<th>\u521b\u5efa\u65f6\u95f4</th>			\r\n				</tr>				\r\n			</thead>\r\n			<tbody>				\r\n			 ".toCharArray();
    _jsp_string20 = "\r\n		\r\n	</div>	\r\n</div>\r\n<div id=\"ami_newwindow\" style=\"display: none;\">\r\n\r\n</div>\r\n".toCharArray();
    _jsp_string16 = "')\"><i class='icon-ok-sign'></i></button>\r\n						".toCharArray();
    _jsp_string3 = "\r\n					\r\n				<tr id=\"tr_".toCharArray();
    _jsp_string7 = "</a></td>\r\n					\r\n					<td>".toCharArray();
    _jsp_string9 = "</td>\r\n	                 <td>".toCharArray();
    _jsp_string13 = "')\"><i class='icon-ban-circle'></i></button>\r\n						".toCharArray();
    _jsp_string17 = "\r\n							<button class='btn btn-mini btn-danger' onclick=\"doDelete('".toCharArray();
    _jsp_string15 = "\r\n						<button class='btn btn-mini btn-info' onclick=\"doBlock('".toCharArray();
    _jsp_string4 = "\">\r\n					\r\n					<td>".toCharArray();
    _jsp_string18 = "')\"><i class='icon-trash'></i></button>						\r\n						</div>\r\n					</td>\r\n				</tr>\r\n				\r\n			  ".toCharArray();
    _jsp_string19 = "\r\n					\r\n			</tbody>\r\n		</table>		\r\n		".toCharArray();
    _jsp_string12 = "							\r\n							<button class='btn btn-mini btn-info' onclick=\"removeBlock('".toCharArray();
    _jsp_string14 = "\r\n						".toCharArray();
    _jsp_string6 = "')\">".toCharArray();
  }
}