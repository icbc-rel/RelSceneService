<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE HTML
PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0090)https://cjy.icbc.com.cn/RelSceneService/mp/analysis -->
<!DOCTYPE html
PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http：//www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<HTML xmlns="http://www.w3.org/1999/xhtml">

<HEAD>
    <META content="IE=11.0000" http-equiv="X-UA-Compatible">
    <TITLE>统计分析</TITLE>
    <META http-equiv="X-UA-Compatible" content="IE=edge">
    <META http-equiv="Content-Type" content="text/html; charset=utf-8">
    <SCRIPT src="../js/jquery-3.2.1.min.js"></SCRIPT>
    <script src="../js/jquery.serializejson.min.js"></script>
    <%--<SCRIPT src="../js/analysis.js"></SCRIPT>--%>
    <SCRIPT src="../js/echarts.min.js"></SCRIPT>
    <SCRIPT src="../css/layui/layui.js"></SCRIPT>
    <LINK href="../css/layui/css/layui.css" rel="stylesheet" media="all">
    <LINK href="../css/common.css" rel="stylesheet" type="text/css">
    <LINK href="../css/analysis.css" rel="stylesheet" type="text/css">

    <META name="GENERATOR" content="MSHTML 11.00.10570.1001">
</HEAD>

<BODY>
<DIV class="header">
    <DIV class="header_a">
        <DIV class="header_mp"></DIV>
        <DIV class="user"><SPAN>南昌共享雨伞</SPAN></DIV>
    </DIV>
</DIV>
<DIV class="content2">
    <DIV class="vbm">
        <DIV class="klkl">
            <DIV class="tttt"></DIV>
            <DIV class="main">
                <!-- 这里是需要填充的主体内容 -->

                <DIV id="activity-panel">
                    <DIV class="layui-card activity-detail">
                        <DIV class="layui-card-header">贺卡概览</DIV>
                        <DIV class="layui-card-body">
                            <TABLE class="layui-table">
                                <COLGROUP>
                                    <COL width="150">
                                    <COL width="230">
                                </COLGROUP>
                                <TBODY>
                                <TR>
                                    <TD>贺卡名称</TD>
                                    <TD id="cardName"></TD>
                                </TR>
                                <TR>
                                    <TD>贺卡类型</TD>
                                    <TD id="cardType"></TD>
                                </TR>
                                <TR>
                                    <TD>操作</TD>
                                    <TD><BUTTON class="layui-btn" onclick="edit()"
                                                data-type="reload">贺卡编辑</BUTTON></TD>
                                </TR>
                                </TBODY>
                            </TABLE>
                        </DIV>
                    </DIV>

                    <DIV class="layui-rows" id="cards">

                        <DIV class="layui-card" style="float:left;">
                            <DIV class="layui-card-header">已发送</DIV>
                            <DIV class="layui-card-body" id="daySend">0</DIV>
                        </DIV>

                        <DIV class="layui-card" style="float: right;;">
                            <DIV class="layui-card-header">已查看</DIV>
                            <DIV class="layui-card-body" id="dayView">0</DIV>
                        </DIV>

                    </DIV>
                </DIV>
                <DIV id="visit-panel">
                    <DIV class="layui-card">
                        <DIV class="layui-card-header">本月数据</DIV>
                        <DIV class="layui-card-body">


                            <DIV class="layui-form-item">
                                <label class="layui-form-label">日期</label>
                                <div class="layui-input-inline"> <input type="text" class="layui-input" id="date1" placeholder="yyyy-MM-dd"></div>
                                <button class="layui-btn" type="button" lay-filter="queryMenu"
                                        lay-submit id="queryData">查询</button>
                            </DIV>

                            <!-- 折线图 -->
                            <DIV id="appoint" style="width: 99%; height: 300px;"></DIV>

                        </DIV>
                    </DIV>
                </DIV>
                <form  id="searchForm">
                <DIV class="data-panel">
                    <DIV class="layui-card">
                        <DIV class="layui-card-header">已发送人员列表</DIV>
                        <DIV class="layui-card-body">
                            <FORM class="layui-form" id="test" lay-filter="main-filter">
                                <DIV class="tool">
                                    <DIV class="layui-form-item"><LABEL class="layui-form-label">发送日期</LABEL>
                                        <DIV class="layui-input-inline"><INPUT name="dateStr" class="layui-input"
                                                                               id="date" placeholder="yyyy-MM-dd"></DIV><LABEL class="layui-form-label tele">手机号</LABEL>
                                        <DIV class="layui-input-inline tele"><INPUT name="mobileNo"
                                                                                    class="layui-input" id="mobileNo"></DIV><BUTTON class="layui-btn"
                                                                                                                                    type="button" lay-filter="queryOrder" lay-submit=""  id="queryTable">查询</BUTTON>
                                    </DIV>
                                </DIV>
                            </FORM>
                            <TABLE id="sendTable" style="width: 100%;" lay-filter="order-filter"></TABLE>
                        </DIV>
                    </DIV>
                </DIV>
                </form>
            </DIV>
        </DIV>
    </DIV>
</DIV>
<DIV class="footer"></DIV>
<SCRIPT src="../js/common.js"></SCRIPT>

<SCRIPT type="text/javascript">
    var id=${id};
    var activityUid = " ";
    var icbcFlag = 0;
    // TODO::接口，获取数据
    $(document).ready(function () {
        $.ajax({
            url: "${pageContext.request.contextPath}/card/"+id,// 请求分析数据
            datatype: "json",
            async: false,
            success: function (data) {
                console.log(data)
                infos = data.data;
                $("#daySend")[0].innerText = data.sendCount;
                $("#dayView")[0].innerText = data.viewCount;
                $("#cardName")[0].innerText = data.data.name;
                if(data.data.type==0)
                {
                    $('#cardType')[0].innerText="生日贺卡"
                }
                if(data.data.type==1) {
                    $('#cardType')[0].innerText = "节日贺卡";
                }
            }
        });
    });


    var toDate = new Date().format('yyyy-MM-dd');
    //日期
    layui.use(['carousel','laydate'],function(){
        var laydate=layui.laydate;
        var carousel=layui.carousel;
        carousel.render({
            elem:'#analysis',
            width:'100%',
            indicator:'outside',
            autoplay:false
        });
        laydate.render({
            elem:'#date',
            type:'date',
            max:toDate

        });
        laydate.render({
            elem:'#date1',
            type:'date',
            max:toDate

        });
    })

    //表格
    layui.use('table', function () {
        var id=${id};
        var table = layui.table;
        var sendTable;
        //订单详情表
        sendTable = table.render({
            elem: '#sendTable'
            , id: 'sendTable'
            , url: '${pageContext.request.contextPath}/taskinfo/'+id// TODO::数据接口
            , page: true //开启分页
            , limit: 5
            ,height: 400
            , page: {
                limits: [5, 10, 15]
                , first: false //不显示首页
                , last: false //不显示尾页
            }
            , cols: [[ //表头
                 { field: 'cusName', title: '姓名', align: 'center' }
                , { field: 'phone', title: '手机号', align: 'center' }
                , { field: 'sendTime', title: '发送时间', align: 'center', templet: formatSendTime }
                , { field: 'viewTime', title: '查看时间', align: 'center', templet: formatViewTime }
            ]]
        });
    })



    // TODO::编辑,跳转至相应活动页面
    function edit() {
        window.location.href = '${ pageContext.request.contextPath }/updatecardconfig?id=' + ${id};
    }

    //关闭对话框
    function cancelDiag() {
        layer.closeAll();
    }



    function lineChart(queryData){
        var dom = document.getElementById("appoint");
        var myChart = echarts.init(dom);
        var app = {};
        option = null;
        option = {
            xAxis: {
                type: 'category',
                data: ['0点', '8点', '12点', '20点']
            },
            yAxis: {
                type: 'value'
            },
            series: [{
                // TODO::data从接口获取
                data: queryData,
                type: 'line'
            }]
        };
        ;
        if (option && typeof option === "object") {
            myChart.setOption(option, true);
        }
    }


    // 折线图
    var dom = document.getElementById("appoint");
    var myChart = echarts.init(dom);
    var app = {};
    option = null;
    option = {
        xAxis: {
            type: 'category',
            data: ['0点', '8点', '12点', '20点']
        },
        yAxis: {
            type: 'value'
        },
        series: [{
            // TODO::data从接口获取
            data: [0,0,0,0],
            type: 'line'
        }]
    };
    ;
    if (option && typeof option === "object") {
        myChart.setOption(option, true);
    }
    /*折线图查询按钮*/
    $('#queryData').click(e => {
        var queryDate=$('#date1').val();
    var param={
        "id": ${id},
        "findDate": queryDate
    }
    $.ajax({
        type: "POST",
        url: '${ pageContext.request.contextPath }/taskinfo/figure',//接口
        data: param,
        success: function (data) {
            $('#appoint').html()
            var figureData=data.data;
            lineChart( JSON.parse(figureData))
            // 渲染数据
        }
    })
    })
/*/!*电话查询按钮*!/
    $('#queryTable').click(e => {
        var param = {
            'toDate' : $('#date').val(),
            'phone' : $('#mobileNo').val()
        }
        console.log(param)
    $.ajax({
        type: "POST",
        url: '${ pageContext.request.contextPath }',//接口
        data: param,
        dataType: 'json',
        success: function (data) {
            // 刷新表格
            sendTable.reload({});
        },
    })
    })*/

    //TODO::查询指定日期所有订单
    layui.use('form', function () {
        var form = layui.form;
        form.on('submit(queryOrder)', function (data) {
            console.log(data)
            var info = data.field;
            console.log(info)
            info.id = ${id};
            console.log(info)
            sendTable.reload({
                url: './taskinfo/condition',
                method: 'post',
                where: info
            });
        });
    })


    function formatSendTime(value)
    {

        if(value.sendTime==undefined)
        {
            return "--";
        }
        return new Date(value.sendTime).format('yyyy-MM-dd hh:mm:ss');
    }
    function formatViewTime(value)
    {
        if(value.viewTime==undefined)
        {
            return "--";
        }
        return new Date(value.viewTime).format('yyyy-MM-dd hh:mm:ss');
    }
</SCRIPT>
</BODY>

</HTML>
