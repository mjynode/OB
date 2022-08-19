<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!--引入core标签库-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link type="text/css" rel="stylesheet" href="style/blue/pageCommon.css"/>
</head>
<body>

<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13"
                 src="style/images/title_arrow.gif"/>
            用户管理
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<div id="MainArea">
    <table cellspacing="0" cellpadding="0" class="TableStyle">
        <!-- 表头-->
        <thead>
        <tr align="center" valign="middle" id="TableTitle">
            <td width="50">登录名</td>
            <td width="50">密码</td>
            <td width="50">姓名</td>
            <td width="50">邮箱</td>
            <td width="50">性别</td>
            <td width="50">电话</td>
            <td width="50">部门名</td>
            <td width="200">岗位</td>
            <td width="100">相关操作</td>
        </tr>
        </thead>
        <!--显示数据列表-->
        <tbody id="TableData" class="dataContainer" datakey="userList">
        <c:forEach items="${userList}" var="user">
            <tr class="TableDetail1 template" align="center">
                <td>${user.loginname}</td>
                <td>${user.password}</td>
                <td>${user.realname}</td>
                <td>${user.email}</td>
                <td>${user.sex}</td>
                <td>${user.phone}</td>
                <td>${user.dname}</td>
                <td>${user.pnames}</td>
                <td>
                    <a href="user?methodName=deleteUser&uid=${user.id}">删除</a>
                    <a href="user?methodName=toUpdateUser&uid=${user.id}">修改</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <div id="TableTail">
        <div id="TableTail_inside">
            <a href="user?methodName=toSaveUser">
                <img src="style/images/createNew.png"/>
            </a>
        </div>
    </div>

</div>

</body>
</html>
