
<div class="navbar">
  <div class="navbar-inner">
    <div class="container-fluid"><img alt="" src="img/logo.png"  style="height: 59px;">  <b style="color:#FFFFFF;font-size:30px;">无双吹牛 管理系统</b>
      <!--/.brand-->
      <ul class="nav ace-nav pull-right">
        
        
       
        <li class="light-blue" > <a data-toggle="dropdown" href="#" class="dropdown-toggle"> <span class="user-info">${_loginInfo.username }</span> <i class="icon-caret-down"></i> </a>
          <ul class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-closer">
            <li> <a href="###" onclick="nav('/admin/admin_modpwd.jsp?1=1',title)" > <i class="icon-user"></i> 修改密码 </a> </li>
            <li class="divider"></li>
            <li> <a href="login.do?method=loginOut"> <i class="icon-off"></i> Logout </a> </li>
          </ul>
        </li>
        
        
      </ul>
      <!--/.ace-nav-->
    </div>
    <!--/.container-fluid-->
  </div>
  <!--/.navbar-inner-->
</div>
