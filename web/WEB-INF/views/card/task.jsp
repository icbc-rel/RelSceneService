<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
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
    <link href="../../css/index.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<%--<div class="header">
    <div class="header_a">
        <div class="header_admin"></div>
        <!-- <div class="user"><span><%=session.getAttribute("adminName").toString()%></span></div> -->
    </div>
</div>

<div class="header">
    <div class="header_a">
        <div class="header_mp"></div>
        <!-- <div class="user"><span><%=session.getAttribute("mpName").toString()%></span></div> -->
    </div>
</div>--%>

<DIV class="content2">
    <DIV class="vbm">
        <DIV class="klkl">
            <DIV class="main">
                <DIV class="layui-card">
                    <DIV class="layui-card-header">贺卡列表</DIV>
                    <DIV class="layui-card-body">
                        <FORM class="layui-form" id="test" lay-filter="main-filter">
                            <DIV class="tool">
                                <DIV class="layui-form-item"><LABEL class="layui-form-label">贺卡名称</LABEL>
                                    <DIV class="layui-input-inline"><INPUT name="activityName" class="layui-input"
                                                                           id="activityName"></DIV>
                                    <BUTTON class="layui-btn" type="button"
                                            lay-filter="btnQuery" lay-submit="">查询
                                    </BUTTON>
                                </DIV>
                            </DIV>
                        </FORM>

                        <TABLE class="layui-table" id="demo" lay-filter="myfilter">
                            <tr>
                                <td>贺卡名称</td>
                                <td>贺卡类型</td>
                                <td>贺卡名称</td>
                            </tr>
                            <c:forEach items="${list }" var="card">
                                <tr>
                                    <td>${card.name }</td>
                                    <c:if test="${card.type==0}">
                                        <td>生日贺卡</td>
                                    </c:if>
                                    <c:if test="${card.type==1}">
                                        <td>节日贺卡</td>
                                    </c:if>
                                    <td>${card.writeName }</td>
                                </tr>
                            </c:forEach>

                        </TABLE>

                        <SCRIPT id="barDemo" type="text/html">
                            <a class="layui-btn layui-btn-xs" lay-event="copy">拷贝链接</a>
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
<script src="../../js/index.js"></script>
<script src="../../js/common.js"></script>
</body>

</html>

