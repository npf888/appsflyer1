/*
 * JSP generated by Resin-3.0.19 (built Mon, 15 May 2006 04:50:47 PDT)
 */

package _jsp._texas._log;
import javax.servlet.*;
import javax.servlet.jsp.*;
import javax.servlet.http.*;
import java.util.*;

public class _log_0summary__jsp extends com.caucho.jsp.JavaPage{
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
      _caucho_expr_1.print(out, pageContext, false);
      out.write(_jsp_string2, 0, _jsp_string2.length);
      _caucho_expr_2.print(out, pageContext, false);
      out.write(_jsp_string3, 0, _jsp_string3.length);
      _caucho_expr_3.print(out, pageContext, false);
      out.write(_jsp_string4, 0, _jsp_string4.length);
      _caucho_expr_4.print(out, pageContext, false);
      out.write(_jsp_string5, 0, _jsp_string5.length);
      _caucho_expr_0.print(out, pageContext, false);
      out.write(_jsp_string6, 0, _jsp_string6.length);
      if (_jsp_loop_0 == null)
        _jsp_loop_0 = new com.caucho.jsp.IteratorLoopSupportTag();
      _jsp_loop_0.setParent((javax.servlet.jsp.tagext.Tag) null);
      java.util.Iterator _jsp_iter_1 = com.caucho.jstl.el.ForEachTag.getIterator(_caucho_expr_5.evalObject(pageContext));
      _jsp_loop_0.init(0, Integer.MAX_VALUE, 1);
      Object _jsp_oldVar_1 = pageContext.getAttribute("one");
      while (_jsp_iter_1.hasNext()) {
        Object _jsp_i_1 = _jsp_iter_1.next();
        pageContext.setAttribute("one", _jsp_i_1);
        _jsp_loop_0.setCurrent(_jsp_i_1, _jsp_iter_1.hasNext());
        out.write(_jsp_string7, 0, _jsp_string7.length);
        _caucho_expr_6.print(out, pageContext, false);
        out.write(_jsp_string8, 0, _jsp_string8.length);
        _caucho_expr_7.print(out, pageContext, false);
        out.write(_jsp_string9, 0, _jsp_string9.length);
        _caucho_expr_7.print(out, pageContext, false);
        out.write(_jsp_string10, 0, _jsp_string10.length);
        _caucho_expr_8.print(out, pageContext, false);
        out.write(_jsp_string11, 0, _jsp_string11.length);
        _caucho_expr_9.print(out, pageContext, false);
        out.write(_jsp_string12, 0, _jsp_string12.length);
        _caucho_expr_10.print(out, pageContext, false);
        out.write(_jsp_string13, 0, _jsp_string13.length);
        if (_jsp_FormatNumberTag_0 == null) {
          _jsp_FormatNumberTag_0 = new org.apache.taglibs.standard.tag.rt.fmt.FormatNumberTag();
          _jsp_FormatNumberTag_0.setPageContext(pageContext);
          _jsp_FormatNumberTag_0.setParent((javax.servlet.jsp.tagext.Tag) _jsp_loop_0);
          _jsp_FormatNumberTag_0.setType("number");
          _jsp_FormatNumberTag_0.setMaxFractionDigits(2);
        }

        _jsp_FormatNumberTag_0.setValue(_caucho_expr_11.evalObject(pageContext));
        _jsp_FormatNumberTag_0.doEndTag();
        out.write(_jsp_string14, 0, _jsp_string14.length);
        if (_jsp_FormatNumberTag_0 == null) {
          _jsp_FormatNumberTag_0 = new org.apache.taglibs.standard.tag.rt.fmt.FormatNumberTag();
          _jsp_FormatNumberTag_0.setPageContext(pageContext);
          _jsp_FormatNumberTag_0.setParent((javax.servlet.jsp.tagext.Tag) _jsp_loop_0);
          _jsp_FormatNumberTag_0.setType("number");
          _jsp_FormatNumberTag_0.setMaxFractionDigits(2);
        }

        _jsp_FormatNumberTag_0.setValue(_caucho_expr_12.evalObject(pageContext));
        _jsp_FormatNumberTag_0.doEndTag();
        out.write(_jsp_string15, 0, _jsp_string15.length);
        if (_jsp_FormatNumberTag_0 == null) {
          _jsp_FormatNumberTag_0 = new org.apache.taglibs.standard.tag.rt.fmt.FormatNumberTag();
          _jsp_FormatNumberTag_0.setPageContext(pageContext);
          _jsp_FormatNumberTag_0.setParent((javax.servlet.jsp.tagext.Tag) _jsp_loop_0);
          _jsp_FormatNumberTag_0.setType("number");
          _jsp_FormatNumberTag_0.setMaxFractionDigits(2);
        }

        _jsp_FormatNumberTag_0.setValue(_caucho_expr_13.evalObject(pageContext));
        _jsp_FormatNumberTag_0.doEndTag();
        out.write(_jsp_string16, 0, _jsp_string16.length);
        if (_jsp_FormatNumberTag_0 == null) {
          _jsp_FormatNumberTag_0 = new org.apache.taglibs.standard.tag.rt.fmt.FormatNumberTag();
          _jsp_FormatNumberTag_0.setPageContext(pageContext);
          _jsp_FormatNumberTag_0.setParent((javax.servlet.jsp.tagext.Tag) _jsp_loop_0);
          _jsp_FormatNumberTag_0.setType("number");
          _jsp_FormatNumberTag_0.setMaxFractionDigits(2);
        }

        _jsp_FormatNumberTag_0.setValue(_caucho_expr_14.evalObject(pageContext));
        _jsp_FormatNumberTag_0.doEndTag();
        out.write(_jsp_string17, 0, _jsp_string17.length);
        if (_jsp_FormatNumberTag_0 == null) {
          _jsp_FormatNumberTag_0 = new org.apache.taglibs.standard.tag.rt.fmt.FormatNumberTag();
          _jsp_FormatNumberTag_0.setPageContext(pageContext);
          _jsp_FormatNumberTag_0.setParent((javax.servlet.jsp.tagext.Tag) _jsp_loop_0);
          _jsp_FormatNumberTag_0.setType("number");
          _jsp_FormatNumberTag_0.setMaxFractionDigits(2);
        }

        _jsp_FormatNumberTag_0.setValue(_caucho_expr_15.evalObject(pageContext));
        _jsp_FormatNumberTag_0.doEndTag();
        out.write(_jsp_string18, 0, _jsp_string18.length);
        if (_jsp_FormatNumberTag_0 == null) {
          _jsp_FormatNumberTag_0 = new org.apache.taglibs.standard.tag.rt.fmt.FormatNumberTag();
          _jsp_FormatNumberTag_0.setPageContext(pageContext);
          _jsp_FormatNumberTag_0.setParent((javax.servlet.jsp.tagext.Tag) _jsp_loop_0);
          _jsp_FormatNumberTag_0.setType("number");
          _jsp_FormatNumberTag_0.setMaxFractionDigits(2);
        }

        _jsp_FormatNumberTag_0.setValue(_caucho_expr_16.evalObject(pageContext));
        _jsp_FormatNumberTag_0.doEndTag();
        out.write(_jsp_string19, 0, _jsp_string19.length);
        if (_jsp_FormatNumberTag_0 == null) {
          _jsp_FormatNumberTag_0 = new org.apache.taglibs.standard.tag.rt.fmt.FormatNumberTag();
          _jsp_FormatNumberTag_0.setPageContext(pageContext);
          _jsp_FormatNumberTag_0.setParent((javax.servlet.jsp.tagext.Tag) _jsp_loop_0);
          _jsp_FormatNumberTag_0.setType("number");
          _jsp_FormatNumberTag_0.setMaxFractionDigits(2);
        }

        _jsp_FormatNumberTag_0.setValue(_caucho_expr_17.evalObject(pageContext));
        _jsp_FormatNumberTag_0.doEndTag();
        out.write(_jsp_string20, 0, _jsp_string20.length);
        if (_jsp_FormatNumberTag_0 == null) {
          _jsp_FormatNumberTag_0 = new org.apache.taglibs.standard.tag.rt.fmt.FormatNumberTag();
          _jsp_FormatNumberTag_0.setPageContext(pageContext);
          _jsp_FormatNumberTag_0.setParent((javax.servlet.jsp.tagext.Tag) _jsp_loop_0);
          _jsp_FormatNumberTag_0.setType("number");
          _jsp_FormatNumberTag_0.setMaxFractionDigits(2);
        }

        _jsp_FormatNumberTag_0.setValue(_caucho_expr_18.evalObject(pageContext));
        _jsp_FormatNumberTag_0.doEndTag();
        out.write(_jsp_string21, 0, _jsp_string21.length);
        if (_jsp_FormatNumberTag_0 == null) {
          _jsp_FormatNumberTag_0 = new org.apache.taglibs.standard.tag.rt.fmt.FormatNumberTag();
          _jsp_FormatNumberTag_0.setPageContext(pageContext);
          _jsp_FormatNumberTag_0.setParent((javax.servlet.jsp.tagext.Tag) _jsp_loop_0);
          _jsp_FormatNumberTag_0.setType("number");
          _jsp_FormatNumberTag_0.setMaxFractionDigits(2);
        }

        _jsp_FormatNumberTag_0.setValue(_caucho_expr_19.evalObject(pageContext));
        _jsp_FormatNumberTag_0.doEndTag();
        out.write(_jsp_string22, 0, _jsp_string22.length);
        if (_jsp_FormatNumberTag_0 == null) {
          _jsp_FormatNumberTag_0 = new org.apache.taglibs.standard.tag.rt.fmt.FormatNumberTag();
          _jsp_FormatNumberTag_0.setPageContext(pageContext);
          _jsp_FormatNumberTag_0.setParent((javax.servlet.jsp.tagext.Tag) _jsp_loop_0);
          _jsp_FormatNumberTag_0.setType("number");
          _jsp_FormatNumberTag_0.setMaxFractionDigits(2);
        }

        _jsp_FormatNumberTag_0.setValue(_caucho_expr_20.evalObject(pageContext));
        _jsp_FormatNumberTag_0.doEndTag();
        out.write(_jsp_string23, 0, _jsp_string23.length);
        if (_jsp_FormatNumberTag_0 == null) {
          _jsp_FormatNumberTag_0 = new org.apache.taglibs.standard.tag.rt.fmt.FormatNumberTag();
          _jsp_FormatNumberTag_0.setPageContext(pageContext);
          _jsp_FormatNumberTag_0.setParent((javax.servlet.jsp.tagext.Tag) _jsp_loop_0);
          _jsp_FormatNumberTag_0.setType("number");
          _jsp_FormatNumberTag_0.setMaxFractionDigits(2);
        }

        _jsp_FormatNumberTag_0.setValue(_caucho_expr_21.evalObject(pageContext));
        _jsp_FormatNumberTag_0.doEndTag();
        out.write(_jsp_string24, 0, _jsp_string24.length);
        if (_jsp_FormatNumberTag_0 == null) {
          _jsp_FormatNumberTag_0 = new org.apache.taglibs.standard.tag.rt.fmt.FormatNumberTag();
          _jsp_FormatNumberTag_0.setPageContext(pageContext);
          _jsp_FormatNumberTag_0.setParent((javax.servlet.jsp.tagext.Tag) _jsp_loop_0);
          _jsp_FormatNumberTag_0.setType("number");
          _jsp_FormatNumberTag_0.setMaxFractionDigits(2);
        }

        _jsp_FormatNumberTag_0.setValue(_caucho_expr_22.evalObject(pageContext));
        _jsp_FormatNumberTag_0.doEndTag();
        out.write(_jsp_string25, 0, _jsp_string25.length);
        if (_jsp_FormatNumberTag_0 == null) {
          _jsp_FormatNumberTag_0 = new org.apache.taglibs.standard.tag.rt.fmt.FormatNumberTag();
          _jsp_FormatNumberTag_0.setPageContext(pageContext);
          _jsp_FormatNumberTag_0.setParent((javax.servlet.jsp.tagext.Tag) _jsp_loop_0);
          _jsp_FormatNumberTag_0.setType("number");
          _jsp_FormatNumberTag_0.setMaxFractionDigits(2);
        }

        _jsp_FormatNumberTag_0.setValue(_caucho_expr_23.evalObject(pageContext));
        _jsp_FormatNumberTag_0.doEndTag();
        out.write(_jsp_string26, 0, _jsp_string26.length);
        if (_jsp_FormatNumberTag_0 == null) {
          _jsp_FormatNumberTag_0 = new org.apache.taglibs.standard.tag.rt.fmt.FormatNumberTag();
          _jsp_FormatNumberTag_0.setPageContext(pageContext);
          _jsp_FormatNumberTag_0.setParent((javax.servlet.jsp.tagext.Tag) _jsp_loop_0);
          _jsp_FormatNumberTag_0.setType("number");
          _jsp_FormatNumberTag_0.setMaxFractionDigits(2);
        }

        _jsp_FormatNumberTag_0.setValue(_caucho_expr_24.evalObject(pageContext));
        _jsp_FormatNumberTag_0.doEndTag();
        out.write(_jsp_string27, 0, _jsp_string27.length);
      }
      pageContext.pageSetOrRemove("one", _jsp_oldVar_1);
      out.write(_jsp_string28, 0, _jsp_string28.length);
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
    depend = new com.caucho.vfs.Depend(appDir.lookup("texas/log/log_summary.jsp"), 5344591231905915983L, false);
    com.caucho.jsp.JavaPage.addDepend(_caucho_depends, depend);
    depend = new com.caucho.vfs.Depend(appDir.lookup("WEB-INF/tlds/fmt.tld"), 7849922606946648492L, false);
    com.caucho.jsp.JavaPage.addDepend(_caucho_depends, depend);
    com.caucho.jsp.JavaPage.addDepend(_caucho_depends, new com.caucho.make.ClassDependency(org.apache.taglibs.standard.tag.rt.fmt.FormatNumberTag.class, 2490015424821522243L));
  }
  private final static com.caucho.el.Expr _caucho_expr_0 =
    new com.caucho.el.PathExpr(new com.caucho.el.ArrayExpr(new com.caucho.el.IdExpr("params"), new com.caucho.el.StringLiteral("type")), "params.type");
  private final static com.caucho.el.Expr _caucho_expr_1 =
    new com.caucho.el.IdExpr("min_date");
  private final static com.caucho.el.Expr _caucho_expr_2 =
    new com.caucho.el.IdExpr("max_date");
  private final static com.caucho.el.Expr _caucho_expr_3 =
    new com.caucho.el.IdExpr("start_date");
  private final static com.caucho.el.Expr _caucho_expr_4 =
    new com.caucho.el.IdExpr("end_date");
  private final static com.caucho.el.Expr _caucho_expr_5 =
    new com.caucho.el.IdExpr("sum_daily");
  private final static com.caucho.el.Expr _caucho_expr_6 =
    new com.caucho.el.PathExpr(new com.caucho.el.ArrayExpr(new com.caucho.el.IdExpr("user"), new com.caucho.el.StringLiteral("id")), "user.id");
  private final static com.caucho.el.Expr _caucho_expr_7 =
    new com.caucho.el.PathExpr(new com.caucho.el.ArrayExpr(new com.caucho.el.IdExpr("one"), new com.caucho.el.StringLiteral("date")), "one.date");
  private final static com.caucho.el.Expr _caucho_expr_8 =
    new com.caucho.el.ArrayExpr(new com.caucho.el.IdExpr("one"), new com.caucho.el.StringLiteral("1"));
  private final static com.caucho.el.Expr _caucho_expr_9 =
    new com.caucho.el.ArrayExpr(new com.caucho.el.IdExpr("one"), new com.caucho.el.StringLiteral("2"));
  private final static com.caucho.el.Expr _caucho_expr_10 =
    new com.caucho.el.BinaryExpr(1, new com.caucho.el.ArrayExpr(new com.caucho.el.IdExpr("one"), new com.caucho.el.StringLiteral("1")), new com.caucho.el.ArrayExpr(new com.caucho.el.IdExpr("one"), new com.caucho.el.StringLiteral("2")));
  private final static com.caucho.el.Expr _caucho_expr_11 =
    new com.caucho.el.BinaryExpr(4, new com.caucho.el.ArrayExpr(new com.caucho.el.IdExpr("one"), new com.caucho.el.StringLiteral("1")), new com.caucho.el.ArrayExpr(new com.caucho.el.IdExpr("one"), new com.caucho.el.StringLiteral("104")));
  private final static com.caucho.el.Expr _caucho_expr_12 =
    new com.caucho.el.BinaryExpr(4, new com.caucho.el.ArrayExpr(new com.caucho.el.IdExpr("one"), new com.caucho.el.StringLiteral("3")), new com.caucho.el.LongLiteral(100L));
  private final static com.caucho.el.Expr _caucho_expr_13 =
    new com.caucho.el.BinaryExpr(3, new com.caucho.el.ArrayExpr(new com.caucho.el.IdExpr("one"), new com.caucho.el.StringLiteral("3")), new com.caucho.el.DoubleLiteral(0.7));
  private final static com.caucho.el.Expr _caucho_expr_14 =
    new com.caucho.el.BinaryExpr(4, new com.caucho.el.BinaryExpr(3, new com.caucho.el.ArrayExpr(new com.caucho.el.IdExpr("one"), new com.caucho.el.StringLiteral("3")), new com.caucho.el.DoubleLiteral(0.7)), new com.caucho.el.ArrayExpr(new com.caucho.el.IdExpr("one"), new com.caucho.el.StringLiteral("1")));
  private final static com.caucho.el.Expr _caucho_expr_15 =
    new com.caucho.el.BinaryExpr(4, new com.caucho.el.ArrayExpr(new com.caucho.el.IdExpr("one"), new com.caucho.el.StringLiteral("4")), new com.caucho.el.ArrayExpr(new com.caucho.el.IdExpr("one"), new com.caucho.el.StringLiteral("1")));
  private final static com.caucho.el.Expr _caucho_expr_16 =
    new com.caucho.el.BinaryExpr(4, new com.caucho.el.ArrayExpr(new com.caucho.el.IdExpr("one"), new com.caucho.el.StringLiteral("4")), new com.caucho.el.ArrayExpr(new com.caucho.el.IdExpr("one"), new com.caucho.el.StringLiteral("5")));
  private final static com.caucho.el.Expr _caucho_expr_17 =
    new com.caucho.el.BinaryExpr(4, new com.caucho.el.ArrayExpr(new com.caucho.el.IdExpr("one"), new com.caucho.el.StringLiteral("6")), new com.caucho.el.LongLiteral(100L));
  private final static com.caucho.el.Expr _caucho_expr_18 =
    new com.caucho.el.BinaryExpr(4, new com.caucho.el.ArrayExpr(new com.caucho.el.IdExpr("one"), new com.caucho.el.StringLiteral("7")), new com.caucho.el.LongLiteral(100L));
  private final static com.caucho.el.Expr _caucho_expr_19 =
    new com.caucho.el.BinaryExpr(4, new com.caucho.el.BinaryExpr(3, new com.caucho.el.ArrayExpr(new com.caucho.el.IdExpr("one"), new com.caucho.el.StringLiteral("3")), new com.caucho.el.DoubleLiteral(0.7)), new com.caucho.el.ArrayExpr(new com.caucho.el.IdExpr("one"), new com.caucho.el.StringLiteral("4")));
  private final static com.caucho.el.Expr _caucho_expr_20 =
    new com.caucho.el.BinaryExpr(4, new com.caucho.el.BinaryExpr(3, new com.caucho.el.BinaryExpr(3, new com.caucho.el.ArrayExpr(new com.caucho.el.IdExpr("one"), new com.caucho.el.StringLiteral("8")), new com.caucho.el.DoubleLiteral(0.1)), new com.caucho.el.LongLiteral(10L)), new com.caucho.el.LongLiteral(32L));
  private final static com.caucho.el.Expr _caucho_expr_21 =
    new com.caucho.el.BinaryExpr(4, new com.caucho.el.BinaryExpr(3, new com.caucho.el.BinaryExpr(3, new com.caucho.el.ArrayExpr(new com.caucho.el.IdExpr("one"), new com.caucho.el.StringLiteral("9")), new com.caucho.el.DoubleLiteral(0.1)), new com.caucho.el.LongLiteral(10L)), new com.caucho.el.ArrayExpr(new com.caucho.el.IdExpr("one"), new com.caucho.el.StringLiteral("8")));
  private final static com.caucho.el.Expr _caucho_expr_22 =
    new com.caucho.el.BinaryExpr(4, new com.caucho.el.BinaryExpr(3, new com.caucho.el.BinaryExpr(3, new com.caucho.el.ArrayExpr(new com.caucho.el.IdExpr("one"), new com.caucho.el.StringLiteral("10")), new com.caucho.el.DoubleLiteral(0.1)), new com.caucho.el.LongLiteral(10L)), new com.caucho.el.ArrayExpr(new com.caucho.el.IdExpr("one"), new com.caucho.el.StringLiteral("8")));
  private final static com.caucho.el.Expr _caucho_expr_23 =
    new com.caucho.el.BinaryExpr(4, new com.caucho.el.BinaryExpr(3, new com.caucho.el.BinaryExpr(3, new com.caucho.el.ArrayExpr(new com.caucho.el.IdExpr("one"), new com.caucho.el.StringLiteral("12")), new com.caucho.el.DoubleLiteral(0.1)), new com.caucho.el.LongLiteral(10L)), new com.caucho.el.ArrayExpr(new com.caucho.el.IdExpr("one"), new com.caucho.el.StringLiteral("8")));
  private final static com.caucho.el.Expr _caucho_expr_24 =
    new com.caucho.el.BinaryExpr(4, new com.caucho.el.BinaryExpr(3, new com.caucho.el.BinaryExpr(3, new com.caucho.el.ArrayExpr(new com.caucho.el.IdExpr("one"), new com.caucho.el.StringLiteral("11")), new com.caucho.el.DoubleLiteral(0.1)), new com.caucho.el.LongLiteral(10L)), new com.caucho.el.ArrayExpr(new com.caucho.el.IdExpr("one"), new com.caucho.el.StringLiteral("12")));

  private final static char []_jsp_string1;
  private final static char []_jsp_string20;
  private final static char []_jsp_string22;
  private final static char []_jsp_string10;
  private final static char []_jsp_string28;
  private final static char []_jsp_string16;
  private final static char []_jsp_string3;
  private final static char []_jsp_string11;
  private final static char []_jsp_string15;
  private final static char []_jsp_string21;
  private final static char []_jsp_string12;
  private final static char []_jsp_string2;
  private final static char []_jsp_string26;
  private final static char []_jsp_string7;
  private final static char []_jsp_string23;
  private final static char []_jsp_string18;
  private final static char []_jsp_string25;
  private final static char []_jsp_string19;
  private final static char []_jsp_string5;
  private final static char []_jsp_string8;
  private final static char []_jsp_string6;
  private final static char []_jsp_string4;
  private final static char []_jsp_string17;
  private final static char []_jsp_string0;
  private final static char []_jsp_string9;
  private final static char []_jsp_string13;
  private final static char []_jsp_string24;
  private final static char []_jsp_string27;
  private final static char []_jsp_string14;
  static {
    _jsp_string1 = "\" method=\"post\" id=\"searchForm\">\r\n			<input type=\"hidden\"  id=\"d4310\" value=\"".toCharArray();
    _jsp_string20 = "</td><!--\u65b0\u7528\u6237\u9996\u4ed8  -->\r\n					<td>".toCharArray();
    _jsp_string22 = "</td><!-- ARPPU  -->\r\n					<td>\u5e73\u5747spin: ".toCharArray();
    _jsp_string10 = "</td>\r\n					<td>".toCharArray();
    _jsp_string28 = "\r\n					\r\n			</tbody>\r\n		</table>		\r\n	</div>\r\n	</div>	\r\n</div>\r\n\r\n<div id=\"ami_newwindow\" style=\"display: none;\">\r\n\r\n</div>\r\n<script type=\"text/javascript\" src=\"/js/Clockpicker/bootstrap-clockpicker.min.js\"></script>\r\n    <!-- Data Tables -->\r\n    <script charset=\"utf-8\" src=\"js/dataTables/jquery.dataTables.js\"></script>\r\n    <script charset=\"utf-8\" src=\"js/dataTables/dataTables.bootstrap.js\"></script>\r\n    <script charset=\"utf-8\" src=\"js/dataTables/dataTables.responsive.js\"></script>\r\n    <script charset=\"utf-8\" src=\"js/dataTables/dataTables.tableTools.min.js\"></script>\r\n\r\n<script charset=\"utf-8\" >\r\n$(document).ready(function() {\r\n    $('.dataTables-example').DataTable({\r\n        \"dom\": 'lTfigpt',\r\n        \"order\":[[0, \"desc\"]],\r\n        buttons: [\r\n                  'copy','excel'\r\n              ],\r\n        \"tableTools\": {\r\n            \"sSwfPath\": \"js/dataTables/swf/copy_csv_xls_pdf.swf\"\r\n        }\r\n    });\r\n});\r\n\r\nfunction go(page)\r\n{\r\n	$('.ami_Mask').show();\r\n	var params = $('#searchForm').formSerialize();\r\n	\r\n	var url = $('#searchForm').attr('action') +\"&currentPage=\"+page;\r\n	$.post(url,params,function (data){\r\n	\r\n	$('#PAGECONTENT').html(data);\r\n	\r\n	$('.ami_Mask').hide();\r\n	\r\n	});\r\n}\r\n</script>".toCharArray();
    _jsp_string16 = "</td><!-- net revenue -->\r\n					<td>".toCharArray();
    _jsp_string3 = "\" class=\"Wdate\"/>\r\n			\u5f00\u59cb\u65f6\u95f4:	<input type=\"text\"  id=\"d4311\" name=\"start\" value=\"".toCharArray();
    _jsp_string11 = "</td><!--\u6bcf\u65e5\u767b\u5f55\u7684\u7528\u6237(DAU)  -->\r\n					<td>".toCharArray();
    _jsp_string15 = "</td><!--income   -->\r\n					<td>".toCharArray();
    _jsp_string21 = "</td><!-- \u9996\u4ed8\u8d39  -->\r\n					<td>".toCharArray();
    _jsp_string12 = "</td><!--\u6bcf\u65e5\u65b0\u6ce8\u518c\u7528\u6237(DNU)  -->\r\n					<td>".toCharArray();
    _jsp_string2 = "\" class=\"Wdate\"/>\r\n			<input type=\"hidden\"  id=\"d4313\" value=\"".toCharArray();
    _jsp_string26 = " \r\n					\u8d54\u7387\uff1a".toCharArray();
    _jsp_string7 = "\r\n				\r\n				<tr id=\"tr_".toCharArray();
    _jsp_string23 = " \r\n					 \u8d62\u7387\uff1a".toCharArray();
    _jsp_string18 = "</td><!-- Pay Rate -->\r\n					<td>".toCharArray();
    _jsp_string25 = "\r\n					\u5e73\u5747\u6d88\u8017\uff1a".toCharArray();
    _jsp_string19 = "</td><!--\u4ed8\u8d39\u7528\u6237/\u8ba2\u5355   -->\r\n					<td>".toCharArray();
    _jsp_string5 = "\" class=\"Wdate\"  onClick=\"WdatePicker({minDate:'#F{$dp.$D(\\'d4311\\')}',maxDate:'#F{$dp.$D(\\'d4313\\')}',realDateFmt:'yyyy_MM_dd',dateFmt:'yyyy_MM_dd'})\" style=\"width:100px\" requiry=\"true\"/> \r\n\r\n				<button type=\"button\"  style=\"margin-left:10px\" class=\"btn btn-mini btn-info\" onClick=\"go(1)\">\u68c0\u7d22</button>	\r\n			</form>\r\n			\r\n	</div>\r\n<hr/>\r\n<div class=\"row-fluid\">\r\n	<div class=\"table-responsive\" style=\"overflow-x:scroll\">\r\n		<div class=\"ami_table_header\"><span class=\"ami_table_header_title\">".toCharArray();
    _jsp_string8 = "\" class=\"gradeA\">\r\n					<!--<td style=\"position:absolute;border-right:1px solid #DDD;background-color:#f1f1f1\">".toCharArray();
    _jsp_string6 = " \u6bcf\u65e5\u603b\u89c8</span>\r\n\r\n		</div>\r\n		<table id=\"table_report\" class=\"table table-striped table-bordered table-hover dataTables-example\">\r\n			<thead>			\r\n				<tr>	\r\n					<!--<th style=\"position:absolute;background-color:#f1f1f1;height:136px;width:62px;border-right:1px solid #DDD\">\u65e5\u671f</th>\r\n					--><th>\u65e5\u671f</th>\r\n					   <th>DAU</th>\r\n					   <th>DNU</th>\r\n					   <th>\u6bcf\u65e5\u767b\u5f55\u7684\u8001\u7528\u6237</th>\r\n					   <th>\u6d3b\u8dc3\u7387</th>\r\n					   <th>income</th>\r\n					   <th>net revenue</th>\r\n					   <th>ARPDAU</th>\r\n					   <th>Pay Rate</th>\r\n					   <th>\u4ed8\u8d39\u7528\u6237/\u8ba2\u5355</th>\r\n					   <th>\u65b0\u7528\u6237\u9996\u4ed8</th>\r\n					   <th>\u9996\u4ed8\u8d39</th>\r\n					   <th>ARPPU</th>\r\n					   <th>Spin</th>\r\n\r\n				</tr>				\r\n			</thead>\r\n			<tbody style=\"position:relative\">		\r\n				\r\n			 ".toCharArray();
    _jsp_string4 = "\" class=\"Wdate\" onClick=\"WdatePicker({minDate:'#F{$dp.$D(\\'d4310\\')}',maxDate:'#F{$dp.$D(\\'d4312\\')}',dateFmt:'yyyy_MM_dd'})\" style=\"width:100px\" requiry=\"true\"/>\r\n			\u622a\u6b62\u65f6\u95f4\uff1a<input type=\"text\"  id=\"d4312\" name=\"end\" value=\"".toCharArray();
    _jsp_string17 = "</td><!-- ARPDAU  -->\r\n					<td>".toCharArray();
    _jsp_string0 = "\r\n\r\n\r\n\r\n\r\n\r\n<link rel=\"stylesheet\" type=\"text/css\" href=\"/js/Clockpicker/bootstrap-clockpicker.min.css\">\r\n    <!-- Data Tables -->\r\n    <link href=\"css/dataTables/dataTables.bootstrap.css\" rel=\"stylesheet\">\r\n    <link href=\"css/dataTables/dataTables.responsive.css\" rel=\"stylesheet\">\r\n    <link href=\"css/dataTables/dataTables.tableTools.min.css\" rel=\"stylesheet\">\r\n<style>\r\n.dataTables_wrapper {\r\n    padding-bottom: 30px;\r\n}	\r\n.dataTables_length {\r\n    float: left;\r\n}\r\n\r\n\r\n</style>\r\n\r\n<div id=\"ami_main\">\r\n	<div class=\"row-fluid\" id=\"div1\">	\r\n			<form class=\"form-inline\" action=\"log.do?method=summary&type=".toCharArray();
    _jsp_string9 = "</td>-->\r\n					<td>".toCharArray();
    _jsp_string13 = "</td><!--\u6bcf\u65e5\u767b\u5f55\u7684\u8001\u7528\u6237  -->\r\n					<td>".toCharArray();
    _jsp_string24 = " \r\n					\u5927\u8d62\u7387\uff1a".toCharArray();
    _jsp_string27 = " </td><!-- Spin  -->\r\n					\r\n					\r\n				</tr>\r\n			  ".toCharArray();
    _jsp_string14 = "</td><!-- \u6d3b\u8dc3\u7387  -->\r\n					<td>".toCharArray();
  }
}