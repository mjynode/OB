<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!--引入core标签库-->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link type="text/css" rel="stylesheet" href="style/blue/pageCommon.css"/>
	<link rel="stylesheet" type="text/css" href="zTree/css/zTreeStyle/zTreeStyle.css">
    <script src="zTree/js/jquery-1.4.4.min.js"></script>
    <script src="zTree/js/jquery.ztree.all.js"></script>

    <script>

        var pid;

        //展示带有复选框的菜单树
        function openMenuTree(postId) {
            pid = postId;

            //显示授权菜单树的div
            $("#pmt").show();

            var setting = {
                data: {
                    simpleData: {
                        enable: true
                    }
                },
                //显示每个菜单项前面的复选框
                check: {
                    enable: true
                }
            };

            $.get("menu?methodName=queryMenus", function(data){
                $.fn.zTree.init($("#postMenuTree"), setting, data);

                /*
                回显之前授权的菜单项
                 */
                //发出ajax异步的post请求,查询该岗位之前授权的所有菜单项的id
                $.post("postMenu", "methodName=queryPostMenu&pid="+pid,function(data) {
                    //拿到ztree树对象
                    var treeObj = $.fn.zTree.getZTreeObj("postMenuTree");
                    //遍历json数组,拿到每个json的mid--菜单id
                    $.each(data, function(index,jsonObj) {
                        //每个菜单id
                        var mid = jsonObj.mid;
                        //根据id属性从ztree树中获取菜单项 -- 返回值是获取的菜单项的json对象
                        var menuNode = treeObj.getNodeByParam("id", mid);
                        //将指定的菜单项选中
                        treeObj.checkNode(menuNode, true);
                    });
                },"json");

            },"json");
        }

        //完成授权的方法
        function savePostMenu() {
            //获取ztree树对象 -- 把整个树作为一个对象拿到
            var treeObj = $.fn.zTree.getZTreeObj("postMenuTree");
            //拿到所有被选中的菜单节点 -- 返回值是json数组
            var nodes = treeObj.getCheckedNodes(true);
            var ids = pid+",";
            //遍历json数组拿到每个json
            $.each(nodes, function(index, jsonObj) {
                //取每个json的id -- 菜单id
                ids+=(jsonObj.id+",");
            });
            //console.log(ids); //-- 5,1,2,21,22,23,24,25, -- 第一个5是岗位id,后面都是菜单id
            //去除最后的,
            ids = ids.substring(0, ids.length-1);
            //console.log(ids); //-- 5,1,2,21,22,23,24,25

            //发送ajax异步的post请求,保存岗位id和它分配的所有菜单id --- 授权
            $.post("postMenu", "methodName=savePostMenu&ids="+ids, function(data) {
                alert("授权 "+data);
                $("#pmt").hide();
            });

        }
    </script>
</head>
<body>
    
	<!--显示授权菜单树的div-->
    <div style="width:250px;background-color:#E5F2FA;
             position:absolute;left:42%;display:none" id="pmt">
        <div id="postMenuTree" class="ztree"></div>
        <div id="InputDetailBar">
            <input type="image" src="style/images/save.png" onclick="savePostMenu()"/>
            <a href="javascript:void(0)" onclick="$('#pmt').hide()">
                <img src="style/images/goBack.png"/>
            </a>
        </div>
    </div>

    <div id="Title_bar">
        <div id="Title_bar_Head">
            <div id="Title_Head"></div>
            <div id="Title"><!--页面标题-->
                <img border="0" width="13" height="13"
                     src="style/images/title_arrow.gif"/>
                岗位管理
            </div>
            <div id="Title_End"></div>
        </div>
    </div>

    <div id="MainArea">
        <table cellspacing="0" cellpadding="0" class="TableStyle">

            <!-- 表头-->
            <thead>
            <tr align="CENTER" valign="MIDDLE" id="TableTitle">
                <td width="200px">岗位名称</td>
                <td width="300px">岗位说明</td>
                <td>相关操作</td>
            </tr>
            </thead>

            <!--显示数据列表-->
            <tbody id="TableData" class="dataContainer" datakey="roleList">
                <c:forEach items="${postList}" var="post">
                    <tr class="TableDetail1 template" align="center">
                        <td>${post.pname}</td>
                        <td>${post.pdesc}</td>
                        <td>
                            <a href="post?methodName=deletePost&id=${post.pid}">删除</a>
                            <a href="post?methodName=toUpdate&id=${post.pid}">修改</a>
                            <a href="javascript:void(0)" onclick="openMenuTree(${post.pid})">授权</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <!-- 其他功能超链接 -->
        <div id="TableTail">
            <div id="TableTail_inside">
                <a href="page?methodName=toPage&pageName=savePost">
                   <img src="style/images/createNew.png"/>
                </a>
            </div>
        </div>
    </div>
</body>
</html>
