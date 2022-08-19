<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
<%--    <link rel="stylesheet" type="text/css" href="style/blue/login.css"/>--%>
<%--    <script src="zTree/js/jquery-1.4.4.min.js"></script>--%>
    <link rel="stylesheet" type="text/css" href="http:192.168.213.130/blue/login.css"/>
    <script src="http:192.168.213.130/zTree/js/jquery-1.4.4.min.js"></script>



    <script>
        function doLogin() {
            //拿用户名和密码
            var loginname = $("[name=loginname]").val();
            var password = $("[name=password]").val();

            if(loginname==""){
                $("#msg").text("用户名不能为空");
                return;
            }
            if(password==""){
                $("#msg").text("密码不能为空");
                return;
            }

            //发ajax请求做登录
            $.post("userLogin", "methodName=login&loginname="+loginname+"&password="+password, function(data) {
                if(data=="0"){//登录失败
                    $("#msg").text("用户名或密码有误");
                }else if(data=="1"){//登录成功
                    location.href = "page?methodName=toPage&pageName=main";
                }
            });
        }
    </script>
</head>

<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" class="PageBody">

    <form name="actForm">
        <div id="CenterAreaBg">
            <div id="CenterArea">

                <div id="LogoImg">
                    <img border="0" src="style/blue/images/logo.png"/>
                </div>

                <div id="LoginInfo">
                    <table border="0" cellspacing="0" cellpadding="0" width="100%">
                        <tr>
                            <td width="50px"></td>
                            <td>
                               <span style="height:50px;color: red;" id="msg">${msg}</span>
                            </td>
                        </tr>
                        <tr>
                            <td width="45" class="Subject">
                                <img border="0" src="style/blue/images/login/userId.gif"/>
                            </td>
                            <td>
                                <input size="20" class="TextField" type="text" name="loginname"/>
                            </td>
                            <td rowspan="2" style="padding-left:10px;">
                                <!--登录是个图片不是按钮-->
                                <img src="style/blue/images/login/userLogin_button.gif" onclick="doLogin()"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="Subject">
                                <img border="0" src="style/blue/images/login/password.gif"/>
                            </td>
                            <td>
                                <input size="20" class="TextField" type="password" name="password"/>
                            </td>
                        </tr>
                    </table>
                </div>

                <div id="CopyRight">
                    <a href="javascript:void(0)">&copy;2019&emsp;版权所有XXX公司</a>
                </div>

            </div>
        </div>
    </form>

</body>

</html>

