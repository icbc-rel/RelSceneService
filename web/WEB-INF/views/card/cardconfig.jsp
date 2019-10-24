<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html
PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http：//www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <title>首页</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <script src="../../js/jquery-3.2.1.min.js"></script>
    <SCRIPT src="../../js/swiper.min.js"></SCRIPT>
    <script src="../../css/layui/layui.js"></script>
    <link rel="stylesheet" href="../../js/card/common.js" type="text/css"/>
    <link rel="stylesheet" href="../../css/card/common.css" type="text/css"/>

    <link rel="stylesheet" href="../../css/layui/css/layui.css" media="all">
    <LINK href="../../css/swiper.min.css" rel="stylesheet">
    <link href="../../css/card/index.css" rel="stylesheet" type="text/css"/>

</head>

<body>


<div class="header">
    <div class="header_a">
        <div class="header_admin"></div>
        //TODO::登录用户名
    </div>
</div>

<div class="header">
    <div class="header_a">
        <div class="header_mp"></div>
    </div>
</div>

<div class="content">
    <div class="vbm">
        <div class="klkl">
            <div class="main">
                <div id="circle-panel">
                    <div class="layui-card">
                        <!-- <div class="layui-mapper.card-header" ></div> -->
                        <div class="layui-card-body">
                            <div class="layui-tab layui-tab-brief" lay-fiter="docDemoTabBrief">
                                <ul class="layui-tab-title">
                                    <li class="layui-this">贺卡设置</li>
                                    <!--  <li>自定义样式</li>  -->
                                </ul>
                                <div class="layui-tab-content" style="display: flex;">
                                    <div class="card-type" id="0">
                                        <div class="choice-box" style="background:rgb(0,150,136);"></div>
                                        生日贺卡
                                    </div>
                                    <div class="card-type" id="1">
                                        <div class="choice-box"></div>
                                        节日贺卡
                                    </div>
                                </div>
                                <%--  <div class="layui-form-item choose-festival">
                                      <LABEL class="layui-form-label">节日选择<SPAN class="red">*</SPAN></LABEL>
                                      <div class="layui-input-inline" id="festval-id"><select name="festival-choice">
                                          <option value="1">妇女节</option>
                                          <option value="2">端午节</option>
                                          <option value="3">元宵节</option>
                                          <option value="4">中秋节</option>
                                      </select></div>
                                  </div>--%>

                            </div>
                        </div>
                    </div>
                </div>
                <div id="example-card">
                    <div class="layui-card">
                        <div class="layui-card-header">界面预览</div>
                        <div class="layui-card-body example" id="bg-pre"
                             style=' background-image: url("../../image/card/bg_birth_1.jpg")'>
                            <div class="container">

                                <img alt="" src="../../image/card/touxiang.jpg">
                                <div class="staff-name">Hi,姓名：</div>

                                <div class="wish-text">
                                    祝福语祝福语祝福语祝福语祝福语祝福语祝福语祝福语祝福语祝福语祝福语祝福语祝福语祝福语
                                </div>
                                <div class="byname-text">
                                    落款姓名
                                </div>
                                <div class="send-Time">
                                    <!-- 2019年10月20日 -->
                                </div>
                            </div>

                            <!-- <div class="btnpic" style="left: 20px;"> 活动规则 </div>
                         <div class="btnpic" style="right: 20px;"> 中奖记录 </div>  -->
                        </div>
                    </div>
                </div>
                <div id="coupon-panel">
                    <div class="layui-card">
                        <div class="layui-card-header">贺卡配置</div>
                        <div class="layui-tab-content">
                            <div class="layui-tab-item layui-show">
                                <div class="swiper-container">
                                    <div class="swiper-wrapper">
                                        <%--可以将这边的图片替换--%>
                                        <div class="swiper-slide pzpic"><IMG alt=""
                                                                             src="../../image/card/bg_birth_1.jpg">
                                        </div>
                                        <div class="swiper-slide pzpic"><IMG alt=""
                                                                             src="../../image/card/bg_birth_1.jpg">
                                        </div>
                                        <div class="swiper-slide pzpic"><IMG alt=""
                                                                             src="../../image/card/bg_birth_1.jpg">
                                        </div>
                                        <div class="swiper-slide pzpic"><IMG alt=""
                                                                             src="../../image/card/bg_birth_1.jpg">
                                        </div>
                                        <div class="swiper-slide pzpic"><IMG alt=""
                                                                             src="../../image/card/bg_birth_1.jpg">
                                        </div>
                                        <div class="swiper-slide pzpic"><IMG alt=""
                                                                             src="../../image/card/bg_birth_1.jpg">
                                        </div>
                                        <div class="swiper-slide pzpic"><IMG alt=""
                                                                             src="../../image/card/bg_birth_1.jpg">
                                        </div>
                                        <div class="swiper-slide pzpic"><IMG alt=""
                                                                             src="../../image/card/bg_birth_1.jpg">
                                        </div>
                                        <div class="swiper-slide pzpic"><IMG alt=""
                                                                             src="../../image/card/bg_birth_1.jpg">
                                        </div>
                                        <!-- <div class="swiper-slide pzpic"><IMG alt="" src="home_files/ex_lty9.png"> -->
                                        <!-- </div> -->
                                    </div><!-- Add Pagination -->
                                    <div class="swiper-pagination"></div>
                                </div>
                            </div>
                        </div>
                        <div class="text-config">
                            <div class="layui-card-header">文案配置</div>
                            <div class="layui-card-body para-card">
                                <form class="layui-form" id="text" lay-filter="main-filter">
                                    <div class="layui-form-item"><LABEL class="layui-form-label">贺卡名称<SPAN
                                            class="red">*</SPAN></LABEL>
                                        <div class="layui-input-inline"><INPUT name="name"
                                                                               class="layui-input  mouseover"
                                                                               type="text" maxlength="10" value="${card.name}">
                                        </div>
                                    </div>
                                    <div class="layui-form-item"><LABEL class="layui-form-label">祝福内容<SPAN
                                            class="red">*</SPAN></LABEL>
                                        <div class="layui-input-inline"><TEXTAREA name="wish"
                                                                                  class="layui-textarea  mouseover"
                                                                                  style="width: 500px; min-height: 60px;"
                                                                                  maxlength="50" >${card.wish}</TEXTAREA>
                                        </div>
                                    </div>
                                    <div class="layui-form-item"><LABEL class="layui-form-label">落款内容<SPAN
                                            class="red">*</SPAN></LABEL>
                                        <div class="layui-input-inline"><INPUT name="writeName"
                                                                               class="layui-input  mouseover"
                                                                               type="text" maxlength="10" value="${card.writeName}">
                                        </div>
                                    </div>
                                    <div class="layui-form-item"><LABEL class="layui-form-label">发送时间<SPAN
                                            class="red">*</SPAN></LABEL>
                                        <div class="layui-input-inline sendTime" id="birth-time"><select
                                                name="birthdayTime" id="birth-send-time">
                                            <option value="0">当日0点</option>
                                            <option value="8">当日8点</option>
                                            <option value="12">当日12点</option>
                                            <option value="20">当日20点</option>
                                        </select>
                                        </div>

                                        <div class="layui-input-inline sendTime" id="festival-date">
                                            <select
                                                    name="festivalTime" id="birth-send-time">
                                                <option value="0">当日0点</option>
                                                <option value="8">当日8点</option>
                                                <option value="12">当日12点</option>
                                                <option value="20">当日20点</option>
                                            </select>
                                        </div>
                                        <button class="layui-btn" id="btnpreview" type="button" lay-filter="btnpreview"
                                                lay-submit="">预览贺卡
                                        </button>
                                    </div>

                                </form>
                            </div>
                            <div class="layui-card-header">发送人员信息</div>
                            <div class="layui-card-body para-card">

                                <div class="layui-form layui-col-md6 x-so">
                                    <button class="layui-btn" id="addStaff" lay-filter="addStaff" lay-submit="">
                                        <I class="layui-icon"></I>添加
                                    </button>
                                    <button class="layui-btn" lay-filter="downloadStaff" lay-submit="">下载模板</button>
                                    <button class="layui-btn" id="uploadStaff" type="button" name="multipartFile">
                                        <I class="layui-icon"></I>导入信息
                                    </button>
                                </div>

                                <table class="layui-table" id="staffTable" lay-filter="staffTable"></table>

                                <div class="btngroup">
                                    <button class="layui-btn" id="btnsave" type="butIton" lay-filter="btnsave"
                                            lay-submit="">应用配置
                                    </button>
                                    <button class="layui-btn" id="btnreturn" onclick="returnLast()"
                                            type="button">返回
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>

    <div class="footer"></div>
    <script src="../../js/card/index.js"></script>
    <script src="../../js/card/common.js"></script>
    <script type="text/html" id="xuhao">
        <!-- {{d.LAY_table_INDEX+1}} -->
    </script>

    <script>

        // 背景图翻页
        var swiper = new Swiper('.swiper-container', {
            direction: 'horizontal',
            slidesPerView: 'auto',
            freeMode: true,
            pagination: '.swiper-pagination',
            mousewheel: true,
        });


        // 员工表格
        layui.use('table', function () {
            var url='${pageContext.request.contextPath}/getSessionTask';
            <c:if test="${not empty card.id}">
            {
                url='${pageContext.request.contextPath}/taskinfo/'+${card.id};
            }
            </c:if>
            var table = layui.table;
            taskTable = table.render({
                elem: '#staffTable'
                //TODO::员工信息接口
                , url: url //获取员工信息
                , method: "get"
                , page: true //开启分页
                , limit: 5
                , page: {
                    limits: [5, 10, 15]
                    , first: false //不显示首页
                    , last: false //不显示尾页
                }
                , cols: [[ //表头
                    {field: 'cusName', width: '200', title: '姓名', align: 'center'}
                    , {field: 'phone', width: '200', title: '手机号码', align: 'center'}
                    , {field: 'birthday', width: '200', title: '生日', align: 'center', templet: formatBirthdayTime}
                ]]
            })
        })


        // 选择贺卡类型生日或者节日
        var type = 0;
        $('.mapper.card-type').click(e => {
            if(e.target.className == 'mapper.card-type'
        )
        {
            type = e.target.id;
        }
        else
        if (e.target.className == 'choice-box') {
            type = e.target.parentElement.id;
        }
        // 0为生日，1为节日,

        if (type == 0) {
            $('.choose-festival').hide();
            $('#festival-date').hide();
            $('#birth-time').show();
            console.log($('#0>.choice-box'))
            $('#0>.choice-box').css('background', 'rgb(0,150,136)')
            $('#1>.choice-box').css('background', '#ffffff')
        } else if (type == 1) {
            type = 1;
            $('.choose-festival').show();
            $('#birth-time').hide();
            $('#festival-date').show();
            $('#0>.choice-box').css('background', '#ffffff')
            $('#1>.choice-box').css('background', 'rgb(0,150,136)')
        }
        })
        // 选择节日，获取不同背景图
        //TODO::背景图，命名bg_fes_fetivalid_,例如：bg_fes_1_1,默认option为1,预览图为每个类目的第一张图
        var festival = 1
        $('#festval-id').click(e => {

            festival = $('select[name="festival-choice"]').val()
            console.log(festival)

        })

        // 点击更换预览背景图,获取背景图
        var bdImg = $('#bg-pre').css('background-image')
        var imgUrl = bdImg.split('("')[1].split('")')[0];
        $('.swiper-container').click(e => {

            if(e.target.nodeName == 'IMG'
        )
        {
            imgUrl = $(e.target).attr('src')
            console.log(imgUrl)
            $('#bg-pre').css('background-image', "url(" + imgUrl + ")")
            console.log($('#bg-pre').css('background-image'))
        }

        })
        // 日期选择器,只能选当天以后的日期
        var today = new Date().format('yyyy-MM-dd hh:mm:ss');

        /*layui.use('laydate', function () {
            var laydate = layui.laydate;

            //执行一个laydate实例
            laydate.render({
                elem: '#festival-date' //指定元素
                , type: 'datetime'
                , min: today
            });

            //执行一个laydate实例
            laydate.render({
                elem: '#birth-day' //指定元素
                , type: 'date'
            });
        });*/

        // 预览界面时间戳
        var year = new Date().getFullYear()
        var month = new Date().getMonth() + 1
        var date = new Date().getDate()
        var sendPre = year +"年" +month+"月" +date+"日";
        $('.send-Time').html(year +"年" +month+"月" +date+"日")

        // 点击‘预览贺卡’进行预览
        $('#btnpreview').click(e => {

            $('.wish-text'
        ).html($('textarea[name="wish"]').val());
        $('.byname-text').html($('input[name="writeName"]').val())

        })


        /*关闭弹出框口*/
        function layerClose() {
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        }

        /*父页向子页传递的值*/
        var layerParams = {};

        /**
         * 设置参数
         */
        function setLayerParams(type, data) {
            layerParams = {
                type: type,
                data: data
            }
        }

        /**
         * 获取父页面设置的参数
         * @returns {{}}
         */
        function getLayerParams() {
            return layerParams
        }


        //返回上一步
        function returnLast() {
            window.history.go(-1);
        }


        // 输入框提示信息
        $(".mouseover").mouseover(function () {
            var obj = $(this);

            var note = "";
            if (obj[0].name == "name") {
                note = "最多可输入10个字符";
            } else if (obj[0].name == "wish") {
                note = "最多可输入50个字符";
            } else if (obj[0].name == "writeName") {
                note = "最多可输入10个字符";
            }
            layer.tips(note, this, {tips: [2, '#009688']});
        });


        //检查提交的元素
        function check(data) {
            var content = "";
            if (data[0].value == "") {
                content = "请填写贺卡名称!";
            } else if (checklength(data[0].value) > 20) {
                content = "贺卡名称最多可输入10个字符";
            } else if (data[1].value == "") {
                content = "请填写祝福语";
            } else if (checklength(data[1].value) > 50) {
                content = "祝福语最多可输入50个字符";
            } else if (data[2].value == "") {
                content = "请填写落款内容";
            } else if (checklength(data[2].value) > 10) {
                content = "落款内容最多可输入10个字符";
            } else if (data[3].value == "") {
                content = "请选择发送时间!";
            }
            if (content.length == 0) {
                return true;
            }
            layer.open({
                type: 0,
                title: '提示',
                content: content
            });
            return false;
        }

        // var f = document.querySelector('#text');
        //     var configArr = new FormData(f);
        // // 上传员工信息和添加时需要检查配置信息是否已经输入完成
        $('#addStaff').click(e => {
            // 获取文案配置内容
            var configArr = $('#text').serializeArray();
        console.log(configArr)
        //    检查配置内容
        var flag = check(configArr);
        // 填写完成后才可添加员工
        if (flag == true) {
            layer.open({
                type: 2,
                area: ['700px', '450px'],
                fixed: false, //不固定
                maxmin: true,
                content: './staff/addStaff.html'
            });

        }
        })
        ;

        // 上传员工名单


        // console.log(configArr)
        //    检查配置内容
        // 填写完成后才可添加员工
        layui.use('upload', function () {
            var upload = layui.upload;
            upload.render({
                elem: "#uploadStaff",
                url: '${pageContext.request.contextPath}/taskinfo/import',//上传接口地址
                accept: 'file',
                exts: 'xls|xlsx',
                method: "POST",
                done: function (data) {
                    if(data.code==0)
                    {
                        data.message="上传成功"
                    }
                    if(data.code==1)
                    {
                        data.message="上传文件为空"
                    }
                    if(data.code==2)
                    {
                        data.message="请严格按照模板格式上传"
                    }
                    layer.open({
                        title: '提示',
                        type: 0,
                        content: data.message
                    });

                    taskTable.reload({});
                }
            });
        })


        // TODO::

        //监听表单提交 搜索
        layui.use('form', function () {
            var form = layui.form;
            form.on('submit(searchStaff)', function (data) {
                //data.field.type = common.articleType.general;
                data.field.activityuid = localIcbc.activityuid;
                table.reload('staffTableId', {
                    url: './staff/list',
                    where: data.field,
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                });
                return false;
            });


            // TODO::下载模板
            //监听表单提交 下载模板
            form.on('submit(downloadStaff)', function (data) {
                window.location.href = '${pageContext.request.contextPath}/taskinfo/template?type='+type;
                return false;
            });
        })


        // 提交配置和人员信息
        $('#btnsave').click(e => {
            // 获取文案配置内容
            var configArr = $('#text').serializeArray();

        var flag = check(configArr);
        // 填写完成后检查是否添加员工
        if (flag == true) {
            var staffInfo = layui.table.cache.staffTable
            if (staffInfo == null || staffInfo == '' || staffInfo == undefined) {
                layer.open({
                    type: 0,
                    content: '请上传或添加员工信息' //这里content是一个普通的String
                });
                // layer.msg('请上传或添加员工信息');
            } else {
                layer.confirm('确定应用当前配置？', {
                    btn: ['确定', '取消'] //按钮
                }, function () {
                    // 数组类型转换
                    var obj = {};
                    $.each(configArr, function (i, v) {
                        obj[v.name] = v.value;
                    })
                    // data.push({ img: imgUrl })
                    //贺卡配置 TODO::背景图及文案配置，缺少员工名单
                    var sendTime;
                    if(type==0)
                    {
                        sendTime=obj.birthdayTime
                    }
                    if(type==1)
                    {
                        sendTime=obj.festivalTime
                    }
                    var param = {
                        'name': obj.name,
                        'wish': obj.wish,
                        'writeName': obj.writeName,
                        'sendTime': sendTime,
                        'bg': imgUrl,
                        'type': type,
                    }
                    var url='${pageContext.request.contextPath}/mapper.card';
                    <c:if test="${not empty card.id}">
                    url='${pageContext.request.contextPath}/mapper.card/'+${card.id}
                        </c:if>
                        $.ajax({
                            type: "POST",
                            url: url,//接口
                            data: param,
                            dataType: 'json',
                            success: function (data) {
                                $(location).attr("href", "${pageContext.request.contextPath}/task");
                            },
                            error: function () {
                                layer.msg('应用配置失败');
                                layer.closeAll();
                            }

                        });

                }, function () {
                    layer.closeAll();
                });

            }

        }
        })

        function formatBirthdayTime(value) {

            if (value.birthday == undefined) {
                return "--";
            }
            return new Date(value.birthday).format('yyyy-MM-dd');
        }
    </script>
</body>

</html>
