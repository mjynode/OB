<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="http:192.168.213.130/blue/menu.css">
    <link rel="stylesheet" type="text/css" href="http:192.168.213.130/zTree/css/zTreeStyle/zTreeStyle.css">
    <script type="text/javascript" src="http:192.168.213.130/zTree/js/jquery-1.4.4.min.js"></script>
    <script type="text/javascript" src="http:192.168.213.130/zTree/js/jquery.ztree.all.js"></script>
<%--    <link rel="stylesheet" type="text/css" href="style/blue/menu.css">--%>
<%--    <link rel="stylesheet" type="text/css" href="zTree/css/zTreeStyle/zTreeStyle.css">--%>
<%--    <script type="text/javascript" src="zTree/js/jquery-1.4.4.min.js"></script>--%>
<%--    <script type="text/javascript" src="zTree/js/jquery.ztree.all.js"></script>--%>
</head>
<body style="margin: 0">
    <table border="0" width="700">
        <tr>
            <td width="340px" align="center" valign="top">
                <div class="zTreeDemoBackground">
                    <ul id="menuTree" class="ztree"></ul>
                </div>
            </td>
        </tr>
    </table>

    <script>
        var setting = {
            data: {
                simpleData: {
                    enable: true
                }
            }
        };

        $.get("menu?methodName=queryPostMenus", function(data){
            $.fn.zTree.init($("#menuTree"), setting, data);
        },"json");

    </script>
</body>
</html>
