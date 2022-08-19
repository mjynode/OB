<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link type="text/css" rel="stylesheet" href="style/blue/pageCommon.css"/>
    <link rel="stylesheet" type="text/css" href="zTree/css/zTreeStyle/zTreeStyle.css">
    <script src="zTree/js/jquery-1.4.4.min.js"></script>
    <script src="zTree/js/jquery.ztree.all.js"></script>

    <script>
        function showDeptTree() {

            $("#deptTree").show();//显示id值是deptTree的div

            var setting = {
                data: {
                    simpleData: {
                        enable: true
                    }
                },
                //给每个菜单项设置事件
                callback: {
                    //给每个菜单项设置单击事件,事件触发执行zTreeOnClick()方法
                    onClick: zTreeOnClick
                }
            };

            /*
              发ajax异步的get请求,查询出所有部门,然后将服务器响应的json数组
              给ztree控件,最后用ztree控件展示出部门树
             */
            $.get("dept?methodName=queryAll",function(data){
                $.fn.zTree.init($("#deptTree"), setting, data);
            },"json");

        }

        /*
              点击每个菜单项触发点击事件执行的方法
              参数：
              1.event--当前触发的事件对象 --- 对你没用
              2.treeId --- 展示菜单树的标签的id值
              3.treeNode --- 当前点击的菜单项的json对象
             */
        function zTreeOnClick(event, treeId, treeNode) {
            //Object { id: 7, name: "总经办", pId: null}
            //console.log(treeNode);
            $("#upDname").text(treeNode.name);//将json对象的name属性作为span的文本 --- 选择的上级部门名
            $("[name=pid]").val(treeNode.id);//将json对象的id属性作为隐藏域的value值 --- 发给服务器的上级部门id
            $("#deptTree").hide();//隐藏展示部门树的div
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

            <input type="hidden" name="pid" value="">
            <input type="hidden" name="methodName" value="addDept">

            <div class="ItemBlock_Title1"></div>
            <!-- 表单内容显示 -->
            <div class="ItemBlockBorder">
                <div class="ItemBlock">
                    <table cellpadding="0" cellspacing="0" class="mainForm">
                        <tr>
                            <td>部门名称</td>
                            <td>
                                <input type="text" name="dname" class="InputStyle"/>
                            </td>
                        </tr>
                        <tr>
                            <td>上级部门</td>
                            <td>
                                <input type="button" value="选择上级部门" onclick="showDeptTree()"/>
                                <span id="upDname"></span>
                            </td>
                        </tr>
                        <tr>
                            <td>职能说明</td>
                            <td>
                                <textarea name="ddesc" class="TextareaStyle"></textarea>
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
