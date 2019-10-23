<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <title>员工信息</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script src="../../js/jquery-3.2.1.min.js"></script>
    <script src="../../css/layui/layui.js"></script>
    <link href="../../css/layui/css/layui.css" rel="stylesheet" media="all">
    <link href="../../css/common.css" rel="stylesheet" type="text/css">
    <script src="../../js/jquery.serializejson.min.js"></script>
    <link id="layuicss-layer"
          href="https://cjy.icbc.com.cn/RelSceneService/css/layui/css/modules/layer/default/layer.css?v=3.1.1"
          rel="stylesheet" media="all">
</head>

<body>
<div class="x-body" style="padding: 0px 40px;">
    <form class="layui-form" id="taskForm">

        <div style="height: 50px;">
            <div id="note" style="padding: 10px 0px 10px 150px; display: block;">员工信息</div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label" for="staffName">姓名<span class="x-red">*</span></label>
            <div class="layui-input-inline">
                <input name="cusName" class="layui-input" id="staffName" type="text" maxlength="4"
                       autocomplete="off">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label" for="staffPhone">手机号码<span class="x-red">*</span></label>
            <div class="layui-input-inline">
                <input name="phone" class="layui-input" id="staffPhone" onkeyup="value=value.replace(/[^\d]/g,'')"
                       type="text" maxlength="11" autocomplete="off">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label" for="staffBirth">出生日期<span class="x-red">*</span></label>
            <div class="layui-input-inline">
                <input name="birthday" type="text" class="layui-input birthDay" id="birthDay"
                       placeholder="yyyy-MM-dd" lay-key="6">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label"></label>
            <button name="addBtn" class="layui-btn" style="width: 190px;" lay-filter="add" lay-submit="" id="addBtn"
                    type="button">保存</button>
        </div>
    </form>
</div>
<script>
    // layui.config({
    //     base: '../../js/'
    // }).use('asks/staffOpertion');

    var reg = /^[1-9]\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$/;
    var regExp = new RegExp(reg);
    var phonereg = /^[1][3,4,5,7,8,9][0-9]{9}$/;

    $('#addBtn').on('click', function () {
        var staffArr = $('form').serializeArray();
        if (staffArr[0].value == "") {
            alert("请填写员工姓名!");
        } else if (staffArr[1].value == "") {
            alert("请填写手机号码");
        } else if (!phonereg.test(staffArr[1].value)) {
            alert("请填写正确的手机号码");
        } else if (staffArr[2].value == "") {
            alert("请填写出生日期");
        } else if (!regExp.test(staffArr[2].value)) {
            alert("日期格式不正确，正确格式为：yyyy-mm-dd");
        }
        else {
            var staffInfo = {};
            $.each(staffArr, function (i, v) {
                staffInfo[v.name] = v.value;

            })
            console.log(staffInfo)
            var JSONObject = $("#taskForm").serializeJSON()
            $.ajax({
                type: "POST",
                data: JSON.stringify(JSONObject),

                url: "${pageContext.request.contextPath}/taskinfo/add",
                dataType: "json",
                contentType: "application/json;charset=utf-8",
                async: false,
                complete: function (data) {
                    if(data.responseJSON.code==0) {
                        alert("添加成功");
                        var index = parent.layer.getFrameIndex(window.name); //获取当前窗口的name
                        parent.layui.table.reload('staffTable');
                        parent.layer.close(index);		//关闭窗口
                    }
                    if(data.responseJSON.code==1)
                    {
                        alert("添加失败，请重新添加");
                    }
                }
            });
        }

    });

</script>



</body>

</html>
