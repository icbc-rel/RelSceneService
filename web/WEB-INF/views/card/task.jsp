<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
    <title>贺卡列表</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <script src="../../js/jquery-3.2.1.min.js"></script>
    <SCRIPT src="../../js/swiper.min.js"></SCRIPT>
    <script src="../../css/layui/layui.js"></script>
    <link rel="stylesheet" href="../../js/common.js" type="text/css"/>
    <link rel="stylesheet" href="../../css/common.css" type="text/css"/>

    <link rel="stylesheet" href="../../css/layui/css/layui.css" media="all">
    <LINK href="../../css/swiper.min.css" rel="stylesheet">
    <link href="../../css/card/index.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="header">
    <div class="header_a">
        <div class="header_admin"></div>
    </div>
</div>

<div class="header">
    <div class="header_a">
        <div class="header_mp"></div>
    </div>
</div>
<DIV class="content2">
    <DIV class="vbm">
        <DIV class="klkl">
            <DIV class="main">
                <DIV class="layui-card">
                    <DIV class="layui-card-header">贺卡列表</DIV>
                    <DIV class="layui-card-body">
                        <%--<FORM class="layui-form" id="test" lay-filter="main-filter">
                            <DIV class="tool">
                                <DIV class="layui-form-item"><LABEL class="layui-form-label">贺卡名称</LABEL>
                                    <DIV class="layui-input-inline"><INPUT name="activityName" class="layui-input"
                                                                           id="activityName"></DIV><BUTTON class="layui-btn" type="button"
                                                                                                           lay-filter="btnQuery" lay-submit="">查询</BUTTON>
                                </DIV>
                            </DIV>
                        </FORM>--%>
                        <TABLE class="layui-table" id="demo" lay-filter="myfilter"></TABLE>
                        <SCRIPT id="barDemo" type="text/html">
                            <a class="layui-btn layui-btn-xs" lay-event="edit">查看配置</a>
                            <a class="layui-btn layui-btn-xs" lay-event="analysis">统计分析</a>
                            <a class="layui-btn layui-btn-xs" lay-event="delete">删除</a>
                        </SCRIPT>
                    </DIV>
                </DIV>
            </DIV>
        </DIV>
    </DIV>
</DIV>


<div class="footer"></div>
<script src="../../js/card/index.js"></script>
<script src="../../js/common.js"></script>
<script type="text/html" id="xuhao">
    {{d.LAY_TABLE_INDEX+1}}
</script>
<script>
    //表格
    layui.use('table', function () {
        var table = layui.table;
        var form = layui.form;
        //第一个实例
        mytable = table.render({
            id: 'demoReload'
            , elem: '#demo'
            , url: '${ pageContext.request.contextPath }/mapper.card' //获取场景数据
            , method: "get"
            , height: 800
            , cols: [[ //表头
                { field: 'id', width: '100', title: '序号', templet: '#xuhao', rowspan: 2 }
                , { field: 'name', width: '120', title: '贺卡名称', align: 'center' }
                , { field: 'type', width: '120', title: '贺卡类型', align: 'center',templet:fomatType }
                , { field: 'createTime', width: '300', title: '创建时间', align: 'center', templet: formatDatetime }
                , { fixed: 'right', width: '500', title: '操作', align: 'center', toolbar: '#barDemo' }
            ]]
        });

        //删除图片
        table.on('tool(myfilter)', function (obj) {
            var data = obj.data;
             if (obj.event == 'edit') {
                    window.location.href = '${ pageContext.request.contextPath }/updatecardconfig?id=' + data.id;

            } else if (obj.event == 'analysis') {
                    window.location.href = '${ pageContext.request.contextPath }/analysis/' + data.id;
            } else if (obj.event == "delete") {
                layer.confirm('确认删除活动?', { icon: 3, title: '提示' }, function (index) {
                    $.ajax({
                        type: "delete",
                        url: "${ pageContext.request.contextPath }/mapper.card/delete/" + data.id,
                        dataType: "json",
                        contentType: "application/json;charset=utf-8",
                        async: false,
                        complete: function (data) {
                            if (data.responseJSON.code == 0) {
                                mytable.reload();
                            } else {
                                alert("删除出错，请重新删除");
                            }
                        }
                    });
                    layer.close(index);
                });
            }
        });

        function formatDatetime(value) {
            return new Date(value.createTime).format('yyyy-MM-dd hh:mm:ss');
        }

        function fomatType(value) {
            if(value.type==0)
            {
                return "生日贺卡";
            }
            if(value.type==1)
            {
                return "节日贺卡";
            }
        }

    });

    layui.use('form', function () {
        var form = layui.form;
        form.on('submit(btnQuery)', function (data) {
            table.reload('mytable', {
                url: './staff/list',
                where: data.field,
                page: {
                    curr: 1 //重新从第 1 页开始
                }
            });
            return false;
        });
    })


</script>
</body>

</html>
