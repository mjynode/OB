<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!--core标签库-->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link type="text/css" rel="stylesheet" href="http:192.168.213.130/blue/pageCommon.css"/>
	<script src="http:192.168.213.130/zTree/js/jquery-1.4.4.min.js"></script>
<%--    <link type="text/css" rel="stylesheet" href="style/blue/pageCommon.css"/>--%>
<%--    <script src="zTree/js/jquery-1.4.4.min.js"></script>--%>
</head>
<body>

    <div id="Title_bar">
        <div id="Title_bar_Head">
            <div id="Title_Head"></div>
            <div id="Title"><!--页面标题-->
                <img border="0" width="13" height="13" src="style/images/title_arrow.gif"/>
                部门管理
            </div>
            <div id="Title_End"></div>
        </div>
    </div>

    <div id="MainArea">
        <table cellspacing="0" cellpadding="0" class="TableStyle">
            <!-- 表头-->
            <thead>
                <tr align=center valign=middle id=TableTitle>
                    <td width="150px">部门名称</td>
                    <td width="150px">上级部门名称</td>
                    <td width="200px">职能说明</td>
                    <td>相关操作</td>
                </tr>
            </thead>
            <!--显示数据列表-->
            <tbody id="TableData" class="dataContainer" datakey="departmentList">
                <c:forEach items="${deptList}" var="dept">
                    <tr class="TableDetail1 template" align="center">
                        <td>${dept.dname}</td>
                        <td>${dept.pdname}</td>
                        <td>${dept.ddesc}</td>
                        <td>
                            <!--
                              href="javascript:void(0)" == 取消超链接链接资源的默认效果,不发请求了;
                              onclick="isDelete(${dept.id})" == 给超链接搞了个点击事件,触发执行isDelete()
                              并传参当前部门id;
                            -->
                            <a href="javascript:void(0)" onclick="isDelete(${dept.id},this)">删除</a>
                            <a href="dept?methodName=toUpdateDept&id=${dept.id}">修改</a>
                        </td>
                    </tr>
                </c:forEach>

            </tbody>
        </table>

        <!-- 其他功能超链接 -->
        <div id="TableTail">
            <div id="TableTail_inside">
                <a href="page?methodName=toPage&pageName=saveDept">
                    <img src="style/images/createNew.png"/>
                </a>
            </div>
        </div>
    </div>

    <!--==========================================================================================================-->

    <script>
        function isDelete(id, a) {
            //发出ajax异步post请求,根据id查询部门是否是上级部门
            $.post("dept", "methodName=isParentDept&id="+id,function(data){
                if(data=="0"){//不删
                    alert("该部门存在下级部门，不能直接删除!");
                }else if(data=="1"){//删
                    //发的是同步请求
                    //window.location.href = "dept?methodName=deleteDept&id="+id;

                    //发出ajax异步post请求
                    $.post("dept", "methodName=deleteDept&id="+id, function(data){
                        if(data=="0"){
                            alert("删除失败");
                        }else if(data=="1"){
                            //对页面做局部刷新
                            $(a).parent().parent().remove();
                        }
                    });
                }
            });
        }
    </script>


</body>
</html>
