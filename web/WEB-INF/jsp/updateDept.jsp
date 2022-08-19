<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link type="text/css" rel="stylesheet" href="style/blue/pageCommon.css"/>
    <link rel="stylesheet" type="text/css" href="zTree/css/zTreeStyle/zTreeStyle.css">
    <script src="zTree/js/jquery-1.4.4.min.js"></script>
    <script src="zTree/js/jquery.ztree.all.js"></script>

    <script>
        function showDeptTree2() {

            $("#deptTree").show();

            var setting = {
                data: {
                    simpleData: {
                        enable: true
                    }
                },
                callback: {
                    onClick: zTreeOnClick2
                }
            };

            $.get("dept?methodName=queryAll",function(data){
                $.fn.zTree.init($("#deptTree"), setting, data);
            },"json");

        }

        function zTreeOnClick2(event, treeId, treeNode) {
            $("#pdname").text(treeNode.name);
            $("[name=pid]").val(treeNode.id);
            $("#deptTree").hide();
        }
    </script>
</head>
<body>
    <!--显示部门树的div-->
    <div style="width:160px;height:300px;background-color:#E5F2FA;
         position:absolute;left:42%;display:none"
         id="deptTree" class="ztree">
    </div>

    <!-- 标题显示 -->
    <div id="Title_bar">
        <div id="Title_bar_Head">
            <div id="Title_Head"></div>
            <div id="Title"><!--页面标题-->
                <img border="0" width="13" height="13" 
				     src="style/images/title_arrow.gif"/>
                部门信息
            </div>
            <div id="Title_End"></div>
        </div>
    </div>

    <!--显示表单内容-->
    <div id=MainArea>
        <form action="dept" method="post">
            <div class="ItemBlock_Title1"></div>

			<input type="hidden" name="id" value="${dept.id}">
			<input type="hidden" name="pid" value="${dept.pid}">
            <input type="hidden" name="methodName" value="updateDept">

            <!-- 表单内容显示 -->
            <div class="ItemBlockBorder">
                <div class="ItemBlock">
                    <table cellpadding="0" cellspacing="0" class="mainForm">
                        <tr>
                            <td>部门名称</td>
                            <td>
                                <input type="text" class="InputStyle" name="dname" value="${dept.dname}"/>
                            </td>
                        </tr>
                        <tr>
                            <td>上级部门</td>
                            <td>
                                <input type="button" value="选择上级部门" onclick="showDeptTree2()"/>
                                <span id="pdname">${dept.pdname}</span>
                            </td>
                        </tr>
                        <tr>
                            <td>职能说明</td>
                            <td>
                                <textarea name="ddesc" class="TextareaStyle">${dept.ddesc}</textarea>
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
