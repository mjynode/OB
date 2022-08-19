<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!--引入core标签库-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link type="text/css" rel="stylesheet" href="style/blue/pageCommon.css"/>
    <script src="zTree/js/jquery-1.4.4.min.js"></script>

    <script>

        var isOk = false;

        function checkName() {
            //取登录名
            var loginname = $("[name=loginname]").val();

            if(loginname==""){
                $("#msg").text("登录名不能为空");
                $("#msg").css("color", "red");
                return false;
            }

            //发出ajax异步post请求判断登录名是否已存在
            $.post("user", "methodName=checkName&loginname="+loginname, function(data){
                if(data=="1"){//存在
                    isOk = false;
                    $("#msg").text("登录名已存在");
                    $("#msg").css("color", "red");
                }else if(data=="0"){//不存在
                    isOk = true;
                    $("#msg").text("登录名可用");
                    $("#msg").css("color", "blue");
                }
            });

            return isOk;
        }

        function checkForm() {
            return checkName();
        }
    </script>
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
    <form action="user" method="post" onsubmit="return checkForm()">

        <input type="hidden" name="methodName" value="addUser">

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
                            <input type="text" name="loginname" class="InputStyle" onblur="checkName()"/>
                            <span id="msg"></span>（登录名要唯一）
                        </td>
                    </tr>
                    <tr>
                        <td>姓名</td>
                        <td>
                            <input type="text" name="realname" class="InputStyle"/>
                        </td>
                    </tr>
                    <tr>
                        <td>E-mail</td>
                        <td>
                            <input type="text" name="email" class="InputStyle"/>
                        </td>
                    </tr>
                    <tr>
                        <td>性别</td>
                        <td>
                            <input type="radio" name="sex" value="男"/>男
                            <input type="radio" name="sex" value="女"/>女
                        </td>
                    </tr>
                    <tr>
                        <td>联系电话</td>
                        <td>
                            <input type="text" name="phone" class="InputStyle"/>
                        </td>
                    </tr>
                    <tr>
                        <td>部门</td>
                        <td>
                            <select name="did" class="SelectStyle">
                                <c:forEach items="${depts}" var="dept">
                                    <option value="${dept.id}">${dept.dname}</option>
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
                                    <option value="${post.pid}">${post.pname}</option>
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
