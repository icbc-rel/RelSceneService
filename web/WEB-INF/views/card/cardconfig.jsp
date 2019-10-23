<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>首页</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script src="../js/jquery-3.2.1.min.js"></script>
    <SCRIPT src="../js/swiper.min.js"></SCRIPT>
    <script src="../css/layui/layui.js"></script>
    <link rel="stylesheet" href="../js/common.js" type="text/css" />
    <link rel="stylesheet" href="../css/common.css" type="text/css" />

    <link rel="stylesheet" href="../css/layui/css/layui.css" media="all">
    <LINK href="../css/swiper.min.css" rel="stylesheet">
    <link href="../css/index.css" rel="stylesheet" type="text/css" />

</head>

<body>

<%--
<div class="header">
    <div class="header_a">
        <div class="header_admin"></div>
        //TODO::登录用户名
        <!-- <div class="user"><span><%=session.getAttribute("adminName").toString()%></span></div> -->
    </div>
</div>

<div class="header">
    <div class="header_a">
        <div class="header_mp"></div>
        <!-- <div class="user"><span><%=session.getAttribute("mpName").toString()%></span></div> -->
    </div>
</div>--%>

<div class="content">
    <div class="vbm">
        <div class="klkl">
            <div class="main">
                <div id="circle-panel">
                    <div class="layui-card">
                        <!-- <div class="layui-card-header" ></div> -->
                        <div class="layui-card-body">
                            <div class="layui-tab layui-tab-brief" lay-fiter="docDemoTabBrief">
                                <ul class="layui-tab-title">
                                    <li class="layui-this">贺卡设置</li>
                                    <!--  <li>自定义样式</li>  -->
                                </ul>
                                <div class="layui-tab-content" style="display: flex;">
                                    <div class="card-type" id="0">
                                        <div class="choice-box"></div>生日贺卡
                                    </div>
                                    <div class="card-type" id="1">
                                        <div class="choice-box"></div>节日贺卡
                                    </div>
                                </div>
                                <div class="layui-form-item choose-festival">
                                    <LABEL class="layui-form-label">节日选择<SPAN class="red">*</SPAN></LABEL>
                                    <div class="layui-input-inline"><select name="festival-choice">
                                        <option value="1">妇女节</option>
                                        <option value="2">端午节</option>
                                        <option value="3">元宵节</option>
                                        <option value="4">中秋节</option>
                                    </select> </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
                <div id="example-card">
                    <div class="layui-card">
                        <div class="layui-card-header">界面预览</div>
                        <div class="layui-card-body example"
                             style=' background-image: url("../../image/wish_card/card_birth_1.jpg")'>

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
                                        <div class="swiper-slide pzpic"><IMG alt=""
                                                                             src="../../image/wish_card/card_birth_1.jpg"></div>
                                        <div class="swiper-slide pzpic"><IMG alt=""
                                                                             src="../../image/wish_card/card_birth_1.jpg"></div>
                                        <div class="swiper-slide pzpic"><IMG alt=""
                                                                             src="../../image/wish_card/card_birth_1.jpg"></div>
                                        <div class="swiper-slide pzpic"><IMG alt=""
                                                                             src="../../image/wish_card/card_birth_1.jpg"></div>
                                        <div class="swiper-slide pzpic"><IMG alt=""
                                                                             src="../../image/wish_card/card_birth_1.jpg"></div>
                                        <div class="swiper-slide pzpic"><IMG alt=""
                                                                             src="../../image/wish_card/card_birth_1.jpg"></div>
                                        <div class="swiper-slide pzpic"><IMG alt=""
                                                                             src="../../image/wish_card/card_birth_1.jpg"></div>
                                        <div class="swiper-slide pzpic"><IMG alt=""
                                                                             src="../../image/wish_card/card_birth_1.jpg"></div>
                                        <div class="swiper-slide pzpic"><IMG alt=""
                                                                             src="../../image/wish_card/card_birth_1.jpg"></div>
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
                                        <div class="layui-input-inline"><INPUT name="ActivityName"
                                                                               class="layui-input  mouseover" type="text">
                                        </div>
                                    </div>
                                    <div class="layui-form-item"><LABEL class="layui-form-label">祝福内容<SPAN
                                            class="red">*</SPAN></LABEL>
                                        <div class="layui-input-inline"><TEXTAREA name="wish"
                                                                                  class="layui-textarea  mouseover"
                                                                                  style="width: 500px; min-height: 60px;" maxlength="50"></TEXTAREA>
                                        </div>
                                    </div>
                                    <div class="layui-form-item"><LABEL class="layui-form-label">落款内容<SPAN
                                            class="red">*</SPAN></LABEL>
                                        <div class="layui-input-inline"><INPUT name="name"
                                                                               class="layui-input  mouseover" type="text">
                                        </div>
                                    </div>
                                    <div class="layui-form-item"><LABEL class="layui-form-label">发送时间<SPAN
                                            class="red">*</SPAN></LABEL>
                                        <div class="layui-input-inline sendTime" id="birth-time"><select
                                                name="sendTime">
                                            <option value="8">当日8点</option>
                                            <option value="12">当日12点</option>
                                            <option value="14">当日14点</option>
                                            <option value="18">当日18点</option>
                                        </select>
                                        </div>
                                        <input type="text" class="layui-input sendTime" id="festival-date"
                                               placeholder="yyyy-MM-dd HH:mm:ss" lay-key="6">
                                    </div>
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
                                <button class="layui-btn" id="uploadStaff" type="button">
                                    <I class="layui-icon"></I>导入信息 </button>
                            </div>
                            <form class="layui-form layui-col-md6 x-so" lay-filter="main-filter">
                                <div class="layui-input-inline" style="display: none;"><INPUT name="startTime"
                                                                                              class="layui-input" id="start" placeholder="开始日期" value=""></div>
                                <div class="layui-input-inline" style="display: none;"><INPUT name="endTime"
                                                                                              class="layui-input" id="end" placeholder="截止日期" value=""></div><button
                                    class="layui-btn" style="float: right;" type="button" lay-filter="searchStaff"
                                    lay-submit=""><I class="layui-icon"></I> </button>
                                <div class="layui-input-inline" style="float: right;"><INPUT name="staffName"
                                                                                             class="layui-input" type="text" placeholder="搜索名称" value=""
                                                                                             autocomplete="off"></div>
                            </form>

                            <table class="layui-table" id="staffTable" lay-filter="staffTable"></table>

                            <div class="btngroup">
                                <button class="layui-btn" id="btnsave" type="button" lay-filter="btnsave"
                                        lay-submit="">应用配置</button>
                                <button class="layui-btn" id="btnreturn" onclick="returnLast()"
                                        type="button">返回</button>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>
<div class="footer"></div>
<script src="../js/index.js"></script>
<script src="../js/common.js"></script>
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
        var table = layui.table;
        table.render({
            elem: '#staffTable'
            //TODO::员工信息接口
            , url: './getStaff' //获取员工信息
            , method: "post"
            , height: 100
            , cols: [[ //表头
                { field: 'xuhao', width: '100', title: '序号', templet: '#xuhao', rowspan: 2 }
                , { field: 'staffId', width: '200', title: '工号', align: 'center' }
                , { field: 'staffName', width: '200', title: '姓名', align: 'center' }
                , { field: 'staffPhone', width: '200', title: '手机号码', align: 'center', templet: formatDatetime }
                , { field: 'staffBirth', width: '200', title: '出生日期', align: 'center', templet: formatDatetime1 }
                , { field: 'activityUrl', width: '220', title: '活动链接', align: 'center' }
                , { fixed: 'right', width: '228', title: '操作', align: 'center', toolbar: '#barDemo' }
            ]]
        })
    })

    // 选择生日或者节日
    var type = 0;
    $('.card-type').click(e => {
        if (e.target.className == 'card-type') {
        type = e.target.id;
    } else if (e.target.className == 'choice-box') {
        type = e.target.parentElement.id;
    }
    // 0为生日，1为节日,

    if (type == 0) {
        $('.choose-festival').hide();
        $('#festival-date').hide();
        $('#birth-time').show();
    } else if (type == 1) {
        type = 1;
        $('.choose-festival').show();
        $('#birth-time').hide();
        $('#festival-date').show();
    }

    })

    // 日期选择器,只能选当天以后的日期
    var today = new Date().format('yyyy-MM-dd hh:mm:ss');

    layui.use('laydate', function () {
        var laydate = layui.laydate;

        //执行一个laydate实例
        laydate.render({
            elem: '#festival-date' //指定元素
            , type: 'datetime'
            , min: today
        });
    });



    /*
        参数解释：
        title   标题
        url     请求的url
        id      需要操作的数据id
        w       弹出层宽度（缺省调默认值）
        h       弹出层高度（缺省调默认值）
        type    操作类型 add edit examine
        data    传递的数据
    */
    function layerShow(title, url, w, h, type, data, reloadId, reloadUrl) {
        setLayerParams(type, data);
        var isCancel = false; // 用于判断是否是点击关闭按钮退出的弹出框
        if (title == null || title === '') {
            title = false;
        }
        if (url == null || url === '') {
            url = "404.html";
        }
        if (w == null || w === '') {
            w = ($(window).width() * 0.9);
        }
        if (h == null || h === '') {
            h = ($(window).height() - 50);
        }
        layer.open({
            type: 2,
            skin: 'layui-layer-molv',
            area: [w + 'px', h + 'px'],
            fix: true, //固定
            resize: false, // 是否允许拉伸
            maxmin: true,
            shadeClose: false,
            shade: 0.4,
            title: title,
            content: url,
            success: function (layero, index) {
                // console.log('reloadId = ', reloadId)
                // console.log('reloadUrl = ', reloadUrl)
                layui.config({
                    base: '../js/'
                }).use(['table'], function () {
                    var table = layui.table
                    //, common = layui.common;
                    table.reload(reloadId, {
                        url: reloadUrl //数据接口
                        //, where: {cid: 0, type : 1}
                    });
                });
            },
            cancel: function (index, layero) {
                isCancel = true;
                /*if(confirm('确定要关闭么')){ //只有当点击confirm框的确定时，该层才会关闭
                    layer.close(index)
                }
                return false;*/
            },
            end: function () {
                // 不是关闭按钮触发时，刷新table
                if (!isCancel) {
                    // 弹出框关闭时刷新
                    layui.config({
                        base: '../js/'
                    }).use(['table'], function () {
                        var table = layui.table
                        //, common = layui.common;
                        table.reload(reloadId, {
                            url: reloadUrl //数据接口
                            //, where: {cid: 0, type : 1}
                        });
                    });
                }
            }
        });
    }

    /*是否弹框控制*/
    var isEdit = 1;
    function IsDoNext(obj) {
        console.log('???')
        if (isEdit == 1) {
            switch (obj) {

                // case "addStaffExaList": layerShow('添加', './staffexamine/staffExamineOperation', 800, 500, 'add', '', 'staffExamineTableId', 'staffexamine/list'); break;
                case "addStaff": layerShow('添加', './staff/addStaff', 800, 500, 'add', '', 'staffTableId', 'staff/list'); break;
                case "importStaff": layerShow('导入员工', './staff/importStaff', 800, 500, 'import'); break;

            }
        } else {
            layer.open({
                title: '提示',
                type: 0,
                content: "请先配置好贺卡文案，再做相应的操作！！"
            });
        }

    }


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
        if (obj[0].name == "ActivityName") {
            note = "最多可输入10个字符";
        } else if (obj[0].name == "wishContent") {
            note = "最多可输入30个字符";
        } else if (obj[0].name == "byName") {
            note = "最多可输入10个字符";
        }
        layer.tips(note, this, { tips: [2, '#009688'] });
    });


    //检查提交的元素
    function check(data) {
        var content = "";
        console.log(data)
        if (data[0].value == "") {
            content = "请填写贺卡名称!";
        } else if (checklength(data[0].value) > 20) {
            content = "贺卡名称最多可输入10个字符";
        } else if (data[1].value == "") {
            content = "请填写祝福语";
        } else if (checklength(data[1].value) > 30) {
            content = "祝福语最多可输入30个字符";
        } else if (data[2].value == "") {
            content = "请填写落款内容";
        } else if (checklength(data[2].value) > 10) {
            content = "落款内容最多可输入30个字符";
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

    // 上传员工信息和添加时需要检查配置信息是否已经输入完成
    $('#addStaff').click(e => {
        // 获取文案配置内容
        var configArr = $('#text').serializeArray();
    //    检查配置内容
    var flag = check(configArr);
    // 填写完成后才可添加员工
    if (flag == true) {
        IsDoNext('addStaff');
    }
    });
    $('#uploadStaff').click(e => {
        // 获取文案配置内容
        var configArr = $('#text').serializeArray();
    //    检查配置内容
    var flag = check(configArr);
    // 填写完成后才可添加员工
    if (flag == true) {
        // TODO::上传函数
        console.log('可以上传');
        layui.use('upload', function () {
            var upload = layui.upload;
            upload.render({
                elem: "#uploadStaff",
                url: './staff/import?activityuid=' + activityUid,
                accept: 'file',
                exts: 'xls|xlsx',
                done: function (data) {
                    if (data.code == 1) {
                        data.message = "上传成功";
                    }
                    layer.open({
                        title: '提示',
                        type: 0,
                        content: data.message
                    });

                    tableIns3.reload({});
                }
            });
        })
    }
    })



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



        //监听表单提交 下载模板
        form.on('submit(download)', function (data) {

            window.location.href = './meeting/download'
            return false;
        });
    })


    // 提交配置和人员信息
    // TODO::接口
    $('#btnsave').click(e => {
        $.ajax({
        type: "POST",
        data: JSON.stringify(info),

        url: "./saveParaInfo",
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        async: false,
        complete: function (data) {
            if (data.responseJSON.code == 1) {
                window.location.href = './success?activityUid=' + data.responseJSON.message;
            } else {
                alert("fail");
            }
        }
    });
    })









</script>
</body>

</html>
