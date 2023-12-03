<%--
  Created by IntelliJ IDEA.
  User: YU
  Date: 2023/9/23
  Time: 8:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>欢迎登录后台管理系统</title>
    <link href="/css/style.css" rel="stylesheet" type="text/css" />
    <script language="JavaScript" src="/js/jquery.js"></script>
    <script src="/js/cloud.js" type="text/javascript"></script>

    <script language="javascript">
        $(function(){
            $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
            $(window).resize(function(){
                $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
            })
        });
    </script>

</head>

<body style="background-color:#1c77ac; background-image:url(images/light.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">



<div id="mainBody">
    <div id="cloud1" class="cloud"></div>
    <div id="cloud2" class="cloud"></div>
</div>


<div class="logintop">
    <span>欢迎登录后台管理界面平台</span>
    <ul>
        <li><a href="#">回首页</a></li>
        <li><a href="#">帮助</a></li>
        <li><a href="#">关于</a></li>
    </ul>
</div>

<div class="loginbody">

    <span class="systemlogo"></span>

    <div class="loginbox">
        <form action="/loginServlet" method="post">
            <ul>
                <li>
                    <input name="userName" type="text" class="loginuser" value="admin"/>
                </li>
                <li>
                    <input name="password" type="password" class="loginpwd" value="123" />
                </li>
                <li>
                    <input name="" type="submit" class="loginbtn" value="登录"   />
                    <label>
                        <input name="" type="checkbox" value="" checked="checked" />记住密码
                    </label>
                    <label>
                        <c:if test="${empty sessionScope.msg}">
                            <a href="#">忘记密码</a>

                        </c:if>
                        <c:if test="${not  empty sessionScope.msg}">
                            <span style="color: red">${sessionScope.msg}</span>
                        </c:if>

                    </label>
                </li>
            </ul>
        </form>


    </div>

</div>



<div class="loginbm">版权所有  2013  <a href="http://www.uimaker.com">uimaker.com</a>  仅供学习交流，勿用于任何商业用途</div>


<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>
</body>
</html>