<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="style/style/style.css"/>
    <link rel="stylesheet" type="text/css" href="style/blue/pageCommon.css"/>
    <link rel="stylesheet" type="text/css" href="zTree/css/zTreeStyle/zTreeStyle.css"/>
    <script src="zTree/js/jquery-1.4.4.min.js"></script>
    <script src="zTree/js/jquery.ztree.all.js"></script>

    <script>
        //加载事件
        $(function() {

            var setting = {
                data: {
                    simpleData: {
                        enable: true
                    }
                },
                //给树节点设置事件
                callback:{
                    //给每个树节点设置右击事件,事件触发执行openAddDelete()
                    onRightClick: openAddDelete,
                    onClick: openAddList
                }
            };

            $.post("type", "methodName=queryAllType", function(data){
                $.fn.zTree.init($("#knowsTypeTree"), setting, data);
            }, "json");
        });

        //参数三treeNode是当前触发右键事件的树节点对应的json
        function openAddDelete(event, treeId, treeNode) {

            //显示添加删除节点的div
            $("#rMenu").show();
            //拿到鼠标当前坐标
            var x = event.clientX;//横坐标
            var y = event.clientY;//纵坐标
            //设置添加删除节点的div的作为为鼠标当前坐标
            $("#rMenu").css("left",x);
            $("#rMenu").css("top",y);

            //--------------------------------------------------------------------------------------

            /*
              添加分类
             */
            //取消之前给添加节点li设置的点击事件
            $("#radd").unbind("click");
            //给添加节点li设置点击事件
            $("#radd").click(function() {
                $("#rMenu").hide();
                //弹出对话框并录入信息 -- 分类名称
                var tname = prompt("分类名称");
                if(tname!=null&&tname!=""){
                    //拿到当前右击的分类的id,作为新增的分类的父id
                    var pid = treeNode.id;
                    //发出ajax异步的post请求,完成添加分类
                    $.post("type", "methodName=addType&tname="+tname+"&pid="+pid,function(data){
                        //拿ztree树对象
                        var ztree = $.fn.zTree.getZTreeObj("knowsTypeTree");
                        //给指定的上级分类添加子分类
                        ztree.addNodes(treeNode, data, true);
                    }, "json");
                }
            });

            //-------------------------------------------------------------------------------------

            //取消删除节点li之前设置的所有的点击事件
            $("#rdel").unbind("click");
            //给删除节点li设置点击事件
            $("#rdel").click(function() {
                $("#rMenu").hide();
                //拿当前右击的分类节点的id
                var id = treeNode.id;
                //判断当前分类是否是上级分类 -- 将当前分类的id作为pid查询是否有数据
                $.post("type", "methodName=queryType&pid="+id,function(data){
                    if(data=="1"){
                        alert("上级分类，不能直接删除");
                    }else if(data=="0"){
                        //发出ajax异步post请求,根据id删除分类
                        $.post("type", "methodName=deleteType&id="+id, function(data){
                            if(data=="0"){
                                alert("删除失败");
                            }else if(data=="1"){
                                //拿ztree树对象
                                var ztree = $.fn.zTree.getZTreeObj("knowsTypeTree");
                                //从ztree树中删除当前分类节点
                                ztree.removeNode(treeNode, false);
                            }
                        });
                    }
                });


            });

        }

        var tid;

        //打开添加内容div或展示内容div
        //参数三treeNode是当前触发点击事件的树节点对应的json
        function openAddList(event, treeId, treeNode) {
            //判断是分类子节点的执行
            if(treeNode.children==undefined){
                //当前分类节点的id --- typeid
                tid = treeNode.id;
                //发出ajax异步post请求,根据typeid查其版本内容
                $.post("content", "methodName=isHasContent&typeid="+tid, function(data){
                    if(data.length>0){//有内容 -- data是查到分类的版本内容的json数组
                        $("#divList").show();
                        $("#divForm").hide();
                        var tab = $("#contentList");//展示版本内容的表格
                        tab.empty();//将展示版本内容的表格之前内容全部清空
                        $.each(data, function(index, obj) {
                            var tr = "<tr>" +
                                       "<td width='160' height='26' align='center' valign='middle' " +
                                            "bgcolor='#FFFFFF' style='border-bottom:1px solid #f3f8fd;'>" +
                                          "<a href=\"javascript:editContent('"+obj.title+"','"+obj.content+"')\">"+obj.versionid+"</a>" +
                                       "</td>" +
                                       "<td width='105' height='26' align='center' valign='middle' " +
                                           "bgcolor='#FFFFFF' style='border-bottom:1px solid #f3f8fd;'>" +
                                          obj.title +
                                       "</td>" +
                                       "<td width='215' align='center' valign='middle' bgcolor='#FFFFFF' " +
                                           "style='border-bottom:1px solid #f3f8fd;'>" +
                                          obj.lasttime +
                                       "</td>" +
                                       "<td width='215' align='center' valign='middle' bgcolor='#FFFFFF' " +
                                            "style='border-bottom:1px solid #f3f8fd;'>" +
                                          "<a href=\"javascript:deleteContent("+obj.id+")\" alt="+obj.id+">删除</a>" +
                                       "</td>" +
                                     "</tr>";
                            tab.append(tr);
                        });
                    }else{//没内容
                        $("#divForm").show();
                        $("#divList").hide();
                    }
                }, "json");
            }
        }

        //添加版本内容或修改版本内容 --- 修改其实也是添加
        function addOrUpdate() {
            //拿到录入的标题
            var title = $("[name=title]").val();
            //拿到录入的内容
            var content = $("[name=content]").val();
            //发送ajax异步post请求完成添加版本内容
            $.post("content", "methodName=addContent&title="+title+"&content="+content+"&typeid="+tid,function(data) {
                if(data=="0"){
                    alert("添加失败");
                }else if(data=="1"){
                    $("[name=title]").val("");//清空标题
                    $("[name=content]").val("");//清空内容
                    $("#divForm").hide();//隐藏添加版本内容的表格
                }
            });
        }

        //回显被修改的版本内容的信息
        function editContent(title, content) {
            $("[name=title]").val(title);
            $("[name=content]").val(content);
            $("#divForm").show();
            $("#divList").hide();
        }

        //删除版本内容的
        function deleteContent(id) {
            $.post("content", "methodName=deleteContent&id="+id, function(data){
                if(data=="0"){
                    alert("删除失败");
                }else if(data=="1"){
                    $("[alt="+id+"]").parent().parent().remove();
                }
            });
        }
    </script>
</head>

<body style="background-color:#F7FFFF;">

    <div id="Title_bar">
        <div id="Title_bar_Head">
            <div id="Title_Head"></div>
            <div id="Title"><!--页面标题-->
                <img border="0" width="13" height="13"
                     src="style/images/title_arrow.gif"/>
                知识管理
            </div>
            <div id="Title_End"></div>
        </div>
    </div>

    <!--当前路径-->
    <div id="QueryArea">
        <div id="FilePath">
            <div class="PathTitle">当前路径:</div>
        </div>
    </div>

    <!--目录列表-->
    <div id="dirListArea" style="width:260px;float:left;">
        <div style="margin-left:15px;">
            <!--显示知识内容分类树-->
            <div id="knowsTypeTree" class="ztree"></div>
        </div>
    </div>

    <!--目录内容添加-->
    <div id="divForm" style="margin-left:3px;width:700px;float:left;display:none" border="3" alt="">
        <table width="700" border="0" cellspacing="0" cellpadding="0"
               style="border:1px solid #91C0E3;">
            <tr>
                <td width="94" height="40" align="center" valign="middle"
                    bgcolor="#F0F7FD" style="color:#069;">
                    <strong>标题</strong>
                </td>
                <td width="540" bgcolor="#F0F7FD">
                    <input style="background-color:#FFFFFF;border:1px solid #91C0E3;
                           color:#004779;width:250px;" type="text" name="title"/>
                </td>
            </tr>
            <tr>
                <td height="53" align="center" valign="middle"
                    bgcolor="#F0F7FD" style="color:#069;">
                    <strong>内容</strong>
                </td>
                <td bgcolor="#F0F7FD">
                    <textarea style="background-color:#FFFFFF;border:1px solid #91C0E3;
                              color:#004779;width:250px;height:auto;" name="content">
                    </textarea>
                </td>
            </tr>
            <tr>
                <td colspan="2" bgcolor="#F0F7FD" height="50">
                    <table width="199" border="0" align="center" cellpadding="0" cellspacing="0">
                       <tr>
                           <td width="100">
                               <input style="background:#D3EDFC;border:1px solid #91C0E3;
                               cursor:pointer;" type="button" value="checkin" onclick="addOrUpdate()"/>
                           </td>
                           <td width="99">
                               <input style="background:#D3EDFC;border:1px solid #91C0E3;
                               cursor:pointer;" type="button" value="checkout"/>
                           </td>
                       </tr>
                </table></td>
            </tr>
        </table>
    </div>

    <!--目录内容列表显示-->
    <div id="divList" style="margin-left:3px;width:700px;float:left;display:none;" border="2">
        <table width="700" height="57" border="0" cellpadding="0" cellspacing="0" style="font-size:12px;">
            <tr>
                <td height="30">
                    <table width="700" height="26" border="0" cellpadding="0" cellspacing="0"
                           style="background:url(style/images/411.jpg) repeat-x;">
                    <tr>
                        <td align="center" valign="middle" style="border:1px solid #CBE3ED;
                            border-right:none;">版本号</td>
                        <td align="center" valign="middle" style="border:1px solid #CBE3ED;
                            border-right:none;">标题</td>
                        <td align="center" valign="middle" style="border:1px solid #CBE3ED;">
                            修改时间</td>
                        <td align="center" valign="middle" style="border:1px solid #CBE3ED;
                            border-left:none;">相关操作</td>
                    </tr>
                </table>
                </td>
            </tr>
            <tr>
                <td>
                    <table width="700" border="0" cellspacing="0" cellpadding="0"
                           id="contentList">
					   <!--
					      <tr>
							  <td width='160' height='26' align='center' valign='middle'
								  bgcolor='#FFFFFF' style='border-bottom:1px solid #f3f8fd;'>
							  </td>" 
							  <td width='105' height='26' align='center' valign='middle'
								  bgcolor='#FFFFFF' style='border-bottom:1px solid #f3f8fd;'>
							  </td>
							  <td width='215' align='center' valign='middle' bgcolor='#FFFFFF' 
								  style='border-bottom:1px solid #f3f8fd;'>
							  </td>
							  <td width='215' align='center' valign='middle' bgcolor='#FFFFFF' 
								  style='border-bottom:1px solid #f3f8fd;'>
								  删除
							  </td>
						   </tr>
					   -->
					</table>
                </td>
            </tr>
        </table>
    </div>

    <div style="position:absolute;display:none;overflow:hidden;"
         class="addnodes" id="rMenu">
        <ul>
            <li style="background:url(style/images/jia.jpg) 10px 5px no-repeat;
                border-bottom:1px solid #8ab2e6;" id="radd">
                增加节点
            </li>
            <li style="background:url(style/images/jian.jpg) 10px 9px no-repeat;"
                id="rdel">
                删除节点
            </li>
        </ul>
    </div>

</body>
</html>
