<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link type="text/css" rel="stylesheet" href="style/blue/statusbar.css"/>
</head>
<body style="margin:0">
    <div id="StatusBar">
        <div id="Online">
            在线人员：共<span class="OnlineUser" id="onlineUserNum">1</span>人
            <span class="OnlineView"><a href="javascript:void(0)">[查看在线名单]</a></span>
        </div>
        <div id="Info">
            <a href="#" title="XXX首页" target="_blank">XXX公司首页</a>
            |
            <a href="#" title="XXXBBS" target="_blank">XXX公司BBS</a>
        </div>
        <div id="DesktopText">
            <a href="javascript:void(0)">
                <img border="0" src="style/images/top/text.gif"/>
                便笺
            </a>
            <span id=TryoutInfo></span>
            <span id="Version">
                <a href="javascript:void(0)">
                    <img border="0" width="11" height="11" src="style/images/top/help.gif"/>
                    <img border="0" width="40" height="11" src="style/blue/images/top/version.gif"/>
                </a>
            </span>
        </div>
    </div>
</body>
