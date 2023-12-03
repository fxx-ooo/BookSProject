<%--
  Created by IntelliJ IDEA.
  User: YU
  Date: 2023/9/21
  Time: 21:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>无标题文档</title>
    <link href="/css/style.css" rel="stylesheet" type="text/css" />
    <link href="/css/style.css" rel="stylesheet" type="text/css" />
    <link href="/css/select.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="/js/jquery.js"></script>
    <script type="text/javascript" src="/js/jquery.idTabs.min.js"></script>
    <script type="text/javascript" src="/js/select-ui.min.js"></script>
    <script type="text/javascript" src="/editor/kindeditor.js"></script>
    <script type="text/javascript">
        $(document).ready(function(e) {
            $(".select1").uedSelect({
                width : 345
            });

            $("#departSelectId").change(function (){
               // alert($(this).val())
                //获取改变后的选中的院系的编号
                var deptId=$(this).val();
                //我们需要根据院系编号查询出班级信息

                $.get("/studentServlet?type=queryClassByDepartId&departId="+deptId,function (data){
                    console.info(data)
                })
            })
        });
    </script>
</head>

<body>

<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li><a href="#">首页</a></li>
        <li><a href="#">表单</a></li>
    </ul>
</div>

<div class="formbody">

    <div class="formtitle"><span>班级信息管理</span></div>
    <form action="/classServlet" method="post" id="myForm">
        <ul class="forminfo">
            <c:if test="${empty cls}">
                <input type="hidden" name="type" value="save">
            </c:if>

            <c:if test="${not empty cls}">
                <input type="hidden" name="type" value="update">
                <input type="hidden" name="id" value="${requestScope.cls.id}">
            </c:if>
            <li>
                <label>学号</label>
                <input name="stuNum" type="text" class="dfinput" value="${cls.className}"/>

            </li>
            <li>
                <label>姓名</label>
                <input name="stuName" type="text" class="dfinput" value="${cls.classDesc}"/>
            </li>
            <li>
                <label>手机号</label>
                <input name="phoneNum" type="text" class="dfinput" value="${cls.classDesc}"/>
            </li>
            <li>
                <label>性别</label>
                <input name="gender" type="text" class="dfinput" value="${cls.classDesc}"/>
            </li>
            <li>
                <label>地址</label>
                <input name="address" type="text" class="dfinput" value="${cls.classDesc}"/>
            </li>
            <li><label>所属院系</label>
                <div class="vocation">
                    <select class="select1" name="departId" id="departSelectId">
                        <c:forEach items="${depts}" var="dept">
                        <option value="${dept.id}">${dept.department}</option>
                        </c:forEach>
                    </select>
                </div>
            </li>

            <li><label>所属班级</label>
                <div class="vocation">
                    <select class="select1" name="classId"  >

                    </select>
                </div>
            </li>

           <%-- <li>
                <label>所属系别编号</label>
                <input name="deptId" type="text" class="dfinput" value="${requestScope.cls.deptId}"/>
                <i>多个关键字用,隔开</i>
            </li>
            <li>
                <label>所属系别名称</label>
                <input name="deptName" type="text" class="dfinput" value="${requestScope.cls.deptName}"/>
                <i>多个关键字用,隔开</i>
            </li>--%>
            <li>
                <label>&nbsp;</label>
                <input name="" type="submit" id="btn" class="btn" value="确认保存"/>
            </li>
        </ul>
    </form>
</div>
<script type="application/javascript">

</script>

<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>
</body>
</html>