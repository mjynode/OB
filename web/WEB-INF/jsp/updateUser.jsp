<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!--引入core标签库-->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link type="text/css" rel="stylesheet" href="style/blue/pageCommon.css"/>
</head>
<body>
<!-- 标题显示 -->
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13"
                 src="style/images/title_arrow.gif"/>
            用户信息
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id=MainArea>
    <form action="user" method="post">

        <input type="hidden" name="id" value="${userMap.user.id}">
        <input type="hidden" name="methodName" value="updateUser">

        <div class="ItemBlock_Title1"><!-- 信息说明 -->
            <div class="ItemBlock_Title1">
                <img border="0" width="4" height="7"
                     src="style/blue/images/item_point.gif"/>
                用户信息
            </div>
        </div>
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                    <tr>
                        <td>登录名</td>
                        <td>
                            <input type="text" name="loginname"
                                   class="InputStyle" readonly value="${userMap.user.loginname}"/>
                        </td>
                    </tr>
                    <tr>
                        <td>密码</td>
                        <td>
                            <input type="text" name="password" class="InputStyle"
                                   value="${userMap.user.password}"/>
                        </td>
                    </tr>
                    <tr>
                        <td>姓名</td>
                        <td>
                            <input type="text" name="realname" class="InputStyle"
                                   value="${userMap.user.realname}"/>
                        </td>
                    </tr>
                    <tr>
                        <td>E-mail</td>
                        <td>
                            <input type="text" name="email" class="InputStyle"
                                   value="${userMap.user.email}"/>
                        </td>
                    </tr>
                    <tr>
                        <td>性别</td>
                        <td>
                            <input type="radio" name="sex" value="男" <c:if test="${userMap.user.sex=='男'}">checked</c:if> />男
                            <input type="radio" name="sex" value="女" <c:if test="${userMap.user.sex=='女'}">checked</c:if> />女
                        </td>
                    </tr>
                    <tr>
                        <td>联系电话</td>
                        <td>
                            <input type="text" name="phone" class="InputStyle"
                                   value="${userMap.user.phone}"/>
                        </td>
                    </tr>
                    <tr>
                        <td>部门</td>
                        <td>
                            <select name="did" class="SelectStyle">
                                <c:forEach items="${depts}" var="dept">
                                    <option value="${dept.id}"
                                            <c:if test="${dept.id==userMap.user.did}">selected</c:if>
                                    >
                                            ${dept.dname}
                                    </option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                </table>
            </div>
        </div>

        <div class="ItemBlock_Title1"><!-- 信息说明 -->
            <div class="ItemBlock_Title1">
                <img border="0" width="4" height="7"
                     src="style/blue/images/item_point.gif"/>
                岗位设置
            </div>
        </div>
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                    <tr>
                        <td width="100">岗位</td>
                        <td>
                            <select name="pids" multiple="true" size="10" class="SelectStyle">
                                <c:forEach items="${posts}" var="post">
                                    <option value="${post.pid}"
                                            <c:forEach items="${userMap.userPosts}" var="userPost">
                                                <c:if test="${userPost.pid==post.pid}">selected</c:if>
                                            </c:forEach>
                                    >
                                            ${post.pname}
                                    </option>
                                </c:forEach>
                            </select>
                            按住Ctrl键可以多选或取消选择
                        </td>
                    </tr>
                </table>
            </div>
        </div>

        <!-- 表单操作 -->
        <div id="InputDetailBar">
            <input type="image" src="style/images/save.png"/>
            <a href="javascript:history.go(-1);">
                <img src="style/images/goBack.png"/>
            </a>
        </div>
    </form>
</div>

</body>
</html>
