<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                     岗位设置
            </div>
            <div id="Title_End"></div>
        </div>
    </div>

    <!--显示表单内容-->
    <div id="MainArea">
        <form action="post" method="post">

            <input type="hidden" name="methodName" value="updatePost">
            <input type="hidden" name="pid" value="${post.pid}">

            <div class="ItemBlock_Title1"></div>
            <!-- 表单内容显示 -->
            <div class="ItemBlockBorder">
                <div class="ItemBlock">
                    <table cellpadding="0" cellspacing="0" class="mainForm">
                        <tr>
                            <td width="100">岗位名称</td>
                            <td><input type="text" name="pname" class="InputStyle" value="${post.pname}"/></td>
                        </tr>
                        <tr>
                            <td>岗位说明</td>
                            <td><textarea name="pdesc" class="TextareaStyle">${post.pdesc}</textarea></td>
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
